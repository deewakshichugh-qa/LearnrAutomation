package pageObject;

import java.util.List;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class HomePage_OR {

	@AndroidFindBy(accessibility = "Home")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'Home')]")
	private MobileElement btnHome;

	public MobileElement getBtnHome() {
		return btnHome;
	}

	@AndroidFindBy(id = "navigation_community")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'Community')]")
	private MobileElement btnCommunity;

	public MobileElement getBtnCommunity() {
		return btnCommunity;
	}

	@AndroidFindBy(id = "navigation_doubt")
	private MobileElement btnDoubts;

	public MobileElement getBtnDoubts() {
		return btnDoubts;
	}

	@AndroidFindBy(accessibility = "My Content")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'My Content')]")
	private MobileElement btnMyContent;

	public MobileElement getBtnMyContent() {
		return btnMyContent;
	}

	@AndroidFindBy(accessibility = "Store")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'storeSection')]")
	private MobileElement btnStore;

	public MobileElement getBtnStore() {
		return btnStore;
	}

	@AndroidFindBy(accessibility = "Test Prime")
	private MobileElement btnTestPass;

	public MobileElement getBtnTestPass() {
		return btnTestPass;
	}

	@AndroidFindBy(id = "image")
	private MobileElement applicationLogo;

	public MobileElement getApplicationLogo() {
		return applicationLogo;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='You can change exam category here']")
	private MobileElement bannerChangeExamCategory;

	public MobileElement getBannerChangeExamCategory() {
		return bannerChangeExamCategory;
	}

	@AndroidFindAll({
			@AndroidBy(xpath = "//android.widget.HorizontalScrollView[contains(@resource-id,'tabs')]/descendant::android.widget.TextView[1]"),
			@AndroidBy(accessibility = "FEED") })
	private MobileElement tabFeed;

	public MobileElement getTabFeed() {
		return tabFeed;
	}

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"STUDY MATERIAL\"]")
	private MobileElement tabStudyMaterial;

	public MobileElement getTabStudyMaterial() {
		return tabStudyMaterial;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Banking']/preceding-sibling::XCUIElementTypeButton")
	private MobileElement btnNavigationDrawer;

	public MobileElement getBtnNavigationDrawer() {
		return btnNavigationDrawer;
	}

	@AndroidFindBy(id = "thumbnail")
	private List<MobileElement> videoPlayIcon;

	public List<MobileElement> getVideoPlayIcon() {
		return videoPlayIcon;
	}

	@AndroidFindBy(id = "youtube_container")
	private MobileElement videoPlayScreen;

	public MobileElement getVideoPlayScreen() {
		return videoPlayScreen;
	}

	@AndroidFindBy(id = "close")
	private MobileElement videoPlayScreenCloseBtn;

	public MobileElement getVideoPlayScreenCloseBtn() {
		return videoPlayScreenCloseBtn;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Choose your Language\n" +
			"Choose a Language you are comfortable in Studying\"]")
	private MobileElement changeLanguagePageTitle;

	public MobileElement getChangeLanguagePageTitle() {
		return changeLanguagePageTitle;
	}

	@AndroidFindBy(xpath = "//*[contains(@text,'English')] /parent::android.view.View/following-sibling::android.view.View")
	private List<MobileElement> languageBtnList;

	public List<MobileElement> getLanguageBtnList() {
		return languageBtnList;
	}

	@AndroidFindBy(accessibility = "Notifications")
	private MobileElement notificationIcon;

	public MobileElement getNotificationIcon() {
		return notificationIcon;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Search\"]")
	private MobileElement searchIconOnHome;

	public MobileElement searchIconOnHome() {
		return searchIconOnHome;
	}

	@AndroidFindBy(xpath = "//*[@text='Notifications']")
	private MobileElement notificationPageTitle;

	public MobileElement getNotificationPageTitle() {
		return notificationPageTitle;
	}

	@AndroidFindBy(accessibility = "Navigate up")
	private MobileElement backBtn;

	public MobileElement getBackBtn() {
		return backBtn;
	}

	@AndroidFindBy(id = "com.adda247.app:id/changeExamContainer")
	@iOSXCUITFindBy(accessibility = "Banking")
	private MobileElement changeExamContainer;

	public MobileElement getChangeExamContainer() {
		return changeExamContainer;
	}

	@AndroidFindBy(id = "exam_title")
	private MobileElement examTitle;

	public MobileElement getExamTitle() {
		return examTitle;
	}

	@AndroidFindBy(id = "title")
	private List<MobileElement> iconTabTextList;

	public List<MobileElement> getIconTabTextList() {
		return iconTabTextList;
	}

	@AndroidFindBy(xpath = "//*[contains(@resource-id,'widget_list_item')]//*[@text='Daily Quizzes']")
	@iOSXCUITFindBy(accessibility = "Daily Quizzes")
	private MobileElement iconDailyQuiz;

	public MobileElement getIconDailyQuiz() {
		return iconDailyQuiz;
	}

	@AndroidFindBy(id = "title")
	private List<MobileElement> homePageTabList;

	public List<MobileElement> getHomePageTabList() {
		return homePageTabList;
	}

	@AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@resource-id,'toolbar')]/android.widget.TextView")
	private MobileElement selectedTabPageTitle;

	public MobileElement getSelectedTabPageTitle() {
		return selectedTabPageTitle;
	}

	@AndroidFindBy(id = "msg")
	private List<MobileElement> postCommentIcon;

	public List<MobileElement> getPostCommentIcon() {
		return postCommentIcon;
	}

	@AndroidFindBy(id = "like_comment_count")
	private List<MobileElement> postCommentCountText;

	public List<MobileElement> getPostCommentCountText() {
		return postCommentCountText;
	}

	@AndroidFindBy(id = "like")
	private List<MobileElement> postLikeIcon;

	public List<MobileElement> getPostLikeIcon() {
		return postLikeIcon;
	}

	@AndroidFindBy(id = "like_count")
	private List<MobileElement> postLikeCountText;

	public List<MobileElement> getPostLikeCountText() {
		return postLikeCountText;
	}

	@AndroidFindBy(id = "share_link")
	private List<MobileElement> postShareIcon;

	public List<MobileElement> getPostShareIcon() {
		return postShareIcon;
	}

	@AndroidFindBy(id = "android:id/resolver_list")
	private MobileElement shareScreen;

	public MobileElement getShareScreen() {
		return shareScreen;
	}

	@AndroidFindBy(id = "book_marks")
	private List<MobileElement> postBookMarkIcon;

	public List<MobileElement> getPostBookMarkIcon() {
		return postBookMarkIcon;
	}

	@AndroidFindBy(id = "book_marks")
	private MobileElement postBookMark;

	public MobileElement getPostBookMark() {
		return postBookMark;
	}

	@AndroidFindBy(id = "view_type")
	private List<MobileElement> postTypeHeaderView;

	public List<MobileElement> getPostTypeHeaderView() {
		return postTypeHeaderView;
	}

	@AndroidFindBy(id = "description")
	private List<MobileElement> videoPostDescription;

	public List<MobileElement> getVideoPostDescription() {
		return videoPostDescription;
	}

	@AndroidFindBy(id = "btn_test_state")
	private MobileElement testStateBtn;

	public MobileElement getTestStateBtn() {
		return testStateBtn;
	}

	@AndroidFindBy(id = "main_img")
	private MobileElement testStateImg;

	public MobileElement getTestStateImg() {
		return testStateImg;
	}

	@AndroidFindBy(id = "btn_register")
	private MobileElement registerForFreeBtn;

	public MobileElement getRegisterForFreeBtn() {
		return registerForFreeBtn;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@text='CONTINUE']")
	private MobileElement registrationConfContinueBtn;

	public MobileElement getRegistrationConfContinueBtn() {
		return registrationConfContinueBtn;
	}

	@AndroidFindBy(id = "helpAndSupportBtn")
	private MobileElement helpAndSupportBtn;

	public MobileElement getHelpAndSupportBtn() {
		return helpAndSupportBtn;
	}

	@AndroidFindBy(id = "text")
	private MobileElement filterInstructionText;

	public MobileElement getFilterInstructionText() {
		return filterInstructionText;
	}

	@AndroidFindBy(xpath = "//*[@text='Ask Doubt']")
	private MobileElement askDoubtTab;

	public MobileElement getAskDoubtTab() {
		return askDoubtTab;
	}

	@AndroidFindBy(xpath = "//*[contains(@resource-id,'widget_list_item')]//*[@text='Daily Quizzes']")
	private MobileElement quizTab;

	public MobileElement getQuizTab() {
		return quizTab;
	}

	@AndroidFindBy(xpath = "//*[contains(@resource-id,'widget_list_item')]//*[@text='Previous Year Papers']")
	private MobileElement pypTab;

	public MobileElement getPypTab() {
		return pypTab;
	}

	@AndroidFindBy(xpath = "//*[contains(@resource-id,'widget_list_item')]//*[@text='Videos']")
	private MobileElement videosTab;

	public MobileElement getVideosTab() {
		return videosTab;
	}

	@AndroidFindBy(xpath = "//*[contains(@resource-id,'widget_list_item')]//*[@text='Current Affairs']")
	private MobileElement currentAffairTab;

	public MobileElement getCurrentAffairTab() {
		return currentAffairTab;
	}

	@AndroidFindBy(xpath = "//*[contains(@resource-id,'widget_list_item')]//*[@text='Job Alerts']")
	private MobileElement jobAlertTab;

	public MobileElement getJobAlertTab() {
		return jobAlertTab;
	}

	@AndroidFindBy(xpath = "//*[contains(@resource-id,'widget_list_item')]//*[@text='Notes and Articles']")
	private MobileElement notesAndArticleTab;

	public MobileElement getNotesAndArticleTab() {
		return notesAndArticleTab;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Free Live Classes' and @resource-id='com.adda247.app:id/title']")
	private MobileElement freeLiveClassTab;

	public MobileElement getFreeLiveClassTab() {
		return freeLiveClassTab;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Free Live Classes']")
	private List<MobileElement> freeLiveClassTabs;

	public List<MobileElement> getFreeLiveClassTabs() {
		return freeLiveClassTabs;
	}

	// -----Daily Quiz---------------------

	@AndroidFindBy(id = "text")
	private MobileElement bannerFilterInstruction;

	public MobileElement getBannerFilterInstruction() {
		return bannerFilterInstruction;
	}

	@AndroidFindBy(id = "com.adda247.app:id/bl_title")
	private MobileElement listQuiz;

	public MobileElement getListQuiz() {
		return listQuiz;
	}

	@AndroidFindBy(id = "downloaded_status_line1")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'GET QUIZ')]")
	private List<MobileElement> linkDownloadQuiz;

	public List<MobileElement> getLinkDownloadQuiz() {
		return linkDownloadQuiz;
	}

	@AndroidFindBy(id = "action_language_change")
	private MobileElement iconFilter;

	public MobileElement getIconFilter() {
		return iconFilter;
	}

	/*-------------------------------------------------quiz attempt section--------------------------------*/
	@AndroidFindBy(id = "ti_title")
	private MobileElement attemptingQuizeTitle;

	public MobileElement getAttemptingQuizeTitle() {
		return attemptingQuizeTitle;
	}

	@AndroidFindBy(id = "start_test")
	private MobileElement startTestBtn;

	public MobileElement getStartTestBtn() {
		return startTestBtn;
	}

	@AndroidFindBy(id = "option_index")
	private List<MobileElement> optionIndexBtn;

	public List<MobileElement> getOptionIndexBtn() {
		return optionIndexBtn;
	}

	@AndroidFindBy(id = "savenext")
	private MobileElement saveNextBtn;

	public MobileElement getSaveNextBtn() {
		return saveNextBtn;
	}

	@AndroidFindBy(id = "submit_test")
	private MobileElement submitTestBtn;

	public MobileElement getSubmitTestBtn() {
		return submitTestBtn;
	}

	@AndroidFindBy(id = "alertTitle")
	private MobileElement finishAlertTitle;

	public MobileElement getFinishAlertTitle() {
		return finishAlertTitle;
	}

	@AndroidFindBy(id = "android:id/button2")
	private MobileElement quizeResumeBtn;

	public MobileElement getQuizeResumeBtn() {
		return quizeResumeBtn;
	}

	@AndroidFindBy(id = "android:id/button1")
	private MobileElement quizeSubmitBtn;

	public MobileElement getQuizeSubmitBtn() {
		return quizeSubmitBtn;
	}
	// ------------------------------------Navigation
	// drawer--------------------------------------------------------------

	@AndroidFindBy(id = "com.adda247.app:id/nav_header_home")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'.com')]")
	private MobileElement sectionUserProfile;

	public MobileElement getSectionUserProfile() {
		return sectionUserProfile;
	}

	@AndroidFindBy(id = "nav_home_night_mode")
	private MobileElement sectionNightMode;

	public MobileElement getSectionNightMode() {
		return sectionNightMode;
	}

	@AndroidFindBy(id = "nav_home_current_affairs")
	@iOSXCUITFindBy(accessibility = "Current Affairs")
	private MobileElement sectionCurrentAffairs;

	public MobileElement getSectionCurrentAffairs() {
		return sectionCurrentAffairs;
	}

	@AndroidFindBy(id = "nav_home_group_quizzes")
	@iOSXCUITFindBy(accessibility = "Daily Quizzes")
	private MobileElement sectionDailyQuizzes;

	public MobileElement getSectionDailyQuizzes() {
		return sectionDailyQuizzes;
	}

	@AndroidFindBy(id = "nav_home_job_alerts")
	@iOSXCUITFindBy(accessibility = "Job Alerts")
	private MobileElement sectionJobAlerts;

	public MobileElement getSectionJobAlerts() {
		return sectionJobAlerts;
	}

	@AndroidFindBy(id = "nav_home_power_capsules")
	@iOSXCUITFindBy(accessibility = "Power Capsules")
	private MobileElement sectionPowerCapsules;

	public MobileElement getSectionPowerCapsules() {
		return sectionPowerCapsules;
	}

	@AndroidFindBy(id = "nav_home_articles")
	@iOSXCUITFindBy(accessibility = "Notes and Articles")
	private MobileElement sectionNotesAndArticles;

	public MobileElement getSectionNotesAndArticles() {
		return sectionNotesAndArticles;
	}

	@AndroidFindBy(id = "nav_home_videos")
	@iOSXCUITFindBy(accessibility = "Videos")
	private MobileElement sectionVideos;

	public MobileElement getSectionVideos() {
		return sectionVideos;
	}

	@AndroidFindBy(id = "nav_store")
	private MobileElement sectionBuyStudyMaterial;

	public MobileElement getSectionBuyStudyMaterial() {
		return sectionBuyStudyMaterial;
	}

	@AndroidFindBy(id = "nav_my_content")
	private MobileElement sectionMyContent;

	public MobileElement getSectionMyContent() {
		return sectionMyContent;
	}

	@AndroidFindBy(id = "nav_home_help")
	@iOSXCUITFindBy(accessibility = "Help & Support")
	private MobileElement sectionHelpAndSupport;

	public MobileElement getSectionHelpAndSupport() {
		return sectionHelpAndSupport;
	}

	@AndroidFindBy(id = "nav_offline_center")
	private MobileElement sectionOfflineCenter;

	public MobileElement getSectionOfflineCenter() {
		return sectionOfflineCenter;
	}

	@AndroidFindBy(xpath = "//android.widget.RelativeLayout/android.webkit.WebView")
	private MobileElement helpAndSupportWebView;

	public MobileElement getHelpAndSupportWebView() {
		return helpAndSupportWebView;
	}

	@AndroidFindBy(id = "nav_home_settings")
	@iOSXCUITFindBy(accessibility = "Settings")
	private MobileElement sectionSettings;

	public MobileElement getSectionSettings() {
		return sectionSettings;
	}

	@AndroidFindBy(id = "rate_app")
	@iOSXCUITFindBy(accessibility = "Rate Us")
	private MobileElement sectionRateUs;

	public MobileElement getSectionRateUs() {
		return sectionRateUs;
	}

	@AndroidFindBy(id = "changeModeSwitchCompat")
	private MobileElement nightModeOnOffToggle;

	public MobileElement getNightModeOnOffToggle() {
		return nightModeOnOffToggle;
	}

	@AndroidFindBy(id = "heading")
	private MobileElement ratingPopupTitle;

	public MobileElement getRatingPopupTitle() {
		return ratingPopupTitle;
	}

	@AndroidFindBy(id = "cancel")
	private MobileElement ratingCancelBtn;

	public MobileElement getRatingCancelBtn() {
		return ratingCancelBtn;
	}

	@AndroidFindBy(id = "rate_now")
	private MobileElement rateNowBtn;

	public MobileElement getRateNowBtn() {
		return rateNowBtn;
	}

	@AndroidFindBy(id = "progressBar")
	private MobileElement pageLoaderIcon;

	public MobileElement getPageLoaderIcon() {
		return pageLoaderIcon;
	}

	@AndroidFindBy(id = "progress_bar_large")
	private MobileElement progressBarIcon;

	public MobileElement getProgressBarIcon() {
		return progressBarIcon;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.adda247.app:id/refer_now']")
	private List<MobileElement> referNowBtn;

	public List<MobileElement> getReferNowBtn() {
		return referNowBtn;
	}

	@AndroidFindBy(id = "android:id/title_container")
	private MobileElement sharePageTitle;

	public MobileElement getSharePageTitle() {
		return sharePageTitle;
	}

	@AndroidFindBy(id = "com.adda247.app:id/nav_home_performance_dashboard")
	private MobileElement performanceDashboard;

	public MobileElement performanceDashboard() {
		return performanceDashboard;
	}

	@AndroidFindBy(id = "com.adda247.app:id/nav_home_adda_ai")
	private MobileElement addaAI;

	public MobileElement addaAI() {
		return addaAI;
	}

//-------------------------------STREAK----------------------------

	@AndroidFindBy(xpath = "//*[@text='Participate and Win']")
	private MobileElement participateAndWin;

	public MobileElement getTitleParticipateAndWin() {
		return participateAndWin;
	}

	@AndroidFindBy(id = "com.adda247.app:id/img_cross")
	private MobileElement freeLiveClassBottomBannerCloseIcon;

	public MobileElement freeLiveClassBottomBannerCloseIcon() {
		return freeLiveClassBottomBannerCloseIcon;
	}
}
