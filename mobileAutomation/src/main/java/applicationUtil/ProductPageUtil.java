package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.ProductPage_OR;
import util.Common_Function;
import util.Common_Function.key;
import util.ConfigFileReader;

public class ProductPageUtil {

    PurchasePackageUtil purchasePackageUtilObj;
    ProductPage_OR productPageObj;
    UserDetailsLayerUtil userDetailsLayerUtilObj;
    public Common_Function cfObj = new Common_Function();
    public ArrayList<String> productPageMsgList = new ArrayList<>();

    public ProductPageUtil(AppiumDriver<MobileElement> driver) {
        productPageObj = new ProductPage_OR();
        PageFactory.initElements(new AppiumFieldDecorator(driver), productPageObj);
    }

    public boolean verifyProductPage(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, productPageObj.getBtnNavigateUp(), 20);
            if (!result) {
                productPageMsgList.add("Unable to verify back icon in ProductPage");
                return result;
            }

            result = cfObj.commonWaitForListOfElementsToBeVisible(driver, productPageObj.getListPackage(), 20);
            if (!result) {
                productPageMsgList.add("Unable to verify package list in ProductPage");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, productPageObj.getBtnFilter(), 10);
            if (!result) {
                productPageMsgList.add("Unable to verify filter btn in ProductPage");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, productPageObj.getBtnSortBy(), 10);
            if (!result) {
                productPageMsgList.add("Unable to verify sort by btn in ProductPage");
                return result;
            }
        } catch (Exception e) {
            result = false;
            productPageMsgList.add("verifyProductPage Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean clickUnpurchasedPackage(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        boolean purchased = true;
        int index = 0;
        try {
            List<MobileElement> el = productPageObj.getListPackage();
            while (true) {
                el.get(index).click();

                /*
                 * Verifying user detail layer is coming on clicking buy now button which ensures
                 * the package is not purchased
                 */
                purchasePackageUtilObj = new PurchasePackageUtil(driver);

                cfObj.waitForPageLoading(driver, 3, 2000, purchasePackageUtilObj.purchasePackagePageObj.getBtnBuyNow());

                result = purchasePackageUtilObj.clickBuyNowBtn(driver);
                if (!result) {
                    productPageMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
                    return result;
                }

                if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
                    purchased = !(cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                            "//android.view.View[@content-desc=\"User Details\"]", "xpath", 10));
                    if (purchased) {
                        purchased = !(cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                                "//*[contains(@text,'Select Payment method')]", "xpath", 10));
                    }

                    cfObj.pressAndroidKey(driver, key.BACK, 1);

                    if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "lay_already_purchased", "id", 5)) {
                        cfObj.pressAndroidKey(driver, key.BACK, 1);
                        index++;
                        continue;
                    }

                    break;
                } else {
                    purchased = !(cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@name='User Details']",
                            "xpath", 10));
                    if (purchased) {
                        purchased = !(cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                                "//XCUIElementTypeStaticText[@name='Preferred Payment Methods']", "xpath", 10));
                    }
                    cfObj.commonClick(cfObj.commonGetElement(driver,
                            "//*[@name='User Details']/following-sibling::XCUIElementTypeOther[1]", "xpath"));
                    if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                            "//*[contains(@name,'ALREADY PURCHASED')]", "xpath", 5)) {
                        cfObj.commonClick(driver, "//*[@name='storeProductDetailBackItem']", "xpath");
                        purchased = true;
                        index++;
                        continue;
                    }
                    break;
                }
            }

            result = purchasePackageUtilObj.verifyUnpurchasedPackagePage(driver);
            if (!result) {
                productPageMsgList.add("Failed to verify Unpurchased Package Page.");
                productPageMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
            }

        } catch (Exception e) {
            result = false;
            productPageMsgList.add("clickUnpurchasedPackage Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean openFirstUnPurchasedPackage(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        boolean purchased = true;
        int index = 0;
        try {
            result = cfObj.commonWaitForListOfElementsToBeVisible(driver, productPageObj.getListPackage(), 10);
            if(!result){
                productPageMsgList.add("List of packages are not visible");
                return result;
            }

            List<MobileElement> el = productPageObj.getListPackage();
            el.get(index).click();

            purchasePackageUtilObj = new PurchasePackageUtil(driver);

            cfObj.waitForPageLoading(driver, 3, 2000, purchasePackageUtilObj.purchasePackagePageObj.getBtnBuyNow());

            result = purchasePackageUtilObj.verifyUnpurchasedPackagePage(driver);
            if (!result) {
                productPageMsgList.add("Failed to verify UnPurchased Package Page.");
                productPageMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
            }
        } catch (Exception e) {
            result = false;
            productPageMsgList.add("openFirstUnPurchasedPackage Exception: " + e.getMessage());
        }
        return result;
    }

}
