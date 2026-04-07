package applicationUtil;

import java.util.ArrayList;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import apiUtill.OtpUtil;
import pageObject.Login_OR;
import util.Common_Function;
import util.ConfigFileReader;
public class LoginUtil {

    private OtpUtil otpUtilObj;
    public ArrayList<String> loginMsgList = new ArrayList<>();
    public Common_Function cfObj = new Common_Function();
    public ConfigFileReader cfReaderObj = new ConfigFileReader();
    Login_OR loginPageObj;

    public LoginUtil(AppiumDriver<MobileElement> driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Appium driver is null! Check your driver initialization.");
        }
        this.loginPageObj = new Login_OR(driver);
    }

    public boolean verifySignUp(AppiumDriver<MobileElement> driver){
        boolean result = true;
        try{
            result = signUp(driver, Common_Function.randomPhoneNumber(10, "8"));
            if(!result){
                loginMsgList.add("Unable to signup");
                return result;
            }
            result = verifyOnboardingScreen(driver);
            if(!result){
                loginMsgList.add("Issue verifying personalisation screen");
                return result;
            }

        } catch (Exception e) {
            loginMsgList.add("verifySignUp_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyOnboardingScreen(AppiumDriver<MobileElement> driver){
        boolean result = true;
        try{
            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getOnboardingCoins(), 30);
            if(!result){
                loginMsgList.add("Onboarding screen is not opened after personalisation screen");
                return result;
            }
        } catch (Exception e) {
            loginMsgList.add("verifyOnboardingScreen_Exception: " + e.getMessage());
            result = false;
        }
        return result;

    }
    public boolean signUp(AppiumDriver<MobileElement> driver, String mobileNumber) {
        boolean result = true;
        String strOtp = null;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver,
                    cfObj.commonGetElement(driver, "//android.view.View[contains(@content-desc,\"pitch_start_karen_button\")]", "xpath"), 30);
            if (!result) {
                loginMsgList.add("Pitch screen is not opened when app is launched");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElement(driver,"//android.view.View[contains(@content-desc,\"pitch_start_karen_button\")]","xpath"));

            result = cfObj.commonWaitForElementToBeVisible(driver, loginPageObj.getOnboardingTitle(),10);
            if (!result) {
                loginMsgList.add("Onboarding title is not present");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver,loginPageObj.getNumberPrefix(),10);
            if (!result) {
                loginMsgList.add("Country prefix is not present");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver,
                    cfObj.commonGetElement(driver, "//android.widget.EditText", "xpath"), 10);
            if (!result) {
                loginMsgList.add("Phone number input field is not visible");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.EditText", "xpath"));

            result = cfObj.commonSetTextTextBox(cfObj.commonGetElement(driver, "//android.widget.EditText", "xpath"), mobileNumber);
            if (!result) {
                loginMsgList.add("Unable to enter phone number");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver,
                    cfObj.commonGetElement(driver, "//android.view.View[@content-desc=\"Send OTP\"]", "xpath"), 20);
            if (!result) {
                loginMsgList.add("Send OTP button is not visible");
                return result;
            }

            cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.View[@content-desc=\"Send OTP\"]", "xpath"));

            if (ConfigFileReader.strEnv.equalsIgnoreCase("production")) {

                strOtp = ConfigFileReader.prodOtpMasterPassword;
            } else {
                otpUtilObj = new OtpUtil();
                strOtp = otpUtilObj.getOtpAdminEmailPhone(mobileNumber, "phone");
                if (strOtp == null) {
                    loginMsgList.add("OTP is null");
                    return false;
                }
            }

            result = cfObj.commonWaitForElementToBeVisible(driver,
                    cfObj.commonGetElement(driver, "//android.view.View[contains(@content-desc,'par bhej diya hai')]", "xpath"), 10);
            if (!result) {
                loginMsgList.add("Otp title is not present");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver,
                    cfObj.commonGetElement(driver, "//android.widget.EditText[@resource-id=\"edt_otp_field\"]/android.widget.EditText", "xpath"), 20);
            if (!result) {
                loginMsgList.add("Otp entering textbox is not visible");
                return result;
            }
            cfObj.commonSetTextTextBox(cfObj.commonGetElement(driver,
                    "//android.widget.EditText[@resource-id=\"edt_otp_field\"]/android.widget.EditText",
                    "xpath"), strOtp);

            Thread.sleep(5000);

        } catch (Exception e) {
            loginMsgList.add("signUp_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

}
