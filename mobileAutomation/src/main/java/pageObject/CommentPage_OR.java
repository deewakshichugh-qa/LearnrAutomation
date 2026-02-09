package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CommentPage_OR {

	@AndroidFindBy(id = "header_txt")
	private MobileElement commentPageHeader;

	@AndroidFindBy(id = "close")
	private MobileElement closeBtn;

	@AndroidFindBy(id = "user_thumb")
	private List<MobileElement> userPic;

	@AndroidFindBy(xpath = "//*[contains(@resource-id,'overflow_icon')]/preceding-sibling::*[contains(@resource-id,'user_name')]")
	private List<MobileElement> userName;

	@AndroidFindBy(id = "time_status")
	private List<MobileElement> commentSentTime;

	@AndroidFindBy(xpath = "//*[contains(@resource-id,'overflow_icon')]/preceding-sibling::*[contains(@resource-id,'comment_container')]/child::*[contains(@resource-id,'comment_txt')]")
	private List<MobileElement> commentText;

	@AndroidFindBy(id = "overflow_icon")
	private List<MobileElement> dotIcon;

	@AndroidFindBy(id = "like_icon")
	private List<MobileElement> likeIcon;

	@AndroidFindBy(id = "like_count")
	private List<MobileElement> likeCountText;

	@AndroidFindBy(id = "reply")
	private List<MobileElement> commentReplyBtn;

	@AndroidFindBy(id = "menu_item_delete")
	private MobileElement commentDeleteBtn;

	@AndroidFindBy(id = "add_image")
	private MobileElement imageUploadBtn;

	@AndroidFindBy(id = "compose_view")
	private List<MobileElement> commentComposeTextField;

	@AndroidFindBy(id = "send")
	private MobileElement commentSendBtn;

	@AndroidFindBy(id = "like_reply_count")
	private List<MobileElement> likeReplyCount;

	@AndroidFindBy(id = "fbMessage")
	private MobileElement deleteToast;

	@AndroidFindBy(id = "reply_comment_txt")
	private List<MobileElement> replyCommentText;

	@AndroidFindBy(id = "reply_count")
	private List<MobileElement> replyCommentCount;

	@AndroidFindBy(id = "empty_text")
	private MobileElement emptyStateText;

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'empty_text')]/preceding-sibling::android.widget.ImageView")
	private MobileElement emptyStateImage;

	@AndroidFindBy(xpath = "//*[@text='Just Now']/following-sibling::android.widget.RelativeLayout/android.widget.TextView")
	private MobileElement sentComment;

	public MobileElement getSentComment() {
		return sentComment;
	}

	public MobileElement getCommentPageHeader() {
		return commentPageHeader;
	}

	public MobileElement getCloseBtn() {
		return closeBtn;
	}

	public List<MobileElement> getUserPic() {
		return userPic;
	}

	public List<MobileElement> getUserName() {
		return userName;
	}

	public List<MobileElement> getCommentSentTime() {
		return commentSentTime;
	}

	public List<MobileElement> getCommentText() {
		return commentText;
	}

	public List<MobileElement> getDotIcon() {
		return dotIcon;
	}

	public List<MobileElement> getLikeIcon() {
		return likeIcon;
	}

	public List<MobileElement> getLikeCountText() {
		return likeCountText;
	}

	public List<MobileElement> getCommentReplyBtn() {
		return commentReplyBtn;
	}

	public MobileElement getCommentDeleteBtn() {
		return commentDeleteBtn;
	}

	public MobileElement getImageUploadBtn() {
		return imageUploadBtn;
	}

	public List<MobileElement> getCommentComposeTextField() {
		return commentComposeTextField;
	}

	public MobileElement getCommentSendBtn() {
		return commentSendBtn;
	}

	public List<MobileElement> getLikeReplyCount() {
		return likeReplyCount;
	}

	public List<MobileElement> getReplyCommentText() {
		return replyCommentText;
	}

	public List<MobileElement> getReplyCommentCount() {
		return replyCommentCount;
	}

	public MobileElement getDeleteToast() {
		return deleteToast;
	}

	public MobileElement getEmptyStateText() {
		return emptyStateText;
	}

	public MobileElement getEmptyStateImage() {
		return emptyStateImage;
	}

	/*-----------------------------------Locator for Image Upload------------------------------------*/

	@AndroidFindBy(id = "take_photo")
	private MobileElement takePhotoBtn;

	@AndroidFindBy(id = "select_gallery")
	private MobileElement selectGalaryBtn;

	@AndroidFindBy(id = "com.android.permissioncontroller:id/permission_message")
	private MobileElement permissionMessage;

	@AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_one_time_button")
	private MobileElement onlyThisTimeBtn;

	@AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
	private MobileElement permissionAllowBtn;

	@AndroidFindBy(accessibility = "Shutter")
	private MobileElement photoClickBtn;

	@AndroidFindBy(accessibility = "Done")
	private MobileElement photoClickDoneBtn;

	@AndroidFindBy(id = "upload_image_view")
	private MobileElement uploadingImageView;

	@AndroidFindBy(id = "upload_cancel")
	private MobileElement uploadCancelBtn;

	@AndroidFindBy(id = "comment_thumb")
	private List<MobileElement> uploadedImageView;

	@AndroidFindBy(id = "com.google.android.documentsui:id/icon_thumb")
	private MobileElement imageFile;

	@AndroidFindBy(id = "crop_image_menu_crop")
	private MobileElement imageCropBtn;

	public MobileElement getImageCropBtn() {
		return imageCropBtn;
	}

	public MobileElement getImageFile() {
		return imageFile;
	}

	public MobileElement getTakePhotoBtn() {
		return takePhotoBtn;
	}

	public MobileElement getSelectGalaryBtn() {
		return selectGalaryBtn;
	}

	public MobileElement getPermissionMessage() {
		return permissionMessage;
	}

	public MobileElement getOnlyThisTimeBtn() {
		return onlyThisTimeBtn;
	}

	public MobileElement getPermissionAllowBtn() {
		return permissionAllowBtn;
	}

	public MobileElement getPhotoClickBtn() {
		return photoClickBtn;
	}

	public MobileElement getPhotoClickDoneBtn() {
		return photoClickDoneBtn;
	}

	public MobileElement getUploadingImageView() {
		return uploadingImageView;
	}

	public MobileElement getUploadCancelBtn() {
		return uploadCancelBtn;
	}

	public List<MobileElement> getUploadedImageView() {
		return uploadedImageView;
	}
	
	/*---------------------------Comment Screen in Video Section------------------------*/
	
	@AndroidFindBy(id = "post")
	private MobileElement commentPostBtn;

	public MobileElement getCommentPostBtn() {
		return commentPostBtn;
	}
	
	@AndroidFindBy(id = "attach")
	private MobileElement attachBtn;

	public MobileElement getAttachBtn() {
		return attachBtn;
	}
	
	@AndroidFindBy(id = "keyBoard")
	private MobileElement keyBoardBtn;

	public MobileElement getKeyBoardBtn() {
		return keyBoardBtn;
	}
	
	@AndroidFindBy(id = "profilePicture")
	private List<MobileElement> profileIcon;

	public List<MobileElement> getProfileIcon() {
		return profileIcon;
	}
	
	@AndroidFindBy(id = "heart_emoji")
	private MobileElement heartEmoji;

	public MobileElement getHeartEmoji() {
		return heartEmoji;
	}
	
	@AndroidFindBy(id = "like_emoji")
	private MobileElement likeEmoji;

	public MobileElement getLikeEmoji() {
		return likeEmoji;
	}
	
	@AndroidFindBy(id = "laugh_emoji")
	private MobileElement laughEmoji;

	public MobileElement getLaughEmoji() {
		return laughEmoji;
	}
	
	@AndroidFindBy(id = "openmouth_emoji")
	private MobileElement openMouthEmoji;

	public MobileElement getOpenMouthEmoji() {
		return openMouthEmoji;
	}
	
	@AndroidFindBy(id = "angry_emoji")
	private MobileElement angryEmoji;

	public MobileElement getAngryEmoji() {
		return angryEmoji;
	}
	
	@AndroidFindBy(id = "sad_emoji")
	private MobileElement sadEmoji;

	public MobileElement getSadEmoji() {
		return sadEmoji;
	}
	
	@AndroidFindBy(id = "alertTitle")
	private MobileElement reportPopUpScreenTitle;

	public MobileElement getReportPopUpScreenTitle() {
		return reportPopUpScreenTitle;
	}
	
	@AndroidFindBy(id = "android:id/text1")
	private List<MobileElement> reportOptionList;

	public List<MobileElement> getReportOptionList() {
		return reportOptionList;
	}
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='REPORT']")
	private MobileElement reportBtn;

	public MobileElement getReportBtn() {
		return reportBtn;
	}
	
	@AndroidFindBy(xpath = "//*[@text='This comment is no longer available as it was reported.']")
	private List<MobileElement> commentReportText;

	public List<MobileElement> getCommentReportText() {
		return commentReportText;
	}
	
	
	
	
}
