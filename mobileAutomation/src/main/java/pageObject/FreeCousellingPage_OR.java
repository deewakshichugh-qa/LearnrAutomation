package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class FreeCousellingPage_OR {
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"How to prepare for this exam ?\")")
	private MobileElement titlePage;
	
	public MobileElement getTitlePage() {
		return titlePage;
	}
	@AndroidFindBy(className = "android.widget.Button")
	private List<MobileElement> btnCloseCousellingPage;
	
	public MobileElement getBtnCloseCousellingPage() {
		return btnCloseCousellingPage.get(1);
	}
	
}
