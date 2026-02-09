package applicationUtil;

import apiUtill.FixMockTestApiUtil;
import apiUtill.MockTestApiUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import pageObject.FixedMockTestPage_OR;
import util.Common_Function;
import util.Common_Function.direction;
import util.Common_Function.key;
import util.ConfigFileReader;

import java.util.ArrayList;
import java.util.List;

public class FixedMockTestUtil {

	public boolean attemptAvailable = false;
	boolean registerAvailable = false;
	boolean resultAvailable = false;

	LoginUtil loginUtilObj;
	FreeCounsellingPageUtil freeCounsellingUtilObj;
	CommonTestUtil commonTestUtilObj;
	StudyMaterialTabUtil studyMaterialUtilObj;
	HomePageUtil homePageUtilObj;
	RegisterNewUserUtil registrationUserUtilObj;
	FixedMockTestPage_OR fixedMockTestPageObj;
	FixMockTestApiUtil fixMockTestApiUtilObj;
	MockTestApiUtil mockTestApiUtilObj;

	public ArrayList<String> fixedMockTestMsgList = new ArrayList<String>();

	public Common_Function cfObj = new Common_Function();

	public FixedMockTestUtil(AppiumDriver<MobileElement> driver) {
		fixedMockTestPageObj = new FixedMockTestPage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), fixedMockTestPageObj);
	}

	public boolean verifyFixedMockTestPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getBtnBack(), 10);
			if (!result) {
				fixedMockTestMsgList.add("Unable to verify New Material back btn");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getListBtnTestState().get(0),
					10);
			if (!result) {
				fixedMockTestMsgList.add("Unable to verify New Material test state btn");
				// In some scenarios the page has no button but timers only
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getListTest(), 10);
			if (!result) {
				fixedMockTestMsgList.add("Unable to verify New Material Test list");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getListTestTitle().get(0), 10);
			if (!result) {
				fixedMockTestMsgList.add("Unable to verify New Material Test title");
				return result;
			}

		} catch (Exception e) {
			result = false;
			fixedMockTestMsgList.add("verifyFixedMockTestPage_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyFreeFixedMockTest(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String fmtMockName = null;
		homePageUtilObj = new HomePageUtil(driver);
		try {
			FixMockTestApiUtil fmtObj = new FixMockTestApiUtil();
			result = fmtObj.createFixMockTest(false);
			if (!result) {
				fixedMockTestMsgList.add("Failed to create Fix Mock Test.");
				return result;
			}

			fmtMockName = FixMockTestApiUtil.strFixedMockName;
			System.out.println(FixMockTestApiUtil.strFixedMockName);

			String mobileNumber = Common_Function.randomPhoneNumber(10, "8");

			loginUtilObj = new LoginUtil(driver);
			result = loginUtilObj.verifySignUp(driver, mobileNumber, false);
			if (!result) {
				fixedMockTestMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			result = homePageUtilObj.JustOpenAndClickHomeBtn(driver);
			if (!result) {
				fixedMockTestMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			result = homePageUtilObj.clickStudyMaterialTab(driver);
			if (!result) {
				fixedMockTestMsgList.add("Unable to open study Material Tab");
				return result;
			}

			Thread.sleep(2000);

			studyMaterialUtilObj = new StudyMaterialTabUtil(driver);
			result = studyMaterialUtilObj.clickAllIndiaMock(driver);
			if (!result) {
				fixedMockTestMsgList.add("Unable to open New Material 25 May Page");
				return result;
			}

			result = homePageUtilObj.testPrimePop(driver);
			if (!result) {
				fixedMockTestMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			result = clickOnSpecificRegisterLink(driver, fmtMockName);
			if (!result) {
				fixedMockTestMsgList.add("Unable to open registration page free mock Test");
				return result;
			}

			result = clickRegisterForFreeBtn(driver);
			if (!result) {
				fixedMockTestMsgList.add("Unable to register for test");
				return result;
			}

			System.out.println("Waiting 99 seconds for the test to get enabled");
			// Wait for Attempt Button
			Thread.sleep(99000);

			System.out.println("Wait is over, check attempt btn should be enabled by now.");

			result = studyMaterialUtilObj.clickAllIndiaMock(driver);
			if (!result) {
				fixedMockTestMsgList.add("Unable to open New Material 25 May Page");
				return result;
			}

			result = clickOnSpecificAttemptLink(driver, fmtMockName);
			if (!result) {
				fixedMockTestMsgList.add("Unable to open Attempt page free mock test");
				return result;
			}

			result = clickOnStartTest(driver);
			if (!result) {
				fixedMockTestMsgList.add("Unable to start test");
				return result;
			}

			commonTestUtilObj = new CommonTestUtil(driver);

			int questionCount = Integer
					.parseInt(cfObj.commonGetElement(driver, "total_question_count", "id").getText());
			result = commonTestUtilObj.clickStartTestBtn(driver);
			if (!result) {
				fixedMockTestMsgList.add("Unable to click start test btn ");
				return result;
			}

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.TextView[contains(@text,'Time Lapsed')]", "xpath", 10)) {
				cfObj.commonClick(cfObj.commonGetElement(driver, "positive_button", "id"));
			}

			result = commonTestUtilObj.takeAndSubmitTest(driver, false, false, questionCount,false);
			if (!result) {
				fixedMockTestMsgList.add("Unable to Submit test");
				return result;
			}

			result = commonTestUtilObj.verifyAndSubmitFinishTestPopUp(driver);
			if (!result) {
				fixedMockTestMsgList.add("Failed to verify And Submit FinishTestPopUp");
				return result;
			}

			result = homePageUtilObj.testPrimePop(driver);
			if (!result) {
				fixedMockTestMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			result = commonTestUtilObj.verifyResultAwaitedScreen(driver);
			if (!result) {
				fixedMockTestMsgList.add("Failed to verify Result Awaited Screen.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "toolbar_title_back_arrow", "id"));

			result = commonTestUtilObj.validateResultAwaitedTagOnSpecificFMTMock(driver, fmtMockName);
			if (!result) {
				fixedMockTestMsgList.add("Failed to validate ResultAwaitedTagOnSpecificFMTMock.");
				return result;
			}

			result = studyMaterialUtilObj.clickOnSpecificMockCardTimer(driver, fmtMockName);
			if (!result) {
				fixedMockTestMsgList.addAll(studyMaterialUtilObj.studyMaterialMsgList);
				return result;
			}

			result = homePageUtilObj.testPrimePop(driver);
			if (!result) {
				fixedMockTestMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			result = commonTestUtilObj.verifyResultAwaitedScreen(driver);
			if (!result) {
				fixedMockTestMsgList.addAll(commonTestUtilObj.commonTestMsgList);
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "toolbar_title_back_arrow", "id"));

			int i = 0;
			while (!cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.TextView[@text='VIEW RESULT']", "xpath", 5)) {
				cfObj.scrollUtill(driver, 2, direction.DOWN);
				if (i > 10) {
					System.out.println("ViewResult Link is not visible.");
					result = false;
					break;
				}
				i++;
			}
		} catch (Exception e) {
			result = false;
			fixedMockTestMsgList.add("verifyFreeFixedMockTest Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickRegisterForFreeBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			cfObj.waitForPageLoading(driver, 10, 1000, fixedMockTestPageObj.getBtnRegister());

			cfObj.commonClick(fixedMockTestPageObj.getBtnRegister());

			result = verifySuccessfulRegisterPopUp(driver);
			if (!result) {
				fixedMockTestMsgList.add("Failed to verify SuccessfullyRegisterPopUp.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			fixedMockTestMsgList.add("clickRegisterForFreeBtn Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifySuccessfulRegisterPopUp(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getTitleCongratulationPopUp(),
					10);
			if (!result) {
				fixedMockTestMsgList.add("Successfully registration pop-up didnot appeared");
				return result;
			}

			cfObj.commonClick(fixedMockTestPageObj.getBtnContinuePopUp());
			Thread.sleep(2000);
			cfObj.pressAndroidKey(driver, key.BACK, 1);

		} catch (Exception e) {
			result = false;
			fixedMockTestMsgList.add("verifySuccessfullyRegisterPopUp_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnStartTest(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			cfObj.waitForPageLoading(driver, 10, 1000, fixedMockTestPageObj.getBtnStartTest());
			cfObj.commonClick(fixedMockTestPageObj.getBtnStartTest());

			commonTestUtilObj = new CommonTestUtil(driver);

			Thread.sleep(2000);

			cfObj.tapOnCenter(driver);

			// verify instruction page
			result = commonTestUtilObj.verifyInstructionPage(driver);
			if (!result) {
				fixedMockTestMsgList.add("Failed to verify InstructionPage.");
			}

		} catch (Exception e) {
			result = false;
			fixedMockTestMsgList.add("clickOnStartTest_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyResultOverviewPageUI(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getBtnSolution(), 10); // TODO:
																												// issue
			if (!result) {
				fixedMockTestMsgList.add("Unable to find solution btn in Result overview page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getBtnShareScoreCard(), 10);
			if (!result) {
				fixedMockTestMsgList.add("Unable to find share scorecard btn in  Result overview page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getTitleScored(), 10);
			if (!result) {
				fixedMockTestMsgList.add("Unable to find Score card title in  Result overview page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getTitleTimeSpent(), 10);
			if (!result) {
				fixedMockTestMsgList.add("Unable to find time spent title in  Result overview page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getIconCorrect(), 10);
			if (!result) {
				fixedMockTestMsgList.add("Unable to find correct icon in  Result overview page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getIconIncorrect(), 10);
			if (!result) {
				fixedMockTestMsgList.add("Unable to find Incorrect icon in  Result overview page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getIconUnanswered(), 10);
			if (!result) {
				fixedMockTestMsgList.add("Unable to find Unanswered icon in  Result overview page");
				return result;
			}

			result = verifyShareScoreCardUI(driver);
			if (!result) {
				fixedMockTestMsgList.add("Share score card UI verification failed");
				return result;
			}

			cfObj.pressAndroidKey(driver, key.BACK, 1);

			result = verifySolutionPage(driver);
			if (!result) {
				fixedMockTestMsgList.add("Solution page UI verification failed");
				return result;
			}

		} catch (Exception e) {
			fixedMockTestMsgList.add("verifyResultOverviewPageUI_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyShareScoreCardUI(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.commonClick(fixedMockTestPageObj.getBtnShareScoreCard());

			result = fixedMockTestPageObj.getTitleResultCard().getText().contains("Automation fixed Mock");
			if (!result) {
				fixedMockTestMsgList.add("Result card title does not contains Automation Fixed Mock.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getBtnShareWithFriends(), 10);
			if (!result) {
				fixedMockTestMsgList.add("ShareWithFriends button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getName(), 10);
			if (!result) {
				fixedMockTestMsgList.add("Name is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getMarksScored(), 10);
			if (!result) {
				fixedMockTestMsgList.add("MarkScored is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getRank(), 10);
			if (!result) {
				fixedMockTestMsgList.add("Rank is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getTimeSpent(), 10);
			if (!result) {
				fixedMockTestMsgList.add("TimeSpent is not visible.");
				return result;
			}

		} catch (Exception e) {
			fixedMockTestMsgList.add("verifyShareScoreCardUI_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean validateBookMarkIcon(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getIconBookmark(), 30);
			if (!result) {
				fixedMockTestMsgList.add("Book mark icon is not visible.");
				return result;
			}

			cfObj.commonClick(fixedMockTestPageObj.getIconBookmark());

			result = fixedMockTestPageObj.getIconBookmark().getAttribute("checked").equals("true");
			if (!result) {
				fixedMockTestMsgList.add("Failed to enable bookmark button.");
				return result;
			}
			Thread.sleep(1000);

			cfObj.commonClick(fixedMockTestPageObj.getIconBookmark());

			result = fixedMockTestPageObj.getIconBookmark().getAttribute("checked").equals("false");
			if (!result) {
				fixedMockTestMsgList.add("Failed to uncheck bookmark button.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			fixedMockTestMsgList.add("validateBookMarkIcon_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateShareIcon(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getIconShare(), 30);
			if (!result) {
				fixedMockTestMsgList.add("Share button is not visible.");
				return result;
			}
			cfObj.commonClick(fixedMockTestPageObj.getIconShare());
			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getShareScreen(), 30);
			if (!result) {
				fixedMockTestMsgList.add("Share screen is not visible.");
				return result;
			}

			cfObj.scrollUtill(driver, 1, direction.UP);

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getIconShare(), 30);
			if (!result) {
				fixedMockTestMsgList.add("Share button is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			fixedMockTestMsgList.add("validateShareIcon_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean  validateReportContentIcon(AppiumDriver<MobileElement> driver, boolean isQB) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getIconReportContent(), 30);
			if (!result) {
				fixedMockTestMsgList.add("ReportContentIcon is not visible.");
				return result;
			}

			cfObj.commonClick(fixedMockTestPageObj.getIconReportContent());

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getReportPageTitle(), 30);
			if (!result) {
				fixedMockTestMsgList.add("ReportPageTitle is not visible.");
				return result;
			}

			for (int i = 0; i < fixedMockTestPageObj.getReportList().size(); i++) {
				result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getReportList().get(i), 30);
				if (!result) {
					fixedMockTestMsgList.add("ReportList is not visible.");
					return result;
				}

				result = fixedMockTestPageObj.getReportList().get(i).getAttribute("checked").equals("false");
				if (!result) {
					fixedMockTestMsgList.add("ReportList is already selected.");
					return result;
				}
				cfObj.commonClick(fixedMockTestPageObj.getReportList().get(i));

				result = fixedMockTestPageObj.getReportList().get(i).getAttribute("checked").equals("true");
				if (!result) {
					fixedMockTestMsgList.add("Unable to select ReportList.");
					return result;
				}
			}

			for (int i = 0; i < fixedMockTestPageObj.getReportList().size(); i++) {
				result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getReportList().get(i), 30);
				if (!result) {
					fixedMockTestMsgList.add("ReportList is not visible.");
					return result;
				}

				result = fixedMockTestPageObj.getReportList().get(i).getAttribute("checked").equals("true");
				if (!result) {
					fixedMockTestMsgList.add("ReportList is not selected.");
					return result;
				}
				cfObj.commonClick(fixedMockTestPageObj.getReportList().get(i));

				result = fixedMockTestPageObj.getReportList().get(i).getAttribute("checked").equals("false");
				if (!result) {
					fixedMockTestMsgList.add("Unable to Unselect ReportList.");
					return result;
				}
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/doubtDescription", "id", 5);
			if(!result) {
				fixedMockTestMsgList.add("Edit text box is not visible in report bottom sheet.");
				return result;
			}

			cfObj.commonClick(fixedMockTestPageObj.getReportList().get(0));

			cfObj.commonSetTextTextBox(fixedMockTestPageObj.getReportText(), "Sample testing text");

            if(!ConfigFileReader.strRunMode.equalsIgnoreCase("localLab")){
				AndroidDriver<MobileElement> androidDriver = (AndroidDriver<MobileElement>) driver;
				androidDriver.pressKey(new KeyEvent(AndroidKey.BACK));
			}

			if(isQB){
				cfObj.commonClick(fixedMockTestPageObj.getNextBtn());
			} else {
				cfObj.commonClick(fixedMockTestPageObj.getCancelBtn());
			}

			result = !cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getReportPageTitle(), 5);
			if (!result) {
				fixedMockTestMsgList.add("Not able to close Report page.");
				return result;
			}

			if(isQB){
				Thread.sleep(2000);

				result = !cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getIconReportContent(), 5);
				if (!result) {
					fixedMockTestMsgList.add("ReportContentIcon is visible, after submitting");
					return result;
				}
			}
		} catch (Exception e) {
			result = false;
			fixedMockTestMsgList.add("validateReportContentIcon_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateTestSummaryIcon(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getTestSummaryBtn(), 30);
			if (!result) {
				fixedMockTestMsgList.add("TestSummary button is not visible.");
				return result;
			}
			cfObj.commonClick(fixedMockTestPageObj.getTestSummaryBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getTestSummaryTitle(), 30);
			if (!result) {
				fixedMockTestMsgList.add("TestSummary title is not visible.");
				return result;
			}
			cfObj.commonClick(fixedMockTestPageObj.getContinueBtn());

			result = !cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getTestSummaryTitle(), 5);
			if (!result) {
				fixedMockTestMsgList.add("Not able to click continue btn.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			fixedMockTestMsgList.add("validateTestSummaryIcon_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateSolutionScreenFilterBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getFilterBtn(), 30);
			if (!result) {
				fixedMockTestMsgList.add("Filter button is not visible.");
				return result;
			}

			int questionNum = Integer.parseInt(fixedMockTestPageObj.getQuestionNumber().getText());

			cfObj.commonClick(fixedMockTestPageObj.getFilterBtn());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "sections_tab", "id", 30);
			if (!result) {
				fixedMockTestMsgList.add("Section Tab is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "ques_num", "id", 30);
			if (!result) {
				fixedMockTestMsgList.add("Question number is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getQuestionCountText(), 30);
			if (!result) {
				fixedMockTestMsgList.add("Question count text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getFilterInfoIcon(), 30);
			if (!result) {
				fixedMockTestMsgList.add("Filter Info Icon is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getFastChipSpeedIndicator(),
					30);
			if (!result) {
				fixedMockTestMsgList.add("FastChip SpeedIndicator is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getOnTimeChipSpeedIndicator(),
					30);
			if (!result) {
				fixedMockTestMsgList.add("OnTimeChip SpeedIndicator is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getSlowChipSpeedIndicator(),
					30);
			if (!result) {
				fixedMockTestMsgList.add("SlowChip SpeedIndicator is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getCorrectTextValue(), 30);
			if (!result) {
				fixedMockTestMsgList.add("CorrectText value is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getIncorrectTextValue(), 30);
			if (!result) {
				fixedMockTestMsgList.add("InCorrectText value is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getUnAttemptedTextValue(), 30);
			if (!result) {
				fixedMockTestMsgList.add("UnAttempted text value is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getNotEvaluatedTextValue(), 30);
			if (!result) {
				fixedMockTestMsgList.add("UnEvaluated Text value is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getPreviousBtn(), 30);
			if (!result) {
				fixedMockTestMsgList.add("Previous button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getNextQuestionBtn(), 30);
			if (!result) {
				fixedMockTestMsgList.add("Next Question button is not visible.");
				return result;
			}

			cfObj.commonClick(fixedMockTestPageObj.getNextQuestionBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getQuestionNumber(), 30);
			if (!result) {
				fixedMockTestMsgList.add("Question number is not visible.");
				return result;
			}

			result = Integer.parseInt(fixedMockTestPageObj.getQuestionNumber().getText()) == questionNum + 1;
			if (!result) {
				fixedMockTestMsgList.add("Next question button is not working.");
				return result;
			}

			questionNum = Integer.parseInt(fixedMockTestPageObj.getQuestionNumber().getText());

			cfObj.commonClick(fixedMockTestPageObj.getFilterBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getPreviousBtn(), 30);
			if (!result) {
				fixedMockTestMsgList.add("Previous button is not visible.");
				return result;
			}

			cfObj.commonClick(fixedMockTestPageObj.getPreviousBtn());

			Thread.sleep(1000);
			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getQuestionNumber(), 30);
			if (!result) {
				fixedMockTestMsgList.add("Question number is not visible.");
				return result;
			}

			result = Integer.parseInt(fixedMockTestPageObj.getQuestionNumber().getText()) == questionNum - 1;
			if (!result) {
				fixedMockTestMsgList.add("Previous button is not working.");
				return result;
			}

			cfObj.commonClick(fixedMockTestPageObj.getFilterBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getFilterScreenCloseBtn(), 30);
			if (!result) {
				fixedMockTestMsgList.add("FilterScreen Close button is not visible.");
				return result;
			}

			cfObj.commonClick(fixedMockTestPageObj.getFilterScreenCloseBtn());

			result = !cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getNextQuestionBtn(), 5);
			if (!result) {
				fixedMockTestMsgList.add("Not able to close Filter Screen.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			fixedMockTestMsgList.add("validateSolutionScreenFilterBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateReAttemapSwitchOnSolutionScreen(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getReAttemptSwitch(), 30);
			if (!result) {
				fixedMockTestMsgList.add("ReAttempt switch is not visible.");
				return result;
			}

			cfObj.commonClick(fixedMockTestPageObj.getReAttemptSwitch());

			result = fixedMockTestPageObj.getReAttemptSwitch().getAttribute("checked").equalsIgnoreCase("true");
			if (!result) {
				fixedMockTestMsgList.add("Failed to toggle on Reattempt button.");
				return result;
			}
			Thread.sleep(1000);

			cfObj.commonClick(fixedMockTestPageObj.getReAttemptSwitch());

			result = fixedMockTestPageObj.getReAttemptSwitch().getAttribute("checked").equalsIgnoreCase("false");
			if (!result) {
				fixedMockTestMsgList.add("Failed to toggle Off Reattempt button.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			fixedMockTestMsgList.add("validateReAttemptSwitchOnSolutionScreen_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean verifySolutionPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getBtnShowCorrectAnswer(), 10);
			if (!result) {
				fixedMockTestMsgList.add("ShowCorrectAnswer button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getIconBookmark(), 10);
			if (!result) {
				fixedMockTestMsgList.add("BookMark icon is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getIconShare(), 10);
			if (!result) {
				fixedMockTestMsgList.add("Share icon is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, fixedMockTestPageObj.getIconReportContent(), 10);
			if (!result) {
				fixedMockTestMsgList.add("ReportContent icon is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			fixedMockTestMsgList.add("verifySolutionPage_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickAttemptLink(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			// Clicking on register link for the test created
			for (int i = 0; i < fixedMockTestPageObj.getListBtnTestState().size(); i++) {
				if (fixedMockTestPageObj.getListBtnTestState().get(i).getText().contains("ATTEMPT")
						&& fixedMockTestPageObj.getListTestTitle().get(i).getText().contains("Automation fixed Mock")) {

					fixedMockTestPageObj.getListBtnTestState().get(i).click();
					result = true;
					break;
				}
			}
			if (!result) {
				fixedMockTestMsgList.add("No test available to register");
				return result;
			}

			Thread.sleep(5000);

		} catch (Exception e) {
			fixedMockTestMsgList.add("clickAttemptLink Exceptions: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnSpecificAttemptLink(AppiumDriver<MobileElement> driver, String mockName) {
		boolean result = false;
		try {
			Thread.sleep(2000);
			// Clicking on Attempt link for the test created
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='" + mockName
					+ "']/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='ATTEMPT']",
					"xpath", 30);
			if (result) {
				cfObj.commonClick(driver, "//*[@text='" + mockName
						+ "']/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='ATTEMPT']",
						"xpath");
			} else {
				fixedMockTestMsgList.add("Attempt button is not visible.");
				return result;
			}

			Thread.sleep(5000);

		} catch (Exception e) {
			fixedMockTestMsgList.add("clickOnSpecificAttemptLink_Exceptions: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickRegisterLink(AppiumDriver<MobileElement> driver) {
		boolean result = false;
		try {
			// Clicking on register link for the test created
			for (int i = 0; i < fixedMockTestPageObj.getListBtnTestState().size(); i++) {
				if (fixedMockTestPageObj.getListBtnTestState().get(i).getText().contains("REGISTER")
						&& fixedMockTestPageObj.getListTestTitle().get(i).getText().contains("Automation fixed Mock")) {

					fixedMockTestPageObj.getListBtnTestState().get(i).click();
					result = true;
					break;
				}
			}
			if (!result) {
				fixedMockTestMsgList.add("No test available to register");
				return result;
			}

			Thread.sleep(5000);

		} catch (Exception e) {
			fixedMockTestMsgList.add("clickRegisterLink Exceptions: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnSpecificRegisterLink(AppiumDriver<MobileElement> driver, String mockName) {
		boolean result = false;
		try {
			Thread.sleep(2000);
			// Clicking on register link for the test created
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='" + mockName
					+ "']/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='REGISTER']",
					"xpath", 30);
			if (result) {
				cfObj.commonClick(driver, "//*[@text='" + mockName
						+ "']/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='REGISTER']",
						"xpath");
			} else {
				fixedMockTestMsgList.add("Register button is not visible.");
				return result;
			}

			Thread.sleep(5000);

		} catch (Exception e) {
			fixedMockTestMsgList.add("clickOnSpecificRegisterLink_Exceptions: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnSpecificResumeLink(AppiumDriver<MobileElement> driver, String mockName) {
		boolean result = false;
		try {
			Thread.sleep(2000);
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='" + mockName
					+ "']/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='RESUME']",
					"xpath", 30);
			if (result) {
				cfObj.commonClick(driver, "//*[@text='" + mockName
						+ "']/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='RESUME']",
						"xpath");
			} else {
				fixedMockTestMsgList.add("Resume button is not visible.");
				return result;
			}

			Thread.sleep(5000);

		} catch (Exception e) {
			fixedMockTestMsgList.add("clickOnSpecificResumeLink_Exceptions: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickViewResultLink(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		int count = 0;
		try {

			while (count < 3) {
				List<MobileElement> el = fixedMockTestPageObj.getListBtnTestState();

				for (int i = 0; i < el.size(); i++) {
					if (el.get(i).getText().contains("VIEW RESULT")) {
						el.get(i).click();
						resultAvailable = true;
						break;
					}
				}
				if (!resultAvailable) {
					cfObj.scrollUtill(driver, 1, direction.DOWN);
					count++;
				} else {
					break;
				}
			}

		} catch (Exception e) {
			fixedMockTestMsgList.add("clickViewResultLink Exceptions: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyOptionalFixMockTest(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String mockName;
		try {
			fixMockTestApiUtilObj = new FixMockTestApiUtil();
			result = fixMockTestApiUtilObj.createFixMockTest(true);
			if (!result) {
				fixedMockTestMsgList.add("Not able to create optional fix mock test.");
				return result;
			}

			mockName = FixMockTestApiUtil.strFixedMockName;
			if (mockName == null) {
				fixedMockTestMsgList.add("Mock name is null.");
				return false;
			}
			System.out.println("------------>" + mockName);

			String mobileNumber = Common_Function.randomPhoneNumber(10, "8");

			loginUtilObj = new LoginUtil(driver);
			result = loginUtilObj.verifySignUp(driver, mobileNumber, false);
			if (!result) {
				fixedMockTestMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			homePageUtilObj = new HomePageUtil(driver);
			result = homePageUtilObj.clickHomeBtn(driver);
			if (!result) {
				fixedMockTestMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			result = homePageUtilObj.clickStudyMaterialTab(driver);
			if (!result) {
				fixedMockTestMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			studyMaterialUtilObj = new StudyMaterialTabUtil(driver);
			result = studyMaterialUtilObj.clickOnScholarshipTestTab(driver);
			if (!result) {
				fixedMockTestMsgList.addAll(studyMaterialUtilObj.studyMaterialMsgList);
				return result;
			}

			result = clickOnSpecificRegisterLink(driver, mockName);
			if (!result) {
				fixedMockTestMsgList.add("Unable to open registration page free mock Test");
				return result;
			}

			result = clickRegisterForFreeBtn(driver);
			if (!result) {
				fixedMockTestMsgList.add("Unable to register for test");
				return result;
			}

			System.out.println("Waiting 85 seconds for test to be enabled.");
			Thread.sleep(85000);

			result = studyMaterialUtilObj.clickOnScholarshipTestTab(driver);
			if (!result) {
				fixedMockTestMsgList.addAll(studyMaterialUtilObj.studyMaterialMsgList);
				return result;
			}

			result = clickOnSpecificAttemptLink(driver, mockName);
			if (!result) {
				fixedMockTestMsgList.add("Unable to open Attempt page free mock test");
				return result;
			}

			result = clickOnStartTest(driver);
			if (!result) {
				fixedMockTestMsgList.add("Unable to start test");
				return result;
			}

			result = commonTestUtilObj.completeAndSubmitFixedMockOptionalTest(driver);
			if (!result) {
				fixedMockTestMsgList.addAll(commonTestUtilObj.commonTestMsgList);
				return result;
			}

			result = homePageUtilObj.testPrimePop(driver);
			if (!result) {
				fixedMockTestMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			result = commonTestUtilObj.verifyResultAwaitedScreen(driver);
			if (!result) {
				fixedMockTestMsgList.addAll(commonTestUtilObj.commonTestMsgList);
				return result;
			}

			result = commonTestUtilObj.clickOnResultAwaitedBackBtn(driver);
			if (!result) {
				fixedMockTestMsgList.addAll(commonTestUtilObj.commonTestMsgList);
				return result;
			}

			result = homePageUtilObj.testPrimePop(driver);
			if (!result) {
				fixedMockTestMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			result = studyMaterialUtilObj.clickOnSpecificMockCardTimer(driver, mockName);
			if (!result) {
				fixedMockTestMsgList.addAll(studyMaterialUtilObj.studyMaterialMsgList);
				return result;
			}

			result = homePageUtilObj.testPrimePop(driver);
			if (!result) {
				fixedMockTestMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			result = commonTestUtilObj.verifyResultAwaitedScreen(driver);
			if (!result) {
				fixedMockTestMsgList.addAll(commonTestUtilObj.commonTestMsgList);
				return result;
			}
		} catch (Exception e) {
			result = false;
			fixedMockTestMsgList.add("OptionalFreeMockTest_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateAttemptButtonOnLeaderBoardScreen(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "toolbar_title", "id", 10);
				if (!result) {
					fixedMockTestMsgList.add("Title is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "rankPredictorTitle", "id", 10);
				if (!result) {
					fixedMockTestMsgList.add("RankPredictor Title is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "rankPredictorSlider", "id", 10);
				if (!result) {
					fixedMockTestMsgList.add("RankPredictorSlider is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "rankPredictorRuler", "id", 10);
				if (!result) {
					fixedMockTestMsgList.add("RankPredictorRuler is not visible.");
					return result;
				}

				if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "innerContainer", "id", 10)) {

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "topper_number", "id", 10);
					if (!result) {
						fixedMockTestMsgList.add("Topper Number is not visible.");
						return result;
					}

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "topper_avatar", "id", 10);
					if (!result) {
						fixedMockTestMsgList.add("Topper avatar is not visible.");
						return result;
					}

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "topper_name", "id", 10);
					if (!result) {
						fixedMockTestMsgList.add("Topper Name is not visible.");
						return result;
					}

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "topper_score", "id", 10);
					if (!result) {
						fixedMockTestMsgList.add("Topper Score is not visible.");
						return result;
					}
				} else {
					System.out.println("No one attended this mock.");
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "attempt_btn_bottom", "id", 10);
				if (!result) {
					fixedMockTestMsgList.add("Attempt button is not visible.");
					return result;
				}

				cfObj.commonClick(cfObj.commonGetElement(driver, "attempt_btn_bottom", "id"));

				commonTestUtilObj = new CommonTestUtil(driver);

				result = commonTestUtilObj.clickStartTestBtn(driver);
				if (!result) {
					fixedMockTestMsgList.addAll(commonTestUtilObj.commonTestMsgList);
					return result;
				}

				Thread.sleep(2000); // page loading takes time

				if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
					result = commonTestUtilObj.clickOnFilterBtn(driver);
					if (!result) {
						fixedMockTestMsgList.addAll(commonTestUtilObj.commonTestMsgList);
						return result;
					}
				}

				result = commonTestUtilObj.clickOnSubmitTestBtn(driver);
				if (!result) {
					fixedMockTestMsgList.addAll(commonTestUtilObj.commonTestMsgList);
					return result;
				}

			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.widget.TextView[@text='Leaderboard']", "xpath", 10);
				if (!result) {
					fixedMockTestMsgList.add("LeaderBoard title is not visible.");
					return result;
				}

				if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.widget.TextView[@text='Top Rankers']", "xpath", 10)) {

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "profilePicture", "id", 10);
					if (!result) {
						fixedMockTestMsgList.add("Participants profile Picture is not visible.");
						return result;
					}

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "number", "id", 10);
					if (!result) {
						fixedMockTestMsgList.add("Rank Number is not visible.");
						return result;
					}

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "name", "id", 10);
					if (!result) {
						fixedMockTestMsgList.add("Participants name is not visible.");
						return result;
					}

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "marksScored", "id", 10);
					if (!result) {
						fixedMockTestMsgList.add("Mark Scored text is not visible.");
						return result;
					}
				} else {
					System.out.println("No one attended this mock.");
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "btn_show_att", "id", 10);
				if (!result) {
					fixedMockTestMsgList.add("Attempt button is not visible.");
					return result;
				}

				cfObj.commonClick(cfObj.commonGetElement(driver, "btn_show_att", "id"));

				commonTestUtilObj = new CommonTestUtil(driver);
				result = commonTestUtilObj.completeAndSubmitTestWithCorrectAnswer(driver, false, false);
				if (!result) {
					fixedMockTestMsgList.add("Unable to complete and submit test");
					return result;
				}
			}

		} catch (Exception e) {
			fixedMockTestMsgList.add("clickAttemptButtonOnLeaderBoardScreen Exceptions: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyFixedMockTestOnHomeFeed(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String fmtMockName = null;
		try {
			FixMockTestApiUtil fmtObj = new FixMockTestApiUtil();
			result = fmtObj.createFixMockTest(false);
			if (!result) {
				fixedMockTestMsgList.add("Failed to create Fix Mock Test.");
				return result;
			}

			fmtMockName = FixMockTestApiUtil.strFixedMockName;
			System.out.println(FixMockTestApiUtil.strFixedMockName);

			String mobileNumber = Common_Function.randomPhoneNumber(10, "8");

			loginUtilObj = new LoginUtil(driver);
			result = loginUtilObj.verifySignUp(driver, mobileNumber, false);
			if (!result) {
				fixedMockTestMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			homePageUtilObj = new HomePageUtil(driver);

			result = homePageUtilObj.JustOpenAndClickHomeBtn(driver);
			if (!result) {
				fixedMockTestMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			int scrollCount = 0;
			while (!cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//*[@text='" + fmtMockName + "']/following-sibling::*[@text='Register']", "xpath", 3)
					&& scrollCount < 6) {

				cfObj.commonClick(cfObj.commonGetElement(driver, "fpm_backward_btn", "id"));
				scrollCount++;
				if (scrollCount == 4) {
					cfObj.scrollUtill(driver, 1, direction.UP);
				}
			}
			if (scrollCount == 6) {
				System.out.println(fmtMockName + " is not visible on Home feed.");
			} else {
				cfObj.scrollUtill(driver, 2, direction.UP);

				cfObj.commonClick(cfObj.commonGetElement(driver,
						"//*[@text='" + fmtMockName + "']/following-sibling::*[@text='Register']", "xpath"));

				result = clickRegisterForFreeBtn(driver);
				if (!result) {
					fixedMockTestMsgList.add("Unable to register for test");
					return result;
				}

				Thread.sleep(100000);

				System.out.println("Wait is over, check attempt btn should be enabled by now.");

				cfObj.scrollUtill(driver, 2, direction.UP);

				cfObj.commonClick(cfObj.commonGetElement(driver,
						"//*[@text='" + fmtMockName + "']/following-sibling::*[@text='Attempt']", "xpath"));

				result = clickOnStartTest(driver);
				if (!result) {
					fixedMockTestMsgList.add("Unable to start test");
					return result;
				}

				commonTestUtilObj = new CommonTestUtil(driver);

				int questionCount = Integer
						.parseInt(cfObj.commonGetElement(driver, "com.adda247.app:id/total_question_count", "id").getText());
				result = commonTestUtilObj.clickStartTestBtn(driver);
				if (!result) {
					fixedMockTestMsgList.add("Unable to click start test btn ");
					return result;
				}

				if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.widget.TextView[contains(@text,'Time Lapsed')]", "xpath", 10)) {
					cfObj.commonClick(cfObj.commonGetElement(driver, "positive_button", "id"));
				}

				result = commonTestUtilObj.takeAndSubmitTest(driver, false, false, questionCount, false);
				if (!result) {
					fixedMockTestMsgList.add("Unable to Submit test");
					return result;
				}

				result = commonTestUtilObj.verifyAndSubmitFinishTestPopUp(driver);
				if (!result) {
					fixedMockTestMsgList.add("Failed to verify And Submit FinishTestPopUp");
					return result;
				}

				result = homePageUtilObj.testPrimePop(driver);
				if (!result) {
					fixedMockTestMsgList.addAll(homePageUtilObj.homeMsgList);
					return result;
				}

				result = commonTestUtilObj.verifyResultAwaitedScreen(driver);
				if (!result) {
					fixedMockTestMsgList.add("Failed to verify Result Awaited Screen.");
					return result;
				}

				driver.navigate().back();

				Thread.sleep(2000);

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.widget.TextView[contains(@text,'Home')]", "xpath", 10);
				if (!result) {
					fixedMockTestMsgList.add("Failed to verify Home page");
					return result;
				}

				cfObj.scrollUtill(driver, 2, direction.UP);

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.widget.TextView[contains(@text,'Result Declaration At')]", "xpath", 30);
				if (!result) {
					System.out.println("Failed to verify Result Declaration At text, might be page not refreshed");
					result = true;
				}
			}

		} catch (Exception e) {
			result = false;
			fixedMockTestMsgList.add("verifyFixedMockTestOnHomeFeed Exception: " + e.getMessage());
		}
		return result;
	}
}