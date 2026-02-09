package test_scripts;

import applicationUtil.MyContentUtil;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import util.Common_Function;
import util.ConfigFileReader;

public class MyContent extends BaseTest {

	MyContentUtil myContentUtilObj;

	@Test
	public void verifyContentSearch() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		myContentUtilObj = new MyContentUtil(getDriver());
		result = myContentUtilObj.verifyMyContentSearch(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
		Assert.assertTrue(result, myContentUtilObj.myContentMsgList.toString());
	}

	@Test
	public void verifyBookmarkSection() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		myContentUtilObj = new MyContentUtil(getDriver());
		result = myContentUtilObj.verifyBookmarkSection(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
		Assert.assertTrue(result, myContentUtilObj.myContentMsgList.toString());
	}

	@Test
	public void verifyNewPackageForOldUser() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios & production.");
		}
		myContentUtilObj = new MyContentUtil(getDriver());
		result = myContentUtilObj.verifyNewPackageForOldUser(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
		Assert.assertTrue(result, myContentUtilObj.myContentMsgList.toString());
	}

	@Test
	public void verifyDoubtsSectionOnNewUser() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios & production.");
		}
		myContentUtilObj = new MyContentUtil(getDriver());
		result = myContentUtilObj.verifyDoubtsSectionOnNewUser(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
		Assert.assertTrue(result, myContentUtilObj.myContentMsgList.toString());
	}

	@Test
	public void verifyDoubtsSectionOnGuestUser() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios & production.");
		}
		myContentUtilObj = new MyContentUtil(getDriver());
		result = myContentUtilObj.verifyDoubtsSectionOnGuestUser(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
		Assert.assertTrue(result, myContentUtilObj.myContentMsgList.toString());
	}

	@Test
	public void verifyDownloadQuizzes() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios & production.");
		}
		myContentUtilObj = new MyContentUtil(getDriver());
		result = myContentUtilObj.verifyDownloadQuizzes(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
		Assert.assertTrue(result, myContentUtilObj.myContentMsgList.toString());
	}

	@Test
	public void verifyReportedQues() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios & production.");
		}
		myContentUtilObj = new MyContentUtil(getDriver());
		result = myContentUtilObj.verifyReportedQues(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
		Assert.assertTrue(result, myContentUtilObj.myContentMsgList.toString());
	}

	@Test
	public void verifyMahaPack_DownloadEbook() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios & production.");
		}
		myContentUtilObj = new MyContentUtil(getDriver());
		result = myContentUtilObj.verifyMahaPack_DownloadEbook(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
		Assert.assertTrue(result, myContentUtilObj.myContentMsgList.toString());
	}

	// Issue in downloading magazine
	@Test(enabled = false)
	public void verifyDownloadMagazines() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios & production.");
		}
		myContentUtilObj = new MyContentUtil(getDriver());
		result = myContentUtilObj.verifyDownloadMagazines(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
		Assert.assertTrue(result, myContentUtilObj.myContentMsgList.toString());
	}
}