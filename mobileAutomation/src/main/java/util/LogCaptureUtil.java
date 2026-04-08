package util;

import java.io.*;
import java.util.regex.Pattern;

public class LogCaptureUtil {

    private Process logProcess;
    private ThreadLocal<String> fullLogFilePath = new ThreadLocal<>();
    private ThreadLocal<String> filteredLogFilePath = new ThreadLocal<>();
    private ThreadLocal<String> eventsLogFilePath = new ThreadLocal<>();
    private volatile boolean running = true;

    public void startLogCapture(String udid, String testName, String logRegexPattern) throws IOException {
        running = true;
        new File("filtered-logs").mkdirs();
        String logFilePath = "filtered-logs/" + testName + ".log";
        String fullLogPath = "filtered-logs/" + testName + "_full.log";
        String eventsPath = "filtered-logs/" + testName + "_events.log";
        filteredLogFilePath.set(logFilePath);
        fullLogFilePath.set(fullLogPath);
        eventsLogFilePath.set(eventsPath);

        // Build ADB command — skip -s flag if UDID is empty
        ProcessBuilder clearBuilder;
        ProcessBuilder builder;
        if (udid != null && !udid.isEmpty()) {
            clearBuilder = new ProcessBuilder("adb", "-s", udid, "logcat", "-c");
            builder = new ProcessBuilder("adb", "-s", udid, "logcat", "-v", "time");
        } else {
            clearBuilder = new ProcessBuilder("adb", "logcat", "-c");
            builder = new ProcessBuilder("adb", "logcat", "-v", "time");
        }

        // Clear old logs
        clearBuilder.redirectErrorStream(true);
        clearBuilder.start();

        builder.redirectErrorStream(true);
        logProcess = builder.start();

        Pattern pattern = Pattern.compile(logRegexPattern);
        // Pattern to strip ANSI color codes
        Pattern ansiPattern = Pattern.compile("\\x1B\\[[0-9;]*m|\\[\\d+;\\d+;?\\d*m");

        new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(logProcess.getInputStream()));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath, false));
                 BufferedWriter fullWriter = new BufferedWriter(new FileWriter(fullLogPath, false));
                 BufferedWriter eventsWriter = new BufferedWriter(new FileWriter(eventsPath, false))) {

                String line;
                StringBuilder eventBuffer = null;

                while (running && (line = reader.readLine()) != null) {
                    fullWriter.write(line);
                    fullWriter.newLine();

                    if (pattern.matcher(line).find()) {
                        writer.write(line);
                        writer.newLine();
                        writer.flush();
                    }

                    // Clean ANSI codes from line for event processing
                    String cleanLine = ansiPattern.matcher(line).replaceAll("");
                    cleanLine = cleanLine.replaceAll("[│├└┌┄─]+", "").trim();
                    cleanLine = cleanLine.replaceAll("💡\\s*", "");

                    // Detect start of a SendEvent line
                    if (cleanLine.contains("SendEvent:")) {
                        // Flush previous buffered event if any
                        if (eventBuffer != null) {
                            eventsWriter.write(eventBuffer.toString());
                            eventsWriter.newLine();
                            eventsWriter.flush();
                        }
                        // Extract from "SendEvent:" onwards, stripping the logcat/flutter prefix
                        String eventData = cleanLine.substring(cleanLine.indexOf("SendEvent:"));
                        eventBuffer = new StringBuilder(eventData);
                    } else if (eventBuffer != null) {
                        // Check if this is a continuation (same flutter log, not a decorator line)
                        if (line.contains("I/flutter") && !cleanLine.contains("┌") && !cleanLine.contains("└")
                                && !cleanLine.contains("├") && !cleanLine.isEmpty()
                                && !cleanLine.matches("^\\d{2}-\\d{2}\\s+\\d{2}:\\d{2}.*I/flutter.*#\\d+.*")) {
                            // Strip the logcat prefix from continuation line too
                            String cont = cleanLine;
                            int flutterIdx = cont.indexOf("I/flutter");
                            if (flutterIdx >= 0) {
                                int colonIdx = cont.indexOf(":", flutterIdx);
                                if (colonIdx >= 0) {
                                    cont = cont.substring(colonIdx + 1).trim();
                                }
                            }
                            eventBuffer.append(cont);
                        } else {
                            // New log line — flush the buffered event
                            eventsWriter.write(eventBuffer.toString());
                            eventsWriter.newLine();
                            eventsWriter.flush();
                            eventBuffer = null;
                        }
                    }
                }
                // Flush any remaining buffered event
                if (eventBuffer != null) {
                    eventsWriter.write(eventBuffer.toString());
                    eventsWriter.newLine();
                    eventsWriter.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void stopLogCapture() {
        running = false;
        if (logProcess != null) {
            logProcess.destroy();
        }
    }

    public String getFullLogFilePath() { return fullLogFilePath.get(); }
    public String getFilteredLogFilePath() { return filteredLogFilePath.get(); }
    public String getEventsLogFilePath() { return eventsLogFilePath.get(); }
}
