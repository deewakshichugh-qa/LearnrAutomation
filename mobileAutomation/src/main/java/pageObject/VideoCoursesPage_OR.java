package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class VideoCoursesPage_OR {

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

	/*----------------------------------------Video section----------------------------------*/

	@AndroidFindAll({ @AndroidBy(xpath = "//*[contains(@text,'Videos')]"),
			@AndroidBy(xpath = "//*[contains(@text,'YouTube Classes')]") })
	private MobileElement videoSectionTitle;

	@AndroidFindBy(accessibility = "Navigate up")
	private MobileElement backBtn;

	@AndroidFindBy(id = "action_language_change")
	private MobileElement videoSectionFilterBtn;

	@AndroidFindBy(id = "search_filter")
	private MobileElement searchTextField;

	@AndroidFindBy(id = "search_src_text")
	private MobileElement searchTextEnterField;

	@AndroidFindBy(id = "back_icon")
	private MobileElement searchTextEnterFieldBackBtn;

	@AndroidFindBy(accessibility = "Clear query")
	private MobileElement clearTextBtn;

	@AndroidFindBy(id = "recent_header")
	private MobileElement recentTab;

	@AndroidFindBy(id = "title")
	private List<MobileElement> videoTitleList;

	@AndroidFindBy(id = "tv_time")
	private List<MobileElement> videoTimeText;

	@AndroidFindBy(id = "favourite")
	private List<MobileElement> dotIconList;

	@AndroidFindBy(id = "tv_bookmark_title")
	private MobileElement addBookMarkBtn;

	@AndroidFindBy(id = "lay_share")
	private MobileElement shareBtn;

	@AndroidFindBy(id = "fbMessage")
	private MobileElement toastMessage;

	@AndroidFindBy(xpath = "//*[@text='Recent Videos']")
	private MobileElement recentVideoPageTitle;

	@AndroidFindBy(id = "lay_thumb")
	private List<MobileElement> videoList;

	@AndroidFindBy(id = "liveNow")
	private List<MobileElement> videoStatusText;

	@AndroidFindBy(id = "tv_Likes")
	private List<MobileElement> videoLikeText;

	@AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@resource-id,'toolbar')]/android.widget.TextView")
	private MobileElement searchTopicTitle;

	@AndroidFindBy(id = "android:id/resolver_list")
	private MobileElement shareScreen;

	@AndroidFindBy(id = "text")
	private MobileElement filterInstructionText;

	@AndroidFindBy(id = "txt_count")
	private MobileElement viewAllTopicSuggestionBtn;

	public MobileElement getViewAllTopicSuggestionBtn() {
		return viewAllTopicSuggestionBtn;
	}

	public MobileElement getFilterInstructionText() {
		return filterInstructionText;
	}

	public MobileElement getVideoSectionTitle() {
		return videoSectionTitle;
	}

	public MobileElement getBackBtn() {
		return backBtn;
	}

	public MobileElement getVideoSectionFilterBtn() {
		return videoSectionFilterBtn;
	}

	public MobileElement getSearchTextField() {
		return searchTextField;
	}

	public MobileElement getSearchTextEnterField() {
		return searchTextEnterField;
	}

	public MobileElement getSearchTextEnterFieldBackBtn() {
		return searchTextEnterFieldBackBtn;
	}

	public MobileElement getClearTextBtn() {
		return clearTextBtn;
	}

	public MobileElement getRecentTab() {
		return recentTab;
	}

	public List<MobileElement> getVideoTitleList() {
		return videoTitleList;
	}

	public List<MobileElement> getVideoTimeText() {
		return videoTimeText;
	}

	public List<MobileElement> getDotIconList() {
		return dotIconList;
	}

	public MobileElement getAddBookMarkBtn() {
		return addBookMarkBtn;
	}

	public MobileElement getShareBtn() {
		return shareBtn;
	}

	public MobileElement getToastMessage() {
		return toastMessage;
	}

	public MobileElement getRecentVideoPageTitle() {
		return recentVideoPageTitle;
	}

	public List<MobileElement> getVideoList() {
		return videoList;
	}

	public List<MobileElement> getVideoStatusText() {
		return videoStatusText;
	}

	public List<MobileElement> getVideoLikeText() {
		return videoLikeText;
	}

	public MobileElement getSearchTopicTitle() {
		return searchTopicTitle;
	}

	public MobileElement getShareScreen() {
		return shareScreen;
	}

	/*-----------------------------------Purchased Video Course-----------------------------*/

	@AndroidFindBy(accessibility = "Share")
	private MobileElement videoShareBtn;

	@AndroidFindBy(id = "playerview_state")
	private MobileElement playerViewState;

	@AndroidFindBy(id = "txt_stream_option")
	private MobileElement streamOptionBtn;

	@AndroidFindBy(id = "playback_speed")
	private MobileElement playBackSpeedBtn;

	@AndroidFindBy(id = "exo_fullscreen_icon")
	private MobileElement fullScreenBtn;

	@AndroidFindBy(id = "exo_fullscreen_icon_land")
	private MobileElement fullScreenLandBtn;

	@AndroidFindBy(id = "exo_rew")
	private MobileElement videoReverseBtn;

	@AndroidFindBy(id = "exo_ffwd")
	private MobileElement videoForwardBtn;

	@AndroidFindBy(id = "exo_play")
	private MobileElement videoPlayBtn;

	@AndroidFindBy(id = "exo_pause")
	private MobileElement videoPauseBtn;

	@AndroidFindBy(id = "exo_position")
	private MobileElement videoPositiontext;

	@AndroidFindBy(id = "exo_duration")
	private MobileElement videoDurationText;

	@AndroidFindBy(id = "exo_progress")
	private MobileElement videoProgressBar;

	@AndroidFindBy(xpath = "//*[contains(@resource-id,'video_course_iv')]/parent::android.view.ViewGroup")
	private List<MobileElement> purchasedVideoList;

	@AndroidFindBy(id = "video_course_iv")
	private List<MobileElement> videoCourseImgList;

	@AndroidFindBy(id = "video_course_iv_icon")
	private List<MobileElement> videoCourseImgIconList;

	@AndroidFindBy(id = "video_course_title_head")
	private List<MobileElement> videoCourseTitleList;

	@AndroidFindBy(id = "video_course_sub_text")
	private List<MobileElement> videoCourseSubTextList;

	@AndroidFindBy(id = "watch_time")
	private List<MobileElement> watchTimerList;

	@AndroidFindBy(id = "description")
	private List<MobileElement> videoDescriptionIconList;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='About course']")
	private MobileElement videoDescriptionBottomSheetTitle;

	@AndroidFindBy(xpath = "close")
	private MobileElement videoDescriptionBottomSheetCloseBtn;

	@AndroidFindBy(id = "state_layout")
	private List<MobileElement> videoDownloadIconList;

	@AndroidFindBy(id = "ftue_desc")
	private MobileElement downloadVideoInstruction;

	@AndroidFindBy(id = "txt_gotit")
	private MobileElement gotItBtn;

	@AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
	private MobileElement permissionAllowBtn;

	@AndroidFindBy(xpath = "//*[@text='Select Quality']")
	private MobileElement selectDownloadQualityTitle;

	@AndroidFindBy(id = "android:id/text1")
	private List<MobileElement> downloadQualityList;

	// @AndroidFindBy(id = "android:id/button1")
	@AndroidFindBy(xpath = "//android.widget.Button[@text='DOWNLOAD']")
	private MobileElement downloadBtn;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='DELETE']")
	private MobileElement deleteBtn;

	@AndroidFindBy(id = "progressBar")
	private MobileElement pageLoaderIcon;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[contains(@resource-id,'exo_subtitles')]/child::android.view.View")
	private MobileElement videoPlaySection;

	@AndroidFindBy(id = "determinate")
	private MobileElement downloadDeterminateBtn;

	@AndroidFindBy(id = "playing_video_title")
	private MobileElement playingVideoTitle;

	@AndroidFindBy(xpath = "//*[@text='CONFIRM']")
	private MobileElement confirmBtn;

	public MobileElement getConfirmBtn() {
		return confirmBtn;
	}

	public MobileElement getPlayingVideoTitle() {
		return playingVideoTitle;
	}

	public MobileElement getDownloadDeterminateBtn() {
		return downloadDeterminateBtn;
	}

	public MobileElement getVideoPlaySection() {
		return videoPlaySection;
	}

	public MobileElement getPageLoaderIcon() {
		return pageLoaderIcon;
	}

	public MobileElement getVideoShareBtn() {
		return videoShareBtn;
	}

	public MobileElement getPlayerViewState() {
		return playerViewState;
	}

	public MobileElement getStreamOptionBtn() {
		return streamOptionBtn;
	}

	public MobileElement getPlayBackSpeedBtn() {
		return playBackSpeedBtn;
	}

	public MobileElement getFullScreenBtn() {
		return fullScreenBtn;
	}

	public MobileElement getFullScreenLandBtn() {
		return fullScreenLandBtn;
	}

	public MobileElement getVideoReverseBtn() {
		return videoReverseBtn;
	}

	public MobileElement getVideoForwardBtn() {
		return videoForwardBtn;
	}

	public MobileElement getVideoPlayBtn() {
		return videoPlayBtn;
	}

	public MobileElement getVideoPauseBtn() {
		return videoPauseBtn;
	}

	public MobileElement getVideoPositiontext() {
		return videoPositiontext;
	}

	public MobileElement getVideoDurationText() {
		return videoDurationText;
	}

	public MobileElement getVideoProgressBar() {
		return videoProgressBar;
	}

	public List<MobileElement> getPurchasedVideoList() {
		return purchasedVideoList;
	}

	public List<MobileElement> getVideoCourseImgList() {
		return videoCourseImgList;
	}

	public List<MobileElement> getVideoCourseImgIconList() {
		return videoCourseImgIconList;
	}

	public List<MobileElement> getVideoCourseTitleList() {
		return videoCourseTitleList;
	}

	public List<MobileElement> getVideoCourseSubTextList() {
		return videoCourseSubTextList;
	}

	public List<MobileElement> getWatchTimerList() {
		return watchTimerList;
	}

	public List<MobileElement> getVideoDescriptionIconList() {
		return videoDescriptionIconList;
	}

	public MobileElement getVideoDescriptionBottomSheetTitle() {
		return videoDescriptionBottomSheetTitle;
	}

	public MobileElement getVideoDescriptionBottomSheetCloseBtn() {
		return videoDescriptionBottomSheetCloseBtn;
	}

	public List<MobileElement> getVideoDownloadIconList() {
		return videoDownloadIconList;
	}

	public MobileElement getDownloadVideoInstruction() {
		return downloadVideoInstruction;
	}

	public MobileElement getGotItBtn() {
		return gotItBtn;
	}

	public MobileElement getPermissionAllowBtn() {
		return permissionAllowBtn;
	}

	public MobileElement getSelectDownloadQualityTitle() {
		return selectDownloadQualityTitle;
	}

	public List<MobileElement> getDownloadQualityList() {
		return downloadQualityList;
	}

	public MobileElement getDownloadBtn() {
		return downloadBtn;
	}

	public MobileElement getDeleteBtn() {
		return deleteBtn;
	}

	@AndroidFindBy(xpath = "//*[contains(@text,'Done')]")
	private MobileElement doneCoachMarkBtn;

	public MobileElement doneCoachMarkBtn() {
		return doneCoachMarkBtn;
	}
	
	@AndroidFindBy(xpath = "//*[contains(@resource-id,'thumbnailIV')]")
	private List<MobileElement> listOfCourses;

	public List<MobileElement> listOfCourses() {
		return listOfCourses;
	}
	
	

	/*---------------Video Quality Icon BottomSheet---------------------*/

	@AndroidFindBy(xpath = "//*[@text='Video Quality']")
	private MobileElement videoQualityTitle;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Auto']/following-sibling::android.widget.RadioButton")
	private MobileElement autoRadioBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='480p - HD']/following-sibling::android.widget.RadioButton")
	private MobileElement pixel480RadioBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='360p']/following-sibling::android.widget.RadioButton")
	private MobileElement pixel360RadioBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='240p - DATA SAVER']/following-sibling::android.widget.RadioButton")
	private MobileElement pixel240RadioBtn;

	public MobileElement getVideoQualityTitle() {
		return videoQualityTitle;
	}

	public MobileElement getAutoRadioBtn() {
		return autoRadioBtn;
	}

	public MobileElement getPixel480RadioBtn() {
		return pixel480RadioBtn;
	}

	public MobileElement getPixel360RadioBtn() {
		return pixel360RadioBtn;
	}

	public MobileElement getPixel240RadioBtn() {
		return pixel240RadioBtn;
	}

	/*--------------PlayBack Speed BottomSheet----------------*/

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Playback Speed']")
	private MobileElement playBackSpeedTitle;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='0.75x']/following-sibling::android.widget.RadioButton")
	private MobileElement speed075RadioBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='1.0x (NORMAL)']/following-sibling::android.widget.RadioButton")
	private MobileElement speedNormalRadioBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='1.25x']/following-sibling::android.widget.RadioButton")
	private MobileElement speed125RadioBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='1.5x']/following-sibling::android.widget.RadioButton")
	private MobileElement speed15RadioBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='2.0x']/following-sibling::android.widget.RadioButton")
	private MobileElement speed20RadioBtn;

	public MobileElement getPlayBackSpeedTitle() {
		return playBackSpeedTitle;
	}

	public MobileElement getSpeed075RadioBtn() {
		return speed075RadioBtn;
	}

	public MobileElement getSpeedNormalRadioBtn() {
		return speedNormalRadioBtn;
	}

	public MobileElement getSpeed125RadioBtn() {
		return speed125RadioBtn;
	}

	public MobileElement getSpeed15RadioBtn() {
		return speed15RadioBtn;
	}

	public MobileElement getSpeed20RadioBtn() {
		return speed20RadioBtn;
	}

	/*---------------------------------VideoCertification Section------------------------------*/

	@AndroidFindBy(id = "class_number")
	private List<MobileElement> videosTag;

	public List<MobileElement> getVideosTag() {
		return videosTag;
	}

	@AndroidFindBy(xpath = "//*[contains(@resource-id,'certificate_card')]/preceding-sibling::*[contains(@resource-id,'test_number')]")
	private MobileElement certificateLabelVideoTag;

	public MobileElement getCertificateLabelVideoTag() {
		return certificateLabelVideoTag;
	}

	@AndroidFindBy(id = "certificate_cta")
	private MobileElement certificateVideoWatchBtn;

	public MobileElement getCertificateVideoWatchBtn() {
		return certificateVideoWatchBtn;
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
	private List<MobileElement> videoStatusTag;

	public List<MobileElement> getVideoStatusTag() {
		return videoStatusTag;
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

	/**
	 * -------------------------------------------LCS
	 * Section----------------------------------------------------
	 **/

	@AndroidFindBy(id = "like_video")
	private MobileElement videoLikeBtn;

	public MobileElement getVideoLikeBtn() {
		return videoLikeBtn;
	}

	@AndroidFindBy(id = "like_count_video")
	private MobileElement videoLikeCount;

	public MobileElement getVideoLikeCount() {
		return videoLikeCount;
	}

	@AndroidFindBy(id = "share_link")
	private MobileElement videoDisLikeBtn;

	public MobileElement getVideoDisLikeBtn() {
		return videoDisLikeBtn;
	}

	@AndroidFindBy(id = "video_pdf")
	private MobileElement videoPdfBtn;

	public MobileElement getVideoPdfBtn() {
		return videoPdfBtn;
	}

	@AndroidFindBy(id = "video_download")
	private MobileElement videoDownloadBtn;

	public MobileElement getVideoDownloadBtn() {
		return videoDownloadBtn;
	}

	@AndroidFindBy(id = "com_txt")
	private MobileElement videoCommentSectionTitle;

	public MobileElement getVideoCommentSectionTitle() {
		return videoCommentSectionTitle;
	}

	@AndroidFindBy(id = "comment_box")
	private MobileElement videoCommentBox;

	public MobileElement getVideoCommentBox() {
		return videoCommentBox;
	}

	@AndroidFindBy(xpath = "//*[@text='Faculty']/following-sibling::android.widget.ImageView")
	private List<MobileElement> starRatingImg;

	public List<MobileElement> getStarRatingImg() {
		return starRatingImg;
	}

	@AndroidFindBy(xpath = "//*[@text='Faculty']")
	private MobileElement facultyRatingTitle;

	public MobileElement getFacultyRatingTitle() {
		return facultyRatingTitle;
	}

	@AndroidFindBy(xpath = "//*[@text='SUBMIT']/following-sibling::android.widget.Button")
	private MobileElement ratingSubmitBtn;

	public MobileElement getRatingSubmitBtn() {
		return ratingSubmitBtn;
	}

	@AndroidFindBy(xpath = "//*[@text='SKIP']/following-sibling::android.widget.Button")
	private MobileElement ratingSkipBtn;

	public MobileElement getRatingSkipBtn() {
		return ratingSkipBtn;
	}

	@AndroidFindBy(xpath = "//*[@text='Rate this Video']")
	private MobileElement ratingPopUpPageTitle;

	public MobileElement getRatingPopUpPageTitle() {
		return ratingPopUpPageTitle;
	}

	@AndroidFindBy(xpath = "//*[@text='Rate this Video']/following-sibling::android.view.View[1]")
	private MobileElement ratingPopUpCloseBtn;

	public MobileElement getRatingPopUpCloseBtn() {
		return ratingPopUpCloseBtn;
	}

	@AndroidFindBy(xpath = "//*[@text='What did you not like?']/following-sibling::android.view.View/child::android.widget.Button")
	private List<MobileElement> notLikeReasonList;

	public List<MobileElement> getNotLikeReasonList() {
		return notLikeReasonList;
	}

	@AndroidFindBy(className = "android.widget.EditText")
	private MobileElement feedBackTextField;

	public MobileElement getFeedBackTextField() {
		return feedBackTextField;
	}

	@AndroidFindBy(id = "comment_count")
	private MobileElement videoCommentCount;

	public MobileElement getVideoCommentCount() {
		return videoCommentCount;
	}

	@AndroidFindBy(xpath = "//*[contains(@text,'Congratulations')]")
	private MobileElement congratPopUpTitle;

	public MobileElement getCongratPopUpTitle() {
		return congratPopUpTitle;
	}

	@AndroidFindBy(xpath = "//*[contains(@text,'Congratulations')]/preceding-sibling::android.view.View")
	private MobileElement congratPopUpCloseBtn;

	public MobileElement getCongratPopUpCloseBtn() {
		return congratPopUpCloseBtn;
	}

	@AndroidFindBy(xpath = "//*[@text='Resume']")
	private MobileElement videoResumeBtn;

	public MobileElement getVideoResumeBtn() {
		return videoResumeBtn;
	}
}
