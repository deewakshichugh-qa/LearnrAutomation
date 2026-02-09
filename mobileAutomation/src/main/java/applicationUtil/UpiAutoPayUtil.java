package applicationUtil;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import pageObject.UpiAutoPayPage_OR;
import util.Common_Function;
import util.ConfigFileReader;

import java.util.ArrayList;

public class UpiAutoPayUtil {

    public ArrayList<String> upiAutoPayMsgList = new ArrayList<>();
    public Common_Function cfObj = new Common_Function();
    public ConfigFileReader cfReaderObj = new ConfigFileReader();

    UpiAutoPayPage_OR upiAutoPayObj;

    public UpiAutoPayUtil(AppiumDriver<MobileElement> driver) {
        if (driver == null) {
            throw new IllegalArgumentException("Appium driver is null! Check your driver initialization.");
        }
        this.upiAutoPayObj = new UpiAutoPayPage_OR(driver);
    }

    public boolean verifyUpiAutoPay(AppiumDriver<MobileElement> driver){
        boolean result = true;
        try {







        } catch (Exception e) {
            upiAutoPayMsgList.add("verifyUpiAutoPay_Exception: "+ e.getMessage());
            result = false;
        }
        return result;
    }
}
