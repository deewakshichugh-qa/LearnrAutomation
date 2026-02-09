package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import applicationUtil.LoginUtil.OtpType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.EditProfilePage_OR;
import util.Common_Function;
import util.Common_Function.direction;
import util.ConfigFileReader;

public class EditProfileUtil {

	LoginUtil loginUtilObj;

	EditProfilePage_OR editProfilePage_OR;
	List<String> editProfileMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();

	public EditProfileUtil(AppiumDriver<MobileElement> driver) {
		editProfilePage_OR = new EditProfilePage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), editProfilePage_OR);
	}

	public boolean validatePersonalInformationPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getPersonalInfoPageTitle(), 10);
			if (!result) {
				editProfileMsgList.add("PersonalInfoPageTitle is not visible.");
            }

			result = cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getProfilePicUpLoadIconNew(), 10);
			if (!result) {
				editProfileMsgList.add("ProfilePicUpLoadIconNew is not visible.");
            }

			result = cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getUserNameNew(), 10);
			if (!result) {
				editProfileMsgList.add("UserNameNew is not visible.");
            }

			result = cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getUserEmailNew(), 10);
			if (!result) {
				editProfileMsgList.add("UserEmailNew is not visible.");
            }

			result = cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getUserNameTextField(), 10);
			if (!result) {
				editProfileMsgList.add("UserNameTextField is not visible.");
            }

			cfObj.scrollUtill(driver, 1, direction.DOWN);

			result = cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getMobileNumberTextFieldNew(),
					10);
			if (!result) {
				editProfileMsgList.add("MobileNumberTextFieldNew is not visible.");
            }

			result = cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getDobTextFieldNew(), 10);
			if (!result) {
				editProfileMsgList.add("DobTextFieldNew is not visible.");
            }

//			result = cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getGenderDropDown(), 10);
//			if(!result) {
//				editProfileMsgList.add("GenderDropDown is not visible.");
//				result = false;
//			}

			result = cfObj.commonWaitForElementToBeVisible(driver,
					editProfilePage_OR.getCategorySelectDropDownFieldNew(), 10);
			if (!result) {
				editProfileMsgList.add("CategorySelectDropDownFieldNew is not visible.");
            }

			result = cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getAboutTextFieldNew(), 10);
			if (!result) {
				editProfileMsgList.add("AboutTextFieldNew is not visible.");
            }

			result = cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getSaveBtnNew(), 10);
			if (!result) {
				editProfileMsgList.add("SaveBtnNew is not visible.");
            }

			cfObj.scrollUtill(driver, 1, direction.UP);

		} catch (Exception e) {
			editProfileMsgList.add("validatePersonalInformationPage_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean enterMobileNumberAndValidate(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getMobileNumberTextFieldNew(),
					10);
			if (!result) {
				editProfileMsgList.add("MobileNumberTextFieldNew is not visible.");
				result = false;
			}
			List<MobileElement> name = null;
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				name = cfObj.commonGetElements(driver,
						"//*[@content-desc='Password']/preceding-sibling::android.widget.EditText", "xpath");
				result = cfObj.commonSetTextTextBox(name.get(name.size() - 1), "9876543212");
			} else {
				result = cfObj.commonSetTextTextBox(editProfilePage_OR.getMobileNumberTextFieldNew(), "9876543212");
			}
			if (!result) {
				editProfileMsgList.add("Not able to enter mobile number in MobileNumberTextFieldNew.");
				result = false;
			}
			result = cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getVerifyTag(), 10);
			if (!result) {
				editProfileMsgList.add("VerifyTag is not visible.");
				result = false;
			}
			Thread.sleep(2000);
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				name.get(name.size() - 1).clear();
			} else {
				editProfilePage_OR.getMobileNumberTextFieldNew().clear();
			}
			result = !cfObj.commonVerifyValueTextBox(editProfilePage_OR.getMobileNumberTextFieldNew(), "9876543212");
			if (!result) {
				editProfileMsgList.add("MobileNumberTextFieldNew is not Clear.");
				result = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			editProfileMsgList.add("enterMobileNumberAndValidate_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean setAccountHolderGender(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String attribute = null;
		try {
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				attribute = "content-desc";
			} else {
				attribute = "name";
			}
			cfObj.commonClick(editProfilePage_OR.getGenderDropDown());
			Thread.sleep(2000);
			result = cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getGenderDropDownList().get(0),
					10);
			if (!result) {
				editProfileMsgList.add("Gender list is not visible.");
				return result;
			}
			String gender = editProfilePage_OR.getGenderDropDownList().get(0).getAttribute(attribute);
			if (gender == null) {
				editProfileMsgList.add("Gender text is null.");
				return false;
			}
			cfObj.commonClick(editProfilePage_OR.getGenderDropDownList().get(0));

			result = editProfilePage_OR.getGenderDropDown().getAttribute(attribute).contains(gender);
			if (!result) {
				editProfileMsgList.add("Selected Gender is not visible in Profile.");
				return result;
			}

		} catch (Exception e) {
			e.printStackTrace();
			editProfileMsgList.add("setAccountHolderGender_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean setAccountHolderCategoryNew(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String attribute = null;
		try {
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				attribute = "content-desc";
			} else {
				attribute = "name";
			}

			cfObj.commonClick(editProfilePage_OR.getCategorySelectDropDownFieldNew());
			Thread.sleep(2000);
			result = cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getCategoryListNew().get(0), 10);
			if (!result) {
				editProfileMsgList.add("Category list is not visible.");
				return result;

			}
			int size = editProfilePage_OR.getCategoryListNew().size() - 1;
			int randomNum = Common_Function.RandomNumber(1, size);
			String category = editProfilePage_OR.getCategoryListNew().get(randomNum).getAttribute(attribute);
			if (category == null) {
				editProfileMsgList.add("Category text is null.");
				return false;
			}
			cfObj.commonClick(editProfilePage_OR.getCategoryListNew().get(randomNum));

			result = editProfilePage_OR.getCategorySelectDropDownFieldNew().getAttribute(attribute).contains(category);
			if (!result) {
				editProfileMsgList.add("Selected Category is not visible in Profile.");
				return result;
			}

		} catch (Exception e) {
			e.printStackTrace();
			editProfileMsgList.add("setAccountHolderCategoryNew_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean setAccountHolderDOBNew(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			cfObj.commonClick(editProfilePage_OR.getDobTextFieldNew());
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getDobEditBtn(), 10);
				if (!result) {
					editProfileMsgList.add("DOB edit button is not visible.");
					return result;
				}
				cfObj.commonClick(editProfilePage_OR.getDobEditBtn());

				result = cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getEnterDateTextField(), 10);
				if (!result) {
					editProfileMsgList.add("EnterDateTextField is not visible.");
					return result;
				}

				result = cfObj.commonSetTextTextBox(editProfilePage_OR.getEnterDateTextField(), "05/02/2000");
				if (!result) {
					editProfileMsgList.add("Not able to enter date in DOB text field.");
					return result;
				}
				cfObj.commonClick(editProfilePage_OR.getSelectDobPageOkBtn());
				result = !cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getSelectDobPageOkBtn(), 10);
				if (!result) {
					editProfileMsgList.add("DOB Ok button is not clickable.");
					return result;
				}
			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//XCUIElementTypeStaticText[@name='Select Your DOB']", "xpath", 10);
				if (!result) {
					editProfileMsgList.add("Select Your DOB screen is not visible.");
					return result;
				}
				cfObj.commonClick(cfObj.commonGetElement(driver,
						"//XCUIElementTypeStaticText[@name='Select Your DOB']/following-sibling::XCUIElementTypeOther[1]",
						"xpath"));
				result = !cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//XCUIElementTypeStaticText[@name='Select Your DOB']", "xpath", 10);
				if (!result) {
					editProfileMsgList.add("Not able to close SelectYourDOB screen.");
					return result;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			editProfileMsgList.add("setAccountHolderDOBNew_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyChangePasswordActionNew(AppiumDriver<MobileElement> driver, String currentPwd, String newPwd) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getCreatePasswordTitle(), 10);
			if (!result) {
				editProfileMsgList.add("ChangePwdPageTitleNew is not visible.");
				result = false;
			}

			result = cfObj.commonSetTextTextBox(editProfilePage_OR.getCurrentPasswordTextFieldNew(), currentPwd);
			if (!result) {
				editProfileMsgList.add("Not able to enter current password.");
				result = false;

			}

			result = cfObj.commonSetTextTextBox(editProfilePage_OR.getNewPwdTextFieldNew(), newPwd);
			if (!result) {
				editProfileMsgList.add("Not able to enter new password.");
				result = false;
			}

			result = cfObj.commonSetTextTextBox(editProfilePage_OR.getConfirmNewPwdTextFieldNew(), newPwd);
			if (!result) {
				editProfileMsgList.add("Not able to enter confirm new password.");
				result = false;

			}

			result = cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getSaveBtnNew(), 10);
			if (!result) {
				editProfileMsgList.add("Password update button is not visible.");
				result = false;

			}

			cfObj.commonClick(editProfilePage_OR.getSaveBtnNew());

//			result = cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getPwdChangedSuccessfulToast(), 10);
//			if(!result) {
//				editProfileMsgList.add("PwdChangedSuccessfulToast is not visible.");
//				result=false;
//
//			}

			result = !(cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getChangePwdPageTitleNew(),
					10));
			if (!result) {
				editProfileMsgList.add("Not able to save the changed password.");
				result = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			editProfileMsgList.add("verifyChangePasswordActionNew_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean updatePasswordForNewUser(AppiumDriver<MobileElement> driver, String newPwd) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getCreatePasswordTitle(), 10);
			if (!result) {
				editProfileMsgList.add("CreatePwdPageTitle is not visible.");
            }

			result = cfObj.commonSetTextTextBox(editProfilePage_OR.getNewPwdTextFieldNew(), newPwd);
			if (!result) {
				editProfileMsgList.add("Not able to enter new password.");
            }

			result = cfObj.commonSetTextTextBox(editProfilePage_OR.getConfirmNewPwdTextFieldNew(), newPwd);
			if (!result) {
				editProfileMsgList.add("Not able to enter confirm new password.");
            }

			result = cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getSaveBtnNew(), 10);
			if (!result) {
				editProfileMsgList.add("Password update button is not visible.");
            }

			cfObj.commonClick(editProfilePage_OR.getSaveBtnNew());

			result = !(cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getChangePwdPageTitleNew(),
					10));
			if (!result) {
				editProfileMsgList.add("Not able to save the changed password.");
            }

			result = validateChangePasswordSuccessfullyPopUp(driver);
			if (!result) {
				editProfileMsgList.add("Failed to validate ChangePasswordSuccessfullyPopUp.");
            }

		} catch (Exception e) {
			editProfileMsgList.add("updatePasswordForNewUser_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean validateChangePasswordSuccessfullyPopUp(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//*[contains(@content-desc,'Your password has been')]", "xpath", 30)) {
				cfObj.commonClick(
						cfObj.commonGetElement(driver, "//*[contains(@content-desc,'btn_sign_in')]", "xpath"));
			}
			cfObj.waitTillElementIsVisible(driver, 5, 2000,
					cfObj.commonGetElement(driver, "android:id/progress", "id"));
//			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,'****')]", "xpath", 10);
//			if(!result) {
//				editProfileMsgList.add("Failed to click Ok button on Password change successfully popup screen.");
//				return result;
//			}
		} catch (Exception e) {
			e.printStackTrace();
			editProfileMsgList.add("validateChangePasswordSuccessfullyPopUp_Exception" + e.getMessage());
			result = false;
		}
		return result;

	}

	public boolean enterTextInAboutField(AppiumDriver<MobileElement> driver, String detailsText) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getAboutTextFieldNew(), 10);
			if (!result) {
				editProfileMsgList.add("AboutTextFieldNew is not visible.");
				return result;
			}

			result = cfObj.commonSetTextTextBox(editProfilePage_OR.getAboutTextFieldNew(), detailsText);
			if (!result) {
				editProfileMsgList.add("Not able to enter Text in About text field.");
				return result;
			}

		} catch (Exception e) {
			e.printStackTrace();
			editProfileMsgList.add("enterTextInAboutField_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnSaveButtonNew(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			Thread.sleep(1000);
			cfObj.scrollUtill(driver, 1, direction.DOWN);
			result = cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getSaveBtnNew(), 10);
			if (!result) {
				editProfileMsgList.add("Save button is not visible.");
				result = false;
			}

			if (editProfilePage_OR.getSaveBtnNew().isEnabled()) {
				cfObj.commonClick(editProfilePage_OR.getSaveBtnNew());

				if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios"))
					result = true;
			} else {
				editProfileMsgList.add("Not able to click Save button");
				return result;
			}

			cfObj.waitTillElementIsVisible(driver, 3, 2000, editProfilePage_OR.getSaveBtnNew());

		} catch (Exception e) {
			e.printStackTrace();
			editProfileMsgList.add("clickOnSaveButtonNew_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean setProfilePicThroughGalleryTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getProfilePicUpLoadIconNew(), 30);
			if (!result) {
				editProfileMsgList.add("ProfilePicUpLoadIconNew is not visible.");
				return result;
			}
			cfObj.commonClick(editProfilePage_OR.getProfilePicUpLoadIconNew());

			result = cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getOpenCameraTab(), 10);
			if (!result) {
				editProfileMsgList.add("OpenCameraTab is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getSelectFromGalleryTab(), 10);
			if (!result) {
				editProfileMsgList.add("SelectFromGalleryTab is not visible.");
				return result;
			}
			cfObj.commonClick(editProfilePage_OR.getSelectFromGalleryTab());

			if (ConfigFileReader.strRunMode.equalsIgnoreCase("cloud")) {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"com.google.android.providers.media.module:id/icon_thumbnail", "id", 30);
				if (!result) {
					editProfileMsgList.add("ImageFile is not visible.");
					return result;
				}

				cfObj.commonClick(cfObj
						.commonGetElements(driver, "com.google.android.providers.media.module:id/icon_thumbnail", "id")
						.get(0));
			} else {

				Thread.sleep(1000);

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"com.google.android.providers.media.module:id/icon_thumbnail", "id", 30);
				if (!result) {
					editProfileMsgList.add("ImageFile is not visible.");
					return result;
				}

				cfObj.commonClick(editProfilePage_OR.getImageFile().get(0));
			}
			Thread.sleep(3000);// For handling pageLoading

			result = cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getCropBtn(), 10);
			if (!result) {
				editProfileMsgList.add("Crop button is not visible.");
				return result;
			}

			cfObj.commonClick(editProfilePage_OR.getCropBtn());

			Thread.sleep(3000);

			result = !cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getCropBtn(), 5);
			if (!result) {
				editProfileMsgList.add("Crop button is not Clickable.");
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
			editProfileMsgList.add("setProfilePicThroughGalleryTab_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean enterAccountHolderName(AppiumDriver<MobileElement> driver, String name) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getUserNameTextField(), 10);
			if (!result) {
				editProfileMsgList.add("UserNameTextField is not visible.");
				result = false;
			}
			editProfilePage_OR.getUserNameTextField().click();
			Thread.sleep(1000);
			editProfilePage_OR.getUserNameTextField().clear();
			result = cfObj.commonSetTextTextBox(editProfilePage_OR.getUserNameTextField(), name);
			if (!result) {
				editProfileMsgList.add("Not able to enter name in UserNameTextField.");
				result = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			editProfileMsgList.add("enterAccountHolderName_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean validateProfileUpdateSuccessfulToast(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, editProfilePage_OR.getProfileUpdateSuccessfulToast(),
					10);
			if (!result) {
				editProfileMsgList.add("ProfileUpdateSuccessfulToast is not visible.");
				result = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			editProfileMsgList.add("validateProfileUpdateSuccessfulToast_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean updateEmailId(AppiumDriver<MobileElement> driver, String userEmailId) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[contains(@content-desc,'btn_email_update')]/parent::android.widget.EditText",
					"xpath", 10);
			if (!result) {
				editProfileMsgList.add("EmailId textField is not visible.");
				result = false;
			}

			cfObj.commonClick(editProfilePage_OR.getUserEmailNew());

			result = cfObj.commonSetTextTextBox(editProfilePage_OR.getUserEmailNew(), userEmailId);
			if (!result) {
				editProfileMsgList.add("Failed to enter email on EmailId text field.");
				result = false;
			}
			cfObj.commonClick(editProfilePage_OR.getEmailUpdateBtn());

			loginUtilObj = new LoginUtil(driver);
			result = loginUtilObj.generateAndEnterOTP(driver, userEmailId, OtpType.Email);
			if (!result) {
				editProfileMsgList.addAll(loginUtilObj.loginMsgList);
				result = false;
			}
			cfObj.waitTillElementIsVisible(driver, 5, 2000,
					cfObj.commonGetElement(driver, "android:id/progress", "id"));
		} catch (Exception e) {
			result = false;
			editProfileMsgList.add("updateEmailId_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean updateMobileNumber(AppiumDriver<MobileElement> driver, String userPhoneNo) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[contains(@content-desc,'btn_mobile_update')]/parent::android.widget.EditText",
					"xpath", 10);
			if (!result) {
				editProfileMsgList.add("MobileNumber textField is not visible.");
				result = false;
			}

			cfObj.commonClick(editProfilePage_OR.getMobileNumberTextFieldNew());

			result = cfObj.commonSetTextTextBox(editProfilePage_OR.getMobileNumberTextFieldNew(), userPhoneNo);
			if (!result) {
				editProfileMsgList.add("Failed to enter email on EmailId text field.");
				result = false;
			}
			cfObj.commonClick(editProfilePage_OR.getMobileNoUpdateBtn());

			loginUtilObj = new LoginUtil(driver);
			result = loginUtilObj.generateAndEnterOTP(driver, userPhoneNo, OtpType.phone);
			if (!result) {

				editProfileMsgList.addAll(loginUtilObj.loginMsgList);
				result = false;
			}
		} catch (Exception e) {
			result = false;
			editProfileMsgList.add("updateMobileNumber_Exception" + e.getMessage());
		}
		return result;

	}
}
