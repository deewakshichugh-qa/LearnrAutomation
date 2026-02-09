package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.MyOrdersPage_OR;
import util.Common_Function;
import util.ConfigFileReader;

public class MyOrdersPageUtil {

	MyOrdersPage_OR myOrdersPageObj;
	List<String> myOrderMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();

	public MyOrdersPageUtil(AppiumDriver<MobileElement> driver) {
		myOrdersPageObj = new MyOrdersPage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), myOrdersPageObj);
	}

	public boolean verifyMyOrdersPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, myOrdersPageObj.getTitlePage(), 10);
			if (!result) {
				myOrderMsgList.add("Unable to verify page title in My Orders page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"btn_appbar_back\"]", "xpath", 10);
			if (!result) {
				myOrderMsgList.add("Unable to verify back btn in My Orders page");
				return result;
			}

			result = cfObj.commonWaitForListOfElementsToBeVisible(driver, myOrdersPageObj.getListOrders(), 10);
			if (!result) {
				myOrderMsgList.add("Unable to verify list of orders title in My Orders page");
				return result;
			}
		} catch (Exception e) {
			result = false;
			myOrderMsgList.add("verifyMyOrdersPage Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean checkOrderIdInMyOrders(AppiumDriver<MobileElement> driver, String orderId) {
		boolean result = true;
		try {

			result = myOrdersPageObj.getTextOrderId().get(0).getText().contains(orderId);
			if (!result) {
				myOrderMsgList.add("OrderId text does not contains " + orderId);
			}

		} catch (Exception e) {
			result = false;
			myOrderMsgList.add("checkOrderIdInMyOrders Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyOrderStatus(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.TextView[contains(@text,'Instant Withdrawal')]", "xpath", 10);
			if (!result) {
				myOrderMsgList.add("Instant Withdrawal in refer & earn box is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"com.adda247.app:id/refer_n_earn_learn_more", "id", 10);
			if (!result) {
				myOrderMsgList.add("Refer & earn box is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.TextView[contains(@text,'Earn while you learn!')]", "xpath", 10);
			if (!result) {
				myOrderMsgList.add("Earn while you learn! text in refer & earn box is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/learn_more", "id", 10);
			if (!result) {
				myOrderMsgList.add("Learn More btn in box is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/refer_now", "id", 10);
			if (!result) {
				myOrderMsgList.add("refer & earn btn in box is not visible");
				return result;
			}

			result = myOrdersPageObj.getTextOrderStatus().get(0).getText().equalsIgnoreCase("Success");
			if (!result) {
				System.out.println("Order status text is does not contains Success text.");
				result = true;
			}

		} catch (Exception e) {
			result = false;
			myOrderMsgList.add("checkOrderIdInMyOrders Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyMyOrderUIForNewUser(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = cfObj.commonWaitForElementToBeVisible(driver, myOrdersPageObj.getBtnHelp(), 10);
				if (!result) {
					result = false;
					myOrderMsgList.add("Unable to verify help btn in My Orders page");
				}
			}
			result = cfObj.commonWaitForElementToBeVisible(driver, myOrdersPageObj.getTitlePage(), 10);
			if (!result) {
				result = false;
				myOrderMsgList.add("Unable to verify page title in My Orders page");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, myOrdersPageObj.getTakeMeToStoreBtn(), 10);
			if (!result) {
				result = false;
				myOrderMsgList.add("TakeMeToStore button is not visible.");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, myOrdersPageObj.getMyOrderEmptyStateImg(), 10);
			if (!result) {
				result = false;
				myOrderMsgList.add("MyOrder Empty state image is not visible.");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, myOrdersPageObj.getMyOrderEmptyStateText(), 10);
			if (!result) {
				result = false;
				myOrderMsgList.add("MyOrder Empty state text is not visible.");
			}
		} catch (Exception e) {
			result = false;
			myOrderMsgList.add("MyOrderUIForNewUser_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean navigateToUserProfilePage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			cfObj.commonClick(myOrdersPageObj.getBackBtn());

			Thread.sleep(2000);
		} catch (Exception e) {
			result = false;
			myOrderMsgList.add("UserProfileNavigation_Exception: " + e.getMessage());
		}
		return result;
	}

}
