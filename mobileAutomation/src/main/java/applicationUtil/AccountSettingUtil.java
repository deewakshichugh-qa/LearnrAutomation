package applicationUtil;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.AccountSettingPage_OR;
import util.Common_Function;
import util.ConfigFileReader;

public class AccountSettingUtil {

	AccountSettingPage_OR accountSettingPageORObj;
	List<String> accountSettingMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();

	public AccountSettingUtil(AppiumDriver<MobileElement> driver) {
		accountSettingPageORObj = new AccountSettingPage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), accountSettingPageORObj);
	}

	public boolean verifyAccountSettingUI(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, accountSettingPageORObj.getSettingPageTitle(), 10);
			if (!result) {
				accountSettingMsgList.add("Account setting page title is not visible.");
				return result;
			}
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {

				result = verifyDotIcon(driver);
				if (!result) {
					accountSettingMsgList.add("Dot icon is not working properly.");
					return result;
				}
			}

			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {

				result = verifyChangeFeedIcon(driver);
				if (!result) {
					accountSettingMsgList.add("Change feed icon is not working properly.");
					return result;
				}
			}
			result = verifyChangeLanguageIcon(driver);
			if (!result) {
				accountSettingMsgList.add("Change language icon is not working properly.");
				return result;
			}

			result = verifyChangeFontSizeIcon(driver);
			if (!result) {
				accountSettingMsgList.add("Change font size icon is not working properly.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, accountSettingPageORObj.getFeedBackIcon(), 10);
			if (!result) {
				accountSettingMsgList.add("FeedBack icon is not visible.");
				return result;
			}

			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result = verifyShareAppIcon(driver);
				if (!result) {
					accountSettingMsgList.add("ShareApp icon is not working properly.");
					return result;
				}
			}
		} catch (Exception e) {
			result = false;
			accountSettingMsgList.add("AccountSettingPage_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyChangeFeedIcon(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			if (cfObj.commonWaitForElementToBeVisible(driver, accountSettingPageORObj.getFeedChangeIcon(), 10)) {

				cfObj.commonClick(accountSettingPageORObj.getFeedChangeIcon());

				result = cfObj.commonWaitForElementToBeVisible(driver,
						accountSettingPageORObj.getChooseFeedLayoutTitle(), 10);
				if (!result) {
					accountSettingMsgList.add("Choose feed layout title is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, accountSettingPageORObj.getNewFeedView(), 10);
				if (!result) {
					accountSettingMsgList.add("New Feed view is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, accountSettingPageORObj.getOldFeedView(), 10);
				if (!result) {
					accountSettingMsgList.add("Old Feed view is not visible.");
					return result;
				}

				cfObj.commonClick(accountSettingPageORObj.getChooseFeedLayoutCloseBtn());
				Thread.sleep(2000);

				result = !(cfObj.commonWaitForElementToBeVisible(driver,
						accountSettingPageORObj.getChooseFeedLayoutTitle(), 5));
				if (!result) {
					accountSettingMsgList.add("Choose feed layout title is visible.");
					return result;
				}
			} else {
				System.out.println("Feed change icon is not visible.");
			}

		} catch (Exception e) {
			result = false;
			accountSettingMsgList.add("FeedLayoutChange_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyChangeLanguageIcon(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, accountSettingPageORObj.getLanguageChangeIcon(), 10);
			if (!result) {
				accountSettingMsgList.add("Language change icon is not visible.");
				return result;
			}
			cfObj.commonClick(accountSettingPageORObj.getLanguageChangeIcon());

			HomePageUtil homePageUtilObj = new HomePageUtil(driver);
			result = cfObj.commonWaitForElementToBeVisible(driver, homePageUtilObj.homePageObj.getChangeLanguagePageTitle(), 30);
			if (!result) {
				accountSettingMsgList.add("Change Language page title is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Choose your Language\n" +
					"Choose a Language you are comfortable in Studying\"]/android.widget.ImageView", "xpath", 10);
			if (!result) {
				accountSettingMsgList.add("back btn on lang change is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'English\n" +
					"Hello')]", "xpath", 10);
			if (!result) {
				accountSettingMsgList.add("English Language btn title is not visible");
				return result;
			}

			cfObj.commonClick(driver, "//*[contains(@content-desc,'English\n" +
					"Hello')]", "xpath");

			result = cfObj.commonWaitForElementToBeVisible(driver, accountSettingPageORObj.getLanguageChangeIcon(), 10);
			if (!result) {
				accountSettingMsgList.add("Language change icon is not visible.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			accountSettingMsgList.add("ChangeLanguageIcon_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyChangeFontSizeIcon(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, accountSettingPageORObj.getChangeFontSizeIcon(), 10);
			if (!result) {
				accountSettingMsgList.add("Font Size change icon is not visible.");
				return result;
			}
			cfObj.commonClick(accountSettingPageORObj.getChangeFontSizeIcon());

			result = cfObj.commonWaitForElementToBeVisible(driver, accountSettingPageORObj.getFontSizeTitle(), 10);
			if (!result) {
				accountSettingMsgList.add("Select Font size title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, accountSettingPageORObj.getExtraLargeFontSizeBtn(),
					10);
			if (!result) {
				accountSettingMsgList.add("Extra large font size button is not visible.");
				return result;
			}
			result = cfObj.commonWaitForElementToBeVisible(driver, accountSettingPageORObj.getLargeFontSizeBtn(), 10);
			if (!result) {
				accountSettingMsgList.add("Large font size button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, accountSettingPageORObj.getMediumFontSizeBtn(), 10);
			if (!result) {
				accountSettingMsgList.add("Medium font size button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, accountSettingPageORObj.getSmallFontSizeBtn(), 10);
			if (!result) {
				accountSettingMsgList.add("Small font size button is not visible.");
				return result;
			}

			cfObj.commonClick(accountSettingPageORObj.getFontSizeCancelBtn());
			Thread.sleep(2000);

			result = !(cfObj.commonWaitForElementToBeVisible(driver, accountSettingPageORObj.getFontSizeTitle(), 5));
			if (!result) {
				accountSettingMsgList.add("Not able to close FontSize popup page.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			accountSettingMsgList.add("ChangeFontSizeIcon_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyShareAppIcon(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, accountSettingPageORObj.getShareAppIcon(), 10);
			if (!result) {
				accountSettingMsgList.add("Share app icon is not visible.");
				return result;
			}
			cfObj.commonClick(accountSettingPageORObj.getShareAppIcon());

			result = cfObj.commonWaitForElementToBeVisible(driver, accountSettingPageORObj.getShareAppWithFriendBtn(),
					10);
			if (!result) {
				accountSettingMsgList.add("ShareAppWithFriend button is not visible.");
				return result;
			}

			cfObj.commonClick(accountSettingPageORObj.getBackBtn());
			Thread.sleep(2000);

			result = !(cfObj.commonWaitForElementToBeVisible(driver, accountSettingPageORObj.getShareAppWithFriendBtn(),
					5));
			if (!result) {
				accountSettingMsgList.add("ShareAppWithFriends button is visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			accountSettingMsgList.add("ShareAppIcon_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyDotIcon(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, accountSettingPageORObj.getDotIcon(), 10);
			if (!result) {
				accountSettingMsgList.add("Dot icon is not visible.");
				return result;
			}
			cfObj.commonClick(accountSettingPageORObj.getDotIcon());

			result = cfObj.commonWaitForElementToBeVisible(driver, accountSettingPageORObj.getPrivacyPolicyIcon(), 10);
			if (!result) {
				accountSettingMsgList.add("Privacy policy icon is not visible.");
				return result;
			}

			cfObj.commonClick(accountSettingPageORObj.getPrivacyPolicyIcon());

			result = cfObj.commonWaitForElementToBeVisible(driver,
					accountSettingPageORObj.getTermAndPrivacyPolicyTitle(), 10);
			if (!result) {
				accountSettingMsgList.add("Privacy policy title is not visible.");
				return result;
			}

			cfObj.commonClick(accountSettingPageORObj.getBackBtn());
			Thread.sleep(2000);

			result = !(cfObj.commonWaitForElementToBeVisible(driver,
					accountSettingPageORObj.getTermAndPrivacyPolicyTitle(), 5));
			if (!result) {
				accountSettingMsgList.add("Privacy policy title is visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			accountSettingMsgList.add("DotIcon_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean navigateToUserProfilePage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			cfObj.commonClick(accountSettingPageORObj.getBackBtn());
			Thread.sleep(2000);
			result = !(cfObj.commonWaitForElementToBeVisible(driver, accountSettingPageORObj.getSettingPageTitle(),
					10));
			if (!result) {
				accountSettingMsgList.add("Setting page title is visible.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			accountSettingMsgList.add("UserProfileNavigation_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean validateAccountSettingPageTitle(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, accountSettingPageORObj.getSettingPageTitle(), 10);
			if (!result) {
				accountSettingMsgList.add("Account setting page title is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			accountSettingMsgList.add("ValidateAccountSettingPageTitle_Exception" + e.getMessage());
		}
		return result;
	}
}
