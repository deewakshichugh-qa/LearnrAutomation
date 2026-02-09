package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CommonTestPage_OR {

	@AndroidFindBy(id = "text")
	private MobileElement bannaerChangeLanguageInstruction;

	public MobileElement getBannaerChangeLanguageInstruction() {
		return bannaerChangeLanguageInstruction;
	}

	@AndroidFindAll({ @AndroidBy(xpath = "//android.widget.Button[@text='Start Test']"),
			@AndroidBy(id = "start_test") })
	private MobileElement btnStartTest;

	public MobileElement getBtnStartTest() {
		return btnStartTest;
	}

	@AndroidFindAll({ @AndroidBy(id = "languageChange_Btn"), @AndroidBy(id = "quiz_language") })
	private MobileElement textTestLanguage;

	public MobileElement getTextTestLanguage() {
		return textTestLanguage;
	}

	@AndroidFindAll({ @AndroidBy(id = "radio_btn_title"), @AndroidBy(id = "text") })
	private List<MobileElement> languageList;

	public List<MobileElement> getLanguageList() {
		return languageList;
	}

	@AndroidFindBy(id = "webView_description")
	private MobileElement textDescription;

	public MobileElement getTextDescription() {
		return textDescription;
	}

	@AndroidFindAll({ @AndroidBy(id = "toolbar_title_back_arrow"), @AndroidBy(id = "ic_close") })
	private MobileElement iconClose;

	public MobileElement getIconClose() {
		return iconClose;
	}

	@AndroidFindAll({ @AndroidBy(id = "test_title"), @AndroidBy(id = "ti_title") })
	private MobileElement titleTest;

	public MobileElement getTitleTest() {
		return titleTest;
	}

	@AndroidFindBy(id = "txt_gotit")
	private MobileElement linkGotItSwipeInfoLayer;

	public MobileElement GetLinkGotItSwipeInfoLayer() {
		return linkGotItSwipeInfoLayer;
	}

	@AndroidFindAll({ @AndroidBy(accessibility = "Control Question"), @AndroidBy(id = "drawerOpener") })
	private MobileElement btnFilter;

	public MobileElement getBtnFilter() {
		return btnFilter;
	}

	@AndroidFindBy(id = "tv_review")
	private MobileElement btnReview;

	public MobileElement getBtnReview() {
		return btnReview;
	}

	@AndroidFindBy(id = "view_time")
	private MobileElement timer;

	public MobileElement getTimer() {
		return timer;
	}

	@AndroidFindBy(className = "android.widget.Toast")
	private MobileElement cantGoBackErrorMsg;

	public MobileElement getCantGoBackErrorMsg() {
		return cantGoBackErrorMsg;
	}

	@AndroidFindBy(id = "skip_title")
	private MobileElement skipSectionTitle;

	public MobileElement getSkipSectionTitle() {
		return skipSectionTitle;
	}

	@AndroidFindBy(xpath = "//*[@text='SKIP']")
	private MobileElement sectionSkipBtn;

	public MobileElement getSectionSkipBtn() {
		return sectionSkipBtn;
	}

	@AndroidFindBy(xpath = "//*[@text='CANCEL']")
	private MobileElement cancelBtn;

	public MobileElement getCancelBtn() {
		return cancelBtn;
	}

	@AndroidFindAll({ @AndroidBy(id = "primary_button"), @AndroidBy(id = "submit_test") })
	private MobileElement btnSubmitTest;

	public MobileElement getBtnSubmitTest() {
		return btnSubmitTest;
	}

	@AndroidFindAll({ @AndroidBy(id = "save_Btn"), @AndroidBy(id = "savenext") })
	private MobileElement btnSaveAndNext;

	public MobileElement getBtnSaveAndNext() {
		return btnSaveAndNext;
	}

	@AndroidFindBy(id = "com.adda247.app:id/option_index")
	private List<MobileElement> listAnswers;

	public List<MobileElement> getListAnswers() {
		return listAnswers;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@text='SUBMIT']")
	private MobileElement btnSubmitFinishTestPopUp;

	public MobileElement getBtnSubmitFinishTestPopUp() {
		return btnSubmitFinishTestPopUp;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@text='RESUME']")
	private MobileElement btnResumetFinishTestPopUp;

	public MobileElement getBtnResumetFinishTestPopUp() {
		return btnResumetFinishTestPopUp;
	}

	@AndroidFindBy(id = "sub_title")
	private MobileElement questionCount;

	public MobileElement getQuestionCount() {
		return questionCount;
	}

	@AndroidFindBy(id = "com.adda247.app:id/ques_num")
	private List<MobileElement> questionCountList;

	public List<MobileElement> getQuestionCountList() {
		return questionCountList;
	}

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.TextView")
	private List<MobileElement> sectionList;

	public List<MobileElement> getSectionList() {
		return sectionList;
	}

	@AndroidFindBy(id = "q_pos")
	private MobileElement textPositveMarking;

	public MobileElement getTextPositiveMarking() {
		return textPositveMarking;
	}

	@AndroidFindBy(id = "q_neg")
	private MobileElement textNegativeMarking;

	public MobileElement getTextNegativeMarking() {
		return textNegativeMarking;
	}

	@AndroidFindAll({ @AndroidBy(id = "question_num_container"), @AndroidBy(id = "q_number") })
	private MobileElement questionNumber;

	public MobileElement getQuestionNumber() {
		return questionNumber;
	}

	@AndroidFindBy(id = "tv_multiple_view")
	private MobileElement multipleOptionTitle;

	public MobileElement getMultipleOptionTitle() {
		return multipleOptionTitle;
	}

//	@AndroidFindAll({
//		@AndroidBy(id = "user_input_checkbox"),
//		@AndroidBy(id = "ch_ans")
//	})
	@AndroidFindBy(id = "user_input_checkbox")
	private List<MobileElement> multipleOptionList;

	public List<MobileElement> getMultipleOptionList() {
		return multipleOptionList;
	}

	@AndroidFindBy(id = "rl_main")
	private List<MobileElement> optionList;

	public List<MobileElement> getOptionList() {
		return optionList;
	}

	@AndroidFindAll({ @AndroidBy(id = "inputText"),
			@AndroidBy(xpath = "//*[contains(@resource-id,'layout_df')]/descendant::android.widget.EditText") })
	private MobileElement answerTextBox;

	public MobileElement getAnswerTextBox() {
		return answerTextBox;
	}

	@AndroidFindAll({ @AndroidBy(accessibility = "Launch Scientific Calculator"), @AndroidBy(id = "fab_calculator") })
	private MobileElement calculatorBtn;

	public MobileElement getCalculatorBtn() {
		return calculatorBtn;
	}

	@AndroidFindBy(id = "backView")
	private MobileElement hindiLanguageBtn;

	public MobileElement getHindiLanguageBtn() {
		return hindiLanguageBtn;
	}

	@AndroidFindBy(id = "frontView")
	private MobileElement englishLanguageBtn;

	public MobileElement getEnglishLanguageBtn() {
		return englishLanguageBtn;
	}

	@AndroidFindBy(accessibility = "Content Language")
	// @AndroidFindBy(id = "action_language_change")
	private MobileElement languageBtn;

	public MobileElement getLanguageBtn() {
		return languageBtn;
	}

	@AndroidFindBy(accessibility = "Change Language")
	private MobileElement solutionScreenLanguageBtn;

	public MobileElement getSolutionScreenLanguageBtn() {
		return solutionScreenLanguageBtn;
	}

	@AndroidFindBy(xpath = "//*[@text='Scientific Calculator']")
	private MobileElement calculatorScreen;

	public MobileElement getCalculatorScreen() {
		return calculatorScreen;
	}

	@AndroidFindBy(accessibility = "close")
	private MobileElement calculatorScreenCloseBtn;

	public MobileElement getCalculatorScreenCloseBtn() {
		return calculatorScreenCloseBtn;
	}

	@AndroidFindBy(xpath = "//*[@text='YES, PAUSE']")
	private MobileElement yesPauseBtn;

	public MobileElement getYesPauseBtn() {
		return yesPauseBtn;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Tap here to quickly switch between languages for this question.']")
	private MobileElement bannerSwitchLanguage;

	public MobileElement getBannerSwitchLangugae() {
		return bannerSwitchLanguage;
	}

	@AndroidFindAll({ @AndroidBy(id = "cutoffChip"), @AndroidBy(id = "cutoff_marks_instruction") })
	private MobileElement cutOffMarksInstruction;

	public MobileElement getCutOffMarksInstruction() {
		return cutOffMarksInstruction;
	}

//----------------------------Test Analysis page daily quizzes------------------------------

	@AndroidFindBy(id = "congratulations_tv")
	private MobileElement textCoinPopupCongratulation;

	public MobileElement getTextPopupCongratulation() {
		return textCoinPopupCongratulation;
	}

	@AndroidFindBy(id = "close")
	private MobileElement btnCloseCoinPopup;

	public MobileElement getBtnCloseCoinPopup() {
		return btnCloseCoinPopup;
	}

	@AndroidFindAll({ @AndroidBy(id = "solution_btn_solo"), @AndroidBy(id = "solution_btn"), @AndroidBy(id = "com.adda247.app:id/view_solution") })
	private MobileElement btnSolution;

	public MobileElement getBtnSolution() {
		return btnSolution;
	}

	@AndroidFindAll({ @AndroidBy(id = "share_score_btn"),
			@AndroidBy(xpath = "//android.widget.TextView[@text='SHARE SCORECARD']") })
	private MobileElement linkShareScoreCard;

	public MobileElement getLinkShareScoreCard() {
		return linkShareScoreCard;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Top Rankers']")
	private MobileElement textTopRankers;

	public MobileElement getTextTopRankers() {
		return textTopRankers;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Rate this test']")
	private MobileElement textRateThisTest;

	public MobileElement getTextRateThisTest() {
		return textRateThisTest;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Scored']")
	private MobileElement textScored;

	public MobileElement getTextScored() {
		return textScored;
	}

	@AndroidFindAll({ @AndroidBy(id = "userMarks"), @AndroidBy(id = "marks_scored") })
	private MobileElement markScoredText;

	public MobileElement getMarkScoredText() {
		return markScoredText;
	}

	@AndroidFindBy(id = "total_marks")
	private MobileElement totalMarksText;

	public MobileElement getTotalMarksText() {
		return totalMarksText;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Rank']")
	private MobileElement textRank;

	public MobileElement getTextRank() {
		return textRank;
	}

	@AndroidFindBy(id = "rank_scored")
	private MobileElement rankScoredText;

	public MobileElement getRankScoredText() {
		return rankScoredText;
	}

	@AndroidFindBy(id = "total_rank")
	private MobileElement totalRankText;

	public MobileElement getTotalRankText() {
		return totalRankText;
	}

	@AndroidFindBy(id = "rank_progress_bar")
	private MobileElement rankProgressBarBtn;

	public MobileElement getRankProgressBarBtn() {
		return rankProgressBarBtn;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Time Spent']")
	private MobileElement textTimeSpent;

	public MobileElement getTextTimeSpent() {
		return textTimeSpent;
	}

	@AndroidFindBy(id = "time_spent")
	private MobileElement timeSpentValue;

	public MobileElement getTimeSpentValue() {
		return timeSpentValue;
	}

	@AndroidFindBy(id = "total_time")
	private MobileElement totalTimeText;

	public MobileElement getTotalTimeText() {
		return totalTimeText;
	}

	@AndroidFindAll({ @AndroidBy(id = "toolbar_title_back_arrow"),
			@AndroidBy(xpath = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]") })
	private MobileElement btnNavigateUp;

	public MobileElement getBtnNavigateUp() {
		return btnNavigateUp;
	}

	@AndroidFindBy(xpath = "//*[@text='POST NOW']")
	private MobileElement postNowBtn;

	public MobileElement getPostNowBtn() {
		return postNowBtn;
	}

	@AndroidFindBy(id = "opt_ques_txt")
	private MobileElement optionQuesTEXT;

	public MobileElement getOptionQuesTEXT() {
		return optionQuesTEXT;
	}

	@AndroidFindBy(id = "com.adda247.app:id/sectionTitle_textView")
	private MobileElement examSectionDropDown;

	public MobileElement getExamSectionDropDown() {
		return examSectionDropDown;
	}

	@AndroidFindBy(xpath = "//*[@text='Section-time Ended']")
	private MobileElement sectionTimeEndTitle;

	public MobileElement getSectionTimeEndTitle() {
		return sectionTimeEndTitle;
	}

	@AndroidFindBy(xpath = "//*[@text='Pause Test']")
	private MobileElement pauseTestBtn;

	public MobileElement getPauseTestBtn() {
		return pauseTestBtn;
	}

	@AndroidFindBy(xpath = "//*[@text='NEXT SECTION']")
	private MobileElement nextSectionBtn;

	public MobileElement getNextSectionBtn() {
		return nextSectionBtn;
	}

	@AndroidFindBy(xpath = "//*[@text='Select Section']")
	private MobileElement examSectionDropDownTitle;

	public MobileElement getExamSectionDropDownTitle() {
		return examSectionDropDownTitle;
	}

	@AndroidFindBy(id = "com.adda247.app:id/close_btn")
	private MobileElement examSectionDropDownCloseBtn;

	public MobileElement getExamSectionDropDownCloseBtn() {
		return examSectionDropDownCloseBtn;
	}

	@AndroidFindBy(id = "com.adda247.app:id/radio_btn_title")
	private List<MobileElement> examSectionList;

	public List<MobileElement> getExamSectionList() {
		return examSectionList;
	}

	@AndroidFindBy(id = "correct_count")
	private MobileElement correctCount;

	public MobileElement getCorrectCount() {
		return correctCount;
	}

	@AndroidFindBy(id = "incorrect_count")
	private MobileElement inCorrectCount;

	public MobileElement getInCorrectCount() {
		return inCorrectCount;
	}

	@AndroidFindBy(id = "unanswered_count")
	private MobileElement unAnsweredCount;

	public MobileElement getUnAnsweredCount() {
		return unAnsweredCount;
	}

//	@AndroidFindBy(id = "tick_icon")
//	private MobileElement tickIcon;
//
//	public MobileElement getTickIcon() {
//		return tickIcon;
//	}

	/*------------------------Result awaited screen---------------*/

	@AndroidFindAll({ @AndroidBy(id = "toolbar_title"), @AndroidBy(id = "toolbar_sub_text") })
	private MobileElement resultAwaitedPageTitle;

	public MobileElement getResultAwaitedPageTitle() {
		return resultAwaitedPageTitle;
	}

	@AndroidFindAll({ @AndroidBy(id = "toolbar_title_back_arrow"),
			@AndroidBy(className = "android.widget.ImageButton") })
	private MobileElement resultAwaitedBackBtn;

	public MobileElement getResultAwaitedBackBtn() {
		return resultAwaitedBackBtn;
	}

	@AndroidFindAll({ @AndroidBy(id = "appCompatImageView"), @AndroidBy(id = "img_main") })
	private MobileElement resultAwaitedImg;

	public MobileElement getResultAwaitedImg() {
		return resultAwaitedImg;
	}

	@AndroidFindAll({ @AndroidBy(id = "appCompatTextView"), @AndroidBy(id = "text_res") })
	private MobileElement resultAwaitedText;

	public MobileElement getResultAwaitedText() {
		return resultAwaitedText;
	}

	@AndroidFindAll({ @AndroidBy(id = "resultTime"), @AndroidBy(id = "text_timer") })
	private MobileElement resultAwaitedTimer;

	public MobileElement getResultAwaitedTimer() {
		return resultAwaitedTimer;
	}

	/*--------------------------------Rating PopUp-----------------------------------*/

	@AndroidFindBy(xpath = "//*[@text='Rating']")
	private MobileElement ratingPopUpTitle;

	public MobileElement getRatingPopUpTitle() {
		return ratingPopUpTitle;
	}

	@AndroidFindBy(id = "close_icon")
	private MobileElement ratingPopUpCloseBtn;

	public MobileElement getRatingPopUpCloseBtn() {
		return ratingPopUpCloseBtn;
	}

	@AndroidFindBy(id = "test_feedback_title")
	private MobileElement testFeedBackTitle;

	public MobileElement getTestFeedBackTitle() {
		return testFeedBackTitle;
	}

	@AndroidFindBy(id = "ratingBarFeedback")
	private MobileElement ratingFeedBackBar;

	public MobileElement getRatingFeedBackBar() {
		return ratingFeedBackBar;
	}

	@AndroidFindBy(id = "submit")
	private MobileElement feedBackSubmitBtn;

	public MobileElement getFeedBackSubmitBtn() {
		return feedBackSubmitBtn;
	}

	@AndroidFindBy(id = "fbMessage")
	private MobileElement toasteMessage;

	public MobileElement getToasteMessage() {
		return toasteMessage;
	}

	@AndroidFindAll({ @AndroidBy(id = "reattempt_btn"), @AndroidBy(id = "reattempt") })
	private MobileElement reAttemptBtn;

	public MobileElement getReAttemptBtn() {
		return reAttemptBtn;
	}

	@AndroidFindAll({ @AndroidBy(id = "dialogTitle_textView"), @AndroidBy(id = "alertTitle") })
	private MobileElement reAttemptPopUpTitle;

	public MobileElement getReAttemptPopUpTitle() {
		return reAttemptPopUpTitle;
	}

	@AndroidFindAll({ @AndroidBy(id = "positive_button"),
			@AndroidBy(xpath = "//android.widget.Button[@text='REATTEMPT']") })
	private MobileElement reAttemptPopUpBtn;

	public MobileElement getReAttemptPopUpBtn() {
		return reAttemptPopUpBtn;
	}

	/*--------------------------ShareScoreCard PopUp Screen--------------------------*/

	@AndroidFindBy(id = "result_card_title")
	private MobileElement scoreCardPopUpTitle;

	public MobileElement getScoreCardPopUpTitle() {
		return scoreCardPopUpTitle;
	}

	@AndroidFindBy(id = "share")
	private MobileElement shareWithFriendBtn;

	public MobileElement getShareWithFriendBtn() {
		return shareWithFriendBtn;
	}

	@AndroidFindBy(id = "android:id/resolver_list")
	private MobileElement shareScreen;

	public MobileElement getShareScreen() {
		return shareScreen;
	}

	/*---------------------------Solution Screen------------------------*/

	@AndroidFindBy(id = "com.adda247.app:id/time_spent")
	private MobileElement timeSpentText;

	public MobileElement getTimeSpentText() {
		return timeSpentText;
	}

	@AndroidFindBy(id = "level")
	private MobileElement questionLevel;

	public MobileElement getQuestionLevel() {
		return questionLevel;
	}

	@AndroidFindBy(id = "btn_show_correct_ans")
	private MobileElement showCorrectAnswerBtn;

	public MobileElement getShowCorrectAnswerBtn() {
		return showCorrectAnswerBtn;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Explanation:']")
	private MobileElement explanationTitle;

	public MobileElement getExplanationTitle() {
		return explanationTitle;
	}

	@AndroidFindBy(id = "rl_main")
	private MobileElement questionCell;

	public MobileElement getQuestionCell() {
		return questionCell;
	}

	@AndroidFindBy(xpath = "//android.widget.GridView[contains(@resource-id,'grid')]/android.widget.TextView")
	private List<MobileElement> questionTabList;

	public List<MobileElement> getQuestionTabList() {
		return questionTabList;
	}

	@AndroidFindBy(id = "cutOffLayout")
	private MobileElement cutOffLayOut;

	public MobileElement getLinkGotItSwipeInfoLayer() {
		return linkGotItSwipeInfoLayer;
	}

	public MobileElement getTextPositveMarking() {
		return textPositveMarking;
	}

	public MobileElement getBannerSwitchLanguage() {
		return bannerSwitchLanguage;
	}

	public MobileElement getTextCoinPopupCongratulation() {
		return textCoinPopupCongratulation;
	}

	public MobileElement getCutOffLayOut() {
		return cutOffLayOut;
	}

	@AndroidFindBy(xpath = "//*[@text='Cutoff']")
	private MobileElement cutOffTitle;

	public MobileElement getCutOffTitle() {
		return cutOffTitle;
	}

	@AndroidFindAll({ @AndroidBy(id = "cutoffMarks"), @AndroidBy(id = "cutoff_marks") })
	private MobileElement cutOffMark;

	public MobileElement getCutOffMark() {
		return cutOffMark;
	}

	@AndroidFindAll({ @AndroidBy(id = "totalMarks"), @AndroidBy(id = "total_marks2") })
	private MobileElement totalMarks;

	public MobileElement getTotalMarks() {
		return totalMarks;
	}

	@AndroidFindBy(id = "toolbar_sub_text")
	private MobileElement resultAnalysisDropDown;

	public MobileElement getResultAnalysisDropDown() {
		return resultAnalysisDropDown;
	}

	@AndroidFindBy(id = "drop_down_title")
	private MobileElement resultAnalysisDropDownTitle;

	public MobileElement getResultAnalysisDropDownTitle() {
		return resultAnalysisDropDownTitle;
	}

	@AndroidFindBy(id = "back")
	private MobileElement resultAnalysisDropDownCloseBtn;

	public MobileElement getResultAnalysisDropDownCloseBtn() {
		return resultAnalysisDropDownCloseBtn;
	}

	@AndroidFindBy(id = "selected_indicator")
	private MobileElement resultAnalysisOptionSelectIcon;

	public MobileElement getResultAnalysisOptionSelectIcon() {
		return resultAnalysisOptionSelectIcon;
	}

	@AndroidFindBy(id = "title")
	private List<MobileElement> resultAnalysisOptionList;

	public List<MobileElement> getResultAnalysisOptionList() {
		return resultAnalysisOptionList;
	}

	@AndroidFindBy(id = "multiLevelProgressBar")
	private MobileElement multiLevelProgressBarIcon;

	public MobileElement getMultiLevelProgressBarIcon() {
		return multiLevelProgressBarIcon;
	}

	@AndroidFindBy(id = "progressTextPercentile")
	private MobileElement progressTextPercentile;

	public MobileElement getProgressTextPercentile() {
		return progressTextPercentile;
	}

	@AndroidFindBy(id = "progressTextAccuracy")
	private MobileElement accuracyPercentageText;

	public MobileElement getAccuracyPercentageText() {
		return accuracyPercentageText;
	}

	@AndroidFindBy(id = "progressTextAttempted")
	private MobileElement attemptedPercentageText;

	public MobileElement getAttemptedPercentageText() {
		return attemptedPercentageText;
	}

	@AndroidFindBy(xpath = "//*[@text='Top Rankers']")
	private MobileElement topRankerHeader;

	public MobileElement getTopRankerHeader() {
		return topRankerHeader;
	}

	@AndroidFindBy(id = "profilePicture")
	private List<MobileElement> rankerProfilePic;

	public List<MobileElement> getRankerProfilePic() {
		return rankerProfilePic;
	}

	@AndroidFindBy(id = "number")
	private List<MobileElement> rankText;

	public List<MobileElement> getRankText() {
		return rankText;
	}

	@AndroidFindBy(id = "name")
	private List<MobileElement> rankerName;

	public List<MobileElement> getRankerName() {
		return rankerName;
	}

	@AndroidFindBy(id = "marksScored")
	private List<MobileElement> rankerMarkScored;

	public List<MobileElement> getRankerMarkScored() {
		return rankerMarkScored;
	}

	@AndroidFindBy(accessibility = "Overall")
	private MobileElement overallTab;

	public MobileElement getOverallTab() {
		return overallTab;
	}

	@AndroidFindBy(accessibility = "Test")
	private MobileElement testTab;

	public MobileElement getTestTab() {
		return testTab;
	}

	@AndroidFindBy(id = "sectionName")
	private MobileElement sectionName;

	public MobileElement getSectionName() {
		return sectionName;
	}

	@AndroidFindBy(id = "score")
	private MobileElement scoreText;

	public MobileElement getScoreText() {
		return scoreText;
	}

	@AndroidFindBy(id = "scoreTotal")
	private MobileElement totalScoreText;

	public MobileElement getTotalScoreText() {
		return totalScoreText;
	}

	@AndroidFindBy(id = "rank")
	private MobileElement rankNumber;

	public MobileElement getRankNumber() {
		return rankNumber;
	}

	@AndroidFindBy(id = "rankTotal")
	private MobileElement totalRankCount;

	public MobileElement getTotalRankCount() {
		return totalRankCount;
	}

	@AndroidFindBy(id = "accuracy")
	private MobileElement accuracyText;

	public MobileElement getAccuracyText() {
		return accuracyText;
	}

	@AndroidFindBy(id = "timeSpent")
	private MobileElement timeSpent;

	public MobileElement getTimeSpent() {
		return timeSpent;
	}

	@AndroidFindBy(id = "timeTotal")
	private MobileElement totalTime;

	public MobileElement getTotalTime() {
		return totalTime;
	}

	@AndroidFindBy(id = "correct")
	private MobileElement correctProgressBar;

	public MobileElement getCorrectProgressBar() {
		return correctProgressBar;
	}

	@AndroidFindBy(id = "wrong")
	private MobileElement wrongProgressBar;

	public MobileElement getWrongProgressBar() {
		return wrongProgressBar;
	}

	@AndroidFindBy(id = "unanswered")
	private MobileElement unAnsweredProgressBar;

	public MobileElement getUnAnsweredProgressBar() {
		return unAnsweredProgressBar;
	}

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc='Overall']/following-sibling::android.widget.LinearLayout/android.widget.TextView")
	private MobileElement subjectTab;

	public MobileElement getSubjectTab() {
		return subjectTab;
	}

	@AndroidFindBy(xpath = "//*[@text='Score']")
	private MobileElement scoreHeader;

	public MobileElement getScoreHeader() {
		return scoreHeader;
	}

	@AndroidFindBy(xpath = "//*[@text='Score']/following-sibling::android.widget.TextView")
	private MobileElement scoreSectionSubTitle;

	public MobileElement getScoreSectionSubTitle() {
		return scoreSectionSubTitle;
	}

	@AndroidFindBy(xpath = "//*[@text='Score']/following-sibling::android.widget.RelativeLayout/child::*[contains(@resource-id,'averageTipLabel')]")
	private MobileElement scoreSectionAverageCount;

	public MobileElement getScoreSectionAverageCount() {
		return scoreSectionAverageCount;
	}

	@AndroidFindBy(xpath = "//*[@text='Score']/following-sibling::android.widget.RelativeLayout/child::*[contains(@resource-id,'bar1TipLabel')]")
	private MobileElement scoreSectionBar1TipLabelCount;

	public MobileElement getScoreSectionBar1TipLabelCount() {
		return scoreSectionBar1TipLabelCount;
	}

	@AndroidFindBy(xpath = "//*[@text='Score']/following-sibling::android.widget.RelativeLayout/child::*[contains(@resource-id,'bar2TipLabel')]")
	private MobileElement scoreSectionBar2TipLabelCount;

	public MobileElement getScoreSectionBar2TipLabelCount() {
		return scoreSectionBar2TipLabelCount;
	}

	@AndroidFindBy(xpath = "//*[@text='Score']/following-sibling::android.widget.RelativeLayout/child::*[@text='Average']")
	private MobileElement scoreSectionAverageText;

	public MobileElement getScoreSectionAverageText() {
		return scoreSectionAverageText;
	}

	@AndroidFindBy(xpath = "//*[@text='Score']/following-sibling::android.widget.RelativeLayout/child::android.widget.ImageView[contains(@resource-id,'bar1')]")
	private MobileElement scoreSectionBar1Img;

	public MobileElement getScoreSectionBar1Img() {
		return scoreSectionBar1Img;
	}

	@AndroidFindBy(xpath = "//*[@text='Score']/following-sibling::android.widget.RelativeLayout/child::android.widget.ImageView[contains(@resource-id,'bar2')]")
	private MobileElement scoreSectionBar2Img;

	public MobileElement getScoreSectionBar2Img() {
		return scoreSectionBar2Img;
	}

	@AndroidFindBy(xpath = "//*[@text='Score']/following-sibling::android.widget.LinearLayout/child::*[@text='Topper']")
	private MobileElement scoreSectionTopperText;

	public MobileElement getScoreSectionTopperText() {
		return scoreSectionTopperText;
	}

	@AndroidFindBy(xpath = "//*[@text='Score']/following-sibling::android.widget.LinearLayout/child::*[@text='You']")
	private MobileElement scoreSectionOwnText;

	public MobileElement getScoreSectionOwnText() {
		return scoreSectionOwnText;
	}

	@AndroidFindBy(xpath = "//*[@text='Accuracy']")
	private MobileElement accuracyHeader;

	public MobileElement getAccuracyHeader() {
		return accuracyHeader;
	}

	@AndroidFindBy(xpath = "//*[@text='Accuracy']/following-sibling::android.widget.TextView")
	private MobileElement accuracySectionSubTitle;

	public MobileElement getAccuracySectionSubTitle() {
		return accuracySectionSubTitle;
	}

	@AndroidFindBy(xpath = "//*[@text='Accuracy']/following-sibling::android.widget.RelativeLayout/child::*[contains(@resource-id,'averageTipLabel')]")
	private MobileElement accuracySectionAverageCount;

	public MobileElement getAccuracySectionAverageCount() {
		return accuracySectionAverageCount;
	}

	@AndroidFindBy(xpath = "//*[@text='Accuracy']/following-sibling::android.widget.RelativeLayout/child::*[contains(@resource-id,'bar1TipLabel')]")
	private MobileElement accuracySectionBar1TipLabelCount;

	public MobileElement getAccuracySectionBar1TipLabelCount() {
		return accuracySectionBar1TipLabelCount;
	}

	@AndroidFindBy(xpath = "//*[@text='Accuracy']/following-sibling::android.widget.RelativeLayout/child::*[contains(@resource-id,'bar2TipLabel')]")
	private MobileElement accuracySectionBar2TipLabelCount;

	public MobileElement getAccuracySectionBar2TipLabelCount() {
		return accuracySectionBar2TipLabelCount;
	}

	@AndroidFindBy(xpath = "//*[@text='Accuracy']/following-sibling::android.widget.RelativeLayout/child::*[@text='Average']")
	private MobileElement accuracySectionAverageText;

	public MobileElement getAccuracySectionAverageText() {
		return accuracySectionAverageText;
	}

	@AndroidFindBy(xpath = "//*[@text='Accuracy']/following-sibling::android.widget.RelativeLayout/child::android.widget.ImageView[contains(@resource-id,'bar1')]")
	private MobileElement accuracySectionBar1Img;

	public MobileElement getAccuracySectionBar1Img() {
		return accuracySectionBar1Img;
	}

	@AndroidFindBy(xpath = "//*[@text='Accuracy']/following-sibling::android.widget.RelativeLayout/child::android.widget.ImageView[contains(@resource-id,'bar2')]")
	private MobileElement accuracySectionBar2Img;

	public MobileElement getAccuracySectionBar2Img() {
		return accuracySectionBar2Img;
	}

	@AndroidFindBy(xpath = "//*[@text='Accuracy']/following-sibling::android.widget.LinearLayout/child::*[@text='Topper']")
	private MobileElement accuracySectionTopperText;

	public MobileElement getAccuracySectionTopperText() {
		return accuracySectionTopperText;
	}

	@AndroidFindBy(xpath = "//*[@text='Accuracy']/following-sibling::android.widget.LinearLayout/child::*[@text='You']")
	private MobileElement accuracySectionOwnText;

	public MobileElement getAccuracySectionOwnText() {
		return accuracySectionOwnText;
	}

	@AndroidFindBy(xpath = "//*[@text='Correct']")
	private MobileElement correctHeader;

	public MobileElement getCorrectHeader() {
		return correctHeader;
	}

	@AndroidFindBy(xpath = "//*[@text='Correct']/following-sibling::android.widget.TextView")
	private MobileElement correctSectionSubTitle;

	public MobileElement getCorrectSectionSubTitle() {
		return correctSectionSubTitle;
	}

	@AndroidFindBy(xpath = "//*[@text='Correct']/following-sibling::android.widget.RelativeLayout/child::*[contains(@resource-id,'averageTipLabel')]")
	private MobileElement correctSectionAverageCount;

	public MobileElement getCorrectSectionAverageCount() {
		return correctSectionAverageCount;
	}

	@AndroidFindBy(xpath = "//*[@text='Correct']/following-sibling::android.widget.RelativeLayout/child::*[contains(@resource-id,'bar1TipLabel')]")
	private MobileElement correctSectionBar1TipLabelCount;

	public MobileElement getCorrectSectionBar1TipLabelCount() {
		return correctSectionBar1TipLabelCount;
	}

	@AndroidFindBy(xpath = "//*[@text='Correct']/following-sibling::android.widget.RelativeLayout/child::*[contains(@resource-id,'bar2TipLabel')]")
	private MobileElement correctSectionBar2TipLabelCount;

	public MobileElement getCorrectSectionBar2TipLabelCount() {
		return correctSectionBar2TipLabelCount;
	}

	@AndroidFindBy(xpath = "//*[@text='Correct']/following-sibling::android.widget.RelativeLayout/child::*[@text='Average']")
	private MobileElement correctSectionAverageText;

	public MobileElement getCorrectSectionAverageText() {
		return correctSectionAverageText;
	}

	@AndroidFindBy(xpath = "//*[@text='Correct']/following-sibling::android.widget.RelativeLayout/child::android.widget.ImageView[contains(@resource-id,'bar1')]")
	private MobileElement correctSectionBar1Img;

	public MobileElement getCorrectSectionBar1Img() {
		return correctSectionBar1Img;
	}

	@AndroidFindBy(xpath = "//*[@text='Correct']/following-sibling::android.widget.RelativeLayout/child::android.widget.ImageView[contains(@resource-id,'bar2')]")
	private MobileElement correctSectionBar2Img;

	public MobileElement getCorrectSectionBar2Img() {
		return correctSectionBar2Img;
	}

	@AndroidFindBy(xpath = "//*[@text='Correct']/following-sibling::android.widget.LinearLayout/child::*[@text='Topper']")
	private MobileElement correctSectionTopperText;

	public MobileElement getCorrectSectionTopperText() {
		return correctSectionTopperText;
	}

	@AndroidFindBy(xpath = "//*[@text='Correct']/following-sibling::android.widget.LinearLayout/child::*[@text='You']")
	private MobileElement correctSectionOwnText;

	public MobileElement getCorrectSectionOwnText() {
		return correctSectionOwnText;
	}

	@AndroidFindBy(xpath = "//*[@text='Incorrect']")
	private MobileElement incorrectHeader;

	public MobileElement getIncorrectHeader() {
		return incorrectHeader;
	}

	@AndroidFindBy(xpath = "//*[@text='Incorrect']/following-sibling::android.widget.TextView")
	private MobileElement incorrectSectionSubTitle;

	public MobileElement getIncorrectSectionSubTitle() {
		return incorrectSectionSubTitle;
	}

	@AndroidFindBy(xpath = "//*[@text='Incorrect']/following-sibling::android.widget.RelativeLayout/child::*[contains(@resource-id,'averageTipLabel')]")
	private MobileElement incorrectSectionAverageCount;

	public MobileElement getIncorrectSectionAverageCount() {
		return incorrectSectionAverageCount;
	}

	@AndroidFindBy(xpath = "//*[@text='Incorrect']/following-sibling::android.widget.RelativeLayout/child::*[contains(@resource-id,'bar1TipLabel')]")
	private MobileElement incorrectSectionBar1TipLabelCount;

	public MobileElement getIncorrectSectionBar1TipLabelCount() {
		return incorrectSectionBar1TipLabelCount;
	}

	@AndroidFindBy(xpath = "//*[@text='Incorrect']/following-sibling::android.widget.RelativeLayout/child::*[contains(@resource-id,'bar2TipLabel')]")
	private MobileElement incorrectSectionBar2TipLabelCount;

	public MobileElement getIncorrectSectionBar2TipLabelCount() {
		return incorrectSectionBar2TipLabelCount;
	}

	@AndroidFindBy(xpath = "//*[@text='Incorrect']/following-sibling::android.widget.RelativeLayout/child::*[@text='Average']")
	private MobileElement incorrectSectionAverageText;

	public MobileElement getIncorrectSectionAverageText() {
		return incorrectSectionAverageText;
	}

	@AndroidFindBy(xpath = "//*[@text='Incorrect']/following-sibling::android.widget.RelativeLayout/child::android.widget.ImageView[contains(@resource-id,'bar1')]")
	private MobileElement incorrectSectionBar1Img;

	public MobileElement getIncorrectSectionBar1Img() {
		return incorrectSectionBar1Img;
	}

	@AndroidFindBy(xpath = "//*[@text='Incorrect']/following-sibling::android.widget.RelativeLayout/child::android.widget.ImageView[contains(@resource-id,'bar2')]")
	private MobileElement incorrectSectionBar2Img;

	public MobileElement getIncorrectSectionBar2Img() {
		return incorrectSectionBar2Img;
	}

	@AndroidFindBy(xpath = "//*[@text='Incorrect']/following-sibling::android.widget.LinearLayout/child::*[@text='Topper']")
	private MobileElement incorrectSectionTopperText;

	public MobileElement getIncorrectSectionTopperText() {
		return incorrectSectionTopperText;
	}

	@AndroidFindBy(xpath = "//*[@text='Incorrect']/following-sibling::android.widget.LinearLayout/child::*[@text='You']")
	private MobileElement incorrectSectionOwnText;

	public MobileElement getIncorrectSectionOwnText() {
		return incorrectSectionOwnText;
	}

	@AndroidFindBy(xpath = "//*[@text='Time Spent']")
	private MobileElement timeSpentHeader;

	public MobileElement getTimeSpentHeader() {
		return timeSpentHeader;
	}

	@AndroidFindBy(xpath = "//*[@text='Time Spent']/following-sibling::android.widget.TextView")
	private MobileElement timeSpentSectionSubTitle;

	public MobileElement getTimeSpentSectionSubTitle() {
		return timeSpentSectionSubTitle;
	}

	@AndroidFindBy(xpath = "//*[@text='Time Spent']/following-sibling::android.widget.RelativeLayout/child::*[contains(@resource-id,'averageTipLabel')]")
	private MobileElement timeSpentSectionAverageCount;

	public MobileElement getTimeSpentSectionAverageCount() {
		return timeSpentSectionAverageCount;
	}

	@AndroidFindBy(xpath = "//*[@text='Time Spent']/following-sibling::android.widget.RelativeLayout/child::*[contains(@resource-id,'bar1TipLabel')]")
	private MobileElement timeSpentSectionBar1TipLabelCount;

	public MobileElement getTimeSpentSectionBar1TipLabelCount() {
		return timeSpentSectionBar1TipLabelCount;
	}

	@AndroidFindBy(xpath = "//*[@text='Time Spent']/following-sibling::android.widget.RelativeLayout/child::*[contains(@resource-id,'bar2TipLabel')]")
	private MobileElement timeSpentSectionBar2TipLabelCount;

	public MobileElement getTimeSpentSectionBar2TipLabelCount() {
		return timeSpentSectionBar2TipLabelCount;
	}

	@AndroidFindBy(xpath = "//*[@text='Time Spent']/following-sibling::android.widget.RelativeLayout/child::*[@text='Average']")
	private MobileElement timeSpentSectionAverageText;

	public MobileElement getTimeSpentSectionAverageText() {
		return timeSpentSectionAverageText;
	}

	@AndroidFindBy(xpath = "//*[@text='Time Spent']/following-sibling::android.widget.RelativeLayout/child::android.widget.ImageView[contains(@resource-id,'bar1')]")
	private MobileElement timeSpentSectionBar1Img;

	public MobileElement getTimeSpentSectionBar1Img() {
		return timeSpentSectionBar1Img;
	}

	@AndroidFindBy(xpath = "//*[@text='Time Spent']/following-sibling::android.widget.RelativeLayout/child::android.widget.ImageView[contains(@resource-id,'bar2')]")
	private MobileElement timeSpentSectionBar2Img;

	public MobileElement getTimeSpentSectionBar2Img() {
		return timeSpentSectionBar2Img;
	}

	@AndroidFindBy(xpath = "//*[@text='Time Spent']/following-sibling::android.widget.LinearLayout/child::*[@text='Topper']")
	private MobileElement timeSpentSectionTopperText;

	public MobileElement getTimeSpentSectionTopperText() {
		return timeSpentSectionTopperText;
	}

	@AndroidFindBy(xpath = "//*[@text='Time Spent']/following-sibling::android.widget.LinearLayout/child::*[@text='You']")
	private MobileElement timeSpentSectionOwnText;

	public MobileElement getTimeSpentSectionOwnText() {
		return timeSpentSectionOwnText;
	}

	@AndroidFindBy(id = "com.adda247.app:id/ques_num")
	private List<MobileElement> questionNumberTabs;

	public List<MobileElement> getQuestionNumberTabs() {
		return questionNumberTabs;
	}

//-----------------------Akash
	@AndroidFindBy(id = "com.adda247.app:id/report_btn")
	private MobileElement ReportIcon;

	public MobileElement getReportIcon() {
		return ReportIcon;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.adda247.app:id/title\"]")
	private MobileElement ReportTitle;

	public MobileElement getReportTitle() {
		return ReportTitle;
	}

	@AndroidFindBy(xpath = "//*[@resource-id= \"com.adda247.app:id/report_checkbox\"]")
	private List<MobileElement> reportLists;

	public List<MobileElement> getReportLists() {
		return reportLists;
	}


	@AndroidFindBy(id = "com.adda247.app:id/doubtDescription")
	private MobileElement enterdoubt;

	public MobileElement getEnterDoubt() {
		return enterdoubt;
	}

	@AndroidFindBy(id = "com.adda247.app:id/positive_button")
	private MobileElement enterNextButton;

	public MobileElement getNextButton() {
		return enterNextButton;
	}

	@AndroidFindBy(id = "com.adda247.app:id/negative_button")
	private MobileElement cancelButton;

	public MobileElement getCancelButton() {
		return cancelButton;
	}

	@AndroidFindBy(id = "com.adda247.app:id/doubt_title")
	private MobileElement doubtTab;

	public MobileElement getDoubtTab() {
		return doubtTab;
	}

	@AndroidFindBy(xpath = "(//android.widget.RelativeLayout[@resource-id=\"com.adda247.app:id/ll_content_item\"])[5]")
	private MobileElement reportedQuestion;

	public MobileElement getReportedQuestion() {
		return reportedQuestion;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Pending\"]")
	private MobileElement pendingTab;

	public MobileElement getPendingTab() {
		return pendingTab;
	}
}
