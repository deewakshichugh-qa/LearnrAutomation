package applicationUtil;

import java.util.ArrayList;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.PriceDetailPage_OR;
import util.Common_Function;
import util.Common_Function.direction;

public class PriceDetailsPageUtil {

	PurchasePackageUtil purchasePackageUtilObj;
	OrderSuccessUtil orderSuccessUtilObj;
	EBooksPageUtil eBooksUtilObj;
	PriceDetailPage_OR priceDetailPageObj;
	public ArrayList<String> priceDetailsMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();

	public PriceDetailsPageUtil(AppiumDriver<MobileElement> driver) {
		priceDetailPageObj = new PriceDetailPage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), priceDetailPageObj);
	}

	public boolean verifyPriceDetailPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, priceDetailPageObj.getBtnApplySelfCoupon(), 10);
			if(!result) {
				priceDetailsMsgList.add("Unable to verify self apply coupon btn in price detail page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Subtotal\"]", "xpath", 10);
			if(!result) {
				priceDetailsMsgList.add("Subtotal text in price detail page is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Amount Payable\"]", "xpath", 10);
			if(!result) {
				priceDetailsMsgList.add("Amount payable text in price detail page is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Available Offers\"]", "xpath", 10);
			if(!result) {
				priceDetailsMsgList.add("Available offers text in price detail page is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, priceDetailPageObj.getTextFieldCouponCode(), 10);
			if(!result) {
				priceDetailsMsgList.add("Unable to verify coupon text field in price detail page");
				return result;
			}
		} catch (Exception e) {
			result = false;
			priceDetailsMsgList.add("verifyPriceDetailPage Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean enterCouponCode(String couponCode) {
		boolean result = true;
		try {

			result = cfObj.commonSetTextTextBox(priceDetailPageObj.getTextFieldCouponCode(), couponCode);
			if(!result) {
				priceDetailsMsgList.add("Failed to enter Coupon code.");
			}

		} catch (Exception e) {
			result = false;
			priceDetailsMsgList.add("enterCouponCode Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickApplySelfCouponBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			cfObj.commonClick(priceDetailPageObj.getBtnApplySelfCoupon());

			Thread.sleep(1500);
			cfObj.scrollUtill(driver, 1, direction.UP);

			purchasePackageUtilObj = new PurchasePackageUtil(driver);

			result = purchasePackageUtilObj.verifyPackageWithCouponAppliedPage(driver);
			if(!result) {
				priceDetailsMsgList.add("Failed to verify PackageWithCouponApplied Page.");
			}
		} catch (Exception e) {
			result = false;
			priceDetailsMsgList.add("enterCouponCode Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyPriceDetailPageInCoinCheckBoxEnableState(AppiumDriver<MobileElement> driver,String savedAmount, String discountAmount) {
		boolean result = true;
		try {
			Thread.sleep(3000);
			result = cfObj.commonWaitForElementToBeVisible(driver, priceDetailPageObj.getPriceDetailsTitle(), 10);
			if(!result) {
				priceDetailsMsgList.add("PriceDetailsTitle is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, priceDetailPageObj.getSubTotalAmount(), 10);
			if(!result) {
				priceDetailsMsgList.add("SubTotalAmount is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, priceDetailPageObj.getAmountPayableAmount(), 10);
			if(!result) {
				priceDetailsMsgList.add("AmountPayableAmount is not visible.");
				return result;
			}

			double payableAmount=Double.parseDouble(priceDetailPageObj.getAmountPayableAmount().getText().split(" ")[1]);
			System.out.println("PayAble Amount"+payableAmount);

			result =payableAmount==Double.parseDouble(discountAmount);
			if(!result) {
				priceDetailsMsgList.add("DiscountAmount is not matching.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, priceDetailPageObj.getCoinDiscountAmount(), 10);
			if(!result) {
				priceDetailsMsgList.add("CoinDiscountAmount is not visible.");
				return result;
			}

			double coinDiscountAmount=Double.parseDouble(priceDetailPageObj.getCoinDiscountAmount().getText().split(" ")[2]);
			System.out.println("CoinDiscount Amount"+coinDiscountAmount);

			result =coinDiscountAmount==Double.parseDouble(savedAmount);
			if(!result) {
				priceDetailsMsgList.add("SavedAmount is not matching.");
				return result;
			}

			result=validateUseCoinCheckBoxInPriceDetailsPage(driver);
			if(!result) {
				priceDetailsMsgList.add("Not able to validate UseCoinCheckBox.");
				return result;
			}

			result=closePriceDetailsPage(driver);
			if(!result) {
				priceDetailsMsgList.add("Not able to close Price details page.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			priceDetailsMsgList.add("verifyPriceDetailPageInCoinCheckBoxEnableState_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean validateUseCoinCheckBoxInPriceDetailsPage(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, priceDetailPageObj.getCoinCheckBox(), 10);
			if (!result) {
				priceDetailsMsgList.add("CoinCheckBox is not visible.");
				return result;
			}

			result=priceDetailPageObj.getCoinCheckBox().getAttribute("checked").equalsIgnoreCase("true");
			if (!result) {
				priceDetailsMsgList.add("CoinCheckBox is already selected.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, priceDetailPageObj.getCoinHeader(), 10);
			if (!result) {
				priceDetailsMsgList.add("CoinHeader is not visible.");
				return result;
			}

			cfObj.commonClick(priceDetailPageObj.getCoinCheckBox());

			result=priceDetailPageObj.getCoinCheckBox().getAttribute("checked").equalsIgnoreCase("false");
			if (!result) {
				priceDetailsMsgList.add("Not able to select coin checkBox.");
				return result;
			}
			Thread.sleep(2000);
			result = !cfObj.commonWaitForElementToBeVisible(driver, priceDetailPageObj.getCoinHeader(), 10);
			if (!result) {
				priceDetailsMsgList.add("CoinHeader is visible.");
				return result;
			}

			cfObj.commonClick(priceDetailPageObj.getCoinCheckBox());

			result=priceDetailPageObj.getCoinCheckBox().getAttribute("checked").equalsIgnoreCase("true");
			if (!result) {
				priceDetailsMsgList.add("Not able to select coin checkBox.");
				return result;
			}
			Thread.sleep(2000);
			result = cfObj.commonWaitForElementToBeVisible(driver, priceDetailPageObj.getCoinHeader(), 10);
			if (!result) {
				priceDetailsMsgList.add("CoinHeader is not visible.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			priceDetailsMsgList.add("validateUseCoinCheckBoxInPriceDetailsPage_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean closePriceDetailsPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, priceDetailPageObj.getBackBtn(), 10);
			if (!result) {
				priceDetailsMsgList.add("Back button is not visible.");
				return result;
			}
			cfObj.commonClick(priceDetailPageObj.getBackBtn());

		} catch (Exception e) {
			result = false;
			priceDetailsMsgList.add("closePriceDetailsPage_Exception" + e.getMessage());
		}
		return result;
	}
	
	public boolean validatePriceDetailsScreen(AppiumDriver<MobileElement> driver,String referalCode) {
		boolean result=true;
		try {
			
			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Price Details\"]", "xpath", 30);
			if (!result) {
				priceDetailsMsgList.add("Price Details page title is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Subtotal\"]", "xpath", 30);
			if (!result) {
				priceDetailsMsgList.add("SubTotal amount text is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Amount Payable\"]", "xpath", 30);
			if (!result) {
				priceDetailsMsgList.add("Payable amount text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, priceDetailPageObj.getBtnApplySelfCoupon(), 10);
			if(!result) {
				priceDetailsMsgList.add("Unable to verify self apply coupon btn in price detail page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, priceDetailPageObj.getTextFieldCouponCode(), 10); 
			if(!result) {
				priceDetailsMsgList.add("Unable to verify coupon text field in price detail page");
				return result;
			}

		} catch (Exception e) {
			result=false;
			priceDetailsMsgList.add("validatePriceDetailsScreen_Exception"+e.getMessage());	
		}
		return result;	
	}

}