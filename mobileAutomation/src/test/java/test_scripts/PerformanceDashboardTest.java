package test_scripts;

import applicationUtil.PerformanceDashboardUtil;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import util.Common_Function;
import util.ConfigFileReader;

public class PerformanceDashboardTest extends BaseTest {

	PerformanceDashboardUtil performanceDashboardUtilObj;

	@Test
	public void verifyPDwithoutAnyPurchase() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production");
		}
		performanceDashboardUtilObj = new PerformanceDashboardUtil(getDriver());
		result = performanceDashboardUtilObj.verifyPerformanceDashboard(getDriver(), false);
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, performanceDashboardUtilObj.performanceDashboardMsgList.toString());
	}

	// No locators present in PD
	@Test(enabled = false)
	public void verifyPDwhenPurchased() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		performanceDashboardUtilObj = new PerformanceDashboardUtil(getDriver());
		result = performanceDashboardUtilObj.verifyPerformanceDashboard(getDriver(), true);
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, performanceDashboardUtilObj.performanceDashboardMsgList.toString());
	}
}
