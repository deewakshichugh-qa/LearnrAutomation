package test_scripts;

import applicationUtil.RepurchaseUtil;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import util.Common_Function;
import util.ConfigFileReader;

public class RepurchaseTest extends BaseTest {

	RepurchaseUtil repurchaseUtilObj;

	@Test
	public void verifyRepurchaseCourse() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production");
		}
		repurchaseUtilObj = new RepurchaseUtil(getDriver());
		result = repurchaseUtilObj.verifyRepurchaseCourse(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, repurchaseUtilObj.repurchaseMsgList.toString());
	}

	@Test
	public void verifyRePurchaseThroughMahapack() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production");
		}
		repurchaseUtilObj = new RepurchaseUtil(getDriver());
		result = repurchaseUtilObj.verifyRepurchaseThroughMahapack(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, repurchaseUtilObj.repurchaseMsgList.toString());
	}
}