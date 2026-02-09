package pageObject;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class OrderSuccessPage_OR {

	@AndroidFindBy(id = "tv_order_id_text")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[contains(@name,'Order ID :')]")
	private MobileElement textOrderID;

	public MobileElement getTextOrderID() {
		return textOrderID;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"VIEW ORDER DETAILS\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'purchaseSuccessViewOrderDetails')]")
	private MobileElement linkViewOrderDetails;

	public MobileElement getLinkViewOrderDetails() {
		return linkViewOrderDetails;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]")
	private MobileElement btnNavigateUp;

	public MobileElement getBtnNavigateUp() {
		return btnNavigateUp;
	}

	@AndroidFindBy(id = "view_face_issue")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[contains(@name,'purchaseSuccessContactSupportSection')]")
	private MobileElement sectionContactSupport;

	public MobileElement getSectionContactSupport() {
		return sectionContactSupport;
	}

	@AndroidFindBy(id = "view_share")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[contains(@name,'purchaseSuccessRecommendFriendsSection')]")
	private MobileElement sectionRecommendToYourFriend;

	public MobileElement getSectionRecommendToYourFriend() {
		return sectionRecommendToYourFriend;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Start Learning\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'purchaseSuccessStartLearningButton')]")
	private MobileElement sectionStartLearning;

	public MobileElement getSectionStartLearning() {
		return sectionStartLearning;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Start Learning\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'purchaseSuccessStartLearningButton')]")
	private MobileElement btnStartLearning;

	public MobileElement getBtnStartLearning() {
		return btnStartLearning;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"shareClick\n" +
			"Liked it ?\n" +
			"Recommend to your Friends\"]")
	private MobileElement btnShare;

	public MobileElement getBtnShare() {
		return btnShare;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"supportClick\n" +
			"Facing an Issue?\n" +
			"Contact Support\"]")
	private MobileElement btnHelp;

	public MobileElement getBtnHelp() {
		return btnHelp;
	}

	@AndroidFindBy(xpath = "//*[@text='Rate your purchase experience']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Enjoying Adda247?')]")
	private MobileElement rateUsPopUpTitle;

	public MobileElement getRateUsPopUpTitle() {
		return rateUsPopUpTitle;
	}

	@AndroidFindBy(id = "not_now")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Not Now']")
	private MobileElement notNowBtn;

	public MobileElement getNotNowBtn() {
		return notNowBtn;
	}

	@AndroidFindAll({ @AndroidBy(id = "book_title"), @AndroidBy(id = "com.adda247.app:id/startLearningTitle") })
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Access purchased content in My Content  later OR']/following-sibling::XCUIElementTypeStaticText[1]")
	private MobileElement startLearningTitle;

	public MobileElement getStartLearningTitle() {
		return startLearningTitle;
	}

	@AndroidFindBy(id = "tv_contain")
	private MobileElement productDetails;

	public MobileElement getProductDetails() {
		return productDetails;
	}

}
