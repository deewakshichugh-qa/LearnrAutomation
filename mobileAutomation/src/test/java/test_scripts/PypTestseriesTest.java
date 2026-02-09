package test_scripts;

import applicationUtil.PypTestseriesUtil;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import util.Common_Function;
import util.ConfigFileReader;

public class PypTestseriesTest extends BaseTest {

	PypTestseriesUtil pypTestUtilObj;

	@Test
	public void verifyPypTestseries() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		pypTestUtilObj = new PypTestseriesUtil(getDriver());
		result = pypTestUtilObj.verifyPypTestseries(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, pypTestUtilObj.pypTestMsgList.toString());
	}
}