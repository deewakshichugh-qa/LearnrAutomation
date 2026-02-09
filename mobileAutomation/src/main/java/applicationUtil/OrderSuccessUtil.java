package applicationUtil;

import java.util.ArrayList;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.HomePage_OR;
import pageObject.OrderSuccessPage_OR;
import util.Common_Function;
import util.Common_Function.direction;

public class OrderSuccessUtil {

	MyContentUtil myContentUtilObj;
	HomePage_OR homePageObj;
	MyOrdersPageUtil myOrdersUtilObj;
	OrderSuccessPage_OR orderSuccessPageObj;
	public Common_Function cfObj = new Common_Function();
	public ArrayList<String> orderSuccessMsgList = new ArrayList<String>();

	public OrderSuccessUtil(AppiumDriver<MobileElement> driver) {
		orderSuccessPageObj = new OrderSuccessPage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), orderSuccessPageObj);
	}

	public boolean verifyOrderSuccessPage(AppiumDriver<MobileElement> driver) {
		boolean result;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@content-desc=\"btn_appbar_back\"]", "xpath", 10);
			if (!result) {
				orderSuccessMsgList.add("Unable to verify back btn in order success page");
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"orderSuccessTickImage\"]", "xpath", 10);
			if (!result) {
				orderSuccessMsgList.add("Unable to verify order green tick mark btn in order success page");
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Order Successful!\"]", "xpath", 10);
			if (!result) {
				orderSuccessMsgList.add("Unable to verify order successful text in order success page");
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Order ID\"]", "xpath", 10);
			if (!result) {
				orderSuccessMsgList.add("Unable to verify order id text in order success page");
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"PACKAGE NAME\"]", "xpath", 10);
			if (!result) {
				orderSuccessMsgList.add("Unable to verify package name text in order success page");
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"VALID UPTO\"]", "xpath", 10);
			if (!result) {
				orderSuccessMsgList.add("Unable to verify valid upto text in order success page");
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"AMOUNT\"]", "xpath", 10);
			if (!result) {
				orderSuccessMsgList.add("Unable to verify amount text in order success page");
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Plan Name\"]", "xpath", 10);
			if (!result) {
				orderSuccessMsgList.add("Unable to verify plan name text in order success page");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, orderSuccessPageObj.getBtnHelp(), 10);
			if (!result) {
				orderSuccessMsgList.add("Unable to verify Help btn in order success page");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, orderSuccessPageObj.getBtnShare(), 10);
			if (!result) {
				orderSuccessMsgList.add("Unable to verify share btn in order success page");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, orderSuccessPageObj.getLinkViewOrderDetails(), 10);
			if (!result) {
				orderSuccessMsgList.add("Unable to verify order view details btn in order success page");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, orderSuccessPageObj.getBtnStartLearning(), 10);
			if (!result) {
				orderSuccessMsgList.add("Unable to verify start learning btn in order success page");
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"learnMore\n" +
							"Learn More\"]", "xpath", 10);
			if (!result) {
				orderSuccessMsgList.add("Unable to verify refer & earn learn more btn in order success page");
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"referNow\n" +
							"REFER THIS PRODUCT\"]", "xpath",
					10);
			if (!result) {
				orderSuccessMsgList.add("Unable to verify REFER THIS PRODUCT btn  in order success page");
			}
			if (!result) {
				return result;
			}
		} catch (Exception e) {
			orderSuccessMsgList.add("verifyOrderSuccessPage Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickViewLink(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			if (cfObj.commonWaitForElementToBeVisible(driver, orderSuccessPageObj.getLinkViewOrderDetails(), 10)) {
				cfObj.commonClick(orderSuccessPageObj.getLinkViewOrderDetails());
			}

			myOrdersUtilObj = new MyOrdersPageUtil(driver);
			result = myOrdersUtilObj.verifyMyOrdersPage(driver);
			if (!result) {
				orderSuccessMsgList.add("Failed to verify MyOrders Page.");
				return result;
			}
		} catch (Exception e) {
			orderSuccessMsgList.add("clickViewLink Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickStartLearningBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		homePageObj = new HomePage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), homePageObj);
		try {

			cfObj.commonClick(orderSuccessPageObj.getBtnStartLearning());

			if (cfObj.commonWaitForElementToBeVisible(driver, homePageObj.getBtnMyContent(), 10)) {
				cfObj.commonClick(homePageObj.getBtnMyContent());
				Thread.sleep(1000);
				cfObj.commonClick(homePageObj.getBtnMyContent());
			} else {
				cfObj.scrollUtill(driver, 1, direction.UP);
				cfObj.commonClick(homePageObj.getBtnMyContent());
				Thread.sleep(1000);
				cfObj.commonClick(homePageObj.getBtnMyContent());
			}

			myContentUtilObj = new MyContentUtil(driver);
			result = myContentUtilObj.verifyMyContentPage(driver);
			if (!result) {
				orderSuccessMsgList.add("Failed to verify MyContent Page.");
			}

		} catch (Exception e) {
			orderSuccessMsgList.add("clickStartLearningBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean handleRateUsPopUpWindow(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			if (cfObj.commonWaitForElementToBeVisible(driver, orderSuccessPageObj.getRateUsPopUpTitle(), 20)) {
				cfObj.commonClick(orderSuccessPageObj.getNotNowBtn());
			}
		} catch (Exception e) {
			result = false;
			orderSuccessMsgList.add("HandleRateUsPopUpWindow_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnBackBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, orderSuccessPageObj.getBtnNavigateUp(), 30);
			if (!result) {
				orderSuccessMsgList.add("Back button is not visible.");
				return result;
			}
			cfObj.commonClick(orderSuccessPageObj.getBtnNavigateUp());
		} catch (Exception e) {
			result = false;
			orderSuccessMsgList.add("BackButton_Exception" + e.getMessage());
		}
		return result;

	}

	public String getStartLearningTitle(AppiumDriver<MobileElement> driver) {
		String productTitle = null;
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, orderSuccessPageObj.getStartLearningTitle(), 10);
			if (!result) {
				orderSuccessMsgList.add("StartLearningTitle is not visible.");
				return productTitle;
			}

			productTitle = orderSuccessPageObj.getStartLearningTitle().getText();
			if (productTitle == null) {
				orderSuccessMsgList.add("Product title is null.");
			}
		} catch (Exception e) {
            orderSuccessMsgList.add("getStartLearningTitle_Exception" + e.getMessage());
		}
		return productTitle;
	}

	public boolean validateProductPrice(AppiumDriver<MobileElement> driver, String productPrice) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, orderSuccessPageObj.getProductDetails(), 10);
			if (!result) {
				orderSuccessMsgList.add("Product details is not visible.");
				return result;
			}

			result = cfObj.commonVerifyValueTextBox(orderSuccessPageObj.getProductDetails(), productPrice);
			if (!result) {
				orderSuccessMsgList.add("Product Price is not visible.");
				return result;
			}

		} catch (Exception e) {
			orderSuccessMsgList.add("verifyOrderSuccessPage Exception:\n" + e.getMessage());
			result = false;
		}
		return result;
	}

}
