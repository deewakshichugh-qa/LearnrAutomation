package test_scripts;

import applicationUtil.CommunityPageUtil;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import util.Common_Function;
import util.ConfigFileReader;

public class CommunityPost extends BaseTest {

	CommunityPageUtil communityPageUtilObj;

	@Test(description = "This test verifies addition of a attachment post from All post and Group detail page")
	public void verifyAddingDiffPost() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		communityPageUtilObj = new CommunityPageUtil(getDriver());
		result = communityPageUtilObj.verifyAddingDiffPost(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, communityPageUtilObj.communityPageMsgList.toString());
	}

	@Test(description = "This test verify the post filters")
	public void verifyFilterPost() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		communityPageUtilObj = new CommunityPageUtil(getDriver());
		result = communityPageUtilObj.verifyFilterPost(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
		Assert.assertTrue(result, communityPageUtilObj.communityPageMsgList.toString());
	}

	@Test
	public void validateUnFollowPublicGroup() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios")||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		communityPageUtilObj = new CommunityPageUtil(getDriver());
		result = communityPageUtilObj.validateUnFollowPublicGroup(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
		Assert.assertTrue(result, communityPageUtilObj.communityPageMsgList.toString());
	}

	@Test(description = "This test verifies that admin can pin and unpin post in group feed page")
	public void verifyPinPost() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		communityPageUtilObj = new CommunityPageUtil(getDriver());
		result = communityPageUtilObj.verifyPinPost(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, communityPageUtilObj.communityPageMsgList.toString());
	}

	@Test(description = "This test verify like, comment, sub comment on a post it also verify the post detail page and deletion of post")
	public void verifyLikeCommentAndPostDetails() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		communityPageUtilObj = new CommunityPageUtil(getDriver());
		result = communityPageUtilObj.verifyLikeCommentAndPostDetails(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, communityPageUtilObj.communityPageMsgList.toString());
	}

	@Test
	public void verifyCommunityPageForProdEnv() {
		boolean result = true;
		if (!ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test: Action not suitable for non-production environments.");

		} else if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios")) {
			throw new SkipException("Skipping test: Feature not supported on iOS.");
		}
		communityPageUtilObj = new CommunityPageUtil(getDriver());
		result = communityPageUtilObj.verifyCommunityPageForProdEnv(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, communityPageUtilObj.communityPageMsgList.toString());
	}

	// As no content in gallery in devices
	@Test(enabled = false)
	public void verifyAddingPostWithPdfAndPPT() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios or production.");
		}
		communityPageUtilObj = new CommunityPageUtil(getDriver());
		result = communityPageUtilObj.verifyAddingPostWithPdfAndPPT(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
		Assert.assertTrue(result, communityPageUtilObj.communityPageMsgList.toString());
	}
}