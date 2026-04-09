package pageObject;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class Profile_OR {

    public Profile_OR(AppiumDriver driver) {

        PageFactory.initElements(
                new AppiumFieldDecorator(driver, Duration.ofSeconds(10)),
                this
        );
    }

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"home_header_profile_button\"]")
    private MobileElement profileIcon;
    public MobileElement getProfileIcon() {
        return profileIcon;
    }

    @AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'Join to Learnr Pro')]")
    private MobileElement paywallBatch;
    public MobileElement getPaywallBatch() {
        return paywallBatch;
    }

    // Profile pic
    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"profile_pic\"]")
    private MobileElement profilePic;
    public MobileElement getProfilePic() { return profilePic; }

    // User name
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"profile_user_name\"]")
    private MobileElement userName;
    public MobileElement getUserName() { return userName; }

    // User phone number
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"profile_user_phone\"]")
    private MobileElement userPhone;
    public MobileElement getUserPhone() { return userPhone; }

    // Edit profile button
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"profile_edit_button\"]")
    private MobileElement editProfileBtn;
    public MobileElement getEditProfileBtn() { return editProfileBtn; }

    // Change goal
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"profile_change_goal\"]")
    private MobileElement changeGoalBtn;
    public MobileElement getChangeGoalBtn() { return changeGoalBtn; }

    // Change goal popup
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"change_goal_popup\"]")
    private MobileElement changeGoalPopup;
    public MobileElement getChangeGoalPopup() { return changeGoalPopup; }

    // Change goal popup close
    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"change_goal_popup_close\"]")
    private MobileElement changeGoalPopupClose;
    public MobileElement getChangeGoalPopupClose() { return changeGoalPopupClose; }

    // Help and support
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"profile_help_support\"]")
    private MobileElement helpAndSupport;
    public MobileElement getHelpAndSupport() { return helpAndSupport; }

    // Terms and conditions
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"profile_terms_conditions\"]")
    private MobileElement termsAndConditions;
    public MobileElement getTermsAndConditions() { return termsAndConditions; }

    // Terms and conditions page title
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"terms_conditions_page_title\"]")
    private MobileElement termsPageTitle;
    public MobileElement getTermsPageTitle() { return termsPageTitle; }

    // Back arrow (generic)
    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"back_arrow\"]")
    private MobileElement backArrow;
    public MobileElement getBackArrow() { return backArrow; }

    // Privacy policy
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"profile_privacy_policy\"]")
    private MobileElement privacyPolicy;
    public MobileElement getPrivacyPolicy() { return privacyPolicy; }

    // Privacy policy page title
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"privacy_policy_page_title\"]")
    private MobileElement privacyPageTitle;
    public MobileElement getPrivacyPageTitle() { return privacyPageTitle; }

    // Back icon
    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"back_icon\"]")
    private MobileElement backIcon;
    public MobileElement getBackIcon() { return backIcon; }

    // Three dots menu
    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"profile_three_dots\"]")
    private MobileElement threeDots;
    public MobileElement getThreeDots() { return threeDots; }

    // Logout option
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"profile_logout\"]")
    private MobileElement logoutOption;
    public MobileElement getLogoutOption() { return logoutOption; }

    // Delete account option
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"profile_delete_account\"]")
    private MobileElement deleteAccountOption;
    public MobileElement getDeleteAccountOption() { return deleteAccountOption; }

    // Cancel button
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"profile_cancel\"]")
    private MobileElement cancelBtn;
    public MobileElement getCancelBtn() { return cancelBtn; }

    // Profile page back icon
    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"profile_back_icon\"]")
    private MobileElement profileBackIcon;
    public MobileElement getProfileBackIcon() { return profileBackIcon; }

    // Home screen element to verify navigation back
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"home_screen\"]")
    private MobileElement homeScreen;
    public MobileElement getHomeScreen() { return homeScreen; }
}