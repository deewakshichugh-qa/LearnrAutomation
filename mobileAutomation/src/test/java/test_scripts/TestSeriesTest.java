package test_scripts;

import applicationUtil.MyContentUtil;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import util.Common_Function;
import util.ConfigFileReader;
import util.TestDescriptionConstant;

public class TestSeriesTest extends BaseTest {

	MyContentUtil myContentUtilObj;

	@Test(description = TestDescriptionConstant.verifyMockTestStoreFront)
	public void verifyMockTestStoreFront() {
		boolean result = true;

		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		myContentUtilObj = new MyContentUtil(getDriver());
		result = myContentUtilObj.verifyMockTestStoreFront(getDriver(), "Automation-Test-Series New Package", false);
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, myContentUtilObj.myContentMsgList.toString());
	}

	@Test(description = TestDescriptionConstant.verifyResumeMockTest)
	public void verifyResumeMockTest() {
		boolean result = true;

		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		myContentUtilObj = new MyContentUtil(getDriver());
		result = myContentUtilObj.verifyResumeMockTest(getDriver(), "Automation-Test-Series New Package");
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, myContentUtilObj.myContentMsgList.toString());
	}

	@Test(description = TestDescriptionConstant.verifyMockTestStoreFrontWithCalculator)
	public void verifyMockTestStoreFrontWithCalculator() {
		boolean result = true;

		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		myContentUtilObj = new MyContentUtil(getDriver());
		result = myContentUtilObj.verifyMockTestStoreFront(getDriver(), "Automation-Test-Series New Package", true);
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, myContentUtilObj.myContentMsgList.toString());
	}

	@Test(description = TestDescriptionConstant.verifyCutOffMockTest)
	public void verifyCutOffMockTest() {
		boolean result = true;

		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		myContentUtilObj = new MyContentUtil(getDriver());
		result = myContentUtilObj.verifyCutOffMockTest(getDriver(), "Automation-Test-Series New Package");
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, myContentUtilObj.myContentMsgList.toString());
	}

	@Test(description = TestDescriptionConstant.verifyMultiLanguageMockTest)
	public void verifyMultiLanguageMockTest() {
		boolean result = true;

		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		myContentUtilObj = new MyContentUtil(getDriver());
		result = myContentUtilObj.verifyMultiLanguageMockTest(getDriver(), "Automation-Test-Series New Package", true);
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, myContentUtilObj.myContentMsgList.toString());
	}

	@Test(description = TestDescriptionConstant.verifyMultiQuestionMockTest)
	public void verifyMultiQuestionMockTest() {
		boolean result = true;

		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		myContentUtilObj = new MyContentUtil(getDriver());
		result = myContentUtilObj.verifyMultiQuestionMockTest(getDriver(), "Automation-Test-Series New Package", true);
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, myContentUtilObj.myContentMsgList.toString());
	}

	@Test(description = TestDescriptionConstant.verifySectionalTimeLimitMockTest)
	public void verifySectionalTimeLimitMockTest() {
		boolean result = true;

		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		myContentUtilObj = new MyContentUtil(getDriver());
		result = myContentUtilObj.verifySectionalTimeLimitMockTest(getDriver(), "Automation-Test-Series New Package", false);
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, myContentUtilObj.myContentMsgList.toString());
	}

	@Test
	public void verifyMockOptionalTestInStoreFront() {
		boolean result = true;

		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		myContentUtilObj = new MyContentUtil(getDriver());
		result = myContentUtilObj.verifyMockOptionalTestInStoreFront(getDriver(), "Automation-Test-Series New Package", false);
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
		Assert.assertTrue(result, myContentUtilObj.myContentMsgList.toString());
	}
	
	@Test
	public void verifyMockTestStoreFrontFromQuestionBank() {
		boolean result = true;

		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		myContentUtilObj = new MyContentUtil(getDriver());
		result = myContentUtilObj.verifyMockTestStoreFrontUsingQuestionBank(getDriver(), "Automation-Test-Series New Package", false);
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
		Assert.assertTrue(result, myContentUtilObj.myContentMsgList.toString());
	}

	@Test(description = TestDescriptionConstant.verifyAttemptAndReAttemptMockTestWithOutInternet, enabled = false)
	public void verifyAttemptAndReAttemptMockTestWithOutInternet() {
		boolean result = true;

		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		myContentUtilObj = new MyContentUtil(getDriver());
		result = myContentUtilObj.verifyAttemptAndReAttemptMockTestWithOutInternet(getDriver(),
				"Automation-Test-Series New Package", false);
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
		Assert.assertTrue(result, myContentUtilObj.myContentMsgList.toString());
	}

	@Test(enabled = false)
	public void verifyMockTestStoreFrontLiveTest() {
		boolean result = true;

		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		myContentUtilObj = new MyContentUtil(getDriver());
		result = myContentUtilObj.verifyMockTestStoreFrontLiveTest(getDriver(), false, true,
				"PKS PARENT 7 | Mock Test Series by Adda 247");
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
		Assert.assertTrue(result, myContentUtilObj.myContentMsgList.toString());
	}

	@Test(enabled = false)
	public void verifyInterNetOnOffFunctionOnLiveTest() {
		boolean result = true;

		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		myContentUtilObj = new MyContentUtil(getDriver());
		result = myContentUtilObj.verifyInterNetOnOffFunctionOnLiveTest(getDriver(), false, false,
				"PKS PARENT 7 | Mock Test Series by Adda 247");
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
		Assert.assertTrue(result, myContentUtilObj.myContentMsgList.toString());
	}
}