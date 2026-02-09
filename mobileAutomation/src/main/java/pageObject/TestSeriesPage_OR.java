package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class TestSeriesPage_OR {

	@AndroidFindBy(id = "com.adda247.app:id/ivBack")
	private MobileElement btnNavigateUp;
	
	public MobileElement getBtnNavigateUp() {
		return btnNavigateUp;
	}
	
	@AndroidFindBy(id = "com.adda247.app:id/product_title")
	private MobileElement listTitle;
	
	public MobileElement getListTitle() {
		return listTitle;
	}
	
	@AndroidFindBy(id = "com.adda247.app:id/product_view")
	private List<MobileElement> listPackage;
	
	public List<MobileElement> getListPackage() {
		return listPackage;
	}
	
	@AndroidFindBy(id = "com.adda247.app:id/product_discount_price")
	private MobileElement sellingPricePackage;
	
	public MobileElement getSellingPricePackage() {
		return sellingPricePackage;
	}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='SORT & FILTER']")
	private MobileElement btnFilter;
	
	public MobileElement getBtnFilter() {
		return btnFilter;
	}
	
	@AndroidFindBy(id = "com.adda247.app:id/tv_title")
	private MobileElement titlePage;
	
	public MobileElement getTitlePage() {
		return titlePage;
	}

	@AndroidFindBy(id = "com.adda247.app:id/add_code")
	private MobileElement textAddPromocode;
	
	public MobileElement getTextAddPromocode() {
		return textAddPromocode;
	}

	@AndroidFindBy(id = "com.adda247.app:id/final_price")
	private MobileElement finalPrice;
	
	public MobileElement getFinalPrice() {
		return finalPrice;
	}
	
	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"BUY NOW\"]")
	private MobileElement btnBuyNow;
	
	public MobileElement getBtnBuyNow() {
		return btnBuyNow;
	}
	
	@AndroidFindBy(id = "com.adda247.app:id/wts_share")
	private MobileElement iconShare;
	
	public MobileElement getIconShare() {
		return iconShare;
	}
}
