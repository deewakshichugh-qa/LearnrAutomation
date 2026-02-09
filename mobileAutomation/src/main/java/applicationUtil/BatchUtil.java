package applicationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.openqa.selenium.support.PageFactory;

import apiUtill.PpcUtil;
import apiUtill.UserLoginUtil;
import applicationUtil.StorePageUtil.ProductType;
import applicationUtil.UserProfilePageUtil.ProfileType;
import util.ConfigFileReader;
import util.Common_Function.direction;
import util.Common_Function.key;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.BatchPage_OR;
import pojo.PackageDetailsAlreadyPurchased.ExamDataAlreadyPurchased;
import util.Common_Function;

public class BatchUtil {

	// temp JWT token for hamza006 email staging
	UserLoginUtil userLoginUtilObj;
	PpcUtil ppcUtilObj;
	FeedbackFormUtil feedbackFormUtilObj;
	MyContentUtil myContentUtilObj;
	OrderSuccessUtil orderSuccessUtilObj;
	RegisterNewUserUtil registrationUserUtilObj;
	UserDetailsLayerUtil userDetailsLayerUtilObj;
	PriceDetailsPageUtil priceDetailsUtilObj;
	PurchasePackageUtil purchasePackageUtil;
	LoginUtil loginUtilObj;
	StorePageUtil storePageUtil;
	HomePageUtil homePageUtil;
	ChildPackageUtil packageUtilObj;
	BatchPage_OR batchPageObj;
	FilterPageUtil filterPageUtilObj;
	ChildPackageUtil childPackageUtilObj;
	VideoCoursesPageUtil videoCoursesPageUtilObj;
	LiveClassesPageUtil liveClassesPageUtilObj;
	UserProfilePageUtil userProfilePageUtilObj;
	EditProfileUtil editProfileUtilObj;
	ConfigFileReader configFileReader = new ConfigFileReader();
	public ArrayList<String> batchMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();

	public BatchUtil(AppiumDriver<MobileElement> driver) {
		batchPageObj = new BatchPage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), batchPageObj);
	}

	public boolean verifyBatchNewPurchases(AppiumDriver<MobileElement> driver, String strTitleMahapack,
			String strMahapackId, String strTitleExamWithBatchFlow) {
		boolean result = true;
		int totalNoOfExams;
		ArrayList<String> examNames = new ArrayList<>();
		String userPhoneNo = Common_Function.randomPhoneNumber(10, "8");
		String strUsrToken = null;
		String newPwd = "123456789";

		try {
			loginUtilObj = new LoginUtil(driver);

			result = loginUtilObj.verifySignUp(driver, userPhoneNo, true);
			if (!result) {
				batchMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			homePageUtil = new HomePageUtil(driver);

			result = homePageUtil.navigateToUserProfilePage(driver);
			if (!result) {
				batchMsgList.addAll(homePageUtil.homeMsgList);
				return result;
			}

			userProfilePageUtilObj = new UserProfilePageUtil(driver);
			result = userProfilePageUtilObj.validateNewProfileScreen(driver, ProfileType.OWN);
			if (!result) {
				batchMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}

			result = userProfilePageUtilObj.clickOnProfileEditBtn(driver);
			if (!result) {
				batchMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
				return result;
			}
			editProfileUtilObj = new EditProfileUtil(driver);

			Thread.sleep(7000);
			result = editProfileUtilObj.updatePasswordForNewUser(driver, newPwd);
			if (!result) {
				batchMsgList.addAll(editProfileUtilObj.editProfileMsgList);
				return result;
			}
//			Thread.sleep(2000);
//			cfObj.scrollUtill(driver, 1, direction.DOWN);
//			result=editProfileUtilObj.clickOnSaveButtonNew(driver);
//			if(!result) {
//				batchMsgList.addAll(editProfileUtilObj.editProfileMsgList);
//				return result;
//			}
//
//			result = cfObj.pressAndroidKey(driver, key.BACK, 1);
//			if (!result) {
//				batchMsgList.add("Unable to press android back key 1 times");
//			}

			// Later will remove if part
			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@text='LOGIN']",
					"xpath", 10)) {
				cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.Button[@text='LOGIN']", "xpath"));
			}

			result = loginUtilObj.verifyLoginUsingEmailId(driver, loginUtilObj.getStrEmailId(), newPwd, false);
			if (!result) {
				batchMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			userLoginUtilObj = new UserLoginUtil();

			Thread.sleep(5000);

			strUsrToken = userLoginUtilObj.getUserToken(loginUtilObj.getStrEmailId(), newPwd);
			if (strUsrToken == null) {
				batchMsgList.add("User token is null");
				return false;
			}

			result = homePageUtil.clickStoreBtn(driver);
			if (!result) {
				batchMsgList.add("Unable to open store btn");
				return result;
			}

			storePageUtil = new StorePageUtil(driver);

			result = storePageUtil.purchasePackage(driver, strTitleMahapack, ProductType.ALL);
			if (!result) {
				batchMsgList.addAll(storePageUtil.storePageMsgList);
				batchMsgList.add("Unable to purchase package");
				return result;
			}

			ppcUtilObj = new PpcUtil();

			totalNoOfExams = ppcUtilObj.getTotalExamCountNewPurchased(strUsrToken, strMahapackId);
			if (totalNoOfExams < 20) {
				throw new Exception("The Mahapack does not contain more than 20 exams.");
			}

			result = cfObj.pressAndroidKey(driver, key.BACK, 3);
			if (!result) {
				batchMsgList.add("Unable to press android back key 3 times");
			}

			result = homePageUtil.clickMyContentButton(driver);
			if (!result) {
				batchMsgList.add("Unable to move to my content page");
				return result;
			}

			myContentUtilObj = new MyContentUtil(driver);

			result = myContentUtilObj.clickOnPurchasedBtn(driver);
			if (!result) {
				batchMsgList.add("Unable to click Purchase button.");
				return result;
			}

			MobileElement purchasePackage = cfObj.commonGetElement(driver,
					"//*[contains(@content-desc,'" + strTitleMahapack + "')]", "xpath");
//					cfObj.getElementFromAttribute(driver, "content", strTitleMahapack);
			if (purchasePackage == null) {
				batchMsgList.add("Purchase package element is not present on DOM.");
				return false;
			}
			// Verifying the purchased section has purchased package
			if (cfObj.commonWaitForElementToBeVisible(driver, purchasePackage, 10)) {
				Thread.sleep(2000);
				cfObj.commonClick(purchasePackage);
				Thread.sleep(2000);
			} else {
				batchMsgList.add("The purchased section is empty");
				return result;
			}

			result = verifyChoosePriorityExamPage(driver);
			if (!result) {
				batchMsgList.add("Unable to verify choose priority exams");
				return result;
			}

			// Later will remove below if condition once issue got resolved.
			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "selectAllExamChkBox", "id", 10)) {
				cfObj.commonClick(cfObj.commonGetElement(driver, "selectAllExamChkBox", "id"));
				Thread.sleep(1000);
				cfObj.commonClick(cfObj.commonGetElement(driver, "selectAllExamChkBox", "id"));
				result = cfObj.commonGetElement(driver, "selectAllExamChkBox", "id").getAttribute("checked")
						.equalsIgnoreCase("false");
				if (!result) {
					cfObj.commonClick(cfObj.commonGetElement(driver, "selectAllExamChkBox", "id"));
				}
			}

			result = selectExamsInExamList(driver, 5, examNames);
			if (!result) {
				batchMsgList.add("Unable to select exams in exam list and proceed further");
				return result;
			}

			result = verifyExamsInPackagePage(driver, examNames);
			if (!result) {
				batchMsgList.add("Unable to verify exams in package page");
				return result;
			}

			if (!examNames.contains(strTitleExamWithBatchFlow)) {

				result = clickManageExamBtn(driver);
				if (!result) {
					batchMsgList.add("Unable to verify manage exam btn in package page");
					return result;
				}

				result = enterExamNameInSearchField(driver, strTitleExamWithBatchFlow);
				if (!result) {
					batchMsgList.add("Unable to enter exam name in search field in choose priority exam");
					return result;
				}

				result = selectExamNameFromSearchResult(driver, strTitleExamWithBatchFlow, examNames);
				if (!result) {
					batchMsgList.add("Unable to select exam name from search result in choose priority exam");
					return result;
				}

				result = verifyExamsInPackagePage(driver, examNames);
				if (!result) {
					batchMsgList.add("Unable to verify exams in package page");
					return result;
				}
			}

			result = clickOnExamName(driver, strTitleExamWithBatchFlow);
			if (!result) {
				batchMsgList.add("Unable to open exam page");
				return result;
			}

		} catch (Exception e) {
			batchMsgList.add("verifyBatchNewPurchases_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyBatchAlreadyPurchased(AppiumDriver<MobileElement> driver, String strTitleMahapack,
			String strMahapackId, String strTitleExamWithBatchFlow) {
		boolean result = true;
		String strUsrToken = null;
		List<String> ListExamName = new ArrayList<>();
		ExamDataAlreadyPurchased examDataObj = new ExamDataAlreadyPurchased();
		try {

			loginUtilObj = new LoginUtil(driver);

			result = loginUtilObj.verifyLoginGuestUser(driver);
			if (!result) {
				batchMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			homePageUtil = new HomePageUtil(driver);

			result = homePageUtil.clickMyContentButton(driver);
			if (!result) {
				batchMsgList.addAll(homePageUtil.homeMsgList);
				return result;
			}

			myContentUtilObj = new MyContentUtil(driver);

			result = myContentUtilObj.findAndOpenPackage(driver, strTitleMahapack);
			if (!result) {
				batchMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}

			Thread.sleep(2000);

			// Dealing with search course hint
			if (cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getBtnDoneCloseHint(), 5)) {
				cfObj.commonClick(batchPageObj.getBtnDoneCloseHint());

			} else {
				System.out.println("Search icon hint in package page didn't appeared");
			}

			userLoginUtilObj = new UserLoginUtil();

			strUsrToken = userLoginUtilObj.getUserToken(configFileReader.getUserNamePassword().split("/")[0].toString(),
					configFileReader.getUserNamePassword().split("/")[1].toString());
			if (strUsrToken == null) {
				batchMsgList.add("user token is null");
				return false;
			}
			System.out.println("From batch Util: " + strUsrToken);

			ppcUtilObj = new PpcUtil();

			// Getting selected exam list for the user from API
			ListExamName = ppcUtilObj.getListOfSelectedExamsAlreadyPurchased(strUsrToken, strMahapackId);
			if (ListExamName == null) {
				batchMsgList.add("Unable to get ListExamName from getListOfSelectedExamsAlreadyPurchased");
				return false;
			}

			result = verifyExamsInPackagePage(driver, ListExamName);
			if (!result) {
				batchMsgList.add("verifyExamsInPackagePage failed");
				return result;
			}

			// Getting exam with batch flow from API
			examDataObj = ppcUtilObj.getExamWithBatchFlow(strUsrToken, strMahapackId);
			if (examDataObj == null) {
				batchMsgList.add("examDataObj is null");
				return false;
			}

			result = clickOnExamName(driver, examDataObj.getName());
			if (!result) {
				batchMsgList.add("Unable to open exam page");
				return result;
			}

		} catch (Exception e) {
			batchMsgList.add("verifyBatchAlreadyPurchased_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickManageExamBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.commonClick(batchPageObj.getManageExamBtn());

			result = verifyChoosePriorityExamPage(driver);
			if (!result) {
				batchMsgList.add("Unable to verify ChoosePriorityExamPage.");
			}

		} catch (Exception e) {
			batchMsgList.add("clickManageExamBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean selectExamNameFromSearchResult(AppiumDriver<MobileElement> driver, String strTitleExam,
			List<String> examNames) {
		boolean result = false;
		int index = 0;
		try {

			List<MobileElement> el = batchPageObj.getListCheckBoxExams();

			while (!result) {
				if (el.get(index).getText().contains(strTitleExam)) {
					cfObj.commonClick(el.get(index));
					examNames.add(el.get(index).getText());
					result = true;
				} else {
					index++;
				}
			}

			cfObj.commonClick(batchPageObj.getBtnDone());

		} catch (Exception e) {
			batchMsgList.add("clickManageExamBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean enterExamNameInSearchField(AppiumDriver<MobileElement> driver, String strPackageExam) {
		boolean result = true;
		try {

			result = cfObj.commonSetTextTextBox(batchPageObj.getSearchField(), strPackageExam);
			if (!result) {
				batchMsgList.add("Unable to enter text on Search field.");
			}

		} catch (Exception e) {
			batchMsgList.add("clickManageExamBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyChoosePriorityExamPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.updateListSize(batchPageObj.getListCheckBoxExams());
			if (!result) {
				batchMsgList.add("The size of checkBoxExam list is zero");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getListCheckBoxExams().get(0), 10);
			if (!result) {
				batchMsgList.add("Unable to verify exam list check box in choose exam page");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getSearchField(), 10);
			if (!result) {
				batchMsgList.add("Unable to verify search field in choose exam page");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getBtnDone(), 10);
			if (!result) {
				batchMsgList.add("Unable to verify exam list check box in choose exam page");
			}

		} catch (Exception e) {
			batchMsgList.add("verifyChoosePriorityExam_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean selectExamsInExamList(AppiumDriver<MobileElement> driver, int noOfExams, List<String> examNames) {
		boolean result = true;
		try {

			result = cfObj.updateListSize(batchPageObj.getListCheckBoxExams());
			if (!result) {
				batchMsgList.add("The size of checkBoxExam list is zero");
			}

			for (int i = 0; i < noOfExams; i++) {
				cfObj.commonClick(batchPageObj.getListCheckBoxExams().get(i));
				examNames.add(batchPageObj.getListCheckBoxExams().get(i).getText());
			}

			for (int i = 0; i < examNames.size(); i++) {
				System.out.println(examNames.get(i) + "  ");
			}

			cfObj.commonClick(batchPageObj.getBtnDone());

			if (cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getBtnDoneCloseHint(), 10)) {
				cfObj.commonClick(batchPageObj.getBtnDoneCloseHint());

			} else {
				batchMsgList.add("Search icon hint in package page didn't appeared");
			}

		} catch (Exception e) {
			batchMsgList.add("chooseExamsInExamList_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyExamsInPackagePage(AppiumDriver<MobileElement> driver, List<String> examNames) {
		boolean result = true;
		try {

			for (int i = 0; i < examNames.size(); i++) {
				System.out.println(batchPageObj.getTextExamName().get(i).getText() + " == " + examNames.get(i));
				result = batchPageObj.getTextExamName().get(i).getText().contains(examNames.get(i));
				if (!result) {
					batchMsgList.add(examNames.get(i) + " : examname is not matching.");
				}
			}

		} catch (Exception e) {
			batchMsgList.add("verifyExamsInPackagePage_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyPackagePage(AppiumDriver<MobileElement> driver, String strPackageType, String strPackageName,
			String strPackageContent) {
		boolean result = true;
		try {
			if (strPackageType.contains("Multiple child multiple exams")) { // package has multiple child(more than 20)
				// and multiple exams(more than 1).
				result = verifyPackageWithMultipleChildMultipleExam(driver, strPackageName);
				if (!result) {
					batchMsgList.add("Not able to verify package with multiple child with multiple exam.");
				}
			} else if (strPackageType.contains("Multiple child single exams")) { // package has more than 1 child but 1
				// exam.
				result = verifyPackageMultipleChildSingleExams(driver, strPackageName);
				if (!result) {
					batchMsgList.add("Not able to verify package with multiple child with single exam.");
				}
			} else {
				result = verifyPackagePageWithSingleChildMultipleExam(driver, strPackageName, strPackageContent);// package
				if (!result) {
					batchMsgList.add("Not able to verify package with single child with multiple exam.");
				}
			}

		} catch (Exception e) {
			batchMsgList.add("verifyPPC_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyPackageWithMultipleChildMultipleExam(AppiumDriver<MobileElement> driver, String strPackage) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getTitlePage(), 10)
					|| batchPageObj.getTitlePage().getText().contains(strPackage);
			if (!result) {
				batchMsgList.add("Unable to verify page title in package page");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getIconSearch(), 10);
			if (!result) {
				batchMsgList.add("Unable to verify search icon in package page");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getManageExamBtn(), 10);
			if (!result) {
				batchMsgList.add("Unable to verify manage exam btn in packag page");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getIconExams().get(0), 10);
			if (!result) {
				batchMsgList.add("Unable to verify Exam icons in pacakge page");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getTextExamName().get(0), 10);
			if (!result) {
				batchMsgList.add("Unable to verify Exam names in pacakge page");
			}

		} catch (Exception e) {
			batchMsgList.add(
					"verifyPackayes BOSS\n" + "￼\n" + "geWithMultipleChildMultipleExam_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyPackagePageWithSingleChildMultipleExam(AppiumDriver<MobileElement> driver,
			String strPackageName, String strContent) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getIconShare(), 10);
			if (!result) {
				System.out.println("Unable to verify share icon in package page");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getBtnNavigateUp(), 10);
			if (!result) {
				batchMsgList.add("Unable to verify btn navigate up in package page");
			}

			if (strContent.equalsIgnoreCase("Test Series")) {

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/downloaded_status",
						"id", 10);
				if (!result) {
					batchMsgList.add("Unable to Get Quiz link in package page");
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"com.adda247.app:id/action_quiz_schedule", "id", 10);
				if (!result) {
					batchMsgList.add("Unable to Quiz schedule btn in package page");
				}

			} else if (strContent.equalsIgnoreCase("EBooks")) {

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/tv_download", "id",
						10);
				if (!result) {
					batchMsgList.add("Unable to download link in package page");
				}

			} else if (strContent.equalsIgnoreCase("Live Classes")) {

				// Unable to obtain screen

			} else if (strContent.equalsIgnoreCase("Video Courses")) {

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/subject_selector",
						"id", 10);
				if (!result) {
					batchMsgList.add("Unable to download link in package page");
				}
			}

		} catch (Exception e) {
			batchMsgList.add("verifyPackagePageWithSingleChildMultipleExam_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyPackageMultipleChildSingleExams(AppiumDriver<MobileElement> driver, String strPackage) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getBtnDoneCloseHint(), 10);
			if (!result) {
				batchMsgList.add("Unable to proceed to package page");
			} else {
				cfObj.commonClick(batchPageObj.getBtnDoneCloseHint());
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getTitlePage(), 10)
					|| batchPageObj.getTitlePage().getText().contains(strPackage);
			if (!result) {
				batchMsgList.add("Unable to verify page title in package page");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getSectionTestSeries(), 10);
			if (!result) {
				batchMsgList.add("Unable to verify Test series section in package page");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getSectionEBooks(), 10);
			if (!result) {
				batchMsgList.add("Unable to verify EBooks section in package page");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getSectionLiveClasses(), 10);
			if (!result) {
				batchMsgList.add("Unable to verify Live class section in package page");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getSectionVideoCourses(), 10);
			if (!result) {
				batchMsgList.add("Unable to verify Video courses section in package page");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getIconNotificaion().get(0), 10);
			if (!result) {
				batchMsgList.add("Unable to verify notification icon section in package page");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getIconSearch(), 10);
			if (!result) {
				batchMsgList.add("Unable to verify Search icon section in package page");
			}

		} catch (Exception e) {
			batchMsgList.add("verifyPackageMultipleChildSingleExams: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyViewAllCoursesManageExam(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			// Swotch on all course view
			result = clickManageExamBtn(driver);
			if (!result) {
				batchMsgList.add("Failed to click Manage Exam btn.");
				return result;
			}

			cfObj.commonClick(batchPageObj.getCheckBoxViewAllCourses());
			Thread.sleep(500);
			cfObj.commonClick(batchPageObj.getBtnDone());

			packageUtilObj = new ChildPackageUtil(driver);

			result = packageUtilObj.verifyMahpackPage(driver, true, true, false, true);
			if (!result) {
				batchMsgList.add("verifyViewAllCoursesManageExam: Mahpack page verification failed");
				return result;
			}

			// Switch off all course view
			result = clickManageExamBtn(driver);
			if (!result) {
				batchMsgList.add("verifyViewAllCoursesManageExam: Unable to swtich on exam mode");
				return result;
			}

			cfObj.commonClick(batchPageObj.getCheckBoxViewAllCourses());
			Thread.sleep(500);
			cfObj.commonClick(batchPageObj.getBtnDone());

		} catch (Exception e) {
			batchMsgList.add("verifySwitchExamMode_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnExamName(AppiumDriver<MobileElement> driver, String strExamName) {
		boolean result = true;
		try {

			MobileElement el = cfObj.commonGetElement(driver, "//*[contains(@text,'" + strExamName + "')]", "xpath");

			if (cfObj.commonWaitForElementToBeVisible(driver, el, 10)) {
				cfObj.commonClick(el);
			} else {
				batchMsgList.add("Unable to find exam name in mahapack page");
				result = false;
			}

		} catch (Exception e) {
			batchMsgList.add("clickOnExamName_Exception: " + e.getMessage());
			result = false;

		}
		return result;
	}

	public boolean verifyViewAllBatches(AppiumDriver<MobileElement> driver, int subParentCount) {
		boolean result = true;
		try {

			// verifying all batch pages from clicking more courses tile if available
			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'more courses')]", "xpath",
					2)) {

				MobileElement el = cfObj.getElementFromAttribute(driver, "text", "more courses");
				el.click();

				result = cfObj.updateListSize(batchPageObj.getListBatchCard());
				if (!result) {
					batchMsgList.add("Failed to update Batch Card size.");
					return result;
				}

				result = cfObj.pressAndroidKey(driver, key.BACK, 1);
				if (!result) {
					batchMsgList.add("Failed to press android back key.");
					return result;
				}
			}

			cfObj.commonClick(batchPageObj.getBtnViewAllBatches());

			result = cfObj.countElementsInList(driver, batchPageObj.getListTitlePackage(), subParentCount);
			if (!result) {
				batchMsgList.add("The actual count is not equal to expected count:  " + subParentCount);
				return result;
			}

			int scrollTime = subParentCount / 4;

			cfObj.scrollUtill(driver, scrollTime, direction.UP);
			/*
			 * //finding parent package in batch list int i =
			 * cfObj.findElementInList(driver, batchPageObj.getListTitlePackage(),
			 * strTitleParentPackage);
			 * cfObj.commonClick(batchPageObj.getListTitlePackage().get(i));
			 * 
			 * //finding child package inside parent package packageUtilObj = new
			 * ChildPackageUtil(driver); int j = cfObj.findElementInList(driver,
			 * packageUtilObj.packagePageObj.getListChildPackage(), strTitleChildPackage);
			 * cfObj.commonClick(packageUtilObj.packagePageObj.getListChildPackage().get(j))
			 * ;
			 */
		} catch (Exception e) {
			batchMsgList.add("verifyViewAllBatches_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyViewAllCourses(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		boolean liveClass, testSeries, videoCourse, EBooks = false;
		try {

			// checking availabilty of exam content on dispkay page
			liveClass = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getCountLiveClasses(), 10);
			if (!liveClass) {
				batchMsgList.add("LiveClass count is not present.");
				return liveClass;
			}
			testSeries = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getCountTestSeries(), 10);
			if (!testSeries) {
				batchMsgList.add("TestSeries Count is not present.");
				return testSeries;
			}
			videoCourse = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getCountVIdeoCourses(), 10);
			if (!videoCourse) {
				batchMsgList.add("VideoCourse Count is not present.");
				return videoCourse;
			}
			EBooks = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getCountEBooks(), 10);
			if (!EBooks) {
				batchMsgList.add("EBook Count is not present.");
				return EBooks;
			}

			cfObj.commonClick(batchPageObj.getBtnViewAllCourses());

			packageUtilObj = new ChildPackageUtil(driver);

			// verifying in all courses page
			result = packageUtilObj.verifyMahpackPage(driver, liveClass, testSeries, videoCourse, EBooks);
			if (!result) {
				batchMsgList.add("Failed to verify MahaPack..");
			}

		} catch (Exception e) {
			batchMsgList.add("verifyViewAllCourses_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean searchAndOpenChildPackage(AppiumDriver<MobileElement> driver, String strTitlePackage) {
		boolean result = true;
		try {
			// Swotch on all course view
			result = clickManageExamBtn(driver);
			if (!result) {
				batchMsgList.add("Failed to click Manage Exam button.");
				return result;
			}

			cfObj.commonClick(batchPageObj.getCheckBoxViewAllCourses());
			Thread.sleep(1000);
			cfObj.commonClick(batchPageObj.getBtnDone());

			// searching package
			cfObj.commonClick(batchPageObj.getIconSearch());
			result = cfObj.commonSetTextTextBox(batchPageObj.getSearchField(), strTitlePackage);
			if (!result) {
				batchMsgList.add("Failed to Enter text on Search Field.");
				return result;
			}
			// Opening package
			cfObj.commonClick(cfObj.getElementFromAttribute(driver, "text", strTitlePackage));

		} catch (Exception e) {
			batchMsgList.add("searchAndOpenChildPackage_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnDoneBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			if (cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getDoneBtn(), 30)) {
				cfObj.commonClick(batchPageObj.getDoneBtn());
			} else {
				System.out.println("Done button is not visible");
			}
		} catch (Exception e) {
			result = false;
			batchMsgList.add("ClickOnDoneBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateCertificateTagInsideTestSeriesChildPackage(AppiumDriver<MobileElement> driver, int index) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getPackageDescription().get(index), 30);
			if (!result) {
				batchMsgList.add("PackageDescription is not visible.");
				return result;
			}

			cfObj.commonClick(batchPageObj.getPackageDescription().get(index));
			Thread.sleep(2000);
			childPackageUtilObj = new ChildPackageUtil(driver);
			result = childPackageUtilObj.validateTestTagCount(driver);
			if (!result) {
				batchMsgList.addAll(childPackageUtilObj.packagePageMsgList);
				return result;
			}
			Thread.sleep(2000);
			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getPackageDescription().get(index), 30);
			if (!result) {
				batchMsgList.add("PackageDescription is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			batchMsgList.add("validateCertificateTagInsideTestSeriesChildPackage_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnSubjectTitle(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getPackageDescription().get(0), 30);
			if (!result) {
				batchMsgList.add("PackageDescription is not visible.");
				return result;
			}
			cfObj.commonClick(batchPageObj.getPackageDescription().get(0));

		} catch (Exception e) {
			result = false;
			batchMsgList.add("clickOnSubjectTitle_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnSpecificSubjectTitle(AppiumDriver<MobileElement> driver, String subjectTitle) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='" + subjectTitle + "']",
					"xpath", 30);
			if (!result) {
				batchMsgList.add("PackageDescription is not visible.");
				return result;
			}
			cfObj.commonClick(driver, "//*[@text='" + subjectTitle + "']", "xpath");

		} catch (Exception e) {
			result = false;
			batchMsgList.add("clickOnSpecificSubjectTitle_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateCertificateTagInsideVideoCourseChildPackage(AppiumDriver<MobileElement> driver, int index) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getPackageDescription().get(index), 30);
			if (!result) {
				batchMsgList.add("PackageDescription is not visible.");
				return result;
			}

			cfObj.commonClick(batchPageObj.getPackageDescription().get(index));
			Thread.sleep(2000);
			videoCoursesPageUtilObj = new VideoCoursesPageUtil(driver);
			result = videoCoursesPageUtilObj.clickOnPurchasedVideo(driver);
			if (!result) {
				batchMsgList.addAll(videoCoursesPageUtilObj.videoSectionMsgList);
				return result;
			}

			result = videoCoursesPageUtilObj.clickOnGotItBtn(driver);
			if (!result) {
				batchMsgList.addAll(videoCoursesPageUtilObj.videoSectionMsgList);
				return result;
			}

			result = videoCoursesPageUtilObj.validateVideosTagCount(driver);
			if (!result) {
				batchMsgList.addAll(videoCoursesPageUtilObj.videoSectionMsgList);
				return result;
			}

			Thread.sleep(2000);
			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getPackageDescription().get(index), 30);
			if (!result) {
				batchMsgList.add("PackageDescription is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			batchMsgList.add("validateCertificateTagInsideTestSeriesChildPackage_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean selectSpecificTab(AppiumDriver<MobileElement> driver, MobileElement element) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, element, 30);
			if (!result) {
				batchMsgList.add("Selected section is not visible.");
				return result;
			}

			cfObj.commonClick(element);

			result = element.getAttribute("selected").equalsIgnoreCase("true");
			if (!result) {
				batchMsgList.add("Section is not Selected.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getPackageDescription().get(0), 30);
			if (!result) {
				batchMsgList.add("PackageDescription is not visible.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			batchMsgList.add("selectSpecificTab_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyBatchView(AppiumDriver<MobileElement> driver, String strTitleMahapack) {
		boolean result = true;
		try {

			loginUtilObj = new LoginUtil(driver);

			result = loginUtilObj.selectYourCategoryExamLanguage(driver);
			if (!result) {
				batchMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}
			result = loginUtilObj.verifyLoginUsingEmailId(driver, "up8111@gmail.com", "12345678", false);
			if (!result) {
				batchMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			homePageUtil = new HomePageUtil(driver);

			result = homePageUtil.clickMyContentButton(driver);
			if (!result) {
				batchMsgList.addAll(homePageUtil.homeMsgList);
				return result;
			}

			myContentUtilObj = new MyContentUtil(driver);
			result = myContentUtilObj.clickOnPurchasedBtn(driver);
			if (!result) {
				batchMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}

			result = myContentUtilObj.clickOnSpecificPurchasedPackage(driver, strTitleMahapack);
			if (!result) {
				batchMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}
			Thread.sleep(2000);
			result = cfObj.handleHints(driver);
			if (!result) {
				batchMsgList.add("Not able to handle Exam Instruction popup.");
				return result;
			}

			result = validateBatchViewUI(driver, strTitleMahapack);
			if (!result) {
				batchMsgList.add("Not able to validate BatchViewUI.");
				return result;
			}

			result = selectEaxmNameInExamList(driver);
			if (!result) {
				batchMsgList.add("Not able to select EaxmNameInExamList.");
				return result;
			}

			String examName = batchPageObj.getExamNameList().get(0).getText();
			if (examName == null) {
				batchMsgList.add("ExamName text is null.");
				return result;
			}

			result = selectSpecificEaxmNameInExamList(driver, examName);
			if (!result) {
				batchMsgList.add("Not able to select Specific EaxmNameInExamList.");
				return result;
			}

			result = validateViewAllSubjectBtn(driver);
			if (!result) {
				batchMsgList.add("Not able to validate ViewAllSubjectBtn.");
				return result;
			}
			cfObj.commonClick(batchPageObj.getBatchMahaPackPageBackBtn());

			result = validateViewAllCourseBtn(driver);
			if (!result) {
				batchMsgList.add("Not able to validate ViewAllCourseBtn.");
				return result;
			}

			result = clickOnBatchCard(driver);
			if (!result) {
				batchMsgList.add("Not able to validate ViewAllCourseBtn.");
				return result;
			}

			cfObj.commonClick(batchPageObj.getBatchMahaPackPageBackBtn());
			Thread.sleep(1000);
			cfObj.commonClick(batchPageObj.getBatchMahaPackPageBackBtn());
			result = clickOnSearchIcon(driver);
			if (!result) {
				batchMsgList.add("Not able to click SearchIcon.");
				return result;
			}

			result = validateFilterBtn(driver);
			if (!result) {
				batchMsgList.add("Not able to validate FilterBtn.");
				return result;
			}
			cfObj.commonClick(batchPageObj.getSearchFieldClearBtn());

			result = cfObj.commonVerifyValueTextBox(batchPageObj.getSearchTextField(), "Find Subscribed Courses");
			if (!result) {
				batchMsgList.add("SearchFieldClearBtn is not clickable.");
				return result;
			}

		} catch (Exception e) {
			batchMsgList.add("verifyBatchView_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	private boolean validateViewAllSubjectBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getBtnViewAllBatches(), 10);
			if (!result) {
				batchMsgList.add("ViewAllBatch btn is not visible.");
				return result;
			}

			cfObj.commonClick(batchPageObj.getBtnViewAllBatches());

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getListBatchCard().get(0), 10);
			if (!result) {
				batchMsgList.add("BatchCard is not visible.");
				return result;
			}

			TreeSet<String> contentName = new TreeSet<>();
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < batchPageObj.getListBatchCard().size(); j++) {
					contentName.add(cfObj.commonGetElements(driver,
							"//*[@resource-id='com.sankalpbharat.app:id/batch_card']/child::*[@resource-id='com.sankalpbharat.app:id/title']",
							"xpath").get(j).getText());
				}
				cfObj.scrollUtill(driver, 1, direction.DOWN);
			}
			cfObj.scrollUtill(driver, 2, direction.UP);

			ArrayList<String> contentText = new ArrayList<>(contentName);
			System.out.println(contentText);

			for (int i = 0; i < contentText.size(); i++) {
				String contentTextName = contentText.get(i);

				boolean contentStatus = false;
				if (contentTextName.contains("Test Series")) {
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
							"//*[@text='" + contentTextName + "']", "xpath", 10);
					if (!result) {
						batchMsgList.add(contentTextName + " BatchCard is not visible.");
						return result;
					}
					cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='" + contentTextName + "']", "xpath"));
					result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getBatchTabList().get(0), 10);
					if (!result) {
						batchMsgList.add("BatchTabList is not visible.");
						return result;
					}
					result = validateTestSeriesTab(driver);
					if (!result) {
						batchMsgList.add("Not able to validate TestSeriesTab.");
						return result;
					}
					contentStatus = true;
				} else if (contentTextName.contains("DPP & EBooks")) {
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
							"//*[@text='" + contentTextName + "']", "xpath", 10);
					if (!result) {
						batchMsgList.add(contentTextName + " BatchCard is not visible.");
						return result;
					}
					cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='" + contentTextName + "']", "xpath"));
					result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getBatchTabList().get(0), 10);
					if (!result) {
						batchMsgList.add("BatchTabList is not visible.");
						return result;
					}
					result = validateEBookTab(driver);
					if (!result) {
						batchMsgList.add("Not able to validate EBook.");
						return result;
					}
					contentStatus = true;
				} else if (contentTextName.contains("Live Classes")) {
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
							"//*[@text='" + contentTextName + "']", "xpath", 10);
					if (!result) {
						batchMsgList.add(contentTextName + " BatchCard is not visible.");
						return result;
					}
					cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='" + contentTextName + "']", "xpath"));
					result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getBatchTabList().get(0), 10);
					if (!result) {
						batchMsgList.add("BatchTabList is not visible.");
						return result;
					}
					result = validateLiveClassTab(driver);
					if (!result) {
						batchMsgList.add("Not able to validate LiveClass.");
						return result;
					}
					contentStatus = true;

				} else if (contentTextName.contains("Video Courses")) {
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
							"//*[@text='" + contentTextName + "']", "xpath", 10);
					if (!result) {
						batchMsgList.add(contentTextName + " BatchCard is not visible.");
						return result;
					}
					cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='" + contentTextName + "']", "xpath"));
					result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getBatchTabList().get(0), 10);
					if (!result) {
						batchMsgList.add("BatchTabList is not visible.");
						return result;
					}
					result = validateVideoCourseTab(driver);
					if (!result) {
						batchMsgList.add("Not able to validate VideoCourse.");
						return result;
					}
					contentStatus = true;
				}
				if (contentStatus == true) {
					cfObj.commonClick(batchPageObj.getBatchMahaPackPageBackBtn());
					Thread.sleep(2000);
				}
			}
		} catch (Exception e) {
			batchMsgList.add("validateViewAllSubjectBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	private boolean validateVideoCourseTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String description = null;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getSectionVideoCourses(), 10);
			if (!result) {
				batchMsgList.add("VideoCourse section  is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getPackageDescription().get(0), 10);
			if (!result) {
				batchMsgList.add("Package Description  is not visible.");
				return result;
			}
			description = batchPageObj.getPackageDescription().get(0).getText();
			cfObj.commonClick(batchPageObj.getPackageDescription().get(0));
			videoCoursesPageUtilObj = new VideoCoursesPageUtil(driver);
			result = videoCoursesPageUtilObj.clickOnPurchasedVideo(driver);
			if (!result) {
				batchMsgList.addAll(videoCoursesPageUtilObj.videoSectionMsgList);
				return result;
			}
			result = videoCoursesPageUtilObj.clickOnGotItBtn(driver);
			if (!result) {
				batchMsgList.addAll(videoCoursesPageUtilObj.videoSectionMsgList);
				return result;
			}
			result = cfObj.commonWaitForElementToBeVisible(driver,
					videoCoursesPageUtilObj.videoCoursesPageObj.getPlayerViewState(), 10);
			if (!result) {
				batchMsgList.add("Player view btn is not visible.");
				return result;
			}
			result = cfObj.pressAndroidKey(driver, key.BACK, 2);
			if (!result) {
				batchMsgList.add("Not able to click back button 2 times.");
				return result;
			}

			result = validateSearchIcon(driver, description, "VideoCourse");
			if (!result) {
				batchMsgList.add("Not able to validate SearchIcon.");
				return result;
			}

			cfObj.commonClick(batchPageObj.getSearchScreenBackBtn());

		} catch (Exception e) {
			batchMsgList.add("validateVideoCourseTab_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	private boolean validateLiveClassTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String description = null;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getSectionLiveClasses(), 10);
			if (!result) {
				batchMsgList.add("LiveClass section  is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getPackageDescription().get(0), 10);
			if (!result) {
				batchMsgList.add("Package Description  is not visible.");
				return result;
			}
			description = batchPageObj.getPackageDescription().get(0).getText();
			cfObj.commonClick(batchPageObj.getPackageDescription().get(0));
			liveClassesPageUtilObj = new LiveClassesPageUtil(driver);
			cfObj.waitForPageLoading(driver, 10, 2000, cfObj.commonGetElement(driver, "progressBar", "id"));
			result = liveClassesPageUtilObj.handledNotificationPopUp(driver);
			if (!result) {
				batchMsgList.addAll(liveClassesPageUtilObj.liveClassMsgList);
				return result;
			}
			result = liveClassesPageUtilObj.clickOnGotItBtn(driver);
			if (!result) {
				batchMsgList.addAll(liveClassesPageUtilObj.liveClassMsgList);
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='Notify Me']", "xpath", 10);
			if (!result) {
				batchMsgList.add("Failed to open Live Class screen.");
				return result;
			}

			result = cfObj.pressAndroidKey(driver, key.BACK, 1);
			if (!result) {
				batchMsgList.add("Not able to click back button.");
				return result;
			}
			result = validateSearchIcon(driver, description, "LiveClass");
			if (!result) {
				batchMsgList.add("Not able to validate SearchIcon.");
				return result;
			}

			cfObj.commonClick(batchPageObj.getSearchScreenBackBtn());

		} catch (Exception e) {
			batchMsgList.add("validateLiveClassTab_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	private boolean validateEBookTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String description = null;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getSectionEBooks(), 10);
			if (!result) {
				batchMsgList.add("EBook section  is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getPackageDescription().get(0), 10);
			if (!result) {
				batchMsgList.add("Package Description  is not visible.");
				return result;
			}
			description = batchPageObj.getPackageDescription().get(0).getText();
			cfObj.commonClick(batchPageObj.getPackageDescription().get(0));
			myContentUtilObj = new MyContentUtil(driver);
			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "emptyViewMessageTitle", "id", 5)) {
				System.out.println("Ebook content is not available.");
			} else {
				result = myContentUtilObj.validateEBookScreenUI(driver);
				if (!result) {
					batchMsgList.addAll(myContentUtilObj.myContentMsgList);
					return result;
				}
			}

			result = cfObj.pressAndroidKey(driver, key.BACK, 1);
			if (!result) {
				batchMsgList.add("Not able to click back button.");
				return result;
			}

			result = validateSearchIcon(driver, description, "EBook");
			if (!result) {
				batchMsgList.add("Not able to validate SearchIcon.");
				return result;
			}

			cfObj.commonClick(batchPageObj.getSearchScreenBackBtn());

		} catch (Exception e) {
			batchMsgList.add("validateEBookTab_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	private boolean validateTestSeriesTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String description = null;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getSectionTestSeries(), 10);
			if (!result) {
				batchMsgList.add("TestSeries section  is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getPackageDescription().get(0), 10);
			if (!result) {
				batchMsgList.add("Package Description  is not visible.");
				return result;
			}
			description = batchPageObj.getPackageDescription().get(0).getText();
			cfObj.commonClick(batchPageObj.getPackageDescription().get(0));
			childPackageUtilObj = new ChildPackageUtil(driver);
			result = cfObj.commonWaitForElementToBeVisible(driver,
					childPackageUtilObj.packagePageObj.getTitleQuiz().get(0), 10);
			if (!result) {
				batchMsgList.add("Unable to verify share icon");
				return result;
			}

			cfObj.commonClick(childPackageUtilObj.packagePageObj.getBtnNavigateUp());

			result = validateSearchIcon(driver, description, "TestSeries");
			if (!result) {
				batchMsgList.add("Not able to validate SearchIcon.");
				return result;
			}

			cfObj.commonClick(batchPageObj.getSearchScreenBackBtn());

		} catch (Exception e) {
			batchMsgList.add("validateTestSeriesTab_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	private boolean validateSearchIcon(AppiumDriver<MobileElement> driver, String description, String packageType) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getIconSearch(), 10);
			if (!result) {
				batchMsgList.add("Search icon is not visible.");
				return result;
			}
			cfObj.commonClick(batchPageObj.getIconSearch());
			System.out.println(description);
			result = cfObj.commonSetTextTextBox(batchPageObj.getSearchTextField(), description);
			if (!result) {
				batchMsgList.add("Not able to enter subject in Search field.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getFilterBtn(), 30);
			if (!result) {
				batchMsgList.add("Filter btn is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getSearchFieldClearBtn(), 30);
			if (!result) {
				batchMsgList.add("SearchFieldClear btn is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.TextView[contains(@text,'" + description + "')]", "xpath", 10);
			if (!result) {
				batchMsgList.add("SearchedText and Result test both are different.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver,
					"//android.widget.TextView[contains(@text,'" + description + "')]", "xpath"));

			switch (packageType) {
			case "TestSeries":
				childPackageUtilObj = new ChildPackageUtil(driver);
				result = cfObj.commonWaitForElementToBeVisible(driver,
						childPackageUtilObj.packagePageObj.getTitleQuiz().get(0), 10);
				if (!result) {
					batchMsgList.add("Daily Quiz title is not visible.");
					return result;
				}

				cfObj.commonClick(childPackageUtilObj.packagePageObj.getBtnNavigateUp());

				break;

			case "EBook":
				myContentUtilObj = new MyContentUtil(driver);
				if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "emptyViewMessageTitle", "id", 5)) {
					System.out.println("Ebook content is not available.");
				} else {
					result = myContentUtilObj.validateEBookScreenUI(driver);
					if (!result) {
						batchMsgList.addAll(myContentUtilObj.myContentMsgList);
						return result;
					}
				}
				result = cfObj.pressAndroidKey(driver, key.BACK, 1);
				break;

			case "LiveClass":
				liveClassesPageUtilObj = new LiveClassesPageUtil(driver);
				result = liveClassesPageUtilObj.handledNotificationPopUp(driver);
				if (!result) {
					batchMsgList.addAll(liveClassesPageUtilObj.liveClassMsgList);
					return result;
				}
				result = liveClassesPageUtilObj.clickOnGotItBtn(driver);
				if (!result) {
					batchMsgList.addAll(liveClassesPageUtilObj.liveClassMsgList);
					return result;
				}
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='Notify Me']", "xpath", 10);
				if (!result) {
					batchMsgList.add("Failed to open Live Class screen.");
					return result;
				}

				result = cfObj.pressAndroidKey(driver, key.BACK, 1);
				if (!result) {
					batchMsgList.add("Not able to click back button.");
					return result;
				}
				break;

			case "VideoCourse":
				videoCoursesPageUtilObj = new VideoCoursesPageUtil(driver);
				result = videoCoursesPageUtilObj.clickOnPurchasedVideo(driver);
				if (!result) {
					batchMsgList.addAll(videoCoursesPageUtilObj.videoSectionMsgList);
					return result;
				}
				result = videoCoursesPageUtilObj.clickOnGotItBtn(driver);
				if (!result) {
					batchMsgList.addAll(videoCoursesPageUtilObj.videoSectionMsgList);
					return result;
				}
				result = cfObj.commonWaitForElementToBeVisible(driver,
						videoCoursesPageUtilObj.videoCoursesPageObj.getPlayerViewState(), 10);
				if (!result) {
					batchMsgList.add("Player view btn is not visible.");
					return result;
				}

				result = cfObj.pressAndroidKey(driver, key.BACK, 2);
				if (!result) {
					batchMsgList.add("Not able to click back button 2 times.");
					return result;
				}
				break;
			}
		} catch (Exception e) {
			batchMsgList.add("validateSearchIcon_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean validateBatchViewUI(AppiumDriver<MobileElement> driver, String mahaPackName) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getBatchMahaPackPageTitle(), 10);
			if (!result) {
				batchMsgList.add("MahaPack Title is not visible.");
				return result;
			}

			result = cfObj.commonVerifyValueTextBox(batchPageObj.getBatchMahaPackPageTitle(), mahaPackName);
			if (!result) {
				batchMsgList.add("MahaPack Title is not Matching.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getIconSearch(), 10);
			if (!result) {
				batchMsgList.add("Search icon is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getBatchMahaPackPageBackBtn(), 10);
			if (!result) {
				batchMsgList.add("BatchMahaPackPageBackBtn is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getYoursExamTitle(), 10);
			if (!result) {
				batchMsgList.add("YoursExamTitle is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getExamNameList().get(0), 10);
			if (!result) {
				batchMsgList.add("ExamNameList is not visible.");
				return result;
			}

		} catch (Exception e) {
			batchMsgList.add("validateBatchViewUI_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean selectEaxmNameInExamList(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getExamNameList().get(0), 10);
			if (!result) {
				batchMsgList.add("ExamNameList is not visible.");
				return result;
			}
			int examList = batchPageObj.getExamNameList().size();
			for (int i = 0; i < examList; i++) {
				result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getExamNameList().get(i), 10);
				if (!result) {
					batchMsgList.add("ExamNameList is not visible.");
					return result;
				}
				cfObj.commonClick(batchPageObj.getExamNameList().get(i));
				result = validateSelectedExamPageUI(driver);
				if (!result) {
					batchMsgList.add("Not able to validate SelectedExamPageUI.");
					return result;
				}
				cfObj.commonClick(batchPageObj.getBatchMahaPackPageBackBtn());
			}

		} catch (Exception e) {
			batchMsgList.add("selectEaxmNameInExamList_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	private boolean validateSelectedExamPageUI(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getExamPageTitle(), 10);
			if (!result) {
				batchMsgList.add("ExamPageTitle is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getBtnViewAllBatches(), 10);
			if (!result) {
				batchMsgList.add("ViewAllBatch btn is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getBtnViewAllCourses(), 10);
			if (!result) {
				batchMsgList.add("ViewAllCourse btn is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getBatchTitle(), 10);
			if (!result) {
				batchMsgList.add("BatchTitle is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getCourseTitle(), 10);
			if (!result) {
				batchMsgList.add("CourseTitle is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getSubjectCardList().get(0), 10);
			if (!result) {
				batchMsgList.add("SubjectCardList is not visible.");
				return result;
			}

		} catch (Exception e) {
			batchMsgList.add("validateSelectedExamPageUI_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean selectSpecificEaxmNameInExamList(AppiumDriver<MobileElement> driver, String examName) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='" + examName + "']", "xpath",
					10);
			if (!result) {
				batchMsgList.add("ExamNameList is not visible.");
				return result;
			}
			cfObj.commonClick(driver, "//*[@text='" + examName + "']", "xpath");
			result = validateSelectedExamPageUI(driver);
			if (!result) {
				batchMsgList.add("Not able to validate SelectedExamPageUI.");
				return result;
			}

		} catch (Exception e) {
			batchMsgList.add("selectSpecificEaxmNameInExamList_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	private boolean validateViewAllCourseBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getBtnViewAllCourses(), 10);
			if (!result) {
				batchMsgList.add("ViewAllCourse btn is not visible.");
				return result;
			}

			cfObj.commonClick(batchPageObj.getBtnViewAllCourses());

			for (int i = 0; i < batchPageObj.getBatchTabList().size(); i++) {

				result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getBatchTabList().get(i), 10);
				if (!result) {
					batchMsgList.add("BatchTab is not visible.");
					return result;
				}

				switch (batchPageObj.getBatchTabList().get(i).getText()) {
				case "Test Series":
					cfObj.commonClick(batchPageObj.getBatchTabList().get(i));

					result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getSectionTestSeries(), 10);
					if (!result) {
						batchMsgList.add("TestSeries section  is not visible.");
						return result;
					}

					result = batchPageObj.getSectionTestSeries().getAttribute("selected").equalsIgnoreCase("true");
					if (!result) {
						batchMsgList.add("TestSeries section  is not Selected.");
						return result;
					}

					result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getPackageDescription().get(0),
							10);
					if (!result) {
						batchMsgList.add("Package Description  is not visible.");
						return result;
					}

					cfObj.commonClick(batchPageObj.getPackageDescription().get(0));
					childPackageUtilObj = new ChildPackageUtil(driver);
					result = cfObj.commonWaitForElementToBeVisible(driver,
							childPackageUtilObj.packagePageObj.getTitleQuiz().get(0), 10);
					if (!result) {
						batchMsgList.add("Daily Quiz title is not visible.");
						return result;
					}

					cfObj.commonClick(childPackageUtilObj.packagePageObj.getBtnNavigateUp());

					break;

				case "DPP & EBooks":
					cfObj.commonClick(batchPageObj.getBatchTabList().get(i));
					result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getSectionEBooks(), 10);
					if (!result) {
						batchMsgList.add("EBook section  is not visible.");
						return result;
					}

					result = batchPageObj.getSectionEBooks().getAttribute("selected").equalsIgnoreCase("true");
					if (!result) {
						batchMsgList.add("EBook section  is not Selected.");
						return result;
					}

					result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getPackageDescription().get(0),
							10);
					if (!result) {
						batchMsgList.add("Package Description  is not visible.");
						return result;
					}
					cfObj.commonClick(batchPageObj.getPackageDescription().get(0));
					myContentUtilObj = new MyContentUtil(driver);
					if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "emptyViewMessageTitle", "id", 5)) {
						System.out.println("Ebook content is not available.");
					} else {
						result = myContentUtilObj.validateEBookScreenUI(driver);
						if (!result) {
							batchMsgList.addAll(myContentUtilObj.myContentMsgList);
							return result;
						}
					}
					result = cfObj.pressAndroidKey(driver, key.BACK, 1);
					if (!result) {
						batchMsgList.add("Not able to click back button.");
						return result;
					}
					break;
				case "Live Classes":
					cfObj.commonClick(batchPageObj.getBatchTabList().get(i));
					result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getSectionLiveClasses(), 10);
					if (!result) {
						batchMsgList.add("LiveClass section  is not visible.");
						return result;
					}

					result = batchPageObj.getSectionLiveClasses().getAttribute("selected").equalsIgnoreCase("true");
					if (!result) {
						batchMsgList.add("LiveClass section  is not Selected.");
						return result;
					}

					result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getPackageDescription().get(0),
							10);
					if (!result) {
						batchMsgList.add("Package Description  is not visible.");
						return result;
					}
					cfObj.commonClick(batchPageObj.getPackageDescription().get(0));
					liveClassesPageUtilObj = new LiveClassesPageUtil(driver);
					result = liveClassesPageUtilObj.handledNotificationPopUp(driver);
					if (!result) {
						batchMsgList.addAll(liveClassesPageUtilObj.liveClassMsgList);
						return result;
					}
					result = liveClassesPageUtilObj.clickOnGotItBtn(driver);
					if (!result) {
						batchMsgList.addAll(liveClassesPageUtilObj.liveClassMsgList);
						return result;
					}

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='Notify Me']", "xpath",
							10);
					if (!result) {
						batchMsgList.add("Failed to open Live Class screen.");
						return result;
					}

					result = cfObj.pressAndroidKey(driver, key.BACK, 1);
					if (!result) {
						batchMsgList.add("Not able to click back button.");
						return result;
					}
					break;

				case "Video Courses":
					cfObj.commonClick(batchPageObj.getBatchTabList().get(i));

					result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getSectionVideoCourses(), 10);
					if (!result) {
						batchMsgList.add("VideoCourse section  is not visible.");
						return result;
					}

					result = batchPageObj.getSectionVideoCourses().getAttribute("selected").equalsIgnoreCase("true");
					if (!result) {
						batchMsgList.add("VideoCourse section  is not Selected.");
						return result;
					}

					result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getPackageDescription().get(0),
							10);
					if (!result) {
						batchMsgList.add("Package Description  is not visible.");
						return result;
					}
					cfObj.commonClick(batchPageObj.getPackageDescription().get(0));
					videoCoursesPageUtilObj = new VideoCoursesPageUtil(driver);
					result = videoCoursesPageUtilObj.clickOnPurchasedVideo(driver);
					if (!result) {
						batchMsgList.addAll(videoCoursesPageUtilObj.videoSectionMsgList);
						return result;
					}
					result = videoCoursesPageUtilObj.clickOnGotItBtn(driver);
					if (!result) {
						batchMsgList.addAll(videoCoursesPageUtilObj.videoSectionMsgList);
						return result;
					}
					result = cfObj.commonWaitForElementToBeVisible(driver,
							videoCoursesPageUtilObj.videoCoursesPageObj.getPlayerViewState(), 10);
					if (!result) {
						batchMsgList.add("Player view btn is not visible.");
						return result;
					}
					result = cfObj.pressAndroidKey(driver, key.BACK, 2);
					if (!result) {
						batchMsgList.add("Not able to click back button 2 times.");
						return result;
					}
					break;
				}
			}

			cfObj.commonClick(batchPageObj.getBatchMahaPackPageBackBtn());

		} catch (Exception e) {
			batchMsgList.add("validateViewAllSubjectBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	private boolean clickOnBatchCard(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getSubjectCardList().get(0), 10);
			if (!result) {
				batchMsgList.add("BatchCard btn is not visible.");
				return result;
			}

			// cfObj.commonClick( batchPageObj.getSubjectCardList().get(0));
			//
			// result=validateVideoCourseTab(driver);
			// if(!result) {
			// System.out.println("Not able to validate VideoCourse.");
			// return result;
			// }
			//
			// cfObj.commonClick(batchPageObj.getBatchMahaPackPageBackBtn());

			if (cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getMoreCourseTitle(), 10)) {
				cfObj.commonClick(batchPageObj.getMoreCourseTitle());
				Thread.sleep(4000);
				TreeSet<String> contentName = new TreeSet<>();
				for (int i = 0; i < 2; i++) {
					for (int j = 0; j < batchPageObj.getListBatchCard().size(); j++) {
						contentName.add(cfObj.commonGetElements(driver,
								"//*[@resource-id='com.sankalpbharat.app:id/batch_card']/child::*[@resource-id='com.sankalpbharat.app:id/title']",
								"xpath").get(j).getText());
					}
					cfObj.scrollUtill(driver, 1, direction.DOWN);
				}
				cfObj.scrollUtill(driver, 1, direction.UP);

				ArrayList<String> contentText = new ArrayList<>(contentName);

				for (int i = 0; i < contentText.size(); i++) {
					String contentTextName = contentText.get(i);

					boolean contentStatus = false;
					if (contentTextName.contains("Test Series")) {
						result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
								"//*[@text='" + contentTextName + "']", "xpath", 10);
						if (!result) {
							batchMsgList.add(contentTextName + " BatchCard is not visible.");
							return result;
						}
						cfObj.commonClick(
								cfObj.commonGetElement(driver, "//*[@text='" + contentTextName + "']", "xpath"));
						result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getBatchTabList().get(0),
								10);
						if (!result) {
							batchMsgList.add("BatchTabList is not visible.");
							return result;
						}
						result = validateTestSeriesTab(driver);
						if (!result) {
							batchMsgList.add("Not able to validate TestSeriesTab.");
							return result;
						}
						contentStatus = true;
					} else if (contentTextName.contains("DPP & EBooks")) {
						result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
								"//*[@text='" + contentTextName + "']", "xpath", 10);
						if (!result) {
							batchMsgList.add(contentTextName + " BatchCard is not visible.");
							return result;
						}
						cfObj.commonClick(
								cfObj.commonGetElement(driver, "//*[@text='" + contentTextName + "']", "xpath"));
						result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getBatchTabList().get(0),
								10);
						if (!result) {
							batchMsgList.add("BatchTabList is not visible.");
							return result;
						}
						result = validateEBookTab(driver);
						if (!result) {
							batchMsgList.add("Not able to validate EBook.");
							return result;
						}
						contentStatus = true;
					} else if (contentTextName.contains("Live Classes")) {
						result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
								"//*[@text='" + contentTextName + "']", "xpath", 10);
						if (!result) {
							batchMsgList.add(contentTextName + " BatchCard is not visible.");
							return result;
						}
						cfObj.commonClick(
								cfObj.commonGetElement(driver, "//*[@text='" + contentTextName + "']", "xpath"));
						result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getBatchTabList().get(0),
								10);
						if (!result) {
							batchMsgList.add("BatchTabList is not visible.");
							return result;
						}
						result = validateLiveClassTab(driver);
						if (!result) {
							batchMsgList.add("Not able to validate LiveClass.");
							return result;
						}
						contentStatus = true;

					} else if (contentTextName.contains("Video Courses")) {
						result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
								"//*[@text='" + contentTextName + "']", "xpath", 10);
						if (!result) {
							batchMsgList.add(contentTextName + " BatchCard is not visible.");
							return result;
						}
						cfObj.commonClick(
								cfObj.commonGetElement(driver, "//*[@text='" + contentTextName + "']", "xpath"));
						result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getBatchTabList().get(0),
								10);
						if (!result) {
							batchMsgList.add("BatchTabList is not visible.");
							return result;
						}
						result = validateVideoCourseTab(driver);
						if (!result) {
							batchMsgList.add("Not able to validate VideoCourse.");
							return result;
						}
						contentStatus = true;
					}
					if (contentStatus == true) {
						cfObj.commonClick(batchPageObj.getBatchMahaPackPageBackBtn());
						Thread.sleep(2000);
					}
				}
			} else {
				System.out.println("MoreCourseTitle tab is not available.");
			}

		} catch (Exception e) {
			batchMsgList.add("clickOnBatchCard_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnSearchIcon(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getIconSearch(), 10);
			if (!result) {
				batchMsgList.add("Search icon is not visible.");
				return result;
			}
			cfObj.commonClick(batchPageObj.getIconSearch());
			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getSearchTextField(), 10);
			if (!result) {
				batchMsgList.add("SearchTextField is not visible.");
				return result;
			}

		} catch (Exception e) {
			batchMsgList.add("clickOnSearchIcon_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean validateFilterBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonSetTextTextBox(batchPageObj.getSearchTextField(), "EB1");
			if (!result) {
				batchMsgList.add("Not able to enter text in search text field.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getSearchFieldClearBtn(), 10);
			if (!result) {
				batchMsgList.add("SearchFieldClearBtn is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getFilterBtn(), 10);
			if (!result) {
				batchMsgList.add("FilterBtn is not visible.");
				return result;
			}

			result = cfObj.commonVerifyValueTextBox(batchPageObj.getFilterCountText(), "0");
			if (!result) {
				batchMsgList.add("Filter Count text is not matching.");
				return result;
			}

			result = clickOnFilterBtn(driver);
			if (!result) {
				batchMsgList.add("Not able to click Filter btn.");
				return result;
			}
			filterPageUtilObj = new FilterPageUtil(driver);
			result = filterPageUtilObj.clickOnMahaPackFilterPageCloseBtn(driver);
			if (!result) {
				batchMsgList.addAll(filterPageUtilObj.filterPageMsgList);
				return result;
			}

			result = clickOnFilterBtn(driver);
			if (!result) {
				batchMsgList.add("Not able to click Filter btn.");
				return result;
			}
			result = filterPageUtilObj.clickOnFilterApplyBtn(driver);
			if (!result) {
				batchMsgList.addAll(filterPageUtilObj.filterPageMsgList);
				return result;
			}
			Thread.sleep(1000);
			result = cfObj.commonVerifyValueTextBox(batchPageObj.getFilterCountText(), "1");
			if (!result) {
				batchMsgList.add("Filter Count text is not Updated.");
				return result;
			}

			result = clickOnFilterBtn(driver);
			if (!result) {
				batchMsgList.add("Not able to click Filter btn.");
				return result;
			}
			result = filterPageUtilObj.clickOnFilterResetBtn(driver);
			if (!result) {
				batchMsgList.addAll(filterPageUtilObj.filterPageMsgList);
				return result;
			}
			result = cfObj.commonVerifyValueTextBox(batchPageObj.getFilterCountText(), "0");
			if (!result) {
				batchMsgList.add("Filter Count text is not Updated.");
				return result;
			}

		} catch (Exception e) {
			batchMsgList.add("validateFilterBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnFilterBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			cfObj.commonClick(batchPageObj.getFilterBtn());
			filterPageUtilObj = new FilterPageUtil(driver);
			result = filterPageUtilObj.validateMahaPackFilterPageUI(driver);
			if (!result) {
				batchMsgList.addAll(filterPageUtilObj.filterPageMsgList);
				return result;
			}
		} catch (Exception e) {
			batchMsgList.add("clickOnSearchIcon_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public List<String> getAllSubjects(AppiumDriver<MobileElement> driver, List<String> subjectList) {
		boolean result = true;

		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//*[contains(@resource-id,'tabs')]/descendant::android.widget.TextView", "xpath", 10);
			if (!result) {
				batchMsgList.add("Course tab is not visible.");
				return null;
			}
			ArrayList<String> tabText = new ArrayList<>();
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < batchPageObj.getBatchTabList().size(); j++) {
					tabText.add(batchPageObj.getBatchTabList().get(j).getText());
				}
			}

			System.out.println(tabText);

			for (int i = 0; i < batchPageObj.getBatchTabList().size(); i++) {

				result = cfObj.commonWaitForElementToBeVisible(driver, batchPageObj.getBatchTabList().get(i), 10);
				if (!result) {
					batchMsgList.add("BatchTab is not visible.");
					return null;
				}
				cfObj.commonClick(batchPageObj.getBatchTabList().get(i));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "packageDescription", "id", 10);
				if (!result) {
					batchMsgList.add("PackageDescription is not visible.");
					return null;
				}
				String tabTitle = tabText.get(i);
				if (tabTitle == null) {
					batchMsgList.add("Batch Tab title is null.");
					return null;
				}
				System.out.println(tabTitle);

				switch (tabTitle) {

				case "Test Series":
					cfObj.commonClick(batchPageObj.getPackageDescription().get(0));

					result = cfObj.waitForPageLoading(driver, 8, 2000,
							cfObj.commonGetElement(driver, "bl_title", "id"));
					if (!result) {
						batchMsgList.add("TestSeries title is not visible.");
						return null;
					}
					List<MobileElement> testSeriesList = cfObj.commonGetElements(driver, "bl_title", "id");
					for (int j = 0; j < testSeriesList.size(); j++) {
						System.out.println(testSeriesList.get(j).getText());
						subjectList.add(testSeriesList.get(j).getText());
					}

					cfObj.commonClick(cfObj.commonGetElement(driver,
							"//android.widget.ImageButton[@content-desc='Navigate up']", "xpath"));

					break;

				case "DPP & EBooks":
					cfObj.commonClick(batchPageObj.getPackageDescription().get(0));

					result = cfObj.waitForPageLoading(driver, 8, 2000, cfObj.commonGetElement(driver, "title", "id"));
					if (!result) {
						batchMsgList.add("EBook title is not visible.");
						return null;
					}

					List<MobileElement> eBookList = cfObj.commonGetElements(driver, "title", "id");

					for (int j = 0; j < eBookList.size(); j++) {
						System.out.println(eBookList.get(j).getText());
						subjectList.add(eBookList.get(j).getText());
					}
					cfObj.pressAndroidKey(driver, key.BACK, 1);

					break;
				case "Live Classes":
					cfObj.commonClick(batchPageObj.getPackageDescription().get(0));

					liveClassesPageUtilObj = new LiveClassesPageUtil(driver);
					result = liveClassesPageUtilObj.handledNotificationPopUp(driver);
					if (!result) {
						batchMsgList.addAll(liveClassesPageUtilObj.liveClassMsgList);
						return null;
					}
					result = liveClassesPageUtilObj.clickOnGotItBtn(driver);
					if (!result) {
						batchMsgList.addAll(liveClassesPageUtilObj.liveClassMsgList);
						return null;
					}
					List<MobileElement> elementList = cfObj.commonGetElements(driver, "video_course_title_head", "id");

					for (int j = 0; j < elementList.size(); j++) {
						System.out.println(elementList.get(j).getText());
						subjectList.add(elementList.get(j).getText());
					}

					cfObj.pressAndroidKey(driver, key.BACK, 1);
					break;

				case "Video Courses":
					cfObj.commonClick(batchPageObj.getPackageDescription().get(0));

					result = cfObj.waitForPageLoading(driver, 8, 2000,
							cfObj.commonGetElement(driver, "video_course_title_head", "id"));
					if (!result) {
						batchMsgList.add("VideoSection title is not visible.");
						return null;
					}
					cfObj.commonClick(cfObj.commonGetElements(driver, "video_course_title_head", "id").get(0));
					videoCoursesPageUtilObj = new VideoCoursesPageUtil(driver);
					result = videoCoursesPageUtilObj.clickOnGotItBtn(driver);
					if (!result) {
						batchMsgList.addAll(videoCoursesPageUtilObj.videoSectionMsgList);
						return null;
					}
					List<MobileElement> videoList = cfObj.commonGetElements(driver, "video_course_title_head", "id");

					for (int j = 0; j < videoList.size(); j++) {
						System.out.println(videoList.get(j).getText());
						subjectList.add(videoList.get(j).getText());
					}

					cfObj.pressAndroidKey(driver, key.BACK, 2);

					break;
				}
				Thread.sleep(2000);

			}
			cfObj.commonClick(batchPageObj.getBatchMahaPackPageBackBtn());

		} catch (Exception e) {
			batchMsgList.add("getAllSubjects_Exception: " + e.getMessage());
			subjectList = null;
		}
		return subjectList;
	}

	public boolean verfiyAlreadyPurchasedNewMahaPack(AppiumDriver<MobileElement> driver, String strTitleMahapack) {
		boolean result = true;

		try {

			loginUtilObj = new LoginUtil(driver);

			result = loginUtilObj.verifyLoginGuestUser(driver);
			if (!result) {
				batchMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			homePageUtil = new HomePageUtil(driver);

			result = homePageUtil.clickMyContentButton(driver);
			if (!result) {
				batchMsgList.addAll(homePageUtil.homeMsgList);
				return result;
			}

			myContentUtilObj = new MyContentUtil(driver);
			result = myContentUtilObj.clickOnPurchasedBtn(driver);
			if (!result) {
				batchMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.ImageView[contains(@content-desc,'" + strTitleMahapack + "')]", "xpath", 10);
			if (!result) {
				batchMsgList.add(strTitleMahapack + " Package is not available.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver,
					"//android.widget.ImageView[contains(@content-desc,'" + strTitleMahapack + "')]", "xpath"));

			Thread.sleep(2000);

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.Button[@content-desc='Manage Exams']", "xpath", 10)) {

				cfObj.commonClick(driver, "//android.widget.Button[@content-desc='Manage Exams']", "xpath");
				Thread.sleep(2000);
				result = validateSelectPriorityExamsScreen(driver);
				if (!result) {
					batchMsgList.add("Failed to validate SelectPriorityExamsScreen.");
					return result;
				}
				cfObj.commonClick(driver, "//android.widget.Button[@content-desc='Reset']", "xpath");

			} else {

				result = validateSelectPriorityExamsScreen(driver);
				if (!result) {
					batchMsgList.add("Failed to validate SelectPriorityExamsScreen.");
					return result;
				}
			}

			// validate Clear All button
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "android.widget.EditText", "class", 10);
			if (!result) {
				batchMsgList.add("Exam search text field is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.Button[@content-desc='Clear All']", "xpath", 10);
			if (!result) {
				batchMsgList.add("ClearAll button is not visible.");
				return result;
			}

			result = cfObj.commonGetElement(driver, "//android.widget.Button[@content-desc='Clear All']", "xpath")
					.getAttribute("enabled").equalsIgnoreCase("false");
			if (!result) {
				batchMsgList.add("ClearAll button is not in disable state.");
				return result;
			}
			result = cfObj.commonSetTextTextBox(cfObj.commonGetElement(driver, "android.widget.EditText", "class"),
					"LiveTest");
			if (!result) {
				batchMsgList.add("Failed to enter Exam name on Exam search textfield.");
				return result;
			}

			result = cfObj.commonGetElement(driver, "//android.widget.Button[@content-desc='Clear All']", "xpath")
					.getAttribute("enabled").equalsIgnoreCase("true");
			if (!result) {
				batchMsgList.add("ClearAll button is not in enable state.");
				return result;
			}

			cfObj.commonClick(driver, "//android.widget.Button[@content-desc='Clear All']", "xpath");

			result = cfObj.commonVerifyValueTextBox(cfObj.commonGetElement(driver, "android.widget.EditText", "class"),
					"");
			if (!result) {
				batchMsgList.add("Search text field is not cleared.");
				return result;
			}

			// Validate Reset button
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.Button[@content-desc='Reset']", "xpath", 10);
			if (!result) {
				batchMsgList.add("ReSet button is not visible.");
				return result;
			}

			result = cfObj.commonGetElement(driver, "//android.widget.Button[@content-desc='Reset']", "xpath")
					.getAttribute("enabled").equalsIgnoreCase("false");
			if (!result) {
				batchMsgList.add("Reset button is not in disable state.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "android.widget.CheckBox", "class", 10);
			if (!result) {
				batchMsgList.add("Exam checkbox is not visible.");
				return result;
			}

			result = cfObj.commonGetElement(driver, "android.widget.CheckBox", "class").getAttribute("checked")
					.equalsIgnoreCase("false");
			if (!result) {
				batchMsgList.add("CheckBox was already selected.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "android.widget.CheckBox", "class"));
			result = cfObj.commonGetElement(driver, "android.widget.CheckBox", "class").getAttribute("checked")
					.equalsIgnoreCase("true");
			if (!result) {
				batchMsgList.add("Failed to select CheckBox.");
				return result;
			}

			result = cfObj.commonGetElement(driver, "//android.widget.Button[@content-desc='Reset']", "xpath")
					.getAttribute("enabled").equalsIgnoreCase("true");
			if (!result) {
				batchMsgList.add("Reset button is not in enable state.");
				return result;
			}

			cfObj.commonClick(
					cfObj.commonGetElement(driver, "//android.widget.Button[@content-desc='Reset']", "xpath"));

			result = cfObj.commonGetElement(driver, "android.widget.CheckBox", "class").getAttribute("checked")
					.equalsIgnoreCase("false");
			if (!result) {
				batchMsgList.add("Failed to click Reset button.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.Button[@content-desc='Apply']", "xpath", 10);
			if (!result) {
				batchMsgList.add("Apply button is not visible.");
				return result;
			}

			result = cfObj.commonGetElement(driver, "//android.widget.Button[@content-desc='Apply']", "xpath")
					.getAttribute("enabled").equalsIgnoreCase("false");
			if (!result) {
				batchMsgList.add("Apply button should be on disable state.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "android.widget.CheckBox", "class"));

			result = cfObj.commonGetElement(driver, "android.widget.CheckBox", "class").getAttribute("checked")
					.equalsIgnoreCase("true");
			if (!result) {
				batchMsgList.add("Failed to select CheckBox.");
				return result;
			}

			result = cfObj.commonGetElement(driver, "//android.widget.Button[@content-desc='Apply']", "xpath")
					.getAttribute("enabled").equalsIgnoreCase("true");
			if (!result) {
				batchMsgList.add("Apply button should be on enable state.");
				return result;
			}

			cfObj.commonClick(
					cfObj.commonGetElement(driver, "//android.widget.Button[@content-desc='Apply']", "xpath"));
			Thread.sleep(2000);

			result = validateMahaPackScreen(driver, strTitleMahapack);
			if (!result) {
				batchMsgList.add("Failed to validate MahaPackScreen.");
				return result;
			}

			// validate Manage exam link
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.Button[@content-desc='Manage Exams']", "xpath", 10);
			if (!result) {
				batchMsgList.add("ManageExam button is not visible.");
				return result;
			}

			cfObj.commonClick(
					cfObj.commonGetElement(driver, "//android.widget.Button[@content-desc='Manage Exams']", "xpath"));

			Thread.sleep(2000);

			result = validateSelectPriorityExamsScreen(driver);
			if (!result) {
				batchMsgList.add("Failed to validate SelectPriorityExamsScreen.");
				return result;
			}

			cfObj.commonClick(
					cfObj.commonGetElement(driver, "//android.widget.Button[@content-desc='Apply']", "xpath"));
			Thread.sleep(2000);

			result = validateMahaPackScreen(driver, strTitleMahapack);
			if (!result) {
				batchMsgList.add("Failed to validate MahaPackScreen.");
				return result;
			}

			String examName = cfObj.commonGetElements(driver,
					"//android.view.View[@content-desc='Your Exams']/following-sibling::android.view.View/descendant::android.widget.ImageView",
					"xpath").get(0).getAttribute("content-desc");

			cfObj.commonClick(cfObj.commonGetElement(driver,
					"//android.view.View[@content-desc='Your Exams']/following-sibling::android.view.View/descendant::android.widget.ImageView",
					"xpath"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.ImageView[@content-desc='" + examName + "']", "xpath", 10);
			if (!result) {
				batchMsgList.add("Exam Screen title is not visible.");
				return result;
			}

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"DNC New Parent 2\"]", "xpath", 10)) {

				cfObj.commonClick(cfObj.commonGetElement(driver,
						"//android.view.View[@content-desc=\"DNC New Parent 2\"]", "xpath"));

				result = validateEBookSectionOnMahaPack(driver);
				if (!result) {
					batchMsgList.add("Failed to validate EBookSection On MahaPack.");
					return result;
				}

				result = validateLiveClassSectionOnMahaPack(driver);
				if (!result) {
					batchMsgList.add("Failed to validate Liveclass Section On MahaPack.");
					return result;
				}

				result = validateTestSeriesSectionOnMahaPack(driver);
				if (!result) {
					batchMsgList.add("Failed to validate TestSeries Section On MahaPack.");
					return result;
				}

				result = validateVideoCourseSectionOnMahaPack(driver);
				if (!result) {
					batchMsgList.add("Failed to validate VideoCourse Section On MahaPack.");
					return result;
				}
			}

		} catch (Exception e) {
			batchMsgList.add("verfiyAlreadyPurchasedNewMahaPack_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean validateSelectPriorityExamsScreen(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Select Priority Exams']", "xpath", 10);
			if (!result) {
				batchMsgList.add("SelectPriorityExams screen title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='btn_appbar_back']", "xpath", 10);
			if (!result) {
				batchMsgList.add("SelectPriorityExams screen back button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "android.widget.EditText", "class", 10);
			if (!result) {
				batchMsgList.add("Exam search textfield is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.Button[@content-desc='Reset']", "xpath", 10);
			if (!result) {
				batchMsgList.add("ReSet button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.Button[@content-desc='Clear All']", "xpath", 10);
			if (!result) {
				batchMsgList.add("ClearAll button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "android.widget.CheckBox", "class", 10);
			if (!result) {
				batchMsgList.add("Exam checkbox is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.Button[@content-desc='Apply']", "xpath", 10);
			if (!result) {
				batchMsgList.add("Apply button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.Button[@content-desc='Cancel']", "xpath", 10);
			if (!result) {
				batchMsgList.add("Cancel button is not visible.");
				return result;
			}

		} catch (Exception e) {
			batchMsgList.add("validateSelectPriorityExamsScreen_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean validateMahaPackScreen(AppiumDriver<MobileElement> driver, String strMahaPack) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='" + strMahaPack + "']", "xpath", 10);
			if (!result) {
				batchMsgList.add("MahaPack screen title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='btn_appbar_back']", "xpath", 10);
			if (!result) {
				batchMsgList.add("MahaPack screen back button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='btn_appbar_back']/following-sibling::android.widget.Button",
					"xpath", 10);
			if (!result) {
				batchMsgList.add("Search button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.Button[@content-desc='Manage Exams']", "xpath", 10);
			if (!result) {
				batchMsgList.add("ManageExam button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Your Exams']", "xpath", 10);
			if (!result) {
				batchMsgList.add("YoursExam header is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Your Exams']/following-sibling::android.view.View/descendant::android.widget.ImageView",
					"xpath", 10);
			if (!result) {
				batchMsgList.add("Exam list are not visible under YourExams section.");
				return result;
			}

		} catch (Exception e) {
			batchMsgList.add("validateMahaPackScreen_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean validateEBookSectionOnMahaPack(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[contains(@content-desc,'Ebooks')]", "xpath", 10);
			if (!result) {
				batchMsgList.add("EBook section is not visible.");
				return result;
			}

			cfObj.commonClick(
					cfObj.commonGetElement(driver, "//android.view.View[contains(@content-desc,'Ebooks')]", "xpath"));

			result = cfObj.commonGetElement(driver, "//android.view.View[contains(@content-desc,'Ebooks')]", "xpath")
					.getAttribute("selected").equalsIgnoreCase("true");
			if (!result) {
				batchMsgList.add("Failed to click EBook section.");
				return result;
			}

			// Subject
			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Subjects']/preceding-sibling::android.view.View/descendant::android.widget.ImageView",
					"xpath", 10)) {

				cfObj.commonClick(cfObj.commonGetElements(driver,
						"//android.view.View[@content-desc='Subjects']/preceding-sibling::android.view.View/descendant::android.widget.ImageView",
						"xpath").get(0));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.view.View[@content-desc='btn_appbar_back']/parent::android.view.View/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
						"xpath", 10);
				if (!result) {
					batchMsgList.add("Failed to open Subject_Content list screen.");
					return result;
				}

				cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.View[@content-desc='btn_appbar_back']",
						"xpath"));
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Subjects']", "xpath", 10);
			if (!result) {
				batchMsgList.add("Failed to close Subject_Content list screen.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Subjects\"]/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath", 10);
			if (!result) {
				batchMsgList.add("Subject list is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElements(driver,
					"//android.view.View[@content-desc='Subjects']/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath").get(0));

			// Chapter

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Chapters']/preceding-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath", 10)) {

				cfObj.commonClick(cfObj.commonGetElements(driver,
						"//android.view.View[@content-desc='Chapters']/preceding-sibling::android.view.View/descendant::android.view.View[@content-desc]",
						"xpath").get(0));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.view.View[@content-desc='btn_appbar_back']/parent::android.view.View/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
						"xpath", 10);
				if (!result) {
					batchMsgList.add("Failed to open Chapter_Content list screen.");
					return result;
				}

				cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.View[@content-desc='btn_appbar_back']",
						"xpath"));
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Chapters']", "xpath", 10);
			if (!result) {
				batchMsgList.add("Failed to close Chapters_Content list screen.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Chapters']/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath", 10);
			if (!result) {
				batchMsgList.add("Subject list is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElements(driver,
					"//android.view.View[@content-desc='Chapters']/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath").get(0));
			// Topic

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Topics']/preceding-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath", 10)) {

				cfObj.commonClick(cfObj.commonGetElements(driver,
						"//android.view.View[@content-desc='Topics']/preceding-sibling::android.view.View/descendant::android.view.View[@content-desc]",
						"xpath").get(0));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.view.View[@content-desc='btn_appbar_back']/parent::android.view.View/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
						"xpath", 10);
				if (!result) {
					batchMsgList.add("Failed to open Chapter_Content list screen.");
					return result;
				}

				cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.View[@content-desc='btn_appbar_back']",
						"xpath"));
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Topics']", "xpath", 10);
			if (!result) {
				batchMsgList.add("Failed to close Chapters_Content list screen.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Topics']/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath", 10);
			if (!result) {
				batchMsgList.add("Subject list is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElements(driver,
					"//android.view.View[@content-desc='Topics']/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath").get(0));

			// Sub Topic
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[contains(@content-desc,'Unread')]", "xpath", 10);
			if (!result) {
				batchMsgList.add("Unread tab is not visible.");
				return result;
			}
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[contains(@content-desc,'All Ebooks')]", "xpath", 10);
			if (!result) {
				batchMsgList.add("All EBooks tab is not visible.");
				return result;
			}

			// result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
			// "//android.view.View[contains(@content-desc,'All
			// Ebooks')]/parent::android.view.View/following-sibling::android.widget.ScrollView/descendant::android.view.View[@content-desc]",
			// "xpath", 10);
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[contains(@content-desc,'All Ebooks')]/parent::android.view.View/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath", 10);
			if (!result) {
				batchMsgList.add("SubTopic materials are not visible.");
				return result;
			}

			// Back Navigation

			cfObj.commonClick(
					cfObj.commonGetElement(driver, "//android.view.View[@content-desc='btn_appbar_back']", "xpath"));
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Topics']", "xpath", 10);
			if (!result) {
				batchMsgList.add("Failed to close SubTopic screen.");
				return result;
			}
			cfObj.commonClick(
					cfObj.commonGetElement(driver, "//android.view.View[@content-desc='btn_appbar_back']", "xpath"));
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Chapters']", "xpath", 10);
			if (!result) {
				batchMsgList.add("Failed to close Topic screen.");
				return result;
			}

			cfObj.commonClick(
					cfObj.commonGetElement(driver, "//android.view.View[@content-desc='btn_appbar_back']", "xpath"));
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Subjects']", "xpath", 10);
			if (!result) {
				batchMsgList.add("Failed to close Chapter screen.");
				return result;
			}

		} catch (Exception e) {
			batchMsgList.add("validateEBookSectionOnMahaPack_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean validateLiveClassSectionOnMahaPack(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[contains(@content-desc,'Live Class')]", "xpath", 10);
			if (!result) {
				batchMsgList.add("Live Class section is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver,
					"//android.view.View[contains(@content-desc,'Live Class')]", "xpath"));

			result = cfObj
					.commonGetElement(driver, "//android.view.View[contains(@content-desc,'Live Class')]", "xpath")
					.getAttribute("selected").equalsIgnoreCase("true");
			if (!result) {
				batchMsgList.add("Failed to click Live Class section.");
				return result;
			}

			// Subject

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Subjects']/preceding-sibling::android.view.View/descendant::android.widget.ImageView",
					"xpath", 10)) {

				cfObj.commonClick(cfObj.commonGetElements(driver,
						"//android.view.View[@content-desc='Subjects']/preceding-sibling::android.view.View/descendant::android.widget.ImageView",
						"xpath").get(0));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.view.View[@content-desc='btn_appbar_back']/parent::android.view.View/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
						"xpath", 10);
				if (!result) {
					batchMsgList.add("Failed to open Subject_Content list screen.");
					return result;
				}

				cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.View[@content-desc='btn_appbar_back']",
						"xpath"));
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Subjects']", "xpath", 10);
			if (!result) {
				batchMsgList.add("Failed to close Subject_Content list screen.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Subjects\"]/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath", 10);
			if (!result) {
				batchMsgList.add("Subject list is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElements(driver,
					"//android.view.View[@content-desc='Subjects']/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath").get(0));

			// Chapter

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Chapters']/preceding-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath", 10)) {

				cfObj.commonClick(cfObj.commonGetElements(driver,
						"//android.view.View[@content-desc='Chapters']/preceding-sibling::android.view.View/descendant::android.view.View[@content-desc]",
						"xpath").get(0));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.view.View[@content-desc='btn_appbar_back']/parent::android.view.View/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
						"xpath", 10);
				if (!result) {
					batchMsgList.add("Failed to open Chapter_Content list screen.");
					return result;
				}

				cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.View[@content-desc='btn_appbar_back']",
						"xpath"));
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Chapters']", "xpath", 10);
			if (!result) {
				batchMsgList.add("Failed to close Chapters_Content list screen.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Chapters']/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath", 10);
			if (!result) {
				batchMsgList.add("Subject list is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElements(driver,
					"//android.view.View[@content-desc='Chapters']/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath").get(0));
			// Topic

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Topics']/preceding-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath", 10)) {

				cfObj.commonClick(cfObj.commonGetElements(driver,
						"//android.view.View[@content-desc='Topics']/preceding-sibling::android.view.View/descendant::android.view.View[@content-desc]",
						"xpath").get(0));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.view.View[@content-desc='btn_appbar_back']/parent::android.view.View/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
						"xpath", 10);
				if (!result) {
					batchMsgList.add("Failed to open Chapter_Content list screen.");
					return result;
				}

				cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.View[@content-desc='btn_appbar_back']",
						"xpath"));
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Topics']", "xpath", 10);
			if (!result) {
				batchMsgList.add("Failed to close Chapters_Content list screen.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Topics']/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath", 10);
			if (!result) {
				batchMsgList.add("Subject list is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElements(driver,
					"//android.view.View[@content-desc='Topics']/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath").get(0));

			// Sub Topic

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[contains(@content-desc,'Live Class Recording')]", "xpath", 10);
			if (!result) {
				batchMsgList.add("Live Class Recording tab is not visible.");
				return result;
			}
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[contains(@content-desc,'Upcoming')]", "xpath", 10);
			if (!result) {
				batchMsgList.add("Upcoming tab is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver,
					"//android.view.View[contains(@content-desc,'Live Class Recording')]", "xpath"));
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.ImageView[@content-desc='No Live Class Recording found']", "xpath", 10);
			if (!result) {
				batchMsgList.add("NoLive class text is not visible.");
				return result;
			}

			cfObj.commonClick(
					cfObj.commonGetElement(driver, "//android.view.View[contains(@content-desc,'Upcoming')]", "xpath"));

//			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,'Upcoming')]/parent::android.view.View/following-sibling::android.widget.ScrollView/descendant::android.view.View[@content-desc]", "xpath", 10);
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[contains(@content-desc,'Upcoming')]/parent::android.view.View/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath", 10);
			if (!result) {
				batchMsgList.add("Upcomming classes are not visible.");
				return result;
			}

			// Back Navigation

			cfObj.commonClick(
					cfObj.commonGetElement(driver, "//android.view.View[@content-desc='btn_appbar_back']", "xpath"));
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Topics']", "xpath", 10);
			if (!result) {
				batchMsgList.add("Failed to close SubTopic screen.");
				return result;
			}
			cfObj.commonClick(
					cfObj.commonGetElement(driver, "//android.view.View[@content-desc='btn_appbar_back']", "xpath"));
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Chapters']", "xpath", 10);
			if (!result) {
				batchMsgList.add("Failed to close Topic screen.");
				return result;
			}

			cfObj.commonClick(
					cfObj.commonGetElement(driver, "//android.view.View[@content-desc='btn_appbar_back']", "xpath"));
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Subjects']", "xpath", 10);
			if (!result) {
				batchMsgList.add("Failed to close Chapter screen.");
				return result;
			}

		} catch (Exception e) {
			batchMsgList.add("validateLiveClassSectionOnMahaPack_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean validateTestSeriesSectionOnMahaPack(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[contains(@content-desc,'Test Series')]", "xpath", 10);
			if (!result) {
				batchMsgList.add("Test Series section is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver,
					"//android.view.View[contains(@content-desc,'Test Series')]", "xpath"));

			result = cfObj
					.commonGetElement(driver, "//android.view.View[contains(@content-desc,'Test Series')]", "xpath")
					.getAttribute("selected").equalsIgnoreCase("true");
			if (!result) {
				batchMsgList.add("Failed to click Test Series section.");
				return result;
			}

			// Subject

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Subjects']/preceding-sibling::android.view.View/descendant::android.widget.ImageView",
					"xpath", 10)) {

				cfObj.commonClick(cfObj.commonGetElements(driver,
						"//android.view.View[@content-desc='Subjects']/preceding-sibling::android.view.View/descendant::android.widget.ImageView",
						"xpath").get(0));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.view.View[@content-desc='btn_appbar_back']/parent::android.view.View/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
						"xpath", 10);
				if (!result) {
					batchMsgList.add("Failed to open Subject_Content list screen.");
					return result;
				}

				cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.View[@content-desc='btn_appbar_back']",
						"xpath"));
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Subjects']", "xpath", 10);
			if (!result) {
				batchMsgList.add("Failed to close Subject_Content list screen.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Subjects\"]/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath", 10);
			if (!result) {
				batchMsgList.add("Subject list is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElements(driver,
					"//android.view.View[@content-desc='Subjects']/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath").get(0));

			// Chapter

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Chapters']/preceding-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath", 10)) {

				cfObj.commonClick(cfObj.commonGetElements(driver,
						"//android.view.View[@content-desc='Chapters']/preceding-sibling::android.view.View/descendant::android.view.View[@content-desc]",
						"xpath").get(0));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.view.View[@content-desc='btn_appbar_back']/parent::android.view.View/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
						"xpath", 10);
				if (!result) {
					batchMsgList.add("Failed to open Chapter_Content list screen.");
					return result;
				}

				cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.View[@content-desc='btn_appbar_back']",
						"xpath"));
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Chapters']", "xpath", 10);
			if (!result) {
				batchMsgList.add("Failed to close Chapters_Content list screen.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Chapters']/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath", 10);
			if (!result) {
				batchMsgList.add("Subject list is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElements(driver,
					"//android.view.View[@content-desc='Chapters']/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath").get(0));
			// Topic

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Topics']/preceding-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath", 10)) {

				cfObj.commonClick(cfObj.commonGetElements(driver,
						"//android.view.View[@content-desc='Topics']/preceding-sibling::android.view.View/descendant::android.view.View[@content-desc]",
						"xpath").get(0));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.view.View[@content-desc='btn_appbar_back']/parent::android.view.View/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
						"xpath", 10);
				if (!result) {
					batchMsgList.add("Failed to open Chapter_Content list screen.");
					return result;
				}

				cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.View[@content-desc='btn_appbar_back']",
						"xpath"));
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Topics']", "xpath", 10);
			if (!result) {
				batchMsgList.add("Failed to close Chapters_Content list screen.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Topics']/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath", 10);
			if (!result) {
				batchMsgList.add("Subject list is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElements(driver,
					"//android.view.View[@content-desc='Topics']/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath").get(0));

			// Sub Topic

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[contains(@content-desc,'All Tests')]", "xpath", 10);
			if (!result) {
				batchMsgList.add("All Tests tab is not visible.");
				return result;
			}
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[contains(@content-desc,'Completed')]", "xpath", 10);
			if (!result) {
				batchMsgList.add("Completed tab is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[contains(@content-desc,'Incomplete')]", "xpath", 10);
			if (!result) {
				batchMsgList.add("Incomplete tab is not visible.");
				return result;
			}
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[contains(@content-desc,'Unattempted')]", "xpath", 10);
			if (!result) {
				batchMsgList.add("Unattempted tab is not visible.");
				return result;
			}

			// result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
			// "//android.view.View[contains(@content-desc,'All
			// Tests')]/parent::android.widget.HorizontalScrollView/following-sibling::android.widget.ScrollView/descendant::android.view.View[@content-desc]",
			// "xpath", 10);
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[contains(@content-desc,'All Tests')]/parent::android.widget.HorizontalScrollView/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath", 10);
			if (!result) {
				batchMsgList.add("SubTopic materials are not visible.");
				return result;
			}

			// Back Navigation

			cfObj.commonClick(
					cfObj.commonGetElement(driver, "//android.view.View[@content-desc='btn_appbar_back']", "xpath"));
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Topics']", "xpath", 10);
			if (!result) {
				batchMsgList.add("Failed to close SubTopic screen.");
				return result;
			}
			cfObj.commonClick(
					cfObj.commonGetElement(driver, "//android.view.View[@content-desc='btn_appbar_back']", "xpath"));
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Chapters']", "xpath", 10);
			if (!result) {
				batchMsgList.add("Failed to close Topic screen.");
				return result;
			}

			cfObj.commonClick(
					cfObj.commonGetElement(driver, "//android.view.View[@content-desc='btn_appbar_back']", "xpath"));
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Subjects']", "xpath", 10);
			if (!result) {
				batchMsgList.add("Failed to close Chapter screen.");
				return result;
			}

		} catch (Exception e) {
			batchMsgList.add("validateTestSeriesSectionOnMahaPack_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean validateVideoCourseSectionOnMahaPack(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[contains(@content-desc,'Video Course')]", "xpath", 10);
			if (!result) {
				batchMsgList.add("Video Course section is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver,
					"//android.view.View[contains(@content-desc,'Video Course')]", "xpath"));

			result = cfObj
					.commonGetElement(driver, "//android.view.View[contains(@content-desc,'Video Course')]", "xpath")
					.getAttribute("selected").equalsIgnoreCase("true");
			if (!result) {
				batchMsgList.add("Failed to click Video Course section.");
				return result;
			}

			// Subject

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Subjects']/preceding-sibling::android.view.View/descendant::android.widget.ImageView",
					"xpath", 10)) {

				cfObj.commonClick(cfObj.commonGetElements(driver,
						"//android.view.View[@content-desc='Subjects']/preceding-sibling::android.view.View/descendant::android.widget.ImageView",
						"xpath").get(0));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.view.View[@content-desc='btn_appbar_back']/parent::android.view.View/following-sibling::android.view.View/descendant::android.widget.ImageView[@content-desc]",
						"xpath", 10);
				if (!result) {
					batchMsgList.add("Failed to open Subject_Content list screen.");
					return result;
				}

				cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.View[@content-desc='btn_appbar_back']",
						"xpath"));
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Subjects']", "xpath", 10);
			if (!result) {
				batchMsgList.add("Failed to close Subject_Content list screen.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Subjects\"]/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath", 10);
			if (!result) {
				batchMsgList.add("Subject list is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElements(driver,
					"//android.view.View[@content-desc='Subjects']/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath").get(0));

			// Chapter

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Chapters']/preceding-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath", 10)) {

				cfObj.commonClick(cfObj.commonGetElements(driver,
						"//android.view.View[@content-desc='Chapters']/preceding-sibling::android.view.View/descendant::android.view.View[@content-desc]",
						"xpath").get(0));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.view.View[@content-desc='btn_appbar_back']/parent::android.view.View/following-sibling::android.view.View/descendant::android.widget.ImageView[@content-desc]",
						"xpath", 10);
				if (!result) {
					batchMsgList.add("Failed to open Chapter_Content list screen.");
					return result;
				}

				cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.View[@content-desc='btn_appbar_back']",
						"xpath"));
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Chapters']", "xpath", 10);
			if (!result) {
				batchMsgList.add("Failed to close Chapters_Content list screen.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Chapters']/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath", 10);
			if (!result) {
				batchMsgList.add("Subject list is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElements(driver,
					"//android.view.View[@content-desc='Chapters']/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath").get(0));
			// Topic

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Topics']/preceding-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath", 10)) {

				cfObj.commonClick(cfObj.commonGetElements(driver,
						"//android.view.View[@content-desc='Topics']/preceding-sibling::android.view.View/descendant::android.view.View[@content-desc]",
						"xpath").get(0));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.view.View[@content-desc='btn_appbar_back']/parent::android.view.View/following-sibling::android.view.View/descendant::android.widget.ImageView[@content-desc]",
						"xpath", 10);
				if (!result) {
					batchMsgList.add("Failed to open Chapter_Content list screen.");
					return result;
				}

				cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.View[@content-desc='btn_appbar_back']",
						"xpath"));
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Topics']", "xpath", 10);
			if (!result) {
				batchMsgList.add("Failed to close Chapters_Content list screen.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Topics']/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath", 10);
			if (!result) {
				batchMsgList.add("Subject list is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElements(driver,
					"//android.view.View[@content-desc='Topics']/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath").get(0));

			// Sub Topic

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[contains(@content-desc,'All Videos')]", "xpath", 10);
			if (!result) {
				batchMsgList.add("All Videos tab is not visible.");
				return result;
			}
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[contains(@content-desc,'Watching')]", "xpath", 10);
			if (!result) {
				batchMsgList.add("Watching tab is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[contains(@content-desc,'Unwatched')]", "xpath", 10);
			if (!result) {
				batchMsgList.add("Unwatched tab is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[contains(@content-desc,'All Videos')]/parent::android.widget.HorizontalScrollView/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath", 10);
			if (!result) {
				batchMsgList.add("Videos materials are not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElements(driver,
					"//android.view.View[contains(@content-desc,'All Videos')]/parent::android.view.View/following-sibling::android.widget.ScrollView/descendant::android.widget.ImageView[@content-desc]",
					"xpath").get(0));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[contains(@content-desc,'Comment')]", "xpath", 10);
			if (!result) {
				batchMsgList.add("Failed to open Videos material.");
				return result;
			}

			cfObj.pressAndroidKey(driver, key.BACK, 1);
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[contains(@content-desc,'All Videos')]/parent::android.view.View/following-sibling::android.widget.ScrollView/descendant::android.widget.ImageView[@content-desc]",
					"xpath", 10);
			if (!result) {
				batchMsgList.add("Failed to close video screen.");
				return result;
			}

			// Back Navigation

			cfObj.commonClick(
					cfObj.commonGetElement(driver, "//android.view.View[@content-desc='btn_appbar_back']", "xpath"));
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Topics']", "xpath", 10);
			if (!result) {
				batchMsgList.add("Failed to close SubTopic screen.");
				return result;
			}
			cfObj.commonClick(
					cfObj.commonGetElement(driver, "//android.view.View[@content-desc='btn_appbar_back']", "xpath"));
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Chapters']", "xpath", 10);
			if (!result) {
				batchMsgList.add("Failed to close Topic screen.");
				return result;
			}

			cfObj.commonClick(
					cfObj.commonGetElement(driver, "//android.view.View[@content-desc='btn_appbar_back']", "xpath"));
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Subjects']", "xpath", 10);
			if (!result) {
				batchMsgList.add("Failed to close Chapter screen.");
				return result;
			}

		} catch (Exception e) {
			batchMsgList.add("validateVideoCourseSectionOnMahaPack_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}
}
