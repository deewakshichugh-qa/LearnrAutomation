package test_scripts;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class BaseTestWithoutLoginDeeplink {

    private final Common_Function commonFunction = new Common_Function();
    Common_Function cfObj = commonFunction;

    // ThreadLocal driver for parallel execution
    private final ThreadLocal<AppiumDriver<MobileElement>> driver = new ThreadLocal<>();
    private final ThreadLocal<String> deviceUdid = new ThreadLocal<>();

    protected ExtentReports extent;
    protected ExtentTest test;
    public static String screenshotFolderPath;

    // Set the driver instance
    public void setDriver(AppiumDriver<MobileElement> driverInstance) {
        driver.set(driverInstance);
    }

    // Get the driver instance
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
            } catch (Exception e) {
                throw new RuntimeException("Not able to start Appium session on port: " + port + ", udid: " + udid, e);
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

            // Handle Truecaller installation
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

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(getDriver(), "//android.widget.ImageView[@content-desc=\"Cancel\"]", "xpath", 5);
            if (result) {
                cfObj.commonClick(cfObj.commonGetElement(getDriver(), "//android.widget.ImageView[@content-desc=\"Cancel\"]", "xpath"));
            }

            try {
                WebDriverWait wait = new WebDriverWait(getDriver(), 60);
                String envText = ConfigFileReader.strEnv.toUpperCase();
                MobileElement envElement = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@text,'" + envText + "')]")));
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
                getDriver().terminateApp(packageName);
            } else {
                if (getDriver().isAppInstalled(packageName)) {
                    getDriver().terminateApp(packageName);
                }
            }

            if (cfObj.commonWaitForElementToBeVisible(getDriver(), cfObj.commonGetElement(getDriver(), "com.android.permissioncontroller:id/permission_allow_button", "id"), 5)) {
                cfObj.commonClick(cfObj.commonGetElement(getDriver(), "com.android.permissioncontroller:id/permission_allow_button", "id"));
            }
        } catch (Exception e) {
        } finally {
            System.out.println("setUp finished.");
        }
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        int retryLimit = Integer.parseInt(ConfigFileReader.retryCount);
        try {
            if (ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {

                // If retryLimit is 0, log results normally without retry handling
                if (retryLimit == 0) {
                    if (result.getStatus() == ITestResult.FAILURE) {
                        captureScreenshot(result.getName());
                        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed: " + result.getThrowable());
                    } else if (result.getStatus() == ITestResult.SUCCESS) {
                        ExtentTestManager.getTest().log(Status.PASS, "Test Passed");
                    } else if (result.getStatus() == ITestResult.SKIP) {
                        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped: " + result.getThrowable());
                    }
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
                }
                ExtentTestManager.removeTest();
            }
        } catch (Exception e) {
            System.out.println("Error during result reporting: " + e.getMessage());
        } finally {
            try {
                AppiumDriver<MobileElement> appDriver = getDriver();
                if (appDriver != null) {
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
        try {
            FileUtils.copyFile(screenshot, new File(destFile));
        } catch (IOException e) {
            System.out.println("Could not capture screenshot: " + e.getMessage());
        }
    }
}