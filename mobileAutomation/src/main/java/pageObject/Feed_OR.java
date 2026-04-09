package pageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class Feed_OR {

    public Feed_OR(AppiumDriver driver) {

        PageFactory.initElements(
                new AppiumFieldDecorator(driver, Duration.ofSeconds(10)),
                this
        );
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Feed\"]")
    private MobileElement feedIcon;
    public MobileElement getFeedIcon() {
        return feedIcon;
    }

}