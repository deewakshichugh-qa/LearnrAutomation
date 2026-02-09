package test_scripts;

import applicationUtil.LoginUtil;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import util.Common_Function;
import util.ConfigFileReader;
import util.TestDescriptionConstant;

public class SignUpTest extends BaseTest {

	LoginUtil loginObj;

	@Test(description = TestDescriptionConstant.verifySignUp)
	public void verifySignUp() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		loginObj = new LoginUtil(getDriver());
		result = loginObj.verifySignUp(getDriver(), Common_Function.randomPhoneNumber(10, "8"), true);
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, loginObj.loginMsgList.toString());
	}
}
