package applicationUtil;

import java.util.ArrayList;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.PerformanceDashboard_OR;
import util.Common_Function;
import util.ConfigFileReader;

public class PerformanceDashboardUtil {

	PerformanceDashboard_OR performanceDashboardObj;
	public Common_Function cfObj = new Common_Function();
	public ArrayList<String> performanceDashboardMsgList = new ArrayList<>();
	HomePageUtil homeUtilObj;
	MyContentUtil contentUtil;

	public PerformanceDashboardUtil(AppiumDriver<MobileElement> driver) {
		performanceDashboardObj = new PerformanceDashboard_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), performanceDashboardObj);
	}

	public boolean verifyPerformanceDashboard(AppiumDriver<MobileElement> driver, Boolean isTestPassPurchased) {
		boolean result = true;
		LoginUtil newLoginUtilObj = new LoginUtil(driver);
		try {

			if (ConfigFileReader.strEnv.equalsIgnoreCase("production")){

				result = newLoginUtilObj.verifyLoginUsingEmailId(driver, "", ConfigFileReader.strUserPassword, false);
				if(!result){
					performanceDashboardMsgList.addAll(newLoginUtilObj.loginMsgList);
					return result;
				}
			} else {
				if (isTestPassPurchased) {
					result = newLoginUtilObj.verifyLoginGuestUser(driver);
				} else {
					result = newLoginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), true);
				}
				if (!result) {
					performanceDashboardMsgList.addAll(newLoginUtilObj.loginMsgList);
					return result;
				}
			}

            result = verifyPerformanceDashboardInSideNav(driver, isTestPassPurchased);
			if (!result) {
				performanceDashboardMsgList.add("Failed verifyPerformanceDashboardInSideNav");
				return result;
			}

			result = verifyPerformanceDashboardInUserProfile(driver, isTestPassPurchased);
			if (!result) {
				performanceDashboardMsgList.add("Failed verifyPerformanceDashboardInUserProfile");
				return result;
			}

			result = verifyPerformanceDashboardInMyContent(driver, isTestPassPurchased);
			if (!result) {
				performanceDashboardMsgList.add("Failed verifyPerformanceDashboardInMyContent");
				return result;
			}
		} catch (Exception e) {
			result = false;
			performanceDashboardMsgList.add("verifyPerformanceDashboard Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyPerformanceDashboardInMyContent(AppiumDriver<MobileElement> driver,
														 Boolean isTestPassPurchased) {
		boolean result = true;
		homeUtilObj = new HomePageUtil(driver);
		contentUtil = new MyContentUtil(driver);
		try {

			result = homeUtilObj.clickMyContentButton(driver);
			if (!result) {
				performanceDashboardMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = cfObj.waitForPageLoading(driver, 10, 2000, performanceDashboardObj.getBtnPurchasedSection());
			if (!result) {
				performanceDashboardMsgList.add("Purchase page Loading Error.");
				return result;
			}
			cfObj.commonClick(performanceDashboardObj.getBtnPurchasedSection());

            if (!isTestPassPurchased) {

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"No content found\"]", "xpath", 10);
				if (!result) {
					performanceDashboardMsgList.add("No content found text in my content is not visible");
					return result;
				}
            }

        } catch (Exception e) {
			performanceDashboardMsgList.add("Exception verifyPerformanceDashboardInMyContent : " + e.getMessage());
			return result;
		}
		return result;
	}

	public boolean verifyPerformanceDashboardInUserProfile(AppiumDriver<MobileElement> driver,
			Boolean isTestPassPurchased) {
		boolean result = true;
		homeUtilObj = new HomePageUtil(driver);
		contentUtil = new MyContentUtil(driver);
		try {

			result = homeUtilObj.justOpenNavigationDrawer(driver);
			if (!result) {
				performanceDashboardMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = homeUtilObj.clickOnUserProfileSection(driver);
			if (!result) {
				performanceDashboardMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.Button[@content-desc=\"openBottomSheet\"]", "xpath", 10);
			if (!result) {
				performanceDashboardMsgList.add("Bottom sheet icon in user profile is not visible");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver,
					"//android.widget.Button[@content-desc=\"openBottomSheet\"]", "xpath"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.ImageView[@content-desc=\"performanceDashboard\n" +
							"Performance Dashboard\"]",
					"xpath", 10);
			if (!result) {
				performanceDashboardMsgList.add("PD btn in Bottom sheet in user profile is not visible");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver,
					"//android.widget.ImageView[@content-desc=\"performanceDashboard\n" +
							"Performance Dashboard\"]",
					"xpath"));

			Thread.sleep(5000);

			result = cfObj.commonWaitForElementToBeVisible(driver, performanceDashboardObj.purchaseCourseToAccessText(),
					20);
			if (!result) {
				performanceDashboardMsgList.add("purchaseCourseToAccessText in pd user profile is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, performanceDashboardObj.takeMeToStore(), 20);
			if (!result) {
				performanceDashboardMsgList.add("takeMeToStore in pd user profile is not visible");
				return result;
			}

			cfObj.commonClick(performanceDashboardObj.takeMeToStore());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.Button[@content-desc=\"Search\"]", "xpath", 10);
			if (!result) {
				performanceDashboardMsgList.add("Search icon in store is not visible");
				return result;
			}
		} catch (Exception e) {
			performanceDashboardMsgList.add("Exception verifyPerformanceDashboard : " + e.getMessage());
			return result;
		}
		return result;
	}

	public boolean verifyPerformanceDashboardInSideNav(AppiumDriver<MobileElement> driver,
			Boolean isTestPassPurchased) {
		boolean result = true;
		homeUtilObj = new HomePageUtil(driver);
		contentUtil = new MyContentUtil(driver);
		try {
			result = homeUtilObj.justOpenNavigationDrawer(driver);
			if (!result) {
				performanceDashboardMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, performanceDashboardObj.performanceDashboard(), 20);
			if (!result) {
				performanceDashboardMsgList.add("Performance dashboard btn in side nav is not visible");
				return result;
			}

			cfObj.commonClick(performanceDashboardObj.performanceDashboard());

			Thread.sleep(5000);

			if(!isTestPassPurchased){
				result = cfObj.commonWaitForElementToBeVisible(driver, performanceDashboardObj.purchaseCourseToAccessText(),
						20);
				if (!result) {
					performanceDashboardMsgList.add("purchaseCourseToAccessText popup in pd side nav is not visible");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, performanceDashboardObj.takeMeToStore(), 20);
				if (!result) {
					performanceDashboardMsgList.add("takeMeToStore in popup in pd side nav is not visible");
					return result;
				}

				cfObj.commonClick(performanceDashboardObj.takeMeToStore());

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.widget.Button[@content-desc=\"Search\"]", "xpath", 10);
				if (!result) {
					performanceDashboardMsgList.add("Search icon in store is not visible");
					return result;
				}

				result = homeUtilObj.clickHomeBtn(driver);
				if (!result) {
					performanceDashboardMsgList.addAll(homeUtilObj.homeMsgList);
					return result;
				}
			} else {
				driver.navigate().back();
				driver.navigate().back();
			}
		} catch (Exception e) {
			performanceDashboardMsgList.add("Exception verifyPerformanceDashboardInSideNav : " + e.getMessage());
			return result;
		}
		return result;
	}
}
