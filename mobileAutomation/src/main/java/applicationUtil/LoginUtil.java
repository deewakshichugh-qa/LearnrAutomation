package applicationUtil;

import java.util.ArrayList;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import apiUtill.OtpUtil;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import pageObject.Login_OR;
import util.Common_Function;
import util.ConfigFileReader;

public class LoginUtil {

    HomePageUtil homeUtilObj;
    OtpUtil otpUtilObj;

    public ArrayList<String> loginMsgList = new ArrayList<>();
    public Common_Function cfObj = new Common_Function();
    public ConfigFileReader cfReaderObj = new ConfigFileReader();
    private String strEmailId;

    Login_OR loginPageObj;

    public LoginUtil(AppiumDriver<MobileElement> driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Appium driver is null! Check your driver initialization.");
        }
        this.loginPageObj = new Login_OR(driver);
    }

    public boolean verifyLoginGuestUser(AppiumDriver<MobileElement> driver) {
        boolean result;
        try {
            result = loginBeforeCategorySelection(driver, cfReaderObj.getUserNamePassword().split("/")[0], cfReaderObj.getUserNamePassword().split("/")[1]);
            if (!result) {
                loginMsgList.add("Unable to login for credentials: " + cfReaderObj.getUserNamePassword());
                return result;
            }

            if (cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getLogoutBtn(), 8)) {

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Login Pending, Device Limit Reached\"]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Login Pending, Device Limit Reached text is not visible");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Your account can only be active on one app and web platform simultaneously. \"]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Your account can only be active on one app and web platform simultaneously. text is not visible");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"btn_appbar_back\"]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Back btn is not visible on logout popup");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Log out 1 device to continue\"]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Log out 1 device to continue text is not visible");
                    return result;
                }

                cfObj.commonClick(loginPageObj.getLogoutBtn());
            }

            // Allowing notification permission if coming
            if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"), 10)) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"));
            }

            result = selectYourCategoryExamLanguage(driver);
            if (!result) {
                loginMsgList.add("Unable to click select category, exam and language");
                return result;
            }

            // Allowing notification permission if coming
            if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"), 8)) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"));
            }

            if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "skip", "id", 5)) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "skip", "id"));
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.storeBtn(), 10);
            if (!result) {
                loginMsgList.add("Store button is not visible on bottom nav");
                return result;
            }

            cfObj.commonClick(loginPageObj.storeBtn());

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.storeBtn(), 10);
            if (!result) {
                loginMsgList.add("Store button is not visible on bottom nav");
                return result;
            }
        } catch (Exception e) {
            loginMsgList.add("verifyLoginGuestUser_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyLoginGuestUserForDeeplink(AppiumDriver<MobileElement> driver) {
        boolean result;
        try {

            result = loginBeforeCategorySelection(driver, cfReaderObj.getUserNamePassword().split("/")[0], cfReaderObj.getUserNamePassword().split("/")[1]);
            if (!result) {
                loginMsgList.add("Unable to login for credentials: " + cfReaderObj.getUserNamePassword());
                return result;
            }

            if (cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getLogoutBtn(), 8)) {

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Login Pending, Device Limit Reached\"]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Login Pending, Device Limit Reached text is not visible");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Your account can only be active on one app and web platform simultaneously. \"]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Your account can only be active on one app and web platform simultaneously. text is not visible");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"btn_appbar_back\"]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Back btn is not visible on logout popup");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Log out 1 device to continue\"]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Log out 1 device to continue text is not visible");
                    return result;
                }

                cfObj.commonClick(loginPageObj.getLogoutBtn());
            }

            // Allowing notification permission if coming
            if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"), 5)) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"));
            }

            result = selectYourCategoryExamLanguage(driver);
            if (!result) {
                loginMsgList.add("Unable to click select category, exam and language");
                return result;
            }
            Thread.sleep(1000);

        } catch (Exception e) {
            loginMsgList.add("verifyLoginGuestUserForDeeplink_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyLoginUsingEmailIdFromConfig(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        String strEmailId;
        String strUserPassword;
        boolean isProd = false;
        try {

            if (ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
                strEmailId = ConfigFileReader.prodStrUserEmailId;
                strUserPassword = ConfigFileReader.prodEmailMasterPassword;
                isProd = true;
            } else {
                strEmailId = ConfigFileReader.strUserEmailId;
                strUserPassword = ConfigFileReader.strUserPassword;
            }

            result = loginBeforeCategorySelection(driver, strEmailId, strUserPassword);
            if (!result) {
                loginMsgList.add("Unable to login for credentials: " + strEmailId);
                return result;
            }

            if(!isProd){
                if (cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getLogoutBtn(), 8)) {

                    result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Login Pending, Device Limit Reached\"]", "xpath", 10);
                    if (!result) {
                        loginMsgList.add("Login Pending, Device Limit Reached text is not visible");
                        return result;
                    }

                    result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Your account can only be active on one app and web platform simultaneously. \"]", "xpath", 10);
                    if (!result) {
                        loginMsgList.add("Your account can only be active on one app and web platform simultaneously. text is not visible");
                        return result;
                    }

                    result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"btn_appbar_back\"]", "xpath", 10);
                    if (!result) {
                        loginMsgList.add("Back btn is not visible on logout popup");
                        return result;
                    }

                    result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Log out 1 device to continue\"]", "xpath", 10);
                    if (!result) {
                        loginMsgList.add("Log out 1 device to continue text is not visible");
                        return result;
                    }

                    cfObj.commonClick(loginPageObj.getLogoutBtn());
                }
            }

            // Allowing notification permission if coming
            if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"), 8)) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"));
            }

            result = selectYourCategoryExamLanguage(driver);
            if (!result) {
                loginMsgList.add("Unable to click select category, exam and language");
                return result;
            }

            // Allowing notification permission if coming
            if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"), 8)) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"));
            }

            if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "skip", "id", 5)) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "skip", "id"));
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.storeBtn(), 10);
            if (!result) {

                try {
                    System.out.println("For now restarting the app when store not visible");
                    String appPackageName = ((AndroidDriver<MobileElement>) driver).getCurrentPackage();
                    driver.terminateApp(appPackageName);
                    Thread.sleep(1000);
                    driver.activateApp(appPackageName);
                } catch (Exception e) {
                    loginMsgList.add("Error restarting app: " + e.getMessage());
                    return false;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.storeBtn(), 10);
                if (!result) {
                    loginMsgList.add("Store button is visible on bottom nav");
                    return result;
                }
            }

            cfObj.commonClick(loginPageObj.storeBtn());
            cfObj.commonClick(loginPageObj.storeBtn());
            cfObj.commonClick(loginPageObj.storeBtn());

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.storeBtn(), 10);
            if (!result) {
                loginMsgList.add("Store button is visible on bottom nav after removing coach mark");
                return result;
            }
        } catch (Exception e) {
            loginMsgList.add("verifyLoginUsingEmailIdFromConfig_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyLoginUsingEmailIdFromConfig(AppiumDriver<MobileElement> driver, String strEmailId) {
        boolean result;
        String strUserPassword;
        boolean isProd = false;
        try {

            if (ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
                strUserPassword = ConfigFileReader.prodEmailMasterPassword;
                isProd = true;
            } else {
                strUserPassword = ConfigFileReader.strUserPassword;
            }

            result = loginBeforeCategorySelection(driver, strEmailId, strUserPassword);
            if (!result) {
                loginMsgList.add("Unable to login for credentials: " + strEmailId);
                return result;
            }

            if(!isProd){
                if (cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getLogoutBtn(), 8)) {

                    result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Login Pending, Device Limit Reached\"]", "xpath", 10);
                    if (!result) {
                        loginMsgList.add("Login Pending, Device Limit Reached text is not visible");
                        return result;
                    }

                    result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Your account can only be active on one app and web platform simultaneously. \"]", "xpath", 10);
                    if (!result) {
                        loginMsgList.add("Your account can only be active on one app and web platform simultaneously. text is not visible");
                        return result;
                    }

                    result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"btn_appbar_back\"]", "xpath", 10);
                    if (!result) {
                        loginMsgList.add("Back btn is not visible on logout popup");
                        return result;
                    }

                    result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Log out 1 device to continue\"]", "xpath", 10);
                    if (!result) {
                        loginMsgList.add("Log out 1 device to continue text is not visible");
                        return result;
                    }

                    cfObj.commonClick(loginPageObj.getLogoutBtn());
                }
            }

            // Allowing notification permission if coming
            if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"), 8)) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"));
            }

            result = selectYourCategoryExamLanguage(driver);
            if (!result) {
                loginMsgList.add("Unable to click select category, exam and language");
                return result;
            }

            // Allowing notification permission if coming
            if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"), 8)) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"));
            }

            if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "skip", "id", 5)) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "skip", "id"));
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.storeBtn(), 10);
            if (!result) {
                loginMsgList.add("Store button is visible on bottom nav");
                return result;
            }
            cfObj.commonClick(loginPageObj.storeBtn());
            cfObj.commonClick(loginPageObj.storeBtn());
            cfObj.commonClick(loginPageObj.storeBtn());

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.storeBtn(), 10);
            if (!result) {
                loginMsgList.add("Store button is visible on bottom nav after removing coach mark");
                return result;
            }
        } catch (Exception e) {
            loginMsgList.add("verifyLoginUsingEmailIdFromConfig_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyLoginUsingMobileNumber(AppiumDriver<MobileElement> driver, String mobileNumber) {
        boolean result = true;
        try {
            result = loginUsingNumberBeforeCategorySelection(driver, mobileNumber);
            if (!result) {
                loginMsgList.add("Unable to login for credentials: " + mobileNumber);
                return result;
            }

            if (cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getLogoutBtn(), 8)) {

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Login Pending, Device Limit Reached\"]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Login Pending, Device Limit Reached text is not visible");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Your account can only be active on one app and web platform simultaneously. \"]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Your account can only be active on one app and web platform simultaneously. text is not visible");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"btn_appbar_back\"]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Back btn is not visible on logout popup");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Log out 1 device to continue\"]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Log out 1 device to continue text is not visible");
                    return result;
                }

                cfObj.commonClick(loginPageObj.getLogoutBtn());
            }

            // Allowing notification permission if coming
            if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"), 10)) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"));
            }

            result = selectYourCategoryExamLanguage(driver);
            if (!result) {
                loginMsgList.add("Unable to click select category, exam and language");
                return result;
            }

            // Allowing notification permission if coming
            if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"), 8)) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"));
            }

            if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "skip", "id", 5)) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "skip", "id"));
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.storeBtn(), 10);
            if (!result) {
                loginMsgList.add("Store button is not visible on bottom nav");
                return result;
            }
            cfObj.commonClick(loginPageObj.storeBtn());

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.storeBtn(), 10);
            if (!result) {
                loginMsgList.add("Store button is not visible on bottom nav");
                return result;
            }
        } catch (Exception e) {
            loginMsgList.add("verifyLoginUsingMobileNumber_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyLoginUsingEmailId(AppiumDriver<MobileElement> driver, String strUsrEmail, String strUsrPassword, boolean isMasterOtp) {
        boolean result = true;
        try {

            result = loginBeforeCategorySelection(driver, strUsrEmail, strUsrPassword);
            if (!result) {
                loginMsgList.add("Unable to login for credentials: " + cfReaderObj.getUserNamePassword());
                return result;
            }

            if (!isMasterOtp) {
                if (cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getLogoutBtn(), 8)) {
                    cfObj.commonClick(loginPageObj.getLogoutBtn());
                }
            }

            // Allowing notification permission if coming
            if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"), 8)) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"));
            }

            result = selectYourCategoryExamLanguage(driver);
            if (!result) {
                loginMsgList.add("Unable to click select category, exam and language");
                return result;
            }

            // Allowing notification permission if coming
            if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"), 8)) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"));
            }

            if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "skip", "id", 5)) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "skip", "id"));
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.storeBtn(), 10);
            if (!result) {
                loginMsgList.add("Store button is not visible on bottom nav");
                return result;
            }
            cfObj.commonClick(loginPageObj.storeBtn());

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.storeBtn(), 10);
            if (!result) {
                loginMsgList.add("Store button is not visible on bottom nav");
                return result;
            }
        } catch (Exception e) {
            loginMsgList.add("verifyLoginUsingEmailId_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyLoginUsingEmailIdWithoutCategorySelection(AppiumDriver<MobileElement> driver, String strUsrEmail, String strUsrPassword) {
        boolean result = true;
        try {

            result = loginBeforeCategorySelection(driver, strUsrEmail, strUsrPassword);
            if (!result) {
                loginMsgList.add("Unable to login for credentials: " + cfReaderObj.getUserNamePassword());
                return result;
            }

            if (cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getLogoutBtn(), 8)) {
                cfObj.commonClick(loginPageObj.getLogoutBtn());
            }

            // Allowing notification permission if coming
            if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"), 8)) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"));
            }

            if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "skip", "id", 5)) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "skip", "id"));
            }
        } catch (Exception e) {
            loginMsgList.add("verifyLoginUsingEmailIdWithoutCategorySelection_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyNegativeLogin(AppiumDriver<MobileElement> driver) {
        boolean result;
        try {
            result = validateWelComePage(driver);
            if (!result) {
                loginMsgList.add("Failed to validate WelComePage.");
                return result;
            }

            result = clickOnEmailBtn(driver);
            if (!result) {
                loginMsgList.add("Failed to click Email btn.");
                return result;
            }

            // Back Button validation
            cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.ImageView[contains(@content-desc,'btn_appbar_back')]", "xpath"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc='Largest Learning']", "xpath", 30);
            if (result) {

                result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getInputBoxBeforeClickInWaterfallLogin(), 15);
                if (!result) {
                    loginMsgList.add("MobileNumber text field is not visible after coming back by back btn");
                    return result;
                }

            } else {
                result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getInputBoxBeforeClickInTeachersLogin(), 15);
                if (!result) {
                    loginMsgList.add("MobileNumber text field is not visible after coming back by back btn");
                    return result;
                }
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getContinueBtn(), 15);
            if (!result) {
                loginMsgList.add("Continue Btn is not visible.");
                return result;
            }

            result = clickOnEmailBtn(driver);
            if (!result) {
                loginMsgList.add("Failed to click Email btn.");
                return result;
            }

            // SignUp Now Link validation
            cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.View[@content-desc=\"sankalp_sign_up\n" + "Sign-Up Now\"]", "xpath"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc='Largest Learning']", "xpath", 30);
            if (result) {

                result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getInputBoxBeforeClickInWaterfallLogin(), 15);
                if (!result) {
                    loginMsgList.add("MobileNumber text field is not visible after coming back by back btn");
                    return result;
                }

            } else {
                result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getInputBoxBeforeClickInTeachersLogin(), 15);
                if (!result) {
                    loginMsgList.add("MobileNumber text field is not visible after coming back by back btn");
                    return result;
                }
            }

            result = clickOnEmailBtn(driver);
            if (!result) {
                loginMsgList.add("Failed to click Email btn.");
                return result;
            }

            // Verifying incorrect password
            result = enterEmailID(driver, "hamza.arif@adda247.com");
            if (!result) {
                loginMsgList.add("Unable to enter in email field");
                return result;
            }

            result = enterUserPassword(driver, "wrongPassword");
            if (!result) {
                loginMsgList.add("Unable to enter in password field");
                return result;
            }

            cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.ImageView[@content-desc=\"btn_login\n" + "btn_login\n" + "LOGIN\"]", "xpath"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"btn_login\n" + "btn_login\n" + "LOGIN\"]", "xpath", 20);
            if (!result) {
                loginMsgList.add("Able to login with wrong password");
                return result;
            }

            Thread.sleep(1500);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc='Incorrect current password, please retry']", "xpath", 20);
            if (!result) {
                loginMsgList.add("[Incorrect current password, please retry]- error message is not visible.");
                return result;
            }

            // verifying unregistered user
            cfObj.commonClick(loginPageObj.userNameEditBoxBtn());

            result = enterEmailID(driver, "unregistered.user@gmail.com");
            if (!result) {
                loginMsgList.add("Unable to enter in email field");
                return result;
            }

            cfObj.commonClick(loginPageObj.passwordEditBoxBtn());

            result = enterUserPassword(driver, "12345678");
            if (!result) {
                loginMsgList.add("Unable to enter in password field");
                return result;
            }

            Thread.sleep(1500);

            cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.ImageView[@content-desc=\"btn_login\n" + "btn_login\n" + "LOGIN\"]", "xpath"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"btn_login\n" + "btn_login\n" + "LOGIN\"]", "xpath", 20);
            if (!result) {
                loginMsgList.add("Able to login with unregistered user");
                return result;
            }
            Thread.sleep(1500);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc='User is not registered, register to continue']", "xpath", 20);
            if (!result) {
                loginMsgList.add("[User is not registered, register to continue]- error message is not visible.");
                return result;
            }

            result = enterEmailID(driver, "sandeep.agrahari@adda247.com");
            if (!result) {
                loginMsgList.add("Unable to enter in email field");
                return result;
            }
            result = enterUserPassword(driver, "amitpundir");
            if (!result) {
                loginMsgList.add("Unable to enter in password field");
                return result;
            }
            cfObj.commonClick(loginPageObj.logIn_ctn());

            // permission popup & logout
            if (cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getLogoutBtn(), 8)) {

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Login Pending, Device Limit Reached\"]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Login Pending, Device Limit Reached text is not visible");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Your account can only be active on one app and web platform simultaneously. \"]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Your account can only be active on one app and web platform simultaneously. text is not visible");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"btn_appbar_back\"]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Back btn is not visible on logout popup");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Log out 1 device to continue\"]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Log out 1 device to continue text is not visible");
                    return result;
                }

                cfObj.commonClick(loginPageObj.getLogoutBtn());
            }

            // Allowing notification permission if coming
            if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"), 8)) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"));
            }

            result = examCourseNegativeCases(driver);
            if (!result) {
                loginMsgList.add("Failed to validate examCourse.");
                return result;
            }

            // Allowing notification permission if coming
            if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"), 8)) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"));
            }

            if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "skip", "id", 5)) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "skip", "id"));
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.storeBtn(), 10);
            if (!result) {

                try {
                    System.out.println("For now restarting the app when store not visible");
                    String appPackageName = ((AndroidDriver<MobileElement>) driver).getCurrentPackage();
                    driver.terminateApp(appPackageName);
                    Thread.sleep(1000);
                    driver.activateApp(appPackageName);
                } catch (Exception e) {
                    loginMsgList.add("Error restarting app: " + e.getMessage());
                    return false;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.storeBtn(), 10);
                if (!result) {
                    loginMsgList.add("Store button is visible on bottom nav");
                    return result;
                }
            }

            cfObj.commonClick(loginPageObj.storeBtn());
            cfObj.commonClick(loginPageObj.storeBtn());
            cfObj.commonClick(loginPageObj.storeBtn());

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.storeBtn(), 10);
            if (!result) {
                loginMsgList.add("Store button is visible on bottom nav after removing coach mark");
                return result;
            }
        } catch (Exception e) {
            loginMsgList.add("verifyNegativeLogin_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean selectYourCategoryExamLanguage(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getSelectExamPageTitle(), 30);
            if (!result) {
                loginMsgList.add("Select your Exam You can switch between Exams later as well on selectYourCategoryExamLanguage");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[contains(@content-desc,'Search Exam or Course')]", "xpath", 20);
            if (!result) {
                loginMsgList.add("Search Exam or Course on selectYourCategoryExamLanguage");
                return result;
            }

            result = selectYourCategory(driver, cfReaderObj.getCategoryExamLang().split("/")[0]);
            if (!result) {
                loginMsgList.add("Unable to select" + cfReaderObj.getCategoryExamLang().split("/")[0] + "in category");
                return result;
            }

            result = selectYourExam(driver, cfReaderObj.getCategoryExamLang().split("/")[1]);
            if (!result) {
                loginMsgList.add("Unable to select" + cfReaderObj.getCategoryExamLang().split("/")[1] + "in exam type");
                return result;
            }

            result = selectYourLanguage(driver, cfReaderObj.getCategoryExamLang().split("/")[2]);
            if (!result) {
                loginMsgList.add("Unable to select" + cfReaderObj.getCategoryExamLang().split("/")[2] + "in language");
                return result;
            }
        } catch (Exception e) {
            loginMsgList.add("selectYourCategoryExamLanguage_Exception:" + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean selectYourCategory(AppiumDriver<MobileElement> driver, String category) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'exam_list_category_wrapper')]", "xpath", 10);
            if (!result) {
                loginMsgList.add("Unable to verify select category page");
                return result;
            }

            MobileElement element = driver.findElements(By.xpath("//*[contains(@content-desc,'exam_list_category_wrapper')]/android.view.View[contains(@content-desc, \"" + category + "\")]")).get(0);
            element.click();

        } catch (Exception e) {
            loginMsgList.add("selectYourCategory_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean selectYourExam(AppiumDriver<MobileElement> driver, String exam) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'exam_list_category')]", "xpath", 10);
            if(!result){
                loginMsgList.add("The exam listing is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[contains(@content-desc,'exam_list_category\n" + exam + "')]", "xpath", 10);
            if (result) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.ImageView[contains(@content-desc,'exam_list_category\n" + exam + "')]", "xpath"));
            } else {
                loginMsgList.add("The exam listing button is not visible");
                return result;
            }
        } catch (Exception e) {
            loginMsgList.add("selectYourExam_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean selectYourLanguage(AppiumDriver<MobileElement> driver, String language) {
        boolean result = true;
        try {

            MobileElement element = driver.findElement(By.xpath("//android.view.View[contains(@content-desc, '" + language + "')]"));
            element.click();

        } catch (Exception e) {
            loginMsgList.add("selectYourLanguage_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean loginUsingNumberBeforeCategorySelection(AppiumDriver<MobileElement> driver, String strMobileNumber) {
        boolean result = true;
        String strOtp = null;
        try {

            result = validateWelComePage(driver);
            if (!result) {
                loginMsgList.add("Failed to validate WelComePage.");
                return result;
            }

            result = enterMobileNumber(driver, strMobileNumber);
            if (!result) {
                loginMsgList.add("Unable to enter mobile number");
                return result;
            }
            cfObj.commonClick(loginPageObj.getContinueBtn());

            // Waiting for autofill otp
            Thread.sleep(10000);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.EditText[@text,'OTP Verification']", "xpath", 15);
            if (result) {

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"btn_login\n" + "btn_login\n" + "SUBMIT\"]", "xpath", 20);
                if (!result) {
                    loginMsgList.add("Submit otp button is not visible");
                    return result;
                }

                if (ConfigFileReader.strEnv.equalsIgnoreCase("production")) {

                    strOtp = ConfigFileReader.prodOtpMasterPassword;
                } else {
                    otpUtilObj = new OtpUtil();
                    strOtp = otpUtilObj.getOtpAdminEmailPhone(strMobileNumber, "phone");
                    if (strOtp == null) {
                        loginMsgList.add("OTP is null");
                        return false;
                    }
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@content-desc=\"btn_help\n" + "Help\"]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Help btn is not visible on otp page");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc='By continuing, you agree with our \n" + "']", "xpath", 10);
                if (!result) {
                    loginMsgList.add("By continuing, you agree with our' text is not visible.");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.termsOfUseText(), 10);
                if (!result) {
                    loginMsgList.add("Terms of use link is not visible.");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.privacyPolicyText(), 10);
                if (!result) {
                    loginMsgList.add("Privacy policy link is not visible.");
                    return result;
                }

                cfObj.commonClick(loginPageObj.termsOfUseText());

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Terms & Conditions\"]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Terms and Condition page is not visible.");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"btn_appbar_back\"]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Back btn in Terms and Condition page is not visible.");
                    return result;
                }

                driver.navigate().back();

                result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.termsOfUseText(), 10);
                if (!result) {
                    loginMsgList.add("Terms of use link is not visible.");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.privacyPolicyText(), 10);
                if (!result) {
                    loginMsgList.add("Privacy policy link is not visible.");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@content-desc=\"btn_help\n" + "Help\"]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Help btn is not visible on otp page");
                    return result;
                }

                cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.Button[@content-desc=\"btn_help\n" + "Help\"]", "xpath"));

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Help Section\"]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Help section bottomsheet is not visible");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"btn_update_phone_number\n" + "btn_update_phone_number\n" + "Update phone number\"]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Update phone number btn is not visible in help section bottomsheet");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"btn_contact_support\n" + "btn_contact_support\n" + "Contact Support\"]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Contact support btn is not visible in help section bottomsheet");
                    return result;
                }

                driver.navigate().back();

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.EditText[@text,'OTP Verification']", "xpath", 10);
                if (!result) {
                    loginMsgList.add("The OTP Verification text is not visible");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//android.widget.EditText[@text,'OTP Verification'])[2]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Otp text field is not visible.");
                    return result;
                }

                cfObj.commonGetElement(driver, "(//android.widget.EditText[@text,'OTP Verification'])[2]", "xpath").click();

                cfObj.commonGetElement(driver, "(//android.widget.EditText[@text,'OTP Verification'])[2]", "xpath").sendKeys(strOtp);

            } else {
                System.out.println("Auto filled the otp");
                result = true;
            }
        } catch (Exception e) {
            loginMsgList.add("loginUsingNumberBeforeCategorySelection_Exception:" + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean loginBeforeCategorySelection(AppiumDriver<MobileElement> driver, String strUsrEmail, String strUsrPassword) {
        boolean result = true;
        try {
            System.out.println("--> UserEmail: " + strUsrEmail + " & UserPassword: " + strUsrPassword);

            result = validateWelComePage(driver);
            if (!result) {
                loginMsgList.add("Failed to validate WelComePage.");
                return result;
            }

            result = clickOnEmailBtn(driver);
            if (!result) {
                loginMsgList.add("Failed to click Email btn.");
                return result;
            }

            result = enterEmailID(driver, strUsrEmail);
            if (!result) {
                loginMsgList.add("Failed to Enter Password.");
                return result;
            }

            result = enterUserPassword(driver, strUsrPassword);
            if (!result) {
                loginMsgList.add("Failed to Enter Password.");
                return result;
            }

            result = clickOnLoginBtn(driver);
            if (!result) {
                loginMsgList.add("Failed to click Login button.");
                return result;
            }
        } catch (Exception e) {
            loginMsgList.add("loginBeforeCategorySelection_Exception:" + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean clickEmailOrMobileBtn(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            Thread.sleep(10000);
            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getBtnEmailMobile(), 30);
            if (!result) {
                loginMsgList.add("Sign up/ login option page not opened");
                return result;
            }

            cfObj.commonClick(loginPageObj.getBtnEmailMobile());

            result = verifySignUpPage(driver);
            if (!result) {
                loginMsgList.add("Failed to verify signup page.");
            }

        } catch (Exception e) {
            loginMsgList.add("clickEmailOrMobileBtn_Exception:" + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean clickOnEmailBtn(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getBtnEmail(), 10);
            if (!result) {
                loginMsgList.add("Email button is not visible");
                return result;
            }

            cfObj.commonClick(loginPageObj.getBtnEmail());

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Continue with Email\"]", "xpath", 10);
            if (!result) {
                loginMsgList.add("Continue with Email text is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"btn_appbar_back\n" + "btn_back\"]", "xpath", 10);
            if (!result) {
                loginMsgList.add("Back btn in Continue with Email page is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Email\"]", "xpath", 10);
            if (!result) {
                loginMsgList.add("Email text in Continue with Email page is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.userNameEditBoxBtn(), 10);
            if (!result) {
                loginMsgList.add("Email id TextField is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Password\"]", "xpath", 10);
            if (!result) {
                loginMsgList.add("Password text in Continue with Email page is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.passwordEditBoxBtn(), 10);
            if (!result) {
                loginMsgList.add("Password TextField is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"btn_update\"]", "xpath", 10);
            if (!result) {
                loginMsgList.add("Password Protect icon is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"btn_login\n" + "btn_login\n" + "LOGIN\"]", "xpath", 10);
            if (!result) {
                loginMsgList.add("Login button is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"forgot_password\n" + "Forgot Password ?\"]", "xpath", 10);
            if (!result) {
                loginMsgList.add("Forgot Password link is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"New to Adda247?\"]", "xpath", 10);
            if (!result) {
                loginMsgList.add("New to Adda247 text on Continue with email page is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"sankalp_sign_up\n" + "Sign-Up Now\"]", "xpath", 10);
            if (!result) {
                loginMsgList.add("SignUp Now link is not visible");
                return result;
            }
        } catch (Exception e) {
            loginMsgList.add("clickOnEmailBtn_Exception:" + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean clickOnLoginBtn(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"btn_login\n" + "btn_login\n" + "LOGIN\"]", "xpath", 10);
            if (!result) {
                loginMsgList.add("Login button is not visible");
                return result;
            }

            cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.ImageView[@content-desc=\"btn_login\n" + "btn_login\n" + "LOGIN\"]", "xpath"));

            Thread.sleep(2000);

        } catch (Exception e) {
            loginMsgList.add("clickOnLoginBtn_Exception:" + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean enterUserPassword(AppiumDriver<MobileElement> driver, String userPwd) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.passwordEditBoxBtn(), 10);
            if (!result) {
                loginMsgList.add("Password TextField is not visible");
                return result;
            }

            cfObj.commonClick(loginPageObj.passwordEditBoxBtn());

            Thread.sleep(500);

            cfObj.commonSetTextTextBox(loginPageObj.passwordEditBoxBtn(), userPwd);

        } catch (Exception e) {
            loginMsgList.add("enterUserPassword_Exception:" + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean enterEmailID(AppiumDriver<MobileElement> driver, String userEmailId) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.userNameEditBoxBtn(), 10);
            if (!result) {
                loginMsgList.add("Email TextField is not visible");
                return result;
            }

            cfObj.commonClick(loginPageObj.userNameEditBoxBtn());

            Thread.sleep(500);

            result = cfObj.commonSetTextTextBox(loginPageObj.userNameEditBoxBtn(), userEmailId);
            if (!result) {
                loginMsgList.add("Failed to enter Email id.");
                return result;
            }
        } catch (Exception e) {
            loginMsgList.add("enterEmailID_Exception:" + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifySignUpPage(AppiumDriver<MobileElement> driver) {
        boolean result;
        try {

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getTitleSignUp(), 10);
            if (!result) {
                loginMsgList.add("Unable to verify Sign Up titie in Sign up page");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getListFieldText().get(0), 10);
            if (!result) {
                loginMsgList.add("Unable to verify Email or Mobile no field in signup page");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getBtnGenerateOTP(), 10);
            if (!result) {
                loginMsgList.add("Unable to verify generate OTP btn in signup page");
                return result;
            }

        } catch (Exception e) {
            result = false;
            loginMsgList.add("verifySignUpPage Exception:\n" + e.getMessage());
        }
        return result;
    }

    public boolean enterEmailOrMobileNumber(AppiumDriver<MobileElement> driver, String strUserEmail) {
        boolean result = true;
        try {

            if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
                result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getListFieldText().get(0), 10);
                if (!result) {
                    loginMsgList.add("The email or mobile number field is not visible");
                    return result;
                }

                result = cfObj.commonSetTextTextBox(loginPageObj.getListFieldText().get(0), strUserEmail);
                if (!result) {
                    Thread.sleep(2000);
                    result = cfObj.commonSetTextTextBox(loginPageObj.getListFieldText().get(0), strUserEmail);
                    if (!result) {
                        loginMsgList.add("Failed enter email id on email text box.");
                    }
                }
            } else {
                if (loginPageObj.getEnterMobileNumber().isEmpty()) {
                    loginMsgList.add("The email or mobile number field is not visible");
                    return false;
                }

                result = cfObj.commonSetTextTextBox(loginPageObj.getEnterMobileNumber().get(0), strUserEmail);
                if (!result) {
                    loginMsgList.add("not able to enter email id");
                    return result;
                }
            }

        } catch (Exception e) {
            loginMsgList.add("enterEmailOrMobileNumber_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifySuccessfulLogout(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getBtnSignInWithGoogle(), 10);
            if (!result) {
                loginMsgList.add("Unable to verify Continue with google btn");
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getBtnEmailMobile(), 10);
            if (!result) {
                loginMsgList.add("Unable to verify Email/Mobile  btn");
            }

            if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios")) {
                result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getBtnApple(), 10);
                if (!result) {
                    loginMsgList.add("Unable to verify Apple btn");
                }
            }
        } catch (Exception e) {
            loginMsgList.add("verifySuccessfullyLogout_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean navigateToChooseExamCategoryScreen(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getSelectExamPageTitle(), 30);
            if (!result) {
                loginMsgList.add("Select exam page title is not visible in navigateToChooseExamCategoryScreen.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"GOVERNMENT JOB EXAMS\"]", "xpath", 30);
            if (!result) {
                loginMsgList.add("GOVERNMENT JOB EXAMS :- Category list is not visible.");
                return result;
            }
        } catch (Exception e) {
            result = false;
            loginMsgList.add("navigateToChooseExamCategoryScreen_Exception " + e.getMessage());
        }
        return result;
    }

    public boolean getSelectExamType(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[contains(@content-desc,\"Banking\")]", "xpath", 30);
            if (!result) {
                loginMsgList.add("Banking :- Category list is not visible.");
                return result;
            }

            cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.ImageView[contains(@content-desc,\"Banking\")]", "xpath"));

        } catch (Exception e) {
            loginMsgList.add("navigateToChooseExamCategoryScreen_Exception " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifySignUpWithoutEmail(AppiumDriver<MobileElement> driver, String userPhoneNo) {
        boolean result = true;
        try {

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'Select your Category')]", "xpath", 10);
            if (result) {

                result = selectYourCategoryExamLanguage(driver);
                if (!result) {
                    loginMsgList.add("Unable to click select category, exam and language");
                    return result;
                }

                strEmailId = "addaAutomation" + Common_Function.randomPhoneNumber(10, "2") + "@gmail.com";

                result = signUpWithoutEmail(driver, userPhoneNo);
                if (!result) {
                    loginMsgList.add("Unable to signUpWithoutEmail");
                    return result;
                }
            } else {

                strEmailId = "addaAutomation" + Common_Function.randomPhoneNumber(10, "2") + "@gmail.com";

                result = signUpWithoutEmailBeforeCategorySelection(driver, userPhoneNo);
                if (!result) {
                    loginMsgList.add("Unable to signUp");
                    return result;
                }

                // Allowing notification permission if coming
                if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"), 5)) {
                    cfObj.commonClick(cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"));
                }

                result = selectYourCategoryExamLanguage(driver);
                if (!result) {
                    loginMsgList.add("Unable to click select category, exam and language");
                    return result;
                }

                // Allowing notification permission if coming
                if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"), 12)) {
                    cfObj.commonClick(cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"));
                }

                if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "skip", "id", 5)) {
                    cfObj.commonClick(cfObj.commonGetElement(driver, "skip", "id"));
                }

                homeUtilObj = new HomePageUtil(driver);
                result = homeUtilObj.clickHomeBtn(driver);
                if (!result) {
                    loginMsgList.addAll(homeUtilObj.homeMsgList);
                    return result;
                }

                cfObj.commonClick(homeUtilObj.homePageObj.getBtnMyContent());
                Thread.sleep(1000);
                cfObj.commonClick(homeUtilObj.homePageObj.getBtnMyContent());

                result = validateAccountRecoveryDetailsPopUp(driver, strEmailId);
                if (!result) {
                    loginMsgList.add("Failed to validate AccountRecoveryDetailsPopUp.");
                    return result;
                }

                result = homeUtilObj.JustOpenAndClickHomeBtn(driver);
                if (!result) {
                    loginMsgList.addAll(homeUtilObj.homeMsgList);
                    loginMsgList.add("Unable to verify homepage after login");
                }
            }
        } catch (Exception e) {
            loginMsgList.add("verifySignUpWithoutEmail_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifySignUp(AppiumDriver<MobileElement> driver, String userPhoneNo, boolean isAccountRecoveryEmail) {
        boolean result = true;
        homeUtilObj = new HomePageUtil(driver);
        try {
            strEmailId = "addaAutomation" + Common_Function.randomPhoneNumber(10, "2") + "@gmail.com";

            result = signUpBeforeSelectCategory(driver, userPhoneNo, strEmailId);
            if (!result) {
                loginMsgList.add("Unable to signUp");
                return result;
            }

            // Allowing notification permission if coming
            if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"), 8)) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"));
            }

            result = selectYourCategoryExamLanguage(driver);
            if (!result) {
                loginMsgList.add("Unable to click select category, exam and language");
                return result;
            }

            // Allowing notification permission if coming
            if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"), 8)) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"));
            }

            if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "skip", "id", 5)) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "skip", "id"));
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.storeBtn(), 10);
            if (!result) {
                    loginMsgList.add("Store button is visible on bottom nav");
                    return result;
            }
            cfObj.commonClick(loginPageObj.storeBtn());

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.storeBtn(), 10);
            if (!result) {
                try {
                    System.out.println("For now restarting the app when store not visible");
                    String appPackageName = ((AndroidDriver<MobileElement>) driver).getCurrentPackage();
                    driver.terminateApp(appPackageName);
                    Thread.sleep(1000);
                    driver.activateApp(appPackageName);
                } catch (Exception e) {
                    loginMsgList.add("Error restarting app: " + e.getMessage());
                    return false;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.storeBtn(), 10);
                if (!result) {
                    loginMsgList.add("Store button is visible on bottom nav");
                    return result;
                }
            }

            if(isAccountRecoveryEmail){
                cfObj.commonClick(homeUtilObj.homePageObj.getBtnMyContent());
                Thread.sleep(500);
                cfObj.commonClick(homeUtilObj.homePageObj.getBtnMyContent());
                Thread.sleep(500);
                cfObj.commonClick(homeUtilObj.homePageObj.getBtnMyContent());

                result = validateAccountRecoveryDetailsPopUp(driver, strEmailId);
                if (!result) {
                    loginMsgList.add("Failed to validate AccountRecoveryDetailsPopUp.");
                    return result;
                }
            }

            result = homeUtilObj.clickHomeBtn(driver);
            if (!result) {
                loginMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }
        } catch (Exception e) {
            loginMsgList.add("verifySignUp_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean signUpBeforeSelectCategory(AppiumDriver<MobileElement> driver, String strMobileNumber, String strEmailId) {
        boolean result = true;
        String strOtp = null;
        try {

            if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios")) {
                result = clickEmailOrMobileBtn(driver);
                if (!result) {
                    loginMsgList.add("Unable to click Email/Mobile btn");
                    return result;
                }

                result = enterEmailOrMobileNumber(driver, strMobileNumber);
                if (!result) {
                    loginMsgList.add("Unable to enter mobile number");
                    return result;
                }

                cfObj.commonClick(loginPageObj.getBtnGenerateOTP());

                result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getFieldOtp(), 10);
                if (!result) {
                    loginMsgList.add("Otp text field is not visible.");
                    return result;
                }

                // Exiting the test if the environment is production
                if (ConfigFileReader.strEnv.equals("PRODUCTION")) {
                    throw new Exception("Cannot run the complete test on production. Please change the environment to run the complete test");
                }

                otpUtilObj = new OtpUtil();
                strOtp = otpUtilObj.getOtp(strMobileNumber);
                if (strOtp == null) {
                    loginMsgList.add("OTP is null");
                    return false;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getFieldOtp(), 10);
                if (!result) {
                    loginMsgList.add("Otp text field is not visible.");
                    return result;
                }
                loginPageObj.getFieldOtp().sendKeys(strOtp);

                result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getFieldText().get(0), 10);
                if (!result) {
                    loginMsgList.add("getFieldText is not visible.");
                    return result;
                }

                // Click on signUp button
                // Enter username
                result = cfObj.commonSetTextTextBox(loginPageObj.getFieldText().get(0), "Automation Test");
                if (!result) {
                    loginMsgList.add("Failed to enter user name.");
                    return result;
                }

                // Enter email
                String email = "addaAutomation" + strMobileNumber + "@gmail.com";
                result = cfObj.commonSetTextTextBox(loginPageObj.getFieldText().get(1), email);
                if (!result) {
                    loginMsgList.add("Failed to enter Email id.");
                    return result;
                }
                System.out.println(email);
                Thread.sleep(1000);

                // Enter Password
                loginPageObj.getFieldText().get(2).sendKeys("002@aada!");
                Thread.sleep(500);
                if (ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {

                    if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
                        result = cfObj.commonVerifyValueTextBox(loginPageObj.getFieldText().get(2), "•••••••••");
                    } else {
                        result = cfObj.commonVerifyValueTextBox(loginPageObj.getFieldText().get(2), "���������", "value");
                    }
                    if (!result) {
                        loginMsgList.add("Failed to enter Password.");
                        return result;
                    }
                }
                System.out.println("Password: - 002@aada!");
                Thread.sleep(1000);

                // Confirm Password
                loginPageObj.getFieldText().get(3).sendKeys("002@aada!");
                Thread.sleep(500);
                if (ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {
                    if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
                        result = cfObj.commonVerifyValueTextBox(loginPageObj.getFieldText().get(3), "•••••••••");
                    } else {
                        result = cfObj.commonVerifyValueTextBox(loginPageObj.getFieldText().get(3), "���������", "value");
                    }
                    if (!result) {
                        loginMsgList.add("Failed to enter Confirm Password.");
                        return result;
                    }
                }

                // Click on signUp button
                cfObj.commonClick(loginPageObj.getBtnSignUp());

                homeUtilObj = new HomePageUtil(driver);

                result = homeUtilObj.clickHomeBtn(driver);
                if (!result) {
                    loginMsgList.addAll(homeUtilObj.homeMsgList);
                    loginMsgList.add("Unable to verify homepage after login");
                }
            } else {

                result = validateWelComePage(driver);
                if (!result) {
                    loginMsgList.add("Failed to validate WelComePage.");
                    return result;
                }

                result = enterMobileNumber(driver, strMobileNumber);
                if (!result) {
                    loginMsgList.add("Unable to enter mobile number");
                    return result;
                }

                cfObj.commonClick(loginPageObj.getContinueBtn());

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@content-desc=\"btn_help\n" + "Help\"]", "xpath", 40);
                if (!result) {
                    loginMsgList.add("Help btn is not visible on otp page, might be taking more time to load number");
                    return result;
                }

                otpUtilObj = new OtpUtil();
                strOtp = otpUtilObj.getOtpAdminEmailPhone(strMobileNumber, "phone");
                if (strOtp == null) {
                    loginMsgList.add("OTP is null");
                    return false;
                }

                if (!ConfigFileReader.strRunMode.equalsIgnoreCase("localLab")) {
                    driver.hideKeyboard();
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc='By continuing, you agree with our \n" + "']", "xpath", 10);
                if (!result) {
                    loginMsgList.add("By continuing, you agree with our' text is not visible.");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.termsOfUseText(), 10);
                if (!result) {
                    loginMsgList.add("Terms of use link is not visible.");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.privacyPolicyText(), 10);
                if (!result) {
                    loginMsgList.add("Privacy policy link is not visible.");
                    return result;
                }

                cfObj.commonClick(loginPageObj.termsOfUseText());

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Terms & Conditions\"]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Terms and Condition page is not visible.");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"btn_appbar_back\"]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Back btn in Terms and Condition page is not visible.");
                    return result;
                }

                driver.navigate().back();

                result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.termsOfUseText(), 10);
                if (!result) {
                    loginMsgList.add("Terms of use link is not visible.");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.privacyPolicyText(), 10);
                if (!result) {
                    loginMsgList.add("Privacy policy link is not visible.");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@content-desc=\"btn_help\n" + "Help\"]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Help btn is not visible on otp page");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.EditText[@text,'OTP Verification']", "xpath", 10);
                if (!result) {
                    loginMsgList.add("The OTP Verification text is not visible");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"btn_login\n" + "btn_login\n" + "SUBMIT\"]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Submit otp button is not visible");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@content-desc=\"btn_help\n" + "Help\"]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Help btn is not visible on otp page");
                    return result;
                }

                cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.Button[@content-desc=\"btn_help\n" + "Help\"]", "xpath"));

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Help Section\"]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Help section bottom sheet is not visible");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"btn_update_phone_number\n" + "btn_update_phone_number\n" + "Update phone number\"]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Update phone number btn is not visible in help section bottom sheet");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"btn_contact_support\n" + "btn_contact_support\n" + "Contact Support\"]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Contact support btn is not visible in help section bottom sheet");
                    return result;
                }

                driver.navigate().back();

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.EditText[@text,'OTP Verification']", "xpath", 10);
                if (!result) {
                    loginMsgList.add("The OTP Verification text is not visible");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//android.widget.EditText[@text,'OTP Verification'])[2]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Otp text field is not visible.");
                    return result;
                }

                cfObj.commonGetElement(driver, "(//android.widget.EditText[@text,'OTP Verification'])[2]", "xpath").click();

                cfObj.commonGetElement(driver, "(//android.widget.EditText[@text,'OTP Verification'])[2]", "xpath").sendKeys(strOtp);

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc='Almost there']", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Almost There title is not visible, after sign up");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "android.widget.EditText", "class", 10);
                if (!result) {
                    loginMsgList.add("FullName text field is not visible.");
                    return result;
                }

                result = cfObj.commonSetTextTextBox(cfObj.commonGetElement(driver, "android.widget.EditText", "class"), "Automation User");
                if (!result) {
                    loginMsgList.add("Failed to enter Full name on FullName TextField.");
                    return result;
                }

                if (!ConfigFileReader.strRunMode.equalsIgnoreCase("localLab")) {
                    driver.hideKeyboard();
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.confirmOrContinueBtn(), 10);
                if (!result) {
                    loginMsgList.add("Confirm button is not visible.");
                    return result;
                }
                cfObj.commonClick(loginPageObj.confirmOrContinueBtn());

                Thread.sleep(3000);

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc='Almost there']", "xpath", 4);
                if (result) {
                    result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.confirmOrContinueBtn(), 4);
                    if (result) {
                        cfObj.commonClick(loginPageObj.confirmOrContinueBtn());
                    } else {
                        System.out.println("Might be went to select category page");
                        result = true;
                    }
                } else {
                    result = true;
                }
            }
        } catch (Exception e) {
            loginMsgList.add("signUpBeforeSelectCategory_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean signUpWithoutEmail(AppiumDriver<MobileElement> driver, String strMobileNumber) {
        boolean result = true;
        String strOtp = null;
        try {

            if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios")) {
                result = clickEmailOrMobileBtn(driver);
                if (!result) {
                    loginMsgList.add("Unable to click Email/Mobile btn");
                    return result;
                }

                result = enterEmailOrMobileNumber(driver, strMobileNumber);
                if (!result) {
                    loginMsgList.add("Unable to enter mobile number");
                    return result;
                }

                cfObj.commonClick(loginPageObj.getBtnGenerateOTP());

                // Exiting the test if the environment is production
                if (ConfigFileReader.strEnv.equals("PRODUCTION")) {
                    throw new Exception("Cannot run the complete test on production. Please change the environment to run the complete test");
                }

                otpUtilObj = new OtpUtil();

                strOtp = otpUtilObj.getOtp(strMobileNumber);
                if (strOtp == null) {
                    loginMsgList.add("OTP is null");
                    return false;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@name,'Enter OTP')]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("The Enter OTP box didn't appeared");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getFieldOtp(), 10);
                if (!result) {
                    loginMsgList.add("Otp text field is not visible.");
                    return result;
                }
                loginPageObj.getFieldOtp().sendKeys(strOtp);

                result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getFieldText().get(0), 10);
                if (!result) {
                    loginMsgList.add("getFieldText is not visible.");
                    return result;
                }

                // Click on signUp button
                // Enter user name
                result = cfObj.commonSetTextTextBox(loginPageObj.getFieldText().get(0), "Automation Test");
                if (!result) {
                    loginMsgList.add("Failed to enter user name.");
                    return result;
                }

                // Enter email
                String email = "addaAutomation" + strMobileNumber + "@gmail.com";
                result = cfObj.commonSetTextTextBox(loginPageObj.getFieldText().get(1), email);
                if (!result) {
                    loginMsgList.add("Failed to enter Email id.");
                    return result;
                }
                System.out.println(email);
                Thread.sleep(1000);
                // Enter Password
                loginPageObj.getFieldText().get(2).sendKeys("002@aada!");
                Thread.sleep(500);
                if (ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {

                    if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
                        result = cfObj.commonVerifyValueTextBox(loginPageObj.getFieldText().get(2), "•••••••••");
                    } else {
                        result = cfObj.commonVerifyValueTextBox(loginPageObj.getFieldText().get(2), "���������", "value");
                    }
                    if (!result) {
                        loginMsgList.add("Failed to enter Password.");
                        return result;
                    }
                }
                System.out.println("Password: - 002@aada!");
                // Confirm Password
                loginPageObj.getFieldText().get(3).sendKeys("002@aada!");
                Thread.sleep(500);
                if (ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {
                    if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
                        result = cfObj.commonVerifyValueTextBox(loginPageObj.getFieldText().get(3), "•••••••••");
                    } else {
                        result = cfObj.commonVerifyValueTextBox(loginPageObj.getFieldText().get(3), "���������", "value");
                    }
                    if (!result) {
                        loginMsgList.add("Failed to enter Confirm Password.");
                        return result;
                    }
                }
                // Click on signUp button
                cfObj.commonClick(loginPageObj.getBtnSignUp());

                homeUtilObj = new HomePageUtil(driver);

                result = homeUtilObj.clickHomeBtn(driver);
                if (!result) {
                    loginMsgList.addAll(homeUtilObj.homeMsgList);
                    loginMsgList.add("Unable to verify homepage after login");
                }

            } else {

                result = validateWelComePage(driver);
                if (!result) {
                    loginMsgList.add("Failed to validate WelComePage.");
                    return result;
                }

                result = enterMobileNumber(driver, strMobileNumber);
                if (!result) {
                    loginMsgList.add("Unable to enter mobile number");
                    return result;
                }

                cfObj.commonClick(loginPageObj.getContinueBtn());

                // Exiting the test if the environment is production
                if (ConfigFileReader.strEnv.equals("PRODUCTION")) {
                    throw new Exception("Cannot run the complete test on production. Please change the environment to run the complete test");
                }

                otpUtilObj = new OtpUtil();

                strOtp = otpUtilObj.getOtpAdminEmailPhone(strMobileNumber, "phone");
                if (strOtp == null) {
                    loginMsgList.add("OTP is null");
                    return false;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.EditText[@text,'OTP Verification']", "xpath", 10);
                if (!result) {
                    loginMsgList.add("The Enter OTP box didn't appeared");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//android.widget.EditText[@text,'OTP Verification'])[2]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Otp text field is not visible.");
                    return result;
                }
                cfObj.commonGetElement(driver, "(//android.widget.EditText[@text,'OTP Verification'])[2]", "xpath").click();
                cfObj.commonGetElement(driver, "(//android.widget.EditText[@text,'OTP Verification'])[2]", "xpath").sendKeys(strOtp);

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc='Almost there']", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Almost There title is not visible, after sign up");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "android.widget.EditText", "class", 10);
                if (!result) {
                    loginMsgList.add("FullName text field is not visible.");
                    return result;
                }

                result = cfObj.commonSetTextTextBox(cfObj.commonGetElement(driver, "android.widget.EditText", "class"), "Automation User");
                if (!result) {
                    loginMsgList.add("Failed to enter Full name on FullName TextField.");
                    return result;
                }

                if (!ConfigFileReader.strRunMode.equalsIgnoreCase("localLab")) {
                    driver.hideKeyboard();
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.confirmOrContinueBtn(), 10);
                if (!result) {
                    loginMsgList.add("Confirm button is not visible.");
                    return result;
                }

                cfObj.commonClick(loginPageObj.confirmOrContinueBtn());

                result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.storeBtn(), 10);
                if (!result) {
                    loginMsgList.add("Store button is not visible on bottom nav");
                    return result;
                }

                cfObj.commonClick(loginPageObj.storeBtn());

                result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.storeBtn(), 10);
                if (!result) {
                    loginMsgList.add("Store button is not visible on bottom nav");
                    return result;
                }
            }
        } catch (Exception e) {
            loginMsgList.add("signUpWithoutEmail_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean signUpWithoutEmailBeforeCategorySelection(AppiumDriver<MobileElement> driver, String strMobileNumber) {
        boolean result = true;
        String strOtp = null;
        try {

            if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios")) {
                result = clickEmailOrMobileBtn(driver);
                if (!result) {
                    loginMsgList.add("Unable to click Email/Mobile btn");
                    return result;
                }

                result = enterEmailOrMobileNumber(driver, strMobileNumber);
                if (!result) {
                    loginMsgList.add("Unable to enter mobile number");
                    return result;
                }

                cfObj.commonClick(loginPageObj.getBtnGenerateOTP());

                // Exiting the test if the environment is production
                if (ConfigFileReader.strEnv.equals("PRODUCTION")) {
                    result = false;
                    throw new Exception("Cannot run the complete test on production. Please change the environment to run the complete test");
                }

                otpUtilObj = new OtpUtil();

                strOtp = otpUtilObj.getOtp(strMobileNumber);
                if (strOtp == null) {
                    loginMsgList.add("OTP is null");
                    return false;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@name,'Enter OTP')]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("The Enter OTP box didn't appeared");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getFieldOtp(), 10);
                if (!result) {
                    loginMsgList.add("Otp text field is not visible.");
                    return result;
                }
                loginPageObj.getFieldOtp().sendKeys(strOtp);

                result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getFieldText().get(0), 10);
                if (!result) {
                    loginMsgList.add("getFieldText is not visible.");
                    return result;
                }

                // Click on signUp button
                // Enter user name
                result = cfObj.commonSetTextTextBox(loginPageObj.getFieldText().get(0), "Automation Test");
                if (!result) {
                    loginMsgList.add("Failed to enter user name.");
                    return result;
                }

                // Enter email
                String email = "addaAutomation" + strMobileNumber + "@gmail.com";
                result = cfObj.commonSetTextTextBox(loginPageObj.getFieldText().get(1), email);
                if (!result) {
                    loginMsgList.add("Failed to enter Email id.");
                    return result;
                }
                System.out.println(email);
                Thread.sleep(1000);
                // Enter Password
                loginPageObj.getFieldText().get(2).sendKeys("002@aada!");
                Thread.sleep(500);
                if (ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {

                    if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
                        result = cfObj.commonVerifyValueTextBox(loginPageObj.getFieldText().get(2), "•••••••••");
                    } else {
                        result = cfObj.commonVerifyValueTextBox(loginPageObj.getFieldText().get(2), "���������", "value");
                    }
                    if (!result) {
                        loginMsgList.add("Failed to enter Password.");
                        return result;
                    }
                }
                System.out.println("Password: - 002@aada!");
                // Confirm Password
                loginPageObj.getFieldText().get(3).sendKeys("002@aada!");
                Thread.sleep(500);
                if (ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {
                    if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
                        result = cfObj.commonVerifyValueTextBox(loginPageObj.getFieldText().get(3), "•••••••••");
                    } else {
                        result = cfObj.commonVerifyValueTextBox(loginPageObj.getFieldText().get(3), "���������", "value");
                    }
                    if (!result) {
                        loginMsgList.add("Failed to enter Confirm Password.");
                        return result;
                    }
                }
                // Click on signUp button
                cfObj.commonClick(loginPageObj.getBtnSignUp());

                homeUtilObj = new HomePageUtil(driver);

                result = homeUtilObj.clickHomeBtn(driver);
                if (!result) {
                    loginMsgList.addAll(homeUtilObj.homeMsgList);
                    loginMsgList.add("Unable to verify homepage after login");
                }
            } else {

                result = validateWelComePage(driver);
                if (!result) {
                    loginMsgList.add("Failed to validate WelComePage.");
                    return result;
                }

                result = enterMobileNumber(driver, strMobileNumber);
                if (!result) {
                    loginMsgList.add("Unable to enter mobile number");
                    return result;
                }

                cfObj.commonClick(loginPageObj.getContinueBtn());

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.EditText[@text,'OTP Verification']", "xpath", 30);
                if (!result) {
                    loginMsgList.add("The Enter OTP box didn't appeared");
                    return result;
                }

                otpUtilObj = new OtpUtil();
                strOtp = otpUtilObj.getOtpAdminEmailPhone(strMobileNumber, "phone");
                if (strOtp == null) {
                    loginMsgList.add("OTP is null");
                    return false;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//android.widget.EditText[@text,'OTP Verification'])[2]", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Otp text field is not visible.");
                    return result;
                }

                cfObj.commonGetElement(driver, "(//android.widget.EditText[@text,'OTP Verification'])[2]", "xpath").click();
                cfObj.commonGetElement(driver, "(//android.widget.EditText[@text,'OTP Verification'])[2]", "xpath").sendKeys(strOtp);

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc='Almost there']", "xpath", 10);
                if (!result) {
                    loginMsgList.add("Almost There title is not visible, after sign up");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "android.widget.EditText", "class", 10);
                if (!result) {
                    loginMsgList.add("FullName textfield is not visible.");
                    return result;
                }

                result = cfObj.commonSetTextTextBox(cfObj.commonGetElement(driver, "android.widget.EditText", "class"), "Automation User");
                if (!result) {
                    loginMsgList.add("Failed to enter Full name on FullName TextField.");
                    return result;
                }

                if (!ConfigFileReader.strRunMode.equalsIgnoreCase("localLab")) {
                    driver.hideKeyboard();
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.confirmOrContinueBtn(), 10);
                if (!result) {
                    loginMsgList.add("Confirm button is not visible.");
                    return result;
                }

                cfObj.commonClick(loginPageObj.confirmOrContinueBtn());
            }
        } catch (Exception e) {
            loginMsgList.add("signUpWithoutEmailBeforeCategorySelection_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean signUpWithoutWelcome(AppiumDriver<MobileElement> driver, String strMobileNumber, String strEmailId) {
        boolean result = true;
        String strOtp = null;
        try {

            result = enterMobileNumber(driver, strMobileNumber);
            if (!result) {
                loginMsgList.add("Unable to enter mobile number");
                return result;
            }

            cfObj.commonClick(loginPageObj.getContinueBtn());

            // Exiting the test if the environment is production
            if (ConfigFileReader.strEnv.equals("PRODUCTION")) {
                throw new Exception("Cannot run the complete test on production. Please change the environment to run the complete test");
            }

            otpUtilObj = new OtpUtil();

            strOtp = otpUtilObj.getOtpAdminEmailPhone(strMobileNumber, "phone");
            if (strOtp == null) {
                loginMsgList.add("OTP is null");
                return false;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.EditText[@text,'OTP Verification']", "xpath", 10);
            if (!result) {
                loginMsgList.add("The Enter OTP box didn't appeared");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//android.widget.EditText[@text,'OTP Verification'])[2]", "xpath", 10);
            if (!result) {
                loginMsgList.add("Otp text field is not visible.");
                return result;
            }
            cfObj.commonGetElement(driver, "(//android.widget.EditText[@text,'OTP Verification'])[2]", "xpath").click();
            cfObj.commonGetElement(driver, "(//android.widget.EditText[@text,'OTP Verification'])[2]", "xpath").sendKeys(strOtp);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc='Almost there']", "xpath", 10);
            if (!result) {
                loginMsgList.add("Almost There title is not visible, after sign up");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "android.widget.EditText", "class", 10);
            if (!result) {
                loginMsgList.add("FullName text field is not visible.");
                return result;
            }

            result = cfObj.commonSetTextTextBox(cfObj.commonGetElement(driver, "android.widget.EditText", "class"), "Automation User");
            if (!result) {
                loginMsgList.add("Failed to enter Full name on FullName TextField.");
                return result;
            }

            if (!ConfigFileReader.strRunMode.equalsIgnoreCase("localLab")) {
                driver.hideKeyboard();
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.confirmOrContinueBtn(), 10);
            if (!result) {
                loginMsgList.add("Confirm button is not visible.");
                return result;
            }

            cfObj.commonClick(loginPageObj.confirmOrContinueBtn());

            if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "skip", "id", 5)) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "skip", "id"));
            }

            HomePageUtil homeUtilObj = new HomePageUtil(driver);
            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.storeBtn(), 10);
            if (!result) {
                loginMsgList.add("Store button is not visible on bottom nav");
                return result;
            }

            cfObj.commonClick(loginPageObj.storeBtn());

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.storeBtn(), 10);
            if (!result) {
                loginMsgList.add("Store button is not visible on bottom nav");
                return result;
            }

            cfObj.commonClick(homeUtilObj.homePageObj.getBtnMyContent());
            Thread.sleep(500);
            cfObj.commonClick(homeUtilObj.homePageObj.getBtnMyContent());

            result = validateAccountRecoveryDetailsPopUp(driver, strEmailId);
            if (!result) {
                loginMsgList.add("Failed to validate AccountRecoveryDetailsPopUp.");
                return result;
            }

            result = homeUtilObj.JustOpenAndClickHomeBtn(driver);
            if (!result) {
                loginMsgList.addAll(homeUtilObj.homeMsgList);
                loginMsgList.add("Unable to verify homepage after login");
            }

        } catch (Exception e) {
            loginMsgList.add("signUpWithoutWelcome_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean validateWelComePage(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            boolean isInstalled = driver.isAppInstalled("com.truecaller");
            if (isInstalled) {
                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'USE ANOTHER MOBILE NUMBER')]", "xpath", 20);
                if (result) {
                    cfObj.commonClick(cfObj.commonGetElement(driver, "//*[contains(@text,'USE ANOTHER MOBILE NUMBER')]", "xpath"));
                } else {
                    System.out.println("True caller installed but not visible");
                }
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"Cancel\"]", "xpath", 5);
            if (result) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.ImageView[@content-desc=\"Cancel\"]", "xpath"));
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Destination\"]", "xpath", 20);
            if (!result) {
                loginMsgList.add("Destination text is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Log in/Sign up\"]", "xpath", 20);
            if (!result) {
                loginMsgList.add("Log in/Sign up text is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Mobile Number\"]", "xpath", 20);
            if (!result) {
                loginMsgList.add("MobileNumber text field is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getInputBoxBeforeClickInWaterfallLogin(), 10);
            if (!result) {
                loginMsgList.add("Number edit box field is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Or Log in\"]", "xpath", 20);
            if (!result) {
                loginMsgList.add("Or Log in text is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getContinueBtn(), 15);
            if (!result) {
                loginMsgList.add("Continue Btn is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[contains(@content-desc,'Google')]", "xpath", 10);
            if (!result) {
                loginMsgList.add("Google icon Btn is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getBtnEmail(), 10);
            if (!result) {
                loginMsgList.add("Email icon Btn is not visible.");
                return result;
            }
        } catch (Exception e) {
            loginMsgList.add("validateWelComePage_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean enterMobileNumber(AppiumDriver<MobileElement> driver, String strMobileNumber) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc='Largest Learning']", "xpath", 6);
            if (result) {

                result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getInputBoxBeforeClickInWaterfallLogin(), 10);
                if (!result) {
                    loginMsgList.add("Number edit box, before click is not visible.");
                    return result;
                }
                cfObj.commonClick(loginPageObj.getInputBoxBeforeClickInWaterfallLogin());

                loginPageObj.getInputBoxAfterClickInWaterfallLogin().sendKeys(strMobileNumber);

                result = cfObj.commonVerifyValueTextBox(loginPageObj.getInputBoxAfterClickInWaterfallLogin(), strMobileNumber);
                if (!result) {
                    loginMsgList.add("Failed enter Mobile number on MobileNumber TextField.");
                    return result;
                }

                if (!ConfigFileReader.strRunMode.equalsIgnoreCase("localLab")) {
                    driver.hideKeyboard();
                }

            } else {
                result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getInputBoxBeforeClickInTeachersLogin(), 10);
                if (!result) {
                    loginMsgList.add("MobileNumber text field is not visible.");
                    return result;
                }

                cfObj.commonClick(loginPageObj.getInputBoxBeforeClickInTeachersLogin());

                if (!ConfigFileReader.strRunMode.equalsIgnoreCase("localLab")) {

                    loginPageObj.getInputBoxAfterClickInTeachersLogin().sendKeys(strMobileNumber);

                    result = cfObj.commonVerifyValueTextBox(loginPageObj.getInputBoxAfterClickInTeachersLogin(), strMobileNumber);
                    if (!result) {
                        loginMsgList.add("Failed enter Mobile number on MobileNumber TextField.");
                        return result;
                    }
                    driver.hideKeyboard();

                } else {
                    cfObj.commonGetElement(driver, "//android.view.View[@content-desc=\"93K + Government Job Selections in 2024\n" + "Log in/Sign up\n" + "Mobile Number\n" + "Or Log in\"]/android.widget.EditText/android.widget.EditText", "xpath").sendKeys(strMobileNumber);

                    result = cfObj.commonVerifyValueTextBox(cfObj.commonGetElement(driver, "//android.view.View[@content-desc=\"93K + Government Job Selections in 2024\n" + "Log in/Sign up\n" + "Mobile Number\n" + "Or Log in\"]/android.widget.EditText/android.widget.EditText", "xpath"), strMobileNumber);
                    if (!result) {
                        loginMsgList.add("Failed enter Mobile number on MobileNumber TextField.");
                        result = true;
                    }
                }
            }
        } catch (Exception e) {
            loginMsgList.add("enterMobileNumber_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean validateAccountRecoveryDetailsPopUp(AppiumDriver<MobileElement> driver, String strEmailId) {
        boolean result = true;
        String strOtp;
        try {
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc='Account Recovery Details']", "xpath", 10);
            if (!result) {

                homeUtilObj = new HomePageUtil(driver);
                result = cfObj.commonWaitForElementToBeVisible(driver, homeUtilObj.homePageObj.getBtnMyContent(), 10);
                if (result) {
                    cfObj.commonClick(homeUtilObj.homePageObj.getBtnMyContent());
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc='Account Recovery Details']", "xpath", 20);
                if (!result) {
                    loginMsgList.add("Account Recovery Details header is not visible.");
                    return result;
                }
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc='btn_bottom_sheet_cancel']", "xpath", 10);
            if (!result) {
                loginMsgList.add("Account Recovery Details PopUp Close btn is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.EditText", "xpath", 10);
            if (!result) {
                loginMsgList.add("EmailId Text field is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.confirmOrContinueBtn(), 10);
            if (!result) {
                loginMsgList.add("Send OTP button is not visible.");
                return result;
            }

            // To enter email id
            result = cfObj.commonSetTextTextBox(cfObj.commonGetElement(driver, "//android.widget.EditText", "xpath"), strEmailId);
            if (!result) {
                loginMsgList.add("Failed to enter EmailId on emailID textField.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc, 'btn_confirm')]", "xpath", 10);
            if (!result) {
                loginMsgList.add("Continue btn is not visible");
                return result;
            }

            cfObj.commonClick(loginPageObj.confirmOrContinueBtn());

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc='OTP Verification']", "xpath", 30);
            if (!result) {

                result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.confirmOrContinueBtn(), 10);
                if (!result) {
                    loginMsgList.add("Continue btn is not visible, after Failed.");
                    return result;
                }
                cfObj.commonClick(loginPageObj.confirmOrContinueBtn());

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc='OTP Verification']", "xpath", 30);
                if (!result) {
                    loginMsgList.add("The Enter OTP box didn't appeared in email account recovery, after again failing and retrying.");
                    return result;
                }
            }

            otpUtilObj = new OtpUtil();
            strOtp = otpUtilObj.getOtpAdminEmailPhone(strEmailId, "Email");
            if (strOtp == null) {
                loginMsgList.add("OTP is null");
                return false;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc='OTP Verification']/following-sibling::android.widget.EditText", "xpath", 15);
            if (!result) {
                loginMsgList.add("Otp text field is not visible.");
                return result;
            }
            cfObj.commonGetElement(driver, "//android.view.View[@content-desc='OTP Verification']/following-sibling::android.widget.EditText/android.widget.EditText", "xpath").click();

            cfObj.commonGetElement(driver, "//android.view.View[@content-desc='OTP Verification']/following-sibling::android.widget.EditText/android.widget.EditText", "xpath").sendKeys(strOtp);

            Thread.sleep(5000);

            result = !cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc='OTP Verification']", "xpath", 3);
            if (!result) {
                loginMsgList.add("Failed to close account recovery OTP.");

                cfObj.commonClick(homeUtilObj.homePageObj.getBtnMyContent());
                result = true;
            }
        } catch (Exception e) {
            loginMsgList.add("validateAccountRecoveryDetailsPopUp_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean validateMobileNoUpdateSuccessfullyPopUp(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc='Successfully updated !']", "xpath", 10);
            if (!result) {
                loginMsgList.add("Successfully updated title is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Your number is successfully updated. Please login to continue.\"]", "xpath", 10);
            if (!result) {
                loginMsgList.add("[Your number is successfully updated. Please login to continue] - text is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,'btn_sign_in')]", "xpath", 10);
            if (!result) {
                loginMsgList.add("Login button is not visible on MobileNoUpdateSuccessfullyPopUp screen.");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.View[contains(@content-desc,'btn_sign_in')]", "xpath"));

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.userNameEditBoxBtn(), 10);
            if (!result) {
                loginMsgList.add("Failed to click Login Button on MobileNoUpdateSuccessfullyPopUp screen");
                return result;
            }

        } catch (Exception e) {
            loginMsgList.add("validateMobileNoUpdateSuccessfullyPopUp_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyForgetPasswordLink(AppiumDriver<MobileElement> driver) {
        boolean result;
        String strUserEmail;
        String strOtp;
        String strUserId;
        try {
            if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
                strUserEmail = "automationOnBoarding12345@gmail.com";
                strUserId = "2901079";
            } else {
                strUserEmail = "automationOnBoarding1234@gmail.com";
                strUserId = "17214";
            }

            result = selectYourCategoryExamLanguage(driver);
            if (!result) {
                loginMsgList.add("Unable to select your category, exam and language");
                return result;
            }

            result = validateWelComePage(driver);
            if (!result) {
                loginMsgList.add("Failed to validate WelComePage.");
                return result;
            }

            result = clickOnEmailBtn(driver);
            if (!result) {
                loginMsgList.add("Failed to click Email btn.");
                return result;
            }

            result = enterEmailID(driver, strUserEmail);
            if (!result) {
                loginMsgList.add("Failed to Enter Password.");
                return result;
            }

            result = clickOnForgotPasswordLink(driver);
            if (!result) {
                loginMsgList.add("Failed to click ForgotPasswordLink.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,'Enter the code from the sms we sent')]/following-sibling::android.view.View[contains(@content-desc,'" + strUserEmail + "')]", "xpath", 10);
            if (!result) {
                loginMsgList.add("Displayed EmailId is incorrect on OTP Verification screen.");
                return result;
            }

            // Validate Email ID edit button
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc='edit_icon']", "xpath", 10);
            if (!result) {
                loginMsgList.add("EmailId edit button is not visible on OTP Verification screen.");
                return result;
            }

            cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.ImageView[@content-desc='edit_icon']", "xpath"));

            cfObj.commonClick(loginPageObj.userNameEditBoxBtn());

            result = enterEmailID(driver, strUserEmail);
            if (!result) {
                loginMsgList.add("Failed to Enter Password.");
                return result;
            }

            result = clickOnForgotPasswordLink(driver);
            if (!result) {
                loginMsgList.add("Failed to click ForgotPasswordLink.");
                return result;
            }

            // Exiting the test if the environment is production
            if (ConfigFileReader.strEnv.equals("PRODUCTION")) {
                throw new Exception("Cannot run the complete test on production. Please change the environment to run the complete test");
            }

            otpUtilObj = new OtpUtil();
            strOtp = otpUtilObj.getOtpAdminForgetPwd(strUserId, String.valueOf(OtpType.Email));
            if (strOtp == null) {
                loginMsgList.add("OTP is null");
                return false;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc='OTP Verification']", "xpath", 10);
            if (!result) {
                loginMsgList.add("The Enter OTP box didn't appeared");
                return result;
            }

            System.out.println(strOtp);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc='OTP Verification']/following-sibling::android.widget.EditText", "xpath", 10);
            if (!result) {
                loginMsgList.add("Otp text field is not visible.");
                return result;
            }

            cfObj.commonGetElement(driver, "//android.view.View[@content-desc='OTP Verification']/following-sibling::android.widget.EditText/android.widget.EditText", "xpath").click();
            cfObj.commonGetElement(driver, "//android.view.View[@content-desc='OTP Verification']/following-sibling::android.widget.EditText/android.widget.EditText", "xpath").sendKeys(strOtp);

            String pwd = Common_Function.getAlphaNumericString(10);
            System.out.println("New Password:-" + pwd);
            result = validateCreatePwdScreen(driver, pwd, strUserId, strUserEmail);
            if (!result) {
                loginMsgList.add("Failed to validate CreatePassword screen..");
                return result;
            }

            homeUtilObj = new HomePageUtil(driver);
            if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "skip", "id", 5)) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "skip", "id"));
            }

            result = homeUtilObj.clickHomeBtn(driver);
            if (!result) {
                loginMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = homeUtilObj.logout(driver);
            if (!result) {
                loginMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = verifyLoginUsingEmailId(driver, strUserEmail, pwd, false);
            if (!result) {
                loginMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }
        } catch (Exception e) {
            loginMsgList.add("verifyLoginNewUser_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean clickOnForgotPasswordLink(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"forgot_password\n" + "Forgot Password ?\"]", "xpath", 10);
            if (!result) {
                loginMsgList.add("Forgot Password Link is not visible.");
                return result;
            }

            cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.View[@content-desc=\"forgot_password\n" + "Forgot Password ?\"]", "xpath"));
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc='OTP Verification']", "xpath", 10);
            if (!result) {
                loginMsgList.add("Failed to click ForgotPasswordLink.");
                return result;
            }
        } catch (Exception e) {
            loginMsgList.add("clickOnForgotPasswordLink_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean generateAndEnterOTP(AppiumDriver<MobileElement> driver, String strMobileOrEmail, OtpType type) {
        boolean result = true;
        String strOtp = null;
        try {
            // Exiting the test if the environment is production
            if (ConfigFileReader.strEnv.equals("PRODUCTION")) {
                throw new Exception("Cannot run the complete test on production. Please change the environment to run the complete test");
            }

            otpUtilObj = new OtpUtil();

            strOtp = otpUtilObj.getOtpAdminEmailPhone(strMobileOrEmail, String.valueOf(type));
            if (strOtp == null) {
                loginMsgList.add("OTP is null");
                return false;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,'OTP Verification')]", "xpath", 10);
            if (!result) {
                loginMsgList.add("The Enter OTP box didn't appeared");
                return result;
            }

            System.out.println(strOtp);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,'OTP Verification')]/following-sibling::android.widget.EditText", "xpath", 10);
            if (!result) {
                loginMsgList.add("Otp text field is not visible.");
                return result;
            }

            cfObj.commonGetElement(driver, "//android.view.View[contains(@content-desc,'OTP Verification')]/following-sibling::android.widget.EditText", "xpath").click();

            cfObj.commonGetElement(driver, "//android.view.View[contains(@content-desc,'OTP Verification')]/following-sibling::android.widget.EditText/android.widget.EditText", "xpath").sendKeys(strOtp);
            Thread.sleep(2000);
            result = !cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,'OTP Verification')]/following-sibling::android.widget.EditText", "xpath", 3);
            if (!result) {
                loginMsgList.add("Failed to enter OTP.");
                return result;
            }
        } catch (Exception e) {
            loginMsgList.add("generateAndEnterOTP_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public enum OtpType {
        phone, Email
    }

    public boolean validateCreatePwdScreen(AppiumDriver<MobileElement> driver, String pwd, String userId, String userMailId) {
        boolean result = true;
        String strOtp = null;
        try {

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,'Confirm Password')]/preceding-sibling::android.widget.EditText", "xpath", 10);
            if (!result) {
                loginMsgList.add("Password text field is not visible.");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.View[contains(@content-desc,'Confirm Password')]/preceding-sibling::android.widget.EditText/android.widget.ImageView", "xpath"));
            // validate password text field with less than 8 character
            result = cfObj.commonSetTextTextBox(cfObj.commonGetElement(driver, "//android.view.View[contains(@content-desc,'Confirm Password')]/preceding-sibling::android.widget.EditText", "xpath"), "1234");
            if (!result) {
                loginMsgList.add("Failed to enter password on Password text field.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,'Confirm Password')]/following-sibling::android.widget.EditText", "xpath", 10);
            if (!result) {
                loginMsgList.add("ConfirmPassword text field is not visible.");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.View[contains(@content-desc,'Confirm Password')]/following-sibling::android.widget.EditText/android.widget.ImageView", "xpath"));

            result = cfObj.commonSetTextTextBox(cfObj.commonGetElement(driver, "//android.view.View[contains(@content-desc,'Confirm Password')]/following-sibling::android.widget.EditText", "xpath"), "1234");
            if (!result) {
                loginMsgList.add("Failed to enter Password on ConfirmPassword text field.");
                return result;
            }

            cfObj.commonClick(loginPageObj.confirmOrContinueBtn());

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc='Password must have atleast 6 characters']", "xpath", 10);
            if (!result) {
                loginMsgList.add("[Password must have atleast 6 characters] - error message is not visible.");
                return result;
            }

            // Password mismatch validation
            result = cfObj.commonSetTextTextBox(cfObj.commonGetElement(driver, "//android.view.View[contains(@content-desc,'Confirm Password')]/preceding-sibling::android.widget.EditText", "xpath"), "12345678");
            if (!result) {
                loginMsgList.add("Failed to enter password on Password text field.");
                return result;
            }

            result = cfObj.commonSetTextTextBox(cfObj.commonGetElement(driver, "//android.view.View[contains(@content-desc,'Confirm Password')]/following-sibling::android.widget.EditText", "xpath"), "abcdefgh");
            if (!result) {
                loginMsgList.add("Failed to enter Password on ConfirmPassword text field.");
                return result;
            }

            cfObj.commonClick(loginPageObj.confirmOrContinueBtn());

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Password don't match!\"]", "xpath", 10);
            if (!result) {
                loginMsgList.add("[Password don't match!] - error message is not visible.");
                return result;
            }

            // Back button validation
            cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.ImageView[contains(@content-desc,'btn_appbar_back')]", "xpath"));

            result = clickOnForgotPasswordLink(driver);
            if (!result) {
                loginMsgList.add("Failed to click ForgotPasswordLink.");
                return result;
            }

            // Exiting the test if the environment is production
            if (ConfigFileReader.strEnv.equals("PRODUCTION")) {
                result = false;
                throw new Exception("Cannot run the complete test on production. Please change the environment to run the complete test");
            }

            otpUtilObj = new OtpUtil();

            strOtp = otpUtilObj.getOtpAdminForgetPwd(userId, String.valueOf(OtpType.Email));
            if (strOtp == null) {
                loginMsgList.add("OTP is null");
                return false;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc='OTP Verification']", "xpath", 10);
            if (!result) {
                loginMsgList.add("The Enter OTP box didn't appeared");
                return result;
            }

            System.out.println(strOtp);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc='OTP Verification']/following-sibling::android.widget.EditText", "xpath", 10);
            if (!result) {
                loginMsgList.add("Otp text field is not visible.");
                return result;
            }

            cfObj.commonGetElement(driver, "//android.view.View[@content-desc='OTP Verification']/following-sibling::android.widget.EditText/android.widget.EditText", "xpath").click();
            cfObj.commonGetElement(driver, "//android.view.View[@content-desc='OTP Verification']/following-sibling::android.widget.EditText/android.widget.EditText", "xpath").sendKeys(strOtp);

            // validate MailId on Create Password screen
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,'" + userMailId + "')]", "xpath", 10);
            if (!result) {
                loginMsgList.add("Visible mailID is not correct on Create Password screen.");
                return result;
            }

            // positive scenarios validation
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,'Confirm Password')]/preceding-sibling::android.widget.EditText", "xpath", 10);
            if (!result) {
                loginMsgList.add("Password text field is not visible.");
                return result;
            }

            cfObj.commonGetElement(driver, "//android.view.View[contains(@content-desc,'Confirm Password')]/preceding-sibling::android.widget.EditText", "xpath").click();
            Thread.sleep(500);
            cfObj.commonGetElement(driver, "//android.view.View[contains(@content-desc,'Confirm Password')]/preceding-sibling::android.widget.EditText", "xpath").sendKeys(pwd);
            Thread.sleep(500);
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,'Confirm Password')]/following-sibling::android.widget.EditText", "xpath", 10);
            if (!result) {
                loginMsgList.add("Confirm password text field is not visible.");
                return result;
            }

            cfObj.commonGetElement(driver, "//android.view.View[contains(@content-desc,'Confirm Password')]/following-sibling::android.widget.EditText", "xpath").click();
            Thread.sleep(500);
            cfObj.commonGetElement(driver, "//android.view.View[contains(@content-desc,'Confirm Password')]/following-sibling::android.widget.EditText", "xpath").sendKeys(pwd);
            Thread.sleep(500);

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.confirmOrContinueBtn(), 10);
            if (!result) {
                loginMsgList.add("Create password screen login button is not visible.");
                return result;
            }

            cfObj.commonClick(loginPageObj.confirmOrContinueBtn());

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc='Changed password']", "xpath", 10);
            if (!result) {
                loginMsgList.add("Password Change toast message is not visible.");
                // return result;
            }

            Thread.sleep(2000);
            result = !cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.confirmOrContinueBtn(), 3);
            if (!result) {
                loginMsgList.add("Failed to click Create Password screen login button.");
                return result;
            }
        } catch (Exception e) {
            loginMsgList.add("validateCreatePwdScreen_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public String getStrEmailId() {
        return strEmailId;
    }

    public boolean examCourseNegativeCases(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.examCourseSearchBar(), 10);
            if (!result) {
                loginMsgList.add("Exam Course Searchbar is not visible on select your exam");
                return result;
            }
            cfObj.commonClick(loginPageObj.examCourseSearchBar());

            result= cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.searchBoxOfExamCategory(),20);
            if (!result) {
                loginMsgList.add("Exam Course search box is not visible on select your exam");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.blankViewTextBox(), 40);
            if (!result) {
                loginMsgList.add("No content screen is not visible after click on search");
                return result;
            }

            cfObj.commonSetTextTextBox(loginPageObj.searchBoxOfExamCategory(),"exam");

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.bckBtnOnSearchPage(), 10);
            if(!result){
                loginMsgList.add("Back button is not visible");
                return result;
            }
            cfObj.commonClick(loginPageObj.bckBtnOnSearchPage());

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.examCourseSearchBar(), 10);
            if(!result){
                loginMsgList.add("Exam Course Searchbar is not visible on select your exam after came back from select upur exam page");
                return result;
            }
            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.govtJobExam(), 10);
            if(!result){
                loginMsgList.add("Government Jobs Exam container is not visible");
                return result;
            }
            cfObj.commonClick(loginPageObj.govtJobExam());

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.bankingSelection(), 10);
            if(!result) {
                loginMsgList.add("Banking section is not visible in Government Jobs Exam container");
                return result;
            }
            cfObj.commonClick(loginPageObj.bankingSelection());

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.languageSelectionPage(), 10);
            if(!result) {
                loginMsgList.add("English Language Selection Page is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.englishLanguageSelected(), 10);
            if(!result) {
                loginMsgList.add("English Language Selection box is not visible");
                return result;
            }
            cfObj.commonClick(loginPageObj.englishLanguageSelected());

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.storeBtn(), 10);
            if (!result) {
                loginMsgList.add("Store button is visible on bottom nav after coming to home.");
                return result;
            }
        } catch (Exception e) {
            loginMsgList.add("examCourseNegativeCases_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }
}