package test_scripts;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import util.Common_Function;
import util.ConfigFileReader;
import util.ExtentManager;
import util.ExtentTestManager;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import util.LogCaptureUtil;

public class BaseTest {

    private final Common_Function commonFunction = new Common_Function();
    Common_Function cfObj = commonFunction;

    // ThreadLocal driver for parallel execution
    private final ThreadLocal<AppiumDriver<MobileElement>> driver = new ThreadLocal<>();
    private final ThreadLocal<String> deviceUdid = new ThreadLocal<>();

    protected ExtentReports extent;
    protected ExtentTest test;
    public static String screenshotFolderPath;
    private String relativePath;
    private final LogCaptureUtil logUtil = new LogCaptureUtil();

    // Set & Get the driver instance
    public void setDriver(AppiumDriver<MobileElement> driverInstance) {
        driver.set(driverInstance);
    }
    public AppiumDriver<MobileElement> getDriver() {
        return driver.get();
    }

    // Set & Get the UDID instance
    public void setDeviceUdid(String udid) {
        deviceUdid.set(udid);
    }
    public String getDeviceUdid() {
        return deviceUdid.get();
    }

    @BeforeSuite
    public void startSuite(ITestContext context) {
        try {
            extent = ExtentManager.getInstance();

            // Create a suite-level timestamped folder for screenshots
            String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
            screenshotFolderPath = System.getProperty("user.dir") + "/ErrorScreenshots/Run_" + timestamp;
            new File(screenshotFolderPath).mkdirs();

            try (FileWriter writer = new FileWriter("runfolder_timestamp.txt")) {
                writer.write(timestamp);
                System.out.println("Screenshot folder timestamp saved: " + timestamp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            throw new RuntimeException("Setup failed in @BeforeSuite", e);
        }
    }

    @Parameters({"port", "udid"})
    @BeforeMethod
    public void setUp(Method method, ITestContext context, @Optional("4723") String port, @Optional("") String udid) {

        try {
            System.out.println("--------------------------------------------------------");
            System.out.println("Executing Test: " + method.getName() + " | Port: " + port + " | UDID: " + udid);

            // Initialize Appium Driver
            AppiumDriver<MobileElement> appDriver = null;
            try {
                appDriver = cfObj.commonStartAndOpenURLBrowser(port, udid);
                setDriver(appDriver);
                setDeviceUdid(udid);
            } catch (Exception e) {
                throw new RuntimeException("Not able to to start Appium session on port: " + port + ", udid: " + udid, e);
            }
            if (appDriver == null) {
                throw new RuntimeException("Driver initialization failed for device: " + udid + " on port: " + port);
            }

            System.out.println("Session ID: " + getDriver().getSessionId());
            System.out.println("------------");

            if (ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {
                if (udid.isEmpty()) {

                    ExtentTest test = ExtentManager.getInstance().createTest(method.getName(), method.getAnnotation(Test.class) != null ? method.getAnnotation(Test.class).description() : "");
                    ExtentTestManager.setTest(test);
                }
            }

            // Set up cloud or local configurations
            if (ConfigFileReader.strRunMode.equalsIgnoreCase("cloud")) {
                getDriver().executeScript("lambda-name = " + method.getName());

                HashMap<String, Object> paramsRotate = new HashMap<>();
                paramsRotate.put("command", "autorotate");
                paramsRotate.put("enableAutoRotate", false);
                getDriver().executeScript("lambda-adb", paramsRotate);
            }

            // Capture network logs and pass ExtentTest instance to log them in the report
            if (ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {
                cfObj.captureNetworkLogs(method.getName(), ExtentTestManager.getTest());
                logUtil.startLogCapture(udid, method.getName(), "SendEvent:|trackEvent\\(\\)|MoEngage Event:|EventSDK|I/flutter|E/flutter|FATAL|ANR|API ERROR");
            }
            if(!ConfigFileReader.strRunMode.equalsIgnoreCase("localLab")){
                Thread.sleep(3000);
            }

            // Handle True caller installation
            boolean result = true;
            boolean isInstalled = getDriver().isAppInstalled("com.truecaller");

            if (isInstalled) {
                result = cfObj.commonWaitForElementToBeLocatedAndVisible(getDriver(), "//*[contains(@text,'USE ANOTHER MOBILE NUMBER')]", "xpath", 10);
                if (result) {
                    cfObj.commonClick(cfObj.commonGetElement(getDriver(), "//*[contains(@text,'USE ANOTHER MOBILE NUMBER')]", "xpath"));
                } else {
                    System.out.println("Truecaller installed but not visible");
                }
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(getDriver(), "//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]", "xpath", 10);
            if (result) {
                cfObj.commonClick(cfObj.commonGetElement(getDriver(), "//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_button\"]", "xpath"));
            }

            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), 30);
                String envText = ConfigFileReader.strEnv.toUpperCase();
                MobileElement envElement = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@content-desc,'" + envText + "')]")));
                envElement.click();

            } catch (org.openqa.selenium.TimeoutException te) {
                System.out.println("Timeout: Environment element '" + ConfigFileReader.strEnv.toUpperCase() + "' not found in 60 seconds.");

            } catch (org.openqa.selenium.NoSuchElementException ne) {
                System.out.println("NoSuchElementException: Couldn't find environment option. Check your UI or locator.");

            } catch (Exception e) {
                System.out.println("Unexpected error while selecting environment: " + e.getMessage());
            }
            System.out.println("--> Switched to " + ConfigFileReader.strEnv);
            String packageName = null;
            try {
                packageName = ((AndroidDriver<MobileElement>) getDriver()).getCurrentPackage();
            } catch (ClassCastException ce) {
                System.out.println("Driver is not AndroidDriver. Skipping package handling.");
            }

            // Reinitialize app
            if (ConfigFileReader.strRunMode.equalsIgnoreCase("cloud")) {
                try { getDriver().terminateApp(packageName); } catch (Exception ignored) {}
                Thread.sleep(1000);
                getDriver().activateApp(packageName);
            } else {
                if (getDriver().isAppInstalled(packageName)) {
                    try { getDriver().terminateApp(packageName); } catch (Exception ignored) {}
                    Thread.sleep(1000);
                    getDriver().activateApp(packageName);
                }
            }

            if (cfObj.commonWaitForElementToBeVisible(getDriver(), cfObj.commonGetElement(getDriver(), "com.android.permissioncontroller:id/permission_allow_button", "id"), 5)) {
                cfObj.commonClick(cfObj.commonGetElement(getDriver(), "com.android.permissioncontroller:id/permission_allow_button", "id"));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed in setUp", e);
        } finally {
            System.out.println("setUp finished.");
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        int retryLimit = Integer.parseInt(ConfigFileReader.retryCount);
        try {
            if (ConfigFileReader.strRunMode.equalsIgnoreCase("local") ||
                    (!ConfigFileReader.strEnv.equalsIgnoreCase("production"))) {

                // If retryLimit is 0, log results normally without retry handling
                if (retryLimit == 0) {
                    if (result.getStatus() == ITestResult.FAILURE) {
                        captureScreenshot(result.getName());
                        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + result.getThrowable());
                        ExtentTestManager.getTest().fail("Screenshot",
                                MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
                        boolean apiFailure = analyzeFlutterApiFailures("com.adda247.gold", result);
                        if (apiFailure) {
                            result.setStatus(ITestResult.FAILURE);
                        }
                    } else if (result.getStatus() == ITestResult.SUCCESS) {
                        ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
                    } else if (result.getStatus() == ITestResult.SKIP) {
                        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped: " + result.getThrowable());
                    }
                    attachDeviceLogs();
                } else {
                    if (result.getStatus() == ITestResult.SKIP && result.getAttribute("isRetried") != null && (boolean) result.getAttribute("isRetried")) {
                        ExtentTestManager.getTest().log(Status.PASS, "Test passed after retry");
                    } else if (result.getStatus() == ITestResult.FAILURE) {
                        captureScreenshot(result.getName());
                        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + result.getThrowable());
                    } else if (result.getStatus() == ITestResult.SUCCESS) {
                        ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
                    } else if (result.getStatus() == ITestResult.SKIP) {
                        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped: " + result.getThrowable());
                    }
                    attachDeviceLogs();
                }
                analyzeFullLogsForCrash(result, "com.adda247.gold");
                ExtentTestManager.removeTest();
            }
        } catch (Exception e) {
            System.out.println("Error in tearDown: " + e.getMessage());
        } finally {
            logUtil.stopLogCapture();
            try {
                AppiumDriver<MobileElement> appDriver = getDriver();
                if (appDriver != null) {
                    Thread.sleep(500);
                    appDriver.quit();
                }
            } catch (Exception e) {
                System.out.println("Driver already closed or error while quitting: " + e.getMessage());
            }
            System.out.println("--------------------------------------------------------");
        }
    }

    @AfterSuite
    public void endSuite() {
        try {
            // Flush the extent report and print the report link
            ExtentManager.flushReport();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void captureScreenshot(String testName) {
        File screenshot = getDriver().getScreenshotAs(OutputType.FILE);
        String safeTestName = testName.replaceAll("[^a-zA-Z0-9.\\-]", "_");
        String destFile = System.getProperty("user.dir") + "/TestReport/Screenshots/" + safeTestName + Common_Function.getTimestamp() + ".png";
        relativePath = "Screenshots/" + safeTestName + Common_Function.getTimestamp() + ".png";
        try {
            FileUtils.copyFile(screenshot, new File(destFile));
        } catch (IOException e) {
            System.out.println("Could not capture screenshot: " + e.getMessage());
        }
    }
    private void attachDeviceLogs() {
        try {
            String logPath = logUtil.getFilteredLogFilePath();
            if (logPath != null && Files.exists(Paths.get(logPath))) {
                String logs = new String(Files.readAllBytes(Paths.get(logPath)));
                ExtentTestManager.getTest()
                        .info("Device Logs:")
                        .info(MarkupHelper.createCodeBlock(logs));
            }

            String eventsPath = logUtil.getEventsLogFilePath();
            if (eventsPath != null && Files.exists(Paths.get(eventsPath))) {
                String events = new String(Files.readAllBytes(Paths.get(eventsPath)));
                if (!events.trim().isEmpty()) {
                    ExtentTestManager.getTest()
                            .info("Captured Events:")
                            .info(MarkupHelper.createCodeBlock(events));
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to attach logs: " + e.getMessage());
        }
    }
    private void analyzeFullLogsForCrash(ITestResult result, String packageName) {

        try {
            Path path = Paths.get(logUtil.getFullLogFilePath());

            if (!Files.exists(path)) {
                return;
            }

            List<String> lines = Files.readAllLines(path);

            // Only analyze last 800 lines (performance safe)
            int start = Math.max(lines.size() - 800, 0);
            lines = lines.subList(start, lines.size());

            boolean crashDetected = false;
            boolean anrDetected = false;

            StringBuilder crashBlock = new StringBuilder();

            for (int i = 0; i < lines.size(); i++) {

                String line = lines.get(i);

                // 🚨 Detect FATAL EXCEPTION
                if (line.contains("FATAL EXCEPTION")) {

                    // Check next few lines for your package
                    for (int j = i; j < i + 5 && j < lines.size(); j++) {

                        if (lines.get(j).contains("Process: " + packageName)) {

                            crashDetected = true;

                            crashBlock.append("---- APP CRASH DETECTED ----\n");

                            // Extract stacktrace block
                            for (int k = i; k < lines.size(); k++) {

                                String crashLine = lines.get(k);

                                crashBlock.append(crashLine).append("\n");

                                if (crashLine.trim().isEmpty()) {
                                    break; // stop at blank line
                                }
                            }
                            break;
                        }
                    }
                }

                // 🚨 Detect ANR
                if (line.contains("ANR in " + packageName)) {

                    anrDetected = true;

                    crashBlock.append("---- ANR DETECTED ----\n")
                            .append(line)
                            .append("\n");
                }
            }

            // 🚨 If crash or ANR found → Fail test
            if (crashDetected || anrDetected) {

                ExtentTestManager.getTest()
                        .fail("🚨 Application Stability Issue Detected");

                ExtentTestManager.getTest()
                        .fail(MarkupHelper.createCodeBlock(crashBlock.toString()));

                result.setStatus(ITestResult.FAILURE);
            }

        } catch (Exception e) {
            System.out.println("Crash analysis error: " + e.getMessage());
        }
    }
private boolean analyzeFlutterApiFailures(String packageName, ITestResult result) {

    try {
        Path path = Paths.get(logUtil.getFullLogFilePath());

        if (!Files.exists(path)) return false;

        List<String> lines = Files.readAllLines(path);

        int start = Math.max(lines.size() - 3000, 0);
        lines = lines.subList(start, lines.size());

        boolean apiFailureDetected = false;
        StringBuilder apiBlock = new StringBuilder();
        boolean insideApiErrorBlock = false;

        for (String line : lines) {

            if (!line.contains("I/flutter")) continue;

            // Start of API error block
            if (line.contains("API ERROR DATA")) {
                System.out.println(line);
                insideApiErrorBlock = true;
                apiFailureDetected = true;
                apiBlock.append("\n======= API FAILURE DETECTED =======\n");
            }

            if (insideApiErrorBlock) {
                apiBlock.append(line).append("\n");

                // End block when separator found
                if (line.contains("RESPONSE_DATA")) {
                    insideApiErrorBlock = false;
                }
            }

            // Detect 5xx directly
            if (line.contains("503") ||
                    line.contains("500") ||
                    line.contains("502") || line.contains("404") || line.contains("400") || line.contains("403")) {

                apiFailureDetected = true;
                apiBlock.append(line).append("\n");
            }

            // Detect HTML error page
            if (line.contains("Service Temporarily Unavailable") ||
                    line.contains("<html>")) {

                apiFailureDetected = true;
                apiBlock.append(line).append("\n");
            }
        }

        if (apiFailureDetected) {
            System.out.println(apiBlock.toString());
            ExtentTestManager.getTest()
                    .fail("🌐 Backend API Failure Detected From Device Logs");

            ExtentTestManager.getTest()
                    .fail(MarkupHelper.createCodeBlock(apiBlock.toString()));

            ExtentTestManager.getTest()
                    .assignCategory("API Failure");

            result.setStatus(ITestResult.FAILURE);
        }
        return apiFailureDetected;

    } catch (Exception e) {
        System.out.println("Flutter API analysis error: " + e.getMessage());
        return false;
    }
}
}
