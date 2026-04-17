package applicationUtil;

import java.util.*;

import apiUtill.OtpUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import pageObject.Feed_OR;
import pageObject.Login_OR;
import pageObject.Profile_OR;
import util.*;

public class EventsUtil {

    public ArrayList<String> eventMsgList = new ArrayList<>();
    public Common_Function cfObj = new Common_Function();
    public ConfigFileReader cfReaderObj = new ConfigFileReader();
    public Login_OR loginPageObj;
    public Profile_OR profilePageObj;
    public Feed_OR feedPageObj;
    public LoginUtil loginUtilObj;
    public OtpUtil otpUtilObj;
    public EventValidationUtil ev;

    public EventsUtil(AppiumDriver<MobileElement> driver, LogCaptureUtil logUtil) {
        if (driver == null) {
            throw new IllegalArgumentException("Appium driver is null! Check your driver initialization.");
        }
        this.loginPageObj = new Login_OR(driver);
        this.profilePageObj = new Profile_OR(driver);
        this.feedPageObj = new Feed_OR(driver);
        this.loginUtilObj = new LoginUtil(driver);
        this.otpUtilObj = new OtpUtil();
        this.ev = new EventValidationUtil(logUtil);
    }

    public boolean verifyAppEvents(AppiumDriver<MobileElement> driver) {
        try {
            if (!verifyLoginEvents(driver)) {
                eventMsgList.add("Login events are not verified");
                return false;
            }
        } catch (Exception e) {
            eventMsgList.add("verifyAppEvents_Exception: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean verifyLoginEvents(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        String num = "9696219097";

        try {
            // ---- Step 1: Pitch Screen ----
            if (!waitForElement(driver, "//android.view.View[contains(@content-desc,\"pitch_start_karen_button\")]")) {
                eventMsgList.add("Pitch screen is not visible");
                return false;
            }

            int checkpoint = ev.getEventCheckpoint();

            if (!validateEvent("screen_view", checkpoint,
                    mapOf("screen_name", "pitch_screen", "screen_source", "onboarding"),
                    "Step 1")) return false;

            if (!validateNotNull("screen_view", checkpoint, "Step 1", "adjust_id")) return false;

            // ---- Step 2: Click Pitch Button ----
            checkpoint = ev.getEventCheckpoint();
            cfObj.commonClick(driver,
                    "//android.view.View[contains(@content-desc,\"pitch_start_karen_button\")]",
                    "xpath");

            if (!validateEvent("button_click", checkpoint,
                    mapOf("button_name", "start karen", "button_source_screen", "pitch_screen"),
                    "Step 2a")) return false;

            if (!validateNotNull("button_click", checkpoint, "Step 2a", "adjust_id")) return false;

            if (!validateEvent("screen_view", checkpoint,
                    mapOf("screen_name", "mobile_number_input", "source_screen", "onboarding"),
                    "Step 2b")) return false;

            if (!validateEvent("truecaller_requested", checkpoint, "Step 2c")) return false;

            if (!validateEvent("button_click", checkpoint,
                    mapOf("button_name", "enter_number", "button_source_screen", "mobile_number_input"),
                    "Step 2d")) return false;

            if (!validateEvent("google_number_requested", checkpoint, "Step 2e")) return false;

            // ---- Step 3: Enter Mobile Number ----
            checkpoint = ev.getEventCheckpoint();

            cfObj.commonSetTextTextBox(
                    cfObj.commonGetElement(driver, "//android.widget.EditText", "xpath"), num);

            if (!validateEvent("button_click", checkpoint,
                    mapOf("button_name", "send_otp", "button_source_screen", "mobile_number_input"),
                    "Step 3a")) return false;

            if (!validateEvent("screen_view", checkpoint,
                    mapOf("screen_name", "otp_verification", "screen_source", "onboarding"),
                    "Step 3b")) return false;

            // ---- Step 4: Enter OTP ----
            checkpoint = ev.getEventCheckpoint();

            String otp = ConfigFileReader.prodOtpMasterPassword;

            cfObj.commonSetTextTextBox(
                    cfObj.commonGetElement(driver,
                            "//android.widget.EditText[@resource-id=\"edt_otp_field\"]/android.widget.EditText",
                            "xpath"),
                    otp);

            // ---- Step 5: Post OTP ----
            if (!validateEvent("login", checkpoint, "Step 5a")) return false;

            if (!validateNotNull("login", checkpoint, "Step 5a",
                    "auto_read_otp", "is_truecaller")) return false;

            if (!validateEvent("screen_view", checkpoint,
                    mapOf("screen_name", "personalization_start_screen_view", "screen_source", "onboarding"),
                    "Step 5b")) return false;

        } catch (Exception e) {
            eventMsgList.add("verifyLoginEvents: " + e.getMessage());
            return false;
        }

        return result;
    }

    // ---- Utility Methods ----

    private boolean waitForElement(AppiumDriver<MobileElement> driver, String xpath) {
        return cfObj.commonWaitForElementToBeVisible(driver,
                cfObj.commonGetElement(driver, xpath, "xpath"), 30);
    }

    private Map<String, String> mapOf(String... data) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < data.length; i += 2) {
            map.put(data[i], data[i + 1]);
        }
        return map;
    }

    private boolean validateEvent(String event, int checkpoint, String step) throws InterruptedException {
        if (!ev.wasEventFiredAfter(event, checkpoint)) {
            eventMsgList.add(step + " → Event '" + event + "' NOT fired");
            return false;
        }
        return true;
    }

    private boolean validateEvent(String event, int checkpoint, Map<String, String> attrs, String step)
            throws InterruptedException {
        String error = ev.validateEventContains(event, checkpoint, attrs);
        if (error != null) {
            eventMsgList.add(step + " → " + error);
            return false;
        }
        return true;
    }

    private boolean validateNotNull(String event, int checkpoint, String step, String... keys)
            throws InterruptedException {
        String error = ev.validateAttributesNotNull(event, checkpoint, Arrays.asList(keys));
        if (error != null) {
            eventMsgList.add(step + " → " + error);
            return false;
        }
        return true;
    }
}