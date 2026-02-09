package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class StorePage_OR {

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"browseByProductItemButton\n" +
			"Mock Test\"]")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'Mock Tests')]")
	private MobileElement iconTestSeries;
	
	public MobileElement getIconTestSeries() {
		return iconTestSeries;
	}
	
	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"browseByProductItemButton\n" +
			"Live Classes\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[contains(@name,'Live Classes')]")
	private MobileElement iconLiveClasses;
	
	public MobileElement getIconLiveClasses() {
		return iconLiveClasses;
	}
	
	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"browseByProductItemButton\n" +
			"Video Courses\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[contains(@name,'Video Courses')]")
	private MobileElement iconVideoCourse;
	
	public MobileElement getIconVideoCourse() {
		return iconVideoCourse;
	}
	
	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"browseByProductItemButton\n" +
			"E-Books\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[contains(@name,'E-Books')]")
	private MobileElement iconEBooks;
	
	public MobileElement getIconEbooks() {
		return iconEBooks;
	}
	
	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"browseByProductItemButton\n" +
			"Printed Books\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[contains(@name,'Printed Books')]")
	private MobileElement iconBooks;
	
	public MobileElement getBooks() {
		return iconBooks;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"browseByProductItemButton\n" +
			"3D Learning\"]")
	private MobileElement icon3dLearning;

	public MobileElement getIcon3dLearning() {
		return icon3dLearning;
	}
	
	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Banking']/preceding-sibling::XCUIElementTypeButton")
	private MobileElement btnNavigationDrawer;
	
	public MobileElement getBtnNavigationDrawer() {
		return btnNavigationDrawer;
	}
	
	@AndroidFindBy(id = "com.adda247.app:id/changeExamContainer")
	private MobileElement btnChangeExamType;
	
	public MobileElement getBtnChangeExamType() {
		return btnChangeExamType;
	}

	@AndroidFindBy(id = "search_item")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Banking']/following-sibling::XCUIElementTypeOther")
	private MobileElement iconSearch;
	
	public MobileElement getIconSearch() {
		return iconSearch;
	}
	
//-------------------------------------Search page under store page-------------------------------------------------------------

	@AndroidFindBy(xpath = "//android.widget.EditText")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[contains(@name,'storeSearchField')]")
	private MobileElement searchField;
	
	public MobileElement getSearchField() {
		return searchField;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Trending\"]")
	@iOSXCUITFindBy(accessibility = "Trending")
	private MobileElement textTrendingSearches;
	
	public MobileElement getTextTrendingSearches() {
		return textTrendingSearches;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Courses bought by most\"]")
	@iOSXCUITFindBy(accessibility = "Courses Bought By Most")
	private MobileElement textCoursesBoughtByMost;
	
	public MobileElement getTextCoursesBoughtByMost() {
		return textCoursesBoughtByMost;
	}
	
	@AndroidFindBy(id = "product_view")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Courses Bought By Most']/following-sibling::XCUIElementTypeOther/descendant::XCUIElementTypeStaticText")
	private MobileElement optionsCoursesBoughtByMost;
	
	public MobileElement getOptionsCoursesBoughtByMost() {
		return optionsCoursesBoughtByMost;
	}
	
	@AndroidFindBy(id = "search_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'storeSearchTrendingItem')]")
	private MobileElement optionsTrendingSearches;
	
	public MobileElement getOptionsTrendingSearches() {
		return optionsTrendingSearches;
	}
	
	@AndroidFindBy(xpath = "//*[contains(@content-desc,\"packageItem\")]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='rightIconButton']/following-sibling::XCUIElementTypeOther/descendant::XCUIElementTypeStaticText")
	private List<MobileElement> listSearchResutls;
	
	public List<MobileElement> getListSearchResutls() {
		return listSearchResutls;
	}
	
	@AndroidFindBy(id = "product_discount_price")
	private List<MobileElement> productDiscountPrice;

	public List<MobileElement> getProductDiscountPrice() {
		return productDiscountPrice;
	}
	
	@AndroidFindBy(id = "product_mrp_price")
	private List<MobileElement> productMrpPrice;

	public List<MobileElement> getProductMrpPrice() {
		return productMrpPrice;
	}
	
	@AndroidFindBy(id = "offers2")
	private List<MobileElement> offerPercentage;

	public List<MobileElement> getOfferPercentage() {
		return offerPercentage;
	}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'tv_recommended') and contains(@text,'Certificate')]")
	private List<MobileElement> certificateCourseTag;

	public List<MobileElement> getCertificateCourseTag() {
		return certificateCourseTag;
	}	
	
}
