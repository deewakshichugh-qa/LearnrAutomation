package util;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class EventValidationUtil {

    private final LogCaptureUtil logCaptureUtil;
    public ArrayList<String> eventMsgList = new ArrayList<>();

    public EventValidationUtil(LogCaptureUtil logCaptureUtil) {
        this.logCaptureUtil = logCaptureUtil;
    }

    /** Returns current event count. Call before an action to mark a checkpoint. */
    public int getEventCheckpoint() {
        return readAllEvents().size();
    }

    /** Check if event was fired after the checkpoint. */
    public boolean wasEventFiredAfter(String eventName, int checkpoint) {
        return getEventAfter(eventName, checkpoint) != null;
    }

    /**
     * Validate event fired after checkpoint with expected attributes.
     * Returns null if valid, error message otherwise.
     * Pass null for expectedAttributes to just check if event was fired.
     */
    public String validateEventAfter(String eventName, int checkpoint, Map<String, String> expectedAttributes) {
        Map<String, String> event = getEventAfter(eventName, checkpoint);
        if (event == null) {
            return "Event '" + eventName + "' was not fired after the action";
        }
        if (expectedAttributes == null || expectedAttributes.isEmpty()) {
            return null;
        }
        StringBuilder errors = new StringBuilder();
        for (Map.Entry<String, String> entry : expectedAttributes.entrySet()) {
            String actual = event.get(entry.getKey());
            if (actual == null) {
                errors.append("Attribute '").append(entry.getKey()).append("' not found in '").append(eventName).append("'. ");
            } else if (!entry.getValue().equals(actual)) {
                errors.append("'").append(entry.getKey()).append("' expected '").append(entry.getValue())
                        .append("' but got '").append(actual).append("'. ");
            }
        }
        return errors.length() == 0 ? null : errors.toString();
    }

    /**
     * Validate that specific attributes exist and are not null/empty in an event fired after checkpoint.
     * Returns null if valid, error message otherwise.
     */
    public String validateAttributesNotNull(String eventName, int checkpoint, List<String> attributeKeys) {
        Map<String, String> event = getEventAfter(eventName, checkpoint);
        if (event == null) {
            return "Event '" + eventName + "' was not fired after the action";
        }
        StringBuilder errors = new StringBuilder();
        for (String key : attributeKeys) {
            String actual = event.get(key);
            if (actual == null || actual.isEmpty()) {
                errors.append("Attribute '").append(key).append("' is missing or empty in '").append(eventName).append("'. ");
            }
        }
        return errors.length() == 0 ? null : errors.toString();
    }

    /**
     * Validate event attributes using contains matching (handles logcat truncation).
     * Returns null if valid, error message otherwise.
     */
    public String validateEventContains(String eventName, int checkpoint, Map<String, String> expectedAttributes) {
        Map<String, String> event = getEventAfter(eventName, checkpoint);
        if (event == null) {
            return "Event '" + eventName + "' was not fired after the action";
        }
        if (expectedAttributes == null || expectedAttributes.isEmpty()) {
            return null;
        }
        StringBuilder errors = new StringBuilder();
        for (Map.Entry<String, String> entry : expectedAttributes.entrySet()) {
            String actual = event.get(entry.getKey());
            if (actual == null) {
                // Key not found — might be truncated, check if any key starts with expected key
                boolean found = false;
                for (Map.Entry<String, String> e : event.entrySet()) {
                    if (e.getKey().startsWith(entry.getKey()) || entry.getKey().startsWith(e.getKey())) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    errors.append("Attribute '").append(entry.getKey()).append("' not found in '").append(eventName).append("'. ");
                }
            } else if (!actual.contains(entry.getValue()) && !entry.getValue().contains(actual)) {
                errors.append("'").append(entry.getKey()).append("' expected to contain '").append(entry.getValue())
                        .append("' but got '").append(actual).append("'. ");
            }
        }
        return errors.length() == 0 ? null : errors.toString();
    }

    /** Get a specific attribute value from an event fired after checkpoint. Returns null if not found. */
    public String getEventAttribute(String eventName, int checkpoint, String attributeKey) {
        Map<String, String> event = getEventAfter(eventName, checkpoint);
        if (event == null) return null;
        return event.get(attributeKey);
    }

    // ---- Internal ----

    private Map<String, String> getEventAfter(String eventName, int checkpoint) {
        List<Map<String, String>> all = readAllEvents();
        for (int i = all.size() - 1; i >= checkpoint; i--) {
            if (eventName.equals(all.get(i).get("event_name"))) {
                return all.get(i);
            }
        }
        return null;
    }

    private List<Map<String, String>> readAllEvents() {
        List<Map<String, String>> events = new ArrayList<>();
        try {
            String path = logCaptureUtil.getEventsLogFilePath();
            if (path == null || !Files.exists(Paths.get(path))) return events;
            for (String line : Files.readAllLines(Paths.get(path))) {
                Map<String, String> parsed = parseLine(line);
                if (parsed != null) events.add(parsed);
            }
        } catch (Exception e) {
            System.out.println("EventValidationUtil read error: " + e.getMessage());
        }
        return events;
    }

    /** Parse: SendEvent: event_name | Attributes: {key: value, ...} */
    private Map<String, String> parseLine(String line) {
        if (!line.contains("SendEvent:")) return null;
        Map<String, String> result = new HashMap<>();
        try {
            String afterSend = line.substring(line.indexOf("SendEvent:") + 10).trim();
            // Extract event name: between "SendEvent: " and " |" or " null"
            int pipeIdx = afterSend.indexOf(" |");
            int nullIdx = afterSend.indexOf(" null");
            int endIdx = -1;
            if (pipeIdx > 0) endIdx = pipeIdx;
            else if (nullIdx > 0) endIdx = nullIdx;
            if (endIdx > 0) {
                result.put("event_name", afterSend.substring(0, endIdx).trim());
            }
            int attrIdx = line.indexOf("Attributes: {");
            if (attrIdx < 0) return result;
            String block = line.substring(attrIdx + 13);
            int lastBrace = block.lastIndexOf("}");
            if (lastBrace >= 0) block = block.substring(0, lastBrace);
            parseAttributes(block, result);
        } catch (Exception e) {
            System.out.println("EventValidationUtil parse error: " + e.getMessage());
        }
        return result;
    }

    private void parseAttributes(String block, Map<String, String> result) {
        int i = 0;
        while (i < block.length()) {
            int colonIdx = block.indexOf(": ", i);
            if (colonIdx < 0) break;
            String key = block.substring(i, colonIdx).trim();
            if (key.startsWith(",")) key = key.substring(1).trim();
            int valueStart = colonIdx + 2;

            if (valueStart < block.length() && block.charAt(valueStart) == '[') {
                int depth = 0, j = valueStart;
                while (j < block.length()) {
                    if (block.charAt(j) == '[') depth++;
                    else if (block.charAt(j) == ']' && --depth == 0) { j++; break; }
                    j++;
                }
                result.put(key, block.substring(valueStart, j).trim());
                i = j;
            } else {
                int nextComma = findNextKeyComma(block, valueStart);
                if (nextComma < 0) {
                    result.put(key, block.substring(valueStart).trim());
                    break;
                }
                result.put(key, block.substring(valueStart, nextComma).trim());
                i = nextComma + 1;
            }
        }
    }

    private int findNextKeyComma(String s, int from) {
        for (int i = from; i < s.length(); i++) {
            if (s.charAt(i) == ',' && s.substring(i + 1).trim().matches("^[a-zA-Z_]\\w*:.*")) {
                return i;
            }
        }
        return -1;
    }
}
