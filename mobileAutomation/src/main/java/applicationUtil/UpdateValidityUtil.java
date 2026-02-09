package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import apiUtill.CreatePackageUtil;
import apiUtill.OrderApiUtil;
import apiUtill.PurchaseApiUtil;
import apiUtill.UpdateValidityApiUtil;
import apiUtill.UserApiUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.UpdateValidity_OR;
import pojo.createPackageResponse.CreatePackageResponse;
import pojo.login.Login;
import pojo.myPurchasedPackage.MyPurchasedPackage;
import util.Common_Function;

public class UpdateValidityUtil {

	LoginUtil loginUtilObj;
	UpdateValidity_OR updateValidityORobj;
	public List<String> updateValidityMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();

	public UpdateValidityUtil(AppiumDriver<MobileElement> driver) {
		updateValidityORobj = new UpdateValidity_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), updateValidityORobj);
	}

	// 3 -> 0 -> month
	// 2 -> 0 -> month
	// 1 -> 3 -> month
	// 2 -> 6 -> month

	public boolean verifyValidityFlow(AppiumDriver<MobileElement> driver, int validityType, int extension) {
		boolean result = true;
		UserApiUtil us = new UserApiUtil();
		MyPurchasedPackage myPurchasePackage;
		Login login = new Login();
		loginUtilObj = new LoginUtil(driver);
		OrderApiUtil orderApiObj = new OrderApiUtil();
		HomePageUtil homePageUtil = new HomePageUtil(driver);
		PurchaseApiUtil myPurchaseApiUtillObj = new PurchaseApiUtil();
		UpdateValidityApiUtil updateValidityObj = new UpdateValidityApiUtil();
		MyContentUtil myContentUtilObj = new MyContentUtil(driver);
		String strEmailId1 = "addaAutomation" + Common_Function.randomPhoneNumber(10, "2") + "@gmail.com";
		String mobileNumber1 = Common_Function.randomPhoneNumber(10, "8");

		try {
			CreatePackageUtil createPackageUtilObj = new CreatePackageUtil();
			List<Integer> categorylist = new ArrayList<Integer>();
			categorylist.add(1);

			CreatePackageResponse createPackageResponse = createPackageUtilObj.createPackage(true, false, categorylist, 2);
			if (createPackageResponse == null) {
				updateValidityMsgList.addAll(createPackageUtilObj.createPackageMsgList);
				return false;
			}
			System.out.println("strPackageId: " + createPackageResponse.getData().getId());

			String strTitlePackage = createPackageResponse.getData().getTitle();

			strEmailId1 = "addaAutomation" + Common_Function.randomPhoneNumber(10, "2") + "@gmail.com";

			result = loginUtilObj.signUpBeforeSelectCategory(driver, mobileNumber1, strEmailId1);
			if (!result) {
				updateValidityMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			// Allowing notification permission if coming
			if (cfObj.commonWaitForElementToBeVisible(driver,
					cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"),
					8)) {
				cfObj.commonClick(cfObj.commonGetElement(driver,
						"com.android.permissioncontroller:id/permission_allow_button", "id"));
			}

			result = loginUtilObj.selectYourCategoryExamLanguage(driver);
			if (!result) {
				updateValidityMsgList.add("Unable to click select category, exam and language");
				return result;
			}

			if (cfObj.commonWaitForElementToBeVisible(driver,
					cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"),
					8)) {
				cfObj.commonClick(cfObj.commonGetElement(driver,
						"com.android.permissioncontroller:id/permission_allow_button", "id"));
			}

			HomePageUtil homeUtilObj = new HomePageUtil(driver);
			cfObj.commonClick(homeUtilObj.homePageObj.getBtnMyContent());
			Thread.sleep(500);
			cfObj.commonClick(homeUtilObj.homePageObj.getBtnMyContent());
//			cfObj.commonClick(homeUtilObj.homePageObj.getBtnMyContent());

			result = loginUtilObj.validateAccountRecoveryDetailsPopUp(driver, strEmailId1);
			if (!result) {
				updateValidityMsgList.addAll(loginUtilObj.loginMsgList);
			}

			result = homeUtilObj.clickHomeBtn(driver);
			if (!result) {
				updateValidityMsgList.addAll(homeUtilObj.homeMsgList);
				updateValidityMsgList.add("Unable to verify homepage after login");
            }

			login = us.userLoginWithMobileNumber(mobileNumber1);
			if (login.getData() == null) {
				updateValidityMsgList.add("error in login with user1");
				return false;
			}

//			result = verifyStorePdpFlow(driver, strTitlePackage, true, validityType, extension);
//			if (!result) {
//				updateValidityMsgList.add("verifyStorePdpFlow for 1st user failed");
//				return result;
//			}

			result = orderApiObj.createOrder(login.getData().getJwtToken(), strEmailId1,
					createPackageResponse.getData().getVpData().get(0).getId(), mobileNumber1);
			if (!result) {
				updateValidityMsgList.add("error in creating 1st order");
				return result;
			}

			Thread.sleep(1000);

			result = homePageUtil.clickMyContentButton(driver);
			if (!result) {
				updateValidityMsgList.addAll(homePageUtil.homeMsgList);
				return result;
			}

			result = myContentUtilObj.clickOnPurchasedBtn(driver);
			if (!result) {
				updateValidityMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}

			myPurchasePackage = myPurchaseApiUtillObj.getMyPurchasedCourse(login.getData().getJwtToken());
			Long expiryBeforeUpdate = myPurchasePackage.getData().get(0).getExpiry();
			System.out.println("expiryBeforeUpdate: " + expiryBeforeUpdate);

			Thread.sleep(2000);

			UserApiUtil userApiUtilObj = new UserApiUtil();
			result = updateValidityObj.updateValidity(
					userApiUtilObj.adminLogin("abhay.rai@adda247.com", "0002@aada!").getFacultyToken(), validityType,
					createPackageResponse.getData().getVpData().get(0).getId(), extension, "Months");
			if (!result) {
				updateValidityMsgList.add("error in updating validity");
				return result;
			}

			System.out.println("After updating validity");

			Thread.sleep(1000);

			myPurchasePackage = myPurchaseApiUtillObj.getMyPurchasedCourse(login.getData().getJwtToken());
			Long expiryAfterUpdate = myPurchasePackage.getData().get(0).getExpiry();
			System.out.println("expiryAfterUpdate: " + expiryAfterUpdate);

			if (!expiryBeforeUpdate.equals(expiryAfterUpdate)) {

				updateValidityMsgList.add(
						"The expiry changed for a user, who already purchased course and afterwards validity changed");
				return false;
			}

			result = homePageUtil.logout(driver);
			if (!result) {
				updateValidityMsgList.addAll(homePageUtil.homeMsgList);
				return result;
			}

			String strEmailId2 = "addaAutomation" + Common_Function.randomPhoneNumber(10, "2") + "@gmail.com";
			String mobileNumber2 = Common_Function.randomPhoneNumber(10, "8");

			result = loginUtilObj.signUpWithoutWelcome(driver, mobileNumber2, strEmailId2);
			if (!result) {
				updateValidityMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			login = us.userLoginWithMobileNumber(mobileNumber2);
			if (login == null) {
				updateValidityMsgList.add("error in login with user2");
				return false;
			}

			result = verifyStorePdpFlow(driver, strTitlePackage, false, validityType, extension);
			if (!result) {
				updateValidityMsgList.add("verifyStorePdpFlow for 2nd user failed");
				return result;
			}

			result = orderApiObj.createOrder(login.getData().getJwtToken(), strEmailId2,
					createPackageResponse.getData().getVpData().get(0).getId(), mobileNumber2);
			if (!result) {
				updateValidityMsgList.add("error in creating 2nd order");
				return result;
			}

			Thread.sleep(3000);

			result = homePageUtil.clickMyContentButton(driver);
			if (!result) {
				updateValidityMsgList.addAll(homePageUtil.homeMsgList);
				return result;
			}

			result = myContentUtilObj.clickOnPurchasedBtn(driver);
			if (!result) {
				updateValidityMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}

			myPurchasePackage = myPurchaseApiUtillObj.getMyPurchasedCourse(login.getData().getJwtToken());
			Long expiryForNewUserAfterUpdate = myPurchasePackage.getData().get(0).getExpiry();
			System.out.println(expiryForNewUserAfterUpdate);

//			Calendar calendar = Calendar.getInstance();
//			calendar.setTimeInMillis(expiryForNewUserAfterUpdate);
//			int validityYear = calendar.get(Calendar.YEAR);
//			System.out.println(validityYear);
//
//			Calendar calendar1 = Calendar.getInstance();
//			int currentYear = calendar1.get(Calendar.YEAR);
//			System.out.println(currentYear);
//
//			if ((validityType == 2) && (extension == 0)) {
//
//				if (!((validityYear - currentYear) == 2)) {
//					updateValidityMsgList.add("The validity is not increased by 2 years for double validity");
//					return false;
//				}
//
//			} else if ((validityType == 3) && (extension == 0)) {
//
//				if (!((validityYear - currentYear) == 3)) {
//					updateValidityMsgList.add("The validity is not increased by 3 years for triple validity");
//					return false;
//				}
//			}

		} catch (Exception e) {
			updateValidityMsgList.add("verifyValidityFlow_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyStorePdpFlow(AppiumDriver<MobileElement> driver, String strTitlePackage, boolean isFirstUser,
			int validityType, int extension) {
		boolean result = true;
		HomePageUtil homePageUtil = new HomePageUtil(driver);
		StorePageUtil storePageUtilObj = new StorePageUtil(driver);

		try {
			result = homePageUtil.clickStoreBtn(driver);
			if (!result) {
				updateValidityMsgList.addAll(homePageUtil.homeMsgList);
				return result;
			}

			result = storePageUtilObj.clickSearchIcon(driver);
			if (!result) {
				updateValidityMsgList.addAll(storePageUtilObj.storePageMsgList);
				return result;
			}

			result = storePageUtilObj.enterPackageNameInSearchField(driver, strTitlePackage);
			if (!result) {
				updateValidityMsgList.addAll(storePageUtilObj.storePageMsgList);
				return result;
			}

			if (isFirstUser) {

				result = storePageUtilObj.clickPackageNameInSearchResult(driver, strTitlePackage);
				if (!result) {
					updateValidityMsgList.addAll(storePageUtilObj.storePageMsgList);
					return result;
				}

				result = verifyOldValidityInPDP(driver);
				if (!result) {
					updateValidityMsgList.add("verifyOldValidityInPDP failed");
					return result;
				}

			} else {

				if ((validityType == 2) && (extension == 0)) {

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
							"//android.widget.ImageView[\n" +
									"  contains(@content-desc, 'Double Validity') and\n" +
									"  contains(@content-desc, ':Automation Package Mahapack')\n" +
									"]", "xpath", 10);
					if (!result) {
						updateValidityMsgList
								.add("Double validity text on package banner when searching is not visible");
						return result;
					}

				} else if ((validityType == 3) && (extension == 0)) {

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
							"//android.widget.ImageView[\n" +
									"  contains(@content-desc, 'Triple Validity') and\n" +
									"  contains(@content-desc, ':Automation Package Mahapack')\n" +
									"]", "xpath", 10);
					if (!result) {
						updateValidityMsgList
								.add("Triple validity text on package banner when searching is not visible");
						return result;
					}

				} else if ((validityType == 2) && (extension == 8)) {

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
							"//*[\n" +
									"  contains(@content-desc, '2X + 8 Months') and\n" +
									"  contains(@content-desc, ':Automation Package Mahapack')\n" +
									"]", "xpath", 10);
					if (!result) {
						updateValidityMsgList.add("2X + 8 Months text on package banner when searching is not visible");
						return result;
					}

				} else if ((validityType == 1) && (extension == 8)) {

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[\n" +
									"  contains(@content-desc, '+ 8 Months') and\n" +
									"  contains(@content-desc, ':Automation Package Mahapack')\n" +
									"]", "xpath", 10);
					if (!result) {
						updateValidityMsgList.add("+8 Months text on package banner when searching is not visible");
						return result;
					}
				} else {
					updateValidityMsgList.add("The validity or extension is wrong");
					return false;
				}

				result = storePageUtilObj.clickPackageNameInSearchResult(driver, strTitlePackage);
				if (!result) {
					updateValidityMsgList.addAll(storePageUtilObj.storePageMsgList);
					return result;
				}

				result = verifyNewValidityInPDP(driver, validityType, extension);
				if (!result) {
					updateValidityMsgList.add("verifyNewValidityInPDP failed");
					return result;
				}
			}

			driver.navigate().back();
			driver.navigate().back();

			HomePageUtil homePageUtilObj = new HomePageUtil(driver);
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageUtilObj.homePageObj.getBtnHome(),5);
			if (!result) {
				driver.navigate().back();
			}

			result = homePageUtil.JustOpenAndClickHomeBtn(driver);
			if (!result) {
				updateValidityMsgList.addAll(homePageUtil.homeMsgList);
				return result;
			}

		} catch (Exception e) {
			updateValidityMsgList.add("verifyStorePdpFlow_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyOldValidityInPDP(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, updateValidityORobj.SelectValidityTextInPDP(), 10);
			if (!result) {
				updateValidityMsgList.add("validity container is not visible in pdp");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, updateValidityORobj.SelectValidityTextInPDP(), 10);
			if (!result) {
				updateValidityMsgList.add("Select Validity text is not visible in pdp");
				return result;
			}

			String validityString = updateValidityORobj.validityTextInPDP().getAttribute("content-desc");
			if (!validityString.contains("1 Months")) {
				updateValidityMsgList.add("Validity is not 1 Months in pdp");
				return false;
			}

		} catch (Exception e) {
			updateValidityMsgList.add("verifyOldValidityInPDP_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyNewValidityInPDP(AppiumDriver<MobileElement> driver, int validityType, int extension) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, updateValidityORobj.SelectValidityTextInPDP(), 10);
			if (!result) {
				updateValidityMsgList.add("validity container is not visible in pdp");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, updateValidityORobj.SelectValidityTextInPDP(), 10);
			if (!result) {
				updateValidityMsgList.add("Validity text is not visible in pdp");
				return result;
			}

			if ((validityType == 2) && (extension == 0)) {

				String validityString = updateValidityORobj.validityTextInPDP().getAttribute("content-desc");
				if (!validityString.contains("2 x 1 Months")) {
					updateValidityMsgList.add("Validity is not 2 x 1 Months in pdp");
					return false;
				}

			} else if ((validityType == 3) && (extension == 0)) {

				String validityString = updateValidityORobj.validityTextInPDP().getAttribute("content-desc");
				if (!validityString.contains("3 x 1 Months")) {
					updateValidityMsgList.add("Validity is not 3 x 1 Months in pdp");
					return false;
				}

			} else if ((validityType == 2) && (extension == 8)) {

				String validityString = updateValidityORobj.validityTextInPDP().getAttribute("content-desc");
				if (!validityString.contains("2 x 1 Months + 8 Months")) {
					updateValidityMsgList.add("Validity is not 2 x 1 Months + 8 Months in pdp");
					return false;
				}

			} else if ((validityType == 1) && (extension == 8)) {

				String validityString = updateValidityORobj.validityTextInPDP().getAttribute("content-desc");
				if (!validityString.contains("1 Months + 8 Months")) {
					updateValidityMsgList.add("Validity is not 1 Months + 8 Months in pdp");
					return false;
				}

			} else {
				updateValidityMsgList.add("The validity or extension is wrong");
				return false;
			}

		} catch (Exception e) {
			updateValidityMsgList.add("verifyNewValidityInPDP_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}
}
