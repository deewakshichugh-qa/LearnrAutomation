package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.TestAnalysisPage_OR;
import util.Common_Function;

public class TestAnalysisPageUtil {

	TestAnalysisPage_OR testAnalysisPageObj;
	List<String> testAnalysisPageMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();

	public TestAnalysisPageUtil(AppiumDriver<MobileElement> driver) {
		testAnalysisPageObj = new TestAnalysisPage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), testAnalysisPageObj);
	}

	public boolean verifyTestAnalysisPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, testAnalysisPageObj.getBtnNavigateUp(), 10);
			if(!result) {
				testAnalysisPageMsgList.add("Back button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testAnalysisPageObj.getBtnReattempt(), 10);
			if(!result) {
				testAnalysisPageMsgList.add("ReAttempt button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testAnalysisPageObj.getBtnShareScorecard(), 10);
			if(!result) {
				testAnalysisPageMsgList.add("ShareScorecard button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testAnalysisPageObj.getLinkPostNow(), 10);
			if(!result) {
				testAnalysisPageMsgList.add("PostNow Link is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testAnalysisPageObj.getTitlePage(), 10);
			if(!result) {
				testAnalysisPageMsgList.add("Title page is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testAnalysisPageObj.getTitleTopRankers(), 10);
			if(!result) {
				testAnalysisPageMsgList.add("TopRankers title is not visible.");
				return result;
			}

		}catch(Exception e) {
			result=false;
			testAnalysisPageMsgList.add("verifyTestAnalysisPage_Exception"+e.getMessage());
		}
		return result;
	}
}
