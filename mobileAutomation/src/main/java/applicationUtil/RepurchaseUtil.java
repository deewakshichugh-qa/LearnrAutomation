package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.PageFactory;

import apiUtill.OrderApiUtil;
import apiUtill.UserApiUtil;
import applicationUtil.StorePageUtil.ProductType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.RepurchagePage_OR;
import pojo.login.Login;
import util.Common_Function;
import util.Common_Function.direction;
import util.Common_Function.key;
import util.ConfigFileReader;

public class RepurchaseUtil {

	public HomePageUtil homeUtilObj;
	public StorePageUtil storePageUtilObj;
	public LoginUtil loginUtilObj;
	public RegisterNewUserUtil registrationUserUtilObj;
	public PurchasePackageUtil purchasePackageUtilObj;
	public RepurchagePage_OR repurchasePageObj;
	public FeedbackFormUtil feedbackFormUtilObj;
	public UserDetailsLayerUtil userDetailsLayerUtilObj;
	public PaymentUtil paymentUtilObj;
	MyContentUtil myContentUtilObj;
	public ArrayList<String> repurchaseMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();
	TestPassUtil testPassUtilObj;

	public RepurchaseUtil(AppiumDriver<MobileElement> driver) {
		repurchasePageObj = new RepurchagePage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), repurchasePageObj);
	}

	public boolean verifyRepurchaseCourse(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		Login login = new Login();
		UserApiUtil us = new UserApiUtil();
		OrderApiUtil orderApiObj = new OrderApiUtil();
		String packageName = "Package Automate";
		try {

			loginUtilObj = new LoginUtil(driver);
			String mobileNumber = Common_Function.randomPhoneNumber(10, "9");
			result = loginUtilObj.verifySignUp(driver, mobileNumber, true);
			if (!result) {
				repurchaseMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			List<Integer> categorylist = new ArrayList<Integer>();
			categorylist.add(1);

			login = us.userLoginWithMobileNumber(mobileNumber);
			if (login.getData() == null) {
				repurchaseMsgList.add("error in login with user1");
				return false;
			}

			result = orderApiObj.createOrder(login.getData().getJwtToken(), "", 5393, mobileNumber);
			if (!result) {
				repurchaseMsgList.add("error in creating 1st order");
				return result;
			}

			homeUtilObj = new HomePageUtil(driver);
			result = homeUtilObj.clickStoreBtn(driver);
			if (!result) {
				repurchaseMsgList.add("Unable to move to store page");
				return result;
			}

			storePageUtilObj = new StorePageUtil(driver);
			result = storePageUtilObj.purchasePackage(driver, packageName, ProductType.ALL);
			if (!result) {
				repurchaseMsgList.addAll(storePageUtilObj.storePageMsgList);
				return result;
			}

			testPassUtilObj = new TestPassUtil(driver);
			result = cfObj.commonWaitForElementToBeVisible(driver, testPassUtilObj.testPassObj.getBtnStartLearning(), 10);
			if (!result) {
				repurchaseMsgList.add("Unable to verify start learning btn in order success page");
				return result;
			}

			cfObj.commonClick(testPassUtilObj.testPassObj.getBtnStartLearning());

			if(ConfigFileReader.strRunMode.equalsIgnoreCase("localLab")){
				cfObj.commonClick(homeUtilObj.homePageObj.getBtnMyContent());
				cfObj.commonClick(homeUtilObj.homePageObj.getBtnMyContent());
			}
			result = homeUtilObj.clickMyContentButton(driver);
			if (!result) {
				repurchaseMsgList.add("Unable to move to My content page");
				return result;
			}
			cfObj.commonClick(homeUtilObj.homePageObj.getBtnMyContent());

			String appPackageName;
			try {
				appPackageName = ((AndroidDriver<MobileElement>) driver).getCurrentPackage();
				driver.terminateApp(appPackageName);
				Thread.sleep(1000);
				driver.activateApp(appPackageName);
			} catch (Exception e) {
				repurchaseMsgList.add("1st Error restarting app: " + e.getMessage());
				return false;
			}

			result = homeUtilObj.clickMyContentButton(driver);
			if (!result) {
				repurchaseMsgList.add("Unable to move to My content page");
				return result;
			}
			cfObj.commonClick(homeUtilObj.homePageObj.getBtnMyContent());

			myContentUtilObj = new MyContentUtil(driver);
			result = myContentUtilObj.clickOnPurchasedBtn(driver);
			if (!result) {
				repurchaseMsgList.add("Failed to click purchase button.");
				return result;
			}

			result = myContentUtilObj.clickOnSpecificPurchasedPackage(driver, packageName);
			if (!result) {
				repurchaseMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//*[@content-desc=\"Repurchase\"]", "xpath", 10)) {

				result = clickOnRepurchaseLink(driver);
				if (!result) {
					repurchaseMsgList.add("Unable to click on repurchase link");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, repurchasePageObj.getBtnRepurchase(), 10);
				if (!result) {
					repurchaseMsgList.add("getBtnRepurchase inside package is not visible");
					return result;
				}
				cfObj.commonClick(repurchasePageObj.getBtnRepurchase());

				PaymentUtil paymentUtilObj = new PaymentUtil(driver);
				result = paymentUtilObj.verifyPaymentOptionList(driver);
				if (!result) {
					repurchaseMsgList.addAll(paymentUtilObj.paymentMsgList);
					return result;
				}

				result = paymentUtilObj.clickOnNetBankingTypePaymentAndValidate(driver);
				if (!result) {
					repurchaseMsgList.addAll(paymentUtilObj.paymentMsgList);
					return result;
				}

				result = paymentUtilObj.clickOnSBIBankTab(driver);
				if (!result) {
					repurchaseMsgList.addAll(paymentUtilObj.paymentMsgList);
					return result;
				}

				result = paymentUtilObj.clickOnPaymentSuccessBtn(driver);
				if (!result) {
					repurchaseMsgList.addAll(paymentUtilObj.paymentMsgList);
					return result;
				}

				OrderSuccessUtil orderSuccessUtilObj = new OrderSuccessUtil(driver);
				result = orderSuccessUtilObj.handleRateUsPopUpWindow(driver);
				if (!result) {
					repurchaseMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
					return result;
				}

				result = orderSuccessUtilObj.verifyOrderSuccessPage(driver);
				if (!result) {
					repurchaseMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
					return result;
				}

				testPassUtilObj = new TestPassUtil(driver);
				result = cfObj.commonWaitForElementToBeVisible(driver, testPassUtilObj.testPassObj.getBtnStartLearning(), 10);
				if (!result) {
					repurchaseMsgList.add("Unable to verify start learning btn in order success page");
					return result;
				}

				cfObj.commonClick(testPassUtilObj.testPassObj.getBtnStartLearning());

				homeUtilObj = new HomePageUtil(driver);
				result = homeUtilObj.clickMyContentButton(driver);
				if (!result) {
					repurchaseMsgList.addAll(homeUtilObj.homeMsgList);
					return result;
				}

				try {
					appPackageName = ((AndroidDriver<MobileElement>) driver).getCurrentPackage();
					driver.terminateApp(appPackageName);
					Thread.sleep(1000);
					driver.activateApp(appPackageName);
				} catch (Exception e) {
					repurchaseMsgList.add("2nd Error restarting app: " + e.getMessage());
					return false;
				}

				result = homeUtilObj.clickMyContentButton(driver);
				if (!result) {
					repurchaseMsgList.addAll(homeUtilObj.homeMsgList);
					return result;
				}

				myContentUtilObj = new MyContentUtil(driver);
				result = myContentUtilObj.clickOnPurchasedBtn(driver);
				if (!result) {
					repurchaseMsgList.addAll(myContentUtilObj.myContentMsgList);
					return result;
				}

				result = verifyPackageRepurchased(driver);
				if (!result) {
					repurchaseMsgList.add("The purchased package still has repurchase link");
					return result;
				}
			} else {
				repurchaseMsgList.add("Repurchase btn in my content is not available.");
				return false;
			}
		} catch (Exception e) {
			repurchaseMsgList.add("verifyRepurchaseCourse_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnRepurchaseLink(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, repurchasePageObj.getLinkRepurchase(), 10);
			if (!result) {
				repurchaseMsgList.add("Repurchase btn is not visible on purchased package.");
				return result;
			}

			cfObj.commonClick(repurchasePageObj.getLinkRepurchase());

			result = verifyComparisonWindow(driver);
			if (!result) {
				repurchaseMsgList.add("Failed to verify ComparisonWindow.");
			}
		} catch (Exception e) {
			repurchaseMsgList.add("clickOnRepurchaseLink_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyComparisonWindow(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.ImageView[@content-desc=\"backClick\"]", "xpath", 10);
			if (!result) {
				repurchaseMsgList.add("crossDialog button in repurchase bottomsheet is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[contains(@content-desc,\"Renew your old\n" +
							"plan!\")]", "xpath", 10);
			if (!result) {
				repurchaseMsgList.add("Renew your old plan text in repurchase bottomsheet is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Recommended\"]", "xpath", 10);
			if (!result) {
				repurchaseMsgList.add("Recommended text in repurchase bottomsheet is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Save more,\n" +
							"Prepare more!\n" +
							"Buy our Maha Pack\n" +
							"buyAMahapackClick\"]", "xpath", 10);
			if (!result) {
				repurchaseMsgList.add("Save more, Prepare more text in repurchase bottomsheet is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, repurchasePageObj.getBtnBuyAMahapack(), 10);
			if (!result) {
				repurchaseMsgList.add("BuyAMahapack button in repurchase bottomsheet is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, repurchasePageObj.getBtnRepurchase(), 10);
			if (!result) {
				repurchaseMsgList.add("Repurchase button in repurchase bottom sheet is not visible.");
				return result;
			}

		} catch (Exception e) {
			repurchaseMsgList.add("verifyComparisonWindow_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyPackageRepurchased(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.scrollUtill(driver, 1, direction.DOWN);
			Thread.sleep(1000);

			result = !cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//*[@content-desc=\"Repurchase\"]", "xpath", 3);
			if (!result) {
				repurchaseMsgList.add("The repurchase btn is still available after repurchasing the package");
				return result;
			}
		} catch (Exception e) {
			repurchaseMsgList.add("verifyPackageRepurchased_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyRepurchaseThroughMahapack(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		Login login = new Login();
		UserApiUtil us = new UserApiUtil();
		OrderApiUtil orderApiObj = new OrderApiUtil();
		String packageName = "Package Automate";
		try {

			loginUtilObj = new LoginUtil(driver);
			String mobileNumber = Common_Function.randomPhoneNumber(10, "9");
			result = loginUtilObj.verifySignUp(driver, mobileNumber, true);
			if (!result) {
				repurchaseMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			List<Integer> categorylist = new ArrayList<Integer>();
			categorylist.add(1);

//			login = us.userLoginWithMobileNumber(mobileNumber);
//			if (login.getData() == null) {
//				repurchaseMsgList.add("error in login with user1");
//				return false;
//			}
//
//			result = orderApiObj.createOrder(login.getData().getJwtToken(), "", 5393, mobileNumber);
//			if (!result) {
//				repurchaseMsgList.add("error in creating 1st order");
//				return result;
//			}

			homeUtilObj = new HomePageUtil(driver);
			result = homeUtilObj.clickStoreBtn(driver);
			if (!result) {
				repurchaseMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			storePageUtilObj = new StorePageUtil(driver);
			result = storePageUtilObj.purchasePackage(driver, packageName, ProductType.ALL);
			if (!result) {
				repurchaseMsgList.addAll(storePageUtilObj.storePageMsgList);
				return result;
			}

			testPassUtilObj = new TestPassUtil(driver);
			result = cfObj.commonWaitForElementToBeVisible(driver, testPassUtilObj.testPassObj.getBtnStartLearning(), 10);
			if (!result) {
				repurchaseMsgList.add("Unable to verify start learning btn in order success page");
				return result;
			}
			cfObj.commonClick(testPassUtilObj.testPassObj.getBtnStartLearning());

			result = homeUtilObj.clickMyContentButton(driver);
			if (!result) {
				repurchaseMsgList.addAll(storePageUtilObj.storePageMsgList);
				return result;
			}

			String appPackageName;
			try {
				appPackageName = ((AndroidDriver<MobileElement>) driver).getCurrentPackage();
				driver.terminateApp(appPackageName);
				Thread.sleep(1000);
				driver.activateApp(appPackageName);
			} catch (Exception e) {
				repurchaseMsgList.add("1st Error restarting app: " + e.getMessage());
				return false;
			}

			result = homeUtilObj.clickMyContentButton(driver);
			if (!result) {
				repurchaseMsgList.addAll(storePageUtilObj.storePageMsgList);
				return result;
			}

			myContentUtilObj = new MyContentUtil(driver);
			result = myContentUtilObj.clickOnPurchasedBtn(driver);
			if (!result) {
				repurchaseMsgList.addAll(storePageUtilObj.storePageMsgList);
				return result;
			}

			result = myContentUtilObj.clickOnSpecificPurchasedPackage(driver, packageName);
			if (!result) {
				repurchaseMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//*[@content-desc=\"Repurchase\"]", "xpath", 10)) {

				result = clickOnRepurchaseLink(driver);
				if (!result) {
					repurchaseMsgList.add("Failed to click RepurchaseLink.");
					return result;
				}

				result = clickOnBuyAMahapackBtn(driver);
				if (!result) {
					repurchaseMsgList.add("Failed to click BuyAMahapackBtn.");
					return result;
				}
				cfObj.commonClick(repurchasePageObj.getListSingleMahapack().get(0));

				purchasePackageUtilObj = new PurchasePackageUtil(driver);

				result = purchasePackageUtilObj.clickBuyNowBtn(driver);
				if (!result) {
					repurchaseMsgList.add("Failed to click BuyNowBtn.");
					return result;
				}

				PaymentUtil paymentUtilObj = new PaymentUtil(driver);
				result = paymentUtilObj.verifyPaymentOptionList(driver);
				if (!result) {
					repurchaseMsgList.addAll(paymentUtilObj.paymentMsgList);
					return result;
				}

				result = paymentUtilObj.clickOnNetBankingTypePaymentAndValidate(driver);
				if (!result) {
					repurchaseMsgList.addAll(paymentUtilObj.paymentMsgList);
					return result;
				}

				result = paymentUtilObj.clickOnSBIBankTab(driver);
				if (!result) {
					repurchaseMsgList.addAll(paymentUtilObj.paymentMsgList);
					return result;
				}

				result = paymentUtilObj.clickOnPaymentSuccessBtn(driver);
				if (!result) {
					repurchaseMsgList.addAll(paymentUtilObj.paymentMsgList);
					return result;
				}

				OrderSuccessUtil orderSuccessUtilObj = new OrderSuccessUtil(driver);
				result = orderSuccessUtilObj.handleRateUsPopUpWindow(driver);
				if (!result) {
					repurchaseMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
					return result;
				}

				result = orderSuccessUtilObj.verifyOrderSuccessPage(driver);
				if (!result) {
					repurchaseMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
					return result;
				}

				testPassUtilObj = new TestPassUtil(driver);
				result = cfObj.commonWaitForElementToBeVisible(driver, testPassUtilObj.testPassObj.getBtnStartLearning(), 10);
				if (!result) {
					repurchaseMsgList.add("Unable to verify start learning btn in order success page");
					return result;
				}
				cfObj.commonClick(testPassUtilObj.testPassObj.getBtnStartLearning());

				homeUtilObj = new HomePageUtil(driver);
				result = homeUtilObj.clickMyContentButton(driver);
				if (!result) {
					repurchaseMsgList.addAll(homeUtilObj.homeMsgList);
					return result;
				}

				try {
					appPackageName = ((AndroidDriver<MobileElement>) driver).getCurrentPackage();
					driver.terminateApp(appPackageName);
					Thread.sleep(1000);
					driver.activateApp(appPackageName);
				} catch (Exception e) {
					repurchaseMsgList.add("2nd Error restarting app: " + e.getMessage());
					return false;
				}

				result = homeUtilObj.clickMyContentButton(driver);
				if (!result) {
					repurchaseMsgList.addAll(homeUtilObj.homeMsgList);
					return result;
				}

				myContentUtilObj = new MyContentUtil(driver);
				result = myContentUtilObj.clickOnPurchasedBtn(driver);
				if (!result) {
					repurchaseMsgList.addAll(myContentUtilObj.myContentMsgList);
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,\"repurchaseClick\")]", "xpath", 10);
				if(!result) {
					repurchaseMsgList.add("The repurchased mahapack diff course is not visible");
					return result;
				}
			} else {
				repurchaseMsgList.add("RePurchase btn is not available.");
				return false;
			}
		} catch (Exception e) {
			repurchaseMsgList.add("verifyRepurchaseThroughMahapack_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnBuyAMahapackBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.commonClick(repurchasePageObj.getBtnBuyAMahapack());

			result = verifyMahapackSelectionPage(driver);
			if (!result) {
				repurchaseMsgList.add("Failed to verify MahapackSelectionPage.");
			}
		} catch (Exception e) {
			repurchaseMsgList.add("clickOnBuyAMahapackBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyMahapackSelectionPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, repurchasePageObj.getListSingleMahapack().get(0), 10);
			if (!result) {
				repurchaseMsgList.add("Single Mahapack is not visible.");
				return result;
			}
		} catch (Exception e) {
			repurchaseMsgList.add("verifyMahapackSelectionPage_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}
}