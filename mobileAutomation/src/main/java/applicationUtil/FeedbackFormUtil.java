package applicationUtil;

import java.util.ArrayList;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.FeedbackForm_OR;
import util.Common_Function;

public class FeedbackFormUtil {

	FeedbackForm_OR feedbackFormPageObj;
	public ArrayList<String> feedbackFormMsgList = new ArrayList<>();
	public Common_Function cfObj = new Common_Function();

	public FeedbackFormUtil(AppiumDriver<MobileElement> driver) {
		feedbackFormPageObj = new FeedbackForm_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), feedbackFormPageObj);
	}

	public boolean veriyFeedbackForm(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, feedbackFormPageObj.getTitleFeedbackForm(), 10);
			if(!result) {
				feedbackFormMsgList.add("Unable to verify Feedback form title.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, feedbackFormPageObj.getFeedbackStars(), 10);
			if(!result) {
				feedbackFormMsgList.add("Unable to verify Stars in feedback form.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, feedbackFormPageObj.getBtnNotNow(), 10);
			if(!result) {
				feedbackFormMsgList.add("Unable to verify Not now Button in Feedback from.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, feedbackFormPageObj.getBtnSubmit(), 10);
			if(!result) {
				feedbackFormMsgList.add("Unable to verify Submit btn in feedback form.");
				return result;
			}

			//			result = cfObj.commonWaitForElementToBeVisible(driver, feedbackFormPageObj.getFieldFeedback(), 10);
			//			if(!result) {
			//				feedbackFormMsgList.add("Unable to verify feedback field.");
			//				return result;
			//			}

		} catch (Exception e) {
			feedbackFormMsgList.add("verifyFeedbackForm_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean skipFeedbackForm(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		int i=0;
		try {
			while(i<2) {

				if(cfObj.commonWaitForElementToBeVisible(driver, feedbackFormPageObj.getTitleFeedbackForm(), 3)) {
					cfObj.commonClick(feedbackFormPageObj.getBtnNotNow());
				}
				i++;
			}
		} catch (Exception e) {
			feedbackFormMsgList.add("skipFeedbackForm_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean submitFeedbackForm(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.commonClick(feedbackFormPageObj.getBtnSubmit());

			result = !(cfObj.commonWaitForElementToBeVisible(driver, feedbackFormPageObj.getTitleFeedbackForm(), 5));
			if(!result) {
				feedbackFormMsgList.add("Unable to click feedback submit button..");
			}
		} catch (Exception e) {
			feedbackFormMsgList.add("submitFeedbackForm_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean setFeedbackField(AppiumDriver<MobileElement> driver, String feedback) {
		boolean result = true;
		try {

			result = cfObj.commonSetTextTextBox(feedbackFormPageObj.getFieldFeedback(), feedback);
			if(!result) {
				feedbackFormMsgList.add("Unable to enter feedback on feedback text field.");
			}

		} catch (Exception e) {
			feedbackFormMsgList.add("submitFeedbackForm_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean rateFeedbackForm(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.commonClick(feedbackFormPageObj.getFeedbackStars());

		} catch (Exception e) {
			feedbackFormMsgList.add("submitFeedbackForm_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

}
