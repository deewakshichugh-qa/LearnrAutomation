package test_scripts;

import applicationUtil.HomePageUtil;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import util.Common_Function;
import util.ConfigFileReader;
import util.TestDescriptionConstant;

public class HomeTest extends BaseTest {

	HomePageUtil homeUtilObj;

	@Test(description = TestDescriptionConstant.verfiyHomePageUI)
	public void verifyHomePageUI() {
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios")) {
			throw new SkipException("Skipping test because this feature is not Suitable for iOS.");
		}
		boolean result = true;
		homeUtilObj = new HomePageUtil(getDriver());
		result = homeUtilObj.verifyHomePageUI(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
		Assert.assertTrue(result, homeUtilObj.homeMsgList.toString());
	}

	@Test(description = TestDescriptionConstant.verfiySideNavigationDrawerComponent)
	public void verifySideNavigationDrawerComponent_Part2() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios")) {
			throw new SkipException("Skipping test because this feature is not Suitable for iOS.");
		}
		homeUtilObj = new HomePageUtil(getDriver());
		result = homeUtilObj.verifySideNavigationDrawerComponent_Part2(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
		Assert.assertTrue(result, homeUtilObj.homeMsgList.toString());
	}

	// Redesign the flow
	@Test(description = TestDescriptionConstant.verfiySideNavigationDrawerComponent, enabled = false)
	public void verifySideNavigationDrawerComponent_Part1() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios")) {
			throw new SkipException("Skipping test because this feature is not Suitable for iOS.");
		}
		homeUtilObj = new HomePageUtil(getDriver());
		result = homeUtilObj.validateSideNavigationDrawerComponent_Part1(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
		Assert.assertTrue(result, homeUtilObj.homeMsgList.toString());
	}

	@Test (enabled = false)
	public void verifyHomePageUIOnIOS() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
			throw new SkipException("Skipping test because this feature is not Suitable for Android.");
		}
		homeUtilObj = new HomePageUtil(getDriver());
		result = homeUtilObj.verifyHomePageUIOnIOS(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
		Assert.assertTrue(result, homeUtilObj.homeMsgList.toString());
	}
}