package applicationUtil;

import java.util.ArrayList;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.UserDetailsLayerPage_OR;
import util.Common_Function;
import util.ConfigFileReader;

public class UserDetailsLayerUtil {

	FeedbackFormUtil feedbackFormUtilObj;
	OrderSuccessUtil orderSuccessUtilObj;
	UserDetailsLayerPage_OR userDetailsLayerPageObj;
	public ArrayList<String> userDetailsLayerMsgList = new ArrayList<>();
	public Common_Function cfObj = new Common_Function();

	public UserDetailsLayerUtil(AppiumDriver<MobileElement> driver) {
		userDetailsLayerPageObj = new UserDetailsLayerPage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), userDetailsLayerPageObj);
	}

	public boolean verifyUserDetailsLayer(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, userDetailsLayerPageObj.getTextFieldFullName(), 10);
			if (!result) {
				userDetailsLayerMsgList.add("Unable to verify self Full name text field in User detail layer");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userDetailsLayerPageObj.getTextFieldPhoneNumber(),
					10);
			if (!result) {
				userDetailsLayerMsgList.add("Unable to verify self Phone Number text field in User detail layer");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, userDetailsLayerPageObj.getbtnContinue(), 10);
			if (!result) {
				userDetailsLayerMsgList.add("Unable to verify self continue btn in User detail layer");
				return result;
			}

		} catch (Exception e) {
			result = false;
			userDetailsLayerMsgList.add("verifyUserDetailsLayer Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean fillUserDetailsForm(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		boolean available = true;
		try {

			result = enterMobileNumber(driver);
			if (!result) {
				userDetailsLayerMsgList.add("Failed to enter mobile number.");
				return result;
			}

			available = cfObj.commonWaitForElementToBeVisible(driver, userDetailsLayerPageObj.getTextFieldPinCode(),
					10);
			if (available) { // if package contains printed books, needs address.

				result = enterPinCode(driver);
				if (!result) {
					userDetailsLayerMsgList.add("Unable to enter pin code");
					return result;
				}

				result = enterAdressLineOne(driver);
				if (!result) {
					userDetailsLayerMsgList.add("Unable to enter Address line one");
					return result;
				}

				if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {

					result = enterAdressLineTwo(driver);
					if (!result) {
						userDetailsLayerMsgList.add("Unable to enter Address line two");
						return result;
					}
				}
				result = enterLandmark(driver);
				if (!result) {
					userDetailsLayerMsgList.add("Unable to enter landmark");
					return result;
				}
			}

		} catch (Exception e) {
			userDetailsLayerMsgList.add("fillUserDetailsForm_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean enterMobileNumber(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			String strMobileNumber = Common_Function.randomPhoneNumber(10, "9");

			cfObj.commonClick(userDetailsLayerPageObj.getTextFieldPhoneNumber());
			userDetailsLayerPageObj.getTextFieldPhoneNumber().clear();
			Thread.sleep(2000);
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "android:id/autofill_dataset_picker", "id",
					5);
			if (result) {
				cfObj.tapOnCenter(driver);
			} else {
				result = true;
			}

			result = cfObj.commonSetTextTextBox(userDetailsLayerPageObj.getTextFieldPhoneNumber(), strMobileNumber);
			if (!result) {
				userDetailsLayerMsgList.add("Failed to enter mobile number on text field.");
			}

		} catch (Exception e) {
			result = false;
			userDetailsLayerMsgList.add("enterMobileNumber Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean enterPinCode(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userDetailsLayerPageObj.getTextFieldPinCode(), 10);
			if (result) {

				cfObj.commonClick(userDetailsLayerPageObj.getTextFieldPinCode());
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "android:id/autofill_dataset_picker",
						"id", 5);
				if (result) {
					cfObj.tapOnCenter(driver);
				} else {
					result = true;
					userDetailsLayerPageObj.getTextFieldPinCode().clear();
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
							"android:id/autofill_dataset_picker", "id", 5);
					if (result) {
						cfObj.tapOnCenter(driver);
					} else {
						result = true;
					}
				}

				result = cfObj.commonSetTextTextBox(userDetailsLayerPageObj.getTextFieldPinCode(), "143001");
				if (!result) {
					userDetailsLayerMsgList.add("Failed to enter PinCode.");
				}
			}

		} catch (Exception e) {
			result = false;
			userDetailsLayerMsgList.add("enterPinCode Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean enterAdressLineOne(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userDetailsLayerPageObj.getTextFieldAddressLineOne(),
					10);
			if (result) {

				cfObj.commonClick(userDetailsLayerPageObj.getTextFieldAddressLineOne());
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "android:id/autofill_dataset_picker",
						"id", 5);
				if (result) {
					cfObj.tapOnCenter(driver);
				} else {
					result = true;
					userDetailsLayerPageObj.getTextFieldAddressLineOne().clear();
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
							"android:id/autofill_dataset_picker", "id", 5);
					if (result) {
						cfObj.tapOnCenter(driver);
					} else {
						result = true;
					}
				}

				result = cfObj.commonSetTextTextBox(userDetailsLayerPageObj.getTextFieldAddressLineOne(), "Flat 100");
				if (!result) {
					userDetailsLayerMsgList.add("Failed to enter AddressLine1.");
				}
			}

		} catch (Exception e) {
			result = false;
			userDetailsLayerMsgList.add("enterAdressLineOne Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean enterAdressLineTwo(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userDetailsLayerPageObj.getTextFieldAddressLineTwo(),
					10);
			if (result) {

				cfObj.commonClick(userDetailsLayerPageObj.getTextFieldAddressLineTwo());
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "android:id/autofill_dataset_picker",
						"id", 5);
				if (result) {
					cfObj.tapOnCenter(driver);
				} else {
					result = true;
					userDetailsLayerPageObj.getTextFieldAddressLineTwo().clear();
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
							"android:id/autofill_dataset_picker", "id", 5);
					if (result) {
						cfObj.tapOnCenter(driver);
					} else {
						result = true;
					}
				}

				result = cfObj.commonSetTextTextBox(userDetailsLayerPageObj.getTextFieldAddressLineTwo(),
						"Model Town");
				if (!result) {
					userDetailsLayerMsgList.add("Failed to enter Address Line 2.");
				}
			}

		} catch (Exception e) {
			result = false;
			userDetailsLayerMsgList.add("enterAddressLineTwo Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean enterLandmark(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userDetailsLayerPageObj.getTextFieldLandmark(), 10);
			if (result) {

				cfObj.commonClick(userDetailsLayerPageObj.getTextFieldLandmark());
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "android:id/autofill_dataset_picker",
						"id", 5);
				if (result) {
					cfObj.tapOnCenter(driver);
				} else {
					result = true;
					userDetailsLayerPageObj.getTextFieldLandmark().clear();
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
							"android:id/autofill_dataset_picker", "id", 5);
					if (result) {
						cfObj.tapOnCenter(driver);
					} else {
						result = true;
					}
				}

				result = cfObj.commonSetTextTextBox(userDetailsLayerPageObj.getTextFieldLandmark(), "New Park");
				if (!result) {
					userDetailsLayerMsgList.add("Failed to enter LandMark.");
				}
			}

		} catch (Exception e) {
			result = false;
			userDetailsLayerMsgList.add("enterLandmark Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean enterCity(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userDetailsLayerPageObj.getTextFieldCity(), 10);
			if (result) {
				result = cfObj.commonSetTextTextBox(userDetailsLayerPageObj.getTextFieldCity(), "Amritsar");
				if (!result) {
					userDetailsLayerMsgList.add("Failed to enter City name.");
				}
			}

		} catch (Exception e) {
			result = false;
			userDetailsLayerMsgList.add("enterCity Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean enterState(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userDetailsLayerPageObj.getTextFieldState(), 10);
			if (result) {
				result = cfObj.commonSetTextTextBox(userDetailsLayerPageObj.getTextFieldState(), "Punjab");
				if (!result) {
					userDetailsLayerMsgList.add("Failed to enter State name.");
				}
			}

		} catch (Exception e) {
			result = false;
			userDetailsLayerMsgList.add("enterState Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickContinueBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.commonClick(userDetailsLayerPageObj.getbtnContinue());
			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='Confirm Delivery Address']",
					"xpath", 10)) {
				cfObj.commonClick(cfObj.commonGetElements(driver, "android.widget.Button", "class").get(1));
			}

			feedbackFormUtilObj = new FeedbackFormUtil(driver);
			result = feedbackFormUtilObj.veriyFeedbackForm(driver);
			if (!result) {
				userDetailsLayerMsgList.add("Failed to verify FeedBack form.");
				result = true;
			}
		} catch (Exception e) {
			result = false;
			userDetailsLayerMsgList.add("clickContinueBtn Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnContinueBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, userDetailsLayerPageObj.getbtnContinue(), 10);
			if (!result) {
				userDetailsLayerMsgList.add("Continue button is not visible.");
				return result;
			}
			cfObj.commonClick(userDetailsLayerPageObj.getbtnContinue());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/design_bottom_sheet", "id", 10);
			if (result) {
				cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.TextView[@text='I Confirm']", "xpath"));
			} else {
				result = true;
			}
		} catch (Exception e) {
			result = false;
			userDetailsLayerMsgList.add("clickOnContinueBtn_Exception: " + e.getMessage());
		}
		return result;
	}
}
