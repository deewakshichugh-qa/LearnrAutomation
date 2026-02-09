package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class EBooksPage_OR {

	@AndroidFindBy(id = "ivBack")
	private MobileElement btnNavigateUp;
	
	public MobileElement getBtnNavigateUp() {
		return btnNavigateUp;
	}
	
	@AndroidFindBy(id = "product_title")
	private MobileElement listTitle;
	
	public MobileElement getListTitle() {
		return listTitle;
	}
	
	@AndroidFindBy(xpath = "//*[contains(@content-desc,'packageItem')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'packageItem')]")
	private List<MobileElement> listPackage;
	
	public List<MobileElement> getListPackage() {
		return listPackage;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='SORT & FILTER']")
	private MobileElement btnFilter;
	
	public MobileElement getBtnFilter() {
		return btnFilter;
	}
	
	@AndroidFindBy(id = "tv_title")
	private MobileElement titlePage;
	
	public MobileElement getTitlePage() {
		return titlePage;
	}

	@AndroidFindBy(id = "add_code")
	private MobileElement textAddPromocode;
	
	public MobileElement getTextAddPromocode() {
		return textAddPromocode;
	}

	@AndroidFindBy(id = "final_price")
	private MobileElement finalPrice;
	
	public MobileElement getFinalPrice() {
		return finalPrice;
	}
	
	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"BUY NOW\"]")
	private MobileElement btnBuyNow;
	
	public MobileElement getBtnBuyNow() {
		return btnBuyNow;
	}
	
	@AndroidFindBy(id = "wts_share")
	private MobileElement iconShare;
	
	public MobileElement getIconShare() {
		return iconShare;
	}
}
