package test_scripts;

import applicationUtil.HomePageUtil;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import util.Common_Function;
import util.ConfigFileReader;

public class FreeMockTest extends BaseTest {

	HomePageUtil homeUtilObj;

	@Test
	public void verifyFreeMockTest() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production");
		}
		homeUtilObj = new HomePageUtil(getDriver());
		result = homeUtilObj.verifyFreeMockTest(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, homeUtilObj.homeMsgList.toString());
	}

	@Test
	public void verifyDigiFillerTest() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production");
		}
		homeUtilObj = new HomePageUtil(getDriver());
		result = homeUtilObj.verifyDigiFillerTest(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, homeUtilObj.homeMsgList.toString());
	}
}