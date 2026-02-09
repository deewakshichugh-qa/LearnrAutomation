package pageObject;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class StreakPage_OR {
	
	@AndroidFindBy(accessibility = "Ongoing\n"
			+ "Tab 1 of 3")
	private MobileElement tabOngoing;
	public MobileElement getTabOngoing() {
		return tabOngoing;
	}
	
	@AndroidFindBy(accessibility = "Upcoming \n"
			+ "Tab 2 of 3")
	private MobileElement tabUpcoming;
	public MobileElement getTabUpcoming() {
		return tabUpcoming;
	}
	
	@AndroidFindBy(accessibility = "Completed\n"
			+ "Tab 3 of 3")
	private MobileElement tabCompleted;
	public MobileElement getTabCompleted() {
		return tabCompleted;
	}
	
	@AndroidFindBy(xpath= "//android.view.View[@content-desc='How Rewards Work?']")
	private MobileElement howRewardsWorkTextButton;
	
	public MobileElement getHowRewardsWorkTextButton() {
		return howRewardsWorkTextButton;
	}
	
	@AndroidFindBy(xpath="//android.view.View[@content-desc=\"pressedBack\"]/android.view.View/android.view.View[1]")
	private MobileElement backButtonOnRewardWorkPage;
	
	public MobileElement getBackButtonOnRewardWorkPage() {
		return backButtonOnRewardWorkPage;
	}
}
