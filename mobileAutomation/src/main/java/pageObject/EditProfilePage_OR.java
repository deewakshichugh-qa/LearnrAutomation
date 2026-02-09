package pageObject;

import java.util.List;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class EditProfilePage_OR {

	@AndroidFindBy(accessibility = "Personal Information")
	@iOSXCUITFindBy(accessibility = "Personal Information")
	private MobileElement personalInfoPageTitle;

	@AndroidFindBy(accessibility = "btn_appbar_back")
	@iOSXCUITFindBy(accessibility = "pressedBack")
	private MobileElement personalInfoBackBtn;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'btn_mobile_update')]/parent::android.widget.EditText")
	@iOSXCUITFindBy(accessibility = "openTextFormField\nMobile Number")
	private MobileElement mobileNumberTextFieldNew;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'btn_mobile_update')]")
	private MobileElement mobileNoUpdateBtn;

	@AndroidFindBy(accessibility = "VERIFY")
	@iOSXCUITFindBy(accessibility = "VERIFY")
	private MobileElement verifyTag;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=' New password']/preceding-sibling::android.widget.EditText")
	@iOSXCUITFindBy(accessibility = "openTextFormField\nCurrent Password")
	private MobileElement currentPasswordTextFieldNew;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=' New password']/following-sibling::android.widget.EditText[2]")
	@iOSXCUITFindBy(accessibility = "openTextFormField\nConfirm New Password")
	private MobileElement confirmNewPwdTextFieldNew;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=' New password']/following-sibling::android.widget.EditText[1]")
	@iOSXCUITFindBy(accessibility = "openTextFormField\nNew Password")
	private MobileElement newPwdTextFieldNew;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'btn_confirm')]")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'saveLocator')]")
	private MobileElement saveBtnNew;

	@AndroidFindBy(accessibility = "Change your password")
	@iOSXCUITFindBy(accessibility = "Change Password")
	private MobileElement changePwdPageTitleNew;

	@AndroidFindBy(accessibility = "Create Password")
	private MobileElement createPasswordTitle;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc='Date of birth']/following-sibling::android.widget.ImageView[1]")
	@iOSXCUITFindBy(accessibility = "selectDateOfPickerLocator")
	private MobileElement dobTextFieldNew;

	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Select year']/preceding-sibling::android.widget.Button")
	private MobileElement dobEditBtn;

	@AndroidFindBy(className = "android.widget.EditText")
	private MobileElement enterDateTextField;

	@AndroidFindBy(accessibility = "OK")
	private MobileElement selectDobPageOkBtn;

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'category_text_field')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name,'selectCategoriesLocator')][2]")
	private MobileElement categorySelectDropDownFieldNew;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc='Select Category']/following-sibling::android.view.View")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Select Category']/following-sibling::XCUIElementTypeStaticText")
	private List<MobileElement> categoryListNew;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc='About']/following-sibling::android.widget.EditText")
	@iOSXCUITFindBy(accessibility = "openTextFormField\nAbout")
	private MobileElement aboutTextFieldNew;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'user_name')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'gmail.com')]/preceding-sibling::XCUIElementTypeStaticText")
	private MobileElement userNameNew;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'btn_email_update')]/parent::android.widget.EditText")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'gmail.com')]")
	private MobileElement userEmailNew;

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'btn_email_update')]")
	private MobileElement emailUpdateBtn;

	@AndroidFindBy(accessibility = "btn_camera")
	@iOSXCUITFindBy(accessibility = "openBottomSheet")
	private MobileElement profilePicUpLoadIconNew;

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'Select from gallery')]")
	@iOSXCUITFindBy(accessibility = "Select from gallery")
	private MobileElement selectFromGalleryTab;

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'Open Camera')]")
	@iOSXCUITFindBy(accessibility = "openCamera")
	private MobileElement openCameraTab;

	@AndroidFindBy(id = "com.google.android.providers.media.module:id/icon_thumbnail")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[contains(@name,'Photo')]")
	private List<MobileElement> imageFile;

	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Crop\"]")
	@iOSXCUITFindBy(accessibility = "Done")
	private MobileElement cropBtn;

	@AndroidFindBy(accessibility = "Please wait...")
	private MobileElement pwdSaveLoader;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"email_text_field\"]/android.widget.EditText[1]")
	@iOSXCUITFindBy(accessibility = "openTextFormField\nName")
	private MobileElement userNameTextField;

	@AndroidFindBy(accessibility = "User profile is updated successfully")
	@iOSXCUITFindBy(accessibility = "User profile is updated successfully")
	private MobileElement profileUpdateSuccessfulToast;

	@AndroidFindBy(accessibility = "Password Changed Successfully")
	@iOSXCUITFindBy(accessibility = "Password Changed Successfully")
	private MobileElement pwdChangedSuccessfulToast;

	@AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_one_time_button")
	@iOSXCUITFindBy(accessibility = "Allow Access to All Photos")
	private MobileElement onlyThisTimeBtn;

	@AndroidFindBy(accessibility = "Shutter")
	private MobileElement picTakeBtn;

	@AndroidFindBy(accessibility = "Done")
	@iOSXCUITFindBy(accessibility = "Done")
	private MobileElement pictureDoneBtn;

	@AndroidFindBy(xpath = "//*[@content-desc='Category']/preceding-sibling::*[contains(@content-desc,'selectCategoriesLocator')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[contains(@name,'selectCategoriesLocator')][1]")
	private MobileElement genderDropDown;

	@AndroidFindBy(xpath = "//*[@content-desc='Select Gender']/following-sibling::android.view.View")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Select Gender']/following-sibling::XCUIElementTypeStaticText")
	private List<MobileElement> genderDropDownList;

	@AndroidFindBy(accessibility = "Successfully updated")
	private MobileElement updateToastMessage;

	public MobileElement getPersonalInfoPageTitle() {
		return personalInfoPageTitle;
	}

	public MobileElement getPersonalInfoBackBtn() {
		return personalInfoBackBtn;
	}

	public MobileElement getMobileNumberTextFieldNew() {
		return mobileNumberTextFieldNew;
	}

	public MobileElement getVerifyTag() {
		return verifyTag;
	}

	public MobileElement getCurrentPasswordTextFieldNew() {
		return currentPasswordTextFieldNew;
	}

	public MobileElement getConfirmNewPwdTextFieldNew() {
		return confirmNewPwdTextFieldNew;
	}

	public MobileElement getNewPwdTextFieldNew() {
		return newPwdTextFieldNew;
	}

	public MobileElement getSaveBtnNew() {
		return saveBtnNew;
	}

	public MobileElement getChangePwdPageTitleNew() {
		return changePwdPageTitleNew;
	}

	public MobileElement getDobTextFieldNew() {
		return dobTextFieldNew;
	}

	public MobileElement getDobEditBtn() {
		return dobEditBtn;
	}

	public MobileElement getEnterDateTextField() {
		return enterDateTextField;
	}

	public MobileElement getSelectDobPageOkBtn() {
		return selectDobPageOkBtn;
	}

	public MobileElement getCategorySelectDropDownFieldNew() {
		return categorySelectDropDownFieldNew;
	}

	public List<MobileElement> getCategoryListNew() {
		return categoryListNew;
	}

	public MobileElement getAboutTextFieldNew() {
		return aboutTextFieldNew;
	}

	public MobileElement getUserNameNew() {
		return userNameNew;
	}

	public MobileElement getUserEmailNew() {
		return userEmailNew;
	}

	public MobileElement getProfilePicUpLoadIconNew() {
		return profilePicUpLoadIconNew;
	}

	public MobileElement getSelectFromGalleryTab() {
		return selectFromGalleryTab;
	}

	public MobileElement getOpenCameraTab() {
		return openCameraTab;
	}

	public List<MobileElement> getImageFile() {
		return imageFile;
	}

	public MobileElement getCropBtn() {
		return cropBtn;
	}

	public MobileElement getPwdSaveLoader() {
		return pwdSaveLoader;
	}

	public MobileElement getProfileUpdateSuccessfulToast() {
		return profileUpdateSuccessfulToast;
	}

	public MobileElement getPwdChangedSuccessfulToast() {
		return pwdChangedSuccessfulToast;
	}

	public MobileElement getUserNameTextField() {
		return userNameTextField;
	}

	public MobileElement getOnlyThisTimeBtn() {
		return onlyThisTimeBtn;
	}

	public MobileElement getPicTakeBtn() {
		return picTakeBtn;
	}

	public MobileElement getPictureDoneBtn() {
		return pictureDoneBtn;
	}

	public MobileElement getGenderDropDown() {
		return genderDropDown;
	}

	public List<MobileElement> getGenderDropDownList() {
		return genderDropDownList;
	}

	public MobileElement getMobileNoUpdateBtn() {
		return mobileNoUpdateBtn;
	}

	public MobileElement getEmailUpdateBtn() {
		return emailUpdateBtn;
	}

	public MobileElement getCreatePasswordTitle() {
		return createPasswordTitle;
	}

	public MobileElement getUpdateToastMessage() {
		return updateToastMessage;
	}

}
