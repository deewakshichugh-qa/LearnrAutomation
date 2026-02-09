package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CommonStudyMaterialSection_OR {
	
	@AndroidFindBy(xpath = "//*[contains(@text,'Current Affairs')]")
	private MobileElement currentAffairPageTitle;

	@AndroidFindAll({
		@AndroidBy(accessibility = "Navigate up"),
		@AndroidBy(id = "backBtn")
	})
	private MobileElement backBtn;
	
	@AndroidFindBy(id = "action_language_change")
	private MobileElement filterBtn;
	
	@AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[contains(@resource-id,'recyclerView')]/child::android.widget.RelativeLayout/android.widget.TextView")
	private List<MobileElement> studyMaterialTitleList;
	
	//@AndroidFindBy(id = "thumb")
	@AndroidFindBy(xpath = "//android.view.ViewGroup/*[contains(@resource-id,'title')]")
	private List<MobileElement> studyMaterialImgList;
	
	@AndroidFindBy(id = "is_read")
	private List<MobileElement> studyMaterialIsReadIndicator;
	
	@AndroidFindBy(id = "is_read")
	private MobileElement IsReadIndicator;
	
	@AndroidFindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[contains(@resource-id,'recyclerView')]/child::android.view.ViewGroup/android.widget.TextView[contains(@resource-id,'title')]")
	private List<MobileElement> studyMaterialNameList;
	
	@AndroidFindBy(id = "description")
	private List<MobileElement> studyMaterialDescriptionList;
	
	@AndroidFindBy(id = "audio_icon")
	private List<MobileElement> audioIconList;
	
	@AndroidFindBy(id = "text")
	private MobileElement filterInstructionText;

	public MobileElement getFilterInstructionText() {
		return filterInstructionText;
	}

	public MobileElement getCurrentAffairPageTitle() {
		return currentAffairPageTitle;
	}

	public MobileElement getBackBtn() {
		return backBtn;
	}

	public MobileElement getFilterBtn() {
		return filterBtn;
	}

	public List<MobileElement> getStudyMaterialTitleList() {
		return studyMaterialTitleList;
	}

	public List<MobileElement> getStudyMaterialImgList() {
		return studyMaterialImgList;
	}

	public List<MobileElement> getStudyMaterialIsReadIndicator() {
		return studyMaterialIsReadIndicator;
	}

	public MobileElement getIsReadIndicator() {
		return IsReadIndicator;
	}

	public List<MobileElement> getStudyMaterialNameList() {
		return studyMaterialNameList;
	}

	public List<MobileElement> getStudyMaterialDescriptionList() {
		return studyMaterialDescriptionList;
	}

	public List<MobileElement> getAudioIconList() {
		return audioIconList;
	}
	
	@AndroidFindBy(xpath = "//*[@text='Job Alerts']")
	private MobileElement jobAlertPageTitle;
	
	public MobileElement getJobAlertPageTitle() {
		return jobAlertPageTitle;
	}
	@AndroidFindAll({
		@AndroidBy(xpath = "//*[@text='Power Capsules']"),
		@AndroidBy(xpath = "//android.widget.ImageButton[@content-desc='Navigate up']/following-sibling::android.widget.TextView")
	})
	private MobileElement powerCapsulesPageTitle;
	
	public MobileElement getPowerCapsulesPageTitle() {
		return powerCapsulesPageTitle;
	}
	
	@AndroidFindAll({
		@AndroidBy(xpath = "//*[@text='Notes and Articles']"),
		@AndroidBy(xpath = "//*[@text='Articles']")
	})
	private MobileElement notesAndArticlePageTitle;
	
	public MobileElement getNotesAndArticlePageTitle() {
		return notesAndArticlePageTitle;
	}

	
	
	/*------------------------------------Locator present inside study material page-------------------------*/
	

	
	@AndroidFindBy(id = "action_bookmark")
	private MobileElement bookMarkIcon;
	
	@AndroidFindBy(accessibility = "More options")
	private MobileElement dotIcon;
	
	@AndroidFindBy(xpath = "//*[@text='Font Size']")
	private MobileElement fontSizeBtn;
	
	@AndroidFindBy(xpath = "//android.widget.RadioGroup[contains(@resource-id,'radioGroup_fs')]/android.widget.RadioButton")
	private List<MobileElement> fontSizeList;
	
	@AndroidFindBy(id = "radioButton_fs_small")
	private MobileElement smallFontSizeBtn;

	@AndroidFindBy(id = "radioButton_fs_medium")
	private MobileElement mediumFontSizeBtn;

	@AndroidFindBy(id = "radioButton_fs_large")
	private MobileElement largeFontSizeBtn;

	@AndroidFindBy(id = "radioButton_fs_extraLarge")
	private MobileElement extraLargeFontSizeBtn;

	@AndroidFindBy(id = "cancel")
	private MobileElement fontSizeCancelBtn;

	@AndroidFindBy(id = "save")
	private MobileElement fontSizeSaveBtn;
	
	@AndroidFindBy(xpath = "//*[@text='Report Content']")
	private MobileElement reportContentBtn;
	
	@AndroidFindBy(id = "like_comment_count")
	private MobileElement likeCommentCountText;
	
	@AndroidFindBy(id = "like")
	private MobileElement likeBtn;
	
	@AndroidFindBy(id = "comment")
	private MobileElement commentTextField;
	
	@AndroidFindBy(id = "share_link")
	private MobileElement shareBtn;
	
	@AndroidFindBy(id = "android:id/resolver_list")
	private MobileElement shareScreen;
	
	@AndroidFindBy(id = "fbMessage")
	private MobileElement toastMessage;
	
	@AndroidFindBy(id = "action_audio")
	private MobileElement auidoIcon;

	public MobileElement getAuidoIcon() {
		return auidoIcon;
	}

	public MobileElement getToastMessage() {
		return toastMessage;
	}

	public MobileElement getBookMarkIcon() {
		return bookMarkIcon;
	}

	public MobileElement getDotIcon() {
		return dotIcon;
	}

	public MobileElement getLikeCommentCountText() {
		return likeCommentCountText;
	}

	public MobileElement getLikeBtn() {
		return likeBtn;
	}

	public MobileElement getCommentTextField() {
		return commentTextField;
	}

	public MobileElement getShareBtn() {
		return shareBtn;
	}

	public MobileElement getShareScreen() {
		return shareScreen;
	}

	public MobileElement getFontSizeBtn() {
		return fontSizeBtn;
	}

	public List<MobileElement> getFontSizeList() {
		return fontSizeList;
	}

	public MobileElement getSmallFontSizeBtn() {
		return smallFontSizeBtn;
	}

	public MobileElement getMediumFontSizeBtn() {
		return mediumFontSizeBtn;
	}

	public MobileElement getLargeFontSizeBtn() {
		return largeFontSizeBtn;
	}

	public MobileElement getExtraLargeFontSizeBtn() {
		return extraLargeFontSizeBtn;
	}

	public MobileElement getFontSizeCancelBtn() {
		return fontSizeCancelBtn;
	}

	public MobileElement getFontSizeSaveBtn() {
		return fontSizeSaveBtn;
	}

	public MobileElement getReportContentBtn() {
		return reportContentBtn;
	}
	
	/*--------------------------------------------Locator present inside PowerCapsule--------------------------*/
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@resource-id,'toolbar')]/android.widget.TextView")
	private MobileElement powerCapsuleNameTitle;
	
	@AndroidFindAll({
		@AndroidBy(id = "shareBtn"),
		@AndroidBy(id = "action_share")
	})
	private MobileElement powerCapsuleShareBtn;
	
	@AndroidFindBy(id = "action_pdf")
	private MobileElement pdfBtn;
	
	@AndroidFindBy(id = "action_font_size")
	private MobileElement fontSizeChangeBtn;
	
	@AndroidFindBy(id = "action_toc")
	private MobileElement topicBtn;
	
	@AndroidFindBy(id = "name")
	private List<MobileElement> chapterNameList;
	
	@AndroidFindBy(xpath = "//android.view.View[@resource-id='calibre_toc_1']")
	private MobileElement selectedChapterName;
	
	@AndroidFindBy(xpath = "//android.widget.Toast[contains(@text,'Download Successful:')]")
	private MobileElement pdfDownloadToast;
	
	@AndroidFindBy(id = "com.google.android.apps.docs:id/pdf_view")
	private MobileElement pdfView;
	
	@AndroidFindBy(xpath = "//android.widget.ImageButton")
	private MobileElement pdfViewCloseBtn;
	
	@AndroidFindBy(id = "progressBar")
	private MobileElement progressBarIcon;
	
	@AndroidFindBy(id = "downloadBtn")
	private MobileElement downloadBtn;
	
	@AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
	private MobileElement allowBtn;
	

	public MobileElement getAllowBtn() {
		return allowBtn;
	}

	public MobileElement getDownloadBtn() {
		return downloadBtn;
	}

	public MobileElement getProgressBarIcon() {
		return progressBarIcon;
	}

	public MobileElement getPdfDownloadToast() {
		return pdfDownloadToast;
	}

	public MobileElement getPdfView() {
		return pdfView;
	}

	public MobileElement getPdfViewCloseBtn() {
		return pdfViewCloseBtn;
	}

	public MobileElement getPowerCapsuleNameTitle() {
		return powerCapsuleNameTitle;
	}

	public MobileElement getPowerCapsuleShareBtn() {
		return powerCapsuleShareBtn;
	}

	public MobileElement getPdfBtn() {
		return pdfBtn;
	}

	public MobileElement getFontSizeChangeBtn() {
		return fontSizeChangeBtn;
	}

	public MobileElement getTopicBtn() {
		return topicBtn;
	}

	public List<MobileElement> getChapterNameList() {
		return chapterNameList;
	}

	public MobileElement getSelectedChapterName() {
		return selectedChapterName;
	}

}
