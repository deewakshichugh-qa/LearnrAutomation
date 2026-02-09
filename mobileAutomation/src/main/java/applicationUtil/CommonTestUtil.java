package applicationUtil;

import java.util.ArrayList;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.support.PageFactory;

import applicationUtil.CommunityPageUtil.PostType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.CommonTestPage_OR;
import util.Common_Function;
import util.Common_Function.direction;
import util.Common_Function.key;
import util.ConfigFileReader;

public class CommonTestUtil {

	CommonTestPage_OR commonTestPageObj;
	ChildPackageUtil childPackageUtilObj;
	FixedMockTestUtil fixedMockTestUtilObj;
	CommunityPageUtil communityPageUtilObj;
	PostingPageUtil postingPageUtilObj;
	public ArrayList<String> commonTestMsgList = new ArrayList<>();
	public Common_Function cfObj = new Common_Function();

	public CommonTestUtil(AppiumDriver<MobileElement> driver) {
		commonTestPageObj = new CommonTestPage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), commonTestPageObj);
	}

	public boolean validateCutOffMarksInstruction(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getCutOffMarksInstruction(), 10);
			if (!result) {
				commonTestMsgList.add("CutOffMarksInstruction is not visible.");
				return result;
			}

			result = cfObj.commonVerifyValueTextBox(commonTestPageObj.getCutOffMarksInstruction(), "60");
			if (!result) {
				commonTestMsgList.add("CutOff mark-60 is not present.");
				return result;
			}

		} catch (Exception e) {
			commonTestMsgList.add("validateCutOffMarksInstruction_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyInstructionPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.waitForPageLoading(driver, 4, 2000, commonTestPageObj.getBtnStartTest());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/total_question_count", "id", 15);
			if (!result) {
				commonTestMsgList.add("Total question count is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/max_marks_count", "id", 15);
			if (!result) {
				commonTestMsgList.add("Maximum mark count is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/duration_count", "id", 15);
			if (!result) {
				commonTestMsgList.add("Test duration count is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnStartTest(), 10);
			if (!result) {
				commonTestMsgList.add("Unable to verify start test btn in Instruction page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTextTestLanguage(), 10);
			if (!result) {
				commonTestMsgList.add("Unable to verify Test language title in Instruction page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getIconClose(), 10);
			if (!result) {
				commonTestMsgList.add("Unable to verify close icon in Instruction page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTitleTest(), 10);
			if (!result) {
				commonTestMsgList.add("Unable to verify Test title in Instruction page");
				return result;
			}

		} catch (Exception e) {
			commonTestMsgList.add("verifyInstructionPage_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnLanguageTabAndSelectLanguage(AppiumDriver<MobileElement> driver, int index) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTextTestLanguage(), 10);
			if (!result) {
				commonTestMsgList.add("Test Language tab is not visible.");
				return result;
			}
//			cfObj.commonClick(commonTestPageObj.getTextTestLanguage());
//
//			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getLanguageList().get(index), 30);
//			if (!result) {
//				commonTestMsgList.add("Language list is not visible.");
//				return result;
//			}
//
//			for (int i = 0; i < commonTestPageObj.getLanguageList().size(); i++) {
//
//				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getLanguageList().get(i), 30);
//				if (!result) {
//					commonTestMsgList.add("Language list is not visible.");
//					return result;
//				}
//
//				String language = commonTestPageObj.getLanguageList().get(i).getText();
//				if (language == null) {
//					commonTestMsgList.add("Language text is null.");
//					return false;
//				}
//			}
//
//			cfObj.commonClick(commonTestPageObj.getLanguageList().get(index));
//			Thread.sleep(2000);

		} catch (Exception e) {
			commonTestMsgList.add("clickOnLanguageTabAndSelectLanguage_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickStartTestBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnStartTest(), 10);
			if (!result) {
				commonTestMsgList.add("getBtnStartTest is not visible.");
				return result;
			}

			cfObj.commonClick(commonTestPageObj.getBtnStartTest());

		} catch (Exception e) {
			commonTestMsgList.add("clickStartTestBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickSubmitTestBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnSubmitTest(), 10);
			if (!result) {
				commonTestMsgList.add("Submit button is not visible.");
				return result;
			}

			cfObj.commonClick(commonTestPageObj.getBtnSubmitTest());

			result = verifyAndSubmitFinishTestPopUp(driver);
			if (!result) {
				commonTestMsgList.add("Not able to verify And  SubmitFinishTestPopUp.");
				return result;
			}

		} catch (Exception e) {
			commonTestMsgList.add("clickSubmitTestBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickGotItSwipeInfoLayer(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			if (cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.GetLinkGotItSwipeInfoLayer(), 10)) {

				cfObj.commonClick(commonTestPageObj.GetLinkGotItSwipeInfoLayer());
			} else {
				System.out.println("GotItSwipeInfoLayer is not present.");
			}

		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("clickGotItSwipeInfoLayer Exception:" + e.getMessage());
		}
		return result;
	}

	public boolean verifyTakeTestPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnFilter(), 10);
			if (!result) {
				commonTestMsgList.add("Unable to verify Filter button");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnReview(), 10);
			if (!result) {
				commonTestMsgList.add("Unable to verify Review button");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTimer(), 10);
			if (!result) {
				commonTestMsgList.add("Unable to verify Timer button");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnSubmitTest(), 10);
			if (!result) {
				commonTestMsgList.add("Unable to verify Submit Test button");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnSaveAndNext(), 10);
			if (!result) {
				commonTestMsgList.add("Unable to verify Save And Next button");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getListAnswers().get(0), 10);
			if (!result) {
				commonTestMsgList.add("Unable to verify getOptionAnswer");
			}

		} catch (Exception e) {
			commonTestMsgList.add("verifyTakeTestPage_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean takeAndSubmitTest(AppiumDriver<MobileElement> driver, boolean calculatorStatus,
			boolean isMultilanguage, int questionCount, boolean isQB) {
		boolean result = true;
		boolean optionsVisible = false;
		try {

			for (int i = 1; i <= questionCount; i++) {
				if (calculatorStatus) {
					result = validateCalculatorButton(driver);
					if (!result) {
						commonTestMsgList.add("Not able to validate Calculator button.");
						return result;
					}
				}
				if (isMultilanguage && i == 0) {
					result = validateLanguageButton(driver);
					if (!result) {
						commonTestMsgList.add("Not able to validate Language button.");
						return result;
					}
				}
				optionsVisible = !commonTestPageObj.getListAnswers().isEmpty();
				while (!optionsVisible) {
					cfObj.scrollUtill(driver, 1, direction.DOWN);
					Thread.sleep(1000);
					optionsVisible = !commonTestPageObj.getListAnswers().isEmpty();
				}

				cfObj.commonClick(commonTestPageObj.getListAnswers().get(0));
				Thread.sleep(200);
				if (i == questionCount) {

					result = verifyReportIconWhenAttempting(driver, isQB);
					if (!result) {
						commonTestMsgList.add("Not able to verify Report icon.");
						return result;
					}

					result = commonTestPageObj.getBtnSaveAndNext().getText().contains("Submit");
					if (!result) {
						commonTestMsgList.add("Submit button is not visible.");
						return result;
					}
				}
				cfObj.commonClick(commonTestPageObj.getBtnSaveAndNext());
				Thread.sleep(200);
			}
		} catch (Exception e) {
			commonTestMsgList.add("takeAndSubmitTest_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	private boolean verifyReportIconWhenAttempting(AppiumDriver<MobileElement> driver, boolean isQB) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getReportIcon(), 20);
			if (!result) {
				commonTestMsgList.add("Unable to verify Report icon");
				return result;
			}
			cfObj.commonClick(commonTestPageObj.getReportIcon());

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getReportTitle(), 20);
			if (!result) {
				commonTestMsgList.add("Unable to verify Report popup title");
				return result;
			}

			for (int i = 0; i < commonTestPageObj.getReportLists().size(); i++) {

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getReportLists().get(i), 30);
				if (!result) {
					commonTestMsgList.add("ReportList is not visible.");
					return result;
				}

				result = commonTestPageObj.getReportLists().get(i).getAttribute("checked").equals("false");
				if (!result) {
					commonTestMsgList.add("ReportList is already selected.");
					return result;
				}
				cfObj.commonClick(commonTestPageObj.getReportLists().get(i));

				result = commonTestPageObj.getReportLists().get(i).getAttribute("checked").equals("true");
				if (!result) {
					commonTestMsgList.add("Unable to select ReportList.");
					return result;
				}
			}

			for (int i = 0; i < commonTestPageObj.getReportLists().size(); i++) {

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getReportLists().get(i), 30);
				if (!result) {
					commonTestMsgList.add("ReportList is not visible.");
					return result;
				}

				result = commonTestPageObj.getReportLists().get(i).getAttribute("checked").equals("true");
				if (!result) {
					commonTestMsgList.add("ReportList is not selected.");
					return result;
				}
				cfObj.commonClick(commonTestPageObj.getReportLists().get(i));

				result = commonTestPageObj.getReportLists().get(i).getAttribute("checked").equals("false");
				if (!result) {
					commonTestMsgList.add("Unable to Unselect ReportList.");
					return result;
				}
			}

			cfObj.commonClick(commonTestPageObj.getReportLists().get(0));

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getNextButton(), 10);
			if (!result) {
				commonTestMsgList.add("Unable to verify next btn");
				return result;
			}

			result = cfObj.commonSetTextTextBox(commonTestPageObj.getEnterDoubt(), "Sample Text to verification");
			if (!result) {
				commonTestMsgList.add("Unable to enter text in the input field.");
				return false;
			}

			// keyboard close
			if(!ConfigFileReader.strRunMode.equalsIgnoreCase("localLab")){
				AndroidDriver<MobileElement> androidDriver = (AndroidDriver<MobileElement>) driver;
				androidDriver.pressKey(new KeyEvent(AndroidKey.BACK));
			}

			if(isQB){
				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getNextButton(), 20);
				if (!result) {
					commonTestMsgList.add("Unable to verify next btn");
					return result;
				}

				cfObj.commonClick(commonTestPageObj.getNextButton());
			} else {
				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getCancelButton(), 20);
				if (!result) {
					commonTestMsgList.add("Unable to verify cancel button");
					return result;
				}
				cfObj.commonClick(commonTestPageObj.getCancelButton());
			}
		} catch (Exception e) {
			commonTestMsgList.add("verifyReportIconWhenAttempting_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyAndSubmitFinishTestPopUp(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			Thread.sleep(1500);

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.TextView[@text='Test Summary']", "xpath", 10);
			if (!result) {
				commonTestMsgList.add("TestSummary title is not visible on Test Submit popup screen. ");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "bottom_error_text", "id", 10);
			if (!result) {
				commonTestMsgList.add("SubmitTest text is not visible on Test Submit popup screen. ");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "negative_button", "id", 10);
			if (!result) {
				commonTestMsgList.add("No button is not visible on Test Submit popup screen. ");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "positive_button", "id", 10);
			if (!result) {
				commonTestMsgList.add("Submittest button is not visible on Test Submit popup screen. ");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "positive_button", "id"));

			Thread.sleep(1500);

		} catch (Exception e) {
			commonTestMsgList.add("verifyAndSubmitFinishTestPopUp_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean completeAndSubmitTest(AppiumDriver<MobileElement> driver, boolean isCalculator,
			boolean isMultilanguage, boolean isQB) {
		boolean result = true;
		try {

			int questionCount = Integer
					.parseInt(cfObj.commonGetElement(driver, "com.adda247.app:id/total_question_count", "id").getText());

			result = clickStartTestBtn(driver);
			if (!result) {
				commonTestMsgList.add("Unable to click start test btn ");
				return result;
			}

			Thread.sleep(2000); // page loading takes time

			result = takeAndSubmitTest(driver, isCalculator, isMultilanguage, questionCount, isQB);
			if (!result) {
				commonTestMsgList.add("Unable to take test");
				return result;
			}

			result = verifyAndSubmitFinishTestPopUp(driver);
			if (!result) {
				commonTestMsgList.add("Unable to verify finish test pop-up");
				return result;
			}

		} catch (Exception e) {
			commonTestMsgList.add("completeAndSubmitTest_Exception: " + e.getMessage());
			result = false;

		}
		return result;
	}

	public boolean completeAndSubmitPaidOptionalTest(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = clickStartTestBtn(driver);
			if (!result) {
				commonTestMsgList.add("Unable to click start test btn ");
				return result;
			}

			Thread.sleep(2000); // page loading takes time

			result = takeAndSubmitOptionalTest(driver);
			if (!result) {
				commonTestMsgList.add("Unable to completeAndSubmitOptionalTest");
				return result;
			}

			result = verifyAndSubmitFinishTestPopUp(driver);
			if (!result) {
				commonTestMsgList.add("Unable to verify finish test pop-up");
				return result;
			}

		} catch (Exception e) {
			commonTestMsgList.add("completeAndSubmitPaidOptionalTest_Exception: " + e.getMessage());
			result = false;

		}
		return result;
	}

	public boolean completeAndSubmitTestWithCorrectAnswer(AppiumDriver<MobileElement> driver, boolean isCalculator,
			boolean isMultilanguage) {
		boolean result = true;
		try {

			int questionCount = Integer
					.parseInt(cfObj.commonGetElement(driver, "com.adda247.app:id/total_question_count", "id").getText());

			result = clickStartTestBtn(driver);
			if (!result) {
				commonTestMsgList.add("Unable to click start test btn ");
				return result;
			}

			Thread.sleep(2000); // page loading takes time

			result = takeAndSubmitTestWithCorrectAnswer(driver, isCalculator, isMultilanguage, questionCount);
			if (!result) {
				commonTestMsgList.add("Unable to take test");
				return result;
			}

			result = verifyAndSubmitFinishTestPopUp(driver);
			if (!result) {
				commonTestMsgList.add("Unable to verify finish test pop-up");
				return result;
			}

		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("completeAndSubmitTestWithCorrectAnswer_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyAndCloseCoinPopup(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			if (cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTextPopupCongratulation(), 10)) {

				cfObj.commonClick(commonTestPageObj.getBtnCloseCoinPopup());
			} else {
				System.out.println("Congratulation popup is not visible.");
			}

		} catch (Exception e) {
			commonTestMsgList.add("clickAttemptLinkOfRecentlyDownloadedTest_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyTestAnalysisDailyQuizPage(AppiumDriver<MobileElement> driver, boolean internetStatus) {
		boolean result = true;
		try {
			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "toolbar_title", "id", 10);
				if (!result) {
					commonTestMsgList.add("Result screen title is not visible.");
					return result;
				}
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "toolbar_title_back_arrow", "id", 10);
				if (!result) {
					commonTestMsgList.add("Result screen back button is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "solution_btn_solo", "id", 10);
				if (!result) {
					commonTestMsgList.add("ViewSolution button is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "overallSummaryTv", "id", 10);
				if (!result) {
					commonTestMsgList.add("OverallSummarySection title is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "userScoreProgressImg", "id", 10);
				if (!result) {
					commonTestMsgList.add("User score progress image is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "userMarks", "id", 10);
				if (!result) {
					commonTestMsgList.add("User mark text is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "totalMarks", "id", 10);
				if (!result) {
					commonTestMsgList.add("Total mark text is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "yourScoreTv", "id", 10);
				if (!result) {
					commonTestMsgList.add("Your score title text is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "userTimeProgressImg", "id", 10);
				if (!result) {
					commonTestMsgList.add("User time Progress image is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "userTime", "id", 10);
				if (!result) {
					commonTestMsgList.add("User Time text is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "totalTime", "id", 10);
				if (!result) {
					commonTestMsgList.add("Total Time text is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "yourTimeTv", "id", 10);
				if (!result) {
					commonTestMsgList.add("Your Time title text is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "userRank", "id", 10);
				if (!result) {
					commonTestMsgList.add("User Rank text is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "userPercentile", "id", 10);
				if (!result) {
					commonTestMsgList.add("UserPercentile text is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "userAccuracy", "id", 10);
				if (!result) {
					commonTestMsgList.add("UserAccuracy text is not visible.");
					return result;
				}

				if (internetStatus) {
					result = cfObj.commonGetElement(driver, "userRank", "id").getText().equalsIgnoreCase("--- | ---");
					if (!result) {
						commonTestMsgList.add("Empty rank is not visible.");
						return result;
					}

					result = cfObj.commonGetElement(driver, "userPercentile", "id").getText()
							.equalsIgnoreCase("--- | ---");
					if (!result) {
						commonTestMsgList.add("Empty Percentile is not visible.");
						return result;
					}

					result = cfObj.commonGetElement(driver, "userAccuracy", "id").getText()
							.equalsIgnoreCase("--- | ---");
					if (!result) {
						commonTestMsgList.add("Empty Accuracy is not visible.");
						return result;
					}
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "share_score_btn", "id", 10);
				if (!result) {
					commonTestMsgList.add("Share score btn is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "reattempt_btn", "id", 10);
				if (!result) {
					commonTestMsgList.add("Reattempt btn is not visible.");
					return result;
				}

			} else {
				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnSolution(), 10);
				if (!result) {
					commonTestMsgList.add("Unable to solution btn test analysis page");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getLinkShareScoreCard(), 10);
				if (!result) {
					commonTestMsgList.add("Unable to share score card link test analysis page");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTextScored(), 10);
				if (!result) {
					commonTestMsgList.add("Unable to scored points test analysis page");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getMarkScoredText(), 10);
				if (!result) {
					commonTestMsgList.add("MarkScoredText is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTotalMarksText(), 10);
				if (!result) {
					commonTestMsgList.add("TotalMarksText is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTextRank(), 10);
				if (!result) {
					commonTestMsgList.add("Unable to rank in test analysis page");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getRankScoredText(), 10);
				if (!result) {
					commonTestMsgList.add("RankScoredText is not visible.");
					return result;
				}

				if (internetStatus) {
					result = cfObj.commonVerifyValueTextBox(commonTestPageObj.getRankScoredText(), "--");
					if (!result) {
						commonTestMsgList.add("Empty rank is not visible.");
						return result;
					}
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getRankProgressBarBtn(), 10);
				if (!result) {
					commonTestMsgList.add("RankProgressBarBtn is not visible.");
					return result;
				}
				cfObj.commonClick(commonTestPageObj.getRankProgressBarBtn());
				if (internetStatus) {
					result = cfObj.commonVerifyValueTextBox(commonTestPageObj.getToasteMessage(),
							"Unable to get rank. Please check your internet connection and try again.");
					if (!result) {
						commonTestMsgList.add("ToastMessage is not visible.");
						// return result;
					}
				} else {
					result = cfObj.commonVerifyValueTextBox(commonTestPageObj.getToasteMessage(),
							"Your rank is up to date.");
					if (!result) {
						commonTestMsgList.add("ToastMessage is not visible.");
						// return result;
					}
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTotalRankText(), 10);
				if (!result) {
					commonTestMsgList.add("TotalRankText is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTextTimeSpent(), 10);
				if (!result) {
					commonTestMsgList.add("Unable to time spent in test analysis page");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTimeSpentValue(), 10);
				if (!result) {
					commonTestMsgList.add("TimeSpentValue is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnNavigateUp(), 10);
				if (!result) {
					commonTestMsgList.add("Unable to navigate up btn test analysis page");
					return result;
				}
			}

			Thread.sleep(2000);
			cfObj.commonClick(commonTestPageObj.getBtnNavigateUp());

		} catch (Exception e) {
			commonTestMsgList.add("verifyTestAnalysisDailyQuizPage_Exception" + e.getMessage());
			result = false;
		}
		return result;

	}

	public boolean clickSwitchLanguageInstruction(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBannerSwitchLangugae(), 10);
			if (result) {
				cfObj.commonClick(commonTestPageObj.getBannerSwitchLangugae());
			} else {
				System.out.println("Banner switch language is not visible.");
			}

		} catch (Exception e) {
			commonTestMsgList.add("clickSwitchLanguageInstruction_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean completeAndSubmitFixedMockOptionalTest(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = clickStartTestBtn(driver);
			if (!result) {
				commonTestMsgList.add("Unable to click start test btn ");
				return result;
			}

			Thread.sleep(2000); // page loading takes time

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.TextView[contains(@text,'Time Lapsed')]", "xpath", 10)) {
				cfObj.commonClick(cfObj.commonGetElement(driver, "positive_button", "id"));
			}

			result = validateExamSectionDropDown(driver);
			if (!result) {
				commonTestMsgList.add("Not able to validate ExamSectionDropDown.");
				return result;
			}

			result = takeAndSubmitOptionalTest(driver);
			if (!result) {
				commonTestMsgList.add("Unable to take test");
				return result;
			}

			result = verifyAndSubmitFinishTestPopUp(driver);
			if (!result) {
				commonTestMsgList.add("Unable to verify finish test pop-up");
				return result;
			}

		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("CompleteAndSubmitOptionalTest_Exception" + e.getMessage());

		}
		return result;
	}

	public boolean takeAndSubmitOptionalTest(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		boolean bool = true;
		boolean bool1 = true;
		try {
			cfObj.commonClick(commonTestPageObj.getBtnFilter());

			for (int i = 0; i < commonTestPageObj.getSectionList().size(); i++) {

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getSectionList().get(i), 10);
				if (!result) {
					commonTestMsgList.add("SectionList is not visible.");
					return result;
				}

				cfObj.commonClick(commonTestPageObj.getSectionList().get(i));

				for (int j = 0; j < commonTestPageObj.getQuestionCountList().size(); j++) {
					result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getQuestionCountList().get(j),
							10);
					if (!result) {
						commonTestMsgList.add("Question count list is not visible.");
						return result;
					}
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,"com.adda247.app:id/info_icon","id",10);
				if (!result) {
					commonTestMsgList.add("Info icon in bottomsheet is not visible.");
					return result;
				}
			}

			cfObj.commonClick(commonTestPageObj.getSectionList().get(0));

			driver.navigate().back();

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnFilter(), 10);
			if (!result) {
				commonTestMsgList.add("filter btn is not visible.");
				return result;
			}

			while(bool){

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getListAnswers().get(0),
						10);
				if (!result) {
					commonTestMsgList.add("Question count list is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,"com.adda247.app:id/review_button_group","id",10);
				if (!result) {
					commonTestMsgList.add("Review btn is not visible");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,"com.adda247.app:id/ques_pos_marks","id",10);
				if (!result) {
					commonTestMsgList.add("Positive marks text is not visible");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,"com.adda247.app:id/optional_info","id",10);
				if (!result) {
					commonTestMsgList.add("Optional text header is not visible");
					return result;
				}

				cfObj.commonClick(commonTestPageObj.getListAnswers().get(0));

				Thread.sleep(500);

				result = commonTestPageObj.getBtnSaveAndNext().getText().contains("Submit");
				if (result){
					bool = false;
				} else {
					cfObj.commonClick(commonTestPageObj.getBtnSaveAndNext());
				}
			}

			// Its state is Submit
			cfObj.commonClick(commonTestPageObj.getBtnSaveAndNext());

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnFilter(), 10);
			if (!result) {
				commonTestMsgList.add("filter btn is not visible.");
				return result;
			}

			while(bool1){

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getListAnswers().get(0),
						10);
				if (!result) {
					commonTestMsgList.add("Question count list is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,"com.adda247.app:id/review_button_group","id",10);
				if (!result) {
					commonTestMsgList.add("Review btn is not visible");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,"com.adda247.app:id/ques_pos_marks","id",10);
				if (!result) {
					commonTestMsgList.add("Positive marks text is not visible");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,"com.adda247.app:id/optional_info","id",10);
				if (!result) {
					commonTestMsgList.add("Optional text header is not visible");
					return result;
				}

				cfObj.commonClick(commonTestPageObj.getListAnswers().get(0));

				Thread.sleep(500);

				result = commonTestPageObj.getBtnSaveAndNext().getText().contains("Submit");
				if (result){
					bool1 = false;
				} else {
					cfObj.commonClick(commonTestPageObj.getBtnSaveAndNext());
				}
			}

			cfObj.commonClick(commonTestPageObj.getBtnSaveAndNext());

		} catch (Exception e) {
			commonTestMsgList.add("takeAndSubmitOptionalTest_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyResultAwaitedScreen(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getResultAwaitedPageTitle(), 30);
			if (!result) {
				commonTestMsgList.add("ResultAwaitedPageTitle is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getResultAwaitedImg(), 30);
			if (!result) {
				commonTestMsgList.add("ResultAwaitedImage is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getResultAwaitedText(), 30);
			if (!result) {
				commonTestMsgList.add("ResultAwaitedText is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getResultAwaitedTimer(), 30);
			if (!result) {
				commonTestMsgList.add("ResultAwaitedTimer is not visible");
				return result;
			}

		} catch (Exception e) {
			commonTestMsgList.add("verifyResultAwaitedScreen_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnResultAwaitedBackBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getResultAwaitedBackBtn(), 30);
			if (!result) {
				commonTestMsgList.add("ResultAwaitedBack button is not visible");
				return result;
			}
			cfObj.commonClick(commonTestPageObj.getResultAwaitedBackBtn());

		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("clickOnResultAwaitedBackBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateExamSectionDropDown(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getExamSectionDropDown(), 10);
			if (!result) {
				commonTestMsgList.add("ExamSectionDropDown is not visible.");
				return result;
			}
			cfObj.commonClick(commonTestPageObj.getExamSectionDropDown());

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getExamSectionDropDownTitle(), 10);
			if (!result) {
				commonTestMsgList.add("ExamSectionDropDown title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getExamSectionList().get(0), 10);
			if (!result) {
				commonTestMsgList.add("ExamSectionList is not visible.");
				return result;
			}
			int sectioncount = commonTestPageObj.getExamSectionList().size();

			cfObj.commonClick(commonTestPageObj.getExamSectionDropDownCloseBtn());

			for (int i = 0; i < sectioncount; i++) {

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getExamSectionDropDown(), 10);
				if (!result) {
					commonTestMsgList.add("ExamSectionDropDown is not visible.");
					return result;
				}
				cfObj.commonClick(commonTestPageObj.getExamSectionDropDown());

//				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTickIcon(), 30);
//				if (!result) {
//					commonTestMsgList.add("Tick icon is not visible.");
//					return result;
//				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getExamSectionList().get(i),
						10);
				if (!result) {
					commonTestMsgList.add("ExamSectionList is not visible.");
					return result;
				}

				String sectionName = commonTestPageObj.getExamSectionList().get(i).getText();
				if (sectionName == null) {
					commonTestMsgList.add("SectionName text is null.");
					return result;
				}

				cfObj.commonClick(commonTestPageObj.getExamSectionList().get(i));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,"com.adda247.app:id/optional_info","id",10);
				if (!result) {
					commonTestMsgList.add("optional_info is not visible after selecting section");
					return result;
				}
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getExamSectionDropDown(), 10);
			if (!result) {
				commonTestMsgList.add("ExamSectionDropDown is not visible.");
				return result;
			}
			cfObj.commonClick(commonTestPageObj.getExamSectionDropDown());

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getExamSectionList().get(0), 10);
			if (!result) {
				commonTestMsgList.add("ExamSectionList is not visible.");
				return result;
			}
			cfObj.commonClick(commonTestPageObj.getExamSectionList().get(0));

		} catch (Exception e) {
			commonTestMsgList.add("validateExamSectionDropDown_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean handleTestRatingPopUp(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			Thread.sleep(2000);
			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
				if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "tv_title_rate", "id", 10)) {
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "iv_great", "id", 10);
					if (!result) {
						commonTestMsgList.add("Rating Star is not visible.");
						return result;
					}

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "close_btn", "id", 10);
					if (!result) {
						commonTestMsgList.add("Rating feedback screen back button is not visible.");
						return result;
					}

					cfObj.commonClick(cfObj.commonGetElement(driver, "iv_good", "id"));

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "title", "id", 10);
					if (!result) {
						commonTestMsgList.add("TestFeedBack messageTab is not visible.");
						return result;
					}
					cfObj.commonClick(cfObj.commonGetElements(driver, "title", "id").get(0));

					result = cfObj.commonSetTextTextBox(cfObj.commonGetElement(driver, "userSuggestion_editText", "id"),
							"TestFeedBack");
					if (!result) {
						commonTestMsgList.add("Not able to enter feedback message.");
						return result;
					}
					Thread.sleep(2000);
					cfObj.commonClick(cfObj.commonGetElement(driver, "submit_btn", "id"));

				}
			} else if (ConfigFileReader.strApplication.equalsIgnoreCase("Sankalp")) {

				if (cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getRatingPopUpTitle(), 30)) {
					result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getRatingFeedBackBar(),
							10);
					if (!result) {
						commonTestMsgList.add("RatingFeedBackBar is not visible.");
						return result;
					}

					result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTestFeedBackTitle(),
							10);
					if (!result) {
						commonTestMsgList.add("TestFeedBackTitle is not visible.");
						return result;
					}
					cfObj.commonClick(commonTestPageObj.getRatingFeedBackBar());
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "title", "id", 10);
					if (!result) {
						commonTestMsgList.add("TestFeedBack messageTab is not visible.");
						return result;
					}
					cfObj.commonClick(cfObj.commonGetElements(driver, "title", "id").get(0));

					result = cfObj.commonSetTextTextBox(cfObj.commonGetElement(driver, "editText_feedback_text", "id"),
							"TestFeedBack");
					if (!result) {
						commonTestMsgList.add("Not able to enter feedback message.");
						return result;
					}
					Thread.sleep(2000);
					cfObj.commonClick(commonTestPageObj.getFeedBackSubmitBtn());

				}
			} else {
				System.out.println("RatingPopUp is not visible.");
			}

		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("HandleTestRatingPopUp_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateInterNetOffToastMessage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getToasteMessage(), 10);
			if (!result) {
				commonTestMsgList.add("Toast message is not visible.");
				return result;
			}
			result = cfObj.commonVerifyValueTextBox(commonTestPageObj.getToasteMessage(), "No internet connection!");
			if (!result) {
				commonTestMsgList.add("Internet Off Toast message is not matching.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("validateInterNetOffToastMessage_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnReAttemptBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getReAttemptBtn(), 10);
			if (!result) {
				commonTestMsgList.add("Re-Attempt button is not visible.");
				return result;
			}
			cfObj.commonClick(commonTestPageObj.getReAttemptBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getReAttemptPopUpTitle(), 10);
			if (!result) {
				commonTestMsgList.add("Re-Attempt PopUp Title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getReAttemptPopUpBtn(), 10);
			if (!result) {
				commonTestMsgList.add("Re-Attempt PopUp button is not visible.");
				return result;
			}
			cfObj.commonClick(commonTestPageObj.getReAttemptPopUpBtn());
			Thread.sleep(1000);
			result = !cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getReAttemptPopUpBtn(), 10);
			if (!result) {
				commonTestMsgList.add("Re-Attempt PopUp button is visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("clickOnReAttemptBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean completeAndSubmitReAttemptTest(AppiumDriver<MobileElement> driver, boolean calculatorStatus,
			boolean isMultiLanguage, boolean isQB) {
		boolean result = true;
		try {

			int questionCount = Integer
					.parseInt(cfObj.commonGetElement(driver, "com.adda247.app:id/total_question_count", "id").getText());

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnStartTest(), 20);
			if (!result) {
				commonTestMsgList.add("Start Button is not visible. ");
				return result;
			}
			cfObj.commonClick(commonTestPageObj.getBtnStartTest());

			Thread.sleep(2000); // page loading takes time

			result = takeAndSubmitTest(driver, calculatorStatus, isMultiLanguage, questionCount, isQB);
			if (!result) {
				commonTestMsgList.add("Unable to take test");
				return result;
			}

			result = verifyAndSubmitFinishTestPopUp(driver);
			if (!result) {
				commonTestMsgList.add("Unable to verify finish test pop-up");
				return result;
			}

		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("completeAndSubmitReAttemptTest_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateResumeStateFlow(AppiumDriver<MobileElement> driver, String mockName) {
		boolean result = true;
		try {

			int questionCount = Integer
					.parseInt(cfObj.commonGetElement(driver, "com.adda247.app:id/total_question_count", "id").getText());

			result = clickStartTestBtn(driver);
			if (!result) {
				commonTestMsgList.add("Unable to click start test btn ");
				return result;
			}

			Thread.sleep(2000); // page loading takes time

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getListAnswers().get(0), 10);
			if (!result) {
				commonTestMsgList.add("AnswerList is not visible.");
				return result;
			}
			cfObj.commonClick(commonTestPageObj.getListAnswers().get(0));

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnSaveAndNext(), 10);
			if (!result) {
				commonTestMsgList.add("SaveAndNext button is not visible.");
				return result;
			}
			cfObj.commonClick(commonTestPageObj.getBtnSaveAndNext());
			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getQuestionNumber(), 10);
			if (!result) {
				commonTestMsgList.add("QuestionNumber is not visible.");
				return result;
			}
			String questionNumber = commonTestPageObj.getQuestionNumber().getText();
			if (questionNumber == null) {
				commonTestMsgList.add("QuestionNumber  text is null.");
				return false;
			}

			result = clickOnTestPauseBtn(driver);
			if (!result) {
				commonTestMsgList.add("Not able to click TestPause button.");
				return result;
			}

			childPackageUtilObj = new ChildPackageUtil(driver);

			result = childPackageUtilObj.clickOnSpecificResumeStatus(driver, mockName);
			if (!result) {
				commonTestMsgList.addAll(childPackageUtilObj.packagePageMsgList);
				return result;
			}

			result = clickOnResumeTestBtn(driver);
			if (!result) {
				commonTestMsgList.add("Not able to click ResumeTest button.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getQuestionNumber(), 10);
			if (!result) {
				commonTestMsgList.add("QuestionNumber is not visible.");
				return result;
			}

			result = cfObj.commonVerifyValueTextBox(commonTestPageObj.getQuestionNumber(), questionNumber);
			if (!result) {
				commonTestMsgList.add("Resumed QuestionNumber is not matching.");
				return result;
			}

			result = takeAndSubmitTest(driver, questionNumber, questionCount);
			if (!result) {
				commonTestMsgList.add("Unable to take test");
				return result;
			}

			result = verifyAndSubmitFinishTestPopUp(driver);
			if (!result) {
				commonTestMsgList.add("Unable to verify finish test pop-up");
				return result;
			}

		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("makeTestInResumeState_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnTestPauseBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "pauseButton", "id", 10);
				if (!result) {
					commonTestMsgList.add("TestPause button is not visible.");
					return result;
				}

				cfObj.commonClick(cfObj.commonGetElement(driver, "pauseButton", "id"));

				result = validatePauseTestPopUpScreen(driver);
				if (!result) {
					commonTestMsgList.add("Failed to validate PauseTestPopUpScreen.");
					return result;
				}

				cfObj.commonClick(cfObj.commonGetElement(driver, "positive_button", "id"));

				result = !cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "dialogTitle_textView", "id", 5);
				if (!result) {
					commonTestMsgList.add("Failed to click Yes button.");
					return result;
				}
			} else {

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnNavigateUp(), 10);
				if (!result) {
					commonTestMsgList.add("TestPause button is not visible.");
					return result;
				}
				cfObj.commonClick(commonTestPageObj.getBtnNavigateUp());

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getYesPauseBtn(), 10);
				if (!result) {
					commonTestMsgList.add("YesPause button is not visible.");
					return result;
				}

				cfObj.commonClick(commonTestPageObj.getYesPauseBtn());

				Thread.sleep(2000);
				result = !cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getYesPauseBtn(), 10);
				if (!result) {
					commonTestMsgList.add("YesPause button is visible.");
					return result;
				}
			}
		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("clickOnTestPauseBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validatePauseTestPopUpScreen(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "dialogTitle_textView", "id", 10);
			if (!result) {
				commonTestMsgList.add("PoseTest popup screen title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "dialogDescription_textView", "id", 10);
			if (!result) {
				commonTestMsgList.add("PoseTest popup screen description is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "negative_button", "id", 10);
			if (!result) {
				commonTestMsgList.add("No button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "positive_button", "id", 10);
			if (!result) {
				commonTestMsgList.add("Yes button is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("validatePauseTestPopUpScreen_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean clickOnResumeTestBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.widget.Button[@text='Resume Test']", "xpath", 30);
				if (!result) {
					commonTestMsgList.add("ResumeTest button is not visible.");
					return result;
				}

				cfObj.commonClick(
						cfObj.commonGetElement(driver, "//android.widget.Button[@text='Resume Test']", "xpath"));

			} else {
				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnStartTest(), 10);
				if (!result) {
					commonTestMsgList.add("ResumeTest button is not visible.");
					return result;
				}

				result = cfObj.commonVerifyValueTextBox(commonTestPageObj.getBtnStartTest(), "RESUME TEST");
				if (!result) {
					commonTestMsgList.add("ResumeTest button text is not matching.");
					return result;
				}

				cfObj.commonClick(commonTestPageObj.getBtnStartTest());
			}

		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("ClickOnResumeTestBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean takeAndSubmitTest(AppiumDriver<MobileElement> driver, String resumeQuestionNumber,
			int questionCount) {
		boolean result = true;
		boolean optionsVisible = false;
		try {
			int resumeQuestion = Integer.parseInt(resumeQuestionNumber);
			// cfObj.commonClick(commonTestPageObj.getBtnFilter());
			// String str = commonTestPageObj.getQuestionCount().getText();
			// if(str==null) {
			// commonTestMsgList.add("Question count text is null.");
			// return false;
			//
			// }
			// str = str.substring(12);
			// if(str==null) {
			// commonTestMsgList.add("Expected Question count text is null.");
			// return false;
			//
			// }
			// int count = Integer.parseInt(str);
			// cfObj.commonClick(commonTestPageObj.getBtnFilter());

			for (int i = resumeQuestion; i <= questionCount; i++) {
				optionsVisible = commonTestPageObj.getListAnswers().size() != 0;
				while (!optionsVisible) {
					cfObj.scrollUtill(driver, 1, direction.DOWN);
					Thread.sleep(1000);
					optionsVisible = commonTestPageObj.getListAnswers().size() != 0;
				}

				cfObj.commonClick(commonTestPageObj.getListAnswers().get(0));
				Thread.sleep(200);
				if (i == questionCount) {
					result = commonTestPageObj.getBtnSaveAndNext().getText().contains("Submit");
					if (!result) {
						commonTestMsgList.add("Submit button is not visible.");
						return result;
					}
				}

				cfObj.commonClick(commonTestPageObj.getBtnSaveAndNext());
				Thread.sleep(200);
			}
		} catch (Exception e) {
			commonTestMsgList.add("takeAndSubmitTest_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean validateShareScoreCardLink(AppiumDriver<MobileElement> driver, String mockName) {
		boolean result = true;
		try {
			Thread.sleep(2000);
			result = clickOnLinkShareScoreCard(driver);
			if (!result) {
				commonTestMsgList.add("Not able to click LinkShareScoreCard.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("validateShareScoreCardLink_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean clickOnLinkShareScoreCard(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getLinkShareScoreCard(), 10);
			if (!result) {
				commonTestMsgList.add("LinkShareScoreCard is not visible.");
				return result;
			}
			cfObj.commonClick(commonTestPageObj.getLinkShareScoreCard());

		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("clickOnLinkShareScoreCard_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateShareScoreCardPopUp(AppiumDriver<MobileElement> driver, String mockName) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getScoreCardPopUpTitle(), 10);
			if (!result) {
				commonTestMsgList.add("ScoreCardPopUpTitle is not visible.");
				return result;
			}
			result = cfObj.commonVerifyValueTextBox(commonTestPageObj.getScoreCardPopUpTitle(), mockName);
			if (!result) {
				commonTestMsgList.add("ScoreCardPopUpTitle text is not matching.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTextScored(), 10);
			if (!result) {
				commonTestMsgList.add("TextScored is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTextRank(), 10);
			if (!result) {
				commonTestMsgList.add("TextRank is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTextTimeSpent(), 10);
			if (!result) {
				commonTestMsgList.add("TextRank is not visible.");
				return result;
			}
			result = validateShareWithFriendBtn(driver);
			if (!result) {
				commonTestMsgList.add("Not able to validate ShareWithFriendBtn.");
				return result;
			}
			cfObj.commonClick(commonTestPageObj.getRatingPopUpCloseBtn());

		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("validateShareScoreCardLink_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateShareWithFriendBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getShareWithFriendBtn(), 10);
			if (!result) {
				commonTestMsgList.add("ShareWithFriendBtn is not visible.");
				return result;
			}
			cfObj.commonClick(commonTestPageObj.getShareWithFriendBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getShareScreen(), 10);
			if (!result) {
				commonTestMsgList.add("ShareScreen is not visible.");
				return result;
			}
			Thread.sleep(1000);
			cfObj.scrollUtill(driver, 1, direction.UP);

			result = !cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getShareScreen(), 10);
			if (!result) {
				commonTestMsgList.add("ShareScreen is visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("validateShareWithFriendBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateSolutionPageUI(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnSolution(), 10);
			if (!result) {

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnSolution(), 10);
				if (!result) {
					commonTestMsgList.add("Solution button is not visible.");
					return result;
				}
				cfObj.commonClick(commonTestPageObj.getBtnSolution());

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "test_title", "id", 10);
				if (!result) {
					commonTestMsgList.add("Solution screen page title is not visible.");
					return result;
				}
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "reattempt_switch", "id", 10);
			if (!result) {
				commonTestMsgList.add("ReAttempt switch is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "back_button", "id", 10);
			if (!result) {
				commonTestMsgList.add("Back button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "languageControl_button", "id", 10);
			if (!result) {
				commonTestMsgList.add("Language control button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "questionControl_button", "id", 10);
			if (!result) {
				commonTestMsgList.add("Filter button is not visible.");
				return result;
			}

//			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "ans_speed_indicator", "id", 10);
//			if (!result) {
//				commonTestMsgList.add("Answer speed indicator is not visible.");
//				return result;
//			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "time_spent", "id", 10);
			if (!result) {
				commonTestMsgList.add("Timespent text is not visible.");
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "avg_time_spent", "id", 10);
			if (!result) {
				commonTestMsgList.add("Average time spent text is not visible.");
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "avg_correct_percent_count_text", "id",
					10);
			if (!result) {
				commonTestMsgList.add("AverageCorrectPercentCount text is not visible.");
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "bookmark_btn", "id", 10);
			if (!result) {
				commonTestMsgList.add("Bookmark button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "option_index", "id", 10);
			if (!result) {
				commonTestMsgList.add("Question options are not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "test_summary_btn", "id", 10);
			if (!result) {
				commonTestMsgList.add("TestSummary button is not visible.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("validateSolutionPageUI_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateTestSolutionBtn(AppiumDriver<MobileElement> driver, boolean isCalculator,
			boolean isMultiLanguage, boolean isQB) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnSolution(), 10);
			if (!result) {
				commonTestMsgList.add("Solution button is not visible.");
				return result;
			}

			cfObj.commonClick(commonTestPageObj.getBtnSolution());

			result = validateSolutionPageUI(driver);
			if (!result) {
				commonTestMsgList.add("Failed to validate SolutionPageUI.");
				return result;
			}

			fixedMockTestUtilObj = new FixedMockTestUtil(driver);
			result = fixedMockTestUtilObj.validateSolutionScreenFilterBtn(driver);
			if (!result) {
				commonTestMsgList.addAll(fixedMockTestUtilObj.fixedMockTestMsgList);
				return result;
			}

			result = fixedMockTestUtilObj.validateReportContentIcon(driver, isQB);
			if (!result) {
				commonTestMsgList.addAll(fixedMockTestUtilObj.fixedMockTestMsgList);
				return result;
			}

			result = fixedMockTestUtilObj.validateBookMarkIcon(driver);
			if (!result) {
				commonTestMsgList.addAll(fixedMockTestUtilObj.fixedMockTestMsgList);
				return result;
			}

			result = fixedMockTestUtilObj.validateTestSummaryIcon(driver);
			if (!result) {
				commonTestMsgList.addAll(fixedMockTestUtilObj.fixedMockTestMsgList);
				return result;
			}

			result = validateLanguageButtonOnSolutionScreen(driver);
			if (!result) {
				commonTestMsgList.add("Failed to validate LanguageButtonOnSolutionScreen.");
				return result;
			}

			cfObj.commonClick(commonTestPageObj.getBtnFilter());
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "ques_num", "id", 30);
			if (!result) {
				commonTestMsgList.add("Question number is not visible.");
				return result;
			}

			int questionCount = commonTestPageObj.getQuestionNumberTabs().size();

			for (int i = 0; i < questionCount; i++) {

				if (i > 0) {
					cfObj.commonClick(commonTestPageObj.getBtnFilter());
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "ques_num", "id", 30);
					if (!result) {
						commonTestMsgList.add("Question number is not visible.");
						return result;
					}
				}

				String questionTab = commonTestPageObj.getQuestionNumberTabs().get(i).getText();

				cfObj.commonClick(commonTestPageObj.getQuestionNumberTabs().get(i));

				result = validateSolutionPageUI(driver);
				if (!result) {
					commonTestMsgList.add("Failed to validate SolutionPageUI.");
					return result;
				}

				result = commonTestPageObj.getQuestionNumber().getText().equalsIgnoreCase(questionTab);
				if (!result) {
					commonTestMsgList.add(
							"Failed to switch question by click on Question number tab present on filter screen.");
					return result;
				}

				if (isCalculator) {
					result = validateCalculatorButton(driver);
					if (!result) {
						commonTestMsgList.add("Not able to validate Calculator button.");
						return result;
					}
				}

				result = fixedMockTestUtilObj.validateReAttemapSwitchOnSolutionScreen(driver);
				if (!result) {
					commonTestMsgList.addAll(fixedMockTestUtilObj.fixedMockTestMsgList);
					return result;
				}
			}

			cfObj.commonClick(commonTestPageObj.getBtnFilter());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/info_icon", "id", 30);
			if (!result) {
				commonTestMsgList.add("Filter info icon is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/info_icon", "id"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "checkbox", "id", 30);
			if (!result) {
				commonTestMsgList.add("Filter checkbox list is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElements(driver, "checkbox", "id").get(0));

			result = cfObj.commonGetElements(driver, "checkbox", "id").get(0).getAttribute("checked")
					.equalsIgnoreCase("false");
			if (!result) {
				commonTestMsgList.add("Failed to unchecked Filter checkbox.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElements(driver, "checkbox", "id").get(0));

			result = cfObj.commonGetElements(driver, "checkbox", "id").get(0).getAttribute("checked")
					.equalsIgnoreCase("true");
			if (!result) {
				commonTestMsgList.add("Failed to select Filter checkbox.");
				return result;
			}

			if (isQB) {

				driver.navigate().back();

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnFilter(), 10);
				if (!result) {
					commonTestMsgList.add("The filter btn is not visible");
				}
				cfObj.commonClick(commonTestPageObj.getBtnFilter());

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/ques_num", "id", 30);
				if (!result) {
					commonTestMsgList.add("Question numbers are not visible");
					return result;
				}

				int questionCounts = commonTestPageObj.getQuestionNumberTabs().size();

				cfObj.commonClick(commonTestPageObj.getQuestionNumberTabs().get(questionCounts - 1));

                Thread.sleep(2000);

                result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getReportIcon(), 5);
                if (result) {
                    commonTestMsgList.add("Report icon is visible on last question, but already submitted the report ques.");
                    return false;
                } else {
                    result = true;
                }
			}
		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("validateTestSolutionBtn_Exception" + e.getMessage());
		}
		return result;
	}

        public boolean validateCalculatorButton(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getCalculatorBtn(), 10);
			if (!result) {
				commonTestMsgList.add("CalculatorBtn is not visible.");
				return result;
			}
			cfObj.commonClick(commonTestPageObj.getCalculatorBtn());
			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getCalculatorScreen(), 10);
			if (!result) {
				commonTestMsgList.add("CalculatorScreen is not visible.");
				return result;
			}

			result = cfObj.waitForPageLoading(driver, 10, 2000, commonTestPageObj.getCalculatorScreenCloseBtn());
			if (!result) {
				commonTestMsgList.add("CalculatorScreenCloseBtn is not visible.");
			}

			cfObj.commonClick(commonTestPageObj.getCalculatorScreenCloseBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getCalculatorBtn(), 30);
			if (!result) {
				commonTestMsgList.add("CalculatorBtn is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("validateCalculatorButton" + e.getMessage());
		}
		return result;

	}

	public boolean validateCutOffLayOut(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {

				result = cfObj.commonVerifyValueTextBox(commonTestPageObj.getCutOffMark(), "60");
				if (result) {
					commonTestMsgList.add("CutOff mark is not matching.");
					return result;
				}

				result = cfObj.commonVerifyValueTextBox(commonTestPageObj.getTotalMarks(), "100");
				if (result) {
					commonTestMsgList.add("Total mark is not matching.");
					return result;
				}

			} else {
				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getCutOffLayOut(), 10);
				if (result) {
					commonTestMsgList.add("CutOffLayOut is not visible.");
					return result;
				}
				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getCutOffTitle(), 10);
				if (result) {
					commonTestMsgList.add("CutOff title is not visible.");
					return result;
				}

				result = cfObj.commonVerifyValueTextBox(commonTestPageObj.getCutOffMark(), "60");
				if (result) {
					commonTestMsgList.add("CutOff mark is not matching.");
					return result;
				}

				result = cfObj.commonVerifyValueTextBox(commonTestPageObj.getTotalMarks(), "/100");
				if (result) {
					commonTestMsgList.add("Total mark is not matching.");
					return result;
				}
			}
		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("validateCutOffLayOut_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean addQuestionToBookMark(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			HomePageUtil homePageUtilObj = new HomePageUtil(driver);
			result = homePageUtilObj.testPrimePop(driver);
			if(!result){
				commonTestMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnSolution(), 10);
			if(!result) {
				commonTestMsgList.add("Solution button is not visible.");
				return result;
			}
			cfObj.commonClick(commonTestPageObj.getBtnSolution());

			fixedMockTestUtilObj = new FixedMockTestUtil(driver);

			result = cfObj.commonWaitForElementToBeVisible(driver,
					fixedMockTestUtilObj.fixedMockTestPageObj.getIconBookmark(), 30);
			if (!result) {
				commonTestMsgList.add("Book mark icon is not visible.");
				return result;
			}
			cfObj.commonClick(fixedMockTestUtilObj.fixedMockTestPageObj.getIconBookmark());

			cfObj.waitTillElementIsVisible(driver, 10, 1000,
					fixedMockTestUtilObj.fixedMockTestPageObj.getToastMessage());

			cfObj.commonClick(cfObj.commonGetElement(driver, "back_button", "id"));

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnSolution(), 20);
			if (!result) {
				commonTestMsgList.add("Solution button is not visible.");
				return result;
			}
		} catch (Exception e) {
			commonTestMsgList.add("addQuestionToBookMark_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean addQuestionToBookMarkOld(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnSolution(), 10);
			if (!result) {
				commonTestMsgList.add("Solution button is not visible.");
				return result;
			}
			cfObj.commonClick(commonTestPageObj.getBtnSolution());
			fixedMockTestUtilObj = new FixedMockTestUtil(driver);
			result = cfObj.commonWaitForElementToBeVisible(driver,
					fixedMockTestUtilObj.fixedMockTestPageObj.getIconBookmark(), 30);
			if (!result) {
				commonTestMsgList.add("Book mark icon is not visible.");
				return result;
			}
			cfObj.commonClick(fixedMockTestUtilObj.fixedMockTestPageObj.getIconBookmark());

			cfObj.waitTillElementIsVisible(driver, 10, 1000,
					fixedMockTestUtilObj.fixedMockTestPageObj.getToastMessage());
			cfObj.commonClick(cfObj.commonGetElement(driver, "back_button", "id"));

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnSolution(), 10);
			if (!result) {
				commonTestMsgList.add("Solution button is not visible.");
				return result;
			}

		} catch (Exception e) {
			commonTestMsgList.add("addQuestionToBookMarkOld_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean validateLanguageAndFontScreen(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "close_btn", "id", 10);
			if (!result) {
				commonTestMsgList.add("Close button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "fontSize_Title", "id", 10);
			if (!result) {
				commonTestMsgList.add("FontSize title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "android.widget.RadioButton", "class", 10);
			if (!result) {
				commonTestMsgList.add("FontSize list is not visible.");
				return result;
			}

			Thread.sleep(1000);
			if (ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {
				cfObj.commonClick_Action(driver,
						cfObj.commonGetElements(driver, "android.widget.RadioButton", "class").get(0));
			} else {
				cfObj.commonClick(cfObj.commonGetElements(driver, "android.widget.RadioButton", "class").get(0));
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getLanguageBtn(), 10);
			if (!result) {
				commonTestMsgList.add("LanguageBtn is not visible.");
				return result;
			}
			cfObj.commonClick(commonTestPageObj.getLanguageBtn());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "language_Title", "id", 10);
			if (!result) {
				commonTestMsgList.add("Language title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "radio_btn_title", "id", 10);
			if (!result) {
				commonTestMsgList.add("Language List is not visible.");
				return result;
			}

			for (int i = 0; i < commonTestPageObj.getLanguageList().size(); i++) {
				cfObj.commonClick(commonTestPageObj.getLanguageList().get(i));
				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getLanguageBtn(), 10);
				if (!result) {
					commonTestMsgList.add("LanguageBtn is not visible.");
					return result;
				}
				if (i > 0) {
					cfObj.commonClick(commonTestPageObj.getLanguageBtn());

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "radio_btn_title", "id", 10);
					if (!result) {
						commonTestMsgList.add("Language List is not visible.");
						return result;
					}
				}

			}

		} catch (Exception e) {
			commonTestMsgList.add("validateLanguageAndFontScreen_Exception: " + e.getMessage());
			result = false;
		}
		return result;

	}

	public boolean validateLanguageButton(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getLanguageBtn(), 10);
			if (!result) {
				commonTestMsgList.add("LanguageBtn is not visible.");
				return result;
			}

			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
				cfObj.commonClick(commonTestPageObj.getLanguageBtn());
				result = validateLanguageAndFontScreen(driver);
				if (!result) {
					commonTestMsgList.add("Failed to validate LanguageAndFontScreen.");
					return result;
				}
			} else {
				if (cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getHindiLanguageBtn(), 10)) {
					cfObj.commonClick(commonTestPageObj.getHindiLanguageBtn());
					result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getEnglishLanguageBtn(),
							30);
					if (!result) {
						commonTestMsgList.add("Not able to change language..");
						return result;
					}
				} else {
					cfObj.commonClick(commonTestPageObj.getEnglishLanguageBtn());
					result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getHindiLanguageBtn(), 30);
					if (!result) {
						commonTestMsgList.add("Not able to change language..");
						return result;
					}
				}
			}
		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("validateLanguageButton_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean validateLanguageButtonOnSolutionScreen(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getSolutionScreenLanguageBtn(),
					10);
			if (!result) {
				commonTestMsgList.add("LanguageBtn is not visible.");
				return result;
			}

//			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
			cfObj.commonClick(commonTestPageObj.getSolutionScreenLanguageBtn());
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "close_btn", "id", 10);
			if (!result) {
				commonTestMsgList.add("Close button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "fontSize_Title", "id", 10);
			if (!result) {
				commonTestMsgList.add("FontSize title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "android.widget.RadioButton", "class", 10);
			if (!result) {
				commonTestMsgList.add("FontSize list is not visible.");
				return result;
			}

			Thread.sleep(1000);
			if (ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {
				cfObj.commonClick_Action(driver,
						cfObj.commonGetElements(driver, "android.widget.RadioButton", "class").get(0));
			} else {
				cfObj.commonClick(cfObj.commonGetElements(driver, "android.widget.RadioButton", "class").get(0));
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getSolutionScreenLanguageBtn(),
					10);
			if (!result) {
				commonTestMsgList.add("LanguageBtn is not visible.");
				return result;
			}
			cfObj.commonClick(commonTestPageObj.getSolutionScreenLanguageBtn());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'Language')]", "xpath",
					10);
			if (!result) {
				commonTestMsgList.add("Language title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "radio_btn_title", "id", 10);
			if (!result) {
				commonTestMsgList.add("Language List is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "radio_btn_title", "id"));

//				for (int i = 0; i < commonTestPageObj.getLanguageList().size(); i++) {
//					cfObj.commonClick(commonTestPageObj.getLanguageList().get(i));
//					result = cfObj.commonWaitForElementToBeVisible(driver,
//							commonTestPageObj.getSolutionScreenLanguageBtn(), 10);
//					if (!result) {
//						commonTestMsgList.add("LanguageBtn is not visible.");
//						return result;
//					}
//					if (i > 0) {
//						cfObj.commonClick(commonTestPageObj.getSolutionScreenLanguageBtn());
//
//						result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "radio_btn_title", "id", 10);
//						if (!result) {
//							commonTestMsgList.add("Language List is not visible.");
//							return result;
//						}
//					}
//
//				}
//			} else {
//				if (cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getHindiLanguageBtn(), 10)) {
//					cfObj.commonClick(commonTestPageObj.getHindiLanguageBtn());
//					result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getEnglishLanguageBtn(),
//							30);
//					if (!result) {
//						commonTestMsgList.add("Not able to change language..");
//						return result;
//					}
//				} else {
//					cfObj.commonClick(commonTestPageObj.getEnglishLanguageBtn());
//					result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getHindiLanguageBtn(), 30);
//					if (!result) {
//						commonTestMsgList.add("Not able to change language..");
//						return result;
//					}
//				}
//			}
		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("validateLanguageButton_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean takeAndSubmitMultiQuestionTest(AppiumDriver<MobileElement> driver, boolean calculatorStatus,
			boolean isMultilanguage, int questionCount) {
		boolean result = true;
		try {

			// cfObj.commonClick(commonTestPageObj.getBtnFilter());
			// String str = commonTestPageObj.getQuestionCount().getText();
			// if(str==null) {
			// commonTestMsgList.add("Question count text is null.");
			// return false;
			// }
			// str = str.substring(12);
			// int count = Integer.parseInt(str);
			// cfObj.commonClick(commonTestPageObj.getBtnFilter());

			for (int i = 1; i <= questionCount; i++) {
				if (calculatorStatus) {
					result = validateCalculatorButton(driver);
					if (!result) {
						commonTestMsgList.add("Not able to validate Calculator button.");
						return result;
					}
				}
				if (isMultilanguage && i == 0) {
					result = validateLanguageButton(driver);
					if (!result) {
						commonTestMsgList.add("Not able to validate Language button.");
						return result;
					}
				}
				// if(cfObj.commonWaitForElementToBeVisible(driver,
				// commonTestPageObj.getMultipleOptionTitle(), 20)) {
				if (i < 11) {
					cfObj.scrollUtill(driver, 1, direction.DOWN);
					int optionCount = commonTestPageObj.getMultipleOptionList().size();
					for (int j = 0; j < optionCount; j++) {
						result = cfObj.commonWaitForElementToBeVisible(driver,
								commonTestPageObj.getMultipleOptionList().get(j), 10);
						if (!result) {
							commonTestMsgList.add("MultipleOption is not visible.");
							return result;
						}

						// result=commonTestPageObj.getMultipleOptionList().get(j).getAttribute("checked").equalsIgnoreCase("false");
						// if(!result) {
						// commonTestMsgList.add("MultipleOption is already selected.");
						// return result;
						// }
						cfObj.commonClick(commonTestPageObj.getMultipleOptionList().get(j));

						// result=commonTestPageObj.getMultipleOptionList().get(j).getAttribute("checked").equalsIgnoreCase("true");
						// if(!result) {
						// commonTestMsgList.add("Not able to select MultipleOption.");
						// return result;
						// }
					}
				}
				// else if (cfObj.commonWaitForElementToBeVisible(driver,
				// commonTestPageObj.getAnswerTextBox(), 20)) {
				else if (i > 20) {
//					result=cfObj.commonSetTextTextBox(commonTestPageObj.getAnswerTextBox(), "TestAnswer");
//					if(!result) {
//						commonTestMsgList.add("Not able to enter answer.");
//						return result;
//					}
				}
				// else if(cfObj.commonWaitForElementToBeVisible(driver,
				// commonTestPageObj.getListAnswers().get(0), 20)){
				else if (i > 10 && i < 21) {

					cfObj.scrollUtill(driver, 1, direction.DOWN);
					Thread.sleep(1000);
					cfObj.commonClick(commonTestPageObj.getListAnswers().get(0));
				}

				Thread.sleep(200);
				if (i == questionCount) {
					result = commonTestPageObj.getBtnSaveAndNext().getText().contains("Submit");
					if (!result) {
						commonTestMsgList.add("Submit button is not visible.");
						return result;
					}
				}

				cfObj.commonClick(commonTestPageObj.getBtnSaveAndNext());
				Thread.sleep(200);
			}

		} catch (Exception e) {
			commonTestMsgList.add("takeAndSubmitMultiQuestionTest_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean validateSectionDropDown(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getExamSectionDropDown(), 10);
			if (!result) {
				commonTestMsgList.add("ExamSectionDropDown is not visible.");
				return result;
			}
			cfObj.commonClick(commonTestPageObj.getExamSectionDropDown());

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getExamSectionDropDownTitle(), 10);
			if (!result) {
				commonTestMsgList.add("ExamSectionDropDown title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getExamSectionList().get(0), 10);
			if (!result) {
				commonTestMsgList.add("ExamSectionList is not visible.");
				return result;
			}

			cfObj.commonClick(commonTestPageObj.getExamSectionDropDownCloseBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getExamSectionDropDown(), 10);
			if (!result) {
				commonTestMsgList.add("ExamSectionDropDown is not visible.");
				return result;
			}

			cfObj.commonClick(commonTestPageObj.getExamSectionDropDown());

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getExamSectionList().get(1), 10);
			if (!result) {
				commonTestMsgList.add("ExamSectionList is not visible.");
				return result;
			}

			cfObj.commonClick(commonTestPageObj.getExamSectionList().get(1));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'Test Summary')]",
					"xpath", 10);
			if (!result) {
				commonTestMsgList.add("Test summary popup is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//*[contains(@text,'Your responses are saved successfully!')]", "xpath", 10);
			if (!result) {
				commonTestMsgList.add("Your responses are saved successfully! text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/positive_button", "id",
					10);
			if (!result) {
				commonTestMsgList.add("positive_button in test summary popup is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//*[contains(@text,'Are you sure you want to submit the section?')]", "xpath", 10);
			if (!result) {
				commonTestMsgList.add("Are you sure you want to submit the section? text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/negative_button", "id",
					10);
			if (!result) {
				commonTestMsgList.add("negative_button in test summary popup is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/negative_button", "id"));

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getExamSectionList().get(2), 10);
			if (!result) {
				commonTestMsgList.add("ExamSectionList is not visible.");
				return result;
			}

			cfObj.commonClick(commonTestPageObj.getExamSectionList().get(2));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'Switch Section')]",
					"xpath", 10);
			if (!result) {
				commonTestMsgList.add("Switch Section popup is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/positive_button", "id",
					10);
			if (!result) {
				commonTestMsgList.add("positive_button in test summary popup is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/positive_button", "id"));

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getExamSectionDropDownCloseBtn(),
					10);
			if (!result) {
				commonTestMsgList.add("getExamSectionDropDownCloseBtn is not visible.");
				return result;
			}

			cfObj.commonClick(commonTestPageObj.getExamSectionDropDownCloseBtn());

		} catch (Exception e) {
			commonTestMsgList.add("validateSectionDropDown_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean validateSectionTimeEndPopUp(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getSectionTimeEndTitle(), 30);
			if (!result) {
				commonTestMsgList.add("SectionTimeEndTitle is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getPauseTestBtn(), 10);
			if (!result) {
				commonTestMsgList.add("PauseTestBtn is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'Next')]", "xpath",
					10);
			if (!result) {
				commonTestMsgList.add("NextSectionBtn is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("validateSectionTimeEndPopUp_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean clickOnPauseTestBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = validateSectionTimeEndPopUp(driver);
			if (!result) {
				commonTestMsgList.add("not able to validate SectionTimeEndPopUp.");
				return result;
			}
			cfObj.commonClick(commonTestPageObj.getPauseTestBtn());

		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("clickOnPauseTestBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnPauseTestBtnInSectional(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("clickOnPauseTestBtnInSectional_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnNextSectionBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = validateSectionTimeEndPopUp(driver);
			if (!result) {
				commonTestMsgList.add("not able to validate SectionTimeEndPopUp.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "//*[contains(@text,'Next')]", "xpath"));

		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("clickOnNextSectionBtn_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean waitForPageLoading(AppiumDriver<MobileElement> driver, MobileElement element) {
		boolean result = true;
		try {

			int j = 0;
			while (cfObj.commonWaitForElementToBeVisible(driver, element, 10)) {
				Thread.sleep(5000);
				if (j > 2) {
					break;
				}
				j++;
			}

		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("waitForPageLoading_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean clickOnSubmitTestBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnSubmitTest(), 10);
			if (!result) {
				commonTestMsgList.add("BtnSubmitTest is not visible.");
				return result;
			}

			cfObj.commonClick(commonTestPageObj.getBtnSubmitTest());

			result = verifyAndSubmitFinishTestPopUp(driver);
			if (!result) {
				commonTestMsgList.add("Not able to verify and submit test.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("clickOnSubmitTestBtn_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean takeAndSubmitSectionalTimeLimitMockTest(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnFilter(), 10);
			if (!result) {
				commonTestMsgList.add("getBtnFilter btn is not visible.");
				return result;
			}

			cfObj.commonClick(commonTestPageObj.getBtnFilter());

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getSectionList().get(1), 10);
			if (!result) {
				commonTestMsgList.add("SectionList_2 is not visible.");
				return result;
			}

			cfObj.commonClick(commonTestPageObj.getSectionList().get(1));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.TextView[@text='Test Summary']", "xpath", 10);
			if (!result) {
				commonTestMsgList
						.add("TestSummary title in SectionList_2 is not visible on Test Submit popup screen. ");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "bottom_error_text", "id", 10);
			if (!result) {
				commonTestMsgList.add("SubmitTest text in SectionList_2 is not visible on Test Submit popup screen. ");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "negative_button", "id", 10);
			if (!result) {
				commonTestMsgList.add("No button is not visible in SectionList_2 on Test Submit popup screen. ");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "positive_button", "id", 10);
			if (!result) {
				commonTestMsgList
						.add("Submittest button is not visible in SectionList_2 on Test Submit popup screen. ");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "positive_button", "id"));

			Thread.sleep(2000);

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnFilter(), 10);
			if (!result) {
				commonTestMsgList.add("getBtnFilter btn is not visible.");
				return result;
			}

			cfObj.commonClick(commonTestPageObj.getBtnFilter());

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getSectionList().get(2), 10);
			if (!result) {
				commonTestMsgList.add("SectionList_2 is not visible.");
				return result;
			}

			cfObj.commonClick(commonTestPageObj.getSectionList().get(2));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.TextView[@text='Test Summary']", "xpath", 10);
			if (!result) {
				commonTestMsgList
						.add("TestSummary title in SectionList_3 is not visible on Test Submit popup screen. ");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "bottom_error_text", "id", 10);
			if (!result) {
				commonTestMsgList.add("SubmitTest text in SectionList_3 is not visible on Test Submit popup screen. ");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "negative_button", "id", 10);
			if (!result) {
				commonTestMsgList.add("No button is not visible in SectionList_3 on Test Submit popup screen. ");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "positive_button", "id", 10);
			if (!result) {
				commonTestMsgList
						.add("Submittest button is not visible in SectionList_3 on Test Submit popup screen. ");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "positive_button", "id"));

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnFilter(), 10);
			if (!result) {
				commonTestMsgList.add("getBtnFilter btn is not visible.");
				return result;
			}

			cfObj.commonClick(commonTestPageObj.getBtnFilter());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/secondary_button",
					"id", 10);
			if (!result) {
				commonTestMsgList.add("Submit section is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/primary_button", "id",
					10);
			if (!result) {
				commonTestMsgList.add("Submit test is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/primary_button", "id"));

		} catch (Exception e) {
			commonTestMsgList.add("takeAndSubmitOptionalTest_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean validateSectionalTimeLimitMockTestSolutionBtn(AppiumDriver<MobileElement> driver,
			boolean isCalculator, boolean isQB) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getInCorrectCount(), 10);
			if (!result) {
				commonTestMsgList.add("InCorrect count is not visible.");
				return result;
			}

			int incorrectCount = Integer
					.parseInt(commonTestPageObj.getInCorrectCount().getText().replace("Wrong", "").trim());
			int unAnsweredCount = Integer
					.parseInt(commonTestPageObj.getUnAnsweredCount().getText().replace("Unanswered", "").trim());

			incorrectCount = incorrectCount + unAnsweredCount;
			System.out.println("InCorrect Count:-> " + incorrectCount);

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnSolution(), 10);
			if (!result) {
				commonTestMsgList.add("Solution button is not visible.");
				return result;
			}

			cfObj.commonClick(commonTestPageObj.getBtnSolution());

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getQuestionLevel(), 10);
			if (!result) {
				commonTestMsgList.add("QuestionLevel is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTimeSpentText(), 10);
			if (!result) {
				commonTestMsgList.add("TimeSpentText is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnFilter(), 10);
			if (!result) {
				commonTestMsgList.add("Filter button is not visible.");
				return result;
			}
			cfObj.commonClick(commonTestPageObj.getBtnFilter());

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getSectionList().get(0), 10);
			if (!result) {
				commonTestMsgList.add("SectionList is not visible.");
				return result;
			}

			int sectionCount = commonTestPageObj.getSectionList().size();
			int showCorrectAnswerBtnCount = 0;
			fixedMockTestUtilObj = new FixedMockTestUtil(driver);
			for (int j = 0; j < sectionCount; j++) {
				if (j > 0) {
					cfObj.commonClick(commonTestPageObj.getBtnFilter());
				}
				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getSectionList().get(j), 10);
				if (!result) {
					commonTestMsgList.add("SectionList is not visible.");
					return result;
				}
				cfObj.commonClick(commonTestPageObj.getSectionList().get(j));
				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getQuestionTabList().get(0),
						10);
				if (!result) {
					commonTestMsgList.add("QuestionTabList is not visible.");
					return result;
				}

				int questionCount = commonTestPageObj.getQuestionTabList().size();
				cfObj.commonClick(commonTestPageObj.getBtnFilter());

				for (int i = 0; i < questionCount; i++) {
					if (isCalculator) {
						result = validateCalculatorButton(driver);
						if (!result) {
							commonTestMsgList.add("Not able to validate Calculator button.");
							return result;
						}
					}

					switch (j) {
					case 0:
						cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
								"//android.widget.TextView[@text='Section_One']", "xpath", 10);
						if (!result) {
							commonTestMsgList.add("Section_One title is not visible.");
							return result;
						}

						break;
					case 1:
						cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
								"//android.widget.TextView[@text='Section_Two']", "xpath", 10);
						if (!result) {
							commonTestMsgList.add("Section_Two title is not visible.");
							return result;
						}

						break;
					case 2:
						cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
								"//android.widget.TextView[@text='Section_Three']", "xpath", 10);
						if (!result) {
							commonTestMsgList.add("Section_Two title is not visible.");
							return result;
						}
						break;
					}

					if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda") && i == 0) {
						result = fixedMockTestUtilObj.validateBookMarkIcon(driver);
						if (!result) {
							commonTestMsgList.addAll(fixedMockTestUtilObj.fixedMockTestMsgList);
							return result;
						}

						result = fixedMockTestUtilObj.validateShareIcon(driver);
						if (!result) {
							commonTestMsgList.addAll(fixedMockTestUtilObj.fixedMockTestMsgList);
							return result;
						}

						result = fixedMockTestUtilObj.validateReportContentIcon(driver, isQB);
						if (!result) {
							commonTestMsgList.addAll(fixedMockTestUtilObj.fixedMockTestMsgList);
							return result;
						}

					} else if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
						result = cfObj.commonWaitForElementToBeVisible(driver,
								fixedMockTestUtilObj.fixedMockTestPageObj.getIconShare(), 10);
						if (!result) {
							commonTestMsgList.add("IconShare is not visible.");
							return result;
						}

						result = cfObj.commonWaitForElementToBeVisible(driver,
								fixedMockTestUtilObj.fixedMockTestPageObj.getIconBookmark(), 10);
						if (!result) {
							commonTestMsgList.add("IconBookmark is not visible.");
							return result;
						}
						result = cfObj.commonWaitForElementToBeVisible(driver,
								fixedMockTestUtilObj.fixedMockTestPageObj.getIconReportContent(), 10);
						if (!result) {
							commonTestMsgList.add("IconReportContent is not visible.");
							return result;
						}
					}
					cfObj.commonClick(commonTestPageObj.getBtnFilter());
					result = cfObj.commonWaitForElementToBeVisible(driver,
							commonTestPageObj.getQuestionTabList().get(i), 10);
					if (!result) {
						commonTestMsgList.add("QuestionTabList is not visible.");
						return result;
					}
					cfObj.commonClick(commonTestPageObj.getQuestionTabList().get(i));

					if (cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getShowCorrectAnswerBtn(),
							10)) {
						showCorrectAnswerBtnCount++;
						cfObj.scrollUtill(driver, 1, direction.DOWN);
						result = !cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getExplanationTitle(),
								10);
						if (!result) {
							commonTestMsgList.add("ExplanationTitle is visible.");
							return result;
						}
						// validate ShowCorrectAnswerBtn is clickable or not
						cfObj.commonClick(commonTestPageObj.getShowCorrectAnswerBtn());
						Thread.sleep(1000);
						result = !cfObj.commonWaitForElementToBeVisible(driver,
								commonTestPageObj.getShowCorrectAnswerBtn(), 3);
						if (!result) {
							commonTestMsgList.add("ShowCorrectAnswerBtn is clickable.");
							return result;
						}
					}
					cfObj.scrollUtill(driver, 1, direction.DOWN);
					result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getExplanationTitle(), 10);
					if (!result) {
						commonTestMsgList.add("ExplanationTitle is not visible.");
						return result;
					}
				}
			}

			System.out.println(showCorrectAnswerBtnCount);
			result = incorrectCount == showCorrectAnswerBtnCount;
			if (!result) {
				commonTestMsgList.add("showCorrectAnswerBtn is not visible on every incorrect answer.");
				return result;
			}

			cfObj.commonClick(commonTestPageObj.getBtnNavigateUp());

		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("validateTestSolutionBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateSolutionBtnStatus(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnSolution(), 10);
			if (!result) {
				commonTestMsgList.add("Solution button is Clickable.");
				return result;
			}

			cfObj.commonClick(commonTestPageObj.getBtnSolution());
			Thread.sleep(2000);
			result = !cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnSolution(), 10);
			if (!result) {
				commonTestMsgList.add("Solution button is not Clickable.");
				return result;
			}

			cfObj.pressAndroidKey(driver, key.BACK, 1);

		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("validateSolutionBtnStatus_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateSolutionBtnStatusInCutOffMockCase(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getCutOffMark(), 10);
			if (!result) {
				commonTestMsgList.add("CutOffMark is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getMarkScoredText(), 10);
			if (!result) {
				commonTestMsgList.add("MarkScoredText is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnSolution(), 10);
			if (!result) {
				commonTestMsgList.add("Solution button is not visible.");
				return result;
			}
			// int cutOffMark=Integer.parseInt(commonTestPageObj.getCutOffMark().getText());
			double cutOffMark = 60.00;
			double markScored = Double.parseDouble(commonTestPageObj.getMarkScoredText().getText());

			if (cutOffMark > markScored) {
				cfObj.commonClick(commonTestPageObj.getBtnSolution());
				Thread.sleep(2000);
				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnSolution(), 10);
				if (!result) {
					commonTestMsgList.add("Solution button is Clickable.");
					return result;
				}
			} else {
				cfObj.commonClick(commonTestPageObj.getBtnSolution());
				Thread.sleep(2000);
				result = !cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnSolution(), 10);
				if (!result) {
					commonTestMsgList.add("Solution button is not Clickable.");
					return result;
				}

				cfObj.pressAndroidKey(driver, key.BACK, 1);
			}

		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("validateSolutionBtnStatusInCutOffMockCase_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean takeAndSubmitTestWithCorrectAnswer(AppiumDriver<MobileElement> driver, boolean calculatorStatus,
			boolean isMultilanguage, int questionCount) {
		boolean result = true;
		boolean optionsVisible = false;
		try {

			// cfObj.commonClick(commonTestPageObj.getBtnFilter());
			// String str = commonTestPageObj.getQuestionCount().getText();
			// if(str==null) {
			// commonTestMsgList.add("Question count text is null.");
			// return false;
			// }
			// str = str.substring(12);
			// int count = Integer.parseInt(str);
			// cfObj.commonClick(commonTestPageObj.getBtnFilter());

			for (int i = 1; i <= questionCount; i++) {
				if (calculatorStatus) {
					result = validateCalculatorButton(driver);
					if (!result) {
						commonTestMsgList.add("Not able to validate Calculator button.");
						return result;
					}
				}
				if (isMultilanguage) {
					result = validateLanguageButton(driver);
					if (!result) {
						commonTestMsgList.add("Not able to validate Language button.");
						return result;
					}
				}
				optionsVisible = commonTestPageObj.getListAnswers().size() != 0;
				while (!optionsVisible) {
					cfObj.scrollUtill(driver, 1, direction.DOWN);
					Thread.sleep(1000);
					optionsVisible = commonTestPageObj.getListAnswers().size() != 0;
				}
				switch (i) {
				case 1:
					cfObj.commonClick(commonTestPageObj.getListAnswers().get(2));
					break;
				case 2:
					cfObj.commonClick(commonTestPageObj.getListAnswers().get(3));
					break;
				case 3:
					cfObj.commonClick(commonTestPageObj.getListAnswers().get(0));
					break;
				case 4:
					cfObj.commonClick(commonTestPageObj.getListAnswers().get(1));
					break;
				case 5:
					cfObj.commonClick(commonTestPageObj.getListAnswers().get(1));
					break;

				}
				Thread.sleep(200);
				if (i == questionCount) {
					result = commonTestPageObj.getBtnSaveAndNext().getText().contains("Submit");
					if (!result) {
						commonTestMsgList.add("Submit button is not visible.");
						return result;
					}
				}

				cfObj.commonClick(commonTestPageObj.getBtnSaveAndNext());
				Thread.sleep(200);
			}

		} catch (Exception e) {
			commonTestMsgList.add("takeAndSubmitTestWithCorrectAnswer_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyDigiFillerTestResultScreen(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			Thread.sleep(2000);

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/toolbar_title", "id", 30);
			if (!result) {
				commonTestMsgList.add("Result screen title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/viewSolutionBtn", "id", 30);
			if (!result) {
				commonTestMsgList.add("Solution button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/reattemptBtn", "id", 10);
			if (!result) {
				commonTestMsgList.add("ReAttempt test button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/toolbar_title_back_arrow", "id", 10);
			if (!result) {
				commonTestMsgList.add("Result screen back button is not visible.");
				return result;
			}
		} catch (Exception e) {
			commonTestMsgList.add("verifyDigiFillerTestResultScreen_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean validateResultOverviewScreen(AppiumDriver<MobileElement> driver, boolean internetStatus) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnSolution(), 10);
			if (!result) {
				commonTestMsgList.add("Unable to solution btn test analysis page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getLinkShareScoreCard(), 10);
			if (!result) {
				commonTestMsgList.add("Unable to share score card link test analysis page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTextScored(), 10);
			if (!result) {
				commonTestMsgList.add("Unable to scored points test analysis page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getMarkScoredText(), 10);
			if (!result) {
				commonTestMsgList.add("MarkScoredText is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTotalMarksText(), 10);
			if (!result) {
				commonTestMsgList.add("TotalMarksText is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTextRank(), 10);
			if (!result) {
				commonTestMsgList.add("Unable to rank in test analysis page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getRankScoredText(), 10);
			if (!result) {
				commonTestMsgList.add("RankScoredText is not visible.");
				return result;
			}

			if (internetStatus) {
				result = cfObj.commonVerifyValueTextBox(commonTestPageObj.getRankScoredText(), "--");
				if (!result) {
					commonTestMsgList.add("Empty rank is not visible.");
					return result;
				}
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getRankProgressBarBtn(), 10);
			if (!result) {
				commonTestMsgList.add("RankProgressBarBtn is not visible.");
				return result;
			}
			cfObj.commonClick(commonTestPageObj.getRankProgressBarBtn());
			if (internetStatus) {
				result = cfObj.commonVerifyValueTextBox(commonTestPageObj.getToasteMessage(),
						"Unable to get rank. Please check your internet connection and try again.");
				if (!result) {
					commonTestMsgList.add("ToastMessage is not visible.");
					// return result;
				}
			} else {
				result = cfObj.commonVerifyValueTextBox(commonTestPageObj.getToasteMessage(),
						"Your rank is up to date.");
				if (!result) {
					commonTestMsgList.add("ToastMessage is not visible.");
					// return result;
				}
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTotalRankText(), 10);
			if (!result) {
				commonTestMsgList.add("TotalRankText is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTextTimeSpent(), 10);
			if (!result) {
				commonTestMsgList.add("Unable to time spent in test analysis page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTimeSpentValue(), 10);
			if (!result) {
				commonTestMsgList.add("TimeSpentValue is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnNavigateUp(), 10);
			if (!result) {
				commonTestMsgList.add("Unable to navigate up btn test analysis page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getReAttemptBtn(), 10);
			if (!result) {
				commonTestMsgList.add("REATTEMPT button is not visible.");
				return result;
			}

			cfObj.scrollUtill(driver, 1, direction.DOWN);
			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getPostNowBtn(), 10);
				if (!result) {
					commonTestMsgList.add("PostNow button is not visible.");
					return result;
				}
			}
			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getMultiLevelProgressBarIcon(),
					10);
			if (!result) {
				commonTestMsgList.add("MultiLevelProgressBar is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getProgressTextPercentile(), 10);
			if (!result) {
				commonTestMsgList.add("ProgressTextPercentile is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getAccuracyPercentageText(), 10);
			if (!result) {
				commonTestMsgList.add("AccuracyPercentageText is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getAttemptedPercentageText(), 10);
			if (!result) {
				commonTestMsgList.add("AttemptedPercentageText is not visible.");
				return result;
			}
			cfObj.scrollUtill(driver, 1, direction.DOWN);

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTopRankerHeader(), 10);
			if (!result) {
				commonTestMsgList.add("TopRanker Header is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getRankerProfilePic().get(0), 10);
			if (!result) {
				commonTestMsgList.add("RankerProfile Picture is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getRankerName().get(0), 10);
			if (!result) {
				commonTestMsgList.add("Ranker Name is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getRankText().get(0), 10);
			if (!result) {
				commonTestMsgList.add("Ranker Rank number is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getRankerMarkScored().get(0), 10);
			if (!result) {
				commonTestMsgList.add("Ranker Mark Scored text is not visible.");
				return result;
			}

		} catch (Exception e) {
			commonTestMsgList.add("validateResultOverviewScreen_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean validateSectionsReportScreen(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getOverallTab(), 10);
			if (!result) {
				commonTestMsgList.add("Overall Tab is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTestTab(), 10);
			if (!result) {
				commonTestMsgList.add("Test Tab is not visible.");
				return result;
			}

			for (int i = 0; i < 2; i++) {
				String sectionName = commonTestPageObj.getSectionName().getText();

				if (i == 0) {
					cfObj.commonClick(commonTestPageObj.getOverallTab());

					result = commonTestPageObj.getOverallTab().getAttribute("selected").equalsIgnoreCase("true");
					if (!result) {
						commonTestMsgList.add("Failed to select Overall Tab.");
						return result;
					}

					result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getSectionName(), 10);
					if (!result) {
						commonTestMsgList.add("Section name is not visible in Overall Tab.");
						return result;
					}

					result = cfObj.commonVerifyValueTextBox(commonTestPageObj.getSectionName(), "Overall");
					if (!result) {
						commonTestMsgList.add("Section name is not Correct in Overall Tab.");
						return result;
					}
					result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTotalTime(), 10);
					if (!result) {
						commonTestMsgList.add("TotalTime text is not visible in Overall Tab.");
						return result;
					}
				} else {
					cfObj.commonClick(commonTestPageObj.getTestTab());

					result = commonTestPageObj.getTestTab().getAttribute("selected").equalsIgnoreCase("true");
					if (!result) {
						commonTestMsgList.add("Failed to select Test Tab.");
						return result;
					}

					result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getSectionName(), 10);
					if (!result) {
						commonTestMsgList.add("Section name is not visible in Test Tab.");
						return result;
					}

					result = cfObj.commonVerifyValueTextBox(commonTestPageObj.getSectionName(), "Test");
					if (!result) {
						commonTestMsgList.add("Section name is not Correct in Test Tab.");
						return result;
					}
					result = !cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTotalTime(), 5);
					if (!result) {
						commonTestMsgList.add("TotalTime text should not be visible on Test Tab.");
						return result;
					}
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getScoreText(), 10);
				if (!result) {
					commonTestMsgList.add("Score Text is not visible in " + sectionName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTotalScoreText(), 10);
				if (!result) {
					commonTestMsgList.add("TotalScore text is not visible in " + sectionName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getRankNumber(), 10);
				if (!result) {
					commonTestMsgList.add("Rank Number is not visible in " + sectionName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTotalRankCount(), 10);
				if (!result) {
					commonTestMsgList.add("TotalRank Count is not visible in " + sectionName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getAccuracyText(), 10);
				if (!result) {
					commonTestMsgList.add("Accuracy Text is not visible in " + sectionName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTimeSpent(), 10);
				if (!result) {
					commonTestMsgList.add("TimeSpent text is not visible in " + sectionName + " Tab.");
					return result;
				}

				int correctCount = Integer
						.parseInt(commonTestPageObj.getCorrectCount().getText().replace("Correct", "").trim());
				System.out.println("Correct Count:- " + correctCount);
				int incorrectCount = Integer
						.parseInt(commonTestPageObj.getInCorrectCount().getText().replace("Wrong", "").trim());
				System.out.println("InCorrect Count:- " + incorrectCount);
				int unAnsweredCount = Integer
						.parseInt(commonTestPageObj.getUnAnsweredCount().getText().replace("Unanswered", "").trim());
				System.out.println("UnAnswered Count:- " + unAnsweredCount);
				if (correctCount > 0) {
					result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getCorrectProgressBar(),
							10);
					if (!result) {
						commonTestMsgList.add("Correct ProgressBar is not visible in " + sectionName + " Tab.");
						return result;
					}
				}
				if (incorrectCount > 0) {

					result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getWrongProgressBar(), 10);
					if (!result) {
						commonTestMsgList.add("Wrong ProgressBar is not visible in " + sectionName + " Tab.");
						return result;
					}
				}
				if (unAnsweredCount > 0) {
					result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getUnAnsweredProgressBar(),
							10);
					if (!result) {
						commonTestMsgList.add("UnAnswered ProgressBar is not visible in " + sectionName + " Tab.");
						return result;
					}
				}
				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getCorrectCount(), 10);
				if (!result) {
					commonTestMsgList.add("Correct Count is not visible in " + sectionName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getInCorrectCount(), 10);
				if (!result) {
					commonTestMsgList.add("InCorrect is not visible in " + sectionName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getUnAnsweredCount(), 10);
				if (!result) {
					commonTestMsgList.add("UnAnswered Count is not visible in " + sectionName + " Tab.");
					return result;
				}
			}
		} catch (Exception e) {
			commonTestMsgList.add("validateSectionsReportScreen_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean validateCompareYourSelfScreen(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getOverallTab(), 10);
			if (!result) {
				commonTestMsgList.add("Overall Tab is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getSubjectTab(), 10);
			if (!result) {
				commonTestMsgList.add("Subject Tab is not visible.");
				return result;
			}

			for (int i = 0; i < 2; i++) {
				String tabName = null;
				if (i == 0) {
					tabName = "Overall";
					cfObj.commonClick(commonTestPageObj.getOverallTab());

					result = commonTestPageObj.getOverallTab().getAttribute("selected").equalsIgnoreCase("true");
					if (!result) {
						commonTestMsgList.add("Failed to select Overall Tab.");
						return result;
					}
				} else {
					tabName = commonTestPageObj.getSubjectTab().getText();
					cfObj.commonClick(commonTestPageObj.getSubjectTab());

					result = commonTestPageObj.getSubjectTab().getAttribute("selected").equalsIgnoreCase("true");
					if (!result) {
						commonTestMsgList.add("Failed to select " + tabName + " Tab.");
						return result;
					}
				}
				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getScoreHeader(), 10);
				if (!result) {
					commonTestMsgList.add("Score Header is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getScoreSectionSubTitle(), 10);
				if (!result) {
					commonTestMsgList.add("Score Section SubTitle is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getScoreSectionAverageCount(),
						10);
				if (!result) {
					commonTestMsgList.add("Score Section Average count is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getScoreSectionAverageText(),
						10);
				if (!result) {
					commonTestMsgList.add("Score Section Average text is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonTestPageObj.getScoreSectionBar1TipLabelCount(), 10);
				if (!result) {
					commonTestMsgList.add("Score Section Bar1 TipLabel count is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonTestPageObj.getScoreSectionBar2TipLabelCount(), 10);
				if (!result) {
					commonTestMsgList.add("Score Section Bar2 TipLabel count is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getScoreSectionBar1Img(), 10);
				if (!result) {
					commonTestMsgList.add("Score Section Bar1 image is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getScoreSectionBar2Img(), 10);
				if (!result) {
					commonTestMsgList.add("Score Section Bar2 image is not visible in " + tabName + " Tab.");
					return result;
				}
				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getScoreSectionTopperText(),
						10);
				if (!result) {
					commonTestMsgList.add("Score Section Topper Text is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getScoreSectionOwnText(), 10);
				if (!result) {
					commonTestMsgList.add("Score Section Own Text is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getAccuracyHeader(), 10);
				if (!result) {
					commonTestMsgList.add("Accuracy Header is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getAccuracySectionSubTitle(),
						10);
				if (!result) {
					commonTestMsgList.add("Accuracy Section SubTitle is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonTestPageObj.getAccuracySectionAverageCount(), 10);
				if (!result) {
					commonTestMsgList.add("Accuracy Section Average count is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonTestPageObj.getAccuracySectionAverageText(), 10);
				if (!result) {
					commonTestMsgList.add("Accuracy Section Average text is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonTestPageObj.getAccuracySectionBar1TipLabelCount(), 10);
				if (!result) {
					commonTestMsgList
							.add("Accuracy Section Bar1 TipLabel count is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonTestPageObj.getAccuracySectionBar2TipLabelCount(), 10);
				if (!result) {
					commonTestMsgList
							.add("Accuracy Section Bar2 TipLabel count is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getAccuracySectionBar1Img(),
						10);
				if (!result) {
					commonTestMsgList.add("Accuracy Section Bar1 image is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getAccuracySectionBar2Img(),
						10);
				if (!result) {
					commonTestMsgList.add("Accuracy Section Bar2 image is not visible in " + tabName + " Tab.");
					return result;
				}
				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getAccuracySectionTopperText(),
						10);
				if (!result) {
					commonTestMsgList.add("Accuracy Section Topper Text is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getAccuracySectionOwnText(),
						10);
				if (!result) {
					commonTestMsgList.add("Accuracy Section Own Text is not visible in " + tabName + " Tab.");
					return result;
				}

				cfObj.scrollUtill(driver, 1, direction.DOWN);

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getCorrectHeader(), 10);
				if (!result) {
					commonTestMsgList.add("Correct Header is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getCorrectSectionSubTitle(),
						10);
				if (!result) {
					commonTestMsgList.add("Correct Section SubTitle is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonTestPageObj.getCorrectSectionAverageCount(), 10);
				if (!result) {
					commonTestMsgList.add("Correct Section Average count is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getCorrectSectionAverageText(),
						10);
				if (!result) {
					commonTestMsgList.add("Correct Section Average text is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonTestPageObj.getCorrectSectionBar1TipLabelCount(), 10);
				if (!result) {
					commonTestMsgList.add("Correct Section Bar1 TipLabel count is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonTestPageObj.getCorrectSectionBar2TipLabelCount(), 10);
				if (!result) {
					commonTestMsgList.add("Correct Section Bar2 TipLabel count is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getCorrectSectionBar1Img(),
						10);
				if (!result) {
					commonTestMsgList.add("Correct Section Bar1 image is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getCorrectSectionBar2Img(),
						10);
				if (!result) {
					commonTestMsgList.add("Correct Section Bar2 image is not visible in " + tabName + " Tab.");
					return result;
				}
				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getCorrectSectionTopperText(),
						10);
				if (!result) {
					commonTestMsgList.add("Correct Section Topper Text is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getCorrectSectionOwnText(),
						10);
				if (!result) {
					commonTestMsgList.add("Correct Section Own Text is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getIncorrectHeader(), 10);
				if (!result) {
					commonTestMsgList.add("Incorrect Header is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getIncorrectSectionSubTitle(),
						10);
				if (!result) {
					commonTestMsgList.add("Incorrect Section SubTitle is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonTestPageObj.getIncorrectSectionAverageCount(), 10);
				if (!result) {
					commonTestMsgList.add("Incorrect Section Average count is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonTestPageObj.getIncorrectSectionAverageText(), 10);
				if (!result) {
					commonTestMsgList.add("Incorrect Section Average text is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonTestPageObj.getIncorrectSectionBar1TipLabelCount(), 10);
				if (!result) {
					commonTestMsgList
							.add("Incorrect Section Bar1 TipLabel count is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonTestPageObj.getIncorrectSectionBar2TipLabelCount(), 10);
				if (!result) {
					commonTestMsgList
							.add("Incorrect Section Bar2 TipLabel count is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getIncorrectSectionBar1Img(),
						10);
				if (!result) {
					commonTestMsgList.add("Incorrect Section Bar1 image is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getIncorrectSectionBar2Img(),
						10);
				if (!result) {
					commonTestMsgList.add("Incorrect Section Bar2 image is not visible in " + tabName + " Tab.");
					return result;
				}
				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonTestPageObj.getIncorrectSectionTopperText(), 10);
				if (!result) {
					commonTestMsgList.add("Incorrect Section Topper Text is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getIncorrectSectionOwnText(),
						10);
				if (!result) {
					commonTestMsgList.add("Incorrect Section Own Text is not visible in " + tabName + " Tab.");
					return result;
				}

				cfObj.scrollUtill(driver, 1, direction.DOWN);

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTimeSpentHeader(), 10);
				if (!result) {
					commonTestMsgList.add("TimeSpent Header is not visible in " + tabName + " Tab.");
					return result;
				}
				if (i == 0) {
					result = cfObj.commonWaitForElementToBeVisible(driver,
							commonTestPageObj.getTimeSpentSectionSubTitle(), 10);
					if (!result) {
						commonTestMsgList.add("TimeSpent Section SubTitle is not visible in " + tabName + " Tab.");
						return result;
					}
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonTestPageObj.getTimeSpentSectionAverageCount(), 10);
				if (!result) {
					commonTestMsgList.add("TimeSpent Section Average count is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonTestPageObj.getTimeSpentSectionAverageText(), 10);
				if (!result) {
					commonTestMsgList.add("TimeSpent Section Average text is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonTestPageObj.getTimeSpentSectionBar1TipLabelCount(), 10);
				if (!result) {
					commonTestMsgList
							.add("TimeSpent Section Bar1 TipLabel count is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonTestPageObj.getTimeSpentSectionBar2TipLabelCount(), 10);
				if (!result) {
					commonTestMsgList
							.add("TimeSpent Section Bar2 TipLabel count is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTimeSpentSectionBar1Img(),
						10);
				if (!result) {
					commonTestMsgList.add("TimeSpent Section Bar1 image is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTimeSpentSectionBar2Img(),
						10);
				if (!result) {
					commonTestMsgList.add("TimeSpent Section Bar2 image is not visible in " + tabName + " Tab.");
					return result;
				}
				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonTestPageObj.getTimeSpentSectionTopperText(), 10);
				if (!result) {
					commonTestMsgList.add("TimeSpent Section Topper Text is not visible in " + tabName + " Tab.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTimeSpentSectionOwnText(),
						10);
				if (!result) {
					commonTestMsgList.add("TimeSpent Section Own Text is not visible in " + tabName + " Tab.");
					return result;
				}

			}

		} catch (Exception e) {
			commonTestMsgList.add("validateCompareYourSelfScreen_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean validateResultAnalysisDropDown(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getResultAnalysisDropDown(), 10);
			if (!result) {
				commonTestMsgList.add("ResultAnalysis DropDown is not visible.");
				return result;
			}

			cfObj.commonClick(commonTestPageObj.getResultAnalysisDropDown());

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getResultAnalysisDropDownTitle(),
					10);
			if (!result) {
				commonTestMsgList.add("ResultAnalysis DropDown title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,
					commonTestPageObj.getResultAnalysisDropDownCloseBtn(), 10);
			if (!result) {
				commonTestMsgList.add("ResultAnalysis DropDown close button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,
					commonTestPageObj.getResultAnalysisOptionList().get(0), 10);
			if (!result) {
				commonTestMsgList.add("ResultAnalysis option List is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,
					commonTestPageObj.getResultAnalysisOptionSelectIcon(), 10);
			if (!result) {
				commonTestMsgList.add("ResultAnalysis option Select Icon is not visible.");
				return result;
			}

			cfObj.commonClick(commonTestPageObj.getResultAnalysisDropDownCloseBtn());
			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getResultAnalysisDropDown(), 10);
			if (!result) {
				commonTestMsgList.add("Failed to close ResultAnalysisDropDown.");
				return result;
			}

		} catch (Exception e) {
			commonTestMsgList.add("validateResultAnalysisDropDown_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean selectSpecificSectionFromResultAnalysisDropDown(AppiumDriver<MobileElement> driver, int index) {
		boolean result = true;
		String resultAnalysisOption = null;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getResultAnalysisDropDown(), 10);
			if (!result) {
				commonTestMsgList.add("ResultAnalysis DropDown is not visible.");
				return result;
			}

			cfObj.commonClick(commonTestPageObj.getResultAnalysisDropDown());

			result = cfObj.commonWaitForElementToBeVisible(driver,
					commonTestPageObj.getResultAnalysisOptionList().get(index), 10);
			if (!result) {
				commonTestMsgList.add("ResultAnalysis option List is not visible.");
				return result;
			}
			resultAnalysisOption = commonTestPageObj.getResultAnalysisOptionList().get(index).getText();

			cfObj.commonClick(commonTestPageObj.getResultAnalysisOptionList().get(index));

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getResultAnalysisDropDown(), 10);
			if (!result) {
				commonTestMsgList.add(
						"ResultAnalysisDropDowd should be closed by selecting " + resultAnalysisOption + " Section.");
				return result;
			}

			result = cfObj.commonVerifyValueTextBox(commonTestPageObj.getResultAnalysisDropDown(),
					resultAnalysisOption);
			if (!result) {
				commonTestMsgList
						.add("Failed to select " + resultAnalysisOption + " Section from ResultAnalysisDropDowd.");
				return result;
			}

		} catch (Exception e) {
			commonTestMsgList.add("selectSpecificSectionFromResultAnalysisDropDown_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean validateSolutionScreen(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnSolution(), 10);
			if (!result) {
				commonTestMsgList.add("Solution button is not visible on Result screen.");
				return result;
			}
			cfObj.commonClick(commonTestPageObj.getBtnSolution());

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getQuestionLevel(), 10);
			if (!result) {
				commonTestMsgList.add("QuestionLevel is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTimeSpentText(), 10);
			if (!result) {
				commonTestMsgList.add("TimeSpentText is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnFilter(), 10);
			if (!result) {
				commonTestMsgList.add("Filter button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnNavigateUp(), 10);
			if (!result) {
				commonTestMsgList.add("Back button is not visible.");
				return result;
			}

			cfObj.commonClick(commonTestPageObj.getBtnNavigateUp());
			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnSolution(), 10);
			if (!result) {
				commonTestMsgList.add("Failed to close TestSolution Screen.");
				return result;
			}

		} catch (Exception e) {
			commonTestMsgList.add("validateSolutionScreen_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean validatePostNowButton(AppiumDriver<MobileElement> driver, String postQuery) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getPostNowBtn(), 10);
			if (!result) {
				commonTestMsgList.add("PostNow button is not visible.");
				return result;
			}

			cfObj.commonClick(commonTestPageObj.getPostNowBtn());

			communityPageUtilObj = new CommunityPageUtil(driver);

			postingPageUtilObj = new PostingPageUtil(driver);

			result = postingPageUtilObj.clickOnAttachmentMCQPostTab(driver, PostType.PICTURE_ATTACHMENT_POST);
			if (!result) {
				commonTestMsgList.addAll(postingPageUtilObj.postingMsgList);
				return result;
			}

			result = communityPageUtilObj.selectGroupFromOptions(driver, "Automation");
			if (!result) {
				commonTestMsgList.add("Unable to select group from given options");
				return result;
			}

			postingPageUtilObj = new PostingPageUtil(driver);

			Thread.sleep(1000);

			result = postingPageUtilObj.enterQueryOrDoubt(driver, postQuery);
			if (!result) {
				commonTestMsgList.add("Unable to enter query in posting page");
				return result;
			}

			Thread.sleep(1000);

			result = postingPageUtilObj.clickPostSubmitBtn(driver);
			if (!result) {
				commonTestMsgList.add("Unable to select type of post from slider menu");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,
					postingPageUtilObj.postingPageObj.getTitleSelectTopic(), 10);
			if (result) {
				cfObj.commonClick(postingPageUtilObj.postingPageObj.getOptionTopics().get(0));

			}

			result = cfObj.commonWaitForElementToBeVisible(driver, postingPageUtilObj.postingPageObj.getPostBtn(), 10);
			if (!result) {
				commonTestMsgList.add("Post button is not visible.");
				return result;
			}
			cfObj.commonClick(postingPageUtilObj.postingPageObj.getPostBtn());
			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getPostNowBtn(), 10);
			if (!result) {
				commonTestMsgList.add("Failed to click PostNow button.");
				return result;
			}

		} catch (Exception e) {
			commonTestMsgList.add("validatePostNowButton_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean takeAndSubmitDescriptiveTypeTest(AppiumDriver<MobileElement> driver, int questionCount) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.EditText", "xpath", 10);
			if (!result) {
				commonTestMsgList.add("Answer TextBox is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/icon_bold", "id",
					10);
			if (!result) {
				commonTestMsgList.add("Bold icon in textBox is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/icon_underline", "id",
					10);
			if (!result) {
				commonTestMsgList.add("Underline icon in textBox is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/icon_bullet", "id",
					10);
			if (!result) {
				commonTestMsgList.add("Bullet icon in textBox is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/icon_number_bullet", "id",
					10);
			if (!result) {
				commonTestMsgList.add("Bullet icon in textBox is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/tv_word_limit_count", "id",
					10);
			if (!result) {
				commonTestMsgList.add("tv_word_limit_count icon in textBox is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.EditText", "xpath"));

			result=cfObj.commonSetTextTextBox(cfObj.commonGetElement(driver, "//android.widget.EditText", "xpath"), "Descriptive type Answer, 1st try");
			if(!result) {
				commonTestMsgList.add("Failed to enter Answer on Answer TextBox.");
				return result;
			}

			if (cfObj.commonGetElement(driver, "//android.widget.EditText", "xpath").getText().isEmpty()){

				cfObj.commonSetTextTextBox(cfObj.commonGetElement(driver, "//android.widget.EditText", "xpath"), "Descriptive type Answer, 2nd try");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,commonTestPageObj.getBtnSaveAndNext(),10);
			if (!result) {
				commonTestMsgList.add("SaveAndNext button is not visible.");
				return result;
			}

			cfObj.commonClick(commonTestPageObj.getBtnSaveAndNext());
			Thread.sleep(200);

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnFilter(), 10);
			if (!result) {
				commonTestMsgList.add("Filter button is not visible on Test screen.");
				return result;
			}

			cfObj.commonClick(commonTestPageObj.getBtnFilter());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "primary_button", "id", 10);
			if (!result) {
				commonTestMsgList.add("Submit test button is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "primary_button", "id"));

		} catch (Exception e) {
			commonTestMsgList.add("takeAndSubmitDescriptiveTypeTest_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean completeAndSubmitDescriptiveTypeTest(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			int questionCount = Integer
					.parseInt(cfObj.commonGetElement(driver, "com.adda247.app:id/total_question_count", "id").getText());

			result = clickStartTestBtn(driver);
			if (!result) {
				commonTestMsgList.add("Unable to click start test btn ");
				return result;
			}

			Thread.sleep(2000); // page loading takes time

			result = takeAndSubmitDescriptiveTypeTest(driver, questionCount);
			if (!result) {
				commonTestMsgList.add("Unable to take test");
				return result;
			}

			result = verifyAndSubmitFinishTestPopUp(driver);
			if (!result) {
				commonTestMsgList.add("Unable to verify finish test pop-up");
				return result;
			}

		} catch (Exception e) {
			commonTestMsgList.add("completeAndSubmitTest_Exception: " + e.getMessage());
			result = false;

		}
		return result;
	}

	public boolean validateDigiFillerTestSolutionBtn(AppiumDriver<MobileElement> driver, boolean isQB) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/viewSolutionBtn", "id", 10);
			if (!result) {
				commonTestMsgList.add("Solution button is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/viewSolutionBtn", "id"));
			Thread.sleep(2000);

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getQuestionLevel(), 10);
			if (!result) {
				commonTestMsgList.add("QuestionLevel is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTimeSpentText(), 10);
			if (!result) {
				commonTestMsgList.add("TimeSpentText is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnFilter(), 10);
			if (!result) {
				commonTestMsgList.add("Filter button is not visible.");
				return result;
			}
			cfObj.commonClick(commonTestPageObj.getBtnFilter());

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getQuestionTabList().get(0), 10);
			if (!result) {
				commonTestMsgList.add("QuestionTabList is not visible.");
				return result;
			}

			int questionCount = commonTestPageObj.getQuestionTabList().size();
			cfObj.commonClick(commonTestPageObj.getBtnFilter());
			fixedMockTestUtilObj = new FixedMockTestUtil(driver);
			for (int i = 0; i < questionCount; i++) {

				if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda") && i == 0) {
					result = fixedMockTestUtilObj.validateBookMarkIcon(driver);
					if (!result) {
						commonTestMsgList.addAll(fixedMockTestUtilObj.fixedMockTestMsgList);
						return result;
					}
					result = fixedMockTestUtilObj.validateShareIcon(driver);
					if (!result) {
						commonTestMsgList.addAll(fixedMockTestUtilObj.fixedMockTestMsgList);
						return result;
					}
					result = fixedMockTestUtilObj.validateReportContentIcon(driver, isQB);
					if (!result) {
						commonTestMsgList.addAll(fixedMockTestUtilObj.fixedMockTestMsgList);
						return result;
					}
				} else if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
					result = cfObj.commonWaitForElementToBeVisible(driver,
							fixedMockTestUtilObj.fixedMockTestPageObj.getIconShare(), 10);
					if (!result) {
						commonTestMsgList.add("IconShare is not visible.");
						return result;
					}

					result = cfObj.commonWaitForElementToBeVisible(driver,
							fixedMockTestUtilObj.fixedMockTestPageObj.getIconBookmark(), 10);
					if (!result) {
						commonTestMsgList.add("IconBookmark is not visible.");
						return result;
					}
					result = cfObj.commonWaitForElementToBeVisible(driver,
							fixedMockTestUtilObj.fixedMockTestPageObj.getIconReportContent(), 10);
					if (!result) {
						commonTestMsgList.add("IconReportContent is not visible.");
						return result;
					}

				}
				cfObj.commonClick(commonTestPageObj.getBtnFilter());
				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getQuestionTabList().get(i),
						10);
				if (!result) {
					commonTestMsgList.add("QuestionTabList is not visible.");
					return result;
				}
				cfObj.commonClick(commonTestPageObj.getQuestionTabList().get(i));
				Thread.sleep(2000);
				result = Integer.parseInt(commonTestPageObj.getQuestionNumber().getText()) - 1 == i;
				if (!result) {
					commonTestMsgList.add("Question Number is not matching.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "layout_model_ans", "id", 10);
				if (!result) {
					commonTestMsgList.add("Answer module dropdown is not visible.");
					return result;
				}
			}

			cfObj.commonClick(commonTestPageObj.getBtnNavigateUp());

		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("validateDigiFillerTestSolutionBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnDigiFillerTestReAttemptBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "reattemptBtn", "id", 10);
			if (!result) {
				commonTestMsgList.add("ReAttempt test button is not visible.");
				return result;
			}
			cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/reattemptBtn", "id"));

			// result=cfObj.commonWaitForElementToBeVisible(driver,
			// commonTestPageObj.getReAttemptPopUpTitle(), 10);
			// if(!result) {
			// commonTestMsgList.add("Re-Attempt PopUp Title is not visible.");
			// return result;
			// }
			//
			// result=cfObj.commonWaitForElementToBeVisible(driver,
			// commonTestPageObj.getReAttemptPopUpBtn(), 10);
			// if(!result) {
			// commonTestMsgList.add("Re-Attempt PopUp button is not visible.");
			// return result;
			// }
			// cfObj.commonClick(commonTestPageObj.getReAttemptPopUpBtn());
			Thread.sleep(1000);
			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnStartTest(), 10);
			if (!result) {
				commonTestMsgList.add("Failed to click Re-Attempt PopUp button.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("clickOnDigiFillerTestReAttemptBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateResultAwaitedTagOnSpecificFMTMock(AppiumDriver<MobileElement> driver, String fmtMockName) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//*[@text='" + fmtMockName
							+ "']/following-sibling::android.widget.LinearLayout/*[@text='RESULT AWAITED']",
					"xpath", 10);
			if (!result) {
				commonTestMsgList.add("ResultAwaited Tag is not visible.");
				return result;
			}
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='" + fmtMockName
					+ "']/following-sibling::android.widget.LinearLayout/*[contains(@resource-id,'tv_test_timer')]",
					"xpath", 10);
			if (!result) {
				commonTestMsgList.add("ResultAwaited Timer is not Visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			commonTestMsgList.add("validateResultAwaitedTagOnSpecificFMTMock_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyFMTMockTestAnalysisPage(AppiumDriver<MobileElement> driver, boolean isTestLive) {
		boolean result = true;
		try {

			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "toolbar_title", "id", 10);
				if (!result) {
					commonTestMsgList.add("Result screen title is not visible.");
					return result;
				}
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "toolbar_title_back_arrow", "id", 10);
				if (!result) {
					commonTestMsgList.add("Result screen back button is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "solution_btn_solo", "id", 10);
				if (!result) {
					commonTestMsgList.add("ViewSolution button is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "overallSummaryTv", "id", 10);
				if (!result) {
					commonTestMsgList.add("OverallSummarySection title is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "userScoreProgressImg", "id", 10);
				if (!result) {
					commonTestMsgList.add("User score progress image is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "userMarks", "id", 10);
				if (!result) {
					commonTestMsgList.add("User mark text is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "totalMarks", "id", 10);
				if (!result) {
					commonTestMsgList.add("Total mark text is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "yourScoreTv", "id", 10);
				if (!result) {
					commonTestMsgList.add("Your score title text is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "userTimeProgressImg", "id", 10);
				if (!result) {
					commonTestMsgList.add("User time Progress image is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "userTime", "id", 10);
				if (!result) {
					commonTestMsgList.add("User Time text is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "totalTime", "id", 10);
				if (!result) {
					commonTestMsgList.add("Total Time text is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "yourTimeTv", "id", 10);
				if (!result) {
					commonTestMsgList.add("Your Time title text is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "userRank", "id", 10);
				if (!result) {
					commonTestMsgList.add("User Rank text is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "userPercentile", "id", 10);
				if (!result) {
					commonTestMsgList.add("UserPercentile text is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "userAccuracy", "id", 10);
				if (!result) {
					commonTestMsgList.add("UserAccuracy text is not visible.");
					return result;
				}

				if (!isTestLive) {
					result = cfObj.commonGetElement(driver, "userRank", "id").getText().equalsIgnoreCase("--- | ---");
					if (!result) {
						commonTestMsgList.add("Empty rank is not visible.");
						return result;
					}

					result = cfObj.commonGetElement(driver, "userPercentile", "id").getText()
							.equalsIgnoreCase("--- | ---");
					if (!result) {
						commonTestMsgList.add("Empty Percentile is not visible.");
						return result;
					}

					result = cfObj.commonGetElement(driver, "userAccuracy", "id").getText()
							.equalsIgnoreCase("--- | ---");
					if (!result) {
						commonTestMsgList.add("Empty Accuracy is not visible.");
						return result;
					}
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "share_score_btn", "id", 10);
				if (!result) {
					commonTestMsgList.add("Share score btn is not visible.");
					return result;
				}
				if (!isTestLive) {
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "reattempt_btn", "id", 10);
					if (!result) {
						commonTestMsgList.add("Reattempt btn is not visible.");
						return result;
					}
				} else {
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "reattempt_btn", "id", 10);
					if (!result) {
						commonTestMsgList.add("Reattempt btn is not visible.");
						return result;
					}
				}

			} else {

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "result_card_title", "id", 10);
				if (!result) {
					commonTestMsgList.add("Mock Title is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnSolution(), 10);
				if (!result) {
					commonTestMsgList.add("Unable to solution btn test analysis page");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getLinkShareScoreCard(), 10);
				if (!result) {
					commonTestMsgList.add("Unable to share score card link test analysis page");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTextScored(), 10);
				if (!result) {
					commonTestMsgList.add("Unable to scored points test analysis page");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getMarkScoredText(), 10);
				if (!result) {
					commonTestMsgList.add("MarkScoredText is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTotalMarksText(), 10);
				if (!result) {
					commonTestMsgList.add("TotalMarksText is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTextRank(), 10);
				if (!result) {
					commonTestMsgList.add("Unable to rank in test analysis page");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getRankScoredText(), 10);
				if (!result) {
					commonTestMsgList.add("RankScoredText is not visible.");
					return result;
				}

				if (!isTestLive) {
					result = cfObj.commonVerifyValueTextBox(commonTestPageObj.getRankScoredText(), "--");
					if (!result) {
						commonTestMsgList.add("Empty rank is not visible.");
						return result;
					}
				}

				result = !cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getRankProgressBarBtn(), 3);
				if (!result) {
					commonTestMsgList.add("Rank refresh button should not be visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTotalRankText(), 10);
				if (!result) {
					commonTestMsgList.add("TotalRankText is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTextTimeSpent(), 10);
				if (!result) {
					commonTestMsgList.add("Unable to time spent in test analysis page");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getTimeSpentValue(), 10);
				if (!result) {
					commonTestMsgList.add("TimeSpentValue is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "android.widget.ImageButton", "class",
						10);
				if (!result) {
					commonTestMsgList.add("Unable to navigate up btn test analysis page");
					return result;
				}
				if (isTestLive) {
					result = !cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getReAttemptBtn(), 3);
					if (!result) {
						commonTestMsgList.add("ReAttempt button should not be visible.");
						return result;
					}
				} else {
					result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getReAttemptBtn(), 3);
					if (!result) {
						commonTestMsgList.add("ReAttempt button is not visible.");
						return result;
					}
				}
			}

		} catch (Exception e) {
			commonTestMsgList.add("verifyFMTMockTestAnalysisPage_Exception" + e.getMessage());
			result = false;
		}
		return result;

	}

	public boolean validateTestScreenFilterScreen(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "sections_tab", "id", 10);
			if (!result) {
				commonTestMsgList.add("Section tab is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "close_btn", "id", 10);
			if (!result) {
				commonTestMsgList.add("Close button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "question_count", "id", 10);
			if (!result) {
				commonTestMsgList.add("Question count header is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "ques_num", "id", 10);
			if (!result) {
				commonTestMsgList.add("Question number is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "info_icon", "id", 10);
			if (!result) {
				commonTestMsgList.add("Info icon is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "primary_button", "id", 10);
			if (!result) {
				commonTestMsgList.add("Submit test button is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "info_icon", "id"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "test_title", "id", 10);
			if (!result) {
				commonTestMsgList.add("Failed to click info icon.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "close_btn", "id"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "primary_button", "id", 10);
			if (!result) {
				commonTestMsgList.add("Failed to close info icon popup screen.");
				return result;
			}

		} catch (Exception e) {
			commonTestMsgList.add("validateTestScreenFilterScreen_Exception" + e.getMessage());
			result = false;
		}
		return result;

	}

	public boolean clickOnFilterBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, commonTestPageObj.getBtnFilter(), 10);
			if (!result) {
				commonTestMsgList.add("Filter button is not visible on Test screen.");
				return result;
			}
			cfObj.commonClick(commonTestPageObj.getBtnFilter());

			result = validateTestScreenFilterScreen(driver);
			if (!result) {
				commonTestMsgList.add("Failed to validate TestScreenFilterScreen.");
				return result;
			}

		} catch (Exception e) {
			commonTestMsgList.add("clickOnFilterBtn_Exception" + e.getMessage());
			result = false;
		}
		return result;

	}

	public boolean validateMockTestResultScreen(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "toolbar_title", "id", 30);
			if (!result) {
				commonTestMsgList.add("Result screen title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "toolbar_title_back_arrow", "id", 30);
			if (!result) {
				commonTestMsgList.add("Result screen back button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "solution_btn_solo", "id", 30)
					|| cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "solution_btn", "id", 30);
			if (!result) {

				commonTestMsgList.add("ViewSolution button is not visible.");
				return result;
			}

			result = validateOverallSummarySection(driver);
			if (!result) {
				commonTestMsgList.add("Failed to validate OverallSummarySection.");
				return result;
			}

			cfObj.scrollUtill(driver, 1, direction.DOWN);

			result = validateSectionalSummarySection(driver);
			if (!result) {
				commonTestMsgList.add("Failed to validate SectionalSummarySection.");
				return result;
			}

			if (cfObj.scrollByID("com.adda247.app:id/avgMarks", driver)) {

				result = validateComparisonChartSection(driver);
				if (!result) {
					commonTestMsgList.add("Failed to validate ComparisonChartSection.");
					return result;
				}
			} else {
				System.out.println("ComparisonChartSection is not visible.");
			}

			if (cfObj.scrollByID("com.adda247.app:id/top_rankers_tv", driver)) {

				result = validateQuestionDistributionSection(driver);
				if (!result) {
					commonTestMsgList.add("Failed to validate QuestionDistributionSection.");
					return result;
				}
			} else {
				System.out.println("QuestionDistributionSection is not visible.");
			}

			if (cfObj.scrollByID("com.adda247.app:id/top_rankers_tv", driver)) {

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/top_rankers_tv",
						"xpath", 5);
				if (!result) {

					cfObj.scrollIntoText(driver, "Leaderboard");
					cfObj.scrollDown(driver, 2);
                }

				result = validateLeaderBoardSection(driver);
				if (!result) {
					commonTestMsgList.add("Failed to validate LeaderBoardSection.");
					return result;
				}
			} else {
				System.out.println("LeaderBoardSection is not visible.");
            }
		} catch (Exception e) {
			commonTestMsgList.add("validateMockTestResultScreen_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean validateOverallSummarySection(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "overallSummaryTv", "id", 10);
			if (!result) {
				commonTestMsgList.add("OverallSummarySection title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "userScoreProgressImg", "id", 10);
			if (!result) {
				commonTestMsgList.add("User score progress image is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "userMarks", "id", 10);
			if (!result) {
				commonTestMsgList.add("User mark text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "totalMarks", "id", 10);
			if (!result) {
				commonTestMsgList.add("Total mark text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "yourScoreTv", "id", 10);
			if (!result) {
				commonTestMsgList.add("Your score title text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "userTimeProgressImg", "id", 10);
			if (!result) {
				commonTestMsgList.add("User time Progress image is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "userTime", "id", 10);
			if (!result) {
				commonTestMsgList.add("User Time text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "totalTime", "id", 10);
			if (!result) {
				commonTestMsgList.add("Total Time text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "yourTimeTv", "id", 10);
			if (!result) {
				commonTestMsgList.add("Your Time title text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "userRank", "id", 10);
			if (!result) {
				commonTestMsgList.add("User Rank text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "userPercentile", "id", 10);
			if (!result) {
				commonTestMsgList.add("UserPercentile text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "userAccuracy", "id", 10);
			if (!result) {
				commonTestMsgList.add("UserAccuracy text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "share_score_btn", "id", 10);
			if (!result) {
				commonTestMsgList.add("Share score btn is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "reattempt_btn", "id", 10);
			if (!result) {
				commonTestMsgList.add("Reattempt btn is not visible.");
				return result;
			}

		} catch (Exception e) {
			commonTestMsgList.add("validateOverallSummarySection_Exception" + e.getMessage());
			result = false;
		}
		return result;

	}

	public boolean validateSectionalSummarySection(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "sectionalSummaryTv", "id", 10)) {

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "tabs", "id", 10);
				if (!result) {
					commonTestMsgList.add("Section Tabs are not visible.");
					return result;
				}

				cfObj.scrollByID("com.adda247.app:id/rate_test_tv", driver);

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/userScoreProgressImg", "id", 10);
				if (!result) {
					commonTestMsgList.add("User score progress image is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "userMarks", "id", 10);
				if (!result) {
					commonTestMsgList.add("User mark text is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "totalMarks", "id", 10);
				if (!result) {
					commonTestMsgList.add("Total mark text is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "yourScoreTv", "id", 10);
				if (!result) {
					commonTestMsgList.add("Your score title text is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "userTime", "id", 10);
				if (!result) {
					commonTestMsgList.add("User Time text is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/yourTimeTv", "id", 10);
				if (!result) {
					commonTestMsgList.add("Time text is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "userRank", "id", 10);
				if (!result) {
					commonTestMsgList.add("User Rank text is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "userPercentile", "id", 10);
				if (!result) {
					commonTestMsgList.add("UserPercentile text is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "userAccuracy", "id", 10);
				if (!result) {
					commonTestMsgList.add("UserAccuracy text is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "rating_card", "id", 10);
				if (!result) {
					commonTestMsgList.add("Rating card is not visible.");
					return result;
				}
			} else {
				System.out.println("SectionalSummarySection title is not visible.");
            }
		} catch (Exception e) {
			commonTestMsgList.add("validateSectionalSummarySection_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean validateComparisonChartSection(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/comparisonChartTitle", "id", 10)) {

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/mpBarChart", "id", 10);
				if (!result) {
					commonTestMsgList.add("Bar chart not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/chartTypeChipGroupScrollContainer", "id", 10);
				if (!result) {
					commonTestMsgList.add("Tabs are not visible.");
					result = true;
				}
			} else {
				System.out.println("ComparisonChartTitle is not visible.");
			}
		} catch (Exception e) {
			commonTestMsgList.add("validateComparisonChartSection_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean validateQuestionDistributionSection(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "ques_distribution_title", "id", 10)) {

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "ques_distribution_progress", "id",
						10);
				if (!result) {
					commonTestMsgList.add("Question distribution progress bar is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@resource-id,'correct_count')]/*[contains(@resource-id,'countTextView')]",
						"xpath", 10);
				if (!result) {
					commonTestMsgList.add("Correct count is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@resource-id,'wrong_count')]/*[contains(@resource-id,'countTextView')]", "xpath",
						10);
				if (!result) {
					commonTestMsgList.add("Wrong Count is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@resource-id,'unattempted_count')]/*[contains(@resource-id,'countTextView')]",
						"xpath", 10);
				if (!result) {
					commonTestMsgList.add("UnAttempt count is not visible.");
					return result;
				}
			} else {

				commonTestMsgList.add("Question distribution title is not visible.");
			}

		} catch (Exception e) {
			commonTestMsgList.add("validateQuestionDistributionSection_Exception" + e.getMessage());
			result = false;
		}
		return result;

	}

	public boolean validateLeaderBoardSection(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "top_rankers_tv", "id", 10)) {

				cfObj.scrollDown(driver, 2);

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/topper_number",
						"id", 10);
				if (!result) {
					commonTestMsgList.add("Topper Number is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/topper_avatar",
						"id", 10);
				if (!result) {
					commonTestMsgList.add("Topper avatar is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/topper_name", "id",
						10);
				if (!result) {
					commonTestMsgList.add("Topper Name is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/topper_score",
						"id", 10);
				if (!result) {
					commonTestMsgList.add("Topper Score is not visible.");
					return result;
				}
			} else {
				commonTestMsgList.add("LeaderBoard title is not visible.");
			}

		} catch (Exception e) {
			commonTestMsgList.add("validateLeaderBoardSection_Exception" + e.getMessage());
			result = false;
		}
		return result;

	}

}
