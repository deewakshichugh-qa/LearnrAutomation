package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LiveClassesPage_OR {

	@AndroidFindBy(id = "ivBack")
	private MobileElement btnNavigateUp;

	public MobileElement getBtnNavigateUp() {
		return btnNavigateUp;
	}

	@AndroidFindBy(id = "product_title")
	private MobileElement listTitle;

	public MobileElement getListTitle() {
		return listTitle;
	}

	@AndroidFindBy(id = "product_view")
	private List<MobileElement> listPackage;

	public List<MobileElement> getListPackage() {
		return listPackage;
	}

	@AndroidFindBy(id = "product_discount_price")
	private MobileElement sellingPricePackage;

	public MobileElement getSellingPricePackage() {
		return sellingPricePackage;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='SORT & FILTER']")
	private MobileElement btnFilter;

	public MobileElement getBtnFilter() {
		return btnFilter;
	}

	@AndroidFindBy(id = "tv_title")
	private MobileElement titlePage;

	public MobileElement getTitlePage() {
		return titlePage;
	}

	@AndroidFindBy(id = "add_code")
	private MobileElement textAddPromocode;

	public MobileElement getTextAddPromocode() {
		return textAddPromocode;
	}

	@AndroidFindBy(id = "final_price")
	private MobileElement finalPrice;

	public MobileElement getFinalPrice() {
		return finalPrice;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"BUY NOW\"]")
	private MobileElement btnBuyNow;

	public MobileElement getBtnBuyNow() {
		return btnBuyNow;
	}

	@AndroidFindBy(id = "wts_share")
	private MobileElement iconShare;

	public MobileElement getIconShare() {
		return iconShare;
	}

	/*--------------------------------LiveClass Recorded Session--------------------------*/

	@AndroidFindBy(xpath = "//*[@text='Turn on the Notifications']")
	private MobileElement turnOnNotificationTitle;

	public MobileElement getTurnOnNotificationTitle() {
		return turnOnNotificationTitle;
	}

	@AndroidFindBy(id = "tv_notify_no")
	private MobileElement notifyNoBtn;

	public MobileElement getNotifyNoBtn() {
		return notifyNoBtn;
	}

	@AndroidFindBy(id = "tv_notify_yes")
	private MobileElement notifyYesBtn;

	public MobileElement getNotifyYesBtn() {
		return notifyYesBtn;
	}

	@AndroidFindBy(accessibility = "Description")
	private MobileElement liveClassDescriptionBtn;

	public MobileElement getLiveClassDescriptionBtn() {
		return liveClassDescriptionBtn;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='About course']")
	private MobileElement liveClassDescriptionBottomSheetTitle;

	public MobileElement getLiveClassDescriptionBottomSheetTitle() {
		return liveClassDescriptionBottomSheetTitle;
	}

	@AndroidFindBy(id = "close")
	private MobileElement liveClassDescriptionBottomSheetCloseBtn;

	public MobileElement getLiveClassDescriptionBottomSheetCloseBtn() {
		return liveClassDescriptionBottomSheetCloseBtn;
	}

	@AndroidFindBy(accessibility = "Share")
	private MobileElement liveClassShareBtn;

	public MobileElement getLiveClassShareBtn() {
		return liveClassShareBtn;
	}

	@AndroidFindBy(id = "android:id/resolver_list")
	private MobileElement shareScreen;

	public MobileElement getShareScreen() {
		return shareScreen;
	}

	@AndroidFindBy(id = "playerview_state")
	private MobileElement playerViewBtn;

	public MobileElement getPlayerViewBtn() {
		return playerViewBtn;
	}

	@AndroidFindBy(id = "changeModeSwitchCompat")
	private MobileElement notifyMeToggleBtn;

	public MobileElement getNotifyMeToggleBtn() {
		return notifyMeToggleBtn;
	}

	@AndroidFindBy(id = "txt_stream_option")
	private MobileElement streamOptionBtn;

	public MobileElement getStreamOptionBtn() {
		return streamOptionBtn;
	}

	@AndroidFindBy(id = "playback_speed")
	private MobileElement playBackSpeedBtn;

	public MobileElement getPlayBackSpeedBtn() {
		return playBackSpeedBtn;
	}

	@AndroidFindBy(id = "exo_fullscreen_icon")
	private MobileElement fullScreenBtn;

	public MobileElement getFullScreenBtn() {
		return fullScreenBtn;
	}

	@AndroidFindBy(id = "exo_fullscreen_icon_land")
	private MobileElement fullScreenLandBtn;

	public MobileElement getFullScreenLandBtn() {
		return fullScreenLandBtn;
	}

	@AndroidFindBy(id = "exo_rew")
	private MobileElement videoReverseBtn;

	public MobileElement getVideoReverseBtn() {
		return videoReverseBtn;
	}

	@AndroidFindBy(id = "exo_ffwd")
	private MobileElement videoForwardBtn;

	public MobileElement getVideoForwardBtn() {
		return videoForwardBtn;
	}

	@AndroidFindBy(id = "exo_play")
	private MobileElement videoPlayBtn;

	public MobileElement getVideoPlayBtn() {
		return videoPlayBtn;
	}

	@AndroidFindBy(id = "exo_pause")
	private MobileElement videoPauseBtn;

	public MobileElement getVideoPauseBtn() {
		return videoPauseBtn;
	}

	@AndroidFindBy(id = "exo_position")
	private MobileElement videoPositiontext;

	public MobileElement getVideoPositiontext() {
		return videoPositiontext;
	}

	@AndroidFindBy(id = "exo_duration")
	private MobileElement videoDurationText;

	public MobileElement getVideoDurationText() {
		return videoDurationText;
	}

	@AndroidFindBy(id = "exo_progress")
	private MobileElement videoProgressBar;

	public MobileElement getVideoProgressBar() {
		return videoProgressBar;
	}

	@AndroidFindBy(id = "state_layout")
	private List<MobileElement> videoDownloadIconList;

	public List<MobileElement> getVideoDownloadIconList() {
		return videoDownloadIconList;
	}

	@AndroidFindBy(xpath = "//*[@text='Select Quality']")
	private MobileElement selectDownloadQualityTitle;

	public MobileElement getSelectDownloadQualityTitle() {
		return selectDownloadQualityTitle;
	}

	@AndroidFindBy(id = "android:id/text1")
	private List<MobileElement> downloadQualityList;

	public List<MobileElement> getDownloadQualityList() {
		return downloadQualityList;
	}

	// @AndroidFindBy(id = "android:id/button1")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='DOWNLOAD']")
	private MobileElement downloadBtn;

	public MobileElement getDownloadBtn() {
		return downloadBtn;
	}

	@AndroidFindBy(xpath = "//*[@text='DELETE']")
	private MobileElement deleteBtn;

	public MobileElement getDeleteBtn() {
		return deleteBtn;
	}

	@AndroidFindBy(id = "progressBar")
	private MobileElement pageLoaderIcon;

	public MobileElement getPageLoaderIcon() {
		return pageLoaderIcon;
	}

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='exo_subtitles']/child::android.view.View")
	private MobileElement videoPlaySection;

	public MobileElement getVideoPlaySection() {
		return videoPlaySection;
	}

	@AndroidFindBy(id = "determinate")
	private MobileElement downloadDeterminateBtn;

	public MobileElement getDownloadDeterminateBtn() {
		return downloadDeterminateBtn;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='video_course_iv']/parent::android.view.ViewGroup")
	private List<MobileElement> purchasedVideoList;

	public List<MobileElement> getPurchasedVideoList() {
		return purchasedVideoList;
	}

	@AndroidFindBy(id = "video_course_iv")
	private List<MobileElement> videoCourseImgList;

	public List<MobileElement> getVideoCourseImgList() {
		return videoCourseImgList;
	}

	@AndroidFindBy(id = "video_course_title_head")
	private List<MobileElement> videoCourseTitleList;

	public List<MobileElement> getVideoCourseTitleList() {
		return videoCourseTitleList;
	}

	@AndroidFindBy(id = "video_course_sub_text")
	private List<MobileElement> videoCourseSubTextList;

	public List<MobileElement> getVideoCourseSubTextList() {
		return videoCourseSubTextList;
	}

	@AndroidFindBy(id = "watch_time")
	private List<MobileElement> watchTimerList;

	public List<MobileElement> getWatchTimerList() {
		return watchTimerList;
	}

	@AndroidFindBy(id = "handouts_frame")
	private MobileElement handOutsIcon;

	public MobileElement getHandOutsIcon() {
		return handOutsIcon;
	}

	@AndroidFindBy(id = "handout_option")
	private MobileElement videoScreenHandOutsBtn;

	public MobileElement getVideoScreenHandOutsBtn() {
		return videoScreenHandOutsBtn;
	}

	@AndroidFindBy(xpath = "//*[@text='Handouts']")
	private MobileElement handOutsTitle;

	public MobileElement getHandOutsTitle() {
		return handOutsTitle;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Handouts']/following-sibling::android.widget.ScrollView/android.view.View/android.widget.ImageView[2]")
	private List<MobileElement> handOutDownLoadBtn;

	public List<MobileElement> getHandOutDownLoadBtn() {
		return handOutDownLoadBtn;
	}

	@AndroidFindBy(id = "btnDownload")
	private MobileElement handOutPdfDownLoadBtn;

	public MobileElement getHandOutPdfDownLoadBtn() {
		return handOutPdfDownLoadBtn;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Handouts']/following-sibling::android.widget.ScrollView/android.view.View")
	private List<MobileElement> handOutCell;

	public List<MobileElement> getHandOutCell() {
		return handOutCell;
	}

	@AndroidFindBy(id = "pdfView")
	private MobileElement pdfView;

	public MobileElement getPdfView() {
		return pdfView;
	}

	@AndroidFindBy(id = "btnExit")
	private MobileElement pdfExitBtn;

	public MobileElement getPdfExitBtn() {
		return pdfExitBtn;
	}

	@AndroidFindBy(id = "ebooks")
	private MobileElement ebookIcon;

	public MobileElement getEbookIcon() {
		return ebookIcon;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Copyright Statement']")
	private MobileElement copyRightPopUpTitle;

	public MobileElement getCopyRightPopUpTitle() {
		return copyRightPopUpTitle;
	}

	@AndroidFindBy(id = "save")
	private MobileElement saveBtn;

	public MobileElement getSaveBtn() {
		return saveBtn;
	}

	@AndroidFindBy(xpath = "//*[@resource-id='viewerContainer']")
	private MobileElement ebookViewerContainer;

	public MobileElement getEbookViewerContainer() {
		return ebookViewerContainer;
	}

	@AndroidFindBy(id = "fbMessage")
	private MobileElement toastMessage;

	public MobileElement getToastMessage() {
		return toastMessage;
	}

	@AndroidFindBy(id = "com.adda247.app:id/txt_gotit")
	private MobileElement gotItBtn;

	public MobileElement getGotItBtn() {
		return gotItBtn;
	}

	@AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
	private MobileElement permissionAllowBtn;

	public MobileElement getPermissionAllowBtn() {
		return permissionAllowBtn;
	}

	@AndroidFindBy(id = "state_layout")
	private MobileElement videoDownloadIcon;

	public MobileElement getVideoDownloadIcon() {
		return videoDownloadIcon;
	}

	@AndroidFindBy(xpath = "//*[@text='CONFIRM']")
	private MobileElement confirmBtn;

	public MobileElement getConfirmBtn() {
		return confirmBtn;
	}

	@AndroidFindBy(id = "playing_video_title")
	private MobileElement playingVideoTitle;

	public MobileElement getPlayingVideoTitle() {
		return playingVideoTitle;
	}

	@AndroidFindBy(id = "com.adda247.app:id/join_live")
	private MobileElement joinLiveBtn;

	public MobileElement getJoinLiveBtn() {
		return joinLiveBtn;
	}

	@AndroidFindBy(id = "android:id/ok")
	private MobileElement liveClassScreenGotItBtn;

	public MobileElement getLiveClassScreenGotItBtn() {
		return liveClassScreenGotItBtn;
	}

	@AndroidFindBy(xpath = "//*[@text='JOIN']")
	private MobileElement joinBtn;

	public MobileElement getJoinBtn() {
		return joinBtn;
	}

	/*------------------------------------LIVE CLASS SCREEN----------------------*/

	@AndroidFindBy(id = "ivWindowResizer")
	private MobileElement windowReSizeBtn;

	public MobileElement getWindowReSizeBtn() {
		return windowReSizeBtn;
	}

	@AndroidFindBy(id = "smallWindowResizerBtn")
	private MobileElement smallWindowSizeBtn;

	public MobileElement getSmallWindowSizeBtn() {
		return smallWindowSizeBtn;
	}

	@AndroidFindBy(id = "ivSettings")
	private MobileElement qualitySettingBtn;

	public MobileElement getQualitySettingBtn() {
		return qualitySettingBtn;
	}

	@AndroidFindBy(id = "ivChat")
	private MobileElement chatWindowBtn;

	public MobileElement getChatWindowBtn() {
		return chatWindowBtn;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'will start soon')]")
	private MobileElement liveClassStartSoonText;

	public MobileElement getLiveClassStartSoonText() {
		return liveClassStartSoonText;
	}

	@AndroidFindBy(id = "loadingProgress")
	private MobileElement liveClassLoadingImg;

	public MobileElement getLiveClassLoadingImg() {
		return liveClassLoadingImg;
	}

	@AndroidFindBy(id = "placeholderTV")
	private MobileElement placeHolderImg;

	public MobileElement getPlaceHolderImg() {
		return placeHolderImg;
	}

	@AndroidFindBy(id = "chatMessage")
	private List<MobileElement> chatMessageText;

	public List<MobileElement> getChatMessageText() {
		return chatMessageText;
	}

	@AndroidFindBy(id = "inputText")
	private MobileElement messageSendTextField;

	public MobileElement getMessageSendTextField() {
		return messageSendTextField;
	}

	@AndroidFindBy(id = "inputSend")
	private MobileElement messageSendBtn;

	public MobileElement getMessageSendBtn() {
		return messageSendBtn;
	}

	@AndroidFindBy(xpath = "//*[@text='Video Quality']")
	private MobileElement videoQualitySheetTitle;

	public MobileElement getVideoQualitySheetTitle() {
		return videoQualitySheetTitle;
	}

	@AndroidFindBy(id = "radioBtn")
	private List<MobileElement> videoQualityRadioBtn;

	public List<MobileElement> getVideoQualityRadioBtn() {
		return videoQualityRadioBtn;
	}

	@AndroidFindBy(id = "optionTitle")
	private List<MobileElement> videoQualityOptionTitle;

	public List<MobileElement> getVideoQualityOptionTitle() {
		return videoQualityOptionTitle;
	}

	@AndroidFindBy(id = "reportIssue")
	private MobileElement reportIssueBtn;

	public MobileElement getReportIssueBtn() {
		return reportIssueBtn;
	}

	@AndroidFindBy(id = "tv_report")
	private MobileElement reportPopUpTitle;

	public MobileElement getReportPopUpTitle() {
		return reportPopUpTitle;
	}

	@AndroidFindBy(id = "rb_issue")
	private List<MobileElement> reportOptionList;

	public List<MobileElement> getReportOptionList() {
		return reportOptionList;
	}

	@AndroidFindBy(id = "btn_cancel")
	private MobileElement reportPopUpCancelBtn;

	public MobileElement getReportPopUpCancelBtn() {
		return reportPopUpCancelBtn;
	}

	@AndroidFindBy(id = "btn_ok")
	private MobileElement reportPopUpOkBtn;

	public MobileElement getReportPopUpOkBtn() {
		return reportPopUpOkBtn;
	}

	@AndroidFindBy(id = "com.adda247.app:id/pinChatDropdown")
	private MobileElement pinChatDropDown;

	public MobileElement getPinChatDropDown() {
		return pinChatDropDown;
	}

	@AndroidFindBy(id = "com.adda247.app:id/pinnedMsgCounterTV")
	private MobileElement pinnedMsgCountText;

	public MobileElement getPinnedMsgCountText() {
		return pinnedMsgCountText;
	}

	@AndroidFindBy(id = "com.adda247.app:id/pinMessagesRv")
	private MobileElement pinMessage;

	public MobileElement getPinMessage() {
		return pinMessage;
	}

	@AndroidFindBy(id = "replyPrivatelyTitle")
	private MobileElement replyPrivatelyTitle;

	public MobileElement getReplyPrivatelyTitle() {
		return replyPrivatelyTitle;
	}

	@AndroidFindBy(id = "hangOutsView")
	private MobileElement handOutsView;

	public MobileElement getHandOutsView() {
		return handOutsView;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='hangOutsView']/preceding-sibling::android.widget.TextView[@resource-id='chatMessage']")
	private MobileElement handOutsCommentText;

	public MobileElement getHandOutsCommentText() {
		return handOutsCommentText;
	}

	@AndroidFindBy(id = "ivBack")
	private MobileElement liveClassBackBtn;

	public MobileElement getLiveClassBackBtn() {
		return liveClassBackBtn;
	}

	@AndroidFindBy(id = "com.adda247.app:id/titlePrivateMessage")
	private MobileElement privateModeEnableTxt;

	public MobileElement getPrivateModeEnableTxt() {
		return privateModeEnableTxt;
	}

	@AndroidFindBy(id = "dummyRadioButton")
	private List<MobileElement> pollRadioBtn;

	public List<MobileElement> getPollRadioBtn() {
		return pollRadioBtn;
	}

	@AndroidFindBy(xpath = "//*[@resource-id='vc-mobile-controls-cont']/descendant::android.widget.Button[3]")
	private MobileElement liveClassExitBtn;

	public MobileElement getLiveClassExitBtn() {
		return liveClassExitBtn;
	}

	@AndroidFindBy(id = "doubtsSectionBtn")
	private MobileElement liveClassDoubtBtn;

	public MobileElement getLiveClassDoubtBtn() {
		return liveClassDoubtBtn;
	}

	@AndroidFindBy(id = "ivDoubts")
	private MobileElement doubtScreenEmptyImg;

	public MobileElement getDoubtScreenEmptyImg() {
		return doubtScreenEmptyImg;
	}

	@AndroidFindBy(id = "doubtsDescription")
	private MobileElement doubtScreenEmptyDesc;

	public MobileElement getDoubtScreenEmptyDesc() {
		return doubtScreenEmptyDesc;
	}

	@AndroidFindBy(id = "inputDoubts")
	private MobileElement doubtImageUploadIcon;

	public MobileElement getDoubtImageUploadIcon() {
		return doubtImageUploadIcon;
	}

	@AndroidFindBy(id = "quizTitle")
	private MobileElement quizTitle;

	public MobileElement getQuizTitle() {
		return quizTitle;
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

	@AndroidFindBy(id = "com.android.chrome:id/positive_button")
	private MobileElement allowBtn;

	public MobileElement getAllowBtn() {
		return allowBtn;
	}

	@AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_one_time_button")
	private MobileElement onlyThisTimeBtn;

	public MobileElement getOnlyThisTimeBtn() {
		return onlyThisTimeBtn;
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

	/*------------------------------LiveClassCertification tag-------------------------*/

	@AndroidFindBy(id = "class_number")
	private List<MobileElement> classTag;

	public List<MobileElement> getClassTag() {
		return classTag;
	}

	@AndroidFindBy(xpath = "//*[contains(@resource-id,'certificate_card')]/preceding-sibling::*[contains(@resource-id,'test_number')]")
	private MobileElement certificateLabelClassTag;

	public MobileElement getCertificateLabelClassTag() {
		return certificateLabelClassTag;
	}

	@AndroidFindBy(id = "certificate_cta")
	private MobileElement certificateClassCommingSoonBtn;

	public MobileElement getCertificateClassCommingSoonBtn() {
		return certificateClassCommingSoonBtn;
	}

	@AndroidFindBy(id = "progress_tv")
	private MobileElement progressBarTextValue;

	public MobileElement getProgressBarTextValue() {
		return progressBarTextValue;
	}

	@AndroidFindBy(id = "progress_percentage_tv")
	private MobileElement progressBarPercentage;

	public MobileElement getProgressBarPercentage() {
		return progressBarPercentage;
	}

	@AndroidFindBy(id = "video_status")
	private List<MobileElement> liveClassStatusTag;

	public List<MobileElement> getLiveClassStatusTag() {
		return liveClassStatusTag;
	}

	@AndroidFindBy(id = "certificateProgressBar")
	private MobileElement certificateProgressBar;

	public MobileElement getCertificateProgressBar() {
		return certificateProgressBar;
	}

	@AndroidFindBy(xpath = "//*[@text='Candidate details for Certificate']")
	private MobileElement candidateDetailsPopUpTitle;

	public MobileElement getCandidateDetailsPopUpTitle() {
		return candidateDetailsPopUpTitle;
	}

	@AndroidFindBy(className = "android.widget.Button")
	private List<MobileElement> candidateDetailsBtn;

	public List<MobileElement> getCandidateDetailsBtn() {
		return candidateDetailsBtn;
	}

	@AndroidFindBy(id = "certificate_type")
	private MobileElement certificateTitle;

	public MobileElement getCertificateTitle() {
		return certificateTitle;
	}

	@AndroidFindBy(xpath = "//*[@text='City']/preceding-sibling::android.widget.EditText")
	private MobileElement fathersNameTextField;

	public MobileElement getFathersNameTextField() {
		return fathersNameTextField;
	}

	@AndroidFindBy(xpath = "//*[@text='City']/following-sibling::android.widget.EditText")
	private MobileElement cityTextField;

	public MobileElement getCityTextField() {
		return cityTextField;
	}

	@AndroidFindBy(xpath = "//*[contains(@text,'Mother')]/following-sibling::android.widget.EditText")
	private MobileElement mothersNameTextField;

	public MobileElement getMothersNameTextField() {
		return mothersNameTextField;
	}

	@AndroidFindBy(xpath = "//*[@text='State']/following-sibling::android.widget.EditText")
	private MobileElement stateTextField;

	public MobileElement getStateTextField() {
		return stateTextField;
	}

	@AndroidFindBy(xpath = "//*[@text='Place']/following-sibling::android.widget.EditText")
	private MobileElement placeTextField;

	public MobileElement getPlaceTextField() {
		return placeTextField;
	}

	@AndroidFindBy(xpath = "//*[@text='Name']/following-sibling::android.widget.EditText")
	private MobileElement candidateNameTextField;

	public MobileElement getCandidateNameTextField() {
		return candidateNameTextField;
	}

	@AndroidFindBy(xpath = "//*[@text='Category']/following-sibling::android.widget.EditText")
	private MobileElement categoryNameTextField;

	public MobileElement getCategoryNameTextField() {
		return categoryNameTextField;
	}

	@AndroidFindBy(xpath = "//*[@text='College']/following-sibling::android.widget.EditText")
	private MobileElement collegeTextField;

	public MobileElement getCollegeTextField() {
		return collegeTextField;
	}

	@AndroidFindBy(xpath = "//*[@text='Address']/following-sibling::android.widget.EditText")
	private MobileElement addressTextField;

	public MobileElement getAddressTextField() {
		return addressTextField;
	}

	@AndroidFindBy(xpath = "//*[@text='OK']")
	private MobileElement okBtn;

	public MobileElement getOkBtn() {
		return okBtn;
	}

	/*-------LiveClass UI-------------------------*/

	@AndroidFindBy(id = "play_pause_button")
	private MobileElement liveScreenPauseBtn;

	public MobileElement getLiveScreenPauseBtn() {
		return liveScreenPauseBtn;
	}

	@AndroidFindBy(id = "forward_button")
	private MobileElement liveScreenForwardBtn;

	public MobileElement getLiveScreenForwardBtn() {
		return liveScreenForwardBtn;
	}

	@AndroidFindBy(id = "backward_button")
	private MobileElement liveScreenBackWardBtn;

	public MobileElement getLiveScreenBackWardBtn() {
		return liveScreenBackWardBtn;
	}

	@AndroidFindBy(id = "liveTag")
	private MobileElement liveTag;

	public MobileElement getLiveTag() {
		return liveTag;
	}

	@AndroidFindBy(id = "handoutsSectionBtn")
	private MobileElement liveClassHandOutIcon;

	public MobileElement getLiveClassHandOutIcon() {
		return liveClassHandOutIcon;
	}

	@AndroidFindBy(id = "handoutsTitle")
	private List<MobileElement> liveClassHandOutTitle;

	public List<MobileElement> getLiveClassHandOutTitle() {
		return liveClassHandOutTitle;
	}

	@AndroidFindBy(id = "openHandouts")
	private MobileElement handOutOpenBtn;

	public MobileElement getHandOutOpenBtn() {
		return handOutOpenBtn;
	}

	@AndroidFindBy(id = "handRaiseSectionBtn")
	private MobileElement handRaiseIcon;

	public MobileElement getHandRaiseIcon() {
		return handRaiseIcon;
	}

	@AndroidFindBy(id = "handRaiseButton")
	private MobileElement handRaiseBtn;

	public MobileElement getHandRaiseBtn() {
		return handRaiseBtn;
	}

	@AndroidFindBy(id = "ivHandRaise")
	private MobileElement handRaiseImage;

	public MobileElement getHandRaiseImage() {
		return handRaiseImage;
	}

	@AndroidFindBy(id = "avatarBgStroke")
	private MobileElement studentAvatar;

	public MobileElement getStudentAvatar() {
		return studentAvatar;
	}

	// --------------------Free Live Class------------------------

	@AndroidFindBy(id = "packageTitle")
	private List<MobileElement> titleClass;

	public List<MobileElement> getListTitleClass() {
		return titleClass;
	}

	@AndroidFindBy(id = "statusBtn")
	private List<MobileElement> statusBtn;

	public List<MobileElement> getListStatusBtn() {
		return statusBtn;
	}

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Free Live Classes' and @resource-id='com.adda247.app:id/title']")
    private MobileElement freeLiveClassTab;

    public MobileElement getFreeLiveClassTab() {
        return freeLiveClassTab;
    }

	@AndroidFindBy(id = "comingSoon")
	private List<MobileElement> btnComingSoon;

	public List<MobileElement> getListBtnComingSoon() {
		return btnComingSoon;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Automation Class:\")]")
	private MobileElement classTitlePdp;

	public MobileElement getClassTitlePdp() {
		return classTitlePdp;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"JOIN NOW\")]")
	private MobileElement joinNowPdp;

	public MobileElement getJoinNowPdp() {
		return joinNowPdp;
	}

	@AndroidFindBy(id = "com.adda247.app:id/calenderSelector")
	private MobileElement btnCalender;

	public MobileElement getBtnCalender() {
		return btnCalender;
	}

	/*-----------------------------------------------PDF button Section-------------------------*/

	@AndroidFindBy(xpath = "//*[@text='Class Notes']")
	private MobileElement classNotesTitle;

	public MobileElement getClassNotesTitle() {
		return classNotesTitle;
	}

	@AndroidFindBy(xpath = "//*[@text='Class Notes']/following-sibling::android.view.View")
	private List<MobileElement> pdfCell;

	public List<MobileElement> getPdfCell() {
		return pdfCell;
	}

	@AndroidFindBy(xpath = "//*[@resource-id='viewerContainer']")
	private MobileElement pdfScreen;

	public MobileElement getPdfScreen() {
		return pdfScreen;
	}

	/*--------LiveClass FeedBack screen----------*/

	@AndroidFindBy(id = "com.adda247.app:id/tv_class_detail")
	private MobileElement liveClassEndTitle;

	@AndroidFindBy(id = "com.adda247.app:id/rb_rate")
	private MobileElement teacherRatingIcon;

	@AndroidFindBy(id = "com.adda247.app:id/rb_rate_content")
	private MobileElement contentRatingIcon;

	@AndroidFindBy(id = "com.adda247.app:id/rb_rate_interface")
	private MobileElement interfaceRatingIcon;

	@AndroidFindBy(id = "com.adda247.app:id/feedbackET")
	private MobileElement liveClassFeedBackTextField;

	@AndroidFindBy(id = "com.adda247.app:id/submitBtn")
	private MobileElement feedBackSubmitBtn;

	public MobileElement getLiveClassEndTitle() {
		return liveClassEndTitle;
	}

	public MobileElement getTeacherRatingIcon() {
		return teacherRatingIcon;
	}

	public MobileElement getContentRatingIcon() {
		return contentRatingIcon;
	}

	public MobileElement getInterfaceRatingIcon() {
		return interfaceRatingIcon;
	}

	public MobileElement getLiveClassFeedBackTextField() {
		return liveClassFeedBackTextField;
	}

	public MobileElement getFeedBackSubmitBtn() {
		return feedBackSubmitBtn;
	}

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"More Free Classes\"]")
    private MobileElement MoreFreeClassBtn;

    public MobileElement getMoreFreeClassBtn() {
        return MoreFreeClassBtn;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Watch for free\"]")
    private MobileElement WatchForFreeBtn;

    public MobileElement getWatchForFreeBtn() {
        return WatchForFreeBtn;
    }
}
