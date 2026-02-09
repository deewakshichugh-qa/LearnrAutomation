package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.Platform;

public class ExtentManager {

	private static ExtentReports extent;
	private static Platform platform;

	// Set up report paths for Windows and Mac
	private static final String reportFileName = "AppAutomationReport_" + getTimestamp() + ".html";
	private static final String macPath = System.getProperty("user.dir") + "/TestReport";
	private static final String windowsPath = System.getProperty("user.dir") + "\\TestReport";

	private static final String macReportFileLoc = macPath + "/" + reportFileName;
	private static final String winReportFileLoc = windowsPath + "\\" + reportFileName;

	private ExtentManager() {
		// Private constructor to prevent instantiation
	}

	// Create an instance of ExtentReports
	public static ExtentReports createInstance() {
		platform = getCurrentPlatform();
		String fileName = getReportFileLocation(platform);
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(fileName);

		// Customize report configurations
		sparkReporter.config().setTheme(Theme.STANDARD);
		sparkReporter.config().setDocumentTitle("Automation Test Report");
		sparkReporter.config().setEncoding("utf-8");
		sparkReporter.config().setReportName("Appium Test Execution Report");

		// Initialize ExtentReports
		if (extent == null) {
			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);
		}
		return extent;
	}

	private static String getTimestamp() {
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		// Save the timestamp to a file for Jenkins to read
		try (FileWriter writer = new FileWriter("timestamp.txt")) {
			writer.write(timestamp);
			System.out.println("Timestamp saved: " + timestamp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return timestamp;
	}

	// Determine the report file location based on the platform
	private static String getReportFileLocation(Platform platform) {
		String reportFileLocation = null;
		switch (platform) {
		case MAC:
			reportFileLocation = macReportFileLoc;
			createReportPath(macPath);
			break;
		case WINDOWS:
			reportFileLocation = winReportFileLoc;
			createReportPath(windowsPath);
			break;
		default:
			reportFileLocation = macReportFileLoc;
			createReportPath(macPath);
			break;
		}
		return reportFileLocation;
	}

	// Get the current platform (OS)
	private static Platform getCurrentPlatform() {
		if (platform == null) {
			String os = System.getProperty("os.name").toLowerCase();
			if (os.contains("win")) {
				platform = Platform.WINDOWS;
			} else if (os.contains("mac")) {
				platform = Platform.MAC;
			} else {
				platform = Platform.LINUX;
			}
		}
		return platform;
	}

	private static void createReportPath(String path) {
		File testDirectory = new File(path);
		if (!testDirectory.exists()) {
			if (testDirectory.mkdirs()) {
				System.out.println("Directory created at: " + path);
			} else {
				System.out.println("Failed to create directory: " + path);
			}
		}

		// Create the Screenshots directory
		File screenshotsDirectory = new File(path + "/Screenshots");
		if (!screenshotsDirectory.exists()) {
			if (screenshotsDirectory.mkdirs()) {
				System.out.println("Screenshots directory created at: " + screenshotsDirectory.getPath());
			} else {
				System.out.println("Failed to create Screenshots directory: " + screenshotsDirectory.getPath());
			}
		}
	}

	// Flush the report
	public static void flushReport() {
		if (extent != null) {
			extent.flush();
			String reportPath = getReportFileLocation(platform);
			System.out.println("Extent report generated at: " + reportPath);
		} else {
			System.out.println("Extent report instance is not initialized.");
		}
	}

	public synchronized static ExtentReports getInstance() {
		if (extent == null) {
			extent = createInstance();
		}
		return extent;
	}

}