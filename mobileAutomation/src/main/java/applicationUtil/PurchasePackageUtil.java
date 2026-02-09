package applicationUtil;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.support.PageFactory;

import apiUtill.CouponUtil;
import apiUtill.CreatePackageUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.PurchasePackagePage_OR;
import pojo.createCouponResponse.CreateCouponResponse;
import pojo.createPackageResponse.CreatePackageResponse;
import util.Common_Function;
import util.Common_Function.direction;
import util.ConfigFileReader;

public class PurchasePackageUtil {

	UserDetailsLayerUtil userDetailsLayerUtilObj;
	PriceDetailsPageUtil priceDetailsUtilObj;
	PurchasePackagePage_OR purchasePackagePageObj;
	LoginUtil loginUtilObj;
	HomePageUtil homePageUtilObj;
	StorePageUtil storePageUtilObj;
	PaymentUtil paymentUtilObj;
	OrderSuccessUtil orderSuccessUtilObj;
	CreatePackageUtil createPackageUtilObj;
	CouponUtil couponUtilObj;
	CertificatePreviewUtil certificatePreviewUtilObj;
	ConfigFileReader configFileReaderObj = new ConfigFileReader();
	public ArrayList<String> purchasePackageMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();

	public PurchasePackageUtil(AppiumDriver<MobileElement> driver) {
		purchasePackagePageObj = new PurchasePackagePage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), purchasePackagePageObj);
	}

	public boolean verifyUnpurchasedPackagePage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.getBtnBuyNow(), 10);
			if (!result) {
				purchasePackageMsgList.add("Unable to verify Buy now btn in package purchase page");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.getOfferNumberFromBottom(), 5);
			if (!result) {
				purchasePackageMsgList.add("Unable to verify Coupon available link in package purchase page, as coupon applied.");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.getCoinsCount(), 10);
			if (!result) {
				purchasePackageMsgList.add("Unable to verify coins count in package purchase page");
			}
		} catch (Exception e) {
			purchasePackageMsgList.add("verifyUnpurchasedPackagePage_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyPackageWithCouponAppliedPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.getBtnBuyNow(), 10);
			if (!result) {
				purchasePackageMsgList.add("Unable to verify Buy now btn in package purchase page");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.getCoinsCount(), 10);
			if (!result) {
				purchasePackageMsgList.add("Unable to verify coins count in package purchase page");
			}
		} catch (Exception e) {
			purchasePackageMsgList.add("verifyPackageWithCouponAppliedPage_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickAddPromoCodeBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		priceDetailsUtilObj = new PriceDetailsPageUtil(driver);
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,\"couponViewClick\")]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("Coupon box is not visible");
				return result;
			}
			cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.View[contains(@content-desc,\"couponViewClick\")]", "xpath"));

			result = priceDetailsUtilObj.verifyPriceDetailPage(driver);
			if (!result) {
				purchasePackageMsgList.addAll(priceDetailsUtilObj.priceDetailsMsgList);
				return result;
			}
		} catch (Exception e) {
			result = false;
			purchasePackageMsgList.add("clickAddPromoCodeBtn Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickBuyNowBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.getBtnBuyNow(), 10);
			if (!result) {
				purchasePackageMsgList.add("Unable to verify getBtnBuyNow in package purchase page");
				return result;
			}
			cfObj.commonClick(purchasePackagePageObj.getBtnBuyNow());

			driver.navigate().back();

			result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.getBtnBuyNow(), 10);
			if (!result) {
				purchasePackageMsgList.add("Unable to verify getBtnBuyNow in package purchase page");
				return result;
			}
			cfObj.commonClick(purchasePackagePageObj.getBtnBuyNow());

		} catch (Exception e) {
			result = false;
			purchasePackageMsgList.add("clickBuyNowBtn Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean applyCouponCode(AppiumDriver<MobileElement> driver, String strCouponCode) {
		boolean result = true;
		try {

			if (ConfigFileReader.strEnv.equalsIgnoreCase("production")){

				System.out.println("Not verifying coupon apply for production");
			} else {
				result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.getOfferNumberFromTop(), 8);
				if (result){
					cfObj.commonClick(purchasePackagePageObj.getDiscountRemoveBtn());
				}
			}

			result = clickAddPromoCodeBtn(driver);
			if (!result) {
				purchasePackageMsgList.add("Unable to click add promo code link");
				return result;
			}

			result = priceDetailsUtilObj.enterCouponCode(strCouponCode);
			if (!result) {
				purchasePackageMsgList.add("Unable to enter coupon : " + strCouponCode);
				return result;
			}

			result = priceDetailsUtilObj.clickApplySelfCouponBtn(driver);
			if (!result) {
				purchasePackageMsgList.add("Unable to click apply coupon btn");
				return result;
			}
		} catch (Exception e) {
			purchasePackageMsgList.add("applyCouponCode_Exception: " + e.getMessage());
			return result;
		}
		return result;
	}

	public boolean applyCouponCodeFromCouponsAvailable(AppiumDriver<MobileElement> driver, String strCouponCode) {
		boolean result = true;
		priceDetailsUtilObj = new PriceDetailsPageUtil(driver);
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.getOfferNumberFromTop(), 10);
			if (!result) {
				purchasePackageMsgList.add("Unable to verify title in package purchase page");
			}
			cfObj.commonClick(purchasePackagePageObj.getOfferNumberFromTop());

			result = priceDetailsUtilObj.verifyPriceDetailPage(driver);
			if (!result) {
				purchasePackageMsgList.add("Failed to verify Price details page.");
			}

			result = priceDetailsUtilObj.enterCouponCode(strCouponCode);
			if (!result) {
				purchasePackageMsgList.add("Unable to enter coupon : " + strCouponCode);
				return result;
			}

			result = priceDetailsUtilObj.clickApplySelfCouponBtn(driver);
			if (!result) {
				purchasePackageMsgList.add("Unable to click apply coupon btn");
				return result;
			}

		} catch (Exception e) {
			purchasePackageMsgList.add("applyCouponCodeFromCouponsAvailable_Exception: " + e.getMessage());
			return result;
		}
		return result;
	}

	public boolean validateValidityDropDown(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.getPackageValidity(), 10);
			if (!result) {
				purchasePackageMsgList.add("Package Validity text is not visible.");
				return result;
			}
			result = cfObj.commonWaitForElementToBeVisible(driver,
					purchasePackagePageObj.getPackageSpinnerTextValue().get(0), 10);
			if (!result) {
				purchasePackageMsgList.add("PackageSpinnerTextValue is not visible.");
				return result;
			}
			cfObj.commonClick(purchasePackagePageObj.getPackageSpinnerTextValue().get(0));
			result = cfObj.commonWaitForElementToBeVisible(driver,
					purchasePackagePageObj.getValidityDropDownElementList().get(0), 10);
			if (!result) {
				purchasePackageMsgList.add("ValidityDropDownElementList is not visible.");
				return result;
			}
			int validityList = purchasePackagePageObj.getValidityDropDownElementList().size();
			for (int i = 0; i < validityList; i++) {
				if (i > 0) {
					cfObj.commonClick(purchasePackagePageObj.getPackageSpinnerTextValue().get(0));
				}
				result = cfObj.commonWaitForElementToBeVisible(driver,
						purchasePackagePageObj.getValidityDropDownElementList().get(i), 10);
				if (!result) {
					purchasePackageMsgList.add("ValidityDropDownElementList is not visible.");
					return result;
				}
				String validityType = purchasePackagePageObj.getValidityDropDownElementList().get(i).getText();
				if (validityType == null) {
					purchasePackageMsgList.add("Error in Validity type text.");
					return false;
				}

				cfObj.commonClick(purchasePackagePageObj.getValidityDropDownElementList().get(i));
				result = cfObj.commonVerifyValueTextBox(purchasePackagePageObj.getPackageSpinnerTextValue().get(0),
						validityType);
				if (!result) {
					purchasePackageMsgList.add("Not able to select Validity type.");
					return result;
				}

				result = cfObj.commonVerifyValueTextBox(purchasePackagePageObj.getPackageValidity(),
						purchasePackagePageObj.getPackageSpinnerTextValue().get(0).getText());
				if (!result) {
					purchasePackageMsgList.add("Not able to select Validity type.");
					return result;
				}

			}

		} catch (Exception e) {
			result = false;
			purchasePackageMsgList.add("validateValidityDropDown_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateValidityList(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "selected_validity_tv", "id", 10)) {
				cfObj.commonClick(cfObj.commonGetElement(driver, "selected_validity_tv", "id"));
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "packageSpinnerTV", "id", 10);
				if (!result) {
					purchasePackageMsgList.add("ValidityDropDownElementList is not visible.");
					return result;
				}

				int validityList = purchasePackagePageObj.getPackageSpinnerTextValue().size();
				for (int i = 0; i < validityList; i++) {
					if (i > 0) {
						cfObj.commonClick(cfObj.commonGetElement(driver, "selected_validity_tv", "id"));
					}

					result = cfObj.commonWaitForElementToBeVisible(driver,
							purchasePackagePageObj.getPackageSpinnerTextValue().get(i), 10);
					if (!result) {
						purchasePackageMsgList.add("ValidityDropDownElementList is not visible.");
						return result;
					}
					String validityType = purchasePackagePageObj.getPackageSpinnerTextValue().get(i).getText();
					if (validityType == null) {
						purchasePackageMsgList.add("Error in Validity type text.");
						return false;
					}

					cfObj.commonClick(purchasePackagePageObj.getPackageSpinnerTextValue().get(i));

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "selected_validity_tv", "id", 10);
					if (!result) {
						purchasePackageMsgList.add("Failed to select validity type.");
						return result;
					}

					result = cfObj.commonVerifyValueTextBox(
							cfObj.commonGetElement(driver, "selected_validity_tv", "id"), validityType);
					if (!result) {
						purchasePackageMsgList.add("Selected validity text is not matching.");
						return result;
					}

				}
			} else {

				result = cfObj.commonWaitForElementToBeVisible(driver,
						purchasePackagePageObj.getPackageSpinnerTextValue().get(0), 10);
				if (!result) {
					purchasePackageMsgList.add("PackageSpinnerTextValue is not visible.");
					return result;
				}

				int validityList = purchasePackagePageObj.getPackageSpinnerTextValue().size();
				for (int i = 0; i < validityList; i++) {
					String finalPrice = purchasePackagePageObj.getFinalPackagePrice().getText();
					if (finalPrice == null) {
						purchasePackageMsgList.add("Error in Final price text.");
						return false;
					}

					cfObj.commonClick(purchasePackagePageObj.getPackageSpinnerTextValue().get(i));
					if (i == 0) {
						result = cfObj.commonVerifyValueTextBox(purchasePackagePageObj.getFinalPackagePrice(),
								finalPrice);
						if (!result) {
							purchasePackageMsgList.add("Not able to select Validity type.");
							return result;
						}
					} else {
						result = !cfObj.commonVerifyValueTextBox(purchasePackagePageObj.getFinalPackagePrice(),
								finalPrice);
						if (!result) {
							purchasePackageMsgList.add("Not able to select Validity type.");
							return result;
						}
					}
				}
			}

		} catch (Exception e) {
			result = false;
			purchasePackageMsgList.add("validateValidityList_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateDiscountRemoveBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			if (cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.getDiscountRemoveBtn(), 10)) {

				result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.getFinalPackagePrice(),
						10);
				if (!result) {
					purchasePackageMsgList.add("FinalPackagePrice is not visible.");
					return result;
				}

				double finalPrice = Double
						.parseDouble(purchasePackagePageObj.getFinalPackagePrice().getText().split(" ")[1]);
				System.out.println("Final price:--->" + finalPrice);
				double discountPrice = Double
						.parseDouble(purchasePackagePageObj.getDiscountAmount().getText().split(" ")[2]);
				System.out.println("Discount price:--->" + discountPrice);

				double actualAmount = finalPrice + discountPrice;

				result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.getDiscountRemoveBtn(),
						10);
				if (!result) {
					purchasePackageMsgList.add("DiscountRemoveBtn is not visible.");
					return result;
				}

				cfObj.commonClick(purchasePackagePageObj.getDiscountRemoveBtn());

				result = cfObj.commonVerifyValueTextBox(purchasePackagePageObj.getFinalPackagePrice(),
						String.valueOf(actualAmount));
				if (!result) {
					purchasePackageMsgList.add("Actual Amount is not visible.");
					return result;
				}
			} else {
				result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.getOfferNumberFromBottom(), 10);
				if (!result) {
					purchasePackageMsgList.add("Coupon available text is not visible.");
					return result;
				}
			}

		} catch (Exception e) {
			result = false;
			purchasePackageMsgList.add("validateDiscountRemoveBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean selectValidityType(AppiumDriver<MobileElement> driver, int index) {
		boolean result = true;
		try {

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "selected_validity_tv", "id", 10)) {

				cfObj.commonClick(cfObj.commonGetElement(driver, "selected_validity_tv", "id"));
				result = cfObj.commonWaitForElementToBeVisible(driver,
						purchasePackagePageObj.getPackageSpinnerTextValue().get(index), 10);
				if (!result) {
					purchasePackageMsgList.add("PackageSpinnerTextValue is not visible.");
					return result;
				}

				String validityText = purchasePackagePageObj.getPackageSpinnerTextValue().get(index).getText();

				cfObj.commonClick(purchasePackagePageObj.getPackageSpinnerTextValue().get(index));
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "selected_validity_tv", "id", 10);
				if (!result) {
					purchasePackageMsgList.add("Failed to select validity type.");
					return result;
				}

				result = cfObj.commonVerifyValueTextBox(cfObj.commonGetElement(driver, "selected_validity_tv", "id"),
						validityText);
				if (!result) {
					purchasePackageMsgList.add("Selected validity text is not matching.");
					return result;
				}

			} else {
				result = cfObj.commonWaitForElementToBeVisible(driver,
						purchasePackagePageObj.getPackageSpinnerTextValue().get(index), 10);
				if (!result) {
					purchasePackageMsgList.add("PackageSpinnerTextValue is not visible.");
					return result;
				}
				if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {

					cfObj.commonClick(purchasePackagePageObj.getPackageSpinnerTextValue().get(index));

				} else {
					cfObj.commonClick(purchasePackagePageObj.getPackageSpinnerTextValue().get(0));
					result = cfObj.commonWaitForElementToBeVisible(driver,
							purchasePackagePageObj.getValidityDropDownElementList().get(index), 10);
					if (!result) {
						purchasePackageMsgList.add("ValidityDropDownElementList is not visible.");
						return result;
					}

					String validityType = purchasePackagePageObj.getValidityDropDownElementList().get(index).getText();
					if (validityType == null) {
						purchasePackageMsgList.add("Error in Validity type text.");
						return false;
					}
					cfObj.commonClick(purchasePackagePageObj.getValidityDropDownElementList().get(index));
					result = cfObj.commonVerifyValueTextBox(purchasePackagePageObj.getPackageSpinnerTextValue().get(0),
							validityType);
					if (!result) {
						purchasePackageMsgList.add("Not able to select Validity type.");
						return result;
					}

					result = cfObj.commonVerifyValueTextBox(purchasePackagePageObj.getPackageValidity(),
							purchasePackagePageObj.getPackageSpinnerTextValue().get(0).getText());
					if (!result) {
						purchasePackageMsgList.add("Not able to select Validity type.");
						return result;
					}

				}
			}
			result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.getFinalPackagePrice(), 10);
			if (!result) {
				purchasePackageMsgList.add("FinalPackagePrice is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			purchasePackageMsgList.add("selectValidityType_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateCoinCount(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.coinsCheckbox(), 10);
			if (!result) {
				purchasePackageMsgList.add("coinsCheckbox is not visible.");
				return result;
			}

			if (purchasePackagePageObj.coinsCheckbox().isEnabled()) {

				cfObj.commonClick(purchasePackagePageObj.coinsCheckbox());
				Thread.sleep(2000);
			}

			double finalPrice = Double
					.parseDouble(purchasePackagePageObj.getFinalPackagePrice().getText().split(" ")[1]);
			System.out.println("Final price:--->" + finalPrice);

			result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.getCoinsCount(), 10);
			if (!result) {
				purchasePackageMsgList.add("CoinsCount is not visible.");
				return result;
			}

			double coinCount = Double
					.parseDouble(purchasePackagePageObj.getCoinsCount().getText().replaceAll("Use Coins:", "").trim());
			System.out.println("Coin_Count:--->" + coinCount);

			driver.navigate().back();

		} catch (Exception e) {
			result = false;
			purchasePackageMsgList.add("selectValidityType_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateUseCoinCheckBox(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.getFinalPackagePrice(), 10);
			if (!result) {
				purchasePackageMsgList.add("FinalPackagePrice is not visible.");
				return result;
			}

			double finalPrice = Double
					.parseDouble(purchasePackagePageObj.getFinalPackagePrice().getText().split(" ")[1]);
			System.out.println("Final price:--->" + finalPrice);

			result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.getCoinCheckBox(), 10);
			if (!result) {
				purchasePackageMsgList.add("CoinCheckBox is not visible.");
				return result;
			}

			result = purchasePackagePageObj.getCoinCheckBox().getAttribute("checked").equalsIgnoreCase("false");
			if (!result) {
				purchasePackageMsgList.add("CoinCheckBox is already selected.");
				return result;
			}

			cfObj.commonClick(purchasePackagePageObj.getCoinCheckBox());

			result = purchasePackagePageObj.getCoinCheckBox().getAttribute("checked").equalsIgnoreCase("true");
			if (!result) {
				purchasePackageMsgList.add("Not able to select coin checkBox.");
				return result;
			}
			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
				result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.getDiscountOffer(), 10);
				if (!result) {
					purchasePackageMsgList.add("DiscountOffer is not visible.");
					return result;
				}
			} else {
				result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.getSavedAmount(), 10);
				if (!result) {
					purchasePackageMsgList.add("SavedAmount is not visible.");
					return result;
				}
			}

			double coinCount = Double
					.parseDouble(purchasePackagePageObj.getCoinsCount().getText().replaceAll("Use Coins:", "").trim());
			double discountPrice = Double
					.parseDouble(purchasePackagePageObj.getFinalPackagePrice().getText().split(" ")[1]);
			System.out.println("Discount price:--->" + discountPrice);
			double savedPrice = 0.0;
			if (ConfigFileReader.strApplication.equalsIgnoreCase("Sankalp")) {
				String[] amount = purchasePackagePageObj.getSavedAmount().getText().split(" ");
				savedPrice = Double.parseDouble(amount[amount.length - 1]);
			} else {
				double mrpPrice = Double.parseDouble(purchasePackagePageObj.getMrpPrice().getText().substring(1));
				String discountOffer = purchasePackagePageObj.getDiscountOffer().getText().replace("(", "")
						.replace("% off)", "");
				if (discountOffer == null) {
					purchasePackageMsgList.add("Error in Discount Offer text.");
					return false;
				}
				savedPrice = (Double.parseDouble(discountOffer) * mrpPrice) / 100;
			}
			System.out.println("Saved price:--->" + savedPrice);

			result = (coinCount * 0.1) == savedPrice;
			if (!result) {
				purchasePackageMsgList.add("Saved price is not correct.");
				return result;
			}

			result = finalPrice == (discountPrice + savedPrice);
			if (!result) {
				purchasePackageMsgList.add("Final Price is not matching with sum of discount and Saved price.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			purchasePackageMsgList.add("validateUseCoinCheckBox_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnOfferLink(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.moreOffersBtnOnBottom(), 10);
			if (!result) {
				purchasePackageMsgList.add("More Offers btn on bottom is not visible.");
				return result;
			}
			cfObj.commonClick(purchasePackagePageObj.moreOffersBtnOnBottom());

		} catch (Exception e) {
			result = false;
			purchasePackageMsgList.add("clickOnOfferLink_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyPDPWithCoupon(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String packageTitle = null;
		CreateCouponResponse createCouponResponse = null;
		CreatePackageResponse createPackageResponse = null;
		int validityType = 0;
		try {

			couponUtilObj = new CouponUtil();
			List<Integer> categorylist = new ArrayList<>();
			categorylist.add(6);
			createPackageUtilObj = new CreatePackageUtil();
			createPackageResponse = createPackageUtilObj.createPackage(true, false, categorylist, 3);
			if (createPackageResponse == null) {
				purchasePackageMsgList.addAll(createPackageUtilObj.createPackageMsgList);
				return false;
			}

			createCouponResponse = couponUtilObj.createCoupon("package", "static", "P", "android",
					String.valueOf(createPackageResponse.getData().getId()), "10");
			if (createCouponResponse == null) {
				purchasePackageMsgList.add("Not able to create Coupon.");
				return false;
			}
			packageTitle = createPackageResponse.getData().getTitle();

			if (packageTitle == null) {
				purchasePackageMsgList.add("Not able to fetch Package title.");
				return false;
			}

			String couponCode = createCouponResponse.getData().getCouponCode();
			if (couponCode == null) {
				purchasePackageMsgList.add("Not able to fetch Coupon code.");
				return false;
			}

			for (int i = 0; i < createPackageResponse.getData().getVpData().size(); i++) {
				if (createPackageResponse.getData().getVpData().get(i).getpM()) {
					validityType = createPackageResponse.getData().getVpData().get(i).getmO();
					break;
				}
			}

			System.out.println("Primary validity type:---->" + validityType);
			System.out.println("Package Title------>" + packageTitle);
			System.out.println("Coupon Code------>" + couponCode);

			loginUtilObj = new LoginUtil(driver);

			result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), true);
			if (!result) {
				purchasePackageMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			homePageUtilObj = new HomePageUtil(driver);

			result = homePageUtilObj.clickStoreBtn(driver);
			if (!result) {
				purchasePackageMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			storePageUtilObj = new StorePageUtil(driver);
			result = storePageUtilObj.clickSearchIcon(driver);
			if (!result) {
				purchasePackageMsgList.addAll(storePageUtilObj.storePageMsgList);
				return result;
			}

			result = storePageUtilObj.enterPackageNameInSearchField(driver,
					packageTitle.split(" ")[packageTitle.split(" ").length - 1]);
			if (!result) {
				purchasePackageMsgList.addAll(storePageUtilObj.storePageMsgList);
				return result;
			}

			result = storePageUtilObj.clickPackageNameInSearchResult(driver, packageTitle);
			if (!result) {
				purchasePackageMsgList.addAll(storePageUtilObj.storePageMsgList);
				return result;
			}

			result = validateProductPriceAfterApplyingCoupon(driver, couponCode);
			if (!result) {
				purchasePackageMsgList.add("Not able to validate ProductPrice After Applying Coupon.");
				return result;
			}

			String discountPrice = purchasePackagePageObj.getFinalPackagePrice().getText().split(" ")[1];
			if (discountPrice == null) {
				purchasePackageMsgList.add("Error in Discount Price text.");
				return false;
			}
			System.out.println("Discount price:--->" + discountPrice);

			result = clickBuyNowBtn(driver);
			if (!result) {
				purchasePackageMsgList.add("Not able to click BuyNowBtn.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			purchasePackageMsgList.add("verifyPDPWithZeroAmount_Exception" + e.getMessage());

		}
		return result;
	}

	public boolean validateUseCoinCheckBoxInZeroPrice(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.getCoinCheckBox(), 10);
			if (!result) {
				purchasePackageMsgList.add("CoinCheckBox is not visible.");
				return result;
			}

			result = purchasePackagePageObj.getCoinCheckBox().getAttribute("checked").equalsIgnoreCase("false");
			if (!result) {
				purchasePackageMsgList.add("CoinCheckBox is already selected.");
				return result;
			}

			cfObj.commonClick(purchasePackagePageObj.getCoinCheckBox());

			result = purchasePackagePageObj.getCoinCheckBox().getAttribute("checked").equalsIgnoreCase("false");
			if (!result) {
				purchasePackageMsgList.add("Coin CheckBox is clickable in Zero Price state.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			purchasePackageMsgList.add("validateUseCoinCheckBoxInZeroPrice_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateProductPriceAfterApplyingCoupon(AppiumDriver<MobileElement> driver, String couponCode) {
		boolean result = true;
		try {

			if (ConfigFileReader.strApplication.equalsIgnoreCase("Sankalp")) {
				result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.getPackageValidity(), 10);
				if (!result) {
					purchasePackageMsgList.add("Package Validity text is not visible.");
					return result;
				}
				result = cfObj.commonWaitForElementToBeVisible(driver,
						purchasePackagePageObj.getPackageSpinnerTextValue().get(0), 10);
				if (!result) {
					purchasePackageMsgList.add("PackageSpinnerTextValue is not visible.");
					return result;
				}
				cfObj.commonClick(purchasePackagePageObj.getPackageSpinnerTextValue().get(0));
				result = cfObj.commonWaitForElementToBeVisible(driver,
						purchasePackagePageObj.getValidityDropDownElementList().get(0), 10);
				if (!result) {
					purchasePackageMsgList.add("ValidityDropDownElementList is not visible.");
					return result;
				}
				int validityList = purchasePackagePageObj.getValidityDropDownElementList().size();
				for (int i = 0; i < validityList; i++) {
					if (i > 0) {
						cfObj.commonClick(purchasePackagePageObj.getPackageSpinnerTextValue().get(0));
					}
					result = cfObj.commonWaitForElementToBeVisible(driver,
							purchasePackagePageObj.getValidityDropDownElementList().get(i), 10);
					if (!result) {
						purchasePackageMsgList.add("ValidityDropDownElementList is not visible.");
						return result;
					}
					String validityType = purchasePackagePageObj.getValidityDropDownElementList().get(i).getText();
					if (validityType == null) {
						purchasePackageMsgList.add("Validity type text is not visible.");
						return false;
					}
					cfObj.commonClick(purchasePackagePageObj.getValidityDropDownElementList().get(i));
					result = cfObj.commonVerifyValueTextBox(purchasePackagePageObj.getPackageSpinnerTextValue().get(0),
							validityType);
					if (!result) {
						purchasePackageMsgList.add("Not able to select Validity type.");
						return result;
					}

					result = cfObj.commonVerifyValueTextBox(purchasePackagePageObj.getPackageValidity(),
							purchasePackagePageObj.getPackageSpinnerTextValue().get(0).getText());
					if (!result) {
						purchasePackageMsgList.add("Not able to select Validity type.");
						return result;
					}
					result = validateDiscountRemoveBtn(driver);
					if (!result) {
						purchasePackageMsgList.add("Not able to validate Discount RemoveBtn.");
						return result;
					}

					double withOutDiscountAmount = Double
							.parseDouble(purchasePackagePageObj.getFinalPackagePrice().getText().split(" ")[1]);
					System.out.println("Actual Amount:-->" + withOutDiscountAmount);

					result = applyCouponCode(driver, couponCode);
					if (!result) {
						purchasePackageMsgList.add("Not able to apply Coupon.");
						return result;
					}

					double withDiscountAmount = Double
							.parseDouble(purchasePackagePageObj.getFinalPackagePrice().getText().split(" ")[1]);
					System.out.println("Actual Amount:-->" + withDiscountAmount);

					result = withOutDiscountAmount * 0.9 == withDiscountAmount;
					if (!result) {
						purchasePackageMsgList.add("Coupon amount is not deducted from product price.");
						return result;
					}
				}
			} else {

				if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "selected_validity_tv", "id", 10)) {
					cfObj.commonClick(cfObj.commonGetElement(driver, "selected_validity_tv", "id"));
				}
				result = cfObj.commonWaitForElementToBeVisible(driver,
						purchasePackagePageObj.getPackageSpinnerTextValue().get(0), 10);
				if (!result) {
					purchasePackageMsgList.add("PackageSpinnerTextValue is not visible.");
					return result;
				}

				int validityList = purchasePackagePageObj.getPackageSpinnerTextValue().size();
				for (int i = 0; i < validityList; i++) {

					if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "selected_validity_tv", "id", 10)) {
						cfObj.commonClick(cfObj.commonGetElement(driver, "selected_validity_tv", "id"));

						result = cfObj.commonWaitForElementToBeVisible(driver,
								purchasePackagePageObj.getPackageSpinnerTextValue().get(0), 10);
						if (!result) {
							purchasePackageMsgList.add("PackageSpinnerTextValue is not visible.");
							return result;
						}
					}

					cfObj.commonClick(purchasePackagePageObj.getPackageSpinnerTextValue().get(i));

					result = validateDiscountRemoveBtn(driver);
					if (!result) {
						purchasePackageMsgList.add("Not able to validate Discount RemoveBtn.");
						return result;
					}

					double withOutDiscountAmount = Double
							.parseDouble(purchasePackagePageObj.getFinalPackagePrice().getText().split(" ")[1]);
					System.out.println("Actual Amount:-->" + withOutDiscountAmount);

					result = applyCouponCode(driver, couponCode);
					if (!result) {
						purchasePackageMsgList.add("Not able to apply Coupon.");
						return result;
					}

					double withDiscountAmount = Double
							.parseDouble(purchasePackagePageObj.getFinalPackagePrice().getText().split(" ")[1]);
					System.out.println("Actual Amount:-->" + withDiscountAmount);

					result = withOutDiscountAmount * 0.9 == withDiscountAmount;
					if (!result) {
						purchasePackageMsgList.add("Coupon amount is not deducted from product price.");
						return result;
					}
				}
			}

		} catch (Exception e) {
			result = false;
			purchasePackageMsgList.add("validateProductPriceAfterApplyingCoupon_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateProductPriceWithDefaultCoupon(AppiumDriver<MobileElement> driver, String couponCode) {
		boolean result = true;
		try {

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "selected_validity_tv", "id", 10)) {
				cfObj.commonClick(cfObj.commonGetElement(driver, "selected_validity_tv", "id"));
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,
					purchasePackagePageObj.getPackageSpinnerTextValue().get(0), 10);
			if (!result) {
				purchasePackageMsgList.add("PackageSpinnerTextValue is not visible.");
				return result;
			}

			int validityList = purchasePackagePageObj.getPackageSpinnerTextValue().size();
			for (int i = 0; i < validityList; i++) {

				if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "selected_validity_tv", "id", 10)) {
					cfObj.commonClick(cfObj.commonGetElement(driver, "selected_validity_tv", "id"));

					result = cfObj.commonWaitForElementToBeVisible(driver,
							purchasePackagePageObj.getPackageSpinnerTextValue().get(0), 10);
					if (!result) {
						purchasePackageMsgList.add("PackageSpinnerTextValue is not visible.");
						return result;
					}
				}
				cfObj.commonClick(purchasePackagePageObj.getPackageSpinnerTextValue().get(i));

				result = cfObj.commonVerifyValueTextBox(purchasePackagePageObj.getOfferNumberFromBottom(), couponCode);
				if (!result) {
					purchasePackageMsgList.add("Default Coupon is not visible.");
					return result;
				}

				result = cfObj.commonVerifyValueTextBox(purchasePackagePageObj.getOfferNumberFromBottom(), "coupon applied");
				if (!result) {
					purchasePackageMsgList.add("Coupon applied text is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.getDiscountOffer(), 10);
				if (!result) {
					purchasePackageMsgList.add("Discount Offer is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.getDiscountRemoveBtn(),
						10);
				if (!result) {
					purchasePackageMsgList.add("DiscountRemoveBtn is not visible.");
					return result;
				}
			}

		} catch (Exception e) {
			result = false;
			purchasePackageMsgList.add("validateProductPriceWithDefaultCoupon_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateCertificateTagOnPurchasePage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			int i = 0;
			while (!cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.getCertificateTag(), 4)) {
				cfObj.scrollUtill(driver, 1, direction.DOWN);
				if (i > 2) {
					System.out.println("Not able to find Certificate Tag.");
					break;
				}
				i++;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.getCertificatePreviewBtn(),
					10);
			if (!result) {
				purchasePackageMsgList.add("CertificatePreviewBtn is not visible.");
				return result;
			}

			cfObj.commonClick(purchasePackagePageObj.getCertificatePreviewBtn());

			result = cfObj.waitWhileLoading(driver, "//*[contains(@resource-id,'progressBar')]", "xpath");
			if (!result) {
				purchasePackageMsgList.add("Not able to load the page.");
				return result;
			}

			certificatePreviewUtilObj = new CertificatePreviewUtil(driver);
			result = certificatePreviewUtilObj.validateCertificatePreviewBeforePurchase(driver);
			if (!result) {
				purchasePackageMsgList.addAll(certificatePreviewUtilObj.certificatePreviewMsgList);
				return result;
			}

		} catch (Exception e) {
			result = false;
			purchasePackageMsgList.add("validateCertificateTagOnPurchasePage_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validatePrimaryValidityType(AppiumDriver<MobileElement> driver, int validityType) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.getPackageValidity(), 10);
			if (!result) {
				purchasePackageMsgList.add("PackageValidity is not visible.");
				return result;
			}

			result = cfObj.commonVerifyValueTextBox(purchasePackagePageObj.getPackageValidity(),
					String.valueOf(validityType));
			if (!result) {
				purchasePackageMsgList.add("Primary type validity is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,
					purchasePackagePageObj.getPackageSpinnerTextValue().get(0), 10);
			if (!result) {
				purchasePackageMsgList.add("PackageSpinnerTextValue is not visible.");
				return result;
			}

			result = cfObj.commonVerifyValueTextBox(purchasePackagePageObj.getPackageSpinnerTextValue().get(0),
					String.valueOf(validityType));
			if (!result) {
				purchasePackageMsgList.add("Primary type validity is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			purchasePackageMsgList.add("validatePrimaryValidityType_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyPDPPage(AppiumDriver<MobileElement> driver) {
		boolean result;
		loginUtilObj = new LoginUtil(driver);
		String packageName;
		homePageUtilObj = new HomePageUtil(driver);
		try {
			if (ConfigFileReader.strEnv.equalsIgnoreCase("production")) {

				result = loginUtilObj.verifyLoginUsingEmailIdFromConfig(driver);
				if (!result) {
					purchasePackageMsgList.addAll(loginUtilObj.loginMsgList);
					return result;
				}

				result = homePageUtilObj.clickHomeBtn(driver);
				if (!result) {
					purchasePackageMsgList.addAll(homePageUtilObj.homeMsgList);
					return result;
				}
			} else {
				result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), false);
				if (!result) {
					purchasePackageMsgList.addAll(loginUtilObj.loginMsgList);
					return result;
				}
			}

			result = homePageUtilObj.clickStoreBtn(driver);
			if (!result) {
				purchasePackageMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			storePageUtilObj = new StorePageUtil(driver);
			result = storePageUtilObj.clickSearchIcon(driver);
			if (!result) {
				purchasePackageMsgList.addAll(storePageUtilObj.storePageMsgList);
				return result;
			}

			if (ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
				packageName = "Banking";
			} else {
				packageName = "OLC autopay";
			}

			result = storePageUtilObj.enterPackageNameInSearchField(driver, packageName);
			if (!result) {
				purchasePackageMsgList.addAll(storePageUtilObj.storePageMsgList);
				return result;
			}

			result = verifyFiltersInSearchFromStore(driver);
			if(!result){
				return result;
			}

			result = storePageUtilObj.clickPackageNameInSearchResult(driver, packageName);
			if (!result) {
				purchasePackageMsgList.addAll(storePageUtilObj.storePageMsgList);
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,'" + packageName + "')]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("Package name: " + packageName + " is not visible on pdp");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"shareClick\"]", "xpath", 10);
			if(!result){
				purchasePackageMsgList.add("Share btn is not visible on pdp");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"backClick\"]", "xpath", 10);
			if(!result){
				purchasePackageMsgList.add("Back btn is not visible on pdp");
				return result;
			}

			result = verifyCouponAvailable(driver);
			if (!result) {
				return result;
			}

			result = verifyAdmissionEnquiry(driver);
			if (!result) {
				return result;
			}

			result = verifyWhatWillYouGet(driver);
			if (!result) {
				return result;
			}

			result = verifyProductOffering(driver);
			if (!result) {
				return result;
			}

			result = verifyExamCovered(driver);
			if (!result) {
				return result;
			}

			result = verifyAboutCourseTab(driver);
			if (!result) {
				return result;
			}

			result = verifyOverviewTab(driver);
			if (!result) {
				purchasePackageMsgList.add("Overview Tab is not verified");
				return result;
			}

			result = verifyFAQTab(driver);
			if (!result) {
				purchasePackageMsgList.add("FAQ Tab is not verified");
				return result;
			}

			result = verifyNeedAnyHelp(driver);
			if (!result) {
				purchasePackageMsgList.add("Need any help section is not verified");
				return result;
			}

			result = verifyOurPlans_MoreOffers(driver);
			if(!result){
				return result;
			}
		} catch (Exception e) {
			result = false;
			purchasePackageMsgList.add("verifyPDPPage_Exception" + e.getMessage());
		}
		return result;
	}

	private boolean verifyOurPlans_MoreOffers(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Our Plans\"]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("Our plans text is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,\"onExpandPlanSelectionClick\")]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("Validity selected text is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,\"finalPriceOnBottomStrip\")]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("Final price on bottom text is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@content-desc=\"BUY NOW\"]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("Buy now btn is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.moreOffersBtnOnBottom(), 10);
			if (!result) {
				purchasePackageMsgList.add("More offers btn is not visible");
				return result;
			}
			cfObj.commonClick(purchasePackagePageObj.moreOffersBtnOnBottom());

			result = verifyPriceDetailsPage(driver);
			if(!result){
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.validitySelectedAtBottom(), 10);
			if (!result) {
				purchasePackageMsgList.add("Validity selected text is not visible");
				return result;
			}
			cfObj.commonClick(purchasePackagePageObj.validitySelectedAtBottom());

			result = verifyOurPlansBottomsheet(driver);
			if(!result){
				return result;
			}
		} catch (Exception e) {
			result = false;
			purchasePackageMsgList.add("verifyOurPlans_MoreOffers_Exception" + e.getMessage());
		}
		return result;
	}

	private boolean verifyOurPlansBottomsheet(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.arrowDownClickOnOurPlans(), 10);
			if (!result) {
				purchasePackageMsgList.add("arrowDownClickOnOurPlans button is not visible in bottomsheet");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Our Plans\"]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("Validity selected text is not visible in bottomsheet");
				return result;
			}

			cfObj.commonClick(purchasePackagePageObj.arrowDownClickOnOurPlans());

			result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.validitySelectedAtBottom(), 10);
			if (!result) {
				purchasePackageMsgList.add("Validity selected text is not visible, after closing the bottomsheet");
				return result;
			}
			cfObj.commonClick(purchasePackagePageObj.validitySelectedAtBottom());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@content-desc=\"BUY NOW\"]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("Buy now btn is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.moreOffersBtnOnBottom(), 10);
			if (!result) {
				purchasePackageMsgList.add("More Offers btn on bottom is not visible.");
				return result;
			}

			result = cfObj.commonWaitForListOfElementsToBeVisible(driver, purchasePackagePageObj.differentTypeValidityPlanCard(), 10);
			if (!result) {
				purchasePackageMsgList.add("Types of validities are not visible");
				return result;
			}

			for(int i=0; i<purchasePackagePageObj.differentTypeValidityPlanCard().size(); i++){

				result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.differentTypeValidityPlanCard().get(i), 10);
				if (!result) {
					purchasePackageMsgList.add("Validity at "+ i +" is not visible");
					return result;
				}
			}
		} catch (Exception e) {
			result = false;
			purchasePackageMsgList.add("verifyOurPlansBottomsheet_Exception" + e.getMessage());
		}
		return result;
	}

	private boolean verifyFiltersInSearchFromStore(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"sortClick\"]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("Sort btn is not visible.");
				return result;
			}
			cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.ImageView[@content-desc=\"sortClick\"]", "xpath"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Sort By\"]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("Sort by text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.RadioButton[@content-desc=\"Latest\"]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("Latest btn text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.RadioButton[@content-desc=\"Best Seller\"]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("Best seller btn text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.RadioButton[@content-desc=\"Price : Low to High\"]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("Price : Low to High btn text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.RadioButton[@content-desc=\"Price : High to Low\"]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("Price : High to Low btn text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"closeClick\"]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("close icon is not visible.");
				return result;
			}

			driver.navigate().back();

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"filterIconButton\"]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("filterIconButton is not visible.");
				return result;
			}
			cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.ImageView[@content-desc=\"filterIconButton\"]", "xpath"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Filters\"]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("Filters heading is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"resetFilterClick\n" +
					"CLEAR ALL\"]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("Clear btn is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,\"filterCategoryClick\")]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("Filters category btns are not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@content-desc=\"CLOSE\"]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("Cancel btn is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@content-desc=\"APPLY\"]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("Apply btn is not visible.");
				return result;
			}

			driver.navigate().back();

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"sortClick\"]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("Sort btn is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"filterIconButton\"]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("filterIconButton is not visible.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			purchasePackageMsgList.add("verifyFiltersInSearchFromStore_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyCouponAvailable(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,\"Coupon available\")]", "xpath", 5);
			if (!result) {

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,\"coupon applied\")]", "xpath", 5);
				if (!result) {
					purchasePackageMsgList.add("Coupon applied text and the coupons available text are not visible.");
					return result;
				} else {

					cfObj.commonClick(cfObj.commonGetElement(driver,"//android.widget.ImageView[@content-desc=\"couponTextClick\"]","xpath"));

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,\"Coupon available\")]", "xpath", 5);
					if (!result) {
						purchasePackageMsgList.add("When coupon applied is removed, then also Coupons available text not visible");
						return result;
					}
				}
			}
			cfObj.commonClick(purchasePackagePageObj.getCouponAvailableTab());

			result = verifyPriceDetailsPage(driver);
			if(!result){
				return result;
			}
		} catch (Exception e) {
			result = false;
			purchasePackageMsgList.add("verifyCouponAvailable_Exception: " + e.getMessage());
		}
		return result;
	}

	private boolean verifyPriceDetailsPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Subtotal\"]", "xpath", 30);
			if (!result) {
				purchasePackageMsgList.add("subtotal is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Coins Used\"]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("coins-used is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Amount Payable\"]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("amount payable is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Available Offers\"]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("Available offers text is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.Button[@content-desc=\"BUY NOW\"]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("Buy now btn is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"applyClick\n" +
							"APPLY\"]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("Apply btn is not visible");
				return result;
			}

			result = verifyUseCoinInCouponAvailablePage(driver);
			if (!result) {
				return result;
			}

			driver.navigate().back();

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"shareClick\"]", "xpath", 10);
			if(!result){
				purchasePackageMsgList.add("Share btn is not visible, when come back from Price Details page");
				return result;
			}
		} catch (Exception e) {
			result = false;
			purchasePackageMsgList.add("verifyPriceDetailsPage_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyUseCoinInCouponAvailablePage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//*[contains(@content-desc,\"Use Coins:\")]", "xpath", 10);
			if (result) {

				if (cfObj.commonGetElement(driver, "//android.widget.CheckBox[@checked='true']", "xpath")
						.isDisplayed()) {

					cfObj.commonClick(
							cfObj.commonGetElement(driver, "//android.widget.CheckBox[@checked='true']", "xpath"));

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
							"//android.widget.CheckBox[@checked='false']", "xpath", 10);
					if (!result) {
						purchasePackageMsgList.add("check-box is not clicked to false");
						return result;
					}

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
							"//android.view.View[@content-desc=\"Coins Used\"]", "xpath", 5);
					if (result) {
						purchasePackageMsgList.add("coins-used is visible, even after removing coins");
						return false;
					}

					cfObj.commonClick(
							cfObj.commonGetElement(driver, "//android.widget.CheckBox[@checked='false']", "xpath"));

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
							"//android.widget.CheckBox[@checked='true']", "xpath", 10);
					if (!result) {
						purchasePackageMsgList.add("check-box is not clicked to true");
						return result;
					}

				} else if (cfObj.commonGetElement(driver, "//android.widget.CheckBox[@checked='false']", "xpath")
						.isDisplayed()) {

					cfObj.commonClick(
							cfObj.commonGetElement(driver, "//android.widget.CheckBox[@checked='false']", "xpath"));

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
							"//android.widget.CheckBox[@checked='true']", "xpath", 10);
					if (!result) {
						purchasePackageMsgList.add("check-box is not clicked to true");
						return result;
					}
				}
			}
		} catch (Exception e) {
			result = false;
			purchasePackageMsgList.add("verifyUseCoinInCouponAvailablePage_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyAvailableOffersInCouponAvailablePage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String applyCoupon = null;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.EditText[@resource-id=\"com.adda247.app:id/edittext_coupon\"]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("Input coupon box is not clicked");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.TextView[@resource-id=\"com.adda247.app:id/apply_self_coupon\"]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("Apply button in Input coupon box is not clicked");
				return result;
			}

			cfObj.commonSetTextTextBox(purchasePackagePageObj.getCouponAvailableTab(), "adda247");

			cfObj.commonClick(cfObj.commonGetElement(driver,
					"//android.widget.TextView[@resource-id=\\\"com.adda247.app:id/apply_self_coupon\\\"]", "xpath"));

			if (!cfObj.commonGetElements(driver,
                    "//android.widget.RelativeLayout[@resource-id=\"com.adda247.app:id/frame_layout\"]", "xpath").isEmpty()) {

				applyCoupon = purchasePackagePageObj.getNameOfCoupon().get(0).getText();

				cfObj.commonClick(purchasePackagePageObj.getApplyButton().get(0));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.widget.TextView[contains(@text, 'coupon applied')]", "xpath", 10);
				if (!result) {
					purchasePackageMsgList.add("coupon applied is not visible");
					return result;
				}
			} else {
				driver.navigate().back();
			}

		} catch (Exception e) {
			result = false;
			purchasePackageMsgList.add("verifyAvailableOffersInCouponAvailablePage_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyAdmissionEnquiry(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.ImageView[@content-desc=\"admissionInquiryClick\n" +
							"For Admission Enquiry\n" +
							"CALL NOW\"]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("'For Admission Enquiry' text is not visible");
				return result;
			}
		} catch (Exception e) {
			result = false;
			purchasePackageMsgList.add("verifyAdmissionEnquiry_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyWhatWillYouGet(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"What will you get\"]", "xpath", 10);
			if (!result) {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.view.View[@content-desc=\"What will you get\"]", "xpath", 10);
				if (!result) {
					purchasePackageMsgList.add("'what will you get' header text is not visible, might be due to scroll");
					return true;
				}
			}
		} catch (Exception e) {
			result = false;
			purchasePackageMsgList.add("verifyWhatWillYouGet_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyProductOffering(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			cfObj.scrollUtill(driver, 1, direction.DOWN);

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Product Offerings\"]", "xpath", 10);
			if (!result) {

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.view.View[@content-desc=\"Product Offerings\"]", "xpath", 10);
				if (!result) {
					purchasePackageMsgList.add("Product Offerings heading is not visible, might be due to scroll");
					return true;
				}
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,\"onClickWhatWillYouGetItem\")]", "xpath",
					10);
			if (!result) {
				purchasePackageMsgList.add("'product offering' container content is not visible, might be scroll issue");
				return result;
			}

			// Click on elements and check inside.

		} catch (Exception e) {
			result = false;
			purchasePackageMsgList.add("verifyProductOffering_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyExamCovered(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Exams Covered\"]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("'Exams Covered' title is not visible, might be scroll very much.");
				return true;
			}

			result = cfObj.commonWaitForListOfElementsToBeVisible(driver, purchasePackagePageObj.getListOfExamsCovered(), 10);
			if (!result) {
				purchasePackageMsgList.add("Exam view is not visible");
				return result;
			}

			if (purchasePackagePageObj.getListOfExamsCovered().isEmpty()) {

				purchasePackageMsgList.add("list of Exams Covered is not visible");
				return false;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@content-desc=\"examCoveredItemClick\n" +
					"View More\"]", "xpath", 5);
			if (!result) {
				System.out.println("View More button is not visible in Exam Covered, less exams covered.");
				result = true;
			} else {

				cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.ImageView[@content-desc=\"examCoveredItemClick\n" +
						"View More\"]", "xpath"));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Exams Covered\"]", "xpath", 5);
				if (!result) {
					purchasePackageMsgList.add("The exam covered heading is not visible in bottomsheet");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"closeClick\"]", "xpath", 5);
				if (!result) {
					purchasePackageMsgList.add("The exam covered close btn is not visible in bottomsheet");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[contains(@content-desc,\"examCoveredItemClick\")]", "xpath", 5);
				if (!result) {
					purchasePackageMsgList.add("The exams image & title is not visible in bottomsheet");
					return result;
				}

				cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.View[@content-desc=\"closeClick\"]", "xpath"));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.view.View[@content-desc=\"Exams Covered\"]", "xpath", 10);
				if (!result) {
					purchasePackageMsgList.add("'Exams Covered' title is not visible, after coming back from bottomsheet");
					return result;
				}
			}
		} catch (Exception e) {
			result = false;
			purchasePackageMsgList.add("verifyExamCovered_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyAboutCourseTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//*[contains(@content-desc, \"About course\")]", "xpath", 10);
			if (!result) {
				cfObj.scrollUtill(driver, 1, direction.DOWN);

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@content-desc, \"About course\")]", "xpath", 10);
				if (!result) {
					purchasePackageMsgList.add("About course Tab is not visible, after scrolling");
					return result;
				}
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Salient Features\"]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("Salient Features title is not visible in About Course Tab");
				result = true;
			}
		} catch (Exception e) {
			result = false;
			purchasePackageMsgList.add("verifyAboutCourseTab_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyOverviewTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.getOverViewTab(), 10);
			if (!result) {
				purchasePackageMsgList.add("Overview Tab is not visible");
				return result;
			}
			cfObj.commonClick(purchasePackagePageObj.getOverViewTab());

		} catch (Exception e) {
			result = false;
			purchasePackageMsgList.add("verifyOverviewTab_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyFAQTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			cfObj.swipeLeftWithNtimes(6, driver);

			result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.getFAQTab(), 10);
			if (!result) {
				purchasePackageMsgList.add("FAQs Tab is not visible, might be scroll not worked");
				return true;
			}
			cfObj.commonClick(purchasePackagePageObj.getFAQTab());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"expandAllClick\n" +
					"EXPAND ALL\"]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("Expand All button Tab is not visible in FAQ section");
				return result;
			}
			cfObj.commonClick(purchasePackagePageObj.getExpandButtonInFAQsection());

			Thread.sleep(500);

			cfObj.commonClick(purchasePackagePageObj.getCollapseAllInFAQsection());

			if (ConfigFileReader.strEnv.equalsIgnoreCase("production")){
				cfObj.swipeRightWithNtimes(6, driver);
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, purchasePackagePageObj.getOverViewTab(), 10);
			if (!result) {
				purchasePackageMsgList.add("Overview Tab is not visible");
				return result;
			}
			cfObj.commonClick(purchasePackagePageObj.getOverViewTab());

		} catch (Exception e) {
			result = false;
			purchasePackageMsgList.add("verifyFAQTab_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyNeedAnyHelp(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			cfObj.scrollUtill(driver, 3, direction.DOWN);

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Need any help?\"]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("'Need any help' section is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"contactUsClick\n" +
							"Get help with our 24x7 Support\n" +
							"System\n" +
							"(Mon - Sat | 9:00am - 7:00pm)\n" +
							"CONTACT US\"]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("'24x7 support system' is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"callNowClick\n" +
							"Call us directly for purchase related queries\n" +
							"(Mon - Sat | 9:00am - 7:00pm)\n" +
							"CALL NOW\"]", "xpath", 10);
			if (!result) {
				purchasePackageMsgList.add("'Call us Directly' is not visible");
				return result;
			}
		} catch (Exception e) {
			result = false;
			purchasePackageMsgList.add("verifyNeedAnyHelp_Exception" + e.getMessage());
		}
		return result;
	}

}
