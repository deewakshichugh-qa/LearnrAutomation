package test_scripts;

import applicationUtil.UpdateValidityUtil;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import util.Common_Function;
import util.ConfigFileReader;

public class UpdateValidityTest extends BaseTest {

	UpdateValidityUtil updateValidityObj;

	@Test
	public void verifyDoubleValidityFlow() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production");
		}
		updateValidityObj = new UpdateValidityUtil(getDriver());
		result = updateValidityObj.verifyValidityFlow(getDriver(), 2, 0);
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, updateValidityObj.updateValidityMsgList.toString());
	}

	@Test
	public void verifyTripleValidityFlow() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production");
		}
		updateValidityObj = new UpdateValidityUtil(getDriver());
		result = updateValidityObj.verifyValidityFlow(getDriver(), 3, 0);
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, updateValidityObj.updateValidityMsgList.toString());
	}

	@Test
	public void verifyValidityWithExtensions() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production");
		}
		updateValidityObj = new UpdateValidityUtil(getDriver());
		result = updateValidityObj.verifyValidityFlow(getDriver(), 2, 8);
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, updateValidityObj.updateValidityMsgList.toString());
	}

	@Test
	public void verifyExtensionsValidityFlow() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production");
		}
		updateValidityObj = new UpdateValidityUtil(getDriver());
		result = updateValidityObj.verifyValidityFlow(getDriver(), 1, 8);
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, updateValidityObj.updateValidityMsgList.toString());
	}
}
