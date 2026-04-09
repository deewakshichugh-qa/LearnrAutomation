package applicationUtil;


import java.util.ArrayList;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import apiUtill.OtpUtil;
import org.testng.Assert;
import pageObject.Login_OR;
import pageObject.Profile_OR;
import util.Common_Function;
import util.ConfigFileReader;

public class ProfileUtil {

    private OtpUtil otpUtilObj;
    public ArrayList<String> profileMsgList = new ArrayList<>();
    public Common_Function cfObj = new Common_Function();
    public ConfigFileReader cfReaderObj = new ConfigFileReader();
    Profile_OR profilePageObj;
    LoginUtil loginUtilObj;

    public ProfileUtil(AppiumDriver<MobileElement> driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Appium driver is null! Check your driver initialization.");
        }
        this.profilePageObj = new Profile_OR(driver);
        this.loginUtilObj = new LoginUtil(driver);
    }

    public boolean verifyProfilePageElements(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            // Sign up and navigate to profile
            result = loginUtilObj.verifySignUp(driver);
            if (!result) {
                profileMsgList.add("Issue in sign in for profile test");
                return result;
            }
            result = cfObj.commonWaitForElementToBeVisible(driver, profilePageObj.getProfileIcon(), 30);
            if (!result) {
                profileMsgList.add("Profile icon is not visible on home");
                return result;
            }
            cfObj.commonClick(profilePageObj.getProfileIcon());

            // Verify paywall batch
            result = cfObj.commonWaitForElementToBeVisible(driver, profilePageObj.getPaywallBatch(), 30);
            if (!result) {
                profileMsgList.add("Paywall batch to join learnr pro is not visible on profile page");
                return result;
            }

            // Verify profile pic
            result = cfObj.commonWaitForElementToBeVisible(driver, profilePageObj.getProfilePic(), 20);
            if (!result) {
                profileMsgList.add("Profile pic is not visible");
                return result;
            }

            // Verify user name
            result = cfObj.commonWaitForElementToBeVisible(driver, profilePageObj.getUserName(), 10);
            if (!result) {
                profileMsgList.add("User name is not visible on profile page");
                return result;
            }

            // Verify user phone number
            result = cfObj.commonWaitForElementToBeVisible(driver, profilePageObj.getUserPhone(), 10);
            if (!result) {
                profileMsgList.add("User phone number is not visible on profile page");
                return result;
            }

            // Verify edit profile button
            result = cfObj.commonWaitForElementToBeVisible(driver, profilePageObj.getEditProfileBtn(), 10);
            if (!result) {
                profileMsgList.add("Edit profile button is not visible");
                return result;
            }

            // Verify change goal and click
            result = cfObj.commonWaitForElementToBeVisible(driver, profilePageObj.getChangeGoalBtn(), 10);
            if (!result) {
                profileMsgList.add("Change goal option is not visible");
                return result;
            }
            cfObj.commonClick(profilePageObj.getChangeGoalBtn());

            // Verify popup opened
            result = cfObj.commonWaitForElementToBeVisible(driver, profilePageObj.getChangeGoalPopup(), 10);
            if (!result) {
                profileMsgList.add("Change goal popup did not open");
                return result;
            }

            // Close popup
            cfObj.commonClick(profilePageObj.getChangeGoalPopupClose());

            // Verify help and support
            result = cfObj.commonWaitForElementToBeVisible(driver, profilePageObj.getHelpAndSupport(), 10);
            if (!result) {
                profileMsgList.add("Help and support is not visible");
                return result;
            }

            // Verify terms and conditions visible
            result = cfObj.commonWaitForElementToBeVisible(driver, profilePageObj.getTermsAndConditions(), 10);
            if (!result) {
                profileMsgList.add("Terms and conditions is not visible");
                return result;
            }

            // Click terms and conditions
            cfObj.commonClick(profilePageObj.getTermsAndConditions());

            // Verify terms page opened
            result = cfObj.commonWaitForElementToBeVisible(driver, profilePageObj.getTermsPageTitle(), 15);
            if (!result) {
                profileMsgList.add("Terms and conditions page did not open");
                return result;
            }

            // Verify back arrow and click
            result = cfObj.commonWaitForElementToBeVisible(driver, profilePageObj.getBackArrow(), 10);
            if (!result) {
                profileMsgList.add("Back arrow is not visible on terms page");
                return result;
            }
            cfObj.commonClick(profilePageObj.getBackArrow());

            // Verify privacy policy visible
            result = cfObj.commonWaitForElementToBeVisible(driver, profilePageObj.getPrivacyPolicy(), 10);
            if (!result) {
                profileMsgList.add("Privacy policy is not visible");
                return result;
            }

            // Click privacy policy
            cfObj.commonClick(profilePageObj.getPrivacyPolicy());

            // Verify privacy policy page opened
            result = cfObj.commonWaitForElementToBeVisible(driver, profilePageObj.getPrivacyPageTitle(), 15);
            if (!result) {
                profileMsgList.add("Privacy policy page did not open");
                return result;
            }

            // Verify back icon and click
            result = cfObj.commonWaitForElementToBeVisible(driver, profilePageObj.getBackIcon(), 10);
            if (!result) {
                profileMsgList.add("Back icon is not visible on privacy policy page");
                return result;
            }
            cfObj.commonClick(profilePageObj.getBackIcon());

            // Verify three dots menu
            result = cfObj.commonWaitForElementToBeVisible(driver, profilePageObj.getThreeDots(), 10);
            if (!result) {
                profileMsgList.add("Three dots menu is not visible");
                return result;
            }
            cfObj.commonClick(profilePageObj.getThreeDots());

            // Verify logout option
            result = cfObj.commonWaitForElementToBeVisible(driver, profilePageObj.getLogoutOption(), 10);
            if (!result) {
                profileMsgList.add("Logout option is not visible");
                return result;
            }

            // Verify delete account option
            result = cfObj.commonWaitForElementToBeVisible(driver, profilePageObj.getDeleteAccountOption(), 10);
            if (!result) {
                profileMsgList.add("Delete account option is not visible");
                return result;
            }

            // Verify cancel button and click
            result = cfObj.commonWaitForElementToBeVisible(driver, profilePageObj.getCancelBtn(), 10);
            if (!result) {
                profileMsgList.add("Cancel button is not visible");
                return result;
            }
            cfObj.commonClick(profilePageObj.getCancelBtn());

            // Verify profile back icon and click
            result = cfObj.commonWaitForElementToBeVisible(driver, profilePageObj.getProfileBackIcon(), 10);
            if (!result) {
                profileMsgList.add("Back icon on profile page is not visible");
                return result;
            }
            cfObj.commonClick(profilePageObj.getProfileBackIcon());

            // Verify home is opened
            result = cfObj.commonWaitForElementToBeVisible(driver, profilePageObj.getHomeScreen(), 15);
            if (!result) {
                profileMsgList.add("Home screen did not open after clicking back from profile");
                return result;
            }

        } catch (Exception e) {
            profileMsgList.add("verifyProfilePageElements_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }
}
