package pageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class UpiAutoPayPage_OR {

    // Constructor to accept AppiumDriver and initialize elements
    public UpiAutoPayPage_OR(AppiumDriver<MobileElement> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "")
    private MobileElement titleSignUp;

    public MobileElement getTitleSignUp() {
        return titleSignUp;
    }
}
