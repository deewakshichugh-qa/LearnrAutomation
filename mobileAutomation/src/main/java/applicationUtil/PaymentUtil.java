package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.PaymentPage_OR;
import util.Common_Function;
import util.Common_Function.key;
import util.ConfigFileReader;

public class PaymentUtil {

	UserDetailsLayerUtil userDetailsLayerUtilObj;
	PriceDetailsPageUtil priceDetailsUtilObj;
	PaymentPage_OR paymentPageObj;
	LoginUtil newLoginUtilObj;
	HomePageUtil homePageUtilObj;
	StorePageUtil storePageUtilObj;
	EBooksPageUtil eBooksPageUtilObj;
	PurchasePackageUtil purchasePackageUtilObj;
	OrderSuccessUtil orderSuccessUtilObj;
	public ArrayList<String> paymentMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();

	public PaymentUtil(AppiumDriver<MobileElement> driver) {
		paymentPageObj = new PaymentPage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), paymentPageObj);
	}

	public boolean verifyPaymentOptionList(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getSelectPaymentPageTitle(), 30);
			if (!result) {
				paymentMsgList.add("Confirm your plan title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,\"validityInConfirmYourPlan\")]", "xpath", 10);
			if (!result) {
				paymentMsgList.add("validityInConfirmYourPlan title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Plan Amount:\"]", "xpath", 10);
			if (!result) {
				paymentMsgList.add("Plan amount title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Plan Validity:\"]", "xpath", 10);
			if (!result) {
				paymentMsgList.add("Plan validity title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Pay using\"]", "xpath", 10);
			if (!result) {
				paymentMsgList.add("Pay using title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Pay using\"]", "xpath", 10);
			if (!result) {
				paymentMsgList.add("Pay using title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[contains(@content-desc,\"PAY ₹\")]", "xpath", 10);
			if (!result) {
				paymentMsgList.add("Pay Rs btn is not visible.");
				return result;
			}
		} catch (Exception e) {
			paymentMsgList.add("PaymentOptionList_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyPayTMTypePayment(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			newLoginUtilObj = new LoginUtil(driver);

			result = newLoginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), true);
			if (!result) {
				paymentMsgList.addAll(newLoginUtilObj.loginMsgList);
				return result;
			}

			homePageUtilObj = new HomePageUtil(driver);
			result = homePageUtilObj.clickStoreBtn(driver);
			if (!result) {
				paymentMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			storePageUtilObj = new StorePageUtil(driver);
			if (ConfigFileReader.strEnv.equalsIgnoreCase("PRODUCTION")) {
				result = storePageUtilObj.clickLiveClassIcon(driver);
				if (!result) {
					paymentMsgList.addAll(storePageUtilObj.storePageMsgList);
					return result;
				}

				cfObj.waitForPageLoading(driver, 4, 2000, cfObj.commonGetElement(driver, "//*[contains(@content-desc,'packageItem')]", "xpath"));
				cfObj.commonClick(cfObj.commonGetElements(driver, "//*[contains(@content-desc,'packageItem')]", "xpath").get(0));

			} else {

				result = storePageUtilObj.clickEBooksIcon(driver);
				if (!result) {
					paymentMsgList.addAll(storePageUtilObj.storePageMsgList);
					return result;
				}
				eBooksPageUtilObj = new EBooksPageUtil(driver);
				result = eBooksPageUtilObj.clickEBookRandomlyFromList(driver);
				if (!result) {
					paymentMsgList.addAll(eBooksPageUtilObj.eBookMsgList);
					return result;
				}
			}
			purchasePackageUtilObj = new PurchasePackageUtil(driver);
			result = purchasePackageUtilObj.verifyUnpurchasedPackagePage(driver);
			if (!result) {
				paymentMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
				return result;
			}

			if (ConfigFileReader.strEnv.equalsIgnoreCase("production")){

				System.out.println("Not verifying coupon apply for production");
			} else {
				result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackageUtilObj.purchasePackagePageObj.getOfferNumberFromTop(), 5);
				if (result){
					cfObj.commonClick(purchasePackageUtilObj.purchasePackagePageObj.getDiscountRemoveBtn());
				}
			}

			result = purchasePackageUtilObj.clickBuyNowBtn(driver);
			if (!result) {
				paymentMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
				return result;
			}
			Thread.sleep(2000);

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.TextView[@resource-id='photosText']", "xpath", 10)) {

				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@resource-id='ContinueView']", "xpath"));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@resource-id='firstOption']",
						"xpath", 10);
				if (!result) {
					paymentMsgList.add("Adda Payment First option is not visible.");
					return result;
				}

				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@resource-id='firstOption']", "xpath"));
			}
			userDetailsLayerUtilObj = new UserDetailsLayerUtil(driver);
			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"User Details\"]", "xpath",
					10)) {

				result = userDetailsLayerUtilObj.fillUserDetailsForm(driver);
				if (!result) {
					paymentMsgList.addAll(userDetailsLayerUtilObj.userDetailsLayerMsgList);
					return result;
				}
				result = userDetailsLayerUtilObj.clickOnContinueBtn(driver);
				if (!result) {
					paymentMsgList.addAll(userDetailsLayerUtilObj.userDetailsLayerMsgList);
					return result;
				}
			}

			result = verifyPaymentOptionList(driver);
			if (!result) {
				paymentMsgList.add("Not able to validate Payment option list.");
				return result;
			}

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'Paytm')]", "xpath", 10)) {

				result = clickOnPayTMTypePaymentAndValidate(driver);
				if (!result) {
					paymentMsgList.add("Not able to click and validate PayTm TypePayment. ");
					return result;
				}
			} else {
				System.out.println("PayTm payment option is not available.");
				paymentMsgList.add("PayTm payment option is not available.");
			}

		} catch (Exception e) {
			paymentMsgList.add("PayTMTypePayment_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnUPITypePaymentAndValidate(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getOptionUPI(), 30);
			if (!result) {
				paymentMsgList.add("UPI option is not visible.");
				return result;
			}
			cfObj.commonClick(paymentPageObj.getOptionUPI());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[contains(@content-desc,\"PAY ₹\")]", "xpath", 10);
			if (!result) {
				paymentMsgList.add("Pay Rs btn is not visible.");
				return result;
			}
			cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.Button[contains(@content-desc,\"PAY ₹\")]", "xpath"));

			while (cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getPaymentLoaderIcon(), 10)) {
				Thread.sleep(1000);
			}

			result = verifyUpiIdForm(driver);
			if (!result) {
				paymentMsgList.add("Not able to verify UpiId Form.");
				return result;
			}

			result = enterUpiIdOnUpiIdTextField(driver);
			if (!result) {
				paymentMsgList.add("Not able to enter UPI Id. ");
				return result;
			}
		} catch (Exception e) {
			result = false;
			paymentMsgList.add("UPITypePaymentAndValidate_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnUPITypePayment(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getOptionUPI(), 10);
			if (!result) {
				paymentMsgList.add("UPI option is not visible.");
				return result;
			}
			cfObj.commonClick(paymentPageObj.getOptionUPI());
			Thread.sleep(2000);
			while (cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getPaymentLoaderIcon(), 10)) {
				Thread.sleep(1000);
			}
			result = validateUPIPaymentScreenType(driver);
			if (!result) {
				paymentMsgList.add("Not able to validate UPI payment screen type.");
				return result;
			}

			result = enterUpiIdOnUpiIdTextField(driver);
			if (!result) {
				paymentMsgList.add("Not able to enter UPI Id. ");
				return result;
			}

		} catch (Exception e) {
			result = false;
			paymentMsgList.add("clickOnUPITypePayment_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnUPITypePaymentAndValidatePackagePrice(AppiumDriver<MobileElement> driver,
			String productAmount) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getOptionUPI(), 10);
			if (!result) {
				paymentMsgList.add("UPI option is not visible.");
				return result;
			}
			cfObj.commonClick(paymentPageObj.getOptionUPI());
			Thread.sleep(2000);
			int i = 0;
			while (cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getPaymentLoaderIcon(), 10)) {
				Thread.sleep(1000);
				if (i > 8) {
					paymentMsgList.add("Not able to load payment screen.");
					break;
				}
				i++;
			}
			result = validateUPIPaymentScreenType(driver);
			if (!result) {
				paymentMsgList.add("Not able to validate UPI payment screen type.");
				return result;
			}

			result = enterUpiIdOnUpiIdTextField(driver);
			if (!result) {
				paymentMsgList.add("Not able to enter UPI Id. ");
				return result;
			}

			result = validateProductAmount(driver, productAmount);
			if (!result) {
				paymentMsgList.add("Not able to validate ProductAmount. ");
				return result;
			}

		} catch (Exception e) {
			result = false;
			paymentMsgList.add("UPITypePaymentAndValidate_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyUpiIdForm(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getUpiPaymentPageHeader(), 30);
			if (!result) {
				paymentMsgList.add("UpiPaymentPageHeader is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getViewDetailsBtn(), 20);
			if (!result) {
				paymentMsgList.add("ViewDetailsBtn is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.Button[@text='Continue']", "xpath", 10);
			if (!result) {
				paymentMsgList.add("Continue btn is not visible.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			paymentMsgList.add("UpiIdForm_Exception" + e.getMessage());

		}
		return result;
	}

	public boolean enterUpiIdOnUpiIdTextField(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			if (ConfigFileReader.isTablet.equalsIgnoreCase("true")) {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'UPI ID')]",
						"xpath", 30);
				if (!result) {
					paymentMsgList.add("UPI ID header is not visible.");
					return result;
				}

				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[contains(@text,'UPI ID')]", "xpath"));
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getUpiIdTextField(), 10);
			if (!result) {
				paymentMsgList.add("UpiIdTextField is not visible.");
				return result;
			}

			cfObj.commonClick(paymentPageObj.getUpiIdTextField());

			Thread.sleep(500);

			result = cfObj.commonSetTextTextBox(paymentPageObj.getUpiIdTextField(), "test@ybl");
			if (!result) {
				paymentMsgList.add("Not able to enter UPI Id.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			paymentMsgList.add("UPITypePaymentAndValidate_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnPayNowBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.continueBtnForPayment(), 10);
			if (!result) {
				paymentMsgList.add("continueBtnForPayment is not visible.");
				return result;
			}
			cfObj.commonClick(paymentPageObj.continueBtnForPayment());

		} catch (Exception e) {
			result = false;
			paymentMsgList.add("PayNowBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateEditContactDetailsTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getEditContactDetailsTab(), 30);
			if (!result) {
				paymentMsgList.add("EditContactDetailsTab is not visible.");
				return result;
			}
			cfObj.commonClick(paymentPageObj.getEditContactDetailsTab());

			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getEditContactdetailsPageTitle(), 30);
			if (!result) {
				paymentMsgList.add("EditContactdetailsPageTitle is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getPhoneNumberTextField(), 30);
			if (!result) {
				paymentMsgList.add("PhoneNumberTextField is not visible.");
				return result;
			}
			result = cfObj.commonSetTextTextBox(paymentPageObj.getPhoneNumberTextField(), "9876543212");
			if (!result) {
				paymentMsgList.add("Not able to enter mobile number.");
				return result;
			}
			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
				result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getEmailTextField(), 30);
				if (!result) {
					paymentMsgList.add("EmailTextField is not visible.");
					return result;
				}
				result = cfObj.commonSetTextTextBox(paymentPageObj.getEmailTextField(),
						"addaAutomation3711749888@gmail.com");
				if (!result) {
					paymentMsgList.add("Not able to enter Email.");
					return result;
				}
			}

			cfObj.commonClick(paymentPageObj.getContinueBtn());

		} catch (Exception e) {
			result = false;
			paymentMsgList.add("EditContactDetailsTab_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateChangeLanguageTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getChangeLangugeTab(), 30);
			if (!result) {
				paymentMsgList.add("ChangeLangugeTab is not visible.");
				return result;
			}
			cfObj.commonClick(paymentPageObj.getChangeLangugeTab());

			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getAccountChangeLanguageTitle(), 30);
			if (!result) {
				paymentMsgList.add("AccountChangeLanguageTitle is not visible.");
				return result;
			}

			cfObj.commonClick(paymentPageObj.getAccountChangeLanguageCloseBtn());

		} catch (Exception e) {
			result = false;
			paymentMsgList.add("ChangeLanguageTab_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateProductAmount(AppiumDriver<MobileElement> driver, String productAmount) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getPackagePrice(), 30);
			if (!result) {
				paymentMsgList.add("PackagePrice is not visible.");
				return result;
			}
			System.out.println(paymentPageObj.getPackagePrice().getText() + "---->" + productAmount);
			double displayAmount = Double
					.parseDouble(paymentPageObj.getPackagePrice().getText().split(" ")[1].replace(",", ""));

			result = Double.parseDouble(productAmount) == displayAmount;
			if (!result) {
				paymentMsgList.add("Package price is not matching.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			paymentMsgList.add("validateProductAmount_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateViewDetailsBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getViewDetailsBtn(), 30);
			if (!result) {

				driver.hideKeyboard();

				result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getViewDetailsBtn(), 30);
				if (!result) {
					paymentMsgList.add("ViewDetailsBtn is not visible, after close keyboard if open.");
					return result;
				}
			}
			cfObj.commonClick(paymentPageObj.getViewDetailsBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getPriceSummaryTitle(), 30);
			if (!result) {
				paymentMsgList.add("getPriceSummaryTitle is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.grandTotalText(), 30);
			if (!result) {
				paymentMsgList.add("grandTotalText is not visible.");
				return result;
			}

			driver.navigate().back();

		} catch (Exception e) {
			result = false;
			paymentMsgList.add("ViewDetailsBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnUpiPaymentPageCloseBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getUpiPaymentPageCloseBtn(), 30);
			if (!result) {
				paymentMsgList.add("UpiPaymentPageCloseBtn is not visible.");
				return result;
			}
			cfObj.commonClick(paymentPageObj.getUpiPaymentPageCloseBtn());

			result = !(cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getPayNowBtn(), 30));
			if (!result) {
				paymentMsgList.add("PayNow button is visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			paymentMsgList.add("ViewDetailsBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnPayTMTypePaymentAndValidate(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getListOptionPayment().get(1), 10);
			if (!result) {
				paymentMsgList.add("PAYTM option is not visible.");
				return result;
			}
			cfObj.commonClick(paymentPageObj.getListOptionPayment().get(1));
			Thread.sleep(2000);
			while (cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getPaymentLoaderIcon(), 10)) {
				Thread.sleep(1000);
			}

			result = verifyPayTMForm(driver);
			if (!result) {
				paymentMsgList.add("Not able to verify PayTM Form.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getPaytmMobileNumberTextField(), 30);
			if (!result) {
				paymentMsgList.add("PaytmMobileNumberTextField is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			paymentMsgList.add("UPITypePaymentAndValidate_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyPayTMForm(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getPaytmFormTitle(), 30);
			if (!result) {
				paymentMsgList.add("PaytmFormTitle is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getLogInToPayWithPaytmHeader(), 30);
			if (!result) {
				paymentMsgList.add("LogInToPayWithPaytmHeader is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getPaytmMobileNumberTextField(), 30);
			if (!result) {
				paymentMsgList.add("PaytmMobileNumberTextField is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getProceedSecurelyBtn(), 30);
			if (!result) {
				paymentMsgList.add("ProceedSecurelyBtn is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getCheckOutCardTab(), 30);
			if (!result) {
				paymentMsgList.add("CheckOutCardTab is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getCheckOutUpiTab(), 30);
			if (!result) {
				paymentMsgList.add("CheckOutUpiTab is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getCheckOutNetBanking(), 30);
			if (!result) {
				paymentMsgList.add("CheckOutNetBanking is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			paymentMsgList.add("PayTMForm_Exception" + e.getMessage());

		}
		return result;
	}

	public boolean validateCheckOutCardTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getCheckOutCardTab(), 30);
			if (!result) {
				paymentMsgList.add("CheckOutCardTab is not visible.");
				return result;
			}

			cfObj.commonClick(paymentPageObj.getCheckOutCardTab());

			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getCheckOutCardPopUpTitle(), 30);
			if (!result) {
				paymentMsgList.add("CheckOutCardPopUpTitle is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getCardNumberTextField(), 30);
			if (!result) {
				paymentMsgList.add("CardNumberTextField is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getCardExpirationMonthTextField(),
					30);
			if (!result) {
				paymentMsgList.add("CardExpirationMonthTextField is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getCardExpirationYearTextField(), 30);
			if (!result) {
				paymentMsgList.add("CardExpirationYearTextField is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getCvvTextField(), 30);
			if (!result) {
				paymentMsgList.add("CvvTextField is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getPayButton(), 30);
			if (!result) {
				paymentMsgList.add("PayButton is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[3]/android.view.View/android.view.View[2]/android.view.View/android.view.View[1]/android.widget.TextView[2]",
					"xpath", 30);
			if (!result) {
				paymentMsgList.add("CloseButton is not visible.");
				return result;
			}
			if (ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {
				cfObj.commonClick(driver,
						"//android.view.View[3]/android.view.View/android.view.View[2]/android.view.View/android.view.View[1]/android.widget.TextView[2]",
						"xpath");
			} else {
				cfObj.commonClick(driver,
						"//*[@text='Enter Debit or Credit Card details']/following-sibling::android.widget.TextView",
						"xpath");
			}

		} catch (Exception e) {
			result = false;
			paymentMsgList.add("CheckOutCardTab_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnDebitTypePaymentAndValidate(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getOpitonDebitCard(), 20);
			if (!result) {
				paymentMsgList.add("DebitCard option is not visible.");
				return result;
			}
			cfObj.commonClick(paymentPageObj.getOpitonDebitCard());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[contains(@content-desc,\"PAY ₹\")]", "xpath", 10);
			if (!result) {
				paymentMsgList.add("Pay Rs btn is not visible.");
				return result;
			}
			cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.Button[contains(@content-desc,\"PAY ₹\")]", "xpath"));

			while (cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getPaymentLoaderIcon(), 20)) {
				Thread.sleep(1000);
			}

			result = verifyAddCardForm(driver);
			if (!result) {
				paymentMsgList.add("Not able to verify add card form.");
				return result;
			}

			result = enterCardNumber(driver, "5555555555554444");
			if (!result) {
				paymentMsgList.add("Not able to enter card number.");
				return result;
			}

			result = enterCardExpireDate(driver, "1135");
			if (!result) {
				paymentMsgList.add("Not able to enter card Expire date.");
				return result;
			}

			result = enterCardHolderName(driver, "Adda");
			if (!result) {
				paymentMsgList.add("Not able to enter card holder name.");
				return result;
			}

			result = enterCardCVV(driver, "009");
			if (!result) {
				paymentMsgList.add("Not able to enter card CVV.");
				return result;
			}

			result = validateViewDetailsBtn(driver);
			if (!result) {
				paymentMsgList.add("Not able to validate View details button.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			paymentMsgList.add("DebitCardTypePaymentAndValidate_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyAddCardForm(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			if (ConfigFileReader.isTablet.equalsIgnoreCase("false")) {

				result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getAddNewCardTitle(), 20);
				if (!result) {
					paymentMsgList.add("AddNewCardTitle is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						paymentPageObj.getCardDetailsEnterTextField().get(0), 20);
				if (!result) {
					paymentMsgList.add("CardNumberEnterTextField is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						paymentPageObj.getCardDetailsEnterTextField().get(1), 20);
				if (!result) {
					paymentMsgList.add("CardExpiryTextField is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						paymentPageObj.getCardDetailsEnterTextField().get(3), 20);
				if (!result) {
					paymentMsgList.add("CardHolderTextField is not visible.");
					return result;
				}
				result = cfObj.commonWaitForElementToBeVisible(driver,
						paymentPageObj.getCardDetailsEnterTextField().get(2), 20);
				if (!result) {
					paymentMsgList.add("CardCvvTextField is not visible.");
					return result;
				}
			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[@text='Card Number']/preceding-sibling::android.widget.EditText", "xpath", 30);
				if (!result) {
					paymentMsgList.add("CardNumberEnterTextField is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[@text='Expiry']/preceding-sibling::android.widget.EditText", "xpath", 30);
				if (!result) {
					paymentMsgList.add("CardExpiryTextField is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@text,'Card Holder')]/preceding-sibling::android.widget.EditText", "xpath", 30);
				if (!result) {
					paymentMsgList.add("CardHolderTextField is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[@text='CVV']/preceding-sibling::android.widget.EditText", "xpath", 30);
				if (!result) {
					paymentMsgList.add("CardCvvTextField is not visible.");
					return result;
				}

			}
			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getViewDetailsBtn(), 20);
			if (!result) {
				paymentMsgList.add("ViewDetailsBtn is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.continueBtnForPayment(), 20);
			if (!result) {
				paymentMsgList.add("continueBtnForPayment is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			paymentMsgList.add("AddCardForm_Exception" + e.getMessage());

		}
		return result;
	}

	public boolean enterCardNumber(AppiumDriver<MobileElement> driver, String cardNumber) {
		boolean result = true;
		try {

			if (ConfigFileReader.isTablet.equalsIgnoreCase("false")) {

				result = cfObj.commonWaitForElementToBeVisible(driver,
						paymentPageObj.getCardDetailsEnterTextField().get(0), 20);
				if (!result) {
					paymentMsgList.add("CardNumberEnterTextField is not visible.");
					return result;
				}

				cfObj.commonClick(paymentPageObj.getCardDetailsEnterTextField().get(0));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "android:id/autofill_dataset_picker",
						"id", 5);
				if (result) {
					cfObj.tapOnCenter(driver);
				} else {
					result = true;
				}

				paymentPageObj.getCardDetailsEnterTextField().get(0).sendKeys(cardNumber);

				Thread.sleep(2000);

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "android:id/autofill_dataset_picker",
						"id", 5);
				if (result) {
					cfObj.tapOnCenter(driver);
				} else {
					result = true;
				}

				String enteredCardNumber = paymentPageObj.getCardDetailsEnterTextField().get(0).getText().replace(" ",
						"");
				if (enteredCardNumber == null) {
					paymentMsgList.add("Entered card number is null.");
					return false;
				}

				result = enteredCardNumber.equals(cardNumber);
				if (!result) {
					paymentMsgList.add("Entered Card number is not matching.");
					return result;
				}

			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[@text='Card Number']/preceding-sibling::android.widget.EditText", "xpath", 30);
				if (!result) {
					paymentMsgList.add("CardNumberEnterTextField is not visible.");
					return result;
				}

				cfObj.commonClick(cfObj.commonGetElement(driver,
						"//*[@text='Card Number']/preceding-sibling::android.widget.EditText", "xpath"));

				cfObj.commonGetElement(driver, "//*[@text='Card Number']/preceding-sibling::android.widget.EditText",
						"xpath").sendKeys(cardNumber);
			}

		} catch (Exception e) {
			result = false;
			paymentMsgList.add("EnterCardNumber_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean enterCardExpireDate(AppiumDriver<MobileElement> driver, String expireDate) {
		boolean result = true;
		try {

			if (ConfigFileReader.isTablet.equalsIgnoreCase("false")) {
				result = cfObj.commonWaitForElementToBeVisible(driver,
						paymentPageObj.getCardDetailsEnterTextField().get(1), 20);
				if (!result) {
					paymentMsgList.add("CardExpiryTextField is not visible.");
					return result;
				}

				cfObj.commonClick(paymentPageObj.getCardDetailsEnterTextField().get(1));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "android:id/autofill_dataset_picker",
						"id", 5);
				if (result) {
					cfObj.tapOnCenter(driver);
				} else {
					result = true;
				}

				paymentPageObj.getCardDetailsEnterTextField().get(1).sendKeys(expireDate);

				Thread.sleep(2000);

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "android:id/autofill_dataset_picker",
						"id", 5);
				if (result) {
					cfObj.tapOnCenter(driver);
				} else {
					result = true;
				}

				String enteredExpireDate = paymentPageObj.getCardDetailsEnterTextField().get(1).getText()
						.replace("/", "").replace(" ", "");
				if (enteredExpireDate == null) {
					paymentMsgList.add("Entered Expire date is null.");
					return false;
				}
				System.out.println(enteredExpireDate + "-----" + expireDate);
				result = enteredExpireDate.equals(expireDate);
				if (!result) {
					paymentMsgList.add("Entered Card Expire date is not matching.");
					return result;
				}
			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[@text='Expiry']/preceding-sibling::android.widget.EditText", "xpath", 30);
				if (!result) {
					paymentMsgList.add("CardExpiryTextField is not visible.");
					return result;
				}

				cfObj.commonGetElement(driver, "//*[@text='Expiry']/preceding-sibling::android.widget.EditText",
						"xpath").sendKeys(expireDate);
			}

		} catch (Exception e) {
			result = false;
			paymentMsgList.add("EnterCardExpireDate_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean enterCardHolderName(AppiumDriver<MobileElement> driver, String name) {
		boolean result = true;
		try {

			if (ConfigFileReader.isTablet.equalsIgnoreCase("false")) {

				result = cfObj.commonWaitForElementToBeVisible(driver,
						paymentPageObj.getCardDetailsEnterTextField().get(3), 20);
				if (!result) {
					paymentMsgList.add("CardHolderTextField is not visible.");
					return result;
				}

				cfObj.commonClick(paymentPageObj.getCardDetailsEnterTextField().get(3));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "android:id/autofill_dataset_picker",
						"id", 5);
				if (result) {
					cfObj.tapOnCenter(driver);
				} else {
					result = true;
				}

				result = cfObj.commonSetTextTextBox(paymentPageObj.getCardDetailsEnterTextField().get(3), name);
				if (!result) {
					paymentMsgList.add("Not able to enter Card holder name.");
					return result;
				}

				Thread.sleep(2000);

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "android:id/autofill_dataset_picker",
						"id", 5);
				if (result) {
					cfObj.tapOnCenter(driver);
				} else {
					result = true;
				}
			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@text,'Card Holder')]/preceding-sibling::android.widget.EditText", "xpath", 30);
				if (!result) {
					paymentMsgList.add("CardHolderTextField is not visible.");
					return result;
				}

				result = cfObj.commonSetTextTextBox(cfObj.commonGetElement(driver,
						"//*[contains(@text,'Card Holder')]/preceding-sibling::android.widget.EditText", "xpath"),
						name);
				if (!result) {
					paymentMsgList.add("Not able to enter Card holder name.");
					return result;
				}

			}

		} catch (Exception e) {
			result = false;
			paymentMsgList.add("EnterCardHolderName_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean enterCardCVV(AppiumDriver<MobileElement> driver, String cardCVV) {
		boolean result = true;
		try {

			if (ConfigFileReader.isTablet.equalsIgnoreCase("false")) {

				result = cfObj.commonWaitForElementToBeVisible(driver,
						paymentPageObj.getCardDetailsEnterTextField().get(2), 20);
				if (!result) {
					paymentMsgList.add("CardCvvTextField is not visible.");
					return result;
				}

				cfObj.commonClick(paymentPageObj.getCardDetailsEnterTextField().get(2));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "android:id/autofill_dataset_picker",
						"id", 5);
				if (result) {
					cfObj.tapOnCenter(driver);
				} else {
					result = true;
				}

				result = cfObj.commonSetTextTextBox(paymentPageObj.getCardDetailsEnterTextField().get(2), cardCVV);
				if (!result) {
					paymentMsgList.add("Not able to enter Card CVV.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "android:id/autofill_dataset_picker",
						"id", 5);
				if (result) {
					cfObj.tapOnCenter(driver);
				} else {
					result = true;
				}

			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[@text='CVV']/preceding-sibling::android.widget.EditText", "xpath", 30);
				if (!result) {
					paymentMsgList.add("CardCvvTextField is not visible.");
					return result;
				}

				result = cfObj.commonSetTextTextBox(cfObj.commonGetElement(driver,
						"//*[@text='CVV']/preceding-sibling::android.widget.EditText", "xpath"), cardCVV);
				if (!result) {
					paymentMsgList.add("Not able to enter Card CVV.");
					return result;
				}

			}

		} catch (Exception e) {
			result = false;
			paymentMsgList.add("EnterCardCVV_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnCreditTypePaymentAndValidate(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getOpitonCreditCard(), 10);
			if (!result) {
				paymentMsgList.add("CreditCard option is not visible.");
				return result;
			}
			cfObj.commonClick(paymentPageObj.getOpitonCreditCard());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[contains(@content-desc,\"PAY ₹\")]", "xpath", 10);
			if (!result) {
				paymentMsgList.add("Pay Rs btn is not visible.");
				return result;
			}
			cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.Button[contains(@content-desc,\"PAY ₹\")]", "xpath"));

			while (cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getPaymentLoaderIcon(), 10)) {
				Thread.sleep(1000);
			}

			result = verifyAddCardForm(driver);
			if (!result) {
				paymentMsgList.add("Not able to verify add card form.");
				return result;
			}

//			result = enterCardNumber(driver, "5105105105105100");
//			if (!result) {
//				paymentMsgList.add("Not able to enter card number.");
//				return result;
//			}
//
//			result = enterCardExpireDate(driver, "1135");
//			if (!result) {
//				paymentMsgList.add("Not able to enter card Expire date.");
//				return result;
//			}
//
//			result = enterCardHolderName(driver, "Adda");
//			if (!result) {
//				paymentMsgList.add("Not able to enter card holder name.");
//				return result;
//			}
//
//			result = enterCardCVV(driver, "009");
//			if (!result) {
//				paymentMsgList.add("Not able to enter card CVV.");
//				return result;
//			}

			result = validateViewDetailsBtn(driver);
			if (!result) {
				paymentMsgList.add("Not able to validate View details button.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			paymentMsgList.add("CreditCardTypePaymentAndValidate_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnNetBankingTypePaymentAndValidate(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getOptionNetbanking(), 20);
			if (!result) {
				paymentMsgList.add("NetBanking option is not visible.");
				return result;
			}
			cfObj.commonClick(paymentPageObj.getOptionNetbanking());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[contains(@content-desc,\"PAY ₹\")]", "xpath", 10);
			if (!result) {
				paymentMsgList.add("Pay Rs btn is not visible.");
				return result;
			}
			cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.Button[contains(@content-desc,\"PAY ₹\")]", "xpath"));

			while (cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getPaymentLoaderIcon(), 8)) {
				Thread.sleep(1000);
			}

			result = verifyNetBankingForm(driver);
			if (!result) {
				paymentMsgList.add("Not able to verify NetBanking form.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getViewDetailsBtn(), 30);
			if (!result) {
				paymentMsgList.add("ViewDetailsBtn is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			paymentMsgList.add("NetBankingTypePaymentAndValidate_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyNetBankingForm(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getSelectBankTitle(), 30);
			if (!result) {
				paymentMsgList.add("SelectBankTitle is not visible.");
				return result;
			}

			Thread.sleep(2000);
			if (ConfigFileReader.isTablet.equalsIgnoreCase("false")) {

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='Netbanking']", "xpath",
						30);
				if (!result) {
					paymentMsgList.add("Netbanking text is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.suggestedBanksText(), 20);
				if (!result) {
					paymentMsgList.add("suggestedBanksText is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getBankSearchResult(), 20);
				if (!result) {
					paymentMsgList.add("BankSearchResult is not visible.");
					return result;
				}

			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[@text='Select Bank']/following-sibling::android.view.View/android.view.View/android.view.View",
						"xpath", 30);
				if (!result) {
					paymentMsgList.add("BankTypeList is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.widget.Button[@text='Select a different bank']", "xpath", 30);
				if (!result) {
					paymentMsgList.add("SelectDifferentBankDropDown is not visible.");
					return result;
				}
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getViewDetailsBtn(), 20);
			if (!result) {
				paymentMsgList.add("ViewDetailsBtn is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.continueBtnForPayment(), 20);
			if (!result) {
				paymentMsgList.add("PayNowBtn is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			paymentMsgList.add("NetBankingForm_Exception" + e.getMessage());

		}
		return result;
	}

	public boolean clickOnOtherWalletTypePaymentAndValidate(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getOptionOtherWallets(), 20);
			if (!result) {
				paymentMsgList.add("OtherWallet option is not visible.");
				return result;
			}

			cfObj.commonClick(paymentPageObj.getOptionOtherWallets());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[contains(@content-desc,\"PAY ₹\")]", "xpath", 10);
			if (!result) {
				paymentMsgList.add("Pay Rs btn is not visible.");
				return result;
			}
			cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.Button[contains(@content-desc,\"PAY ₹\")]", "xpath"));

			while (cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getPaymentLoaderIcon(), 20)) {
				Thread.sleep(1000);
			}

			result = verifySelectWalletForm(driver);
			if (!result) {
				paymentMsgList.add("Not able to verify select wallet form.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			paymentMsgList.add("NetBankingTypePaymentAndValidate_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifySelectWalletForm(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getSelectWalletTitle(), 20);
			if (!result) {
				paymentMsgList.add("SelectWalletTitle is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='All Wallet Options']", "xpath",
					20);
			if (!result) {
				paymentMsgList.add("All Wallet Options text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getViewDetailsBtn(), 20);
			if (!result) {
				paymentMsgList.add("ViewDetailsBtn is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.continueBtnForPayment(), 20);
			if (!result) {
				paymentMsgList.add("continueBtnForPayment is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			paymentMsgList.add("NetBankingForm_Exception" + e.getMessage());

		}
		return result;
	}

	public boolean verifyCompletePaymentFlowByUsingUPI(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		newLoginUtilObj = new LoginUtil(driver);
		try {

			if (ConfigFileReader.strEnv.equalsIgnoreCase("PRODUCTION")) {

				result = newLoginUtilObj.verifyLoginUsingEmailIdFromConfig(driver);
			} else {
				result = newLoginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), true);
			}
			if (!result) {
				paymentMsgList.addAll(newLoginUtilObj.loginMsgList);
				return result;
			}

			HomePageUtil homeUtilObj = new HomePageUtil(driver);
			result = homeUtilObj.clickMyContentButton(driver);
			if (!result) {
				paymentMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			MyContentUtil myContentUtilObj = new MyContentUtil(driver);
			result = cfObj.waitForPageLoading(driver, 10, 2000, myContentUtilObj.myContentPageObj.getBtnPurchasedSection());
			if (!result) {
				paymentMsgList.add("Purchase page Loading Error.");
				return result;
			}
			cfObj.commonClick(myContentUtilObj.myContentPageObj.getBtnPurchasedSection());

			if (!ConfigFileReader.strEnv.equalsIgnoreCase("production")) {

				result = myContentUtilObj.verifyEmptyPurchasedPage(driver);
				if (!result) {
					paymentMsgList.add("Failed to verifyEmptyPurchasedPage");
					return result;
				}
			}

			result = homeUtilObj.clickStoreBtn(driver);
			if (!result) {
				paymentMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			storePageUtilObj = new StorePageUtil(driver);
			if (ConfigFileReader.strEnv.equalsIgnoreCase("PRODUCTION")) {

				result = storePageUtilObj.clickLiveClassIcon(driver);
				if (!result) {
					paymentMsgList.addAll(storePageUtilObj.storePageMsgList);
					return result;
				}

				cfObj.waitForPageLoading(driver, 4, 2000, cfObj.commonGetElement(driver, "//*[contains(@content-desc,'packageItem')]", "xpath"));

				cfObj.commonClick(cfObj.commonGetElements(driver, "//*[contains(@content-desc,'packageItem')]", "xpath").get(0));

			} else {
				result = storePageUtilObj.clickEBooksIcon(driver);
				if (!result) {
					paymentMsgList.addAll(storePageUtilObj.storePageMsgList);
					return result;
				}

				eBooksPageUtilObj = new EBooksPageUtil(driver);
				result = eBooksPageUtilObj.clickEBookRandomlyFromList(driver);
				if (!result) {
					paymentMsgList.addAll(eBooksPageUtilObj.eBookMsgList);
					return result;
				}
			}

			purchasePackageUtilObj = new PurchasePackageUtil(driver);
			result = purchasePackageUtilObj.verifyUnpurchasedPackagePage(driver);
			if (!result) {
				paymentMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
				return result;
			}

			if (ConfigFileReader.strEnv.equalsIgnoreCase("production")) {

				System.out.println("Not verifying coupon apply for production");
			} else {
				result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackageUtilObj.purchasePackagePageObj.getOfferNumberFromTop(), 8);
				if (result) {
					cfObj.commonClick(purchasePackageUtilObj.purchasePackagePageObj.getDiscountRemoveBtn());
				}
			}

			result = purchasePackageUtilObj.clickBuyNowBtn(driver);
			if (!result) {
				paymentMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
				return result;
			}

			Thread.sleep(2000);

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.TextView[@resource-id='photosText']", "xpath", 10)) {

				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@resource-id='ContinueView']", "xpath"));
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@resource-id='firstOption']",
						"xpath", 10);
				if (!result) {
					paymentMsgList.add("Adda Payment First option is not visible.");
					return result;
				}
				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@resource-id='firstOption']", "xpath"));
			}

			userDetailsLayerUtilObj = new UserDetailsLayerUtil(driver);
			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"User Details\"]", "xpath",
					10)) {

				result = userDetailsLayerUtilObj.fillUserDetailsForm(driver);
				if (!result) {
					paymentMsgList.addAll(userDetailsLayerUtilObj.userDetailsLayerMsgList);
					return result;
				}

				result = userDetailsLayerUtilObj.clickOnContinueBtn(driver);
				if (!result) {
					paymentMsgList.addAll(userDetailsLayerUtilObj.userDetailsLayerMsgList);
					return result;
				}
			}

			result = verifyPaymentOptionList(driver);
			if (!result) {
				paymentMsgList.add("Not able to validate Payment option list.");
				return result;
			}

			if (ConfigFileReader.strEnv.equalsIgnoreCase("production")) {

				PaymentUtil paymentUtilObj = new PaymentUtil(driver);
				result = cfObj.commonWaitForElementToBeVisible(driver, paymentUtilObj.paymentPageObj.getOptionUPI(), 30);
				if (!result) {
					paymentMsgList.add("UPI option is not visible.");
					return result;
				}
				cfObj.commonClick(paymentUtilObj.paymentPageObj.getOptionUPI());

				System.out.println("Verified on production, as cannot do payment.");
				return true;
			}

			result = clickOnUPITypePaymentAndValidate(driver);
			if (!result) {
				paymentMsgList.add("Not able to click and validate UPITypePayment.");
				return result;
			}

			result = clickOnPayNowBtn(driver);
			if (!result) {
				paymentMsgList.add("Not able to click UpiPaymentPageCloseBtn.");
				return result;
			}

			orderSuccessUtilObj = new OrderSuccessUtil(driver);
			result = orderSuccessUtilObj.handleRateUsPopUpWindow(driver);
			if (!result) {
				paymentMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
				return result;
			}

			result = orderSuccessUtilObj.verifyOrderSuccessPage(driver);
			if (!result) {
				paymentMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
				return result;
			}

			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {

				OrderSuccessUtil orderSuccessUtilObj = new OrderSuccessUtil(driver);

				result = orderSuccessUtilObj.clickViewLink(driver);
				if (!result) {
					paymentMsgList.add("Unable to click view link");
					return result;
				}
			}
		} catch (Exception e) {
			paymentMsgList.add("verifyCompletePaymentFlowByUsingUPI_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyCouponApply(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/offer_number",
					"id", 10);
			if (!result) {
				paymentMsgList.add("coupons available text is not visible");
				return result;
			} else {

				cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/offer_number", "id"));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/subtotal", "id",
						10);
				if (!result) {
					paymentMsgList.add("Price Details text is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/amount_payable",
						"id", 10);
				if (!result) {
					paymentMsgList.add("Close btn coupon screen is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.CouponEditBox(), 10);
				if (!result) {
					paymentMsgList.add("CouponEditBox is not visible.");
					return result;
				}

				paymentPageObj.CouponEditBox().sendKeys("AKASH50");

				result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getAllApplyBtns().get(0), 10);
				if (!result) {
					paymentMsgList.add("getAllApplyBtns at 0 index is not visible.");
					return result;
				}

				cfObj.commonClick(paymentPageObj.getAllApplyBtns().get(0));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@text,'Offers & Discounts')]", "xpath", 10);
				if (!result) {
					paymentMsgList.add("coupons available text is not visible");
					return result;
				}
			}

		} catch (Exception e) {
			paymentMsgList.add("verifyCouponApply_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyPaymentFlowUsingDiffMethods(AppiumDriver<MobileElement> driver, String paymentType) {
		boolean result = true;
		newLoginUtilObj = new LoginUtil(driver);
		homePageUtilObj = new HomePageUtil(driver);
		storePageUtilObj = new StorePageUtil(driver);
		purchasePackageUtilObj = new PurchasePackageUtil(driver);
		try {
			if (ConfigFileReader.strEnv.equalsIgnoreCase("production")) {

				result = newLoginUtilObj.verifyLoginUsingEmailId(driver, ConfigFileReader.prodStrUserEmailId, ConfigFileReader.prodEmailMasterPassword, false);
			} else {
				result = newLoginUtilObj.verifySignUpWithoutEmail(driver, Common_Function.randomPhoneNumber(10, "8"));
			}
			if (!result) {
				paymentMsgList.addAll(newLoginUtilObj.loginMsgList);
				return result;
			}

			result = homePageUtilObj.clickStoreBtn(driver);
			if (!result) {
				paymentMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			if (paymentType.equalsIgnoreCase("upi")) {

				result = storePageUtilObj.clickVideoCoursesIcon(driver);
				if (!result) {
					paymentMsgList.addAll(storePageUtilObj.storePageMsgList);
					return result;
				}
				cfObj.waitForPageLoading(driver, 4, 2000, cfObj.commonGetElement(driver, "//*[contains(@content-desc,'packageItem')]", "xpath"));

				// Click on first package
				cfObj.commonClick(cfObj.commonGetElements(driver, "//*[contains(@content-desc,'packageItem')]", "xpath").get(0));

				purchasePackageUtilObj = new PurchasePackageUtil(driver);
				result = purchasePackageUtilObj.verifyUnpurchasedPackagePage(driver);
				if (!result) {
					paymentMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
					return result;
				}

			} else if (paymentType.equalsIgnoreCase("upiAutoPay")) {
				String strTitlePackage = "OLC autopay";

				result = storePageUtilObj.clickSearchIcon(driver);
				if (!result) {
					paymentMsgList.addAll(storePageUtilObj.storePageMsgList);
					return result;
				}

				result = storePageUtilObj.enterPackageNameInSearchField(driver, strTitlePackage);
				if (!result) {
					paymentMsgList.addAll(storePageUtilObj.storePageMsgList);
					return result;
				}

				result = storePageUtilObj.clickPackageNameInSearchResult(driver, strTitlePackage);
				if (!result) {
					paymentMsgList.addAll(storePageUtilObj.storePageMsgList);
					return result;
				}

			} else {
				result = storePageUtilObj.clickLiveClassIcon(driver);
				if (!result) {
					paymentMsgList.addAll(storePageUtilObj.storePageMsgList);
					return result;
				}

				cfObj.waitForPageLoading(driver, 4, 2000, cfObj.commonGetElement(driver, "//*[contains(@content-desc,'packageItem')]", "xpath"));

				// Click on first package
				cfObj.commonClick(cfObj.commonGetElements(driver, "//*[contains(@content-desc,'packageItem')]", "xpath").get(0));

				result = purchasePackageUtilObj.verifyUnpurchasedPackagePage(driver);
				if (!result) {
					paymentMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
					return result;
				}
			}

			if (ConfigFileReader.strEnv.equalsIgnoreCase("production")) {

				System.out.println("Not verifying coupon apply for production");
			} else {
				result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackageUtilObj.purchasePackagePageObj.getOfferNumberFromTop(), 5);
				if (result) {
					cfObj.commonClick(purchasePackageUtilObj.purchasePackagePageObj.getDiscountRemoveBtn());

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"couponViewClick\n" +
							"Coupon available\"]", "xpath", 10);
					if (!result) {
						paymentMsgList.add("After removing coupon, coupon available text not visible");
						return result;
					}
				}
			}

			result = purchasePackageUtilObj.clickBuyNowBtn(driver);
			if (!result) {
				paymentMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
				return result;
			}
			Thread.sleep(500);

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.TextView[@resource-id='photosText']", "xpath", 10)) {

				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@resource-id='ContinueView']", "xpath"));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@resource-id='firstOption']",
						"xpath", 10);
				if (!result) {
					paymentMsgList.add("Adda Payment First option is not visible.");
					return result;
				}
				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@resource-id='firstOption']", "xpath"));
			}

			userDetailsLayerUtilObj = new UserDetailsLayerUtil(driver);
			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"User Details\"]", "xpath",
					10)) {

				result = userDetailsLayerUtilObj.fillUserDetailsForm(driver);
				if (!result) {
					paymentMsgList.addAll(userDetailsLayerUtilObj.userDetailsLayerMsgList);
					return result;
				}

				result = userDetailsLayerUtilObj.clickOnContinueBtn(driver);
				if (!result) {
					paymentMsgList.addAll(userDetailsLayerUtilObj.userDetailsLayerMsgList);
					return result;
				}
			}

			result = verifyPaymentOptionList(driver);
			if (!result) {
				paymentMsgList.add("Not able to validate Payment option list.");
				return result;
			}

			String normalizedPaymentType = paymentType.trim().toLowerCase();

			switch (normalizedPaymentType) {
				case "netbanking": {
					result = clickOnNetBankingTypePaymentAndValidate(driver);
					if (!result) {
						paymentMsgList.add("Not able to click and validate NetBanking TypePayment. ");
						return result;
					}
					if (ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
						System.out.println("Verified on production");
						return true;
					}
					result = clickOnSBIBankTab(driver);
					if (!result) {
						paymentMsgList.add("Not able to click SBI bank Tab. ");
						return result;
					}
					break;
				}
				case "upi": {
					if (ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
						PaymentUtil paymentUtilObj = new PaymentUtil(driver);
						result = cfObj.commonWaitForElementToBeVisible(driver, paymentUtilObj.paymentPageObj.getOptionUPI(), 30);
						if (!result) {
							paymentMsgList.add("UPI option is not visible.");
							return result;
						}
						cfObj.commonClick(paymentUtilObj.paymentPageObj.getOptionUPI());
						System.out.println("Verified on production, as cannot do payment.");
						return true;
					}
					result = clickOnUPITypePaymentAndValidate(driver);
					if (!result) {
						paymentMsgList.add("Not able to click and validate UPITypePayment.");
						return result;
					}
					result = clickOnPayNowBtn(driver);
					if (!result) {
						paymentMsgList.add("Not able to click UpiPaymentPageCloseBtn.");
						return result;
					}
					break;
				}
				case "allothertypes": {
					result = clickOnDebitTypePaymentAndValidate(driver);
					if (!result) {
						paymentMsgList.add("Not able to click and validate DebitCard TypePayment. ");
						return result;
					}

					result = verifyRetryCase(driver);
					if(!result){
						return result;
					}

					result = clickOnCreditTypePaymentAndValidate(driver);
					if (!result) {
						paymentMsgList.add("Not able to click and validate CreditCard type Payment. ");
						return result;
					}

					result = verifyRetryCase(driver);
					if(!result){
						return result;
					}

					result = clickOnEMITypePaymentAndValidate(driver);
					if (!result) {
						paymentMsgList.add("Not able to click and validate EMI TypePayment. ");
						return result;
					}

					result = verifyRetryCase(driver);
					if(!result){
						return result;
					}

					result = clickOnOtherWalletTypePaymentAndValidate(driver);
					if (!result) {
						paymentMsgList.add("Not able to click and validate OtherWallet TypePayment. ");
						return result;
					}

					result = verifyRetryCase(driver);
					if(!result){
						return result;
					}

					System.out.println("Verified the payment flows with retry with unsuccessful payment.");
					return true;
				}
				default: {
					paymentMsgList.add("Unsupported payment type: " + paymentType);
					return false;
				}
			}

			result = clickOnPaymentSuccessBtn(driver);
			if (!result) {
				paymentMsgList.add("Not able to click Payment Success button.");
				return result;
			}

			orderSuccessUtilObj = new OrderSuccessUtil(driver);
			result = orderSuccessUtilObj.handleRateUsPopUpWindow(driver);
			if (!result) {
				paymentMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
				return result;
			}

			result = orderSuccessUtilObj.verifyOrderSuccessPage(driver);
			if (!result) {
				paymentMsgList.add("Not able to verify orderSuccess page.");
				return result;
			}

			OrderSuccessUtil orderSuccessUtilObj = new OrderSuccessUtil(driver);
			result = orderSuccessUtilObj.clickViewLink(driver);
			if (!result) {
				paymentMsgList.add("Unable to click view link");
				return result;
			}
			driver.navigate().back();

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Order Successful!\"]", "xpath", 10);
			if (!result) {
				paymentMsgList.add("Unable to verify order successful text in order success page, after coming back from orders page");
				return result;
			}

			cfObj.pressAndroidKey(driver, key.BACK, 3);

			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getBtnMyContent(), 5);
			if (!result) {
				System.out.println("getBtnMyContent not visible");
				driver.navigate().back();
			}

			HomePageUtil homeUtilObj = new HomePageUtil(driver);
			result = homeUtilObj.clickMyContentButton(driver);
			if (!result) {
				paymentMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			String appPackageName;
			try {
				appPackageName = ((AndroidDriver<MobileElement>) driver).getCurrentPackage();
				driver.terminateApp(appPackageName);
				Thread.sleep(1000);
				driver.activateApp(appPackageName);
			} catch (Exception e) {
				paymentMsgList.add("Error restarting app: " + e.getMessage());
				return false;
			}

			result = homeUtilObj.clickMyContentButton(driver);
			if (!result) {
				paymentMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			MyContentUtil myContentUtilObj = new MyContentUtil(driver);
			result = myContentUtilObj.clickOnPurchasedBtn(driver);
			if (!result) {
				paymentMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'packageCardClick')]", "xpath", 10);
			if (!result) {
				paymentMsgList.add("The purchased package is not visible in my content, after purchasing.");
				return result;
			}

			List<MobileElement> listOfPurchasedCourses = paymentPageObj.listOfPurchasedCourses();
			if (listOfPurchasedCourses.size() != 1) {
				paymentMsgList.add("The list Of purchased courses is greater or less than 1 for new user who purchased the course.");
				return false;
			}
		} catch (Exception e) {
			paymentMsgList.add("verifyCompletePaymentByUsingNetBanking_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	private boolean verifyRetryCase(AppiumDriver<MobileElement> driver) {
		boolean result;
		try {
			driver.navigate().back();
			driver.navigate().back();

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@text='Yes, exit']", "xpath", 10);
			if(!result){

				driver.navigate().back();

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@text='Yes, exit']", "xpath", 10);
				if(!result){
					paymentMsgList.add("The exit popup in razorpay not visible");
					return result;
				}
			}
			cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.Button[@text='Yes, exit']", "xpath"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Order Failed!\"]", "xpath", 10);
			if(!result){
				paymentMsgList.add("Order Failed text not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@content-desc=\"RETRY NOW\"]", "xpath", 10);
			if(!result){
				paymentMsgList.add("Retry now btn text not visible");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.Button[@content-desc=\"RETRY NOW\"]", "xpath"));

			result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackageUtilObj.purchasePackagePageObj.getBtnBuyNow(), 10);
			if (!result) {
				paymentMsgList.add("Unable to verify getBtnBuyNow in package purchase page, after retry now");
				return result;
			}
			cfObj.commonClick(purchasePackageUtilObj.purchasePackagePageObj.getBtnBuyNow());

		} catch (Exception e) {
			paymentMsgList.add("verifyRetryCase_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnSBIBankTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			if (ConfigFileReader.isTablet.equalsIgnoreCase("false")) {

				result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getHdfcBankTab(), 30);
				if (!result) {
					paymentMsgList.add("SBI bank tab is not visible.");
					return result;
				}

				cfObj.commonClick(paymentPageObj.getHdfcBankTab());

			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='SBI']", "xpath", 30);
				if (!result) {
					paymentMsgList.add("SBI bank tab is not visible.");
					return result;
				}
				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='SBI']", "xpath"));
			}

		} catch (Exception e) {
			result = false;
			paymentMsgList.add("SBIBankTab_Exception" + e.getMessage());

		}
		return result;

	}

	public boolean clickOnPaymentSuccessBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getPaymentSuccessBtn(), 30);
			if (!result) {
				paymentMsgList.add("Payment success button is not visible.");
				return result;
			}

			cfObj.commonClick(paymentPageObj.getPaymentSuccessBtn());

		} catch (Exception e) {
			result = false;
			paymentMsgList.add("PaymentSuccessBtn_Exception" + e.getMessage());

		}
		return result;

	}

	public boolean clickOnPaymentFailureBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getPaymentFailureBtn(), 30);
			if (!result) {
				paymentMsgList.add("Payment failure button is not visible.");
				return result;
			}

			cfObj.commonClick(paymentPageObj.getPaymentFailureBtn());

		} catch (Exception e) {
			result = false;
			paymentMsgList.add("PaymentFailureBtn_Exception" + e.getMessage());

		}
		return result;

	}

	public boolean verifyPaymentFailedTitleAndClickOnTryAgainBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getPaymentFailedTitle(), 30);
			if (!result) {
				paymentMsgList.add("PaymentFailedTitle is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.TextView[contains(@text,'Show All Options')]", "xpath", 10);
			if (!result) {
				paymentMsgList.add("Show All Options button is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver,
					"//android.widget.TextView[contains(@text,'Show All Options')]", "xpath"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'Netbanking')]",
					"xpath", 40);
			if (!result) {
				paymentMsgList.add("NetBanking Tab is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "//*[contains(@text,'Netbanking')]", "xpath"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.Button[contains(@text,'SBI')]", "xpath", 10);
			if (!result) {
				paymentMsgList.add("SBI button not visible.");
				return result;
			}

			cfObj.commonClick(
					cfObj.commonGetElement(driver, "//android.widget.Button[contains(@text,'SBI')]", "xpath"));

		} catch (Exception e) {
			result = false;
			paymentMsgList.add("PaymentFailedTitleAndClickOnTryAgainBtn_Exception" + e.getMessage());

		}
		return result;

	}

	public boolean clickOnEMITypePaymentAndValidate(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getOptionEMI(), 20);
			if (!result) {
				paymentMsgList.add("EMI option is not visible.");
				return result;
			}
			cfObj.commonClick(paymentPageObj.getOptionEMI());
			Thread.sleep(2000);

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[contains(@content-desc,\"PAY ₹\")]", "xpath", 10);
			if (!result) {
				paymentMsgList.add("Pay Rs btn is not visible.");
				return result;
			}
			cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.Button[contains(@content-desc,\"PAY ₹\")]", "xpath"));

			while (cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getPaymentLoaderIcon(), 20)) {
				Thread.sleep(1000);
			}

			result = validateEMIPaymentScreenType(driver);
			if (!result) {
				paymentMsgList.add("Not able to validate EMIPaymentScreenType.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			paymentMsgList.add("EMITypePaymentAndValidate_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateUPIPaymentScreenType(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getPhoneNumberTextField(), 30);
			if (!result) {
				paymentMsgList.add("Phone number text field is not visible.");
				return result;
			}
			result = cfObj.commonSetTextTextBox(paymentPageObj.getPhoneNumberTextField(), "9876543212");
			if (!result) {
				paymentMsgList.add("Not able to enter phone number.");
				return result;
			}
			if (cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getEmailTextField(), 5)) {
				result = cfObj.commonSetTextTextBox(paymentPageObj.getEmailTextField(), "test@gmail.com");
				if (!result) {
					paymentMsgList.add("Not able to enter Email Id.");
					return result;
				}
			}

			cfObj.commonClick(paymentPageObj.getProceedBtn());
			Thread.sleep(1000);
			if (ConfigFileReader.isTablet.equalsIgnoreCase("true")) {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='UPI / QR ...']", "xpath",
						30);
				if (!result) {
					paymentMsgList.add("UpiTab is not visible.");
					return result;
				}
				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='UPI / QR ...']", "xpath"));
			} else {
				result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getUpiTab(), 30);
				if (!result) {
					paymentMsgList.add("UpiTab is not visible.");
					return result;
				}

				cfObj.commonClick(paymentPageObj.getUpiTab());
			}
		} catch (Exception e) {
			result = false;
			paymentMsgList.add("UPIPaymentScreenType_Exception" + e.getMessage());

		}
		return result;

	}

	public boolean validateEMIPaymentScreenType(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='EMI']", "xpath", 30)) {

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='All Options']", "xpath",
						20);
				if (!result) {
					paymentMsgList.add("Cardless and Others option not visible");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getViewDetailsBtn(), 20);
				if (!result) {
					paymentMsgList.add("ViewDetailsBtn is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.continueBtnForPayment(), 20);
				if (!result) {
					paymentMsgList.add("continueBtnForPayment is not visible.");
					return result;
				}

			} else {
				paymentMsgList.add("Not EMI payment screen");
				return false;
			}
		} catch (Exception e) {
			result = false;
			paymentMsgList.add("EMIPaymentScreenType_Exception" + e.getMessage());

		}
		return result;
	}

	public boolean completePaymentUsingUPI(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getOptionUPI(), 10);
			if (!result) {
				paymentMsgList.add("UPI option is not visible.");
				return result;
			}
			cfObj.commonClick(paymentPageObj.getOptionUPI());
			Thread.sleep(2000);
			while (cfObj.commonWaitForElementToBeVisible(driver, paymentPageObj.getPaymentLoaderIcon(), 10)) {
				Thread.sleep(1000);
			}

			result = enterUpiIdOnUpiIdTextField(driver);
			if (!result) {
				paymentMsgList.add("Not able to enter UPI Id. ");
				return result;
			}

			result = clickOnPayNowBtn(driver);
			if (!result) {
				paymentMsgList.add("Failed to click PayNow Button. ");
				return result;
			}

		} catch (Exception e) {
			result = false;
			paymentMsgList.add("completePaymentUsingUPI_Exception" + e.getMessage());
		}
		return result;
	}

}
