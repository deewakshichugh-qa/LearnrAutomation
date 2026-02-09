package pageObject;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class FeedbackForm_OR {

	@AndroidFindBy(id = "heading")
	private MobileElement titleFeedbackForm;
	
	public MobileElement getTitleFeedbackForm() {
		return titleFeedbackForm;
	}
	
	@AndroidFindBy(id = "rating_bar")
	private MobileElement feedbackStars;
	
	public MobileElement getFeedbackStars() {
		return feedbackStars;
	}
	
	@AndroidFindBy(id = "not_now")
	private MobileElement btnNotNow;
	
	public MobileElement getBtnNotNow() {
		return btnNotNow;
	}
	
	@AndroidFindBy(id = "submit")
	private MobileElement btnSubmit;
	
	public MobileElement getBtnSubmit() {
		return btnSubmit;
	}
	
	@AndroidFindBy(id = "feedback")
	private MobileElement fieldFeedback;
	
	public MobileElement getFieldFeedback() {
		return fieldFeedback;
	}
}
