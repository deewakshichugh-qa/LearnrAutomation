package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class MyOrdersPage_OR {

	@AndroidFindBy(id = "tv_orderId")
	private List<MobileElement> textOrderId;
	
	public List<MobileElement> getTextOrderId() {
		return textOrderId;
	}
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Order Details\"]")
	@iOSXCUITFindBy(accessibility = "My Orders")
	private MobileElement titlePage;
	
	public MobileElement getTitlePage() {
		return titlePage;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,\"orderDetailCardClick\")]")
	private List<MobileElement> listOrders;

	public List<MobileElement> getListOrders() {
		return listOrders;
	}

	@AndroidFindBy(accessibility = "Help & Support")
	private MobileElement btnHelp;
	
	public MobileElement getBtnHelp() {
		return btnHelp;
	}
	
	@AndroidFindBy(id = "tv_order_status")
	private List<MobileElement> textOrderStatus;
	
	public List<MobileElement> getTextOrderStatus() {
		return textOrderStatus;
	}
	
	@AndroidFindBy(accessibility = "Navigate up")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='My Orders']/preceding-sibling::XCUIElementTypeButton")
	private MobileElement backBtn;

	public MobileElement getBackBtn() {
		return backBtn;
	}
	
	@AndroidFindBy(id = "takeMeToStoreBtn")
	@iOSXCUITFindBy(accessibility = "TAKE ME TO STORE")
	private MobileElement takeMeToStoreBtn;

	public MobileElement getTakeMeToStoreBtn() {
		return takeMeToStoreBtn;
	}
	
	@AndroidFindBy(id = "thumbnail")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='TAKE ME TO STORE']/preceding-sibling::XCUIElementTypeImage")
	private MobileElement myOrderEmptyStateImg;

	public MobileElement getMyOrderEmptyStateImg() {
		return myOrderEmptyStateImg;
	}

	@AndroidFindBy(id = "emptyViewMessageTitle")
	@iOSXCUITFindBy(accessibility = "You have no purchased \n items to show")
	private MobileElement myOrderEmptyStateText;

	public MobileElement getMyOrderEmptyStateText() {
		return myOrderEmptyStateText;
	}
	
}
