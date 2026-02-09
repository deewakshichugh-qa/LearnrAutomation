package test_scripts;

import applicationUtil.ReferAndEarnUtil;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import util.Common_Function;
import util.ConfigFileReader;

public class ReferAndEarnTest extends BaseTest {

	ReferAndEarnUtil referAndEarnUtilObj;

	/*
	 FYI - Don't keep 100% coupon visible
	 */

	@Test
	public void verifyReferAndEarnPage_SelfCodeInCourse() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios")) {
			throw new SkipException("Skipping test because this feature can not run on ios.");
		}
		referAndEarnUtilObj = new ReferAndEarnUtil(getDriver());
		result = referAndEarnUtilObj.verifyReferAndEarnPage_SelfCodeInCourse(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, referAndEarnUtilObj.referAndEarnMsgList.toString());
	}

	@Test
	public void verifyAnotherCodeInCourse() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		referAndEarnUtilObj = new ReferAndEarnUtil(getDriver());
		result = referAndEarnUtilObj.verifyAnotherCodeInCourse_ReferAndEarn(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
		Assert.assertTrue(result, referAndEarnUtilObj.referAndEarnMsgList.toString());
	}

	@Test
	public void verifyReferNowLinkUsingCampaign() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		referAndEarnUtilObj = new ReferAndEarnUtil(getDriver());
		result = referAndEarnUtilObj.verifyReferNowLinkUsingCampaign(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, referAndEarnUtilObj.referAndEarnMsgList.toString());
	}
}