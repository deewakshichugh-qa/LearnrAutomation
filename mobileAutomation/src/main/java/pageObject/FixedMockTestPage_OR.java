package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class FixedMockTestPage_OR {
	
	@AndroidFindBy(id = "btnTestState")
	private List<MobileElement> btnTestState;
	
	public List<MobileElement> getListBtnTestState() {
		return btnTestState;
	}
	
	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]")
	private MobileElement btnBack;
	
	public MobileElement getBtnBack() {
		return btnBack;
	}
	
	@AndroidFindBy(id = "tv_title")
	private List<MobileElement> titleTest;
	
	public List<MobileElement> getListTestTitle() {
		return titleTest;
	}
	
	@AndroidFindBy(id = "cv_main")
	private MobileElement listTest;
	

	public MobileElement getListTest() {
		return listTest;
	}

//--------------------------------ATTEMPT FREEMOCK TEST-----------------------------------------------
	@AndroidFindBy(xpath = "//android.view.View[@text='Test Guidelines']")
	private MobileElement titleTestGuidlines;
	
	public MobileElement getTitleTestGuidlines() {
		return titleTestGuidlines;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@text='Frequently Asked Questions']")
	private MobileElement titleFAQ;
	
	public MobileElement getTitleFAQ() {
		return titleFAQ;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@text='Get Cash Rewards!!']")
	private MobileElement titleGetCashReward;
	
	public MobileElement getCashReward() {
		return titleGetCashReward;
	}
	
	@AndroidFindBy(id = "btn_register")
	private MobileElement btnStartTest;
	
	public MobileElement getBtnStartTest() {
		return btnStartTest;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='You’ve already attempted the test.']")
	private MobileElement textTestTaken;
	
	public MobileElement getTextTestTaken() {
		return textTestTaken;
	}
	
//	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Result Awaited']")
//	private MobileElement textResultAwaited;
//	
//	public MobileElement getTextResultAwaited() {
//		return textResultAwaited;
//	}
	
//---------------------------------VIEW RESULT FREEMOCKTEST-------------------------------------------
	@AndroidFindBy(id = "android.widget.TextView")
	private MobileElement textResultAwaited;
	
	public MobileElement getTextResultAwaited() {
		return textResultAwaited;
	}
	
	@AndroidFindBy(id = "view_solution_container")
	private MobileElement btnSolution;
	
	public MobileElement getBtnSolution() {
		return btnSolution;
	}
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"SHARE SCORECARD\")")
	private MobileElement btnShareScorecard;
	
	public MobileElement getBtnShareScoreCard() {
		return btnShareScorecard;
	}
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Scored\")")
	private MobileElement textScored;
	
	public MobileElement getTitleScored() {
		return textScored;
	}
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Time Spent\")")
	private MobileElement textTimeSpent;
	
	public MobileElement getTitleTimeSpent() {
		return textTimeSpent;
	}
	
	@AndroidFindBy(id = "correct_count")
	private MobileElement iconCorrect;
	
	public MobileElement getIconCorrect() {
		return iconCorrect;
	}
	
	@AndroidFindBy(id = "incorrect_count")
	private MobileElement iconIncorrect;
	
	public MobileElement getIconIncorrect() {
		return iconIncorrect;
	}
	
	@AndroidFindBy(id = "unanswered_count")
	private MobileElement iconUnanswered;
	
	public MobileElement getIconUnanswered() {
		return iconUnanswered;
	}

//-------------------------------------RESULT CARD--------------------------------------------------
	@AndroidFindBy(id = "result_card_title")
	private MobileElement titleResultCard;
	
	public MobileElement getTitleResultCard() {
		return titleResultCard;
	}
	
	@AndroidFindBy(xpath = "//*[contains(@text,'SHARE WITH FRIENDS')]")
	private MobileElement btnShareWithFriends;
	
	public MobileElement getBtnShareWithFriends() {
		return btnShareWithFriends;
	}
	

	@AndroidFindBy(id = "toolbar_sub_text")
	private MobileElement name;
	
	public MobileElement getName() {
		return name;
	}
	
	@AndroidFindBy(id = "btn_show_att")
	private MobileElement marksScored;

	public MobileElement getMarksScored() {
		return marksScored;
	}
	
	@AndroidFindBy(xpath = "//*[contains(@text,'Rank')]")
	private MobileElement rank;
	
	public MobileElement getRank() {
		return rank;
	}

	@AndroidFindBy(id = "text_timer")
	private MobileElement timer;
	
	@AndroidFindBy(xpath = "//*[contains(@text,'Time Spent')]")
	private MobileElement timeSpent;
	
	public MobileElement getTimeSpent() {
		return timeSpent;
	}

//---------------------------------------SOLUTIONS PAGE IN RESULT-----------------------
	@AndroidFindBy(xpath = "//*[contains(@text,'SHOW CORRECT ANSWER')]")
	private MobileElement btnShowCorrectAnswer;
	
	public MobileElement getBtnShowCorrectAnswer() {
		return btnShowCorrectAnswer;
	}
	
	//@AndroidFindBy(id = "action_bookmark")
	@AndroidFindBy(id = "bookmark_btn")
	private MobileElement iconBookmark;
	
	public MobileElement getIconBookmark() {
		return iconBookmark;
	}
	
	@AndroidFindBy(id = "fbMessage")
	private MobileElement toastMessage;
	
	public MobileElement getToastMessage() {
		return toastMessage;
	}

	@AndroidFindBy(id = "action_share")
	private MobileElement iconShare;
	
	public MobileElement getIconShare() {
		return iconShare;
	}
	
	@AndroidFindBy(id = "android:id/resolver_list")
	private MobileElement shareScreen;
	
	
	public MobileElement getShareScreen() {
		return shareScreen;
	}

	//@AndroidFindBy(id = "action_report_content")
	@AndroidFindBy(id = "report_btn")
	private MobileElement iconReportContent;
	
	public MobileElement getIconReportContent() {
		return iconReportContent
				;
	}
	
	//@AndroidFindBy(id = "android:id/text1")
	@AndroidFindBy(id = "report_checkbox")
	private List<MobileElement> reportList;
	
	
	public List<MobileElement> getReportList() {
		return reportList;
	}
	
	//@AndroidFindBy(xpath = "//android.widget.Button[@text='NEXT']")
	@AndroidFindBy(id = "positive_button")
	private MobileElement nextBtn;

	public MobileElement getNextBtn() {
		return nextBtn;
	}
	
	//@AndroidFindBy(xpath = "//android.widget.Button[@text='CANCEL']")
	@AndroidFindBy(id = "negative_button")
	private MobileElement cancelBtn;

	public MobileElement getCancelBtn() {
		return cancelBtn;
	}

	//@AndroidFindBy(id = "alertTitle")
	@AndroidFindBy(id = "title")
	private MobileElement reportPageTitle;
	
	public MobileElement getReportPageTitle() {
		return reportPageTitle;
	}
	
	@AndroidFindBy(id = "com.adda247.app:id/dialogTitle_textView")
	private MobileElement testSummaryTitle;
	
	public MobileElement getTestSummaryTitle() {
		return testSummaryTitle;
	}
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='Continue']")
	private MobileElement continueBtn;

	public MobileElement getContinueBtn() {
		return continueBtn;
	}

	@AndroidFindBy(id = "com.adda247.app:id/test_summary_btn")
	private MobileElement testSummaryBtn;
	
	public MobileElement getTestSummaryBtn() {
		return testSummaryBtn;
	}
	
	
//---------------------------------------REGISTER FREEMOCKTEST----------------------------------------
	
//	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Get Cash Rewards!!']")
//	private MobileElement titleGetCashReward;
//	
//	public MobileElement getTitleGetCashReward() {
//		return titleGetCashReward;
//	}
	
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Test Guidelines']")
	private MobileElement titleTestGuidelines;
	
	public MobileElement getTitleTestGuidelines() {
		return titleTestGuidelines;
	}
	
//	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Test Guidelines']")
//	private MobileElement titleFAQ;
//	
//	public MobileElement getTitleFAQ() {
//		return titleFAQ;
//	}
	
	@AndroidFindBy(id = "btn_register")
	private MobileElement btnRegister;
	
	public MobileElement getBtnRegister() {
		return btnRegister;
	}
	
	@AndroidFindBy(id = "alertTitle")
	private MobileElement titleCongratulationPopUp;
	
	public MobileElement getTitleCongratulationPopUp() {
		return titleCongratulationPopUp;
	}
	
	@AndroidFindBy(id = "android:id/button1")
	private MobileElement btnContinuePopUp;
	
	public MobileElement getBtnContinuePopUp() {
		return btnContinuePopUp;
	}
	
	@AndroidFindBy(id = "questionControl_button")
	private MobileElement filterBtn;

	public MobileElement getFilterBtn() {
		return filterBtn;
	}
	
	@AndroidFindBy(id = "sections_tab")
	private List<MobileElement> sectionTab;

	public List<MobileElement> getSectionTab() {
		return sectionTab;
	}
	
	@AndroidFindBy(id = "question_count")
	private MobileElement questionCountText;

	public MobileElement getQuestionCountText() {
		return questionCountText;
	}
	
	@AndroidFindBy(id = "info_icon")
	private MobileElement filterInfoIcon;
	
	public MobileElement getFilterInfoIcon() {
		return filterInfoIcon;
	}
	
	@AndroidFindBy(id = "filterList")
	private MobileElement filterList;
	
	public MobileElement getFilterList() {
		return filterList;
	}
	
	@AndroidFindBy(id = "checkbox")
	private List<MobileElement> filterListCheckBox;
	
	public List<MobileElement> getFilterListCheckBox() {
		return filterListCheckBox;
	}
	
	@AndroidFindBy(id = "ques_num")
	private List<MobileElement> questionNumbers;
	
	public List<MobileElement> getQuestionNumbers() {
		return questionNumbers;
	}
	
	@AndroidFindBy(id = "fast_chip")
	private MobileElement fastChipSpeedIndicator;

	public MobileElement getFastChipSpeedIndicator() {
		return fastChipSpeedIndicator;
	}
	
	@AndroidFindBy(id = "on_time_chip")
	private MobileElement onTimeChipSpeedIndicator;

	public MobileElement getOnTimeChipSpeedIndicator() {
		return onTimeChipSpeedIndicator;
	}
	
	@AndroidFindBy(id = "slow_chip")
	private MobileElement slowChipSpeedIndicator;

	public MobileElement getSlowChipSpeedIndicator() {
		return slowChipSpeedIndicator;
	}
	
	@AndroidFindBy(id = "correct_tv")
	private MobileElement correctTextValue;

	public MobileElement getCorrectTextValue() {
		return correctTextValue;
	}
	
	@AndroidFindBy(id = "incorrect_tv")
	private MobileElement incorrectTextValue;
	
	public MobileElement getIncorrectTextValue() {
		return incorrectTextValue;
	}

	@AndroidFindBy(id = "unattempted_tv")
	private MobileElement unAttemptedTextValue;

	public MobileElement getUnAttemptedTextValue() {
		return unAttemptedTextValue;
	}
	
	@AndroidFindBy(id = "notEvaluated_tv")
	private MobileElement notEvaluatedTextValue;

	public MobileElement getNotEvaluatedTextValue() {
		return notEvaluatedTextValue;
	}
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='Previous']")
	private MobileElement previousBtn;

	public MobileElement getPreviousBtn() {
		return previousBtn;
	}
	
    @AndroidFindBy(xpath = "//android.widget.Button[@text='Next']")
    private MobileElement nextQuestionBtn;

	public MobileElement getNextQuestionBtn() {
		return nextQuestionBtn;
	}
    
    @AndroidFindBy(id = "close_btn")
    private MobileElement filterScreenCloseBtn;

	public MobileElement getFilterScreenCloseBtn() {
		return filterScreenCloseBtn;
	}
    
    @AndroidFindBy(id = "reattempt_switch")
    private MobileElement reAttemptSwitch;

	public MobileElement getReAttemptSwitch() {
		return reAttemptSwitch;
	}
    
    @AndroidFindBy(id = "question_num_container")
    private MobileElement questionNumber;

	public MobileElement getQuestionNumber() {
		return questionNumber;
	}

	@AndroidFindBy(id = "com.adda247.app:id/doubtDescription")
	private MobileElement reportText;

	public MobileElement getReportText() {
		return reportText;
	}
	
	
	
}
