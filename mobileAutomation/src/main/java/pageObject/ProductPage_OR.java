package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class ProductPage_OR {

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"backClick\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='storeSearcIconButton']/preceding-sibling::XCUIElementTypeButton")
	private MobileElement btnNavigateUp;
	
	public MobileElement getBtnNavigateUp() {
		return btnNavigateUp;
	}
	
	@AndroidFindBy(xpath = "//*[contains(@content-desc,'packageItem')]")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'packageItem')]")
	private List<MobileElement> listPackage;
	
	public List<MobileElement> getListPackage() {
		return listPackage;
	}
	
	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"filterByClick\n" +
			"Filter\n" +
			"No Filter\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Filter')]")
	private MobileElement btnFilter;
	
	public MobileElement getBtnFilter() {
		return btnFilter;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"sortClick\n" +
			"Sort By\"]")
	private MobileElement btnSortBy;

	public MobileElement getBtnSortBy() {
		return btnSortBy;
	}
}
