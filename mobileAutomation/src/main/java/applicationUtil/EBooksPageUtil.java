package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.EBooksPage_OR;
import util.Common_Function;

public class EBooksPageUtil {

	PurchasePackageUtil purchasePackageUtilObj;
	UserDetailsLayerUtil userDetailsLayerUtilObj;
	PriceDetailsPageUtil priceDetailsUtilObj;
	EBooksPage_OR eBookPageObj;
	public Common_Function cfObj = new Common_Function();
	public ArrayList<String> eBookMsgList = new ArrayList<String>();


	public EBooksPageUtil(AppiumDriver<MobileElement> driver) {
		eBookPageObj = new EBooksPage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), eBookPageObj);
	}

	public boolean verifyEBooksPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, eBookPageObj.getBtnNavigateUp(), 10);
			if(!result) {
				eBookMsgList.add("Unable to verify back icon in EBooks page"); 
				//				return result;        
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, eBookPageObj.getListTitle(), 10);
			if(!result) {
				eBookMsgList.add("Unable to verify package title list in EBooks page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, eBookPageObj.getListPackage().get(0), 10);
			if(!result) {
				eBookMsgList.add("Unable to verify package list in EBooks page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, eBookPageObj.getBtnFilter(), 10);
			if(!result) {
				eBookMsgList.add("Unable to verify filter btn in EBooks page");
				return result;
			}

		} catch (Exception e) {
			result = false;
			eBookMsgList.add("verifyEBooksPage Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickUnpurchasedPackage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		boolean purchased = true;
		int index=0;
		try {
			List<MobileElement> el = eBookPageObj.getListPackage();
			el.get(index).click();
			while(purchased) {
				purchased = !(cfObj.commonWaitForElementToBeVisible(driver, eBookPageObj.getBtnBuyNow(), 10));

				if(purchased) {
					eBookPageObj.getBtnNavigateUp();
					el.get(index+1).click();
					index++;
				}
			}

			purchasePackageUtilObj = new PurchasePackageUtil(driver);

			result = purchasePackageUtilObj.verifyUnpurchasedPackagePage(driver);
			if(!result) {
				eBookMsgList.add("Failed to verify UnPurchased Package page.");
			}

		} catch (Exception e) {
			result = false;
			eBookMsgList.add("clickUnpurchasedPackage Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickEBookFromList(AppiumDriver<MobileElement> driver, String strTitleEBook) {
		boolean result = true;
		try {

			MobileElement el = cfObj.scrollIntoText(driver, strTitleEBook);

			result = cfObj.commonWaitForElementToBeVisible(driver, el, 10);
			if(result) {
				el.click();
			}else {
				eBookMsgList.add("Unable to find message list");
				return result;
			}

		} catch (Exception e) {
			result = false;
			eBookMsgList.add("clickEBookFromList Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickEBookRandomlyFromList(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, eBookPageObj.getListPackage().get(0), 20);
			if(!result) {
				eBookMsgList.add("EBook package list is not visible.");
				return result;
			}
			int listCount=eBookPageObj.getListPackage().size()-2;
			int randomPackage=Common_Function.RandomNumber(0, listCount);

			cfObj.commonClick(eBookPageObj.getListPackage().get(randomPackage));

		} catch (Exception e) {
			result = false;
			eBookMsgList.add("clickEBookRandomlyFromList_Exception: " + e.getMessage());
		}
		return result;
	}
}
