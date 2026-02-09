package applicationUtil;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import apiUtill.*;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonObject;

import applicationUtil.StorePageUtil.ProductType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.CommunityPage_OR;
import pageObject.MyContentPage_OR;
import pageObject.PackagePage_OR;
import pojo.createAdmitCardResponse.CreateCampaignAdmitCardResponse;
import pojo.createCertificateResponse.CreateCertificateResponse;
import pojo.createPackageResponse.CreatePackageResponse;
import pojo.getSignedUrl.GetSignedUrl;
import pojo.getVideoList.GetVideoList;
import util.Common_Function;
import util.Common_Function.NetworkState;
import util.Common_Function.direction;
import util.Common_Function.key;
import util.ConfigFileReader;

public class MyContentUtil {

    String strTitleEBook = "null";
    String strPhoneNo = Common_Function.randomPhoneNumber(10, "8");

    UserLoginUtil userLoginUtiliObj;
    MyContentSearchApiUtil myContentSearchApiObj;
    BatchUtil batchUtilObj;
    JsonObject loginDataObj;
    CommunityPage_OR communityPageObj;
    CommunityPageUtil communityPageUtilObj;
    ChildPackageUtil childPackageUtilObj;
    StudyMaterialTabUtil studyMaterialTabUtilObj;
    PackagePage_OR packagePageObj;
    RegisterNewUserUtil registrationUserUtilObj;
    LoginUtil loginUtilObj;
    HomePageUtil homeUtilObj;
    MyContentPage_OR myContentPageObj;
    StorePageUtil storeUtilObj;
    VideoCoursesPageUtil videoCoursesPageUtilObj;
    MockTestApiUtil mockTestApiUtilObj;
    PackageApiUtil packageApiUtilObj;
    CommonTestUtil commonTestUtilObj;
    ConfigFileReader cfrObj = new ConfigFileReader();
    PurchasePackageUtil purchasePackageUtilObj;
    UserDetailsLayerUtil userDetailsLayerUtilObj;
    PaymentUtil paymentUtilObj;
    OrderSuccessUtil orderSuccessUtilObj;
    LiveClassesPageUtil liveClassesPageUtilObj;
    CertificatePreviewUtil certificatePreviewUtilObj;
    UserProfilePageUtil userProfilePageUtilObj;
    CommonStudyMaterialSectionUtil commonStudyMaterialSectionUtilObj;
    FeedbackFormUtil feedbackFormUtilObj;
    EBooksPageUtil eBooksPageUtilObj;
    public MockTestApiUtil mockTestApiUtil = new MockTestApiUtil();

    public ArrayList<String> myContentMsgList = new ArrayList<>();
    public Common_Function cfObj = new Common_Function();
    public ConfigFileReader configObj = new ConfigFileReader();

    public MyContentUtil(AppiumDriver<MobileElement> driver) {
        myContentPageObj = new MyContentPage_OR();
        PageFactory.initElements(new AppiumFieldDecorator(driver), myContentPageObj);
    }

    // --------------------------VERIFY BOOKMARK SECTION NEW
    // USER-----------------------------------------------------------------

    public boolean verifyBookmarkSectionNewUser(AppiumDriver<MobileElement> driver) {
        boolean result;
        homeUtilObj = new HomePageUtil(driver);
        try {

            loginUtilObj = new LoginUtil(driver);
            result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), true);
            if (!result) {
                myContentMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.add("Unable to open my contents page");
                return result;
            }

            result = clickOnBookmarksBtn(driver);
            if (!result) {
                myContentMsgList.add("Unable to open bookmarks section");
                return result;
            }

            result = verifyQuestionsInBookmarksSectionNewUser(driver);
            if (!result) {
                myContentMsgList.add("The questions section in bookmark is not empty");
                return result;
            }

            result = verifyCurrentAffairsInBookmarksSectionNewUser(driver);
            if (!result) {
                myContentMsgList.add("The Current Affairs section in bookmark is not empty");
                return result;
            }

            result = verifyJobAlertsInBookmarksSectionNewUser(driver);
            if (!result) {
                myContentMsgList.add("The Job Alerts section in bookmark is not empty");
                return result;
            }

            result = verifyArticlesInBookmarksSectionNewUser(driver);
            if (!result) {
                myContentMsgList.add("The Articles section in bookmark is not empty");
                return result;
            }

            result = verifyVideosInBookmarksSectionNewUser(driver);
            if (!result) {
                myContentMsgList.add("The Videos section in bookmark is not empty");
                return result;
            }

        } catch (Exception e) {

            myContentMsgList.add("verifyBookmarkSectionNewUser_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean clickOnBookmarksBtn(AppiumDriver<MobileElement> driver) {
        boolean result;
        try {

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getBtnBookmarkSection(), 10);
            if (!result) {
                myContentMsgList.add("Bookmark section not visible in my content");
                return result;
            }

            cfObj.commonClick(myContentPageObj.getBtnBookmarkSection());

            cfObj.commonClick(myContentPageObj.getBtnBookmarkSection());

            result = verifyBookmarksPage(driver);
            if (!result) {
                myContentMsgList.add("Failed to verify BookMark page.");
            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("clickOnBookmarksBtn_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean verifyBookmarksPage(AppiumDriver<MobileElement> driver) {
        boolean result;
        try {

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getQuesitonsBookmarksSection(), 10);
            if (!result) {
                myContentMsgList.add("Unable to verify Questions in Bookmarks section");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver,
                    myContentPageObj.getCurrentAffairsInBookmarksSection(), 10);
            if (!result) {
                myContentMsgList.add("Unable to verify Current Affairs in Bookmarks section");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getJobAlertsBookmarksSection(), 10);
            if (!result) {
                myContentMsgList.add("Unable to verify Job Alerts in Bookmarks section");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getArticlesBookmarksSection(), 10);
            if (!result) {
                myContentMsgList.add("Unable to verify Articles in Bookmarks section");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getVideoBookmarkdedSection(), 10);
            if (!result) {
                myContentMsgList.add("Unable to verify Videos in Bookmarks section");
                return result;
            }

        } catch (Exception e) {
            myContentMsgList.add("verifyPurchasedPage_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyQuestionsInBookmarksSectionNewUser(AppiumDriver<MobileElement> driver) {
        boolean result;
        try {

            cfObj.commonClick(myContentPageObj.getQuesitonsBookmarksSection());

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/emptyViewMessageTitle", "id", 10);
            if (!result) {
                myContentMsgList.add("Empty message is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/emptyViewMessage", "id", 10);
            if (!result) {
                myContentMsgList.add("Empty sub message is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/emptyViewBuy", "id", 10);
            if (!result) {
                myContentMsgList.add("Go to btn is not visible");
                return result;
            }

            driver.navigate().back();

        } catch (Exception e) {
            myContentMsgList.add("verifyQuestionsInBookmarksSectionNewUser_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyCurrentAffairsInBookmarksSectionNewUser(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            cfObj.commonClick(myContentPageObj.getCurrentAffairsInBookmarksSection());

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/emptyViewMessageTitle", "id", 10);
            if (!result) {
                myContentMsgList.add("Empty message is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/emptyViewMessage", "id", 10);
            if (!result) {
                myContentMsgList.add("Empty sub message is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/emptyViewBuy", "id", 10);
            if (!result) {
                myContentMsgList.add("Go to btn is not visible");
                return result;
            }

            cfObj.pressAndroidKey(driver, key.BACK, 1);

        } catch (Exception e) {
            myContentMsgList.add("verifyCurrentAffairsInBookmarksSectionNewUser_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyJobAlertsInBookmarksSectionNewUser(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            cfObj.commonClick(myContentPageObj.getJobAlertsBookmarksSection());

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/emptyViewMessageTitle", "id", 10);
            if (!result) {
                myContentMsgList.add("Empty message is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/emptyViewMessage", "id", 10);
            if (!result) {
                myContentMsgList.add("Empty sub message is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/emptyViewBuy", "id", 10);
            if (!result) {
                myContentMsgList.add("Go to btn is not visible");
                return result;
            }
            cfObj.pressAndroidKey(driver, key.BACK, 1);

        } catch (Exception e) {
            myContentMsgList.add("verifyJobAlertsInBookmarksSectionNewUser_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyArticlesInBookmarksSectionNewUser(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            cfObj.commonClick(myContentPageObj.getArticlesBookmarksSection());

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/emptyViewMessageTitle", "id", 10);
            if (!result) {
                myContentMsgList.add("Empty message is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/emptyViewMessage", "id", 10);
            if (!result) {
                myContentMsgList.add("Empty sub message is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/emptyViewBuy", "id", 10);
            if (!result) {
                myContentMsgList.add("Go to btn is not visible");
                return result;
            }
            cfObj.pressAndroidKey(driver, key.BACK, 1);

        } catch (Exception e) {
            myContentMsgList.add("verifyArticlesInBookmarksSectionNewUser_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyVideosInBookmarksSectionNewUser(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            cfObj.commonClick(myContentPageObj.getVideoBookmarkdedSection());

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/emptyViewMessageTitle", "id", 10);
            if (!result) {
                myContentMsgList.add("Empty message is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/emptyViewMessage", "id", 10);
            if (!result) {
                myContentMsgList.add("Empty sub message is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/emptyViewBuy", "id", 10);
            if (!result) {
                myContentMsgList.add("Go to btn is not visible");
                return result;
            }
            cfObj.pressAndroidKey(driver, key.BACK, 1);

        } catch (Exception e) {
            myContentMsgList.add("verifyVideosInBookmarksSectionNewUser_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    // -----------------------------VERIFY BOOKMARK SECTION GUEST
    // USER----------------------------------------------------------------

    public boolean verifyBookmarkSection(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        String strTitleCurrentAffairs = null;
        String strTitleJobAlerts = null;
        String strTitleArticles = null;
        String strTitleVideos = null;
        try {

            loginUtilObj = new LoginUtil(driver);
            homeUtilObj = new HomePageUtil(driver);

            result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), true);
            if (!result) {
                myContentMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.add("Unable to open my contents page");
                return result;
            }

            result = clickOnBookmarksBtn(driver);
            if (!result) {
                myContentMsgList.add("Unable to open bookmarks section");
                return result;
            }

            result = verifyQuestionsInBookmarksSectionNewUser(driver);
            if (!result) {
                myContentMsgList.add("The questions section in bookmark is not empty");
                return result;
            }

            result = verifyCurrentAffairsInBookmarksSectionNewUser(driver);
            if (!result) {
                myContentMsgList.add("The Current Affairs section in bookmark is not empty");
                return result;
            }

            result = verifyJobAlertsInBookmarksSectionNewUser(driver);
            if (!result) {
                myContentMsgList.add("The Job Alerts section in bookmark is not empty");
                return result;
            }

            result = verifyArticlesInBookmarksSectionNewUser(driver);
            if (!result) {
                myContentMsgList.add("The Articles section in bookmark is not empty");
                return result;
            }

            result = verifyVideosInBookmarksSectionNewUser(driver);
            if (!result) {
                myContentMsgList.add("The Videos section in bookmark is not empty");
                return result;
            }

            homeUtilObj = new HomePageUtil(driver);
            result = homeUtilObj.clickHomeBtn(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = homeUtilObj.clickStudyMaterialTab(driver);
            if (!result) {
                myContentMsgList.add("Unable to open study material tab");
                return result;
            }

            studyMaterialTabUtilObj = new StudyMaterialTabUtil(driver);
            strTitleCurrentAffairs = studyMaterialTabUtilObj.bookmarkCurrentAffairs(driver, strTitleCurrentAffairs,
                    result);
            if (strTitleCurrentAffairs == null) {
                myContentMsgList.add("Unable to bookmark Current Affairs or no current affairs post available");
            }

            strTitleJobAlerts = studyMaterialTabUtilObj.bookmarkJobAlerts(driver, strTitleJobAlerts, result);
            if (strTitleJobAlerts == null) {
                myContentMsgList.add("Unable to bookmark Job Alerts or no job alerts post available");
            }

            strTitleArticles = studyMaterialTabUtilObj.bookmarkArticles(driver, strTitleArticles, result);
            if (strTitleArticles == null) {
                myContentMsgList.add("Unable to bookmark Articles or no articles post available");
            }

            strTitleVideos = studyMaterialTabUtilObj.bookmarkVideos(driver, strTitleVideos, result);
            if (strTitleVideos == null) {
                myContentMsgList.add("Unable to bookmark Videos or no videos post available");
            }

            if ((strTitleCurrentAffairs == null) && (strTitleJobAlerts == null) && (strTitleArticles == null) && (strTitleVideos == null)) {

                myContentMsgList.add("All the diff types of bookmark posts not available.");
                return false;
            }

            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.add("Unable to open My Content section");
                return result;
            }

            result = clickOnBookmarksBtn(driver);
            if (!result) {
                myContentMsgList.add("Unable to open bookmark section in my content page");
                return result;
            }

            if (strTitleCurrentAffairs != null) {
                result = verifyCurrentAffairsInBookmarksSection(driver, strTitleCurrentAffairs);
                if (!result) {
                    myContentMsgList.add("Unable to verify Current Affairs in Bookmarks section");
                    return result;
                }
            }

            if (strTitleJobAlerts != null) {
                result = verifyJobAlertsInBookmarksSection(driver, strTitleJobAlerts);
                if (!result) {
                    myContentMsgList.add("Unable to verify Job Alerts in Bookmarks section");
                    return result;
                }
            }

            if (strTitleArticles != null) {
                result = verifyArticlesInBookmarksSection(driver, strTitleArticles);
                if (!result) {
                    myContentMsgList.add("Unable to verify Articles in Bookmarks section");
                    return result;
                }
            }

            if (strTitleVideos != null) {
                result = verifyVideosInBookmarksSection(driver, strTitleVideos);
                if (!result) {
                    myContentMsgList.add("Unable to verify Videos in Bookmarks section");
                    return result;
                }
            }
        } catch (Exception e) {
            myContentMsgList.add("verifyBookmarkSection_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyCurrentAffairsInBookmarksSection(AppiumDriver<MobileElement> driver,
                                                          String strTitleCurrentAffaris) {
        boolean result;
        try {

            cfObj.commonClick(myContentPageObj.getCurrentAffairsInBookmarksSection());
            if (ConfigFileReader.strEnv.equalsIgnoreCase("PRODUCTION")) {
                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "title", "id", 30);
                if (result) {
                    result = cfObj.commonVerifyValueTextBox(myContentPageObj.getListPackageTitle().get(0),
                            strTitleCurrentAffaris);
                }
            } else {
                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                        "//*[contains(@text, '" + strTitleCurrentAffaris + "')]", "xpath", 10);
            }

            cfObj.pressAndroidKey(driver, key.BACK, 1);

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifyCurrentAffairsInBookmarksSection_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean verifyJobAlertsInBookmarksSection(AppiumDriver<MobileElement> driver, String strTitleJobAlerts) {
        boolean result = true;
        try {

            cfObj.commonClick(myContentPageObj.getJobAlertsBookmarksSection());

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//*[contains(@text, '" + strTitleJobAlerts + "')]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("JobAlert title is not visible.");
            }
            cfObj.pressAndroidKey(driver, key.BACK, 1);

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifyJobAlertsInBookmarksSection_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean verifyArticlesInBookmarksSection(AppiumDriver<MobileElement> driver, String strTitleArticles) {
        boolean result = true;
        try {

            cfObj.commonClick(myContentPageObj.getArticlesBookmarksSection());

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//*[contains(@text, '" + strTitleArticles + "')]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Articles title is not visible.");
            }
            cfObj.pressAndroidKey(driver, key.BACK, 1);

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifyArticlesInBookmarksSection_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean verifyVideosInBookmarksSection(AppiumDriver<MobileElement> driver, String strTitleVideos) {
        boolean result = true;
        try {

            cfObj.commonClick(myContentPageObj.getVideoBookmarkdedSection());

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//*[contains(@text, '" + strTitleVideos + "')]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Videos title is not visible.");
            }
            cfObj.pressAndroidKey(driver, key.BACK, 1);

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifyVideosInBookmarksSection_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean verifyVideosInBookmarksSection(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            cfObj.commonClick(myContentPageObj.getVideoBookmarkdedSection());

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getBookMarkVideos(), 10);
            if (!result) {
                myContentMsgList.add("BookMarked video is not visible.");
                return result;
            }
            result = removedVideoPostFromBookMarkSection(driver);
            if (!result) {
                myContentMsgList.add("Not able to removed Video Post From BookMarkSection.");
                return result;
            }
            Thread.sleep(3000);

            cfObj.pressAndroidKey(driver, key.BACK, 1);

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifyVideosInBookmarksSection_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean removedMaterialFromBookMarkSection(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            Thread.sleep(2000);
            cfObj.commonClick(myContentPageObj.getBookmarkedMaterial().get(0));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "action_bookmark", "id", 10);
            if (!result) {
                myContentMsgList.add("BookMark icon is not visible.");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElement(driver, "action_bookmark", "id"));
            Thread.sleep(2000);
            result = cfObj.pressAndroidKey(driver, key.BACK, 1);
            if (!result) {
                myContentMsgList.add("Not able to press back button on material screen.");
                return result;
            }
            Thread.sleep(1000);

            if (!ConfigFileReader.strEnv.equalsIgnoreCase("PRODUCTION")) {
                result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getEmptyState(), 10);
                if (!result) {
                    myContentMsgList.add("Not able to remove material from BookMark section.");
                    return result;
                }
            } else {
                System.out.println("The logged In user might have other bookmarks");
            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("removedMaterialFromBookMarkSection_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean removedVideoPostFromBookMarkSection(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            Thread.sleep(2000);
            cfObj.commonClick(myContentPageObj.getBookMarkVideoDotIcon().get(0));

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getBookMarkTab(), 10);
            if (!result) {
                myContentMsgList.add("BookMarkTab is not visible.");
                return result;
            }
            cfObj.commonClick(myContentPageObj.getBookMarkTab());
            Thread.sleep(2000);

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getEmptyState(), 10);
            if (!result) {
                myContentMsgList.add("Not able to remove Video from BookMark section.");
                return result;
            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("removedVideoPostFromBookMarkSection_Exception: " + e.getMessage());
        }
        return result;
    }

    // -------------------------VERIFY DOUBT SECTION NEW
    // USER------------------------------------------------------

    public boolean verifyDoubtsSectionOnNewUser(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        String postContentDesc = null;
        try {

            loginUtilObj = new LoginUtil(driver);
            result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), true);
            if (!result) {
                myContentMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            homeUtilObj = new HomePageUtil(driver);
            result = homeUtilObj.clickHomeBtn(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.add("Unable to click my content btn");
                return result;
            }

            result = clickDoubtsBtn(driver);
            if (!result) {
                myContentMsgList.add("Unable to click Doubts btn ");
                return result;
            }

            result = verifyAnsweredInDoubtsSectionNewUser(driver);
            if (!result) {
                myContentMsgList.add("Unable to verify Answered section for new user");
                return result;
            }

            result = verifyAskedInDoubtsSectionNewUser(driver);
            if (!result) {
                myContentMsgList.add("Unable to verify Asked section for new user");
                return result;
            }

            result = verifyBookmarkedInDoubtsSectionNewUser(driver);
            if (!result) {
                myContentMsgList.add("Unable to verify Bookmarked section for new user");
                return result;
            }

            result = homeUtilObj.clickCommunityBtn(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            communityPageUtilObj = new CommunityPageUtil(driver);

            result = communityPageUtilObj.addNewAttachmentPost(driver);
            if (!result) {
                myContentMsgList.addAll(communityPageUtilObj.communityPageMsgList);
                myContentMsgList.add("Unable to add addNewAttachmentPost");
                return result;
            }

            Thread.sleep(4000);

            result = communityPageUtilObj.clickOnCommentBtn(driver);
            if (!result) {
                myContentMsgList.add("Unable to set click on comment btn");
                return result;
            }

            result = communityPageUtilObj.setCommentField(driver, "Automation comment");
            if (!result) {
                myContentMsgList.add("Unable to set comment field");
                return result;
            }

            result = communityPageUtilObj.clickOnSendCommentBtn(driver, "Automation comment");
            if (!result) {
                myContentMsgList.add("Unable to send comment");
                return result;
            }

            cfObj.pressAndroidKey(driver, key.BACK, 1);

            postContentDesc = communityPageUtilObj.savePost(driver, postContentDesc);
            if (postContentDesc == null) {
                myContentMsgList.add("Failed to save a post.");
                return false;
            }

            result = communityPageUtilObj.attemptCorrectMcqPost(driver);
            if (!result) {
                myContentMsgList.add("Unable to add Mcq Post");
                return result;
            }

            homeUtilObj = new HomePageUtil(driver);
            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.add("Unable to proceed to my Content page");
                return result;
            }

            result = verifyBookmarkedInDoubtSection(driver, postContentDesc, UserType.NewUser);
            if (!result) {
                myContentMsgList.add("Unable to verify bookmarked in doubts");
                return result;
            }

            result = verifyAskedInDoubtSection(driver, postContentDesc, UserType.NewUser);
            if (!result) {
                myContentMsgList.add("Unable to verify asked in doubts");
                return result;
            }

            result = verifyAnsweredInDoubtSection(driver, postContentDesc, UserType.NewUser);
            if (!result) {
                myContentMsgList.add("Unable to verify answered in doubts");
                return result;
            }

            result = verifyMcqInDoubtSection(driver, postContentDesc, UserType.NewUser);
            if (!result) {
                myContentMsgList.add("Unable to verify Mcq in doubts");
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("verifyDoubtsSectionOnNewUser_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyDoubtsSectionOnGuestUser(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        String postContentDesc = null;
        try {

            loginUtilObj = new LoginUtil(driver);
            result = loginUtilObj.verifyLoginGuestUser(driver);
            if (!result) {
                myContentMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            homeUtilObj = new HomePageUtil(driver);
            result = homeUtilObj.clickHomeBtn(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = homeUtilObj.clickCommunityBtn(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            communityPageUtilObj = new CommunityPageUtil(driver);
            result = communityPageUtilObj.addNewAttachmentPost(driver);
            if (!result) {
                myContentMsgList.addAll(communityPageUtilObj.communityPageMsgList);
                myContentMsgList.add("Unable to add addNewAttachmentPost");
                return result;
            }

            Thread.sleep(4000);

            result = communityPageUtilObj.clickOnCommentBtn(driver);
            if (!result) {
                myContentMsgList.add("Unable to set click on comment btn");
                return result;
            }

            result = communityPageUtilObj.setCommentField(driver, "Automation comment");
            if (!result) {
                myContentMsgList.add("Unable to set comment field");
                return result;
            }

            result = communityPageUtilObj.clickOnSendCommentBtn(driver, "Automation comment");
            if (!result) {
                myContentMsgList.add("Unable to send comment");
                return result;
            }

            cfObj.pressAndroidKey(driver, key.BACK, 1);

            postContentDesc = communityPageUtilObj.savePost(driver, postContentDesc);
            if (postContentDesc == null) {
                myContentMsgList.add("Failed to save a post.");
                return false;
            }

            result = communityPageUtilObj.attemptCorrectMcqPost(driver);
            if (!result) {
                myContentMsgList.add("Unable to add Mcq Post");
                return result;
            }

            homeUtilObj = new HomePageUtil(driver);
            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.add("Unable to proceed to my Content page");
                return result;
            }

            result = verifyBookmarkedInDoubtSection(driver, postContentDesc, UserType.GuestUser);
            if (!result) {
                myContentMsgList.add("Unable to verify bookmarked in doubts");
                return result;
            }

            result = verifyAskedInDoubtSection(driver, postContentDesc, UserType.GuestUser);
            if (!result) {
                myContentMsgList.add("Unable to verify asked in doubts");
                return result;
            }

            result = verifyAnsweredInDoubtSection(driver, postContentDesc, UserType.GuestUser);
            if (!result) {
                myContentMsgList.add("Unable to verify answered in doubts");
                return result;
            }

            result = verifyMcqInDoubtSection(driver, postContentDesc, UserType.GuestUser);
            if (!result) {
                myContentMsgList.add("Unable to verify Mcq in doubts");
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("verifyDoubtsSectionOnGuestUser_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean handleHintInsideContent(AppiumDriver<MobileElement> driver, String strPackage) {
        boolean result = true;
        try {

            driver.navigate().back();

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='Live Today']", "xpath", 5);
            if (!result) {

                HomePageUtil homePageUtilObj = new HomePageUtil(driver);
                result = homePageUtilObj.verifyViewAllContentInSelectedCourse(driver);
                if (!result){
                    myContentMsgList.addAll(homePageUtilObj.homeMsgList);
                    return result;
                }
            }
        } catch (Exception e) {
            myContentMsgList.add("handleHint_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyMahaPack_DownloadEbook(AppiumDriver<MobileElement> driver) {
        boolean result;
        homeUtilObj = new HomePageUtil(driver);
        LoginUtil loginUtilObj = new LoginUtil(driver);
        String productTitle = "DNC Mahapack 1";
        try {
            result = loginUtilObj.verifyLoginUsingEmailId(driver, "shu@yopmail.com", "amitpundir", true);
            if (!result) {
                myContentMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = clickOnPurchasedBtn(driver);
            if (!result) {
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//*[contains(@content-desc, 'Select Your Course')]", "xpath", 10);
            if (result) {
                cfObj.commonClick(cfObj.commonGetElement(driver,
                        "//*[contains(@content-desc,'" + productTitle + "')]", "xpath"));

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Selected Course\"]", "xpath", 20);
                if (!result) {
                    myContentMsgList.add("Selected course text is not visible or might be page is not loaded.");
                    return result;
                }
            }
            Thread.sleep(1000);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Switch courses anytime from the dropdown\"]", "xpath", 10);
            if (!result) {
                System.out.println("Unable to verify coach mark for mahapack.");
            } else {
                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"skipButton\n" + "SKIP\"]", "xpath", 10);
                if (!result) {
                    myContentMsgList.add("Skip btn is not visible");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"nextButton\n" + "NEXT\"]", "xpath", 10);
                if (!result) {
                    myContentMsgList.add("Next btn is not visible");
                    return result;
                }
                cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.View[@content-desc=\"skipButton\n" +
                        "SKIP\"]", "xpath"));
            }

            // To remove the fav batch marked
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//android.view.View[@content-desc=\"Your Favorite Batches\"]", "xpath", 30);
            if (result) {

                result = verifyFavBatchInMahapack(driver, true);
                if (!result) {
                    return result;
                }
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//android.view.View[@content-desc=\"Browse Batches by Exam\"]", "xpath", 30);
            if (!result) {
                myContentMsgList.add("Browse Batches by Exam text is not visible");
                return result;
            }

            result = verifyPickYourExamsInMahapack(driver);
            if (!result) {
                return result;
            }

            result = verifyFavBatchInMahapack(driver, false);
            if (!result) {
                return result;
            }

            result = verifyAllInOldPackagesPresentInExamInMahapack(driver);
            if (!result) {
                return result;
            }

            result = verifyAllInNewPackagesPresentInExamInMahapack(driver);
            if (!result) {
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("verifyMahaPack_DownloadEbook_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean verifyAllInNewPackagesPresentInExamInMahapack(AppiumDriver<MobileElement> driver) {
        boolean result;
        try {
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,\"Tab 1 of 6\")]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Tab 1 in maha package is not visible");
                return result;
            }

            cfObj.swipeLeftOnElement(cfObj.commonGetElement(driver, "//*[contains(@content-desc,\"Tab 1 of 6\")]", "xpath"), driver);
            cfObj.swipeLeftOnElement(cfObj.commonGetElement(driver, "//*[contains(@content-desc,\"Tab 2 of 6\")]", "xpath"), driver);
            cfObj.swipeLeftOnElement(cfObj.commonGetElement(driver, "//*[contains(@content-desc,\"Tab 3 of 6\")]", "xpath"), driver);
            cfObj.swipeLeftOnElement(cfObj.commonGetElement(driver, "//*[contains(@content-desc,\"Tab 4 of 6\")]", "xpath"), driver);
            cfObj.swipeLeftOnElement(cfObj.commonGetElement(driver, "//*[contains(@content-desc,\"Tab 5 of 6\")]", "xpath"), driver);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,\"Tab 6 of 6\")]", "xpath", 10);
            if (!result) {
                cfObj.swipeLeftOnElement(cfObj.commonGetElement(driver, "//*[contains(@content-desc,\"Tab 5 of 6\")]", "xpath"), driver);

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,\"Tab 6 of 6\")]", "xpath", 10);
                if (!result) {
                    myContentMsgList.add("Tab 6 of new package is not visible");
                    return result;
                }
            }
            cfObj.commonClick(cfObj.commonGetElement(driver, "//*[contains(@content-desc,\"Tab 6 of 6\")]", "xpath"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,\"batchClick\")]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Batches are not visible.");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElements(driver, "//android.view.View[contains(@content-desc,\"batchClick\")]", "xpath").get(0));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,'Batch Start Date :')]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Batch start date text is not visible in new package.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.backBtnInNewPackage(), 30);
            if (!result) {
                myContentMsgList.add("Back btn is not visible in new package.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.searchIconBtnInNewPackage(), 30);
            if (!result) {
                myContentMsgList.add("Search btn is not visible in new package.");
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("verifyAllInNewPackagesPresentInExamInMahapack_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean verifyFavBatchInMahapack(AppiumDriver<MobileElement> driver, boolean isFavMarked) {
        boolean result;
        try {
            if (isFavMarked) {

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                        "//android.view.View[@content-desc=\"Your Favorite Batches\"]/following-sibling::android.view.View/android.view.View[contains(@content-desc,\"batchClick\")]", "xpath", 30);
                if (!result) {
                    myContentMsgList.add("Fav batch is not visible when already liked");
                    return result;
                }

                result = cfObj.commonWaitForListOfElementsToBeVisible(driver, myContentPageObj.listOfLikeButtonInMahapack(), 10);
                if (!result) {
                    myContentMsgList.add("listOfLikeButtons are not visible, on fav batch.");
                    return result;
                }

                List<MobileElement> listOfLikeButtonsMain = myContentPageObj.listOfLikeButtonInMahapack();
                cfObj.commonClick(listOfLikeButtonsMain.get(0));

                Thread.sleep(3000);

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                        "//android.view.View[@content-desc=\"Your Favorite Batches\"]", "xpath", 4);
                if (result) {
                    myContentMsgList.add("Your fav batches text is visible, even after removing the batch which was liked already.");
                    return false;
                } else {
                    result = true;
                }
            } else {

                Thread.sleep(500);
                cfObj.smallScrollUtillNtimes(driver, 4, direction.DOWN);

                result = cfObj.commonWaitForListOfElementsToBeVisible(driver, myContentPageObj.listOfLikeButtonInMahapack(), 5);
                if (!result) {
                    cfObj.smallScrollUtillNtimes(driver, 2, direction.DOWN);

                    result = cfObj.commonWaitForListOfElementsToBeVisible(driver, myContentPageObj.listOfLikeButtonInMahapack(), 5);
                    if (!result) {
                        myContentMsgList.add("listOfLikeButtons are not visible, after scroll down again.");
                        return result;
                    }
                }

                List<MobileElement> listOfLikeButtons = myContentPageObj.listOfLikeButtonInMahapack();
                for (int i = 0; i < listOfLikeButtons.size(); i++) {

                    result = cfObj.commonWaitForElementToBeVisible(driver, listOfLikeButtons.get(i), 10);
                    if (!result) {
                        myContentMsgList.add("Like btn is not visible for batch: " + i);
                        return result;
                    }
                }
                cfObj.commonClick(listOfLikeButtons.get(0));

                cfObj.smallScrollUtillNtimes(driver, 6, direction.UP);

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                        "//android.view.View[@content-desc=\"Your Favorite Batches\"]", "xpath", 30);
                if (!result) {
                    cfObj.smallScrollUtillNtimes(driver, 2, direction.UP);

                    result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                            "//android.view.View[@content-desc=\"Your Favorite Batches\"]", "xpath", 30);
                    if (!result) {
                        myContentMsgList.add("Your fav batches text is not visible.");
                        return result;
                    }
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                        "//android.view.View[@content-desc=\"Your Favorite Batches\"]/following-sibling::android.view.View/android.view.View[contains(@content-desc,\"batchClick\")]", "xpath", 30);
                if (!result) {
                    myContentMsgList.add("Fav batch is not visible after liking it.");
                    return result;
                }

                result = cfObj.commonWaitForListOfElementsToBeVisible(driver, myContentPageObj.listOfLikeButtonInMahapack(), 10);
                if (!result) {
                    myContentMsgList.add("listOfLikeButtons are not visible, on fav batch.");
                    return result;
                }

                List<MobileElement> listOfLikeButtonsMain = myContentPageObj.listOfLikeButtonInMahapack();
                cfObj.commonClick(listOfLikeButtonsMain.get(0));

                Thread.sleep(3000);

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                        "//android.view.View[@content-desc=\"Your Favorite Batches\"]", "xpath", 4);
                if (result) {
                    myContentMsgList.add("Your fav batches text is visible, even after removing the batch.");
                    return false;
                } else {
                    result = true;
                }
            }
        } catch (Exception e) {
            myContentMsgList.add("verifyFavBatchInMahapack_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean verifyAllInOldPackagesPresentInExamInMahapack(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            cfObj.smallScrollUtillNtimes(driver, 4, direction.DOWN);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[contains(@content-desc,\"Tab\")]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Tab btns are not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,\"batchClick\")]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Batches are not visible.");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElements(driver, "//android.view.View[contains(@content-desc,\"batchClick\")]", "xpath").get(0));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/btn_done", "id", 10);
            if (!result) {
                System.out.println("Done btn coach mark is not visible.");
            } else {
                cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/btn_done", "id"));
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/searchTitle", "id", 10);
            if (!result) {
                System.out.println("searchTitle box is not visible");
            }

            // LiveClasses tab
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.LinearLayout[@content-desc=\"Live Classes\"]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Live classes tab is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/packageDescription", "id", 10);
            if (!result) {
                myContentMsgList.add("Packages tab desc at 1st position is not visible.");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElements(driver, "com.adda247.app:id/packageDescription", "id").get(0));

            LiveClassesPageUtil liveClassesUtilObj = new LiveClassesPageUtil(driver);
            result = liveClassesUtilObj.handledNotificationPopUp(driver);
            if (!result) {
                myContentMsgList.addAll(liveClassesUtilObj.liveClassMsgList);
                return result;
            }
            driver.navigate().back();

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/packageDescription", "id", 10);
            if (!result) {
                myContentMsgList.add("Packages tab desc at 1st position is not visible, after coming back");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElements(driver, "com.adda247.app:id/packageDescription", "id").get(0));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.TextView[@text='All Classes']", "xpath", 10);
            if (!result) {
                myContentMsgList.add("All classes text is not visible, after inside old package.");
                return result;
            }
            driver.navigate().back();

            // TestSeries tab
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.LinearLayout[@content-desc=\"Test Series\"]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Testseries tab is not visible.");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.LinearLayout[@content-desc=\"Test Series\"]", "xpath"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/packageDescription", "id", 10);
            if (!result) {
                myContentMsgList.add("Packages tab desc at 1st position is not visible.");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElements(driver, "com.adda247.app:id/packageDescription", "id").get(0));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.TextView[@text='GET QUIZ']", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Get Quiz text is not visible, after inside package.");
                return result;
            }
            driver.navigate().back();

            // Video Courses tab
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.LinearLayout[@content-desc=\"Video Courses\"]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("VideoCourses tab is not visible.");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.LinearLayout[@content-desc=\"Video Courses\"]", "xpath"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/packageDescription", "id", 10);
            if (!result) {
                myContentMsgList.add("Packages tab desc at 1st position is not visible.");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElements(driver, "com.adda247.app:id/packageDescription", "id").get(0));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/video_course_title_head", "id", 10);
            if (!result) {
                myContentMsgList.add("Video course section text is not visible, after inside package.");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElements(driver, "com.adda247.app:id/video_course_title_head", "id").get(0));

            driver.navigate().back();

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/video_course_title_head", "id", 10);
            if (!result) {
                myContentMsgList.add("Video course section text is not visible, after inside package.");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElements(driver, "com.adda247.app:id/video_course_title_head", "id").get(0));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.TextView[@text='All Videos']", "xpath", 10);
            if (!result) {
                myContentMsgList.add("All videos text is not visible, after inside old package.");
                return result;
            }
            driver.navigate().back();
            driver.navigate().back();

            //Ebooks tab
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.LinearLayout[@content-desc=\"EBooks\"]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("EBooks tab is not visible.");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.LinearLayout[@content-desc=\"EBooks\"]", "xpath"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/packageDescription", "id", 10);
            if (!result) {
                myContentMsgList.add("Packages tab desc at 1st position is not visible.");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElements(driver, "com.adda247.app:id/packageDescription", "id").get(0));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/lay_download", "id", 10);
            if (!result) {
                myContentMsgList.add("Download text is not visible, after inside package.");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElements(driver, "com.adda247.app:id/thumb", "id").get(0));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/save", "id", 10);
            if (!result) {
                System.out.println("Accept permission is not visible");
            } else {
                cfObj.commonClick(cfObj.commonGetElements(driver, "com.adda247.app:id/save", "id").get(0));
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/pdfView", "id", 10);
            if (!result) {
                myContentMsgList.add("Pdf view of ebook is not visible, after opening.");
                return result;
            }
            driver.navigate().back();

            cfObj.commonClick(cfObj.commonGetElements(driver, "com.adda247.app:id/lay_download", "id").get(0));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/iv_downloaded", "id", 30);
            if (!result) {
                myContentMsgList.add("Ebook downloaded is not visible.");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElements(driver, "com.adda247.app:id/iv_downloaded", "id").get(0));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.TextView[@text='Delete Ebook']", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Delete Ebook popup is not visible.");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElement(driver, "android:id/button1", "id"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/lay_download", "id", 10);
            if (!result) {
                myContentMsgList.add("Download text is not visible after deleting.");
                return result;
            }
            driver.navigate().back();

            //3d Learning tab
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.LinearLayout[@content-desc=\"3D Learning\"]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("3d Learning tab is not visible.");
                return result;
            }
            driver.navigate().back();

            result = cfObj.commonWaitForElementToBeVisible(driver, homeUtilObj.homePageObj.getBtnMyContent(), 30);
            if (!result) {
                myContentMsgList.add("Click on my content button, to close popup");
                return result;
            }
            cfObj.commonClick(homeUtilObj.homePageObj.getBtnMyContent());

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//android.view.View[@content-desc=\"Browse Batches by Exam\"]", "xpath", 30);
            if (!result) {
                myContentMsgList.add("Browse Batches by Exam text is not visible, after coming back from package.");
                result = true;
            }
        } catch (Exception e) {
            myContentMsgList.add("verifyAllInOldPackagesPresentInExamInMahapack_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean verifyPickYourExamsInMahapack(AppiumDriver<MobileElement> driver) {
        boolean result;
        try {

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"addExam\n" +
                    "Add Exams\"]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Add Exams btn is not visible.");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.View[@content-desc=\"addExam\n" +
                    "Add Exams\"]", "xpath"));

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.backBtnInNewPackage(), 30);
            if (!result) {
                myContentMsgList.add("Back Btn in pickYourExams is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.selectPickYourExamsText(), 30);
            if (!result) {
                myContentMsgList.add("selectPickYourExamsText is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.clearAllBtnInPriorityExams(), 30);
            if (!result) {
                myContentMsgList.add("clearAllBtn In pickYourExams is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.examsInputBox(), 30);
            if (!result) {
                myContentMsgList.add("examsInputBox in pickYourExams is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@content-desc=\"BACK\"]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("back btn at bottom in pickYourExams is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@content-desc=\"CONTINUE\"]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Continue btn at bottom in pickYourExams is not visible.");
                return result;
            }

            List<MobileElement> listingOfExamsSelected = myContentPageObj.examsSelectedListing();
            for (int i = 0; i < listingOfExamsSelected.size(); i++) {

                result = cfObj.commonWaitForElementToBeVisible(driver, listingOfExamsSelected.get(i), 30);
                if (!result) {
                    myContentMsgList.add("listingOfExamsSelected in pickYourExams is not visible.");
                    return result;
                }
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.clearAllBtnInPriorityExams(), 30);
            if (!result) {
                myContentMsgList.add("clear all btn in pickYourExams is not visible.");
                return result;
            }
            driver.navigate().back();

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"addExam\n" +
                    "Add Exams\"]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Add Exams btn is not visible after coming back from pickYourExams");
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("verifyPickYourExamsInMahapack_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public enum UserType {
        NewUser, GuestUser;
    }

    public boolean verifyAnsweredInDoubtsSectionNewUser(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getAnsweredDoubtsSection(), 30);
            if (!result) {
                myContentMsgList.add("AnsweredDoubtsSection is not visible.");
                return result;
            }
            cfObj.commonClick(myContentPageObj.getAnsweredDoubtsSection());
            cfObj.waitForPageLoading(driver, 10, 2000, myContentPageObj.getBtnGoToDoubts());

            // Verifying empty answered section
            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getTitleNoDoubtsFound(), 10)
                    || cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getBtnGoToDoubts(), 10);
            if (!result) {
                myContentMsgList.add("The Answered section section in doubts is not empty");
                return result;
            }

            cfObj.pressAndroidKey(driver, key.BACK, 1);

        } catch (Exception e) {
            myContentMsgList.add("verifyAnsweredInDoubtsSectionNewUser_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyAskedInDoubtsSectionNewUser(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getAskedDoubtsSection(), 30);
            if (!result) {
                myContentMsgList.add("AskedDoubtsSection is not visible.");
                return result;
            }

            cfObj.commonClick(myContentPageObj.getAskedDoubtsSection());

            cfObj.waitForPageLoading(driver, 6, 2000, myContentPageObj.getBtnGoToDoubts());

            // Verifying empty asked section
            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getTitleNoDoubtsFound(), 10)
                    || cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getBtnGoToDoubts(), 10);
            if (!result) {
                myContentMsgList.add("The asked section section in doubts is not empty");
                return result;
            }

            cfObj.pressAndroidKey(driver, key.BACK, 1);

        } catch (Exception e) {
            myContentMsgList.add("verifyAskedInDoubtsSectionNewUser_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyBookmarkedInDoubtsSectionNewUser(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getBookmarksDoubtsSection(), 30);
            if (!result) {
                myContentMsgList.add("BookmarksDoubtsSection is not visible.");
                return result;
            }
            // click bookmarked in doubts section
            cfObj.commonClick(myContentPageObj.getBookmarksDoubtsSection());

            cfObj.waitForPageLoading(driver, 6, 2000, myContentPageObj.getBtnGoToDoubts());
            // verifrying empty bookmarked section
            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getTitleNoDoubtsAreBookmarked(), 10)
                    || cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getBtnGoToDoubts(), 10);
            if (!result) {
                myContentMsgList.add("The bookmarked in doubts section is not empty for new user");
                return result;
            }

            cfObj.pressAndroidKey(driver, key.BACK, 1);

        } catch (Exception e) {
            myContentMsgList.add("verifyBookmarkedInDoubtsSectionNewUser_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyBookmarkedInDoubtSection(AppiumDriver<MobileElement> driver, String postContentDesc,
                                                  UserType userType) {
        boolean result = true;
        try {

            result = clickDoubtsBtn(driver);
            if (!result) {
                myContentMsgList.add("Unable to proceed to doubts page in my content section");
                return result;
            }

            // click bookmarked in doubts section
            cfObj.commonClick(myContentPageObj.getBookmarksDoubtsSection());

            Thread.sleep(3000); // application takes too much time to respond

            communityPageObj = new CommunityPage_OR();
            PageFactory.initElements(new AppiumFieldDecorator(driver), communityPageObj);

            if (userType.equals(UserType.NewUser)) {
                if (cfObj.commonGetElements(driver, "//android.widget.ImageView[@content-desc='openBottomSheet']",
                        "xpath").size() != 1) {
                    myContentMsgList.add("The number of Bookmarked post is more than one");
                    return false;
                }
            }

            // Creating a element for post
            MobileElement el = cfObj.getElementFromAttribute(driver, "text", postContentDesc);
            result = cfObj.commonWaitForElementToBeVisible(driver, el, 10);
            if (!result) {
                myContentMsgList.add("Unable to verify bookmarked post");
                return result;
            }

            cfObj.pressAndroidKey(driver, key.BACK, 1);

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifyAnsweredInDoubtSection_Exception:\n" + e.getMessage());
        }
        return result;
    }

    public boolean clickDoubtsBtn(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            cfObj.commonClick(myContentPageObj.getBtnDoubtSection());

            result = verifyDoubtsPage(driver);
            if (!result) {
                myContentMsgList.add("Failed to verify Doubts Page.");
            }

        } catch (Exception e) {
            myContentMsgList.add("clickDoubtsBtn_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyDoubtsPage(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getAskedDoubtsSection(), 10);
            if (!result) {
                myContentMsgList.add("Unable to verify asked section in doubts");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getAnsweredDoubtsSection(), 10);
            if (!result) {
                myContentMsgList.add("Unable to verify answered section in doubts");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getBookmarksDoubtsSection(), 10);
            if (!result) {
                myContentMsgList.add("Unable to verify Bookmarks section in doubts");
                return result;
            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("clickDoubtSectionTab Exception:\n" + e.getMessage());
        }
        return result;
    }

    public boolean verifyAskedInDoubtSection(AppiumDriver<MobileElement> driver, String postContentDesc,
                                             UserType userType) {
        boolean result = true;
        try {

            result = clickDoubtsBtn(driver);
            if (!result) {
                myContentMsgList.add("Unable to click Doubts btn");
                return result;
            }

            // click Asked in doubts section
            cfObj.commonClick(myContentPageObj.getAskedDoubtsSection());

            Thread.sleep(3000);

            if (userType.equals(UserType.NewUser)) {
                if (cfObj.commonGetElements(driver, "//android.widget.ImageView[@content-desc='openBottomSheet']",
                        "xpath").size() != 2) {
                    myContentMsgList.add("The number of Asked post is more than two Or content is not loaded.");
                    return false;
                }
            }

            // Creating a element for post
            MobileElement el = cfObj.getElementFromAttribute(driver, "text", postContentDesc);
            result = cfObj.commonWaitForElementToBeVisible(driver, el, 10);
            if (!result) {
                myContentMsgList.add("Unable to verify Asked post");
                return result;
            }

            cfObj.pressAndroidKey(driver, key.BACK, 1);

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifyAskedInDoubtSection_Exception:" + e.getMessage());
        }
        return result;
    }

    public boolean verifyAnsweredInDoubtSection(AppiumDriver<MobileElement> driver, String postContentDesc,
                                                UserType userType) {
        boolean result = true;
        try {

            result = clickDoubtsBtn(driver);
            if (!result) {
                myContentMsgList.add("Unable to click Doubts btn");
                return result;
            }

            // click answered in doubts section
            cfObj.commonClick(myContentPageObj.getAnsweredDoubtsSection());

            Thread.sleep(3000);

            if (userType.equals(UserType.NewUser)) {
                if (cfObj.commonGetElements(driver, "//android.widget.ImageView[@content-desc='openBottomSheet']",
                        "xpath").size() != 1) {
                    myContentMsgList.add("The number of Answered post is more than one");
                    return result;
                }
            }

            // Creating a element for post
            MobileElement el = cfObj.getElementFromAttribute(driver, "text", postContentDesc);
            result = cfObj.commonWaitForElementToBeVisible(driver, el, 10);
            if (!result) {
                myContentMsgList.add("Unable to verify Answered post");
                return result;
            }

            cfObj.pressAndroidKey(driver, key.BACK, 1);

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifyAnsweredInDoubtSection_Exception:\n" + e.getMessage());
        }
        return result;
    }

    public boolean verifyMcqInDoubtSection(AppiumDriver<MobileElement> driver, String postContentDesc,
                                           UserType userType) {
        boolean result = true;
        try {

            result = clickDoubtsBtn(driver);
            if (!result) {
                myContentMsgList.add("Unable to click Doubts btn");
                return result;
            }

            // click mcq in doubts section
            cfObj.commonClick(myContentPageObj.getMcqInDoubtsSection());

            Thread.sleep(3000);

            if (userType.equals(UserType.NewUser)) {
                if (cfObj.commonGetElements(driver, "//android.widget.ImageView[@content-desc='openBottomSheet']",
                        "xpath").size() != 1) {
                    myContentMsgList.add("The number of Mcq post is more than one");
                    return false;
                }
            }

            // Creating a element for post
            MobileElement el = cfObj.getElementFromAttribute(driver, "text", postContentDesc);
            result = cfObj.commonWaitForElementToBeVisible(driver, el, 10);
            if (!result) {
                myContentMsgList.add("Unable to verify Mcq post");
                return result;
            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifyAnsweredInDoubtSection_Exception:\n" + e.getMessage());
        }
        return result;
    }

    public boolean clickAskedInDoubtsSection(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            cfObj.commonClick(myContentPageObj.getAskedDoubtsSection());

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("clickAskedInDoubtsSection_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean verifyMyContentSearch(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        List<String> listPopularCourseTitle = new ArrayList<>();
        List<String> listSearchResults = new ArrayList<>();
        String userToken = null;
        homeUtilObj = new HomePageUtil(driver);
        loginUtilObj = new LoginUtil(driver);
        userLoginUtiliObj = new UserLoginUtil();
        myContentSearchApiObj = new MyContentSearchApiUtil();

        try {
            if (ConfigFileReader.strEnv.equalsIgnoreCase("production")){

                result = loginUtilObj.verifyLoginUsingEmailIdFromConfig(driver);
                if (!result) {
                    myContentMsgList.addAll(loginUtilObj.loginMsgList);
                    return result;
                }
            } else {
                result = loginUtilObj.verifyLoginGuestUser(driver);
                if (!result) {
                    myContentMsgList.addAll(loginUtilObj.loginMsgList);
                    return result;
                }
            }

            result = homeUtilObj.clickHomeBtn(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            if(ConfigFileReader.strEnv.equalsIgnoreCase("production")){

                listPopularCourseTitle.add("bank");
            } else {
                userToken = userLoginUtiliObj.getUserToken(cfrObj.getUserNamePassword().split("/")[0],
                        cfrObj.getUserNamePassword().split("/")[1]);
                if (userToken == null) {
                    myContentMsgList.add("User token is null");
                    return false;
                }

                listPopularCourseTitle = myContentSearchApiObj.getListOfPopularCourseTitle(userToken);
                if (listPopularCourseTitle == null) {
                    myContentMsgList.add("listPopularCourseTitle is null");
                    return false;
                }
            }

            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.add("Unable to open My Content Page");
                return result;
            }

            result = clickSearchIcon(driver);
            if (!result) {
                myContentMsgList.add("Unable to click search icon");
                return result;
            }

            result = verifyPopularCourseList(driver, listPopularCourseTitle);
            if (!result) {
                myContentMsgList.add("Popular course verification failed");
                return result;
            }

            result = enterValueInSearchFieldAndTapSearch(driver, listPopularCourseTitle.get(0));
            if (!result) {
                myContentMsgList.add("Unable to enter value and tap search successfully");
                return result;
            }

            if(ConfigFileReader.strEnv.equalsIgnoreCase("production")){

                listSearchResults.add("banking");
            } else {
                listSearchResults = myContentSearchApiObj.getListOfSearchResults(userToken, "Banking");
                if (listSearchResults == null) {

                    myContentMsgList.add("listSearchResults is null");
                    return false;
                }
            }

            result = verifySearchResults(driver, listSearchResults);
            if (!result) {
                myContentMsgList.add("Search results verification failed");
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("verifyMyContentSearch_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyMyContentPage(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getBtnPurchasedSection(), 10);
            if (!result) {
                myContentMsgList.add("Unable to verify purchased section btn");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getBtnDownloadSection(), 10);
            if (!result) {
                myContentMsgList.add("Unable to verify download section btn");
                return result;
            }

            if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")
                    && ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {

                result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getBtnDoubtSection(), 10);
                if (!result) {
                    myContentMsgList.add("Unable to verify doubts section btn");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getBtnBookmarkSection(), 10);
                if (!result) {
                    myContentMsgList.add("Unable to verify bookmark section btn");
                    return result;
                }
            }
        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifyMyContentPage_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean clickSearchIcon(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            cfObj.commonClick(myContentPageObj.getIconSearch());

            result = verifySearchPageUnderMyContent(driver);
            if (!result) {
                myContentMsgList.add("Failed to verify Search page under MyContent.");
            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("clickSearchIcon_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean verifySearchPageUnderMyContent(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
                result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getIconVoice(), 10);
                if (!result) {
                    myContentMsgList.add("Unable to verify voice icon in my content");
                    return result;
                }
            }
            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getOptionBack(), 10);
            if (!result) {
                myContentMsgList.add("Unable to verify back option in my content");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getFieldText(), 10);
            if (!result) {
                myContentMsgList.add("Unable to verify text field in my content");
                return result;
            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifySearchPageUnderMyContent_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean verifyPopularCourseList(AppiumDriver<MobileElement> driver, List<String> listCourseTitle) {
        boolean result = true;
        try {
            if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {

                List<MobileElement> listOfTitle = cfObj.commonGetElements(driver, "packageDescription", "id");

                System.out.println(listOfTitle.size());

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                        "//*[contains(@text,'" + listCourseTitle.get(0) + "')]", "xpath", 10);
                if (!result) {
                    myContentMsgList.add("Course is not visible.");
                    return result;
                }

            } else {
                List<MobileElement> listOfTitle = cfObj.commonGetElements(driver,
                        "//XCUIElementTypeStaticText[@name='Popular Courses']/following-sibling::XCUIElementTypeOther/descendant::XCUIElementTypeStaticText",
                        "xpath");

                System.out.println(listOfTitle.size());

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                        "//*[contains(@name,'" + listCourseTitle.get(0) + "')]", "xpath", 10);
                if (!result) {
                    myContentMsgList.add("Course is not visible.");
                    return result;
                }

            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifyPopularCourseList_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean enterValueInSearchFieldAndTapSearch(AppiumDriver<MobileElement> driver, String value) {
        boolean result = true;
        try {

            result = cfObj.commonSetTextTextBox(myContentPageObj.getFieldText(), value);
            if (!result) {
                myContentMsgList.add("Entered text does not match value");
                return result;
            }

            if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
                driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
            }

            result = verifyMyContentSearchResultsPage(driver);
            if (!result) {
                myContentMsgList.add("Unable to verify back option in my content");
                return result;
            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("enterValueInSearchFieldAndTapSearch_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean verifySearchResults(AppiumDriver<MobileElement> driver, List<String> listCourseTitle) {
        boolean result = true;
        try {

            int size;
            if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
                if (ConfigFileReader.strEnv.equalsIgnoreCase("Production")) {
                    size = listCourseTitle.size();
                } else {
                    if (listCourseTitle.size() > 8) {
                        size = 8;
                    } else {
                        size = listCourseTitle.size();
                    }
                }

                for (int i = 0; i < 1; i++) {

                    System.out.println(listCourseTitle.get(i));
                    result = cfObj.commonSetTextTextBox(myContentPageObj.getFieldText(), listCourseTitle.get(i));
                    if (!result) {
                        myContentMsgList.add("Entered text does not match value");
                        return result;
                    }
                    result = cfObj.commonGetElements(driver, "title", "id").get(0).getText()
                            .equalsIgnoreCase(listCourseTitle.get(i));
                    if (!result) {
                        myContentMsgList.add("Not able to get correct result by using search text field.");
                        return result;
                    }

                    cfObj.commonClick(cfObj.commonGetElement(driver, "clearSearchButton", "id"));

                    result = cfObj.commonVerifyValueTextBox(myContentPageObj.getFieldText(), "Find Subscribed Courses");
                    if (!result) {
                        myContentMsgList.add("Search Clear button is not working.");
                        return result;
                    }
                }
            } else {
                List<MobileElement> listOfTitle = cfObj.commonGetElements(driver,
                        "//XCUIElementTypeTextField[@name='storeSearchField']/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeOther[2]/descendant::XCUIElementTypeStaticText",
                        "xpath");

                System.out.println(listOfTitle.size());

                for (int i = 0; i < listOfTitle.size(); i++) {

                    System.out.println(listCourseTitle.get(i));

                    if (listOfTitle.get(i).getAttribute("name").contains(listCourseTitle.get(i))) {
                        System.out.println(listOfTitle.get(i).getAttribute("name") + " == " + listCourseTitle.get(i));
                    } else {
                        result = false;
                        break;
                    }
                }
            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifySearchResults_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean verifyMyContentSearchResultsPage(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getOptionBack(), 10);
            if (!result) {
                myContentMsgList.add("Unable to verify back option in search result");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getOptionVideos(), 10);
            if (!result) {
                myContentMsgList.add("Unable to verify videos btn in search result");
                // return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getOptionTestSeries(), 10);
            if (!result) {
                myContentMsgList.add("Unable to verify test series btn in search result");
                return result;

            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getOptionLiveClasses(), 10);
            if (!result) {
                myContentMsgList.add("Unable to verify live classes btn in search result");
                return result;

            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getOptionEbooks(), 10);
            if (!result) {
                myContentMsgList.add("Unable to verify ebooks btn in search result");
                return result;

            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getBtnFilter(), 10);
            if (!result) {
                myContentMsgList.add("Unable to verify filter btn in search result");
                return result;
            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifySearchPageUnderMyContent_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean clickTestSeriesFilterOption(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            cfObj.commonClick(myContentPageObj.getOptionTestSeries());

            result = verifyResultsAreTestSeriesOnly(driver);
            if (!result) {
                myContentMsgList.add("Failed to verify ResultsAreTestSeriesOnly.");
            }

            cfObj.commonClick(myContentPageObj.getOptionTestSeries());

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("clickTestSeriesFilterOption_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean verifyResultsAreTestSeriesOnly(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        int count = 0;
        try {
            List<MobileElement> course = cfObj.commonGetElements(driver, "title", "id");

            int size = course.size();
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    if (!course.get(i).getText().contains("Test Series")) {
                        count++;
                    }
                }
            } else {
                myContentMsgList.add("Unable to find text tag associated with a search result.");
                result = false;
            }
            if (count > 0) {
                myContentMsgList.add(
                        "At least " + count + " results in the first page list are not from Test Series category.");
                result = false;
            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifyResultsAreTestSeriesOnly_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean clickVideoCoursesFilterOption(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            cfObj.commonClick(myContentPageObj.getOptionVideos());

            result = verifyResultsAreVideoCoursesOnly(driver);
            if (!result) {
                myContentMsgList.add("Failed to verify ResultsAreVideoCoursesOnly.");
            }

            cfObj.commonClick(myContentPageObj.getOptionVideos());

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("clickVideoCoursesFilterOption_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean verifyResultsAreVideoCoursesOnly(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        int count = 0;
        try {
            List<MobileElement> course = cfObj.commonGetElements(driver, "title", "id");
            if (!course.isEmpty()) {
                for (int i = 0; i < course.size(); i++) {
                    if (!course.get(i).getText().contains("Video")) {
                        count++;
                    }
                }
            } else {
                myContentMsgList.add("Unable to find text tag associated with a search result.");
                result = false;
            }
            if (count > 0) {
                myContentMsgList.add(
                        "At least " + count + " results in the first page list are not from Video Courses category.");
                result = false;
            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifyResultsAreVideoCoursesOnly_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean clickEBooksFilterOption(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            cfObj.commonClick(myContentPageObj.getOptionEbooks());

            result = verifyResultsAreEBooksOnly(driver);
            if (!result) {
                myContentMsgList.add("Failed to verify ResultsAreEBooksOnly.");
            }
            cfObj.commonClick(myContentPageObj.getOptionEbooks());

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("clickEBooksFilterOption_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean verifyResultsAreEBooksOnly(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        int count = 0;
        try {
            List<MobileElement> course = cfObj.commonGetElements(driver, "title", "id");

            int size = course.size();
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    if (!course.get(i).getText().contains("EBooks")) {
                        count++;
                    }
                }
            } else {
                myContentMsgList.add("Unable to find text tag associated with a search result.");
                result = false;
            }
            if (count > 0) {
                myContentMsgList.add(
                        "At least " + count + " results in the first page list are not from Video Courses category.");
                result = false;
            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifyResultsAreVideoCoursesOnly_Exception:\n" + e.getMessage());
        }
        return result;
    }

    public boolean verifyResultsAreLiveClassesOnly(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        int count = 0;
        try {

            List<MobileElement> course = cfObj.commonGetElements(driver, "title", "id");

            int size = course.size();
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    if (!course.get(i).getText().contains("Live Classes")) {
                        count++;
                    }
                }
            } else {
                myContentMsgList.add("Unable to find text tag associated with a search result.");
                result = false;
            }
            if (count > 0) {
                myContentMsgList.add(
                        "At least " + count + " results in the first page list are not from Live classes category.");
                result = false;
            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifyResultsAreTestSeriesOnly_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean clickFilterBtnAndClosePanel(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            cfObj.commonClick(myContentPageObj.getBtnFilter());

            result = verifyFilterOptionsPanel(driver);
            if (!result) {
                myContentMsgList.add("Failed to verify FilterOptionsPanel.");
                return result;
            }
            cfObj.commonClick(myContentPageObj.getFilterResetBtn()); // close filter pane

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "filterButtonClk", "id", 10);
            if (!result) {
                myContentMsgList.add("Failed to close FilterOptionsPanel.");
            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("clickFilterBtnAndClosePanel_Exception:" + e.getMessage());
        }
        return result;
    }

    public boolean verifyFilterOptionsPanel(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getBtnProductCategory(), 10);
            if (!result) {
                myContentMsgList.add("Unable to verify Product Category in filter panel");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getTabExamName(), 10);
            if (!result) {
                myContentMsgList.add("Unable to verify exam Name tab in filter panel");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getTabPackages(), 10);
            if (!result) {
                myContentMsgList.add("Unable to verify Packages tab in filter panel");
                return result;

            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getTabFaculty(), 10);
            if (!result) {
                myContentMsgList.add("Unable to verify faculty tab in filter panel");
                return result;

            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getTabLanguage(), 10);
            if (!result) {
                myContentMsgList.add("Unable to verify language tab in filter panel");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getBtnReset(), 10);
            if (!result) {
                myContentMsgList.add("Unable to verify reset btn in filter panel");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getBtnApply(), 10);
            if (!result) {
                myContentMsgList.add("Unable to verify apply btn in search result");
                return result;
            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifyFilterOptionsPanel_Exception:" + e.getMessage());
        }
        return result;
    }

    public boolean verifyReportedQues(AppiumDriver<MobileElement> driver){
        boolean result = true;
        HomePageUtil homePageUtilObj = new HomePageUtil(driver);
        try{
            loginUtilObj = new LoginUtil(driver);
            result = loginUtilObj.verifyLoginUsingEmailId(driver, "sttt@yopmail.com", "amitpundir", true);
            if (!result) {
                myContentMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            result = homePageUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.addAll(homePageUtilObj.homeMsgList);
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/doubt_title", "id", 10);
            if (!result) {
                myContentMsgList.add("Doubts btn is not visible in my content");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/doubt_title", "id"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.TextView[@text='Reported Questions']", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Reported ques btn is not visible");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.TextView[@text='Reported Questions']", "xpath"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Reported Questions\"]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Reported ques heading is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Resolved\"]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Resolved btn is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"No content found\"]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("No content text is not visible");
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Pending\"]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Pending is not visible");
                return result;
            }

            cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.View[@content-desc=\"Pending\"]", "xpath"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,\"1)\")]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("First reported ques is not visible in pending");
                return result;
            }

            cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.View[contains(@content-desc,\"1)\")]", "xpath"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Pending\"]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Pending state is not visible in ques");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Your Doubt/Report: Wrong Translation, Out of Syllabus\"]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Your Doubt/Report text is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,\"Your Comment:\")]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Your Comment text is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.TextView[contains(@resource-id,\"content\")]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Content of ques is not visible");
                return result;
            }
        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifyReportedQues_Exception: " + e.getMessage());
        }
        return result;
    }

    // --------------------------VERIFY DOWNLOADED STUDY
    // MATERIAL-----------------------------------------

    public boolean verifyDownloadQuizzes(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        String strTitleQuizz;
        try {

            loginUtilObj = new LoginUtil(driver);
            result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), true);
            if (!result) {
                myContentMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            homeUtilObj = new HomePageUtil(driver);
            result = homeUtilObj.clickStudyMaterialTab(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            studyMaterialTabUtilObj = new StudyMaterialTabUtil(driver);
            strTitleQuizz = studyMaterialTabUtilObj.downloadDailyQuizzes(driver, result, true);
            if (strTitleQuizz == null) {
                myContentMsgList.add("Unable to download Daily Quizzes");
                myContentMsgList.addAll(studyMaterialTabUtilObj.studyMaterialMsgList);
                return false;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='ATTEMPT']", "xpath", 10);
            if (!result) {
                myContentMsgList.add("attempt btn is not visible");
                return result;

            } else {
                cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='ATTEMPT']", "xpath"));
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//android.widget.Button[@text='Start Test']", "xpath", 30);
            if (!result) {

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='ATTEMPT']", "xpath", 10);
                if (!result) {
                    myContentMsgList.add("attempt btn is not visible");
                    return result;
                } else {
                    cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='ATTEMPT']", "xpath"));

                    result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                            "//android.widget.Button[@text='Start Test']", "xpath", 30);
                    if (!result) {
                        myContentMsgList.add("Failed to click attempt button.");
                        return result;
                    }

                    cfObj.commonClick(
                            cfObj.commonGetElement(driver, "//android.widget.Button[@text='Start Test']", "xpath"));
                }
            } else {
                cfObj.commonClick(
                        cfObj.commonGetElement(driver, "//android.widget.Button[@text='Start Test']", "xpath"));
            }

            driver.navigate().back();

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='Yes']", "xpath", 30);
            if (result) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='Yes']", "xpath"));
            } else {
                return result;
            }

            result = cfObj.pressAndroidKey(driver, key.BACK, 2);
            if (!result) {
                myContentMsgList.add("Unable to press android back key");
                return result;
            }

            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = clickOnDownloadsBtn(driver);
            if (!result) {
                myContentMsgList.add("Unable to open Downloads in My content");
                return result;
            }

            result = clickQuizzesInDownloadSection(driver);
            if (!result) {
                myContentMsgList.add("Unable to open Quizzes in download section");
                return result;
            }

            result = verifyDownloadedQuizzWhileNetworkOn(driver, strTitleQuizz);
            if (!result) {
                myContentMsgList.add("Downloaded Quizzes verification failed with Network On");
                return result;
            }

            cfObj.pressAndroidKey(driver, key.BACK, 1);

            result = verifyDownloadedQuizzWhileNetworkOff(driver, strTitleQuizz);
            if (!result) {
                myContentMsgList.add("Downloaded Quizzes verification failed with Network Off");
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("verifyDownloadQuizzes_Exception: " + e.getMessage());
            result = false;
        } finally {
            cfObj.changeNetworkState(driver, NetworkState.ON);
        }
        return result;
    }

    public boolean clickQuizzesInDownloadSection(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            cfObj.commonClick(myContentPageObj.getQuizzesDownloadsSection());

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("clickQuizzesInDownloadSection_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean clickOnDownloadsBtn(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            cfObj.commonClick(myContentPageObj.getBtnDownloadSection());

            result = verifyDownloadsPage(driver);
            if (!result) {
                myContentMsgList.add("Failed to verify Downloads page.");
            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("clickOnDownloadsBtn_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean verifyDownloadsPage(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getQuizzesDownloadsSection(), 10);
            if (!result) {
                myContentMsgList.add("Unable to verify Quizzes in Downloads page");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getVideosDownloadSection(), 10);
            if (!result) {
                myContentMsgList.add("Unable to verify Videos in Downloads page");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getEBooksDownloadSection(), 10);
            if (!result) {
                myContentMsgList.add("Unable to verify EBooks in Downloads page");
                return result;
            }
            if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {

                result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getMagazinesDownloadSection(),
                        10);
                if (!result) {
                    myContentMsgList.add("Unable to verify Magazines in Downloads page");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getCapsulesDownloadSection(),
                        10);
                if (!result) {
                    myContentMsgList.add("Unable to verify Capsules in Downloads page");
                    return result;
                }
            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifyDownloadsPage_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean verifyDownloadedQuizzWhileNetworkOn(AppiumDriver<MobileElement> driver, String strTitleQuizz) {
        boolean result = true;
        try {

            String[] trimStr = strTitleQuizz.split(": ");

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//*[contains(@text, '" + trimStr[1] + "')]", "xpath", 10);
            if (result) {
                cfObj.commonClick(
                        cfObj.commonGetElement(driver, "//*[contains(@text, '" + trimStr[1] + "')]", "xpath"));

                if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"), 5)) {
                    cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"));
                }
            } else {
                myContentMsgList.add("The downloaded quiz is not available");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//*[contains(@text, '" + trimStr[1] + "')]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Unable to open downloaded quiz");
            }

        } catch (Exception e) {
            myContentMsgList.add("verifyDownloadedQuizWhileNetworkOn_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyDownloadedQuizzWhileNetworkOff(AppiumDriver<MobileElement> driver, String strTitleQuizz) {
        boolean result = true;
        try {

            cfObj.changeNetworkState(driver, NetworkState.OFF);

            String[] trimStr = strTitleQuizz.split(": ");

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//*[contains(@text, '" + trimStr[1] + "')]", "xpath", 10);
            if (result) {
                cfObj.commonClick(
                        cfObj.commonGetElement(driver, "//*[contains(@text, '" + trimStr[1] + "')]", "xpath"));
            } else {
                myContentMsgList.add("The downloaded quiz is not available");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//*[contains(@text, '" + trimStr[1] + "')]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Unable to open downloaded quiz");
            }

            cfObj.changeNetworkState(driver, NetworkState.ON);

        } catch (Exception e) {
            myContentMsgList.add("verifyDownloadedQuizWhileNetworkOff_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyDownloadEBooks(AppiumDriver<MobileElement> driver, String strTitlePackge) {
        boolean result = true;
        try {

            loginUtilObj = new LoginUtil(driver);
            homeUtilObj = new HomePageUtil(driver);
            childPackageUtilObj = new ChildPackageUtil(driver);

            if (ConfigFileReader.strEnv.equalsIgnoreCase("PRODUCTION")) {

                result = loginUtilObj.verifyLoginGuestUser(driver);
                if (!result) {
                    myContentMsgList.addAll(loginUtilObj.loginMsgList);
                }

                result = homeUtilObj.clickHomeBtn(driver);
                if (!result) {
                    myContentMsgList.addAll(homeUtilObj.homeMsgList);
                    return result;
                }

                result = homeUtilObj.clickMyContentButton(driver);
                if (!result) {
                    myContentMsgList.addAll(homeUtilObj.homeMsgList);
                }

                result = searchAndOpenPackage(driver, strTitlePackge);
                if (!result) {
                    myContentMsgList.add("Unable to open " + strTitlePackge);
                }

                strTitleEBook = childPackageUtilObj.dowloadEbook(driver, strTitleEBook);
                if (strTitleEBook == null) {
                    myContentMsgList.add("EBook title is null.");
                    return false;
                }

                cfObj.pressAndroidKey(driver, key.BACK, 2);

            } else {

                strTitlePackge = "PKS CHILD 12 Ebook | Video Course by Adda 247";

                result = loginUtilObj.verifyLoginUsingEmailId(driver, "downloadebook@yopmail.com", "1234567890", false);
                if (!result) {
                    myContentMsgList.addAll(loginUtilObj.loginMsgList);
                }

                result = homeUtilObj.clickMyContentButton(driver);
                if (!result) {
                    myContentMsgList.addAll(homeUtilObj.homeMsgList);
                }

                result = clickOnPurchasedBtn(driver);
                if (!result) {
                    myContentMsgList.add("Failed to click purchase button.");
                }

                result = clickOnSpecificPurchasedPackage(driver, strTitlePackge);
                if (!result) {
                    myContentMsgList.add("Failed to click purchased Package.");
                }

                result = homeUtilObj.verifyViewAllContentInSelectedCourse(driver);
                if (!result){
                    myContentMsgList.addAll(homeUtilObj.homeMsgList);
                    return result;
                }

                // Download EBook
                List<MobileElement> getAllDownloadBtn = myContentPageObj.getAllDownloadBtn();
                System.out.println(getAllDownloadBtn.size());

                for (int i = 0; i < getAllDownloadBtn.size(); i++) {

                    result = cfObj.commonWaitForElementToBeVisible(driver, getAllDownloadBtn.get(i), 10);
                    if (!result) {
                        myContentMsgList.add("EBook download button is not visible.");
                    }

                    cfObj.commonClick(getAllDownloadBtn.get(i));
                }

                Thread.sleep(30000);

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "iv_downloaded", "id", 30);
                if (!result) {
                    myContentMsgList.add("Failed to download EBook.");
                }

                strTitleEBook = cfObj.commonGetElements(driver,
                        "//*[contains(@resource-id,'rl_download_view')]/preceding-sibling::*[contains(@resource-id,'title')]",
                        "xpath").get(0).getText();
                if (strTitleEBook == null) {
                    myContentMsgList.add("EBook title is null.");
                    return false;
                }

                cfObj.pressAndroidKey(driver, key.BACK, 1);

                result = clickOnSpecificPurchasedPackage(driver, strTitlePackge);
                if (!result) {
                    myContentMsgList.add("Failed to click purchased Package.");
                }

                result = homeUtilObj.verifyViewAllContentInSelectedCourse(driver);
                if (!result){
                    myContentMsgList.addAll(homeUtilObj.homeMsgList);
                    return result;
                }

                Thread.sleep(20000);

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "iv_downloaded", "id", 30);
                if (!result) {
                    myContentMsgList.add("Failed to download EBook.");
                }

                strTitleEBook = cfObj.commonGetElements(driver,
                        "//*[contains(@resource-id,'rl_download_view')]/preceding-sibling::*[contains(@resource-id,'title')]",
                        "xpath").get(0).getText();
                if (strTitleEBook == null) {
                    myContentMsgList.add("EBook title is null.");
                    return false;
                }

                cfObj.pressAndroidKey(driver, key.BACK, 1);
            }

            result = clickOnDownloadsBtn(driver);
            if (!result) {
                myContentMsgList.add("Unable to open download section in My content page");
                return result;
            }

            result = clickEBooksInDownloadsSection(driver);
            if (!result) {
                myContentMsgList.add("Unable to open EBooks in download section");
                return result;
            }

            result = verifyDownloadedEBookWhileNetworkOn(driver, strTitlePackge);
            if (!result) {
                myContentMsgList.add("Downloaded EBook verification failed with Network On");
                return result;
            }

            cfObj.pressAndroidKey(driver, key.BACK, 2);

            result = verifyDownloadedEBookWhileNetworkOff(driver, strTitlePackge);
            if (!result) {
                myContentMsgList.add("Downloaded EBook verification failed with Network Off");
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("verifyDownloadEBooks_Exception: " + e.getMessage());
            result = false;
        } finally {
            cfObj.changeNetworkState(driver, NetworkState.ON);
        }
        return result;
    }

    public boolean downloadEBook(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            childPackageUtilObj = new ChildPackageUtil(driver);

            result = childPackageUtilObj.verifyEBooksPage(driver);
            if (!result) {
                myContentMsgList.add("Unable to verify EBook page");
                return result;
            }

            strTitleEBook = childPackageUtilObj.dowloadEbook(driver, strTitleEBook);
            if (strTitleEBook == null) {
                myContentMsgList.add("Failed to download EBook.");
            }

        } catch (Exception e) {
            myContentMsgList.add("downloadEBook_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean clickOnPurchasedBtn(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.waitForPageLoading(driver, 10, 2000, myContentPageObj.getBtnPurchasedSection());
            if (!result) {
                myContentMsgList.add("Purchase page Loading Error.");
                return result;
            }
            cfObj.commonClick(myContentPageObj.getBtnPurchasedSection());

            Thread.sleep(2000);

            HomePageUtil homePageUtil = new HomePageUtil(driver);
			if (cfObj.commonWaitForElementToBeVisible(driver, homePageUtil.homePageObj.freeLiveClassBottomBannerCloseIcon(), 7)) {
				cfObj.commonClick(homePageUtil.homePageObj.freeLiveClassBottomBannerCloseIcon());
			}
        } catch (Exception e) {
            myContentMsgList.add("clickOnPurchasedBtn_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyPurchasedPage(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            cfObj.smallScrollUtillNtimes(driver, 2, direction.DOWN);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//*[contains(@content-desc, 'Select Your Course')]", "xpath", 30);
            if (!result) {
                myContentMsgList.add("Unable to verify Select your course title in purchased page");
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("verifyPurchasedPage_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyEmptyPurchasedPage(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"No content found\"]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("No content found text in my content is not visible");
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("verifyPurchasedPage_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean clickOnPackageInPurchasedSection(AppiumDriver<MobileElement> driver, String strPackage) {
        boolean result = false;
        int i;
        int size = 0;
        try {

            size = myContentPageObj.getListPurchasedCourses().size();
            if (size == 0) {
                myContentMsgList.add("The purchased pacakge in not available in purchased section");
                return false;
            }

            for (i = 0; i < size; i++) {
                if (myContentPageObj.getListPurchasedCourses().get(i).getText().contains(strPackage)) {
                    cfObj.commonClick(myContentPageObj.getListPurchasedCourses().get(i));
                    result = true;
                    break;
                }
            }
        } catch (Exception e) {
            myContentMsgList.add("clickOnPackageInPurchasedSection_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean clickOnPackageInPurchasedSectionWithoutSearch(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//android.view.View[@content-desc=\"Your Purchased Courses\"]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Your Purchased Courses text is not visible");
                return result;
            }

            Thread.sleep(1000);

            cfObj.commonClick(myContentPageObj.getListPurchasedCourses().get(0));

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("clickOnPackageInPurchasedSection_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean clickEBooksInDownloadsSection(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            cfObj.commonClick(myContentPageObj.getEBooksDownloadSection());

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("clickEBooksInDownloadsSection_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean verifyDownloadedEBookWhileNetworkOn(AppiumDriver<MobileElement> driver, String strTitlePackage) {
        boolean result = true;
        try {
            // Verifying Ebook package in download section

            if (ConfigFileReader.strEnv.equalsIgnoreCase("Production")) {
                if (myContentPageObj.getListPackageTitle().get(0).getText().equalsIgnoreCase(strTitlePackage)) {
                    cfObj.commonClick((myContentPageObj.getListPackageTitle().get(0)));
                } else {
                    myContentMsgList.add("The downloaded EBook is not available");
                    return result;
                }
            } else if (ConfigFileReader.strEnv.equalsIgnoreCase("Staging")) {
                if (strTitlePackage.contains(myContentPageObj.getListPackageTitle().get(0).getText())) {
                    cfObj.commonClick((myContentPageObj.getListPackageTitle().get(0)));
                } else {
                    myContentMsgList.add("The downloaded EBook is not available");
                    return result;
                }
            }

            // Verifying Ebook inside the Ebook package
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//*[contains(@text, '" + strTitleEBook + "')]", "xpath", 10);
            if (result) {
                cfObj.commonClick(
                        cfObj.commonGetElement(driver, "//*[contains(@text, '" + strTitleEBook + "')]", "xpath"));
                Thread.sleep(1000);
                cfObj.commonClick(cfObj.getElementFromAttribute(driver, "text", "ACCEPT"));

            } else {
                myContentMsgList.add("The Ebook package has no Ebook inside");
                return result;
            }

            result = !(cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//*[contains(@text, '" + strTitleEBook + "')]", "xpath", 5));
            if (!result) {
                myContentMsgList.add("Unable to open downloaded EBook");
            }

        } catch (Exception e) {
            myContentMsgList.add("verifyDownloadedEBookWhileNetworkOn_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyDownloadedEBookWhileNetworkOff(AppiumDriver<MobileElement> driver, String strTitlePackage) {
        boolean result = true;

        try {

            result = cfObj.changeNetworkState(driver, NetworkState.OFF);
            if (!result) {
                myContentMsgList.add("Unable to turn of connections");
                return result;
            }

            // Verifying Ebook package in download section

            if (ConfigFileReader.strEnv.equalsIgnoreCase("Production")) {
                if (myContentPageObj.getListPackageTitle().get(0).getText().equalsIgnoreCase(strTitlePackage)) {
                    cfObj.commonClick((myContentPageObj.getListPackageTitle().get(0)));
                } else {
                    myContentMsgList.add("The downloaded EBook is not available");
                    return result;
                }
            } else if (ConfigFileReader.strEnv.equalsIgnoreCase("Staging")) {
                if (strTitlePackage.contains(myContentPageObj.getListPackageTitle().get(0).getText())) {
                    cfObj.commonClick((myContentPageObj.getListPackageTitle().get(0)));
                } else {
                    myContentMsgList.add("The downloaded EBook is not available");
                    return result;
                }
            }

            // Verifying Ebook inside the Ebook package
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//*[contains(@text, '" + strTitleEBook + "')]", "xpath", 10);
            if (result) {
                cfObj.commonClick(
                        cfObj.commonGetElement(driver, "//*[contains(@text, '" + strTitleEBook + "')]", "xpath"));
            } else {
                myContentMsgList.add("The Ebook package has no Ebook inside");
                return result;
            }

            result = !(cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//*[contains(@text, '" + strTitleEBook + "')]", "xpath", 5));
            if (!result) {
                myContentMsgList.add("Unable to open downloaded EBook");
            }

            result = cfObj.changeNetworkState(driver, NetworkState.ON);
            if (!result) {
                myContentMsgList.add("Unable to turn of connections");
                return result;
            }

        } catch (Exception e) {
            myContentMsgList.add("verifyDownloadedEBookWhileNetworkOff_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyDownloadMagazines(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        String strTitleMagazines = null;
        try {

            loginUtilObj = new LoginUtil(driver);

            result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), false);
            if (!result) {
                myContentMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            homeUtilObj = new HomePageUtil(driver);
            result = homeUtilObj.clickStudyMaterialTab(driver);
            if (!result) {
                myContentMsgList.add("Unable to open study material tab");
                return result;
            }

            studyMaterialTabUtilObj = new StudyMaterialTabUtil(driver);
            strTitleMagazines = studyMaterialTabUtilObj.downloadFreePdf(driver, strTitleMagazines);
            if (strTitleMagazines == null) {
                myContentMsgList.addAll(studyMaterialTabUtilObj.studyMaterialMsgList);
                return false;
            }

            result = cfObj.pressAndroidKey(driver, key.BACK, 2);
            if (!result) {
                myContentMsgList.add("Unable to press android back key");
                return result;
            }

            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.add("Unable to open my content section");
                return result;
            }

            result = clickOnDownloadsBtn(driver);
            if (!result) {
                myContentMsgList.add("Unable to open download section");
                return result;
            }

            result = clickMagazinesInDownloadSection(driver);
            if (!result) {
                myContentMsgList.add("Unable to open Magazine in download section");
                return result;
            }

            result = verifyDownloadedMagazinesWhileNetworkOn(driver, strTitleMagazines);
            if (!result) {
                myContentMsgList.add("Downloaded Magazine verification failed with Network on");
                return result;
            }

            cfObj.pressAndroidKey(driver, key.BACK, 1);

            result = verifyDownloadedMagazinesWhileNetworkOff(driver, strTitleMagazines);
            if (!result) {
                myContentMsgList.add("Downloaded Magazine verification failed with Network Off");
                return result;
            }

        } catch (Exception e) {
            myContentMsgList.add("verifyDownloadMagazines_Exception: " + e.getMessage());
            result = false;
        } finally {
            cfObj.changeNetworkState(driver, NetworkState.ON);
        }
        return result;
    }

    public boolean clickMagazinesInDownloadSection(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            cfObj.commonClick(myContentPageObj.getMagazinesDownloadSection());

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("clickMagazinesInDownloadSection_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean verifyDownloadedMagazinesWhileNetworkOn(AppiumDriver<MobileElement> driver,
                                                           String strTitleMagazines) {
        boolean result = true;
        try {
            commonStudyMaterialSectionUtilObj = new CommonStudyMaterialSectionUtil(driver);
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//*[contains(@text, '" + strTitleMagazines + "')]", "xpath", 10);
            if (result) {
                cfObj.commonClick(
                        cfObj.commonGetElement(driver, "//*[contains(@text, '" + strTitleMagazines + "')]", "xpath"));
            } else {
                myContentMsgList.add("The downloaded Magazine is not available");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver,
                    commonStudyMaterialSectionUtilObj.commonStudyMaterialSectionORObj.getPowerCapsuleShareBtn(), 10);
            if (!result) {
                myContentMsgList.add("Unable to open downloaded Magazine");
            }

        } catch (Exception e) {
            myContentMsgList.add("verifyDownloadedMagazinesWhileNetworkOn_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyDownloadedMagazinesWhileNetworkOff(AppiumDriver<MobileElement> driver,
                                                            String strTitleMagazines) {
        boolean result = true;
        try {

            cfObj.changeNetworkState(driver, NetworkState.OFF);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//*[contains(@text, '" + strTitleMagazines + "')]", "xpath", 10);
            if (result) {
                cfObj.commonClick(
                        cfObj.commonGetElement(driver, "//*[contains(@text, '" + strTitleMagazines + "')]", "xpath"));
            } else {
                myContentMsgList.add("The downloaded Magazine is not available");
                return result;
            }

            commonStudyMaterialSectionUtilObj = new CommonStudyMaterialSectionUtil(driver);
            result = cfObj.commonWaitForElementToBeVisible(driver,
                    commonStudyMaterialSectionUtilObj.commonStudyMaterialSectionORObj.getPowerCapsuleShareBtn(), 10);
            if (!result) {
                myContentMsgList.add("Unable to open downloaded Magazine");
            }

            cfObj.changeNetworkState(driver, NetworkState.ON);

        } catch (Exception e) {
            myContentMsgList.add("verifyDownloadedQuizWhileNetworkOff_Exception: " + e.getMessage());
            result = false;
        } finally {
            cfObj.changeNetworkState(driver, NetworkState.ON);
        }
        return result;
    }

    public boolean verifyDownloadCapsules(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        String strTitleCapsules = null;
        try {

            loginUtilObj = new LoginUtil(driver);

            result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), true);
            if (!result) {
                myContentMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            homeUtilObj = new HomePageUtil(driver);
            if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {

                result = homeUtilObj.clickStudyMaterialTab(driver);
                if (!result) {
                    myContentMsgList.add("Unable to open study material tab");
                    return result;
                }
            }
            studyMaterialTabUtilObj = new StudyMaterialTabUtil(driver);

            strTitleCapsules = studyMaterialTabUtilObj.downloadPowerCapsules(driver, strTitleCapsules);
            if (strTitleCapsules == null) {
                myContentMsgList.add("Unable to download Daily Quizzes");
                return false;
            }

            result = cfObj.pressAndroidKey(driver, key.BACK, 1);
            if (!result) {
                myContentMsgList.add("Unable to press android back key");
                return result;
            }

            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.add("Unable to open My content section");
                return result;
            }

            result = clickOnDownloadsBtn(driver);
            if (!result) {
                myContentMsgList.add("Unable to open Downloads in My content");
                return result;
            }

            result = clickCapsulesInDownloadSection(driver);
            if (!result) {
                myContentMsgList.add("Unable to open Capsule in download section");
                return result;
            }

            result = verifyDownloadedCapsulesWhileNetworkOn(driver, strTitleCapsules);
            if (!result) {
                myContentMsgList.add("Downloaded Capsule verification failed with Network On");
                return result;
            }

            result = cfObj.pressAndroidKey(driver, key.BACK, 1);
            if (!result) {
                myContentMsgList.add("Unable to press android back key");
                return result;
            }

            result = verifyDownloadedCapsulessWhileNetworkOff(driver, strTitleCapsules);
            if (!result) {
                myContentMsgList.add("Downloaded Capsule verification failed with Network Off");
                return result;
            }

        } catch (Exception e) {
            myContentMsgList.add("verifyDownloadCapsules_Exception: " + e.getMessage());
            result = false;
        } finally {
            cfObj.changeNetworkState(driver, NetworkState.ON);
        }
        return result;
    }

    public boolean clickCapsulesInDownloadSection(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            cfObj.commonClick(myContentPageObj.getCapsulesDownloadSection());

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("clickCapsulesInDownloadSection_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean verifyDownloadedCapsulesWhileNetworkOn(AppiumDriver<MobileElement> driver, String strTitleCapsules) {
        boolean result = true;
        try {

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//*[contains(@text, '" + strTitleCapsules + "')]", "xpath", 10);
            if (result) {
                cfObj.commonClick(
                        cfObj.commonGetElement(driver, "//*[contains(@text, '" + strTitleCapsules + "')]", "xpath"));
            } else {
                myContentMsgList.add("The downloaded Capsule is not available");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "action_share", "id", 10);
            if (!result) {
                myContentMsgList.add("Share btn not visible");
                result = true;
            }

        } catch (Exception e) {
            myContentMsgList.add("verifyDownloadedMagazinesWhileNetworkOn_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyDownloadedCapsulessWhileNetworkOff(AppiumDriver<MobileElement> driver,
                                                            String strTitleCapsules) {
        boolean result = true;
        try {

            cfObj.changeNetworkState(driver, NetworkState.OFF);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//*[contains(@text, '" + strTitleCapsules + "')]", "xpath", 10);
            if (result) {
                cfObj.commonClick(
                        cfObj.commonGetElement(driver, "//*[contains(@text, '" + strTitleCapsules + "')]", "xpath"));
            } else {
                myContentMsgList.add("The downloaded Capsule is not available");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/action_share", "id",
                    10);
            if (!result) {
                myContentMsgList.add("Unable to open downloaded Capsule");
            }

            cfObj.changeNetworkState(driver, NetworkState.ON);

        } catch (Exception e) {
            myContentMsgList.add("verifyDownloadedQuizWhileNetworkOff_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean clickOnVideosDownloadSection(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getVideosDownloadSection(), 10);
            if (!result) {
                myContentMsgList.add("VideosDownloadSection is not visible.");
                return result;
            }
            cfObj.commonClick(myContentPageObj.getVideosDownloadSection());

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("VideosDownloadSection_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean validateDownloadedVideoIsPresent(AppiumDriver<MobileElement> driver, String downloadedVideo) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getDownloadVideoTitle(), 10);
            if (!result) {
                myContentMsgList.add("DownloadVideoTitle is not visible.");
                return result;
            }
            result = cfObj.commonVerifyValueTextBox(myContentPageObj.getDownloadVideoTitle(), downloadedVideo);
            if (!result) {
                myContentMsgList.add("Downloaded video title is not matching.");
                return result;
            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("DownloadedVideoIsPresent_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean validateDownloadedVideo(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getDownloadVideoTitle(), 10);
            if (!result) {
                myContentMsgList.add("DownloadVideoTitle is not visible.");
                return result;
            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("validateDownloadedVideo_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean validateDownloadedVideoIsNotPresent(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = !(cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getDownloadVideoTitle(), 10));
            if (!result) {
                myContentMsgList.add("DownloadVideoTitle is visible.");
                return result;
            }
            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getDownloadedVideoEmptyStateTitle(),
                    10);
            if (!result) {
                myContentMsgList.add("DownloadedVideoEmptyStateTitle is not present.");
                return result;
            }
        } catch (Exception e) {
            result = false;
            myContentMsgList.add("DownloadedVideoIsNotPresent_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean clickOnDownloadedVideo(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getDownloadVideoTitle(), 10);
            if (!result) {
                myContentMsgList.add("DownloadVideoTitle is not visible.");
                return result;
            }
            cfObj.commonClick(myContentPageObj.getDownloadVideoTitle());

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("ClickOnDownloadedVideo_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean clickOnGoToPurchasedBtn(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getGoToPurchasedBtn(), 10);
            if (!result) {
                myContentMsgList.add("GoToPurchasedBtn is not visible.");
                return result;
            }
            cfObj.commonClick(myContentPageObj.getGoToPurchasedBtn());

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("ClickOnGoToPurchasedBtn_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean clickOnLiveVideoCell(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            if (cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getSkipBtn(), 30)) {
                cfObj.commonClick(myContentPageObj.getSkipBtn());
            }
            if (cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getLiveVideoCell(), 10)) {
                cfObj.commonClick(myContentPageObj.getLiveVideoCell());
            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("ClickOnLiveVideoCell_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean clickOnSkipBtn(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            if (cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getSkipBtn(), 30)) {
                cfObj.commonClick(myContentPageObj.getSkipBtn());
            }
        } catch (Exception e) {
            result = false;
            myContentMsgList.add("̑ClickOnSkipBtn_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean searchAndOpenPackage(AppiumDriver<MobileElement> driver, String strPackageTitle) {
        boolean result = true;
        int i = 0;
        try {

            cfObj.commonClick(myContentPageObj.getIconSearch());

            result = verifySearchPageUnderMyContent(driver);
            if (!result) {
                myContentMsgList.add("Failed to verify SearchPageUnderMyContent.");
                return result;
            }

            result = cfObj.commonSetTextTextBox(myContentPageObj.getFieldText(), strPackageTitle);
            if (!result) {
                myContentMsgList.add("Failed to enter package title on textfield.");
                return result;
            }

            for (i = 0; i < 5; i++) {
                System.out.println(myContentPageObj.getListPackageTitle().get(i).getText());
                if (myContentPageObj.getListPackageTitle().get(i).getText().equalsIgnoreCase(strPackageTitle)) {
                    myContentPageObj.getListPackageTitle().get(i).click();
                    break;
                }
            }
            if (i == 5) {
                myContentMsgList.add("The package is not visible in search resutl");
            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("searchAndOpenPackage_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean clickOnPurchasedPackage(AppiumDriver<MobileElement> driver, String strPackage) {
        boolean result = true;
        int i;
        int size = 0;
        try {

            size = myContentPageObj.getListPurchasedCourses().size();
            if (size == 0) {
                myContentMsgList.add("The purchased package in not available in purchased section");
                return false;
            }

            for (i = 0; i < size; i++) {
                if (myContentPageObj.getListPurchasedCourses().get(i).getText().contains(strPackage)) {
                    cfObj.commonClick(myContentPageObj.getListPurchasedCourses().get(i));
                    break;
                }
            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("clickOnPackageInPurchasedSection_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean clickOnSpecificPurchasedPackage(AppiumDriver<MobileElement> driver, String strPackage) {
        boolean result = true;
        try {
            int loop = 0;
            if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {

                while (!cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                        "//*[contains(@content-desc,'" + strPackage + "')]", "xpath", 5)) {

                    cfObj.smallScrollUtillNtimes(driver, 1, direction.DOWN);

                    if (loop > 10) {
                        myContentMsgList.add("Package is not available.");
                        return false;
                    }
                    loop++;
                }
                if (ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {

                    cfObj.commonClick(cfObj.commonGetElement(driver,
                            "//*[contains(@content-desc,'" + strPackage + "')]", "xpath"));
                } else {
                    cfObj.commonClick(driver, "//*[contains(@content-desc,'" + strPackage + "')]", "xpath");
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'" + strPackage + "')]", "xpath", 6);
                if (result) {

                    cfObj.commonClick(cfObj.commonGetElement(driver,
                            "//*[contains(@content-desc,'" + strPackage + "')]", "xpath"));
                } else {
                    result = true;
                }

            } else {
                while (!cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                        "//*[contains(@name,'" + strPackage + "')]", "xpath", 10)) {

                    cfObj.scrollUtill(driver, 1, direction.DOWN);
                    if (loop > 9) {
                        System.out.println("Package is not available.");
                        myContentMsgList.add("Package is not available.");
                        return false;
                    }
                    loop++;
                }

                cfObj.commonClick(driver, "//*[contains(@name,'" + strPackage + "')]", "xpath");
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"viewAllContentClicked\n" +
                    "VIEW ALL CONTENT\"]", "xpath", 5);
            if (!result) {
                System.out.println("View all content btn is not visible.");

                driver.navigate().back();

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"viewAllContentClicked\n" +
                        "VIEW ALL CONTENT\"]", "xpath", 5);
                if (!result) {

                    cfObj.scrollUtill(driver, 2, direction.DOWN);

                    result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"viewAllContentClicked\n" +
                            "VIEW ALL CONTENT\"]", "xpath", 5);
                    if (!result) {

                        myContentMsgList.add("The package list is open or content page is not opened.");
                        return result;
                    }
                    cfObj.scrollUpUsingTouchAction(driver);
                }
            }
        } catch (Exception e) {
            result = false;
            myContentMsgList.add("clickOnSpecificPurchasedPackage_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean verifyMockTestStoreFront(AppiumDriver<MobileElement> driver, String packageName,
                                            boolean isCalculator) {
        boolean result = true;
        String mockName;
        int mockTestId;
        try {
            mockTestApiUtilObj = new MockTestApiUtil();
            result = mockTestApiUtilObj.createMockTestStoreFront(isCalculator);
            if (!result) {
                myContentMsgList.addAll(mockTestApiUtilObj.mockTestApiMsgList);
                return result;
            }
            mockName = MockTestApiUtil.strFixedMockName;
            if (mockName == null) {
                myContentMsgList.add("MockName is null.");
                return false;
            }
            mockTestId = Integer.parseInt(MockTestApiUtil.strFixedMockTestId);

            packageApiUtilObj = new PackageApiUtil();
            result = packageApiUtilObj.assignTestSeriesToPackage(1, mockTestId,
                    Integer.parseInt(configObj.getPackageId()));
            if (!result) {
                myContentMsgList.addAll(packageApiUtilObj.packagetApiMsgList);
                return result;
            }
            System.out.println("----->" + mockName);

            loginUtilObj = new LoginUtil(driver);
            result = loginUtilObj.verifyLoginUsingEmailId(driver, "abcd123@gmail.com", "amitpundir", true);
            if (!result) {
                myContentMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            homeUtilObj = new HomePageUtil(driver);
            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = clickOnPurchasedBtn(driver);
            if (!result) {
                myContentMsgList.add("Not able to click Purchased button.");
                return result;
            }

            result = clickOnSpecificPurchasedPackage(driver, packageName);
            if (!result) {
                myContentMsgList.add("Not able to click Package in PurchasedSection.");
                return result;
            }

            result = homeUtilObj.verifyViewAllContentInSelectedCourse(driver);
            if (!result){
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            childPackageUtilObj = new ChildPackageUtil(driver);
            result = cfObj.waitForPageLoading(driver, 5, 3000,
                    cfObj.commonGetElement(driver, "//*[contains(@text,'" + mockName
                                    + "')]/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='GET QUIZ']",
                            "xpath"));
            if (!result) {
                driver.navigate().back();

                result = homeUtilObj.verifyViewAllContentInSelectedCourse(driver);
                if (!result){
                    myContentMsgList.addAll(homeUtilObj.homeMsgList);
                    return result;
                }

                result = cfObj.waitForPageLoading(driver, 5, 3000,
                        cfObj.commonGetElement(driver, "//*[contains(@text,'" + mockName
                                        + "')]/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='GET QUIZ']",
                                "xpath"));
                if (!result) {
                    myContentMsgList.add("Unable to Load test series Page even after retry.");
                    return result;
                }
            }

            result = childPackageUtilObj.verifyTestSeriesPage(driver);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.validateSpecificMockTitle(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.downloadSpecificQuiz(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.clickOnSpecificAttemptLink(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            commonTestUtilObj = new CommonTestUtil(driver);
            result = commonTestUtilObj.verifyInstructionPage(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.completeAndSubmitTest(driver, isCalculator, false, false);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            System.out.println("Test Submitted");
            Thread.sleep(3000);

            result = commonTestUtilObj.validateMockTestResultScreen(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver,
                    commonTestUtilObj.commonTestPageObj.getBtnNavigateUp(), 10);
            if (!result) {
                myContentMsgList.add("getBtnNavigateUp not visible");
                return result;
            }

            cfObj.commonClick(commonTestUtilObj.commonTestPageObj.getBtnNavigateUp());

            result = childPackageUtilObj.clickOnSpecificResultStatus(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = commonTestUtilObj.validateTestSolutionBtn(driver, isCalculator, false, false);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }
        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifyMockTestStoreFront_Exception: " + e.getMessage());
        } finally {
            System.out.println("---------------------");
            mockTestApiUtil.edit_deleteTestSeriesForPackageId48219();
        }
        return result;
    }

    public boolean verifyAttemptAndReAttemptMockTestWithOutInternet(AppiumDriver<MobileElement> driver,
                                                                    String packageName, boolean calculatorStatus) {
        boolean result = true;
        String mockName;
        int mockTestId;
        try {

            mockTestApiUtilObj = new MockTestApiUtil();
            result = mockTestApiUtilObj.createMockTestStoreFront(false);
            if (!result) {
                myContentMsgList.add("Not able to create MockTestStoreFront with calculator false.");
                return result;
            }
            mockName = MockTestApiUtil.strFixedMockName;
            if (mockName == null) {
                myContentMsgList.add("MockName is null.");
                return false;
            }
            mockTestId = Integer.parseInt(MockTestApiUtil.strFixedMockTestId);

            packageApiUtilObj = new PackageApiUtil();
            result = packageApiUtilObj.assignTestSeriesToPackage(1, mockTestId,
                    Integer.parseInt(configObj.getPackageId()));
            if (!result) {
                myContentMsgList.addAll(packageApiUtilObj.packagetApiMsgList);
                return result;
            }
            System.out.println("----->" + mockName);

            loginUtilObj = new LoginUtil(driver);
            result = loginUtilObj.verifyLoginUsingEmailId(driver, "abcd123@gmail.com", "amitpundir", true);
            if (!result) {
                myContentMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            homeUtilObj = new HomePageUtil(driver);
            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = clickOnPurchasedBtn(driver);
            if (!result) {
                myContentMsgList.add("Not able to click Purchased button.");
                return result;
            }

            result = clickOnSpecificPurchasedPackage(driver, packageName);
            if (!result) {
                myContentMsgList.add("Not able to click Package in PurchasedSection.");
                return result;
            }

            result = homeUtilObj.verifyViewAllContentInSelectedCourse(driver);
            if (!result){
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            childPackageUtilObj = new ChildPackageUtil(driver);

            result = cfObj.waitForPageLoading(driver, 10, 2000, cfObj.commonGetElement(driver, "//*[contains(@text,'"
                            + mockName
                            + "')]/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='GET QUIZ']",
                    "xpath"));
            if (!result) {
                myContentMsgList.add("Unable to Load testseries Page.");
            }

            result = childPackageUtilObj.verifyTestSeriesPage(driver);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.validateSpecificMockTitle(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.downloadSpecificQuiz(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            Thread.sleep(2000);

            result = cfObj.changeNetworkState(driver, NetworkState.OFF);
            if (!result) {
                myContentMsgList.add("Unable to turn of connections");
                return result;
            }

            result = childPackageUtilObj.clickOnSpecificAttemptLink(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            commonTestUtilObj = new CommonTestUtil(driver);

            result = commonTestUtilObj.verifyInstructionPage(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.completeAndSubmitTest(driver, calculatorStatus, false, false);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.handleTestRatingPopUp(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            if (!ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {

                Thread.sleep(2000);
                result = commonTestUtilObj.verifyAndCloseCoinPopup(driver);
                if (!result) {
                    myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                    return result;
                }
            }

            result = commonTestUtilObj.verifyTestAnalysisDailyQuizPage(driver, true);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = childPackageUtilObj.clickOnSpecificResultStatus(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }
            if (!ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {

                Thread.sleep(2000);
                result = commonTestUtilObj.verifyAndCloseCoinPopup(driver);
                if (!result) {
                    myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                    return result;
                }
            }

            result = commonTestUtilObj.clickOnReAttemptBtn(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.verifyInstructionPage(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.completeAndSubmitReAttemptTest(driver, calculatorStatus, false, false);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.handleTestRatingPopUp(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            if (!ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {

                Thread.sleep(2000);
                result = commonTestUtilObj.verifyAndCloseCoinPopup(driver);
                if (!result) {
                    myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                    return result;
                }
            }

            result = commonTestUtilObj.verifyTestAnalysisDailyQuizPage(driver, true);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifyAttemptMockTestWithOutInternet_Exception" + e.getMessage());
        } finally {
            result = cfObj.changeNetworkState(driver, NetworkState.ON);
            if (!result) {
                myContentMsgList.add("Not able to turn ON the internet");
            }
            System.out.println("---------------------");
            mockTestApiUtil.edit_deleteTestSeriesForPackageId48219();
        }
        return result;
    }

    public boolean verifyResumeMockTest(AppiumDriver<MobileElement> driver, String packageName) {
        boolean result = true;
        String mockName;
        int mockTestId;
        try {
            mockTestApiUtilObj = new MockTestApiUtil();
            result = mockTestApiUtilObj.createMockTestStoreFront(false);
            if (!result) {
                myContentMsgList.add("Not able to create MockTestStoreFront- verifyResumeMockTest");
                return result;
            }
            mockName = MockTestApiUtil.strFixedMockName;
            if (mockName == null) {
                myContentMsgList.add("MockName is null.");
                return false;
            }
            mockTestId = Integer.parseInt(MockTestApiUtil.strFixedMockTestId);

            packageApiUtilObj = new PackageApiUtil();
            result = packageApiUtilObj.assignTestSeriesToPackage(1, mockTestId,
                    Integer.parseInt(configObj.getPackageId()));
            if (!result) {
                myContentMsgList.addAll(packageApiUtilObj.packagetApiMsgList);
                return result;
            }
            System.out.println("----->" + mockName);

            loginUtilObj = new LoginUtil(driver);
            result = loginUtilObj.verifyLoginUsingEmailId(driver, "abcd123@gmail.com", "amitpundir", true);
            if (!result) {
                myContentMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }
            homeUtilObj = new HomePageUtil(driver);
            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = clickOnPurchasedBtn(driver);
            if (!result) {
                myContentMsgList.add("Not able to click Purchased button.");
                return result;
            }

            result = clickOnSpecificPurchasedPackage(driver, packageName);
            if (!result) {
                myContentMsgList.add("Not able to click Package in PurchasedSection.");
                return result;
            }

            result = homeUtilObj.verifyViewAllContentInSelectedCourse(driver);
            if (!result){
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            childPackageUtilObj = new ChildPackageUtil(driver);
            result = cfObj.waitForPageLoading(driver, 5, 3000, cfObj.commonGetElement(driver, "//*[contains(@text,'"
                            + mockName
                            + "')]/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='GET QUIZ']",
                    "xpath"));
            if (!result) {
                driver.navigate().back();

                result = homeUtilObj.verifyViewAllContentInSelectedCourse(driver);
                if (!result){
                    myContentMsgList.addAll(homeUtilObj.homeMsgList);
                    return result;
                }

                result = cfObj.waitForPageLoading(driver, 5, 3000, cfObj.commonGetElement(driver, "//*[contains(@text,'"
                                + mockName
                                + "')]/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='GET QUIZ']",
                        "xpath"));
                if (!result) {
                    myContentMsgList.add("Unable to Load test series Page even after retry.");
                    return result;
                }
            }

            result = childPackageUtilObj.verifyTestSeriesPage(driver);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.validateSpecificMockTitle(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.downloadSpecificQuiz(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.clickOnSpecificAttemptLink(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            commonTestUtilObj = new CommonTestUtil(driver);

            result = commonTestUtilObj.verifyInstructionPage(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.validateResumeStateFlow(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.handleTestRatingPopUp(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.verifyAndCloseCoinPopup(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            Thread.sleep(2000);

            result = commonTestUtilObj.verifyTestAnalysisDailyQuizPage(driver, false);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }
            result = childPackageUtilObj.clickOnSpecificResultStatus(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            Thread.sleep(2000);

            result = commonTestUtilObj.validateShareScoreCardLink(driver, mockName);
            if (!result) {
                myContentMsgList.add("Unable to validate ShareScoreCardLink.");
                return result;
            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifyResumeMockTest_Exception" + e.getMessage());
        } finally {
            System.out.println("---------------------");
            mockTestApiUtil.edit_deleteTestSeriesForPackageId48219();
        }
        return result;
    }

    public boolean findAndOpenPackage(AppiumDriver<MobileElement> driver, String strPackageTitle) {
        boolean result = true;
        String lastPackage = "temp";
        List<MobileElement> listPackages;
        try {

            while (true) {
                // If scrolling ends
                listPackages = myContentPageObj.getListPackageDescription();
                if (lastPackage.equalsIgnoreCase(listPackages.get(listPackages.size() - 1).getAttribute("text"))) {
                    myContentMsgList.add("Pacakage not found");
                    return false;
                }

                lastPackage = listPackages.get(listPackages.size() - 1).getAttribute("text");
                if (lastPackage == null) {
                    myContentMsgList.add("last package is null.");
                    return false;
                }

                // Finding Package
                for (int i = 0; i < listPackages.size(); i++) {
                    if (listPackages.get(i).getAttribute("text").equalsIgnoreCase(strPackageTitle)) {
                        Thread.sleep(1000);
                        listPackages.get(i).click();
                        return true;
                    }
                }

                cfObj.scrollUtill(driver, 1, direction.DOWN);

            }
        } catch (Exception e) {
            result = false;
            myContentMsgList.add("searchAndOpenPackage_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean verifyCutOffMockTest(AppiumDriver<MobileElement> driver, String packageName) {
        boolean result = true;
        String mockName;
        int mockTestId;
        try {
            mockTestApiUtilObj = new MockTestApiUtil();
            result = mockTestApiUtilObj.createCutOffMockTest();
            if (!result) {
                myContentMsgList.add("Not able to create CutOff MockTest.");
                return result;
            }
            mockName = MockTestApiUtil.strFixedMockName;
            if (mockName == null) {
                myContentMsgList.add("MockName is null.");
                return false;
            }
            mockTestId = Integer.parseInt(MockTestApiUtil.strFixedMockTestId);

            packageApiUtilObj = new PackageApiUtil();
            result = packageApiUtilObj.assignTestSeriesToPackage(1, mockTestId,
                    Integer.parseInt(configObj.getPackageId()));
            if (!result) {
                myContentMsgList.addAll(packageApiUtilObj.packagetApiMsgList);
                return result;
            }
            System.out.println("----->" + mockName);

            loginUtilObj = new LoginUtil(driver);
            result = loginUtilObj.verifyLoginUsingEmailId(driver, "abcd123@gmail.com", "amitpundir", true);
            if (!result) {
                myContentMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            homeUtilObj = new HomePageUtil(driver);
            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = clickOnPurchasedBtn(driver);
            if (!result) {
                myContentMsgList.add("Not able to click Purchased button.");
                return result;
            }

            result = clickOnSpecificPurchasedPackage(driver, packageName);
            if (!result) {
                myContentMsgList.add("Not able to click Package in PurchasedSection.");
                return result;
            }

            result = homeUtilObj.verifyViewAllContentInSelectedCourse(driver);
            if (!result){
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            childPackageUtilObj = new ChildPackageUtil(driver);
            result = cfObj.waitForPageLoading(driver, 5, 3000, cfObj.commonGetElement(driver, "//*[contains(@text,'"
                            + mockName
                            + "')]/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='GET QUIZ']",
                    "xpath"));
            if (!result) {
                driver.navigate().back();

                result = homeUtilObj.verifyViewAllContentInSelectedCourse(driver);
                if (!result){
                    myContentMsgList.addAll(homeUtilObj.homeMsgList);
                    return result;
                }

                result = cfObj.waitForPageLoading(driver, 5, 3000, cfObj.commonGetElement(driver, "//*[contains(@text,'"
                                + mockName
                                + "')]/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='GET QUIZ']",
                        "xpath"));
                if (!result) {
                    myContentMsgList.add("Unable to Load test series Page even after retry.");
                    return result;
                }
            }

            result = childPackageUtilObj.verifyTestSeriesPage(driver);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.validateSpecificMockTitle(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.verifySpecificCutOffTag(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.downloadSpecificQuiz(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.clickOnSpecificAttemptLink(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            commonTestUtilObj = new CommonTestUtil(driver);

            result = commonTestUtilObj.verifyInstructionPage(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.validateCutOffMarksInstruction(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.completeAndSubmitTest(driver, false, false, false);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.validateCutOffLayOut(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            Thread.sleep(2000);
            result = commonTestUtilObj.verifyTestAnalysisDailyQuizPage(driver, false);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = childPackageUtilObj.clickOnSpecificResultStatus(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifyCutOffMockTest_Exception" + e.getMessage());
        } finally {
            System.out.println("---------------------");
            mockTestApiUtil.edit_deleteTestSeriesForPackageId48219();
        }
        return result;
    }

    public boolean verifyMultiLanguageMockTest(AppiumDriver<MobileElement> driver, String packageName,
                                               boolean isCalculator) {
        boolean result = true;
        String mockName;
        int mockTestId;
        try {
            mockTestApiUtilObj = new MockTestApiUtil();
            result = mockTestApiUtilObj.createMultiLanguageMockTestStoreFront(isCalculator);
            if (!result) {
                myContentMsgList.add("Not able to create MultiLanguage MockTest.");
                return result;
            }
            mockName = MockTestApiUtil.strFixedMockName;
            if (mockName == null) {
                myContentMsgList.add("MockName is null.");
                return false;
            }
            mockTestId = Integer.parseInt(MockTestApiUtil.strFixedMockTestId);

            packageApiUtilObj = new PackageApiUtil();
            result = packageApiUtilObj.assignTestSeriesToPackage(1, mockTestId,
                    Integer.parseInt(configObj.getPackageId()));
            if (!result) {
                myContentMsgList.addAll(packageApiUtilObj.packagetApiMsgList);
                return result;
            }
            System.out.println("----->" + mockName);

            loginUtilObj = new LoginUtil(driver);
            result = loginUtilObj.verifyLoginUsingEmailId(driver, "abcd123@gmail.com", "amitpundir", true);
            if (!result) {
                myContentMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            homeUtilObj = new HomePageUtil(driver);
            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = clickOnPurchasedBtn(driver);
            if (!result) {
                myContentMsgList.add("Not able to click Purchased button.");
                return result;
            }

            result = clickOnSpecificPurchasedPackage(driver, packageName);
            if (!result) {
                myContentMsgList.add("Not able to click Package in PurchasedSection.");
                return result;
            }

            result = homeUtilObj.verifyViewAllContentInSelectedCourse(driver);
            if (!result){
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            childPackageUtilObj = new ChildPackageUtil(driver);
            result = cfObj.waitForPageLoading(driver, 5, 3000, cfObj.commonGetElement(driver, "//*[contains(@text,'"
                            + mockName
                            + "')]/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='GET QUIZ']",
                    "xpath"));
            if (!result) {
                driver.navigate().back();

                result = homeUtilObj.verifyViewAllContentInSelectedCourse(driver);
                if (!result){
                    myContentMsgList.addAll(homeUtilObj.homeMsgList);
                    return result;
                }

                result = cfObj.waitForPageLoading(driver, 5, 3000, cfObj.commonGetElement(driver, "//*[contains(@text,'"
                                + mockName
                                + "')]/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='GET QUIZ']",
                        "xpath"));
                if (!result) {
                    myContentMsgList.add("Unable to Load test series Page even after retry.");
                    return result;
                }
            }

            result = childPackageUtilObj.verifyTestSeriesPage(driver);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.validateSpecificMockTitle(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.verifySpecificCutOffTag(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.downloadSpecificQuiz(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.clickOnSpecificAttemptLink(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            commonTestUtilObj = new CommonTestUtil(driver);

            result = commonTestUtilObj.verifyInstructionPage(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.validateCutOffMarksInstruction(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.clickOnLanguageTabAndSelectLanguage(driver, 0);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            int questionCount = Integer
                    .parseInt(cfObj.commonGetElement(driver, "com.adda247.app:id/total_question_count", "id").getText());

            result = commonTestUtilObj.clickStartTestBtn(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            Thread.sleep(2000); // page loading takes time

            cfObj.handleHints(driver);

            result = commonTestUtilObj.takeAndSubmitTest(driver, isCalculator, true, questionCount, false);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.verifyAndSubmitFinishTestPopUp(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            // result=commonTestUtilObj.handleTestRatingPopUp(driver);
            // if (!result) {
            // myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
            // return result;
            // }
            //
            // result=commonTestUtilObj.verifyAndCloseCoinPopup(driver);
            // if (!result) {
            // myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
            // return result;
            // }

            result = commonTestUtilObj.validateCutOffLayOut(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            Thread.sleep(2000);
            // if(ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {
            //
            // result=cfObj.compareTwoImages(driver,
            // "TestSeries/verifyMultiLanguageMockTest_ResultOverview",
            // "TestSeries/verifyCutOffMockTest_ResultOverview", 95);
            // if (!result) {
            // myContentMsgList.add("Not able to compare Images.");
            // return result;
            // }
            // }
            result = commonTestUtilObj.verifyTestAnalysisDailyQuizPage(driver, false);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = childPackageUtilObj.clickOnSpecificResultStatus(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            Thread.sleep(2000);
            // if(ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {
            //
            // result=cfObj.compareTwoImages(driver,
            // "TestSeries/verifyMultiLanguageMockTest_ResultOverview",
            // "TestSeries/verifyCutOffMockTest_ResultOverview", 95);
            // if (!result) {
            // myContentMsgList.add("Not able to compare Images.");
            // return result;
            // }
            // }

            result = commonTestUtilObj.clickOnReAttemptBtn(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.verifyInstructionPage(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.validateCutOffMarksInstruction(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.clickOnLanguageTabAndSelectLanguage(driver, 1);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.completeAndSubmitReAttemptTest(driver, isCalculator, true, false);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }
            // result=commonTestUtilObj.handleTestRatingPopUp(driver);
            // if (!result) {
            // myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
            // return result;
            // }

            Thread.sleep(2000);
            // if(ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {
            //
            // result=cfObj.compareTwoImages(driver,
            // "TestSeries/verifyMultiLanguageMockTest_ResultOverview",
            // "TestSeries/verifyCutOffMockTest_ResultOverview", 95);
            // if (!result) {
            // myContentMsgList.add("Not able to compare Images.");
            // return result;
            // }
            // }
            result = commonTestUtilObj.validateTestSolutionBtn(driver, isCalculator, true, false);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifyMultiLanguageMockTest_Exception" + e.getMessage());
        } finally {
            System.out.println("---------------------");
            mockTestApiUtil.edit_deleteTestSeriesForPackageId48219();
        }
        return result;
    }

    public boolean verifyMultiQuestionMockTest(AppiumDriver<MobileElement> driver, String packageName,
                                               boolean isCalculator) {
        boolean result = true;
        String mockName;
        int mockTestId;
        try {
            mockTestApiUtilObj = new MockTestApiUtil();
            result = mockTestApiUtilObj.createAllTypeofMockTestStoreFront(isCalculator);
            if (!result) {
                myContentMsgList.add("Not able to create AllType MockTest.");
                return result;
            }
            mockName = MockTestApiUtil.strFixedMockName;
            if (mockName == null) {
                myContentMsgList.add("Mockname is null.");
                return false;
            }
            mockTestId = Integer.parseInt(MockTestApiUtil.strFixedMockTestId);

            packageApiUtilObj = new PackageApiUtil();
            result = packageApiUtilObj.assignTestSeriesToPackage(1, mockTestId,
                    Integer.parseInt(configObj.getPackageId()));
            if (!result) {
                myContentMsgList.addAll(packageApiUtilObj.packagetApiMsgList);
                return result;
            }
            System.out.println("----->" + mockName);

            loginUtilObj = new LoginUtil(driver);
            result = loginUtilObj.verifyLoginUsingEmailId(driver, "abcd123@gmail.com", "amitpundir", true);
            if (!result) {
                myContentMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }
            homeUtilObj = new HomePageUtil(driver);
            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = clickOnPurchasedBtn(driver);
            if (!result) {
                myContentMsgList.add("Not able to click Purchased button.");
                return result;
            }

            result = clickOnSpecificPurchasedPackage(driver, packageName);
            if (!result) {
                myContentMsgList.add("Not able to click Package in PurchasedSection.");
                return result;
            }

            result = homeUtilObj.verifyViewAllContentInSelectedCourse(driver);
            if (!result){
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            childPackageUtilObj = new ChildPackageUtil(driver);
            result = cfObj.waitForPageLoading(driver, 5, 3000, cfObj.commonGetElement(driver, "//*[contains(@text,'"
                            + mockName
                            + "')]/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='GET QUIZ']",
                    "xpath"));
            if (!result) {
                driver.navigate().back();

                result = homeUtilObj.verifyViewAllContentInSelectedCourse(driver);
                if (!result){
                    myContentMsgList.addAll(homeUtilObj.homeMsgList);
                    return result;
                }

                result = cfObj.waitForPageLoading(driver, 5, 3000, cfObj.commonGetElement(driver, "//*[contains(@text,'"
                                + mockName
                                + "')]/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='GET QUIZ']",
                        "xpath"));
                if (!result) {
                    myContentMsgList.add("Unable to Load test series Page even after retry.");
                    return result;
                }
            }

            result = childPackageUtilObj.verifyTestSeriesPage(driver);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.validateSpecificMockTitle(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.verifySpecificCutOffTag(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.downloadSpecificQuiz(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.clickOnSpecificAttemptLink(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            commonTestUtilObj = new CommonTestUtil(driver);

            result = commonTestUtilObj.verifyInstructionPage(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.validateCutOffMarksInstruction(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            int questionCount = Integer
                    .parseInt(cfObj.commonGetElement(driver, "com.adda247.app:id/total_question_count", "id").getText());

            result = commonTestUtilObj.clickStartTestBtn(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            Thread.sleep(2000); // page loading takes time
            result = commonTestUtilObj.takeAndSubmitMultiQuestionTest(driver, isCalculator, false, questionCount);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.verifyAndSubmitFinishTestPopUp(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.validateCutOffLayOut(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.verifyTestAnalysisDailyQuizPage(driver, false);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = childPackageUtilObj.clickOnSpecificResultStatus(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = commonTestUtilObj.verifyTestAnalysisDailyQuizPage(driver, false);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }
        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifyMultiQuestionMockTest_Exception" + e.getMessage());
        } finally {
            System.out.println("---------------------");
            mockTestApiUtil.edit_deleteTestSeriesForPackageId48219();
        }
        return result;
    }

    public boolean verifySectionalTimeLimitMockTest(AppiumDriver<MobileElement> driver, String packageName,
                                                    boolean isCalculator) {
        boolean result = true;
        String mockName;
        int mockTestId;
        try {
            mockTestApiUtilObj = new MockTestApiUtil();
            result = mockTestApiUtilObj.createSectionalTimeLimitMockTestStoreFront("MCQ Sankalp.docx");
            if (!result) {
                myContentMsgList.add("Not able to create Sectional TimeLimit MockTest.");
                return result;
            }

            mockName = MockTestApiUtil.strFixedMockName;
            if (mockName == null) {
                myContentMsgList.add("Mock name is null.");
                return false;
            }

            mockTestId = Integer.parseInt(MockTestApiUtil.strFixedMockTestId);

            packageApiUtilObj = new PackageApiUtil();
            result = packageApiUtilObj.assignTestSeriesToPackage(1, mockTestId,
                    Integer.parseInt(configObj.getPackageId()));
            if (!result) {
                myContentMsgList.addAll(packageApiUtilObj.packagetApiMsgList);
                return result;
            }

            System.out.println("----->" + mockName);

            loginUtilObj = new LoginUtil(driver);
            result = loginUtilObj.verifyLoginUsingEmailId(driver, "abcd123@gmail.com", "amitpundir", true);
            if (!result) {
                myContentMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            homeUtilObj = new HomePageUtil(driver);
            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = clickOnPurchasedBtn(driver);
            if (!result) {
                myContentMsgList.add("Not able to click Purchased button.");
                return result;
            }

            result = clickOnSpecificPurchasedPackage(driver, packageName);
            if (!result) {
                myContentMsgList.add("Not able to click Package in PurchasedSection.");
                return result;
            }

            result = homeUtilObj.verifyViewAllContentInSelectedCourse(driver);
            if (!result){
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            childPackageUtilObj = new ChildPackageUtil(driver);
            result = cfObj.waitForPageLoading(driver, 5, 3000, cfObj.commonGetElement(driver, "//*[contains(@text,'"
                            + mockName
                            + "')]/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='GET QUIZ']",
                    "xpath"));
            if (!result) {
                driver.navigate().back();

                result = homeUtilObj.verifyViewAllContentInSelectedCourse(driver);
                if (!result){
                    myContentMsgList.addAll(homeUtilObj.homeMsgList);
                    return result;
                }

                result = cfObj.waitForPageLoading(driver, 5, 3000, cfObj.commonGetElement(driver, "//*[contains(@text,'"
                                + mockName
                                + "')]/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='GET QUIZ']",
                        "xpath"));
                if (!result) {
                    myContentMsgList.add("Unable to Load test series Page even after retry.");
                    return result;
                }
            }

            result = childPackageUtilObj.verifyTestSeriesPage(driver);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.validateSpecificMockTitle(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.downloadSpecificQuiz(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.clickOnSpecificAttemptLink(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            commonTestUtilObj = new CommonTestUtil(driver);

            result = commonTestUtilObj.verifyInstructionPage(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.clickStartTestBtn(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.validateSectionDropDown(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.clickOnTestPauseBtn(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = childPackageUtilObj.clickOnSpecificResumeStatus(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = commonTestUtilObj.clickOnResumeTestBtn(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            Thread.sleep(25000);

            result = commonTestUtilObj.clickOnNextSectionBtn(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = cfObj.commonVerifyValueTextBox(commonTestUtilObj.commonTestPageObj.getExamSectionDropDown(),
                    "Section_Two");
            if (!result) {
                myContentMsgList.add("Section is not changed.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "com.adda247.app:id/questionControl_button", "id", 10);
            if (!result) {
                myContentMsgList.add("questionControl_button drawer is not visible");
                return result;
            }

            cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/questionControl_button", "id"));

            result = commonTestUtilObj.clickOnSubmitTestBtn(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.handleTestRatingPopUp(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.clickOnReAttemptBtn(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.clickStartTestBtn(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.takeAndSubmitSectionalTimeLimitMockTest(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.verifyAndSubmitFinishTestPopUp(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.handleTestRatingPopUp(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.verifyAndCloseCoinPopup(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }
            Thread.sleep(2000);

            result = commonTestUtilObj.verifyTestAnalysisDailyQuizPage(driver, false);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            cfObj.pressAndroidKey(driver, key.BACK, 1);

            result = clickOnSpecificPurchasedPackage(driver, packageName);
            if (!result) {
                System.out.println("Not able to click Package in PurchasedSection.");
                return result;
            }

            result = homeUtilObj.verifyViewAllContentInSelectedCourse(driver);
            if (!result){
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = cfObj.waitForPageLoading(driver, 10, 2000, cfObj.commonGetElement(driver, "//*[contains(@text,'"
                            + mockName
                            + "')]/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='GET QUIZ']",
                    "xpath"));
            if (!result) {
                myContentMsgList.add("Unable to Load testseries Page.");
            }

            result = childPackageUtilObj.clickOnSpecificResultStatus(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }
        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifySectionalTimeLimitMockTest_Exception" + e.getMessage());
        } finally {
            System.out.println("---------------------");
            mockTestApiUtil.edit_deleteTestSeriesForPackageId48219();
        }
        return result;
    }

    public boolean validatePurchasedScreenEmptyState(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getBtnPurchasedSection(), 10);
            if (!result) {
                myContentMsgList.add("PurchasedSection button is not visible.");
                return result;
            }

            cfObj.commonClick(myContentPageObj.getBtnPurchasedSection());

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getTakeMeToStoreBtn(), 10);
            if (!result) {
                myContentMsgList.add("TakeMeToStore button is not visible.");
                return result;
            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("validatePurchasedScreenEmptyState_Exception" + e.getMessage());
        }
        return result;

    }

    public boolean verifyCertificationInCutOffMockTestSeries(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        String packageTitle;
        try {

            CreatePackageUtil createPackageUtil = new CreatePackageUtil();
            List<Integer> categorylist = new ArrayList<Integer>();
            Map<String, Integer> conetntList = new HashMap<String, Integer>();
            PackageApiUtil packageApiUtil = new PackageApiUtil();
            MockTestApiUtil mockTestApiUtil = new MockTestApiUtil();
            GetSignedUrl getSignedUrlObj;
            categorylist.add(1);
            String strToken = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTMxLCJlbWFpbCI6ImFrYXNoLmthbm9qaWFAYWRkYTI0Ny5jb20iLCJuYW1lIjoiQWthc2giLCJhbGxvd2VkU2VjdGlvbnMiOiJBY3Rpb25zLEFkZCBuZXcgdXJsLEFkZFBhY2thZ2UsYnVsa1B1Ymxpc2hVbnB1Ymxpc2gsQ29udGVudEFkbWluVmlldyxDb3Vwb25BY3Rpb25zLENvdXBvbkRlYWN0aXZhdGUsQ291cG9uRGVsZXRlLENvdXBvbkVkaXQsQ291cG9uVmlldyxDcmVhdGUgSW5zdGl0dXRlICxDcmVhdGUgbmV3IGNvdXBvbixDcmVhdGUgbmV3IGdyb3VwLENyZWF0ZSBuZXcgcGVybWlzc2lvbnMsQ3JlYXRlIE5ldyBQb3N0LENyZWF0ZSBuZXcgcm9sZSxDcmVhdGUgTmV3IFVzZXIsQ3JlYXRlU29jaWFsR3JvdXAsRGFzaGJvYXJkVmlldyxEZWxldGUgcm9sZSxEZWxldGUgdXNlcnMsRGVsZXRlU29jaWFsR3JvdXAsRG91YnQgUm9vbSxEdXBsaWNhdGVQYWNrYWdlLEVkaXQgdXNlcnMsRWRpdFBhY2thZ2UsRWRpdFBvc3QsRWRpdFNvY2lhbEdyb3VwLEZhY3VsdHksZmFjdWx0eWFkbWluLEZlZWRDdXN0b21pc2F0aW9uLEZpeGVkUGVyaW9kTW9jayxHbG9iYWxDb25maWcsR3JvdXBEZWxldGUsR3JvdXBFZGl0LEdyb3VwcyxJbnN0aXR1dGVzLEpvaW5fQXNfVGVhY2hlcixMY3NWaWV3LExpZ2h0aG91c2VBY3Rpb25zLExpZ2h0aG91c2VWaWV3LExpbmtDaGlsZFBhY2thZ2UsTGlua1BhcmVudFBhY2thZ2UsTGl2ZUNsYXNzLFBhY2thZ2VBY3Rpb24sUGFja2FnZUlPU1ByaWNlVXBsb2FkLHBhY2thZ2VVcGdyYWRlLFBhY2thZ2VWaWRlb1VwbG9hZCxQYWNrYWdlVmlldyxQQ1IgYWRtaW4sUERQRnJlZUNvbnRlbnQsUGVybWlzc2lvbkFjdGlvbnMsUGVybWlzc2lvbkRlbGV0ZSxQZXJtaXNzaW9uRWRpdCxQZXJtaXNzaW9uc1ZpZXcsUHJvbW9Db2RlcyxSb2xlQWN0aW9ucyxSb2xlRGVsZXRlLFJvbGVFZGl0LFJvbGVzVmlldyxTRU8sU2VvIEVkaXRvcixTZXRPckNoYW5nZVBhc3N3b3JkLFNraWxsQ2VydGlmaWNhdGlvbixTb2NpYWxHcm91cEFjdGlvbnMsU29jaWFsVXNlcnNWaWV3LFNvY2lhbF9Hcm91cHMsU3BvbnNvcmVkTGlzdCxTVE9QV09SRFMsU3RvcmVmcm9udEFkbWluLFN1cGVyQWRtaW5fVGVzdCx0ZXN0aW5nYWRtaW4sVXNlcnNWIiwicm9sZSI6IlN1cGVyQWRtaW4iLCJpYXQiOjE2ODg0NTc2NzZ9.oFwXxDcVpILFdF4Y-TZPnmCOqdCRsztXwFiQ6StLIXBTgsCQmIdWudNLCXlfUvATQS-RZcViH52HqziKOYJ5Yw";

            CreatePackageResponse createPackageResponse = createPackageUtil.createPackage(false, false, categorylist,
                    2);
            if (createPackageResponse == null) {
                myContentMsgList.addAll(createPackageUtil.createPackageMsgList);
                return false;
            }
            System.out.println("strPackageId: " + createPackageResponse.getData().getId());

            for (int i = 0; i < 3; i++) {
                result = mockTestApiUtil.createCutOffMockTest();
                if (!result) {
                    myContentMsgList.add("Not able to create CutOffMockTest.");
                    return false;
                }
                String strId = MockTestApiUtil.strFixedMockTestId;
                if (strId == null) {
                    myContentMsgList.add("MockTest id is null.");
                    return false;
                }
                conetntList.put(strId, (i + 1));
                result = packageApiUtil.assignTestSeriesToPackage(1, Integer.parseInt(strId),
                        createPackageResponse.getData().getId());
                if (!result) {
                    myContentMsgList.addAll(packageApiUtilObj.packagetApiMsgList);
                    return false;
                }

            }
            CertificateUtil cfUtilObj = new CertificateUtil();

            getSignedUrlObj = cfUtilObj.getSignedUrl(strToken, "certificate1.html",
                    createPackageResponse.getData().getId());
            if (getSignedUrlObj == null) {
                myContentMsgList.add("error in get signed url");
                return false;
            }

            result = cfUtilObj.uploadfile(getSignedUrlObj.getData().getSignedUrl(), "certificate1.html",
                    String.valueOf(createPackageResponse.getData().getId()));
            if (!result) {
                myContentMsgList.add("Not able to Upload file.");
                return false;
            }

            CreateCertificateResponse createCertificateResponseObj = cfUtilObj.createCertificate(strToken,
                    String.valueOf(createPackageResponse.getData().getId()), 2, 2, conetntList,
                    getSignedUrlObj.getData().getTempS3Url());
            if (!result) {
                myContentMsgList.add("Not able to Create Certificate.");
                return false;
            }
            cfUtilObj.activeCertificate(strToken, String.valueOf(createPackageResponse.getData().getId()));
            cfUtilObj.aprroveCertificate(strToken, String.valueOf(createPackageResponse.getData().getId()),
                    createCertificateResponseObj.getData().getCertificateId());
            packageTitle = createPackageResponse.getData().getTitle();
            if (packageTitle == null) {
                myContentMsgList.add("Package title is null.");
                return false;
            }
            System.out.println("Package Title:->" + packageTitle);

            loginUtilObj = new LoginUtil(driver);

            result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), true);
            if (!result) {
                myContentMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            homeUtilObj = new HomePageUtil(driver);

            result = homeUtilObj.clickStoreBtn(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            storeUtilObj = new StorePageUtil(driver);
            if (ConfigFileReader.strApplication.equalsIgnoreCase("Sankalp")) {
                result = storeUtilObj.clickVideoCoursesIcon(driver);
                if (!result) {
                    myContentMsgList.addAll(storeUtilObj.storePageMsgList);
                    return result;
                }
                result = cfObj.pressAndroidKey(driver, key.BACK, 1);
                if (!result) {
                    myContentMsgList.add("Not able to click back button.");
                    return result;
                }
            }
            result = storeUtilObj.clickSearchIcon(driver);
            if (!result) {
                myContentMsgList.addAll(storeUtilObj.storePageMsgList);
                return result;
            }

            result = storeUtilObj.enterPackageNameInSearchField(driver,
                    packageTitle.split(" ")[packageTitle.split(" ").length - 1]);
            if (!result) {
                myContentMsgList.addAll(storeUtilObj.storePageMsgList);
                return result;
            }

            result = storeUtilObj.selectProductType(driver, ProductType.TEST_SERIES);
            if (!result) {
                myContentMsgList.addAll(storeUtilObj.storePageMsgList);
                return result;
            }

            result = storeUtilObj.validateCertificateCourseTag(driver);
            if (!result) {
                myContentMsgList.addAll(storeUtilObj.storePageMsgList);
                return result;
            }

            result = storeUtilObj.clickPackageNameInSearchResult(driver, packageTitle);
            if (!result) {
                myContentMsgList.addAll(storeUtilObj.storePageMsgList);
                return result;
            }

            purchasePackageUtilObj = new PurchasePackageUtil(driver);
            result = purchasePackageUtilObj.verifyUnpurchasedPackagePage(driver);
            if (!result) {
                myContentMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
                return result;
            }
            // result=purchasePackageUtilObj.validateCertificateTagOnPurchasePage(driver);
            // if(!result) {
            // myContentMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
            // return result;
            // }

            result = purchasePackageUtilObj.applyCouponCode(driver, configObj.getCoupon());
            if (!result) {
                myContentMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
                return result;
            }

            result = purchasePackageUtilObj.clickBuyNowBtn(driver);
            if (!result) {
                myContentMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
                return result;
            }

            orderSuccessUtilObj = new OrderSuccessUtil(driver);
            result = orderSuccessUtilObj.handleRateUsPopUpWindow(driver);
            if (!result) {
                myContentMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
                return result;
            }
            result = orderSuccessUtilObj.verifyOrderSuccessPage(driver);
            if (!result) {
                myContentMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
                return result;
            }

            result = cfObj.pressAndroidKey(driver, key.BACK, 3);
            if (!result) {
                myContentMsgList.add("Unable to press Android back key");
                return result;
            }

            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = clickOnPurchasedBtn(driver);
            if (!result) {
                myContentMsgList.add("Not able to click Purchased button.");
                return result;
            }

            result = clickOnPackageInPurchasedSection(driver, packageTitle);
            if (!result) {
                myContentMsgList.add("Not able to click Purchased package.");
                return result;
            }

            childPackageUtilObj = new ChildPackageUtil(driver);
            result = childPackageUtilObj.validateTestTagCount(driver);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }
            result = childPackageUtilObj.validateQuizCertificateCell(driver);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }
            result = childPackageUtilObj.attemptSpecificTestTagQuizInCutOffCase(driver);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.validateCertificateReAttemptAndResumeBtn(driver, true);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }
            result = childPackageUtilObj.validateCertificateAttemptBtnInCutOffMock(driver);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.clickOnGenerateCertificateBtn(driver);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.fillCandidateDetailsForm(driver);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }
            certificatePreviewUtilObj = new CertificatePreviewUtil(driver);
            result = certificatePreviewUtilObj.validateCertificatePreview(driver);
            if (!result) {
                myContentMsgList.addAll(certificatePreviewUtilObj.certificatePreviewMsgList);
                return result;
            }

            Thread.sleep(2000);
            // result=cfObj.compareTwoImages(driver,
            // "Certification/CutOffMockTestSeriesCompletedSeriesCertificatePreview",
            // "Certification/CutOffMockTestSeriesCompletedSeriesCertificatePreview", 95);
            // if (!result) {
            // myContentMsgList.add("Unable to compare
            // CutOffMockTestSeriesCompletedSeriesCertificatePreview.");
            // return result;
            // }
            result = certificatePreviewUtilObj.validateEditDetailsBtn(driver);
            if (!result) {
                myContentMsgList.addAll(certificatePreviewUtilObj.certificatePreviewMsgList);
                return result;
            }

            // result=cfObj.compareTwoImages(driver,
            // "Certification/CutOffMockTestSeriesCompletedSeriesCertificatePreview",
            // "Certification/CutOffMockTestSeriesCompletedSeriesCertificatePreview", 95);
            // if (!result) {
            // myContentMsgList.add("Unable to compare
            // CutOffMockTestSeriesCompletedSeriesCertificatePreview.");
            // return result;
            // }
            result = certificatePreviewUtilObj.validateSubmitDetailsBtn(driver,
                    "Certification/CutOffMockTestSeriesCertificateDownloadShareScreen");
            if (!result) {
                myContentMsgList.addAll(certificatePreviewUtilObj.certificatePreviewMsgList);
                return result;
            }

            result = childPackageUtilObj.clickOnViewCertificateBtn(driver,
                    "Certification/verifyCertificationInCutOffMockTestSeriesDownloadedCertificateScreen");
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = cfObj.pressAndroidKey(driver, key.BACK, 1);
            if (!result) {
                myContentMsgList.add("Not able to click back button.");
                return result;
            }

            result = homeUtilObj.openNavigationDrawer(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }
            result = homeUtilObj.clickOnUserProfileSection(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            userProfilePageUtilObj = new UserProfilePageUtil(driver);
            result = userProfilePageUtilObj.clickOnProfileDotIcon(driver);
            if (!result) {
                myContentMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
                return result;
            }
            result = userProfilePageUtilObj.clickOnMyCertificatesTab(driver);
            if (!result) {
                myContentMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
                return result;
            }

            result = certificatePreviewUtilObj.validateCertificateShareAndDownloadBtn(driver);
            if (!result) {
                myContentMsgList.addAll(certificatePreviewUtilObj.certificatePreviewMsgList);
                return result;
            }

        } catch (Exception e) {
            myContentMsgList.add("verifyCertificationInCutOffMockTestSeries_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyCertificationInTestSeries(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        String packageTitle;
        try {

            CreatePackageUtil createPackageUtil = new CreatePackageUtil();
            List<Integer> categorylist = new ArrayList<Integer>();
            Map<String, Integer> conetntList = new HashMap<String, Integer>();
            PackageApiUtil packageApiUtil = new PackageApiUtil();
            MockTestApiUtil mockTestApiUtil = new MockTestApiUtil();
            GetSignedUrl getSignedUrlObj;
            categorylist.add(1);
            String strToken = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTMxLCJlbWFpbCI6ImFrYXNoLmthbm9qaWFAYWRkYTI0Ny5jb20iLCJuYW1lIjoiQWthc2giLCJhbGxvd2VkU2VjdGlvbnMiOiJBY3Rpb25zLEFkZCBuZXcgdXJsLEFkZFBhY2thZ2UsYnVsa1B1Ymxpc2hVbnB1Ymxpc2gsQ29udGVudEFkbWluVmlldyxDb3Vwb25BY3Rpb25zLENvdXBvbkRlYWN0aXZhdGUsQ291cG9uRGVsZXRlLENvdXBvbkVkaXQsQ291cG9uVmlldyxDcmVhdGUgSW5zdGl0dXRlICxDcmVhdGUgbmV3IGNvdXBvbixDcmVhdGUgbmV3IGdyb3VwLENyZWF0ZSBuZXcgcGVybWlzc2lvbnMsQ3JlYXRlIE5ldyBQb3N0LENyZWF0ZSBuZXcgcm9sZSxDcmVhdGUgTmV3IFVzZXIsQ3JlYXRlU29jaWFsR3JvdXAsRGFzaGJvYXJkVmlldyxEZWxldGUgcm9sZSxEZWxldGUgdXNlcnMsRGVsZXRlU29jaWFsR3JvdXAsRG91YnQgUm9vbSxEdXBsaWNhdGVQYWNrYWdlLEVkaXQgdXNlcnMsRWRpdFBhY2thZ2UsRWRpdFBvc3QsRWRpdFNvY2lhbEdyb3VwLEZhY3VsdHksZmFjdWx0eWFkbWluLEZlZWRDdXN0b21pc2F0aW9uLEZpeGVkUGVyaW9kTW9jayxHbG9iYWxDb25maWcsR3JvdXBEZWxldGUsR3JvdXBFZGl0LEdyb3VwcyxJbnN0aXR1dGVzLEpvaW5fQXNfVGVhY2hlcixMY3NWaWV3LExpZ2h0aG91c2VBY3Rpb25zLExpZ2h0aG91c2VWaWV3LExpbmtDaGlsZFBhY2thZ2UsTGlua1BhcmVudFBhY2thZ2UsTGl2ZUNsYXNzLFBhY2thZ2VBY3Rpb24sUGFja2FnZUlPU1ByaWNlVXBsb2FkLHBhY2thZ2VVcGdyYWRlLFBhY2thZ2VWaWRlb1VwbG9hZCxQYWNrYWdlVmlldyxQQ1IgYWRtaW4sUERQRnJlZUNvbnRlbnQsUGVybWlzc2lvbkFjdGlvbnMsUGVybWlzc2lvbkRlbGV0ZSxQZXJtaXNzaW9uRWRpdCxQZXJtaXNzaW9uc1ZpZXcsUHJvbW9Db2RlcyxSb2xlQWN0aW9ucyxSb2xlRGVsZXRlLFJvbGVFZGl0LFJvbGVzVmlldyxTRU8sU2VvIEVkaXRvcixTZXRPckNoYW5nZVBhc3N3b3JkLFNraWxsQ2VydGlmaWNhdGlvbixTb2NpYWxHcm91cEFjdGlvbnMsU29jaWFsVXNlcnNWaWV3LFNvY2lhbF9Hcm91cHMsU3BvbnNvcmVkTGlzdCxTVE9QV09SRFMsU3RvcmVmcm9udEFkbWluLFN1cGVyQWRtaW5fVGVzdCx0ZXN0aW5nYWRtaW4sVXNlcnNWIiwicm9sZSI6IlN1cGVyQWRtaW4iLCJpYXQiOjE2ODg0NTc2NzZ9.oFwXxDcVpILFdF4Y-TZPnmCOqdCRsztXwFiQ6StLIXBTgsCQmIdWudNLCXlfUvATQS-RZcViH52HqziKOYJ5Yw";

            CreatePackageResponse createPackageResponse = createPackageUtil.createPackage(false, false, categorylist,
                    2);
            if (createPackageResponse == null) {
                myContentMsgList.addAll(createPackageUtil.createPackageMsgList);
                return false;
            }
            System.out.println("strPackageId: " + createPackageResponse.getData().getId());

            for (int i = 0; i < 3; i++) {
                result = mockTestApiUtil.createMockTestStoreFront(false);
                if (!result) {
                    myContentMsgList.add("Not able to create MockTest.");
                    return false;
                }
                String strId = MockTestApiUtil.strFixedMockTestId;
                if (strId == null) {
                    myContentMsgList.add("Mock test id is null.");
                    return false;
                }
                conetntList.put(strId, (i + 1));
                result = packageApiUtil.assignTestSeriesToPackage(1, Integer.parseInt(strId),
                        createPackageResponse.getData().getId());
                if (!result) {
                    myContentMsgList.addAll(packageApiUtilObj.packagetApiMsgList);
                    return false;
                }

            }
            CertificateUtil cfUtilObj = new CertificateUtil();

            getSignedUrlObj = cfUtilObj.getSignedUrl(strToken, "certificate1.html",
                    createPackageResponse.getData().getId());
            if (getSignedUrlObj == null) {
                myContentMsgList.add("error in get signed url");
                return false;
            }

            result = cfUtilObj.uploadfile(getSignedUrlObj.getData().getSignedUrl(), "certificate1.html",
                    String.valueOf(createPackageResponse.getData().getId()));
            if (!result) {
                myContentMsgList.add("Not able to Upload file.");
                return false;
            }

            CreateCertificateResponse createCertificateResponseObj = cfUtilObj.createCertificate(strToken,
                    String.valueOf(createPackageResponse.getData().getId()), 1, 2, conetntList,
                    getSignedUrlObj.getData().getTempS3Url());
            if (createCertificateResponseObj == null) {
                myContentMsgList.add("Not able to Create Certificate.");
                return false;
            }
            cfUtilObj.activeCertificate(strToken, String.valueOf(createPackageResponse.getData().getId()));
            cfUtilObj.aprroveCertificate(strToken, String.valueOf(createPackageResponse.getData().getId()),
                    createCertificateResponseObj.getData().getCertificateId());
            packageTitle = createPackageResponse.getData().getTitle();
            if (packageTitle == null) {
                myContentMsgList.add("Package title is null.");
                return false;
            }
            System.out.println("Package Title:->" + packageTitle);
            String[] st = packageTitle.split(" ");
            packageTitle = st[st.length - 1];

            loginUtilObj = new LoginUtil(driver);

            result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), true);
            if (!result) {
                myContentMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            homeUtilObj = new HomePageUtil(driver);

            result = homeUtilObj.clickStoreBtn(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            storeUtilObj = new StorePageUtil(driver);

            result = storeUtilObj.clickSearchIcon(driver);
            if (!result) {
                myContentMsgList.addAll(storeUtilObj.storePageMsgList);
                return result;
            }

            result = storeUtilObj.enterPackageNameInSearchField(driver,
                    packageTitle.split(" ")[packageTitle.split(" ").length - 1]);
            if (!result) {
                myContentMsgList.addAll(storeUtilObj.storePageMsgList);
                return result;
            }

            result = storeUtilObj.selectProductType(driver, ProductType.TEST_SERIES);
            if (!result) {
                myContentMsgList.addAll(storeUtilObj.storePageMsgList);
                return result;
            }

            result = storeUtilObj.validateCertificateCourseTag(driver);
            if (!result) {
                myContentMsgList.addAll(storeUtilObj.storePageMsgList);
                return result;
            }

            result = storeUtilObj.clickPackageNameInSearchResult(driver, packageTitle);
            if (!result) {
                myContentMsgList.addAll(storeUtilObj.storePageMsgList);
                return result;
            }

            purchasePackageUtilObj = new PurchasePackageUtil(driver);
            result = purchasePackageUtilObj.verifyUnpurchasedPackagePage(driver);
            if (!result) {
                myContentMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
                return result;
            }

            // result=purchasePackageUtilObj.validateCertificateTagOnPurchasePage(driver);
            // if(!result) {
            // myContentMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
            // return result;
            // }

            result = purchasePackageUtilObj.applyCouponCode(driver, configObj.getCoupon());
            if (!result) {
                myContentMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
                return result;
            }

            result = purchasePackageUtilObj.clickBuyNowBtn(driver);
            if (!result) {
                myContentMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
                return result;
            }

            orderSuccessUtilObj = new OrderSuccessUtil(driver);
            result = orderSuccessUtilObj.handleRateUsPopUpWindow(driver);
            if (!result) {
                myContentMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
                return result;
            }
            result = orderSuccessUtilObj.verifyOrderSuccessPage(driver);
            if (!result) {
                myContentMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
                return result;
            }

            result = cfObj.pressAndroidKey(driver, key.BACK, 3);
            if (!result) {
                myContentMsgList.add("Unable to press Android back key");
                return result;
            }

            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = clickOnPurchasedBtn(driver);
            if (!result) {
                System.out.println("Not able to click Purchased button.");
                return result;
            }

            result = clickOnPackageInPurchasedSection(driver, packageTitle);
            if (!result) {
                System.out.println("Not able to click Purchased package.");
                return result;
            }

            childPackageUtilObj = new ChildPackageUtil(driver);
            result = childPackageUtilObj.validateTestTagCount(driver);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }
            result = childPackageUtilObj.validateQuizCertificateCell(driver);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.attemptSpecificTestTagQuiz(driver);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.validateCertificateReAttemptAndResumeBtn(driver, false);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.validateCertificateAttemptBtn(driver);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.clickOnGenerateCertificateBtn(driver);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.fillCandidateDetailsForm(driver);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }
            Thread.sleep(2000);
            certificatePreviewUtilObj = new CertificatePreviewUtil(driver);
            result = certificatePreviewUtilObj.validateCertificatePreview(driver);
            if (!result) {
                myContentMsgList.addAll(certificatePreviewUtilObj.certificatePreviewMsgList);
                return result;
            }
            result = certificatePreviewUtilObj.validateEditDetailsBtn(driver);
            if (!result) {
                myContentMsgList.addAll(certificatePreviewUtilObj.certificatePreviewMsgList);
                return result;
            }
            result = certificatePreviewUtilObj.validateSubmitDetailsBtn(driver, null);
            if (!result) {
                myContentMsgList.addAll(certificatePreviewUtilObj.certificatePreviewMsgList);
                return result;
            }

            result = childPackageUtilObj.clickOnViewCertificateBtn(driver, null);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = cfObj.pressAndroidKey(driver, key.BACK, 1);
            if (!result) {
                myContentMsgList.add("Not able to click back button.");
                return result;
            }

            result = homeUtilObj.openNavigationDrawer(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }
            result = homeUtilObj.clickOnUserProfileSection(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            userProfilePageUtilObj = new UserProfilePageUtil(driver);
            result = userProfilePageUtilObj.clickOnProfileDotIcon(driver);
            if (!result) {
                myContentMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
                return result;
            }
            result = userProfilePageUtilObj.clickOnMyCertificatesTab(driver);
            if (!result) {
                myContentMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
                return result;
            }

            result = certificatePreviewUtilObj.validateCertificateShareAndDownloadBtn(driver);
            if (!result) {
                myContentMsgList.addAll(certificatePreviewUtilObj.certificatePreviewMsgList);
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("verifyCertificationInTestSeries_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyCertificationInVideoCourse(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        String packageTitle = "Certification Parent packag";
        try {

            CreatePackageUtil createPackageUtil = new CreatePackageUtil();
            List<Integer> categorylist = new ArrayList<Integer>();
            Map<String, Integer> conetntList = new HashMap<String, Integer>();
            PackageApiUtil packageApiUtil = new PackageApiUtil();
            GetSignedUrl getSignedUrlObj;
            categorylist.add(5);
            String strToken = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTMxLCJlbWFpbCI6ImFrYXNoLmthbm9qaWFAYWRkYTI0Ny5jb20iLCJuYW1lIjoiQWthc2giLCJhbGxvd2VkU2VjdGlvbnMiOiJBY3Rpb25zLEFkZCBuZXcgdXJsLEFkZFBhY2thZ2UsYnVsa1B1Ymxpc2hVbnB1Ymxpc2gsQ29udGVudEFkbWluVmlldyxDb3Vwb25BY3Rpb25zLENvdXBvbkRlYWN0aXZhdGUsQ291cG9uRGVsZXRlLENvdXBvbkVkaXQsQ291cG9uVmlldyxDcmVhdGUgSW5zdGl0dXRlICxDcmVhdGUgbmV3IGNvdXBvbixDcmVhdGUgbmV3IGdyb3VwLENyZWF0ZSBuZXcgcGVybWlzc2lvbnMsQ3JlYXRlIE5ldyBQb3N0LENyZWF0ZSBuZXcgcm9sZSxDcmVhdGUgTmV3IFVzZXIsQ3JlYXRlU29jaWFsR3JvdXAsRGFzaGJvYXJkVmlldyxEZWxldGUgcm9sZSxEZWxldGUgdXNlcnMsRGVsZXRlU29jaWFsR3JvdXAsRG91YnQgUm9vbSxEdXBsaWNhdGVQYWNrYWdlLEVkaXQgdXNlcnMsRWRpdFBhY2thZ2UsRWRpdFBvc3QsRWRpdFNvY2lhbEdyb3VwLEZhY3VsdHksZmFjdWx0eWFkbWluLEZlZWRDdXN0b21pc2F0aW9uLEZpeGVkUGVyaW9kTW9jayxHbG9iYWxDb25maWcsR3JvdXBEZWxldGUsR3JvdXBFZGl0LEdyb3VwcyxJbnN0aXR1dGVzLEpvaW5fQXNfVGVhY2hlcixMY3NWaWV3LExpZ2h0aG91c2VBY3Rpb25zLExpZ2h0aG91c2VWaWV3LExpbmtDaGlsZFBhY2thZ2UsTGlua1BhcmVudFBhY2thZ2UsTGl2ZUNsYXNzLFBhY2thZ2VBY3Rpb24sUGFja2FnZUlPU1ByaWNlVXBsb2FkLHBhY2thZ2VVcGdyYWRlLFBhY2thZ2VWaWRlb1VwbG9hZCxQYWNrYWdlVmlldyxQQ1IgYWRtaW4sUERQRnJlZUNvbnRlbnQsUGVybWlzc2lvbkFjdGlvbnMsUGVybWlzc2lvbkRlbGV0ZSxQZXJtaXNzaW9uRWRpdCxQZXJtaXNzaW9uc1ZpZXcsUHJvbW9Db2RlcyxSb2xlQWN0aW9ucyxSb2xlRGVsZXRlLFJvbGVFZGl0LFJvbGVzVmlldyxTRU8sU2VvIEVkaXRvcixTZXRPckNoYW5nZVBhc3N3b3JkLFNraWxsQ2VydGlmaWNhdGlvbixTb2NpYWxHcm91cEFjdGlvbnMsU29jaWFsVXNlcnNWaWV3LFNvY2lhbF9Hcm91cHMsU3BvbnNvcmVkTGlzdCxTVE9QV09SRFMsU3RvcmVmcm9udEFkbWluLFN1cGVyQWRtaW5fVGVzdCx0ZXN0aW5nYWRtaW4sVXNlcnNWIiwicm9sZSI6IlN1cGVyQWRtaW4iLCJpYXQiOjE2ODg0NTc2NzZ9.oFwXxDcVpILFdF4Y-TZPnmCOqdCRsztXwFiQ6StLIXBTgsCQmIdWudNLCXlfUvATQS-RZcViH52HqziKOYJ5Yw";

            CreatePackageResponse createPackageResponse = createPackageUtil.createPackage(false, false, categorylist,
                    2);
            if (createPackageResponse == null) {
                myContentMsgList.addAll(createPackageUtil.createPackageMsgList);
                return false;
            }
            System.out.println("strPackageId: " + createPackageResponse.getData().getId());
            GetVideoList getVideoList = packageApiUtil
                    .videoLinking(String.valueOf(createPackageResponse.getData().getId()));
            if (getVideoList == null) {
                myContentMsgList.add("Not able to Link video.");
                return false;
            }

            System.out.println(
                    "VideoList:--->" + getVideoList.getData().getInputVideoStreamingBean().getVideoStreamingBean()
                            .getBilingual().getCc().getSubject().get(0).getCh().get(0).getVideo().size());
            for (int i = 0; i < getVideoList.getData().getInputVideoStreamingBean().getVideoStreamingBean()
                    .getBilingual().getCc().getSubject().get(0).getCh().get(0).getVideo().size(); i++) {
                conetntList.put(getVideoList.getData().getInputVideoStreamingBean().getVideoStreamingBean()
                        .getBilingual().getCc().getSubject().get(0).getCh().get(0).getVideo().get(i).getUrl(), (i + 1));

            }
            CertificateUtil cfUtilObj = new CertificateUtil();

            getSignedUrlObj = cfUtilObj.getSignedUrl(strToken, "certificate1.html",
                    createPackageResponse.getData().getId());
            if (getSignedUrlObj == null) {
                myContentMsgList.add("error in get signed url");
                return false;
            }

            result = cfUtilObj.uploadfile(getSignedUrlObj.getData().getSignedUrl(), "certificate1.html",
                    String.valueOf(createPackageResponse.getData().getId()));
            if (!result) {
                myContentMsgList.add("Not able to upload file.");
                return result;
            }

            CreateCertificateResponse createCertificateResponseObj = cfUtilObj.createCertificate(strToken,
                    String.valueOf(createPackageResponse.getData().getId()), 4, 2, conetntList,
                    getSignedUrlObj.getData().getTempS3Url());
            if (createCertificateResponseObj == null) {
                myContentMsgList.add("Not able to create Certificate.");
                return false;
            }
            cfUtilObj.activeCertificate(strToken, String.valueOf(createPackageResponse.getData().getId()));
            cfUtilObj.aprroveCertificate(strToken, String.valueOf(createPackageResponse.getData().getId()),
                    createCertificateResponseObj.getData().getCertificateId());

            // Remove commented line while using API
            // packageTitle=createPackageResponse.getData().getTitle();
            System.out.println("Package Title:->" + packageTitle);

            loginUtilObj = new LoginUtil(driver);

            result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), true);
            if (!result) {
                myContentMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            homeUtilObj = new HomePageUtil(driver);

            result = homeUtilObj.clickStoreBtn(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            storeUtilObj = new StorePageUtil(driver);
            if (ConfigFileReader.strApplication.equalsIgnoreCase("Sankalp")) {
                result = storeUtilObj.clickVideoCoursesIcon(driver);
                if (!result) {
                    myContentMsgList.addAll(storeUtilObj.storePageMsgList);
                    return result;
                }
                result = cfObj.pressAndroidKey(driver, key.BACK, 1);
                if (!result) {
                    myContentMsgList.add("Not able to click back button.");
                    return result;
                }
            }
            result = storeUtilObj.clickSearchIcon(driver);
            if (!result) {
                myContentMsgList.addAll(storeUtilObj.storePageMsgList);
                return result;
            }

            result = storeUtilObj.enterPackageNameInSearchField(driver, packageTitle);
            if (!result) {
                myContentMsgList.addAll(storeUtilObj.storePageMsgList);
                return result;
            }

            // result =storeUtilObj. selectProductType(driver, ProductType.VIDEOS);
            // if (!result) {
            // myContentMsgList.addAll(storeUtilObj.storePageMsgList);
            // return result;
            // }

            result = storeUtilObj.validateCertificateCourseTag(driver);
            if (!result) {
                myContentMsgList.addAll(storeUtilObj.storePageMsgList);
                return result;
            }

            result = storeUtilObj.clickPackageNameInSearchResult(driver, packageTitle);
            if (!result) {
                myContentMsgList.addAll(storeUtilObj.storePageMsgList);
                return result;
            }

            purchasePackageUtilObj = new PurchasePackageUtil(driver);
            result = purchasePackageUtilObj.verifyUnpurchasedPackagePage(driver);
            if (!result) {
                myContentMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
                return result;
            }

            result = purchasePackageUtilObj.validateCertificateTagOnPurchasePage(driver);
            if (!result) {
                myContentMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
                return result;
            }

            result = purchasePackageUtilObj.applyCouponCode(driver, configObj.getCoupon());
            if (!result) {
                myContentMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
                return result;
            }

            result = purchasePackageUtilObj.clickBuyNowBtn(driver);
            if (!result) {
                myContentMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
                return result;
            }

            orderSuccessUtilObj = new OrderSuccessUtil(driver);
            result = orderSuccessUtilObj.handleRateUsPopUpWindow(driver);
            if (!result) {
                myContentMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
                return result;
            }
            result = orderSuccessUtilObj.verifyOrderSuccessPage(driver);
            if (!result) {
                myContentMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
                return result;
            }

            result = cfObj.pressAndroidKey(driver, key.BACK, 3);
            if (!result) {
                myContentMsgList.add("Unable to press Android back key");
                return result;
            }

            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = clickOnPurchasedBtn(driver);
            if (!result) {
                myContentMsgList.add("Not able to click Purchased Btn.");
                return result;
            }

            result = clickOnPackageInPurchasedSection(driver, packageTitle);
            if (!result) {
                myContentMsgList.add("Not able to click Purchased package.");
                return result;
            }
            batchUtilObj = new BatchUtil(driver);
            result = batchUtilObj.clickOnDoneBtn(driver);
            if (!result) {
                myContentMsgList.addAll(batchUtilObj.batchMsgList);
                return result;
            }
            result = batchUtilObj.selectSpecificTab(driver, batchUtilObj.batchPageObj.getSectionVideoCourses());
            if (!result) {
                myContentMsgList.addAll(batchUtilObj.batchMsgList);
                return result;
            }

            // While using API UnComment below method

            // result=batchUtilObj.clickOnSubjectTitle(driver);
            // if (!result) {
            // myContentMsgList.addAll(batchUtilObj.batchMsgList);
            // return result;
            // }

            // While using API Comment below method

            result = batchUtilObj.clickOnSpecificSubjectTitle(driver, "Certification child videos");
            if (!result) {
                myContentMsgList.addAll(batchUtilObj.batchMsgList);
                return result;
            }

            videoCoursesPageUtilObj = new VideoCoursesPageUtil(driver);
            result = videoCoursesPageUtilObj.clickOnPurchasedVideo(driver);
            if (!result) {
                myContentMsgList.addAll(videoCoursesPageUtilObj.videoSectionMsgList);
                return result;
            }

            result = videoCoursesPageUtilObj.clickOnGotItBtn(driver);
            if (!result) {
                myContentMsgList.addAll(videoCoursesPageUtilObj.videoSectionMsgList);
                return result;
            }

            result = videoCoursesPageUtilObj.validateVideosTagCount(driver);
            if (!result) {
                myContentMsgList.addAll(videoCoursesPageUtilObj.videoSectionMsgList);
                return result;
            }

            cfObj.scrollUtill(driver, 3, direction.UP);

            result = videoCoursesPageUtilObj.validateVideoCertificateCell(driver);
            if (!result) {
                myContentMsgList.addAll(videoCoursesPageUtilObj.videoSectionMsgList);
                return result;
            }

            result = videoCoursesPageUtilObj.validateCertificateVideoWatchBtn(driver);
            if (!result) {
                myContentMsgList.addAll(videoCoursesPageUtilObj.videoSectionMsgList);
                return result;
            }

            childPackageUtilObj = new ChildPackageUtil(driver);
            result = childPackageUtilObj.clickOnGenerateCertificateBtn(driver);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.fillCandidateDetailsForm(driver);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }
            certificatePreviewUtilObj = new CertificatePreviewUtil(driver);
            result = certificatePreviewUtilObj.validateCertificatePreview(driver);
            if (!result) {
                myContentMsgList.addAll(certificatePreviewUtilObj.certificatePreviewMsgList);
                return result;
            }
            result = certificatePreviewUtilObj.validateEditDetailsBtn(driver);
            if (!result) {
                myContentMsgList.addAll(certificatePreviewUtilObj.certificatePreviewMsgList);
                return result;
            }
            result = certificatePreviewUtilObj.validateSubmitDetailsBtn(driver, null);
            if (!result) {
                myContentMsgList.addAll(certificatePreviewUtilObj.certificatePreviewMsgList);
                return result;
            }

            result = childPackageUtilObj.clickOnViewCertificateBtn(driver, null);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = cfObj.pressAndroidKey(driver, key.BACK, 3);
            if (!result) {
                myContentMsgList.add("Not able to click back button.");
                return result;
            }

            result = homeUtilObj.openNavigationDrawer(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }
            result = homeUtilObj.clickOnUserProfileSection(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            userProfilePageUtilObj = new UserProfilePageUtil(driver);
            Thread.sleep(1000);
            result = userProfilePageUtilObj.clickOnProfileDotIcon(driver);
            if (!result) {
                myContentMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
                return result;
            }
            result = userProfilePageUtilObj.clickOnMyCertificatesTab(driver);
            if (!result) {
                myContentMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
                return result;
            }

            result = certificatePreviewUtilObj.validateCertificateShareAndDownloadBtn(driver);
            if (!result) {
                myContentMsgList.addAll(certificatePreviewUtilObj.certificatePreviewMsgList);
                return result;
            }

        } catch (Exception e) {
            myContentMsgList.add("verifyCertificationInVideoCourse_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyCertificationInRecordedLiveClass(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        String packageTitle = "Certification Parent packag";
        try {

            loginUtilObj = new LoginUtil(driver);

            result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), true);
            if (!result) {
                myContentMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            homeUtilObj = new HomePageUtil(driver);

            result = homeUtilObj.clickStoreBtn(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            storeUtilObj = new StorePageUtil(driver);
            result = storeUtilObj.clickSearchIcon(driver);
            if (!result) {
                myContentMsgList.addAll(storeUtilObj.storePageMsgList);
                return result;
            }

            result = storeUtilObj.enterPackageNameInSearchField(driver, packageTitle);
            if (!result) {
                myContentMsgList.addAll(storeUtilObj.storePageMsgList);
                return result;
            }

            result = storeUtilObj.validateCertificateCourseTag(driver);
            if (!result) {
                myContentMsgList.addAll(storeUtilObj.storePageMsgList);
                return result;
            }

            result = storeUtilObj.clickPackageNameInSearchResult(driver, packageTitle);
            if (!result) {
                myContentMsgList.addAll(storeUtilObj.storePageMsgList);
                return result;
            }

            purchasePackageUtilObj = new PurchasePackageUtil(driver);
            result = purchasePackageUtilObj.verifyUnpurchasedPackagePage(driver);
            if (!result) {
                myContentMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
                return result;
            }
            result = purchasePackageUtilObj.validateCertificateTagOnPurchasePage(driver);
            if (!result) {
                myContentMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
                return result;
            }

            result = purchasePackageUtilObj.applyCouponCode(driver, configObj.getCoupon());
            if (!result) {
                myContentMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
                return result;
            }

            result = purchasePackageUtilObj.clickBuyNowBtn(driver);
            if (!result) {
                myContentMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
                return result;
            }

            orderSuccessUtilObj = new OrderSuccessUtil(driver);
            result = orderSuccessUtilObj.handleRateUsPopUpWindow(driver);
            if (!result) {
                myContentMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
                return result;
            }
            result = orderSuccessUtilObj.verifyOrderSuccessPage(driver);
            if (!result) {
                myContentMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
                return result;
            }

            result = cfObj.pressAndroidKey(driver, key.BACK, 3);
            if (!result) {
                myContentMsgList.add("Unable to press Android back key");
                return result;
            }

            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = clickOnPurchasedBtn(driver);
            if (!result) {
                myContentMsgList.add("Not able to click Purchased button.");
                return result;
            }

            result = clickOnPackageInPurchasedSection(driver, packageTitle);
            if (!result) {
                myContentMsgList.add("Not able to click Purchased package.");
                return result;
            }

            batchUtilObj = new BatchUtil(driver);
            result = batchUtilObj.clickOnDoneBtn(driver);
            if (!result) {
                myContentMsgList.addAll(batchUtilObj.batchMsgList);
                return result;
            }
            result = batchUtilObj.selectSpecificTab(driver, batchUtilObj.batchPageObj.getSectionLiveClasses());
            if (!result) {
                myContentMsgList.addAll(batchUtilObj.batchMsgList);
                return result;
            }

            result = batchUtilObj.clickOnSubjectTitle(driver);
            if (!result) {
                myContentMsgList.addAll(batchUtilObj.batchMsgList);
                return result;
            }

            liveClassesPageUtilObj = new LiveClassesPageUtil(driver);
            result = liveClassesPageUtilObj.handledNotificationPopUp(driver);
            if (!result) {
                myContentMsgList.add("Not able to handled NotificationPopUp.");
                return result;
            }

            if (cfObj.commonWaitForElementToBeVisible(driver, liveClassesPageUtilObj.liveClassPageObj.getGotItBtn(),
                    10)) {
                result = liveClassesPageUtilObj.clickOnGotItBtn(driver);
                if (!result) {
                    myContentMsgList.add("Not able to click GotIt button.");
                    return result;
                }
            }

            videoCoursesPageUtilObj = new VideoCoursesPageUtil(driver);
            result = videoCoursesPageUtilObj.validateVideoCertificateCell(driver);
            if (!result) {
                myContentMsgList.addAll(videoCoursesPageUtilObj.videoSectionMsgList);
                return result;
            }
            result = videoCoursesPageUtilObj.validateCertificateVideoWatchBtn(driver);
            if (!result) {
                myContentMsgList.addAll(videoCoursesPageUtilObj.videoSectionMsgList);
                return result;
            }

            childPackageUtilObj = new ChildPackageUtil(driver);
            result = childPackageUtilObj.clickOnGenerateCertificateBtn(driver);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.fillCandidateDetailsForm(driver);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }
            certificatePreviewUtilObj = new CertificatePreviewUtil(driver);
            result = certificatePreviewUtilObj.validateCertificatePreview(driver);
            if (!result) {
                myContentMsgList.addAll(certificatePreviewUtilObj.certificatePreviewMsgList);
                return result;
            }
            result = certificatePreviewUtilObj.validateEditDetailsBtn(driver);
            if (!result) {
                myContentMsgList.addAll(certificatePreviewUtilObj.certificatePreviewMsgList);
                return result;
            }
            result = certificatePreviewUtilObj.validateSubmitDetailsBtn(driver, null);
            if (!result) {
                myContentMsgList.addAll(certificatePreviewUtilObj.certificatePreviewMsgList);
                return result;
            }

            result = childPackageUtilObj.clickOnViewCertificateBtn(driver, null);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = cfObj.pressAndroidKey(driver, key.BACK, 2);
            if (!result) {
                myContentMsgList.add("Not able to click back button.");
                return result;
            }

            result = homeUtilObj.openNavigationDrawer(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }
            result = homeUtilObj.clickOnUserProfileSection(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            userProfilePageUtilObj = new UserProfilePageUtil(driver);
            Thread.sleep(1000);
            result = userProfilePageUtilObj.clickOnProfileDotIcon(driver);
            if (!result) {
                myContentMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
                return result;
            }
            result = userProfilePageUtilObj.clickOnMyCertificatesTab(driver);
            if (!result) {
                myContentMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
                return result;
            }

            result = certificatePreviewUtilObj.validateCertificateShareAndDownloadBtn(driver);
            if (!result) {
                myContentMsgList.addAll(certificatePreviewUtilObj.certificatePreviewMsgList);
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("verifyCertificationInLiveClass_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean validateEBookScreenUI(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.geteBookDownloadBtn().get(0), 10);
            if (!result) {
                myContentMsgList.add("EBook download button is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.geteBookTitle().get(0), 10);
            if (!result) {
                myContentMsgList.add("EBook Title is not visible.");
                return result;
            }

        } catch (Exception e) {
            myContentMsgList.add("validateEBookScreenUI_Exception: " + e.getMessage());
            result = false;
        }
        return result;

    }

    public boolean verifyCertificationInLiveClass(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        String packageTitle;
        try {

            CertificateUtil certificateUtil = new CertificateUtil();
            String strToken = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTMxLCJlbWFpbCI6ImFrYXNoLmthbm9qaWFAYWRkYTI0Ny5jb20iLCJuYW1lIjoiQWthc2giLCJhbGxvd2VkU2VjdGlvbnMiOiJBY3Rpb25zLEFkZCBuZXcgdXJsLEFkZFBhY2thZ2UsYnVsa1B1Ymxpc2hVbnB1Ymxpc2gsQ29udGVudEFkbWluVmlldyxDb3Vwb25BY3Rpb25zLENvdXBvbkRlYWN0aXZhdGUsQ291cG9uRGVsZXRlLENvdXBvbkVkaXQsQ291cG9uVmlldyxDcmVhdGUgSW5zdGl0dXRlICxDcmVhdGUgbmV3IGNvdXBvbixDcmVhdGUgbmV3IGdyb3VwLENyZWF0ZSBuZXcgcGVybWlzc2lvbnMsQ3JlYXRlIE5ldyBQb3N0LENyZWF0ZSBuZXcgcm9sZSxDcmVhdGUgTmV3IFVzZXIsQ3JlYXRlU29jaWFsR3JvdXAsRGFzaGJvYXJkVmlldyxEZWxldGUgcm9sZSxEZWxldGUgdXNlcnMsRGVsZXRlU29jaWFsR3JvdXAsRG91YnQgUm9vbSxEdXBsaWNhdGVQYWNrYWdlLEVkaXQgdXNlcnMsRWRpdFBhY2thZ2UsRWRpdFBvc3QsRWRpdFNvY2lhbEdyb3VwLEZhY3VsdHksZmFjdWx0eWFkbWluLEZlZWRDdXN0b21pc2F0aW9uLEZpeGVkUGVyaW9kTW9jayxHbG9iYWxDb25maWcsR3JvdXBEZWxldGUsR3JvdXBFZGl0LEdyb3VwcyxJbnN0aXR1dGVzLEpvaW5fQXNfVGVhY2hlcixMY3NWaWV3LExpZ2h0aG91c2VBY3Rpb25zLExpZ2h0aG91c2VWaWV3LExpbmtDaGlsZFBhY2thZ2UsTGlua1BhcmVudFBhY2thZ2UsTGl2ZUNsYXNzLFBhY2thZ2VBY3Rpb24sUGFja2FnZUlPU1ByaWNlVXBsb2FkLHBhY2thZ2VVcGdyYWRlLFBhY2thZ2VWaWRlb1VwbG9hZCxQYWNrYWdlVmlldyxQQ1IgYWRtaW4sUERQRnJlZUNvbnRlbnQsUGVybWlzc2lvbkFjdGlvbnMsUGVybWlzc2lvbkRlbGV0ZSxQZXJtaXNzaW9uRWRpdCxQZXJtaXNzaW9uc1ZpZXcsUHJvbW9Db2RlcyxSb2xlQWN0aW9ucyxSb2xlRGVsZXRlLFJvbGVFZGl0LFJvbGVzVmlldyxTRU8sU2VvIEVkaXRvcixTZXRPckNoYW5nZVBhc3N3b3JkLFNraWxsQ2VydGlmaWNhdGlvbixTb2NpYWxHcm91cEFjdGlvbnMsU29jaWFsVXNlcnNWaWV3LFNvY2lhbF9Hcm91cHMsU3BvbnNvcmVkTGlzdCxTVE9QV09SRFMsU3RvcmVmcm9udEFkbWluLFN1cGVyQWRtaW5fVGVzdCx0ZXN0aW5nYWRtaW4sVXNlcnNWIiwicm9sZSI6IlN1cGVyQWRtaW4iLCJpYXQiOjE2ODg0NTc2NzZ9.oFwXxDcVpILFdF4Y-TZPnmCOqdCRsztXwFiQ6StLIXBTgsCQmIdWudNLCXlfUvATQS-RZcViH52HqziKOYJ5Yw";
            packageTitle = certificateUtil.verifyLiveClassCertification(strToken);
            if (packageTitle == null) {
                myContentMsgList.addAll(certificateUtil.certificateMsgList);
                return false;
            }
            System.out.println("Package Title:->" + packageTitle);

            loginUtilObj = new LoginUtil(driver);

            result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), true);
            if (!result) {
                myContentMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            homeUtilObj = new HomePageUtil(driver);

            result = homeUtilObj.clickStoreBtn(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            storeUtilObj = new StorePageUtil(driver);

            result = storeUtilObj.clickSearchIcon(driver);
            if (!result) {
                myContentMsgList.addAll(storeUtilObj.storePageMsgList);
                return result;
            }

            result = storeUtilObj.enterPackageNameInSearchField(driver,
                    packageTitle.split(" ")[packageTitle.split(" ").length - 1]);
            if (!result) {
                myContentMsgList.addAll(storeUtilObj.storePageMsgList);
                return result;
            }
            result = storeUtilObj.selectProductType(driver, ProductType.LIVE_CLASSES);
            if (!result) {
                myContentMsgList.addAll(storeUtilObj.storePageMsgList);
                return result;
            }

            result = storeUtilObj.validateCertificateCourseTag(driver);
            if (!result) {
                myContentMsgList.addAll(storeUtilObj.storePageMsgList);
                return result;
            }

            result = storeUtilObj.clickPackageNameInSearchResult(driver, packageTitle);
            if (!result) {
                myContentMsgList.addAll(storeUtilObj.storePageMsgList);
                return result;
            }

            purchasePackageUtilObj = new PurchasePackageUtil(driver);
            result = purchasePackageUtilObj.verifyUnpurchasedPackagePage(driver);
            if (!result) {
                myContentMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
                return result;
            }
            // result=purchasePackageUtilObj.validateCertificateTagOnPurchasePage(driver);
            // if(!result) {
            // myContentMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
            // return result;
            // }

            result = purchasePackageUtilObj.applyCouponCode(driver, configObj.getCoupon());
            if (!result) {
                myContentMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
                return result;
            }

            result = purchasePackageUtilObj.clickBuyNowBtn(driver);
            if (!result) {
                myContentMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
                return result;
            }

            orderSuccessUtilObj = new OrderSuccessUtil(driver);
            result = orderSuccessUtilObj.handleRateUsPopUpWindow(driver);
            if (!result) {
                myContentMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
                return result;
            }
            result = orderSuccessUtilObj.verifyOrderSuccessPage(driver);
            if (!result) {
                myContentMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
                return result;
            }

            result = cfObj.pressAndroidKey(driver, key.BACK, 3);
            if (!result) {
                myContentMsgList.add("Unable to press Android back key");
                return result;
            }

            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = clickOnPurchasedBtn(driver);
            if (!result) {
                myContentMsgList.add("Not able to click Purchased button.");
                return result;
            }

            result = clickOnPackageInPurchasedSection(driver, packageTitle);
            if (!result) {
                myContentMsgList.add("Not able to click Purchased package.");
                return result;
            }

            liveClassesPageUtilObj = new LiveClassesPageUtil(driver);
            result = liveClassesPageUtilObj.handledNotificationPopUp(driver);
            if (!result) {
                myContentMsgList.add("Not able to handled NotificationPopUp.");
                return result;
            }

            result = liveClassesPageUtilObj.clickOnGotItBtn(driver);
            if (!result) {
                myContentMsgList.add("Not able to click GotIt button.");
                return result;
            }

            videoCoursesPageUtilObj = new VideoCoursesPageUtil(driver);
            result = liveClassesPageUtilObj.validateLiveClassCertificateCell(driver);
            if (!result) {
                myContentMsgList.addAll(liveClassesPageUtilObj.liveClassMsgList);
                return result;
            }
            result = liveClassesPageUtilObj.validateCertificateLiveClassCommingSoonBtn(driver);
            if (!result) {
                myContentMsgList.addAll(liveClassesPageUtilObj.liveClassMsgList);
                return result;
            }

            result = clickOnPackageInPurchasedSection(driver, packageTitle);
            if (!result) {
                myContentMsgList.add("Not able to click Purchased package.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver,
                    liveClassesPageUtilObj.liveClassPageObj.getLiveClassStatusTag().get(0), 10);
            if (!result) {
                myContentMsgList.add("LiveClassStatusTag is not visible.");
                return result;
            }

            childPackageUtilObj = new ChildPackageUtil(driver);
            result = childPackageUtilObj.validateUpdatedCertificatePercentage(driver);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.clickOnGenerateCertificateBtn(driver);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.fillCandidateDetailsForm(driver);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }
            // certificatePreviewUtilObj=new CertificatePreviewUtil(driver);
            // result=certificatePreviewUtilObj.validateCertificatePreview(driver);
            // if (!result) {
            // myContentMsgList.addAll(certificatePreviewUtilObj.certificatePreviewMsgList);
            // return result;
            // }
            // result=certificatePreviewUtilObj.validateEditDetailsBtn(driver);
            // if (!result) {
            // myContentMsgList.addAll(certificatePreviewUtilObj.certificatePreviewMsgList);
            // return result;
            // }
            // result=certificatePreviewUtilObj.validateSubmitDetailsBtn(driver,null);
            // if (!result) {
            // myContentMsgList.addAll(certificatePreviewUtilObj.certificatePreviewMsgList);
            // return result;
            // }
            //
            // result=childPackageUtilObj.clickOnViewCertificateBtn(driver,null);
            // if (!result) {
            // myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
            // return result;
            // }
            //
            // result=cfObj.pressAndroidKey(driver, key.BACK, 2);
            // if (!result) {
            // myContentMsgList.add("Not able to click back button.");
            // return result;
            // }
            //
            // result=homeUtilObj.openNavigationDrawer(driver);
            // if (!result) {
            // myContentMsgList.addAll(homeUtilObj.homeMsgList);
            // return result;
            // }
            // result=homeUtilObj.clickOnUserProfileSection(driver);
            // if (!result) {
            // myContentMsgList.addAll(homeUtilObj.homeMsgList);
            // return result;
            // }
            //
            // userProfilePageUtilObj=new UserProfilePageUtil(driver);
            // Thread.sleep(1000);
            // result=userProfilePageUtilObj.clickOnProfileDotIcon(driver);
            // if (!result) {
            // myContentMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
            // return result;
            // }
            // result=userProfilePageUtilObj.clickOnMyCertificatesTab(driver);
            // if (!result) {
            // myContentMsgList.addAll(userProfilePageUtilObj.userProfileMsgList);
            // return result;
            // }
            //
            // result=certificatePreviewUtilObj.validateCertificateShareAndDownloadBtn(driver);
            // if (!result) {
            // myContentMsgList.addAll(certificatePreviewUtilObj.certificatePreviewMsgList);
            // return result;
            // }
        } catch (Exception e) {
            myContentMsgList.add("verifyCertificationInLiveClass_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyMyContentSearchTest(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        List<String> subjectList = new ArrayList<>();

        try {
            loginUtilObj = new LoginUtil(driver);
            result = loginUtilObj.selectYourCategoryExamLanguage(driver);
            if (!result) {
                myContentMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            result = loginUtilObj.verifyLoginUsingEmailId(driver, "up8111@gmail.com", "amitpundir", true);
            if (!result) {
                myContentMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            homeUtilObj = new HomePageUtil(driver);

            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = clickOnPurchasedBtn(driver);
            if (!result) {
                myContentMsgList.add("Not able to click Purchase button.");
                return result;
            }

            result = clickOnSpecificPurchasedPackage(driver, "20 sep video");
            if (!result) {
                myContentMsgList.add("Not able to click Specific purchase package.");
                return result;
            }
            batchUtilObj = new BatchUtil(driver);
            subjectList = batchUtilObj.getAllSubjects(driver, subjectList);
            if (subjectList == null) {
                myContentMsgList.addAll(batchUtilObj.batchMsgList);
                return false;
            }
            System.out.println(subjectList);

            result = validateContentSearchFunction(driver, subjectList);
            if (!result) {
                myContentMsgList.add("Not able to validateContentSearchFunction.");
                return result;
            }

            cfObj.pressAndroidKey(driver, key.BACK, 1);
            result = validateContentFilterFunction(driver, "sep");
            if (!result) {
                myContentMsgList.add("Not able to validateContentFilterFunction.");
                return result;
            }

        } catch (Exception e) {
            myContentMsgList.add("verifyMyContentSearchTest_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean validateContentSearchFunction(AppiumDriver<MobileElement> driver, List<String> subjectList) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getIconSearch(), 10);
            if (!result) {
                cfObj.scrollUtill(driver, 1, direction.UP);
                result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getIconSearch(), 10);
                if (!result) {
                    myContentMsgList.add("Search icon is not visible.");
                    return result;
                }
            }
            cfObj.commonClick(myContentPageObj.getIconSearch());

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getFieldText(), 10);
            if (!result) {
                myContentMsgList.add("Search Field is not visible.");
                return result;
            }

            for (int i = 0; i < subjectList.size(); i++) {
                String contentTitle = subjectList.get(i);
                result = cfObj.commonSetTextTextBox(myContentPageObj.getFieldText(), contentTitle);
                if (!result) {
                    myContentMsgList.add("Not able to enter text.");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                        "//*[contains(@text,'" + contentTitle + "')]", "xpath", 10);
                if (!result) {
                    myContentMsgList.add("Searched content is not visible.");
                    return result;
                }

                cfObj.commonClick(cfObj.commonGetElement(driver, "clearSearchButton", "id"));
            }

        } catch (Exception e) {
            myContentMsgList.add("validateContentSearchFunction_Exception: " + e.getMessage());
            result = false;
        }
        return result;

    }

    public boolean validateContentFilterFunction(AppiumDriver<MobileElement> driver, String packageName) {
        boolean result = true;
        try {
            commonTestUtilObj = new CommonTestUtil(driver);
            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getIconSearch(), 10);
            if (!result) {
                myContentMsgList.add("Search icon is not visible.");
                return result;
            }
            cfObj.commonClick(myContentPageObj.getIconSearch());

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getFieldText(), 10);
            if (!result) {
                myContentMsgList.add("Search Field is not visible.");
                return result;
            }
            result = cfObj.commonSetTextTextBox(myContentPageObj.getFieldText(), packageName);
            if (!result) {
                myContentMsgList.add("Not able to enter text.");
                return result;
            }

            cfObj.commonClick(myContentPageObj.getBtnFilter());
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "android.widget.CheckBox", "class", 30);
            if (!result) {
                myContentMsgList.add("FilterContent option is not visible.");
                return result;
            }

            int contentCount = myContentPageObj.getFilterContentOption().size();

            cfObj.commonClick(myContentPageObj.getFilterResetBtn());

            for (int i = 0; i < contentCount; i++) {
                result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getBtnFilter(), 30);
                if (!result) {
                    myContentMsgList.add("FilterBtn is not visible.");
                    return result;
                }

                cfObj.commonClick(myContentPageObj.getBtnFilter());

                result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getFilterContentOption().get(i),
                        30);
                if (!result) {
                    myContentMsgList.add("FilterContent option is not visible.");
                    return result;
                }

                cfObj.commonClick(myContentPageObj.getFilterContentOption().get(i));

                result = myContentPageObj.getFilterContentOption().get(i).getAttribute("checked")
                        .equalsIgnoreCase("true");
                if (!result) {
                    myContentMsgList.add("FilterContent option is not selected.");
                    return result;
                }

                String contentType = myContentPageObj.getFilterContentOption().get(i).getText();
                if (contentType == null) {
                    myContentMsgList.add("Content type text is null.");
                    return false;
                }

                if (contentType.equalsIgnoreCase("Models 3d")) {
                    cfObj.commonClick(cfObj.commonGetElement(driver, "close", "id"));
                    continue;
                }
                cfObj.commonClick(myContentPageObj.getFilterApplyBtn());
                Thread.sleep(2000);

                switch (contentType) {
                    case "Videos":
                        List<MobileElement> watchBtn = cfObj.commonGetElements(driver, "//*[@text='Watch']", "xpath");
                        cfObj.commonClick(watchBtn.get(0));
                        if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "txt_gotit", "id", 5)) {
                            cfObj.commonClick(cfObj.commonGetElement(driver, "txt_gotit", "id"));
                        }
                        result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "video_course_title_head", "id",
                                10);
                        if (!result) {
                            myContentMsgList.add("Video description is not visible.");
                            return result;
                        }

                        cfObj.pressAndroidKey(driver, key.BACK, 1);

                        break;
                    case "Test series":
                        result = validateTestSeriesContent(driver);
                        if (!result) {
                            myContentMsgList.add("Not able to validate TestSeriesContent.");
                            return result;
                        }

                        if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "btn_coming_soon", "id", 10)) {
                            cfObj.commonClick(cfObj.commonGetElements(driver, "btn_coming_soon", "id").get(0));
                            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "btn_coming_soon", "id", 10);
                            if (!result) {
                                myContentMsgList.add("Comming soon button is clickable.");
                                return result;
                            }
                        }

                        if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "btn_select", "id", 10)) {
                            cfObj.commonClick(cfObj.commonGetElements(driver, "btn_select", "id").get(0));
                            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "submit", "id", 10);
                            if (!result) {
                                myContentMsgList.add("Quiz content button is not visible.");
                                return result;
                            }
                            int quizContentCount = cfObj.commonGetElements(driver, "submit", "id").size();

                            for (int j = 0; j < quizContentCount; j++) {
                                if (j != 0) {
                                    cfObj.commonClick(cfObj.commonGetElements(driver, "btn_select", "id").get(0));
                                    result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "submit", "id", 10);
                                    if (!result) {
                                        myContentMsgList.add("Quiz content button is not visible.");
                                        return result;
                                    }
                                }
                                System.out.println(myContentPageObj.getQuizContentBtn().size());
                                result = cfObj.commonWaitForElementToBeVisible(driver,
                                        myContentPageObj.getQuizContentBtn().get(j), 30);
                                if (!result) {
                                    myContentMsgList.add("Quiz content button is not visible.");
                                    return result;
                                }

                                String quizBtnStatus = myContentPageObj.getQuizContentBtn().get(j).getText();
                                if (quizBtnStatus == null) {
                                    myContentMsgList.add("QuizBtn status text is null.");
                                    return false;
                                }
                                cfObj.commonClick(myContentPageObj.getQuizContentBtn().get(j));
                                switch (quizBtnStatus) {
                                    case "Resume":
                                        cfObj.handleHints(driver);
                                        result = commonTestUtilObj.verifyInstructionPage(driver);
                                        if (!result) {
                                            myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                                            return result;
                                        }
                                        result = cfObj.commonVerifyValueTextBox(
                                                commonTestUtilObj.commonTestPageObj.getBtnStartTest(), "RESUME TEST");
                                        if (!result) {
                                            myContentMsgList.add("ResumeTest button text is not matching.");
                                            return result;
                                        }

                                        break;

                                    case "Result":
                                        result = commonTestUtilObj.handleTestRatingPopUp(driver);
                                        if (!result) {
                                            myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                                            return result;
                                        }

                                        result = commonTestUtilObj.verifyAndCloseCoinPopup(driver);
                                        if (!result) {
                                            myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                                            return result;
                                        }
                                        Thread.sleep(2000);

                                        result = commonTestUtilObj.verifyTestAnalysisDailyQuizPage(driver, false);
                                        if (!result) {
                                            myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                                            return result;
                                        }
                                        if (cfObj.commonWaitForElementToBeVisible(driver,
                                                commonTestUtilObj.commonTestPageObj.getRatingPopUpTitle(), 30)) {
                                            result = commonTestUtilObj.handleTestRatingPopUp(driver);
                                            if (!result) {
                                                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                                                return result;
                                            }
                                            cfObj.pressAndroidKey(driver, key.BACK, 1);
                                        }

                                        break;
                                    case "Attempt":
                                        cfObj.handleHints(driver);
                                        result = commonTestUtilObj.verifyInstructionPage(driver);
                                        if (!result) {
                                            myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                                            return result;
                                        }

                                        break;

                                }
                                if (!quizBtnStatus.equalsIgnoreCase("Result")) {
                                    cfObj.pressAndroidKey(driver, key.BACK, 1);
                                }

                            }

                        }
                        break;

                    case "Ebooks":

                        result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='Read']", "xpath", 10);
                        if (!result) {
                            myContentMsgList.add("EBook Read button is not visible.");
                            return result;
                        }

                        cfObj.commonClick(driver, "//*[@text='Read']", "xpath");
                        if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                                "//android.widget.Button[@text='ACCEPT']", "xpath", 5)) {
                            cfObj.commonClick(
                                    cfObj.commonGetElement(driver, "//android.widget.Button[@text='ACCEPT']", "xpath"));
                        }

                        // Not able to inspect PDF screen
                        Thread.sleep(2000);
                        cfObj.pressAndroidKey(driver, key.BACK, 1);

                        break;

                    case "Online live classes":
                        List<MobileElement> liveClassWatchBtn = cfObj.commonGetElements(driver, "//*[@text='Watch']",
                                "xpath");
                        cfObj.commonClick(liveClassWatchBtn.get(0));
                        if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "tv_notify_yes", "id", 5)) {
                            cfObj.commonClick(cfObj.commonGetElement(driver, "tv_notify_yes", "id"));
                        }
                        if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "txt_gotit", "id", 5)) {
                            cfObj.commonClick(cfObj.commonGetElement(driver, "txt_gotit", "id"));
                        }
                        result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "video_course_title_head", "id",
                                10);
                        if (!result) {
                            myContentMsgList.add("Video description is not visible.");
                            return result;
                        }

                        cfObj.pressAndroidKey(driver, key.BACK, 1);

                        break;
                }

                cfObj.commonClick(myContentPageObj.getBtnFilter());
                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "android.widget.CheckBox", "class",
                        10);
                if (!result) {
                    myContentMsgList.add("FilterContent option is not visible.");
                    return result;
                }

                cfObj.commonClick(myContentPageObj.getFilterResetBtn());
                Thread.sleep(2000);

            }

        } catch (Exception e) {
            myContentMsgList.add("validateContentFilterFunction_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean validateTestSeriesContent(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            commonTestUtilObj = new CommonTestUtil(driver);

            if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "submit", "id", 10)) {

                List<MobileElement> quizContentBtn = cfObj.commonGetElements(driver, "submit", "id");

                for (int i = 0; i < quizContentBtn.size(); i++) {
                    result = cfObj.commonWaitForElementToBeVisible(driver, quizContentBtn.get(i), 10);
                    if (!result) {
                        myContentMsgList.add("Quiz content button is not visible.");
                        return result;
                    }

                    String quizBtnStatus = quizContentBtn.get(i).getText();
                    if (quizBtnStatus == null) {
                        myContentMsgList.add("Quiz button status text is null.");
                        return false;
                    }
                    cfObj.commonClick(quizContentBtn.get(i));
                    switch (quizBtnStatus) {
                        case "Resume":
                            cfObj.handleHints(driver);
                            result = commonTestUtilObj.verifyInstructionPage(driver);
                            if (!result) {
                                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                                return result;
                            }
                            result = cfObj.commonVerifyValueTextBox(commonTestUtilObj.commonTestPageObj.getBtnStartTest(),
                                    "RESUME TEST");
                            if (!result) {
                                myContentMsgList.add("ResumeTest button text is not matching.");
                                return result;
                            }

                            break;

                        case "Result":
                            result = commonTestUtilObj.handleTestRatingPopUp(driver);
                            if (!result) {
                                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                                return result;
                            }

                            result = commonTestUtilObj.verifyAndCloseCoinPopup(driver);
                            if (!result) {
                                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                                return result;
                            }
                            Thread.sleep(2000);

                            result = commonTestUtilObj.verifyTestAnalysisDailyQuizPage(driver, false);
                            if (!result) {
                                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                                return result;
                            }

                            break;
                        case "Attempt":
                            cfObj.handleHints(driver);
                            result = commonTestUtilObj.verifyInstructionPage(driver);
                            if (!result) {
                                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                                return result;
                            }

                            break;

                    }
                    if (!quizBtnStatus.equalsIgnoreCase("Result")) {
                        cfObj.pressAndroidKey(driver, key.BACK, 1);
                    }

                }
            } else {
                myContentMsgList.add("Quiz content button is not visible.");
            }

        } catch (Exception e) {
            myContentMsgList.add("validateTestSeriesContent_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyBookMarkedMaterialBookmarksSection(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            cfObj.commonClick(myContentPageObj.getVideoBookmarkdedSection());
            for (int i = 0; i < 4; i++) {
                switch (i) {
                    case 0:
                        cfObj.commonClick(myContentPageObj.getVideoBookmarkdedSection());
                        result = false;
                        if (cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getBookMarkVideos(), 5)) {
                            result = true;
                            result = removedVideoPostFromBookMarkSection(driver);
                            if (!result) {
                                myContentMsgList.add("Not able to removed Video Post From BookMarkSection.");
                                return result;
                            }
                        }

                        break;

                    case 1:
                        cfObj.commonClick(myContentPageObj.getArticlesBookmarksSection());
                        result = false;
                        if (cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getBookMarkVideos(), 10)) {
                            result = true;
                            result = removedMaterialFromBookMarkSection(driver);
                            if (!result) {
                                myContentMsgList.add("Not able to removed Material From BookMarkSection.");
                                return result;
                            }
                        }
                        break;
                    case 2:
                        cfObj.commonClick(myContentPageObj.getCurrentAffairsInBookmarksSection());
                        result = false;
                        if (cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getBookMarkVideos(), 10)) {
                            result = true;
                            result = removedMaterialFromBookMarkSection(driver);
                            if (!result) {
                                myContentMsgList.add("Not able to removed Material From BookMarkSection.");
                                return result;
                            }
                        }
                        break;

                    case 3:
                        cfObj.commonClick(myContentPageObj.getJobAlertsBookmarksSection());
                        result = false;
                        if (cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getBookMarkVideos(), 10)) {
                            result = true;
                            result = removedMaterialFromBookMarkSection(driver);
                            if (!result) {
                                myContentMsgList.add("Not able to removed Material From BookMarkSection.");
                                return result;
                            }
                        }
                        break;
                }
                if (result == true) {
                    break;
                }
            }
            Thread.sleep(3000);

            cfObj.pressAndroidKey(driver, key.BACK, 1);

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifyBookMarkedMaterialBookmarksSection_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean verifyMockTestStoreFrontLiveTest(AppiumDriver<MobileElement> driver, boolean isCalculator,
                                                    boolean isFullLength, String packageName) {
        boolean result = true;
        String mockName;
        int mockTestId;
        try {
            mockTestApiUtilObj = new MockTestApiUtil();
            result = mockTestApiUtilObj.createMockTestStoreFrontLiveTest(isCalculator, isFullLength);
            if (!result) {
                myContentMsgList.add("Not able to create Live MockTest.");
                return result;
            }

            mockName = MockTestApiUtil.strFixedMockName;
            if (mockName == null) {
                myContentMsgList.add("Mock name is null.");
                return false;
            }

            mockTestId = Integer.parseInt(MockTestApiUtil.strFixedMockTestId);
            packageApiUtilObj = new PackageApiUtil();
            result = packageApiUtilObj.assignTestSeriesToPackage(1, mockTestId, 41293);
            if (!result) {
                myContentMsgList.addAll(packageApiUtilObj.packagetApiMsgList);
                return result;
            }
            System.out.println("----->" + mockName);

            loginUtilObj = new LoginUtil(driver);
            result = loginUtilObj.verifyLoginUsingEmailId(driver, "abhay.ray@adda247.com", "amitpundir", true);
            if (!result) {
                myContentMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            homeUtilObj = new HomePageUtil(driver);
            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = clickOnPurchasedBtn(driver);
            if (!result) {
                myContentMsgList.add("Not able to click Purchased button.");
                return result;
            }

            result = clickOnSpecificPurchasedPackage(driver, packageName);
            if (!result) {
                myContentMsgList.add("Not able to click Package in PurchasedSection.");
                return result;
            }

            result = homeUtilObj.verifyViewAllContentInSelectedCourse(driver);
            if (!result){
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = goToChildPackage(driver);
            if (!result) {
                myContentMsgList.add("goToChildPackage failed");
                return result;
            }

            Thread.sleep(10000);

            childPackageUtilObj = new ChildPackageUtil(driver);

            result = cfObj.commonWaitForElementToBeVisible(driver,
                    childPackageUtilObj.packagePageObj.getStatusOfTest().get(0), 30);
            if (!result) {
                myContentMsgList.add("getStatusOfTest of 1st is not visible.");
                return result;
            }

            if (isFullLength) {

                result = cfObj.commonWaitForElementToBeVisible(driver,
                        childPackageUtilObj.packagePageObj.fullLengthLiveTestTab().get(0), 10);
                if (!result) {
                    myContentMsgList.add("fullLengthLiveTestTab text is not visible.");
                    return result;
                }
            }

            String comingSoonText = childPackageUtilObj.packagePageObj.getStatusOfTest().get(0).getText();

            if (!comingSoonText.contains("COMING SOON")) {

                result = cfObj.commonWaitForElementToBeVisible(driver,
                        childPackageUtilObj.packagePageObj.getStatusOfTest().get(0), 10);
                if (!result) {
                    myContentMsgList.add("Schedule On status is not visible.");
                    return result;
                }

                String status = childPackageUtilObj.packagePageObj.getStatusOfTest().get(0).getText();

                if (!status.contains("SCHEDULED ON")) {

                    System.out.println("The status is not SCHEDULED ON");
                }

                result = cfObj.commonWaitForElementToBeVisible(driver,
                        childPackageUtilObj.packagePageObj.getTimeDetailOfTest().get(0), 10);
                if (!result) {
                    myContentMsgList.add("Mock time date text is not visible.");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver,
                        childPackageUtilObj.packagePageObj.allLiveTestTab().get(0), 10);
                if (!result) {
                    myContentMsgList.add("allLiveTestTab text is not visible.");
                    return result;
                }

                Thread.sleep(200000);

                result = cfObj.commonWaitForElementToBeVisible(driver,
                        childPackageUtilObj.packagePageObj.getStatusOfTest().get(0), 30);
                if (!result) {
                    myContentMsgList.add("getStatusOfTest is not visible.");
                    return result;
                }

                String testStatuString = childPackageUtilObj.packagePageObj.getStatusOfTest().get(0).getText();

                if (!testStatuString.equalsIgnoreCase("ATTEMPT")) {

                    Thread.sleep(20000);

                    String testStatusAgain = childPackageUtilObj.packagePageObj.getStatusOfTest().get(0).getText();

                    if (!testStatusAgain.equalsIgnoreCase("ATTEMPT")) {
                        myContentMsgList.add("Attempt test not coming, after it is live");
                        return false;
                    }
                }

                result = cfObj.commonWaitForElementToBeVisible(driver,
                        childPackageUtilObj.packagePageObj.getTitleQuiz().get(0), 10);
                if (!result) {
                    myContentMsgList.add("Quiz title is not visible.");
                    return result;
                }

                cfObj.commonClick(childPackageUtilObj.packagePageObj.getTitleQuiz().get(0));

                Thread.sleep(1000);

                cfObj.commonClick(childPackageUtilObj.packagePageObj.getTitleQuiz().get(0));

                Thread.sleep(3000);

                result = cfObj.commonWaitForElementToBeVisible(driver,
                        childPackageUtilObj.packagePageObj.getBtnStartTest(), 10);
                if (!result) {
                    myContentMsgList.add("Start btn in instruction page is not visible.");
                    return result;
                }

                commonTestUtilObj = new CommonTestUtil(driver);
                result = commonTestUtilObj.verifyInstructionPage(driver);
                if (!result) {
                    myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                    return result;
                }

                int questionCount = Integer
                        .parseInt(cfObj.commonGetElement(driver, "com.adda247.app:id/total_question_count", "id").getText());

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'Time Lapsed')]",
                        "xpath", 120);
                if (!result) {
                    myContentMsgList.add("Time Lapsed text in time lapsed popup is not visible.");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'Resume')]",
                        "xpath", 10);
                if (!result) {
                    myContentMsgList.add("Resume btn in time lapsed popup is not visible.");
                    return result;
                }

                cfObj.commonClick(cfObj.commonGetElement(driver, "//*[contains(@text,'Resume')]", "xpath"));

                result = cfObj.commonWaitForElementToBeVisible(driver,
                        commonTestUtilObj.commonTestPageObj.getQuestionNumber(), 10);
                if (!result) {
                    myContentMsgList.add("QuestionNumber is not visible.");
                    return result;
                }

                result = cfObj.commonVerifyValueTextBox(commonTestUtilObj.commonTestPageObj.getQuestionNumber(), "1");
                if (!result) {
                    myContentMsgList.add("Resumed QuestionNumber is not matching.");
                    return result;
                }

                result = commonTestUtilObj.takeAndSubmitTest(driver, "1", questionCount);
                if (!result) {
                    myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                    return result;
                }

                result = commonTestUtilObj.verifyAndSubmitFinishTestPopUp(driver);
                if (!result) {
                    myContentMsgList.add("Unable to verify finish test pop-up");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                        "//*[contains(@text,'Test Submitted')]", "xpath", 10);
                if (!result) {
                    myContentMsgList.add("Test Submitted popup title is not visible.");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                        "com.adda247.app:id/dialogDescription_textView", "id", 10);
                if (!result) {
                    myContentMsgList.add("Description in popup message is not visible.");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/positive_button",
                        "id", 10);
                if (!result) {
                    myContentMsgList.add("okay btn in popup is not visible.");
                    return result;
                }

                cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/positive_button", "id"));

                Thread.sleep(1000);

                result = cfObj.commonWaitForElementToBeVisible(driver,
                        childPackageUtilObj.packagePageObj.getStatusOfTest().get(0), 30);
                if (!result) {
                    myContentMsgList.add("getStatusOfTest is not visible.");
                    return result;
                }

                String testStatuString2 = childPackageUtilObj.packagePageObj.getStatusOfTest().get(0).getText();

                if (!testStatuString2.equalsIgnoreCase("RESULT AWAITED")) {

                    myContentMsgList.add("RESULT AWAITED test not coming");
                    return false;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/tv_result_timer",
                        "id", 10);
                if (!result) {
                    myContentMsgList.add("Mock Result timer text is not visible.");
                    return result;
                }

            } else {
                myContentMsgList.add("The live test is in coming soon, cannot test further");
                return false;
            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifySectionalTimeLimitMockTest_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean verifyInterNetOnOffFunctionOnLiveTest(AppiumDriver<MobileElement> driver, boolean isCalculator,
                                                         boolean isFullLength, String packageName) {
        boolean result = true;
        String mockName;
        int mockTestId;
        try {
            mockTestApiUtilObj = new MockTestApiUtil();
            result = mockTestApiUtilObj.createMockTestStoreFrontLiveTest(isCalculator, isFullLength);
            if (!result) {
                myContentMsgList.add("Not able to create Live MockTest.");
                return result;
            }

            mockName = MockTestApiUtil.strFixedMockName;
            if (mockName == null) {
                myContentMsgList.add("Mock name is null.");
                return false;
            }

            mockTestId = Integer.parseInt(MockTestApiUtil.strFixedMockTestId);
            packageApiUtilObj = new PackageApiUtil();
            result = packageApiUtilObj.assignTestSeriesToPackage(1, mockTestId, 41293);
            if (!result) {
                myContentMsgList.addAll(packageApiUtilObj.packagetApiMsgList);
                return result;
            }
            System.out.println("----->" + mockName);
            loginUtilObj = new LoginUtil(driver);

            loginUtilObj = new LoginUtil(driver);
            result = loginUtilObj.verifyLoginUsingEmailId(driver, "abhay.ray@adda247.com", "amitpundir", true);
            if (!result) {
                myContentMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            homeUtilObj = new HomePageUtil(driver);
            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = clickOnPurchasedBtn(driver);
            if (!result) {
                myContentMsgList.add("Not able to click Purchased button.");
                return result;
            }

            result = clickOnSpecificPurchasedPackage(driver, packageName);
            if (!result) {
                myContentMsgList.add("Not able to click Package in PurchasedSection.");
                return result;
            }

            result = homeUtilObj.verifyViewAllContentInSelectedCourse(driver);
            if (!result){
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = goToChildPackage(driver);
            if (!result) {
                myContentMsgList.add("goToChildPackage failed");
                return result;
            }

            childPackageUtilObj = new ChildPackageUtil(driver);

            Thread.sleep(10000);

            result = cfObj.commonWaitForElementToBeVisible(driver,
                    childPackageUtilObj.packagePageObj.getStatusOfTest().get(0), 30);
            if (!result) {
                myContentMsgList.add("getStatusOfTest of 1st is not visible.");
                return result;
            }

            if (isFullLength == true) {

                result = cfObj.commonWaitForElementToBeVisible(driver,
                        childPackageUtilObj.packagePageObj.fullLengthLiveTestTab().get(0), 10);
                if (!result) {
                    myContentMsgList.add("fullLengthLiveTestTab text is not visible.");
                    return result;
                }
            }

            String comingSoonText = childPackageUtilObj.packagePageObj.getStatusOfTest().get(0).getText();

            if (!comingSoonText.contains("COMING SOON")) {

                result = cfObj.commonWaitForElementToBeVisible(driver,
                        childPackageUtilObj.packagePageObj.getStatusOfTest().get(0), 10);
                if (!result) {
                    myContentMsgList.add("Schedule On status is not visible.");
                    return result;
                }

                String status = childPackageUtilObj.packagePageObj.getStatusOfTest().get(0).getText();

                if (!status.contains("SCHEDULED ON")) {

                    System.out.println("The status is not SCHEDULED ON");
                }

                result = cfObj.commonWaitForElementToBeVisible(driver,
                        childPackageUtilObj.packagePageObj.getTimeDetailOfTest().get(0), 10);
                if (!result) {
                    myContentMsgList.add("Mock time date text is not visible.");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver,
                        childPackageUtilObj.packagePageObj.allLiveTestTab().get(0), 10);
                if (!result) {
                    myContentMsgList.add("allLiveTestTab text is not visible.");
                    return result;
                }

                Thread.sleep(190000);

                result = cfObj.commonWaitForElementToBeVisible(driver,
                        childPackageUtilObj.packagePageObj.getStatusOfTest().get(0), 30);
                if (!result) {
                    myContentMsgList.add("getStatusOfTest is not visible.");
                    return result;
                }

                String testStatuString = childPackageUtilObj.packagePageObj.getStatusOfTest().get(0).getText();

                if (!testStatuString.equalsIgnoreCase("ATTEMPT")) {

                    Thread.sleep(20000);

                    String testStatusAgain = childPackageUtilObj.packagePageObj.getStatusOfTest().get(0).getText();

                    if (!testStatusAgain.equalsIgnoreCase("ATTEMPT")) {
                        myContentMsgList.add("Attempt test not coming, after it is live");
                        return false;
                    }
                }

                result = cfObj.commonWaitForElementToBeVisible(driver,
                        childPackageUtilObj.packagePageObj.getTitleQuiz().get(0), 10);
                if (!result) {
                    myContentMsgList.add("Quiz title is not visible.");
                    return result;
                }

                cfObj.commonClick(childPackageUtilObj.packagePageObj.getTitleQuiz().get(0));

                Thread.sleep(1000);

                cfObj.commonClick(childPackageUtilObj.packagePageObj.getTitleQuiz().get(0));

                Thread.sleep(4000);

                result = cfObj.commonWaitForElementToBeVisible(driver,
                        childPackageUtilObj.packagePageObj.getBtnStartTest(), 10);
                if (!result) {
                    myContentMsgList.add("Start btn in instruction page is not visible.");
                    return result;
                }

                result = cfObj.changeNetworkState(driver, NetworkState.OFF);
                if (!result) {
                    myContentMsgList.add("Failed to turn off internet.");
                    return result;
                }

                System.out.println("Network OFF");

                commonTestUtilObj = new CommonTestUtil(driver);
                result = commonTestUtilObj.verifyInstructionPage(driver);
                if (!result) {
                    myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                    return result;
                }

                int questionCount = Integer
                        .parseInt(cfObj.commonGetElement(driver, "com.adda247.app:id/total_question_count", "id").getText());

                Thread.sleep(80000);

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                        "//*[contains(@text,'You are offline')]", "xpath", 60);
                if (!result) {
                    myContentMsgList.add("You are offline text on instruction page, when internet off is not visible.");
                    return result;
                }

                Thread.sleep(5000);

                result = cfObj.changeNetworkState(driver, NetworkState.ON);
                if (!result) {
                    myContentMsgList.add("Failed to turn ON internet.");
                    return result;
                }

                System.out.println("Network ON");

                Thread.sleep(5000);

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                        "//*[contains(@text,'Your internet connection is restored')]", "xpath", 60);
                if (!result) {
                    myContentMsgList.add(
                            "Your internet connection is restored text on instruction page, when internet on is not visible..");
                    return result;
                }

                Thread.sleep(2000);

                result = cfObj.commonWaitForElementToBeVisible(driver,
                        commonTestUtilObj.commonTestPageObj.getBtnStartTest(), 20);
                if (!result) {
                    myContentMsgList.add("Start btn in instruction page is not visible.");
                    return result;
                }

                cfObj.commonClick(commonTestUtilObj.commonTestPageObj.getBtnStartTest());

                Thread.sleep(2000);

                if (cfObj.commonWaitForElementToBeVisible(driver, commonTestUtilObj.commonTestPageObj.getBtnStartTest(),
                        5)) {
                    System.out.println("Second Attempt to click start test button.");
                    cfObj.commonClick(commonTestUtilObj.commonTestPageObj.getBtnStartTest());
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'Time Lapsed')]",
                        "xpath", 60);
                if (!result) {
                    myContentMsgList.add("Time Lapsed text in time lapsed popup is not visible.");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'Resume')]",
                        "xpath", 10);
                if (!result) {
                    myContentMsgList.add("Resume btn in time lapsed popup is not visible.");
                    return result;
                }

                cfObj.commonClick(cfObj.commonGetElement(driver, "//*[contains(@text,'Resume')]", "xpath"));

                result = commonTestUtilObj.takeAndSubmitTest(driver, isCalculator, false, questionCount, false);
                if (!result) {
                    myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                    return result;
                }

                result = commonTestUtilObj.verifyAndSubmitFinishTestPopUp(driver);
                if (!result) {
                    myContentMsgList.add("Unable to verify finish test pop-up");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                        "//*[contains(@text,'Test Submitted')]", "xpath", 10);
                if (!result) {
                    myContentMsgList.add("Test Submitted popup title is not visible.");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                        "com.adda247.app:id/dialogDescription_textView", "id", 10);
                if (!result) {
                    myContentMsgList.add("Description in popup message is not visible.");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/positive_button",
                        "id", 10);
                if (!result) {
                    myContentMsgList.add("okay btn in popup is not visible.");
                    return result;
                }

                cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/positive_button", "id"));

                Thread.sleep(1000);

                result = cfObj.commonWaitForElementToBeVisible(driver,
                        childPackageUtilObj.packagePageObj.getStatusOfTest().get(0), 30);
                if (!result) {
                    myContentMsgList.add("getStatusOfTest is not visible.");
                    return result;
                }

                String testStatuString2 = childPackageUtilObj.packagePageObj.getStatusOfTest().get(0).getText();

                if (!testStatuString2.equalsIgnoreCase("RESULT AWAITED")) {

                    myContentMsgList.add("RESULT AWAITED test not coming");
                    return false;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/tv_result_timer",
                        "id", 10);
                if (!result) {
                    myContentMsgList.add("Mock Result timer text is not visible.");
                    return result;
                }
            } else {
                myContentMsgList.add("The live test is in coming soon, cannot test further");
                return false;
            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifySectionalTimeLimitMockTest_Exception" + e.getMessage());
        } finally {
            result = cfObj.changeNetworkState(driver, NetworkState.ON);
            if (!result) {
                myContentMsgList.add("Failed to turn ON internet.");
                return result;
            }
        }
        return result;
    }

    public boolean verifyMockTestResultScreen(AppiumDriver<MobileElement> driver, String packageName) {
        boolean result = true;
        String postQuery = "Post Created From Result Screen.";
        try {

            loginUtilObj = new LoginUtil(driver);

            result = loginUtilObj.selectYourCategoryExamLanguage(driver);
            if (!result) {
                myContentMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            result = loginUtilObj.verifyLoginUsingEmailId(driver, "abhay.rai@adda247.com", "0002@aaada!", false);
            if (!result) {
                myContentMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            homeUtilObj = new HomePageUtil(driver);
            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = clickOnPurchasedBtn(driver);
            if (!result) {
                myContentMsgList.add("Not able to click Purchased button.");
                return result;
            }

            result = clickOnSpecificPurchasedPackage(driver, packageName);
            if (!result) {
                myContentMsgList.add("Not able to click Package in PurchasedSection.");
                return result;
            }

            childPackageUtilObj = new ChildPackageUtil(driver);
            if (ConfigFileReader.strApplication.equalsIgnoreCase("Sankalp")) {

                result = childPackageUtilObj.handleSearchInstructionPopUp(driver);
                if (!result) {
                    myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                    return result;
                }

                result = childPackageUtilObj.clickOnPurchasedCourse(driver, packageName);
                if (!result) {
                    myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                    return result;
                }
            }
            cfObj.waitForPageLoading(driver, 5, 2000, cfObj.commonGetElement(driver, "progressBar", "id"));
            result = childPackageUtilObj.clickOnResultStatus(driver);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }
            if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.TextView[@text='RESULT']",
                    "xpath", 3)) {
                result = childPackageUtilObj.clickOnResultStatus(driver);
                if (!result) {
                    myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                    return result;
                }
            }

            commonTestUtilObj = new CommonTestUtil(driver);
            Thread.sleep(3000);
            result = commonTestUtilObj.validateResultAnalysisDropDown(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            for (int i = 0; i < 3; i++) {
                result = commonTestUtilObj.selectSpecificSectionFromResultAnalysisDropDown(driver, i);
                if (!result) {
                    myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                    return result;
                }
                String screenName = commonTestUtilObj.commonTestPageObj.getResultAnalysisDropDown().getText();
                switch (screenName) {
                    case "Result Overview":
                        result = commonTestUtilObj.validateResultOverviewScreen(driver, false);
                        if (!result) {
                            myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                            return result;
                        }

                        break;
                    case "Sections Report":
                        result = commonTestUtilObj.validateSectionsReportScreen(driver);
                        if (!result) {
                            myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                            return result;
                        }

                        break;
                    case "Compare Yourself":
                        result = commonTestUtilObj.validateCompareYourSelfScreen(driver);
                        if (!result) {
                            myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                            return result;
                        }

                        break;

                    default:
                        System.out.println(screenName + " section is not automated.");
                        break;
                }
            }
            result = commonTestUtilObj.selectSpecificSectionFromResultAnalysisDropDown(driver, 0);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.validateShareScoreCardLink(driver,
                    cfObj.commonGetElement(driver, "result_card_title", "id").getText());
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.validateSolutionScreen(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }
            if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
                result = commonTestUtilObj.validatePostNowButton(driver, postQuery);
                if (!result) {
                    myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                    return result;
                }
            }
            result = commonTestUtilObj.clickOnReAttemptBtn(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            cfObj.handleHints(driver);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "start_test", "id", 10);
            if (!result) {
                myContentMsgList.add("Failed to click ReAttempt button. StartTest button is not visible.");
                return result;
            }
            if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {

                result = cfObj.pressAndroidKey(driver, key.BACK, 2);
                if (!result) {
                    myContentMsgList.add("Failed to click back button 2 times.");
                    return result;
                }

                result = homeUtilObj.clickCommunityBtn(driver);
                if (!result) {
                    myContentMsgList.addAll(homeUtilObj.homeMsgList);
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='" + postQuery + "']",
                        "xpath", 10);
                if (!result) {
                    myContentMsgList.add("Created post is not visible.");
                    return result;
                }
            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifyMockTestResultScreen_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean verifyAdmitCardOnPurchasedPackage(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        String strPackageTitle = "Sankalp Bharat MahaPack";
        try {
            communityPageUtilObj = new CommunityPageUtil(driver);
            result = communityPageUtilObj.verifyPushAction(driver, "./Adda247.jpg", "/sdcard/Adda247.jpg");
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            loginUtilObj = new LoginUtil(driver);
            result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), true);
            if (!result) {
                myContentMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            homeUtilObj = new HomePageUtil(driver);
            result = homeUtilObj.clickStoreBtn(driver);
            if (!result) {
                myContentMsgList.add("Unable to open Store Page");
                return result;
            }

            storeUtilObj = new StorePageUtil(driver);
            result = storeUtilObj.clickLiveClassIcon(driver);
            if (!result) {
                myContentMsgList.addAll(storeUtilObj.storePageMsgList);
                return result;
            }

            result = storeUtilObj.clickOnSpecificPackage(driver, "131:Sankalp Bharat MahaPack");
            if (!result) {
                myContentMsgList.addAll(storeUtilObj.storePageMsgList);
                return result;
            }
            purchasePackageUtilObj = new PurchasePackageUtil(driver);
            result = purchasePackageUtilObj.verifyUnpurchasedPackagePage(driver);
            if (!result) {
                myContentMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
                return result;
            }

            result = purchasePackageUtilObj.applyCouponCode(driver, configObj.getCoupon());
            if (!result) {
                myContentMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
                return result;
            }

            result = purchasePackageUtilObj.clickBuyNowBtn(driver);
            if (!result) {
                myContentMsgList.add("Unable to click buy now btn");
                return result;
            }

            userDetailsLayerUtilObj = new UserDetailsLayerUtil(driver);
            if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"User Details\"]", "xpath",
                    10)) {

                result = userDetailsLayerUtilObj.fillUserDetailsForm(driver);
                if (!result) {
                    myContentMsgList.addAll(userDetailsLayerUtilObj.userDetailsLayerMsgList);
                    return result;
                }

                result = userDetailsLayerUtilObj.clickContinueBtn(driver);
                if (!result) {
                    myContentMsgList.addAll(userDetailsLayerUtilObj.userDetailsLayerMsgList);
                    return result;
                }
            }

            feedbackFormUtilObj = new FeedbackFormUtil(driver);

            result = feedbackFormUtilObj.skipFeedbackForm(driver);
            if (!result) {
                myContentMsgList.addAll(feedbackFormUtilObj.feedbackFormMsgList);
                myContentMsgList.add("Unable to skip feedback form");
            }

            orderSuccessUtilObj = new OrderSuccessUtil(driver);
            result = orderSuccessUtilObj.verifyOrderSuccessPage(driver);
            if (!result) {
                myContentMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
                return result;
            }

            cfObj.pressAndroidKey(driver, key.BACK, 3);

            CreateCampaignAdmitCardApiUtil createCampaignAdmitCardApiUtilObj = new CreateCampaignAdmitCardApiUtil();
            CreateCampaignAdmitCardResponse campaignResponse = createCampaignAdmitCardApiUtilObj.createCampaign("131",
                    31540, true);
            if (campaignResponse.getStatusCode() != 200) {
                myContentMsgList.add(
                        "Failed to create Campaign for Admitcard. Status code : -" + campaignResponse.getStatusCode());
                return false;
            }
            if (!campaignResponse.getMessage().equalsIgnoreCase("Campaign created successfully")) {
                myContentMsgList.add(
                        "Failed to create Campaign for Admitcard. Api Message is : -" + campaignResponse.getMessage());
                return false;
            }

            String examName = CreateCampaignAdmitCardApiUtil.examName;
            if (examName == null) {
                myContentMsgList.add("Exam Name is null.");
                return false;
            }

            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.add("Unable to open my contents page.");
                return result;
            }

            result = clickOnPurchasedBtn(driver);
            if (!result) {
                myContentMsgList.add("Failed to click Purchase button.");
                return result;
            }

            result = clickOnSpecificPurchasedPackage(driver, strPackageTitle);
            if (!result) {
                myContentMsgList.add("Failed to click Purchased Package.");
                return result;
            }

            result = clickOnSpecificTabOnRewardScreen(driver, examName);
            if (!result) {
                myContentMsgList.add("Failed to click SpecificTab On RewardScreen.");
                return result;
            }

            result = validatePackageRewardScreen(driver, true, true);
            if (!result) {
                myContentMsgList.add("Failed to validate PackageRewardScreen.");
                return result;
            }

            result = enterName(driver, "AddaUser");
            if (!result) {
                myContentMsgList.add("Failed to Enter Name.");
                return result;
            }
            result = enterRollNo(driver, "1234");
            if (!result) {
                myContentMsgList.add("Failed to Enter Roll Number.");
                return result;
            }

            result = enterAddress(driver, "TestingAddress");
            if (!result) {
                myContentMsgList.add("Failed to Enter Address.");
                return result;
            }

            result = enterExamName(driver, "AddaExam");
            if (!result) {
                myContentMsgList.add("Failed to Enter Exam Name.");
                return result;
            }
            result = enterDOB(driver, "16-09-2002");
            if (!result) {
                myContentMsgList.add("Failed to Enter Date of Birth.");
                return result;
            }
            result = enterGender(driver, "Male");
            if (!result) {
                myContentMsgList.add("Failed to Enter Gender.");
                return result;
            }

            result = uploadAdmitCard(driver);
            if (!result) {
                myContentMsgList.add("Failed to Upload AdmitCard.");
                return result;
            }
            Thread.sleep(2000);
            result = clickOnSubmitBtn(driver);
            if (!result) {
                myContentMsgList.add("Failed to Click Submit button.");
                return result;
            }
            result = validateRewardThankYouScreen(driver);
            if (!result) {
                myContentMsgList.add("Failed to validate RewardThankYouScreen.");
                return result;
            }

            cfObj.commonClick(cfObj.commonGetElement(driver, "back_arrow", "id"));
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "gift_imageview", "id", 10);
            if (!result) {
                myContentMsgList.add("Failed to click Reward thank you screen back button.");
                return result;
            }

            result = !cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//android.widget.TextView[@text='" + examName + "']", "xpath", 3);
            if (!result) {
                myContentMsgList.add(examName + " tab is still visible after submitting the datails.");
                return result;
            }

            cfObj.commonClick(cfObj.commonGetElement(driver, "close", "id"));

            result = !cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "gift_imageview", "id", 3);
            if (!result) {
                myContentMsgList.add("Failed to click Reward screen back button.");
                return result;
            }

            cfObj.pressAndroidKey(driver, key.BACK, 1);

        } catch (Exception e) {

            myContentMsgList.add("verifyAdmitCardOnPurchasedPackage_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean validatePackageRewardScreen(AppiumDriver<MobileElement> driver, boolean isTextField,
                                               boolean isUploadIcon) {
        boolean result = true;
        try {

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "gift_imageview", "id", 10);
            if (!result) {
                myContentMsgList.add("GiftImage is not visible on Reward screen.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "close", "id", 10);
            if (!result) {
                myContentMsgList.add("Close button is not visible on Reward screen.");
                return result;
            }

            if (isTextField) {
                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "textfield_value", "id", 10);
                if (!result) {
                    myContentMsgList.add("TextFields are not visible on Reward screen.");
                    return result;
                }
            }
            if (isUploadIcon) {

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "upload_textview", "id", 10);
                if (!result) {
                    myContentMsgList.add("AdmitCard upload button is not visible on Reward screen.");
                    return result;
                }
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "submitButton", "id", 10);
            if (!result) {
                myContentMsgList.add("Submit button is not visible on Reward screen.");
                return result;
            }

            result = cfObj.commonVerifyValueTextBox(cfObj.commonGetElement(driver, "submitButton", "id"), "false",
                    "enabled");
            if (!result) {
                myContentMsgList.add("Submit button is not in Disable state.");
                return result;
            }

        } catch (Exception e) {
            myContentMsgList.add("validatePackageRewardScreen_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean clickOnSpecificTabOnRewardScreen(AppiumDriver<MobileElement> driver, String tabName) {
        boolean result = true;
        try {

            if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "tabLabel", "id", 10)) {
                int i = 0;
                while (!cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                        "//android.widget.TextView[@text='" + tabName + "']", "xpath", 3)) {
                    if (i > 12) {
                        System.out.println(tabName + " is not available on Reward screen.");
                        result = false;
                        break;
                    }
                    cfObj.swipeLeftOnElement(cfObj.commonGetElements(driver, "tabLabel", "id")
                            .get(cfObj.commonGetElements(driver, "tabLabel", "id").size() - 2), driver);
                    Thread.sleep(500);
                    i++;
                }
                if (!result) {
                    myContentMsgList.add(tabName + " is not available on Reward screen.");
                    return result;
                }

                cfObj.commonClick(
                        cfObj.commonGetElement(driver, "//android.widget.TextView[@text='" + tabName + "']", "xpath"));

                result = cfObj.commonVerifyValueTextBox(cfObj.commonGetElement(driver,
                                "//android.widget.TextView[@text='" + tabName + "']/parent::android.view.ViewGroup", "xpath"),
                        "true", "selected");
                if (!result) {
                    myContentMsgList.add("Failed to select " + tabName + " Tab.");
                    return result;
                }
            }

        } catch (Exception e) {
            myContentMsgList.add("clickOnSpecificTabOnRewardScreen_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean enterName(AppiumDriver<MobileElement> driver, String name) {
        boolean result = true;
        try {

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//android.widget.TextView[@text='NAME:']/following-sibling::android.widget.EditText", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Name text field is not visible.");
                return result;
            }

            result = cfObj.commonSetTextTextBox(cfObj.commonGetElement(driver,
                            "//android.widget.TextView[@text='NAME:']/following-sibling::android.widget.EditText", "xpath"),
                    name);
            if (!result) {
                myContentMsgList.add("Failed to enter name on Name text field.");
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("enterName_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean enterRollNo(AppiumDriver<MobileElement> driver, String rollNo) {
        boolean result = true;
        try {

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//android.widget.TextView[@text='ROLL NUMBER:']/following-sibling::android.widget.EditText",
                    "xpath", 10);
            if (!result) {
                myContentMsgList.add("RollNo text field is not visible.");
                return result;
            }

            result = cfObj.commonSetTextTextBox(cfObj.commonGetElement(driver,
                    "//android.widget.TextView[@text='ROLL NUMBER:']/following-sibling::android.widget.EditText",
                    "xpath"), rollNo);
            if (!result) {
                myContentMsgList.add("Failed to enter RollNo on RollNo text field.");
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("enterRollNo_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean enterAddress(AppiumDriver<MobileElement> driver, String address) {
        boolean result = true;
        try {

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//android.widget.TextView[@text='ADDRESS:']/following-sibling::android.widget.EditText", "xpath",
                    10);
            if (!result) {
                myContentMsgList.add("ADDRESS text field is not visible.");
                return result;
            }

            result = cfObj.commonSetTextTextBox(cfObj.commonGetElement(driver,
                            "//android.widget.TextView[@text='ADDRESS:']/following-sibling::android.widget.EditText", "xpath"),
                    address);
            if (!result) {
                myContentMsgList.add("Failed to enter ADDRESS on ADDRESS text field.");
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("enterAddress_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean enterExamName(AppiumDriver<MobileElement> driver, String examName) {
        boolean result = true;
        try {

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//android.widget.TextView[@text='EXAM NAME:']/following-sibling::android.widget.EditText", "xpath",
                    10);
            if (!result) {
                myContentMsgList.add("EXAM NAME text field is not visible.");
                return result;
            }

            result = cfObj.commonSetTextTextBox(cfObj.commonGetElement(driver,
                    "//android.widget.TextView[@text='EXAM NAME:']/following-sibling::android.widget.EditText",
                    "xpath"), examName);
            if (!result) {
                myContentMsgList.add("Failed to enter EXAM NAME on EXAM NAME text field.");
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("enterExamName_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean enterGender(AppiumDriver<MobileElement> driver, String gender) {
        boolean result = true;
        try {

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//android.widget.TextView[@text='GENDER:']/following-sibling::android.widget.EditText", "xpath",
                    10);
            if (!result) {
                myContentMsgList.add("GENDER text field is not visible.");
                return result;
            }

            result = cfObj.commonSetTextTextBox(cfObj.commonGetElement(driver,
                            "//android.widget.TextView[@text='GENDER:']/following-sibling::android.widget.EditText", "xpath"),
                    gender);
            if (!result) {
                myContentMsgList.add("Failed to enter GENDER on GENDER text field.");
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("enterGender_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean enterDOB(AppiumDriver<MobileElement> driver, String dob) {
        boolean result = true;
        try {

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//android.widget.TextView[@text='DOB:']/following-sibling::android.widget.EditText", "xpath", 10);
            if (!result) {
                myContentMsgList.add("DOB text field is not visible.");
                return result;
            }

            cfObj.commonGetElement(driver,
                            "//android.widget.TextView[@text='DOB:']/following-sibling::android.widget.EditText", "xpath")
                    .sendKeys(dob);

            result = cfObj.commonVerifyValueTextBox(cfObj.commonGetElement(driver,
                            "//android.widget.TextView[@text='DOB:']/following-sibling::android.widget.EditText", "xpath"),
                    dob);
            if (!result) {
                myContentMsgList.add("Failed to enter DOB on DOB text field.");
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("enterDOB_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean clickOnSubmitBtn(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "submitButton", "id", 10);
            if (!result) {
                myContentMsgList.add("Submit button is not visible on Reward screen.");
                return result;
            }

            cfObj.commonClick(cfObj.commonGetElement(driver, "submitButton", "id"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//android.widget.TextView[@text='Thank you for providing the details. You have been rewarded with the below package.']",
                    "xpath", 10);
            if (!result) {
                myContentMsgList.add("Failed to click Submit button.");
                return result;
            }

        } catch (Exception e) {
            myContentMsgList.add("clickOnSubmitBtn_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean validateRewardThankYouScreen(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//android.widget.TextView[@text='Thank you for providing the details. You have been rewarded with the below package.']",
                    "xpath", 10);
            if (!result) {
                myContentMsgList.add("Thank you header is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//android.widget.TextView[@text='This reward package will be added in your \"My Content\" within 48 hours.']",
                    "xpath", 10);
            if (!result) {
                myContentMsgList.add("Reward added header is not visible.");
                return result;
            }

        } catch (Exception e) {
            myContentMsgList.add("validateRewardThankYouScreen_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean uploadAdmitCard(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "upload_textview", "id", 10);
            if (!result) {
                myContentMsgList.add("AdmitCard upload button is not visible on Reward screen.");
                return result;
            }

            cfObj.commonClick(cfObj.commonGetElement(driver, "upload_textview", "id"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "files_layout", "id", 10);
            if (!result) {
                myContentMsgList.add("File upload tab is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "camera_layout", "id", 10);
            if (!result) {
                myContentMsgList.add("Open Camera tab is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "gallery_layout", "id", 10);
            if (!result) {
                myContentMsgList.add("Gallery tab is not visible.");
                return result;
            }

            cfObj.commonClick(cfObj.commonGetElement(driver, "gallery_layout", "id"));
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "com.google.android.documentsui:id/icon_thumb", "id", 30);
            if (!result) {
                myContentMsgList.add("ImageFile is not visible.");
                return result;
            }

            cfObj.commonClick(cfObj.commonGetElement(driver, "com.google.android.documentsui:id/icon_thumb", "id"));
            Thread.sleep(3000);// For handling pageLoading

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "crop_image_menu_crop", "id", 30);
            if (!result) {
                myContentMsgList.add("Crop button is not visible.");
                return result;
            }

            cfObj.commonClick(cfObj.commonGetElement(driver, "crop_image_menu_crop", "id"));
            Thread.sleep(3000);
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//android.widget.TextView[contains(@text,'jpg')]", "xpath", 30);
            if (!result) {
                myContentMsgList.add("Crop button is not Clickable.");
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("uploadAdmitCard_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean goToChildPackage(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//android.widget.ImageView[@content-desc=\"Insurance Exams 2019 Ali 4a\"]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Exam is not visible.");
                return result;
            }

            cfObj.commonClick(cfObj.commonGetElement(driver,
                    "//android.widget.ImageView[@content-desc=\"Insurance Exams 2019 Ali 4a\"]", "xpath"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//android.widget.LinearLayout[@content-desc=\"Test Series\"]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Testseries tab is not visible.");
                return result;
            }

            cfObj.commonClick(cfObj.commonGetElement(driver,
                    "//android.widget.LinearLayout[@content-desc=\"Test Series\"]", "xpath"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//*[contains(@text,'PKS CHILD  LIVETEST  21 | Video Course by Adda 247')]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("child package desc is not visible.");
                return result;
            }

            cfObj.commonClick(cfObj.commonGetElement(driver,
                    "//*[contains(@text,'PKS CHILD  LIVETEST  21 | Video Course by Adda 247')]", "xpath"));

        } catch (Exception e) {
            myContentMsgList.add("goToChildPackage_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyMyContentScreenForNewUser(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            loginUtilObj = new LoginUtil(driver);

            result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), true);
            if (!result) {
                myContentMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            homeUtilObj = new HomePageUtil(driver);
            result = homeUtilObj.clickHomeBtn(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = cfObj.waitForPageLoading(driver, 10, 2000, myContentPageObj.getBtnPurchasedSection());
            if (!result) {
                myContentMsgList.add("Purchase page Loading Error.");
                return result;
            }
            cfObj.commonClick(myContentPageObj.getBtnPurchasedSection());

            result = verifyEmptyPurchasedPage(driver);
            if (!result) {
                myContentMsgList.add("Failed to verifyEmptyPurchasedPage");
                return result;
            }

            storeUtilObj = new StorePageUtil(driver);
            result = storeUtilObj.verifyStorePage(driver);
            if (!result) {
                myContentMsgList.addAll(storeUtilObj.storePageMsgList);
                return result;
            }

            result = storeUtilObj.clickEBooksIcon(driver);
            if (!result) {
                myContentMsgList.addAll(storeUtilObj.storePageMsgList);
                return result;
            }
            eBooksPageUtilObj = new EBooksPageUtil(driver);
            result = eBooksPageUtilObj.clickEBookRandomlyFromList(driver);
            if (!result) {
                myContentMsgList.addAll(eBooksPageUtilObj.eBookMsgList);
                return result;
            }

            purchasePackageUtilObj = new PurchasePackageUtil(driver);
            result = purchasePackageUtilObj.verifyUnpurchasedPackagePage(driver);
            if (!result) {
                myContentMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
                return result;
            }

            // Apply code for 0 amount
            result = purchasePackageUtilObj.applyCouponCode(driver, configObj.getCoupon());
            if (!result) {
                myContentMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
                return result;
            }

            result = purchasePackageUtilObj.clickBuyNowBtn(driver);
            if (!result) {
                myContentMsgList.add("Unable to click buy now btn");
                return result;
            }

            userDetailsLayerUtilObj = new UserDetailsLayerUtil(driver);
            if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"User Details\"]", "xpath",
                    10)) {

                result = userDetailsLayerUtilObj.fillUserDetailsForm(driver);
                if (!result) {
                    myContentMsgList.addAll(userDetailsLayerUtilObj.userDetailsLayerMsgList);
                    return result;
                }

                result = userDetailsLayerUtilObj.clickContinueBtn(driver);
                if (!result) {
                    myContentMsgList.addAll(userDetailsLayerUtilObj.userDetailsLayerMsgList);
                    return result;
                }
            }

            feedbackFormUtilObj = new FeedbackFormUtil(driver);
            result = feedbackFormUtilObj.skipFeedbackForm(driver);
            if (!result) {
                myContentMsgList.addAll(feedbackFormUtilObj.feedbackFormMsgList);
                myContentMsgList.add("Unable to skip feedback form");
            }

            orderSuccessUtilObj = new OrderSuccessUtil(driver);
            result = orderSuccessUtilObj.verifyOrderSuccessPage(driver);
            if (!result) {
                myContentMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
                return result;
            }

            String productTitle = orderSuccessUtilObj.getStartLearningTitle(driver);
            if (productTitle == null) {
                myContentMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
                return false;
            }

            System.out.println("Purchased Package: -> " + productTitle);

            result = cfObj.pressAndroidKey(driver, key.BACK, 3);
            if (!result) {
                myContentMsgList.add("Unable to press android back key 3 times");
                return result;
            }

            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = clickOnPurchasedBtn(driver);
            if (!result) {
                myContentMsgList.add("Failed to click Purchase button.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//*[contains(@content-desc,'" + productTitle + "')]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Purchased course is not visible under Your purchased course section.");
                return result;
            }

            cfObj.commonClick(
                    cfObj.commonGetElement(driver, "//*[contains(@content-desc,'" + productTitle + "')]", "xpath"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//android.view.View[@content-desc='Select Priority Exams']", "xpath", 5);
            if (!result) {
                result = true;
                myContentMsgList.add("Select Priority Exams text not visible.");
            } else {

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                        "//android.widget.Button[@content-desc=\"Reset\"]", "xpath", 10);
                if (!result) {
                    myContentMsgList.add("Reset btn not visible.");
                    return result;
                }

                cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.View[@content-desc='btn_appbar_back']",
                        "xpath"));

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                        "//*[contains(@content-desc,'" + productTitle + "')]", "xpath", 10);
                if (!result) {
                    myContentMsgList.add("Purchased course is not visible under Your purchased course section.");
                    return result;
                }
            }
        } catch (Exception e) {
            myContentMsgList.add("verifyMyContentScreenForNewUser_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyNewPackageForOldUser(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        String packageName = "DNC New Parent 1";
        loginUtilObj = new LoginUtil(driver);
        homeUtilObj = new HomePageUtil(driver);
        try {

            result = loginUtilObj.verifyLoginUsingEmailId(driver, "testing123@gmail.com", "amitpundir", true);
            if (!result) {
                myContentMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            result = homeUtilObj.JustOpenAndClickHomeBtn(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = homeUtilObj.justOpenNavigationDrawer(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, homeUtilObj.homePageObj.getNightModeOnOffToggle(), 30);
            if (!result) {
                myContentMsgList.add("NightModeOnOffToggle button is not visible.");
                return result;
            }
            cfObj.commonClick(homeUtilObj.homePageObj.getNightModeOnOffToggle());

            Thread.sleep(2000);

            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = clickOnPurchasedBtn(driver);
            if (!result) {
                myContentMsgList.add("Failed to click Purchase button.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//*[contains(@content-desc,'" + packageName + "')]", "xpath", 20);
            if (!result) {
                myContentMsgList.add("Package is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Selected Course\"]", "xpath", 20);
            if (!result) {
                System.out.println("Selected course text is not visible or might be page is not loaded, in 1st try.");

                cfObj.commonClick(cfObj.commonGetElement(driver,
                        "//*[contains(@content-desc,'" + packageName + "')]", "xpath"));

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Selected Course\"]", "xpath", 20);
                if (!result) {
                    myContentMsgList.add("Selected course text is not visible or might be page is not loaded, in 2nd try.");
                    return result;
                }
            }

            result = homeUtilObj.verifyViewAllContentInSelectedCourseWithoutSelectedCourse(driver);
            if (!result){
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,'Batch Start Date :')]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Batch start date text is not visible in new package.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.backBtnInNewPackage(), 30);
            if (!result) {
                myContentMsgList.add("Back btn is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.searchIconBtnInNewPackage(), 30);
            if (!result) {
                myContentMsgList.add("Search btn is not visible ");
                return result;
            }

            result = verifyLiveClassInNewPackage(driver);
            if (!result) {
                myContentMsgList.add("Failed to verify LiveClass section");
                return result;
            }

            result = verifyTestseriesInNewPackage(driver);
            if (!result) {
                myContentMsgList.add("Failed to verify Testseries section");
                return result;
            }

            result = verifyVideoCourseInNewPackage(driver);
            if (!result) {
                myContentMsgList.add("Failed to verify VideoCourse section");
                return result;
            }

            result = verifyEbooksInNewPackage(driver);
            if (!result) {
                myContentMsgList.add("Failed to verify Ebooks section");
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("verifyNewPackageForOldUser_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean verifyLiveClassInNewPackage(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.LiveclassBtnInNewPackage(), 30);
            if (!result) {
                myContentMsgList.add("Live Class heading btn is not visible ");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.turnOnNotificationTextInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Turn on notification text is not visible ");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.turnOnNotificationBtnInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Turn on notification btn is not visible ");
                return result;
            }

//            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@content-desc=\"crossIconClickFloatingButton\"]", "xpath", 10);
//            if (!result) {
//                System.out.println("Floating button on bottom right is not visible in live class");
//            } else {
//                cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.Button[@content-desc=\"crossIconClickFloatingButton\"]", "xpath"));
//
//                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@content-desc=\"Community\"]", "xpath", 10);
//                if (!result) {
//                    myContentMsgList.add("Floating button on bottom right is not visible after click in live class");
//                    return result;
//                }
//                cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.Button[@content-desc=\"crossIconClickFloatingButton\"]", "xpath"));
//            }

            result = verifyWeeklySchedule(driver);
            if (!result) {
                myContentMsgList.add("Failed to verify verifyWeeklySchedule");
                return result;
            }

            result = verifyExamLevelContentInLiveclass(driver);
            if (!result) {
                myContentMsgList.add("Failed to verify ExamLevelContentInLiveclass");
                return result;
            }

            result = verifySubjectWiseContentInLiveclass(driver);
            if (!result) {
                myContentMsgList.add("Failed to verify SubjectWiseContentInLiveclass");
                return result;
            }

            cfObj.pressAndroidKey(driver, key.BACK, 3);

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.LiveclassBtnInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Live Class heading btn is not visible after coming from topic level.");
                return result;
            }
        } catch (
                Exception e) {
            myContentMsgList.add("verifyLiveClassInNewPackage_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean verifyWeeklySchedule(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.weeklyScheduleBtnInNewPackage(), 8);
            if (!result) {
                System.out.println("Might be a liveClass is scheduled.");

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"onScheduleClick\n" +
                        "Weekly Schedule\"]", "xpath", 10);
                if (!result) {
                    myContentMsgList.add("Weekly Schedule btn is not visible");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Today’s Live Class\"]", "xpath", 10);
                if (!result) {
                    myContentMsgList.add("Today’s Live Class text is not visible");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'liveNowCardClick')]", "xpath", 10);
                if (!result) {
                    myContentMsgList.add("LiveClass scheduled card is not visible");
                    return result;
                }

                cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.ImageView[@content-desc=\"onScheduleClick\n" +
                        "Weekly Schedule\"]", "xpath"));
            } else {
                cfObj.commonClick(myContentPageObj.weeklyScheduleBtnInNewPackage());
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@content-desc=\"Weekly Schedule\"]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Weekly schedule heading text is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"weekDatePickerNextClick\"]/preceding-sibling::android.widget.ImageView", "xpath", 10);
            if(!result){
                myContentMsgList.add("Filter icon is not visible");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.ImageView[@content-desc=\"weekDatePickerNextClick\"]/preceding-sibling::android.widget.ImageView", "xpath"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Filter\"]", "xpath", 10);
            if(!result){
                myContentMsgList.add("Filter heading is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@content-desc=\"clear_all_button\"]", "xpath", 10);
            if(!result){
                myContentMsgList.add("Clear All btn is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"filterItemListClick\n" +
                    "Subject\"]", "xpath", 10);
            if(!result){
                myContentMsgList.add("Subject filter is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"filterItemListClick\n" +
                    "Faculty\"]", "xpath", 10);
            if(!result){
                myContentMsgList.add("Faculty filter is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@content-desc=\"cancel_button\"]", "xpath", 10);
            if(!result){
                myContentMsgList.add("Cancel btn is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@content-desc=\"apply_button\"]", "xpath", 10);
            if(!result){
                myContentMsgList.add("Apply btn is not visible");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.Button[@content-desc=\"apply_button\"]", "xpath"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"weekDatePickerNextClick\"]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Date picker calender btn is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@content-desc=\"weekDatePickerPrevClick\"]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("PrevClick btn is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@content-desc=\"weekDatePickerNextClick\"]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("NextClick btn is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'plannerCardClick')]", "xpath", 10);
            if (!result) {
                System.out.println("Might be classes are not scheduled.");

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,\"Take a break\n" +
                        "No class is scheduled for this week!\")]", "xpath", 10);
                if (!result) {
                    myContentMsgList.add("No classes scheduled text is not visible");
                    return result;
                }
            }

            driver.navigate().back();

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.LiveclassBtnInNewPackage(), 30);
            if (!result) {
                myContentMsgList.add("Live Class heading btn is not visible after coming back from weekly schedule.");
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("verifyWeeklySchedule_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean verifyExamLevelContentInLiveclass(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.examLevelLiveClassesTextInNewPackage(), 20);
            if (!result) {
                myContentMsgList.add("Exam Level Live Classes text is not visible ");
                return result;
            }

            List<MobileElement> listOfExamLevel = myContentPageObj.examLevelCardBtnsInNewPackage();
            for (int i = 0; i < listOfExamLevel.size(); i++) {

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfExamLevel.get(i), 10);
                if (!result) {
                    myContentMsgList.add("Exam level card is not visible ");
                    return result;
                }

                cfObj.commonClick(listOfExamLevel.get(i));

                result = verifyLiveclassVideoListingInNewPackage(driver, true);
                if (!result) {
                    myContentMsgList.add("Failed to verify liveclass VideoListing from exam level");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfExamLevel.get(i), 10);
                if (!result) {
                    myContentMsgList.add("Exam level card is not visible after coming back");
                    return result;
                }
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.viewAllBtnInNewPackage(), 10);
            if (!result) {
                System.out.println("View all btn is not visible");
            } else {

                cfObj.commonClick(myContentPageObj.viewAllBtnInNewPackage());

                result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.examLevelLiveClassesInsideTextInNewPackage(), 10);
                if (!result) {
                    myContentMsgList.add("Exam Level Live Classes inside text is not visible ");
                    return result;
                }

                List<MobileElement> listOfExamLevelInside = myContentPageObj.examLevelCardBtnsInNewPackage();
                for (int i = 0; i < listOfExamLevelInside.size(); i++) {

                    result = cfObj.commonWaitForElementToBeVisible(driver, listOfExamLevelInside.get(i), 10);
                    if (!result) {
                        myContentMsgList.add("Exam level card inside is not visible ");
                        return result;
                    }
                }

                cfObj.commonClick(listOfExamLevelInside.get(0));

                result = verifyLiveclassVideoListingInNewPackage(driver, true);
                if (!result) {
                    myContentMsgList.add("Failed to verify liveclass VideoListing from exam level inside");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfExamLevelInside.get(0), 10);
                if (!result) {
                    myContentMsgList.add("Exam level card inside of first is not visible after coming back");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.backBtnInNewPackage(), 10);
                if (!result) {
                    myContentMsgList.add("Back btn is not visible ");
                    return result;
                }
                cfObj.commonClick(myContentPageObj.backBtnInNewPackage());
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.examLevelLiveClassesTextInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Exam Level Live Classes text is not visible after coming back");
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("verifyExamLevelContentInLiveclass_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean verifyExamLevelContentInTestseries(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.examLevelMockTestsTextInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Exam Level Testseries text is not visible ");
                return result;
            }

            List<MobileElement> listOfExamLevel = myContentPageObj.examLevelCardBtnsInNewPackage();
            for (int i = 0; i < listOfExamLevel.size(); i++) {

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfExamLevel.get(i), 10);
                if (!result) {
                    myContentMsgList.add("Exam level card is not visible ");
                    return result;
                }

                cfObj.commonClick(listOfExamLevel.get(i));

                result = verifyTestseriesListingInNewPackage(driver, true, false);
                if (!result) {
                    myContentMsgList.add("Failed to verify testseries listing from exam level");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfExamLevel.get(i), 10);
                if (!result) {
                    myContentMsgList.add("Exam level card is not visible after coming back");
                    return result;
                }
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.viewAllBtnInNewPackage(), 10);
            if (!result) {
                System.out.println("View all btn is not visible");
            } else {

                cfObj.commonClick(myContentPageObj.viewAllBtnInNewPackage());

                result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.examLevelMockTestsTextInNewPackage(), 10);
                if (!result) {
                    myContentMsgList.add("Exam Level tests text inside is not visible ");
                    return result;
                }

                List<MobileElement> listOfExamLevelInside = myContentPageObj.examLevelCardBtnsInNewPackage();
                for (int i = 0; i < listOfExamLevelInside.size(); i++) {

                    result = cfObj.commonWaitForElementToBeVisible(driver, listOfExamLevelInside.get(i), 10);
                    if (!result) {
                        myContentMsgList.add("Exam level card inside is not visible ");
                        return result;
                    }
                }

                cfObj.commonClick(listOfExamLevelInside.get(0));

                result = verifyTestseriesListingInNewPackage(driver, true, false);
                if (!result) {
                    myContentMsgList.add("Failed to verify testseriesListing from exam level inside");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfExamLevelInside.get(0), 10);
                if (!result) {
                    myContentMsgList.add("Exam level card inside for first is not visible after coming back");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.backBtnInNewPackage(), 10);
                if (!result) {
                    myContentMsgList.add("Back btn is not visible ");
                    return result;
                }
                cfObj.commonClick(myContentPageObj.backBtnInNewPackage());
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.examLevelMockTestsTextInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Exam Level testseries text is not visible after coming back");
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("verifyExamLevelContentInTestseries_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean verifyExamLevelContentInVideoCourse(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.examLevelVideosTextInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Exam Level videos text is not visible ");
                return result;
            }

            List<MobileElement> listOfExamLevel = myContentPageObj.examLevelCardBtnsInNewPackage();
            for (int i = 0; i < listOfExamLevel.size(); i++) {

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfExamLevel.get(i), 10);
                if (!result) {
                    myContentMsgList.add("Exam level card is not visible ");
                    return result;
                }

                cfObj.commonClick(listOfExamLevel.get(i));

                result = verifyVideosListingInNewPackage(driver, true);
                if (!result) {
                    myContentMsgList.add("Failed to verify VideoListing from exam level");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfExamLevel.get(i), 10);
                if (!result) {
                    myContentMsgList.add("Exam level card is not visible after coming back");
                    return result;
                }
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.viewAllBtnInNewPackage(), 5);
            if (!result) {
                System.out.println("View all btn is not visible");
            } else {

                cfObj.commonClick(myContentPageObj.viewAllBtnInNewPackage());

                result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.subjectWiseVideosTextInNewPackage(), 10);
                if (!result) {
                    myContentMsgList.add("Exam Level videos text inside is not visible ");
                    return result;
                }

                List<MobileElement> listOfExamLevelInside = myContentPageObj.examLevelCardBtnsInNewPackage();
                for (int i = 0; i < listOfExamLevelInside.size(); i++) {

                    result = cfObj.commonWaitForElementToBeVisible(driver, listOfExamLevelInside.get(i), 10);
                    if (!result) {
                        myContentMsgList.add("Exam level card inside is not visible ");
                        return result;
                    }
                }

                cfObj.commonClick(listOfExamLevelInside.get(0));

                result = verifyVideosListingInNewPackage(driver, true);
                if (!result) {
                    myContentMsgList.add("Failed to verify VideoListing from subject wise");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfExamLevelInside.get(0), 10);
                if (!result) {
                    myContentMsgList.add("Exam level card inside for first is not visible after coming back");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.backBtnInNewPackage(), 10);
                if (!result) {
                    myContentMsgList.add("Back btn is not visible ");
                    return result;
                }
                cfObj.commonClick(myContentPageObj.backBtnInNewPackage());
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.examLevelVideosTextInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Exam Level videos text is not visible after coming back");
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("verifyExamLevelContentInVideoCourse_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean verifyExamLevelContentInEbooks(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.examLevelEbooksTextInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Exam Level ebooks text is not visible ");
                return result;
            }

            List<MobileElement> listOfExamLevel = myContentPageObj.examLevelCardBtnsInNewPackage();
            for (int i = 0; i < listOfExamLevel.size(); i++) {

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfExamLevel.get(i), 10);
                if (!result) {
                    myContentMsgList.add("Exam level card is not visible ");
                    return result;
                }

                cfObj.commonClick(listOfExamLevel.get(i));

                result = verifyEbooksListingInNewPackage(driver, true);
                if (!result) {
                    myContentMsgList.add("Failed to verify ebooks Listing from exam level");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfExamLevel.get(i), 10);
                if (!result) {
                    myContentMsgList.add("Exam level card is not visible after coming back");
                    return result;
                }
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.viewAllBtnInNewPackage(), 5);
            if (!result) {
                System.out.println("View all btn is not visible");
            } else {

                cfObj.commonClick(myContentPageObj.viewAllBtnInNewPackage());

                result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.subjectWiseEbooksTextInNewPackage(), 10);
                if (!result) {
                    myContentMsgList.add("Exam Level ebooks text inside is not visible ");
                    return result;
                }

                List<MobileElement> listOfExamLevelInside = myContentPageObj.examLevelCardBtnsInNewPackage();
                for (int i = 0; i < listOfExamLevelInside.size(); i++) {

                    result = cfObj.commonWaitForElementToBeVisible(driver, listOfExamLevelInside.get(i), 10);
                    if (!result) {
                        myContentMsgList.add("Exam level card inside is not visible ");
                        return result;
                    }
                }

                cfObj.commonClick(listOfExamLevelInside.get(0));

                result = verifyEbooksListingInNewPackage(driver, true);
                if (!result) {
                    myContentMsgList.add("Failed to verify ebooksListing from exam level inside");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfExamLevelInside.get(0), 10);
                if (!result) {
                    myContentMsgList.add("Exam level card inside for first is not visible after coming back");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.backBtnInNewPackage(), 10);
                if (!result) {
                    myContentMsgList.add("Back btn is not visible ");
                    return result;
                }
                cfObj.commonClick(myContentPageObj.backBtnInNewPackage());
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.examLevelEbooksTextInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Exam Level videos text is not visible after coming back");
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("verifyExamLevelContentInEbooks_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean verifyLiveclassVideoListingInNewPackage(AppiumDriver<MobileElement> driver, boolean isBack) {
        boolean result = true;
        try {

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.backBtnInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Back btn is not visible ");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.searchIconBtnInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Search btn is not visible ");
                return result;
            }

            List<MobileElement> listOfLiveVideo = myContentPageObj.listingOfLiveVideosInNewPackage();
            for (int i = 0; i < listOfLiveVideo.size(); i++) {

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfLiveVideo.get(i), 10);
                if (!result) {
                    myContentMsgList.add("Liveclass Video card is not visible ");
                    return result;
                }
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@content-desc=\"crossIconClickFloatingButton\"]", "xpath", 20);
            if (!result) {
                myContentMsgList.add("Floating button on bottom right is not visible in live class video listing.");
                return result;
            }

            if (isBack) {
                cfObj.commonClick(myContentPageObj.backBtnInNewPackage());
            }
        } catch (Exception e) {
            myContentMsgList.add("verifyVideoListingInNewPackage_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean verifyVideosListingInNewPackage(AppiumDriver<MobileElement> driver, boolean isBack) {
        boolean result = true;
        try {

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.backBtnInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Back btn is not visible ");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.searchIconBtnInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Search btn is not visible ");
                return result;
            }

            List<MobileElement> listOfVideo = myContentPageObj.listingOfVideosInNewPackage();
            for (int i = 0; i < listOfVideo.size(); i++) {

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfVideo.get(i), 10);
                if (!result) {
                    myContentMsgList.add("Video card is not visible ");
                    return result;
                }
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@content-desc=\"crossIconClickFloatingButton\"]", "xpath", 20);
            if (!result) {
                myContentMsgList.add("Floating button on bottom right is not visible in videos listing.");
                return result;
            }

            if (isBack) {
                cfObj.commonClick(myContentPageObj.backBtnInNewPackage());
            }
        } catch (Exception e) {
            myContentMsgList.add("verifyVideosListingInNewPackage_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean verifyTestseriesListingInNewPackage(AppiumDriver<MobileElement> driver, boolean isBack, boolean firstTime) {
        boolean result = true;
        try {

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.backBtnInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Back btn is not visible ");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.searchIconBtnInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Search btn is not visible ");
                return result;
            }

            List<MobileElement> listOfTest = myContentPageObj.listingOfTestseries();
            result = cfObj.commonWaitForElementToBeVisible(driver, listOfTest.get(0), 10);
            if (!result) {
                myContentMsgList.add("Testseries card is not visible ");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@content-desc=\"crossIconClickFloatingButton\"]", "xpath", 20);
            if (!result) {
                myContentMsgList.add("Floating button on bottom right is not visible in testseries listing.");
                return result;
            }

            if (firstTime) {
                for (int i = 0; i < listOfTest.size(); i++) {

                    result = cfObj.commonWaitForElementToBeVisible(driver, listOfTest.get(i), 10);
                    if (!result) {
                        myContentMsgList.add("Testseries card is not visible ");
                        return result;
                    }
                }

                String downloadStatus = listOfTest.get(0).getAttribute("content-desc");
                if (downloadStatus.contains("DOWNLOAD")) {

                    cfObj.commonClick(listOfTest.get(0));

                    Thread.sleep(3000);

                    String attemptStatus = listOfTest.get(0).getAttribute("content-desc");
                    if (!attemptStatus.contains("ATTEMPT")) {
                        myContentMsgList.add("ATTEMPT btn is not visible for 1st test, after downloading");
                        return false;
                    } else {

                        cfObj.commonClick(listOfTest.get(0));

                        if (!cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.TextView[contains(@text,'Instructions')]", "xpath", 10)) {
                            myContentMsgList.add("Test instruction page is not open or visible");
                            return false;
                        } else {
                            driver.navigate().back();
                        }
                    }
                } else {
                    myContentMsgList.add("Download btn is not visible for 1st test");
                    return false;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfTest.get(0), 10);
                if (!result) {
                    myContentMsgList.add("Testseries card of 1st test is not visible ");
                    return result;
                }
            }

            if (isBack) {
                cfObj.commonClick(myContentPageObj.backBtnInNewPackage());
            }
        } catch (Exception e) {
            myContentMsgList.add("verifyTestseriesListingInNewPackage_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean verifyEbooksListingInNewPackage(AppiumDriver<MobileElement> driver, boolean isBack) {
        boolean result = true;
        try {

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.backBtnInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Back btn is not visible ");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.searchIconBtnInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Search btn is not visible ");
                return result;
            }

            List<MobileElement> listOfEbook = myContentPageObj.listingOfEbooksInNewPackage();
            List<MobileElement> listOfEbookStatus = myContentPageObj.listingOfEbooksDownloadStatusInNewPackage();
            for (int i = 0; i < listOfEbook.size(); i++) {

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfEbook.get(i), 10);
                if (!result) {
                    myContentMsgList.add("Ebook card is not visible ");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfEbookStatus.get(i), 10);
                if (!result) {
                    myContentMsgList.add("Ebook download status card is not visible ");
                    return result;
                }
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@content-desc=\"crossIconClickFloatingButton\"]", "xpath", 20);
            if (!result) {
                myContentMsgList.add("Floating button on bottom right is not visible in ebooks listing.");
                return result;
            }

            if (isBack) {
                cfObj.commonClick(myContentPageObj.backBtnInNewPackage());
            }
        } catch (Exception e) {
            myContentMsgList.add("verifyEbooksListingInNewPackage_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean verifySubjectWiseContentInLiveclass(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.subjectWiseLiveClassesTextInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Subject Wise Live Classes text is not visible ");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.backBtnInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Back btn is not visible ");
                return result;
            }

            List<MobileElement> listOfSubjectLevel = myContentPageObj.subjectWiseCardBtnsOutsideInNewPackage();
            for (int i = 0; i < listOfSubjectLevel.size(); i++) {

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfSubjectLevel.get(i), 10);
                if (!result) {
                    myContentMsgList.add("Subject Wise card is not visible ");
                    return result;
                }
            }
            cfObj.commonClick(listOfSubjectLevel.get(0));

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.subjectLevelLiveClassesTextInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Subject level live classes is not visible ");
                return result;
            }

            List<MobileElement> listOfSubjectLevelInside = myContentPageObj.subjectLevelCardBtnsInSubInNewPackage();
            for (int i = 0; i < listOfSubjectLevelInside.size(); i++) {

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfSubjectLevelInside.get(i), 10);
                if (!result) {
                    myContentMsgList.add("Subject level card is not visible ");
                    return result;
                }

                cfObj.commonClick(listOfSubjectLevelInside.get(i));

                result = verifyLiveclassVideoListingInNewPackage(driver, true);
                if (!result) {
                    myContentMsgList.add("Failed to verify liveclass VideoListing from subject level");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfSubjectLevelInside.get(i), 10);
                if (!result) {
                    myContentMsgList.add("Subject level card is not visible  after coming back");
                    return result;
                }
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.chapterWiseLiveClassesTextInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Chapter wise liveclass text is not visible ");
                return result;
            }

            List<MobileElement> listOfChapterLevel = myContentPageObj.chapterLevelCardBtnsInNewPackage();
            for (int i = 0; i < listOfChapterLevel.size(); i++) {

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfChapterLevel.get(i), 10);
                if (!result) {
                    myContentMsgList.add("Chapter level card is not visible ");
                    return result;
                }
            }
            cfObj.commonClick(listOfChapterLevel.get(0));

            result = verifyTopicsInNewPackageInLiveclass(driver);
            if (!result) {
                myContentMsgList.add("Failed to verify liveClass VideoListing");
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("verifySubjectWiseContentInLiveClass_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean verifySubjectWiseContentInTestseries(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.subjectWiseMockTestsTextInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Subject Wise Testseries text is not visible ");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.backBtnInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Back btn is not visible ");
                return result;
            }

            List<MobileElement> listOfSubjectLevel = myContentPageObj.subjectWiseCardBtnsOutsideInNewPackage();
            for (int i = 0; i < listOfSubjectLevel.size(); i++) {

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfSubjectLevel.get(i), 10);
                if (!result) {
                    myContentMsgList.add("Subject Wise card is not visible ");
                    return result;
                }
            }
            cfObj.commonClick(listOfSubjectLevel.get(0));

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.subjectLevelMockTestsTextInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("subject Level testseries text is not visible ");
                return result;
            }

            List<MobileElement> listOfSubjectLevelInside = myContentPageObj.subjectLevelCardBtnsInSubInNewPackage();
            for (int i = 0; i < listOfSubjectLevelInside.size(); i++) {

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfSubjectLevelInside.get(i), 10);
                if (!result) {
                    myContentMsgList.add("Subject level card is not visible ");
                    return result;
                }

                cfObj.commonClick(listOfSubjectLevelInside.get(i));

                result = verifyTestseriesListingInNewPackage(driver, true, false);
                if (!result) {
                    myContentMsgList.add("Failed to verify testseriesListing from subject level");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfSubjectLevelInside.get(i), 10);
                if (!result) {
                    myContentMsgList.add("Subject level card is not visible  after coming back");
                    return result;
                }
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.chapterHeadingMockTestsTextInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Chapter wise testseries text is not visible ");
                return result;
            }

            List<MobileElement> listOfChapterLevel = myContentPageObj.chapterLevelCardBtnsInNewPackage();
            for (int i = 0; i < listOfChapterLevel.size(); i++) {

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfChapterLevel.get(i), 10);
                if (!result) {
                    myContentMsgList.add("Chapter level card is not visible ");
                    return result;
                }
            }
            cfObj.commonClick(listOfChapterLevel.get(0));

            result = verifyTopicsInNewPackageInTestseries(driver);
            if (!result) {
                myContentMsgList.add("Failed to verify testseriesListing");
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("verifySubjectWiseContentInTestseries_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean verifySubjectWiseContentInVideoCourse(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.subjectWiseVideosTextInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Subject Wise videos text is not visible ");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.backBtnInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Back btn is not visible ");
                return result;
            }

            List<MobileElement> listOfSubjectLevel = myContentPageObj.subjectWiseCardBtnsOutsideInNewPackage();
            for (int i = 0; i < listOfSubjectLevel.size(); i++) {

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfSubjectLevel.get(i), 10);
                if (!result) {
                    myContentMsgList.add("Subject Wise card is not visible ");
                    return result;
                }
            }
            cfObj.commonClick(listOfSubjectLevel.get(0));

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.subjectLevelVideosTextInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("subjectLevel videos text is not visible ");
                return result;
            }

            List<MobileElement> listOfSubjectLevelInside = myContentPageObj.subjectLevelCardBtnsInSubInNewPackage();
            for (int i = 0; i < listOfSubjectLevelInside.size(); i++) {

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfSubjectLevelInside.get(i), 10);
                if (!result) {
                    myContentMsgList.add("Subject level card is not visible ");
                    return result;
                }

                cfObj.commonClick(listOfSubjectLevelInside.get(i));

                result = verifyVideosListingInNewPackage(driver, true);
                if (!result) {
                    myContentMsgList.add("Failed to verify VideoListing from subject level");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfSubjectLevelInside.get(i), 10);
                if (!result) {
                    myContentMsgList.add("Subject level card is not visible  after coming back");
                    return result;
                }
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.chapterHeadingVideosTextInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Chapter wise videos text is not visible ");
                return result;
            }

            List<MobileElement> listOfChapterLevel = myContentPageObj.chapterLevelCardBtnsInNewPackage();
            for (int i = 0; i < listOfChapterLevel.size(); i++) {

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfChapterLevel.get(i), 10);
                if (!result) {
                    myContentMsgList.add("Chapter level card is not visible ");
                    return result;
                }
            }
            cfObj.commonClick(listOfChapterLevel.get(0));

            result = verifyTopicsInNewPackageInVideoCourse(driver);
            if (!result) {
                myContentMsgList.add("Failed to verify VideoListing");
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("verifySubjectWiseContentInVideoCourse_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean verifySubjectWiseContentInEbooks(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.subjectWiseEbooksTextInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Subject Wise Ebooks text is not visible ");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.backBtnInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Back btn is not visible ");
                return result;
            }

            List<MobileElement> listOfSubjectLevel = myContentPageObj.subjectWiseCardBtnsOutsideInNewPackage();
            for (int i = 0; i < listOfSubjectLevel.size(); i++) {

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfSubjectLevel.get(i), 10);
                if (!result) {
                    myContentMsgList.add("Subject Wise card is not visible ");
                    return result;
                }
            }
            cfObj.commonClick(listOfSubjectLevel.get(0));

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.subjectLevelEbooksTextInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("subjectLevel Ebooks text is not visible ");
                return result;
            }

            List<MobileElement> listOfSubjectLevelInside = myContentPageObj.subjectLevelCardBtnsInSubInNewPackage();
            for (int i = 0; i < listOfSubjectLevelInside.size(); i++) {

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfSubjectLevelInside.get(i), 10);
                if (!result) {
                    myContentMsgList.add("Subject level card is not visible ");
                    return result;
                }

                cfObj.commonClick(listOfSubjectLevelInside.get(i));

                result = verifyEbooksListingInNewPackage(driver, true);
                if (!result) {
                    myContentMsgList.add("Failed to verify ebooks Listing from subject level");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfSubjectLevelInside.get(i), 10);
                if (!result) {
                    myContentMsgList.add("Subject level card is not visible  after coming back");
                    return result;
                }
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.chapterHeadingEbooksTextInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Chapter wise ebooks text is not visible ");
                return result;
            }

            List<MobileElement> listOfChapterLevel = myContentPageObj.chapterLevelCardBtnsInNewPackage();
            for (int i = 0; i < listOfChapterLevel.size(); i++) {

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfChapterLevel.get(i), 10);
                if (!result) {
                    myContentMsgList.add("Chapter level card is not visible ");
                    return result;
                }
            }
            cfObj.commonClick(listOfChapterLevel.get(0));

            result = verifyTopicsInNewPackageInEbooks(driver);
            if (!result) {
                myContentMsgList.add("Failed to verify ebooks Listing");
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("verifySubjectWiseContentInEbooks_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean verifyTopicsInNewPackageInLiveclass(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.chapterLevelLiveClassesTextInNewPackage(), 10);
            if (!result) {
                System.out.println("Chapter Level liveclass text is not visible");
            } else {
                List<MobileElement> listOfVideo = myContentPageObj.listingOfVideosInNewPackage();
                for (int i = 0; i < listOfVideo.size(); i++) {

                    result = cfObj.commonWaitForElementToBeVisible(driver, listOfVideo.get(i), 10);
                    if (!result) {
                        myContentMsgList.add("liveclass video card is not visible ");
                        return result;
                    }
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.viewAllBtnInNewPackage(), 10);
                if (!result) {
                    System.out.println("View all btn is not visible");
                } else {
                    cfObj.commonClick(myContentPageObj.viewAllBtnInNewPackage());

                    result = verifyLiveclassVideoListingInNewPackage(driver, true);
                    if (!result) {
                        myContentMsgList.add("Failed to verify liveclass VideoListing from chapter level");
                        return result;
                    }

                    result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.chapterLevelLiveClassesTextInNewPackage(), 10);
                    if (!result) {
                        myContentMsgList.add("Chapter Level liveclass text is not visible after coming back");
                        return result;
                    }
                }
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.backBtnInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Back btn is not visible ");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.searchIconBtnInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Search btn is not visible ");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.topicLevelLiveClassesTextInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Topic Level liveclass text is not visible ");
                return result;
            }

            List<MobileElement> listOfTopicLevel = myContentPageObj.topicLevelCardBtnsInNewPackage();
            for (int i = 0; i < listOfTopicLevel.size(); i++) {

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfTopicLevel.get(i), 10);
                if (!result) {
                    myContentMsgList.add("topic level card is not visible ");
                    return result;
                }
            }
            cfObj.commonClick(listOfTopicLevel.get(0));

            result = verifyLiveclassVideoListingInNewPackage(driver, false);
            if (!result) {
                myContentMsgList.add("Failed to verify liveclass videoListing from topic level");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'All Classes')]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("All classes text is not visible at topic level");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.liveclassRecordingTextInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Live class recording text is not visible at topic level");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.upcomingTextInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Upcoming text is not visible at topic level");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"videoButtonCLick\"]", "xpath", 10);
            if (!result) {
                System.out.println("Pdf button is not visible in topics live class");
                result = true;
            } else {
                cfObj.commonClick(cfObj.commonGetElements(driver, "//android.widget.ImageView[@content-desc=\"videoButtonCLick\"]", "xpath").get(0));

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Study Material\"]", "xpath", 10);
                if (!result) {
                    myContentMsgList.add("Study material bottomsheet is not visible ");
                    return result;
                }
                driver.navigate().back();
            }
/*
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"deleteVideoDialogDeleteClick\"]", "xpath", 10);
            if (!result) {
                System.out.println("Download video button is not visible");
                result = true;
            } else {
                cfObj.commonClick(cfObj.commonGetElements(driver, "//android.widget.ImageView[@content-desc=\"deleteVideoDialogDeleteClick\"]", "xpath").get(0));

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text=\"Select Quality\"]", "xpath", 10);
                if (!result) {
                    myContentMsgList.add("Select quality text is not visible as might be video coming soon");
                    return true;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text=\"DOWNLOAD\"]", "xpath", 20);
                if (!result) {
                    myContentMsgList.add("Download text is not visible in Select quality popup");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text=\"CANCEL\"]", "xpath", 20);
                if (!result) {
                    myContentMsgList.add("Cancel text is not visible in Select quality popup");
                    return result;
                }
                cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text=\"CANCEL\"]", "xpath"));

                result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.upcomingTextInNewPackage(), 10);
                if (!result) {
                    myContentMsgList.add("Upcoming text is not visible at topic level");
                    return result;
                }
            }
 */

//            result = verifyVideoPlayerScreen(driver);
//            if (!result) {
//                myContentMsgList.add("Failed to verify video player screen");
//                return result;
//            }
        } catch (Exception e) {
            myContentMsgList.add("verifyTopicsInNewPackageInLiveClass_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean verifyVideoPlayerScreen(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,'left')]", "xpath", 10);
            if (!result) {
                System.out.println("No videos available with recording.");
                return true;
            } else {
                cfObj.commonClick(cfObj.commonGetElements(driver, "//android.view.View[contains(@content-desc, 'left')]", "xpath").get(0));
            }

        } catch (Exception e) {
            myContentMsgList.add("verifyVideoPlayerScreen_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean verifyTopicsInNewPackageInVideoCourse(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.chapterLevelVideosTextInNewPackage(), 10);
            if (!result) {
                System.out.println("Chapter Level videos text is not visible");
            } else {
                List<MobileElement> listOfVideo = myContentPageObj.listingOfVideosInNewPackage();
                for (int i = 0; i < listOfVideo.size(); i++) {

                    result = cfObj.commonWaitForElementToBeVisible(driver, listOfVideo.get(i), 10);
                    if (!result) {
                        myContentMsgList.add("Video card is not visible ");
                        return result;
                    }
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.viewAllBtnInNewPackage(), 10);
                if (!result) {
                    System.out.println("View all btn is not visible");
                } else {
                    cfObj.commonClick(myContentPageObj.viewAllBtnInNewPackage());

                    result = verifyVideosListingInNewPackage(driver, true);
                    if (!result) {
                        myContentMsgList.add("Failed to verify VideoListing from chapter level");
                        return result;
                    }

                    result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.chapterLevelVideosTextInNewPackage(), 10);
                    if (!result) {
                        myContentMsgList.add("Chapter Level Video course text is not visible after coming back");
                        return result;
                    }
                }
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.backBtnInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Back btn is not visible ");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.searchIconBtnInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Search btn is not visible ");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.topicLevelVideosTextInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Topic Level Video course text is not visible ");
                return result;
            }

            List<MobileElement> listOfTopicLevel = myContentPageObj.topicLevelCardBtnsInNewPackage();
            for (int i = 0; i < listOfTopicLevel.size(); i++) {

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfTopicLevel.get(i), 10);
                if (!result) {
                    myContentMsgList.add("topic level card is not visible ");
                    return result;
                }
            }
            cfObj.commonClick(listOfTopicLevel.get(0));

            result = verifyVideosListingInNewPackage(driver, false);
            if (!result) {
                myContentMsgList.add("Failed to verify VideoListing from topic level");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'All Videos')]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("All videos text is not visible at topic level");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'Watching')]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Watching text is not visible at topic level");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'Completed')]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Completed text is not visible at topic level");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'Unwatched')]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Unwatched text is not visible at topic level");
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("verifyTopicsInNewPackageInVideoCourse_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean verifyTopicsInNewPackageInEbooks(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.chapterLevelEbooksTextInNewPackage(), 10);
            if (!result) {
                System.out.println("Chapter Level Ebooks text is not visible");
            } else {

                List<MobileElement> listOfEbook = myContentPageObj.listingOfEbooksInNewPackage();
                List<MobileElement> listOfEbookStatus = myContentPageObj.listingOfEbooksDownloadStatusInNewPackage();
                for (int i = 0; i < listOfEbook.size(); i++) {

                    result = cfObj.commonWaitForElementToBeVisible(driver, listOfEbook.get(i), 10);
                    if (!result) {
                        myContentMsgList.add("Ebook card is not visible ");
                        return result;
                    }

                    result = cfObj.commonWaitForElementToBeVisible(driver, listOfEbookStatus.get(i), 10);
                    if (!result) {
                        myContentMsgList.add("Ebook download status card is not visible ");
                        return result;
                    }
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.viewAllBtnInNewPackage(), 10);
                if (!result) {
                    System.out.println("View all btn is not visible");
                } else {
                    cfObj.commonClick(myContentPageObj.viewAllBtnInNewPackage());

                    result = verifyLiveclassVideoListingInNewPackage(driver, true);
                    if (!result) {
                        myContentMsgList.add("Failed to verify ebooks Listing from chapter level");
                        return result;
                    }
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.chapterLevelEbooksTextInNewPackage(), 10);
                if (!result) {
                    myContentMsgList.add("Chapter Level ebooks text is not visible ");
                    return result;
                }
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.backBtnInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Back btn is not visible ");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.searchIconBtnInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Search btn is not visible ");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.topicLevelEbooksTextInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Topic Level ebooks text is not visible ");
                return result;
            }

            List<MobileElement> listOfTopicLevel = myContentPageObj.topicLevelCardBtnsInNewPackage();
            for (int i = 0; i < listOfTopicLevel.size(); i++) {

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfTopicLevel.get(i), 10);
                if (!result) {
                    myContentMsgList.add("topic level card is not visible ");
                    return result;
                }
            }
            cfObj.commonClick(listOfTopicLevel.get(0));

            result = verifyEbooksListingInNewPackage(driver, false);
            if (!result) {
                myContentMsgList.add("Failed to verify ebooks Listing from topic level");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,'All Ebooks')]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("All ebooks text is not visible at topic level");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,'Read')]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Read text is not visible at topic level");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[contains(@content-desc,'Unread')]", "xpath", 10);
            if (!result) {
                myContentMsgList.add("Unread text is not visible at topic level");
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("verifyTopicsInNewPackageInEbooks_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean verifyTopicsInNewPackageInTestseries(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.chapterLevelMockTestsTextInNewPackage(), 10);
            if (!result) {
                System.out.println("Chapter Level testseries text is not visible");
            } else {
                List<MobileElement> listOfTestseriesOutside = myContentPageObj.listingOfTestseries();
                for (int i = 0; i < listOfTestseriesOutside.size(); i++) {

                    result = cfObj.commonWaitForElementToBeVisible(driver, listOfTestseriesOutside.get(i), 10);
                    if (!result) {
                        myContentMsgList.add("Testseries card is not visible  on chapter level");
                        return result;
                    }
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.viewAllBtnInNewPackage(), 10);
                if (!result) {
                    System.out.println("View all btn is not visible");
                } else {
                    cfObj.commonClick(myContentPageObj.viewAllBtnInNewPackage());

                    result = verifyTestseriesListingInNewPackage(driver, true, false);
                    if (!result) {
                        myContentMsgList.add("Failed to verify testseriesListing from chapter level");
                        return result;
                    }

                    result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.chapterLevelMockTestsTextInNewPackage(), 10);
                    if (!result) {
                        myContentMsgList.add("Chapter Level testseries text is not visible ");
                        return result;
                    }
                }
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.backBtnInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Back btn is not visible ");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.searchIconBtnInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Search btn is not visible ");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.topicLevelMockTestsTextInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Chapter Level testseries text is not visible ");
                return result;
            }

            List<MobileElement> listOfTopicLevel = myContentPageObj.topicLevelCardBtnsInNewPackage();
            for (int i = 0; i < listOfTopicLevel.size(); i++) {

                result = cfObj.commonWaitForElementToBeVisible(driver, listOfTopicLevel.get(i), 10);
                if (!result) {
                    myContentMsgList.add("topic level card is not visible ");
                    return result;
                }
            }
            cfObj.commonClick(listOfTopicLevel.get(0));

            result = verifyTestseriesListingInNewPackage(driver, false, true);
            if (!result) {
                myContentMsgList.add("Failed to verify testseriesListing from topic level");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.allTestsTabTextInTopicLevel(), 10);
            if (!result) {
                myContentMsgList.add("All tests tab text is not visible at topic level");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.completedTabTextInTopicLevel(), 10);
            if (!result) {
                myContentMsgList.add("completed tab text is not visible at topic level");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.incompleteTabTextInTopicLevel(), 10);
            if (!result) {
                myContentMsgList.add("Incomplete tab text is not visible at topic level");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.unattemptedTabTextInTopicLevel(), 10);
            if (!result) {
                myContentMsgList.add("unattempted tab text is not visible at topic level");
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("verifyTopicsInNewPackageInTestseries_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean verifyTestseriesInNewPackage(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.testseriesBtnInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Testseries heading btn is not visible ");
                return result;
            }

            cfObj.commonClick(myContentPageObj.testseriesBtnInNewPackage());

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@content-desc=\"crossIconClickFloatingButton\"]", "xpath", 20);
            if (!result) {
                myContentMsgList.add("Floating button on bottom right is not visible in testseries.");
                return result;
            }

            result = verifyExamLevelContentInTestseries(driver);
            if (!result) {
                myContentMsgList.add("Failed to verify ExamLevelContentInTestseries");
                return result;
            }

            result = verifySubjectWiseContentInTestseries(driver);
            if (!result) {
                myContentMsgList.add("Failed to verify SubjectWiseContentInTestseries");
                return result;
            }

            cfObj.pressAndroidKey(driver, key.BACK, 3);

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.testseriesBtnInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Testseries heading btn is not visible after coming from topic level.");
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("verifyTestseriesInNewPackage_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean verifyVideoCourseInNewPackage(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.videoCourseBtnInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Video course heading btn is not visible ");
                return result;
            }

            cfObj.commonClick(myContentPageObj.videoCourseBtnInNewPackage());

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@content-desc=\"crossIconClickFloatingButton\"]", "xpath", 20);
            if (!result) {
                myContentMsgList.add("Floating button on bottom right is not visible in video.");
                return result;
            }

            result = verifyExamLevelContentInVideoCourse(driver);
            if (!result) {
                myContentMsgList.add("Failed to verify ExamLevelContentInVideoCourse");
                return result;
            }

            result = verifySubjectWiseContentInVideoCourse(driver);
            if (!result) {
                myContentMsgList.add("Failed to verify SubjectWiseContentInVideoCourse");
                return result;
            }

            cfObj.pressAndroidKey(driver, key.BACK, 3);

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.videoCourseBtnInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Video course heading btn is not visible after coming from topic level.");
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("verifyVideoCourseInNewPackage_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean verifyEbooksInNewPackage(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.ebooksBtnInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Ebooks heading btn is not visible ");
                return result;
            }

            cfObj.commonClick(myContentPageObj.ebooksBtnInNewPackage());

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@content-desc=\"crossIconClickFloatingButton\"]", "xpath", 20);
            if (!result) {
                myContentMsgList.add("Floating button on bottom right is not visible in ebooks.");
                return result;
            }

            result = verifyExamLevelContentInEbooks(driver);
            if (!result) {
                myContentMsgList.add("Failed to verify ExamLevelContentInEbooks");
                return result;
            }

            result = verifySubjectWiseContentInEbooks(driver);
            if (!result) {
                myContentMsgList.add("Failed to verify SubjectWiseContentInEbooks");
                return result;
            }

            cfObj.pressAndroidKey(driver, key.BACK, 3);

            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.ebooksBtnInNewPackage(), 10);
            if (!result) {
                myContentMsgList.add("Ebooks heading btn is not visible after coming from topic level.");
                return result;
            }
        } catch (Exception e) {
            myContentMsgList.add("verifyEbooksInNewPackage_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean validatePurchasedPackageDescription(AppiumDriver<MobileElement> driver, String packageName) {
        boolean result = true;
        try {
            Thread.sleep(3000);
            result = cfObj.commonWaitForElementToBeVisible(driver, myContentPageObj.getListPackageDescription().get(0),
                    10);
            if (!result) {
                myContentMsgList.add("purchased package description is not visible.");
                return result;
            }

            result = cfObj.commonVerifyValueTextBox(myContentPageObj.getListPackageDescription().get(0), packageName);
            if (!result) {
                myContentMsgList.add("purchased package description is not matching.");
                return result;
            }
        } catch (Exception e) {
            result = false;
            myContentMsgList.add("validatePurchasedPackageDescription_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean verifyMockOptionalTestInStoreFront(AppiumDriver<MobileElement> driver, String packageName,
                                                      boolean isCalculator) {
        boolean result = true;
        String mockName;
        int mockTestId;
        try {
            mockTestApiUtilObj = new MockTestApiUtil();
            result = mockTestApiUtilObj.createOptionalMockTestStoreFront("MCQ Sankalp.docx");
            if (!result) {
                myContentMsgList.add("Not able to create MockTestStoreFront - verifyMockOptionalTestInStoreFront");
                return result;
            }

            mockName = MockTestApiUtil.strFixedMockName;
            if (mockName == null) {
                myContentMsgList.add("MockName is null.");
                return false;
            }
            mockTestId = Integer.parseInt(MockTestApiUtil.strFixedMockTestId);

            packageApiUtilObj = new PackageApiUtil();
            result = packageApiUtilObj.assignTestSeriesToPackage(1, mockTestId,
                    Integer.parseInt(configObj.getPackageId()));
            if (!result) {
                myContentMsgList.addAll(packageApiUtilObj.packagetApiMsgList);
                return result;
            }
            System.out.println("----->" + mockName);

            loginUtilObj = new LoginUtil(driver);
            result = loginUtilObj.verifyLoginUsingEmailId(driver, "testseries123@yopmail.com", "testseries123", false);
            if (!result) {
                myContentMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            homeUtilObj = new HomePageUtil(driver);
            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = clickOnPurchasedBtn(driver);
            if (!result) {
                myContentMsgList.add("Not able to click Purchased button.");
                return result;
            }

            result = clickOnSpecificPurchasedPackage(driver, packageName);
            if (!result) {
                myContentMsgList.add("Not able to click Package in PurchasedSection.");
                return result;
            }

            result = homeUtilObj.verifyViewAllContentInSelectedCourse(driver);
            if (!result){
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            childPackageUtilObj = new ChildPackageUtil(driver);
            result = cfObj.waitForPageLoading(driver, 5, 3000,
                    cfObj.commonGetElement(driver, "//*[contains(@text,'" + mockName
                                    + "')]/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='GET QUIZ']",
                            "xpath"));
            if (!result) {
                driver.navigate().back();

                result = homeUtilObj.verifyViewAllContentInSelectedCourse(driver);
                if (!result){
                    myContentMsgList.addAll(homeUtilObj.homeMsgList);
                    return result;
                }

                result = cfObj.waitForPageLoading(driver, 5, 3000,
                        cfObj.commonGetElement(driver, "//*[contains(@text,'" + mockName
                                        + "')]/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='GET QUIZ']",
                                "xpath"));
                if (!result) {
                    myContentMsgList.add("Unable to Load test series Page even after retry.");
                    return result;
                }
            }

            result = childPackageUtilObj.verifyTestSeriesPage(driver);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.validateSpecificMockTitle(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.downloadSpecificQuiz(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.clickOnSpecificAttemptLink(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            commonTestUtilObj = new CommonTestUtil(driver);

            result = commonTestUtilObj.verifyInstructionPage(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.completeAndSubmitPaidOptionalTest(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            Thread.sleep(8000);

            result = commonTestUtilObj.validateMockTestResultScreen(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver,
                    commonTestUtilObj.commonTestPageObj.getBtnNavigateUp(), 10);
            if (!result) {
                myContentMsgList.add("getBtnNavigateUp not visible");
                return result;
            }

            cfObj.commonClick(commonTestUtilObj.commonTestPageObj.getBtnNavigateUp());

            result = childPackageUtilObj.clickOnSpecificResultStatus(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            Thread.sleep(2000);
            result = commonTestUtilObj.validateTestSolutionBtn(driver, isCalculator, false, false);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifyMockOptionalTestInStoreFront_Exception" + e.getMessage());
        } finally {
            System.out.println("---------------------");
            mockTestApiUtil.edit_deleteTestSeriesForPackageId48219();
        }
        return result;
    }

    public boolean verifyMockTestStoreFrontUsingQuestionBank(AppiumDriver<MobileElement> driver, String packageName,
                                                             boolean isCalculator) {
        boolean result = true;
        String mockName;
        int mockTestId;
        try {
            mockTestApiUtilObj = new MockTestApiUtil();
            result = mockTestApiUtilObj.createMockTestStoreFrontUsingQuestionBank(isCalculator);
            if (!result) {
                myContentMsgList.add("Not able to create MockTestStoreFront - verifyMockTestStoreFrontUsingQuestionBankN");
                return result;
            }
            mockName = MockTestApiUtil.strFixedMockName;
            if (mockName == null) {
                myContentMsgList.add("MockName is null.");
                return false;
            }
            mockTestId = Integer.parseInt(MockTestApiUtil.strFixedMockTestId);

            packageApiUtilObj = new PackageApiUtil();
            result = packageApiUtilObj.assignTestSeriesToPackage(1, mockTestId,
                    Integer.parseInt(configObj.getPackageId()));
            if (!result) {
                myContentMsgList.addAll(packageApiUtilObj.packagetApiMsgList);
                return result;
            }
            System.out.println("----->" + mockName);

            loginUtilObj = new LoginUtil(driver);
            result = loginUtilObj.verifyLoginUsingEmailId(driver, "abcd123@gmail.com", "amitpundir", true);
            if (!result) {
                myContentMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            homeUtilObj = new HomePageUtil(driver);
            result = homeUtilObj.clickMyContentButton(driver);
            if (!result) {
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            result = clickOnPurchasedBtn(driver);
            if (!result) {
                myContentMsgList.add("Not able to click Purchased button.");
                return result;
            }

            result = clickOnSpecificPurchasedPackage(driver, packageName);
            if (!result) {
                myContentMsgList.add("Not able to click Package in PurchasedSection.");
                return result;
            }

            result = homeUtilObj.verifyViewAllContentInSelectedCourse(driver);
            if (!result){
                myContentMsgList.addAll(homeUtilObj.homeMsgList);
                return result;
            }

            childPackageUtilObj = new ChildPackageUtil(driver);
            result = cfObj.waitForPageLoading(driver, 5, 3000,
                    cfObj.commonGetElement(driver, "//*[contains(@text,'" + mockName
                                    + "')]/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='GET QUIZ']",
                            "xpath"));
            if (!result) {
                driver.navigate().back();

                result = homeUtilObj.verifyViewAllContentInSelectedCourse(driver);
                if (!result){
                    myContentMsgList.addAll(homeUtilObj.homeMsgList);
                    return result;
                }

                result = cfObj.waitForPageLoading(driver, 5, 3000,
                        cfObj.commonGetElement(driver, "//*[contains(@text,'" + mockName
                                        + "')]/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='GET QUIZ']",
                                "xpath"));
                if (!result) {
                    myContentMsgList.add("Unable to Load test series Page even after retry.");
                    return result;
                }
            }

            result = childPackageUtilObj.verifyTestSeriesPage(driver);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.validateSpecificMockTitle(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.downloadSpecificQuiz(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            result = childPackageUtilObj.clickOnSpecificAttemptLink(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

            commonTestUtilObj = new CommonTestUtil(driver);

            result = commonTestUtilObj.verifyInstructionPage(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = commonTestUtilObj.completeAndSubmitTest(driver, isCalculator, false, true);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            System.out.println("Test Submitted");
            Thread.sleep(3000);

            result = commonTestUtilObj.validateMockTestResultScreen(driver);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver,
                    commonTestUtilObj.commonTestPageObj.getBtnNavigateUp(), 10);
            if (!result) {
                myContentMsgList.add("getBtnNavigateUp not visible");
                return result;
            }
            cfObj.commonClick(commonTestUtilObj.commonTestPageObj.getBtnNavigateUp());

            result = childPackageUtilObj.clickOnSpecificResultStatus(driver, mockName);
            if (!result) {
                myContentMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }
            Thread.sleep(2000);

            result = commonTestUtilObj.validateTestSolutionBtn(driver, isCalculator, false, true);
            if (!result) {
                myContentMsgList.addAll(commonTestUtilObj.commonTestMsgList);
                return result;
            }
        } catch (Exception e) {
            result = false;
            myContentMsgList.add("verifyMockTestStoreFrontUsingQuestionBank_Exception" + e.getMessage());
        } finally {
            System.out.println("---------------------");
            mockTestApiUtil.edit_deleteTestSeriesForPackageId48219();
        }
        return result;
    }
}
