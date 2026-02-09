package test_scripts;

import applicationUtil.LiveClassesPageUtil;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import util.ConfigFileReader;

public class FreeLiveClassTest extends BaseTest {

	LiveClassesPageUtil liveClassesPageUtilObj;

	@Test
	public void verifyFreeLiveClass() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		liveClassesPageUtilObj = new LiveClassesPageUtil(getDriver());
		result = liveClassesPageUtilObj.verifyFreeLiveClass(getDriver(), "normal");
        Assert.assertTrue(result, liveClassesPageUtilObj.liveClassMsgList.toString());
	}
}