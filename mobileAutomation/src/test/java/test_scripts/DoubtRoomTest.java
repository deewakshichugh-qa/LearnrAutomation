package test_scripts;

import applicationUtil.DoubtRoomUtil;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import util.Common_Function;
import util.ConfigFileReader;
import util.TestDescriptionConstant;

public class DoubtRoomTest extends BaseTest {
	
	DoubtRoomUtil doubtRoomUtilObj;

	// Locators not present
	@Test(description = TestDescriptionConstant.verifyDoubtRoomUI, enabled = false)
	public void verifyDoubtRoomUI() {
		boolean result=true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		doubtRoomUtilObj=new DoubtRoomUtil(getDriver());
		result=doubtRoomUtilObj.verifyDoubtRoomUI(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, doubtRoomUtilObj.doubtRoomMsgList.toString());
	}
}
