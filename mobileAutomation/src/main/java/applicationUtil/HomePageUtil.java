package applicationUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.support.PageFactory;

import apiUtill.MockTestApiUtil;
import apiUtill.OrderApiUtil;
import apiUtill.UserApiUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.HomePage_OR;
import pojo.login.Login;
import pojo.userRegistration.UserRegistration;
import util.Common_Function;
import util.Common_Function.direction;
import util.Common_Function.key;
import util.ConfigFileReader;

public class HomePageUtil {

	int questionIndex;

	ConfigFileReader configFileReaderObj;
	CommunityPageUtil communityPageUtilObj;
	RegisterNewUserUtil registrationUserUtilObj;
	LoginUtil loginUtilObj;
	StorePageUtil storeUtilObj;
	CommonTestUtil commonTestUtilObj;
	MyContentUtil myContentUtilObj;
	StudyMaterialTabUtil studyMaterialUtilObj;
	UserProfilePageUtil userProfileUtilObj;
	CommonStudyMaterialSectionUtil commonStudyMaterialSectionUtilObj;
	VideoCoursesPageUtil videoCoursesPageUtilObj;
	CommentPageUtil commentPageUtilObj;
	ChildPackageUtil childPackageUtilObj;
	AccountSettingUtil accountSettingUtilObj;
	MockTestApiUtil mockTestApiUtilObj;
	PostingPageUtil postingPageUtilObj;
	HomePage_OR homePageObj;
	FixedMockTestUtil fixedMockTestUtilObj;
	PurchasePackageUtil purchasePackageUtilObj;
	public List<String> homeMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();

	public HomePageUtil(AppiumDriver<MobileElement> driver) {
		homePageObj = new HomePage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), homePageObj);
	}

	public boolean verifyDownloadStudyMaterials(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		UserRegistration userRegistration;
		String strUserEmail;
		try {

			registrationUserUtilObj = new RegisterNewUserUtil();
			userRegistration = registrationUserUtilObj.reigsterNewUser();
			if (userRegistration == null) {
				homeMsgList.add("Not able to register user using api");
				return false;
			}

			strUserEmail = userRegistration.getUserInfo().getEmail();
			if (strUserEmail == null) {
				homeMsgList.add("User email is null.");
				return false;
			}

			loginUtilObj = new LoginUtil(driver);

			result = loginUtilObj.selectYourCategoryExamLanguage(driver);
			if (!result) {
				homeMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			result = loginUtilObj.verifyLoginUsingEmailId(driver, strUserEmail, "0002@aaada!", false);
			if (!result) {
				homeMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			result = clickStudyMaterialTab(driver);
			if (!result) {
				homeMsgList.add("Unable to open study material tab");
				return result;
			}

			String quizTitle = studyMaterialUtilObj.downloadDailyQuizzes(driver, result, true);
			if (quizTitle == null) {
				homeMsgList.add("Unable to download Daily Quizzes");
				return false;
			}

			result = cfObj.pressAndroidKey(driver, key.BACK, 1);
			if (!result) {
				homeMsgList.add("Unable to press back key");
			}

			result = studyMaterialUtilObj.downloadFreePdf(driver);
			if (!result) {
				homeMsgList.add("Unable to download Magzines");
				return result;
			}

			result = cfObj.pressAndroidKey(driver, key.BACK, 1);

			result = studyMaterialUtilObj.downloadPowerCapsules(driver);
			if (!result) {
				homeMsgList.add("Unable to download Capsules");
				return result;
			}

		} catch (Exception e) {
			homeMsgList.add("verifyDownlaodQuizzes_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyFreeMockTest(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			String mobileNumber = Common_Function.randomPhoneNumber(10, "8");

			loginUtilObj = new LoginUtil(driver);
			result = loginUtilObj.verifySignUp(driver, mobileNumber, false);
			if (!result) {
				homeMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			result = clickDailyQuizFeedTab(driver);
			if (!result) {
				homeMsgList.add("Unable to open daily quiz page from home page");
				return result;
			}

			studyMaterialUtilObj = new StudyMaterialTabUtil(driver);
			ChildPackageUtil packageUtilObj = new ChildPackageUtil(driver);

			questionIndex = packageUtilObj.downloadQuiz(driver, result);

            result = clickAttemptLinkOfRecentlyDownloadedTest(driver);
			if (!result) {
				homeMsgList.add("Unable to open recently downloaded test");
				return result;
			}

			commonTestUtilObj = new CommonTestUtil(driver);
			result = commonTestUtilObj.verifyInstructionPage(driver);
			if (!result) {
				homeMsgList.add("Unable to verify Instruction Page");
				return result;
			}

			result = commonTestUtilObj.clickStartTestBtn(driver);
			if (!result) {
				homeMsgList.add("Unable to click start test btn ");
				return result;
			}

			Thread.sleep(2000); // page loading takes time

			result = cfObj.commonWaitForElementToBeVisible(driver,
					commonTestUtilObj.commonTestPageObj.getQuestionNumber(), 10);
			if (!result) {
				homeMsgList.add("QuestionNumber is not visible.");
				return result;
			}

			String questionNumber = commonTestUtilObj.commonTestPageObj.getQuestionNumber().getText();
			if (questionNumber == null) {
				homeMsgList.add("QuestionNumber text is null.");
				return false;
			}

			result = commonTestUtilObj.clickOnTestPauseBtn(driver);
			if (!result) {
				homeMsgList.add("Not able to click TestPause button.");
				return result;
			}

			childPackageUtilObj = new ChildPackageUtil(driver);
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='RESUME']", "xpath", 30);
			if (result) {
				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='RESUME']", "xpath"));
			} else {
				homeMsgList.add("RESUME is not visible.");
				return result;
			}

			result = commonTestUtilObj.clickOnResumeTestBtn(driver);
			if (!result) {
				homeMsgList.add("Not able to click ResumeTest button.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,
					commonTestUtilObj.commonTestPageObj.getQuestionNumber(), 10);
			if (!result) {
				homeMsgList.add("QuestionNumber is not visible.");
				return result;
			}

			result = cfObj.commonVerifyValueTextBox(commonTestUtilObj.commonTestPageObj.getQuestionNumber(),
					questionNumber);
			if (!result) {
				homeMsgList.add("Resumed QuestionNumber is not matching.");
				return result;
			}
			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
				result = commonTestUtilObj.clickOnFilterBtn(driver);
				if (!result) {
					homeMsgList.addAll(commonTestUtilObj.commonTestMsgList);
					return result;
				}
			}

			result = commonTestUtilObj.clickOnSubmitTestBtn(driver);
			if (!result) {
				homeMsgList.add("Unable to Submit test");
				return result;
			}

			result = testPrimePop(driver);
			if (!result) {
				System.out.println("The test prime popup is not visible after click on submit test btn");
            }

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/solution_btn_solo",
					"id", 30)) {

				result = commonTestUtilObj.validateMockTestResultScreen(driver);
				if (!result) {
					homeMsgList.addAll(commonTestUtilObj.commonTestMsgList);
					return result;
				}

				cfObj.commonClick(cfObj.commonGetElement(driver, "toolbar_title_back_arrow", "id"));
			} else {
				result = commonTestUtilObj.verifyDigiFillerTestResultScreen(driver);
				if (!result) {
					homeMsgList.addAll(commonTestUtilObj.commonTestMsgList);
					return result;
				}
				cfObj.commonClick(cfObj.commonGetElement(driver, "cancel", "id"));
			}

			result = verifyTestCompleted(driver);
			if (!result) {
				homeMsgList.add("Failed to verify Test Completed.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.TextView[@text='RESULT']", "xpath"));

			result = testPrimePop(driver);
			if (!result) {
				System.out.println("The test prime popup is not visible after click on result btn");
            }

			fixedMockTestUtilObj = new FixedMockTestUtil(driver);

			if (cfObj.commonWaitForElementToBeVisible(driver, commonTestUtilObj.commonTestPageObj.getReAttemptBtn(),
					10)) {

				cfObj.commonClick(commonTestUtilObj.commonTestPageObj.getReAttemptBtn());

			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "reattemptBtn", "id", 10);
				if (!result) {
					homeMsgList.add("ReAttempt button is not visible on Result screen.");
					return result;
				}
				cfObj.commonClick(cfObj.commonGetElement(driver, "reattemptBtn", "id"));
			}

			result = testPrimePop(driver);
			if (!result) {
				System.out.println("The test prime popup is not visible after click on reattempt test btn");
				result = true;
			}
		} catch (Exception e) {
			homeMsgList.add("verifyFreeMockTest_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean testPrimePop(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/test_pass_chip", "id",
					5);
			if (!result) {
				result = true;
			} else {

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/close_dialog",
						"id", 5);
				if (!result) {
					System.out.println("Close icon of testpass bottomsheet is not visible.");
					return true;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/all_exams_text",
						"id", 5);
				if (!result) {
					homeMsgList.add("All exams text of testpass bottomsheet is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"com.adda247.app:id/test_pass_exam_icons", "id", 5);
				if (!result) {
					homeMsgList.add("All exams icons text of testpass bottomsheet is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"com.adda247.app:id/access_exams_textview", "id", 5);
				if (!result) {
					homeMsgList.add("access_exams_textview of testpass bottomsheet is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/des_tv1", "id", 5);
				if (!result) {
					homeMsgList.add("Descriptions of testpass bottomsheet is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"com.adda247.app:id/already_purchased_tv", "id", 5);
				if (!result) {
					homeMsgList.add("already_purchased_tv of testpass bottomsheet is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"com.adda247.app:id/attempt_free_test_btn", "id", 5);
				if (!result) {
					homeMsgList.add("attempt_free_test_btn btn of testpass bottomsheet is not visible.");
					return result;
				}

				cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/close_dialog", "id"));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/test_pass_chip",
						"id", 3);
				if (result) {
					homeMsgList.add("After closing testpass bottomsheet, still it is visible");
					return false;
				} else {
					result = true;
				}
			}
		} catch (Exception e) {
			homeMsgList.add("testPrimePop_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyHomePage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnHome(), 10);
			if (!result) {
				homeMsgList.add("Unable to verify Home btn");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnMyContent(), 10);
			if (!result) {
				homeMsgList.add("Unable to verify My content btn");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnStore(), 10);
			if (!result) {
				homeMsgList.add("Unable to verify Store Btn");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnTestPass(), 15);
			if (!result) {
				homeMsgList.add("Unable to verify Test Prime Btn");
            }

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnNavigationDrawer(), 10);
			if (!result) {
				homeMsgList.add("Unable to verify Navigation drawer");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getChangeExamContainer(), 10);
			if (!result) {
				homeMsgList.add("Unable to verify Change Exam container.");
				return result;
			}

			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getTabFeed(), 10);
				if (!result) {
					homeMsgList.add("Unable to verify Feed tab");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getTabStudyMaterial(), 10);
				if (!result) {
					homeMsgList.add("Unable to verify Study Material Tab");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getNotificationIcon(), 10);
				if (!result) {
					homeMsgList.add("Unable to verify Notification icon.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getApplicationLogo(), 10);
				if (!result) {
					homeMsgList.add("Unable to verify ApplicationLogo.");
					return result;
				}
			}
		} catch (Exception e) {
			homeMsgList.add("verifyHomePage_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyLogout(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		LoginUtil loginObj = new LoginUtil(driver);
		try {
			if (ConfigFileReader.strEnv.equalsIgnoreCase("production")) {

				result = loginObj.verifyLoginUsingEmailIdFromConfig(driver);
				if (!result) {
					homeMsgList.addAll(loginObj.loginMsgList);
					return result;
				}
			} else {
				String mobileNumber = Common_Function.randomPhoneNumber(10, "8");

				result = loginObj.verifySignUp(driver, mobileNumber, false);
				if (!result) {
					homeMsgList.addAll(loginObj.loginMsgList);
					return result;
				}
			}

			result = clickHomeBtn(driver);
			if (!result) {
				return result;
			}

			result = logout(driver);
			if (!result) {
				return result;
			}
		} catch (Exception e) {
			homeMsgList.add("verifyLogout Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean logout(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		loginUtilObj = new LoginUtil(driver);
		userProfileUtilObj = new UserProfilePageUtil(driver);
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnNavigationDrawer(), 10);
			if (!result) {
				homeMsgList.add("Unable to verify Navigation drawer");
				return result;
			}
			cfObj.commonClick(homePageObj.getBtnNavigationDrawer());

			result = clickOnUserProfileSection(driver);
			if (!result) {
				homeMsgList.add("Unable to open user profile page");
				return result;
			}
			Thread.sleep(1000);

			result = userProfileUtilObj.clickOnProfileDotIcon(driver);
			if (!result) {
				homeMsgList.addAll(userProfileUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfileUtilObj.clickOnLogOutTab(driver);
			if (!result) {
				homeMsgList.addAll(userProfileUtilObj.userProfileMsgList);
				return result;
			}

			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {

				result = userProfileUtilObj.verifyConfirmLogoutPopup(driver);
				if (!result) {
					homeMsgList.addAll(userProfileUtilObj.userProfileMsgList);
					return result;
				}

				result = userProfileUtilObj.clickLogoutBtnPopup(driver);
				if (!result) {
					homeMsgList.addAll(userProfileUtilObj.userProfileMsgList);
					return result;
				}
			} else {
				result = loginUtilObj.verifySuccessfulLogout(driver);
				if (!result) {
					homeMsgList.addAll(loginUtilObj.loginMsgList);
					return result;
				}
			}

		} catch (Exception e) {
			homeMsgList.add("logout_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickHomeBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnHome(), 10);
			if (!result) {
				homeMsgList.add("getBtnHome is not visible");
				return result;
			}

			cfObj.commonClick(homePageObj.getBtnHome());
			Thread.sleep(500);
			cfObj.commonClick(homePageObj.getBtnHome());
			Thread.sleep(500);
			cfObj.commonClick(homePageObj.getBtnHome());

			if(ConfigFileReader.strEnv.equalsIgnoreCase("production")){
				cfObj.commonClick(homePageObj.getBtnHome());
			}

			result = verifyHomePage(driver);
			if (!result) {
				homeMsgList.add("Failed to verify Home page.");
			}
		} catch (Exception e) {
			homeMsgList.add("clickHomeBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean JustOpenAndClickHomeBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnHome(), 10);
			if (!result) {
				homeMsgList.add("getBtnHome is not visible");
				return result;
			}

			cfObj.commonClick(homePageObj.getBtnHome());
			Thread.sleep(500);
			cfObj.commonClick(homePageObj.getBtnHome());
			Thread.sleep(500);
			cfObj.commonClick(homePageObj.getBtnHome());

			Thread.sleep(2000);

			if (cfObj.commonWaitForElementToBeVisible(driver, homePageObj.freeLiveClassBottomBannerCloseIcon(), 7)) {
				cfObj.commonClick(homePageObj.freeLiveClassBottomBannerCloseIcon());
			}
		} catch (Exception e) {
			homeMsgList.add("JustOpenAndClickHomeBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickStudyMaterialTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getTabStudyMaterial(), 10);
			if (!result){
				homeMsgList.add("getTabStudyMaterial is not visible");
				return result;
			}
			cfObj.commonClick(homePageObj.getTabStudyMaterial());

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.LinearLayout[@content-desc=\"STUDY MATERIAL\"]/android.widget.TextView", "xpath", 5)) {
				cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.LinearLayout[@content-desc=\"STUDY MATERIAL\"]/android.widget.TextView", "xpath"));
			}

			studyMaterialUtilObj = new StudyMaterialTabUtil(driver);
			result = studyMaterialUtilObj.verifyStudyMaterialTab(driver);
			if (!result) {
				homeMsgList.add("Failed to verify StudyMaterial tab.");
			}
		} catch (Exception e) {
			homeMsgList.add("clickStudyMaterialTab Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean openNavigationDrawer(AppiumDriver<MobileElement> driver) {
		boolean result = false;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnNavigationDrawer(), 10);
			if (!result) {
				homeMsgList.add("Unable to verify Navigation drawer");
				return result;
			} else {
				cfObj.commonClick(homePageObj.getBtnNavigationDrawer());
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnNavigationDrawer(), 5);
			if (result) {
				cfObj.commonClick(homePageObj.getBtnNavigationDrawer());
			} else {
				result = true;
			}

			result = verifyNavigationDrawer(driver);
			if (!result) {
				homeMsgList.add("Failed to verify Navigation Drawer.");
			}

		} catch (Exception e) {
			homeMsgList.add("openNavigationDrawer Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean openPYPonHome(AppiumDriver<MobileElement> driver) {
		boolean result = false;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver,homePageObj.getQuizTab(),10);
			if (!result) {
				homeMsgList.add("Unable to verify Quiz Tab");
				return result;
			}

			cfObj.swipeLeftOnElement(homePageObj.getQuizTab(), driver);
			cfObj.swipeLeftOnElement(homePageObj.getQuizTab(), driver);
			cfObj.swipeLeftOnElement(homePageObj.getQuizTab(), driver);
			cfObj.swipeLeftOnElement(homePageObj.getQuizTab(), driver);
			cfObj.swipeLeftOnElement(homePageObj.getJobAlertTab(), driver);
			cfObj.swipeLeftOnElement(homePageObj.getJobAlertTab(), driver);
			cfObj.swipeLeftOnElement(homePageObj.getJobAlertTab(), driver);

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getPypTab(),10);
			if (!result) {

				cfObj.swipeLeftOnElement(homePageObj.getJobAlertTab(), driver);
				cfObj.swipeLeftOnElement(homePageObj.getNotesAndArticleTab(), driver);
				cfObj.swipeLeftOnElement(homePageObj.getNotesAndArticleTab(), driver);

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getPypTab(),10);
				if (!result) {
					homeMsgList.add("Unable to verify PYP Tab");
					return result;
				}
			}

			cfObj.commonClick(homePageObj.getPypTab());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,"//android.view.View[@content-desc=\"Previous Year Papers\"]","xpath",10);
			if (!result) {
				homeMsgList.add("Unable to verify inside PYP heading");
				return result;
			}
		} catch (Exception e) {
			homeMsgList.add("openPYPonHome_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean justOpenNavigationDrawer(AppiumDriver<MobileElement> driver) {
		boolean result = false;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnNavigationDrawer(), 10);
			if (!result) {
				homeMsgList.add("Unable to verify Navigation drawer");
				return result;
			}
			cfObj.commonClick(homePageObj.getBtnNavigationDrawer());

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnNavigationDrawer(), 5);
			if (result) {
				cfObj.commonClick(homePageObj.getBtnNavigationDrawer());
			} else {
				result = true;
			}
		} catch (Exception e) {
			homeMsgList.add("justOpenNavigationDrawer Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyNavigationDrawer(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionUserProfile(), 10);
			if (!result) {
				homeMsgList.add("Unable to verify user profile section in navigation drawer");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.performanceDashboard(), 10);
			if (!result) {
				homeMsgList.add("Unable to verify performance dashboard section in navigation drawer");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/nav_home_language_change", "id", 10);
			if (!result) {
				homeMsgList.add("Change Language btn is not visible in navigation drawer");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionDailyQuizzes(), 10);
			if (!result) {
				homeMsgList.add("Unable to verify daily quizzes section in navigation drawer");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionPowerCapsules(), 5);
			if (!result) {
				homeMsgList.add("Unable to verify power capsules section in navigation drawer");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionNotesAndArticles(), 5);
			if (!result) {
				homeMsgList.add("Unable to verify note and articles section in navigation drawer");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionVideos(), 5);
			if (!result) {
				homeMsgList.add("Unable to verify videos section in navigation drawer");
			}


			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.addaAI(), 5);
			if (!result) {
				System.out.println("addaAI btn in navigation drawer is not visible");

                result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionSettings(), 10);
				if (!result) {
					homeMsgList.add("Unable to verify setting section in navigation drawer");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.widget.TextView[contains(@text,'Rate Us')]", "xpath", 10);
				if (!result) {
					homeMsgList.add("Rate Us btn in navigation drawer is not visible");
					return result;
				}
			}

			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionBuyStudyMaterial(), 5);
				if (!result) {
					homeMsgList.add("Unable to verify study materials section in navigation drawer");
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionCurrentAffairs(), 5);
				if (!result) {
					homeMsgList.add("Unable to verify current affairs section in navigation drawer");
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionHelpAndSupport(), 5);
				if (!result) {
					homeMsgList.add("Unable to verify help and support section in navigation drawer");
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionJobAlerts(), 5);
				if (!result) {
					homeMsgList.add("Unable to verify job alerts section in navigation drawer");
				}

				if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {

					result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionMyContent(), 5);
					if (!result) {
						homeMsgList.add("Unable to verify My content section in navigation drawer");
					}

					result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionNightMode(), 10);
					if (!result) {
						homeMsgList.add("Unable to verify Night Mode section in navigation drawer");
						return result;
					}
				}
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.TextView[contains(@text,'Instant Withdrawal')]", "xpath", 10);
			if (!result) {
				homeMsgList.add("Instant Withdrawal in refer & earn box in navigation drawer is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/refer_view", "id", 10);
			if (!result) {
				homeMsgList.add("Refer & earn text in refer & earn box in navigation drawer is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.TextView[contains(@text,'Get cash by referring friends')]", "xpath", 10);
			if (!result) {
				homeMsgList.add(
						"Get cash by referring friends text in refer & earn box in navigation drawer is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/refer_now", "id", 10);
			if (!result) {
				homeMsgList.add("refer & earn btn in box in navigation drawer is not visible");
				return result;
			}
		} catch (Exception e) {
			result = false;
			homeMsgList.add("verifyNavigationDrawer Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnUserProfileSection(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionUserProfile(), 20);
			if (!result) {
				homeMsgList.add("getSectionUserProfile btn not visible");
				return result;
			}
			cfObj.commonClick(homePageObj.getSectionUserProfile());

			Thread.sleep(1000);

			if(cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionUserProfile(), 10)){
				cfObj.commonClick(homePageObj.getSectionUserProfile());
			}
		} catch (Exception e) {
			System.out.println("clickOnUserProfileSection Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickMyContentButton(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		myContentUtilObj = new MyContentUtil(driver);
		try {
			cfObj.commonClick(homePageObj.getBtnMyContent());
			Thread.sleep(1000);
			cfObj.commonClick(homePageObj.getBtnMyContent()); // remove hint

			result = myContentUtilObj.verifyMyContentPage(driver);
			if (!result) {
				homeMsgList.addAll(myContentUtilObj.myContentMsgList);
			}
		} catch (Exception e) {
			homeMsgList.add("clickMyContentButton Exception:  " + e.getMessage());
			return result;
		}
		return result;
	}

	public boolean clickDailyQuizFeedTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getIconDailyQuiz(), 20);
			if (!result) {
				homeMsgList.add("getIconDailyQuiz is not visible");
				return result;
			}
			cfObj.commonClick(homePageObj.getIconDailyQuiz());

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='Daily Quizzes' and @resource-id='com.adda247.app:id/title']", "xpath", 5)) {
				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='Daily Quizzes' and @resource-id='com.adda247.app:id/title']", "xpath"));
			}

			Thread.sleep(2000);

			if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"), 5)) {
				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"));
			}

			result = verifyDailyQuizFeedTabPage(driver);
			if (!result) {
				homeMsgList.add("Failed to verify DailyQuizFeedTabPage.");
			}
		} catch (Exception e) {
			homeMsgList.add("clickDailyQuizFeedTab Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyDailyQuizFeedTabPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getListQuiz(), 10);
			if (!result) {
				homeMsgList.add("Unable to verify quiz list");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getLinkDownloadQuiz().get(0), 10);
			if (!result) {
				homeMsgList.add("Unable to verify download link");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getIconFilter(), 10);
			if (!result) {
				homeMsgList.add("Unable to verify  filter icon");
				return result;
			}
		} catch (Exception e) {
			homeMsgList.add("verifyDailyQuizFeedTabPage Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickQuizByIndex(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		boolean available = false;
		try {
			result = cfObj.commonWaitForListOfElementsToBeVisible(driver, homePageObj.getLinkDownloadQuiz(), 10);
			if(!result){
				homeMsgList.add("In daily quizzes - get quiz btns are not visible");
				return result;
			}

			int size = homePageObj.getLinkDownloadQuiz().size();

			while (size > 0) {

				size = homePageObj.getLinkDownloadQuiz().size();

				for (questionIndex = 0; questionIndex < size; questionIndex++) {
					available = homePageObj.getLinkDownloadQuiz().get(questionIndex).getText().contains("GET QUIZ");
					if (available) {
						cfObj.commonClick(homePageObj.getLinkDownloadQuiz().get(questionIndex));
						break;
					}
				}
				if (available) {
					break;
				} else {
					cfObj.scrollUtill(driver, 1, direction.DOWN);
				}
			}
			if (!available) {
				homeMsgList.add("Downloaded quiz buttons are not available.");
				return available;
			}

			result = verifyTestDownloaded(driver);
			if (!result) {
				homeMsgList.add("Failed to verify Test Downloaded.");
			}
		} catch (Exception e) {
			homeMsgList.add("clickQuizByIndex Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyTestDownloaded(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			// waiting for test to download
			for (int i = 0; i < 10; i++) {
				result = homePageObj.getLinkDownloadQuiz().get(questionIndex).getText().contains("DOWNLOADING");
				if (result) {
					Thread.sleep(2000);
				} else {
					break;
				}
			}

			Thread.sleep(2000);

			result = homePageObj.getLinkDownloadQuiz().get(questionIndex).getText().contains("ATTEMPT");
			if (!result) {
				homeMsgList.add("ClickAttemptLinkOfRecentlyDownloadedTest is not downloaded");
				result = true;
			}

		} catch (Exception e) {
			homeMsgList.add("verifyTestDownloaded Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickAttemptLinkOfRecentlyDownloadedTest(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			Thread.sleep(2000);

			result = homePageObj.getLinkDownloadQuiz().get(questionIndex).getText().contains("ATTEMPT");
			if (result) {
				cfObj.commonClick(homePageObj.getLinkDownloadQuiz().get(questionIndex));
			} else {
				homeMsgList.add("No test is available to attempt");
				return result;
			}
			Thread.sleep(2000);
		} catch (Exception e) {
			homeMsgList.add("clickAttemptLinkOfRecentlyDownloadedTest Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyTestCompleted(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			Thread.sleep(3000);

			result = homePageObj.getLinkDownloadQuiz().get(questionIndex).getText().contains("RESULT");
			if (!result) {
				homeMsgList.add("Test is not in Completed state");
				return result;
			}

		} catch (Exception e) {
			homeMsgList.add("verifyTestCompleted Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickStoreBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			cfObj.commonClick(homePageObj.getBtnStore());

			cfObj.commonClick(homePageObj.getBtnStore()); // to remove coach mark

			storeUtilObj = new StorePageUtil(driver);
			result = storeUtilObj.verifyStorePage(driver);
			if (!result) {
				homeMsgList.addAll(storeUtilObj.storePageMsgList);
			}
		} catch (Exception e) {
			homeMsgList.add("clickStoreBtn Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickCommunityBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		communityPageUtilObj = new CommunityPageUtil(driver);
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnCommunity(), 10);
			if(!result){
				homeMsgList.add("getBtnCommunity is not visible");
				return result;
			}
			cfObj.commonClick(homePageObj.getBtnCommunity());
			cfObj.commonClick(homePageObj.getBtnCommunity());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'All Post')]", "xpath", 10);
			if(result){
				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[contains(@content-desc,'All Post')]", "xpath"));
			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'All Post')]", "xpath", 5);
				if(result){
					cfObj.commonClick(cfObj.commonGetElement(driver, "//*[contains(@content-desc,'All Post')]", "xpath"));
				}
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'Your Group')]", "xpath", 10);
			if(result){
				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[contains(@content-desc,'Your Group')]", "xpath"));
			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'Your Group')]", "xpath", 5);
				if(result){
					cfObj.commonClick(cfObj.commonGetElement(driver, "//*[contains(@content-desc,'Your Group')]", "xpath"));
				}
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'Explore Group')]", "xpath", 10);
			if(result){
				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[contains(@content-desc,'Explore Group')]", "xpath"));
			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'Explore Group')]", "xpath", 5);
				if(result){
					cfObj.commonClick(cfObj.commonGetElement(driver, "//*[contains(@content-desc,'Explore Group')]", "xpath"));
				}
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'Explore Group')]", "xpath", 6);
			if(result){
				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[contains(@content-desc,'Explore Group')]", "xpath"));
			}
			if(!result){
				homeMsgList.add("Issue in coach mark of community");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnCommunity(), 10);
			if(!result){
				homeMsgList.add("getBtnCommunity is not visible after coach mark removal");
				return result;
			}
			cfObj.commonClick(homePageObj.getBtnCommunity());

			result = communityPageUtilObj.verifyCommunityPage(driver);
			if (!result) {
				homeMsgList.addAll(communityPageUtilObj.communityPageMsgList);
			}
		} catch (Exception e) {
			homeMsgList.add("clickCommunityBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean navigateToUserProfilePage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = justOpenNavigationDrawer(driver);
			if (!result) {
				homeMsgList.add("Not able to navigate side drawer.");
				return result;
			}

			result = clickOnUserProfileSection(driver);
			if (!result) {
				homeMsgList.add("User profile section is not clicked.");
				return result;
			}

			Thread.sleep(1000);

		} catch (Exception e) {
			homeMsgList.add("navigateToUserProfile_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyPushAction(AppiumDriver<MobileElement> driver, String sourcePath, String remotePath) {
		boolean result = true;
		try {
			File file = new File(sourcePath);
			String absoluteSourcePath = file.getAbsolutePath();
			if (absoluteSourcePath == null) {
				homeMsgList.add("Absolute source path is null.");
				return false;
			}
			System.out.println(absoluteSourcePath);
			result = cfObj.pushFileToEmulator(driver, absoluteSourcePath, remotePath);
			if (!result) {
				homeMsgList.add("Not able to push the file to emulator.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			homeMsgList.add("verifyPushAction_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyHomePageUI(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		loginUtilObj = new LoginUtil(driver);
		try {

			if (ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
				result = loginUtilObj.verifyLoginUsingEmailIdFromConfig(driver);
				if (!result) {
					homeMsgList.addAll(loginUtilObj.loginMsgList);
					return result;
				}

				result = clickHomeBtn(driver);
				if (!result) {
					return result;
				}
			} else {
				result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), false);
				if (!result) {
					homeMsgList.addAll(loginUtilObj.loginMsgList);
					return result;
				}
			}

			result = validateHomePageTabs(driver);
			if (!result) {
				return result;
			}

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='Free Live Class']", "xpath", 4)) {
				cfObj.scrollUtill(driver, 1, direction.DOWN);
			}

			result = validateHomePageVideoPostElements(driver);
			if (!result) {
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnHome(), 5);
			if (!result) {
				driver.navigate().back();

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnHome(), 5);
				if (!result) {
					homeMsgList.add("getBtnHome not visible.");
					return result;
				}
			}
			cfObj.commonClick(homePageObj.getBtnHome());

			result = verifySearchIconOnHome(driver);
			if(!result){
				return result;
			}

			result = verifyNotificationIcon(driver);
			if (!result) {
				return result;
			}

			result = verifyChangeLanguageIconFromNav(driver);
			if (!result) {
				return result;
			}

			result = verifyChangeExamContainer(driver);
			if (!result) {
				return result;
			}
		} catch (Exception e) {
			homeMsgList.add("HomePageUI_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	private boolean verifySearchIconOnHome(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.searchIconOnHome(), 10);
			if(!result){
				homeMsgList.add("searchIconOnHome is not visible");
				return result;
			}
			cfObj.commonClick(homePageObj.searchIconOnHome());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"btn_app_bar_back\"]", "xpath", 10);
			if(!result){
				homeMsgList.add("back btn is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.EditText", "xpath", 10);
			if(!result){
				homeMsgList.add("Edit box is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Trending Searches\"]", "xpath", 10);
			if(!result){
				homeMsgList.add("Trending searches text is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Recent Searches\"]", "xpath", 10);
			if(!result){
				homeMsgList.add("Recent searches text is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"You have no recent searches\"]", "xpath", 10);
			if(!result) {
				System.out.println("You have no recent searches might be not visible due to some searches.");
            }
/*
			cfObj.commonGetElement(driver, "//android.widget.EditText", "xpath").sendKeys("Banking");

			cfObj.commonGetElement(driver, "//android.widget.EditText", "xpath").submit();

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"All\"]", "xpath", 10);
			if(!result){
				homeMsgList.add("All tab btn is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Packages\"]", "xpath", 10);
			if(!result){
				homeMsgList.add("Packages tab btn is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Quizzes\"]", "xpath", 10);
			if(!result){
				homeMsgList.add("Quizzes tab btn is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"PYPs\"]", "xpath", 10);
			if(!result){
				homeMsgList.add("PYPs tab btn is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Showing all results for \"]", "xpath", 10);
			if(!result){
				homeMsgList.add("Showing all results for text is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"Packages\n" +
					"View All\"]", "xpath", 10);
			if(!result){
				homeMsgList.add("Packages & view all btn text are not visible");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.View[@content-desc=\"PYPs\"]", "xpath"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"PDFs\"]", "xpath", 10);
			if(!result){
				homeMsgList.add("PDFs tab btn is not visible");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.View[@content-desc=\"PDFs\"]", "xpath"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Job Alerts\"]", "xpath", 10);
			if(!result){
				homeMsgList.add("Job alerts tab btn is not visible");
				return result;
			}

			driver.navigate().back();
 */
			driver.hideKeyboard();

			driver.navigate().back();

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.searchIconOnHome(), 10);
			if(!result){
				homeMsgList.add("searchIconOnHome is not visible");
				return result;
			}
		} catch (Exception e) {
			homeMsgList.add("verifySearchIconOnHome_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean validateSideNavigationDrawerComponent_Part1(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		loginUtilObj = new LoginUtil(driver);
		try {

			if (ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
				result = loginUtilObj.verifyLoginUsingEmailIdFromConfig(driver);
				if (!result) {
					homeMsgList.addAll(loginUtilObj.loginMsgList);
					return result;
				}

				result = clickHomeBtn(driver);
				if (!result) {
					return result;
				}
			} else {
				result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), false);
				if (!result) {
					homeMsgList.addAll(loginUtilObj.loginMsgList);
					return result;
				}
			}

			result = openNavigationDrawer(driver);
			if (!result) {
				homeMsgList.add("Not able to open Navigation drawer.");
				return result;
			}

			result = validateBuyStudyMaterialSection(driver);
			if (!result) {
				homeMsgList.add("Not able to validate BuyStudyMaterial section.");
				return result;
			}

			result = verifyHome_NavigationDrawer(driver);
			if (!result) {
				homeMsgList.add("Not able to open home and navigate side drawer.");
				return result;
			}

			result = verifyNightModeOnOffToggleBtn(driver);
			if (!result) {
				homeMsgList.add("Not able to validate NightModeOnOff toggle.");
				return result;
			}

			result = verifyHome_NavigationDrawer(driver);
			if (!result) {
				homeMsgList.add("Not able to open home and navigate side drawer.");
				return result;
			}

//			result = validateMyContentSection(driver);
//			if (!result) {
//				homeMsgList.add("Not able to validate MyContent section.");
//				return result;
//			}
//
//			result = validateHelpAndSupportSection(driver);
//			if (!result) {
//				homeMsgList.add("Not able to validate HelpAndSupport section.");
//				return result;
//			}
//
//			result = validateOffLineCenterSection(driver);
//			if (!result) {
//				homeMsgList.add("Not able to validate OfflineCenter section.");
//				return result;
//			}
//
//			result = verifyHome_NavigationDrawer(driver);
//			if (!result) {
//				homeMsgList.add("Not able to open home and navigate side drawer.");
//				return result;
//			}

			result = validateReferNowBtn(driver);
			if (!result) {
				homeMsgList.add("Not able to validate ReferNowBtn.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.addaAI(), 5);
			if (!result) {
				System.out.println("Adda AI btn is not visible in navigation drawer");

                result = verifyRateUsSection(driver);
				if (!result) {
					homeMsgList.add("Not able to validate RateUs section.");
					return result;
				}

				result = validateSettingSection(driver);
				if (!result) {
					homeMsgList.add("Not able to validate Setting section.");
					return result;
				}
			}
		} catch (Exception e) {
			result = false;
			homeMsgList.add("validateSideNavigationDrawerComponent_Part1_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyChangeExamContainer(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnHome(), 10);
			if(!result){
				homeMsgList.add("getBtnHome is not visible.");
				return result;
			}
			cfObj.commonClick(homePageObj.getBtnHome());

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getChangeExamContainer(), 10);
			if(!result){
				homeMsgList.add("getChangeExamContainer is not visible.");
				return result;
			}
			cfObj.commonClick(homePageObj.getChangeExamContainer());

			loginUtilObj = new LoginUtil(driver);
			result = loginUtilObj.navigateToChooseExamCategoryScreen(driver);
			if (!result) {
				homeMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			result = loginUtilObj.getSelectExamType(driver);
			if (!result) {
				homeMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

		} catch (Exception e) {
			homeMsgList.add("clickStoreBtn Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnClass11ExamType(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			cfObj.commonClick(homePageObj.getChangeExamContainer());
			loginUtilObj = new LoginUtil(driver);
			result = cfObj.commonWaitForElementToBeVisible(driver, loginUtilObj.loginPageObj.getSelectExamPageTitle(),
					30);
			if (!result) {
				homeMsgList.add("Select exam page title is not visible in class 11 exam type.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, loginUtilObj.loginPageObj.getExamListText().get(0),
					10);
			if (!result) {
				homeMsgList.add("Exam list text is not visible.");
				return result;
			}

			cfObj.commonClick(loginUtilObj.loginPageObj.getExamListText().get(0));

		} catch (Exception e) {
			homeMsgList.add("clickOnClass11ExamType_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyNotificationIcon(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getNotificationIcon(), 10);
			if (!result) {
				homeMsgList.add("Notification icon is not visible");
				return result;
			}

			cfObj.commonClick(homePageObj.getNotificationIcon());

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getNotificationPageTitle(), 10);
			if (!result) {
				homeMsgList.add("Notification page title is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBackBtn(), 10);
			if (!result) {
				homeMsgList.add("Back btn is not visible");
				return result;
			}

			cfObj.commonClick(homePageObj.getBackBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getNotificationIcon(), 10);
			if (!result) {
				homeMsgList.add("Notification icon is not visible");
				return result;
			}
		} catch (Exception e) {
			homeMsgList.add("NotificationIcon_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyChangeLanguageIconFromNav(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = justOpenNavigationDrawer(driver);
			if (!result) {
				homeMsgList.add("Not able to open Navigation drawer.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/nav_home_language_change", "id", 10);
			if (!result) {
				homeMsgList.add("Change Language btn is not visible on nav bar");
				return result;
			} else {
				cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/nav_home_language_change", "id"));

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getChangeLanguagePageTitle(), 30);
				if (!result) {
					homeMsgList.add("Change Language page title is not visible");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'English\n" +
						"Hello')]", "xpath", 10);
				if (!result) {
					homeMsgList.add("English Language btn title is not visible");
					return result;
				}

				cfObj.commonClick(driver, "//*[contains(@content-desc,'English\n" +
						"Hello')]", "xpath");

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getNotificationIcon(), 5);
				if (!result) {
					homeMsgList.add("After clicking on lang, not on notification btn - store page");
					result = true;
				}
			}
		} catch (Exception e) {
			homeMsgList.add("verifyChangeLanguageIconFromNav_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean CurrentAffairsSection(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		loginUtilObj = new LoginUtil(driver);
		try {

			if (ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
				result = loginUtilObj.verifyLoginUsingEmailIdFromConfig(driver);
				if (!result) {
					homeMsgList.addAll(loginUtilObj.loginMsgList);
					return result;
				}
			} else {
				result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), false);
				if (!result) {
					homeMsgList.addAll(loginUtilObj.loginMsgList);
					return result;
				}
			}

			result = openNavigationDrawer(driver);
			if (!result) {
				homeMsgList.add("Not able to open Navigation drawer.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionCurrentAffairs(), 5);
			if (!result) {
				homeMsgList.add("getSectionCurrentAffairs not visible");
				return result;
			} else {

				cfObj.commonClick(homePageObj.getSectionCurrentAffairs());
				commonStudyMaterialSectionUtilObj = new CommonStudyMaterialSectionUtil(driver);

				Thread.sleep(2000);

				if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"), 5)) {
					cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"));
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@text,'Current Affairs')]", "xpath", 5);
				if (!result) {
					driver.navigate().back();
					result = true;
				}

				if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//androidx.recyclerview.widget.RecyclerView[contains(@resource-id,'recyclerView')]/child::android.widget.RelativeLayout/android.widget.TextView",
						"xpath", 10)) {

					result = commonStudyMaterialSectionUtilObj.verifyCurrentAffairPage(driver);
					if (!result) {
						homeMsgList.addAll(commonStudyMaterialSectionUtilObj.commonStudyMaterialMsgList);
						return result;
					}

					result = commonStudyMaterialSectionUtilObj.validateFilterBtn(driver);
					if (!result) {
						homeMsgList.addAll(commonStudyMaterialSectionUtilObj.commonStudyMaterialMsgList);
						return result;
					}

					result = commonStudyMaterialSectionUtilObj.clickOnCurrentAffairStudyMaterialAndValidate(driver);
					if (!result) {
						homeMsgList.addAll(commonStudyMaterialSectionUtilObj.commonStudyMaterialMsgList);
						return result;
					}
				}
			}
		} catch (Exception e) {
			result = false;
			homeMsgList.add("CurrentAffairsSection_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifySideNavigationDrawerComponent_Part2(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		loginUtilObj = new LoginUtil(driver);
		try {

			if (ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
				result = loginUtilObj.verifyLoginUsingEmailIdFromConfig(driver);
				if (!result) {
					homeMsgList.addAll(loginUtilObj.loginMsgList);
					return result;
				}

				result = clickHomeBtn(driver);
				if (!result) {
					return result;
				}
			} else {
				result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), false);
				if (!result) {
					homeMsgList.addAll(loginUtilObj.loginMsgList);
					return result;
				}
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnNavigationDrawer(), 5);
			if (!result) {
				homeMsgList.add("Navigation drawer not visible, might be not on home");
				return result;
			}
			cfObj.commonClick(homePageObj.getBtnNavigationDrawer());

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionJobAlerts(), 5);
			if (!result) {
				homeMsgList.add("getSectionJobAlerts not visible");
			} else {

				cfObj.commonClick(homePageObj.getSectionJobAlerts());
				commonStudyMaterialSectionUtilObj = new CommonStudyMaterialSectionUtil(driver);
				Thread.sleep(3000);

				result = commonStudyMaterialSectionUtilObj.verifyJobAlertPage(driver);
				if (!result) {
					homeMsgList.addAll(commonStudyMaterialSectionUtilObj.commonStudyMaterialMsgList);
					return result;
				}

				driver.navigate().back();

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnHome(), 5);
				if (!result) {
					homeMsgList.add("getBtnHome not visible");
					return result;
				}
				cfObj.commonClick(homePageObj.getBtnHome());

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnNavigationDrawer(), 5);
				if (!result) {
					homeMsgList.add("Navigation drawer not visible, might be not on home");
					return result;
				}
				cfObj.commonClick(homePageObj.getBtnNavigationDrawer());
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionNotesAndArticles(), 5);
				if (!result) {
					homeMsgList.add("getSectionNotesAndArticles not visible");
				} else {

					cfObj.commonClick(homePageObj.getSectionNotesAndArticles());
					commonStudyMaterialSectionUtilObj = new CommonStudyMaterialSectionUtil(driver);
					Thread.sleep(3000);

					result = commonStudyMaterialSectionUtilObj.verifyNotesAndArticlesPage(driver);
					if (!result) {
						homeMsgList.addAll(commonStudyMaterialSectionUtilObj.commonStudyMaterialMsgList);
						return result;
					}

					driver.navigate().back();

					result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnHome(), 5);
					if (!result) {
						homeMsgList.add("getBtnHome not visible");
						return result;
					}
					cfObj.commonClick(homePageObj.getBtnHome());

					result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnNavigationDrawer(), 5);
					if (!result) {
						homeMsgList.add("Navigation drawer not visible, might be not on home");
						return result;
					}
					cfObj.commonClick(homePageObj.getBtnNavigationDrawer());
				}

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionPowerCapsules(), 5);
				if (!result) {
					homeMsgList.add("getSectionPowerCapsules not visible");
				} else {

					cfObj.commonClick(homePageObj.getSectionPowerCapsules());
					commonStudyMaterialSectionUtilObj = new CommonStudyMaterialSectionUtil(driver);
					Thread.sleep(3000);

					result = commonStudyMaterialSectionUtilObj.verifyPowerCapsulesPage(driver);
					if (!result) {
						homeMsgList.addAll(commonStudyMaterialSectionUtilObj.commonStudyMaterialMsgList);
						return result;
					}

					driver.navigate().back();

					result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnHome(), 5);
					if (!result) {
						homeMsgList.add("getBtnHome not visible");
						return result;
					}
					cfObj.commonClick(homePageObj.getBtnHome());

					result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnNavigationDrawer(), 5);
					if (!result) {
						homeMsgList.add("Navigation drawer not visible, might be not on home");
						return result;
					}
					cfObj.commonClick(homePageObj.getBtnNavigationDrawer());
				}

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionVideos(), 5);
				if (!result) {
					homeMsgList.add("getSectionVideos not visible");
				} else {

					cfObj.commonClick(homePageObj.getSectionVideos());

					videoCoursesPageUtilObj = new VideoCoursesPageUtil(driver);
					Thread.sleep(3000);

					result = videoCoursesPageUtilObj.validateVideoSectionUI(driver);
					if (!result) {
						homeMsgList.addAll(videoCoursesPageUtilObj.videoSectionMsgList);
						return result;
					}

					cfObj.commonClick(videoCoursesPageUtilObj.videoCoursesPageObj.getBackBtn());

					result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnHome(), 5);
					if (!result) {
						homeMsgList.add("getBtnHome not visible");
						return result;
					}
					cfObj.commonClick(homePageObj.getBtnHome());

					result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnNavigationDrawer(), 5);
					if (!result) {
						homeMsgList.add("Navigation drawer not visible, might be not on home");
						return result;
					}
					cfObj.commonClick(homePageObj.getBtnNavigationDrawer());
				}

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionDailyQuizzes(), 5);
				if (!result) {
					homeMsgList.add("getSectionDailyQuizzes not visible");
				} else {

					cfObj.commonClick(homePageObj.getSectionDailyQuizzes());

					studyMaterialUtilObj = new StudyMaterialTabUtil(driver);

					result = studyMaterialUtilObj.validateDailyQuizzesPageOpenFromSideDrawer(driver);
					if (!result) {
						homeMsgList.addAll(studyMaterialUtilObj.studyMaterialMsgList);
						return result;
					}

					driver.navigate().back();

					result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getQuizTab(), 20);
					if (!result) {
						homeMsgList.add("The daily quizzes btn is not visible");
						return result;
					}

					cfObj.commonClick(homePageObj.getQuizTab());

					result = verifyDailyQuizFeedTabPage(driver);
					if (!result) {
						homeMsgList.add("Not able to verify daily quiz feed tab page.");
						return result;
					}

					result = clickQuizByIndex(driver);
					if (!result) {
						homeMsgList.add("Not able to click quiz by index.");
						return result;
					}

					result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBackBtn(), 10);
					if (!result) {
						homeMsgList.add("Back btn is not visible");
						return result;
					}

					cfObj.commonClick(homePageObj.getBackBtn());
					Thread.sleep(2000);
					cfObj.commonClick(homePageObj.getBackBtn());

					result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnHome(), 5);
					if (!result) {
						homeMsgList.add("getBtnHome not visible");
						return result;
					}
					cfObj.commonClick(homePageObj.getBtnHome());
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getTabStudyMaterial(), 5);
				if (!result) {
					homeMsgList.add("getTabStudyMaterial store not visible");
					return result;
				}
				cfObj.commonClick(homePageObj.getTabStudyMaterial());

				studyMaterialUtilObj = new StudyMaterialTabUtil(driver);
				result = studyMaterialUtilObj.verifyStudyMaterialTabFunctionality(driver);
				if (!result) {
					homeMsgList.addAll(studyMaterialUtilObj.studyMaterialMsgList);
					return result;
				}
		} catch (Exception e) {
			result = false;
			homeMsgList.add("verifySideNavigationDrawerComponent_Part2_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyNightModeOnOffToggleBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getNightModeOnOffToggle(), 30);
			if (!result) {
				homeMsgList.add("NightModeOnOffToggle button is not visible.");
				return result;
			}

			if (homePageObj.getNightModeOnOffToggle().getAttribute("checked").equals("true")) {

				cfObj.commonClick(homePageObj.getNightModeOnOffToggle());
				Thread.sleep(1000);

				result = clickHomeBtn(driver);
				if (!result) {
					homeMsgList.add("Not able to click home button.");
					return result;
				}

				result = justOpenNavigationDrawer(driver);
				if (!result) {
					homeMsgList.add("Not able to navigate side drawer.");
					return result;
				}

				result = homePageObj.getNightModeOnOffToggle().getAttribute("checked").equals("false");
				if (!result) {
					homeMsgList.add("NightModeOnOff button is not clicked.");
					return result;
				}
				driver.navigate().back();

			} else {
				cfObj.commonClick(homePageObj.getNightModeOnOffToggle());
				Thread.sleep(1000);

				result = clickHomeBtn(driver);
				if (!result) {
					homeMsgList.add("Not able to click home button.");
					return result;
				}

				result = justOpenNavigationDrawer(driver);
				if (!result) {
					homeMsgList.add("Not able to navigate side drawer.");
					return result;
				}

				result = homePageObj.getNightModeOnOffToggle().getAttribute("checked").equals("true");
				if (!result) {
					homeMsgList.add("NightModeOnOff button is not clicked.");
					return result;
				}
				driver.navigate().back();
			}
		} catch (Exception e) {
			result = false;
			homeMsgList.add("NightModeOnOffToggleBtn_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyRateUsSection(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			if (!(cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionRateUs(), 3))) {
				cfObj.scrollUntilElementFound(driver, homePageObj.getSectionRateUs(), "Help & Support", 20, "text",
						direction.DOWN);
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionRateUs(), 30);
			if (!result) {
				homeMsgList.add("RateUs section is not visible.");
				return result;
			}

			cfObj.commonClick(homePageObj.getSectionRateUs());
			cfObj.commonClick(homePageObj.getSectionRateUs());

			Thread.sleep(2000);

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getRatingPopupTitle(), 30);
			if (!result) {
				homeMsgList.add("Rating popup title is not visible.");
				return result;
			}

			cfObj.commonClick(homePageObj.getRatingCancelBtn());

			Thread.sleep(1000);

			result = !(cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getRatingPopupTitle(), 5));
			if (!result) {
				homeMsgList.add("Rating popup title is visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			homeMsgList.add("RateUsSection_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean validateReferNowBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/know_more", "id", 10);
			if (!result) {
				homeMsgList.add("refer & earn btn in box in navigation drawer is not visible");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/know_more", "id"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Invite your friends\"]", "xpath", 30);
			if (!result) {
				homeMsgList.add("invite your friends text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.Button[@content-desc=\"Refer now\"]", "xpath", 20);
			if (!result) {
				homeMsgList.add("Your referral code text is not visible.");
				return result;
			}

			driver.navigate().back();

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/refer_now", "id", 10);
			if (!result) {
				homeMsgList.add("refer & earn btn in box in navigation drawer is not visible");
				return result;
			}
		} catch (Exception e) {
			result = false;
			homeMsgList.add("validateReferNowBtn_Exception: " + e.getMessage());
		}
		return result;
	}

//	public boolean clickOnReferNowBtn(AppiumDriver<MobileElement> driver) {
//		boolean result = true;
//		try {
//
//			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getReferNowBtn().get(1), 30);
//			if (!result) {
//				homeMsgList.add("ReferNowBtn is not visible.");
//				return result;
//			}
//			cfObj.commonClick(homePageObj.getReferNowBtn().get(1));
//
//			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@content-desc='Invite your friends']",
//					"xpath", 20);
//			if (!result) {
//				homeMsgList.add("Failed to click RefereNow Button.");
//				return result;
//			}
//
//		} catch (Exception e) {
//			result = false;
//			homeMsgList.add("clickOnReferNowBtn_Exception: " + e.getMessage());
//		}
//		return result;
//	}

	public boolean verifyVideoSection(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			loginUtilObj = new LoginUtil(driver);
			result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), false);
			if (!result) {
				homeMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}
			result = clickHomeBtn(driver);
			if (!result) {
				homeMsgList.add("Home button is not clicked.");
				return result;
			}
			result = openNavigationDrawer(driver);
			if (!result) {
				homeMsgList.add("Not able to open Navigation drawer.");
				return result;
			}
			if (cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionVideos(), 10)) {

				cfObj.commonClick(homePageObj.getSectionVideos());
				videoCoursesPageUtilObj = new VideoCoursesPageUtil(driver);

				if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"), 5)) {
					cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"));
				}

				result = videoCoursesPageUtilObj.validateVideoSectionUI(driver);
				if (!result) {
					homeMsgList.addAll(videoCoursesPageUtilObj.videoSectionMsgList);
					return result;
				}
				cfObj.commonClick(videoCoursesPageUtilObj.videoCoursesPageObj.getBackBtn());
			} else {
				System.out.println("Video section is not available.");
			}
		} catch (Exception e) {
			result = false;
			homeMsgList.add("VideoSection_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyQuizeSection(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			loginUtilObj = new LoginUtil(driver);
			result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), true);
			if (!result) {
				homeMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}
			result = clickHomeBtn(driver);
			if (!result) {
				homeMsgList.add("Home button is not clicked.");
				return result;
			}
			result = openNavigationDrawer(driver);
			if (!result) {
				homeMsgList.add("Not able to open Navigation drawer.");
				return result;
			}
			if (cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionDailyQuizzes(), 10)) {

				cfObj.commonClick(homePageObj.getSectionDailyQuizzes());
				studyMaterialUtilObj = new StudyMaterialTabUtil(driver);
				if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
					result = studyMaterialUtilObj.validateDailyQuizzesPageOpenFromSideDrawer(driver);
					if (!result) {
						homeMsgList.addAll(studyMaterialUtilObj.studyMaterialMsgList);
						return result;
					}
					result = studyMaterialUtilObj.clickOnDailyQuizeTopic(driver);
					if (!result) {
						homeMsgList.addAll(studyMaterialUtilObj.studyMaterialMsgList);
						return result;
					}
				} else {
					if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"), 5)) {
						cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"));
					}
				}

				if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "go_back", "id", 10)) {
					cfObj.commonClick(cfObj.commonGetElement(driver, "go_back", "id"));
				}

				result = verifyDailyQuizFeedTabPage(driver);
				if (!result) {
					homeMsgList.add("Not able to verify daily quiz feed tab page.");
					return result;
				}
				result = clickQuizByIndex(driver);
				if (!result) {
					homeMsgList.add("Not able to click quiz by index.");
					return result;
				}
				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBackBtn(), 10);
				if (!result) {
					homeMsgList.add("Back btn is not visible");
					return result;
				}

				cfObj.commonClick(homePageObj.getBackBtn());
				Thread.sleep(2000);
				cfObj.commonClick(homePageObj.getBackBtn());
			} else {
				System.out.println("Quize section is not available.");
			}

		} catch (Exception e) {
			result = false;
			homeMsgList.add("QuizeSection_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean validateBuyStudyMaterialSection(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionBuyStudyMaterial(), 30);
			if (!result) {
				homeMsgList.add("BuyStudyMaterial section is not visible.");
				return result;
			}
			cfObj.commonClick(homePageObj.getSectionBuyStudyMaterial());

			result = homePageObj.getBtnStore().getAttribute("selected").equals("true");
			if (!result) {
				homeMsgList.add("Not able to click BuyStudyMaterial Section");
				return result;
			}
			cfObj.commonClick(homePageObj.getBtnStore());

			storeUtilObj = new StorePageUtil(driver);
			result = storeUtilObj.verifyStorePage(driver);
			if (!result) {
				homeMsgList.addAll(storeUtilObj.storePageMsgList);
				return result;
			}
		} catch (Exception e) {
			result = false;
			homeMsgList.add("BuyStudyMaterialSection_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateMyContentSection(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionMyContent(), 30);
			if (!result) {
				homeMsgList.add("MyContent section is not visible, as it is hidden below refer & earn banner");
				return true;
			}
			cfObj.commonClick(homePageObj.getSectionMyContent());

			result = homePageObj.getBtnMyContent().getAttribute("selected").equals("true");
			if (!result) {
				homeMsgList.add("Not able to click MyContent Section");
				return result;
			}

			myContentUtilObj = new MyContentUtil(driver);
			result = myContentUtilObj.verifyMyContentPage(driver);
			if (!result) {
				homeMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnHome(), 5);
			if (!result) {
				homeMsgList.add("getBtnHome not visible");
				return result;
			}

			cfObj.commonClick(homePageObj.getBtnHome());

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnNavigationDrawer(), 5);
			if (!result) {
				homeMsgList.add("Navigation drawer not visible, might be not on home");
				return result;
			}
			cfObj.commonClick(homePageObj.getBtnNavigationDrawer());

		} catch (Exception e) {
			result = false;
			homeMsgList.add("MyContentSection_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateHelpAndSupportSection(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionHelpAndSupport(), 10);
			if (!result) {
				driver.navigate().back();
				homeMsgList.add("HelpAndSupport section is not visible, as it is hidden below refer & earn banner");
				return true;
			}
			cfObj.commonClick(homePageObj.getSectionHelpAndSupport());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.TextView[contains(@text,'Adda247 Help & Support')]",
					"xpath", 10);
			if (!result) {
				driver.navigate().back();

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.TextView[contains(@text,'Adda247 Help & Support')]",
						"xpath", 20);
				if (!result) {
					homeMsgList.add("HelpAndSupportWebView is not visible, even after removing permission popup");
					return result;
				}
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.TextView[contains(@text,'Adda247 Help & Support')]",
					"xpath", 20);
			if (!result) {
				homeMsgList.add("HelpAndSupportWebView is not visible, even after removing permission popup");
				return result;
			}

			driver.navigate().back();

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionHelpAndSupport(), 30);
			if (!result) {
				homeMsgList.add("HelpAndSupport section is not visible.");
				result = true;
			}

		} catch (Exception e) {
			result = false;
			homeMsgList.add("validateHelpAndSupportSection_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateOffLineCenterSection(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionOfflineCenter(), 10);
			if (!result) {

				driver.navigate().back();
				homeMsgList.add("OfflineCenter section is not visible, might be under refer & earn banner.");
				return true;

			} else {
				cfObj.commonClick(homePageObj.getSectionOfflineCenter());

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.widget.TextView[@text='Offline Centers']", "xpath", 10);
				if (!result) {
					homeMsgList.add("OfflineCenterWebView is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBackBtn(), 10);
				if (!result) {
					homeMsgList.add("Help & Support back btn is not visible.");
					return result;
				}
				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBackBtn(), 10);
				if (!result) {
					homeMsgList.add("Back btn is not visible");
					return result;
				}
				cfObj.commonClick(homePageObj.getBackBtn());
			}
		} catch (Exception e) {
			result = false;
			homeMsgList.add("validateOffLineCenterSection_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateHomePageTabs(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			commonStudyMaterialSectionUtilObj = new CommonStudyMaterialSectionUtil(driver);
			studyMaterialUtilObj = new StudyMaterialTabUtil(driver);
			videoCoursesPageUtilObj = new VideoCoursesPageUtil(driver);

			if (cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getFreeLiveClassTab(), 20)) {

				cfObj.commonClick(homePageObj.getFreeLiveClassTab());

				if (cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getFreeLiveClassTabs().get(0), 7)) {
					cfObj.commonClick(homePageObj.getFreeLiveClassTabs().get(0));
				}

				if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "emptyTextView", "id", 5)) {
					System.out.println("Currently no Free Live Class present.");
				} else {
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/calenderRecyclerView", "id", 10);
					if (!result) {
						homeMsgList.add("FreeLiveClass Calender is not visible.");
						return result;
					}

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "heading", "id", 10);
					if (!result) {
						homeMsgList.add("FreeLiveClass screen header is not visible.");
						return result;
					}
				}

				cfObj.pressAndroidKey(driver, key.BACK, 1);

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnHome(), 10);
				if (!result) {
					homeMsgList.add("home btn is not visible");
					return result;
				}
			} else {
				homeMsgList.add("FreeLiveClass tab is not available.");
			}

			if (cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getAskDoubtTab(), 20)) {

				cfObj.commonClick(homePageObj.getAskDoubtTab());

				Thread.sleep(2000);
				communityPageUtilObj = new CommunityPageUtil(driver);
				postingPageUtilObj = new PostingPageUtil(driver);

				cfObj.skipCoachMarkSocial(driver);

				if (cfObj.commonWaitForElementToBeVisible(driver,
						postingPageUtilObj.postingPageObj.getAttachmentPostTab(), 30)) {

					result = cfObj.pressAndroidKey(driver, key.BACK, 1);
					if (!result) {
						homeMsgList.add("Not able press back button.");
						return result;
					}
				} else {
					homeMsgList.add("getAttachmentPostTab is not visible, after click on getAskDoubtTab");
				}

				result = clickCommunityBtn(driver);
				if (!result) {
					return result;
				}

				result = communityPageUtilObj.verifyCommunityPage(driver);
				if (!result) {
					homeMsgList.addAll(communityPageUtilObj.communityPageMsgList);
					return result;
				}
			} else {
				homeMsgList.add("AskDoubt tab is not available.");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnHome(), 10);
			if (!result) {
				homeMsgList.add("home btn is not visible");
				return result;
			}
			cfObj.commonClick(homePageObj.getBtnHome());

			if (cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getCurrentAffairTab(), 20)) {

				cfObj.commonClick(homePageObj.getCurrentAffairTab());

				if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"), 5)) {
					cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"));
				}

				result = commonStudyMaterialSectionUtilObj.verifyCurrentAffairPage(driver);
				if (!result) {
					homeMsgList.addAll(commonStudyMaterialSectionUtilObj.commonStudyMaterialMsgList);
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBackBtn(), 10);
				if (!result) {
					homeMsgList.add("Back btn is not visible after coming from current affairs");
					return result;
				}
				cfObj.commonClick(homePageObj.getBackBtn());

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnHome(), 10);
				if (!result) {
					homeMsgList.add("home btn is not visible");
					return result;
				}
				cfObj.commonClick(homePageObj.getBtnHome());

			} else {
				homeMsgList.add("CurrentAffairTab tab is not available.");
			}

			if (cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getQuizTab(), 20)) {

				cfObj.commonClick(homePageObj.getQuizTab());

				if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"), 5)) {
					cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"));
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.widget.TextView[@text='Daily Quizzes']", "xpath", 10);
				if (!result) {
					homeMsgList.add("Daily Quizzes inside is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBackBtn(), 10);
				if (!result) {
					homeMsgList.add("Back btn is not visible after coming from quizzes");
					return result;
				}
				cfObj.commonClick(homePageObj.getBackBtn());

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnHome(), 10);
				if (!result) {
					homeMsgList.add("home btn is not visible");
					return result;
				}
				cfObj.commonClick(homePageObj.getBtnHome());

			} else {
				homeMsgList.add("QuizTab is not available.");
			}

			if (ConfigFileReader.strEnv.equalsIgnoreCase("PRODUCTION")){
				cfObj.swipeLeftOnElement(homePageObj.getQuizTab(), driver);
				cfObj.swipeLeftOnElement(homePageObj.getQuizTab(), driver);
				cfObj.swipeLeftOnElement(homePageObj.getQuizTab(), driver);
				cfObj.swipeLeftOnElement(homePageObj.getNotesAndArticleTab(), driver);
				cfObj.swipeLeftOnElement(homePageObj.getNotesAndArticleTab(), driver);
				cfObj.swipeLeftOnElement(homePageObj.getNotesAndArticleTab(), driver);
			}

			if (cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getVideosTab(), 20)) {

				cfObj.commonClick(homePageObj.getVideosTab());

				if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"), 5)) {
					cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"));
				}

				result = videoCoursesPageUtilObj.verifyVideoCoursesPage(driver);
				if (!result) {
					homeMsgList.addAll(videoCoursesPageUtilObj.videoSectionMsgList);
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBackBtn(), 10);
				if (!result) {
					homeMsgList.add("Back btn is not visible after coming from video");
					return result;
				}
				cfObj.commonClick(homePageObj.getBackBtn());

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnHome(), 10);
				if (!result) {
					homeMsgList.add("home btn is not visible");
					return result;
				}
				cfObj.commonClick(homePageObj.getBtnHome());
			} else {
				homeMsgList.add("VideosTab is not available.");
			}

			if (cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getPypTab(), 20)) {

				if(!ConfigFileReader.strEnv.equalsIgnoreCase("production")){
					cfObj.swipeLeftOnElement(homePageObj.getQuizTab(), driver);
					cfObj.swipeLeftOnElement(homePageObj.getJobAlertTab(), driver);
					cfObj.swipeLeftOnElement(homePageObj.getJobAlertTab(), driver);
				}

				cfObj.commonClick(homePageObj.getPypTab());

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,"//android.view.View[@content-desc=\"Previous Year Papers\"]","xpath",20);
				if (!result) {
					homeMsgList.add("Unable to verify inside PYP heading");
					return result;
				}
				driver.navigate().back();

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnHome(), 10);
				if (!result) {
					homeMsgList.add("home btn is not visible");
					return result;
				}
				cfObj.commonClick(homePageObj.getBtnHome());
			} else {
				System.out.println("PYP tab is not available.");
			}

			cfObj.swipeLeftOnElement(homePageObj.getQuizTab(), driver);
			Thread.sleep(500);
			cfObj.swipeLeftOnElement(homePageObj.getQuizTab(), driver);

			if (cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getJobAlertTab(), 20)) {

				cfObj.commonClick(homePageObj.getJobAlertTab());

				if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"), 5)) {
					cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"));
				}

				result = commonStudyMaterialSectionUtilObj.verifyJobAlertPage(driver);
				if (!result) {
					homeMsgList.addAll(commonStudyMaterialSectionUtilObj.commonStudyMaterialMsgList);
					return result;
				}
				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBackBtn(), 10);
				if (!result) {
					homeMsgList.add("Back btn is not visible after coming from job alert");
					return result;
				}
				cfObj.commonClick(homePageObj.getBackBtn());

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnHome(), 10);
				if (!result) {
					homeMsgList.add("home btn is not visible");
					return result;
				}
				cfObj.commonClick(homePageObj.getBtnHome());
			} else {
				homeMsgList.add("JobAlertTab is not available.");
			}

			cfObj.swipeLeftOnElement(homePageObj.getQuizTab(), driver);
			Thread.sleep(500);
			cfObj.swipeLeftOnElement(homePageObj.getQuizTab(), driver);
			cfObj.swipeLeftOnElement(homePageObj.getJobAlertTab(), driver);
			cfObj.swipeLeftOnElement(homePageObj.getJobAlertTab(), driver);

			if (cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getNotesAndArticleTab(), 20)) {

				cfObj.commonClick(homePageObj.getNotesAndArticleTab());

				if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"), 5)) {
					cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"));
				}

				result = commonStudyMaterialSectionUtilObj.verifyNotesAndArticlesPage(driver);
				if (!result) {
					homeMsgList.addAll(commonStudyMaterialSectionUtilObj.commonStudyMaterialMsgList);
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBackBtn(), 10);
				if (!result) {
					homeMsgList.add("Back btn is not visible after coming from NotesAndArticle");
					return result;
				}
				cfObj.commonClick(homePageObj.getBackBtn());

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnHome(), 10);
				if (!result) {
					homeMsgList.add("home btn is not visible");
					return result;
				}
				cfObj.commonClick(homePageObj.getBtnHome());
			} else {
				homeMsgList.add("NotesAndArticlesTab is not available.");
			}
		} catch (Exception e) {
			result = false;
			homeMsgList.add("HomePageTabs_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateHomePageVideoPostElements(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getPostTypeHeaderView().get(0), 30);
			if (!result) {
				homeMsgList.add("PostTypeHeaderView is not visible.");
				return result;
			}

			String postTypeName = homePageObj.getPostTypeHeaderView().get(0).getText();
			if (postTypeName == null) {
				homeMsgList.add("PostType name is null.");
				return false;
			}

			if (postTypeName.equalsIgnoreCase("Free Live Classes")) {
				cfObj.scrollUtill(driver, 1, direction.DOWN);
				postTypeName = homePageObj.getPostTypeHeaderView().get(0).getText();
			}

			if (postTypeName.equalsIgnoreCase("Videos")) {

				result = cfObj.commonWaitForListOfElementsToBeVisible(driver, homePageObj.getVideoPlayIcon(), 10);
				if(!result){
					homeMsgList.add("getVideoPlayIcon is not visible");
					return result;
				}

				cfObj.commonClick(homePageObj.getVideoPlayIcon().get(0));

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getVideoPlayScreen(), 30);
				if (!result) {
					homeMsgList.add("VideoPlayScreen is not visible.");
					return result;
				}

				Thread.sleep(4000);

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getVideoPlayScreenCloseBtn(), 30);
				if (!result) {
					homeMsgList.add("getVideoPlayScreenCloseBtn is not visible.");
					return result;
				}
				cfObj.commonClick(homePageObj.getVideoPlayScreenCloseBtn());

				Thread.sleep(2000);

				result = !cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getVideoPlayScreen(), 8);
				if (!result) {
					homeMsgList.add("Not able to close video play screen.");
					return result;
				}
			}

			result = cfObj.commonWaitForListOfElementsToBeVisible(driver, homePageObj.getPostTypeHeaderView(), 30);
			if (!result) {
				homeMsgList.add("getPostTypeHeaderView at 1st position is not visible.");
				return result;
			}
			cfObj.commonClick(homePageObj.getPostTypeHeaderView().get(0));

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSelectedTabPageTitle(), 30);
			if (!result) {
				homeMsgList.add("Selected post title is not visible.");
				return result;
			}

			result = cfObj.commonVerifyValueTextBox(homePageObj.getSelectedTabPageTitle(), postTypeName);
			if (!result) {
				homeMsgList.add("Selected post title is not matching.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBackBtn(), 10);
			if (!result) {
				homeMsgList.add("Back btn is not visible");
				return result;
			}
			cfObj.commonClick(homePageObj.getBackBtn());

			cfObj.scrollUtill(driver, 1, direction.DOWN);

			result = validateCommentPostIcon(driver);
			if (!result) {
				homeMsgList.add("Not able to validate CommentPostIcon.");
				return result;
			}

			result = validateLikePostIcon(driver);
			if (!result) {
				homeMsgList.add("Not able to validate LikePostIcon.");
				return result;
			}

			if (ConfigFileReader.isTablet.equalsIgnoreCase("false")) {

				result = validatePostShareIcon(driver);
				if (!result) {
					homeMsgList.add("Not able to validate PostShareIcon.");
					return result;
				}
			}

			boolean isBookmark = true;
			int scrollCount = 0;
			while (cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getPostBookMark(), 10)
					&& homePageObj.getPostBookMark().getAttribute("clickable").equals("false")) {
				cfObj.scrollUtill(driver, 1, direction.DOWN);
				if (scrollCount > 3) {
					isBookmark = false;
					homeMsgList.add("VideoPost bookMark is not present.");
					break;
				}
				scrollCount++;
			}
			if (isBookmark) {
				result = validateBookMarkBtnOnHomePagePost(driver);
				if (!result) {
					homeMsgList.add("Not able to validate BookMarkBtn on HomePage Post.");
					return result;
				}
			}
		} catch (Exception e) {
			result = false;
			homeMsgList.add("HomePageVideoPostElements_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateCommentPostIcon(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForListOfElementsToBeVisible(driver, homePageObj.getPostCommentIcon(), 30);
			if (!result) {
				homeMsgList.add("PostCommentIcon is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getPostCommentCountText().get(0), 30);
			if (!result) {
				homeMsgList.add("PostCommentCountText is not visible.");
				return result;
			}

			if(ConfigFileReader.strEnv.equalsIgnoreCase("production")){

				System.out.println("As it is production, not able to comment.");

			} else {
				cfObj.commonClick(homePageObj.getPostCommentIcon().get(0));

				Thread.sleep(5000);

				commentPageUtilObj = new CommentPageUtil(driver);
				result = commentPageUtilObj.verifyCommentPageUi(driver);
				if (!result) {
					homeMsgList.addAll(commentPageUtilObj.commentPageMsgList);
					return result;
				}
			}
		} catch (Exception e) {
			result = false;
			homeMsgList.add("CommentPostIcon_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateLikePostIcon(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getPostLikeIcon().get(0), 30);
			if (!result) {
				homeMsgList.add("PostLikeIcon is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getPostLikeCountText().get(0), 30);
			if (!result) {
				homeMsgList.add("PostLikeCountText is not visible.");
				return result;
			}

			String likeCount = homePageObj.getPostLikeCountText().get(0).getText();
			if (likeCount == null) {
				homeMsgList.add("Like count text is null.");
				return false;
			}

			cfObj.commonClick(homePageObj.getPostLikeIcon().get(0));

			Thread.sleep(2000);

			System.out.println(likeCount + "------------" + homePageObj.getPostLikeCountText().get(0).getText());

		} catch (Exception e) {
			result = false;
			homeMsgList.add("LikePostIcon_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateBookMarkBtnOnHomePagePost(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "book_marks", "id", 10)) {

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getPostBookMarkIcon().get(0), 30);
				if (!result) {
					homeMsgList.add("Book mark icon is not visible.");
					return result;
				}
				cfObj.commonClick(homePageObj.getPostBookMarkIcon().get(0));

				cfObj.commonClick(homePageObj.getBtnMyContent());

				myContentUtilObj = new MyContentUtil(driver);
				result = myContentUtilObj.clickOnBookmarksBtn(driver);
				if (!result) {
					homeMsgList.addAll(myContentUtilObj.myContentMsgList);
					return result;
				}

				result = myContentUtilObj.verifyBookMarkedMaterialBookmarksSection(driver);
				if (!result) {
					homeMsgList.addAll(myContentUtilObj.myContentMsgList);
					return result;
				}

				result = clickHomeBtn(driver);
				if (!result) {
					homeMsgList.add("Not able to click Home button.");
				}
			} else {
				System.out.println("BookMark icon is not visible.");
            }
		} catch (Exception e) {
			result = false;
			homeMsgList.add("validateBookMarkBtnOnHomePagePost_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validatePostShareIcon(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForListOfElementsToBeVisible(driver, homePageObj.getPostShareIcon(), 30);
			if (!result) {
				homeMsgList.add("PostShareIcon is not visible.");
				return result;
			}
//			cfObj.commonClick(homePageObj.getPostShareIcon().get(0));
//
//			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'Share via')]",
//					"xpath", 10);
//			if (!result) {
//				homeMsgList.add("Share via bottom sheet is not visible or popup title might be different.");
//			}
//
//			driver.navigate().back();
//
//			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getPostShareIcon().get(0), 30);
//			if (!result) {
//				homeMsgList.add("PostShareIcon is not visible.");
//				return result;
//			}

		} catch (Exception e) {
			result = false;
			homeMsgList.add("validateShareBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean handlePageLoader(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			int i = 0;
			while (cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getPageLoaderIcon(), 30)) {

				Thread.sleep(3000);
				i++;
				if (i > 4)
					break;

			}

		} catch (Exception e) {
			result = false;
			homeMsgList.add("handlePageLoader_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateTestStateBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			if (cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getTestStateBtn(), 20)) {

				if (homePageObj.getTestStateBtn().getText().equalsIgnoreCase("Register for free")) {
					cfObj.commonClick(homePageObj.getTestStateBtn());
					if (cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getTestStateBtn(), 10)) {
						cfObj.commonClick(homePageObj.getTestStateBtn());
					}
					int count = 0;
					while (!cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getRegisterForFreeBtn(), 10)) {
						Thread.sleep(3000);
						if (count > 6) {
							System.out.println("Not able to load RegistorForFreeBtn.");
							break;
						}
						count++;
					}

					result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getRegisterForFreeBtn(), 30);
					if (!result) {
						homeMsgList.add("RegisterForFreeBtn is not visible.");
						return result;
					}
					cfObj.commonClick(homePageObj.getRegisterForFreeBtn());
					result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getRegistrationConfContinueBtn(),
							30);
					if (!result) {
						homeMsgList.add("RegistrationConfContinueBtn is not visible.");
						return result;
					}

					cfObj.commonClick(homePageObj.getRegistrationConfContinueBtn());
				}
			}

			else {
				System.out.println("TestState button is not available.");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnHome(), 30);
			if (!result) {
				homeMsgList.add("Home button is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			homeMsgList.add("TestStateBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateSettingSection(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			if (!(cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionSettings(), 30))) {
				cfObj.scrollUtill(driver, 1, direction.DOWN);
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionSettings(), 10);
			if (!result) {
				homeMsgList.add("Setting section is not visible.");
				return result;
			}
			cfObj.commonClick(homePageObj.getSectionSettings());
			accountSettingUtilObj = new AccountSettingUtil(driver);
			result = accountSettingUtilObj.validateAccountSettingPageTitle(driver);
			if (!result) {
				homeMsgList.addAll(accountSettingUtilObj.accountSettingMsgList);
				return result;
			}

		} catch (Exception e) {
			result = false;
			homeMsgList.add("ValidateSettingSection_Exception" + e.getMessage());
		}
		return result;
	}

	/*---------------------------Sankalpa Application Method-------------------------------------*/

	public boolean validateDailyTestSection(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionDailyQuizzes(), 10);
			if (!result) {
				homeMsgList.add("Daily test section is not visible.");
				return result;
			}

			cfObj.commonClick(homePageObj.getSectionDailyQuizzes());
			studyMaterialUtilObj = new StudyMaterialTabUtil(driver);

			if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"), 5)) {
				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"));
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,
					studyMaterialUtilObj.studyMaterialPageObj.getDailyTestPageTitle(), 10);
			if (!result) {
				homeMsgList.add("Daily test page title is not visible.");
				return result;
			}

			cfObj.commonClick(studyMaterialUtilObj.studyMaterialPageObj.getBackBtn());

		} catch (Exception e) {
			result = false;
			homeMsgList.add("ValidateDailyTestSection_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean validateFreeEbookSection(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionPowerCapsules(), 10);
			if (!result) {
				homeMsgList.add("Free Ebook is not visible.");
				return result;
			}
			cfObj.commonClick(homePageObj.getSectionPowerCapsules());
			commonStudyMaterialSectionUtilObj = new CommonStudyMaterialSectionUtil(driver);
			result = commonStudyMaterialSectionUtilObj.verifyPowerCapsulesPage(driver);
			if (!result) {
				homeMsgList.addAll(commonStudyMaterialSectionUtilObj.commonStudyMaterialMsgList);
				return result;
			}

			cfObj.commonClick(studyMaterialUtilObj.studyMaterialPageObj.getBackBtn());

		} catch (Exception e) {
			result = false;
			homeMsgList.add("ValidateFreeEbookSection_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean validateArticlesSection(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionNotesAndArticles(), 10);
			if (!result) {
				homeMsgList.add("Article section is not visible.");
				return result;
			}
			cfObj.commonClick(homePageObj.getSectionNotesAndArticles());
			commonStudyMaterialSectionUtilObj = new CommonStudyMaterialSectionUtil(driver);
			result = cfObj.commonWaitForElementToBeVisible(driver,
					commonStudyMaterialSectionUtilObj.commonStudyMaterialSectionORObj.getNotesAndArticlePageTitle(),
					10);
			if (!result) {
				homeMsgList.add("Notes and Articles page title is not visible.");
				return result;
			}

			cfObj.commonClick(studyMaterialUtilObj.studyMaterialPageObj.getBackBtn());

		} catch (Exception e) {
			result = false;
			homeMsgList.add("ValidateArticlesSection_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean validateYouTubeClassSection(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getSectionVideos(), 10);
			if (!result) {
				homeMsgList.add("YouTube class section is not visible.");
				return result;
			}
			cfObj.commonClick(homePageObj.getSectionVideos());
			videoCoursesPageUtilObj = new VideoCoursesPageUtil(driver);
			result = cfObj.commonWaitForElementToBeVisible(driver,
					videoCoursesPageUtilObj.videoCoursesPageObj.getVideoSectionTitle(), 10);
			if (!result) {
				homeMsgList.add("YouTube class section page title is not visible.");
				return result;
			}
			cfObj.commonClick(studyMaterialUtilObj.studyMaterialPageObj.getBackBtn());

		} catch (Exception e) {
			result = false;
			homeMsgList.add("ValidateYouTubeClassSection_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean verifyStudyMaterialTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			loginUtilObj = new LoginUtil(driver);
			result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), true);
			if (!result) {
				homeMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}
			result = clickHomeBtn(driver);
			if (!result) {
				homeMsgList.add("Home button is not clicked.");
				return result;
			}
			result = clickStudyMaterialTab(driver);
			if (!result) {
				homeMsgList.add("Not able to click StudyMaterialTab.");
				return result;
			}
			studyMaterialUtilObj = new StudyMaterialTabUtil(driver);
			result = studyMaterialUtilObj.verifyStudyMaterialTabFunctionality(driver);
			if (!result) {
				homeMsgList.addAll(studyMaterialUtilObj.studyMaterialMsgList);
				return result;
			}

		} catch (Exception e) {
			result = false;
			homeMsgList.add("verifyStudyMaterialTab_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean addedMaterialInBookMarkTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			commonStudyMaterialSectionUtilObj = new CommonStudyMaterialSectionUtil(driver);
			for (int i = 0; i < 3; i++) {
				result = justOpenNavigationDrawer(driver);
				if (!result) {
					homeMsgList.add("Not able to open sideDrawer.");
					return result;
				}
				switch (i) {
				case 0:
					cfObj.commonClick(homePageObj.getSectionCurrentAffairs());
					Thread.sleep(2000);

					if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"), 5)) {
						cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"));
					}
					break;
				case 1:
					cfObj.commonClick(homePageObj.getSectionJobAlerts());
					break;

				case 2:
					cfObj.commonClick(homePageObj.getSectionNotesAndArticles());
					break;
				}

				result = commonStudyMaterialSectionUtilObj.clicOnMaterialAndAddInBookMark(driver);
				if (!result) {
					homeMsgList.addAll(commonStudyMaterialSectionUtilObj.commonStudyMaterialMsgList);
					return result;
				}
			}

			result = justOpenNavigationDrawer(driver);
			if (!result) {
				homeMsgList.add("Not able to open sideDrawer.");
				return result;
			}
			cfObj.commonClick(homePageObj.getSectionVideos());
			videoCoursesPageUtilObj = new VideoCoursesPageUtil(driver);
			result = videoCoursesPageUtilObj.addVideoInBookMark(driver);
			if (!result) {
				homeMsgList.addAll(videoCoursesPageUtilObj.videoSectionMsgList);
				return result;
			}

			result = justOpenNavigationDrawer(driver);
			if (!result) {
				homeMsgList.add("Not able to open sideDrawer.");
				return result;
			}

			cfObj.commonClick(homePageObj.getSectionDailyQuizzes());
			studyMaterialUtilObj = new StudyMaterialTabUtil(driver);
			result = studyMaterialUtilObj.clickOnDailyQuizeTopic(driver);
			if (!result) {
				homeMsgList.addAll(studyMaterialUtilObj.studyMaterialMsgList);
				return result;
			}

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "go_back", "id", 10)) {
				cfObj.commonClick(cfObj.commonGetElement(driver, "go_back", "id"));
			}

			result = clickQuizByIndex(driver);
			if (!result) {
				homeMsgList.add("Not able to click quize by index.");
				return result;
			}
			cfObj.waitForPageLoading(driver, 8, 2000, cfObj.commonGetElement(driver, "//*[@text='ATTEMPT']", "xpath"));
			result = clickAttemptLinkOfRecentlyDownloadedTest(driver);
			if (!result) {
				homeMsgList.add("Unable to open recently downloaded test");
				return result;
			}

			commonTestUtilObj = new CommonTestUtil(driver);

			result = commonTestUtilObj.verifyInstructionPage(driver);
			if (!result) {
				homeMsgList.addAll(commonTestUtilObj.commonTestMsgList);
				return result;
			}

			result = commonTestUtilObj.clickStartTestBtn(driver);
			if (!result) {
				homeMsgList.addAll(commonTestUtilObj.commonTestMsgList);
				return result;
			}

			cfObj.handleHints(driver);

			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
				result = commonTestUtilObj.clickOnFilterBtn(driver);
				if (!result) {
					homeMsgList.addAll(commonTestUtilObj.commonTestMsgList);
					return result;
				}
			}
			result = commonTestUtilObj.clickSubmitTestBtn(driver);
			if (!result) {
				homeMsgList.addAll(commonTestUtilObj.commonTestMsgList);
				return result;
			}
			result = commonTestUtilObj.addQuestionToBookMarkOld(driver);
			if (!result) {
				homeMsgList.addAll(commonTestUtilObj.commonTestMsgList);
				return result;
			}

			result = cfObj.pressAndroidKey(driver, key.BACK, 3);
			if (!result) {
				homeMsgList.add("Not able to press back button 3 times.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			homeMsgList.add("addedMaterialInBookMark_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnPostCommentBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			cfObj.scrollUtill(driver, 1, direction.DOWN);

			int i = 0;

			while (!cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "msg", "id", 5)) {
				cfObj.scrollUtill(driver, 1, direction.DOWN);
				if (i > 6) {
					System.out.println("Comment button is not available.");
					break;
				}
				i++;
			}
			cfObj.commonClick_Action(driver, homePageObj.getPostCommentIcon().get(0));

		} catch (Exception e) {
			homeMsgList.add("clickOnPostCommentBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyDigiFillerTest(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String mockName = "DTB testseries";
		Login login = new Login();
		UserApiUtil us = new UserApiUtil();
		OrderApiUtil orderApiObj = new OrderApiUtil();
		try {

			String mobileNumber = Common_Function.randomPhoneNumber(10, "8");

			loginUtilObj = new LoginUtil(driver);
			result = loginUtilObj.verifySignUp(driver, mobileNumber, true);
			if (!result) {
				homeMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			login = us.userLoginWithMobileNumber(mobileNumber);
			if (login.getData() == null) {
				homeMsgList.add("error in login with user1");
				return false;
			}

			result = orderApiObj.createOrder(login.getData().getJwtToken(), "", 39413, mobileNumber);
			if (!result) {
				homeMsgList.add("error in creating order");
				return result;
			}

			Thread.sleep(5000);

			result = clickMyContentButton(driver);
			if (!result) {
				homeMsgList.add("Failed to click MyContentButton.");
				return result;
			}

			myContentUtilObj = new MyContentUtil(driver);
			result = myContentUtilObj.clickOnPurchasedBtn(driver);
			if (!result) {
				homeMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}

			result = myContentUtilObj.clickOnSpecificPurchasedPackage(driver, mockName);
			if (!result) {
				homeMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}

			result = verifyViewAllContentInSelectedCourse(driver);
			if (!result){
				homeMsgList.add("verifyViewAllContentInSelectedCourse failed.");
				return result;
			}

			childPackageUtilObj = new ChildPackageUtil(driver);
			result = childPackageUtilObj.verifyTestSeriesPage(driver);
			if (!result) {
				homeMsgList.addAll(childPackageUtilObj.packagePageMsgList);
				return result;
			}

			result = childPackageUtilObj.downloadQuiz(driver);
			if (!result) {
				homeMsgList.addAll(childPackageUtilObj.packagePageMsgList);
				return result;
			}

			result = childPackageUtilObj.clickOnAttemptLink(driver);
			if (!result) {
				homeMsgList.addAll(childPackageUtilObj.packagePageMsgList);
				return result;
			}

			commonTestUtilObj = new CommonTestUtil(driver);

			result = commonTestUtilObj.verifyInstructionPage(driver);
			if (!result) {
				homeMsgList.addAll(commonTestUtilObj.commonTestMsgList);
				return result;
			}

			int questionCount = Integer
					.parseInt(cfObj.commonGetElement(driver, "com.adda247.app:id/total_question_count", "id").getText());
			result = commonTestUtilObj.clickStartTestBtn(driver);
			if (!result) {
				homeMsgList.addAll(commonTestUtilObj.commonTestMsgList);
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,
					commonTestUtilObj.commonTestPageObj.getQuestionNumber(), 10);
			if (!result) {
				homeMsgList.add("QuestionNumber is not visible.");
				return result;
			}
			String questionNumber = commonTestUtilObj.commonTestPageObj.getQuestionNumber().getText();
			if (questionNumber == null) {
				homeMsgList.add("QuestionNumber text is null.");
				return false;
			}

			result = commonTestUtilObj.clickOnTestPauseBtn(driver);
			if (!result) {
				homeMsgList.add("Not able to click TestPause button.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='RESUME']", "xpath", 30);
			if (result) {
				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='RESUME']", "xpath"));
			} else {
				homeMsgList.add("RESUME is not visible.");
				return result;
			}

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='RESUME']", "xpath", 10)) {
				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='RESUME']", "xpath"));
			}

			result = commonTestUtilObj.clickOnResumeTestBtn(driver);
			if (!result) {
				homeMsgList.add("Not able to click ResumeTest button.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,
					commonTestUtilObj.commonTestPageObj.getQuestionNumber(), 10);
			if (!result) {
				homeMsgList.add("QuestionNumber is not visible.");
				return result;
			}

			result = cfObj.commonVerifyValueTextBox(commonTestUtilObj.commonTestPageObj.getQuestionNumber(),
					questionNumber);
			if (!result) {
				homeMsgList.add("Resumed QuestionNumber is not matching.");
				return result;
			}

			result = commonTestUtilObj.clickOnFilterBtn(driver);
			if (!result) {
				homeMsgList.addAll(commentPageUtilObj.commentPageMsgList);
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "close_btn", "id"));

			result = commonTestUtilObj.takeAndSubmitDescriptiveTypeTest(driver, questionCount);
			if (!result) {
				homeMsgList.add("Unable to take test");
				return result;
			}

			result = commonTestUtilObj.verifyAndSubmitFinishTestPopUp(driver);
			if (!result) {
				homeMsgList.add("Unable to verify finish test pop-up");
				return result;
			}

			result = commonTestUtilObj.verifyDigiFillerTestResultScreen(driver);
			if (!result) {
				homeMsgList.addAll(commonTestUtilObj.commonTestMsgList);
				return result;
			}

//			 result = commonTestUtilObj.validateDigiFillerTestSolutionBtn(driver, isQB);
//			 if(!result) {
//			 	homeMsgList.addAll(commonTestUtilObj.commonTestMsgList);
//			 	return result;
//			 }

			result = commonTestUtilObj.clickOnDigiFillerTestReAttemptBtn(driver);
			if (!result) {
				homeMsgList.addAll(commonTestUtilObj.commonTestMsgList);
				return result;
			}

			result = commonTestUtilObj.verifyInstructionPage(driver);
			if (!result) {
				homeMsgList.addAll(commonTestUtilObj.commonTestMsgList);
				return result;
			}

			result = commonTestUtilObj.completeAndSubmitDescriptiveTypeTest(driver);
			if (!result) {
				homeMsgList.addAll(commonTestUtilObj.commonTestMsgList);
				return result;
			}

			result = commonTestUtilObj.verifyDigiFillerTestResultScreen(driver);
			if (!result) {
				homeMsgList.addAll(commonTestUtilObj.commonTestMsgList);
				return result;
			}

		} catch (Exception e) {
			result = false;
			homeMsgList.add("verifyDigiFillerTest_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyViewAllContentInSelectedCourse(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Selected Course\"]", "xpath", 15);
			if (!result) {

				cfObj.scrollUtill(driver, 2, direction.UP);

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Selected Course\"]", "xpath", 20);
				if (!result) {
					homeMsgList.add("Selected course text is not visible or might be page is not loaded.");
					return result;
				}
			}
			Thread.sleep(500);

			cfObj.scrollUtill(driver, 2, direction.DOWN);

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"viewAllContentClicked\n" +
					"VIEW ALL CONTENT\"]", "xpath", 20);
			if (!result) {
				homeMsgList.add("View all content btn is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'Refer & Earn')]", "xpath", 20);
			if (!result) {
				homeMsgList.add("Refer & earn text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'REFER NOW')]", "xpath", 20);
			if (!result) {
				homeMsgList.add("Refer & earn btn is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Dream big,\n" +
					"achieve bigger!\"]", "xpath", 20);
			if (!result) {
				homeMsgList.add("Dream big text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Crafted with\"]", "xpath", 20);
			if (!result) {
				homeMsgList.add("Crafted with text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"at Adda247\"]", "xpath", 20);
			if (!result) {
				homeMsgList.add("At adda247 text is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.View[@content-desc=\"viewAllContentClicked\n" +
					"VIEW ALL CONTENT\"]", "xpath"));

		} catch (Exception e) {
			result = false;
			homeMsgList.add("verifyViewAllContentInSelectedCourse_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyViewAllContentInSelectedCourseWithoutSelectedCourse(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			cfObj.smallScrollUtillNtimes(driver, 1, direction.DOWN);
			cfObj.scrollUtill(driver, 2, direction.DOWN);

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"viewAllContentClicked\n" +
					"VIEW ALL CONTENT\"]", "xpath", 20);
			if (!result) {
				homeMsgList.add("View all content btn is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'Refer & Earn')]", "xpath", 20);
			if (!result) {
				homeMsgList.add("Refer & earn text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'REFER NOW')]", "xpath", 20);
			if (!result) {
				homeMsgList.add("Refer & earn btn is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Dream big,\n" +
					"achieve bigger!\"]", "xpath", 20);
			if (!result) {
				homeMsgList.add("Dream big text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Crafted with\"]", "xpath", 20);
			if (!result) {
				homeMsgList.add("Crafted with text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"at Adda247\"]", "xpath", 20);
			if (!result) {
				homeMsgList.add("At adda247 text is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.View[@content-desc=\"viewAllContentClicked\n" +
					"VIEW ALL CONTENT\"]", "xpath"));

		} catch (Exception e) {
			result = false;
			homeMsgList.add("verifyViewAllContentInSelectedCourse_Exception" + e.getMessage());
		}
		return result;
	}


	public boolean verifyHomePageUIOnIOS(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			loginUtilObj = new LoginUtil(driver);
			result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), false);
			if (!result) {
				homeMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}
			result = clickHomeBtn(driver);
			if (!result) {
				homeMsgList.add("Home button is not clicked.");
				return result;
			}

			result = validateCurrentAffairTabForIOS(driver);
			if (!result) {
				homeMsgList.add("Failed to validate CurrentAffairTab For IOS .");
				return result;
			}

			result = validateJobAlertsTabForIOS(driver);
			if (!result) {
				homeMsgList.add("Failed to validate JobAlertsTab For IOS .");
				return result;
			}

			// Quiz Tab validation
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "Daily Quizzes", "name", 10);
			if (!result) {
				homeMsgList.add("DailyQuiz tab is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "Daily Quizzes", "name"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//XCUIElementTypeStaticText[contains(@name,'GET QUIZ')]", "xpath", 10);
			if (!result) {
				homeMsgList.add("Quizzes are not visible.Failed to click DailyQuiz Tab.");
				return result;
			}

			// Subject-wise Quiz tab validation
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "Subject-wise Quizzes", "name", 10);
			if (!result) {
				homeMsgList.add("Subject-wise Quizzes tab is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "Subject-wise Quizzes", "name"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"(//XCUIElementTypeScrollView)[2]/following-sibling::XCUIElementTypeImage", "xpath", 10);
			if (!result) {
				homeMsgList.add("Failed to click Subject-wise Quizzes tab.");
				return result;
			}
			int size = cfObj.commonGetElements(driver,
					"(//XCUIElementTypeScrollView)[2]/following-sibling::XCUIElementTypeImage", "xpath").size();

			for (int i = 0; i < size; i++) {
				cfObj.commonClick(cfObj
						.commonGetElements(driver,
								"(//XCUIElementTypeScrollView)[2]/following-sibling::XCUIElementTypeImage", "xpath")
						.get(i));
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//XCUIElementTypeStaticText[contains(@name,'GET QUIZ')]", "xpath", 10);
				if (!result) {

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "RETRY", "name", 10);
					if (!result) {
						homeMsgList.add("Quizzes are not visible.Failed to click DailyQuiz Tab.");
						return result;
					}
				}

				cfObj.commonClick(cfObj.commonGetElements(driver, "XCUIElementTypeButton", "class").get(0));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"(//XCUIElementTypeScrollView)[2]/following-sibling::XCUIElementTypeImage", "xpath", 10);
				if (!result) {
					homeMsgList.add("Failed to click Subject-wise Quizzes tab screen back button.");
					return result;
				}
			}

			// Free PDF validation

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "Free PDF", "name", 10);
			if (!result) {
				homeMsgList.add("Free PDF tab is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "Free PDF", "name"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "XCUIElementTypeStaticText", "class", 10);
			if (!result) {
				homeMsgList.add("Free PDF is not visible.Failed to click Free PDF tab.");
				return result;
			}
			cfObj.swipeUpOnElement(cfObj.commonGetElement(driver, "Job Alerts", "name"), driver);
			Thread.sleep(1000);
			cfObj.swipeUpOnElement(cfObj.commonGetElement(driver, "Free PDF", "name"), driver);

			// All India Mock & Scholarship Tests validation

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "All India Mock & Scholarship Tests",
					"name", 10);
			if (!result) {
				homeMsgList.add("All India Mock & Scholarship Tests Tab is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "All India Mock & Scholarship Tests", "name"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//XCUIElementTypeScrollView/following-sibling::XCUIElementTypeOther/XCUIElementTypeImage", "xpath",
					10);
			if (!result) {
				homeMsgList.add(
						"All India Mock & Scholarship Tests Tab material is not visible.Failed to click All India Mock & Scholarship Tests Tab.");
				return result;
			}

			// Power Capsules tab validation

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "Power Capsules", "name", 10);
			if (!result) {
				homeMsgList.add("Power Capsules Tab is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "Power Capsules", "name"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//XCUIElementTypeScrollView/following-sibling::XCUIElementTypeOther", "xpath", 10);
			if (!result) {
				homeMsgList.add("PowerCapsule material is not visible.");
				return result;
			}

			// Notes and Articles Tab validation

			result = validateNotesAndArticlesTabForIOS(driver);
			if (!result) {
				homeMsgList.add("Failed to validate NotesAndArticlesTab For IOS.");
				return result;
			}

			// Videos tab Validation

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "Videos", "name", 10);
			if (!result) {
				homeMsgList.add("Videos tab is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "Videos", "name"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "Recent Videos", "name", 10);
			if (!result) {
				homeMsgList.add("Recent Video Link is not visible.Failed to click Videos tab.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "View More", "name", 10);
			if (!result) {
				homeMsgList.add("ViewMore Link is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "View More", "name"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//XCUIElementTypeOther[@name='Recent Videos']", "xpath", 10);
			if (!result) {
				homeMsgList.add("RecentVideo screen title is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "XCUIElementTypeButton", "class"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "View More", "name", 10);
			if (!result) {
				homeMsgList.add("ViewMore Link is not visible.Failed to close RecentVideo screen.");
				return result;
			}

			result = verifyChangeExamContainerOnIOS(driver);
			if (!result) {
				homeMsgList.add("Failed to verify ChangeExamContainer On IOS.");
				return result;
			}

			result = verifyChangeLanguageIconOnIOS(driver);
			if (!result) {
				homeMsgList.add("Failed to verify ChangeLanguageIcon On IOS.");
				return result;
			}

		} catch (Exception e) {
			homeMsgList.add("verifyHomePageUIOnIOS_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean validateCurrentAffairTabForIOS(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "Current Affairs", "name", 10);
			if (!result) {
				homeMsgList.add("CurrentAffair Tab is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "Current Affairs", "name"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//XCUIElementTypeScrollView/following-sibling::XCUIElementTypeOther", "xpath", 10);
			if (!result) {
				homeMsgList.add("CurrentAffair material is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElements(driver,
					"//XCUIElementTypeScrollView/following-sibling::XCUIElementTypeOther", "xpath").get(0));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//XCUIElementTypeOther[@name='Current Affairs']", "xpath", 10);
			if (!result) {
				homeMsgList.add("CurrentAffair screen title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "XCUIElementTypeButton", "class", 10);
			if (!result) {
				homeMsgList.add("CurrentAffair screen back button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//XCUIElementTypeOther[@name='Current Affairs']/following-sibling::XCUIElementTypeOther", "xpath",
					10);
			if (!result) {
				homeMsgList.add("CurrentAffair screen 3Dot icon is not visible.");
				return result;
			}

			for (int i = 0; i < 4; i++) {

				cfObj.commonClick(cfObj.commonGetElement(driver,
						"//XCUIElementTypeOther[@name='Current Affairs']/following-sibling::XCUIElementTypeOther",
						"xpath"));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "Font Size", "name", 10);
				if (!result) {
					homeMsgList.add("FontSize Tab is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "Report Content", "name", 10);
				if (!result) {
					homeMsgList.add("ReportContent Tab is not visible.");
					return result;
				}

				cfObj.commonClick(cfObj.commonGetElement(driver, "Font Size", "name"));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//XCUIElementTypeOther[@name='Font Size']", "xpath", 10);
				if (!result) {
					homeMsgList.add("FontSize screen title is not visible.");
					return result;
				}
				if (i == 0) {
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "Small", "name", 10);
					if (!result) {
						homeMsgList.add("Small-FontSize option is not visible.");
						return result;
					}

					cfObj.commonClick(cfObj.commonGetElement(driver, "Small", "name"));
				} else if (i == 1) {
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "Large", "name", 10);
					if (!result) {
						homeMsgList.add("Large-FontSize option is not visible.");
						return result;
					}

					cfObj.commonClick(cfObj.commonGetElement(driver, "Large", "name"));
				} else if (i == 2) {
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "Extra Large", "name", 10);
					if (!result) {
						homeMsgList.add("ExtraLarge-FontSize option is not visible.");
						return result;
					}

					cfObj.commonClick(cfObj.commonGetElement(driver, "Extra Large", "name"));
				} else if (i == 3) {
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "Medium (Default)", "name", 10);
					if (!result) {
						homeMsgList.add("Medium-FontSize option is not visible.");
						return result;
					}

					cfObj.commonClick(cfObj.commonGetElement(driver, "Medium (Default)", "name"));
				}
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//XCUIElementTypeOther[@name='Current Affairs']/following-sibling::XCUIElementTypeOther",
						"xpath", 10);
				if (!result) {
					homeMsgList.add("CurrentAffair screen 3Dot icon is not visible.");
					return result;
				}
			}

			cfObj.scrollUtill(driver, 6, direction.DOWN);

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "XCUIElementTypeLink", "class", 10)) {
				cfObj.commonClick(cfObj.commonGetElements(driver, "XCUIElementTypeLink", "class").get(0));
				purchasePackageUtilObj = new PurchasePackageUtil(driver);
				result = purchasePackageUtilObj.verifyUnpurchasedPackagePage(driver);
				if (!result) {
					homeMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
					return result;
				}
				cfObj.commonClick(cfObj.commonGetElement(driver, "storeProductDetailBackItem", "name"));
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//XCUIElementTypeOther[@name='Current Affairs']", "xpath", 10);
				if (!result) {
					homeMsgList.add("Failed to click UnPurchasePackage page back button.");
					return result;
				}
			} else {
				homeMsgList.add("CurrentAffair Link is not found");
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "XCUIElementTypeButton", "class"));
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//XCUIElementTypeOther[contains(@name,'Home')]", "xpath", 10);
			if (!result) {
				homeMsgList.add("Failed to click CurrentAffair Screen back button.");
				return result;
			}

		} catch (Exception e) {
			homeMsgList.add("validateCurrentAffairTabForIOS_Exception: " + e.getMessage());
			result = false;
		}
		return result;

	}

	public boolean validateJobAlertsTabForIOS(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "Job Alerts", "name", 10);
			if (!result) {
				homeMsgList.add("Job Alerts Tab is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "Job Alerts", "name"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//XCUIElementTypeScrollView/following-sibling::XCUIElementTypeOther", "xpath", 10);
			if (!result) {
				homeMsgList.add("Job Alerts material is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElements(driver,
					"//XCUIElementTypeScrollView/following-sibling::XCUIElementTypeOther", "xpath").get(0));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//XCUIElementTypeOther[@name='Job Alerts']", "xpath", 10);
			if (!result) {
				homeMsgList.add("Job Alerts screen title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "XCUIElementTypeButton", "class", 10);
			if (!result) {
				homeMsgList.add("Job Alerts screen back button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//XCUIElementTypeOther[@name='Job Alerts']/following-sibling::XCUIElementTypeOther", "xpath", 10);
			if (!result) {
				homeMsgList.add("Job Alerts screen 3Dot icon is not visible.");
				return result;
			}

			for (int i = 0; i < 4; i++) {

				cfObj.commonClick(cfObj.commonGetElement(driver,
						"//XCUIElementTypeOther[@name='Job Alerts']/following-sibling::XCUIElementTypeOther", "xpath"));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "Font Size", "name", 10);
				if (!result) {
					homeMsgList.add("FontSize Tab is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "Report Content", "name", 10);
				if (!result) {
					homeMsgList.add("ReportContent Tab is not visible.");
					return result;
				}

				cfObj.commonClick(cfObj.commonGetElement(driver, "Font Size", "name"));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//XCUIElementTypeOther[@name='Font Size']", "xpath", 10);
				if (!result) {
					homeMsgList.add("FontSize screen title is not visible.");
					return result;
				}
				if (i == 0) {
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "Small", "name", 10);
					if (!result) {
						homeMsgList.add("Small-FontSize option is not visible.");
						return result;
					}

					cfObj.commonClick(cfObj.commonGetElement(driver, "Small", "name"));
				} else if (i == 1) {
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "Large", "name", 10);
					if (!result) {
						homeMsgList.add("Large-FontSize option is not visible.");
						return result;
					}

					cfObj.commonClick(cfObj.commonGetElement(driver, "Large", "name"));
				} else if (i == 2) {
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "Extra Large", "name", 10);
					if (!result) {
						homeMsgList.add("ExtraLarge-FontSize option is not visible.");
						return result;
					}

					cfObj.commonClick(cfObj.commonGetElement(driver, "Extra Large", "name"));
				} else if (i == 3) {
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "Medium (Default)", "name", 10);
					if (!result) {
						homeMsgList.add("Medium-FontSize option is not visible.");
						return result;
					}

					cfObj.commonClick(cfObj.commonGetElement(driver, "Medium (Default)", "name"));
				}
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//XCUIElementTypeOther[@name='Job Alerts']/following-sibling::XCUIElementTypeOther", "xpath",
						10);
				if (!result) {
					homeMsgList.add("Job Alerts screen 3Dot icon is not visible.");
					return result;
				}
			}

			cfObj.scrollUtill(driver, 6, direction.DOWN);

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "XCUIElementTypeLink", "class", 10)) {
				cfObj.commonClick(cfObj.commonGetElements(driver, "XCUIElementTypeLink", "class").get(0));
				purchasePackageUtilObj = new PurchasePackageUtil(driver);
				result = purchasePackageUtilObj.verifyUnpurchasedPackagePage(driver);
				if (!result) {
					homeMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
					return result;
				}
				cfObj.commonClick(cfObj.commonGetElement(driver, "storeProductDetailBackItem", "name"));
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//XCUIElementTypeOther[@name='Job Alerts']", "xpath", 10);
				if (!result) {
					homeMsgList.add("Failed to click UnPurchasePackage page back button.");
					return result;
				}
			} else {
				homeMsgList.add("Job Alerts Link is not found");
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "XCUIElementTypeButton", "class"));
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//XCUIElementTypeOther[contains(@name,'Home')]", "xpath", 10);
			if (!result) {
				homeMsgList.add("Failed to click Job Alerts Screen back button.");
				return result;
			}

		} catch (Exception e) {
			homeMsgList.add("validateJobAlertsTabForIOS_Exception: " + e.getMessage());
			result = false;
		}
		return result;

	}

	public boolean validateNotesAndArticlesTabForIOS(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "Notes and Articles", "name", 10);
			if (!result) {
				homeMsgList.add("Notes and Articles Tab is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "Notes and Articles", "name"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//XCUIElementTypeScrollView/following-sibling::XCUIElementTypeOther", "xpath", 10);
			if (!result) {
				homeMsgList.add("Notes and Articles material is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElements(driver,
					"//XCUIElementTypeScrollView/following-sibling::XCUIElementTypeOther", "xpath").get(0));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//XCUIElementTypeOther[@name='Notes and Articles']", "xpath", 10);
			if (!result) {
				homeMsgList.add("Notes and Articles screen title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "XCUIElementTypeButton", "class", 10);
			if (!result) {
				homeMsgList.add("Notes and Articles screen back button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//XCUIElementTypeOther[@name='Notes and Articles']/following-sibling::XCUIElementTypeOther",
					"xpath", 10);
			if (!result) {
				homeMsgList.add("Notes and Articles screen 3Dot icon is not visible.");
				return result;
			}

			for (int i = 0; i < 4; i++) {

				cfObj.commonClick(cfObj.commonGetElement(driver,
						"//XCUIElementTypeOther[@name='Notes and Articles']/following-sibling::XCUIElementTypeOther",
						"xpath"));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "Font Size", "name", 10);
				if (!result) {
					homeMsgList.add("FontSize Tab is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "Report Content", "name", 10);
				if (!result) {
					homeMsgList.add("ReportContent Tab is not visible.");
					return result;
				}

				cfObj.commonClick(cfObj.commonGetElement(driver, "Font Size", "name"));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//XCUIElementTypeOther[@name='Font Size']", "xpath", 10);
				if (!result) {
					homeMsgList.add("FontSize screen title is not visible.");
					return result;
				}
				if (i == 0) {
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "Small", "name", 10);
					if (!result) {
						homeMsgList.add("Small-FontSize option is not visible.");
						return result;
					}

					cfObj.commonClick(cfObj.commonGetElement(driver, "Small", "name"));
				} else if (i == 1) {
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "Large", "name", 10);
					if (!result) {
						homeMsgList.add("Large-FontSize option is not visible.");
						return result;
					}

					cfObj.commonClick(cfObj.commonGetElement(driver, "Large", "name"));
				} else if (i == 2) {
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "Extra Large", "name", 10);
					if (!result) {
						homeMsgList.add("ExtraLarge-FontSize option is not visible.");
						return result;
					}

					cfObj.commonClick(cfObj.commonGetElement(driver, "Extra Large", "name"));
				} else if (i == 3) {
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "Medium (Default)", "name", 10);
					if (!result) {
						homeMsgList.add("Medium-FontSize option is not visible.");
						return result;
					}

					cfObj.commonClick(cfObj.commonGetElement(driver, "Medium (Default)", "name"));
				}
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//XCUIElementTypeOther[@name='Notes and Articles']/following-sibling::XCUIElementTypeOther",
						"xpath", 10);
				if (!result) {
					homeMsgList.add("Notes and Articles screen 3Dot icon is not visible.");
					return result;
				}
			}

			cfObj.scrollUtill(driver, 6, direction.DOWN);

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "XCUIElementTypeLink", "class", 10)) {
				cfObj.commonClick(cfObj.commonGetElements(driver, "XCUIElementTypeLink", "class").get(0));
				purchasePackageUtilObj = new PurchasePackageUtil(driver);
				result = purchasePackageUtilObj.verifyUnpurchasedPackagePage(driver);
				if (!result) {
					homeMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
					return result;
				}
				cfObj.commonClick(cfObj.commonGetElement(driver, "storeProductDetailBackItem", "name"));
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//XCUIElementTypeOther[@name='Notes and Articles']", "xpath", 10);
				if (!result) {
					homeMsgList.add("Failed to click UnPurchasePackage page back button.");
					return result;
				}
			} else {
				homeMsgList.add("Notes and Articles Link is not found");
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "XCUIElementTypeButton", "class"));
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//XCUIElementTypeOther[contains(@name,'Home')]", "xpath", 10);
			if (!result) {
				homeMsgList.add("Failed to click Notes and Articles Screen back button.");
				return result;
			}

		} catch (Exception e) {
			homeMsgList.add("validateNotesandArticlesTabForIOS_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyChangeExamContainerOnIOS(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			cfObj.commonClick(homePageObj.getChangeExamContainer());

			loginUtilObj = new LoginUtil(driver);
			result = cfObj.commonWaitForElementToBeVisible(driver, loginUtilObj.loginPageObj.getSelectExamPageTitle(),
					30);
			if (!result) {
				homeMsgList.add("Select exam page title is not visible in ios.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//XCUIElementTypeOther[contains(@name,'GOVERNMENT JOB EXAMS')]", "xpath", 10);
			if (!result) {
				homeMsgList.add("GOVERNMENT JOB EXAMS tab is not visible on SelectCategoryPage.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver,
					"//XCUIElementTypeOther[contains(@name,'GOVERNMENT JOB EXAMS')]", "xpath"));

			result = cfObj.commonWaitForElementToBeVisible(driver, loginUtilObj.loginPageObj.getSelectExamPageTitle(),
					30);
			if (!result) {
				homeMsgList.add("Select exam page title is not visible on ios.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//XCUIElementTypeOther[contains(@name,'Banking')]", "xpath", 10);
			if (!result) {
				homeMsgList.add("Banking Tab is not visible on SelectExamPage.");
				return result;
			}

			cfObj.commonClick(
					cfObj.commonGetElement(driver, "//XCUIElementTypeOther[contains(@name,'Banking')]", "xpath"));
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//XCUIElementTypeOther[@name='Banking']",
					"xpath", 10);
			if (!result) {
				homeMsgList.add("Failed to click Banking Tab on SelectExamPage.");
				return result;
			}

		} catch (Exception e) {
			homeMsgList.add("verifyChangeExamContainerOnIOS_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyChangeLanguageIconOnIOS(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//XCUIElementTypeOther[@name='Banking']/following-sibling::XCUIElementTypeImage", "xpath", 10);
			if (!result) {
				homeMsgList.add("Language Change icon is not visible on home page.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver,
					"//XCUIElementTypeOther[@name='Banking']/following-sibling::XCUIElementTypeImage", "xpath"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "Select Language", "name", 10);
			if (!result) {
				homeMsgList.add("Language Change Page title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//XCUIElementTypeButton[contains(@name,'English')]", "xpath", 10);
			if (!result) {
				homeMsgList.add("English Language tab is not visible on LanguageChangePage.");
				return result;
			}

			cfObj.commonClick(
					cfObj.commonGetElement(driver, "//XCUIElementTypeButton[contains(@name,'English')]", "xpath"));
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//XCUIElementTypeOther[@name='Banking']/following-sibling::XCUIElementTypeImage", "xpath", 10);
			if (!result) {
				homeMsgList.add("Failed to click English Language tab on Language Change Page.");
				return result;
			}

		} catch (Exception e) {
			homeMsgList.add("verifyChangeLanguageIconOnIOS_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyAndCickOnParticipateAndWin(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getTitleParticipateAndWin(), 10);
			if (!result) {
				homeMsgList.add("Unable to locate participant and win on homepage");
				return result;
			}

			cfObj.commonClick(homePageObj.getTitleParticipateAndWin());

		} catch (Exception e) {
			homeMsgList.add("clickOnPostCommentBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyHome_NavigationDrawer(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnHome(), 5);
			if (!result) {
				homeMsgList.add("getBtnHome not visible, as might be nav drawer is open.");
				result = true;

			} else {
				cfObj.commonClick(homePageObj.getBtnHome());

				result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnNavigationDrawer(), 5);
				if (!result) {
					homeMsgList.add("Navigation drawer not visible, might be not on home");
					return result;
				}
				cfObj.commonClick(homePageObj.getBtnNavigationDrawer());
			}
		} catch (Exception e) {
			homeMsgList.add("verifyHome_NavigationDrawer_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean switchOnNightModeToggleBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getNightModeOnOffToggle(), 30);
			if (!result) {
				homeMsgList.add("NightModeOnOffToggle button is not visible.");
				return result;
			}

			cfObj.commonClick(homePageObj.getNightModeOnOffToggle());

			Thread.sleep(1000);

			result = cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnHome(), 10);
			if (!result) {
				homeMsgList.add("getBtnHome is not visible after night mode on");
				return result;
			}

			result = justOpenNavigationDrawer(driver);
			if (!result) {
				homeMsgList.add("Not able to navigate side drawer.");
				return result;
			}

			result = homePageObj.getNightModeOnOffToggle().getAttribute("checked").equals("true");
			if (!result) {
				homeMsgList.add("NightModeOnOff button is not clicked.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			homeMsgList.add("switchOnNightModeToggleBtn_Exception: " + e.getMessage());
		}
		return result;
	}
}
