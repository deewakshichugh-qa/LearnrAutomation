package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class AccountSettingPage_OR {

	@AndroidFindBy(xpath = "//androidx.appcompat.widget.LinearLayoutCompat/preceding-sibling::android.widget.TextView")
	@iOSXCUITFindBy(accessibility = "Settings")
	private MobileElement settingPageTitle;

	@AndroidFindBy(accessibility = "Navigate up")
	@iOSXCUITFindBy(className = "XCUIElementTypeButton")
	private MobileElement backBtn;

	@AndroidFindBy(accessibility = "More options")
	private MobileElement dotIcon;

	@AndroidFindBy(xpath = "//*[@text='Privacy Policy']")
	private MobileElement privacyPolicyIcon;

	@AndroidFindBy(xpath = "//*[@text='Terms and Privacy Policy']")
	private MobileElement termAndPrivacyPolicyTitle;

	@AndroidFindBy(id = "com.adda247.app:id/feed_change_icon")
	private MobileElement feedChangeIcon;

	@AndroidFindBy(id = "com.adda247.app:id/selected_feed")
	private MobileElement selectedFeedText;

	@AndroidFindBy(xpath = "//*[@text='Choose Feed Layout']")
	private MobileElement chooseFeedLayoutTitle;

	@AndroidFindBy(id = "com.adda247.app:id/close")
	private MobileElement chooseFeedLayoutCloseBtn;

	@AndroidFindBy(id = "com.adda247.app:id/new_feed_view")
	private MobileElement newFeedView;

	@AndroidFindBy(id = "com.adda247.app:id/old_feed_view")
	private MobileElement oldFeedView;

	@AndroidFindBy(id = "com.adda247.app:id/apply")
	private MobileElement applyBtn;

	@AndroidFindBy(id = "com.adda247.app:id/changeContentLanguageButtonContainer_content")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Select Language')]")
	private MobileElement languageChangeIcon;

	@AndroidFindBy(id = "com.adda247.app:id/selected_lang_view")
	private MobileElement selectedLanguageText;

	@AndroidFindBy(id = "com.adda247.app:id/alertTitle")
	@iOSXCUITFindBy(accessibility = "Select Language")
	private MobileElement changeLanguageTitle;

	@AndroidFindBy(id = "android:id/text1")
	@iOSXCUITFindBy(className = "XCUIElementTypeButton")
	private List<MobileElement> languageList;

	@AndroidFindBy(id = "android:id/button1")
	private MobileElement languageSaveBtn;

	@AndroidFindBy(id = "android:id/button2")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Select Language']/following-sibling::XCUIElementTypeOther[1]")
	private MobileElement languageCancelBtn;


	@AndroidFindBy(id = "com.adda247.app:id/changeFontSizeContainer_content")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Content Font Size')]")
	private MobileElement changeFontSizeIcon;

	@AndroidFindBy(id = "com.adda247.app:id/sub_title")
	private MobileElement selectedFontSizeText;

	@AndroidFindBy(xpath = "//android.widget.RadioGroup[contains(@resource-id,'radioGroup_fs')]/preceding-sibling::android.widget.TextView")
	@iOSXCUITFindBy(accessibility = "Content Font Size")
	private MobileElement fontSizeTitle;


	@AndroidFindBy(id = "radioButton_fs_small")
	@iOSXCUITFindBy(accessibility = "Small")
	private MobileElement smallFontSizeBtn;


	@AndroidFindBy(id = "radioButton_fs_medium")
	@iOSXCUITFindBy(accessibility = "Medium (Default)")
	private MobileElement mediumFontSizeBtn;


	@AndroidFindBy(id = "radioButton_fs_large")
	@iOSXCUITFindBy(accessibility = "Large")
	private MobileElement largeFontSizeBtn;

	@AndroidFindBy(id = "com.adda247.app:id/fbMessage")
	private MobileElement fontSizeChangeToast;


	@AndroidFindBy(id = "radioButton_fs_extraLarge")
	@iOSXCUITFindBy(accessibility = "Extra Large")
	private MobileElement extraLargeFontSizeBtn;


	@AndroidFindBy(id = "cancel")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Content Font Size')]/following-sibling::XCUIElementTypeOther")
	private MobileElement fontSizeCancelBtn;

	@AndroidFindBy(id = "com.adda247.app:id/save")
	private MobileElement fontSizeSaveBtn;


	@AndroidFindBy(id = "write_feedback")
	@iOSXCUITFindBy(accessibility = "Feedback")
	private MobileElement feedBackIcon;


	@AndroidFindBy(id = "share_app")
	private MobileElement shareAppIcon;


	@AndroidFindBy(id = "invite_friend_n_earn")
	private MobileElement shareAppWithFriendBtn;

	@AndroidFindBy(xpath = "//*[@text='Share']")
	private MobileElement shareScreenTitle;

	public MobileElement getSettingPageTitle() {
		return settingPageTitle;
	}

	public MobileElement getBackBtn() {
		return backBtn;
	}

	public MobileElement getDotIcon() {
		return dotIcon;
	}

	public MobileElement getPrivacyPolicyIcon() {
		return privacyPolicyIcon;
	}

	public MobileElement getTermAndPrivacyPolicyTitle() {
		return termAndPrivacyPolicyTitle;
	}

	public MobileElement getFeedChangeIcon() {
		return feedChangeIcon;
	}

	public MobileElement getSelectedFeedText() {
		return selectedFeedText;
	}

	public MobileElement getChooseFeedLayoutTitle() {
		return chooseFeedLayoutTitle;
	}

	public MobileElement getChooseFeedLayoutCloseBtn() {
		return chooseFeedLayoutCloseBtn;
	}

	public MobileElement getNewFeedView() {
		return newFeedView;
	}

	public MobileElement getOldFeedView() {
		return oldFeedView;
	}

	public MobileElement getApplyBtn() {
		return applyBtn;
	}

	public MobileElement getLanguageChangeIcon() {
		return languageChangeIcon;
	}

	public MobileElement getSelectedLanguageText() {
		return selectedLanguageText;
	}

	public MobileElement getChangeLanguageTitle() {
		return changeLanguageTitle;
	}

	public List<MobileElement> getLanguageList() {
		return languageList;
	}

	public MobileElement getLanguageSaveBtn() {
		return languageSaveBtn;
	}

	public MobileElement getLanguageCancelBtn() {
		return languageCancelBtn;
	}

	public MobileElement getChangeFontSizeIcon() {
		return changeFontSizeIcon;
	}

	public MobileElement getSelectedFontSizeText() {
		return selectedFontSizeText;
	}

	public MobileElement getFontSizeTitle() {
		return fontSizeTitle;
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

	public MobileElement getFontSizeChangeToast() {
		return fontSizeChangeToast;
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

	public MobileElement getFeedBackIcon() {
		return feedBackIcon;
	}

	public MobileElement getShareAppIcon() {
		return shareAppIcon;
	}

	public MobileElement getShareAppWithFriendBtn() {
		return shareAppWithFriendBtn;
	}

	public MobileElement getShareScreenTitle() {
		return shareScreenTitle;
	}

}
