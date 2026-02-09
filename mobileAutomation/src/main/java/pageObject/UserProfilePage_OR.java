package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class UserProfilePage_OR {

	@AndroidFindBy(id = "logout")
	private MobileElement btnLogout;

	public MobileElement getBtnLogout() {
		return btnLogout;
	}

	@AndroidFindBy(id = "reward_point_view")
	private MobileElement sectionCoinBalance;

	public MobileElement getSectionCoinBalance() {
		return sectionCoinBalance;
	}

	@AndroidFindBy(id = "tv_coin_history")
	private MobileElement coinHistoryText;

	public MobileElement getCoinHistoryText() {
		return coinHistoryText;
	}

	@AndroidFindBy(id = "my_order")
	private MobileElement sectionMyOrder;

	public MobileElement getSectionMyOrder() {
		return sectionMyOrder;
	}

	@AndroidFindBy(id = "settings")
	private MobileElement sectionSettings;

	public MobileElement getSectionSettings() {
		return sectionSettings;
	}

	@AndroidFindBy(id = "alertTitle")
	private MobileElement popupLogout;

	public MobileElement getPopupLogout() {
		return popupLogout;
	}

	@AndroidFindBy(id = "android:id/button1")
	private MobileElement btnLogoutPopup;

	public MobileElement getBtnLogoutPopup() {
		return btnLogoutPopup;
	}

	@AndroidFindBy(id = "android:id/button2")
	private MobileElement btnCancelPopup;

	public MobileElement getBtnCancelPopup() {
		return btnCancelPopup;
	}

	@AndroidFindBy(id = "accountHolderName")
	private MobileElement textUserName;

	public MobileElement getTextUserName() {
		return textUserName;
	}

	@AndroidFindBy(id = "accountHolderEmail")
	private MobileElement textUserEmail;

	public MobileElement getTextUserEmail() {
		return textUserEmail;
	}

	@AndroidFindBy(id = "com.adda247.app:id/accountHolderNumber")
	private MobileElement textUserNumber;

	public MobileElement getTextUserNumber() {
		return textUserNumber;
	}

	@AndroidFindBy(id = "accountHolderPicture")
	private MobileElement userProfilePicture;

	public MobileElement getUserProfilePicture() {
		return userProfilePicture;
	}

	@AndroidFindBy(accessibility = "updateProfilePageLocator")
	private MobileElement iconEdit;

	public MobileElement getIconEdit() {
		return iconEdit;
	}

	@AndroidFindBy(id = "com.adda247.app:id/image_view")
	private MobileElement userImageView;

	public MobileElement getUserImageView() {
		return userImageView;
	}

	@AndroidFindBy(id = "com.adda247.app:id/close")
	private MobileElement imageViewCloseBtn;

	public MobileElement getImageViewCloseBtn() {
		return imageViewCloseBtn;
	}

	@AndroidFindBy(accessibility = "Navigate up")
	private MobileElement backBtn;

	public MobileElement getBackBtn() {
		return backBtn;
	}

	/*-----------------------------------New User Profile Screen----------------------------*/

	@AndroidFindBy(accessibility = "pressedBack")
	@iOSXCUITFindBy(accessibility = "pressedBack")
	private MobileElement newUserProfileBackBtn;

	public MobileElement getNewUserProfileBackBtn() {
		return newUserProfileBackBtn;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'My Purchased')]/preceding-sibling::android.view.View[3]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'myPurchasePageLocator')]/preceding-sibling::XCUIElementTypeStaticText[1]")
	private MobileElement ownUserName;

	public MobileElement getOwnUserName() {
		return ownUserName;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"openBottomSheet\"]")
	@iOSXCUITFindBy(accessibility = "openBottomSheet")
	private MobileElement newUserProfileDotIcon;

	public MobileElement getNewUserProfileDotIcon() {
		return newUserProfileDotIcon;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'My Purchased')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'myPurchasePageLocator')]")
	private MobileElement myPurchasedBtn;

	public MobileElement getMyPurchasedBtn() {
		return myPurchasedBtn;
	}

	@AndroidFindAll({ @AndroidBy(xpath = "//*[contains(@content-desc,'Bookmark')]"),
			@AndroidBy(xpath = "//*[contains(@content-desc,'Bookmark')]") })
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[contains(@name,'BookMarkPageLocator')]")
	private List<MobileElement> bookMarkBtn;

	public List<MobileElement> getBookMarkBtn() {
		return bookMarkBtn;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Followers')]")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'Followers')]")
	private MobileElement followersTab;

	public MobileElement getFollowersTab() {
		return followersTab;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Following')]")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'Following')]")
	private MobileElement followingTab;

	public MobileElement getFollowingTab() {
		return followingTab;
	}

	@AndroidFindBy(accessibility = "No User Found")
	@iOSXCUITFindBy(accessibility = "No User Found")
	private MobileElement followFollowingSceenEmptyState;

	public MobileElement getFollowFollowingSceenEmptyState() {
		return followFollowingSceenEmptyState;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'Coin Earned')]")
	private MobileElement coinEarnedTabTitle;

	public MobileElement getCoinEarnedTabTitle() {
		return coinEarnedTabTitle;
	}

	@AndroidFindBy(accessibility = "Profile Completion")
	@iOSXCUITFindBy(accessibility = "Profile Completion")
	private MobileElement profileCompletionTitle;

	public MobileElement getProfileCompletionTitle() {
		return profileCompletionTitle;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"allActivityLocator\n" + "View All Activity\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'allActivityLocator')]")
	private MobileElement allActivityTab;

	public MobileElement getAllActivityTab() {
		return allActivityTab;
	}

	@AndroidFindBy(accessibility = "updateProfilePageLocator")
	@iOSXCUITFindBy(accessibility = "updateProfilePageLocator")
	private MobileElement userDetailsEditBtn;

	public MobileElement getUserDetailsEditBtn() {
		return userDetailsEditBtn;
	}

	@AndroidFindBy(accessibility = "Total Likes")
	@iOSXCUITFindBy(accessibility = "Total Likes")
	private MobileElement totalLikesTitle;

	public MobileElement getTotalLikesTitle() {
		return totalLikesTitle;
	}

	@AndroidFindBy(accessibility = "Total Posts")
	@iOSXCUITFindBy(accessibility = "Total Posts")
	private MobileElement totalPostTitle;

	public MobileElement getTotalPostTitle() {
		return totalPostTitle;
	}

	@AndroidFindBy(accessibility = "Total Comments")
	@iOSXCUITFindBy(accessibility = "Total Comments")
	private MobileElement totalCommentsTitle;

	public MobileElement getTotalCommentsTitle() {
		return totalCommentsTitle;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'Attempted Quiz')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[contains(@name,'Attempted Quiz')]")
	private MobileElement attemptedQuizTab;

	public MobileElement getAttemptedQuizTab() {
		return attemptedQuizTab;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'Videos Watched')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[contains(@name,'Videos Watched')]")
	private MobileElement videoWatchedTab;

	public MobileElement getVideoWatchedTab() {
		return videoWatchedTab;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'Articles Read')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[contains(@name,'Articles Read')]")
	private MobileElement articlesReadTab;

	public MobileElement getArticlesReadTab() {
		return articlesReadTab;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Follow')or contains(@content-desc,'Following')]")
	@iOSXCUITFindAll({ @iOSXCUITBy(xpath = "//*[contains(@name,'followLocator')]"),
			@iOSXCUITBy(xpath = "//*[contains(@name,'followingLocator')]") })
	private List<MobileElement> followFollowingBtn;

	public List<MobileElement> getFollowFollowingBtn() {
		return followFollowingBtn;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'Remove')]")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'REMOVE')]")
	private List<MobileElement> removeBtn;

	public List<MobileElement> getRemoveBtn() {
		return removeBtn;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'userProfileLocator')]")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'userProfileLocator')]")
	private List<MobileElement> followFollowingUserName;

	public List<MobileElement> getFollowFollowingUserName() {
		return followFollowingUserName;
	}

	@AndroidFindBy(accessibility = "pressedBack")
	@iOSXCUITFindBy(accessibility = "pressedBack")
	private MobileElement followFollowingScreenBackBtn;

	public MobileElement getFollowFollowingScreenBackBtn() {
		return followFollowingScreenBackBtn;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'Copy Profile Link')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[contains(@name,'Copy Profile Link')]")
	private MobileElement copyProfileLinkTab;

	public MobileElement getCopyProfileLinkTab() {
		return copyProfileLinkTab;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'Report')]")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'Report')]")
	private MobileElement reportTab;

	public MobileElement getReportTab() {
		return reportTab;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'Report Profile')]")
	@iOSXCUITFindBy(accessibility = "Report Profile")
	private MobileElement reportProfilePopUpTitle;

	public MobileElement getReportProfilePopUpTitle() {
		return reportProfilePopUpTitle;
	}

	@AndroidFindBy(className = "android.widget.RadioButton")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeSwitch[@name='selectedReportOption']/following-sibling::XCUIElementTypeButton")
	private List<MobileElement> reportOptionList;

	public List<MobileElement> getReportOptionList() {
		return reportOptionList;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"CANCEL\"]")
	@iOSXCUITFindBy(accessibility = "CANCEL")
	private MobileElement reportCancelBtn;

	public MobileElement getReportCancelBtn() {
		return reportCancelBtn;
	}

	@AndroidFindBy(xpath = "//*[@content-desc='REPORT']")
	@iOSXCUITFindBy(accessibility = "REPORT")
	private MobileElement reportBtn;

	public MobileElement getReportBtn() {
		return reportBtn;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'Edit Personal Information')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[contains(@name,'Edit Personal Information')]")
	private MobileElement editPersonalTab;

	public MobileElement getEditPersonalTab() {
		return editPersonalTab;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'Coins Balance')]")
	private MobileElement coinBalanceTab;

	public MobileElement getCoinBalanceTab() {
		return coinBalanceTab;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'My Orders')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[contains(@name,'My Orders')]")
	private MobileElement myOrderTab;

	public MobileElement getMyOrderTab() {
		return myOrderTab;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'Settings')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[contains(@name,'Settings')]")
	private MobileElement settingTab;

	public MobileElement getSettingTab() {
		return settingTab;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'Logout')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[contains(@name,'Logout')]")
	private MobileElement logoutTab;

	public MobileElement getLogoutTab() {
		return logoutTab;
	}

	@AndroidFindBy(accessibility = "Copied")
	@iOSXCUITFindBy(accessibility = "Copied")
	private MobileElement profileLinkCopyToast;

	public MobileElement getProfileLinkCopyToast() {
		return profileLinkCopyToast;
	}

	@AndroidFindBy(accessibility = "Faculty")
	@iOSXCUITFindBy(accessibility = "Faculty")
	private MobileElement facultyTag;

	public MobileElement getFacultyTag() {
		return facultyTag;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'My Courses')]")
	@iOSXCUITFindBy(accessibility = "myCoursesPageLocator\nMy Courses")
	private MobileElement myCourseBtn;

	public MobileElement getMyCourseBtn() {
		return myCourseBtn;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'Sarika')]")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'Sarika')]")
	private MobileElement myCoursePageTitle;

	public MobileElement getMyCoursePageTitle() {
		return myCoursePageTitle;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'Sarika')]/preceding-sibling::android.widget.Button")
	@iOSXCUITFindBy(accessibility = "pressedBack")
	private MobileElement myCoursePageBackBtn;

	public MobileElement getMyCoursePageBackBtn() {
		return myCoursePageBackBtn;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Groups')]")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'Groups')]")
	private MobileElement groupsTab;

	public MobileElement getGroupsTab() {
		return groupsTab;
	}

	@AndroidFindBy(accessibility = "No Groups Found")
	@iOSXCUITFindBy(accessibility = "No Groups Found")
	private MobileElement groupEmptyState;

	public MobileElement getGroupEmptyState() {
		return groupEmptyState;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'groups')]")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'groups')]")
	private MobileElement groupsTabPageTitle;

	public MobileElement getGroupsTabPageTitle() {
		return groupsTabPageTitle;
	}

	/*-----------------------Activity Screen------------------*/

	@AndroidFindBy(accessibility = "Activity")
	@iOSXCUITFindBy(accessibility = "Activity")
	private MobileElement activityPageTitle;

	public MobileElement getActivityPageTitle() {
		return activityPageTitle;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'All Post')]")
	@iOSXCUITFindBy(accessibility = "All Post\nTab 1 of 4")
	private MobileElement allPostTab;

	public MobileElement getAllPostTab() {
		return allPostTab;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Comment')]")
	@iOSXCUITFindBy(accessibility = "Comment\nTab 2 of 4")
	private MobileElement commentTab;

	public MobileElement getCommentTab() {
		return commentTab;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Likes')]")
	@iOSXCUITFindBy(accessibility = "Likes\nTab 3 of 4")
	private MobileElement likeTab;

	public MobileElement getLikeTab() {
		return likeTab;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Mcq')]")
	@iOSXCUITFindBy(accessibility = "Mcq\nTab 4 of 4")
	private MobileElement mcqTab;

	public MobileElement getMcqTab() {
		return mcqTab;
	}

	@AndroidFindBy(accessibility = "No Post Found")
	@iOSXCUITFindBy(accessibility = "No Post Found")
	private MobileElement emptyStateImg;

	public MobileElement getEmptyStateImg() {
		return emptyStateImg;
	}

	@AndroidFindBy(xpath = "//android.widget.CheckBox[contains(@content-desc,'markMcq')]/android.widget.RadioButton")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeSwitch/following-sibling::XCUIElementTypeButton")
	private List<MobileElement> mcqOptionsRadioBtn;

	public List<MobileElement> getMcqOptionsRadioBtn() {
		return mcqOptionsRadioBtn;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'submitMcq')]")
	@iOSXCUITFindBy(accessibility = "submitMcq\nSubmit")
	private MobileElement mcqSubmitBtn;

	public MobileElement getMcqSubmitBtn() {
		return mcqSubmitBtn;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'Correct Answer')]")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'Correct Answer')]")
	private MobileElement correctAnswerTag;

	public MobileElement getCorrectAnswerTag() {
		return correctAnswerTag;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc='Activity']/preceding-sibling::android.widget.Button")
	@iOSXCUITFindBy(accessibility = "pressedBack")
	private MobileElement activityPageBackBtn;

	public MobileElement getActivityPageBackBtn() {
		return activityPageBackBtn;
	}

	/*-----------------------------------BookMark Screen----------------------------*/

	@AndroidFindBy(accessibility = "Bookmark")
	@iOSXCUITFindBy(accessibility = "Bookmarks")
	private MobileElement bookMarkPageTitle;

	public MobileElement getBookMarkPageTitle() {
		return bookMarkPageTitle;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc='Bookmark']/preceding-sibling::android.widget.Button")
	@iOSXCUITFindBy(accessibility = "pressedBack")
	private MobileElement bookMarkPageBackBtn;

	public MobileElement getBookMarkPageBackBtn() {
		return bookMarkPageBackBtn;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Posts')]")
	@iOSXCUITFindBy(accessibility = "Posts\nTab 1 of 1")
	private MobileElement postsTab;

	public MobileElement getPostsTab() {
		return postsTab;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Questions')]")
	private MobileElement questionsTab;

	public MobileElement getQuestionsTab() {
		return questionsTab;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Videos')]")
	private MobileElement videosTab;

	public MobileElement getVideosTab() {
		return videosTab;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Current Affairs')]")
	private MobileElement currentAffairsTab;

	public MobileElement getCurrentAffairsTab() {
		return currentAffairsTab;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Articles')]")
	private MobileElement articlesTab;

	public MobileElement getArticlesTab() {
		return articlesTab;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Job Alert')]")
	private MobileElement jobAlertsTab;

	public MobileElement getJobAlertsTab() {
		return jobAlertsTab;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Follower')]")
	private MobileElement followersText;

	public MobileElement getFollowersText() {
		return followersText;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Follower')]/preceding-sibling::android.view.View[1]")
	private MobileElement userNameText;

	public MobileElement getUserNameText() {
		return userNameText;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'moveToFeedDetailScreen')]")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'moveToFeedDetailScreen')]")
	private List<MobileElement> bookMarkPost;

	public List<MobileElement> getBookMarkPost() {
		return bookMarkPost;
	}

	@AndroidFindAll({ @AndroidBy(xpath = "//android.view.View[contains(@content-desc,'moveToGroupFeedScreen')]"),
			@AndroidBy(xpath = "//android.widget.ImageView[@content-desc='openBottomSheet']/preceding-sibling::android.view.View[contains(@content-desc,'userProfileLocator')][1]") })
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'moveToFeedDetailScreen')]")
	private List<MobileElement> bookMarkPostTitle;

	public List<MobileElement> getBookMarkPostTitle() {
		return bookMarkPostTitle;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Follower')]/following-sibling::android.view.View/descendant::*[@content-desc]")
	private List<MobileElement> bookMarkMaterial;

	public List<MobileElement> getBookMarkMaterial() {
		return bookMarkMaterial;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Sarika')]")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'Sarika')]")
	private MobileElement facultyAccountName;

	public MobileElement getFacultyAccountName() {
		return facultyAccountName;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Vibhor')]")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'Vibhor')]")
	private MobileElement simpleUserAccountName;

	public MobileElement getSimpleUserAccountName() {
		return simpleUserAccountName;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'reported')]")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'reported')]")
	private MobileElement reportedToastMsg;

	public MobileElement getReportedToastMsg() {
		return reportedToastMsg;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc='About']/following-sibling::android.view.View[2]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='About']/following-sibling::XCUIElementTypeStaticText[2]")
	private MobileElement totalLikesCount;

	public MobileElement getTotalLikesCount() {
		return totalLikesCount;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc='About']/following-sibling::android.view.View[3]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='About']/following-sibling::XCUIElementTypeStaticText[3]")
	private MobileElement totalPostCount;

	public MobileElement getTotalPostCount() {
		return totalPostCount;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc='About']/following-sibling::android.view.View[4]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='About']/following-sibling::XCUIElementTypeStaticText[4]")
	private MobileElement totalCommentsCount;

	public MobileElement getTotalCommentsCount() {
		return totalCommentsCount;
	}

	@AndroidFindBy(accessibility = "No Post Found")
	@iOSXCUITFindBy(accessibility = "No Post Found")
	private MobileElement emptyStatePost;

	public MobileElement getEmptyStatePost() {
		return emptyStatePost;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'No Questions are Bookmarked')]")
	private MobileElement questionTabEmptyState;

	public MobileElement getQuestionTabEmptyState() {
		return questionTabEmptyState;
	}

	@AndroidFindBy(accessibility = "GO TO QUIZZES")
	private MobileElement goToQuizzeBtn;

	public MobileElement getGoToQuizzeBtn() {
		return goToQuizzeBtn;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'All')]")
	private MobileElement bookMarkedQuestion;

	public MobileElement getBookMarkedQuestion() {
		return bookMarkedQuestion;
	}

	@AndroidFindBy(xpath = "//*[@text='Bookmarked Question']")
	private MobileElement bookMarkedQuestionPageTitle;

	public MobileElement getBookMarkedQuestionPageTitle() {
		return bookMarkedQuestionPageTitle;
	}

	@AndroidFindBy(accessibility = "GO TO VIDEOS")
	private MobileElement goToVideoBtn;

	public MobileElement getGoToVideoBtn() {
		return goToVideoBtn;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'No Videos are Bookmarked')]")
	private MobileElement videoTabEmptyState;

	public MobileElement getVideoTabEmptyState() {
		return videoTabEmptyState;
	}

	@AndroidFindBy(accessibility = "GO TO CURRENT AFFAIRS")
	private MobileElement goToCurrentAffairsBtn;

	public MobileElement getGoToCurrentAffairsBtn() {
		return goToCurrentAffairsBtn;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'No Current Affairs are Bookmarked')]")
	private MobileElement currentAffairTabEmptyState;

	public MobileElement getCurrentAffairTabEmptyState() {
		return currentAffairTabEmptyState;
	}

	@AndroidFindBy(accessibility = "GO TO ARTICLE")
	private MobileElement goToArticleBtn;

	public MobileElement getGoToArticleBtn() {
		return goToArticleBtn;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'No Articles are Bookmarked')]")
	private MobileElement articleTabEmptyState;

	public MobileElement getArticleTabEmptyState() {
		return articleTabEmptyState;
	}

	@AndroidFindBy(accessibility = "GO TO JOB ALERTS")
	private MobileElement goToJobAlertBtn;

	public MobileElement getGoToJobAlertBtn() {
		return goToJobAlertBtn;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'No Job Alerts are Bookmarked')]")
	private MobileElement jobAlertTabEmptyState;

	public MobileElement getJobAlertTabEmptyState() {
		return jobAlertTabEmptyState;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'like')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[contains(@name,'like')]")
	private List<MobileElement> postLikeBtn;

	public List<MobileElement> getPostLikeBtn() {
		return postLikeBtn;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'moveToComment')][1]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[contains(@name,'moveToComment')]")
	private List<MobileElement> postCommentBtn;

	public List<MobileElement> getPostCommentBtn() {
		return postCommentBtn;
	}

	@AndroidFindBy(accessibility = "share")
	@iOSXCUITFindBy(accessibility = "share")
	private List<MobileElement> postShareBtn;

	public List<MobileElement> getPostShareBtn() {
		return postShareBtn;
	}

	@AndroidFindBy(accessibility = "bookMarked")
	@iOSXCUITFindBy(accessibility = "bookMarked")
	private MobileElement postBookMarkBtn;

	public MobileElement getPostBookMarkBtn() {
		return postBookMarkBtn;
	}

	@AndroidFindBy(accessibility = "Delete Post")
	@iOSXCUITFindBy(accessibility = "Delete Post")
	private MobileElement deletePostTab;

	public MobileElement getDeletePostTab() {
		return deletePostTab;
	}

	@AndroidFindBy(xpath = "Edit Post")
	@iOSXCUITFindBy(xpath = "Edit Post")
	private MobileElement editPostTab;

	public MobileElement getEditPostTab() {
		return editPostTab;
	}

	@AndroidFindBy(accessibility = "turnOnNotification")
	@iOSXCUITFindBy(accessibility = "turnOnNotification")
	private MobileElement postTurnOnNotificationTab;

	public MobileElement getPostTurnOnNotificationTab() {
		return postTurnOnNotificationTab;
	}

	@AndroidFindBy(accessibility = "Hide Post")
	@iOSXCUITFindBy(accessibility = "Hide Post")
	private MobileElement postHideTab;

	public MobileElement getPostHideTab() {
		return postHideTab;
	}

	@AndroidFindBy(accessibility = "Report Post")
	@iOSXCUITFindBy(accessibility = "Report Post")
	private MobileElement postReportTab;

	public MobileElement getPostReportTab() {
		return postReportTab;
	}

	@AndroidFindBy(accessibility = "Post has been bookmarked")
	@iOSXCUITFindBy(accessibility = "Post has been bookmarked")
	private MobileElement postBookMarkAddedToast;

	public MobileElement getPostBookMarkAddedToast() {
		return postBookMarkAddedToast;
	}

	@AndroidFindBy(accessibility = "Bookmark remove successfully")
	@iOSXCUITFindBy(accessibility = "Bookmark remove successfully")
	private MobileElement postBookMarkRemovedToast;

	public MobileElement getPostBookMarkRemovedToast() {
		return postBookMarkRemovedToast;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Notifications turned On')]")
	private MobileElement notificationTurnOnToast;

	public MobileElement getNotificationTurnOnToast() {
		return notificationTurnOnToast;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Notifications turned Off')]")
	private MobileElement notificationTurnOffToast;

	public MobileElement getNotificationTurnOffToast() {
		return notificationTurnOffToast;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'hamza six')]/parent::android.view.View/following-sibling::android.widget.ImageView[@content-desc='openBottomSheet']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Hamza Arif')]/following-sibling::XCUIElementTypeImage[@name='openBottomSheet']")
	private MobileElement ownPostDotIcon;

	public MobileElement getOwnPostDotIcon() {
		return ownPostDotIcon;
	}

	@AndroidFindBy(accessibility = "openBottomSheet")
	@iOSXCUITFindBy(accessibility = "openBottomSheet")
	private MobileElement postDotIcon;

	public MobileElement getPostDotIcon() {
		return postDotIcon;
	}

	@AndroidFindBy(accessibility = "Like remove successfully")
	@iOSXCUITFindBy(accessibility = "Like remove successfully")
	private MobileElement likeRemovedToastMessage;

	public MobileElement getLikeRemovedToastMessage() {
		return likeRemovedToastMessage;
	}

	@AndroidFindBy(accessibility = "Post liked successfully")
	@iOSXCUITFindBy(accessibility = "Post liked successfully")
	private MobileElement postLikedToast;

	public MobileElement getPostLikedToast() {
		return postLikedToast;
	}

	@AndroidFindBy(xpath = "//android.widget.EditText")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[contains(@name,'openTextFormField')]")
	private MobileElement textFieldComment;

	public MobileElement getTextFieldComment() {
		return textFieldComment;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'uploadComment')]")
	@iOSXCUITFindBy(accessibility = "uploadComment\nPost")
	private MobileElement btnSendComment;

	public MobileElement getBtnSendComment() {
		return btnSendComment;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'editComment')]")
	@iOSXCUITFindBy(accessibility = "editComment\nEdit Comment")
	private MobileElement editCommentBtn;

	public MobileElement getEditCommentBtn() {
		return editCommentBtn;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'deleteComment')]")
	@iOSXCUITFindBy(accessibility = "deleteComment\nDelete Comment")
	private MobileElement deleteCommentBtn;

	public MobileElement getDeleteCommentBtn() {
		return deleteCommentBtn;
	}

	@AndroidFindBy(accessibility = "Reply")
	@iOSXCUITFindBy(accessibility = "Reply")
	private MobileElement rePlyPageTitle;

	public MobileElement getRePlyPageTitle() {
		return rePlyPageTitle;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc='Reply']/following-sibling::android.widget.EditText")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Reply']/following-sibling::XCUIElementTypeTextField[contains(@name,'openTextFormField')]")
	private MobileElement replyCommentTextField;

	public MobileElement getReplyCommentTextField() {
		return replyCommentTextField;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'uploadComment')]")
	@iOSXCUITFindBy(accessibility = "uploadComment\nPost")
	private MobileElement replyCommentPostBtn;

	public MobileElement getReplyCommentPostBtn() {
		return replyCommentPostBtn;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Reply')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Reply')]")
	private List<MobileElement> replyCommentBtn;

	public List<MobileElement> getReplyCommentBtn() {
		return replyCommentBtn;
	}

	@AndroidFindAll({
			@AndroidBy(xpath = "//android.view.View[@content-desc='Adda247 Groups']/following-sibling::android.view.View/descendant::android.widget.ImageView"),
			@AndroidBy(xpath = "//android.view.View[@content-desc='Sankalp Bharat Groups']/following-sibling::android.view.View/descendant::android.widget.ImageView"),

	})
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Adda247 Groups']/following-sibling::XCUIElementTypeOther/descendant::XCUIElementTypeImage")
	private List<MobileElement> addaGroupList;

	public List<MobileElement> getAddaGroupList() {
		return addaGroupList;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'openBottomSheet')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'openBottomSheet')]")
	private MobileElement selectTopicDropDown;

	public MobileElement getSelectTopicDropDown() {
		return selectTopicDropDown;
	}

	@AndroidFindBy(className = "android.widget.RadioButton")
	@iOSXCUITFindBy(className = "XCUIElementTypeButton")
	private MobileElement topicRadioBtn;

	public MobileElement getTopicRadioBtn() {
		return topicRadioBtn;
	}

	@AndroidFindBy(className = "android.widget.EditText")
	@iOSXCUITFindBy(className = "XCUIElementTypeTextField")
	private MobileElement queryTextField;

	public MobileElement getQueryTextField() {
		return queryTextField;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'Post')]")
	@iOSXCUITFindBy(accessibility = "post\nPost")
	private MobileElement postBtn;

	public MobileElement getPostBtn() {
		return postBtn;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'markMcq')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeSwitch[contains(@name,'markMcq')]")
	private List<MobileElement> mcqOptionList;

	public List<MobileElement> getMcqOptionList() {
		return mcqOptionList;
	}

	@AndroidFindBy(accessibility = "Post has been reposted")
	@iOSXCUITFindBy(accessibility = "Post has been reposted")
	private MobileElement rePostToast;

	public MobileElement getRePostToast() {
		return rePostToast;
	}

	@AndroidFindBy(accessibility = "Post deleted successfully")
	@iOSXCUITFindBy(accessibility = "Post deleted successfully")
	private MobileElement postDeleteToast;

	public MobileElement getPostDeleteToast() {
		return postDeleteToast;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'This Post is unavailable because it was deleted.')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[contains(@name,'This Post is unavailable because it was deleted')]")
	private MobileElement postDeletedText;

	public MobileElement getPostDeletedText() {
		return postDeletedText;
	}

	@AndroidFindBy(accessibility = "This post is no longer available")
	@iOSXCUITFindBy(accessibility = "This post is no longer available")
	private MobileElement postNotAvailableImg;

	public MobileElement getPostNotAvailableImg() {
		return postNotAvailableImg;
	}

	@AndroidFindBy(xpath = "//*[@content-desc='bookMarked']/following-sibling::android.widget.ImageView")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='bookMarked']/following-sibling::XCUIElementTypeButton")
	private MobileElement commentPagePostDotIcon;

	public MobileElement getCommentPagePostDotIcon() {
		return commentPagePostDotIcon;
	}

	@AndroidBy(xpath = "//android.widget.Button[contains(@content-desc,'https://www.adda247.com')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeLink[@name='https://www.adda247.com/profile']")
	private MobileElement userProfileDeepLink;

	public MobileElement getUserProfileDeepLink() {
		return userProfileDeepLink;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'My Certificates')]")
	private MobileElement myCertificateTab;

	public MobileElement getMyCertificateTab() {
		return myCertificateTab;
	}

	@AndroidFindBy(xpath = "//*[@text='View']/following-sibling::android.widget.Button")
	private List<MobileElement> certificateViewBtn;

	public List<MobileElement> getCertificateViewBtn() {
		return certificateViewBtn;
	}

}
