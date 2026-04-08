package pageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class Login_OR {

    public Login_OR(AppiumDriver driver) {

        PageFactory.initElements(
                new AppiumFieldDecorator(driver, Duration.ofSeconds(10)),
                this
        );
    }

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Apna phone number')]")
    private MobileElement onboardingTitle;
    public MobileElement getOnboardingTitle() {
        return onboardingTitle;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='+91']")
    private MobileElement numberPrefix;
    public MobileElement getNumberPrefix() {
        return numberPrefix;
    }

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Apne baare mein batao aur pao 10 coins')]")
    private MobileElement onboardingCoins;
    public MobileElement getOnboardingCoins() {
        return onboardingCoins;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Skip\"]")
    private MobileElement skipOnboarding;
    public MobileElement getSkipOnboarding() {
        return skipOnboarding;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"payment_wall_skip_button\nSkip\"]\n")
    private MobileElement skipPaywall;
    public MobileElement getSkipPaywall() {
        return skipPaywall;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"paywall_exit_maybe_later\nMaybe Later\"]\n")
    private MobileElement maybeLaterPaywall;
    public MobileElement getMaybeLaterPaywall() {
        return maybeLaterPaywall;
    }

    @AndroidFindBy(xpath = "//android.widget.Button")
    private MobileElement freeTokensCross;
    public MobileElement getFreeTokensCross() {
        return freeTokensCross;
    }
}
