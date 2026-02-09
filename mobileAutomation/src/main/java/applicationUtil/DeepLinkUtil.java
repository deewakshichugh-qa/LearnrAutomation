package applicationUtil;

import java.util.ArrayList;

import org.openqa.selenium.JavascriptExecutor;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import pojo.deepLinkData.DeepLinkData;
import util.Common_Function;
import util.ConfigFileReader;

public class DeepLinkUtil {

    Common_Function cfObj = new Common_Function();
    public ArrayList<String> deepLinkMsgList = new ArrayList<String>();
    LoginUtil loginObj;

    public boolean verifyDeeplink(AppiumDriver<MobileElement> driver, DeepLinkData deepLinkdata,
                                  boolean isUserLoggedIn) {
        boolean result = true;
        String strEmailId, strUserPassword;
        try {
            String packageName = "com.adda247.app";
            System.out.println("---- Starting deeplink: " + deepLinkdata.getDeeplinkType() + " ----");

            if (isUserLoggedIn) {

                try {
                    // Execute the deep link command
                    ((JavascriptExecutor) driver).executeScript("mobile: deepLink",
                            ImmutableMap.of("url", deepLinkdata.getDeeplink(), "package", packageName));
                } catch (Exception e) {
                    deepLinkMsgList.add("Error executing deeplink: " + e.getMessage());
                    return false;
                }
            } else {
                try {
                    // Execute the deep link command
                    ((JavascriptExecutor) driver).executeScript("mobile: deepLink",
                            ImmutableMap.of("url", deepLinkdata.getDeeplink(), "package", packageName));
                } catch (Exception e) {
                    deepLinkMsgList.add("Error executing deeplink: " + e.getMessage());
                    return false;
                }

                loginObj = new LoginUtil(driver);
                if (ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
                    strEmailId = ConfigFileReader.prodStrUserEmailId;
                    strUserPassword = ConfigFileReader.prodEmailMasterPassword;
                } else {
                    strEmailId = ConfigFileReader.strUserEmailId;
                    strUserPassword = ConfigFileReader.strUserPassword;
                }

                result = loginObj.verifyLoginUsingEmailId(driver, strEmailId, strUserPassword, false);
                if (!result) {
                    deepLinkMsgList.addAll(loginObj.loginMsgList);
                    deepLinkMsgList.add("Issue in Login after executing deeplink");
                    return result;
                }

                // Allowing notification permission if coming
                if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"), 8)) {
                    cfObj.commonClick(cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"));
                }
            }

            if (deepLinkdata.getDeeplinkType().equalsIgnoreCase("PDP")) {

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@content-desc=\"BUY NOW\"]", "xpath", 10);
                if (!result) {
                    deepLinkMsgList.add("PDP page not opened for deeplink: " + deepLinkdata.getDeeplink());
                    return result;
                }
                System.out.println("Checked PDP");

            } else if (deepLinkdata.getDeeplinkType().equalsIgnoreCase("Testseries Instructions")) {

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                        "//*[contains(@text,'Instructions')]", "xpath", 15);
                if (!result) {
                    deepLinkMsgList.add("Testseries instructions page is not visible.");
                    return result;
                }
                System.out.println("Checked Testseries Instructions");

            } else if (deepLinkdata.getDeeplinkType().equalsIgnoreCase("Home")) {

                HomePageUtil homePageUtilObj = new HomePageUtil(driver);
                result = homePageUtilObj.verifyHomePage(driver);
                if (!result) {
                    deepLinkMsgList.add("Failed to verify Home page.");
                    return result;
                }
                System.out.println("Checked Home");

            } else if (deepLinkdata.getDeeplinkType().equalsIgnoreCase("Refer and Earn")) {

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                        "//android.view.View[@content-desc=\"Invite your friends\"]", "xpath", 15);
                if (!result) {
                    deepLinkMsgList.add("invite your friends text is not visible.");
                    return result;
                }
                System.out.println("Checked Refer & Earn");

            } else if (deepLinkdata.getDeeplinkType().equalsIgnoreCase("MockTests")) {

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                        "//android.view.View[@content-desc=\"Mock Tests\"]", "xpath", 15);
                if (!result) {
                    deepLinkMsgList.add("Mock Tests heading is not visible.");
                    return result;
                }
                System.out.println("Checked Mock Tests");

            } else if (deepLinkdata.getDeeplinkType().equalsIgnoreCase("Books")) {

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                        "//android.view.View[@content-desc=\"Books\"]", "xpath", 15);
                if (!result) {
                    deepLinkMsgList.add("Books heading is not visible.");
                    return result;
                }
                System.out.println("Checked Books");

            } else if (deepLinkdata.getDeeplinkType().equalsIgnoreCase("Ebooks")) {

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                        "//android.view.View[@content-desc=\"E-Books\"]", "xpath", 15);
                if (!result) {
                    deepLinkMsgList.add("EBooks heading is not visible.");
                    return result;
                }
                System.out.println("Checked Ebooks");

            } else if (deepLinkdata.getDeeplinkType().equalsIgnoreCase("VideoCourse")) {

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                        "//android.view.View[@content-desc=\"Videos\"]", "xpath", 15);
                if (!result) {
                    deepLinkMsgList.add("Video Course heading is not visible.");
                    return result;
                }
                System.out.println("Checked Video Course");

            } else if (deepLinkdata.getDeeplinkType().equalsIgnoreCase("LiveClasses")) {

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                        "//android.view.View[@content-desc=\"Live Class\"]", "xpath", 15);
                if (!result) {
                    deepLinkMsgList.add("LiveClasses heading is not visible.");
                    return result;
                }
                System.out.println("Checked LiveClasses");

            } else if (deepLinkdata.getDeeplinkType().equalsIgnoreCase("Store")) {

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                        "//android.view.View[@content-desc=\"Browse By Product\"]", "xpath", 15);
                if (!result) {
                    deepLinkMsgList.add("Store is not visible.");
                    return result;
                }
                System.out.println("Checked Store");

            } else if (deepLinkdata.getDeeplinkType().equalsIgnoreCase("StoreExam")) {

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                        "//android.view.View[@content-desc=\"demo-testing-om\"]", "xpath", 15);
                if (!result) {
                    deepLinkMsgList.add("StoreExam is not visible.");
                    return result;
                }
                System.out.println("Checked StoreExam");

            } else if (deepLinkdata.getDeeplinkType().equalsIgnoreCase("Doubtroom")) {

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                        "//android.widget.TextView[@text=\"Doubt Room\"]", "xpath", 15);
                if (!result) {
                    deepLinkMsgList.add("Doubt room is not visible.");
                    return result;
                }
                System.out.println("Checked Doubt room");

            } else if (deepLinkdata.getDeeplinkType().equalsIgnoreCase("PaidLiveClassRecorded")) {

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                        "com.adda247.app:id/playerCard", "id", 15);
                if (!result) {
                    deepLinkMsgList.add("The video screen is not visible, after opening paid liveclass recorded deeplink");
                    return result;
                }
                System.out.println("Checked PaidLiveClassRecorded");

            } else if (deepLinkdata.getDeeplinkType().equalsIgnoreCase("CommunityPost")) {

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[contains(@content-desc,'postBack')]", "xpath", 10);
                if (!result) {
                    deepLinkMsgList.add("Back btn is not visible in the post");
                    return result;
                }

                cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.ImageView[contains(@content-desc,'postBack')]", "xpath"));

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Be the first one to answer\"]", "xpath", 10);
                if (!result) {
                    deepLinkMsgList.add("Share btn is not visible in the post");
                    return result;
                }

                 result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[contains(@content-desc,'Correct Answer')]", "xpath", 10);
                if (!result) {
                    deepLinkMsgList.add("Correct answer text is not visible in the post");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"uploadComment\n" +
                        "Post\"]", "xpath", 10);
                if (!result) {
                    deepLinkMsgList.add("Share btn is not visible in the post");
                    return result;
                }
                System.out.println("Checked CommunityPost");

            } else if (deepLinkdata.getDeeplinkType().equalsIgnoreCase("CommunityGroup")) {

                cfObj.handleHints(driver, 4);

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"Share\"]", "xpath", 10);
                if (!result) {
                    deepLinkMsgList.add("Share btn is not visible in the group");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"Post your query/doubt\"]", "xpath", 10);
                if (!result) {
                    deepLinkMsgList.add("Post btn is not visible in the group");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,'Daily Posts :')]", "xpath", 10);
                if (!result) {
                    deepLinkMsgList.add("Daily Posts text is not visible in the group");
                    return result;
                }
                System.out.println("Checked CommunityGroup");

            } else {
                System.out.println("---------");
                System.out.println(deepLinkdata.getDeeplinkType() + " is not configured.");
                System.out.println("---------");
            }
        } catch (Exception e) {
            deepLinkMsgList.add("verifyDeeplinkException : " + e.getMessage());
            result = false;
        }
        return result;
    }
}