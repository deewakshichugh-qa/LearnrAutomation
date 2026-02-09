package applicationUtil;

import java.util.ArrayList;

import org.openqa.selenium.support.PageFactory;

import apiUtill.StreakApiUtill;
import apiUtill.UserLoginUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.StreakPage_OR;
import util.Common_Function;
import util.Common_Function.direction;
import util.ConfigFileReader;

public class StreakUtill {

	StreakPage_OR streakOR;
	LoginUtil loginUtilObj;
	HomePageUtil homeUtilObj;
	StreakApiUtill streakApiObj;
	Common_Function cfObj = new Common_Function();
	public static ArrayList<String> streakMsgList = new ArrayList<>();

	public StreakUtill(AppiumDriver<MobileElement> driver) {

		streakOR = new StreakPage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), streakOR);

	}

	public boolean verifyStreakNew(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			loginUtilObj = new LoginUtil(driver);

			result = loginUtilObj.verifyLoginGuestUser(driver);
			if (!result) {
				streakMsgList.addAll(loginUtilObj.loginMsgList);

				return result;
			}

			homeUtilObj = new HomePageUtil(driver);

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='Participate and Win']",
					"xpath", 10);
			if (!result) {

				System.out.println("------------------------------");
				System.out
						.println("The Streak functionality is paused by product, not visible on testing or prod env.");
				System.out.println("------------------------------");

				return true;
			}

			result = homeUtilObj.verifyAndCickOnParticipateAndWin(driver);
			if (!result) {
				streakMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			Thread.sleep(3000);

			result = verifyUpcomingStreakNewUser(driver);
			if (!result) {
				streakMsgList.add("Unable to verify Upcoming streak for new user");
				return result;
			}

			result = verifyOngoingStreakNewUser(driver);
			if (!result) {
				streakMsgList.add("Unable to verify Ongoing streak for new user");
				return result;
			}

			result = verifyCompletedStreakNewUser(driver);
			if (!result) {
				streakMsgList.add("Unable to verify Completed streak for new user");
				return result;
			}

			result = verifyMyStreakPageNewUser(driver);
			if (!result) {
				streakMsgList.add("Unable to verify My Streak page for new user");
				return result;
			}

		} catch (Exception e) {
			streakMsgList.add("verifyStreakNew_Exception: " + e.getMessage());
			return false;

		}
		return result;
	}

	public boolean verifyStreak(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String userToken = null;
		try {

			loginUtilObj = new LoginUtil(driver);

			result = loginUtilObj.verifyLoginGuestUser(driver);
			if (!result) {
				streakMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			HomePageUtil homeUtilObj = new HomePageUtil(driver);
			result = homeUtilObj.verifyAndCickOnParticipateAndWin(driver);
			if (!result) {
				streakMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			Thread.sleep(3000);

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Check today’s task to maintain your streak!\n"
							+ "0 day streak\n" + "0 rewards earned\"]",
					"xpath", 10);
			if (!result) {
				streakMsgList.add("O days and O reward earned is not visible at streak page.");
				return result;
			}

			UserLoginUtil userLoginUtiliObj = new UserLoginUtil();
			ConfigFileReader cfrObj = new ConfigFileReader();

			userToken = userLoginUtiliObj.getUserToken(cfrObj.getUserNamePassword().split("/")[0].toString(),
					cfrObj.getUserNamePassword().split("/")[1].toString());
			if (userToken == null) {
				streakMsgList.add("UserToken is null cannot proceed");
				return false;
			}

			// Add streak
			streakApiObj = new StreakApiUtill();
			result = streakApiObj.addStreak("1116fb34-21e1-46e9-9685-1cf6cde0fead", userToken);
			if (!result) {
				streakMsgList.add("Unable to add streak using api");
				return result;
			}

		} catch (Exception e) {
			streakMsgList.add("verifyStreak_Exception: " + e.getMessage());
			return false;
		}
		return result;
	}

	public boolean verifyStreakPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, streakOR.getTabOngoing(), 10);
			if (!result) {
				streakMsgList.add("Unable to locate Ongoing Tab");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, streakOR.getTabUpcoming(), 10);
			if (!result) {
				streakMsgList.add("Unable to locate Upcoming Tab");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, streakOR.getTabCompleted(), 10);
			if (!result) {
				streakMsgList.add("Unable to locate Completed Tab");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//*[contains(@text,'Check today’s task to maintain your streak!\n1 day streak\n1 rewards earned')]",
					"xpath", 10);
			if (!result) {
				streakMsgList.add("Unable to locate My streak description");
				return result;
			}

		} catch (Exception e) {
			streakMsgList.add("verifyStreakPage_Exception: " + e.getMessage());
			return false;
		}
		return result;
	}

	public boolean verifyMyStreakPageNewUser(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//*[contains(@content-desc,'to maintain your streak')]", "xpath", 10);
			if (!result) {
				streakMsgList.add("O days and O reward earned is not visible at streak page new user");
				return result;
			} else {
				cfObj.commonClick(cfObj.commonGetElement(driver,
						"//*[contains(@content-desc,'to maintain your streak')]", "xpath"));
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Your Progress']", "xpath", 10);
			if (!result) {
				streakMsgList.add("Unable to Your progress in My streak");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Daily Goal\"]", "xpath", 10);
			if (!result) {
				streakMsgList.add("Unable to verify Daily goals section in my streak");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Your Streaks\"]", "xpath", 10);
			if (!result) {
				streakMsgList.add("Unable to verify My streak title in My streak page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Current Streak\"]", "xpath", 10);
			if (!result) {
				streakMsgList.add("Unable to verify current streak title in My streak page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"(//android.view.View[contains(@content-desc,\" Day \")])[1]", "xpath", 10);
			if (!result) {
				streakMsgList.add("The current streak is not showing 'days' for a user");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"(//android.view.View[contains(@content-desc,\" Day \")])[2]", "xpath", 10);
			if (!result) {
				streakMsgList.add("The Highest Streak is not showing 'days' for a user");
				return result;
			}

			result = verifyHowRewardsWork(driver);
			if (!result) {
				streakMsgList.add("Rewards-Work page is not verified");
				return result;
			}

			// cfObj.scrollUtill(driver, 1, direction.DOWN);

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.ImageView[@content-desc=\"My Rewards\n"
							+ "Check your rewards and keep completing\n" + "task to win more!\"]",
					"xpath", 10);
			if (!result) {
				streakMsgList.add("Unable to verify My rewards in My streak");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Leaderboard\"]", "xpath", 10);
			if (!result) {
				streakMsgList.add("Unable to verify Leaderboard in My streak page");
				return result;
			}

		} catch (Exception e) {
			streakMsgList.add("verifyMyStreakPageNewUser_Exception: " + e.getMessage());
			return false;
		}
		return result;
	}

	public boolean verifyOngoingStreakNewUser(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.commonClick(streakOR.getTabOngoing());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.ImageView[@content-desc=\"Adda247 Challenge\nThere is no ongoing challenge currently.\nKeep an eye out, exciting challenges are coming out soon!\"]",
					"xpath", 10);
			if (!result) {
				streakMsgList.add("Unable to verify ongoing tab for new user");
				return result;
			}

		} catch (Exception e) {
			streakMsgList.add("verifyOngoingStreak_Exception: " + e.getMessage());
			return false;
		}
		return result;
	}

	public boolean verifyUpcomingStreakNewUser(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.waitForPageLoading(driver, 4, 2000, streakOR.getTabUpcoming());

			cfObj.commonClick(streakOR.getTabUpcoming());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.ImageView[@content-desc=\"Adda247 Challenge\n"
							+ "Keep waiting! A new challenge is coming soon.\"]",
					"xpath", 10);
			if (!result) {
				streakMsgList
						.add("Unable to find 'Keep waiting! A new challenge is coming soon' text in Upcoming section.");
				return result;
			}

		} catch (Exception e) {
			streakMsgList.add("verifyUpcomingStreak_Exception: " + e.getMessage());
			return false;
		}
		return result;
	}

	public boolean verifyCompletedStreakNewUser(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.commonClick(streakOR.getTabCompleted());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//*[contains(@content-desc,'Not Participated')]", "xpath", 10);
			if (!result) {
				streakMsgList.add("Unable to find '' text in Completed section.");
				return result;
			}

		} catch (Exception e) {
			streakMsgList.add("verifyCompletedStreakk_Exception: " + e.getMessage());
			return false;
		}
		return result;
	}

	public boolean verifyHowRewardsWork(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.scrollUtill(driver, 2, direction.DOWN);

			result = clickOnHowRewardsWorkTextButton(driver);
			if (!result) {
				streakMsgList.add("'How Rewards Work' text button is not verified");
				return result;
			}

			result = verifyHowRewardsWorkPage(driver);
			if (!result) {
				streakMsgList.add("'How Rewards Work' page button is not verified");
				return result;
			}

			result = clickOnBackButton(driver);
			if (!result) {
				streakMsgList.add("'How Rewards Work' page button is not verified");
				return result;
			}

		} catch (Exception e) {
			result = true;
			streakMsgList.add("verifyHowRewardsWork_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnHowRewardsWorkTextButton(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='How Rewards Work?']", "xpath", 10);
			if (!result) {
				streakMsgList.add("'How Rewards Work' text button is not visible");
				return result;
			}

			cfObj.commonClick(streakOR.getHowRewardsWorkTextButton());

		} catch (Exception e) {
			result = true;
			streakMsgList.add("verifyHowRewardsWork_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnBackButton(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"pressedBack\"]/android.view.View/android.view.View[1]",
					"xpath", 10);
			if (!result) {
				streakMsgList.add("Back button is not visible");
				return result;
			}

			cfObj.commonClick(streakOR.getBackButtonOnRewardWorkPage());

		} catch (Exception e) {
			result = true;
			streakMsgList.add("clickOnBackButton_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyHowRewardsWorkPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.ImageView[@content-desc=\"My Rewards\n"
							+ "Check your rewards and keep completing\n" + "task to win more!\"]",
					"xpath", 10);
			if (!result) {
				streakMsgList.add("'My Rewards' tiles is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc='Streak Milestones']", "xpath", 10);
			if (!result) {
				streakMsgList.add("'Streak Milestones' header text is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Rewards will be automatically added to your "
							+ "account on completing the Streak Milestones.\"]",
					"xpath", 10);
			if (!result) {
				streakMsgList.add("'Streak Milestones' sub-header text is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[contains(@content-desc,\" Day Streak - \n" + "\")]", "xpath", 10);
			if (!result) {
				streakMsgList.add("'Streak Milestones' list of Days streak is not visible");
				return result;
			}

		} catch (Exception e) {
			result = true;
			streakMsgList.add("verifyHowRewardsWorkPage_Exception: " + e.getMessage());
		}
		return result;
	}
}
