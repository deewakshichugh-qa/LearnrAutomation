package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.TestSeriesPage_OR;
import util.Common_Function;

public class TestSeriesPageUtil {

	PurchasePackageUtil purchasePackageUtilObj;
	UserDetailsLayerUtil userDetailsLayerUtilObj;
	PriceDetailsPageUtil priceDetailsUtilObj;
	TestSeriesPage_OR testSeriesPageObj;
	public ArrayList<String> testSeriesPageMsgList = new ArrayList<>();
	public Common_Function cfObj = new Common_Function();

	public TestSeriesPageUtil(AppiumDriver<MobileElement> driver) {
		testSeriesPageObj = new TestSeriesPage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), testSeriesPageObj);
	}

	public boolean verifyTestSeriesPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, testSeriesPageObj.getBtnNavigateUp(), 10);
			if(!result) {
				testSeriesPageMsgList.add("Unable to verify back icon in Test Series page");
				//				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testSeriesPageObj.getListTitle(), 10);
			if(!result) {
				testSeriesPageMsgList.add("Unable to verify package title list in Test Series page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testSeriesPageObj.getListPackage().get(0), 10);
			if(!result) {
				testSeriesPageMsgList.add("Unable to verify package list in Test Series page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testSeriesPageObj.getSellingPricePackage(), 10);
			if(!result) {
				testSeriesPageMsgList.add("Unable to verify selling price in Test Series page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testSeriesPageObj.getBtnFilter(), 10);
			if(!result) {
				testSeriesPageMsgList.add("Unable to verify filter btn in Test Series page");
				return result;
			}

		} catch (Exception e) {
			result = false;
			testSeriesPageMsgList.add("verifyTestSeriesPage Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickUnpurchasedPackage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		boolean purchased = true;
		int index=0;
		try {
			List<MobileElement> el = testSeriesPageObj.getListPackage();
			el.get(index).click();
			while(purchased) {
				purchased = !(cfObj.commonWaitForElementToBeVisible(driver, testSeriesPageObj.getBtnBuyNow(), 10));

				if(purchased) {
					testSeriesPageObj.getBtnNavigateUp();
					el.get(index+1).click();
					index++;
				}
			}

			purchasePackageUtilObj = new PurchasePackageUtil(driver);

			result = purchasePackageUtilObj.verifyUnpurchasedPackagePage(driver);
			if(!result) {
				testSeriesPageMsgList.add("Failed to verify Unpurchased Package page.");
			}

		} catch (Exception e) {
			result = false;
			testSeriesPageMsgList.add("clickUnpurchasedPackage Exception: " + e.getMessage());
		}
		return result;
	}

}
