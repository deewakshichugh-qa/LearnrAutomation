package test_scripts;

import applicationUtil.HomePageUtil;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import util.Common_Function;
import util.ConfigFileReader;
import util.TestDescriptionConstant;

public class LogoutTest extends BaseTest {

	HomePageUtil homeUtilObj;
	
	@Test(description = TestDescriptionConstant.verifyLogout)
	public void verifyLogout() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios")) {
			throw new SkipException("Skipping test because this feature can not run on ios");
		}
		homeUtilObj = new HomePageUtil(getDriver());
		result = homeUtilObj.verifyLogout(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, homeUtilObj.homeMsgList.toString());
	}
}