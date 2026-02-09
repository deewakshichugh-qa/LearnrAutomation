package test_scripts;

import applicationUtil.LiveClassesPageUtil;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import util.Common_Function;
import util.ConfigFileReader;
import util.TestDescriptionConstant;

public class LiveClassTest extends BaseTest {

	LiveClassesPageUtil liveClassesPageUtilObj;

	@Test
	public void verifyNormalLiveClass() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production") ||
                ConfigFileReader.strRunMode.equalsIgnoreCase("localLab")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production or localLab due to desktop session creation.");
		}
		liveClassesPageUtilObj = new LiveClassesPageUtil(getDriver());
		result = liveClassesPageUtilObj.verifyLiveClassOnAndroidWeb(getDriver(), LiveClassesPageUtil.ConfigurationMode.STANDARD, "normal");
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, liveClassesPageUtilObj.liveClassMsgList.toString());
	}

	@Test
	public void verifySscLiveClass() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		liveClassesPageUtilObj = new LiveClassesPageUtil(getDriver());
		result = liveClassesPageUtilObj.verifyLiveClassOnAndroidWeb(getDriver(), LiveClassesPageUtil.ConfigurationMode.STANDARD, "ssc");
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
		Assert.assertTrue(result, liveClassesPageUtilObj.liveClassMsgList.toString());
	}

	// Script is very slow
	@Test(description = TestDescriptionConstant.verifyLiveClassRecordedSessionUI, enabled = false)
	public void verifyLiveClassRecordedSessionUI() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		liveClassesPageUtilObj = new LiveClassesPageUtil(getDriver());
		result = liveClassesPageUtilObj.verifyLiveClassRecordedSessionUI(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, liveClassesPageUtilObj.liveClassMsgList.toString());
	}

	// Script is very slow
	@Test(description = TestDescriptionConstant.verifyDownloadOnRecordedLiveClass, enabled = false)
	public void verifyDownloadOnRecordedLiveClass() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		liveClassesPageUtilObj = new LiveClassesPageUtil(getDriver());
		result = liveClassesPageUtilObj.verifyRecordedVideoClassDownload(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, liveClassesPageUtilObj.liveClassMsgList.toString());
	}

	// Script is very slow
	@Test(enabled = false)
	public void verifyLCSInRecordedLiveClass() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		liveClassesPageUtilObj = new LiveClassesPageUtil(getDriver());
		result = liveClassesPageUtilObj.verifyLCSInRecordedLiveClass(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, liveClassesPageUtilObj.liveClassMsgList.toString());
	}
}