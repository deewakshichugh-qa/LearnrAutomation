package applicationUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;
import com.google.gson.JsonObject;
import apiUtill.CouponUtil;
import apiUtill.CreatePackageUtil;
import apiUtill.SocialApiUtil;
import applicationUtil.UserProfilePageUtil.ProfileType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.CommunityPage_OR;
import pojo.createPostResponse.CreatePostResponse;
import util.Common_Function;
import util.Common_Function.direction;
import util.Common_Function.key;
import util.ConfigFileReader;

public class CommunityPageUtil {

	String strPhoneNo = Common_Function.randomPhoneNumber(10, "8");
	StorePageUtil storeUtilObj;
	MyContentUtil myContentUtil;
	HomePageUtil homeUtilObj;
	LoginUtil loginUtilObj;
	RegisterNewUserUtil registrationUserUtilObj;
	PostingPageUtil postingPageUtilObj;
	JsonObject socialJsonObject;
	SocialApiUtil socialApiUtilUserObj;
	UserProfilePageUtil userProfilePageUtilObj;
	EditProfileUtil editProfileUtilObj;
	StorePageUtil storePageUtilObj;
	OrderSuccessUtil orderSuccessUtilObj;
	CommentPageUtil commentPageUtilObj;
	PaymentUtil paymentUtilObj;
	EBooksPageUtil eBooksPageUtilObj;
	CreatePackageUtil createPackageUtilObj;
	CouponUtil couponUtilObj;
	PurchasePackageUtil purchasePackageUtilObj;
	UserDetailsLayerUtil userDetailsLayerUtilObj;
	ProductPageUtil productPageUtilObj;
	FeedbackFormUtil feedbackFormUtilObj;
	ConfigFileReader configFileReaderObj = new ConfigFileReader();
	CreatePostResponse createPostResponseObj = null;

	CommunityPage_OR communityPageObj;
	public ArrayList<String> communityPageMsgList = new ArrayList<>();
	public Common_Function cfObj = new Common_Function();
	public ConfigFileReader cfReaderObj = new ConfigFileReader();

	public CommunityPageUtil(AppiumDriver<MobileElement> driver) {
		communityPageObj = new CommunityPage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), communityPageObj);
	}

	public boolean verifyAddingDiffPost(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		loginUtilObj = new LoginUtil(driver);
		try {
			if (ConfigFileReader.strEnv.equalsIgnoreCase("production")) {

				result = loginUtilObj.verifyLoginUsingEmailIdFromConfig(driver);
				if (!result) {
					communityPageMsgList.addAll(loginUtilObj.loginMsgList);
					return result;
				}
			} else {
				result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "9"), false);
				if (!result) {
					communityPageMsgList.addAll(loginUtilObj.loginMsgList);
					return result;
				}
			}

			Thread.sleep(1500);

			homeUtilObj = new HomePageUtil(driver);
			result = homeUtilObj.clickCommunityBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = addNewAttachmentPost(driver);
			if (!result) {
				communityPageMsgList.add("Unable to add NewAttachmentPost");
				return result;
			}

			Thread.sleep(3000);

			result = attemptCorrectMcqPost(driver);
			if (!result) {
				communityPageMsgList.add("Unable to attempt correct Mcq");
				return result;
			}

			result = attemptWrongMcqPost(driver);
			if (!result) {
				communityPageMsgList.add("Unable to attempt wrong Mcq");
				return result;
			}

			result = attemptMultipleCorrectMcqPost(driver);
			if (!result) {
				communityPageMsgList.add("Failed to attempt MultipleCorrectMcqPost.");
				return result;
			}

			Thread.sleep(3000);

			result = addPostGroupDetailScreen(driver, PostType.PICTURE_ATTACHMENT_POST);
			if (!result) {
				communityPageMsgList.add("Unable to add new post from group detail screen");
				return result;
			}
		} catch (Exception e) {
			communityPageMsgList.add("verifyAddingDiffPost_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean addNewAttachmentPost(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			socialJsonObject = cfObj.getJsonData("src//main//resources//socialData.json");
			if (socialJsonObject == null) {
				communityPageMsgList.add("Social Json Object is null.");
			}

			result = clickOnAddPostBtn(driver);
			if (!result) {
				communityPageMsgList.add("Unable to click on add post btn");
				return result;
			}

			postingPageUtilObj = new PostingPageUtil(driver);
			result = postingPageUtilObj.clickOnAttachmentMCQPostTab(driver, PostType.PICTURE_ATTACHMENT_POST);
			if (!result) {
				communityPageMsgList.addAll(postingPageUtilObj.postingMsgList);
				return result;
			}

			result = selectGroupFromOptions(driver, socialJsonObject.get("postGroup").getAsString());
			if (!result) {
				communityPageMsgList.add("Unable to select group from given options");
				return result;
			}

			postingPageUtilObj = new PostingPageUtil(driver);

			Thread.sleep(1000);

			result = postingPageUtilObj.enterQueryOrDoubt(driver, socialJsonObject.get("postContent").getAsString());
			if (!result) {
				communityPageMsgList.add("Unable to enter query in posting page");
				return result;
			}

			Thread.sleep(1000);

			if(!ConfigFileReader.strRunMode.equalsIgnoreCase("localLab")){
				driver.hideKeyboard();
			}

			result = postingPageUtilObj.clickPostSubmitBtn(driver);
			if (!result) {
				communityPageMsgList.add("Unable to select type of post from slider menu");
				return result;
			}

			result = postingPageUtilObj.selectTopic(driver, socialJsonObject.get("postContent").getAsString());
			if (!result) {
				communityPageMsgList.addAll(postingPageUtilObj.postingMsgList);
				communityPageMsgList.add("Unable to select topic in Posting page");
				return result;
			}

		} catch (Exception e) {
			communityPageMsgList.add("addNewPost_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean addNewMcqPost(AppiumDriver<MobileElement> driver, int noOfOptions, int noOfCorrectOptions) {
		boolean result = true;
		try {
			socialJsonObject = cfObj.getJsonData("src//main//resources//socialData.json");

			result = clickOnAddPostBtn(driver);
			if (!result) {
				communityPageMsgList.add("Unable to click on add post btn");
				return result;
			}

			Thread.sleep(1500);

			postingPageUtilObj = new PostingPageUtil(driver);
			result = postingPageUtilObj.clickOnAttachmentMCQPostTab(driver, PostType.MCQ);
			if (!result) {
				communityPageMsgList.add("Unable to select MCQ post");
				return result;
			}

			result = selectGroupFromOptions(driver, socialJsonObject.get("postGroup").getAsString());
			if (!result) {
				communityPageMsgList.add("Unable to select group from given options");
				return result;
			}

			result = postingPageUtilObj.enterQueryOrDoubt(driver, socialJsonObject.get("postContent").getAsString());
			if (!result) {
				communityPageMsgList.add("Unable to select type of post from slider menu");
				return result;
			}

			if(!ConfigFileReader.strRunMode.equalsIgnoreCase("localLab")){
				driver.hideKeyboard();
			}

			result = postingPageUtilObj.addOptionsInMcq(driver, noOfOptions, noOfCorrectOptions);
			if (!result) {
				communityPageMsgList.add("Unable to add Mcq options");
				return result;
			}

			Thread.sleep(1000);

			if(!ConfigFileReader.strRunMode.equalsIgnoreCase("localLab")){
				driver.hideKeyboard();
			}

			result = postingPageUtilObj.clickPostSubmitBtn(driver);
			if (!result) {
				communityPageMsgList.add("Unable to click post button");
				return result;
			}

			result = postingPageUtilObj.selectTopic(driver, socialJsonObject.get("postContent").getAsString());
			if (!result) {
				communityPageMsgList.add("Unable to select type of post from slider menu");
				return result;
			}

		} catch (Exception e) {
			communityPageMsgList.add("addNewPost_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean selectTypeOfPostFromOptions(AppiumDriver<MobileElement> driver, PostType postType) {
		boolean result = true;
		try {
			if (postType.equals(PostType.MCQ)) {
				cfObj.commonClick(communityPageObj.getOptionMCQs());
			} else {
				cfObj.commonClick(communityPageObj.getOptionPictureVideoAttachmentPost());
			}

			postingPageUtilObj = new PostingPageUtil(driver);
			result = postingPageUtilObj.verifyPostingPage(driver);

		} catch (Exception e) {
			communityPageMsgList.add("selectTypeOfPostFromOptions_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnAddPostBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			cfObj.skipCoachMarkSocial(driver);

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getBtnAddPost(), 10);
			if (!result) {
					communityPageMsgList.add("Add Post button is not visible.");
					return result;
			}

			cfObj.commonClick(communityPageObj.getBtnAddPost());

			cfObj.skipCoachMarkSocial(driver);
			postingPageUtilObj = new PostingPageUtil(driver);

			result = cfObj.commonWaitForElementToBeVisible(driver,
					postingPageUtilObj.postingPageObj.getAttachmentPostTab(), 10);
			if (!result) {
				communityPageMsgList.add("Attachment Post Tab is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, postingPageUtilObj.postingPageObj.getMcqPostTab(),
					10);
			if (!result) {
				communityPageMsgList.add("MCQ Post Tab is not visible.");
				return result;
			}

		} catch (Exception e) {
			communityPageMsgList.add("clickOnAddPostBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifySelectGroupSlider(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getTitleSlider(), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify title in Slider menu");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getSearchField(), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify search field in Slider menu");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getOptionsRadioBtn().get(0), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify groups options in Slider menu");
				return result;
			}

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifySelectGroupPage_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean selectGroupFromOptions(AppiumDriver<MobileElement> driver, String strPostGroup) {
		boolean result = true;
		int index = 0;
		try {
			postingPageUtilObj = new PostingPageUtil(driver);
			result = postingPageUtilObj.clickOnSelectGroupDropDown(driver);
			if (!result) {
				communityPageMsgList.addAll(postingPageUtilObj.postingMsgList);
				return result;
			}

			communityPageObj.getSearchField().click();
			Thread.sleep(500);
			communityPageObj.getSearchField().sendKeys(strPostGroup);
			Thread.sleep(1500);

			while (true) {
				if (communityPageObj.getOptionsRadioBtn().get(index).getAttribute("content-desc")
						.contains(strPostGroup)) {
					cfObj.commonClick(communityPageObj.getOptionsRadioBtn().get(index));
					break;
				}
				index++;
			}
		} catch (Exception e) {
			communityPageMsgList.add("selectGroupFromOptions_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyAddedPostInAskedDoubts(AppiumDriver<MobileElement> driver, String strPostContent) {
		boolean result = true;
		try {

			homeUtilObj = new HomePageUtil(driver);

			result = homeUtilObj.clickMyContentButton(driver);
			if (!result) {
				communityPageMsgList.add("Unable to proceed to my Content page");
				return result;
			}

			myContentUtil = new MyContentUtil(driver);

			result = myContentUtil.clickDoubtsBtn(driver);
			if (!result) {
				communityPageMsgList.add("Unable to proceed to doubts page in my content section");
				return result;
			}

			result = myContentUtil.clickAskedInDoubtsSection(driver);
			if (!result) {
				communityPageMsgList.add("Unable to proceed to bookmarks in Doubts section");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//*[contains(@text,'" + strPostContent + "')]", "xpath", 10);
			if (!result) {
				communityPageMsgList.add("PostContent text is not visible.");
			}

		} catch (Exception e) {
			communityPageMsgList.add("verifyAddedPostInAskedDoubts_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	// -------------------------------------------ADD POST FROM GROUP DETAIL
	// SCREEN----------------------------------

	public boolean addPostGroupDetailScreen(AppiumDriver<MobileElement> driver, PostType postType) {
		boolean result = true;
		try {

			result = clickOnFirstPostGroup(driver);
			if (!result) {
				communityPageMsgList.add("Unable to open the first post group");
				return result;
			}

			cfObj.commonClick(communityPageObj.getBtnAddPost());
			// result = cfObj.commonWaitForElementToBeVisible(driver,
			// cfObj.getElementFromAttribute(driver, "content-desc", "imagePost"), 10);
			// if (!result) {
			// communityPageMsgList.add("Unable to click on add post button");
			// return result;
			// }

			Thread.sleep(1500);
			postingPageUtilObj = new PostingPageUtil(driver);
			result = postingPageUtilObj.clickOnAttachmentMCQPostTab(driver, postType);
			if (!result) {
				communityPageMsgList.addAll(postingPageUtilObj.postingMsgList);
				return result;
			}

			if (postType.equals(PostType.PICTURE_ATTACHMENT_POST)) {

				result = postingPageUtilObj.enterQueryOrDoubt(driver,
						socialJsonObject.get("postContent").getAsString());
				if (!result) {
					communityPageMsgList.add("Unable to select type of post from slider menu");
					return result;
				}

				Thread.sleep(1000);

				if(!ConfigFileReader.strRunMode.equalsIgnoreCase("localLab")){
					driver.hideKeyboard();
				}

				result = postingPageUtilObj.clickPostSubmitBtn(driver);
				if (!result) {
					communityPageMsgList.add("Unable to click post submit button.");
					return result;
				}

			} else if (postType.equals(PostType.MCQ)) {

				result = postingPageUtilObj.enterQueryOrDoubt(driver,
						socialJsonObject.get("postContent").getAsString());
				if (!result) {
					communityPageMsgList.add("Unable to select type of post from slider menu");
					return result;
				}

				result = postingPageUtilObj.addOptionsInMcq(driver, 4, 1);
				if (!result) {
					communityPageMsgList.add("Unable to add Mcq options");
					return result;
				}

				if(!ConfigFileReader.strRunMode.equalsIgnoreCase("localLab")){
					driver.hideKeyboard();
				}
				Thread.sleep(1000);
				cfObj.scrollDown(driver);

				result = postingPageUtilObj.clickPostSubmitBtn(driver);
				if (!result) {
					communityPageMsgList.add("Unable to click post submit button");
					return result;
				}
			}

			result = postingPageUtilObj.selectTopic(driver, socialJsonObject.get("postContent").getAsString());
			if (!result) {
				communityPageMsgList.add("Unable to select type of post from slider menu");
				return result;
			}

		} catch (Exception e) {
			communityPageMsgList.add("addNewPost_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}
	// ----------------------------------------------ATTEMPT
	// MCQ---------------------------------------------------------------

	public boolean verifyMCQPost(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			loginUtilObj = new LoginUtil(driver);
			result = loginUtilObj.verifyLoginGuestUser(driver);
			if (!result) {
				communityPageMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}
			homeUtilObj = new HomePageUtil(driver);

			result = homeUtilObj.clickCommunityBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			Thread.sleep(1500);

			if(!ConfigFileReader.strRunMode.equalsIgnoreCase("localLab")){
				driver.hideKeyboard();
			}

			result = attemptCorrectMcqPost(driver);
			if (!result) {
				communityPageMsgList.add("Unable to attempt correct Mcq");
				return result;
			}

			result = attemptWrongMcqPost(driver);
			if (!result) {
				communityPageMsgList.add("Unable to attempt wrong Mcq");
				return result;
			}

			result = attemptMultipleCorrectMcqPost(driver);
			if (!result) {
				communityPageMsgList.add("Unable to verify MCMC post");
				return result;
			}

		} catch (Exception e) {
			communityPageMsgList.add("verifyAddingPost_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean attemptCorrectMcqPost(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			Thread.sleep(200);

			result = addNewMcqPost(driver, 4, 1);
			if (!result) {
				communityPageMsgList.add("Unable to add new post MCQ type");
				return result;
			}

			Thread.sleep(2000);

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.RadioButton", "xpath", 10);
			if(!result){

				cfObj.commonClick(cfObj.commonGetElement(driver, "navigation_community", "id"));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.RadioButton", "xpath", 10);
				if(!result){
					communityPageMsgList.add("The mcq post or radio button is not visible");
					return result;
				}
			}
			// selecting top most option on page
			cfObj.commonClick(communityPageObj.getOptionsRadioBtn().get(0));
			cfObj.commonClick(communityPageObj.getOptionsRadioBtn().get(0));
			cfObj.commonClick(cfObj.commonGetElements(driver, "//android.widget.CheckBox/android.widget.RadioButton", "xpath").get(0));

			Thread.sleep(2000);

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getButtonSubmitMcq(), 10);
			if (!result){
				communityPageMsgList.add("Mcq post submit button is not visible");
				return result;
			}
			// Clicking on submit mcq button
			cfObj.commonClick(communityPageObj.getButtonSubmitMcq());

		} catch (Exception e) {
			communityPageMsgList.add("attemptMcqPost_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean attemptWrongMcqPost(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = addNewMcqPost(driver, 4, 1);
			if (!result) {
				communityPageMsgList.add("Unable to add new post MCQ type");
				return result;
			}

			Thread.sleep(2000);

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.RadioButton", "xpath", 10);
			if(!result){

				cfObj.commonClick(cfObj.commonGetElement(driver, "navigation_community", "id"));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.RadioButton", "xpath", 10);
				if(!result){
					communityPageMsgList.add("The mcq post or radio button is not visible");
					return result;
				}
			}
			// selecting top most option on page
			cfObj.commonClick(communityPageObj.getOptionsRadioBtn().get(1));
			cfObj.commonClick(communityPageObj.getOptionsRadioBtn().get(1));
			cfObj.commonClick(cfObj.commonGetElements(driver, "//android.widget.CheckBox/android.widget.RadioButton", "xpath").get(1));

			Thread.sleep(2000);

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getButtonSubmitMcq(), 10);
			if (!result){
				communityPageMsgList.add("Mcq post submit button is not visible");
				return result;
			}
			// Clicking on submit mcq button
			cfObj.commonClick(communityPageObj.getButtonSubmitMcq());

		} catch (Exception e) {
			communityPageMsgList.add("attemptWrongMcqPost_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean attemptMultipleCorrectMcqPost(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = addNewMcqPost(driver, 4, 4);
			if (!result) {
				communityPageMsgList.add("Unable to add new post MCQ type");
				return result;
			}

			// selecting multiple options from mcq post
			for (int i = 0; i < 4; i++) {
				cfObj.commonClick(
						cfObj.commonGetElements(driver, "//android.widget.CheckBox[not(@content-desc)]", "xpath")
								.get(i));
			}
			Thread.sleep(2000);

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getButtonSubmitMcq(), 10);
			if (!result){
				communityPageMsgList.add("Mcq post submit button is not visible");
				return result;
			}
			// Clicking on submit mcq button
			cfObj.commonClick(communityPageObj.getButtonSubmitMcq());

		} catch (Exception e) {
			communityPageMsgList.add("attemptMcqPost_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	//----------------VERIFY PIN POST---------------------------------------------

	public boolean verifyPinPost(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			loginUtilObj = new LoginUtil(driver);
			result = loginUtilObj.verifyLoginUsingEmailId(driver, "sarika.tyagi0802@gmail.com", "amitpundir", true);
			if (!result) {
				communityPageMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			homeUtilObj = new HomePageUtil(driver);
			result = homeUtilObj.clickCommunityBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = clickYourGroupTab(driver);
			if (!result) {
				communityPageMsgList.add("Failed to click YourGroup tab.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getSectionYourCreatedGroups(), 10);
			if (!result) {
				communityPageMsgList.add("Your created group is missing in Your Group tab");
				return result;
			}

			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {

				if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'moveToGroupFeedScreen')]", "xpath", 10)) {

					List<MobileElement> groupList = cfObj.commonGetElements(driver, "//*[contains(@content-desc,'moveToGroupFeedScreen')]", "xpath");

					cfObj.commonClick(groupList.get(0));

					Thread.sleep(2000);

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button/android.view.View", "xpath", 7);
					if (result) {
						cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.Button/android.view.View", "xpath"));
					}

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'moveToGroupFeedScreen')]", "xpath", 10);
					if (result) {
						cfObj.commonClick(groupList.get(0));

						Thread.sleep(2000);

						result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button/android.view.View", "xpath", 5);
						if (result) {
							cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.Button/android.view.View", "xpath"));
						}

						result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'moveToGroupFeedScreen')]", "xpath", 10);
						if (!result) {
							communityPageMsgList.add("Not able to open the group page");
                        }
					}

					if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'Content will be available soon')]", "xpath", 5)) {

						cfObj.commonClick(communityPageObj.getBtnAddPost());

						Thread.sleep(2000);

						result = cfObj.commonWaitForElementToBeVisible(driver, cfObj.getElementFromAttribute(driver, "content-desc", "imagePost"), 30);
						if (!result) {
							communityPageMsgList.add("Unable to click on add post button");
							return result;
						}

						postingPageUtilObj = new PostingPageUtil(driver);
						result = postingPageUtilObj.clickOnAttachmentMCQPostTab(driver, PostType.PICTURE_ATTACHMENT_POST);
						if (!result) {
							communityPageMsgList.addAll(postingPageUtilObj.postingMsgList);
							return result;
						}

						Thread.sleep(1000);

						result = cfObj.commonSetTextTextBox(postingPageUtilObj.postingPageObj.getFieldText().get(0), "Automation Post");
						if (!result) {
							communityPageMsgList.add("Unable to enter Query.");
							return result;
						}

						Thread.sleep(1000);

						cfObj.commonClick(postingPageUtilObj.postingPageObj.getSubmitBtn());

						result = cfObj.commonWaitForElementToBeVisible(driver, postingPageUtilObj.postingPageObj.getTitleSelectTopic(), 10);
						if (result) {
							List<MobileElement> el = postingPageUtilObj.postingPageObj.getOptionTopics();
							cfObj.commonClick(el.get(0));
						}

						cfObj.commonClick(postingPageUtilObj.postingPageObj.getPostBtn());

						Thread.sleep(1000);

						result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'Automation Post')]", "xpath", 10);
						if (!result) {
							communityPageMsgList.add("Unable to create post.");
							return result;
						}
					}
				}
			} else {
				if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@name,'moveToGroupFeedScreen')]", "xpath", 10)) {
					List<MobileElement> groupList = cfObj.commonGetElements(driver, "//*[contains(@name,'moveToGroupFeedScreen')]", "xpath");

					cfObj.commonClick(groupList.get(0));
					cfObj.skipCoachMarkSocial(driver);

					if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@name,'Content will be available soon')]", "xpath", 10)) {
						cfObj.commonClick(communityPageObj.getBtnAddPost());
						Thread.sleep(2000);
						result = cfObj.commonWaitForElementToBeVisible(driver, cfObj.getElementFromAttribute(driver, "name", "imagePost"), 30);
						if (!result) {
							communityPageMsgList.add("Unable to click on add post button");
							return result;
						}

						Thread.sleep(1500);
						cfObj.commonClick(communityPageObj.getOptionPictureVideoAttachmentPost());

						postingPageUtilObj = new PostingPageUtil(driver);

						cfObj.commonClick(postingPageUtilObj.postingPageObj.getDropMenuTopic());

						result = cfObj.commonWaitForElementToBeVisible(driver, postingPageUtilObj.postingPageObj.getTitleSelectTopic(), 10);
						if (result) {
							List<MobileElement> el = postingPageUtilObj.postingPageObj.getOptionTopics();

							cfObj.commonClick(el.get(0));
						}
						Thread.sleep(1000);

						result = cfObj.commonSetTextTextBox(postingPageUtilObj.postingPageObj.getFieldText().get(0), "Automation Post");
						if (!result) {
							communityPageMsgList.add("Unable to enter Query.");
							return result;
						}
						Thread.sleep(1000);

						cfObj.commonClick(postingPageUtilObj.postingPageObj.getBtnPost());
						result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@value,'Automation Post')]", "xpath", 10);
						if (!result) {
							communityPageMsgList.add("Unable to create post.");
							return result;
						}
					}
				}
			}
			Thread.sleep(1000);

			result = validatePostPinned(driver);
			if (!result) {
				communityPageMsgList.add("Not able to validate Post pinned.");
				return result;
			}
		} catch (Exception e) {
			communityPageMsgList.add("verifyPinPost_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean isPostPinned(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'Pinned Post')]",
					"xpath", 4)) {
				result = openBottomSliderMenu(driver);
				if (!result) {
					communityPageMsgList.add("Failed to open Bottom slider menu.");
				}
			} else {
				System.out.println("Pinned post is not present.");
			}

		} catch (Exception e) {
			communityPageMsgList.add("_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean pinTopPost(AppiumDriver<MobileElement> driver) {
		String strPostContent = null;
		boolean result = true;
		try {

			cfObj.scrollUtill(driver, 2, direction.DOWN);

			strPostContent = communityPageObj.getListPost().get(0).getAttribute("content-desc");
			if (strPostContent == null) {
				communityPageMsgList.add("Post Content text is null.");
				return false;
			}

			// Pinning post
			result = openBottomSliderMenu(driver);
			if (!result) {
				communityPageMsgList.add("Failed to open Bottom slider menu.");
				return result;
			}
			cfObj.commonClick(communityPageObj.getOptionBsmPinPost());

			// verify the pinned post is present on top
			cfObj.scrollUtill(driver, 3, direction.UP);

			result = isPostPinned(driver);
			if (!result) {
				communityPageMsgList.add("The post is not pinned");
				return result;
			}

			// verify the pinned post is the correct one
			result = communityPageObj.getListPost().get(0).getAttribute("content-desc").contains(strPostContent);
			if (!result) {
				communityPageMsgList.add("The pinned post is not correct");
				return result;
			}

			// Unpinning Post
			result = openBottomSliderMenu(driver);
			if (!result) {
				communityPageMsgList.add("Failed to open Bottom slider menu.");
				return result;
			}
			cfObj.commonClick(communityPageObj.getOptionBsmPinPost());

		} catch (Exception e) {
			communityPageMsgList.add("_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean validatePostPinned(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {

				if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'Pinned Post')]", "xpath", 5)) {

					result = openBottomSliderMenu(driver);
					if (!result) {
						communityPageMsgList.add("Failed to open Bottom slider menu.");
						return result;
					}

					cfObj.commonClick(communityPageObj.getOptionBsmPinPost());

					Thread.sleep(3000);

					result = !cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'Pinned Post')]", "xpath", 5);
					if (!result) {
						communityPageMsgList.add("Not able to unpin the post.");
						return result;
					}
				}
			} else {
				if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@name,'Pinned Post')]", "xpath", 4)) {

					result = openBottomSliderMenu(driver);
					if (!result) {
						communityPageMsgList.add("Failed to open Bottom slider menu.");
						return result;
					}
					cfObj.commonClick(communityPageObj.getOptionBsmPinPost());

					Thread.sleep(1000);

					result = !cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@name,'Pinned Post')]", "xpath", 4);
					if (!result) {
						communityPageMsgList.add("Not able to unpin the post.");
						return result;
					}
				}
			}
			result = openBottomSliderMenu(driver);
			if (!result) {
				communityPageMsgList.add("Failed to open Bottom slider menu.");
				return result;
			}

			cfObj.commonClick(communityPageObj.getOptionBsmPinPost());

			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'Pinned Post')]", "xpath", 30);
			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@name,'Pinned Post')]", "xpath", 30);
			}
			if (!result) {
				communityPageMsgList.add("Not able to pin the post.");
				return result;
			}

			// Unpinning Post
			result = openBottomSliderMenu(driver);
			if (!result) {
				communityPageMsgList.add("Failed to open Bottom slider menu.");
				return result;
			}

			cfObj.commonClick(communityPageObj.getOptionBsmPinPost());

			Thread.sleep(2000);
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = !cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'Pinned Post')]", "xpath", 4);
			} else {
				result = !cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@name,'Pinned Post')]", "xpath", 4);
			}
			if (!result) {
				communityPageMsgList.add("Not able to unpin the post.");
				return result;
			}

		} catch (Exception e) {
			communityPageMsgList.add("validatePostPinned_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean openBottomSliderMenu(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			// click on dot menu of top post
			cfObj.commonClick(communityPageObj.getListDotMenu().get(0));

			// verify bottom slider menu
			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getOptionBsmHidePost(), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify Hide post in bsm");
				// return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getOptionBsmReportPost(), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify report post in bsm");
				// return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getOptionBsmTurnOnNotification(),
					10);
			if (!result) {
				communityPageMsgList.add("Unable to verify turn on notifcaiton in bsm");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getOptionBsmPinPost(), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify pin post in bsm");
				return result;
			}

		} catch (Exception e) {
			communityPageMsgList.add("_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	// ----------------------------------------------VERIFY_FILTER_POST---------------------------------------------------------------

	public boolean verifyFilterPost(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			socialJsonObject = cfObj.getJsonData("src//main//resources//socialData.json");
			if (socialJsonObject == null) {
				communityPageMsgList.add("Social Json Object is null.");
				return false;
			}

			loginUtilObj = new LoginUtil(driver);
			result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "9"), false);
			if (!result) {
				communityPageMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			homeUtilObj = new HomePageUtil(driver);
			result = homeUtilObj.clickCommunityBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = verifyGroupFilter(driver, socialJsonObject.get("postGroup").getAsString());
			if (!result) {
				communityPageMsgList.add("Unable to verify groups tab filter page");
				return result;
			}
		} catch (Exception e) {
			communityPageMsgList.add("verifyFilterPost_Exception: " + e.getMessage());
			result = false;
		}
		return result;
    }

	public boolean verifyGroupFilter(AppiumDriver<MobileElement> driver, String strPostGroup) {
		boolean result = true;
		try {

			result = clickOnFilterBtn(driver);
			if (!result) {
				communityPageMsgList.add("Unable to verify filter btn");
				return result;
			}

			result = clickOnResetBtn(driver);
			if (!result) {
				communityPageMsgList.add("Unable to verify Reset btn filter page");
				return result;
			}

			result = selectGroup(driver, strPostGroup);
			if (!result) {
				communityPageMsgList.add("Unable to verify Reset btn filter page");
				return result;
			}

			result = clickOnApplyBtn(driver);
			if (!result) {
				communityPageMsgList.add("Unable to verify apply btn filter page");
				return result;
			}

			result = verifyAllPostAreSelectedGroup(driver, strPostGroup);
			if (!result) {
				communityPageMsgList.add("The group filter is not working");
				return result;
			}
		} catch (Exception e) {
			communityPageMsgList.add("clickOnResetBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean selectGroup(AppiumDriver<MobileElement> driver, String strPostGroup) {
		boolean result = true;
		try {
			cfObj.commonClick(communityPageObj.getTabGroups());
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.widget.CheckBox[contains(@content-desc,'" + strPostGroup + "')]", "xpath", 10))
					cfObj.commonClick(cfObj.commonGetElement(driver,
							"//android.widget.CheckBox[contains(@content-desc,'" + strPostGroup + "')]", "xpath"));
			} else {
				if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//XCUIElementTypeSwitch[contains(@name,'" + strPostGroup + "')]", "xpath", 10))
					cfObj.commonClick(cfObj.commonGetElement(driver,
							"//XCUIElementTypeSwitch[contains(@name,'" + strPostGroup + "')]", "xpath"));
			}

		} catch (Exception e) {
			communityPageMsgList.add("selectGroup_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyAllPostAreSelectedGroup(AppiumDriver<MobileElement> driver, String strPostGroup) {
		boolean result = true;
		int scroll = 0;
		try {

			while (scroll < 5) {
				// List<MobileElement> el = communityPageObj.getPostGroupList();
				if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
					List<MobileElement> el = cfObj.commonGetElements(driver,
							"//*[@content-desc='openBottomSheet']/preceding-sibling::android.view.View[1]", "xpath");
					for (int i = 0; i < el.size(); i++) {
						if (!(el.get(i).getAttribute("content-desc").contains(strPostGroup))) {
							System.out.println("This: " + el.get(i).getAttribute("content-desc")
									+ " string does not contains " + strPostGroup);
							return result;
						}
					}
				} else {
					List<MobileElement> el = cfObj.commonGetElements(driver,
							"//XCUIElementTypeStaticText[contains(@name,'userProfileLocator')]/following-sibling::XCUIElementTypeStaticText",
							"xpath");
					for (int i = 0; i < el.size(); i++) {
						if (!(el.get(i).getAttribute("name").contains(strPostGroup))) {
							System.out.println("This: " + el.get(i).getAttribute("name") + " string does not contains "
									+ strPostGroup);
							return result;
						}
					}
				}
				cfObj.scrollUtill(driver, 1, direction.DOWN);
				scroll++;

			}
		} catch (Exception e) {
			communityPageMsgList.add("verifyAllPostAreSelectedGroup_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyTopicsFilter(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			/*
			 * Need to change this function the current logic is not working
			 */

			result = clickOnFilterBtn(driver);
			if (!result) {
				communityPageMsgList.add("Unable to verify filter btn");
				return result;
			}

			result = clickOnResetBtn(driver);
			if (!result) {
				communityPageMsgList.add("Unable to verify Reset btn filter page");
				return result;
			}

			result = selectTopic(driver, "");
			if (!result) {
				communityPageMsgList.add("Unable to verify Reset btn filter page");
				return result;
			}

			result = clickOnApplyBtn(driver);
			if (!result) {
				communityPageMsgList.add("Unable to verify apply btn filter page");
				return result;
			}

			result = verifyAllPostAreSelectedTopic(driver, "");
			if (!result) {
				communityPageMsgList.add("Unable to verify apply btn filter page");
				return result;
			}

		} catch (Exception e) {
			communityPageMsgList.add("verifyTopicsFilter_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean selectTopic(AppiumDriver<MobileElement> driver, String strPostTopic) {
		boolean result = true;
		boolean found = false;
		int index = 0;
		try {
			cfObj.commonClick(communityPageObj.getTabTopics());

			// communityPageObj.getFieldSearchGroups().click();
			// communityPageObj.getFieldSearchGroups().sendKeys(strPostTopic); // wasn't
			// able to use cfObj

			while (!found) {
				if (communityPageObj.getOptionsCheckBox().get(index).getAttribute("content-desc")
						.equalsIgnoreCase(strPostTopic)) {
					cfObj.commonClick(communityPageObj.getOptionsCheckBox().get(index));
					found = true;
					break;
				}
				index++;
			}

		} catch (Exception e) {
			communityPageMsgList.add("selectTopic_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyAllPostAreSelectedTopic(AppiumDriver<MobileElement> driver, String strPostTopic) {
		boolean result = true;
		try {

			MobileElement el = communityPageObj.getListPost().get(0);

			if (!(el.getAttribute("content-desc").contains(strPostTopic))) {
				result = false;
				return result;
			}

		} catch (Exception e) {
			communityPageMsgList.add("verifyAllPostAreSelectedTopic_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyPostTypeFilter(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = clickOnFilterBtn(driver);
			if (!result) {
				communityPageMsgList.add("Unable to verify filter btn");
				return result;
			}

			result = clickOnResetBtn(driver);
			if (!result) {
				communityPageMsgList.add("Unable to verify Reset btn filter page");
				return result;
			}
			// have to chenge the implementation
			result = selectPostType(driver, "");
			if (!result) {
				communityPageMsgList.add("Unable to verify Reset btn filter page");
				return result;
			}

			result = clickOnApplyBtn(driver);
			if (!result) {
				communityPageMsgList.add("Unable to verify apply btn filter page");
				return result;
			}

			result = verifyAllPostAreSelectedPostType(driver, "");
			if (!result) {
				communityPageMsgList.add("The post type filter is not working");
				return result;
			}

		} catch (Exception e) {
			communityPageMsgList.add("verifyPostTypeFilter_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean selectPostType(AppiumDriver<MobileElement> driver, String strPostType) {
		boolean result = true;
		boolean found = false;
		int index = 0;
		try {
			cfObj.commonClick(communityPageObj.getTabPostType());

			// communityPageObj.getFieldSearchGroups().click();
			// communityPageObj.getFieldSearchGroups().sendKeys(strPostType); // wasn't able
			// to use cfObj

			while (!found) {
				if (communityPageObj.getOptionsCheckBox().get(index).getAttribute("content-desc")
						.equalsIgnoreCase(strPostType)) {
					cfObj.commonClick(communityPageObj.getOptionsCheckBox().get(index));
					found = true;
					break;
				}
				index++;
			}

		} catch (Exception e) {
			communityPageMsgList.add("selectPostType_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyAllPostAreSelectedPostType(AppiumDriver<MobileElement> driver, String strPostType) {
		boolean result = true;
		try {

			MobileElement el = communityPageObj.getListPost().get(0);

			if (!(el.getAttribute("content-desc").contains(strPostType))) {
				communityPageMsgList
						.add("The post: " + el.getAttribute("content-desc") + "doesn't contains" + strPostType);
				result = false;
				return result;
			}

		} catch (Exception e) {
			communityPageMsgList.add("verifyAllPostAreSelectedPostType_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyLikeCommentAndPostDetails(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = verifyLikeCommentCount(driver);
			if (!result) {
				communityPageMsgList.add("Unable to verify like and comment on post");
				return result;
			}

			result = verifyPostDetails(driver);
			if (!result) {
				communityPageMsgList.add("Unable to verify post detail");
				return result;
			}
		} catch (Exception e) {
			communityPageMsgList.add("verifyLikeCommentAndPostDetails_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyLikeCommentCount(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		loginUtilObj = new LoginUtil(driver);
		try {
			result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "9"), false);
			if (!result) {
				communityPageMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			homeUtilObj = new HomePageUtil(driver);
			result = homeUtilObj.clickCommunityBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			socialJsonObject = cfObj.getJsonData("src//main//resources//socialData.json");
			if (socialJsonObject == null) {
				communityPageMsgList.add("Social Json Object is null.");
			}

			result = addNewAttachmentPost(driver);
			if (!result) {
				communityPageMsgList.add("Unable to create post.");
				return result;
			}

			Thread.sleep(4000);

			result = verifyLikePost(driver, socialJsonObject.get("postContent").getAsString());
			if (!result) {
				communityPageMsgList.add("Unable to verify like post");
				return result;
			}

			result = savePost(driver);
			if (!result) {
				communityPageMsgList.add("Unable to save post");
				return result;
			}

			result = verifyCommentAndSubCommentOnPost(driver, socialJsonObject.get("postComment").getAsString(),
					socialJsonObject.get("postContent").getAsString());
			if (!result) {
				communityPageMsgList.add("Unable to verify comment post for verifyCommentAndSubCommentOnPost");
				return result;
			}
		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifyLikeCommentCount_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyLikePost(AppiumDriver<MobileElement> driver, String strPostContent) {
		boolean result = true;
		try {
			cfObj.commonClick(communityPageObj.getBtnLike().get(0));

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getToastPostLikedSuccessfully(),
					10);
			if (!result) {
				communityPageMsgList.add(
						"Unable to verify successful like message, the message may have appeared but was too fast to detect");
			}

			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = communityPageObj.getBtnLike().get(0).getAttribute("content-desc").contains("1");
			} else {
				result = communityPageObj.getBtnLike().get(0).getAttribute("name").contains("1");
			}
			if (!result) {
				communityPageMsgList.add("Unable to verify like count");
				return result;
			}

			Thread.sleep(1500);

			cfObj.commonClick(communityPageObj.getBtnLike().get(0));
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = communityPageObj.getBtnLike().get(0).getAttribute("content-desc").contains("0");
			} else {
				result = communityPageObj.getBtnLike().get(0).getAttribute("name").contains("0");
			}
			if (!result) {
				communityPageMsgList.add("Unable to verify like count");
				return result;
			}

		} catch (Exception e) {
			communityPageMsgList.add("likePost_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public String savePost(AppiumDriver<MobileElement> driver, String postContentDesc) {
		boolean result = true;
		try {

			cfObj.commonClick(communityPageObj.getBtnSave().get(0));

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getToastPostHasBeenBookmarked(),
					10);
			if (!result) {
				communityPageMsgList.add(
						"Unable to verify successful bookmark message, the message may have appeared but was unable to detect");
			}

			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				postContentDesc = cfObj
						.commonGetElements(driver, "//*[@content-desc='Automation']/parent::android.view.View", "xpath")
						.get(0).getAttribute("text");
			} else {
				postContentDesc = communityPageObj.getListPost().get(0).getAttribute("name");
			}
			if (postContentDesc == null) {
				communityPageMsgList.add("Post Content description is null.");
			}

		} catch (Exception e) {
			communityPageMsgList.add("bookmarkPost_Exception: " + e.getMessage());
			result = false;
		}
		return postContentDesc;
	}

	public boolean savePost(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.commonClick(communityPageObj.getBtnSave().get(0));

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getToastPostHasBeenBookmarked(),
					10);
			if (!result) {
				communityPageMsgList.add(
						"Unable to verify successfull bookmark message, the message may have appeated but was unables to detect");
				result = true;
			}

		} catch (Exception e) {
			communityPageMsgList.add("bookmarkPost_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyCommentAndSubCommentOnPost(AppiumDriver<MobileElement> driver, String strPostComment,
			String strPostContent) {
		boolean result = true;
		try {
			result = clickOnCommentBtn(driver);
			if (!result) {
				communityPageMsgList.add("Unable to click on comment btn");
				return result;
			}

			result = setCommentField(driver, strPostComment);
			if (!result) {
				communityPageMsgList.add("Unable to enter comment");
				return result;
			}

			result = clickOnSendCommentBtn(driver, strPostComment);
			if (!result) {
				communityPageMsgList.add("Unable to send comment");
				return result;
			}

			result = clickOnLikeCommentBtn(driver);
			if (!result) {
				communityPageMsgList.add("Unable to like comment");
				return result;
			}

			result = clickOnReplyCommentBtn(driver);
			if (!result) {
				communityPageMsgList.add("Unable to reply on comment");
				return result;
			}

			result = setCommentField(driver, "Sub-comment");
			if (!result) {
				communityPageMsgList.add("Unable to enter sub-comment");
				return result;
			}

			result = clickOnSendSubCommentBtn(driver, "Sub-comment");
			if (!result) {
				communityPageMsgList.add("Unable to send sub-comment");
				return result;
			}

			// verifying reply count on comment
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@content-desc,'| 1 Reply')]", "xpath", 10);
			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@name,'| 1 Reply')]",
						"xpath", 10);
			}
			if (!result) {
				communityPageMsgList.add("Unable to verify sub-comment like count");
				return result;
			}

			result = clickOnCloseBtn(driver);
			if (!result) {
				communityPageMsgList.add("Unable to enter comment");
				return result;
			}
		} catch (Exception e) {
			communityPageMsgList.add("verifyCommentAndSubCommentOnPost_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnCommentBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForListOfElementsToBeVisible(driver, communityPageObj.getBtnComment(), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify comment btn");
				return result;
			}
			cfObj.commonClick(communityPageObj.getBtnComment().get(0));

		} catch (Exception e) {
			communityPageMsgList.add("clickOnCommentBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyCommentPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getCloseCommentsBtn(), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify in close btn comment page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getTextFieldComment(), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify comment field in comment page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getBtnSendComment(), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify send btn in comment page");
				return result;
			}

		} catch (Exception e) {
			communityPageMsgList.add("verifyCommentPage_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean setCommentField(AppiumDriver<MobileElement> driver, String strComment) {
		boolean result = true;
		try {
			result = cfObj.commonSetTextTextBox(communityPageObj.getTextFieldComment(), strComment);
			if (!result) {
				communityPageMsgList.add("Failed to enter comment on CommentTextField.");
				return result;
			}
		} catch (Exception e) {
			communityPageMsgList.add("setCommentField_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnSendCommentBtn(AppiumDriver<MobileElement> driver, String strPostComment) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getBtnSendComment(), 10);
			if(!result){
				communityPageMsgList.add("getBtnSendComment is not visible");
				return result;
			}
			cfObj.commonClick(communityPageObj.getBtnSendComment());

			Thread.sleep(5000);
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@text,'" + strPostComment + "')]", "xpath", 10);
			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@value,'" + strPostComment + "')]", "xpath", 10);
			}
			if (!result) {
				communityPageMsgList.add("Post Comment is not visible.");
			}

		} catch (Exception e) {
			communityPageMsgList.add("clickOnSendCommentBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnSendSubCommentBtn(AppiumDriver<MobileElement> driver, String strPostSubComment) {
		boolean result = true;
		try {

			cfObj.commonClick(communityPageObj.getBtnSendComment());
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@text,'" + strPostSubComment + "')]", "xpath", 10);
			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@name,'" + strPostSubComment + "')]", "xpath", 10);
			}
			if (!result) {
				communityPageMsgList.add("SubPost Comment is not visible.");
			}
		} catch (Exception e) {
			communityPageMsgList.add("clickOnSendSubCommentBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnCloseBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			if(!ConfigFileReader.strRunMode.equalsIgnoreCase("localLab")){
				driver.hideKeyboard();
			}
			driver.navigate().back();

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getTabYourGroup(), 10);
			if (!result) {
				driver.navigate().back();

				result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getTabYourGroup(), 10);
				if (!result) {
					communityPageMsgList.add("Unable to verify your group tab on community page");
					return result;
				}
			}
		} catch (Exception e) {
			communityPageMsgList.add("clickOnCommentBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyPostDetails(AppiumDriver<MobileElement> driver) {
		boolean result = true;

		try {
			result = clickOnTopPost(driver);
			if (!result) {
				communityPageMsgList.add("Unable to open top post");
				return result;
			}

			result = clickDotMenu(driver);
			if (!result) {
				communityPageMsgList.add("Unable to verify dot menu");
				return result;
			}

			result = editPost(driver);
			if (!result) {
				communityPageMsgList.add("Unable to Edit post.");
				return result;
			}

			if(!ConfigFileReader.strRunMode.equalsIgnoreCase("localLab")){
				driver.hideKeyboard();
			}

			result = clickDotMenu(driver);
			if (!result) {
				communityPageMsgList.add("Unable to verify dot menu");
				return result;
			}

			result = deletePost(driver);
			if (!result) {
				communityPageMsgList.add("Unable to delete post");
				return result;
			}
		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifySelectGroupPage_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickDotMenu(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getBtnDotMenuPostDetailPage(), 10);
			if(!result){
				communityPageMsgList.add("Failed to verify getBtnDotMenuPostDetailPage.");
				return result;
			}

			cfObj.commonClick(communityPageObj.getBtnDotMenuPostDetailPage());

			result = verifyDotMenu(driver);
			if (!result) {
				communityPageMsgList.add("Failed to verify Dot menu.");
			}

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifySelectGroupPage_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyDotMenu(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getOptionDeletePost(), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify delete option in dot menu post detail page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getOptionEditPost(), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify Edit option in dot menu post detail page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getOptionTurnOffNotification(), 10);
			if (!result) {
				communityPageMsgList.add(
						"Unable to verify Turn off notification fot this post option in dot menu post detail page");
				return result;
			}
		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifyDotMenu_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean deletePost(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.commonClick(communityPageObj.getOptionDeletePost());
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				cfObj.pressAndroidKey(driver, key.BACK, 1);
			} else {
				cfObj.commonClick(communityPageObj.getCloseCommentsBtn());
			}

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifySelectGroupPage_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean editPost(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			postingPageUtilObj = new PostingPageUtil(driver);

			cfObj.commonClick(communityPageObj.getOptionEditPost());

			String strPostContent = socialJsonObject.get("postContent").getAsString() + "Edited";
			postingPageUtilObj.postingPageObj.getPostQueryTextField().clear();

			result = postingPageUtilObj.enterQueryOrDoubt(driver, strPostContent);
			if (!result) {
				communityPageMsgList.add("Unable to Re-enter query in posting page");
				return result;
			}

			Thread.sleep(1000);

			if(!ConfigFileReader.strRunMode.equalsIgnoreCase("localLab")){
				driver.hideKeyboard();
			}
			result = postingPageUtilObj.clickPostSubmitBtn(driver);
			if (!result) {
				communityPageMsgList.add("Unable to select type of post from slider menu");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,
					postingPageUtilObj.postingPageObj.getTitleSelectTopic(), 10);
			if (result) {
				List<MobileElement> el = postingPageUtilObj.postingPageObj.getOptionTopics();

				cfObj.commonClick(el.get(0));
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@content-desc='Update Post']",
					"xpath", 10);
			if (!result) {
				communityPageMsgList.add("Post Update button is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@content-desc='Update Post']", "xpath"));
			cfObj.waitForPageLoading(driver, 10, 2000,
					cfObj.commonGetElement(driver, "//*[contains(@text,'" + strPostContent + "')]", "xpath"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//*[contains(@text,'" + strPostContent + "')]", "xpath", 10);
			if (!result) {
				communityPageMsgList.add("Unable to Edit Post.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("editPost_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnTopPost(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			HomePageUtil homePageUtil = new HomePageUtil(driver);
			cfObj.commonClick(homePageUtil.homePageObj.getBtnCommunity());

			Thread.sleep(4000);

			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				cfObj.commonClick(cfObj.commonGetElements(driver,
						"//*[contains(@content-desc,'Automation')]/parent::android.view.View", "xpath").get(0));
			} else {
				cfObj.commonClick(cfObj.commonGetElements(driver,
						"//*[contains(@name,'Automation')]/parent::XCUIElementTypeOther", "xpath").get(0));
			}

			Thread.sleep(1000);

			result = verifyPostDetailPage(driver);
			if (!result) {
				communityPageMsgList.add("Failed to verify Post details page.");
			}

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifySelectGroupPage_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyPostDetailPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getBtnDotMenuPostDetailPage(), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify btn dot menu post detail page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getBtnLikePostDetailPage(), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify btn like post detail page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getBtnSavePostDetailPage(), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify save btn post detail page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getIconSharePostDetailPage(), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify share icon post detail page");
				return result;
			}

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifySelectGroupPage_Exception: " + e.getMessage());
		}
		return result;
	}

	// ------------------------------------------------------------------------------------------------------------------------------
	public boolean verifyCommunityPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getBtnFilter(), 30);
			if (!result) {
				communityPageMsgList.add("Unable to verify filter btn");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getBtnAddPost(), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify Add Post btn");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getTabAllPost(), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify All post tab");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getTabYourGroup(), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify your group tab");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getTabExploreGroup(), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify Explore groups tab");
				return result;
			}
			int i = 0;
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				while (!cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@content-desc,'moveToComment')]", "xpath", 5)) {
					cfObj.scrollUtill(driver, 1, direction.DOWN);
					if (i > 5) {
						System.out.println("Element is not present.");
						break;
					}
					i++;
				}
			} else {
				while (!cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@name,'moveToComment')]",
						"xpath", 5)) {
					cfObj.scrollUtill(driver, 1, direction.DOWN);
					if (i > 5) {
						System.out.println("Element is not present.");
						break;
					}
					i++;
				}

			}
			cfObj.scrollUtill(driver, i, direction.UP);

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getBtnComment().get(0), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify comment btn");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getBtnLike().get(0), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify like btn");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getBtnSave().get(0), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify save btn");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getBtnShare().get(0), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify share btn");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getListDotMenu().get(0), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify Add Post btn");
				return result;
			}
		} catch (Exception e) {
			communityPageMsgList.add("verifyCommunityPage_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnLikeCommentBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.commonClick(communityPageObj.getBtnLikeComment().get(0));

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getSuccessfulLikedCommentMessage(),
					10);
			if (!result) {
				System.out.println("SuccessfulLikedCommentMessage is not visible.");
			}

			Thread.sleep(1000);

			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = communityPageObj.getBtnLikeComment().get(0).getAttribute("content-desc").contains("1");
			} else {
				result = communityPageObj.getBtnLikeComment().get(0).getAttribute("name").contains("1");
			}
			if (!result) {
				communityPageMsgList.add("Like count is not updated.");
			}

		} catch (Exception e) {
			communityPageMsgList.add("clickOnLikeCommentBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnReplyCommentBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.commonClick(communityPageObj.getBtnReplyComment());

			result = verifySubCommentWindow(driver);
			if (!result) {
				communityPageMsgList.add("Failed to verify Sub comment window.");
			}

		} catch (Exception e) {
			communityPageMsgList.add("clickOnLikeCommentBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifySubCommentWindow(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getBtnSendComment(), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify send comment button in sub-comment page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getTextFieldComment(), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify text field in sub-comment page");
				return result;
			}

		} catch (Exception e) {
			communityPageMsgList.add("verifySubCommentPage_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnResetBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.commonClick(communityPageObj.getBtnReset());

		} catch (Exception e) {
			communityPageMsgList.add("clickOnResetBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnApplyBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getBtnApply(), 10);
			if(!result){
				communityPageMsgList.add("getBtnApply is not visible");
				return result;
			}
			cfObj.commonClick(communityPageObj.getBtnApply());

			result = verifyCommunityPage(driver);
			if (!result) {
				communityPageMsgList.add("Failed to verify Community page.");
			}
		} catch (Exception e) {
			communityPageMsgList.add("clickOnApplyBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean selectAllCheckBox(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			List<MobileElement> el = communityPageObj.getOptionsRadioBtn();

			for (int i = 0; i < el.size(); i++) {
				el.get(i).click();
			}

		} catch (Exception e) {
			communityPageMsgList.add("selectAllCheckBox_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnFilterBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.commonClick(communityPageObj.getBtnFilter());

			result = verifyFilterPostPage(driver);
			if (!result) {
				communityPageMsgList.add("Failed to verify Filter Post page.");
			}

		} catch (Exception e) {
			communityPageMsgList.add("clickOnFilterBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyFilterPostPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getTabGroups(), 0);
			if (!result) {
				communityPageMsgList.add("Unable to verify group tab in post filter page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getTabTopics(), 0);
			if (!result) {
				communityPageMsgList.add("Unable to verify topics tab in post filter page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getTabPostType(), 0);
			if (!result) {
				communityPageMsgList.add("Unable to verify post type tab in post filter page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getBtnReset(), 0);
			if (!result) {
				communityPageMsgList.add("Unable to verify reset btn in filter page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getBtnApply(), 0);
			if (!result) {
				communityPageMsgList.add("Unable to verify apply btn in filter page");
				return result;
			}

			// result = cfObj.commonWaitForElementToBeVisible(driver,
			// communityPageObj.getFieldSearchGroups(), 0);
			// if (!result) {
			// communityPageMsgList.add("Unable to verify search field in filter page");
			// return result;
			// }

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getBtnClose(), 0);
			if (!result) {
				communityPageMsgList.add("Unable to verify close btn in filter page");
				return result;
			}

		} catch (Exception e) {
			communityPageMsgList.add("verifyFilterPostPage_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyChooseTheTypeOfPostSlider(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getTitleSliderTypeOfPost(), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify title in Slider menu");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,
					communityPageObj.getOptionPictureVideoAttachmentPost(), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify Picture, Videos and attachment post in Slider menu");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getOptionMCQs(), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify MCQs option in slider menu");
				return result;
			}

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifyChooseTheTypeOfPostSlider_Exception: " + e.getMessage());
		}
		return result;
	}

	// -----------------------------------------Follow unfollow
	// group--------------------------------------------------------------------------

	public boolean verifyFollowUnfollowGroup(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			loginUtilObj = new LoginUtil(driver);
			result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), false);
			if (!result) {
				communityPageMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			homeUtilObj = new HomePageUtil(driver);
			result = homeUtilObj.clickCommunityBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = verifyFollowGroup(driver);
			if (!result) {
				communityPageMsgList.add("Unable to verify follow group");
				return result;
			}

			result = verifyUnfollowGroup(driver);
			if (!result) {
				communityPageMsgList.add("Unable to verify unfollow group");
				return result;
			}
		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifyFollowUnfollowGroup_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyFollowGroup(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String strGroupTitle = null;
		try {

			result = clickYourGroupTab(driver);
			if (!result) {
				communityPageMsgList.add("Unable to open Your group tab");
				return result;
			}

			Thread.sleep(1000);

			result = verifyEmptyGroupYouAreFollowingSection(driver);
			if (!result) {
				communityPageMsgList.add("The Group you are following section is not empty");
				return result;
			}

			Thread.sleep(1000);

			result = clickOnExploreMoreGroupsToFollowBtn(driver);
			if (!result) {
				communityPageMsgList
						.add("The explore more groups to follow btn is not redirecting to explore groups page");
				return result;
			}

			Thread.sleep(1000);

			strGroupTitle = followFirstAvailableGroup(driver, strGroupTitle);
			if (!result) {
				communityPageMsgList.add("Unable to follow the first available group");
				return result;
			}

			Thread.sleep(1000);

			result = clickYourGroupTab(driver);
			if (!result) {
				communityPageMsgList.add("Unable to open Your group tab");
				return result;
			}

			Thread.sleep(1000);

			result = verifyFollowedGroupInFollowingSection(driver, strGroupTitle);
			if (!result) {
				communityPageMsgList.add("The followed group is not available in following section");
				return result;
			}

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifyFollowGroup_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickYourGroupTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.commonClick(communityPageObj.getTabYourGroup());

			cfObj.skipCoachMarkSocial(driver);

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,\"Banking groups you are following\")]", "xpath", 5);
			if(!result){
				System.out.println("The banking coachmark is not visible in Your Group");
			} else {
				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[contains(@content-desc,\"Banking groups you are following\")]", "xpath"));

				result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getTabYourGroup(), 5);
				if (!result){
					driver.navigate().back();

					result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getTabYourGroup(), 10);
					if (!result){
						communityPageMsgList.add("Your group text is not visible");
						return result;
					}
				}
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,\"Your created groups\")]", "xpath", 5);
			if(!result){
				System.out.println("The created coachmark is not visible in Your Group");
            } else {
				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[contains(@content-desc,\"Your created groups\")]", "xpath"));

				result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getTabYourGroup(), 5);
				if (!result){
					driver.navigate().back();

					result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getTabYourGroup(), 10);
					if (!result){
						communityPageMsgList.add("Your group text is not visible");
						return result;
					}
				}
			}

			result = verifyYourGroupTab(driver);
			if (!result) {
				communityPageMsgList.add("Failed to verify YourGroup tab.");
			}

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("clickYourGroupTab_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyYourGroupTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver,
					communityPageObj.getSectionOfficialBankingGroups(), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify Official groups section");
				return result;
			}
		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifyYourGroupTab_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyEmptyGroupYouAreFollowingSection(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		int noOfGroups = 0;
		try {

			Thread.sleep(1000);

			noOfGroups = communityPageObj.getGroupList().size();

			if (noOfGroups > 0) {
				result = false;
				communityPageMsgList.add("There are " + noOfGroups + "in the Group You are following section");
			}

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifyEmptyGroupYouAreFollowingSection_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnExploreMoreGroupsToFollowBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.commonClick(communityPageObj.getBtnExploreMoreGroupToFollow());
			if (!(ConfigFileReader.strApplication.equalsIgnoreCase("Sankalp")
					&& ConfigFileReader.strEnv.equalsIgnoreCase("PRODUCTION"))) {

				result = verifyExploreMoreGroupsTab(driver);
				if (!result) {
					communityPageMsgList.add("Failed to verify ExploreMoreGroups Tab.");
				}
			}

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("clickOnExploreMoreGroupsToFollowBtn_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyExploreMoreGroupsTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getSectionLatestIntroducedGroup(), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify Latest Introduced Groups");
				return result;
			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"shareIcon\"]", "xpath", 10);
				if (!result) {
					communityPageMsgList.add("Unable to verify Share btn on group for latest groups.");
					return result;
				}
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getSectionOtherGroups(), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify Other Groups section");
				return result;
			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"SHARE\"]", "xpath", 10);
				if (!result) {
					communityPageMsgList.add("Unable to verify Share btn on group for other groups.");
					return result;
				}
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"Follow\"]", "xpath", 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify follow btn on group");
				return result;
			}
		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifyExploreMoreGroupsTab_Exception: " + e.getMessage());
		}
		return result;
	}

	public String followFirstAvailableGroup(AppiumDriver<MobileElement> driver, String strGroupTitle) {
		boolean result = true;
		int index = 0;
		try {

			result = cfObj.updateListSize(communityPageObj.getListGroupTiles());
			if (!result) {
				communityPageMsgList.add("Group list is empty");
			}

			Thread.sleep(1000);

			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				strGroupTitle = communityPageObj.getListGroupTiles().get(index).getAttribute("content-desc");
			} else {
				strGroupTitle = communityPageObj.getListGroupTiles().get(index).getAttribute("name");
			}
			if (strGroupTitle == null) {
				communityPageMsgList.add("Group title text is null.");
			}

			cfObj.commonClick(communityPageObj.getBtnFollowGroup().get(0));

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("followFirstAvailableGroup_Exception: " + e.getMessage());
		}
		return strGroupTitle;
	}

	public boolean verifyFollowedGroupInFollowingSection(AppiumDriver<MobileElement> driver, String strGroupTitle) {
		boolean result = true;
		try {

			Thread.sleep(1000);

			if (communityPageObj.getGroupList().size() != 1) {
				result = false;
				communityPageMsgList.add("There is more than one group in followed section");
			}

			String strGroupTitleList[] = strGroupTitle.split("\\r?\\n");
			strGroupTitle = strGroupTitleList[1];
			if (strGroupTitle == null) {
				communityPageMsgList.add("Group title text is null.");
				return false;
			}
			System.out.println(strGroupTitle);
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@content-desc, '" + strGroupTitle + "')]", "xpath", 10);
			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@name, '" + strGroupTitle + "')]", "xpath", 10);
			}
			if (!result) {
				communityPageMsgList.add("Group title text is not visible.");
			}

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifyFollowedGroupInFollowingSection_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyUnfollowGroup(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = clickExploreGroupTab(driver);
			if (!result) {
				communityPageMsgList.add("Unable to move to explore group page");
				return result;
			}

			Thread.sleep(1000);

			result = unfollowFollowedGroup(driver);
			if (!result) {
				communityPageMsgList.add("Unable to unfollow followed group");
				return result;
			}

			Thread.sleep(1000);

			result = clickYourGroupTab(driver);
			if (!result) {
				communityPageMsgList.add("Unable to open Your group tab");
				return result;
			}

			Thread.sleep(1000);

			result = verifyEmptyGroupYouAreFollowingSection(driver);
			if (!result) {
				communityPageMsgList.add("The Group you are following section is not empty");
				return result;
			}

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifyUnfollowGroup_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickExploreGroupTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getTabExploreGroup(), 10);
			if (!result) {
				communityPageMsgList.add("getTabExploreGroup btn is not visible");
				return result;
			}
			cfObj.commonClick(communityPageObj.getTabExploreGroup());

			if (!(ConfigFileReader.strApplication.equalsIgnoreCase("Sankalp")
					&& ConfigFileReader.strEnv.equalsIgnoreCase("PRODUCTION"))) {

				result = verifyExploreMoreGroupsTab(driver);
				if (!result) {
					communityPageMsgList.add("Failed to verify ExploreMoreGroups tab.");
				}
			}
		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("clickExploreGroupTab_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean unfollowFollowedGroup(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getBtnUnfollowGroup().get(0), 10);
			if (!result) {
				communityPageMsgList.add("getBtnUnfollowGroup btn at 1st postion is not visible");
				return result;
			}
			cfObj.commonClick(communityPageObj.getBtnUnfollowGroup().get(0));

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("unfollowFollowedGroup_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyShareGroup(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			loginUtilObj = new LoginUtil(driver);
			result = loginUtilObj.verifyLoginGuestUser(driver);
			if (!result) {
				communityPageMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			socialJsonObject = cfObj.getJsonData("src//main//resources//socialData.json");
			if (socialJsonObject == null) {
				communityPageMsgList.add("Social Json Object is null.");
			}

			homeUtilObj = new HomePageUtil(driver);

			result = homeUtilObj.clickCommunityBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			Thread.sleep(1000);

			result = verifyShareGroupFromGroupFeedPage(driver, socialJsonObject);
			if (!result) {
				communityPageMsgList.add("Failed to verify ShareGroupFromGroupFeedPage.");
				return result;
			}

			Thread.sleep(1000);

			result = verifyShareGroupFromShareIcon(driver, socialJsonObject);
			if (!result) {
				communityPageMsgList.add("Failed to verify ShareGroupFromShareIcon.");
				return result;
			}

			Thread.sleep(1000);

			result = verifyShareGroupFromShareButton(driver, socialJsonObject);
			if (!result) {
				communityPageMsgList.add("Failed to verify ShareGroupFromShareButton.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifyShareGroup_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyShareGroupFromGroupFeedPage(AppiumDriver<MobileElement> driver, JsonObject socialJsonObject) {
		boolean result = true;
		try {

			result = clickOnFirstPostGroup(driver);
			if (!result) {
				communityPageMsgList.add("Unable to open the group of top post in page");
				return result;
			}

			result = clickOnShareButtonInGroupFeedPage(driver);
			if (!result) {
				communityPageMsgList.add("Unable to click share button in group page");
				return result;
			}

			result = shareGroup(driver, socialJsonObject);
			if (!result) {
				communityPageMsgList.add("Unable to share group");
				return result;
			}
			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
				cfObj.waitForPageLoading(driver, 10, 2000, cfObj.commonGetElement(driver,
						"//*[contains(@content-desc,'test automation group')]", "xpath"));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@content-desc,'test automation group')]", "xpath", 10);
			} else {
				cfObj.waitForPageLoading(driver, 10, 2000,
						cfObj.commonGetElement(driver, "//*[contains(@content-desc,'is for automation')]", "xpath"));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@content-desc,'is for automation')]", "xpath", 10);
			}
			if (!result) {
				communityPageMsgList.add("Shared Post is not visible on GroupFeedPage.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifyShareGroupFromShareButton_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnFirstPostGroup(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String strGroupTitle = null;
		try {
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				int i = 0;
				while (!cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@content-desc,'Automation')]", "xpath", 5)) {
					cfObj.scrollUtill(driver, 1, direction.DOWN);
					if (i > 5) {
						System.out.println("Automation group is not available.");
						result = false;
						break;
					}
					i++;
				}
				if (!result) {
					communityPageMsgList.add("Automation group is not available.");
					return false;
				}
				result = cfObj.updateListSize(
						cfObj.commonGetElements(driver, "//*[contains(@content-desc,'Automation')]", "xpath"));
				if (!result) {
					communityPageMsgList.add("The group list in empty");
					return result;
				}

				strGroupTitle = cfObj.commonGetElements(driver, "//*[contains(@content-desc,'Automation')]", "xpath")
						.get(0).getAttribute("content-desc");
				if (strGroupTitle == null) {
					communityPageMsgList.add("Group title text is null.");
					return false;
				}
				cfObj.commonClick(
						cfObj.commonGetElements(driver, "//*[contains(@content-desc,'Automation')]", "xpath").get(0));
			} else {
				int i = 0;
				while (!cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@name='Automation']", "xpath",
						5)) {
					cfObj.scrollUtill(driver, 1, direction.DOWN);
					if (i > 5) {
						System.out.println("Automation group is not available.");
						result = false;
						break;
					}
					i++;
				}
				if (!result) {
					communityPageMsgList.add("Automation group is not available.");
					return false;
				}
				result = cfObj.updateListSize(cfObj.commonGetElements(driver, "//*[@name='Automation']", "xpath"));
				if (!result) {
					communityPageMsgList.add("The group list in empty");
					return result;
				}

				strGroupTitle = cfObj.commonGetElements(driver, "//*[@name='Automation']", "xpath").get(0)
						.getAttribute("name");
				if (strGroupTitle == null) {
					communityPageMsgList.add("Group title text is null.");
					return false;
				}
				cfObj.commonClick(cfObj.commonGetElements(driver, "//*[@name='Automation']", "xpath").get(0));
			}
			result = verifyGroupPage(driver, strGroupTitle);
			if (!result) {
				communityPageMsgList.add("Failed to verify Group page.");
			}

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("clickOnFirstPostGroup_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyGroupPage(AppiumDriver<MobileElement> driver, String strGroupTitle) {
		boolean result = true;
		try {

			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@content-desc,'" + strGroupTitle + "')]", "xpath", 15);
			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@name,'" + strGroupTitle + "')]", "xpath", 10);
			}
			if (!result) {
				communityPageMsgList.add("Unable to verify group page title");
				return result;
			}

			cfObj.skipCoachMarkSocial(driver);

			result = validateGroupUi(driver);
			if (!result) {
				communityPageMsgList.add("Not able to verify Group UI.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifyGroupPage_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean validateGroupUi(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@content-desc,'Daily Posts')]", "xpath", 10);
				if (!result) {
					communityPageMsgList.add("Unable to verify Groups post");
					return result;
				}
				if (!cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'like')]",
						"xpath", 5)) {
					cfObj.scrollUtill(driver, 1, direction.DOWN);
				}

			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@name,'Daily Posts')]",
						"xpath", 10);
				if (!result) {
					communityPageMsgList.add("Unable to verify Groups post");
					return result;
				}
				if (!cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@name,'like')]", "xpath",
						5)) {
					cfObj.scrollUtill(driver, 1, direction.DOWN);
				}
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getBtnLike().get(0), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify like button in post");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getBtnComment().get(0), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify comment btn in post");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getBtnShare().get(0), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify share btn in post");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getBtnSave().get(0), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify save btn in post");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getListDotMenu().get(0), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify dots menu in post");
				return result;
			}
		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifyGroupUi_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnShareButtonInGroupFeedPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.commonClick(communityPageObj.getBtnShareGroupPage());

			result = verifyShareGroupSlider(driver);
			if (!result) {
				communityPageMsgList.add("Failed to verify ShareGroup slider.");
			}

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("clickOnShareButtonInGroupPage_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyShareGroupSlider(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getBtnShareVia(), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify Share via btn");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getOptionCopyToClipboard(), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify Copy to clipboard option");
				return result;
			}
			if (ConfigFileReader.strApplication.equalsIgnoreCase("adda")) {
				result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getTitleAddaGroups(), 10);
			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[@content-desc='Sankalp Bharat Groups']", "xpath", 10);
			}
			if (!result) {
				communityPageMsgList.add("Unable to verify title adda group");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getBtnCloseShareGroupSlider(), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify close btn in share group slider");
				return result;
			}
		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifyShareSliderMenu_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnCopyToClipBoardBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getOptionCopyToClipboard(), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify Copy to clipboard option");
				return result;
			}

			cfObj.commonClick(communityPageObj.getOptionCopyToClipboard());
			Thread.sleep(1000);
			result = !cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getOptionCopyToClipboard(), 10);
			if (!result) {
				communityPageMsgList.add("CopyToClipBoard button is not clickable.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("clickOnCopyToClipBoardBtn_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean shareGroup(AppiumDriver<MobileElement> driver, JsonObject socialDataJsonObject) {
		boolean result = true;
		// String strGroupName = "A Default Group";
		try {

			// MobileElement e = cfObj.commonGetElement(driver,
			// "//*[contains(@content-desc,'" + socialDataJsonObject.get(strGroupName) +
			// "')]", "xpath");
			// e.click();

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.ImageView[contains(@content-desc,'Automation')]", "xpath", 10);
			if (!result) {
				communityPageMsgList.add("Automation Group is not available.");
				return result;
			}

			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
				cfObj.scrollIntoDesc(driver, "Automation");
//				cfObj.scrollUtill(driver, 1, direction.DOWN);
			}

			cfObj.commonClick(driver, "//android.widget.ImageView[contains(@content-desc,'Automation')]", "xpath");

			postingPageUtilObj = new PostingPageUtil(driver);

			result = postingPageUtilObj.verifyPostSharingPage(driver);
			if (!result) {
				communityPageMsgList.add("Unable to select group in share via slider");
				return result;
			}

			cfObj.commonClick(postingPageUtilObj.postingPageObj.getSubmitBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver,
					postingPageUtilObj.postingPageObj.getTitleSelectTopic(), 10);
			if (result) {
				cfObj.commonClick(postingPageUtilObj.postingPageObj.getOptionTopics().get(0));
			}

			cfObj.commonClick(postingPageUtilObj.postingPageObj.getPostBtn());

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("shareGroup_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyShareGroupFromShareIcon(AppiumDriver<MobileElement> driver, JsonObject socialJsonObject) {
		boolean result = true;
		try {

			result = cfObj.pressAndroidKey(driver, key.BACK, 1);
			if (!result) {
				communityPageMsgList.add("Unable to press back key android");
				return result;
			}

			Thread.sleep(1500);

			result = clickYourGroupTab(driver);
			if (!result) {
				communityPageMsgList.add("Unable to open Your group tab");
				return result;
			}

			result = clickOnShareIcon(driver);
			if (!result) {
				communityPageMsgList.add("Unable to click on share icon");
				return result;
			}

			result = shareGroup(driver, socialJsonObject);
			if (!result) {
				communityPageMsgList.add("Unable to share group From ShareIcon");
				return result;
			}
		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifyShareGroupFromShareIcon_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnShareIcon(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.commonClick(communityPageObj.getIconShareGroup().get(0));

			result = verifyShareGroupSlider(driver);
			if (!result) {
				communityPageMsgList.add("Failed to verify ShareGroup slider.");
			}

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("clickOnShareIcon_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyShareGroupFromShareButton(AppiumDriver<MobileElement> driver, JsonObject socialJsonObject) {
		boolean result = true;
		try {
			result = cfObj.pressAndroidKey(driver, key.BACK, 1);
			if (!result) {
				communityPageMsgList.add("Unable to press back key android");
				return result;
			}

			Thread.sleep(500);

			result = clickExploreGroupTab(driver);
			if (!result) {
				communityPageMsgList.add("Unable to open Your group tab");
				return result;
			}

			Thread.sleep(500);

			result = clickOnShareButton(driver);
			if (!result) {
				communityPageMsgList.add("Unable to click on share button");
				return result;
			}

			result = shareGroup(driver, socialJsonObject);
			if (!result) {
				communityPageMsgList.add("Unable to share group From ShareButton.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifyShareGroupFromShareButton_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnShareButton(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getBtnShareGroup().get(0), 10);
			if(!result){
				communityPageMsgList.add("The share btn on group is not visible");
				return result;
			}

			cfObj.commonClick(communityPageObj.getBtnShareGroup().get(0));

			result = verifyShareGroupSlider(driver);
			if (!result) {
				communityPageMsgList.add("Failed to verify ShareGroup slider.");
			}

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("clickOnShareButton_Exception: " + e.getMessage());
		}
		return result;
	}

	public enum PostType {
		PICTURE_ATTACHMENT_POST, MCQ;
	}

	public boolean verifyLikeCommentShareUsingPostInProfile(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			loginUtilObj = new LoginUtil(driver);
			result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), false);
			if (!result) {
				communityPageMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			homeUtilObj = new HomePageUtil(driver);
			result = homeUtilObj.justOpenNavigationDrawer(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = homeUtilObj.clickOnUserProfileSection(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			userProfilePageUtilObj = new UserProfilePageUtil(driver);
			result = userProfilePageUtilObj.validateNewProfileScreen(driver, ProfileType.OWN);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			String ownName = null;
			int totalLikeCount, totalCommentCount, totalPostCount;
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {

				ownName = userProfilePageUtilObj.userProfilePageObj.getOwnUserName().getAttribute("content-desc");
				if (ownName == null) {
					communityPageMsgList.add("Own name text is null.");
					return false;
				}
				totalLikeCount = Integer.parseInt(
						userProfilePageUtilObj.userProfilePageObj.getTotalLikesCount().getAttribute("content-desc"));
				totalCommentCount = Integer.parseInt(
						userProfilePageUtilObj.userProfilePageObj.getTotalCommentsCount().getAttribute("content-desc"));
				totalPostCount = Integer.parseInt(
						userProfilePageUtilObj.userProfilePageObj.getTotalPostCount().getAttribute("content-desc"));

			} else {
				ownName = userProfilePageUtilObj.userProfilePageObj.getOwnUserName().getAttribute("name");
				if (ownName == null) {
					communityPageMsgList.add("Own name text is null.");
					return false;
				}
				totalLikeCount = Integer
						.parseInt(userProfilePageUtilObj.userProfilePageObj.getTotalLikesCount().getAttribute("name"));
				totalCommentCount = Integer.parseInt(
						userProfilePageUtilObj.userProfilePageObj.getTotalCommentsCount().getAttribute("name"));
				totalPostCount = Integer
						.parseInt(userProfilePageUtilObj.userProfilePageObj.getTotalPostCount().getAttribute("name"));

			}
			System.out.println("Like:-->" + totalLikeCount + "---Post:--->" + totalPostCount + "-----Comment:-->"
					+ totalCommentCount);
			cfObj.commonClick(userProfilePageUtilObj.userProfilePageObj.getNewUserProfileBackBtn());

			HomePageUtil homePageUtilObj = new HomePageUtil(driver);
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageUtilObj.homePageObj.getBtnHome(), 10);
			if (!result) {
				communityPageMsgList.add("Unable to verify Home btn");
				return result;
			}

			result = homeUtilObj.clickCommunityBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = addNewAttachmentPost(driver);
			if (!result) {
				communityPageMsgList.add("Unable to add NewAttachmentPost");
				return result;
			}

			Thread.sleep(5000);	// Take time for post creation

			result = clickOnLikeCommentBtn(driver);
			if (!result) {
				communityPageMsgList.add("Unable to click LikeCommentBtn.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getBtnSave().get(0), 10);
			if (!result) {
				communityPageMsgList.add("BookMark button is not visible..");
				return result;
			}

			cfObj.commonClick(communityPageObj.getBtnSave().get(0));

			result = clickOnOwnProfileFromCommentSection(driver, "Hii");
			if (!result) {
				communityPageMsgList.add("Not able to click OwnProfile FromCommentSection");
				return result;
			}

			result = userProfilePageUtilObj.validateOwnFollowFollowingTabScreen(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}
		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifyLikeCommentShareUsingPostInProfile_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnOwnProfileFromCommentSection(AppiumDriver<MobileElement> driver, String strPostComment) {
		boolean result = true;
		try {
			result = clickOnCommentBtn(driver);
			if (!result) {
				communityPageMsgList.add("Unable to click Comment btn.");
				return result;
			}
			Thread.sleep(4000);
			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getTextFieldComment(), 30);
			if (!result) {
				communityPageMsgList.add("Comment text field is not visible.");
				return result;
			}

			communityPageObj.getTextFieldComment().click();
			Thread.sleep(1000);
			communityPageObj.getTextFieldComment().sendKeys(strPostComment);

			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = cfObj.commonVerifyValueTextBox(communityPageObj.getTextFieldComment(), strPostComment);
			} else {
				result = cfObj.commonVerifyValueTextBox(communityPageObj.getTextFieldComment(), strPostComment,
						"value");
			}
			if (!result) {
				communityPageMsgList.add("Entered comment is not matching.");
				return result;
			}

			result = clickOnSendCommentBtn(driver, strPostComment);
			if (!result) {
				communityPageMsgList.add("Unable to send comment");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getOwnProfilePic().get(0), 10);
			if (!result) {
				communityPageMsgList.add("OwnProfilePic is not visible.");
				return result;
			}
			cfObj.commonClick(communityPageObj.getOwnProfilePic().get(0));

			result = !cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getTextFieldComment(), 10);
			if (!result) {
				communityPageMsgList.add("OwnProfilePic is not clickable.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("clickOnOwnProfileFromCommentSection_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnOtherUserProfile(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getPostCreatorName().get(0), 10);
			if (!result) {
				communityPageMsgList.add("PostCreatorName is not visible.");
				return result;
			}
			for (int i = 0; i < communityPageObj.getPostCreatorName().size(); i++) {
				System.out.println(communityPageObj.getPostCreatorName().get(i).getAttribute("content-desc"));
				if (!(communityPageObj.getPostCreatorName().get(i).getAttribute("content-desc")
						.equalsIgnoreCase("hamza six"))) {
					cfObj.commonClick(communityPageObj.getPostCreatorName().get(i));
					break;
				}
			}

			result = !cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getTabAllPost(), 10);
			if (!result) {
				communityPageMsgList.add("OtherUserProfilePic is not clickable.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("clickOnOtherUserProfile_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyEditPersonalDetailsTab(AppiumDriver<MobileElement> driver, String accountHolderName,
			String detailsText) {
		boolean result = true;
		String newPwd = "12345678";
		try {

			result = verifyPushAction(driver, "./Adda247.jpg", "/sdcard/Adda247.jpg");
			if (!result) {
				communityPageMsgList.add("Not able to verify Push action.");
				return result;
			}

			loginUtilObj = new LoginUtil(driver);
			result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), false);
			if (!result) {
				communityPageMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			String newUserMail = loginUtilObj.getStrEmailId();
			if (newUserMail == null) {
				communityPageMsgList.add("New user mail is null.");
				return false;
			}

			homeUtilObj = new HomePageUtil(driver);
			userProfilePageUtilObj = new UserProfilePageUtil(driver);
			editProfileUtilObj = new EditProfileUtil(driver);

			result = homeUtilObj.navigateToUserProfilePage(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = userProfilePageUtilObj.clickOnBookMarkBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.validateBookMarkTabEmptyState(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			cfObj.commonClick(userProfilePageUtilObj.userProfilePageObj.getBookMarkPageBackBtn());

			result = userProfilePageUtilObj.validateOwnProfileScreenDotIcon(driver, "NewUser");
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.clickOnProfileEditBtnNew(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}
			editProfileUtilObj = new EditProfileUtil(driver);
			result = editProfileUtilObj.validatePersonalInformationPage(driver);
			if (!result) {
				communityPageMsgList.addAll(editProfileUtilObj.editProfileMsgList);
				return result;
			}

			if(ConfigFileReader.strRunMode.equalsIgnoreCase("local")){
				if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
					result = editProfileUtilObj.setProfilePicThroughGalleryTab(driver);
					if (!result) {
						communityPageMsgList.addAll(editProfileUtilObj.editProfileMsgList);
						return result;
					}
				}
			}

			// Not working as phone number or email not visible when signup in profile - BUG
			result = editProfileUtilObj.updatePasswordForNewUser(driver, newPwd);
			if (!result) {
				communityPageMsgList.addAll(editProfileUtilObj.editProfileMsgList);
				return result;
			}

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@text='LOGIN']",
					"xpath", 10)) {
				cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.Button[@text='LOGIN']", "xpath"));
			}

			result = loginUtilObj.verifyLoginUsingEmailId(driver, newUserMail, newPwd, false);
			if (!result) {
				communityPageMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			result = homeUtilObj.navigateToUserProfilePage(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = userProfilePageUtilObj.validateNewProfileScreen(driver, ProfileType.OWN);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.clickOnProfileEditBtnNew(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			String updatedUserMail = "addaAutomation" + Common_Function.randomPhoneNumber(10, "2") + "@gmail.com";
			result = editProfileUtilObj.updateEmailId(driver, updatedUserMail);
			if (!result) {
				communityPageMsgList.addAll(editProfileUtilObj.editProfileMsgList);
				return result;
			}

			String newMobileNo = Common_Function.randomPhoneNumber(10, "8");
			result = editProfileUtilObj.updateMobileNumber(driver, newMobileNo);
			if (!result) {
				communityPageMsgList.addAll(editProfileUtilObj.editProfileMsgList);
				return result;
			}

			result = editProfileUtilObj.enterAccountHolderName(driver, accountHolderName);
			if (!result) {
				communityPageMsgList.addAll(editProfileUtilObj.editProfileMsgList);
				return result;
			}

			cfObj.scrollUtill(driver, 1, direction.DOWN);

			result = editProfileUtilObj.setAccountHolderDOBNew(driver);
			if (!result) {
				communityPageMsgList.addAll(editProfileUtilObj.editProfileMsgList);
				return result;
			}

			// result=editProfileUtilObj.setAccountHolderGender(driver);
			// if(!result) {
			// communityPageMsgList.addAll(editProfileUtilObj.editProfileMsgList);
			// return result;
			// }

			result = editProfileUtilObj.setAccountHolderCategoryNew(driver);
			if (!result) {
				communityPageMsgList.addAll(editProfileUtilObj.editProfileMsgList);
				return result;
			}

			result = editProfileUtilObj.enterTextInAboutField(driver, detailsText);
			if (!result) {
				communityPageMsgList.addAll(editProfileUtilObj.editProfileMsgList);
				return result;
			}

			result = editProfileUtilObj.clickOnSaveButtonNew(driver);
			if (!result) {
				communityPageMsgList.addAll(editProfileUtilObj.editProfileMsgList);
				return result;
			}

			Thread.sleep(2000);

			result = userProfilePageUtilObj.clickOnProfileDotIcon(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.clickOnLogOutTab(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = userProfilePageUtilObj.verifyConfirmLogoutPopup(driver);
				if (!result) {
					communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
					return result;
				}

				result = userProfilePageUtilObj.clickLogoutBtnPopup(driver);
				if (!result) {
					communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
					return result;
				}
			} else {
				result = loginUtilObj.verifySuccessfulLogout(driver);
				if (!result) {
					communityPageMsgList.addAll(loginUtilObj.loginMsgList);
					return result;
				}
			}

			result = loginUtilObj.verifyLoginUsingEmailId(driver, updatedUserMail, newPwd, false);
			if (!result) {
				communityPageMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			result = homeUtilObj.verifyHomePage(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = homeUtilObj.navigateToUserProfilePage(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = userProfilePageUtilObj.validateChangedUserNameAndDetails(driver, accountHolderName, detailsText);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifyEditPersonalDetailsTab_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyPushAction(AppiumDriver<MobileElement> driver, String sourcePath, String remotePath) {
		boolean result = true;
		try {
			File file = new File(sourcePath);
			String absoluteSourcePath = file.getAbsolutePath();
			if (absoluteSourcePath == null) {
				communityPageMsgList.add("Absolute sourse path is null.");
				return false;
			}
			System.out.println(absoluteSourcePath);
			result = cfObj.pushFileToEmulator(driver, absoluteSourcePath, remotePath);
			if (!result) {
				communityPageMsgList.add("Not able to push the file to emulator.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifyPushAction_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyLikeCommentBookMarkAndAllActivityBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String productTitle = null;
		try {

			loginUtilObj = new LoginUtil(driver);
			result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), true);
			if (!result) {
				communityPageMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			homeUtilObj = new HomePageUtil(driver);
			storePageUtilObj = new StorePageUtil(driver);
			purchasePackageUtilObj = new PurchasePackageUtil(driver);

			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = homeUtilObj.clickStoreBtn(driver);
				if (!result) {
					communityPageMsgList.addAll(homeUtilObj.homeMsgList);
					return result;
				}

				result = storePageUtilObj.clickEBooksIcon(driver);
				if (!result) {
					communityPageMsgList.addAll(storePageUtilObj.storePageMsgList);
					return result;
				}
				eBooksPageUtilObj = new EBooksPageUtil(driver);
				result = eBooksPageUtilObj.clickEBookRandomlyFromList(driver);
				if (!result) {
					communityPageMsgList.addAll(eBooksPageUtilObj.eBookMsgList);
					return result;
				}

				result = purchasePackageUtilObj.verifyUnpurchasedPackagePage(driver);
				if (!result) {
					communityPageMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
					return result;
				}

				result = purchasePackageUtilObj.applyCouponCode(driver, configFileReaderObj.getCoupon());
				if (!result) {
					communityPageMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
					return result;
				}

				result = purchasePackageUtilObj.clickBuyNowBtn(driver);
				if (!result) {
					communityPageMsgList.add("Unable to click buy now btn");
					return result;
				}

				userDetailsLayerUtilObj = new UserDetailsLayerUtil(driver);
				if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"User Details\"]",
						"xpath", 10)) {

					result = userDetailsLayerUtilObj.fillUserDetailsForm(driver);
					if (!result) {
						communityPageMsgList.addAll(userDetailsLayerUtilObj.userDetailsLayerMsgList);
						return result;
					}

					result = userDetailsLayerUtilObj.clickContinueBtn(driver);
					if (!result) {
						communityPageMsgList.addAll(userDetailsLayerUtilObj.userDetailsLayerMsgList);
						return result;
					}
				}

				feedbackFormUtilObj = new FeedbackFormUtil(driver);

				result = feedbackFormUtilObj.skipFeedbackForm(driver);
				if (!result) {
					communityPageMsgList.addAll(feedbackFormUtilObj.feedbackFormMsgList);
					communityPageMsgList.add("Unable to skip feedback form");
				}

				orderSuccessUtilObj = new OrderSuccessUtil(driver);
				result = orderSuccessUtilObj.verifyOrderSuccessPage(driver);
				if (!result) {
					communityPageMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
					return result;
				}
				productTitle = orderSuccessUtilObj.getStartLearningTitle(driver);
				if (productTitle == null) {
					communityPageMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
					return false;
				}
				System.out.println("Purchased Package: -> " + productTitle);

				result = cfObj.pressAndroidKey(driver, key.BACK, 3);
				if (!result) {
					communityPageMsgList.add("Unable to press android back key 3 times");
					return result;
				}

				result = homeUtilObj.clickMyContentButton(driver);
				if (!result) {
					communityPageMsgList.addAll(homeUtilObj.homeMsgList);
					return result;
				}
			}
			result = homeUtilObj.clickCommunityBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = addNewAttachmentPost(driver);
			if (!result) {
				communityPageMsgList.add("Unable to add NewAttachmentPost");
				return result;
			}

			Thread.sleep(6000);// Take time for post creation
			result = clickOnLikeCommentBtn(driver);
			if (!result) {
				communityPageMsgList.add("Unable to click LikeCommentBtn.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getBtnSave().get(0), 10);
			if (!result) {
				communityPageMsgList.add("BookMark button is not visible..");
				return result;
			}

			cfObj.commonClick(communityPageObj.getBtnSave().get(0));
			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")
					&& ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {

				result = homeUtilObj.addedMaterialInBookMarkTab(driver);
				if (!result) {
					communityPageMsgList.addAll(homeUtilObj.homeMsgList);
					return result;
				}
			}
			result = clickOnOwnProfileFromCommentSection(driver, "Hii");
			if (!result) {
				communityPageMsgList.add("Not able to click OwnProfile FromCommentSection");
				return result;
			}

			userProfilePageUtilObj = new UserProfilePageUtil(driver);

			result = userProfilePageUtilObj.validateLikeCommentPostCount(driver, "1", "1", "1");
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				// return result;
			}

			result = userProfilePageUtilObj.clickOnAllActivityBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}
			Thread.sleep(6000);// PageLoading issue
			result = userProfilePageUtilObj.validateActivityPageUI(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.validatePostInActivityTab(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			cfObj.commonClick(userProfilePageUtilObj.userProfilePageObj.getActivityPageBackBtn());

			result = userProfilePageUtilObj.clickOnBookMarkBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.validateBookMarkPageUI(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.validateMaterialInBookMarkPost(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}
			cfObj.commonClick(userProfilePageUtilObj.userProfilePageObj.getBookMarkPageBackBtn());

			result = userProfilePageUtilObj.clickOnMyPurchaseTab(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = homeUtilObj.clickMyContentButton(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}
			myContentUtil = new MyContentUtil(driver);
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = myContentUtil.validatePurchasedPackageDescription(driver, productTitle);
				if (!result) {
					communityPageMsgList.addAll(myContentUtil.myContentMsgList);
					return result;
				}
			}

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifyPushAction_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyFacultyUserProfileScreen(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			loginUtilObj = new LoginUtil(driver);
			result = loginUtilObj.verifyLoginUsingEmailId(driver, "sarika.tyagi0802@gmail.com", "amitpundir", true);
			if (!result) {
				communityPageMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			homeUtilObj = new HomePageUtil(driver);
			userProfilePageUtilObj = new UserProfilePageUtil(driver);

			result = homeUtilObj.JustOpenAndClickHomeBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = homeUtilObj.justOpenNavigationDrawer(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = homeUtilObj.clickOnUserProfileSection(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			Thread.sleep(2000);

			result = userProfilePageUtilObj.validateFacultyProfileScreen(driver, ProfileType.OWN);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}
		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifyFacultyUserProfileScreen_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean enterReplyCommentOnAdminFacultysComment(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getReplyCommentBtn().get(0), 10);
			if (!result) {
				communityPageMsgList.add("ReplyCommentBtn is not visible.");
				return result;
			}

			cfObj.commonClick(communityPageObj.getReplyCommentBtn().get(0));
			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getRePlyPageTitle(), 10);
			if (!result) {
				communityPageMsgList.add("RePlyPageTitle is not visible.");
				return result;
			}

			result = cfObj.commonSetTextTextBox(communityPageObj.getReplyCommentTextField(), "ReplyComment");
			if (!result) {
				communityPageMsgList.add("Not able to enter reply comment.");
				return result;
			}

			cfObj.commonClick(communityPageObj.getReplyCommentPostBtn());

			result = !cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getRePlyPageTitle(), 10);
			if (!result) {
				communityPageMsgList.add("Reply Comment post is not clickable.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("enterReplyCommentOnAdminFacultysComment_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyMCQPostInAllActivityTab(AppiumDriver<MobileElement> driver, int mcqOptionCount) {
		boolean result = true;
		try {

			loginUtilObj = new LoginUtil(driver);
			result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), true);
			if (!result) {
				communityPageMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			homeUtilObj = new HomePageUtil(driver);

			result = homeUtilObj.clickCommunityBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = addNewMcqPost(driver, mcqOptionCount, 1);
			if (!result) {
				communityPageMsgList.add("Not able to create MCQ post.");
				return result;
			}

			Thread.sleep(4000);

			result = homeUtilObj.openNavigationDrawer(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = homeUtilObj.clickOnUserProfileSection(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			userProfilePageUtilObj = new UserProfilePageUtil(driver);
			result = userProfilePageUtilObj.clickOnAllActivityBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.validateMCQPostInActivityTab(driver, mcqOptionCount, "PostComment",
					"EditComment", "SubComment");
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			cfObj.commonClick(userProfilePageUtilObj.userProfilePageObj.getActivityPageBackBtn());

			cfObj.commonClick(userProfilePageUtilObj.userProfilePageObj.getNewUserProfileBackBtn());

			result = homeUtilObj.clickCommunityBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = homeUtilObj.justOpenNavigationDrawer(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = homeUtilObj.clickOnUserProfileSection(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = userProfilePageUtilObj.validateLikeCommentPostCount(driver, "0", "0", "0");
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return true;
			}

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifyMCQPostInAllActivityTab_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyMCQPostInBookMarkPostsTab(AppiumDriver<MobileElement> driver, int mcqOptionCount) {
		boolean result = true;
		try {

			loginUtilObj = new LoginUtil(driver);
			result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), true);
			if (!result) {
				communityPageMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			homeUtilObj = new HomePageUtil(driver);
			userProfilePageUtilObj = new UserProfilePageUtil(driver);
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {

				result = homeUtilObj.clickCommunityBtn(driver);
				if (!result) {
					communityPageMsgList.addAll(homeUtilObj.homeMsgList);
					return result;
				}
				result = addNewMcqPost(driver, mcqOptionCount, 1);
				if (!result) {
					communityPageMsgList.add("Not able to create MCQ post.");
					return result;
				}
				Thread.sleep(6000);

				result = homeUtilObj.openNavigationDrawer(driver);
				if (!result) {
					communityPageMsgList.addAll(homeUtilObj.homeMsgList);
					return result;
				}

				result = homeUtilObj.clickOnUserProfileSection(driver);
				if (!result) {
					communityPageMsgList.addAll(homeUtilObj.homeMsgList);
					return result;
				}
				result = userProfilePageUtilObj.clickOnAllActivityBtn(driver);
				if (!result) {
					communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
					return result;
				}

				result = userProfilePageUtilObj.validateLikeShareBookMarkDeleteBtnInPostCommentPage(driver,
						"QueryComment", mcqOptionCount);
				if (!result) {
					communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
					return result;
				}

				cfObj.commonClick(userProfilePageUtilObj.userProfilePageObj.getActivityPageBackBtn());

				cfObj.commonClick(userProfilePageUtilObj.userProfilePageObj.getNewUserProfileBackBtn());
			}
			result = homeUtilObj.clickCommunityBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = addNewMcqPost(driver, mcqOptionCount, 1);
			if (!result) {
				communityPageMsgList.add("Not able to create MCQ post.");
				return result;
			}

			Thread.sleep(6000);
			result = homeUtilObj.openNavigationDrawer(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = homeUtilObj.clickOnUserProfileSection(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = userProfilePageUtilObj.clickOnAllActivityBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.clickOnPostBookMarkBtn(driver, true);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.validatePostShareBtn(driver, "QueryComment");
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			cfObj.commonClick(userProfilePageUtilObj.userProfilePageObj.getActivityPageBackBtn());
			result = userProfilePageUtilObj.clickOnBookMarkBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.validateMCQPostInBookMarkPostsTab(driver, mcqOptionCount, "PostComment",
					"EditComment", "SubComment");
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifyMCQPostInAllActivityTab_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyProfile_FollowFunctionality(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			loginUtilObj = new LoginUtil(driver);
			result = loginUtilObj.verifyLoginUsingEmailId(driver, "addaAutomation2955627365@gmail.com", "12345678", false);
			if (!result) {
				communityPageMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			homeUtilObj = new HomePageUtil(driver);
			result = homeUtilObj.justOpenNavigationDrawer(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = homeUtilObj.clickOnUserProfileSection(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			userProfilePageUtilObj = new UserProfilePageUtil(driver);
			result = userProfilePageUtilObj.validateNewProfileScreen(driver, ProfileType.OWN);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			String ownName;
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				ownName = userProfilePageUtilObj.userProfilePageObj.getOwnUserName().getAttribute("content-desc");
			} else {
				ownName = userProfilePageUtilObj.userProfilePageObj.getOwnUserName().getAttribute("name");
			}
			if (ownName == null) {
				communityPageMsgList.add("OwnName text is null.");
				return false;
			}

			System.out.println("Name:-----" + ownName);

			result = userProfilePageUtilObj.clickOnFollowingTab(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}
			Thread.sleep(3000);

			result = userProfilePageUtilObj.clickOnFacultProfileUserName(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.validateFacultyProfileScreen(driver, ProfileType.OTHER);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.validateOthersFollowFollowingTabScreen(driver, ownName);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.clickOnFollowFollowingScreenBackBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			Thread.sleep(2000);

			result = userProfilePageUtilObj.validateMyCourseTab(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.clickOnAllActivityBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.validateActivityPageUI(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			cfObj.commonClick(userProfilePageUtilObj.userProfilePageObj.getActivityPageBackBtn());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Faculty\"]", "xpath", 5);
			if (!result) {

				result = userProfilePageUtilObj.clickOnBookMarkBtn(driver);
				if (!result) {
					communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
					return result;
				}

				result = userProfilePageUtilObj.validateBookMarkPageUI(driver);
				if (!result) {
					communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
					return result;
				}

				cfObj.commonClick(userProfilePageUtilObj.userProfilePageObj.getBookMarkPageBackBtn());

				Thread.sleep(2000);
			}

			result = userProfilePageUtilObj.validateOtherUserScreenDotIcon(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.clickOnOtherUserProfileInFollowFollowingScreen(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.validateNewProfileScreen(driver, ProfileType.OTHER);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.validateOthersFollowFollowingTabScreen(driver, ownName);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.clickOnFollowFollowingScreenBackBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.clickOnAllActivityBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.validateActivityPageUI(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			cfObj.commonClick(userProfilePageUtilObj.userProfilePageObj.getActivityPageBackBtn());
/*
			Thread.sleep(2000);

			result = userProfilePageUtilObj.validateFollowFollowingBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.validateFollowerInFacultyFollowerListing(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.validateOtherUserScreenDotIcon(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}
 */
		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifyProfile_FollowFunctionality_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyUserScreenFromHomePage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			loginUtilObj = new LoginUtil(driver);
			result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), true);
			if (!result) {
				communityPageMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			homeUtilObj = new HomePageUtil(driver);

			result = homeUtilObj.clickHomeBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = homeUtilObj.clickOnPostCommentBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			commentPageUtilObj = new CommentPageUtil(driver);
			result = commentPageUtilObj.validateCommentComposeTextField(driver);
			if (!result) {
				communityPageMsgList.addAll(commentPageUtilObj.commentPageMsgList);
				return result;
			}

			result = commentPageUtilObj.clickOnUserName(driver);
			if (!result) {
				communityPageMsgList.addAll(commentPageUtilObj.commentPageMsgList);
				return result;
			}

			Thread.sleep(5000);
			userProfilePageUtilObj = new UserProfilePageUtil(driver);
			result = userProfilePageUtilObj.validateNewProfileScreen(driver, ProfileType.OWN);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.validateLikeCommentPostCount(driver, "0", "1", "0");
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				// return result;
			}

			result = userProfilePageUtilObj.clickOnBookMarkBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.validateBookMarkPageUI(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			cfObj.commonClick(userProfilePageUtilObj.userProfilePageObj.getBookMarkPageBackBtn());

			result = userProfilePageUtilObj.clickOnAllActivityBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.validateActivityPageUI(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			cfObj.commonClick(userProfilePageUtilObj.userProfilePageObj.getActivityPageBackBtn());

			result = userProfilePageUtilObj.validateOwnFollowFollowingTabScreen(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			cfObj.commonClick(userProfilePageUtilObj.userProfilePageObj.getFollowFollowingScreenBackBtn());

			result = userProfilePageUtilObj.validateOwnProfileScreenDotIcon(driver, "NewUser");
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.clickOnMyPurchaseTab(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			// myContentUtil=new MyContentUtil(driver);
			//
			// result=myContentUtil.validatePurchasedScreenEmptyState(driver);
			// if(!result) {
			// communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
			// return result;
			// }

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifyUserScreenFromHomePage_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyUserProfileDeepLink(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String amazonLink = "https://www.amazon.in/";
		try {

			loginUtilObj = new LoginUtil(driver);
//			result = loginUtilObj.selectYourCategoryExamLanguage(driver);
//			if (!result) {
//				communityPageMsgList.addAll(loginUtilObj.loginMsgList);
//				return result;
//			}
//			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
//				result = loginUtilObj.login(driver, "addaAutomation2503621275@gmail.com", "12345678");
//			} else {
//				result = loginUtilObj.login(driver, "addaAutomation2498419370@gmail.com", "12345678");
//			}
//			if (!result) {
//				communityPageMsgList.addAll(loginUtilObj.loginMsgList);
//				return result;
//			}

			result = loginUtilObj.verifyLoginUsingEmailId(driver, "addaAutomation2503621275@gmail.com", "12345678", false);
			if (!result) {
				communityPageMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			homeUtilObj = new HomePageUtil(driver);
			userProfilePageUtilObj = new UserProfilePageUtil(driver);
			commentPageUtilObj = new CommentPageUtil(driver);

			result = homeUtilObj.clickCommunityBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = homeUtilObj.clickOnPostCommentBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = commentPageUtilObj.enterOtherDeepLinkAndValidateErrorToast(driver, amazonLink);
			if (!result) {
				communityPageMsgList.addAll(commentPageUtilObj.commentPageMsgList);
				return result;
			}

			result = homeUtilObj.JustOpenAndClickHomeBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = homeUtilObj.justOpenNavigationDrawer(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = homeUtilObj.clickOnUserProfileSection(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}
			Thread.sleep(4000);

			result = userProfilePageUtilObj.clickOnProfileDotIcon(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.validateCopyProfileLinkTab(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			cfObj.commonClick(userProfilePageUtilObj.userProfilePageObj.getNewUserProfileBackBtn());

			result = homeUtilObj.JustOpenAndClickHomeBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = homeUtilObj.clickCommunityBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = addNewMcqPost(driver, 3, 1);
			if (!result) {
				communityPageMsgList.add("Not able to create MCQ post.");
				return result;
			}

			Thread.sleep(3000);

			result = userProfilePageUtilObj.enterUserDeepLinkInCommentTextField(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			// Link not clickable
			result = userProfilePageUtilObj.clickOnDeepLink(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			Thread.sleep(4000);

			result = userProfilePageUtilObj.validateNewProfileScreen(driver, ProfileType.OWN);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = cfObj.pressAndroidKey(driver, key.BACK, 2);
			if (!result) {
				communityPageMsgList.add("Not able to click back button 2 times.");
				return result;
			}

			result = homeUtilObj.clickCommunityBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = clickYourGroupTab(driver);
			if (!result) {
				System.out.println("Not able to click YourGroupTab.");
				return result;
			}

			result = clickOnShareButton(driver);
			if (!result) {
				System.out.println("Not able to click ShareButton.");
				return result;
			}

			result = clickOnCopyToClipBoardBtn(driver);
			if (!result) {
				System.out.println("Not able to click CopyToClipBoard Btn.");
				return result;
			}

			result = homeUtilObj.clickCommunityBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = addNewAttachmentPost(driver);
			if (!result) {
				System.out.println("Not able to add NewAttachementPost.");
				return result;
			}

			Thread.sleep(4000);

			result = userProfilePageUtilObj.enterUserDeepLinkInCommentTextField(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			// Link not clickable
			result = userProfilePageUtilObj.clickOnDeepLink(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = validateGroupUi(driver);
			if (!result) {
				System.out.println("Not able to validate Group UI.");
				return result;
			}

			result = cfObj.pressAndroidKey(driver, key.BACK, 1);
			if (!result) {
				communityPageMsgList.add("Not able to click back button 1 times.");
				return result;
			}

			result = commentPageUtilObj.enterOtherDeepLinkAndValidateCanotShareToast(driver, amazonLink);
			if (!result) {
				communityPageMsgList.addAll(commentPageUtilObj.commentPageMsgList);
				return result;
			}

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifyUserProfileDeepLink_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyAddingPostWithPdfAndPPT(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = verifyPushAction(driver, "./Adda247.jpg", "/sdcard/Adda247.jpg");
			if (!result) {
				communityPageMsgList.add("Not able to verify Push Image.");
				return result;
			}

			result = verifyPushAction(driver, "./SocialDemo.pdf", "/sdcard/SocialDemo.pdf");
			if (!result) {
				communityPageMsgList.add("Not able to verify Push Pdf File.");
				return result;
			}

			result = verifyPushAction(driver, "./SocialDemo.pptx", "/sdcard/SocialDemo.pptx");
			if (!result) {
				communityPageMsgList.add("Not able to verify Push PPT file.");
				return result;
			}

			loginUtilObj = new LoginUtil(driver);

			result = loginUtilObj.verifyLoginUsingEmailId(driver, "sarika.tyagi0802@gmail.com", "12345678", false);
			if (!result) {
				communityPageMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			Thread.sleep(1500);

			homeUtilObj = new HomePageUtil(driver);

			result = homeUtilObj.clickCommunityBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = addNewAttachementPostWithPdfAndPpt(driver);
			if (!result) {
				communityPageMsgList.add("Unable to add NewAttachementPostWithPdfAndPpt");
				return result;
			}

			Thread.sleep(2000);
			result = validateUploadedFiles(driver);
			if (!result) {
				communityPageMsgList.add("Unable to validateUploadedFiles.");
				return result;
			}

		} catch (Exception e) {
			communityPageMsgList.add("verifyAddingPostWithPdfAndPPT_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean addNewAttachementPostWithPdfAndPpt(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String groupName;
		try {

			if (ConfigFileReader.strApplication.equalsIgnoreCase("adda")) {
				groupName = "A B Formal1_01";
			} else {
				groupName = "IITJEE 01";
			}
			socialJsonObject = cfObj.getJsonData("src//main//resources//socialData.json");

			result = clickOnAddPostBtn(driver);
			if (!result) {
				communityPageMsgList.add("Unable to click on add post btn");
				return result;
			}

			Thread.sleep(1500);
			postingPageUtilObj = new PostingPageUtil(driver);
			result = postingPageUtilObj.clickOnAttachmentMCQPostTab(driver, PostType.PICTURE_ATTACHMENT_POST);
			if (!result) {
				communityPageMsgList.add("Unable to select Attachment type post.");
				return result;
			}

			result = selectGroupFromOptions(driver, groupName);
			if (!result) {
				communityPageMsgList.add("Unable to select group from given options");
				return result;
			}

			Thread.sleep(1000);

			result = postingPageUtilObj.enterQueryOrDoubt(driver, socialJsonObject.get("postContent").getAsString());
			if (!result) {
				communityPageMsgList.add("Unable to enter query in posting page");
				return result;
			}

			Thread.sleep(1000);
			if(!ConfigFileReader.strRunMode.equalsIgnoreCase("localLab")){
				driver.hideKeyboard();
			}

			result = postingPageUtilObj.uploadImagePdfPpt(driver);
			if (!result) {
				communityPageMsgList.add("Unable to Upload files.");
				return result;
			}

			result = postingPageUtilObj.clickPostSubmitBtn(driver);
			if (!result) {
				communityPageMsgList.add("Unable to select type of post from slider menu");
				return result;
			}

			result = postingPageUtilObj.selectTopic(driver, socialJsonObject.get("postContent").getAsString());
			if (!result) {
				communityPageMsgList.add("Unable to select topic in Posting page");
				return result;
			}

		} catch (Exception e) {
			communityPageMsgList.add("addNewPost_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean validateUploadedFiles(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@content-desc='fullImagePreview']",
					"xpath", 10);
			if (!result) {
				communityPageMsgList.add("Uploaded image file is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@content-desc='DOWNLOAD'][1]",
					"xpath", 10);
			if (!result) {
				communityPageMsgList.add("Uploaded PPT file is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@content-desc='DOWNLOAD'][2]",
					"xpath", 10);
			if (!result) {
				communityPageMsgList.add("Uploaded PDF file is not visible.");
				return result;
			}

		} catch (Exception e) {
			communityPageMsgList.add("validateUploadedFiles_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyCommunityPageForProdEnv(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			loginUtilObj = new LoginUtil(driver);
			result = loginUtilObj.verifyLoginUsingEmailIdFromConfig(driver);
			if (!result) {
				communityPageMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}
			Thread.sleep(1000);

			homeUtilObj = new HomePageUtil(driver);
			result = homeUtilObj.clickCommunityBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = validateAddPostBtn(driver);
			if (!result) {
				communityPageMsgList.add("Unable to validate validateAddPostBtn");
				return result;
			}

			result = clickExploreGroupTab(driver);
			if (!result) {
				communityPageMsgList.add("Unable to click ExploreGroupTab.");
				return result;
			}

			result = clickYourGroupTab(driver);
			if (!result) {
				communityPageMsgList.add("Unable to click YourGroup Tab.");
				return result;
			}
		} catch (Exception e) {
			communityPageMsgList.add("verifyCommunityPageForProdEnv_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean validateAddPostBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			socialJsonObject = cfObj.getJsonData("src//main//resources//socialData.json");

			result = clickOnAddPostBtn(driver);
			if (!result) {
				communityPageMsgList.add("Unable to click on add post btn");
				return result;
			}

			Thread.sleep(1000);

			postingPageUtilObj = new PostingPageUtil(driver);
			result = postingPageUtilObj.clickOnAttachmentMCQPostTab(driver, PostType.PICTURE_ATTACHMENT_POST);
			if (!result) {
				communityPageMsgList.add("Unable to select type of post from slider menu");
				return result;
			}

			result = selectGroupFromOptions(driver, "General");
			if (!result) {
				communityPageMsgList.add("Unable to select group from given options");
				return result;
			}

			result = postingPageUtilObj.enterQueryOrDoubt(driver, socialJsonObject.get("postContent").getAsString());
			if (!result) {
				communityPageMsgList.add("Unable to select type of post from slider menu");
				return result;
			}

			result = postingPageUtilObj.clickPostSubmitBtn(driver);
			if (!result) {
				communityPageMsgList.add("Unable to select type of post from slider menu");
				return result;
			}
			Thread.sleep(1000);

			result = cfObj.commonWaitForElementToBeVisible(driver,
					postingPageUtilObj.postingPageObj.getTitleSelectTopic(), 10);
			if (!result) {
				communityPageMsgList.add("Submit button is not clickable.");
				return result;
			}

			result = cfObj.pressAndroidKey(driver, key.BACK, 1);
			if (!result) {
				communityPageMsgList.add("Unable to click back button.");
				return result;
			}

			result = postingPageUtilObj.clickOnAttachmentMCQPostTab(driver, PostType.MCQ);
			if (!result) {
				communityPageMsgList.add("Unable to select type of post from slider menu");
				return result;
			}

			result = postingPageUtilObj.enterQueryOrDoubt(driver, socialJsonObject.get("postContent").getAsString());
			if (!result) {
				communityPageMsgList.add("Unable to select type of post from slider menu");
				return result;
			}

			result = postingPageUtilObj.addOptionsInMcq(driver, 4, 1);
			if (!result) {
				communityPageMsgList.add("Unable to add Mcq options");
				return result;
			}

			result = postingPageUtilObj.clickPostSubmitBtn(driver);
			if (!result) {
				communityPageMsgList.add("Unable to select type of post from slider menu");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,
					postingPageUtilObj.postingPageObj.getTitleSelectTopic(), 10);
			if (!result) {
				communityPageMsgList.add("Submit button is not clickable.");
				return result;
			}

			result = cfObj.pressAndroidKey(driver, key.BACK, 2);
			if (!result) {
				communityPageMsgList.add("Unable to click back button.");
				return result;
			}
		} catch (Exception e) {
			communityPageMsgList.add("validateAddPostBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyUserProfileScreenForProdEnv(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			loginUtilObj = new LoginUtil(driver);
			result = loginUtilObj.verifyLoginGuestUser(driver);
			if (!result) {
				communityPageMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			homeUtilObj = new HomePageUtil(driver);
			result = homeUtilObj.openNavigationDrawer(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}
			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
				result = homeUtilObj.verifyNightModeOnOffToggleBtn(driver);
				if (!result) {
					communityPageMsgList.addAll(homeUtilObj.homeMsgList);
					return result;
				}
			}

			result = homeUtilObj.clickOnUserProfileSection(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			userProfilePageUtilObj = new UserProfilePageUtil(driver);
			result = cfObj.waitForPageLoading(driver, 10, 2000,
					userProfilePageUtilObj.userProfilePageObj.getAllActivityTab());
			if (!result) {
				System.out.println("Profile page Loading error.");
			}
			result = userProfilePageUtilObj.validateNewProfileScreen(driver, ProfileType.OWN);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.validateLikeCommentPostCount(driver, "0", "0", "0");
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.validateOwnFollowFollowingTabScreen(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			cfObj.commonClick(userProfilePageUtilObj.userProfilePageObj.getFollowFollowingScreenBackBtn());

			result = userProfilePageUtilObj.clickOnAllActivityBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}
			Thread.sleep(6000);// PageLoading issue
			result = userProfilePageUtilObj.validateActivityPageUI(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.validateEmptyStateInActivityTab(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			cfObj.commonClick(userProfilePageUtilObj.userProfilePageObj.getActivityPageBackBtn());

			result = userProfilePageUtilObj.clickOnBookMarkBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.validateBookMarkPageUI(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.validateBookMarkTabEmptyState(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}
			cfObj.commonClick(userProfilePageUtilObj.userProfilePageObj.getBookMarkPageBackBtn());

			result = userProfilePageUtilObj.clickOnMyPurchaseTab(driver);
			if (!result) {
				communityPageMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = homeUtilObj.clickMyContentButton(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}
			myContentUtil = new MyContentUtil(driver);
			result = myContentUtil.validatePurchasedScreenEmptyState(driver);
			if (!result) {
				communityPageMsgList.addAll(myContentUtil.myContentMsgList);
				return result;
			}

		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifyUserProfileScreenForProdEnv_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean validateUnFollowPublicGroup(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String groupName;
		loginUtilObj = new LoginUtil(driver);
		homeUtilObj = new HomePageUtil(driver);
		try {
			if (ConfigFileReader.strEnv.equalsIgnoreCase("production")) {

				groupName = "Maths By Navneet Sir";

				result = loginUtilObj.verifyLoginUsingEmailIdFromConfig(driver);
				if (!result) {
					communityPageMsgList.addAll(loginUtilObj.loginMsgList);
					return result;
				}
			} else {
				groupName = "A New Group";

				result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "9"), false);
				if (!result) {
					communityPageMsgList.addAll(loginUtilObj.loginMsgList);
					return result;
				}
			}

			result = homeUtilObj.clickCommunityBtn(driver);
			if (!result) {
				communityPageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = clickExploreGroupTab(driver);
			if (!result) {
				communityPageMsgList.add("Unable to click Explore Group tab.");
				return result;
			}

			result = clickOnSpecificGroup(driver, groupName);
			if (!result) {
				communityPageMsgList.add("Unable to click Explore Group tab.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@content-desc='Follow']", "xpath",
					10);
			if (result) {

				result = verifyShareFollowBtnInGroup(driver);
				if (!result) {
					communityPageMsgList.add("Failed to verifyShareFollowBtnInGroup.");
					return result;
				}
			} else {
				communityPageMsgList.add("The btn is following, but it is new user");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, communityPageObj.getBtnClose(), 10);
			if (!result) {
				communityPageMsgList.add("getBtnClose is not visible");
				return result;
			}

			cfObj.commonClick(communityPageObj.getBtnClose());

			result = clickYourGroupTab(driver);
			if (!result) {
				communityPageMsgList.add("Failed to click YourGroup tab.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//*[contains(@content-desc,'" + groupName + "')]", "xpath", 10);
			if (!result) {
				communityPageMsgList.add("Recently Following Group is not visible on YourGroup tab.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("validateUnFollowPublicGroup_Exception: " + e.getMessage());
		}
		return result;
	}

	private boolean clickOnSpecificGroup(AppiumDriver<MobileElement> driver, String groupName) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//*[contains(@content-desc,'" + groupName + "')]", "xpath", 15);
			if (!result) {
				communityPageMsgList.add("Specific group is not visible.");
				return result;
			}
			cfObj.commonClick(driver, "//*[contains(@content-desc,'" + groupName + "')]", "xpath");

			Thread.sleep(1000);

			result = verifyGroupPage(driver, groupName);
			if (!result) {
				communityPageMsgList.add("Failed to verify Group page.");
			}
		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("clickOnSpecificGroup_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyShareFollowBtnInGroup(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.ImageView[@content-desc=\"Share\"]", "xpath", 10);
			if (!result) {
				communityPageMsgList.add("Share btn is not visible in the Group.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.ImageView[@content-desc=\"Follow\"]", "xpath", 10);
			if (!result) {
				communityPageMsgList.add("Follow btn is not visible in the Group.");
				return result;
			}

			cfObj.commonClick(driver, "//android.widget.ImageView[@content-desc='Follow']", "xpath");

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.ImageView[@content-desc='Following']", "xpath", 10);
			if (!result) {

				cfObj.commonClick(driver, "//android.widget.ImageView[@content-desc='Follow']", "xpath");

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.widget.ImageView[@content-desc='Following']", "xpath", 10);
				if (!result) {
					communityPageMsgList.add("User is not able to Follow the Group using Follow btn.");
					return result;
				}
			}
		} catch (Exception e) {
			result = false;
			communityPageMsgList.add("verifyShareFollowBtnInGroup_Exception: " + e.getMessage());
		}
		return result;
	}
}