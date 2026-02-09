package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITBy;
import io.appium.java_client.pagefactory.iOSXCUITFindAll;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebDriver;

public class CommunityPage_OR {

	@AndroidFindBy(id = "com.adda247.app:id/home_coach_text")
	private MobileElement instructionPopUp;

	public MobileElement getInstructionPopUp() {
		return instructionPopUp;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"All Post\nTab 1 of 3\"]")
	@iOSXCUITFindBy(xpath = "//*[@name='All Post\nTab 1 of 3']")
	private MobileElement tabAllPost;

	public MobileElement getTabAllPost() {
		return tabAllPost;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Your Group\nTab 2 of 3\"]")
	@iOSXCUITFindBy(xpath = "//*[@name='Your Group\nTab 2 of 3']")
	private MobileElement tabYourGroup;

	public MobileElement getTabYourGroup() {
		return tabYourGroup;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Explore Group\nTab 3 of 3\"]")
	@iOSXCUITFindBy(xpath = "//*[@name='Explore Group\nTab 3 of 3']")
	private MobileElement tabExploreGroup;

	public MobileElement getTabExploreGroup() {
		return tabExploreGroup;
	}

	@AndroidFindBy(accessibility = "moveToFilterScreen")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='FILTER']")
	private MobileElement btnFilter;

	public MobileElement getBtnFilter() {
		return btnFilter;
	}

	@AndroidFindAll({
		@AndroidBy(accessibility = "Post your query/doubt"),
		@AndroidBy(accessibility = "addPostButton")//Sankalp
	})
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='addPostButton']")
	private MobileElement btnAddPost;

	public MobileElement getBtnAddPost() {
		return btnAddPost;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'like')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[contains(@name,'like')]")
	private List<MobileElement> btnLike;

	public List<MobileElement> getBtnLike() {
		return btnLike;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'moveToComment')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[contains(@name,'moveToComment')]")
	private List<MobileElement> btnComment;

	public List<MobileElement> getBtnComment() {
		return btnComment;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'share')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name='share']")
	private List<MobileElement> btnShare;

	public List<MobileElement> getBtnShare() {
		return btnShare;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'bookMarked')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name='bookMarked']")
	private List<MobileElement> btnSave;

	public List<MobileElement> getBtnSave() {
		return btnSave;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'openBottomSheet')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name='openBottomSheet']")
	private List<MobileElement> dotsMenu;

	public List<MobileElement> getListDotMenu() {
		return dotsMenu;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Post has been bookmarked\"]")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'Post has been bookmarked')]")
	private MobileElement toastPostHasBeenBookmarked;

	public MobileElement getToastPostHasBeenBookmarked() {
		return toastPostHasBeenBookmarked;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Bookmark removed successfully\"]/android.widget.Button")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'Bookmark removed successfully')]")
	private MobileElement toastBookmarkRemovedSuccessfully;

	public MobileElement getToastBookmarkRemovedSuccessfully() {
		return toastBookmarkRemovedSuccessfully;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Post liked successfully\"]")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'Post liked successfully')]")
	private MobileElement toastPostLikedSuccessfully;

	public MobileElement getToastPostLikedSuccessfully() {
		return toastPostLikedSuccessfully;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Post liked successfully\"]/android.widget.Button")
	private MobileElement toastLikeRemovedSuccessfully;

	public MobileElement getToastLikeRemovedSuccessfully() {
		return toastLikeRemovedSuccessfully;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'moveToGroupFeedScreen')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[contains(@name,'moveToFeedDetailScreen')]")
	private List<MobileElement> postGroupList;

	public List<MobileElement> getPostGroupList() {
		return postGroupList;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'moveToGroupFeedScreen')]/child::android.view.View")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'userProfileLocator')]")
	private List<MobileElement> postCreatorName;

	public List<MobileElement> getPostCreatorName() {
		return postCreatorName;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'moveToFeedDetailScreen')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[contains(@name,'moveToFeedDetailScreen')]")
	private List<MobileElement> listPost;

	public List<MobileElement> getListPost() {
		return listPost;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'submitMcq')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'submitMcq')]")
	private MobileElement buttonSubmitMcq;

	public MobileElement getButtonSubmitMcq() {
		return buttonSubmitMcq;
	}

	//------------------------------------------YOUR GROUPS TAB----------------------------------------------------

	@AndroidFindBy(accessibility = "Official Banking groups")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[contains(@name,'Official Banking groups')]")
	private MobileElement sectionOfficialBankingGroups;

	public MobileElement getSectionOfficialBankingGroups() {
		return sectionOfficialBankingGroups;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'Official CLASS12')]")
	private MobileElement sectionOfficialClass12JeeGroups;

	public MobileElement getSectionOfficialClass12JeeGroups() {
		return sectionOfficialClass12JeeGroups;
	}

	//	@AndroidFindBy(id = "Your created groups")
	//	private MobileElement sectionYourCreatedGroups;
	//	
	//	public MobileElement getSectionYourCreatedGroups() {
	//		return sectionYourCreatedGroups;
	//	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'groups you are following')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'groups you are following')]")
	private MobileElement sectionBankingGroupsYouAreFollowing;

	public MobileElement getSectionBankingGroupsYouAreFollowing() {
		return sectionBankingGroupsYouAreFollowing;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'Your created groups')]")
	@iOSXCUITFindBy(accessibility = "Your created groups")
	private MobileElement sectionYourCreatedGroup;

	public MobileElement getSectionYourCreatedGroups() {
		return sectionYourCreatedGroup;
	}

	@AndroidFindBy(accessibility = "Explore more groups to follow")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Explore more groups to follow']")
	private MobileElement btnExploreMoreGroupToFollow;

	public MobileElement getBtnExploreMoreGroupToFollow() {
		return btnExploreMoreGroupToFollow;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'moveToGroupFeedScreen')]")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'moveToGroupFeedScreen')]")
	private List<MobileElement> groupList;

	public List<MobileElement> getGroupList() {
		return groupList;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'groupTiles')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[contains(@name,'groupTiles')]")
	private List<MobileElement> groupTile;

	public List<MobileElement> getListGroupTiles() {
		return groupTile;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'shareIcon')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name='shareIcon']")
	private List<MobileElement> iconShareGroup;

	public List<MobileElement> getIconShareGroup() {
		return iconShareGroup;
	}
	//--------------------------------------EXPLORE GROUPS TAB-------------------------------------------------

	@AndroidFindBy(accessibility = "Latest introduced Banking Groups")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Latest introduced Banking Groups']")
	private MobileElement sectionLatestIntroducedGroup;

	public MobileElement getSectionLatestIntroducedGroup() {
		return sectionLatestIntroducedGroup;
	}


	@AndroidFindBy(accessibility = "Other Banking groups")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Other Banking groups']")
	private MobileElement sectionOtherGroups;

	public MobileElement getSectionOtherGroups() {
		return sectionOtherGroups;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'FOLLOW')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name='Follow']")
	private List<MobileElement> btnFollowUnfollowGroup;

	public List<MobileElement> getBtnFollowGroup() {
		return btnFollowUnfollowGroup;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'FOLLOWING')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name='Following']")
	private List<MobileElement> btnUnfollowGroup;

	public List<MobileElement> getBtnUnfollowGroup() {
		return btnUnfollowGroup;
	}

	@AndroidFindAll({
		@AndroidBy(xpath = "//android.widget.ImageView[@content-desc=\"SHARE\"]"),
		@AndroidBy(xpath = "//android.widget.ImageView[@content-desc=\"shareIcon\"]")
	})
	private List<MobileElement> btnShareGroup;

	public List<MobileElement> getBtnShareGroup() {
		return btnShareGroup;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Group followed successfully\"]/android.widget.Button")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'Group followed successfully')]")
	private MobileElement toastGroupFollowedSuccessfully;

	public MobileElement getToastGroupFollowedSuccessfully() {
		return toastGroupFollowedSuccessfully;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Group unfollowed successfully\"]/android.widget.Button")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'Group unfollowed successfully')]")
	private MobileElement toastGroupUnfollowedSuccessfully;

	public MobileElement getToastGroupUnfollowedSuccessfully() {
		return toastGroupUnfollowedSuccessfully;
	}

	//------------------------------------------GROUP FEED PAGE-------------------------------------------------

	@AndroidFindBy(accessibility = "Share")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name='Share']")
	private MobileElement btnShareGroupPage;

	public MobileElement getBtnShareGroupPage() {
		return btnShareGroupPage;
	}

	@AndroidFindBy(accessibility = "Pinned Post")
	@iOSXCUITFindBy(accessibility = "Pinned Post")
	private MobileElement pinnedPost;

	public MobileElement getPinnedPost() {
		return pinnedPost;
	}

	//-----------------------------------------BOTTOM SLIDER MENU---------------------------------------------------

	@AndroidFindBy(accessibility = "hidePost")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Hide Post']")
	private MobileElement optionBsmHidePost;

	public MobileElement getOptionBsmHidePost() {
		return optionBsmHidePost;
	}

	@AndroidFindBy(accessibility = "reportPost")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Report Post']")
	private MobileElement optionBsmReportPost;

	public MobileElement getOptionBsmReportPost() {
		return optionBsmReportPost;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'notification for this post')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name,'notification for this post')]")
	private MobileElement optionBsmTurnOnNotification;

	public MobileElement getOptionBsmTurnOnNotification() {
		return optionBsmTurnOnNotification;
	}

	@AndroidFindAll({
		@AndroidBy(accessibility = "pinPost"),
		@AndroidBy(accessibility = "Pin Post"),
		@AndroidBy(accessibility = "UnPin Post")
	})
	@iOSXCUITFindAll({
		@iOSXCUITBy(accessibility = "Pin Post"),
		@iOSXCUITBy(accessibility = "UnPin Post")
	})
	private MobileElement optionBsmPinPost;

	public MobileElement getOptionBsmPinPost() {
		return optionBsmPinPost;
	}

	//------------------------------------------SHARE GROUP SLIDER-----------------------------------------------

	@AndroidFindBy(accessibility = "Share via")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Share via']")
	private MobileElement titleShareVia;

	public MobileElement getBtnShareVia() {
		return titleShareVia;
	}

	@AndroidFindBy(accessibility = "Copy to Clipboard")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name='Copy to Clipboard']")
	private MobileElement optionCopyToClipboard;

	public MobileElement getOptionCopyToClipboard() {
		return optionCopyToClipboard;
	}

	@AndroidFindBy(accessibility = "Adda247 Groups")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Adda247 Groups']")
	private MobileElement titleAddaGroups;

	public MobileElement getTitleAddaGroups() {
		return titleAddaGroups;
	}

	@AndroidFindBy(className = "android.widget.Button")
	@iOSXCUITFindBy(className = "XCUIElementTypeButton")
	private MobileElement btnCloseShareGroupSlider;

	public MobileElement getBtnCloseShareGroupSlider() {
		return btnCloseShareGroupSlider;
	}

	//------------------------------------------SELECT GROUP PAGE------------------------------------------------


	@AndroidFindAll({
		@AndroidBy(accessibility = "Select Group"),
		@AndroidBy(xpath = "//*[contains(@content-desc,'Select Group')]")//Sankalp
	})
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Your post belongs to?\nSelect Group']")
	private MobileElement titleSlider;

	public MobileElement getTitleSlider() {
		return titleSlider;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"search\n" +
			"Search\"]/android.widget.EditText")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[contains(@name,'search')]")
	private MobileElement searchField;

	public MobileElement getSearchField() {
		return searchField;
	}

	@AndroidFindAll({
		@AndroidBy(xpath = "//*[contains(@content-desc,'All BANKING Groups')]"),
		@AndroidBy(xpath = "//android.view.View[contains(@content-desc,'All CLASS') and contains(@content-desc,'JEE Groups')]")
	})
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='All BANKING Groups']")
	private MobileElement titleAllBankingGroups;

	public MobileElement getTitleAllBankingGroups() {
		return titleAllBankingGroups;
	}

	@AndroidFindBy(xpath = "//android.widget.RadioButton")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeScrollView/following-sibling::XCUIElementTypeOther/child::XCUIElementTypeButton")
	private List<MobileElement> optionsRadioBtn;

	public List<MobileElement> getOptionsRadioBtn() {
		return optionsRadioBtn;
	}

	//--------------------------CHOOSE THE TYPE OF POST SLIDER MENU------------------------------------

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Choose the type of post\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Choose the type of post']")
	private MobileElement titleSliderTypeOfPost;

	public MobileElement getTitleSliderTypeOfPost() {
		return titleSliderTypeOfPost;
	}

	@AndroidFindBy(xpath = "//android.view.View/android.view.View[1]/android.view.View[1]/android.view.View/android.view.View")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Picture / Attachment Post']")
	private MobileElement optionPictureVideoAttachmentPost;

	public MobileElement getOptionPictureVideoAttachmentPost() {
		return optionPictureVideoAttachmentPost;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'mcqPost')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='MCQ']")
	private MobileElement optionMCQs;

	public MobileElement getOptionMCQs() {
		return optionMCQs;
	}

	
	//------------------------------------COMMENT PAGE------------------------------------------------


	

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'userProfileLocator')][1]")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'userProfileLocator')][1]")
	private List<MobileElement> ownProfilePic;

	public List<MobileElement> getOwnProfilePic() {
		return ownProfilePic;
	}

	@AndroidFindBy(accessibility = "pressedBack")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name,'postBack')]")
	private MobileElement closeCommentsBtn;

	public MobileElement getCloseCommentsBtn() {
		return closeCommentsBtn;
	}

	@AndroidFindBy(xpath = "//android.widget.EditText")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[contains(@name,'openTextFormField')]")
	private MobileElement textFieldComment;

	public MobileElement getTextFieldComment() {
		return textFieldComment;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'uploadComment')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name='uploadComment\nPost']")
	private MobileElement btnSendComment;

	public MobileElement getBtnSendComment() {
		return btnSendComment;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'like')]")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'like')]")
	private List<MobileElement> btnLikeComment;

	public List<MobileElement> getBtnLikeComment() {
		return btnLikeComment;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Reply')]")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'Reply')]")
	private MobileElement btnReplyComment;

	public MobileElement getBtnReplyComment() {
		return btnReplyComment;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"like\n" +
			"0 Like\"]")
	@iOSXCUITFindBy(xpath = "//*[@name='Comment liked successfully']")
	private MobileElement successfulLikedCommentMessage;

	public MobileElement getSuccessfulLikedCommentMessage() {
		return successfulLikedCommentMessage;
	}



	//---------------------------------FILTER PAGE--------------------------------------------------

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'Groups')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='SelectedFilterType\nGroups']")
	private MobileElement tabGroups;

	public MobileElement getTabGroups() {
		return tabGroups;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'Topics')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='SelectedFilterType\nTopics']")
	private MobileElement tabTopics;

	public MobileElement getTabTopics() {
		return tabTopics;
	}

	@AndroidFindAll({
		@AndroidBy(xpath = "//*[contains(@content-desc,'SelectedFilterType\nPost Type"),
		@AndroidBy(xpath = "//*[contains(@content-desc,'Post')]")
	})
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='SelectedFilterType\nPost']")
	private MobileElement tabPostType;

	public MobileElement getTabPostType() {
		return tabPostType;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'APPLY')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='applyFiler\nAPPLY']")
	private MobileElement btnApply;

	public MobileElement getBtnApply() {
		return btnApply;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"RESET\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name='RESET ALL FILTERS']")
	private MobileElement btnReset;

	public MobileElement getBtnReset() {
		return btnReset;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"pressedBack\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='resetFilter\nCLOSE']")
	private MobileElement btnClose;

	public MobileElement getBtnClose() {
		return btnClose;
	}

	@AndroidFindBy(xpath = "//*[contains(@text,'Groups')]") // issue -- removed
	private MobileElement fieldSearchGroups;

	public MobileElement getFieldSearchGroups() {
		return fieldSearchGroups;
	}

	@AndroidFindBy(xpath = "//*[contains(@text,'Topics')]") // issue -- removed
	private MobileElement fieldSearchTopics;

	public MobileElement getFieldSearchTopics() {
		return fieldSearchGroups;
	}

	@AndroidFindBy(className = "com.adda247.app.android.widget.CheckBox$android.widget.CheckBox")
	@iOSXCUITFindBy(className = "XCUIElementTypeSwitch")
	private List<MobileElement> optionsCheckBox;

	public List<MobileElement> getOptionsCheckBox() {
		return optionsCheckBox;
	}

	//-------------------------------------POST-DETAIL PAGE----------------------------------------

	@AndroidFindBy(xpath = "(//android.widget.ImageView)[2]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='bookMarked']")
	private MobileElement btnSavePostDetailPage;

	public MobileElement getBtnSavePostDetailPage() {
		return btnSavePostDetailPage;
	}

	@AndroidFindBy(xpath = "(//android.widget.ImageView)[3]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='bookMarked']/following-sibling::XCUIElementTypeButton")
	private MobileElement btnDotMenuPostDetailPage;

	public MobileElement getBtnDotMenuPostDetailPage() {
		return btnDotMenuPostDetailPage;
	}

	@AndroidFindBy(xpath = "(//android.widget.ImageView)[4]") // need a specific locator for it
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name='share']/preceding-sibling::XCUIElementTypeImage[contains(@name,'like')]")
	private MobileElement btnLikePostDetailPage;

	public MobileElement getBtnLikePostDetailPage() {
		return btnLikePostDetailPage;
	}

	//	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Send Message ...\"]")
	//	private MobileElement textFieldCommentPostDetailPage;
	//
	//	public MobileElement getTextFieldCommentPostDetailPage() {
	//		return textFieldCommentPostDetailPage;
	//	}

	@AndroidFindBy(xpath = "(//android.view.View)[8]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name='share']")
	private MobileElement iconSharePostDetailPage;

	public MobileElement getIconSharePostDetailPage() {
		return iconSharePostDetailPage;
	}

	//-----------------POST DETAILS PAGE DOT MENU---------------------------------------------

	@AndroidFindBy(xpath = "//*[contains(@content-desc, 'Delete Post')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Delete Post']")
	private MobileElement OptionDeletePost;

	public MobileElement getOptionDeletePost() {
		return OptionDeletePost;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Hide Post\"]")
	private MobileElement OptionHidePost;

	public MobileElement getOptionHidePost() {
		return OptionHidePost;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc, 'Report Post')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Report Post']")
	private MobileElement optionReportPost;

	public MobileElement getOptionReportPost() {
		return optionReportPost;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc, 'Turn off notification for this post')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Turn off notification for this post']")
	private MobileElement optionTurnOffNotification;

	public MobileElement getOptionTurnOffNotification() {
		return optionTurnOffNotification;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc, 'Turn on notification for this post')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Turn off notification for this post']")
	private MobileElement optionTurnOnNotification;

	public MobileElement getOptionTurnOnNotification() {
		return optionTurnOnNotification;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc, 'Edit Post')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Edit Post']")
	private MobileElement optionEditPost;

	public MobileElement getOptionEditPost() {
		return optionEditPost;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"openBottomSheet\"]/preceding-sibling::android.view.View[contains(@content-desc,'Sarika ')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Sarika ')]")
	private List<MobileElement> adminFacultyName;

	public List<MobileElement> getAdminFacultyName() {
		return adminFacultyName;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Sarika ')]/preceding-sibling::android.view.View")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Sarika Tyagi')]/preceding-sibling::XCUIElementTypeStaticText")
	private List<MobileElement> adminFacultyImage;

	public List<MobileElement> getAdminFacultyImage() {
		return adminFacultyImage;
	}

	@AndroidFindBy(accessibility = "Reply")
	@iOSXCUITFindBy(accessibility = "Reply")
	private MobileElement rePlyPageTitle;

	public MobileElement getRePlyPageTitle() {
		return rePlyPageTitle;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc='Reply']/following-sibling::android.widget.EditText")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Reply']/following-sibling::XCUIElementTypeTextField")
	private MobileElement replyCommentTextField;

	public MobileElement getReplyCommentTextField() {
		return replyCommentTextField;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'uploadComment')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[contains(@name,'uploadComment')]")
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
}
