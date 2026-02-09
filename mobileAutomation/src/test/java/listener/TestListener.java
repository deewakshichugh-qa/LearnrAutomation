package listener;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import test_scripts.BaseTest;
import test_scripts.BaseTestWithLoginDeeplink;
import test_scripts.BaseTestWithoutLoginDeeplink;
import util.ExtentManager;

public class TestListener implements ITestListener {

	private static final ExtentReports extent = ExtentManager.createInstance();
	private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Override
	public synchronized void onStart(ITestContext context) {
		System.out.println("Test Suite started!");
	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		System.out.println(("Test Suite is ending!"));
		extent.flush();
	}

	@Override
	public synchronized void onTestStart(ITestResult result) {
		System.out.println(result);
		System.out.println(result.getTestName());
		System.out.println((result.getMethod().getMethodName() + " started!"));
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),result.getMethod().getDescription());
		test.set(extentTest);
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		System.out.println((result.getMethod().getMethodName() + " passed!"));
		test.get().pass("Test passed");
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		String path = null;
		String strSessionId = null;

		try {
			System.out.println(result.getMethod().getMethodName() + " failed!");

			Object instance = result.getInstance();
			AppiumDriver<MobileElement> driver = null;

			if (instance instanceof BaseTest) {
				driver = ((BaseTest) instance).getDriver();
			} else if (instance instanceof BaseTestWithLoginDeeplink) {
				driver = ((BaseTestWithLoginDeeplink) instance).getDriver();
			} else if (instance instanceof BaseTestWithoutLoginDeeplink) {
				driver = ((BaseTestWithoutLoginDeeplink) instance).getDriver();
			}

			if (driver != null) {
				try {
					strSessionId = driver.getSessionId() != null ? driver.getSessionId().toString() : "No session";
					path = GetScreenShot.capture(driver, result.getMethod().getMethodName());
					test.get().addScreenCaptureFromPath(path);
				} catch (Exception screenshotEx) {
					System.err.println("Screenshot capture failed: " + screenshotEx.getMessage());
					test.get().warning("Screenshot capture failed. Session: " + strSessionId);
				}
			}

			test.get().fail(result.getThrowable());

		} catch (Exception e) {
			e.printStackTrace();
			test.get().fail("Listener failure: " + e.getMessage());
		}
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		test.get().skip("Test Skipped: " + result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}
}