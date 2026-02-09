package pageObject;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class UserDetailsLayerPage_OR {


	@AndroidFindBy(id = "full_name_et")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[1]")
	private MobileElement textFieldFullName;

	public MobileElement getTextFieldFullName() {
		return textFieldFullName;
	}

	@AndroidFindBy(id = "phone_number_et")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[2]")
	private MobileElement textFieldPhoneNumber;

	public MobileElement getTextFieldPhoneNumber() {
		return textFieldPhoneNumber;
	}

	@AndroidFindBy(id = "com.adda247.app:id/save_address")
	@iOSXCUITFindBy(xpath = "//*[@name='Continue']")
	private MobileElement btnContinue;

	public MobileElement getbtnContinue() {
		return btnContinue;
	}

	@AndroidFindBy(id = "pincode_et")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[3]")
	private MobileElement textFieldPinCode;

	public MobileElement getTextFieldPinCode() {
		return textFieldPinCode;
	}

	@AndroidFindBy(id = "flat_number_et")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[4]")
	private MobileElement textFieldAddressLineOne;

	public MobileElement getTextFieldAddressLineOne() {
		return textFieldAddressLineOne;
	}

	@AndroidFindBy(id = "area_et")
	private MobileElement textFieldAddressLineTwo;

	public MobileElement getTextFieldAddressLineTwo() {
		return textFieldAddressLineTwo;
	}

	@AndroidFindBy(id = "landmark_et")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[5]")
	private MobileElement textFieldLandmark;

	public MobileElement getTextFieldLandmark() {
		return textFieldLandmark;
	}

	@AndroidFindBy(id = "city_et")
	private MobileElement textFieldCity;

	public MobileElement getTextFieldCity() {
		return textFieldCity;
	}

	@AndroidFindBy(id = "state_et")
	private MobileElement textFieldState;

	public MobileElement getTextFieldState() {
		return textFieldState;
	}



}
