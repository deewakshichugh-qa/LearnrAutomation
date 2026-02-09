package pageObject;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class PriceDetailPage_OR {

	@AndroidFindBy(xpath = "//android.widget.EditText")
	private MobileElement textFieldCouponCode;
	
	public MobileElement getTextFieldCouponCode() {
		return textFieldCouponCode;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"applyClick\n" +
			"APPLY\"]")
	private MobileElement btnApplySelfCoupon;
	
	public MobileElement getBtnApplySelfCoupon() {
		return btnApplySelfCoupon;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Price Details\"]")
	private MobileElement priceDetailsTitle;

	public MobileElement getPriceDetailsTitle() {
		return priceDetailsTitle;
	}
	
	@AndroidFindBy(id = "subtotal")
	private MobileElement subTotalText;

	public MobileElement getSubTotalText() {
		return subTotalText;
	}
	
	@AndroidFindBy(id = "subtotal_amount")
	private MobileElement subTotalAmount;

	public MobileElement getSubTotalAmount() {
		return subTotalAmount;
	}
	
	@AndroidFindBy(id = "coins_header")
	private MobileElement coinHeader;

	public MobileElement getCoinHeader() {
		return coinHeader;
	}
	
	@AndroidFindBy(id = "coins_discount")
	private MobileElement coinDiscountAmount;

	public MobileElement getCoinDiscountAmount() {
		return coinDiscountAmount;
	}
	
	@AndroidFindBy(id = "amount_payable")
	private MobileElement amountPayableHeader;

	public MobileElement getAmountPayableHeader() {
		return amountPayableHeader;
	}
	
	@AndroidFindBy(id = "amount_payable_amount")
	private MobileElement amountPayableAmount;

	public MobileElement getAmountPayableAmount() {
		return amountPayableAmount;
	}
	
	@AndroidFindBy(id = "usable_coins_count")
	private MobileElement usableCoinsCountText;

	public MobileElement getUsableCoinsCountText() {
		return usableCoinsCountText;
	}
	
	@AndroidFindBy(id = "checkBox")
	private MobileElement coinCheckBox;

	public MobileElement getCoinCheckBox() {
		return coinCheckBox;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"backClick\"]")
	private MobileElement backBtn;

	public MobileElement getBackBtn() {
		return backBtn;
	}
	
	@AndroidFindBy(id = "apply_coupon")
	private MobileElement applyCouponBtn;

	public MobileElement getApplyCouponBtn() {
		return applyCouponBtn;
	}
	
}
