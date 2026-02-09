package test_scripts;

import applicationUtil.FixedMockTestUtil;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import util.Common_Function;
import util.ConfigFileReader;
import util.TestDescriptionConstant;

public class FixedMockTest extends BaseTest {

	FixedMockTestUtil fixedMockTestUtilObj;

	@Test(description = "validate FMT mock Register,Attempt,Resume functionality and Result screen.")
	public void verifyFreeFixedMockTest() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		fixedMockTestUtilObj = new FixedMockTestUtil(getDriver());
		result = fixedMockTestUtilObj.verifyFreeFixedMockTest(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, fixedMockTestUtilObj.fixedMockTestMsgList.toString());
	}

	@Test
	public void verifyFixedMockTestOnHomeFeed() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		fixedMockTestUtilObj = new FixedMockTestUtil(getDriver());
		result = fixedMockTestUtilObj.verifyFixedMockTestOnHomeFeed(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, fixedMockTestUtilObj.fixedMockTestMsgList.toString());
	}

	@Test(description = TestDescriptionConstant.verifyOptionFixMockTest)
	public void verifyOptionalFixedMockTest() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		fixedMockTestUtilObj = new FixedMockTestUtil(getDriver());
		result = fixedMockTestUtilObj.verifyOptionalFixMockTest(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, fixedMockTestUtilObj.fixedMockTestMsgList.toString());
	}
}