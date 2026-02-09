package test_scripts;

import applicationUtil.TestPassUtil;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import util.Common_Function;
import util.ConfigFileReader;

public class TestPass extends BaseTest {

	TestPassUtil testPassObj;

	@Test
	public void verifyTestPass() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production");
		}
		testPassObj = new TestPassUtil(getDriver());
		result = testPassObj.verifyTestPassFlow(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, testPassObj.testPassMsgList.toString());
	}
}