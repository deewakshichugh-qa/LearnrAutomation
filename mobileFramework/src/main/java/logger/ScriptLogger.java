package logger;

import java.io.File;
import java.util.List;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class ScriptLogger {

	private static Logger infologger = Logger.getLogger("info");
	private static Logger debuglogger = Logger.getLogger("debug");
	private static Logger errorlogger = Logger.getLogger("error");
	private static Logger warnlogger = Logger.getLogger("warn");

	static {

		String filepath = "target" + File.separator;
		initializeLogger(infologger, filepath + "info.log");
		initializeLogger(debuglogger, filepath + "debug.log");
		initializeLogger(errorlogger, filepath + "error.log");
		initializeLogger(warnlogger, filepath + "warn.log");
		consoleAppender(infologger);
		consoleAppender(debuglogger);
		consoleAppender(errorlogger);
		consoleAppender(warnlogger);

	}

	private static void initializeLogger(Logger logger, String file) {
		String pattern = "%d [%p] %m%n";
		Layout layout = new PatternLayout(pattern);
		FileAppender appender = new FileAppender();
		appender.setLayout(layout);
		appender.setFile(file);
		appender.setAppend(false);
		appender.activateOptions();
		logger.addAppender(appender);
	}

	private static void consoleAppender(Logger logger) {
		String conversionPattern = "%d [%p] %m%n";
		Logger rootLogger = Logger.getLogger(ScriptLogger.class.getName());
		rootLogger.addAppender(new ConsoleAppender(new PatternLayout(conversionPattern)));
		/*
		 * ConsoleAppender console = new ConsoleAppender(); PatternLayout layout = new
		 * PatternLayout(conversionPattern);
		 * layout.setConversionPattern(conversionPattern); console.setLayout(layout);
		 * console.activateOptions(); logger.addAppender(console);
		 */
	}

	public static void info(String message) {
		infologger.setLevel(Level.INFO);
		infologger.info(getClassMethodInfo() + ": " + message);
	}

	public static void info() {
		infologger.setLevel(Level.INFO);
		infologger.info(getClassMethodInfo());
	}

	public static void debug(String message) {
		debuglogger.setLevel(Level.DEBUG);
		debuglogger.debug(getClassMethodInfo() + ": " + message);
	}

	public static void debug() {
		debuglogger.setLevel(Level.DEBUG);
		debuglogger.debug(getClassMethodInfo());
	}

	public static void error(Exception e) {
		errorlogger.setLevel(Level.ERROR);
		StringBuffer buffer = new StringBuffer("\n\n" + e.getClass().getName() + ":" + e.getMessage() + "\n");
		for (StackTraceElement ste : e.getStackTrace()) {
			buffer.append(ste.toString() + "\n");
		}

		errorlogger.error(buffer.toString());

	}

	public static void warn(String message) {
		warnlogger.setLevel(Level.WARN);
		warnlogger.warn(message);
	}

	private static String getClassMethodInfo() {

		String s = "";
		final StackTraceElement[] ste = Thread.currentThread().getStackTrace();

		String classname = ste[3].getClassName();
		String methodname = ste[3].getMethodName();

		s = classname + ":" + methodname + "()";
		return s;
	}

	public static void startTestCase(int i) {

		info("                                 ");
		info("-----------------------------------------------------------");
		info("                     Test Case #" + i + "                  ");
		info("-----------------------------------------------------------");
		info("                                 ");

	}

	public static void endTestCase() {
		info("                                 ");
		info("------------------------------------------------------------");
		info("          Ending test case: " + Thread.currentThread().getStackTrace()[2].getMethodName());
		info("------------------------------------------------------------");
		info("                                 ");
	}
}
