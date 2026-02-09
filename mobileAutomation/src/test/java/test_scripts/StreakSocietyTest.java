package test_scripts;

import applicationUtil.StreakUtill;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import util.Common_Function;
import util.ConfigFileReader;

public class StreakSocietyTest extends BaseTest {

	StreakUtill streakUtilObj;

	// The Streak functionality is paused by product, not visible on testing or prod env.
	@Test(enabled = false)
	public void verifyStreakNew() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		streakUtilObj = new StreakUtill(getDriver());
		result = streakUtilObj.verifyStreakNew(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, StreakUtill.streakMsgList.toString());
	}
}
