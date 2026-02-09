package test_scripts;

import applicationUtil.VideoCoursesPageUtil;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import util.Common_Function;
import util.ConfigFileReader;
import util.TestDescriptionConstant;

public class VideoCourseTest extends BaseTest {

	VideoCoursesPageUtil videoCoursesPageUtilObj;

	// Script is very slow
	@Test(description = TestDescriptionConstant.verifyVideoCourse, enabled = false)
	public void verifyVideoCourse() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		videoCoursesPageUtilObj = new VideoCoursesPageUtil(getDriver());
		result = videoCoursesPageUtilObj.verifyVideoCourse(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
		Assert.assertEquals(result, true, videoCoursesPageUtilObj.videoSectionMsgList.toString());
	}

	// Script is very slow
	@Test(description = TestDescriptionConstant.verifyVideoCourseDownload, enabled = false)
	public void verifyVideoCourseDownload() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		videoCoursesPageUtilObj = new VideoCoursesPageUtil(getDriver());
		result = videoCoursesPageUtilObj.verifyVideoCourseDownload(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
		Assert.assertEquals(result, true, videoCoursesPageUtilObj.videoSectionMsgList.toString());
	}

	// Script is very slow
	@Test(enabled = false)
	public void verifyLCSFeatureInVideoCourse() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		videoCoursesPageUtilObj = new VideoCoursesPageUtil(getDriver());
		result = videoCoursesPageUtilObj.verifyLCSFeatureInVideoCourse(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
		Assert.assertEquals(result, true, videoCoursesPageUtilObj.videoSectionMsgList.toString());
	}

	// Script is very slow
	@Test(enabled = false)
	public void verifyLCSFeatureOnPurchasedVideoCourse() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		videoCoursesPageUtilObj = new VideoCoursesPageUtil(getDriver());
		result = videoCoursesPageUtilObj.verifyLCSFeatureOnPurchasedVideoCourse(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
		Assert.assertEquals(result, true, videoCoursesPageUtilObj.videoSectionMsgList.toString());
	}
}
