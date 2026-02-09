package applicationUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import apiUtill.*;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import pojo.login.Login;
import applicationUtil.StorePageUtil.ProductType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.ui.Select;
import pageObject.LiveClassesPage_OR;
import pojo.LiveClassPackageResponse.liveClassPackageResponse;
import pojo.adminLogin.AdminLogin;
import pojo.createScheduleResponse.CreateScheduleResponse;
import pojo.mapExternalIdResponse.MapExternalIdResponse;
import util.Common_Function;
import util.Common_Function.NetworkState;
import util.Common_Function.direction;
import util.Common_Function.key;
import util.ConfigFileReader;

public class LiveClassesPageUtil {

    PurchasePackageUtil purchasePackageUtilObj;
    UserDetailsLayerUtil userDetailsLayerUtilObj;
    LoginUtil loginUtilObj;
    HomePageUtil homePageUtilObj;
    StorePageUtil storePageUtilObj;
    OrderSuccessUtil orderSuccessUtilObj;
    MyContentUtil myContentUtilObj;
    LiveClassesPage_OR liveClassPageObj;
    WebViewLiveClassUtil webViewLiveClassUtilObj;
    ChildPackageUtil childPackageUtilObj;
    VideoCoursesPageUtil videoCoursesPageUtilObj;
    PaymentUtil paymentUtilObj;
    BatchUtil batchUtilObj;
    PostingPageUtil postingPageUtilObj;
    public Common_Function cfObj = new Common_Function();
    ConfigFileReader configFileReaderObj = new ConfigFileReader();
    public ArrayList<String> liveClassMsgList = new ArrayList<String>();
    public String externalScheduleId;

    private int handOutsList = 0;

    WebDriver webDriver = null;
    public String strParentWindow;
    public String strChildWindow;

    private final String sendMessage = "Message from student side.";

    static String strTeacherUrl, strStudentUrl;

    public LiveClassesPageUtil(AppiumDriver<MobileElement> driver) {
        liveClassPageObj = new LiveClassesPage_OR();
        PageFactory.initElements(new AppiumFieldDecorator(driver), liveClassPageObj);
    }

    public boolean verifyLiveClassRecordedSessionUI(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            loginUtilObj = new LoginUtil(driver);

            result = loginUtilObj.selectYourCategoryExamLanguage(driver);
            if (!result) {
                liveClassMsgList.add("Unable to click select category, exam and language");
                return result;
            }

            result = loginUtilObj.verifyLoginUsingEmailId(driver, "addaautomation2115142915@gmail.com", "1234567890", false);
            if (!result) {
                liveClassMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            homePageUtilObj = new HomePageUtil(driver);
            result = homePageUtilObj.clickMyContentButton(driver);
            if (!result) {
                liveClassMsgList.addAll(homePageUtilObj.homeMsgList);
                return result;
            }

            myContentUtilObj = new MyContentUtil(driver);
            result = myContentUtilObj.clickOnPurchasedBtn(driver);
            if (!result) {
                liveClassMsgList.addAll(myContentUtilObj.myContentMsgList);
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//android.widget.ImageView[contains(@content-desc,\"purchase_course_card\n"
                            + "New Live Class Package\")]",
                    "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Package is not visible in my content");
                return result;
            }

            cfObj.commonClick(cfObj.commonGetElement(driver,
                    "//android.widget.ImageView[contains(@content-desc,\"purchase_course_card\n"
                            + "New Live Class Package\")]",
                    "xpath"));

            batchUtilObj = new BatchUtil(driver);
            result = batchUtilObj.clickOnDoneBtn(driver);
            if (!result) {
                liveClassMsgList.addAll(batchUtilObj.batchMsgList);
                return result;
            }

            result = batchUtilObj.clickOnSubjectTitle(driver);
            if (!result) {
                liveClassMsgList.addAll(batchUtilObj.batchMsgList);
                return result;
            }

            result = handledNotificationPopUp(driver);
            if (!result) {
                liveClassMsgList.add("Not able to handled NotificationPopUp.");
                return result;
            }

            result = clickOnGotItBtn(driver);
            if (!result) {
                liveClassMsgList.add("Not able to click GotIt button.");
                return result;
            }

            result = validatePurchasedLiveClassUI(driver);
            if (!result) {
                liveClassMsgList.add("Not able to validate PurchasedLiveClassUI.");
                return result;
            }

            result = validateLiveClassShareBtn(driver);
            if (!result) {
                liveClassMsgList.add("Not able to validate LiveClassShareBtn.");
                return result;
            }

            result = validateLiveClassDescriptionIcon(driver);
            if (!result) {
                liveClassMsgList.add("Not able to validate LiveClassDescriptionIcon.");
                return result;
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("LiveClassRecordedSessionUI_Exception" + e.getMessage());

        }
        return result;
    }

    public boolean handledNotificationPopUp(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            if (cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getTurnOnNotificationTitle(), 10)) {

                cfObj.commonClick(liveClassPageObj.getNotifyNoBtn());
            } else {
                System.out.println("Turn on Notification title is not visible.");
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("HandledNotificationPopUp_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean validatePurchasedLiveClassUI(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getLiveClassShareBtn(), 10);
            if (!result) {
                liveClassMsgList.add("LiveClassShareBtn is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getLiveClassDescriptionBtn(), 10);
            if (!result) {
                liveClassMsgList.add("LiveClassdescriptionBtn is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getPlayerViewBtn(), 10);
            if (!result) {
                liveClassMsgList.add("PlayerViewBtn is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getVideoCourseImgList().get(0), 10);
            if (!result) {
                liveClassMsgList.add("VideoCourseImgList is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getVideoCourseTitleList().get(0),
                    10);
            if (!result) {
                liveClassMsgList.add("VideoCourseTitleList is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getVideoCourseSubTextList().get(0),
                    10);
            if (!result) {
                liveClassMsgList.add("VideoCourseSubTextList is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getWatchTimerList().get(0), 10);
            if (!result) {
                liveClassMsgList.add("WatchTimerList is not visible.");
                return result;
            }

        } catch (Exception e) {
            liveClassMsgList.add("PurchasedVideoCourseUI_Exception" + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean validateLiveClassShareBtn(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getLiveClassShareBtn(), 20);
            if (!result) {
                liveClassMsgList.add("LiveClassShareBtn is not visible.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getLiveClassShareBtn());
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getShareScreen(), 30);
            if (!result) {
                liveClassMsgList.add("Share screen is not visible.");
                return result;
            }
            Thread.sleep(2000);
            cfObj.scrollUtill(driver, 1, direction.UP);

            // Handle Scrolling
            if (cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getShareScreen(), 10)) {
                cfObj.scrollUtill(driver, 1, direction.UP);
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getLiveClassShareBtn(), 20);
            if (!result) {
                liveClassMsgList.add("LiveClassShare button is not visible.");
                return result;
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("validateLiveClassShareBtn_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean validateLiveClassDescriptionIcon(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getLiveClassDescriptionBtn(), 30);
            if (!result) {
                liveClassMsgList.add("LiveClassDescriptionBtn is not visible.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getLiveClassDescriptionBtn());

            result = cfObj.commonWaitForElementToBeVisible(driver,
                    liveClassPageObj.getLiveClassDescriptionBottomSheetTitle(), 30);
            if (!result) {
                liveClassMsgList.add("LiveClassDescriptionBottomSheetTitle is not visible.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getLiveClassDescriptionBottomSheetCloseBtn());

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getLiveClassDescriptionBtn(), 30);
            if (!result) {
                liveClassMsgList.add("Failed to close LiveClassDescriptionBottomSheet.");
                return result;
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("LiveClassDescriptionIcon_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean clickOnGotItBtn(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            if (cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getGotItBtn(), 10)) {

                cfObj.commonClick(liveClassPageObj.getGotItBtn());
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("GotItBtn_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean clickOnPlayerViewStateBtn(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getPlayerViewBtn(), 10);
            if (!result) {
                liveClassMsgList.add("PlayerViewBtn is not visible.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getPlayerViewBtn());

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("PlayerViewStateBtn_Exception" + e.getMessage());

        }
        return result;
    }

    public boolean verifyRecordedVideoClassDownload(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        String productTitle;
        String packageTitle1 = "3:Live class package";
        String liveCoursePackageName;
        try {

            loginUtilObj = new LoginUtil(driver);

            result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), true);
            if (!result) {
                liveClassMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
                liveCoursePackageName = "New Live Class Packag";
            } else {
                liveCoursePackageName = "Live class package";
            }

            homePageUtilObj = new HomePageUtil(driver);

            result = homePageUtilObj.clickStoreBtn(driver);
            if (!result) {
                liveClassMsgList.addAll(homePageUtilObj.homeMsgList);
                return result;
            }

            storePageUtilObj = new StorePageUtil(driver);
            orderSuccessUtilObj = new OrderSuccessUtil(driver);

            if (ConfigFileReader.strApplication.equalsIgnoreCase("Sankalp")) {
                result = storePageUtilObj.clickLiveClassIcon(driver);
                if (!result) {
                    liveClassMsgList.addAll(storePageUtilObj.storePageMsgList);
                    return result;
                }
                result = storePageUtilObj.clickOnSpecificPackage(driver, packageTitle1);
                if (!result) {
                    liveClassMsgList.addAll(storePageUtilObj.storePageMsgList);
                    return result;
                }

                purchasePackageUtilObj = new PurchasePackageUtil(driver);
                result = purchasePackageUtilObj.verifyUnpurchasedPackagePage(driver);
                if (!result) {
                    liveClassMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
                    return result;
                }

                result = purchasePackageUtilObj.validateDiscountRemoveBtn(driver);
                if (!result) {
                    liveClassMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
                    return result;
                }

                result = purchasePackageUtilObj.clickBuyNowBtn(driver);
                if (!result) {
                    liveClassMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
                    return result;
                }
                userDetailsLayerUtilObj = new UserDetailsLayerUtil(driver);
                result = userDetailsLayerUtilObj.fillUserDetailsForm(driver);
                if (!result) {
                    liveClassMsgList.addAll(userDetailsLayerUtilObj.userDetailsLayerMsgList);
                    return result;
                }
                result = userDetailsLayerUtilObj.clickOnContinueBtn(driver);
                if (!result) {
                    liveClassMsgList.addAll(userDetailsLayerUtilObj.userDetailsLayerMsgList);
                    return result;
                }

                paymentUtilObj = new PaymentUtil(driver);
                result = paymentUtilObj.clickOnUPITypePayment(driver);
                if (!result) {
                    liveClassMsgList.addAll(paymentUtilObj.paymentMsgList);
                    return result;
                }

                result = paymentUtilObj.clickOnPayNowBtn(driver);
                if (!result) {
                    liveClassMsgList.addAll(paymentUtilObj.paymentMsgList);
                    return result;
                }
                orderSuccessUtilObj = new OrderSuccessUtil(driver);
                result = orderSuccessUtilObj.handleRateUsPopUpWindow(driver);
                if (!result) {
                    liveClassMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
                    return result;
                }
                result = orderSuccessUtilObj.verifyOrderSuccessPage(driver);
                if (!result) {
                    liveClassMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
                    return result;
                }

            } else {
                result = storePageUtilObj.purchasePackage(driver, liveCoursePackageName, ProductType.LIVE_CLASSES);
                if (!result) {
                    liveClassMsgList.addAll(storePageUtilObj.storePageMsgList);
                    return result;
                }
            }
            productTitle = orderSuccessUtilObj.getStartLearningTitle(driver);
            if (productTitle == null) {
                liveClassMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
                return false;
            }

            result = cfObj.pressAndroidKey(driver, key.BACK, 3);
            if (!result) {
                liveClassMsgList.add("Unable to press android back key 3 times");
                return result;
            }

            result = homePageUtilObj.clickMyContentButton(driver);
            if (!result) {
                liveClassMsgList.addAll(homePageUtilObj.homeMsgList);
                return result;
            }

            myContentUtilObj = new MyContentUtil(driver);
            result = myContentUtilObj.clickOnPurchasedBtn(driver);
            if (!result) {
                liveClassMsgList.addAll(myContentUtilObj.myContentMsgList);
                return result;
            }

            result = myContentUtilObj.clickOnPackageInPurchasedSection(driver, productTitle);
            if (!result) {
                liveClassMsgList.addAll(myContentUtilObj.myContentMsgList);
                return result;
            }

            if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
                batchUtilObj = new BatchUtil(driver);
                result = batchUtilObj.clickOnDoneBtn(driver);
                if (!result) {
                    liveClassMsgList.addAll(batchUtilObj.batchMsgList);
                    return result;
                }

                result = batchUtilObj.clickOnSubjectTitle(driver);
                if (!result) {
                    liveClassMsgList.addAll(batchUtilObj.batchMsgList);
                    return result;
                }
            }

            result = handledNotificationPopUp(driver);
            if (!result) {
                liveClassMsgList.add("Not able to handled NotificationPopUp.");
                return result;
            }

            result = clickOnGotItBtn(driver);
            if (!result) {
                liveClassMsgList.add("Not able to click GotIt button.");
                return result;
            }

            cfObj.scrollUtill(driver, 1, direction.DOWN);
            result = validateHandOutsIcon(driver);
            if (!result) {
                liveClassMsgList.add("Not able to validate HandOutsIcon.");
                return result;
            }

            // result=clickOnPlayerViewStateBtn(driver);
            // if (!result) {
            // System.out.println("Not able to click PlayerViewStateBtn.");
            // return result;
            // }

            // result=validateVideoScreenHandOutsIcon(driver);
            // if (!result) {
            // System.out.println("Not able to validate VideoScreenHandOutsIcon.");
            // return result;
            // }

            // result=validateEBookIcon(driver);
            // if (!result) {
            // liveClassMsgList.add("Not able to validate EBookIcon.");
            // return result;
            // }

            cfObj.scrollUtill(driver, 1, direction.UP);

            result = validateRecordedClassDownloadOnDifferentQuality(driver, productTitle);
            if (!result) {
                liveClassMsgList.add("Not able to validate VideoDownloadOnDifferentQuality.");
                return result;
            }
        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("VideoCourse_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean validateRecordedClassDownloadOnDifferentQuality(AppiumDriver<MobileElement> driver,
                                                                   String downloadedVideo) {
        boolean result = true;
        try {
            myContentUtilObj = new MyContentUtil(driver);
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getVideoDownloadIconList().get(0),
                    10);
            if (!result) {
                liveClassMsgList.add("VideoDownloadIconList is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getVideoDownloadIconList().get(0),
                    10);
            if (!result) {
                liveClassMsgList.add("VideoDownloadIconList is not visible.");
                return result;
            }

            cfObj.commonClick(liveClassPageObj.getVideoDownloadIconList().get(0));
            Thread.sleep(2000);
            if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "state_layout", "id", 5)) {
                cfObj.commonClick(liveClassPageObj.getVideoDownloadIconList().get(0));
            }
            if (cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getPermissionAllowBtn(), 30)) {

                cfObj.commonClick(liveClassPageObj.getPermissionAllowBtn());
            }
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getSelectDownloadQualityTitle(),
                    10);
            if (!result) {
                liveClassMsgList.add("SelectDownloadQualityTitle is not visible.");
                return result;
            }

            cfObj.commonClick(liveClassPageObj.getDownloadQualityList().get(0));
            result = liveClassPageObj.getDownloadQualityList().get(0).getAttribute("checked").equals("true");
            if (!result) {
                liveClassMsgList.add("Download Quality RadioButton is not selected.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getDownloadBtn());

            result = cfObj.waitTillElementIsVisible(driver, 10, 2000, liveClassPageObj.getDownloadDeterminateBtn());
            if (!result) {
                liveClassMsgList.add("Not able to download video completely.");
                return result;
            }
            result = clickOnGotItBtn(driver);
            if (!result) {
                liveClassMsgList.add("Not able to click GotIt button.");
                return result;
            }

            result = navigateToRecordedVideoDownloadSection(driver);
            if (!result) {
                liveClassMsgList.add("Not able to navigate navigate RecordedVideoDownloadSection.");
                return result;
            }

            result = myContentUtilObj.validateDownloadedVideoIsPresent(driver, downloadedVideo);
            if (!result) {
                liveClassMsgList.addAll(myContentUtilObj.myContentMsgList);
                return result;
            }

            result = validateDownloadedVideoIsPlayingWithInterNet(driver);
            if (!result) {
                liveClassMsgList.add("Not able to validate DownloadedVideoIsPlayingWithInterNet.");
                return result;
            }

            result = validateDownloadedVideoIsPlayingWithOutInterNet(driver);
            if (!result) {
                liveClassMsgList.add("Not able to validate DownloadedVideoIsPlayingWithInterNet.");
                return result;
            }

            result = validateDeleteActionOnDownloadedVideo(driver);
            if (!result) {
                liveClassMsgList.add("Not able to validate DeleteActionOnDownloadedVideo.");
                return result;
            }

            result = myContentUtilObj.validateDownloadedVideoIsNotPresent(driver);
            if (!result) {
                liveClassMsgList.addAll(myContentUtilObj.myContentMsgList);
                return result;
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("RecordedClassDownloadOnDifferentQuality_Exception" + e.getMessage());
        }
        return result;

    }

    public boolean waitForCompleteDownload(AppiumDriver<MobileElement> driver, MobileElement element) {
        boolean result = true;
        try {
            int i = 0;
            while (cfObj.commonWaitForElementToBeVisible(driver, element, 10)) {

                Thread.sleep(1000);
                i++;
                if (i > 4) {
                    liveClassMsgList.add("Not able to download.");
                    break;
                }
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("waitForCompleteDownload_Exception" + e.getMessage());
        }
        return result;

    }

    public boolean navigateToRecordedVideoDownloadSection(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
                result = cfObj.pressAndroidKey(driver, key.BACK, 2);
                if (!result) {
                    liveClassMsgList.add("Unable to press android back key 2 times");
                    return result;
                }
            } else {
                result = cfObj.pressAndroidKey(driver, key.BACK, 1);
                if (!result) {
                    liveClassMsgList.add("Unable to press android back key 1 times");
                    return result;
                }
            }
            result = myContentUtilObj.clickOnDownloadsBtn(driver);
            if (!result) {
                liveClassMsgList.addAll(myContentUtilObj.myContentMsgList);
                return result;
            }
            result = myContentUtilObj.clickOnVideosDownloadSection(driver);
            if (!result) {
                liveClassMsgList.addAll(myContentUtilObj.myContentMsgList);
                return result;
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("NavigateToVideoDownloadSection_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean navigateToPurchasedVideoSection(AppiumDriver<MobileElement> driver, String productTitle) {
        boolean result = true;
        try {
            result = cfObj.pressAndroidKey(driver, key.BACK, 1);
            if (!result) {
                liveClassMsgList.add("Unable to press android back key 1 times");
                return result;
            }
            result = myContentUtilObj.clickOnPurchasedBtn(driver);
            if (!result) {
                liveClassMsgList.addAll(myContentUtilObj.myContentMsgList);
                return result;
            }

            result = myContentUtilObj.clickOnPackageInPurchasedSection(driver, productTitle);
            if (!result) {
                liveClassMsgList.addAll(myContentUtilObj.myContentMsgList);
                return result;
            }
        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("NavigateToPurchasedVideoSection_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean clickOnRecordedVideoDownloadIcon(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            int i = 0;
            while (!(cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getVideoDownloadIcon(), 10))) {
                cfObj.scrollUtill(driver, 1, direction.DOWN);
                if (i > 10) {
                    break;
                }
                i++;
            }
            cfObj.commonClick(liveClassPageObj.getVideoDownloadIcon());
        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("RecordedVideoDownloadIcon_Exception" + e.getMessage());
        }
        return result;

    }

    public boolean validateHandOutsIcon(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            int i = 0;
            while (!(cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getHandOutsIcon(), 10))) {
                cfObj.scrollUtill(driver, 1, direction.DOWN);
                if (i > 10) {
                    break;
                }
                i++;
            }
            if (ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {
                cfObj.commonClick(liveClassPageObj.getHandOutsIcon());
            } else {
                cfObj.commonClick(driver, "//*[contains(@resource-id,'handouts_frame')]/android.widget.ImageView",
                        "xpath");
            }
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getHandOutsTitle(), 10);
            if (!result) {
                liveClassMsgList.add("HandOutsTitle is not visible.");
                return result;
            }

            handOutsList = liveClassPageObj.getHandOutCell().size();
            cfObj.commonClick(liveClassPageObj.getHandOutCell().get(0));
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getHandOutPdfDownLoadBtn(), 10);
            if (!result) {
                liveClassMsgList.add("HandOutPdfDownLoadBtn is not visible.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getHandOutPdfDownLoadBtn());
            if (cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getPermissionAllowBtn(), 10)) {
                cfObj.commonClick(liveClassPageObj.getPermissionAllowBtn());
            }
            result = !(cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getHandOutPdfDownLoadBtn(), 10));
            if (!result) {
                liveClassMsgList.add("HandOutPdfDownLoadBtn is visible.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getPdfExitBtn());
            Thread.sleep(2000);
            if (ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {
                cfObj.commonClick(liveClassPageObj.getHandOutsIcon());
            } else {
                cfObj.commonClick(driver, "//*[contains(@resource-id,'handouts_frame')]/android.widget.ImageView",
                        "xpath");
            }
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getHandOutsTitle(), 10);
            if (!result) {
                liveClassMsgList.add("HandOutsTitle is not visible.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getHandOutDownLoadBtn().get(0));
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getDeleteBtn(), 10);
            if (!result) {
                liveClassMsgList.add("DeleteBtn is not visible.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getDeleteBtn());
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getHandOutsTitle(), 10);
            if (!result) {
                liveClassMsgList.add("HandOutsTitle is not visible.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getHandOutCell().get(0));
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getHandOutPdfDownLoadBtn(), 10);
            if (!result) {
                liveClassMsgList.add("HandOutPdfDownLoadBtn is not visible.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getPdfExitBtn());

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("HandOutsIcon_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean validateEBookIcon(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            int i = 0;
            while (!(cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getEbookIcon(), 10))) {
                cfObj.scrollUtill(driver, 1, direction.DOWN);
                if (i > 10) {
                    break;
                }
                i++;
            }
            if (ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {
                cfObj.commonClick(liveClassPageObj.getEbookIcon());
            } else {
                cfObj.commonClick(cfObj.commonGetElements(driver,
                        "//*[contains(@resource-id,'ebooks')]/android.widget.ImageView", "xpath").get(0));
            }
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getCopyRightPopUpTitle(), 10);
            if (!result) {
                liveClassMsgList.add("CopyRightPopUpTitle is not visible.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getSaveBtn());
            result = waitForCompleteDownload(driver, liveClassPageObj.getPageLoaderIcon());
            if (!result) {
                liveClassMsgList.add("Not able to load EBook PDF.");
                return result;
            }
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getEbookViewerContainer(), 10);
            if (!result) {
                liveClassMsgList.add("EbookViewerContainer is not visible.");
                return result;
            }

            result = cfObj.pressAndroidKey(driver, key.BACK, 1);
            if (!result) {
                liveClassMsgList.add("Unable to press android back key 1 times");
                return result;
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("EBookIcon_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean validateVideoDownloadOnDifferentQuality(AppiumDriver<MobileElement> driver, String downloadedVideo) {
        boolean result = true;
        try {
            myContentUtilObj = new MyContentUtil(driver);
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getVideoDownloadIconList().get(0),
                    10);
            if (!result) {
                liveClassMsgList.add("VideoDownloadIconList is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getVideoDownloadIconList().get(0),
                    10);
            if (!result) {
                liveClassMsgList.add("VideoDownloadIconList is not visible.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getVideoDownloadIconList().get(0));

            if (!(cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getPermissionAllowBtn(), 10))) {
                cfObj.commonClick(liveClassPageObj.getVideoDownloadIconList().get(0));
            }
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getPermissionAllowBtn(), 30);
            if (!result) {
                liveClassMsgList.add("PermissionAllowBtn is not visible.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getPermissionAllowBtn());

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getSelectDownloadQualityTitle(),
                    10);
            if (!result) {
                liveClassMsgList.add("SelectDownloadQualityTitle is not visible.");
                return result;
            }

            cfObj.commonClick(liveClassPageObj.getDownloadQualityList().get(0));
            result = liveClassPageObj.getDownloadQualityList().get(0).getAttribute("checked").equals("true");
            if (!result) {
                liveClassMsgList.add("Download Quality RadioButton is not selected.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getDownloadBtn());

            result = clickOnGotItBtn(driver);
            if (!result) {
                liveClassMsgList.add("Not able to click GotIt button.");
                return result;
            }

            result = navigateToRecordedVideoDownloadSection(driver);
            if (!result) {
                liveClassMsgList.add("Not able to navigate RecordedVideoDownloadSection.");
                return result;
            }
            result = myContentUtilObj.validateDownloadedVideoIsPresent(driver, downloadedVideo);
            if (!result) {
                liveClassMsgList.addAll(myContentUtilObj.myContentMsgList);
                return result;
            }

            result = validateDownloadedVideoIsPlayingWithInterNet(driver);
            if (!result) {
                liveClassMsgList.add("Not able to validate DownloadedVideoIsPlayingWithInterNet.");
                return result;
            }

            result = validateDownloadedVideoIsPlayingWithOutInterNet(driver);
            if (!result) {
                liveClassMsgList.add("Not able to validate DownloadedVideoIsPlayingWithInterNet.");
                return result;
            }
            result = myContentUtilObj.clickOnDownloadedVideo(driver);
            if (!result) {
                liveClassMsgList.addAll(myContentUtilObj.myContentMsgList);
                return result;
            }
            result = validateDeleteActionOnDownloadedVideo(driver);
            if (!result) {
                liveClassMsgList.add("Not able to validate DeleteActionOnDownloadedVideo.");
                return result;
            }

            result = cfObj.pressAndroidKey(driver, key.BACK, 1);
            if (!result) {
                liveClassMsgList.add("Unable to press android back key 1 times");
                return result;
            }

            result = myContentUtilObj.validateDownloadedVideoIsNotPresent(driver);
            if (!result) {
                liveClassMsgList.addAll(myContentUtilObj.myContentMsgList);
                return result;
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("VideoDownloadOnDifferentQuality_Exception" + e.getMessage());
        }
        return result;

    }

    public boolean validateDeleteActionOnDownloadedVideo(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            myContentUtilObj = new MyContentUtil(driver);
            result = myContentUtilObj.clickOnDownloadedVideo(driver);
            if (!result) {
                liveClassMsgList.addAll(myContentUtilObj.myContentMsgList);
                return result;
            }
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getVideoDownloadIconList().get(0),
                    10);
            if (!result) {
                liveClassMsgList.add("VideoDownloadIconList is not visible.");
                return result;
            }

            int downloadedVideoList = liveClassPageObj.getVideoDownloadIconList().size();
            for (int i = 0; i < downloadedVideoList; i++) {
                result = cfObj.commonWaitForElementToBeVisible(driver,
                        liveClassPageObj.getVideoDownloadIconList().get(i), 10);
                if (!result) {
                    liveClassMsgList.add("VideoDownloadIconList is not visible.");
                    return result;
                }
                cfObj.commonClick(liveClassPageObj.getVideoDownloadIconList().get(i));
                result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getDeleteBtn(), 30);
                if (!result) {
                    liveClassMsgList.add("Delete button is not visible.");
                    return result;
                }
                cfObj.commonClick(liveClassPageObj.getDeleteBtn());
            }
            Thread.sleep(2000);
            result = cfObj.pressAndroidKey(driver, key.BACK, 1);
            if (!result) {
                liveClassMsgList.add("Unable to press android back key 1 times");
                return result;
            }
        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("ValidateDeleteActionOnDownloadedVideo_Exception" + e.getMessage());
        }
        return result;

    }

    public boolean verifyLiveClassOnAndroidWeb(AppiumDriver<MobileElement> driver, ConfigurationMode configMode, String typeOfClass) {
        boolean result = true;
        String productTitle = "Automation live class - Dont use please";
        try {
            result = createLiveClass(false, typeOfClass, "44262", "abhay.rai@adda247.com", "0002@aada!");
            if (!result) {
                liveClassMsgList.add("Not able to create Live class.");
                return result;
            }
            Thread.sleep(2000);

            // Setup Live Class for Teacher Window
            webDriver = cfObj.commonStartAndOpenURLBrowserOnWeb(strTeacherUrl);
            if (webDriver == null) {
                liveClassMsgList.add("Not able to Open Teacher Screen.");
                return false;
            }

            loginUtilObj = new LoginUtil(driver);
            result = loginUtilObj.verifyLoginUsingEmailId(driver, "adda13@gmail.com", "amitpundir", true);
            if (!result) {
                liveClassMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            homePageUtilObj = new HomePageUtil(driver);
            result = homePageUtilObj.clickMyContentButton(driver);
            if (!result) {
                liveClassMsgList.addAll(homePageUtilObj.homeMsgList);
                return result;
            }

            myContentUtilObj = new MyContentUtil(driver);
            result = myContentUtilObj.clickOnPurchasedBtn(driver);
            if (!result) {
                liveClassMsgList.addAll(myContentUtilObj.myContentMsgList);
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//*[contains(@content-desc, 'Select Your Course')]", "xpath", 30);
            if (result) {
                Thread.sleep(1000);
                cfObj.commonClick(cfObj.commonGetElement(driver,
                        "//*[contains(@content-desc,'" + productTitle + "')]", "xpath"));

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"Selected Course\"]", "xpath", 20);
                if (!result) {
                    liveClassMsgList.add("Selected course text is not visible or might be page is not loaded.");
                    return result;
                }
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"viewAllContentClicked\n" +
                    "VIEW ALL CONTENT\"]", "xpath", 10);
            if (!result) {
                result = myContentUtilObj.clickOnSpecificPurchasedPackage(driver, productTitle);
                if (!result) {
                    liveClassMsgList.addAll(myContentUtilObj.myContentMsgList);
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"viewAllContentClicked\n" +
                        "VIEW ALL CONTENT\"]", "xpath", 10);
                if(!result){
                    liveClassMsgList.add("View all content btn is not visible after selecting the package.");
                    return result;
                }
                cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.View[@content-desc=\"viewAllContentClicked\n" +
                        "VIEW ALL CONTENT\"]", "xpath"));

            } else {
                cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.View[@content-desc=\"viewAllContentClicked\n" +
                        "VIEW ALL CONTENT\"]", "xpath"));
            }

            result = handledNotificationPopUp(driver);
            if (!result) {
                liveClassMsgList.add("Not able to handled NotificationPopUp.");
                return result;
            }

            // handle hint
            result = myContentUtilObj.handleHintInsideContent(driver, productTitle);
            if (!result) {
                liveClassMsgList.addAll(myContentUtilObj.myContentMsgList);
                return result;
            }

            // Setup Live Class for Student Window
            result = setUpLiveClass(webDriver);
            if (!result) {
                liveClassMsgList.add("Live class setup failed.");
                return result;
            }

            // Start Live Class from Teacher
            webViewLiveClassUtilObj = new WebViewLiveClassUtil(webDriver);
            result = webViewLiveClassUtilObj.startClassFromTeacherSide(webDriver, configMode, strTeacherUrl);
            if (!result) {
                liveClassMsgList.addAll(webViewLiveClassUtilObj.webViewLiveClassUtilMsgList);
                liveClassMsgList.add("Not able to start class from teacher screen.");
                return result;
            }

            System.out.println("Join live starting");

            result = clickOnJoinLiveBtn(driver);
            if (!result) {
                liveClassMsgList.add("Not able to click JoinLiveBtn.");
                return result;
            }
            System.out.println("Join live clicked");

            result = cfObj.waitTillElementIsVisible(driver, 5, 1000, cfObj.commonGetElement(driver, "loadingProgress", "id"));
            if (!result) {
                liveClassMsgList.add("Failed to load LiveClass Screen.");
                return result;
            }

            // Verify Screen UI on android
            result = validateLiveClassStartedScreenUi(driver);
            if (!result) {
                liveClassMsgList.add("LiveClassNotStartedVideoScreenUi verification failed.");
                return result;
            }

            // Verify Chat (Teacher, Web Student, Android Student)
            result = verifyChatInAppWeb(webDriver, driver);
            if (!result) {
                liveClassMsgList.add("Chat functionality verification failed.");
                return result;
            }

            // Verify Windows Resize
            result = validateWindowResizeBtnInAppWeb(driver, webDriver);
            if (!result) {
                liveClassMsgList.add("WindowResize functionality verification failed.");
                return result;
            }

            result = validateReportIssueBtnInAppWeb(driver, webDriver);
            if (!result) {
                liveClassMsgList.add("ReportIssue functionality verification failed.");
                return result;
            }

            result = validateQualitySettingBtnInAppWeb(driver, configMode, webDriver);
            if (!result) {
                liveClassMsgList.add("QualitySetting functionality verification failed.");
                return result;
            }

            result = validateBookmarkInAppWeb(driver, webDriver);
            if (!result) {
                liveClassMsgList.add("Bookmark functionality verification failed.");
                return result;
            }

            result = validateAskDoubtInAppWeb(driver, webDriver);
            if (!result) {
                liveClassMsgList.add("Ask doubt functionality verification failed.");
                return result;
            }

            // As hand raise section getting false, as handraise api is not called, need to call handraise event api
//			result = verifyStages(driver, webDriver);
//			if (!result) {
//				return result;
//			}

            result = validateHandOutInAppWeb(driver, webDriver);
            if (!result) {
                liveClassMsgList.add("Handout functionality verification failed.");
                return result;
            }

            // Changes in current sprint
//			result = validatePollsInAppWeb(driver, webDriver);
//			if (!result) {
//				liveClassMsgList.add("Polls functionality verification failed.");
//				return result;
//			}

            result = verifyOtherButtons(webDriver, typeOfClass);
            if (!result) {
                liveClassMsgList.add("Swap camera position, SOS Verification failed");
                return result;
            }

            result = webViewLiveClassUtilObj.endLiveClass(webDriver);
            if (!result) {
                liveClassMsgList.addAll(webViewLiveClassUtilObj.webViewLiveClassUtilMsgList);
                return result;
            }

            result = verifyTeacherFeedback(webDriver);
            if (!result) {
                liveClassMsgList.add("Teacher feedback functionality verification failed.");
                return result;
            }

            result = verifyStudentFeedbackFormOnWeb(webDriver);
            if (!result) {
                liveClassMsgList.add("Feedback screen on web functionality verification failed.");
                return result;
            }

            result = validateLiveClassFeedbackScreenOnAndroid(driver);
            if (!result) {
                liveClassMsgList.add("Feedback screen on android functionality verification failed.");
                return result;
            }
        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("verifyLiveClassOnAndroidWeb_Exception" + e.getMessage());

        } finally {
            LiveClassApiUtil liveClassApiUtil = new LiveClassApiUtil();
            liveClassApiUtil.endAddaLiveClass(externalScheduleId);

            if (webDriver != null) {
                webDriver.quit();
            }
        }
        return result;
    }

    private boolean validatePollsInAppWeb(AppiumDriver<MobileElement> androidDriver, WebDriver driver) {
        boolean result = true;
        try {

            driver.switchTo().window(strParentWindow);

            Thread.sleep(1000);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver,
                    "//div[@class='icon_bg teacher_icon mx-2 poll  ']", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Poll btn is not visible");
                return result;
            }

            // Click on poll btn
            cfObj.commonClick(
                    cfObj.commonGetElement(driver, "//div[@class='icon_bg teacher_icon mx-2 poll  ']", "xpath"));

            result = cfObj.commonWaitForElementToBeVisible(driver, webViewLiveClassUtilObj.getViewQuickPollBtn(), 20);
            if (!result) {
                liveClassMsgList.add("Quick poll btn is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, webViewLiveClassUtilObj.getViewPollResponsesBtn(), 20);
            if (!result) {
                liveClassMsgList.add("Poll responses btn is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, webViewLiveClassUtilObj.getViewCumulativeLeaderboardBtn(),
                    20);
            if (!result) {
                liveClassMsgList.add("Cumulative leaderboard btn is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//span[contains(text(),'Poll')]", "xpath",
                    10);
            if (result) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "//span[contains(text(),'Poll')]", "xpath"));

                System.out.println("1ST POLL STARTED");

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".ant-modal-header", "css", 10);
                if (!result) {
                    liveClassMsgList.add("Poll window is not present");
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, webViewLiveClassUtilObj.getBtnSubmitPoll(), 10);
                if (!result) {
                    liveClassMsgList.add("Submit poll button is not present");
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, webViewLiveClassUtilObj.getBtnCancelPoll(), 10);
                if (!result) {
                    liveClassMsgList.add("Cancel poll button is not present");
                }

                // Type Poll question
                cfObj.commonSetTextTextBox(webViewLiveClassUtilObj.getTextFieldPoll().get(0), "This is a automated poll question");

                // Type poll first option and marking correct
                cfObj.commonSetTextTextBox(webViewLiveClassUtilObj.getTextFieldPoll().get(1), "Correct");
                Thread.sleep(1000);

                // Selecting first option as correct
                WebElement selection = cfObj.commonGetElement(driver, "//Select", "xpath");
                Select dropdown = new Select(selection);
                dropdown.selectByValue("true");

                // Add another option
                cfObj.commonClick(cfObj.commonGetElement(driver, ".addmoreoption", "css"));
                Thread.sleep(1000);

                cfObj.commonSetTextTextBox(webViewLiveClassUtilObj.getTextFieldPoll().get(2), "Wrong");

                Thread.sleep(1000);

                // Sending poll
                cfObj.commonClick(webViewLiveClassUtilObj.getBtnSubmitPoll());

                // Attempting poll at student web
                driver.switchTo().window(strChildWindow);
                Thread.sleep(500);

                // Waiting for poll to appear at student side
                result = false;
                for (int i = 1; i < 5; i++) {
                    if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".poll-radio-wrapper", "css", 1)) {
                        result = true;
                        break;
                    } else {
                        System.out.println("Did not received poll waited for " + i * 10 + " seconds....");
                        Thread.sleep(5000);
                    }
                }
                if (!result) {
                    liveClassMsgList.add("Student view did not received poll after waiting");
                    return result;
                }

                Thread.sleep(1000);

                // Select option in poll
                cfObj.commonClick(webViewLiveClassUtilObj.getListPollOptions().get(0));

                result = attemptPoll(androidDriver);
                if (!result) {
                    liveClassMsgList.add("Failed to attempt Poll on android student");
                    return result;
                }

                // Wait while poll timeout
                Thread.sleep(20000);

                // switch to parent window
                driver.switchTo().window(strParentWindow);

                Thread.sleep(10000);

                // Checking if poll response/leaderboard window is open
                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='ant-modal-content']",
                        "xpath", 15);
                if (!result) {
                    liveClassMsgList.add("The 1st leaderboard popup is not visible at teacher side");
                    result = true;
                } else {
                    if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".ant-modal-close-x", "css", 10)) {
                        cfObj.commonClick(androidDriver, "//*[@class='anticon anticon-close ant-modal-close-icon']", "xpath");

                    } else {

                        if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".ant-modal-close-x", "css", 10)) {
                            cfObj.commonClick(androidDriver, "//*[@class='anticon anticon-close ant-modal-close-icon']",
                                    "xpath");
                        } else {
                            System.out.println(
                                    "1st Leaderboard did not appeared at teacher side after submitting poll on student");
                            liveClassMsgList.add(
                                    "1st Leaderboard did not appeared at teacher side after submitting poll on student");
                            return false;
                        }
                    }
                }

                driver.switchTo().window(strChildWindow);

                Thread.sleep(5000);

                // Checking if poll response window is open at student side
                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='ant-modal-content']",
                        "xpath", 15);
                if (!result) {
                    liveClassMsgList.add("The 1st leaderboard popup is not visible at student side");
                } else {

                    if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".ant-modal-close-x", "css", 10)) {
                        cfObj.commonClick(androidDriver, "//*[@class='anticon anticon-close ant-modal-close-icon']", "xpath");

                    } else {

                        if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".ant-modal-close-x", "css", 10)) {
                            cfObj.commonClick(androidDriver, "//*[@class='anticon anticon-close ant-modal-close-icon']",
                                    "xpath");
                        } else {
                            System.out.println(
                                    "1st Leaderboard did not appeared at student side after submitting poll on student");
                            liveClassMsgList.add(
                                    "1st Leaderboard did not appeared at student side after submitting poll on student");
                            return false;
                        }
                    }
                }

                driver.switchTo().window(strParentWindow);

                Thread.sleep(2000);

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                        "//div[@class='icon_bg teacher_icon mx-2 poll  ']", "xpath", 10);
                if (!result) {
                    liveClassMsgList.add("Poll btn is not visible, after 1st poll finished");
                    return result;
                }

            } else {
                liveClassMsgList.add("Unable to open new poll option or not visible");
                return false;
            }

            Thread.sleep(2000);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//div[@class='icon_bg teacher_icon mx-2 poll  ']", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Poll btn is not visible");
                return result;
            }

            cfObj.commonClick(
                    cfObj.commonGetElement(driver, "//div[@class='icon_bg teacher_icon mx-2 poll  ']", "xpath"));

            result = cfObj.commonWaitForElementToBeVisible(driver, webViewLiveClassUtilObj.getViewCumulativeLeaderboardBtn(),
                    20);
            if (!result) {
                liveClassMsgList.add("Cumulative leaderboard btn is not visible");
                return result;
            }

            cfObj.commonClick(webViewLiveClassUtilObj.getViewCumulativeLeaderboardBtn());

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//b[text()='Cumulative Leaderboard']",
                    "xpath", 30);
            if (!result) {
                liveClassMsgList.add("The Cumulative leaderboard heading in popup is not visible");
                return result;
            }

            if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//span[@aria-label='close']", "xpath", 10)) {
                cfObj.commonClick(androidDriver, "//span[@aria-label='close']", "xpath");
            } else {
                liveClassMsgList.add("Cumulative Leaderboard did not appeared at teacher end");
                return false;
            }


            //-----------------------//
            webDriver.switchTo().window(strParentWindow);

            result = webViewLiveClassUtilObj.createPoll(webDriver);
            if (!result) {
                liveClassMsgList.addAll(webViewLiveClassUtilObj.webViewLiveClassUtilMsgList);
                return result;
            }

            result = attemptPoll(androidDriver);
            if (!result) {
                liveClassMsgList.add("Failed to attempt Poll on android student");
                return result;
            }

            result = validatePollResponseLeaderboardPage(androidDriver);
            if (!result) {
                liveClassMsgList.add("Failed to validate PollResponse page on android student");
                return result;
            }

            result = webViewLiveClassUtilObj.closePollResponseScreenOnTeacherEnd(webDriver);
            if (!result) {
                liveClassMsgList.addAll(webViewLiveClassUtilObj.webViewLiveClassUtilMsgList);
                return result;
            }
        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("validatePollsInAppWeb_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean validateDownloadedVideoIsPlayingWithInterNet(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            myContentUtilObj = new MyContentUtil(driver);
            result = myContentUtilObj.clickOnDownloadedVideo(driver);
            if (!result) {
                liveClassMsgList.addAll(myContentUtilObj.myContentMsgList);
                return result;
            }
            int downloadVideoClassList = liveClassPageObj.getVideoCourseTitleList().size();

            for (int i = 0; i < downloadVideoClassList; i++) {
                cfObj.commonClick(liveClassPageObj.getVideoCourseTitleList().get(i));
                result = cfObj.commonVerifyValueTextBox(liveClassPageObj.getPlayingVideoTitle(),
                        liveClassPageObj.getVideoCourseTitleList().get(i).getText());
                if (!result) {
                    liveClassMsgList.add("Clicked video is not playing.");
                    return result;
                }

                result = cfObj.commonVerifyValueTextBox(liveClassPageObj.getWatchTimerList().get(i), "Playing");
                if (!result) {
                    liveClassMsgList.add("Watch timer text is not matching.");
                    return result;
                }
            }
            result = cfObj.pressAndroidKey(driver, key.BACK, 2);
            if (!result) {
                liveClassMsgList.add("Unable to press android back key 2 times");
                return result;
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("DownloadedVideoIsPlayingWithInterNet_Exception" + e.getMessage());
        }
        return result;

    }

    public boolean validateDownloadedVideoIsPlayingWithOutInterNet(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.changeNetworkState(driver, NetworkState.OFF);
            if (!result) {
                liveClassMsgList.add("Not able to make make mobile network off.");
                return result;
            }
            Thread.sleep(2000);
            myContentUtilObj = new MyContentUtil(driver);
            result = myContentUtilObj.clickOnDownloadedVideo(driver);
            if (!result) {
                liveClassMsgList.addAll(myContentUtilObj.myContentMsgList);
                return result;
            }
            int downloadVideoClassList = liveClassPageObj.getVideoCourseTitleList().size();

            for (int i = 0; i < downloadVideoClassList; i++) {

                cfObj.commonClick(liveClassPageObj.getVideoCourseTitleList().get(i));
                result = cfObj.commonVerifyValueTextBox(liveClassPageObj.getPlayingVideoTitle(),
                        liveClassPageObj.getVideoCourseTitleList().get(i).getText());
                if (!result) {
                    liveClassMsgList.add("Clicked video is not playing.");
                    return result;
                }

                result = cfObj.commonVerifyValueTextBox(liveClassPageObj.getWatchTimerList().get(i), "Playing");
                if (!result) {
                    liveClassMsgList.add("Watch timer text is not matching.");
                    return result;
                }
            }
            result = cfObj.pressAndroidKey(driver, key.BACK, 2);
            if (!result) {
                liveClassMsgList.add("Unable to press android back key 2 times");
                return result;
            }

            result = cfObj.changeNetworkState(driver, NetworkState.ON);
            if (!result) {
                liveClassMsgList.add("Not able to make make mobile network ON.");
                return result;
            }
            Thread.sleep(2000);

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("DownloadedVideoIsPlayingWithInterNet_Exception" + e.getMessage());
        }
        return result;

    }

    public boolean validateVideoScreenHandOutsIcon(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            cfObj.commonClick(liveClassPageObj.getVideoScreenHandOutsBtn());
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getHandOutsTitle(), 10);
            if (!result) {
                liveClassMsgList.add("HandOutsTitle is not visible.");
                return result;
            }
            result = liveClassPageObj.getHandOutCell().size() == handOutsList;
            if (!result) {
                liveClassMsgList.add("HandOuts list is not matching.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getHandOutCell().get(0));
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getHandOutPdfDownLoadBtn(), 10);
            if (!result) {
                liveClassMsgList.add("HandOutPdfDownLoadBtn is not visible.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getHandOutPdfDownLoadBtn());
            result = !(cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getHandOutPdfDownLoadBtn(), 10));
            if (!result) {
                liveClassMsgList.add("HandOutPdfDownLoadBtn is visible.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getPdfExitBtn());
            cfObj.commonClick(liveClassPageObj.getHandOutsIcon());
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getHandOutsTitle(), 10);
            if (!result) {
                liveClassMsgList.add("HandOutsTitle is not visible.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getHandOutDownLoadBtn().get(0));
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getDeleteBtn(), 10);
            if (!result) {
                liveClassMsgList.add("DeleteBtn is not visible.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getDeleteBtn());
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getHandOutsTitle(), 10);
            if (!result) {
                liveClassMsgList.add("HandOutsTitle is not visible.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getHandOutCell().get(0));
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getHandOutPdfDownLoadBtn(), 10);
            if (!result) {
                liveClassMsgList.add("HandOutPdfDownLoadBtn is not visible.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getPdfExitBtn());

        } catch (Exception e) {
            result = false;

            liveClassMsgList.add("HandOutsIcon_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean clickOnJoinLiveBtn(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/join_live", "id", 30)) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/join_live", "id"));

            } else if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='JOIN LIVE']", "xpath", 20)) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='JOIN LIVE']", "xpath"));

            } else {
                result = false;
                liveClassMsgList.add("JoinLiveBtn_Not_Working.");
            }
        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("JoinLiveBtn_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean clickOnJoinBtn(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getJoinBtn(), 10);
            if (!result) {
                liveClassMsgList.add("JoinBtn is not visible.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getJoinBtn());

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("clickOnJoinBtn_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean clickOnLiveClassScreenGotItBtn(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            if (cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getLiveClassScreenGotItBtn(), 10)) {

                cfObj.commonClick(liveClassPageObj.getLiveClassScreenGotItBtn());
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("LiveClassScreenGotItBtn_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean validateLiveClassNotStartedVideoScreenUi(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getLiveClassStartSoonText(), 10);
            if (!result) {
                liveClassMsgList.add("LiveClassStartSoonText is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getWindowReSizeBtn(), 10);
            if (!result) {
                liveClassMsgList.add("WindowReSizeBtn is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getQualitySettingBtn(), 10);
            if (!result) {
                liveClassMsgList.add("QualitySettingBtn is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getChatWindowBtn(), 10);
            if (!result) {
                liveClassMsgList.add("ChatWindowBtn is not visible.");
                return result;
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("LiveClassNotStartedVideoScreenUi_Exception" + e.getMessage());
        }

        return result;
    }

    public boolean validateLiveClassStartedScreenUi(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getWindowReSizeBtn(), 15);
            if (!result) {

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='JOIN LIVE']", "xpath", 10);
                if (result) {
                    liveClassMsgList.add("The join live btn is not visible and not inside liveclass also.");
                    return false;
                } else {
                    cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='JOIN LIVE']", "xpath"));
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getWindowReSizeBtn(), 20);
                if (!result) {
                    liveClassMsgList.add("WindowReSizeBtn is not visible.");
                    return result;
                }
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getHandRaiseIcon(), 10);
            if (!result) {
                liveClassMsgList.add("HandRaiseBtn is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getLiveClassDoubtBtn(), 10);
            if (!result) {
                liveClassMsgList.add("DoubtBtn is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getQualitySettingBtn(), 10);
            if (!result) {
                liveClassMsgList.add("QualitySettingBtn is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getChatWindowBtn(), 10);
            if (!result) {
                liveClassMsgList.add("ChatWindowBtn is not visible.");
                return result;
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("LiveClassStartedScreenUi_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean validateWindowResizeBtnInAppWeb(AppiumDriver<MobileElement> driver, WebDriver webDriver) {
        boolean result = true;
        try {
            // Verify window resize btn on android
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getWindowReSizeBtn(), 10);
            if (!result) {
                liveClassMsgList.add("WindowReSizeBtn on android student is not visible.");
                return result;
            }

            cfObj.commonClick(liveClassPageObj.getWindowReSizeBtn());

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getSmallWindowSizeBtn(), 10);
            if (!result) {
                liveClassMsgList.add("SmallWindowSizeBtn on android student is not visible.");
                return result;
            }

            cfObj.commonClick(liveClassPageObj.getSmallWindowSizeBtn());

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getWindowReSizeBtn(), 10);
            if (!result) {
                liveClassMsgList.add("WindowReSizeBtn on android student is not visible.");
                return result;
            }

            // Verify window resize btn on web
            webDriver.switchTo().window(strChildWindow);

            result = cfObj.commonWaitForElementToBeVisible(webDriver, webViewLiveClassUtilObj.getWindowResizeBtn(), 10);
            if (!result) {
                liveClassMsgList.add("WindowResizeBtn on student web is not visible.");
                return result;
            }

            cfObj.commonClick(webViewLiveClassUtilObj.getWindowResizeBtn());

            result = cfObj.commonWaitForElementToBeVisible(webDriver, webViewLiveClassUtilObj.getFullScreenWindowResizeBtn(), 10);
            if (!result) {
                liveClassMsgList.add("Full screen WindowResizeBtn on student web is not visible.");
                return result;
            }

            cfObj.commonClick(webViewLiveClassUtilObj.getFullScreenWindowResizeBtn());

            result = cfObj.commonWaitForElementToBeVisible(webDriver, webViewLiveClassUtilObj.getWindowResizeBtn(), 10);
            if (!result) {
                liveClassMsgList.add("WindowResizeBtn on student web is not visible after come back from full screen");
                return result;
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("validateWindowResizeBtnInAppWeb_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean validateQualitySettingBtnBeforeClassStarted(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getQualitySettingBtn(), 10);
            if (!result) {
                liveClassMsgList.add("QualitySettingBtn is not visible.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getQualitySettingBtn());
            if (cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getQualitySettingBtn(), 10)) {
                cfObj.commonClick(liveClassPageObj.getQualitySettingBtn());
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getVideoQualitySheetTitle(), 10);
            if (!result) {
                liveClassMsgList.add("VideoQualitySheetTitle is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getVideoQualityRadioBtn().get(0),
                    10);
            if (!result) {
                liveClassMsgList.add("VideoQualityRadioBtn is not visible.");
                return result;
            }
            result = liveClassPageObj.getVideoQualityOptionTitle().size() == 1;
            if (!result) {
                liveClassMsgList.add("More than one video quality option is present.");
                return result;
            }

            result = cfObj.commonVerifyValueTextBox(liveClassPageObj.getVideoQualityOptionTitle().get(0), "Auto");
            if (!result) {
                liveClassMsgList.add("Auto quality option is not visible.");
                return result;
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("QualitySettingBtn_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean validateQualitySettingBtnInAppWeb(AppiumDriver<MobileElement> driver, ConfigurationMode configMode, WebDriver webDriver) {
        boolean result = true;
        try {
            // Verify quality btn on android student
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getQualitySettingBtn(), 10);
            if (!result) {
                liveClassMsgList.add("QualitySettingBtn on android student is not visible.");
                return result;
            }

            cfObj.commonClick(liveClassPageObj.getQualitySettingBtn());

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getVideoQualitySheetTitle(), 10);
            if (!result) {
                liveClassMsgList.add("VideoQualitySheetTitle on android student is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getVideoQualityRadioBtn().get(0), 10);
            if (!result) {
                liveClassMsgList.add("VideoQualityRadioBtn on android student is not visible.");
                return result;
            }

            if (configMode.equals(ConfigurationMode.STANDARD)) {

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.TextView[@text='720p']", "xpath", 10);
                if (!result) {
                    liveClassMsgList.add("720-VideoQualityRadioBtn on android student is not visible.");
                    return result;
                }
            } else {
                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.TextView[@text='480p']", "xpath", 10);
                if (!result) {
                    liveClassMsgList.add("480-VideoQualityRadioBtn on android student is not visible.");
                    return result;
                }
            }

            int randomBtn = Common_Function.RandomNumber(0, liveClassPageObj.getVideoQualityRadioBtn().size() - 1);
            cfObj.commonClick(liveClassPageObj.getVideoQualityRadioBtn().get(randomBtn));

            // Verify quality btn on web student
            webDriver.switchTo().window(strChildWindow);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//li[contains(@class,'settingbtn')]//div", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Quality btn on web student is not visible");
                return result;
            }

            cfObj.commonClick(cfObj.commonGetElement(webDriver, "//li[contains(@class,'settingbtn')]//div", "xpath"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//span[contains(text(),'Video Quality')]", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Video Quality text on web student is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//label[contains(.,'audio_only')]", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("audio_only Quality on web student is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//label[contains(.,'160p')]", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("160p Quality on web student is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//label[contains(.,'360p')]", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("360p Quality on web student is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//label[contains(.,'480p')]", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("480p Quality on web student is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//label[contains(.,'720p')]", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("720p Quality on web student is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//button[@class='ant-btn ant-btn-default ant-dropdown-trigger ant-dropdown-open']", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Quality btn on web student is not visible when opened");
                return result;
            }

            cfObj.commonClick(cfObj.commonGetElement(webDriver, "//button[@class='ant-btn ant-btn-default ant-dropdown-trigger ant-dropdown-open']", "xpath"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//button[@class='ant-btn ant-btn-default ant-dropdown-trigger ant-dropdown-open']", "xpath", 10);
            if (result) {
                liveClassMsgList.add("Quality btn on web student is visible when opened, but we closed it.");
                return false;
            } else {
                result = true;
            }
        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("validateQualitySettingBtnInAppWeb_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean validateReportIssueBtnInAppWeb(AppiumDriver<MobileElement> driver, WebDriver webDriver) {
        boolean result = true;
        try {
            // Verify report btn on android student
            cfObj.tapOnTopPointerClass(driver);

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getReportIssueBtn(), 10);
            if (!result) {

                cfObj.tapOnTopPointerClass(driver);

                result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getReportIssueBtn(), 10);
                if (!result) {
                    liveClassMsgList.add("ReportIssueBtn is not visible after clicking on center");
                    return result;
                }
            }

            cfObj.commonClick(liveClassPageObj.getReportIssueBtn());

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getReportPopUpTitle(), 10);
            if (!result) {
                liveClassMsgList.add("ReportPopUpTitle is not visible.");
                return result;
            }

            int reportListCount = liveClassPageObj.getReportOptionList().size();
            for (int i = 0; i < reportListCount; i++) {
                result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getReportOptionList().get(i), 10);
                if (!result) {
                    liveClassMsgList.add("ReportOptionList is not visible.");
                    return result;
                }

                cfObj.commonClick(liveClassPageObj.getReportOptionList().get(i));

                result = liveClassPageObj.getReportOptionList().get(i).getAttribute("checked").equals("true");
                if (!result) {
                    liveClassMsgList.add("ReportOptionList is not selected.");
                    return result;
                }
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getReportPopUpOkBtn(), 10);
            if (!result) {
                liveClassMsgList.add("ReportPopUpOkBtn is not visible.");
                return result;
            }

            cfObj.commonClick(liveClassPageObj.getReportPopUpOkBtn());

            result = !(cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getReportPopUpTitle(), 10));
            if (!result) {
                liveClassMsgList.add("ReportPopUpTitle is visible.");
                return result;
            }

            // Could not verify report btn on web student, as hovering/locator issues

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("validateReportIssueBtnInAppWeb_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean validateChatWindowBtn(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            cfObj.commonClick(liveClassPageObj.getChatWindowBtn());

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getMessageSendBtn(), 10);
            if (!result) {
                liveClassMsgList.add("MessageSendBtn is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getMessageSendTextField(), 10);
            if (!result) {
                liveClassMsgList.add("MessageSendTextField is not visible.");
                return result;
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("ChatWindowBtn_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean enterMessageInMessageSendTextField(AppiumDriver<MobileElement> driver, boolean privateMsg) {
        boolean result = true;
        try {

            cfObj.commonClick(liveClassPageObj.getMessageSendTextField());

            if (privateMsg) {
                result = cfObj.enterText(driver, "privateandroid");
            } else {
                result = cfObj.enterText(driver, "hiandroidstudent");
            }
            if (!result) {
                liveClassMsgList.add("Not able to enter message in messageSendTextField.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getMessageSendBtn(), 10);
            if (!result) {
                liveClassMsgList.add("MessageSendBtn is not visible.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getMessageSendBtn());
            Thread.sleep(2000);

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getChatMessageText().get(0), 10);
            if (!result) {
                liveClassMsgList.add("ChatMessageText is not visible at android student chat");
                return result;
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("enterMessageInMessageSendTextField_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean handleAudioVideoAllowPopUp(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            boolean status = false;
            for (int i = 0; i < 2; i++) {
                if (cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getContinueBtn(), 10)) {
                    cfObj.commonClick(liveClassPageObj.getContinueBtn());
                    status = true;
                }
                if (cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getAllowBtn(), 10)) {
                    cfObj.commonClick(liveClassPageObj.getAllowBtn());
                    status = true;
                }
                if (cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getOnlyThisTimeBtn(), 10)) {
                    cfObj.commonClick(liveClassPageObj.getOnlyThisTimeBtn());
                    status = true;
                }
                if (!status) {
                    break;
                }
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("HandleAudioVideoAllowPopUp_Exception" + e.getMessage());
        }
        return result;

    }

    public boolean clickOnLanguageChangeSheetCloseBtn(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            if (cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getLanguageChangeSheetCloseBtn(), 10)) {
                result = cfObj.commonWaitForElementToBeVisible(driver,
                        liveClassPageObj.getLanguageChangeSheetCloseBtn(), 10);
                if (!result) {
                    liveClassMsgList.add("LanguageChangeSheetClose button is not visible.");
                    return result;
                }
                cfObj.commonClick(liveClassPageObj.getLanguageChangeSheetCloseBtn());
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("LanguageChangeSheetCloseBtn_Exception" + e.getMessage());
        }
        return result;

    }

    @SuppressWarnings("unlikely-arg-type")
    public boolean validateFacultySentMessage(AppiumDriver<MobileElement> driver, String facultyMessage) {
        boolean result = true;
        try {
            Thread.sleep(4000);
            result = cfObj.commonWaitForElementToBeVisible(driver,
                    liveClassPageObj.getChatMessageText().get(liveClassPageObj.getChatMessageText().size() - 1), 10);
            if (!result) {
                liveClassMsgList.add("ChatMessage text is not visible.");
                return result;
            }

            result = liveClassPageObj.getChatMessageText().get(liveClassPageObj.getChatMessageText().size() - 1)
                    .equals(facultyMessage);
            if (!result) {
                liveClassMsgList.add("Faculty sent message is not visible.");
                return result;
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("ValidateFacultySentMessage_Exception" + e.getMessage());

        }
        return result;
    }

    public boolean verifyPushAction(AppiumDriver<MobileElement> driver, String sourcePath, String remotePath) {
        boolean result = true;
        try {
            File file = new File(sourcePath);
            String absoluteSourcePath = file.getAbsolutePath();
            System.out.println(absoluteSourcePath);
            result = cfObj.pushFileToEmulator(driver, absoluteSourcePath, remotePath);
            if (!result) {
                liveClassMsgList.add("Not able to push the file to emulator.");
                return result;
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("verifyPushAction_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean validatePinnedMessage(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getPinnedMsgCountText(), 10);
            if (!result) {
                liveClassMsgList.add("Pinned message count is not visible.");
                return result;
            }
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getPinMessage(), 10);
            if (result) {
                cfObj.commonClick(liveClassPageObj.getPinChatDropDown());
                result = !(cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getPinMessage(), 10));
                if (!result) {
                    liveClassMsgList.add("Pinned message is visible.");
                    return result;
                }
            } else {
                cfObj.commonClick(liveClassPageObj.getPinChatDropDown());
                result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getPinMessage(), 10);
                if (!result) {
                    liveClassMsgList.add("Pinned message is not visible.");
                    return result;
                }
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("ValidatePinnedMessage_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean validatePrivateModeEnableText(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getPrivateModeEnableTxt(), 10);
            if (!result) {
                liveClassMsgList.add("Private Mode Enable text is not visible.");
                return result;
            }
        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("ValidatePrivateModeEnableText_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean validateBookmarkInAppWeb(AppiumDriver<MobileElement> driver, WebDriver webDriver) {
        boolean result = true;
        try {
            webDriver.switchTo().window(strParentWindow);

            // Start bookmark from teacher
            result = webViewLiveClassUtilObj.verifyStartBookmark(webDriver);
            if (!result) {
                liveClassMsgList.addAll(webViewLiveClassUtilObj.webViewLiveClassUtilMsgList);
                return result;
            }

            // Verify bookmark banner on web student
            webDriver.switchTo().window(strChildWindow);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//*[contains(text(),'Important Concept')]", "xpath", 30);
            if (!result) {
                liveClassMsgList.add("Important concept tag is not visible on web student");
            }

            // Verify bookmark banner on android student
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='Important Concept']", "xpath", 20);
            if (!result) {
                liveClassMsgList.add("Imp concept title is not visible on android student.");
            }

            // Stop bookmark from teacher
            webDriver.switchTo().window(strParentWindow);

            result = webViewLiveClassUtilObj.verifyStopBookmark(webDriver);
            if (!result) {
                liveClassMsgList.addAll(webViewLiveClassUtilObj.webViewLiveClassUtilMsgList);
                return result;
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("validateBookmark_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean validateMessageEnableModeFunction(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getMessageSendTextField(), 10);
            if (!result) {
                liveClassMsgList.add("MessageSendTextField is not visible.");
                return result;
            }

            result = cfObj.commonVerifyValueTextBox(liveClassPageObj.getMessageSendTextField(), "Chat turned Off");
            if (!result) {
                liveClassMsgList.add("MessageSendTextField  text is not matching.");
                return result;
            }

            result = !(cfObj.commonSetTextTextBox(liveClassPageObj.getMessageSendTextField(), sendMessage));
            if (!result) {
                liveClassMsgList.add("Message enable mode is not working.");
                return result;
            }
        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("ValidateMessageEnableModeFunction_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean validateExitFunction(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            cfObj.pressAndroidKey(driver, key.BACK, 1);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/tv_improve", "id", 10);
            if (!result) {
                liveClassMsgList.add("Please help us improve feedback page is not visible.");
                return result;
            }

            result = !(cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getMessageSendTextField(), 4));
            if (!result) {
                liveClassMsgList.add("MessageSendTextField is visible inside liveclass");
                return result;
            }

            cfObj.pressAndroidKey(driver, key.BACK, 1);

            Thread.sleep(1000);

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("ValidateExitFunction_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean enterMessageInNetworkOffState(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getMessageSendTextField(), 10);
            if (!result) {
                liveClassMsgList.add("MessageSendTextField is not visible.");
                return result;
            }

            result = cfObj.commonSetTextTextBox(liveClassPageObj.getMessageSendTextField(), sendMessage);
            if (!result) {
                liveClassMsgList.add("Not able to enter message in messageSendTextField.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getMessageSendBtn(), 10);
            if (!result) {
                liveClassMsgList.add("MessageSendBtn is not visible.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getMessageSendBtn());
            Thread.sleep(2000);

            result = cfObj.commonWaitForElementToBeVisible(driver,
                    liveClassPageObj.getChatMessageText().get(liveClassPageObj.getChatMessageText().size() - 1), 10);
            if (!result) {
                liveClassMsgList.add("ChatMessageText is not visible.");
                return result;
            }
            result = !(cfObj.commonVerifyValueTextBox(
                    liveClassPageObj.getChatMessageText().get(liveClassPageObj.getChatMessageText().size() - 1),
                    "You � " + sendMessage));
            if (!result) {
                liveClassMsgList.add("ChatMessageText is matching.");
                return result;
            }
        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("EnterMessageInNetworkOffState_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean handleFileUploadPopUp(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            if (cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getOnlyThisTimeBtn(), 10)) {
                cfObj.commonClick(liveClassPageObj.getOnlyThisTimeBtn());
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("HandleFileUploadPopUp_Exception" + e.getMessage());
        }
        return result;

    }

    public boolean uploadPdfFile(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getFileTab(), 10);
            if (!result) {
                liveClassMsgList.add("File tab is not visible.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getFileTab());

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getPdfFile(), 10);
            if (!result) {
                liveClassMsgList.add("PdfFile is not visible.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getPdfFile());

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("UploadPdfFile_Exception" + e.getMessage());
        }
        return result;

    }

    public boolean validateHandOutsNote(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getHandOutsView(), 10);
            if (!result) {
                liveClassMsgList.add("HandOuts view is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getHandOutsCommentText(), 10);
            if (!result) {
                liveClassMsgList.add("HandOuts comment text is not visible.");
                return result;
            }

            cfObj.commonClick(liveClassPageObj.getHandOutsView());

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getHandOutPdfDownLoadBtn(), 10);
            if (!result) {
                liveClassMsgList.add("HandOuts Pdf download btn is not visible.");
                return result;
            }

            cfObj.commonClick(liveClassPageObj.getHandOutPdfDownLoadBtn());
            Thread.sleep(3000);

            result = !(cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getHandOutPdfDownLoadBtn(), 10));
            if (!result) {
                liveClassMsgList.add("HandOuts Pdf download btn is visible.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getPdfExitBtn());
        } catch (Exception e) {

            result = false;
            liveClassMsgList.add("ValidateHandOutsNote_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean selectQuizOption(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getPollRadioBtn().get(0), 30);
            if (!result) {
                liveClassMsgList.add("PollRadioBtn is not visible.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getPollRadioBtn().get(0));
            int i = 0;
            while (cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getQuizTitle(), 10)) {
                Thread.sleep(3000);
                if (i > 5)
                    break;
                i++;
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("SelectQuizOption_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean validateLiveClassCertificateCell(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getCertificateTitle(), 10);
            if (!result) {
                liveClassMsgList.add("CertificateTitle is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getProgressBarTextValue(), 10);
            if (!result) {
                liveClassMsgList.add("ProgressBar Text value is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getCertificateClassCommingSoonBtn(),
                    10);
            if (!result) {
                liveClassMsgList.add("ProgressBar Text value is not visible.");
                return result;
            }

            // result=cfObj.commonVerifyValueTextBox(liveClassPageObj.getCertificateClassCommingSoonBtn(),
            // "Coming Soon");
            // if(!result) {
            // liveClassMsgList.add("CertificateVideoWatchBtn text is not matching.");
            // return result;
            // }

            childPackageUtilObj = new ChildPackageUtil(driver);
            result = childPackageUtilObj.validateCertificatePercentage(driver);
            if (!result) {
                liveClassMsgList.addAll(childPackageUtilObj.packagePageMsgList);
                return result;
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("validateLiveClassCertificateCell_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean validateCertificateLiveClassCommingSoonBtn(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getCertificateClassCommingSoonBtn(),
                    10);
            if (!result) {
                liveClassMsgList.add("CertificateClassCommingSoonBtn is not visible.");
                return result;
            }

            result = cfObj.commonVerifyValueTextBox(liveClassPageObj.getCertificateClassCommingSoonBtn(),
                    "Coming Soon");
            if (!result) {
                liveClassMsgList.add("CertificateClassCommingSoonBtn is not visible.");
                return result;
            }

            result = cfObj.waitForPageLoading(driver, 6, 60000, liveClassPageObj.getJoinLiveBtn());
            if (!result) {
                liveClassMsgList.add("Not able to handle JoinLive button.");
                return result;
            }

            result = clickOnJoinLiveBtn(driver);
            if (!result) {
                liveClassMsgList.add("Not able to click JoinLiveBtn.");
                return result;
            }

            result = clickOnJoinBtn(driver);
            if (!result) {
                liveClassMsgList.add("Not able to click JoinBtn.");
                return result;
            }

            result = handleAudioPermissionBtn(driver);
            if (!result) {
                liveClassMsgList.add("Not able to handle AudioPermissionBtn.");
                return result;
            }

            Thread.sleep(3000);
            result = clickOnLiveClassScreenGotItBtn(driver);
            if (!result) {
                liveClassMsgList.add("Not able to click JoinBtn.");
                return result;
            }

            result = validateLiveClassNotStartedVideoScreenUi(driver);
            if (!result) {
                liveClassMsgList.add("Not able to validate LiveClassNotStartedVideoScreenUi.");
                return result;
            }

            result = cfObj.pressAndroidKey(driver, key.BACK, 1);
            if (!result) {
                liveClassMsgList.add("Not able to click back button.");
                return result;
            }

            result = cfObj.commonVerifyValueTextBox(liveClassPageObj.getCertificateClassCommingSoonBtn(), "JOIN LIVE");
            if (!result) {
                liveClassMsgList.add("CertificateClass LiveBtn is not visible.");
                return result;
            }

            cfObj.commonClick(liveClassPageObj.getCertificateClassCommingSoonBtn());
            Thread.sleep(1000);
            result = validateLiveClassNotStartedVideoScreenUi(driver);
            if (!result) {
                liveClassMsgList.add("Not able to validate LiveClassNotStartedVideoScreenUi.");
                return result;
            }

            result = cfObj.pressAndroidKey(driver, key.BACK, 2);
            if (!result) {
                liveClassMsgList.add("Not able to click back button.");
                return result;
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("validateCertificateLiveClassCommingSoonBtn_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean handleAudioPermissionBtn(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            if (cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getOnlyThisTimeBtn(), 10)) {
                cfObj.commonClick(liveClassPageObj.getOnlyThisTimeBtn());
            }

            if (cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getOkBtn(), 10)) {
                cfObj.commonClick(liveClassPageObj.getOkBtn());
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("handleAudioPermissionBtn_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean clickOnExitBtn(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getLiveClassExitBtn(), 10);
            if (!result) {
                liveClassMsgList.add("LiveClassExitBtn is not visible.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getLiveClassExitBtn());

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("clickOnExitBtn_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean verifyLCSInRecordedLiveClass(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        String packageTitle;
        String packageTitle1 = null;
        try {

            if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
                packageTitle = "New Live Class Packag";
            } else {
                packageTitle = "3:Live class package";
                packageTitle1 = "Live class package";
            }
            loginUtilObj = new LoginUtil(driver);

            result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), true);
            if (!result) {
                liveClassMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            homePageUtilObj = new HomePageUtil(driver);

            result = homePageUtilObj.clickStoreBtn(driver);
            if (!result) {
                liveClassMsgList.addAll(homePageUtilObj.homeMsgList);
                return result;
            }
            storePageUtilObj = new StorePageUtil(driver);

            if (ConfigFileReader.strApplication.equalsIgnoreCase("Sankalp")) {
                result = storePageUtilObj.clickVideoCoursesIcon(driver);
                if (!result) {
                    liveClassMsgList.addAll(storePageUtilObj.storePageMsgList);
                    return result;
                }
                result = cfObj.pressAndroidKey(driver, key.BACK, 1);
                if (!result) {
                    liveClassMsgList.add("Not able to click back button..");
                    return result;
                }
            }

            result = storePageUtilObj.purchasePackage(driver, packageTitle, ProductType.LIVE_CLASSES);
            if (!result) {
                liveClassMsgList.add("purchasePackage failed");
                return result;
            }

            orderSuccessUtilObj = new OrderSuccessUtil(driver);
            result = orderSuccessUtilObj.handleRateUsPopUpWindow(driver);
            if (!result) {
                liveClassMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
                return result;
            }
            result = orderSuccessUtilObj.verifyOrderSuccessPage(driver);
            if (!result) {
                liveClassMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
                return result;
            }

            result = cfObj.pressAndroidKey(driver, key.BACK, 3);
            if (!result) {
                liveClassMsgList.add("Not able to click back button 3 times.");
                return result;
            }

            result = homePageUtilObj.clickMyContentButton(driver);
            if (!result) {
                liveClassMsgList.addAll(homePageUtilObj.homeMsgList);
                return result;
            }

            myContentUtilObj = new MyContentUtil(driver);

            result = myContentUtilObj.clickOnPurchasedBtn(driver);
            if (!result) {
                liveClassMsgList.addAll(myContentUtilObj.myContentMsgList);
                return result;
            }
            if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
                result = myContentUtilObj.clickOnPackageInPurchasedSection(driver, packageTitle);
            } else {
                result = myContentUtilObj.clickOnPackageInPurchasedSection(driver, packageTitle1);
            }
            if (!result) {
                liveClassMsgList.addAll(myContentUtilObj.myContentMsgList);
                return result;
            }

            if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
                batchUtilObj = new BatchUtil(driver);
                result = batchUtilObj.clickOnDoneBtn(driver);
                if (!result) {
                    liveClassMsgList.addAll(batchUtilObj.batchMsgList);
                    return result;
                }

                result = batchUtilObj.clickOnSubjectTitle(driver);
                if (!result) {
                    liveClassMsgList.addAll(batchUtilObj.batchMsgList);
                    return result;
                }
            }

            result = handledNotificationPopUp(driver);
            if (!result) {
                System.out.println("Not able to handled NotificationPopUp.");
                return result;
            }

            result = clickOnGotItBtn(driver);
            if (!result) {
                System.out.println("Not able to click GotIt button.");
                return result;
            }

            result = cfObj.pressAndroidKey(driver, key.POWER, 2);
            if (!result) {
                liveClassMsgList.add("Not able to press power button 2 times.");
                return result;
            }

            result = clickOnPlayerViewStateBtn(driver);
            if (!result) {
                liveClassMsgList.add("Not able to click On PlayerViewStateBtn.");
                return result;
            }

            result = cfObj.pressAndroidKey(driver, key.POWER, 2);
            if (!result) {
                liveClassMsgList.add("Not able to press power button 2 times.");
                return result;
            }

            videoCoursesPageUtilObj = new VideoCoursesPageUtil(driver);
            result = videoCoursesPageUtilObj.validateLCSSection(driver);
            if (!result) {
                liveClassMsgList.addAll(videoCoursesPageUtilObj.videoSectionMsgList);
                return result;
            }

            result = videoCoursesPageUtilObj.validateVideoLikeBtn(driver);
            if (!result) {
                liveClassMsgList.addAll(videoCoursesPageUtilObj.videoSectionMsgList);
                return result;
            }

            result = videoCoursesPageUtilObj.validateVideoDisLikeBtn(driver);
            if (!result) {
                liveClassMsgList.addAll(videoCoursesPageUtilObj.videoSectionMsgList);
                return result;
            }

            result = videoCoursesPageUtilObj.validateVideoCommentTextBox(driver);
            if (!result) {
                liveClassMsgList.addAll(videoCoursesPageUtilObj.videoSectionMsgList);
                return result;
            }

            result = videoCoursesPageUtilObj.validateVideoPdfButton(driver);
            if (!result) {
                liveClassMsgList.addAll(videoCoursesPageUtilObj.videoSectionMsgList);
                return result;
            }

            result = videoCoursesPageUtilObj.validateVideoDownloadBtn(driver);
            if (!result) {
                liveClassMsgList.addAll(videoCoursesPageUtilObj.videoSectionMsgList);
                return result;
            }
            result = cfObj.pressAndroidKey(driver, key.BACK, 2);
            if (!result) {
                liveClassMsgList.add("Unable to press android back key 2 times");
                return result;
            }

            result = videoCoursesPageUtilObj.validateDownloadedVideo(driver);
            if (!result) {
                liveClassMsgList.addAll(videoCoursesPageUtilObj.videoSectionMsgList);
                return result;
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("verifyLCSFeatureInVideoCourse_Exception" + e.getMessage());

        }
        return result;
    }

    public boolean verifyStudentLiveScreenOnLiveClass(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        String productTitle;
        try {

            loginUtilObj = new LoginUtil(driver);
            result = loginUtilObj.selectYourCategoryExamLanguage(driver);
            if (!result) {
                liveClassMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }
            if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
                productTitle = "Automation Package Mahapack 909";
                result = loginUtilObj.verifyLoginUsingEmailId(driver, "addaAutomation2384882252@gmail.com", "1234567", false);
            } else {
                productTitle = "Automation Package Parent 988";
                result = loginUtilObj.verifyLoginUsingEmailId(driver, "addaAutomation2353096698@gmail.com", "1234567", false);
            }
            if (!result) {
                liveClassMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            homePageUtilObj = new HomePageUtil(driver);

            result = homePageUtilObj.clickMyContentButton(driver);
            if (!result) {
                liveClassMsgList.addAll(homePageUtilObj.homeMsgList);
                return result;
            }

            myContentUtilObj = new MyContentUtil(driver);

            result = myContentUtilObj.clickOnPurchasedBtn(driver);
            if (!result) {
                liveClassMsgList.addAll(myContentUtilObj.myContentMsgList);
                return result;
            }
            result = myContentUtilObj.clickOnPackageInPurchasedSection(driver, productTitle);
            if (!result) {
                liveClassMsgList.addAll(myContentUtilObj.myContentMsgList);
                return result;
            }
            result = handledNotificationPopUp(driver);
            if (!result) {
                liveClassMsgList.add("Not able to handled NotificationPopUp.");
                return result;
            }

            cfObj.handleHints(driver);

            result = clickOnJoinLiveBtn(driver);
            if (!result) {
                liveClassMsgList.add("Not able to click JoinLiveBtn.");
                return result;
            }

            result = cfObj.waitTillElementIsVisible(driver, 5, 1000,
                    cfObj.commonGetElement(driver, "loadingProgress", "id"));
            if (!result) {
                liveClassMsgList.add("Failed to load LiveClass Screen.");
                return result;
            }

            result = clickOnLiveClassScreenGotItBtn(driver);
            if (!result) {
                liveClassMsgList.add("Not able to click LiveClassScreenGotItBtn.");
                return result;
            }

            result = validateLiveClassStartedScreenUi(driver);
            if (!result) {
                liveClassMsgList.add("Not able to validate LiveClassNotStartedVideoScreenUi.");
                return result;
            }

            result = validateWindowResizeBtnInAppWeb(driver, webDriver);
            if (!result) {
                liveClassMsgList.add("Not able to validate WindowResizeBtn.");
                return result;
            }

            result = validateQualitySettingBtnInAppWeb(driver, ConfigurationMode.BASIC, webDriver);
            if (!result) {
                liveClassMsgList.add("Not able to validate QualitySettingBtnAfterClassStarted.");
                return result;
            }

            result = validateHandRaiseIcon(driver);
            if (!result) {
                liveClassMsgList.add("Not able to validate HandRaiseIcon.");
                return result;
            }

            result = validateChatWindowBtn(driver);
            if (!result) {
                liveClassMsgList.add("Not able to validate ChatWindowBtn.");
                return result;
            }

            result = enterMessageInMessageSendTextField(driver, false);
            if (!result) {
                liveClassMsgList.add("Not able to enter Message in MessageSendTextField.");
                return result;
            }
        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("StudentLiveScreenOnLiveClass_Exception" + e.getMessage());
        }

        return result;
    }

    public boolean validateHandRaiseIcon(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getHandRaiseIcon(), 10);
            if (!result) {
                liveClassMsgList.add("HandRaiseIcon is not visible.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getHandRaiseIcon());

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getHandRaiseBtn(), 10);
            if (!result) {
                liveClassMsgList.add("HandRaiseBtn is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getHandRaiseImage(), 10);
            if (!result) {
                liveClassMsgList.add("HandRaiseImage is not visible.");
                return result;
            }
            result = cfObj.commonVerifyValueTextBox(liveClassPageObj.getHandRaiseBtn(), "Raise Hand");
            if (!result) {
                liveClassMsgList.add("HandRaise button text is not matching.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getHandRaiseBtn());

            result = cfObj.waitForPageLoading(driver, 5, 1000,
                    cfObj.commonGetElement(driver, "//*[@text='Lower Hand']", "xpath"));
            if (!result) {
                liveClassMsgList.add("Not able to load page.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getStudentAvatar(), 10);
            if (!result) {
                liveClassMsgList.add("StudentAvatar is not visible.");
                return result;
            }
            result = cfObj.commonVerifyValueTextBox(liveClassPageObj.getHandRaiseBtn(), "Lower Hand");
            if (!result) {
                liveClassMsgList.add("HandRaise button text is not matching.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getHandRaiseBtn());
            result = cfObj.waitForPageLoading(driver, 5, 1000,
                    cfObj.commonGetElement(driver, "//*[@text='Raise Hand']", "xpath"));
            if (!result) {
                liveClassMsgList.add("Not able to load page.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getHandRaiseBtn(), 10);
            if (!result) {
                liveClassMsgList.add("HandRaiseBtn is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getHandRaiseImage(), 10);
            if (!result) {
                liveClassMsgList.add("HandRaiseImage is not visible.");
                return result;
            }
            result = cfObj.commonVerifyValueTextBox(liveClassPageObj.getHandRaiseBtn(), "Raise Hand");
            if (!result) {
                liveClassMsgList.add("HandRaise button text is not matching.");
                return result;
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("validateHandRaiseBtn_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean verifyFreeLiveClass(AppiumDriver<MobileElement> driver, String typeOfClass) {
        boolean result = true;
        String strTitleClass = "Automation Class:";
        String strTitlePackage = "Automation Package Parent 111187";
        Login login = new Login();
        UserApiUtil us = new UserApiUtil();
        OrderApiUtil orderApiObj = new OrderApiUtil();
        loginUtilObj = new LoginUtil(driver);
        try {
            result = createLiveClass(true, typeOfClass, "102112", "shubhbansal@gmail.com", "123456789");
            if (!result) {
                liveClassMsgList.add("Unable to create 1st free live class");
                return result;
            }

            String mobileNumber = Common_Function.randomPhoneNumber(10, "8");
            result = loginUtilObj.verifySignUp(driver, mobileNumber, true);
            if (!result) {
                liveClassMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            homePageUtilObj = new HomePageUtil(driver);
            result = homePageUtilObj.clickHomeBtn(driver);
            if (!result) {
                liveClassMsgList.addAll(homePageUtilObj.homeMsgList);
                return result;
            }

            result = verifyFreeLiveClassListOnHomepage(driver, strTitleClass);
            if (!result) {
                liveClassMsgList.add("Unable to verify Free Live Class outside section on homepage");
                return result;
            }

            result = verifyFreeLiveClassSection(driver);
            if (!result) {
                liveClassMsgList.add("Unable to verify Free Live Class inside section");
                return result;
            }
            cfObj.pressAndroidKey(driver, key.BACK, 1);

            result = verifyMockTestsBottomSheetAfterLiveClass(driver);
            if(!result){
                return result;
            }

            result = verifyFreeLiveClassOnPdp(driver, strTitlePackage);
            if (!result) {
                liveClassMsgList.add("Unable to verify Free Live Class on PDP");
                return result;
            }

            login = us.userLoginWithMobileNumber(mobileNumber);
            if (login.getData() == null) {
                liveClassMsgList.add("error in login with user");
                return false;
            }

            // Purchasing the course
            result = orderApiObj.createOrder(login.getData().getJwtToken(), "", 102113, mobileNumber);
            if (!result) {
                liveClassMsgList.add("error in creating order");
                return result;
            }
            Thread.sleep(3000);

            homePageUtilObj = new HomePageUtil(driver);
            result = homePageUtilObj.clickMyContentButton(driver);
            if (!result) {
                liveClassMsgList.addAll(homePageUtilObj.homeMsgList);
                return result;
            }

            result = homePageUtilObj.JustOpenAndClickHomeBtn(driver);
            if (!result) {
                liveClassMsgList.addAll(homePageUtilObj.homeMsgList);
                return result;
            }

            result = verifyFreeLiveclassSectionAfterPurchase(driver);
            if (!result) {
                liveClassMsgList.add("Failed verifyFreeLiveClassSectionAfterPurchase");
                return result;
            }

            // change category to ssc
            result = verifyChangeInSscCategory(driver);
            if (!result) {
                liveClassMsgList.add("Failed verifyChangeInSscCategory");
                return result;
            }

            result = verifyFreeLiveClassNewDesignOnFeed(driver);
            if (!result) {
                liveClassMsgList.add("Failed verifyFreeLiveClassNewDesignOnFeed");
                return result;
            }
        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("verifyFreeLiveClass_Exception" + e.getMessage());
        } finally {
            LiveClassApiUtil liveClassApiUtil = new LiveClassApiUtil();
            liveClassApiUtil.endAddaLiveClass(externalScheduleId);

            if (webDriver != null) {
                webDriver.quit();
            }
        }
        return result;
    }

    public boolean verifyFreeLiveClassNewDesignOnFeed(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        String strTitleClass = "Automation Class:";
        String strTitlePackage = "Automation Package Parent ";
        Login login = new Login();
        UserApiUtil us = new UserApiUtil();
        OrderApiUtil orderApiObj = new OrderApiUtil();

        try {

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getWatchForFreeBtn(), 10);
            if (!result) {
                liveClassMsgList.add("Watch for free button is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getMoreFreeClassBtn(), 10);
            if (!result) {
                liveClassMsgList.add("More free classes button is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"Free Live Class\"]", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Free live class icon is not visible on banner");
                return result;
            }

            cfObj.commonClick(liveClassPageObj.getWatchForFreeBtn());

            // Click on Continue button if visible
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View/android.widget.Button",
                    "xpath", 5);
            if (result) {
                cfObj.commonClick(cfObj.commonGetElement(driver,
                        "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View/android.widget.Button",
                        "xpath"));
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/buyNowButton", "id", 10);
            if (result) {
                liveClassMsgList.add("Buy Now button is visible, but this package is purchased.");
                return false;
            }

            driver.navigate().back();

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.Button", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Exit btn is not visible, when click back in live class");
                return result;
            }

            cfObj.commonClick(cfObj.commonGetElement(driver, "//android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.Button", "xpath"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/tv_skip", "id", 10);
            if (!result) {
                liveClassMsgList.add("Skip btn is not visible, when click back in live class");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/tv_skip", "id"));

            result = verifyMockTestsBottomSheetAfterLiveClass(driver);
            if(!result){
                return result;
            }

            HomePageUtil homePageUtil = new HomePageUtil(driver);
            result = cfObj.commonWaitForElementToBeVisible(driver, homePageUtil.homePageObj.getBtnHome(), 10);
            if (!result) {
                liveClassMsgList.add("Home btn is not visible, after coming from live class listing");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getMoreFreeClassBtn(), 10);
            if (!result) {
                liveClassMsgList.add("More free classes button is not visible");
                return result;
            }

            cfObj.commonClick(liveClassPageObj.getMoreFreeClassBtn());

            result = validateFreeLiveClassPage(driver, false);
            if (!result) {
                liveClassMsgList.add("validateFreeLiveClassPage failed after going from new design");
                return result;
            }

            // Create 2nd free live class and check the banner design of remind and starting text.
        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("verifyFreeLiveClassNewDesignOnFeed_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean verifyChangeInSscCategory(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            HomePageUtil homePageUtil = new HomePageUtil(driver);
            result = cfObj.commonWaitForElementToBeVisible(driver, homePageUtil.homePageObj.getChangeExamContainer(), 10);
            if (!result) {
                liveClassMsgList.add("Change exam container is not visible");
                return result;
            }

            cfObj.commonClick(homePageUtil.homePageObj.getChangeExamContainer());

            loginUtilObj = new LoginUtil(driver);
            result = loginUtilObj.navigateToChooseExamCategoryScreen(driver);
            if (!result) {
                liveClassMsgList.addAll(loginUtilObj.loginMsgList);
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"exam_list_category\n" +
                    "SSC Exam\n" +
                    "Banking\"]", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("SSC Exam is not visible");
                return result;
            }

            cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.ImageView[@content-desc=\"exam_list_category\n" +
                    "SSC Exam\n" +
                    "Banking\"]", "xpath"));

            result = cfObj.commonWaitForElementToBeVisible(driver, homePageUtil.homePageObj.getBtnHome(), 10);
            if (!result) {
                liveClassMsgList.add("Home btn is not visible, after coming from pdp");
                return result;
            }

            cfObj.commonClick(homePageUtil.homePageObj.getBtnHome());

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("verifyChangeInCategory_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean verifyFreeLiveClassListOnHomepage(AppiumDriver<MobileElement> driver, String strTitleClass) {
        boolean result;
        homePageUtilObj = new HomePageUtil(driver);
        String appPackageName = null;
        try {
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.TextView[@text='More Free Classes']", "xpath", 10);
            if (!result) {

                try {
                    appPackageName = ((AndroidDriver<MobileElement>) driver).getCurrentPackage();
                    driver.terminateApp(appPackageName);
                    Thread.sleep(500);
                    driver.activateApp(appPackageName);
                } catch (Exception e) {
                    liveClassMsgList.add("Error restarting app: " + e.getMessage());
                    return false;
                }

                result = homePageUtilObj.JustOpenAndClickHomeBtn(driver);
                if(!result){
                    liveClassMsgList.addAll(homePageUtilObj.homeMsgList);
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.TextView[@text='More Free Classes']", "xpath", 15);
                if (!result) {
                    liveClassMsgList.add("Free Live class is not available on feed, might be page not refreshed.");
                    return result;
                }
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.TextView[@text='Live Now']", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Unable to find any schedule with LIVE NOW on Feed");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.TextView[@text='Watch for free']", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Unable to find any schedule with Watch for free btn on Feed");
                return result;
            }

            cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.TextView[@text='Watch for free']", "xpath"));

            Thread.sleep(3000);

            // Click on Continue button if visible
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View/android.widget.Button",
                    "xpath", 5);
            if (result) {
                cfObj.commonClick(cfObj.commonGetElement(driver,
                        "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View/android.widget.Button",
                        "xpath"));
            }

            // Click on Permission Allow button if visible
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "com.android.permissioncontroller:id/permission_allow_button",
                    "id", 5);
            if (result) {
                cfObj.commonClick(cfObj.commonGetElement(driver,
                        "com.android.permissioncontroller:id/permission_allow_button",
                        "id"));
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/buyNowButton", "id", 20);
            if (result) {
                cfObj.commonGetElement(driver, "com.adda247.app:id/buyNowButton", "id").click();
            } else {
                liveClassMsgList.add("Buy now button is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getClassTitlePdp(), 10);
            if (!result) {
                liveClassMsgList.add("The Class and faculty name is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getJoinNowPdp(), 10);
            if (!result) {
                liveClassMsgList.add("Join Now is not visible on pdp");
                return result;
            }

            cfObj.pressAndroidKey(driver, key.BACK, 2);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.TextView[@text='EXIT']", "xpath", 30);
            if (!result) {
                liveClassMsgList.add("Exit btn is not visible, when click back in live class");
                return result;
            }

            cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.TextView[@text='EXIT']", "xpath"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/tv_skip", "id", 30);
            if (!result) {
                liveClassMsgList.add("skip btn is not visible, when exit live class");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/tv_skip", "id"));

            result = verifyMockTestsBottomSheetAfterLiveClass(driver);
            if(!result){
                return result;
            }
        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("verifyFreeLiveClassListOnHomepage_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean verifyFreeLiveclassSectionAfterPurchase(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getFreeLiveClassTab(), 10);
            if (!result) {
                liveClassMsgList.add("free Live class section button is not visible");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getFreeLiveClassTab());

            result = validateFreeLiveClassPage(driver, false);
            if (!result) {
                liveClassMsgList.add("validateFreeLiveClassPage failed");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "join_live", "id", 10);
            if (!result) {
                liveClassMsgList.add("Join live button is not visible");
                return result;
            }

            cfObj.commonClick(cfObj.commonGetElement(driver, "join_live", "id"));

            // Click on Continue button if visible
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View/android.widget.Button",
                    "xpath", 5);
            if (result) {
                cfObj.commonClick(cfObj.commonGetElement(driver,
                        "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View/android.widget.Button",
                        "xpath"));
            }

            // Click on Permission Allow button if visible
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "com.android.permissioncontroller:id/permission_allow_button",
                    "id", 5);
            if (result) {
                cfObj.commonClick(cfObj.commonGetElement(driver,
                        "com.android.permissioncontroller:id/permission_allow_button",
                        "id"));
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/freeLiveClassesTitle", "id", 10);
            if (!result) {
                liveClassMsgList.add("The package name is not visible inside live class");
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/buyNowButton", "id", 10);
            if (result) {
                liveClassMsgList.add("Buy now button is visible, after purchasing");
                return false;
            }

            driver.navigate().back();

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.TextView[@text='EXIT']", "xpath", 30);
            if (!result) {
                liveClassMsgList.add("Exit btn is not visible, when click back in live class");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.TextView[@text='EXIT']", "xpath"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/tv_skip", "id", 30);
            if (!result) {
                liveClassMsgList.add("skip btn in rating is not visible, when exit live class");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/tv_skip", "id"));

            result = verifyMockTestsBottomSheetAfterLiveClass(driver);
            if(!result){
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getBtnCalender(), 10);
            if (!result) {
                liveClassMsgList.add("The calendar btn is not visible");
                return result;
            }

            driver.navigate().back();

            HomePageUtil homePageUtil = new HomePageUtil(driver);
            result = cfObj.commonWaitForElementToBeVisible(driver, homePageUtil.homePageObj.getBtnHome(), 10);
            if (!result) {
                liveClassMsgList.add("Home btn is not visible, after coming from free live class section");
                return result;
            }
        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("verifyFreeLiveclassSectionAfterPurchase_Exception" + e.getMessage());
        }
        return result;
    }


    public boolean verifyFreeLiveClassSection(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        boolean exploreOneTime = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getFreeLiveClassTab(), 10);
            if (!result) {
                liveClassMsgList.add("Free Live class section button is not visible");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getFreeLiveClassTab());

            result = validateFreeLiveClassPage(driver, exploreOneTime);
            if (!result) {
                liveClassMsgList.add("validateFreeLiveClassPage failed");
                return result;
            }

            result = cfObj.updateListSize(liveClassPageObj.getVideoCourseTitleList());
            if (!result) {
                liveClassMsgList.add("Free Live class is not available inside section");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "join_live", "id", 10);
            if (!result) {
                liveClassMsgList.add("Join Live button is not available");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/flcShareBtn", "id", 10);
            if (!result) {
                liveClassMsgList.add("Share btn with Join Now btn is not visible");
            }

            // Click on JOIN LIVE button
            cfObj.commonClick(cfObj.commonGetElement(driver, "join_live", "id"));

            // Click on Continue button if visible
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View/android.widget.Button",
                    "xpath", 5);
            if (result) {
                cfObj.commonClick(cfObj.commonGetElement(driver,
                        "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View/android.widget.Button",
                        "xpath"));
            }

            // Click on Permission Allow button if visible
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
                    "com.android.permissioncontroller:id/permission_allow_button",
                    "id", 5);
            if (result) {
                cfObj.commonClick(cfObj.commonGetElement(driver,
                        "com.android.permissioncontroller:id/permission_allow_button",
                        "id"));
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/freeLiveClassesTitle", "id", 20);
            if (!result) {
                liveClassMsgList.add("The package name is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/buyNowButton", "id", 20);
            if (result) {
                cfObj.commonGetElement(driver, "com.adda247.app:id/buyNowButton", "id").click();
            } else {
                liveClassMsgList.add("Buy now button is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getClassTitlePdp(), 10);
            if (!result) {
                liveClassMsgList.add("getClassTitlePdp is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getJoinNowPdp(), 10);
            if (!result) {
                liveClassMsgList.add("Join Now is not visible on pdp");
                return result;
            }

            cfObj.pressAndroidKey(driver, key.BACK, 2);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.TextView[@text='EXIT']", "xpath", 20);
            if (!result) {
                liveClassMsgList.add("Exit btn is not visible, when click back in live class");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.TextView[@text='EXIT']", "xpath"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/tv_skip", "id", 20);
            if (!result) {
                liveClassMsgList.add("skip btn is not visible, when exit live class");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/tv_skip", "id"));

            // Open calendar
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getBtnCalender(), 15);
            if (!result) {
                liveClassMsgList.add("The calendar btn is not visible");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getBtnCalender());

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "android:id/datePicker", "id", 10);
            if (!result) {
                liveClassMsgList.add("Calendar section doesn't open");
                return result;
            }

            // Clicking OK button
            cfObj.commonClick(cfObj.commonGetElement(driver, "android:id/button1", "id"));

            Thread.sleep(1000);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "android:id/datePicker", "id", 3);
            if (result) {
                liveClassMsgList.add("Calendar doesn't closed on clicking ok");
                return false;
            }

            // Open calendar again
            cfObj.commonClick(liveClassPageObj.getBtnCalender());

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "android:id/datePicker", "id", 10);
            if (!result) {
                liveClassMsgList.add("Calendar section doesn't open");
                return result;
            }

            // Clicking Cancel button
            cfObj.commonClick(cfObj.commonGetElement(driver, "android:id/button2", "id"));

            Thread.sleep(1000);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "android:id/datePicker", "id", 3);
            if (result) {
                liveClassMsgList.add("Calendar doesn't closed on clicking Cancel");
                return result;
            } else {
                result = true;
            }
        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("verifyFreeLiveClassSection_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean validateFreeLiveClassPage(AppiumDriver<MobileElement> driver, boolean exploreOneTime) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/heading", "id", 15);
            if (!result) {
                liveClassMsgList.add("Heading is not visible on free live class page");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/calenderRecyclerView",
                    "id", 10);
            if (!result) {
                liveClassMsgList.add("Dates are not visible on free live class page");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/date_time", "id", 10);
            if (!result) {
                liveClassMsgList.add("Date and time are not visible on free live class page");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/top_lay", "id", 10);
            if (!result) {
                liveClassMsgList.add("Video screen is not visible on free live class page");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/backBtn", "id", 10);
            if (!result) {
                liveClassMsgList.add("Back btn is not visible on free live class page");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getBtnCalender(), 10);
            if (!result) {
                liveClassMsgList.add("The calendar btn is not visible on free live class page");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/want_more_free_view", "id", 10);
            if (!result) {
                liveClassMsgList.add("want_more_free_view banner is not visible on free live class page");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/more_free_icon", "id", 10);
            if (!result) {
                liveClassMsgList.add("banner icon is not visible on free live class page");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/subtitle", "id", 10);
            if (!result) {
                liveClassMsgList.add("Boost your preparation. Watch more, learn more! text is not visible on free live class page");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/more_heading", "id", 10);
            if (!result) {
                liveClassMsgList.add("Explore More Free Videos text is not visible on free live class page");
                return result;
            }

            if(exploreOneTime){
                cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/want_more_free_view", "id"));

                if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"), 8)) {
                    cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"));
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.TextView[contains(@text,'Videos')]", "xpath", 10);
                if (!result) {
                    liveClassMsgList.add("Videos section is not visible after clicking on Explore more free videos btn from free liveClass.");
                    return result;
                }
                driver.navigate().back();

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/heading", "id", 15);
                if (!result) {
                    liveClassMsgList.add("Heading is not visible on free live class page after coming from video section.");
                    return result;
                }
            }
        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("validateLiveClassPages_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean verifyFreeLiveClassOnPdp(AppiumDriver<MobileElement> driver, String strPackageTitle) {
        boolean result = true;
        try {
            homePageUtilObj = new HomePageUtil(driver);
            result = homePageUtilObj.clickStoreBtn(driver);
            if (!result) {
                liveClassMsgList.addAll(homePageUtilObj.homeMsgList);
                return result;
            }

            storePageUtilObj = new StorePageUtil(driver);
            result = storePageUtilObj.clickSearchIcon(driver);
            if (!result) {
                liveClassMsgList.addAll(storePageUtilObj.storePageMsgList);
                return result;
            }

            result = storePageUtilObj.enterPackageNameInSearchField(driver, strPackageTitle);
            if (!result) {
                liveClassMsgList.add("Unable to enter package name in store search");
                return result;
            }

            cfObj.commonClick(cfObj.commonGetElements(driver, "//*[contains(@content-desc,\"packageItem\")]", "xpath").get(1));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@content-desc=\"BUY NOW\"]", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("The package is not opened");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getClassTitlePdp(), 10);
            if (!result) {
                liveClassMsgList.add("getClassTitlePdp is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getJoinNowPdp(), 10);
            if (!result) {
                liveClassMsgList.add("Join Now is not visible on pdp");
                return result;
            }

            cfObj.smallScrollUtillNtimes(driver, 3, direction.DOWN);

            // Verify FreeLiveClass Preview Section:
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,\"PREVIEW\")]", "xpath", 10);
            if (!result) {

                cfObj.smallScrollUtillNtimes(driver, 2, direction.UP);

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,\"PREVIEW\")]", "xpath", 10);
                if (!result) {
                    liveClassMsgList.add("Explore our free course content text is not visible on preview section");
                    return result;
                }
            }

            cfObj.commonClick(cfObj.commonGetElement(driver, "//*[contains(@content-desc,\"PREVIEW\")]", "xpath"));

            result = validateFreeLiveClassPage(driver, false);
            if (!result) {
                liveClassMsgList.add("validateFreeLiveClassPage failed");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "join_live", "id", 10);
            if (!result) {
                liveClassMsgList.add("Join Live button is not available, in the free live class page");
                return result;
            }

            if (!ConfigFileReader.strRunMode.equalsIgnoreCase("localLab")) {
                driver.navigate().back();
            }
            driver.navigate().back();
            driver.navigate().back();
            driver.navigate().back();

            HomePageUtil homePageUtil = new HomePageUtil(driver);
            result = cfObj.commonWaitForElementToBeVisible(driver, homePageUtil.homePageObj.getBtnHome(), 10);
            if (!result) {
                liveClassMsgList.add("Home btn is not visible, after coming from pdp");
                return result;
            }
        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("verifyFreeLiveClassOnPdp_Exception " + e.getMessage());
        }
        return result;
    }

    public boolean createLiveClass(boolean isFreeLiveClass, String typeOfClass, String strPackageId, String facultyMailId, String facultyPassword) {
        boolean result = true;
        int intFacultyId;
        String liveClassUrl, liveClassUrlStudent;
        int noOfSchedule;
        List<String> listOfCategories = new ArrayList<>();
        List<String> listOfPackages = new ArrayList<>();

        try {
            configFileReaderObj = new ConfigFileReader();
            liveClassUrl = "https://staging-live1.adda247.com";
            liveClassUrlStudent = "https://staging.adda247.com";

            UserApiUtil us = new UserApiUtil();
            AdminLogin adminLogin = us.adminLogin(facultyMailId, facultyPassword);
            String strToken = adminLogin.getFacultyToken();
            intFacultyId = adminLogin.getFacultyId();
            LiveClassApiUtil lp = new LiveClassApiUtil();

            result = lp.deleteAllTeacherSchedule(strToken, String.valueOf(intFacultyId));
            if (!result) {
                liveClassMsgList.add("not able to delete teacher schedule");
                return false;
            }

            CreateScheduleResponse createScheduleResponse = lp.createSchedule(strToken, strPackageId, false,
                    intFacultyId);
            if (createScheduleResponse == null) {
                liveClassMsgList.add("error in create schedule");
                return false;
            }

            if (!createScheduleResponse.getSuccess()) {
                liveClassMsgList.add("success is false when creating an live class schedule");
                return false;
            }

            if (!createScheduleResponse.getData().getOverlapped().isEmpty()) {
                liveClassMsgList.add("Overlapped in schedule, error in creating schedule");
                return false;

            } else {

                String id = createScheduleResponse.getData().getCreated().get(0).getId();
                System.out.println("id: " + id);

                MapExternalScheduleIdUtil mexId = new MapExternalScheduleIdUtil();
                MapExternalIdResponse mapExternalResponse = mexId.mapExternalScheduleId(id);
                if (mapExternalResponse.getData() == null) {
                    liveClassMsgList.add("error in mapping the schedule id, data is null");
                    return false;
                }

                Thread.sleep(2000);

                liveClassPackageResponse lcPackageResponse = lp.getListOfLiveClassPackage(strPackageId);
                if (lcPackageResponse.getData() == null) {
                    liveClassMsgList.add("error in getting the data, as it is null");
                    return false;
                }

                externalScheduleId = lcPackageResponse.getData().getData().get(0).getExternalScheduleId();

                result = lp.updateSchedule(strToken, strPackageId,
                        Integer.parseInt(createScheduleResponse.getData().getCreated().get(0).getId()), intFacultyId,
                        id);
                if (!result) {
                    liveClassMsgList.add("error in updating the schedule");
                    return false;
                }

                if (typeOfClass.equalsIgnoreCase("ssc")) {
                    strTeacherUrl = liveClassUrl + "/ssc?id=" + intFacultyId + "&name=ApiAutomation%20QA&vc="
                            + externalScheduleId + "&token=" + strToken;
                } else {
                    strTeacherUrl = liveClassUrl + "/faculty-student?id=" + intFacultyId + "&name=ApiAutomation%20QA&vc="
                            + externalScheduleId + "&token=" + strToken;
                }
                strStudentUrl = liveClassUrlStudent + "/adda-live-class/?vc=" + externalScheduleId + "&token="
                        + strToken + "&admin=true&userId=8";

                System.out.println("Teacher Link : " + strTeacherUrl);
                System.out.println("Student Link : " + strStudentUrl);
            }

            if (isFreeLiveClass) {
                // Checking if the schedule is overlapping
                if (createScheduleResponse.getMessage() == null) {
                    // Mark it free
                    noOfSchedule = 1;
                    for (int i = 0; i < noOfSchedule; i++) {
                        // adding categories where schedule will be marked free
                        listOfCategories.add("BANKING");
                        listOfCategories.add("SSC");

                        // adding packages where schedule will be marked free
                        listOfPackages.add(strPackageId);

                        FLCUtil flcObj = new FLCUtil();
                        UserApiUtil userApiUtilObj = new UserApiUtil();

                        int id = Integer.parseInt(createScheduleResponse.getData().getCreated().get(i).getId());
                        result = flcObj.createFLC(
                                userApiUtilObj.adminLogin(facultyMailId, facultyPassword).getFacultyToken(), id,
                                listOfCategories, listOfPackages);
                        if (!result) {
                            liveClassMsgList.add("Create FLC failed");
                            return result;
                        }
                    }
                }
            }
        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("createLiveClass_Exception: " + e.getMessage());
        }
        return result;
    }

    public enum ConfigurationMode {
        BASIC, STANDARD;
    }

    public boolean validateLiveClassFeedbackScreenOnAndroid(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/tv_report", "id", 5);
            if (result) {
                cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/crossBtn", "id"));
            }

            Thread.sleep(2000);

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getLiveClassEndTitle(), 10);
            if (!result) {
                liveClassMsgList.add("LiveClass End title is not visible in feedback");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/tv_skip", "id", 10);
            if (!result) {
                liveClassMsgList.add("Skip btn is not visible in feedback");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/tv_improve", "id", 10);
            if (!result) {
                liveClassMsgList.add("Help us improve text is not visible in feedback");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getTeacherRatingIcon(), 10);
            if (!result) {
                liveClassMsgList.add("Teacher rating icon is not visible.");
                return result;
            }

            cfObj.commonClick(liveClassPageObj.getTeacherRatingIcon());

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'Is the topic taught in class the same as in the study plan?')]", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Question not visible when give 3 or less than 3 stars faculty rating");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/radioGroup", "id", 10);
            if (!result) {
                liveClassMsgList.add("Radio icons of question not visible when give 3 or less than 3 stars faculty rating");
                return result;
            }

            if (driver.getOrientation().equals(ScreenOrientation.LANDSCAPE)) {

                cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/tv_skip", "id"));

                System.out.println("As it is in landscape mode, skip feedback");

                result = verifyMockTestsBottomSheetAfterLiveClass(driver);
                if(!result){
                    return result;
                }
                return true;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getContentRatingIcon(), 10);
            if (!result) {
                liveClassMsgList.add("Content rating icon is not visible.");
                return result;
            }

            cfObj.commonClick(liveClassPageObj.getContentRatingIcon());

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getInterfaceRatingIcon(), 10);
            if (!result) {
                liveClassMsgList.add("InterFace rating icon is not visible.");
                return result;
            }

            cfObj.commonClick(liveClassPageObj.getInterfaceRatingIcon());

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getLiveClassFeedBackTextField(),
                    10);
            if (!result) {
                liveClassMsgList.add("LiveClassFeedBackTextField is not visible.");
                return result;
            }

            result = cfObj.commonSetTextTextBox(liveClassPageObj.getLiveClassFeedBackTextField(),
                    "LiveClass FeedBack Test");
            if (!result) {
                liveClassMsgList.add("Failed to enter feedback on LiveClassFeedBackTextField.");
                return result;
            }

            cfObj.commonClick(liveClassPageObj.getFeedBackSubmitBtn());

            Thread.sleep(1000);

            result = !cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getLiveClassEndTitle(), 3);
            if (!result) {
                liveClassMsgList.add("Failed to click Feedback Submit button.");
                return result;
            }
        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("validateLiveClassFeedbackScreenOnAndroid_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean validateAskDoubtInAppWeb(AppiumDriver<MobileElement> driver, WebDriver webDriver) {
        boolean result = true;
        String teacherMsg = "Automated message from Teacher";
        try {

            webDriver.switchTo().window(strParentWindow);

            result = cfObj.commonWaitForElementToBeVisible(webDriver, webViewLiveClassUtilObj.getTeacherdoubtIcon(), 10);
            if (!result) {
                liveClassMsgList.add("doubt icon not display at teach screen");
                return result;
            }

            // verify Ask doubt count it should zero
            if (!webViewLiveClassUtilObj.getDoubtCount().get(0).getText().equalsIgnoreCase("0")) {
                liveClassMsgList.add("live doubt count should be zero when users did not ask any question");
                return false;
            }
            webDriver.switchTo().window(strChildWindow);

            Thread.sleep(1000);

            // check ask doubt button should be visible at student side
            if (webViewLiveClassUtilObj.getAskDoubt().isEmpty()) {
                liveClassMsgList.add("ask doubt icon not display at student screen");
                return false;
            }

            // click on ask doubt icon
            cfObj.commonClick(webViewLiveClassUtilObj.getAskDoubt().get(0));

            // wait for ask doubt section to be open
            if (webViewLiveClassUtilObj.getDoubtEditBox().isEmpty()) {
                liveClassMsgList.add("ask doubt side window not opened when click on ask doubt menu at student end");
                return false;
            }

            // enter doubt
            cfObj.commonSetTextTextBox(webViewLiveClassUtilObj.getDoubtEditBox().get(0), "Student Doubt");

            // click on enter button
            cfObj.commonClick_JS(webDriver, webViewLiveClassUtilObj.getSendButon().get(1));
            Thread.sleep(3000);

            // verify doubt should be display on window
            if (webViewLiveClassUtilObj.getChatMsg().isEmpty()) {
                liveClassMsgList.add("student not able to ask doubt");
                return false;
            }
            if (!webViewLiveClassUtilObj.getChatMsg().get(webViewLiveClassUtilObj.getChatMsg().size() - 1).getText().equalsIgnoreCase("Student Doubt")) {
                liveClassMsgList.add("mismatch in ask doubt what user send and what display in chat window");
                return false;
            }

            // switch to teacher window
            webDriver.switchTo().window(strParentWindow);

            Thread.sleep(1000);

            // verify Ask doubt count it should one
            // click on ask doubt icon
            cfObj.commonClick_JS(webDriver, webViewLiveClassUtilObj.getTeacherdoubtIcon());

            // verify doubt should be display on window
            if (webViewLiveClassUtilObj.getChatMsg().isEmpty()) {
                liveClassMsgList.add("teacher did not receive the doubt when student ask the doubt");
                return false;
            }
            if (!webViewLiveClassUtilObj.getChatMsg().get(webViewLiveClassUtilObj.getChatMsg().size() - 1).getText().equalsIgnoreCase("Student Doubt")) {
                liveClassMsgList.add("mismatch in ask doubt what user send and what display at teacher chat window");
                return false;
            }

            Thread.sleep(1000);

            // enter teacher reply
            cfObj.commonSetTextTextBox(cfObj.commonGetElements(webDriver, "//input[@placeholder='Send Message']", "xpath").get(0), "Teacher Reply doubt");

            // click on enter button
            cfObj.commonClick_JS(webDriver, webViewLiveClassUtilObj.getSendButon().get(1));
            Thread.sleep(3000);

            // close ask doubt window
            cfObj.commonClick_JS(webDriver, webViewLiveClassUtilObj.getTeacherdoubtIcon());

            // wait that ask doubt window should be close
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, ".switches-btn", "css", 10);
            if (!result) {
                liveClassMsgList.add("ask doubt window is not closed when close ask doubt button");
                return result;
            }
            webDriver.switchTo().window(strChildWindow);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//span[contains(text(),'Teacher Reply doubt')]", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("The reply from teacher is not visible on student side");
                return result;
            }

            Thread.sleep(2000);

            // Verify Ask doubt on android
            cfObj.commonClick(liveClassPageObj.getLiveClassDoubtBtn());

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getMessageSendBtn(), 10);
            if (!result) {
                liveClassMsgList.add("MessageSendBtn is not visible.");
                return result;
            }

            cfObj.commonClick(liveClassPageObj.getMessageSendTextField());

            result = cfObj.enterText(driver, "hiandroidstudent");
            if (!result) {
                liveClassMsgList.add("Not able to enter message in messageSendTextField.");
                return result;
            }

            postingPageUtilObj = new PostingPageUtil(driver);
            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getMessageSendBtn(), 10);
            if (!result) {
                liveClassMsgList.add("MessageSendBtn is not visible.");
                return result;
            }
            cfObj.commonClick(liveClassPageObj.getMessageSendBtn());

            Thread.sleep(2000);

            webDriver.switchTo().window(strParentWindow);

            cfObj.commonClick_JS(webDriver, webViewLiveClassUtilObj.getTeacherdoubtIcon());

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getChatMessageText().get(0), 10);
            if (!result) {
                liveClassMsgList.add("ChatMessageText is not visible at teacher chat");
                return result;
            }

            result = cfObj.commonVerifyValueTextBox(liveClassPageObj.getChatMessageText().get(liveClassPageObj.getChatMessageText().size() - 1), "hiandroidstudent");
            if (!result) {
                liveClassMsgList.add("ChatMessageText is not matching at teacher chat");
                return result;
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("validateAskDoubtInAppWeb Exception" + e.getMessage());
        }
        return result;
    }

    public boolean validatePollResponseLeaderboardPage(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {

            Thread.sleep(10000);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "tv_report", "id", 15);
            if (result) {

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "crossBtn", "id", 10);
                if (!result) {
                    liveClassMsgList.add("Poll Response page close button is not visible.");
                    return result;
                }
                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "progressPercentTV", "id", 10);
                if (!result) {
                    liveClassMsgList.add("Progress Percentage textvalue is not visible.");
                    return result;
                }
                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "progressBar", "id", 10);
                if (!result) {
                    liveClassMsgList.add("Progress bar is not visible.");
                    return result;
                }
                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "optionTV", "id", 10);
                if (!result) {
                    liveClassMsgList.add("Poll option text value is not visible.");
                    return result;
                }
                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "leaderBoardTitle", "id", 10);
                if (!result) {
                    liveClassMsgList.add("LeaderBoard title is not visible.");
                    return result;
                }
                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "userName", "id", 10);
                if (!result) {
                    liveClassMsgList.add("User name is not visible.");
                    return result;
                }
                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "trophyIv", "id", 10);
                if (!result) {
                    liveClassMsgList.add("Leader board trophy icon is not visible.");
                    return result;
                }
                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "userRank", "id", 10);
                if (!result) {
                    liveClassMsgList.add("User Rank is not visible.");
                    return result;
                }

                cfObj.commonClick(cfObj.commonGetElement(driver, "crossBtn", "id"));
                Thread.sleep(1000);

                result = !cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "tv_report", "id", 4);
                if (!result) {
                    liveClassMsgList.add("Failed to close poll response.");
                    return result;
                }
            } else {
                liveClassMsgList.add("Poll Response leaderboard page is not visible.");
                result = true;
            }
        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("validatePollResponseLeaderboardPage_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean attemptPoll(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "quizRoot", "id", 30);
            if (!result) {
                liveClassMsgList.add("Poll Quiz screen is not visible.");
                return result;
            }
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "quizTitle", "id", 10);
            if (!result) {
                liveClassMsgList.add("Quiz title is not visible.");
                return result;
            }
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "dummyRadioButton", "id", 10);
            if (!result) {
                liveClassMsgList.add("Quiz option is not visible.");
                return result;
            }

            Thread.sleep(1000);

            cfObj.commonClick(cfObj.commonGetElements(driver, "dummyRadioButton", "id").get(0));

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("attemptPoll_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean validateHandOutInAppWeb(AppiumDriver<MobileElement> driver, WebDriver webDriver) {
        boolean result = true;
        try {
            webDriver.switchTo().window(strParentWindow);

            // Click on handout button
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//div[@id='ele_stream_handout']", "xpath", 10);
            if (result) {
                cfObj.commonClick(cfObj.commonGetElement(webDriver, "//div[@id='ele_stream_handout']", "xpath"));
            } else {
                liveClassMsgList.add("Handout button is not visible");
                return result;
            }

            Thread.sleep(500);

            // Validate Handout window open
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//div[@class='ant-upload ant-upload-drag']", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("The upload handout pop not visible");
                return result;
            }

            result = cfObj.commonFileUploadWithEdgeCases("src/main/resources/small.pdf", cfObj.commonGetElement(webDriver, "//input[@accept='.pdf,.jpg, .jpeg,.png,.docx,.doc']", "xpath"));
            if (!result) {
                liveClassMsgList.add("Not able to UpLoad HandOut.");
                return result;
            }
            Thread.sleep(2000);

            result = cfObj.commonWaitForElementToBeVisible(webDriver, webViewLiveClassUtilObj.getHandOutCommentField(), 20);
            if (!result) {
                liveClassMsgList.add("Comment box in handout popup is not visible.");
                return result;
            }

            webViewLiveClassUtilObj.getHandOutCommentField().click();
            webViewLiveClassUtilObj.getHandOutCommentField().sendKeys("Handout Sample");

//			// Write Handout title in Comment
//			result = cfObj.commonSetTextTextBox(webViewLiveClassUtilObj.getHandOutCommentField(), "Handout Sample");
//			if (!result) {
//				liveClassMsgList.add("Not able to enter HandOut Comment.");
//				return result;
//			}

            // Click on Send button
            cfObj.commonClick(webViewLiveClassUtilObj.getHandOutSendBtn());

            Thread.sleep(2000);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, ".anticon-eye", "css", 15);
            if (!result) {
                result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//div[@id='ele_stream_chatstatus']", "xpath", 20);
                if (!result) {
                    liveClassMsgList.add("chatbox btn is not visible");
                    return result;
                }

                cfObj.commonClick(cfObj.commonGetElement(webDriver, "//div[@id='ele_stream_chatstatus']", "xpath"));

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, ".anticon-eye", "css", 20);
                if (!result) {
                    liveClassMsgList.add("Not able to Send handout, as not visible in chatbox");
                    return result;
                }
            }

            // Validate handout title in chat
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//span[text()='Handout Sample']", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("The handout title is not visible at teacher chat");
                return result;
            }

            // Validate Handout pdf
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, ".chat-upload-widget", "css", 10);
            if (!result) {
                liveClassMsgList.add("Handout pdf is not visible at teacher");
                return result;
            }

            webDriver.switchTo().window(strChildWindow);
            Thread.sleep(500);

            // Open Handout section in student window
            cfObj.commonClick(webViewLiveClassUtilObj.getBtnHandoutSection());

            // Validate handout title in Handout Section
            result = webViewLiveClassUtilObj.getListHandoutTitle().get(webViewLiveClassUtilObj.getListHandoutTitle().size() - 1).getText().contains("Handout Sample");
            if (!result) {
                liveClassMsgList.add("The handout title is not matching at web student");
                return result;
            }

            // Validate handout opening at student web
            cfObj.commonClick(webViewLiveClassUtilObj.getListBtnHandoutOpen().get(0));

            result = cfObj.commonWaitForElementToBeVisible(webDriver, webViewLiveClassUtilObj.getBtnDownlaodHandout(), 10);
            if (!result) {
                liveClassMsgList.add("Download Handout button is not present");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(webDriver, webViewLiveClassUtilObj.getBtnExitHandout(), 10);
            if (!result) {
                liveClassMsgList.add("Exit handout button is not present");
                return result;
            }

            // Click on download Handout button
            cfObj.commonClick_JS(webDriver, webViewLiveClassUtilObj.getBtnDownlaodHandout());
            Thread.sleep(2000);

            // Exit Handout page
            cfObj.commonClick_JS(webDriver, webViewLiveClassUtilObj.getBtnExitHandout());

            result = !cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, ".lc-button-download", "css", 5);
            if (!result) {
                liveClassMsgList.add("Unable to exit Handout");
                return result;
            }

            Thread.sleep(2000);

            // Verifying on android student
            if (cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getLiveClassHandOutIcon(), 10)) {

                cfObj.commonClick(liveClassPageObj.getLiveClassHandOutIcon());

                result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getLiveClassHandOutTitle().get(0), 10);
                if (!result) {
                    liveClassMsgList.add("HandOut Title is not visible.");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getHandOutOpenBtn(), 10);
                if (!result) {
                    liveClassMsgList.add("HandOut open button is not visible.");
                    return result;
                }

                cfObj.commonClick(liveClassPageObj.getHandOutOpenBtn());

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "pdfView", "id", 10);

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "btnDownload", "id", 10);
                if (!result) {
                    liveClassMsgList.add("HandOut download button is not visible.");
                    return result;
                }

                cfObj.commonClick(cfObj.commonGetElement(driver, "btnDownload", "id"));

                if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.android.permissioncontroller:id/permission_allow_button", "id", 5)) {
                    cfObj.commonClick(cfObj.commonGetElement(driver, "com.android.permissioncontroller:id/permission_allow_button", "id"));
                }

                cfObj.commonClick(cfObj.commonGetElement(driver, "btnExit", "id"));

                result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getHandOutOpenBtn(), 10);
                if (!result) {
                    liveClassMsgList.add("HandOut open button is not visible.");
                    return result;
                }
            } else {
                liveClassMsgList.add("LiveClass handout icon is not visible on android student");
                return false;
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("validateHandOutInAppWeb_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean verifyStages(AppiumDriver<MobileElement> driver, WebDriver webDriver) {
        boolean result = true;
        try {

            result = validateHandRaiseIcon(driver);
            if (!result) {
                liveClassMsgList.add("Not able to validate HandRaiseIcon.");
                return result;
            }

            cfObj.commonClick(liveClassPageObj.getHandRaiseBtn());

            result = cfObj.waitForPageLoading(driver, 5, 1000,
                    cfObj.commonGetElement(driver, "//*[@text='Lower Hand']", "xpath"));
            if (!result) {
                liveClassMsgList.add("Not able to load page.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, ".handraised-subtitle", "css", 7);
            if (!result) {

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//div[@id='ele_stream_handraise']",
                        "xpath", 15);
                if (!result) {
                    liveClassMsgList.add("Hand raise section button is not visible at teacher side");
                    return result;
                }

                // Click on handraise section
                cfObj.commonClick(cfObj.commonGetElement(webDriver, "//div[@id='ele_stream_handraise']", "xpath"));

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, ".handraised-subtitle", "css", 15);
                if (!result) {
                    liveClassMsgList.add("Hand raise section is not visible at teacher side");
                    return result;
                }
            }

            Thread.sleep(1000);

            // verify handraise profile is coming at teacher side
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, ".handraised-avatars", "css", 10);
            if (!result) {
                liveClassMsgList.add("Student profile is not visible at teacher side");
                return result;
            }

            Thread.sleep(1000);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//div[@class='mx-3']", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Allow btn for a student in hand raise listing is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver,
                    "//div[contains(@class,'d-flex flex-row cursorptr')]//div[2]", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Delete btn for a student in hand raise listing is not visible");
                return result;
            }

            // Take student on stages
            cfObj.commonClick(cfObj.commonGetElement(webDriver, "//div[contains(@class,'mx-3')]", "xpath"));

            Thread.sleep(15000);

            //Student on stages from teacher side
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//div[contains(@class,'mx-3')]", "xpath",
                    20);
            if (!result) {
                liveClassMsgList.add(
                        "audio icon beside student name is not visible at teacher side after connecting to student");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//div[@class='video-icon-stage']",
                    "xpath", 20);
            if (!result) {
                liveClassMsgList.add(
                        "video icon beside student name is not visible at teacher side after connecting to student");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//*[@id=\"ele_stream_dustbinIcon\"]",
                    "xpath", 20);
            if (!result) {
                liveClassMsgList.add("Delete icon is not visible at teacher side after connecting to student");
                return result;
            }

            // Android student on stages
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/btnAudio",
                    "id", 20);
            if (!result) {
                liveClassMsgList.add("audio icon on student side is not visible after connecting to student");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/btnVideo",
                    "id", 5);
            if (result) {
                liveClassMsgList.add("video icon on student side is visible after connecting to student");
                return false;
            }

            //Mute student on stages from teacher side
            cfObj.commonClick(cfObj.commonGetElement(webDriver, "//div[contains(@class,'mx-3')]", "xpath"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/btnAudio",
                    "id", 20);
            if (!result) {
                liveClassMsgList.add("audio icon on student side is not visible after mute the student ");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/btnVideo",
                    "id", 5);
            if (result) {
                liveClassMsgList.add("video icon on student side is visible after mute the student");
                return false;
            }

            // Enable Video from teacher end
            cfObj.commonClick(cfObj.commonGetElement(webDriver, "//div[@class='video-icon-stage']", "xpath"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/btnAudio",
                    "id", 20);
            if (!result) {
                liveClassMsgList.add("audio icon on student side is not visible after enable video by teacher");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/btnVideo",
                    "id", 20);
            if (!result) {
                liveClassMsgList.add("video icon on student side is not visible after after enable video by teacher");
                return result;
            }

            // Disable Video from teacher end
            cfObj.commonClick(cfObj.commonGetElement(webDriver, "//div[@class='video-icon-stage']", "xpath"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/btnAudio",
                    "id", 20);
            if (!result) {
                liveClassMsgList.add("audio icon on student side is not visible after disable video by teacher");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/btnVideo",
                    "id", 5);
            if (result) {
                liveClassMsgList.add("video icon on student side is visible after disable video by teacher");
                return false;
            }

            // Enable Video from teacher end
            cfObj.commonClick(cfObj.commonGetElement(webDriver, "//div[@class='video-icon-stage']", "xpath"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/btnVideo",
                    "id", 20);
            if (!result) {
                liveClassMsgList.add("video icon on student side is not visible after after enable video by teacher");
                return result;
            }

            // Student enables video
            cfObj.commonClick(cfObj.commonGetElement(driver, "//div[@class='video-icon-stage']", "xpath"));

            if (cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "com.android.permissioncontroller:id/permission_allow_foreground_only_button",
                    "id", 20)) {
                cfObj.commonClick(cfObj.commonGetElement(webDriver, "com.android.permissioncontroller:id/permission_allow_foreground_only_button", "id"));
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/userPreviewView",
                    "id", 20);
            if (!result) {
                liveClassMsgList.add("video icon switched on by student is not visible");
                return result;
            }

            //check video on by android student

            // Student disables video
            cfObj.commonClick(cfObj.commonGetElement(driver, "//div[@class='video-icon-stage']", "xpath"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/userPreviewView",
                    "id", 20);
            if (!result) {
                liveClassMsgList.add("video icon switched off by student is not visible");
                return result;
            }

            //check avatar for video off by android student

            // Student enables video
            cfObj.commonClick(cfObj.commonGetElement(driver, "//div[@class='video-icon-stage']", "xpath"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/userPreviewView",
                    "id", 20);
            if (!result) {
                liveClassMsgList.add("video icon switched on by student is not visible");
                return result;
            }

            //check video on by android student

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//*[@id=\"ele_stream_dustbinIcon\"]",
                    "xpath", 20);
            if (!result) {
                liveClassMsgList.add("Delete icon is not visible at teacher side after connecting to student");
                return result;
            }

            // Teacher removes student from stages using dustbin icon
            cfObj.commonClick(cfObj.commonGetElement(webDriver, "//*[@id=\"ele_stream_dustbinIcon\"]", "xpath"));

            Thread.sleep(3000);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//div[@class='mx-3']", "xpath", 5);
            if (result) {
                liveClassMsgList.add("Allow btn or mute as a student in hand raise listing is visible");
                return false;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getHandRaiseBtn(), 10);
            if (!result) {
                liveClassMsgList.add("HandRaiseBtn is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getHandRaiseImage(), 10);
            if (!result) {
                liveClassMsgList.add("HandRaiseImage is not visible.");
                return result;
            }
            result = cfObj.commonVerifyValueTextBox(liveClassPageObj.getHandRaiseBtn(), "Raise Hand");
            if (!result) {
                liveClassMsgList.add("HandRaise button text is not matching.");
                return result;
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("verifyStages_Exception" + e.getMessage());
        }
        return result;
    }

    public boolean verifyChatInAppWeb(WebDriver webDriver, AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            webDriver.switchTo().window(strParentWindow);

            result = verifyChat(webDriver, driver);
            if (!result) {
                liveClassMsgList.add("Not able to verify chat.");
            }

            result = verifyDisableChat(webDriver, driver);
            if (!result) {
                liveClassMsgList.add("Not able to verify Disable chat");
                return result;
            }

            result = verifyPrivateChat(webDriver, driver);
            if (!result) {
                liveClassMsgList.add("Not able to verify Private chat");
                return result;
            }

            result = verifyPinChat(webDriver, driver);
            if (!result) {
                liveClassMsgList.add("Not able to verify Pin chat");
            }

            result = verifyReplyPrivatelyChat(webDriver, driver);
            if (!result) {
                liveClassMsgList.add("Not able to verify Reply Privately chat");
            }

        } catch (Exception e) {
            liveClassMsgList.add("Chat Verification in App Web Failed: " + e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean verifyReplyPrivatelyChat(WebDriver webDriver, AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            /* ---------------- Reply Privately ----------------- */

            int sizeOfMessages = webViewLiveClassUtilObj.getVerticalDots().size();

            cfObj.commonClick(webViewLiveClassUtilObj.getVerticalDots().get(sizeOfMessages - 1));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//span[contains(text(),'Reply Privately')]", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Reply Privately option is not visible at teacher");
            } else {
                cfObj.commonClick(webViewLiveClassUtilObj.getOptionReplyPrivately());

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//div[@class='private-input-wrapper']//div[@class='private-input-chat-line-wrapper input-chat-line-wrapper']", "xpath", 10);
                if (!result) {
                    liveClassMsgList.add("Reply Privately box below is not visible at teacher");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//div[@class='private-input-close']", "xpath", 10);
                if (!result) {
                    liveClassMsgList.add("Reply Privately box close icon is not visible at teacher");
                    return result;
                }

                cfObj.commonSetTextTextBox(cfObj.commonGetElement(webDriver, "//input[@placeholder='Send message']", "xpath"), "Reply privately msg");
                cfObj.commonClick(webViewLiveClassUtilObj.getBtnSendMessage());

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//span[text()='Reply privately msg']", "xpath", 10);
                if (!result) {
                    liveClassMsgList.add("Reply message is not visible to teacher chat");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'Reply privately msg')]", "xpath", 10);
                if (!result) {
                    System.out.println("Reply message is not visible to android student chat, might be on web student");

                    webDriver.switchTo().window(strChildWindow);

                    result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//span[text()='Reply privately msg']", "xpath", 10);
                    if (!result) {
                        liveClassMsgList.add("Reply message is not visible to student web chat or android chat");
                        return result;
                    }
                }
            }

        } catch (Exception e) {
            liveClassMsgList.add("verifyReplyPrivatelyChat Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean verifyPinChat(WebDriver webDriver, AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            /* ---------------- Pin Messages ----------------- */

            webDriver.switchTo().window(strParentWindow);

            // Clicking teacher message vertical menu
            cfObj.commonClick(webViewLiveClassUtilObj.getVerticalDots().get(0));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, ".ant-dropdown-menu-title-content", "css", 10);
            if (result) {
                cfObj.commonClick(webViewLiveClassUtilObj.getOptionPinMessage());
            } else {
                liveClassMsgList.add("Pin Message option is not visible");
                return false;
            }

            Thread.sleep(500);

            // Clicking student message vertical menu
            cfObj.commonClick(webViewLiveClassUtilObj.getVerticalDots().get(1));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, ".ant-dropdown-menu-vertical", "css", 10);
            if (result) {
                cfObj.commonClick(webViewLiveClassUtilObj.getOptionPinMessage());
            } else {
                liveClassMsgList.add("Pin Message option is not visible");
                return false;
            }

            Thread.sleep(500);

            // Clicking student message vertical menu
            cfObj.commonClick(webViewLiveClassUtilObj.getVerticalDots().get(2));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, ".ant-dropdown-menu-vertical", "css", 10);
            if (result) {
                cfObj.commonClick(webViewLiveClassUtilObj.getOptionPinMessage());
            } else {
                liveClassMsgList.add("Pin Message option is not visible");
                return false;
            }

            // Check if done for more than 3 pins, error message displayed


            // Check pin messages on android student
            result = validatePinnedMessage(driver);
            if (!result) {
                liveClassMsgList.add("failed pin message for android student");
                return result;
            }

            // Check pin messages on web student
            webDriver.switchTo().window(strChildWindow);
            Thread.sleep(1000);

            result = cfObj.commonGetElement(webDriver, ".pinned-count-student", "css").getText() != "3";
            if (!result) {
                liveClassMsgList.add("Pinned message count is not 3 at web student side, failed pin message for web student");
                return result;
            }

            // Teacher unpin the pin messages
            webDriver.switchTo().window(strParentWindow);
            Thread.sleep(1000);

            cfObj.commonClick(webViewLiveClassUtilObj.getVerticalDots().get(0));

            result = cfObj.commonGetElements(webDriver, "//*[contains(text(),'Unpin Message')]", "xpath").size() == 1;
            if (!result) {
                liveClassMsgList.add("The vertical dots menu did not opened for unpinned message");
            } else {
                cfObj.commonGetElements(webDriver, "//*[contains(text(),'Unpin Message')]", "xpath").get(0).click();
                Thread.sleep(500);
            }

        } catch (Exception e) {
            liveClassMsgList.add("verifyPinChat Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean verifyPrivateChat(WebDriver webDriver, AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            /* ---------------- Private Chat ----------------- */

            // Teacher enables the private mode
            webDriver.switchTo().window(strParentWindow);

            result = webViewLiveClassUtilObj.clickOnPrivateMode(webDriver);
            if (!result) {
                liveClassMsgList.addAll(webViewLiveClassUtilObj.webViewLiveClassUtilMsgList);
                return result;
            }

            // Check private mode enabled & send private msg from android student
            result = validatePrivateModeEnableText(driver);
            if (!result) {
                liveClassMsgList.add("Not able to validate PrivateMode on android student");
                return result;
            }

            result = enterMessageInMessageSendTextField(driver, true);
            if (!result) {
                liveClassMsgList.add("Not able to enter private Message in MessageSendTextField of android student");
                return result;
            }

            // Check private mode enabled & send private msg from web student
            webDriver.switchTo().window(strChildWindow);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, ".private-chat-pop-msg", "css", 10);
            if (!result) {
                liveClassMsgList.add("Not able to validate PrivateMode on web student");
                return result;
            }

            cfObj.commonSetTextTextBox(webViewLiveClassUtilObj.getTextField(), "private message from Web Student");
            cfObj.commonClick(webViewLiveClassUtilObj.getBtnSendMessage());

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//span[text()='privateandroid']", "xpath", 10);
            if (result) {
                liveClassMsgList.add("android student private msg is visible to web student");
                return false;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'private message from Web Student')]", "xpath", 10);
            if (result) {
                liveClassMsgList.add("web student private msg is visible to android student");
                return false;
            }

            // Check private students messages on teacher
            webDriver.switchTo().window(strParentWindow);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//*[text()='privateandroid']", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Android student private message is not visible to teacher chat");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//*[contains(text(),'private message from Web Student')]", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Web student private message is not visible to teacher chat");
                return result;
            }

            // Teacher disable private mode
            result = webViewLiveClassUtilObj.clickOnPrivateMode(webDriver);
            if (!result) {
                liveClassMsgList.addAll(webViewLiveClassUtilObj.webViewLiveClassUtilMsgList);
                return result;
            }
            Thread.sleep(4000);

            // Check private mode disabled on android student
            result = cfObj.commonWaitForElementToBeVisible(webDriver, liveClassPageObj.getPrivateModeEnableTxt(), 5);
            if (result) {
                liveClassMsgList.add("private mode is visible at android student");
                return false;
            } else {
                result = true;
            }

        } catch (Exception e) {
            liveClassMsgList.add("verifyPrivateChat Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean verifyDisableChat(WebDriver webDriver, AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            /* ---------------- Disable Chat ----------------- */

            // Teacher disables the chat
            webDriver.switchTo().window(strParentWindow);

            result = webViewLiveClassUtilObj.clickOnEnableChatButton(webDriver);
            if (!result) {
                liveClassMsgList.addAll(webViewLiveClassUtilObj.webViewLiveClassUtilMsgList);
                return result;
            }

            // Check chat disable text on android student
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.EditText[@text='Chat turned Off']", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("The chat is not disabled at android student");
                return result;
            }

            // Check chat disable text on web student
            webDriver.switchTo().window(strChildWindow);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//input[@placeholder='Teacher disabled chat']", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("The chat is not disabled at web student");
                return result;
            }

            // Teacher enables the chat
            webDriver.switchTo().window(strParentWindow);

            result = webViewLiveClassUtilObj.clickOnEnableChatButton(webDriver);
            if (!result) {
                liveClassMsgList.addAll(webViewLiveClassUtilObj.webViewLiveClassUtilMsgList);
                return result;
            }

            // Check chat enabled on android student
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.EditText[contains(@text,'Send Message')]", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Chat Enable text is not visible on android student.");
                return result;
            }

            // Check chat enabled on web student
            webDriver.switchTo().window(strChildWindow);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//input[@placeholder='Send Message']", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Chat Enable text is not visible on web student.");
                return result;
            }

        } catch (Exception e) {
            liveClassMsgList.add("verifyDisableChat Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    private boolean verifyChat(WebDriver webDriver, AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            /* ---------------- Chat Message ----------------- */

            // Send message from teacher
            cfObj.commonSetTextTextBox(webViewLiveClassUtilObj.getTextField(), "Automated message from Teacher");
            cfObj.commonClick(webViewLiveClassUtilObj.getBtnSendMessage());

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//span[text()='Automated message from Teacher']", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Teacher message is not visible to teacher chat");
                return result;
            }

            // Check teacher message on web student
            webDriver.switchTo().window(strChildWindow);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//span[text()='Automated message from Teacher']", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Teacher message is not visible to student chat");
                return result;
            }

            // Check teacher message on android student
            cfObj.commonClick(liveClassPageObj.getChatWindowBtn());

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getMessageSendBtn(), 10);
            if (!result) {
                liveClassMsgList.add("MessageSendBtn is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, liveClassPageObj.getMessageSendTextField(), 10);
            if (!result) {
                liveClassMsgList.add("MessageSendTextField is not visible.");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'Automated message from Teacher')]", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Teacher message is not visible to android student chat");
                return result;
            }

            // Send message from web student
            cfObj.commonSetTextTextBox(webViewLiveClassUtilObj.getTextField(), "Automated message from Web Student");
            cfObj.commonClick(webViewLiveClassUtilObj.getBtnSendMessage());

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//span[text()='Automated message from Web Student']", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Student message is not visible to web student chat");
                return result;
            }

            // Send message from android student
            result = enterMessageInMessageSendTextField(driver, false);
            if (!result) {
                liveClassMsgList.add("Not able to enter Message in MessageSendTextField.");
                return result;
            }

            // Check android & web message on teacher chat
            webDriver.switchTo().window(strParentWindow);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//*[text()='hiandroidstudent']", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Android student message is not visible to teacher chat");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//*[text()='Automated message from Web Student']", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Teacher did not receive the correct message");
                return result;
            }

            // Send link message from teacher
            cfObj.commonSetTextTextBox(webViewLiveClassUtilObj.getTextField(), "https://www.google.co.in/");
            cfObj.commonClick(webViewLiveClassUtilObj.getBtnSendMessage());

            // Check link message is visible in teacher chat
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//a[.='https://www.google.co.in/']", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Teacher link message is not visible to teacher chat");
                return result;
            }

            // Check teacher sent link message is display to web student
            webDriver.switchTo().window(strChildWindow);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//a[.='https://www.google.co.in/']", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Teacher link message is not visible to student chat");
                return result;
            }

            // Check teacher sent link message is display to android student

        } catch (Exception e) {
            liveClassMsgList.add("verifyChat Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean setUpLiveClass(WebDriver driver) {
        boolean result = true;
        try {
            // Teacher Window Already Opened, Get Window Handle
            strParentWindow = driver.getWindowHandle();
            System.out.println("Teacher Window: " + strParentWindow);

            // Open New Tab for Student
            ((JavascriptExecutor) driver).executeScript("window.open()");
            List<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));

            driver.get(strStudentUrl);

            // Wait for Student to Join
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".studentchat", "css", 30);
            if (!result) {
                liveClassMsgList.add("Web student not able to join class.");
                return result;
            }

            Thread.sleep(1000);

            // Save Student Window Handle
            strChildWindow = driver.getWindowHandle();
            System.out.println("Student Window: " + strChildWindow);

            // Switch Back to Teacher Window
            driver.switchTo().window(strParentWindow);

        } catch (Exception e) {
            liveClassMsgList.add("setUpLiveClass Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyStudentFeedbackFormOnWeb(WebDriver driver) {
        boolean result = true;
        try {
            webDriver.switchTo().window(strChildWindow);

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".feedbackform", "css", 10);
            if (!result) {
                liveClassMsgList.add("Feedback form not received after waiting for 20 seconds");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[contains(text(),'Please help us improve.')]", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("The default placeholder is not: Please help us improve");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='wrap-toptext']", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("About your class text is not visible in student feedback");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[contains(text(),'Rate The Faculty')]", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Rate The Faculty text is not visible in student feedback");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[contains(text(),'Rate The Content')]", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Rate The Content text is not visible in student feedback");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[contains(text(),'Rate The Interface')]", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Rate The Interface text is not visible in student feedback");
                return result;
            }

            System.out.println(webViewLiveClassUtilObj.getRateStar().size());

            cfObj.commonClick(webViewLiveClassUtilObj.getRateStar().get(0));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "(//label[contains(text(),'Is the topic taught in class the same as in the st')])", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Is the topic taught in class ... text is not visible in student feedback");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//span[normalize-space()='Yes']", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Yes btn in Is the topic taught in class ... is not visible in student feedback");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//span[normalize-space()='No']", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("No btn in Is the topic taught in class ...  is not visible in student feedback");
                return result;
            }

            cfObj.commonClick(webViewLiveClassUtilObj.getRateStar().get(6));

            result = cfObj.commonGetElement(driver, "//button[normalize-space()='SUBMIT']", "xpath").isEnabled();
            if (result) {
                liveClassMsgList.add("After only 2 input, the submit is enabled");
                return false;
            }

            System.out.println(webViewLiveClassUtilObj.getRateStar().size());

            cfObj.commonClick(webViewLiveClassUtilObj.getRateStar().get(webViewLiveClassUtilObj.getRateStar().size() - 1));

            result = cfObj.commonGetElement(driver, "//button[normalize-space()='SUBMIT']", "xpath").isEnabled();
            if (!result) {
                liveClassMsgList.add("After 3 inputs, the submit is not enabled");
                return false;
            }

            // Verify submitting when giving bad rating without feedback
            cfObj.commonGetElement(driver, ".fbsubmitbtn", "css").click();
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, ".Toastify__toast-container", "css", 10);
            if (!result) {
                liveClassMsgList.add("Please comment your feedback toast might not be visible");
                return result;
            }

            // Write Feedback
            result = cfObj.commonSetTextTextBox(cfObj.commonGetElement(driver, "//textarea[@id='feedback']", "xpath"), "Automated feedback");
            if (!result) {
                liveClassMsgList.add("Unable to type feedback");
                return result;
            }

            cfObj.commonClick(cfObj.commonGetElement(driver, "//button[text()='SUBMIT']", "xpath"));

            // Waiting for feedback form to submit
            Thread.sleep(5000);

            System.out.println("Teacher feedback is completed.");

        } catch (Exception e) {
            System.out.println("verifyStudentFeedbackFormOnWeb_Exception: " + e.getMessage());
            result = false;
        }
        return result;
    }

    public boolean verifyTeacherFeedback(WebDriver webDriver) {
        boolean result = true;
        try {

            webDriver.switchTo().window(strParentWindow);

            // Verify Leaderboard appears on student side
//            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//*[text()='Cumulative Leaderboard']", "xpath", 3);
//            if (!result) {
//                System.out.println("The Cumulative leaderboard heading in popup is not visible after end class");
//            } else {
//
//                // close leaderboard
//                result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//*[@id=\"icondiv\"]/span", "xpath", 10);
//                if (!result) {
//                    liveClassMsgList.add("In Cumulative leaderboard close icon in popup is not visible after end class");
//                    return result;
//                } else {
//                    cfObj.commonGetElement(webDriver, "//*[@id=\"icondiv\"]/span", "xpath").click();
//                }
//            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//img[@alt='Feedback']", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Teacher feedback image is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//span[normalize-space()='Skip']", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Teacher feedback image is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//span[normalize-space()='Rate your platform']", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Teacher feedback image is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//span[@class='sub-heading']", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Teacher feedback image is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//div[@class='teacher-feedback-stars']", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Teacher feedback image is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//button[contains(.,'Submit')]", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Teacher feedback image is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//div[contains(@class,'input-sub-box')]", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("Teacher feedback image is not visible");
                return result;
            }

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//li[position()=5]", "xpath", 10);
            if (!result) {
                liveClassMsgList.add("5th Star image is not visible in teacher feedback");
                return result;
            }

            cfObj.commonClick(cfObj.commonGetElement(webDriver, "//span[normalize-space()='Skip']", "xpath"));

            System.out.println("Teacher feedback is completed.");

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("verifyTeacherFeedback_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean verifyOtherButtons(WebDriver driver, String typeOfClass) {
        boolean result = true;
        try {
            driver.switchTo().window(strParentWindow);

            result = cfObj.commonWaitForElementToBeVisible(driver, webViewLiveClassUtilObj.getBtnScreenshare(), 10);
            if (!result) {
                liveClassMsgList.add("Screenshare btn is not visible at teacher side");
                return result;
            }

            if (typeOfClass.equalsIgnoreCase("ssc")) {

                result = cfObj.commonWaitForElementToBeVisible(driver, webViewLiveClassUtilObj.getBtnCameraSwap(), 5);
                if (result) {
                    liveClassMsgList.add("Swap icon btn is visible at teacher side, but should not be visible");
                    return false;
                }
            } else {
                result = cfObj.commonWaitForElementToBeVisible(driver, webViewLiveClassUtilObj.getBtnCameraSwap(), 10);
                if (!result) {
                    liveClassMsgList.add("Swap icon btn is not visible at teacher side");
                    return result;
                }
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, webViewLiveClassUtilObj.getBtnSos(), 10);
            if (!result) {
                liveClassMsgList.add("getBtnSos is not visible at teacher side");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, webViewLiveClassUtilObj.getBtnWhiteboard(), 5);
            if (!result) {
                liveClassMsgList.add("getBtnWhiteboard is not visible at teacher side");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, webViewLiveClassUtilObj.getBtnExtendClass(), 10);
            if (!result) {
                liveClassMsgList.add("getBtnExtendClass is not visible at teacher side");
                return result;
            }

        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("verifyOtherButtons_Exception: " + e.getMessage());
        }
        return result;
    }

    public boolean verifyMockTestsBottomSheetAfterLiveClass(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@text='EXPLORE']", "xpath", 7)) {

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/design_bottom_sheet", "id", 10);
                if (!result) {
                    liveClassMsgList.add("design bottomsheet is not visible");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/heading", "id", 10);
                if (!result) {
                    liveClassMsgList.add("Heading bottomsheet is not visible");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/des_heading", "id", 10);
                if (!result) {
                    liveClassMsgList.add("des_heading bottomsheet is not visible");
                    return result;
                }

                result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/already_purchased_tv", "id", 10);
                if (!result) {
                    liveClassMsgList.add("already_purchased_tv text bottomsheet is not visible");
                    return result;
                }
                driver.navigate().back();

                Thread.sleep(2000);

                if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@text='EXPLORE']", "xpath", 3)) {
                    liveClassMsgList.add("Explore mock tests bottomsheet didn't closed.");
                    return false;
                }
            } else {
                System.out.println("Explore mock tests bottomsheet is not visible");
            }
        } catch (Exception e) {
            result = false;
            liveClassMsgList.add("verifyMockTestsBottomSheetAfterLiveClass_Exception: " + e.getMessage());
        }
        return result;
    }
}