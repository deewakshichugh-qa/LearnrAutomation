package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class PurchasePackagePage_OR {

	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"BUY NOW\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'BUY NOW')]")
	private MobileElement btnBuyNow;

	public MobileElement getBtnBuyNow() {
		return btnBuyNow;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,\"Use Coins:\")]")
	private MobileElement coinsCount;

	public MobileElement getCoinsCount() {
		return coinsCount;
	}

	@AndroidFindBy(id = "add_code")
	private MobileElement textAddPromocode;

	public MobileElement getTextAddPromocode() {
		return textAddPromocode;
	}

//	@AndroidFindBy(id = "tv_title")
//	@iOSXCUITFindBy(xpath = "//*[contains(@name,'MONTHS')]/following-sibling::XCUIElementTypeStaticText[1]")
//	private MobileElement titlePage;
//
//	public MobileElement getTitlePage() {
//		return titlePage;
//	}

//	@AndroidFindBy(id = "final_price")
//	private MobileElement packagePrice;
//
//	public MobileElement getPackagePrice() {
//		return packagePrice;
//	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@resource-id,'img_icon')]/following-sibling::android.widget.TextView[contains(@text,'Validity')]")
	private MobileElement packageValidity;

	public MobileElement getPackageValidity() {
		return packageValidity;
	}

	@AndroidFindBy(id = "wts_share")
	@iOSXCUITFindBy(accessibility = "storeProductDetailShareItem")
	private MobileElement packageShareBtn;

	public MobileElement getPackageShareBtn() {
		return packageShareBtn;
	}

	@AndroidFindBy(id = "packageSpinnerTV")
	private List<MobileElement> packageSpinnerTextValue;

	public List<MobileElement> getPackageSpinnerTextValue() {
		return packageSpinnerTextValue;
	}

	@AndroidFindBy(id = "ddSpinnerTV")
	private List<MobileElement> validityDropDownElementList;

	public List<MobileElement> getValidityDropDownElementList() {
		return validityDropDownElementList;
	}

	@AndroidFindBy(id = "tv_value")
	private MobileElement discountAmount;

	public MobileElement getDiscountAmount() {
		return discountAmount;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"couponTextClick\"]")
	private MobileElement discountRemoveBtn;

	public MobileElement getDiscountRemoveBtn() {
		return discountRemoveBtn;
	}

	@AndroidFindBy(id = "final_price")
	private MobileElement finalPackagePrice;

	public MobileElement getFinalPackagePrice() {
		return finalPackagePrice;
	}

	@AndroidFindBy(id = "mrp_price")
	private MobileElement mrpPrice;

	public MobileElement getMrpPrice() {
		return mrpPrice;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"MORE OFFERS\"]")
	private MobileElement moreOffersBtnOnBottom;

	public MobileElement moreOffersBtnOnBottom() {
		return moreOffersBtnOnBottom;
	}

	@AndroidFindBy(id = "discount_offer")
	private MobileElement discountOffer;

	public MobileElement getDiscountOffer() {
		return discountOffer;
	}

	@AndroidFindBy(id = "saved_amount")
	private MobileElement savedAmount;

	public MobileElement getSavedAmount() {
		return savedAmount;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"couponViewClick\n" +
			"Coupon available\"]")
	private MobileElement offerNumberFromBottom;

	public MobileElement getOfferNumberFromBottom() {
		return offerNumberFromBottom;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'coupon applied')]")
	private MobileElement getOfferNumberFromTop;

	public MobileElement getOfferNumberFromTop() {
		return getOfferNumberFromTop;
	}

	@AndroidFindBy(id = "checkBox")
	private MobileElement coinCheckBox;

	public MobileElement getCoinCheckBox() {
		return coinCheckBox;
	}

	@AndroidFindBy(id = "certificate_tag")
	private MobileElement certificateTag;

	public MobileElement getCertificateTag() {
		return certificateTag;
	}

	@AndroidFindBy(id = "certificate_preview")
	private MobileElement certificatePreviewBtn;

	public MobileElement getCertificatePreviewBtn() {
		return certificatePreviewBtn;
	}

	@AndroidFindBy(id = "category")
	private List<MobileElement> categoryList;

	public List<MobileElement> getCategoryList() {
		return categoryList;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'Live Classes')]")
	private MobileElement liveClassTab;

	public MobileElement getLiveClassTab() {
		return liveClassTab;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'E-books')]")
	private MobileElement eBookTab;

	public MobileElement geteBookTab() {
		return eBookTab;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'Test Series')]")
	private MobileElement testSeriesTab;

	public MobileElement getTestSeriesTab() {
		return testSeriesTab;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'Video Courses')]")
	private MobileElement videoCourseTab;

	public MobileElement getVideoCourseTab() {
		return videoCourseTab;
	}

	@AndroidFindBy(id = "subject_title")
	private List<MobileElement> subjectTitle;

	public List<MobileElement> getSubjectTitle() {
		return subjectTitle;
	}

	@AndroidFindBy(xpath = "//android.widget.CheckBox")
	private MobileElement coinsCheckbox;

	public MobileElement coinsCheckbox() {
		return coinsCheckbox;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Coupon available\")]")
	private MobileElement couponAvailableTab;

	public MobileElement getCouponAvailableTab() {
		return couponAvailableTab;
	}

	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id=\\\"com.adda247.app:id/edittext_coupon\\\"]")
	private MobileElement inputCouponBox;

	public MobileElement getInputCouponBox() {
		return inputCouponBox;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.adda247.app:id/textView_couponCode']")
	private List<MobileElement> nameOfCoupon;

	public List<MobileElement> getNameOfCoupon() {
		return nameOfCoupon;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.adda247.app:id/apply_coupon\"]")
	private List<MobileElement> applyButton;

	public List<MobileElement> getApplyButton() {
		return applyButton;
	}

	@AndroidFindBy(xpath = "(//android.view.ViewGroup[@resource-id=\"com.adda247.app:id/container\"])")
	private List<MobileElement> productOfferingContent;

	public List<MobileElement> getProductOfferingContent() {
		return productOfferingContent;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,\"examCoveredItemClick\")]")
	private List<MobileElement> listOfExamsCovered;

	public List<MobileElement> getListOfExamsCovered() {
		return listOfExamsCovered;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Overview')]")
	private MobileElement overViewTab;

	public MobileElement getOverViewTab() {
		return overViewTab;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'FAQs')]")
	private MobileElement faqTab;

	public MobileElement getFAQTab() {
		return faqTab;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"expandAllClick\n" +
			"EXPAND ALL\"]")
	private MobileElement expandButtonInFAQsection;

	public MobileElement getExpandButtonInFAQsection() {
		return expandButtonInFAQsection;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"collapseAllClick\n" +
			"COLLAPSE ALL\"]")
	private MobileElement collapseAllInFAQsection;

	public MobileElement getCollapseAllInFAQsection() {
		return collapseAllInFAQsection;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"onExpandPlanSelectionClick\")]")
	private MobileElement validitySelectedAtBottom;

	public MobileElement validitySelectedAtBottom() {
		return validitySelectedAtBottom;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"arrowDownClickOnOurPlans\")]")
	private MobileElement arrowDownClickOnOurPlans;

	public MobileElement arrowDownClickOnOurPlans() {
		return arrowDownClickOnOurPlans;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"differentTypeValidityPlanCard\")]")
	private List<MobileElement> differentTypeValidityPlanCard;

	public List<MobileElement> differentTypeValidityPlanCard() {
		return differentTypeValidityPlanCard;
	}
}
