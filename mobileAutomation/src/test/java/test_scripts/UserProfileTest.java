package test_scripts;

import applicationUtil.CommunityPageUtil;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import util.Common_Function;
import util.ConfigFileReader;
import util.TestDescriptionConstant;

public class UserProfileTest extends BaseTest {

	CommunityPageUtil communityPageUtilObj;

	@Test(description = TestDescriptionConstant.verifyOtherUserProfileScreen)
	public void verifyProfile_FollowFunctionality() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		communityPageUtilObj = new CommunityPageUtil(getDriver());
		result = communityPageUtilObj.verifyProfile_FollowFunctionality(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, communityPageUtilObj.communityPageMsgList.toString());
	}

	@Test(description = TestDescriptionConstant.verifyFacultyUserProfileScreen)
	public void verifyFacultyUserProfileScreen() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		communityPageUtilObj = new CommunityPageUtil(getDriver());
		result = communityPageUtilObj.verifyFacultyUserProfileScreen(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
		Assert.assertTrue(result, communityPageUtilObj.communityPageMsgList.toString());
	}

	@Test(description = TestDescriptionConstant.verifyUserProfileScreenNew)
	public void verifyLikeCommentShareUsingPostInProfile() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		communityPageUtilObj = new CommunityPageUtil(getDriver());
		result = communityPageUtilObj.verifyLikeCommentShareUsingPostInProfile(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, communityPageUtilObj.communityPageMsgList.toString());
	}

	// Due to user service issue
	@Test(description = TestDescriptionConstant.verifyEditPersonalDetailsTab, enabled = false)
	public void verifyEditPersonalDetailsTab() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		communityPageUtilObj = new CommunityPageUtil(getDriver());
		result = communityPageUtilObj.verifyEditPersonalDetailsTab(getDriver(), "Adda", "Tester");
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, communityPageUtilObj.communityPageMsgList.toString());
	}
}