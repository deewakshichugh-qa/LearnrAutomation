package util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class EventValidationUtil {

    private final LogCaptureUtil logCaptureUtil;
    public ArrayList<String> eventMsgList = new ArrayList<>();

    public EventValidationUtil(LogCaptureUtil logCaptureUtil) {
        this.logCaptureUtil = logCaptureUtil;
    }

    /**
     * Gets all captured events from the events log file.
     * Returns list of raw event lines.
     */
    public List<String> getAllCapturedEvents() {
        List<String> events = new ArrayList<>();
        try {
            String eventsPath = logCaptureUtil.getEventsLogFilePath();
            if (eventsPath != null && Files.exists(Paths.get(eventsPath))) {
                events = Files.readAllLines(Paths.get(eventsPath));
            }
        } catch (Exception e) {
            System.out.println("Error reading events log: " + e.getMessage());
        }
        return events;
    }

    /**
     * Gets the current event count — useful for marking a checkpoint
     * so you can later validate only events fired after a specific action.
     */
    public int getEventCheckpoint() {
        return getAllCapturedEvents().size();
    }

    /**
     * Gets events captured after a given checkpoint index.
     */
    public List<String> getEventsAfterCheckpoint(int checkpoint) {
        List<String> allEvents = getAllCapturedEvents();
        if (checkpoint >= allEvents.size()) {
            return new ArrayList<>();
        }
        return allEvents.subList(checkpoint, allEvents.size());
    }
