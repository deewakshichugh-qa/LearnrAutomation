package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;
import apiUtill.CouponUtil;
import apiUtill.CreatePackageUtil;
import apiUtill.CreateReferAndEarnCampaignUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.ReferAndEarnPage_OR;
import pojo.createReferAndEarnCampaignResponse.CreateReferAndEarnCampaignResponse;
import util.Common_Function;
import util.ConfigFileReader;
import util.Common_Function.direction;
import util.Common_Function.key;

public class ReferAndEarnUtil {

	StorePageUtil storePageUtilObj;
	LoginUtil loginUtilObj;
	HomePageUtil homePageUtilObj;
	CouponUtil couponUtilObj;
	CreatePackageUtil createPackageUtilObj;
	ProductPageUtil productPageUtilObj;
	PriceDetailsPageUtil priceDetailsPageUtilObj;
	ReferAndEarnPage_OR referAndEarnPageOrObj;
	PurchasePackageUtil purchasePackageUtilObj;
	UserDetailsLayerUtil userDetailsLayerUtilObj;
	MyOrdersPageUtil myOrdersPageUtilObj;
	FeedbackFormUtil feedbackFormUtilObj;
	OrderSuccessUtil orderSuccessUtilObj;
	public List<String> referAndEarnMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();
    ConfigFileReader configFileReaderObj = new ConfigFileReader();

	public ReferAndEarnUtil(AppiumDriver<MobileElement> driver) {
		referAndEarnPageOrObj = new ReferAndEarnPage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), referAndEarnPageOrObj);
	}

	public boolean verifyReferAndEarnPage_SelfCodeInCourse(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		loginUtilObj = new LoginUtil(driver);
		try {
			if(ConfigFileReader.strEnv.equalsIgnoreCase("production")){

				result = loginUtilObj.verifyLoginUsingEmailIdFromConfig(driver);
				if (!result) {
					referAndEarnMsgList.addAll(loginUtilObj.loginMsgList);
					return result;
				}
			} else {
				result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), false);
				if (!result) {
					referAndEarnMsgList.addAll(loginUtilObj.loginMsgList);
					return result;
				}
			}

			homePageUtilObj = new HomePageUtil(driver);
			result = homePageUtilObj.justOpenNavigationDrawer(driver);
			if (!result) {
				referAndEarnMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			result = homePageUtilObj.validateReferNowBtn(driver);
			if (!result) {
				referAndEarnMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/know_more", "id", 10);
			if (!result) {
				referAndEarnMsgList.add("refer & earn btn in box in navigation drawer is not visible");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/know_more", "id"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Invite your friends\"]", "xpath", 30);
			if (!result) {
				referAndEarnMsgList.add("invite your friends text is not visible.");
				return result;
			}

			result = validateReferAndEarnScreen(driver);
			if (!result) {
				referAndEarnMsgList.add("Failed to validate ReferAndEarnScreen.");
				return result;
			}

			result = validateHelpBtn(driver);
			if (!result) {
				referAndEarnMsgList.add("Failed to validate Help button.");
				return result;
			}

			result = validateMyRewardLink(driver);
			if (!result) {
				referAndEarnMsgList.add("Failed to validate MyRewardLink.");
				return result;
			}

			result = clickOnReferACourseTab(driver);
			if (!result) {
				referAndEarnMsgList.add("Failed to click ReferACourseTab.");
				return result;
			}

			result = validateReferACourseTabScreen(driver);
			if (!result) {
				referAndEarnMsgList.add("Failed to validate ReferACourseTabScreen.");
				return result;
			}

			cfObj.scrollIntoDesc(driver, "Share app\nTab 2 of 2");

			result = clickOnShareAppTab(driver);
			if (!result) {
				referAndEarnMsgList.add("Failed to click ShareAppTab.");
				return result;
			}

			result = validateShareAppTabScreen(driver);
			if (!result) {
				referAndEarnMsgList.add("Failed to validate ShareAppTabScreen.");
				return result;
			}

			cfObj.scrollIntoDesc(driver, "Share app\nTab 2 of 2");

			result = clickOnReferACourseTab(driver);
			if (!result) {
				referAndEarnMsgList.add("Failed to click ReferACourseTab.");
				return result;
			}

			cfObj.commonClick(referAndEarnPageOrObj.getReferalCodeCopyBtn());

            String referralCode = cfObj.commonGetTextFromClipBoard(driver);
			if (referralCode == null) {
				referAndEarnMsgList.add("Referral code is Empty.");
				return false;
			}

			System.out.println("Referral Code: - " + referralCode);

			driver.navigate().back();

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/know_more", "id", 10);
			if (!result) {
				referAndEarnMsgList.add("refer & earn btn in box in navigation drawer is not visible after coming back");
				return result;
			}

			driver.navigate().back();

			homePageUtilObj = new HomePageUtil(driver);
			result = homePageUtilObj.clickStoreBtn(driver);
			if (!result) {
				referAndEarnMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			cfObj.scrollUtill(driver, 1, direction.DOWN);

			storePageUtilObj = new StorePageUtil(driver);

			result = storePageUtilObj.clickLiveClassIcon(driver);
			if (!result) {
				referAndEarnMsgList.addAll(storePageUtilObj.storePageMsgList);
				return result;
			}

			cfObj.waitForPageLoading(driver, 4, 2000, cfObj.commonGetElement(driver, "product_view", "id"));

			productPageUtilObj = new ProductPageUtil(driver);
			result = productPageUtilObj.openFirstUnPurchasedPackage(driver);
			if (!result) {
				referAndEarnMsgList.addAll(productPageUtilObj.productPageMsgList);
				return result;
			}

			purchasePackageUtilObj = new PurchasePackageUtil(driver);

			result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackageUtilObj.purchasePackagePageObj.moreOffersBtnOnBottom(), 10);
			if (!result) {
				referAndEarnMsgList.add("More offers btn is not visible");
				return result;
			}
			cfObj.commonClick(purchasePackageUtilObj.purchasePackagePageObj.moreOffersBtnOnBottom());

			priceDetailsPageUtilObj = new PriceDetailsPageUtil(driver);
			result = priceDetailsPageUtilObj.enterCouponCode(referralCode);
			if (!result) {
				referAndEarnMsgList.add("Unable to enter coupon : " + referralCode);
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, priceDetailsPageUtilObj.priceDetailPageObj.getBtnApplySelfCoupon(), 10);
			if(!result){
					referAndEarnMsgList.add("Failed to verify getBtnApplySelfCoupon");
					return result;
			}
			cfObj.commonClick(priceDetailsPageUtilObj.priceDetailPageObj.getBtnApplySelfCoupon());

			Thread.sleep(1500);

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.EditText", "xpath", 10);
			if (!result) {

				result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackageUtilObj.purchasePackagePageObj.getOfferNumberFromBottom(), 5);
				if (!result){
					referAndEarnMsgList.add("edittext_coupon is not visible as OwnReferral code has applied & also, coupon is also applied.");
					return result;
				}
			} else {
				cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.View[@content-desc=\"backClick\"]", "xpath"));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@content-desc=\"BUY NOW\"]", "xpath", 10);
				if (!result) {
					referAndEarnMsgList.add("Failed to close coupon apply screen.");
					return result;
				}
			}
		} catch (Exception e) {
			result = false;
			referAndEarnMsgList.add("verifyReferAndEarnPage_SelfCodeInCourse_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyAnotherCodeInCourse_ReferAndEarn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String referralCode = "REF_4D2F2599";
		homePageUtilObj = new HomePageUtil(driver);
		loginUtilObj = new LoginUtil(driver);
		storePageUtilObj = new StorePageUtil(driver);
		productPageUtilObj = new ProductPageUtil(driver);
		try {

			result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), true);
			if (!result) {
				referAndEarnMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			result = homePageUtilObj.clickStoreBtn(driver);
			if (!result) {
				referAndEarnMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			cfObj.scrollUtill(driver, 1, direction.DOWN);

			result = storePageUtilObj.clickLiveClassIcon(driver);
			if (!result) {
				referAndEarnMsgList.addAll(storePageUtilObj.storePageMsgList);
				return result;
			}

			cfObj.waitForPageLoading(driver, 4, 2000, cfObj.commonGetElement(driver, "product_view", "id"));

			result = productPageUtilObj.openFirstUnPurchasedPackage(driver);
			if (!result) {
				referAndEarnMsgList.addAll(productPageUtilObj.productPageMsgList);
				return result;
			}

			purchasePackageUtilObj = new PurchasePackageUtil(driver);
			result = purchasePackageUtilObj.clickAddPromoCodeBtn(driver);
			if (!result) {
				referAndEarnMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
				return result;
			}

			// Case Sensitive case validation
			priceDetailsPageUtilObj = new PriceDetailsPageUtil(driver);
			result = priceDetailsPageUtilObj.enterCouponCode(referralCode.toLowerCase());
			if (!result) {
				referAndEarnMsgList.add("Unable to enter coupon : " + referralCode.toLowerCase());
				return result;
			}

			cfObj.commonClick(priceDetailsPageUtilObj.priceDetailPageObj.getBtnApplySelfCoupon());

			Thread.sleep(1500);
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.EditText", "xpath", 10);
			if (!result) {
				referAndEarnMsgList.add("Referral code was applied in case Sensitive case.");
				return result;
			}

			// Validate Referral Code by adding some space
//			String refCodeWithSpace = referralCode + " ";
//            System.out.println("ReferralCode with Space : -" + refCodeWithSpace);
//
//			result = priceDetailsPageUtilObj.enterCouponCode(refCodeWithSpace);
//			if (!result) {
//				referAndEarnMsgList.add("Unable to enter coupon : " + refCodeWithSpace);
//				return result;
//			}
//
//			cfObj.commonClick(priceDetailsPageUtilObj.priceDetailPageObj.getBtnApplySelfCoupon());
//
//			Thread.sleep(1500);
//			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "edittext_coupon", "id", 10);
//			if (!result) {
//				referAndEarnMsgList.add("Referral code was applied in case if we add space in between referral code.");
//				return result;
//			}

			result = priceDetailsPageUtilObj.enterCouponCode(referralCode);
			if (!result) {
				referAndEarnMsgList.add("Unable to enter coupon : " + referralCode);
				return result;
			}

			result = priceDetailsPageUtilObj.clickApplySelfCouponBtn(driver);
			if (!result) {
				referAndEarnMsgList.addAll(priceDetailsPageUtilObj.priceDetailsMsgList);
				return result;
			}

			result = purchasePackageUtilObj.clickOnOfferLink(driver);
			if (!result) {
				referAndEarnMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
				return result;
			}

			result = priceDetailsPageUtilObj.validatePriceDetailsScreen(driver, referralCode);
			if (!result) {
				referAndEarnMsgList.addAll(priceDetailsPageUtilObj.priceDetailsMsgList);
				return result;
			}

			result = priceDetailsPageUtilObj.closePriceDetailsPage(driver);
			if (!result) {
				referAndEarnMsgList.addAll(priceDetailsPageUtilObj.priceDetailsMsgList);
				return result;
			}

			result = purchasePackageUtilObj.clickBuyNowBtn(driver);
			if (!result) {
				referAndEarnMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
				return result;
			}

			userDetailsLayerUtilObj = new UserDetailsLayerUtil(driver);
			boolean userDetailsPopUp = false;
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				userDetailsPopUp = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.view.View[@content-desc=\"User Details\"]", "xpath", 10);
			} else {
				userDetailsPopUp = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@name,'User Details')]", "xpath", 10);
			}
			if (userDetailsPopUp) {
					result = userDetailsLayerUtilObj.fillUserDetailsForm(driver);
					if (!result) {
						referAndEarnMsgList.addAll(userDetailsLayerUtilObj.userDetailsLayerMsgList);
						return result;
					}

					result = userDetailsLayerUtilObj.clickContinueBtn(driver);
					if (!result) {
						referAndEarnMsgList.addAll(userDetailsLayerUtilObj.userDetailsLayerMsgList);
						return result;
					}
			}

			PaymentUtil paymentUtilObj = new PaymentUtil(driver);

			result = paymentUtilObj.verifyPaymentOptionList(driver);
			if (!result) {
				referAndEarnMsgList.add("Not able to validate Payment option list.");
				return result;
			}

			result = paymentUtilObj.clickOnNetBankingTypePaymentAndValidate(driver);
			if (!result) {
				referAndEarnMsgList.add("Not able to click and validate NetBanking TypePayment, might be 100% code has been applied.");
				System.out.println("Might be 100% code has been applied.");
				referAndEarnMsgList.addAll(paymentUtilObj.paymentMsgList);
				return result;
			}

			result = paymentUtilObj.clickOnSBIBankTab(driver);
			if (!result) {
				referAndEarnMsgList.addAll(paymentUtilObj.paymentMsgList);
				return result;
			}

			result = paymentUtilObj.clickOnPaymentSuccessBtn(driver);
			if (!result) {
				referAndEarnMsgList.addAll(paymentUtilObj.paymentMsgList);
				return result;
			}

			feedbackFormUtilObj = new FeedbackFormUtil(driver);
			result = feedbackFormUtilObj.skipFeedbackForm(driver);
			if (!result) {
				referAndEarnMsgList.addAll(paymentUtilObj.paymentMsgList);
			}

			TestPassUtil testPassUtilObj = new TestPassUtil(driver);
			result = cfObj.commonWaitForElementToBeVisible(driver, testPassUtilObj.testPassObj.getBtnStartLearning(), 10);
			if (!result) {
				referAndEarnMsgList.add("Unable to verify start learning btn in order success page");
				return result;
			}
			cfObj.commonClick(testPassUtilObj.testPassObj.getBtnStartLearning());

			result = homePageUtilObj.clickMyContentButton(driver);
			if (!result) {
				referAndEarnMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			result = homePageUtilObj.JustOpenAndClickHomeBtn(driver);
			if (!result) {
				referAndEarnMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			result = homePageUtilObj.logout(driver);
			if (!result) {
				referAndEarnMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}
			Thread.sleep(2000);

			loginUtilObj = new LoginUtil(driver);
			result = loginUtilObj.verifyLoginUsingEmailIdWithoutCategorySelection(driver, configFileReaderObj.getUserNamePassword().split("/")[0],
                    configFileReaderObj.getUserNamePassword().split("/")[1]);
			if (!result) {
				referAndEarnMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			homePageUtilObj = new HomePageUtil(driver);
			result = homePageUtilObj.justOpenNavigationDrawer(driver);
			if (!result) {
				referAndEarnMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			result = homePageUtilObj.validateReferNowBtn(driver);
			if (!result) {
				referAndEarnMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/know_more", "id", 10);
			if (!result) {
				referAndEarnMsgList.add("refer & earn btn in box in navigation drawer is not visible");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/know_more", "id"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Invite your friends\"]", "xpath", 30);
			if (!result) {
				referAndEarnMsgList.add("invite your friends text is not visible.");
				return result;
			}

			result = validateReferAndEarnScreen(driver);
			if (!result) {
				referAndEarnMsgList.add("Failed to validate ReferAndEarnScreen.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getMyRewardsLink(), 30);
			if (!result) {
				referAndEarnMsgList.add("MyRewardLink is not visible.");
				return result;
			}
			cfObj.commonClick(referAndEarnPageOrObj.getMyRewardsLink());

			result = validateYourRewardScreen(driver);
			if (!result) {
				referAndEarnMsgList.add("Failed to validate YourRewardScreen.");
				return result;
			}

			result = validateWithDrawBtn(driver);
			if (!result) {
				referAndEarnMsgList.add("Failed to validate WithDraw btn.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			referAndEarnMsgList.add("verifyAnotherCodeInCourse_ReferAndEarn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateReferAndEarnScreen(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getInviteYourFrndHeader(), 30);
			if (!result) {
				referAndEarnMsgList.add("Invite your Friend header is not visible.");
				return result;
			}
			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getReferAndEarnScreenBackBtn(),
					10);
			if (!result) {
				referAndEarnMsgList.add("ReferAndEarnScreenBack Btn is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getHelpBtn(), 10);
			if (!result) {
				referAndEarnMsgList.add("Help Btn is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getMyRewardsLink(), 10);
			if (!result) {
				referAndEarnMsgList.add("MyRewards Link is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getReferCourseTab(), 10);
			if (!result) {
				referAndEarnMsgList.add("ReferCourse Tab is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getShareAppTab(), 10);
			if (!result) {
				referAndEarnMsgList.add("ShareApp Tab is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			referAndEarnMsgList.add("validateReferAndEarnScreen_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnHelpBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getHelpBtn(), 10);
			if (!result) {
				referAndEarnMsgList.add("Help Btn is not visible.");
				return result;
			}

			cfObj.commonClick(referAndEarnPageOrObj.getHelpBtn());

			result = validateReferAndEarnHelpScreen(driver);
			if (!result) {
				referAndEarnMsgList.add("Failed to validate ReferAndEarnHelpScreen.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			referAndEarnMsgList.add("clickOnHelpBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateReferAndEarnHelpScreen(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver,
					referAndEarnPageOrObj.getFrequentlyAskedQuestionHeader(), 30);
			if (!result) {
				referAndEarnMsgList.add("FrequentlyAskedQuestionHeader is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getExpandAllBtn(), 30);
			if (!result) {
				referAndEarnMsgList.add("ExpandAll is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getHelpScreenCloseBtn(), 30);
			if (!result) {
				referAndEarnMsgList.add("HelpScreen back button is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			referAndEarnMsgList.add("validateReferAndEarnHelpScreen_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateHelpBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = clickOnHelpBtn(driver);
			if (!result) {
				referAndEarnMsgList.add("Failed to click Help button.");
				return result;
			}

			cfObj.commonClick(referAndEarnPageOrObj.getHelpScreenCloseBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getInviteYourFrndHeader(), 30);
			if (!result) {
				referAndEarnMsgList.add("Failed to close ReferAndEarn Help screen.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			referAndEarnMsgList.add("validateHelpBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateMyRewardLink(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getMyRewardsLink(), 30);
			if (!result) {
				referAndEarnMsgList.add("MyRewardLink is not visible.");
				return result;
			}

			cfObj.commonClick(referAndEarnPageOrObj.getMyRewardsLink());

			result = validateYourRewardScreen(driver);
			if (!result) {
				referAndEarnMsgList.add("Failed to validate YourRewardScreen.");
				return result;
			}

			cfObj.commonClick(referAndEarnPageOrObj.getRewardScreenBackBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getMyRewardsLink(), 30);
			if (!result) {
				referAndEarnMsgList.add("Failed to close Reward Screen.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			referAndEarnMsgList.add("validateMyRewardLink_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateYourRewardScreen(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getYourRewardScreenTitle(),
					30);
			if (!result) {
				referAndEarnMsgList.add("Your Reward title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getEarningAmountText(), 30);
			if (!result) {
				referAndEarnMsgList.add("Earning Amount text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getWithdrawBtn(), 30);
			if (!result) {
				referAndEarnMsgList.add("WithDraw btn is not visible.");
				return result;
			}

			if (referAndEarnPageOrObj.getEarningAmountText().getAttribute("content-desc").charAt(1) == ('0')) {

				result = referAndEarnPageOrObj.getWithdrawBtn().getAttribute("enabled").equalsIgnoreCase("false");
				if (!result) {
					referAndEarnMsgList.add("WithDraw btn is not in Disable state.");
					return result;
				}

			} else {
				result = referAndEarnPageOrObj.getWithdrawBtn().getAttribute("enabled").equalsIgnoreCase("true");
				if (!result) {
					referAndEarnMsgList.add("WithDraw btn is not in Enable state.");
					return result;
				}
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getSuccessfullyInstallHeader(),
					30);
			if (!result) {
				referAndEarnMsgList.add("SuccessfullyInstall Header is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,
					referAndEarnPageOrObj.getSuccessfullyPurchasedHeader(), 30);
			if (!result) {
				referAndEarnMsgList.add("SuccessfullyPurchased Header is not visible.");
				return result;
			}

//			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getTransactionHistoryHeader(),
//					30);
//			if (!result) {
//				refereAndEarnMsgList.add("TransactionHistory Header is not visible.");
//				return result;
//			}
//
//			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getEarningActivityHeader(),
//					30);
//			if (!result) {
//				refereAndEarnMsgList.add("EarningActivity Header is not visible.");
//				return result;
//			}

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getRewardScreenBackBtn(), 30);
			if (!result) {
				referAndEarnMsgList.add("RewardScreenBack Btn is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			referAndEarnMsgList.add("validateYourRewardScreen_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnReferACourseTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getReferCourseTab(), 30);
			if (!result) {
				referAndEarnMsgList.add("ReferACourse tab is not visible.");
				return result;
			}

			cfObj.commonClick(referAndEarnPageOrObj.getReferCourseTab());

		} catch (Exception e) {
			result = false;
			referAndEarnMsgList.add("clickOnReferACourseTab_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateReferACourseTabScreen(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver,
					referAndEarnPageOrObj.getReferOurCourseAndEarnTitle(), 30);
			if (!result) {
				referAndEarnMsgList.add("ReferOurCourseAndEarnTitle is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getReferalCodeText(), 30);
			if (!result) {
				referAndEarnMsgList.add("ReferalCodeText is not visible.");
				return result;
			}

			result = referAndEarnPageOrObj.getReferalCodeText().getAttribute("content-desc").contains("REF");
			if (!result) {
				referAndEarnMsgList.add("ReferalCodeText is not started with REF.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getReferalCodeCopyBtn(), 30);
			if (!result) {
				referAndEarnMsgList.add("ReferalCodeCopy btn is not visible.");
				return result;
			}

			result = validateReferNowBtn(driver);
			if (!result) {
				referAndEarnMsgList.add("Failed to validate ReferNowBtn.");
				return result;
			}

//			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getAmountColumnHeader(), 30);
//			if (!result) {
//				refereAndEarnMsgList.add("AmountColumnHeader is not visible.");
//				return result;
//			}
//
//			result = cfObj.commonWaitForElementToBeVisible(driver,
//					referAndEarnPageOrObj.getReferrerRewardColumnHeader(), 30);
//			if (!result) {
//				refereAndEarnMsgList.add("ReferrerRewardColumnHeader is not visible.");
//				return result;
//			}
//
//			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getRefreeRewardColumnHeader(),
//					30);
//			if (!result) {
//				refereAndEarnMsgList.add("RefreeRewardColumnHeader is not visible.");
//				return result;
//			}
//
//			cfObj.scrollIntoDesc(driver,
//					"Earn More!\nExplore exclusive deals and earn \nrewards for your successful referrals.\nExplore");
//			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getEarnMoreLink(), 30);
//			if (!result) {
//				refereAndEarnMsgList.add("EarnMoreLink is not visible.");
//				return result;
//			}
//
//			cfObj.commonClick(referAndEarnPageOrObj.getEarnMoreLink());
//			
//			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
//					"//android.view.View[contains(@content-desc,'Referred')]", "xpath", 30);
//			if (!result) {
//				refereAndEarnMsgList.add("Referred List are not visible.");
//				return result;
//			}
//
//			cfObj.commonClick(referAndEarnPageOrObj.getReferalScreenBackBtn());
//
//			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getEarnMoreLink(), 30);
//			if (!result) {
//				refereAndEarnMsgList.add("Failed to close Referal screen.");
//				return result;
//			}

			cfObj.scrollIntoDesc(driver, "Leaderboard");

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getLeaderBoardHeader(), 20);
			if (!result) {
				referAndEarnMsgList.add("LeaderBoard Header is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getMonthlyAnnuallyBtn(), 20);
			if (!result) {
				referAndEarnMsgList.add("MonthAnnually btn is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[contains(@content-desc,'Monthly')]/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath", 10);
			if (!result) {
				System.out.println("LeaderBoard user cell is not visible.");
            }

			cfObj.scrollIntoDesc(driver, "Your Rewards\nView all your rewards and referrals");

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getYourRewardLink(), 20);
			if (!result) {
				referAndEarnMsgList.add("YourReward link is not visible.");
				return result;
			}

			cfObj.commonClick(referAndEarnPageOrObj.getYourRewardLink());

			result = validateYourRewardScreen(driver);
			if (!result) {
				referAndEarnMsgList.add("Failed to validate YourReward screen.");
				return result;
			}

			cfObj.commonClick(referAndEarnPageOrObj.getRewardScreenBackBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getYourRewardLink(), 30);
			if (!result) {
				referAndEarnMsgList.add("Failed to close Reward screen.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			referAndEarnMsgList.add("validateReferACourseTabScreen_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnShareAppTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getShareAppTab(), 30);
			if (!result) {
				referAndEarnMsgList.add("ShareApp tab is not visible.");
				return result;
			}

			cfObj.commonClick(referAndEarnPageOrObj.getShareAppTab());

		} catch (Exception e) {
			result = false;
			referAndEarnMsgList.add("clickOnShareAppTab_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateShareAppTabScreen(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getSignupReferralHeader(), 30);
			if (!result) {
				referAndEarnMsgList.add("SignupReferralHeader is not visible.");
				return result;
			}

			result = validateReferNowBtn(driver);
			if (!result) {
				referAndEarnMsgList.add("Failed to validate ReferNowBtn.");
				return result;
			}

			cfObj.scrollDown(driver);

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getYourRewardLink(), 30);
			if (!result) {
				referAndEarnMsgList.add("YourReward link is not visible.");
				return result;
			}

			cfObj.commonClick(referAndEarnPageOrObj.getYourRewardLink());

			result = validateYourRewardScreen(driver);
			if (!result) {
				referAndEarnMsgList.add("Failed to validate YourReward screen.");
				return result;
			}

			cfObj.commonClick(referAndEarnPageOrObj.getRewardScreenBackBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getYourRewardLink(), 20);
			if (!result) {
				referAndEarnMsgList.add("Failed to close Reward screen.");
				return result;
			}

			cfObj.scrollIntoDesc(driver, "Leaderboard");

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getLeaderBoardHeader(), 20);
			if (!result) {
				referAndEarnMsgList.add("LeaderBoard Header is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[contains(@content-desc,'Monthly')]/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
					"xpath", 10);
			if (!result) {

				cfObj.scrollUtill(driver, 1, direction.DOWN);

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.view.View[contains(@content-desc,'Monthly')]/following-sibling::android.view.View/descendant::android.view.View[@content-desc]",
						"xpath", 20);
				if (!result) {
					referAndEarnMsgList.add("LeaderBoard user cell is not visible, after scroll.");
					return result;
				}
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getMonthlyAnnuallyBtn(), 20);
			if (!result) {
				referAndEarnMsgList.add("MonthAnnually btn is not visible.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			referAndEarnMsgList.add("validateShareAppTabScreen_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateReferNowBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getReferNowBtn(), 30);
			if (!result) {
				referAndEarnMsgList.add("ReferNow btn is not visible.");
				return result;
			}

			cfObj.commonClick(referAndEarnPageOrObj.getReferNowBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getReferShareScreenHeader(),
					30);
			if (!result) {
				referAndEarnMsgList.add("ReferShareScreenHeader is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getReferShareScreenCloseBtn(),
					30);
			if (!result) {
				referAndEarnMsgList.add("ReferShareScreen close btn is not visible.");
				return result;
			}

			cfObj.commonClick(referAndEarnPageOrObj.getReferShareScreenCloseBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getReferNowBtn(), 30);
			if (!result) {
				referAndEarnMsgList.add("Failed to close ReferShare Screen.");
				return result;
			}

			cfObj.commonClick(referAndEarnPageOrObj.getReferNowBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getReferShareScreenHeader(),
					30);
			if (!result) {
				referAndEarnMsgList.add("ReferShareScreenHeader is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getCopyToClipboardLink(), 30);
			if (!result) {
				referAndEarnMsgList.add("CopyToClipboardLink is not visible.");
				return result;
			}

			cfObj.commonClick(referAndEarnPageOrObj.getCopyToClipboardLink());

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getCopyToastMessage(), 10);
			if (!result) {
				System.out.println("CopyToClipboard toast message is not visible.");
				result = true;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getReferShareScreenHeader(),
					10);
			if (result) {
				cfObj.commonClick(referAndEarnPageOrObj.getReferShareScreenCloseBtn());
			} else {
				result = true;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getReferNowBtn(), 30);
			if (!result) {
				referAndEarnMsgList.add("Failed to close ReferShare Screen by selecting CopyToClipboard.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			referAndEarnMsgList.add("validateReferNowBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyReferNowLinkUsingCampaign(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String referNowLink = null;
		CreateReferAndEarnCampaignUtil createReferAndEarnCampaignUtil = null;
		try {

			ArrayList<Integer> packageId = new ArrayList<Integer>();
			packageId.add(40702);
			createReferAndEarnCampaignUtil = new CreateReferAndEarnCampaignUtil();
			CreateReferAndEarnCampaignResponse createReferAndEarnCampaignResponse = createReferAndEarnCampaignUtil
					.createReferAndEarnCampaign(packageId);
			if (createReferAndEarnCampaignResponse == null) {
				referAndEarnMsgList.addAll(createReferAndEarnCampaignUtil.referAndEarnCampaignMsgList);
				return false;
			}

			String campaignDesc = createReferAndEarnCampaignResponse.getData().getCampaignDescription();
			if (campaignDesc == null) {
				referAndEarnMsgList.add("CampaignDescription is null.");
				return false;
			}

			loginUtilObj = new LoginUtil(driver);
			result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), false);
			if (!result) {
				referAndEarnMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			homePageUtilObj = new HomePageUtil(driver);
			result = homePageUtilObj.justOpenNavigationDrawer(driver);
			if (!result) {
				referAndEarnMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			result = homePageUtilObj.validateReferNowBtn(driver);
			if (!result) {
				referAndEarnMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/know_more", "id", 10);
			if (!result) {
				referAndEarnMsgList.add("refer & earn btn in box in navigation drawer is not visible");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/know_more", "id"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Invite your friends\"]", "xpath", 30);
			if (!result) {
				referAndEarnMsgList.add("invite your friends text is not visible.");
				return result;
			}

			result = validateReferAndEarnScreen(driver);
			if (!result) {
				referAndEarnMsgList.add("Failed to validate ReferAndEarnScreen.");
				return result;
			}

			cfObj.scrollDown(driver,1);

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getEarnMoreLink(), 30);
			if (!result) {
				referAndEarnMsgList.add("EarnMoreLink is not visible.");
				return result;
			}
			cfObj.commonClick(referAndEarnPageOrObj.getEarnMoreLink());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[contains(@content-desc,'Referred')]", "xpath", 30);
			if (!result) {
				referAndEarnMsgList.add("Referred List are not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getReferNowBtn(), 30);
			if (!result) {
				referAndEarnMsgList.add("ReferNow btn is not visible.");
				return result;
			}

			int count = 0;
			while (!cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//*[contains(@content-desc,'" + campaignDesc + "')]/android.widget.Button", "xpath", 5)
					&& count < 10) {
				cfObj.scrollDown(driver);
				count++;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//*[contains(@content-desc,'" + campaignDesc + "')]/android.widget.Button", "xpath", 10);
			if (!result) {
				referAndEarnMsgList.add(campaignDesc + " CampaignReferNow btn is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver,
					"//*[contains(@content-desc,'" + campaignDesc + "')]/android.widget.Button", "xpath"));

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getReferShareScreenHeader(),
					30);
			if (!result) {
				referAndEarnMsgList.add("ReferShareScreenHeader is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getCopyToClipboardLink(), 30);
			if (!result) {
				referAndEarnMsgList.add("CopyToClipboardLink is not visible.");
				return result;
			}

			cfObj.commonClick_Action(driver, referAndEarnPageOrObj.getCopyToClipboardLink());

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getCopyToastMessage(), 5);
			if (!result) {
				System.out.println("CopyToClipboard toast message is not visible.");
            }

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getReferShareScreenHeader(),
					5);
			if (result) {
				cfObj.commonClick(referAndEarnPageOrObj.getReferShareScreenCloseBtn());
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getReferNowBtn(), 30);
			if (!result) {
				referAndEarnMsgList.add("Failed to close ReferShare Screen by selecting CopyToClipboard.");
				return result;
			}

			referNowLink = cfObj.commonGetTextFromClipBoard(driver);
			if (referNowLink == null) {
				referAndEarnMsgList.add("Getting null on ClipBoard text.");
				return false;
			}

			cfObj.commonClick(referAndEarnPageOrObj.getReferalScreenBackBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getEarnMoreLink(), 30);
			if (!result) {
				referAndEarnMsgList.add("Failed to close Referral screen.");
				return result;
			}

			/*

			cfObj.pressAndroidKey(driver, key.BACK, 2);

			result = homePageUtilObj.logout(driver);
			if (!result) {
				referAndEarnMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			String referalCode = null;
			String[] ref = referNowLink.split(" ");
			for (int i = 0; i < ref.length; i++) {
				if (ref[i].contains("REF_")) {
					referalCode = ref[i];
					break;
				}
			}

			System.out.println("Referral Code:- " + referalCode);

			String strEmailId = "addaAutomation" + Common_Function.randomPhoneNumber(10, "2") + "@gmail.com";

			result = loginUtilObj.signUp(driver, Common_Function.randomPhoneNumber(10, "8"), strEmailId);
			if (!result) {
				referAndEarnMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			homePageUtilObj = new HomePageUtil(driver);
			result = homePageUtilObj.clickStoreBtn(driver);
			if (!result) {
				referAndEarnMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			cfObj.scrollUtill(driver, 1, direction.DOWN);

			storePageUtilObj = new StorePageUtil(driver);
			result = storePageUtilObj.clickLiveClassIcon(driver);
			if (!result) {
				referAndEarnMsgList.addAll(storePageUtilObj.storePageMsgList);
				return result;
			}

			cfObj.waitForPageLoading(driver, 4, 2000, cfObj.commonGetElement(driver, "product_view", "id"));

			productPageUtilObj = new ProductPageUtil(driver);
			result = productPageUtilObj.clickUnpurchasedPackage(driver);
			if (!result) {
				referAndEarnMsgList.addAll(productPageUtilObj.productPageMsgList);
				return result;
			}

			purchasePackageUtilObj = new PurchasePackageUtil(driver);
			result = purchasePackageUtilObj.applyCouponCode(driver, referalCode);
			if (!result) {
				referAndEarnMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
				return result;
			}

			result = purchasePackageUtilObj.clickBuyNowBtn(driver);
			if (!result) {
				referAndEarnMsgList.add("Unable to click buy now btn");
				return result;
			}

			userDetailsLayerUtilObj = new UserDetailsLayerUtil(driver);
			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'User Details')]", "xpath",
					10)) {
				result = userDetailsLayerUtilObj.fillUserDetailsForm(driver);
				if (!result) {
					referAndEarnMsgList.addAll(userDetailsLayerUtilObj.userDetailsLayerMsgList);
					return result;
				}

				result = userDetailsLayerUtilObj.clickContinueBtn(driver);
				if (!result) {
					referAndEarnMsgList.addAll(feedbackFormUtilObj.feedbackFormMsgList);
					return result;
				}
			}

			PaymentUtil paymentUtilObj = new PaymentUtil(driver);
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {

				result = paymentUtilObj.verifyPaymentOptionList(driver);
				if (!result) {
					referAndEarnMsgList.add("Not able to validate Payment option list.");
					return result;
				}
			}
			result = paymentUtilObj.clickOnNetBankingTypePaymentAndValidate(driver);
			if (!result) {
				referAndEarnMsgList.add("Not able to click and validate NetBanking TypePayment. ");
				return result;
			}

			result = paymentUtilObj.clickOnSBIBankTab(driver);
			if (!result) {
				referAndEarnMsgList.add("Not able to click SBI bank Tab. ");
				return result;
			}

			result = paymentUtilObj.clickOnPaymentSuccessBtn(driver);
			if (!result) {
				referAndEarnMsgList.add("Not able to click Payment Success button.");
				return result;
			}

			feedbackFormUtilObj = new FeedbackFormUtil(driver);
			result = feedbackFormUtilObj.skipFeedbackForm(driver);
			if (!result) {
				referAndEarnMsgList.add("Unable to skip feedback form");
				return result;
			}

			result = cfObj.pressAndroidKey(driver, key.BACK, 3);
			if (!result) {
				referAndEarnMsgList.add("Not able to click Back button.");
				return result;
			}

			result = homePageUtilObj.clickMyContentButton(driver);
			if (!result) {
				referAndEarnMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			result = homePageUtilObj.JustOpenAndClickHomeBtn(driver);
			if (!result) {
				referAndEarnMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			result = homePageUtilObj.justOpenNavigationDrawer(driver);
			if (!result) {
				referAndEarnMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			result = homePageUtilObj.validateReferNowBtn(driver);
			if (!result) {
				referAndEarnMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/know_more", "id", 10);
			if (!result) {
				referAndEarnMsgList.add("refer & earn btn in box in navigation drawer is not visible");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/know_more", "id"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Invite your friends\"]", "xpath", 30);
			if (!result) {
				referAndEarnMsgList.add("invite your friends text is not visible.");
				return result;
			}

			result = validateReferAndEarnScreen(driver);
			if (!result) {
				referAndEarnMsgList.add("Failed to validate ReferAndEarnScreen.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getMyRewardsLink(), 30);
			if (!result) {
				referAndEarnMsgList.add("MyRewardLink is not visible.");
				return result;
			}

			cfObj.commonClick(referAndEarnPageOrObj.getMyRewardsLink());

			result = validateYourRewardScreen(driver);
			if (!result) {
				referAndEarnMsgList.add("Failed to validate YourRewardScreen.");
				return result;
			}

			result = validateWithDrawBtn(driver);
			if (!result) {
				referAndEarnMsgList.add("Failed to validate WithDraw btn.");
				return result;
			}

			*/

		} catch (Exception e) {
			result = false;
			referAndEarnMsgList.add("verifyReferNowLink_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnWithdrawBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getWithdrawBtn(), 10);
			if (!result) {
				referAndEarnMsgList.add("WithDraw button is not visible.");
				return result;
			}

			if (referAndEarnPageOrObj.getWithdrawBtn().getAttribute("enabled").equalsIgnoreCase("true")) {
				cfObj.commonClick(referAndEarnPageOrObj.getWithdrawBtn());
			} else {
				referAndEarnMsgList.add("WithDraw button is on disable state.");
            }

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getWithdrawlRequestFormTitle(),
					30);
			if (!result) {
				referAndEarnMsgList.add("Failed to click WithDraw button.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			referAndEarnMsgList.add("clickOnWithdrawBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean fillWithdrawRequestForm(AppiumDriver<MobileElement> driver, String upiId) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getWithdrawlRequestFormTitle(),
					30);
			if (!result) {
				referAndEarnMsgList.add("WithdrawalRequestFormTitle is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,
					referAndEarnPageOrObj.getWithDrawlRequestFormCloseBtn(), 30);
			if (!result) {
				referAndEarnMsgList.add("WithdrawalRequestFormClose btn is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getWithDrawlAmountHeader(),
					30);
			if (!result) {
				referAndEarnMsgList.add("WithdrawalAmount header is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getUpiIdTextField(), 30);
			if (!result) {
				referAndEarnMsgList.add("UpiIdTextField is not visible.");
				return result;
			}

			result = cfObj.commonSetTextTextBox(referAndEarnPageOrObj.getUpiIdTextField(), upiId);
			if (!result) {
				referAndEarnMsgList.add("Failed to enter UpiId on TextField.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getSubmitBtn(), 30);
			if (!result) {
				referAndEarnMsgList.add("Submit btn is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			referAndEarnMsgList.add("fillWithdrawRequestForm_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnSubmitBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, referAndEarnPageOrObj.getSubmitBtn(), 10);
			if (!result) {
				referAndEarnMsgList.add("Submit button is not visible.");
				return result;
			}

			cfObj.commonClick(referAndEarnPageOrObj.getSubmitBtn());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//*[contains(@content-desc,'Transaction in Progress')]", "xpath", 30);
			if (!result) {
				referAndEarnMsgList.add("Failed to click Submit button.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			referAndEarnMsgList.add("clickOnSubmitBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateWithDrawBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = clickOnWithdrawBtn(driver);
			if (!result) {
				referAndEarnMsgList.add("Failed to click Withdrawal button before fail");
				return result;
			}

			result = fillWithdrawRequestForm(driver, "failure@upi");
			if (!result) {
				referAndEarnMsgList.add("Failed to fill WithdrawRequestForm.");
				return result;
			}

			result = clickOnSubmitBtn(driver);
			if (!result) {
				referAndEarnMsgList.add("Failed to click Submit button.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver,
					"//*[contains(@content-desc,'Transaction in Progress')]/android.widget.ImageView[1]", "xpath"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//*[@content-desc='Transaction History']/following-sibling::android.view.View[1]/descendant::android.widget.ImageView",
					"xpath", 30);
			if (!result) {
				referAndEarnMsgList.add("Transaction statement is not visible.");
				return result;
			}

			result = cfObj.commonGetElements(driver,
					"//*[@content-desc='Transaction History']/following-sibling::android.view.View[1]/descendant::android.widget.ImageView",
					"xpath").get(0).getAttribute("content-desc").contains("Failed");
			if (!result) {
				referAndEarnMsgList.add("Transaction Failed statement is not visible.");
				return result;
			}

			result = clickOnWithdrawBtn(driver);
			if (!result) {
				referAndEarnMsgList.add("Failed to click Withdrawal button before success");
				return result;
			}

			result = fillWithdrawRequestForm(driver, "success@upi");
			if (!result) {
				referAndEarnMsgList.add("Failed to fill WithdrawRequestForm.");
				return result;
			}

			result = clickOnSubmitBtn(driver);
			if (!result) {
				referAndEarnMsgList.add("Failed to click Submit button.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver,
					"//*[contains(@content-desc,'Transaction in Progress')]/android.widget.ImageView[1]", "xpath"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//*[@content-desc='Transaction History']/following-sibling::android.view.View[1]/descendant::android.widget.ImageView",
					"xpath", 30);
			if (!result) {
				referAndEarnMsgList.add("Transaction statement is not visible.");
				return result;
			}

			result = cfObj.commonGetElements(driver,
					"//*[@content-desc='Transaction History']/following-sibling::android.view.View[1]/descendant::android.widget.ImageView",
					"xpath").get(0).getAttribute("content-desc").contains("Success");
			if (!result) {
				referAndEarnMsgList.add("Transaction success statement is not visible.");
				return result;
			}

			result = referAndEarnPageOrObj.getWithdrawBtn().getAttribute("enabled").equalsIgnoreCase("false");
			if (!result) {
				referAndEarnMsgList.add("WithDraw btn is not in Disable state.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			referAndEarnMsgList.add("validateWithDrawBtn_Exception" + e.getMessage());
		}
		return result;

	}
}