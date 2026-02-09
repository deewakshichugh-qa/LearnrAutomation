package pageObject;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class UpdateValidity_OR {

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"selectYourValidityClick\")]")
	private MobileElement validityTextInPDP;

	public MobileElement validityTextInPDP() {
		return validityTextInPDP;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Select Validity\"]")
	private MobileElement selectValidityTextInPDP;

	public MobileElement SelectValidityTextInPDP() {
		return validityTextInPDP;
	}

}
