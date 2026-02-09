package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
//Audio status--->enabled
public class DoubtRoomPage_OR {
	
	@AndroidFindBy(id = "topTitle")
	private MobileElement subscribedPackageTopTitle;

	public MobileElement getSubscribedPackageTopTitle() {
		return subscribedPackageTopTitle;
	}
	
	@AndroidFindBy(id = "selectAllCourseChkBox")
	private MobileElement selectAllCourseCheckBox;

	public MobileElement getSelectAllCourseCheckBox() {
		return selectAllCourseCheckBox;
	}
	
	@AndroidFindBy(id = "selectAllExamChkBox")
	private MobileElement selectAllExamCheckBox;

	public MobileElement getSelectAllExamCheckBox() {
		return selectAllExamCheckBox;
	}
	
	@AndroidFindBy(id = "applyFilter")
	private MobileElement applyFilterBtn;

	public MobileElement getApplyFilterBtn() {
		return applyFilterBtn;
	}
	
	@AndroidFindBy(id = "btn_done")
	private MobileElement doneBtn;

	public MobileElement getDoneBtn() {
		return doneBtn;
	}
	
	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,'ivBack')]/following-sibling::android.widget.TextView")
	private MobileElement purchasedPackageTitle;

	public MobileElement getPurchasedPackageTitle() {
		return purchasedPackageTitle;
	}
	
	@AndroidFindBy(id = "examModeSwitch")
	private MobileElement examModeSwitchBtn;

	public MobileElement getExamModeSwitchBtn() {
		return examModeSwitchBtn;
	}
	
	@AndroidFindBy(id = "manageExamBtn")
	private MobileElement manageExamBtn;
	
	public MobileElement getManageExamBtn() {
		return manageExamBtn;
	}

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc]")
	private List<MobileElement> courseTabList;

	public List<MobileElement> getCourseTabList() {
		return courseTabList;
	}
	
	@AndroidFindBy(id = "packageDescription")
	private List<MobileElement> childPackageDescription;

	public List<MobileElement> getChildPackageDescription() {
		return childPackageDescription;
	}
	
	@AndroidFindBy(id = "view_float")
	private MobileElement doubtRoomBtn;

	public MobileElement getDoubtRoomBtn() {
		return doubtRoomBtn;
	}
	
	@AndroidFindBy(xpath = "//*[@text='Doubt Room']")
	private MobileElement doubtRoomPageTitle;

	public MobileElement getDoubtRoomPageTitle() {
		return doubtRoomPageTitle;
	}
	
	@AndroidFindBy(accessibility = "Back")
	private MobileElement doubtRoomBackBtn;

	public MobileElement getDoubtRoomBackBtn() {
		return doubtRoomBackBtn;
	}
	
	@AndroidFindBy(xpath = "//*[@text='Our experts are taking a break']")
	private MobileElement facultyNotAvailableText;

	public MobileElement getFacultyNotAvailableText() {
		return facultyNotAvailableText;
	}
	
	@AndroidFindBy(xpath = "//*[@text='Live Doubt Rooms']")
	private MobileElement liveDoubtRoomTitle;

	public MobileElement getLiveDoubtRoomTitle() {
		return liveDoubtRoomTitle;
	}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Upcoming Doubt Rooms']")
	private MobileElement upCommingDoubtRoomTitle;
	
	public MobileElement getUpCommingDoubtRoomTitle() {
		return upCommingDoubtRoomTitle;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Faculty')]")
	private List<MobileElement> facultNameText;

	public List<MobileElement> getFacultNameText() {
		return facultNameText;
	}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Faculty')]/preceding-sibling::android.widget.TextView")
	private List<MobileElement> doubtRoomTopicText;

	public List<MobileElement> getDoubtRoomTopicText() {
		return doubtRoomTopicText;
	}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Faculty')]/following-sibling::android.widget.TextView")
	private List<MobileElement> doubtRoomTimingText;

	public List<MobileElement> getDoubtRoomTimingText() {
		return doubtRoomTimingText;
	}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Join now']")
	private MobileElement joinNowBtn;

	public MobileElement getJoinNowBtn() {
		return joinNowBtn;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@text='Welcome']")
	private MobileElement welocmePopUpTitle;

	public MobileElement getWelocmePopUpTitle() {
		return welocmePopUpTitle;
	}
	
	@AndroidFindBy(xpath = "//*[@text='Topic']")
	//@AndroidFindBy(xpath = "//android.view.View[@text='Topic']/following-sibling::android.view.View")
	private MobileElement welcomePopUpTopicName;

	public MobileElement getWelcomePopUpTopicName() {
		return welcomePopUpTopicName;
	}
	
	@AndroidFindBy(xpath = "//*[@text='Professor']")
	//@AndroidFindBy(xpath = "//android.view.View[@text='Professor']/following-sibling::android.view.View")
	private MobileElement welcomePopUpProfessorName;

	public MobileElement getWelcomePopUpProfessorName() {
		return welcomePopUpProfessorName;
	}
	
	@AndroidFindBy(xpath = "//*[@text='Start Time']")
	//@AndroidFindBy(xpath = "//android.view.View[@text='Start Time']/following-sibling::android.view.View")
	private MobileElement startTimeText;

	public MobileElement getStartTimeText() {
		return startTimeText;
	}
	
	@AndroidFindBy(xpath = "//*[@text='JOIN']")
	private MobileElement joinBtn;

	public MobileElement getJoinBtn() {
		return joinBtn;
	}
	
	@AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_one_time_button")
	private MobileElement onlyThisTimeBtn;

	public MobileElement getOnlyThisTimeBtn() {
		return onlyThisTimeBtn;
	}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Class Resuming Soon !']")
	private MobileElement classResumeSoonText;
	
	public MobileElement getClassResumeSoonText() {
		return classResumeSoonText;
	}
	
	/*------------Live Doubt Room Screen-----------------------------*/
	
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Group chat is')]")//Group chat is enabled//Group chat is disabled
	private MobileElement groupChatStatusText;

	public MobileElement getGroupChatStatusText() {
		return groupChatStatusText;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@resource-id='vc-mobile-main-vid-cont']/android.widget.Button[1]")
	private MobileElement fullScreenBtn;

	public MobileElement getFullScreenBtn() {
		return fullScreenBtn;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@resource-id='vc-mobile-main-vid-cont']/android.widget.Button[2]")
	private MobileElement dataSaverBtn;

	public MobileElement getDataSaverBtn() {
		return dataSaverBtn;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@text='Do you want to enable Datasaver mode?']")
	private MobileElement dataSaverEnableText;

	public MobileElement getDataSaverEnableText() {
		return dataSaverEnableText;
	}
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='OK']")
	private MobileElement OkBtn;
	
	public MobileElement getOkBtn() {
		return OkBtn;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@text='CANCEL']")
	private MobileElement cancelBtn;
	
	public MobileElement getCancelBtn() {
		return cancelBtn;
	}

	@AndroidFindBy(xpath = "//android.view.View[@resource-id='vc-mobile-main-vid-cont']/android.view.View[not(@resource-id)]")
	private MobileElement eventStartText;

	public MobileElement getEventStartText() {
		return eventStartText;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[contains(@text,'Sending to')]")//Sending to the professor only//Sending to everyone
	private MobileElement sendingToText;

	public MobileElement getSendingToText() {
		return sendingToText;
	}
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='vc-mobile-chat-input']")
	private MobileElement messageSentTextField;

	public MobileElement getMessageSentTextField() {
		return messageSentTextField;
	}
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='send']")
	private MobileElement messageSendBtn;

	public MobileElement getMessageSendBtn() {
		return messageSendBtn;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@resource-id='vc-mobile-controls-cont']/android.view.View[1]")
	private MobileElement audioTab;
	
	
	public MobileElement getAudioTab() {
		return audioTab;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@resource-id='vc-mobile-controls-cont']/android.view.View[2]")
	private MobileElement videoTab;

	public MobileElement getVideoTab() {
		return videoTab;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@resource-id='vc-mobile-controls-cont']/android.view.View[3]")
	private MobileElement exitTab;

	public MobileElement getExitTab() {
		return exitTab;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@text='Do you really want to exit the session?']")
	private MobileElement exitSessionText;

	public MobileElement getExitSessionText() {
		return exitSessionText;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@text='Raise Hand']")
	private MobileElement raiseHandTab;

	public MobileElement getRaiseHandTab() {
		return raiseHandTab;
	}
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='Open phone interactions menu']")
	private MobileElement threeDotTab;

	public MobileElement getThreeDotTab() {
		return threeDotTab;
	}
	
	@AndroidFindBy(xpath = "//android.view.MenuItem[@text='report issue']")
	private MobileElement reportIssueTab;

	public MobileElement getReportIssueTab() {
		return reportIssueTab;
	}
	
	@AndroidFindBy(xpath = "//android.widget.ListView[contains(@resource-id,'select')]")
	private MobileElement issueDropDown;

	public MobileElement getIssueDropDown() {
		return issueDropDown;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[contains(@resource-id,'select_option')]")
	private List<MobileElement> issueList;

	public List<MobileElement> getIssueList() {
		return issueList;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@resource-id='chatWindow']/android.view.View")
	private List<MobileElement> chatMessageText;

	public List<MobileElement> getChatMessageText() {
		return chatMessageText;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@resource-id='chatWindow']/android.view.View/android.view.View[1]")
	private List<MobileElement> ownSentMessageText;

	public List<MobileElement> getOwnSentMessageText() {
		return ownSentMessageText;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@resource-id='chatWindow']/android.view.View/android.view.View[2]")
	private List<MobileElement> facultySentMessageText;

	public List<MobileElement> getFacultySentMessageText() {
		return facultySentMessageText;
	}
	
	@AndroidFindBy(xpath = "//android.widget.Image[@resource-id='i-whiteboard']")
	private MobileElement whiteBoardScreen;

	public MobileElement getWhiteBoardScreen() {
		return whiteBoardScreen;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@resource-id='vc-mobile-other-videos-cont']")
	private MobileElement videoOnScreen;

	public MobileElement getVideoOnScreen() {
		return videoOnScreen;
	}
	
	@AndroidFindBy(xpath = "//android.widget.RadioButton[contains(@resource-id,'radio')]")
	private List<MobileElement> pollOptionRadioBtn;

	public List<MobileElement> getPollOptionRadioBtn() {
		return pollOptionRadioBtn;
	}
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='arrow_forward']")
	private MobileElement pollSubmitBtn;

	public MobileElement getPollSubmitBtn() {
		return pollSubmitBtn;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@text='A new handout has been shared']")
	private MobileElement handOutShareText;

	public MobileElement getHandOutShareText() {
		return handOutShareText;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@text='A new handout has been shared']/following-sibling::android.view.View/android.view.View")
	private List<MobileElement> handOutsDownloadBtn;

	public List<MobileElement> getHandOutsDownloadBtn() {
		return handOutsDownloadBtn;
	}
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='CLOSE']")
	private MobileElement closeBtn;

	public MobileElement getCloseBtn() {
		return closeBtn;
	}
	
	@AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
	private MobileElement allowBtn;

	public MobileElement getAllowBtn() {
		return allowBtn;
	}
	
	@AndroidFindBy(id = "progressBar")
	private MobileElement progressBar;

	public MobileElement getProgressBar() {
		return progressBar;
	}
	
	/*------------------ChromeBrowser for teacher Screen---------------------------*/

	@AndroidFindBy(id = "com.android.chrome:id/terms_accept")
	private MobileElement termAcceptBtn;

	public MobileElement getTermAcceptBtn() {
		return termAcceptBtn;
	}

	@AndroidFindBy(id = "com.android.chrome:id/negative_button")
	private MobileElement noThanksBtn;

	public MobileElement getNoThanksBtn() {
		return noThanksBtn;
	}

	@AndroidFindBy(id = "com.android.chrome:id/search_box_text")
	private MobileElement chromeSearchTextField;

	public MobileElement getChromeSearchTextField() {
		return chromeSearchTextField;
	}

	@AndroidFindBy(id = "com.android.chrome:id/url_bar")
	private MobileElement urlBarSearchField;


	public MobileElement getUrlBarSearchField() {
		return urlBarSearchField;
	}

	@AndroidFindBy(xpath = "//*[@text='Continue']")
	private MobileElement continueBtn;

	public MobileElement getContinueBtn() {
		return continueBtn;
	}

	@AndroidFindBy(accessibility = "Close")
	private MobileElement languageChangeSheetCloseBtn;

	public MobileElement getLanguageChangeSheetCloseBtn() {
		return languageChangeSheetCloseBtn;
	}

	@AndroidFindBy(xpath = "//*[@text='Files']/preceding-sibling::android.widget.ImageView")
	private MobileElement fileTab;

	public MobileElement getFileTab() {
		return fileTab;
	}

	@AndroidFindBy(id = "com.google.android.documentsui:id/icon_mime_lg")
	private MobileElement pdfFile;

	public MobileElement getPdfFile() {
		return pdfFile;
	}
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='feEmailAddress']")
	private MobileElement emailTextField;

	public MobileElement getEmailTextField() {
		return emailTextField;
	}
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='fePassword']")
	private MobileElement passwordTextField;

	public MobileElement getPasswordTextField() {
		return passwordTextField;
	}
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='Login']")
	private MobileElement loginBtn;

	public MobileElement getLoginBtn() {
		return loginBtn;
	}
	
	@AndroidFindBy(id = "com.android.chrome:id/infobar_message")
	private MobileElement savePwdText;

	public MobileElement getSavePwdText() {
		return savePwdText;
	}
	
	@AndroidFindBy(id = "com.android.chrome:id/button_secondary")
	private MobileElement neverBtn;

	public MobileElement getNeverBtn() {
		return neverBtn;
	}
	
	@AndroidFindBy(xpath = "//android.view.View/android.view.View/android.view.View[@text='Join Now']")
	private MobileElement teacherJoinBtn;

	public MobileElement getTeacherJoinBtn() {
		return teacherJoinBtn;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@resource-id='customroot']")
	private MobileElement customRoot;

	public MobileElement getCustomRoot() {
		return customRoot;
	}
	
	@AndroidFindBy(xpath = "//*[@text='Packages']")
	private MobileElement packageTitle;

	public MobileElement getPackageTitle() {
		return packageTitle;
	}
	
	@AndroidFindBy(xpath = "//android.widget.Button[contains(@text,'NOW')]")
	private MobileElement startNowBtn;

	public MobileElement getStartNowBtn() {
		return startNowBtn;
	}
	
	@AndroidFindBy(id = "com.android.chrome:id/positive_button")
	private MobileElement audioAllowBtn;

	public MobileElement getAudioAllowBtn() {
		return audioAllowBtn;
	}
	
	@AndroidFindBy(xpath = "//android.widget.CheckBox[@text='ChatLock']")
	private MobileElement groupChatDisableBtn;

	public MobileElement getGroupChatDisableBtn() {
		return groupChatDisableBtn;
	}
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='vc-mobile-chat-input']")
	private MobileElement teacherSideMessageTextField;

	public MobileElement getTeacherSideMessageTextField() {
		return teacherSideMessageTextField;
	}
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='send']")
	private MobileElement teacherSideMessageSendBtn;

	public MobileElement getTeacherSideMessageSendBtn() {
		return teacherSideMessageSendBtn;
	}
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='share whiteboard']")
	private MobileElement whiteBoardTab;

	public MobileElement getWhiteBoardTab() {
		return whiteBoardTab;
	}
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='Open phone interactions menu']")
	private MobileElement teacherSideThreeDotTab;

	public MobileElement getTeacherSideThreeDotTab() {
		return teacherSideThreeDotTab;
	}
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='share whiteboard']/preceding-sibling::android.view.View[4]")
	private MobileElement teacherExitTab;

	public MobileElement getTeacherExitTab() {
		return teacherExitTab;
	}
	
	@AndroidFindBy(xpath = "//android.view.MenuItem[@text='True False Poll']")
	private MobileElement trueFalsePollTab;

	public MobileElement getTrueFalsePollTab() {
		return trueFalsePollTab;
	}
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='SUBMIT']")
	private MobileElement pollCreateBtn;

	public MobileElement getPollCreateBtn() {
		return pollCreateBtn;
	}
	
	@AndroidFindBy(id = "com.android.chrome:id/tab_switcher_button")
	private MobileElement tabSwitchBtn;

	public MobileElement getTabSwitchBtn() {
		return tabSwitchBtn;
	}
	
	@AndroidFindBy(id = "com.android.chrome:id/menu_button")
	private MobileElement chromeMoreOption;

	public MobileElement getChromeMoreOption() {
		return chromeMoreOption;
	}
	
	@AndroidFindBy(accessibility = "Close all tabs")
	private MobileElement closeAllTabs;

	public MobileElement getCloseAllTabs() {
		return closeAllTabs;
	}
	
	@AndroidFindBy(accessibility = "New tab")
	private MobileElement newTab;

	public MobileElement getNewTab() {
		return newTab;
	}
	
	@AndroidFindBy(xpath = "//*[@text='LIVE / UPCOMING ROOMS']/ancestor::android.view.View/preceding-sibling::android.view.View/descendant::android.view.View[@content-desc]")
	private MobileElement adminProfileIcon;

	public MobileElement getAdminProfileIcon() {
		return adminProfileIcon;
	}
	
	@AndroidFindBy(xpath = "//*[contains(@text,'Logout')]")
	private MobileElement adminLogoutBtn;

	public MobileElement getAdminLogoutBtn() {
		return adminLogoutBtn;
	}
	
	@AndroidFindBy(xpath = "//*[contains(@text,'has raised hand')]")
	private MobileElement studentRaiseHandMsg;

	public MobileElement getStudentRaiseHandMsg() {
		return studentRaiseHandMsg;
	}
	
	@AndroidFindBy(xpath = "//*[@text='The lecture is over. Recording will be available shortly.']")
	private MobileElement doubtRoomCompleteText;

	public MobileElement getDoubtRoomCompleteText() {
		return doubtRoomCompleteText;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
