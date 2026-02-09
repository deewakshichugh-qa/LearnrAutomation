package applicationUtil;

import apiUtill.PypApiUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import pageObject.PypTestseries_OR;
import util.Common_Function;
import util.ConfigFileReader;

import java.util.ArrayList;

public class PypTestseriesUtil {

	public Common_Function cfObj = new Common_Function();
	public ArrayList<String> pypTestMsgList = new ArrayList<String>();
	PypTestseries_OR pypTestORobj;
	LoginUtil loginUtilObj;
	HomePageUtil homePageUtilObj;

	public PypTestseriesUtil(AppiumDriver<MobileElement> driver) {
		pypTestORobj = new PypTestseries_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), pypTestORobj);
	}

	public boolean verifyPypTestseries(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		loginUtilObj = new LoginUtil(driver);
		try {
			if(ConfigFileReader.strEnv.equalsIgnoreCase("production")){

				result = loginUtilObj.verifyLoginUsingEmailIdFromConfig(driver);
				if (!result) {
					pypTestMsgList.addAll(loginUtilObj.loginMsgList);
					return result;
				}

			} else {
				PypApiUtil pypApiUtilObj = new PypApiUtil();
				result = pypApiUtilObj.createFreePYPMockTest();
				if (!result) {
					pypTestMsgList.add("Unable to create free pyp test");
					return result;
				}

				result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), false);
				if (!result) {
					pypTestMsgList.addAll(loginUtilObj.loginMsgList);
					return result;
				}
			}

			homePageUtilObj = new HomePageUtil(driver);
			result = homePageUtilObj.JustOpenAndClickHomeBtn(driver);
			if (!result) {
				pypTestMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			result = homePageUtilObj.openPYPonHome(driver);
			if (!result) {
				pypTestMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			result = verifyPypPdf(driver);
			if (!result) {
				return result;
			}
		} catch (Exception e) {
			result = false;
			pypTestMsgList.add("verifyPypTestseries Exception: " + e.getMessage());
		}
		return result;
	}

	private boolean verifyPypPdf(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,"//android.view.View[@content-desc=\"Previous Year Papers\"]","xpath",10);
			if (!result) {
				pypTestMsgList.add("PYP Heading is not visible in pyp");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,"//android.widget.ImageView[@content-desc=\"filter_btn\"]","xpath",10);
			if (!result) {
				pypTestMsgList.add("Filter btn is not visible in pyp");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,"//android.widget.ImageView[@content-desc=\"Search\"]","xpath",10);
			if (!result) {
				pypTestMsgList.add("Search btn is not visible in pyp");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,"//android.view.View[@content-desc=\"btn_appbar_back\"]","xpath",10);
			if (!result) {
				pypTestMsgList.add("Back btn is not visible in pyp");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,"//android.view.View[@content-desc=\"Banking\n" +
					"Tab 1 of 1\"]","xpath",10);
			if (!result) {
				pypTestMsgList.add("Unable to verify exam type inside PYP heading");
				return result;
			}

			if(ConfigFileReader.strEnv.equalsIgnoreCase("production")){

			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,"//android.view.View[contains(@content-desc,\"Automation PYP Free Test\")]","xpath",10);
				if (!result) {
					pypTestMsgList.add("Unable to verify listing of tests");
					return result;
				}
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,"//android.widget.ImageView[@content-desc=\"test_series_btn\"]","xpath",10);
			if (!result) {
				pypTestMsgList.add("Unable to verify getQuiz btn in listing of tests");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,"//android.widget.ImageView[@content-desc=\"download_pdf_btn\"]","xpath",10);
			if (!result) {
				pypTestMsgList.add("Unable to verify download btn in listing of tests");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElements(driver, "//android.widget.ImageView[@content-desc=\"download_pdf_btn\"]", "xpath").get(0));

			Thread.sleep(20000);

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,"//android.widget.ImageView[@content-desc=\"download_pdf_btn\"]","xpath",10);
			if (!result) {
				pypTestMsgList.add("Unable to verify download btn in listing of tests");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElements(driver, "//android.widget.ImageView[@content-desc=\"download_pdf_btn\"]", "xpath").get(0));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,"//android.widget.Button[@content-desc=\"Back\"]","xpath",10);
			if (!result) {

				cfObj.commonClick(cfObj.commonGetElements(driver, "//android.widget.ImageView[@content-desc=\"download_pdf_btn\"]", "xpath").get(0));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,"//android.widget.Button[@content-desc=\"Back\"]","xpath",10);
				if (!result) {
					pypTestMsgList.add("Unable to verify back btn inside pdf in 2nd try, might be it is downloading the pdf.");
					return result;
				}
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,"//android.widget.RelativeLayout","xpath",10);
			if (!result) {
				pypTestMsgList.add("Unable to verify pdf content inside pdf");
				return result;
			}

			driver.navigate().back();

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,"//android.view.View[@content-desc=\"Previous Year Papers\"]","xpath",10);
			if (!result) {
				pypTestMsgList.add("Unable to verify inside PYP heading");
				return result;
			}
		} catch (Exception e) {
			result = false;
			pypTestMsgList.add("verifyPypPdf_Exception: " + e.getMessage());
		}
		return result;
	}
}
