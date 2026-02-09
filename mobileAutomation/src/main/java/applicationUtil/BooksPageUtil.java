package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.BooksPage_OR;
import util.Common_Function;

public class BooksPageUtil {

	PurchasePackageUtil purchasePackageUtilObj;
	UserDetailsLayerUtil userDetailsLayerUtilObj;
	PriceDetailsPageUtil priceDetailsUtilObj;
	BooksPage_OR booksPageObj;
	public ArrayList<String> bookPageMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();

	public BooksPageUtil(AppiumDriver<MobileElement> driver) {
		booksPageObj = new BooksPage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), booksPageObj);
	}
	
	public boolean verifyBooksPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, booksPageObj.getBtnNavigateUp(), 10);
			if(!result) {
				bookPageMsgList.add("Unable to verify back icon in Books page");
				return result;        
			}
			
			result = cfObj.commonWaitForElementToBeVisible(driver, booksPageObj.getListTitle(), 10);
			if(!result) {
				bookPageMsgList.add("Unable to verify package title list in Books page");
				return result;
			}
			
			result = cfObj.commonWaitForElementToBeVisible(driver, booksPageObj.getListPackage().get(0), 10);
			if(!result) {
				bookPageMsgList.add("Unable to verify package list in Books page");
				return result;
			}
			
			result = cfObj.commonWaitForElementToBeVisible(driver, booksPageObj.getSellingPricePackage(), 10);
			if(!result) {
				bookPageMsgList.add("Unable to verify selling price in Books page");
				return result;
			}
			
			result = cfObj.commonWaitForElementToBeVisible(driver, booksPageObj.getBtnFilter(), 10);
			if(!result) {
				bookPageMsgList.add("Unable to verify filter btn in Books page");
				return result;
			}
			
		} catch (Exception e) {
			result = false;
			bookPageMsgList.add("verifyBooksPage Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean clickUnpurchasedPackage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		boolean purchased = true;
		int index=0;
		try {
			List<MobileElement> el = booksPageObj.getListPackage();
			el.get(index).click();
			while(purchased) {
				purchased = !(cfObj.commonWaitForElementToBeVisible(driver, booksPageObj.getBtnBuyNow(), 10));
				
				if(purchased) {
					booksPageObj.getBtnNavigateUp();
					el.get(index+1).click();
					index++;
				}
			}
			
			purchasePackageUtilObj = new PurchasePackageUtil(driver);
			
			result = purchasePackageUtilObj.verifyUnpurchasedPackagePage(driver);
			if(!result) {
				bookPageMsgList.add("Failed to verify UnPurchased Package page.");
			}
			
		} catch (Exception e) {
			result = false;
			bookPageMsgList.add("clickUnpurchasedPackage Exception: " + e.getMessage());
		}
		return result;
	}
}
