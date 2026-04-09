package applicationUtil;

import java.util.ArrayList;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import apiUtill.OtpUtil;
import org.testng.Assert;
import pageObject.Feed_OR;
import pageObject.Login_OR;
import pageObject.Profile_OR;
import util.Common_Function;
import util.ConfigFileReader;

public class FeedUtil {

    public ArrayList<String> feedMsgList = new ArrayList<>();
    public Common_Function cfObj = new Common_Function();
    public ConfigFileReader cfReaderObj = new ConfigFileReader();
    Feed_OR feedPageObj;
    LoginUtil loginUtilObj;

    public FeedUtil(AppiumDriver<MobileElement> driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Appium driver is null! Check your driver initialization.");
        }
        this.feedPageObj = new Feed_OR(driver);
    }
}
