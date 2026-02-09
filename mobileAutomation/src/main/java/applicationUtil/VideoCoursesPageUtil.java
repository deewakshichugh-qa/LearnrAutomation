package applicationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.openqa.selenium.support.PageFactory;

import apiUtill.CreatePackageUtil;
import apiUtill.PackageApiUtil;
import applicationUtil.StorePageUtil.ProductType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.VideoCoursesPage_OR;
import pojo.createPackageResponse.CreatePackageResponse;
import pojo.getVideoList.GetVideoList;
import util.Common_Function;
import util.Common_Function.NetworkState;
import util.Common_Function.direction;
import util.Common_Function.key;
import util.ConfigFileReader;

public class VideoCoursesPageUtil {

	PurchasePackageUtil purchasePackageUtilObj;
	UserDetailsLayerUtil userDetailsLayerUtilObj;
	PriceDetailsPageUtil priceDetailsUtilObj;
	VideoCoursesPage_OR videoCoursesPageObj;;
	FilterPageUtil filterPageUtilObj;
	LoginUtil loginUtilObj;
	HomePageUtil homePageUtilObj;
	StorePageUtil storePageUtilObj;
	MyContentUtil myContentUtilObj;
	OrderSuccessUtil orderSuccessUtilObj;
	ChildPackageUtil childPackageUtilObj;
	PaymentUtil paymentUtilObj;
	CommentPageUtil commentPageUtilObj;
	LiveClassesPageUtil liveClassesPageUtilObj;
	BatchUtil batchUtilObj;
	public Common_Function cfObj = new Common_Function();
	public List<String> videoSectionMsgList = new ArrayList<String>();
	private String searchTopic = "SSC";

	public List<String> videoClassList = new ArrayList<String>();

	public VideoCoursesPageUtil(AppiumDriver<MobileElement> driver) {
		videoCoursesPageObj = new VideoCoursesPage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), videoCoursesPageObj);
	}

	public boolean verifyVideoCoursesPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoSectionTitle(), 30);
			if (!result) {
				videoSectionMsgList.add("Video section title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForListOfElementsToBeVisible(driver, videoCoursesPageObj.getVideoList(), 30);
			if (!result) {
				videoSectionMsgList.add("VideoList list is not visible.");
				return result;
			}

			result = cfObj.commonWaitForListOfElementsToBeVisible(driver, videoCoursesPageObj.getVideoTimeText(), 30);
			if (!result) {
				videoSectionMsgList.add("VideoTimeText list is not visible.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("verifyVideoCoursesPage Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickUnpurchasedPackage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		boolean purchased = true;
		int index = 0;
		try {
			List<MobileElement> el = videoCoursesPageObj.getListPackage();
			el.get(index).click();
			while (purchased) {
				purchased = !(cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getBtnBuyNow(), 10));

				if (purchased) {
					videoCoursesPageObj.getBtnNavigateUp();
					el.get(index + 1).click();
					index++;
				}
			}

			purchasePackageUtilObj = new PurchasePackageUtil(driver);

			result = purchasePackageUtilObj.verifyUnpurchasedPackagePage(driver);
			if (!result) {
				videoSectionMsgList.add("Failed to verify Unpurchased Package Page.");
			}

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("clickUnpurchasedPackage Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean validateVideoSectionUI(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoSectionTitle(), 30);
			if (!result) {
				videoSectionMsgList.add("Video section title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForListOfElementsToBeVisible(driver, videoCoursesPageObj.getVideoList(), 30);
			if (!result) {
				videoSectionMsgList.add("VideoList is not visible.");
				return result;
			}

			result = cfObj.commonWaitForListOfElementsToBeVisible(driver, videoCoursesPageObj.getVideoTimeText(), 30);
			if (!result) {
				videoSectionMsgList.add("VideoTimeText are not visible.");
				return result;
			}

			result = cfObj.commonWaitForListOfElementsToBeVisible(driver, videoCoursesPageObj.getVideoTitleList(), 30);
			if (!result) {
				videoSectionMsgList.add("VideoTitleList are not visible.");
				return result;
			}

			result = cfObj.commonWaitForListOfElementsToBeVisible(driver, videoCoursesPageObj.getVideoLikeText(), 30);
			if (!result) {
				videoSectionMsgList.add("VideoLikeText are not visible.");
				return result;
			}

			result = validateRecentTab(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to validate Recent Tab.");
				return result;
			}

			result = validateVideoSectionFilterIcon(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to validate VideoSectionFilterIcon.");
				return result;
			}

			result = validateVideoSectionSearchTextField(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to validate VideoSectionSearchTextField.");
				return result;
			}

			result = validateDotIcon(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to validate Dot icon.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("VideoSectionUI_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean validateRecentTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getRecentTab(), 30);
			if (!result) {
				videoSectionMsgList.add("Recent tab is not visible.");
				return result;
			}

			String title = videoCoursesPageObj.getRecentTab().getText();
			if (title == null) {
				videoSectionMsgList.add("Title is null.");
				return false;
			}
			cfObj.commonClick(videoCoursesPageObj.getRecentTab());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'" + title + "')]",
					"xpath", 30);
			if (!result) {
				videoSectionMsgList.add("RecentVideoPageTitle is not visible.");
				return result;
			}
			cfObj.commonClick(videoCoursesPageObj.getBackBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getRecentTab(), 30);
			if (!result) {
				videoSectionMsgList.add("Recent tab is not visible.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("RecentTab_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateVideoSectionFilterIcon(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoSectionFilterBtn(), 30);
			if (!result) {
				videoSectionMsgList.add("Filter button is not visible.");
				return result;
			}
			cfObj.commonClick(videoCoursesPageObj.getVideoSectionFilterBtn());
			Thread.sleep(2000);
			filterPageUtilObj = new FilterPageUtil(driver);
			result = filterPageUtilObj.verifyFilterPageUI(driver);
			if (!result) {
				videoSectionMsgList.addAll(filterPageUtilObj.filterPageMsgList);
				return result;
			}
			result = filterPageUtilObj.clickOnStatusTab(driver,
					filterPageUtilObj.filterPageORObj.getStatusList().get(2), "selected");
			if (!result) {
				videoSectionMsgList.addAll(filterPageUtilObj.filterPageMsgList);
				return result;
			}
			result = filterPageUtilObj.clickOnApplyBtn(driver);
			if (!result) {
				videoSectionMsgList.addAll(filterPageUtilObj.filterPageMsgList);
				return result;
			}
			Thread.sleep(2000);
			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoStatusText().get(0), 30);
			if (!result) {
				videoSectionMsgList.add("VideoStatusText is not visible.");
				return result;
			}
			result = cfObj.commonVerifyValueTextBox(videoCoursesPageObj.getVideoStatusText().get(0), "UPCOMING");
			if (!result) {
				videoSectionMsgList.add("VideoStatusText is not matching.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("VideoSectionFilterIcon_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateVideoSectionSearchTextField(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getSearchTextField(), 30);
			if (!result) {
				videoSectionMsgList.add("SearchTextField is not visible.");
				return result;
			}

			cfObj.commonClick(videoCoursesPageObj.getSearchTextField());

			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getSearchTextEnterField(), 30);
			if (!result) {
				videoSectionMsgList.add("SearchTextEnterField is not visible.");
				return result;
			}

			if (ConfigFileReader.strApplication.equalsIgnoreCase("Sankalp")
					&& ConfigFileReader.strEnv.equalsIgnoreCase("PRODUCTION")) {
				result = cfObj.commonSetTextTextBox(videoCoursesPageObj.getSearchTextEnterField(), "RBI");
			} else {
				result = cfObj.commonSetTextTextBox(videoCoursesPageObj.getSearchTextEnterField(), searchTopic);
			}
			if (!result) {
				videoSectionMsgList.add("Not able to enter topic in SearchTextEnterField.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getViewAllTopicSuggestionBtn(),
					30);
			if (!result) {
				videoSectionMsgList.add("ViewAllTopicSuggestionBtn is not visible.");
				return result;
			}

			cfObj.commonClick(videoCoursesPageObj.getViewAllTopicSuggestionBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getSearchTopicTitle(), 30);
			if (!result) {
				videoSectionMsgList.add("SearchTopicTitle is not visible.");
				return result;
			}

			if (ConfigFileReader.strApplication.equalsIgnoreCase("Sankalp")
					&& ConfigFileReader.strEnv.equalsIgnoreCase("PRODUCTION")) {
				result = cfObj.commonVerifyValueTextBox(videoCoursesPageObj.getSearchTopicTitle(), "RBI");
			} else {
				result = cfObj.commonVerifyValueTextBox(videoCoursesPageObj.getSearchTopicTitle(), searchTopic);
			}
			if (!result) {
				videoSectionMsgList.add("SearchTopicTitle is not matching.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getBackBtn(),
					30);
			if (!result) {
				videoSectionMsgList.add("getBackBtn is not visible.");
				return result;
			}
			cfObj.commonClick(videoCoursesPageObj.getBackBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getSearchTextEnterFieldBackBtn(),
					30);
			if (!result) {
				videoSectionMsgList.add("SearchTextEnterFieldBackBtn is not visible.");
				return result;
			}
			cfObj.commonClick(videoCoursesPageObj.getSearchTextEnterFieldBackBtn());

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("VideoSectionSearchTextField_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean addVideoInBookMark(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "favourite", "id", 30);
			if (!result) {
				videoSectionMsgList.add("DotIconList is not visible.");
				return result;
			}
			cfObj.commonClick(videoCoursesPageObj.getDotIconList().get(0));
			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getAddBookMarkBtn(), 30);
			if (!result) {
				videoSectionMsgList.add("Book mark icon is not visible.");
				return result;
			}
			cfObj.commonClick(videoCoursesPageObj.getAddBookMarkBtn());
			cfObj.waitTillElementIsVisible(driver, 10, 1000, videoCoursesPageObj.getToastMessage());
			cfObj.commonClick(videoCoursesPageObj.getBackBtn());
		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("addVideoInBookMark_Exception" + e.getMessage());

		}
		return result;

	}

	public boolean validateDotIcon(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getDotIconList().get(0), 30);
			if (!result) {
				videoSectionMsgList.add("DotIconList is not visible.");
				return result;
			}
			cfObj.commonClick(videoCoursesPageObj.getDotIconList().get(0));

			result = validateVideoSectionShareBtn(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to validate Video share button.");
				return result;
			}

			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {

				result = validateBookMarkBtn(driver);
				if (!result) {
					videoSectionMsgList.add("Not able to validate BookMark button.");
					return result;
				}
			}

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("DotIcon_Exception" + e.getMessage());

		}
		return result;
	}

	public boolean validateVideoSectionShareBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getShareBtn(), 30);
			if (!result) {
				videoSectionMsgList.add("Video Share button is not visible.");
				return result;
			}
			cfObj.commonClick(videoCoursesPageObj.getShareBtn());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'Share via')]",
					"xpath", 15);
			if (!result) {
				videoSectionMsgList.add("Share via bottom sheet is not visible or popup title might be different.");
				result = true;
			}

			driver.navigate().back();

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("VideoSectionShareBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateBookMarkBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			for (int i = 0; i < 2; i++) {
				Thread.sleep(2000);
				cfObj.commonClick(videoCoursesPageObj.getDotIconList().get(0));
				result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getAddBookMarkBtn(), 30);
				if (!result) {
					videoSectionMsgList.add("Book mark icon is not visible.");
					return result;
				}
				if (videoCoursesPageObj.getAddBookMarkBtn().getText().equals("Delete Bookmark")) {
					Thread.sleep(1000);
					cfObj.commonClick(videoCoursesPageObj.getAddBookMarkBtn());
				} else {
					Thread.sleep(1000);
					cfObj.commonClick(videoCoursesPageObj.getAddBookMarkBtn());
				}
			}
		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("BookMarkBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyVideoCourse(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			LoginUtil loginUtil = new LoginUtil(driver);

			result = loginUtil.verifyLoginUsingEmailId(driver, "testingabc130@yopmail.com", "1234567", false);
			if (!result) {
				videoSectionMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			homePageUtilObj = new HomePageUtil(driver);
			result = homePageUtilObj.clickMyContentButton(driver);
			if (!result) {
				videoSectionMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			myContentUtilObj = new MyContentUtil(driver);

			result = myContentUtilObj.clickOnPackageInPurchasedSectionWithoutSearch(driver);
			if (!result) {
				videoSectionMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}

			result = clickOnPurchasedVideo(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to click Purchased Video.");
				return result;
			}

			result = clickOnGotItBtn(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to click GotIt button.");
				return result;
			}

			result = validatePurchasedVideoCourseUI(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to validate PurchasedVideoCourseUI.");
				return result;
			}

			result = validateVideoCourseShareBtn(driver);
			if (!result) {
				System.out.println("Not able to validate VideoCourseShareBtn.");
				return result;
			}

			result = cfObj.pressAndroidKey(driver, key.POWER, 2);
			if (!result) {
				videoSectionMsgList.add("Not able to click power button 2 times.");
				return result;
			}

			result = clickOnPlayerViewStateBtn(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to click playerViewStateBtn.");
				return result;
			}

			result = cfObj.pressAndroidKey(driver, key.POWER, 2);
			if (!result) {
				videoSectionMsgList.add("Not able to click power button 2 times.");
				return result;
			}

			result = validateVideoPauseAndPlayBtn(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to validate VideoPauseAndPlayBtn.");
				return result;
			}

			result = validateVideoStreamOptionBtn(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to validate VideoStreamOptionBtn.");
				return result;
			}

			cfObj.tapOnElementEndpoint(driver, videoCoursesPageObj.getVideoProgressBar());

			result = validateVideoPlayBackSpeedBtn(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to validate VideoStreamOptionBtn.");
				return result;
			}

			result = validateVideoFullScreenBtn(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to validate VideoFullScreenBtn.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("VideoCourse_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyVideoCourseDownload(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String packageTitle;
		try {

			CreatePackageUtil createPackageUtil = new CreatePackageUtil();
			List<Integer> categorylist = new ArrayList<Integer>();
			PackageApiUtil packageApiUtil = new PackageApiUtil();
			categorylist.add(5);

			CreatePackageResponse createPackageResponse = createPackageUtil.createPackage(false, false, categorylist,
					2);
			if (createPackageResponse == null) {
				videoSectionMsgList.add("Not able to create package.");
				return false;
			}
			System.out.println("strPackageId: " + createPackageResponse.getData().getId());
			GetVideoList getVideoList = packageApiUtil
					.videoLinking(String.valueOf(createPackageResponse.getData().getId()));
			if (getVideoList == null) {
				videoSectionMsgList.add("Not able to Link video.");
				return false;
			}

			packageTitle = createPackageResponse.getData().getTitle();
			if (packageTitle == null) {
				videoSectionMsgList.add("Package title is null.");
				return false;
			}
			System.out.println("Package Title:->" + packageTitle);

			loginUtilObj = new LoginUtil(driver);

			result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), true);
			if (!result) {
				videoSectionMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			homePageUtilObj = new HomePageUtil(driver);

			result = homePageUtilObj.clickStoreBtn(driver);
			if (!result) {
				videoSectionMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			storePageUtilObj = new StorePageUtil(driver);
			if (ConfigFileReader.strApplication.equalsIgnoreCase("Sankalp")) {
				result = storePageUtilObj.clickVideoCoursesIcon(driver);
				if (!result) {
					videoSectionMsgList.addAll(storePageUtilObj.storePageMsgList);
					return result;
				}
				result = cfObj.pressAndroidKey(driver, key.BACK, 1);
				if (!result) {
					videoSectionMsgList.add("Not able to click back button.");
					return result;
				}
			}
			result = storePageUtilObj.clickSearchIcon(driver);
			if (!result) {
				videoSectionMsgList.addAll(storePageUtilObj.storePageMsgList);
				return result;
			}

			result = storePageUtilObj.enterPackageNameInSearchField(driver,
					packageTitle.split(" ")[packageTitle.split(" ").length - 1]);
			if (!result) {
				videoSectionMsgList.addAll(storePageUtilObj.storePageMsgList);
				return result;
			}

			result = storePageUtilObj.selectProductType(driver, ProductType.VIDEOS);
			if (!result) {
				videoSectionMsgList.addAll(storePageUtilObj.storePageMsgList);
				return result;
			}

			result = storePageUtilObj.clickPackageNameInSearchResult(driver, packageTitle);
			if (!result) {
				videoSectionMsgList.addAll(storePageUtilObj.storePageMsgList);
				return result;
			}

			purchasePackageUtilObj = new PurchasePackageUtil(driver);
			result = purchasePackageUtilObj.verifyUnpurchasedPackagePage(driver);
			if (!result) {
				videoSectionMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
				return result;
			}

			result = purchasePackageUtilObj.validateDiscountRemoveBtn(driver);
			if (!result) {
				videoSectionMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
				return result;
			}

			result = purchasePackageUtilObj.clickBuyNowBtn(driver);
			if (!result) {
				videoSectionMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
				return result;
			}
			userDetailsLayerUtilObj = new UserDetailsLayerUtil(driver);
			result = userDetailsLayerUtilObj.fillUserDetailsForm(driver);
			if (!result) {
				videoSectionMsgList.addAll(userDetailsLayerUtilObj.userDetailsLayerMsgList);
				return result;
			}
			result = userDetailsLayerUtilObj.clickOnContinueBtn(driver);
			if (!result) {
				videoSectionMsgList.addAll(userDetailsLayerUtilObj.userDetailsLayerMsgList);
				return result;
			}

			paymentUtilObj = new PaymentUtil(driver);

			result = paymentUtilObj.clickOnUPITypePayment(driver);
			if (!result) {
				videoSectionMsgList.addAll(paymentUtilObj.paymentMsgList);
				return result;
			}

			result = paymentUtilObj.clickOnPayNowBtn(driver);
			if (!result) {
				videoSectionMsgList.addAll(paymentUtilObj.paymentMsgList);
				return result;
			}
			orderSuccessUtilObj = new OrderSuccessUtil(driver);
			result = orderSuccessUtilObj.handleRateUsPopUpWindow(driver);
			if (!result) {
				videoSectionMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
				return result;
			}
			result = orderSuccessUtilObj.verifyOrderSuccessPage(driver);
			if (!result) {
				videoSectionMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
				return result;
			}
			String productTitle = orderSuccessUtilObj.getStartLearningTitle(driver);
			if (productTitle == null) {
				videoSectionMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
				return false;
			}

			result = cfObj.pressAndroidKey(driver, key.BACK, 3);
			if (!result) {
				videoSectionMsgList.add("Unable to press android back key 3 times");
				return result;
			}

			result = homePageUtilObj.clickMyContentButton(driver);
			if (!result) {
				videoSectionMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			myContentUtilObj = new MyContentUtil(driver);

			result = myContentUtilObj.clickOnPurchasedBtn(driver);
			if (!result) {
				videoSectionMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}

			result = myContentUtilObj.clickOnPackageInPurchasedSection(driver, packageTitle);
			if (!result) {
				videoSectionMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}

			result = clickOnPurchasedVideo(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to click Purchased Video.");
				return result;
			}

			result = clickOnGotItBtn(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to click GotIt button.");
				return result;
			}

			cfObj.scrollUtill(driver, 1, direction.DOWN);

			result = validateVideoDescriptionIcon(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to validate VideoDescriptionIcon.");
				return result;
			}

			result = validateVideoDownloadOnDifferentQuality(driver, packageTitle);
			if (!result) {
				videoSectionMsgList.add("Not able to validate VideoDownloadOnDifferentQuality.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("VideoCourse_Exception" + e.getMessage());

		} finally {
			result = cfObj.changeNetworkState(driver, NetworkState.ON);
			if (!result) {
				videoSectionMsgList.add("Not able to make mobile network off.");
				return result;
			}
		}
		return result;
	}

	public boolean clickOnPurchasedVideo(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			Thread.sleep(2000);
			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.doneCoachMarkBtn(), 5);
			if (result) {
				cfObj.commonClick(videoCoursesPageObj.doneCoachMarkBtn());
			} else {
				result = true;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'Video Courses')]",
					"xpath", 10);
			if (!result) {
				videoSectionMsgList.add("Video courses btn is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "//*[contains(@text,'Video Courses')]", "xpath"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'Your Courses')]",
					"xpath", 10);
			if (!result) {
				videoSectionMsgList.add("Your Courses heading is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.listOfCourses().get(0), 10);
			if (!result) {
				videoSectionMsgList.add("listOfCourses at 1st position is not visible.");
				return result;
			}

			cfObj.commonClick(videoCoursesPageObj.listOfCourses().get(0));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//*[contains(@resource-id,'video_course_title_head')]", "xpath", 10);
			if (!result) {
				videoSectionMsgList.add("Video course head title is not visible.");
				return result;
			}

			cfObj.commonClick(
					cfObj.commonGetElement(driver, "//*[contains(@resource-id,'video_course_title_head')]", "xpath"));

		} catch (Exception e) {
			videoSectionMsgList.add("PurchasedVideo_Exception" + e.getMessage());
			result = false;

		}
		return result;

	}

	public boolean validatePurchasedVideoCourseUI(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoShareBtn(), 10);
			if (!result) {
				videoSectionMsgList.add("VideoShareBtn is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getPlayerViewState(), 10);
			if (!result) {
				videoSectionMsgList.add("PlayerViewState is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoCourseImgList().get(0),
					10);
			if (!result) {
				videoSectionMsgList.add("VideoCourseImgList is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,
					videoCoursesPageObj.getVideoCourseImgIconList().get(0), 10);
			if (!result) {
				videoSectionMsgList.add("VideoCourseImgIconList is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoCourseTitleList().get(0),
					10);
			if (!result) {
				videoSectionMsgList.add("VideoCourseTitleList is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,
					videoCoursesPageObj.getVideoCourseSubTextList().get(0), 10);
			if (!result) {
				videoSectionMsgList.add("VideoCourseSubTextList is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getWatchTimerList().get(0), 10);
			if (!result) {
				videoSectionMsgList.add("WatchTimerList is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,
					videoCoursesPageObj.getVideoDescriptionIconList().get(0), 10);
			if (!result) {
				videoSectionMsgList.add("VideoDescriptionIconList is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,
					videoCoursesPageObj.getVideoDownloadIconList().get(0), 10);
			if (!result) {
				videoSectionMsgList.add("VideoDownloadIconList is not visible.");
				return result;
			}

		} catch (Exception e) {
			videoSectionMsgList.add("PurchasedVideoCourseUI_Exception" + e.getMessage());
			result = false;

		}
		return result;

	}

	public boolean validateVideoCourseShareBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoShareBtn(), 20);
			if (!result) {
				videoSectionMsgList.add("VideoShareBtn is not visible.");
				return result;
			}
			cfObj.commonClick(videoCoursesPageObj.getVideoShareBtn());
			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getShareScreen(), 30);
			if (!result) {
				videoSectionMsgList.add("Share screen is not visible.");
				return result;
			}

			cfObj.scrollUtill(driver, 1, direction.UP);

			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoShareBtn(), 20);
			if (!result) {
				videoSectionMsgList.add("Video Share button is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("validateVideoShareBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnPlayerViewStateBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getPlayerViewState(), 10);
			if (!result) {
				videoSectionMsgList.add("PlayerViewState is not visible.");
				return result;
			}
			cfObj.commonClick(videoCoursesPageObj.getPlayerViewState());

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("PlayerViewStateBtn_Exception" + e.getMessage());

		}
		return result;
	}

	public boolean validateVideoPauseAndPlayBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			// cfObj.commonClick(videoCoursesPageObj.getVideoPlaySection());
			//
			// result = cfObj.commonWaitForElementToBeVisible(driver,
			// videoCoursesPageObj.getVideoPauseBtn(), 30);
			// if (!result) {
			// videoSectionMsgList.add("VideoPauseBtn is not visible.");
			// return result;
			// }
			// cfObj.commonClick(videoCoursesPageObj.getVideoPauseBtn());
			// result = cfObj.commonWaitForElementToBeVisible(driver,
			// videoCoursesPageObj.getVideoPlayBtn(), 30);
			// if (!result) {
			// videoSectionMsgList.add("VideoPlayBtn is not visible.");
			// return result;
			// }

			result = validateVideoForwardReverseBtn(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to validate video forward reverse button.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoProgressBar(), 30);
			if (!result) {
				videoSectionMsgList.add("VideoProgressBar is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("ValidateVideoPauseAndPlayBtn_Exception" + e.getMessage());

		}
		return result;
	}

	public boolean validateVideoForwardReverseBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoReverseBtn(), 30);
			if (!result) {
				videoSectionMsgList.add("VideoReverseBtn is not visible.");
				return result;
			}
			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoForwardBtn(), 30);
			if (!result) {
				videoSectionMsgList.add("VideoForwardBtn is not visible.");
				return result;
			}
			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoPositiontext(), 30);
			if (!result) {
				videoSectionMsgList.add("VideoPositiontext is not visible.");
				return result;
			}
			int videoPosition = Integer.parseInt(videoCoursesPageObj.getVideoPositiontext().getText().split(":")[1]);
			System.out.println("Video Position:-->" + videoPosition);
			cfObj.commonClick(videoCoursesPageObj.getVideoForwardBtn());
			int forwardVideoPosition = Integer
					.parseInt(videoCoursesPageObj.getVideoPositiontext().getText().split(":")[1]);
			System.out.println("Forward Video Position:-->" + forwardVideoPosition);
			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
				result = videoPosition + 5 == forwardVideoPosition;
				if (!result) {
					videoSectionMsgList.add("VideoForwardBtn is not working.");
					return result;
				}

				cfObj.commonClick(videoCoursesPageObj.getVideoReverseBtn());
				int reverseVideoPosition = Integer
						.parseInt(videoCoursesPageObj.getVideoPositiontext().getText().split(":")[1]);
				System.out.println("Reverse Video Position:-->" + reverseVideoPosition);
				result = reverseVideoPosition == forwardVideoPosition - 5;
				if (!result) {
					videoSectionMsgList.add("VideoReverseBtn is not working.");
					return result;
				}
			} else {
				result = videoPosition + 15 == forwardVideoPosition;
				if (!result) {
					videoSectionMsgList.add("VideoForwardBtn is not working.");
					return result;
				}

				cfObj.commonClick(videoCoursesPageObj.getVideoReverseBtn());
				int reverseVideoPosition = Integer
						.parseInt(videoCoursesPageObj.getVideoPositiontext().getText().split(":")[1]);
				System.out.println("Reverse Video Position:-->" + reverseVideoPosition);
				result = reverseVideoPosition == forwardVideoPosition - 15;
				if (!result) {
					videoSectionMsgList.add("VideoReverseBtn is not working.");
					return result;
				}
			}

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("VideoForwardReverseBtn_Exception" + e.getMessage());

		}
		return result;

	}

	public boolean clickOnGotItBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			if (cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getGotItBtn(), 10)) {

				cfObj.commonClick(videoCoursesPageObj.getGotItBtn());
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'All Videos')]",
					"xpath", 10);
			if (!result) {
				videoSectionMsgList.add("All Videos text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getPurchasedVideoList().get(0),
					30);
			if (!result) {
				videoSectionMsgList.add("Purchased video list is not visible.");
				return result;
			}

			cfObj.commonClick(videoCoursesPageObj.getPurchasedVideoList().get(0));

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("GotItBtn_Exception" + e.getMessage());

		}
		return result;
	}

	public boolean handlePageLoader(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			int i = 0;
			while (cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getPageLoaderIcon(), 10)) {

				Thread.sleep(1000);
				i++;
				if (i > 4)
					break;

			}

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("handlePageLoader_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean validateVideoStreamOptionBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getStreamOptionBtn(), 30);
			if (!result) {
				videoSectionMsgList.add("StreamOptionBtn is not visible.");
				return result;
			}
			cfObj.commonClick(videoCoursesPageObj.getStreamOptionBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoQualityTitle(), 30);
			if (!result) {
				videoSectionMsgList.add("VideoQualityTitle is not visible.");
				return result;
			}
			result = videoCoursesPageObj.getAutoRadioBtn().getAttribute("checked").equals("true");
			if (!result) {
				videoSectionMsgList.add("AutoRadio button is not selected.");
				return result;
			}
			// result = validateVideoQualityRadioButtonState(driver,
			// videoCoursesPageObj.getPixel480RadioBtn(),"Pixel-480");
			// if (!result) {
			// System.out.println("Not able to validate Pixel-480 Radio button.");
			// return result;
			// }
			result = validateVideoQualityRadioButtonState(driver, videoCoursesPageObj.getPixel360RadioBtn(),
					"Pixel-360");
			if (!result) {
				videoSectionMsgList.add("Not able to validate Pixel-360 Radio button.");
				return result;
			}

			result = validateVideoQualityRadioButtonState(driver, videoCoursesPageObj.getPixel240RadioBtn(),
					"Pixel-240");
			if (!result) {
				videoSectionMsgList.add("Not able to validate Pixel-240 Radio button.");
				return result;
			}
			cfObj.commonClick(videoCoursesPageObj.getAutoRadioBtn());

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("ValidateVideoPauseAndPlayBtn_Exception" + e.getMessage());

		}
		return result;
	}

	public boolean validateVideoQualityRadioButtonState(AppiumDriver<MobileElement> driver, MobileElement element,
			String radioBtnType) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, element, 30);
			if (!result) {
				videoSectionMsgList.add(radioBtnType + " RadioButton is not visible.");
				return result;
			}
			cfObj.commonClick(element);
			cfObj.commonClick(videoCoursesPageObj.getStreamOptionBtn());
			result = element.getAttribute("checked").equals("true");
			if (!result) {
				videoSectionMsgList.add(radioBtnType + " RadioButton is not selected.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("VideoQualityRadioButtonState_Exception" + e.getMessage());

		}
		return result;
	}

	public boolean validateVideoPlayBackSpeedBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getPlayBackSpeedBtn(), 30);
			if (!result) {
				videoSectionMsgList.add("PlayBackSpeedBtn is not visible.");
				return result;
			}
			cfObj.commonClick(videoCoursesPageObj.getPlayBackSpeedBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getPlayBackSpeedTitle(), 30);
			if (!result) {
				videoSectionMsgList.add("PlayBackSpeedTitle is not visible.");
				return result;
			}
			result = videoCoursesPageObj.getSpeedNormalRadioBtn().getAttribute("checked").equals("true");
			if (!result) {
				videoSectionMsgList.add("SpeedNormalRadioBtn is not selected.");
				return result;
			}
			result = validateVideoPlayBackRadioButtonState(driver, videoCoursesPageObj.getSpeed075RadioBtn(),
					"Speed-075");
			if (!result) {
				videoSectionMsgList.add("Not able to validate Speed-075 Radio button.");
				return result;
			}
			result = validateVideoPlayBackRadioButtonState(driver, videoCoursesPageObj.getSpeed125RadioBtn(),
					"Speed-125");
			if (!result) {
				videoSectionMsgList.add("Not able to validate Speed-125 Radio button.");
				return result;
			}

			result = validateVideoPlayBackRadioButtonState(driver, videoCoursesPageObj.getSpeed15RadioBtn(),
					"Speed-150");
			if (!result) {
				videoSectionMsgList.add("Not able to validate Speed-150 Radio button.");
				return result;
			}
			result = validateVideoPlayBackRadioButtonState(driver, videoCoursesPageObj.getSpeed20RadioBtn(),
					"Speed-200");
			if (!result) {
				videoSectionMsgList.add("Not able to validate Speed-200 Radio button.");
				return result;
			}
			cfObj.commonClick(videoCoursesPageObj.getSpeedNormalRadioBtn());

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("VideoPlayBackSpeedBtn_Exception" + e.getMessage());

		}
		return result;
	}

	public boolean validateVideoPlayBackRadioButtonState(AppiumDriver<MobileElement> driver, MobileElement element,
			String radioBtnType) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, element, 30);
			if (!result) {
				videoSectionMsgList.add(radioBtnType + " RadioButton is not visible.");
				return result;
			}
			cfObj.commonClick(element);
			cfObj.commonClick(videoCoursesPageObj.getPlayBackSpeedBtn());
			result = element.getAttribute("checked").equals("true");
			if (!result) {
				videoSectionMsgList.add(radioBtnType + " RadioButton is not selected.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("VideoPlayBackRadioButtonState_Exception" + e.getMessage());

		}
		return result;
	}

	public boolean validateVideoFullScreenBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getFullScreenBtn(), 30);
			if (!result) {
				videoSectionMsgList.add("FullScreenbtn is not visible.");
				return result;
			}
			cfObj.commonClick(videoCoursesPageObj.getFullScreenBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getFullScreenLandBtn(), 30);
			if (!result) {
				videoSectionMsgList.add("FullScreenLandBtn is not visible.");
				return result;
			}
			cfObj.commonClick(videoCoursesPageObj.getFullScreenLandBtn());

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("VideoFullScreenBtn_Exception" + e.getMessage());

		}
		return result;
	}

	public boolean validateVideoDescriptionIcon(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver,
					videoCoursesPageObj.getVideoDescriptionIconList().get(0), 30);
			if (!result) {
				videoSectionMsgList.add("VideoDescriptionIconList is not visible.");
				return result;
			}
			cfObj.commonClick(videoCoursesPageObj.getVideoDescriptionIconList().get(0));

			result = cfObj.commonWaitForElementToBeVisible(driver,
					videoCoursesPageObj.getVideoDescriptionBottomSheetTitle(), 30);
			if (!result) {
				videoSectionMsgList.add("VideoDescriptionBottomSheetTitle is not visible.");
				return result;
			}
			cfObj.commonClick(videoCoursesPageObj.getVideoDescriptionBottomSheetCloseBtn());

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("VideoDescriptionIcon_Exception" + e.getMessage());

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
				if (i > 4)
					break;

			}

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("waitForCompleteDownload_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean navigateToVideoDownloadSection(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.pressAndroidKey(driver, key.BACK, 2);
			if (!result) {
				videoSectionMsgList.add("Unable to press android back key 2 times");
				return result;
			}
			result = myContentUtilObj.clickOnDownloadsBtn(driver);
			if (!result) {
				videoSectionMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}
			result = myContentUtilObj.clickOnVideosDownloadSection(driver);
			if (!result) {
				videoSectionMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("NavigateToVideoDownloadSection_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean navigateToPurchasedVideoSection(AppiumDriver<MobileElement> driver, String productTitle) {
		boolean result = true;
		try {
			result = cfObj.pressAndroidKey(driver, key.BACK, 1);
			if (!result) {
				videoSectionMsgList.add("Unable to press android back key 1 times");
				return result;
			}
			result = myContentUtilObj.clickOnPurchasedBtn(driver);
			if (!result) {
				videoSectionMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}

			result = myContentUtilObj.clickOnPackageInPurchasedSection(driver, productTitle);
			if (!result) {
				videoSectionMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}

			result = clickOnPurchasedVideo(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to click Purchased Video.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("NavigateToPurchasedVideoSection_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateDownloadedVideoIsPlayingWithInterNet(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			myContentUtilObj = new MyContentUtil(driver);
			result = myContentUtilObj.clickOnDownloadedVideo(driver);
			if (!result) {
				videoSectionMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}
			String buttonState = videoCoursesPageObj.getPlayerViewState().getText();
			cfObj.commonClick(videoCoursesPageObj.getPlayerViewState());
			result = cfObj.pressAndroidKey(driver, key.BACK, 1);
			if (!result) {
				videoSectionMsgList.add("Unable to press android back key 1 times");
				return result;
			}
			result = !(cfObj.commonVerifyValueTextBox(videoCoursesPageObj.getPlayerViewState(), buttonState));
			if (!result) {
				videoSectionMsgList.add("Not able to play downloaded video.");
				return result;
			}
			result = validateDownloadedVideoClassAndPlayingState(driver, NetworkState.ON);
			if (!result) {
				videoSectionMsgList.add("Unable to validate DownloadedVideoClassAndplayingState.");
				return result;
			}
			result = cfObj.pressAndroidKey(driver, key.BACK, 2);
			if (!result) {
				videoSectionMsgList.add("Unable to press android back key 2 times");
				return result;
			}

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("DownloadedVideoIsPlayingWithInterNet_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean validateDownloadedVideoClassAndPlayingState(AppiumDriver<MobileElement> driver,
			NetworkState networkState) {
		boolean result = true;
		try {

			cfObj.commonClick(videoCoursesPageObj.getVideoCourseTitleList().get(0));
			if (networkState.equals(NetworkState.ON)) {

				result = cfObj.commonVerifyValueTextBox(videoCoursesPageObj.getPlayingVideoTitle(),
						videoCoursesPageObj.getVideoCourseTitleList().get(0).getText());
				if (!result) {
					videoSectionMsgList.add("Clicked video is not playing.");
					return result;
				}
				result = cfObj.commonVerifyValueTextBox(videoCoursesPageObj.getWatchTimerList().get(0), "Playing");
				if (!result) {
					videoSectionMsgList.add("Watch timer text is not matching.");
					return result;
				}
			}
		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("DownloadedVideoClassAndplayingState_Exception" + e.getMessage());

		}
		return result;

	}

	public boolean validateDownloadedVideoIsPlayingWithOutInterNet(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.changeNetworkState(driver, NetworkState.OFF);
			if (!result) {
				videoSectionMsgList.add("Not able to make make mobile network off.");
				return result;
			}
			myContentUtilObj = new MyContentUtil(driver);
			result = myContentUtilObj.clickOnDownloadedVideo(driver);
			if (!result) {
				videoSectionMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}
			String buttonState = videoCoursesPageObj.getPlayerViewState().getText();
			cfObj.commonClick(videoCoursesPageObj.getPlayerViewState());
			result = cfObj.pressAndroidKey(driver, key.BACK, 1);
			if (!result) {
				videoSectionMsgList.add("Unable to press android back key 1 times");
				return result;
			}
			result = !(cfObj.commonVerifyValueTextBox(videoCoursesPageObj.getPlayerViewState(), buttonState));
			if (!result) {
				videoSectionMsgList.add("Not able to play downloaded video.");
				return result;
			}
			result = validateDownloadedVideoClassAndPlayingState(driver, NetworkState.OFF);
			if (!result) {
				videoSectionMsgList.add("Unable to validate DownloadedVideoClassAndplayingState.");
				return result;
			}
			result = cfObj.pressAndroidKey(driver, key.BACK, 2);
			if (!result) {
				videoSectionMsgList.add("Unable to press android back key 2 times");
				return result;
			}

			result = cfObj.changeNetworkState(driver, NetworkState.ON);
			if (!result) {
				videoSectionMsgList.add("Not able to make make mobile network ON.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("DownloadedVideoIsPlayingWithInterNet_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean validateVideoDownloadOnDifferentQuality(AppiumDriver<MobileElement> driver, String downloadedVideo) {
		boolean result = true;
		try {
			myContentUtilObj = new MyContentUtil(driver);
			result = cfObj.commonWaitForElementToBeVisible(driver,
					videoCoursesPageObj.getVideoDownloadIconList().get(0), 10);
			if (!result) {
				videoSectionMsgList.add("VideoDownloadIconList is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,
					videoCoursesPageObj.getVideoDownloadIconList().get(0), 10);
			if (!result) {
				videoSectionMsgList.add("VideoDownloadIconList is not visible.");
				return result;
			}
			cfObj.commonClick(videoCoursesPageObj.getVideoDownloadIconList().get(0));
			if (!(cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getPermissionAllowBtn(), 10))) {
				cfObj.commonClick(videoCoursesPageObj.getVideoDownloadIconList().get(0));
			}
			if (cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getPermissionAllowBtn(), 10)) {

				cfObj.commonClick(videoCoursesPageObj.getPermissionAllowBtn());
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getSelectDownloadQualityTitle(),
					10);
			if (!result) {
				videoSectionMsgList.add("SelectDownloadQualityTitle is not visible.");
				return result;
			}

			cfObj.commonClick(videoCoursesPageObj.getDownloadQualityList().get(0));
			result = videoCoursesPageObj.getDownloadQualityList().get(0).getAttribute("checked").equals("true");
			if (!result) {
				videoSectionMsgList.add("Download Quality RadioButton is not selected.");
				return result;
			}
			Thread.sleep(1000);
			cfObj.commonClick(videoCoursesPageObj.getDownloadBtn());

			result = cfObj.waitTillElementIsVisible(driver, 10, 2000, videoCoursesPageObj.getDownloadDeterminateBtn());
			if (!result) {
				videoSectionMsgList.add("Not able to download video completely.");
				return result;
			}

			result = clickOnGotItBtn(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to click GotIt button.");
				return result;
			}
			result = navigateToVideoDownloadSection(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to navigate VideoDownloadSection.");
				return result;
			}
			result = myContentUtilObj.validateDownloadedVideoIsPresent(driver, downloadedVideo);
			if (!result) {
				videoSectionMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}

			result = validateDownloadedVideoIsPlayingWithInterNet(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to validate DownloadedVideoIsPlayingWithInterNet.");
				return result;
			}

			result = validateDownloadedVideoIsPlayingWithOutInterNet(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to validate DownloadedVideoIsPlayingWithOutInterNet.");
				return result;
			}
			result = myContentUtilObj.clickOnDownloadedVideo(driver);
			if (!result) {
				videoSectionMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}
			result = validateDeleteActionOnDownloadedVideo(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to validate DeleteActionOnDownloadedVideo.");
				return result;
			}

			result = cfObj.pressAndroidKey(driver, key.BACK, 1);
			if (!result) {
				videoSectionMsgList.add("Unable to press android back key 1 times");
				return result;
			}

			result = myContentUtilObj.validateDownloadedVideoIsNotPresent(driver);
			if (!result) {
				videoSectionMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("VideoDownloadOnDifferentQuality_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean validateDeleteActionOnDownloadedVideo(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver,
					videoCoursesPageObj.getVideoDownloadIconList().get(0), 10);
			if (!result) {
				videoSectionMsgList.add("VideoDownloadIconList is not visible.");
				return result;
			}

			int downloadedVideoList = videoCoursesPageObj.getVideoDownloadIconList().size();
			for (int i = 0; i < downloadedVideoList; i++) {
				result = cfObj.commonWaitForElementToBeVisible(driver,
						videoCoursesPageObj.getVideoDownloadIconList().get(i), 10);
				if (!result) {
					videoSectionMsgList.add("VideoDownloadIconList is not visible.");
					return result;
				}
				cfObj.commonClick(videoCoursesPageObj.getVideoDownloadIconList().get(i));
				result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getDeleteBtn(), 10);
				if (!result) {
					videoSectionMsgList.add("Delete button is not visible.");
					return result;
				}
				cfObj.commonClick(videoCoursesPageObj.getDeleteBtn());
			}
		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("ValidateDeleteActionOnDownloadedVideo_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean validateVideoSectionPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoSectionTitle(), 30);
			if (!result) {
				videoSectionMsgList.add("Video section title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForListOfElementsToBeVisible(driver, videoCoursesPageObj.getVideoList(), 30);
			if (!result) {
				videoSectionMsgList.add("VideoList is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoList().get(0), 30);
			if (!result) {
				videoSectionMsgList.add("VideoList at 0 is not visible.");
				return result;
			}

			result = cfObj.commonWaitForListOfElementsToBeVisible(driver, videoCoursesPageObj.getVideoTimeText(), 30);
			if (!result) {
				videoSectionMsgList.add("VideoTimeText is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoTimeText().get(0), 30);
			if (!result) {
				videoSectionMsgList.add("VideoTimeText at 0 is not visible.");
				return result;
			}

			result = cfObj.commonWaitForListOfElementsToBeVisible(driver, videoCoursesPageObj.getVideoTitleList(), 30);
			if (!result) {
				videoSectionMsgList.add("getVideoTitleList is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoTitleList().get(0), 30);
			if (!result) {
				videoSectionMsgList.add("VideoTitleList at 0 is not visible.");
				return result;
			}

			result = cfObj.commonWaitForListOfElementsToBeVisible(driver, videoCoursesPageObj.getVideoLikeText(), 30);
			if (!result) {
				videoSectionMsgList.add("getVideoLikeText is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoLikeText().get(0), 30);
			if (!result) {
				videoSectionMsgList.add("VideoLikeText at 0 is not visible.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("validateVideoSectionPage_Exception " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnVideoSectionBackBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			cfObj.commonClick(videoCoursesPageObj.getBackBtn());

			result = !cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoSectionTitle(), 30);
			if (!result) {
				videoSectionMsgList.add("Video section title is visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("clickOnVideoSectionBackBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateVideosTagCount(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		TreeSet<String> videosTag = new TreeSet<>();
		try {
			Thread.sleep(3000);
			if (cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getCertificateLabelVideoTag(), 10)) {
				result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getProgressBarTextValue(),
						10);
				if (!result) {
					videoSectionMsgList.add("ProgressBar Text value is not visible.");
					return result;
				}
				String videoTagCount = videoCoursesPageObj.getProgressBarTextValue().getText().split(" ")[0];
				int index = videoTagCount.indexOf("/");
				System.out.println(index);
				System.out.println(videoTagCount.substring(index).replace("/", ""));
				int totalVideoTagCount = Integer.parseInt(videoTagCount.substring(index).replace("/", ""));
				System.out.println("TotalVideoTagCount:-->" + totalVideoTagCount);
				System.out.println(videoTagCount.substring(0, index));
				int watchedVideoTagCount = Integer.parseInt(videoTagCount.substring(0, index));
				System.out.println("Watched VideoTagCount:-->" + watchedVideoTagCount);
				for (int i = 0; i < totalVideoTagCount; i++) {
					for (int j = 0; j < videoCoursesPageObj.getVideosTag().size(); j++) {
						videosTag.add(videoCoursesPageObj.getVideosTag().get(j).getText());
					}
					cfObj.scrollUtill(driver, 1, direction.DOWN);
				}

				result = totalVideoTagCount == videosTag.size();
				if (!result) {
					videoSectionMsgList.add("VideoTag count is not matching.");
					return result;
				}
				Thread.sleep(1000);
			}

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("validateVideosTagCount_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateVideoCertificateCell(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getCertificateTitle(), 10);
			if (!result) {
				videoSectionMsgList.add("CertificateTitle is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getProgressBarTextValue(), 10);
			if (!result) {
				videoSectionMsgList.add("ProgressBar Text value is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getCertificateVideoWatchBtn(),
					10);
			if (!result) {
				videoSectionMsgList.add("ProgressBar Text value is not visible.");
				return result;
			}

			result = cfObj.commonVerifyValueTextBox(videoCoursesPageObj.getCertificateVideoWatchBtn(), "Watch");
			if (!result) {
				videoSectionMsgList.add("CertificateVideoWatchBtn text is not matching.");
				return result;
			}

			childPackageUtilObj = new ChildPackageUtil(driver);
			result = childPackageUtilObj.validateCertificatePercentage(driver);
			if (!result) {
				videoSectionMsgList.addAll(childPackageUtilObj.packagePageMsgList);
				return result;
			}

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("validateVideoCertificateCell_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateCertificateVideoWatchBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			String testTagCount = videoCoursesPageObj.getProgressBarTextValue().getText().split(" ")[0];
			int index = testTagCount.indexOf("/");
			System.out.println(index);
			int totalTestTagCount = Integer.parseInt(testTagCount.substring(index).replace("/", ""));
			System.out.println("Total TestTag:-->" + totalTestTagCount);

			cfObj.pressAndroidKey(driver, key.POWER, 2);

			for (int i = 0; i < totalTestTagCount; i++) {
				result = cfObj.commonWaitForElementToBeVisible(driver,
						videoCoursesPageObj.getCertificateVideoWatchBtn(), 10);
				if (!result) {
					videoSectionMsgList.add("CertificateVideoWatchBtn is not visible.");
					return result;
				}

				result = cfObj.commonVerifyValueTextBox(videoCoursesPageObj.getCertificateVideoWatchBtn(), "Watch");
				if (!result) {
					videoSectionMsgList.add("CertificateVideoWatchBtn is not visible.");
					return result;
				}

				cfObj.commonClick(videoCoursesPageObj.getCertificateVideoWatchBtn());

				Thread.sleep(1000);
				if (cfObj.commonVerifyValueTextBox(videoCoursesPageObj.getCertificateVideoWatchBtn(), "Watch")) {
					cfObj.commonClick(videoCoursesPageObj.getCertificateVideoWatchBtn());
				}

				result = cfObj.waitForPageLoading(driver, 3, 2000, videoCoursesPageObj.getVideoResumeBtn());
				if (!result) {
					videoSectionMsgList.add("Not able to load Resume button.");
					return result;
				}

				cfObj.pressAndroidKey(driver, key.POWER, 2);
				cfObj.tapOnElementEndpoint(driver, videoCoursesPageObj.getVideoProgressBar());

				// result=cfObj.commonWaitForElementToBeVisible(driver,
				// videoCoursesPageObj.getVideoStatusTag().get(0), 10);
				// if(!result) {
				// videoSectionMsgList.add("VideoStatusTag is not visible.");
				// return result;
				// }

				childPackageUtilObj = new ChildPackageUtil(driver);
				result = childPackageUtilObj.validateUpdatedCertificatePercentage(driver);
				if (!result) {
					videoSectionMsgList.addAll(childPackageUtilObj.packagePageMsgList);
					return result;
				}
			}

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("validateCertificateVideoWatchBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyLCSFeatureInVideoCourse(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String packageTitle;
		try {

			CreatePackageUtil createPackageUtil = new CreatePackageUtil();
			List<Integer> categorylist = new ArrayList<Integer>();
			List<Integer> conetntList = new ArrayList<>();
			PackageApiUtil packageApiUtil = new PackageApiUtil();
			categorylist.add(5);

			CreatePackageResponse createPackageResponse = createPackageUtil.createPackage(false, false, categorylist,
					2);
			if (createPackageResponse == null) {
				videoSectionMsgList.add("Not able to create package.");
				return false;
			}
			System.out.println("strPackageId: " + createPackageResponse.getData().getId());
			GetVideoList getVideoList = packageApiUtil
					.videoLinking(String.valueOf(createPackageResponse.getData().getId()));
			if (getVideoList == null) {
				videoSectionMsgList.add("Not able to Link video.");
				return false;
			}

			System.out.println(
					"VideoList:--->" + getVideoList.getData().getInputVideoStreamingBean().getVideoStreamingBean()
							.getBilingual().getCc().getSubject().get(0).getCh().get(0).getVideo().size());
			for (int i = 0; i < getVideoList.getData().getInputVideoStreamingBean().getVideoStreamingBean()
					.getBilingual().getCc().getSubject().get(0).getCh().get(0).getVideo().size() - 1; i++) {
				conetntList
						.add(Integer.valueOf(getVideoList.getData().getInputVideoStreamingBean().getVideoStreamingBean()
								.getBilingual().getCc().getSubject().get(0).getCh().get(0).getVideo().get(i).getUrl()));

			}

			packageTitle = createPackageResponse.getData().getTitle();
			if (packageTitle == null) {
				videoSectionMsgList.add("Package title is null.");
				return false;
			}
			System.out.println("Package Title:->" + packageTitle);
			loginUtilObj = new LoginUtil(driver);

			result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), true);
			if (!result) {
				videoSectionMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			homePageUtilObj = new HomePageUtil(driver);

			result = homePageUtilObj.clickStoreBtn(driver);
			if (!result) {
				videoSectionMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}
			storePageUtilObj = new StorePageUtil(driver);

			if (ConfigFileReader.strApplication.equalsIgnoreCase("Sankalp")) {
				result = storePageUtilObj.clickVideoCoursesIcon(driver);
				if (!result) {
					videoSectionMsgList.addAll(storePageUtilObj.storePageMsgList);
					return result;
				}
				result = cfObj.pressAndroidKey(driver, key.BACK, 1);
				if (!result) {
					videoSectionMsgList.add("Not able to click back button..");
					return result;
				}
			}

			result = storePageUtilObj.purchasePackage(driver,
					packageTitle.split(" ")[packageTitle.split(" ").length - 1], ProductType.VIDEOS);
			if (!result) {
				videoSectionMsgList.add("purchasePackage failed");
				return result;
			}

			orderSuccessUtilObj = new OrderSuccessUtil(driver);
			result = orderSuccessUtilObj.handleRateUsPopUpWindow(driver);
			if (!result) {
				videoSectionMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
				return result;
			}
			result = orderSuccessUtilObj.verifyOrderSuccessPage(driver);
			if (!result) {
				videoSectionMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
				return result;
			}

			result = cfObj.pressAndroidKey(driver, key.BACK, 3);
			if (!result) {
				videoSectionMsgList.add("Not able to click back button 3 times.");
				return result;
			}

			result = homePageUtilObj.clickMyContentButton(driver);
			if (!result) {
				videoSectionMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			myContentUtilObj = new MyContentUtil(driver);

			result = myContentUtilObj.clickOnPurchasedBtn(driver);
			if (!result) {
				videoSectionMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}

			result = myContentUtilObj.clickOnPackageInPurchasedSection(driver, packageTitle);
			if (!result) {
				videoSectionMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}

			result = clickOnPurchasedVideo(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to click Purchased Video.");
				return result;
			}

			result = clickOnGotItBtn(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to click GotIt button.");
				return result;
			}

			result = clickOnPlayerViewStateBtn(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to click On PlayerViewStateBtn.");
				return result;
			}

			result = validateLCSSection(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to validate LCSSection.");
				return result;
			}

			result = validateVideoLikeBtn(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to validate VideoLikeBtn.");
				return result;
			}

			result = validateVideoDisLikeBtn(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to validate VideoDisLikeBtn.");
				return result;
			}

			result = validateVideoCommentTextBox(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to validate VideoCommentTextBox.");
				return result;
			}

			result = validateVideoPdfButton(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to validate VideoPdfButton.");
				return result;
			}

			result = validateVideoDownloadBtn(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to validate VideoDownloadBtn.");
				return result;
			}

			result = cfObj.pressAndroidKey(driver, key.BACK, 2);
			if (!result) {
				videoSectionMsgList.add("Unable to press android back key 2 times");
				return result;
			}

			result = validateDownloadedVideo(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to validate DownloadVideo.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("verifyLCSFeatureInVideoCourse_Exception" + e.getMessage());

		}
		return result;
	}

	public boolean validateLCSSection(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoLikeBtn(), 30);
			if (!result) {
				videoSectionMsgList.add("VideoLikeBtn is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoDisLikeBtn(), 30);
			if (!result) {
				videoSectionMsgList.add("VideoDisLikeBtn is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoPdfBtn(), 30);
			if (!result) {
				videoSectionMsgList.add("VideoPdfBtn is not visible.");
				// return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoCommentBox(), 30);
			if (!result) {
				videoSectionMsgList.add("VideoCommentBox is not visible.");
				// return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoDownloadBtn(), 30);
			if (!result) {
				videoSectionMsgList.add("VideoDownloadBtn is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("validateLCSSection_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateVideoLikeBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoLikeBtn(), 30);
			if (!result) {
				videoSectionMsgList.add("VideoLikeBtn is not visible.");
				return result;
			}

			if (cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoLikeCount(), 30)) {

				int likeCount = Integer.parseInt(videoCoursesPageObj.getVideoLikeCount().getText());

				cfObj.commonClick(videoCoursesPageObj.getVideoLikeBtn());
				Thread.sleep(1000);
				result = Integer.parseInt(videoCoursesPageObj.getVideoLikeCount().getText()) == likeCount + 1;
				if (!result) {
					videoSectionMsgList.add("Not able to add Video Like count.");
					return result;
				}

				cfObj.commonClick(videoCoursesPageObj.getVideoLikeBtn());
				Thread.sleep(1000);
				result = Integer.parseInt(videoCoursesPageObj.getVideoLikeCount().getText()) == likeCount;
				if (!result) {
					videoSectionMsgList.add("Not able to remove Video Like count.");
					return result;
				}
			} else {
				cfObj.commonClick(videoCoursesPageObj.getVideoLikeBtn());
				Thread.sleep(1000);
				result = Integer.parseInt(videoCoursesPageObj.getVideoLikeCount().getText()) == 1;
				if (!result) {
					videoSectionMsgList.add("Not able to add Video Like count.");
					return result;
				}

				cfObj.commonClick(videoCoursesPageObj.getVideoLikeBtn());
				Thread.sleep(1000);
				result = !cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoLikeCount(), 10);
				if (!result) {
					videoSectionMsgList.add("Not able to remove Video Like count.");
					return result;
				}
			}

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("validateVideoLikeBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateVideoDisLikeBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = clickOnVideoDisLikeBtn(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to click VideoDisLikeBtn.");
				return result;
			}
			result = validateRatingPopUp(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to validate RatingPopUp.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("validateVideoDisLikeBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnVideoDisLikeBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoDisLikeBtn(), 30);
			if (!result) {
				videoSectionMsgList.add("VideoDisLikeBtn is not visible.");
				return result;
			}
			cfObj.commonClick(videoCoursesPageObj.getVideoDisLikeBtn());

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("clickOnVideoDisLikeBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean setFacultyAndContentRating(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getStarRatingImg().get(0), 30);
			if (!result) {
				videoSectionMsgList.add("StarRatingImg is not visible.");
				return result;
			}

			cfObj.commonClick(videoCoursesPageObj.getStarRatingImg().get(1));
			Thread.sleep(1000);
			cfObj.commonClick(videoCoursesPageObj.getRatingSubmitBtn());
			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getStarRatingImg().get(0), 30);
			if (!result) {
				videoSectionMsgList.add("Submit button is not in Disable state.");
				return result;
			}

			cfObj.commonClick(videoCoursesPageObj.getStarRatingImg().get(6));
			Thread.sleep(1000);
			cfObj.commonClick(videoCoursesPageObj.getRatingSubmitBtn());
			result = !cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getFacultyRatingTitle(), 5);
			if (!result) {
				videoSectionMsgList.add("Not able to click Submit button.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("setFacultyAndContentRating_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean setRatingFeedBack(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getNotLikeReasonList().get(0),
					30);
			if (!result) {
				videoSectionMsgList.add("VideoDisLikeBtn is not visible.");
				return result;
			}
			int reasonList = videoCoursesPageObj.getNotLikeReasonList().size() - 2;
			for (int i = 0; i < reasonList; i++) {
				cfObj.commonClick(videoCoursesPageObj.getNotLikeReasonList().get(i));
			}

			if (cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getFeedBackTextField(), 30)) {
				result = cfObj.commonSetTextTextBox(videoCoursesPageObj.getFeedBackTextField(), "TestingFeedBack");
				if (!result) {
					videoSectionMsgList.add("Not able to enter FeedBack.");
					return result;
				}
			}

			cfObj.commonClick(videoCoursesPageObj.getRatingSubmitBtn());

			if (cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getCongratPopUpTitle(), 10)) {
				cfObj.commonClick(videoCoursesPageObj.getCongratPopUpCloseBtn());

			} else {
				System.out.println("Congratulation PopUp screen is not visible.");
			}

			Thread.sleep(1000);
			result = !cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getRatingSubmitBtn(), 5);
			if (!result) {
				videoSectionMsgList.add("Submit button is not Clickable.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("validateVideoLikeBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateRatingPopUp(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getRatingPopUpPageTitle(), 30);
			if (!result) {
				videoSectionMsgList.add("RatingPopUpPageTitle is not visible.");
				return result;
			}
			cfObj.commonClick(videoCoursesPageObj.getRatingPopUpCloseBtn());
			result = clickOnVideoDisLikeBtn(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to click video DisLike button.");
				return result;
			}
			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getRatingSkipBtn(), 30);
			if (!result) {
				videoSectionMsgList.add("RatingSkipBtn is not visible.");
				return result;
			}

			cfObj.commonClick(videoCoursesPageObj.getRatingSkipBtn());
			result = clickOnVideoDisLikeBtn(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to click video DisLike button.");
				return result;
			}

			result = setFacultyAndContentRating(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to set FacultyAndContentRating.");
				return result;
			}
			result = setRatingFeedBack(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to set RatingFeedBack.");
				return result;
			}

			// result=clickOnVideoDisLikeBtn(driver);
			// if (!result) {
			// videoSectionMsgList.add("Not able to click video DisLike button.");
			// return result;
			// }
			// Thread.sleep(1000);
			// result =
			// !cfObj.commonWaitForElementToBeVisible(driver,videoCoursesPageObj.getRatingPopUpPageTitle(),
			// 5);
			// if (!result) {
			// videoSectionMsgList.add("Not able give Rating.");
			// return result;
			// }

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("validateRatingPopUp_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnVideoCommentTextBox(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoCommentBox(), 30);
			if (!result) {
				videoSectionMsgList.add("VideoCommentBox is not visible.");
				return result;
			}
			cfObj.commonClick(videoCoursesPageObj.getVideoCommentBox());
			Thread.sleep(1000);
			result = !cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoCommentBox(), 5);
			if (!result) {
				videoSectionMsgList.add("VideoComment textbox is not clickable.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("clickOnVideoCommentTextBox_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnSpecificUnpurchasedPackage(AppiumDriver<MobileElement> driver, String packageName) {
		boolean result = true;
		try {

			int i = 0;
			while (!cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//*[contains(@text,'" + packageName + "')]", "xpath", 5)) {
				cfObj.scrollUtill(driver, 1, direction.DOWN);
				if (i > 10) {
					System.out.println("Package is not available.");
					break;
				}
				i++;
			}
			cfObj.commonClick(driver, "//*[contains(@text,'" + packageName + "')]", "xpath");

			Thread.sleep(1000);

			purchasePackageUtilObj = new PurchasePackageUtil(driver);
			result = purchasePackageUtilObj.verifyUnpurchasedPackagePage(driver);
			if (!result) {
				videoSectionMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
				return result;
			}

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("clickOnSpecificUnpurchasedPackage_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean validateVideoCommentTextBox(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			commentPageUtilObj = new CommentPageUtil(driver);
			if (cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoCommentCount(), 10)) {
				int commentCount = Integer.parseInt(videoCoursesPageObj.getVideoCommentCount().getText());
				System.out.println("Comment Count:-->" + commentCount);
				result = clickOnVideoCommentTextBox(driver);
				if (!result) {
					videoSectionMsgList.add("Not able to click VideoCommentTextBox.");
					return result;
				}

				result = commentPageUtilObj.validateVideoCommentPage(driver);
				if (!result) {
					videoSectionMsgList.addAll(commentPageUtilObj.commentPageMsgList);
					return result;
				}

				result = Integer.parseInt(videoCoursesPageObj.getVideoCommentCount().getText()) == commentCount + 1;
				if (!result) {
					videoSectionMsgList.add("Comment count is not Update.");
					return result;
				}
			} else {

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "comment_btn", "id", 10);
				if (!result) {
					videoSectionMsgList.add("Comment button is not visible.");
					return result;
				}
				cfObj.commonClick(cfObj.commonGetElement(driver, "comment_btn", "id"));
				// result=clickOnVideoCommentTextBox(driver);
				// if(!result) {
				// videoSectionMsgList.add("Not able to click VideoCommentTextBox.");
				// return result;
				// }

				result = commentPageUtilObj.validateVideoCommentPage(driver);
				if (!result) {
					videoSectionMsgList.addAll(commentPageUtilObj.commentPageMsgList);
					return result;
				}

				result = Integer.parseInt(videoCoursesPageObj.getVideoCommentCount().getText()) == 1;
				if (!result) {
					videoSectionMsgList.add("Comment count is not Update.");
					return result;
				}

			}
		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("validateVideoCommentTextBox_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean validateVideoPdfButton(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		int handOutsList = 0;
		try {
			if (cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoPdfBtn(), 10)) {

				cfObj.commonClick(videoCoursesPageObj.getVideoPdfBtn());

				liveClassesPageUtilObj = new LiveClassesPageUtil(driver);
				if (cfObj.commonWaitForElementToBeVisible(driver, liveClassesPageUtilObj.liveClassPageObj.getClassNotesTitle(), 10)) {
					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='Class Notes']/following-sibling::android.view.View", "xpath", 30);
					if (!result) {
						videoSectionMsgList.add("PDF Cell is not visible.");
						return result;
					}
					cfObj.commonClick(liveClassesPageUtilObj.liveClassPageObj.getPdfCell().get(0));
					if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='Copyright Statement']", "xpath", 10)) {
						cfObj.commonClick(driver, "//*[@text='ACCEPT']", "xpath");
					}
					Thread.sleep(3000);
					result = cfObj.commonWaitForElementToBeVisible(driver, liveClassesPageUtilObj.liveClassPageObj.getPdfScreen(), 30);
					if (!result) {
						videoSectionMsgList.add("PDF Screen is not visible.");
						return result;
					}
					cfObj.pressAndroidKey(driver, key.BACK, 2);
				} else if (cfObj.commonWaitForElementToBeVisible(driver, liveClassesPageUtilObj.liveClassPageObj.getHandOutsTitle(), 10)) {

					handOutsList = liveClassesPageUtilObj.liveClassPageObj.getHandOutCell().size();
					cfObj.commonClick(liveClassesPageUtilObj.liveClassPageObj.getHandOutCell().get(0));
					result = cfObj.commonWaitForElementToBeVisible(driver, liveClassesPageUtilObj.liveClassPageObj.getHandOutPdfDownLoadBtn(), 10);
					if (!result) {
						videoSectionMsgList.add("HandOutPdfDownLoadBtn is not visible.");
						return result;
					}
					cfObj.commonClick(liveClassesPageUtilObj.liveClassPageObj.getHandOutPdfDownLoadBtn());
					if (cfObj.commonWaitForElementToBeVisible(driver, liveClassesPageUtilObj.liveClassPageObj.getPermissionAllowBtn(), 10)) {
						cfObj.commonClick(liveClassesPageUtilObj.liveClassPageObj.getPermissionAllowBtn());
					}
					result = !(cfObj.commonWaitForElementToBeVisible(driver, liveClassesPageUtilObj.liveClassPageObj.getHandOutPdfDownLoadBtn(), 10));
					if (!result) {
						videoSectionMsgList.add("HandOutPdfDownLoadBtn is visible.");
						return result;
					}
					cfObj.commonClick(liveClassesPageUtilObj.liveClassPageObj.getPdfExitBtn());
					Thread.sleep(2000);
					cfObj.commonClick(videoCoursesPageObj.getVideoPdfBtn());
					result = cfObj.commonWaitForElementToBeVisible(driver, liveClassesPageUtilObj.liveClassPageObj.getHandOutsTitle(), 10);
					if (!result) {
						videoSectionMsgList.add("HandOutsTitle is not visible.");
						return result;
					}
					cfObj.commonClick(liveClassesPageUtilObj.liveClassPageObj.getHandOutDownLoadBtn().get(0));
					result = cfObj.commonWaitForElementToBeVisible(driver, liveClassesPageUtilObj.liveClassPageObj.getDeleteBtn(), 10);
					if (!result) {
						videoSectionMsgList.add("DeleteBtn is not visible.");
						return result;
					}
					cfObj.commonClick(liveClassesPageUtilObj.liveClassPageObj.getDeleteBtn());
					result = cfObj.commonWaitForElementToBeVisible(driver, liveClassesPageUtilObj.liveClassPageObj.getHandOutsTitle(), 10);
					if (!result) {
						videoSectionMsgList.add("HandOutsTitle is not visible.");
						return result;
					}
					cfObj.commonClick(liveClassesPageUtilObj.liveClassPageObj.getHandOutCell().get(0));
					result = cfObj.commonWaitForElementToBeVisible(driver, liveClassesPageUtilObj.liveClassPageObj.getHandOutPdfDownLoadBtn(), 10);
					if (!result) {
						videoSectionMsgList.add("HandOutPdfDownLoadBtn is not visible.");
						return result;
					}
					cfObj.commonClick(liveClassesPageUtilObj.liveClassPageObj.getPdfExitBtn());
				} else {
					System.out.println("PDF is not available.");
				}
			} else {
				videoSectionMsgList.add("Video PDF button is not visible.");
			}
		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("validateVideoPdfButton_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateVideoDownloadBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getVideoDownloadBtn(), 10);
			if (!result) {
				videoSectionMsgList.add("VideoDownloadBtn is not visible.");
				return result;
			}
			cfObj.commonClick(videoCoursesPageObj.getVideoDownloadBtn());

			if (!(cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getPermissionAllowBtn(), 10))) {
				cfObj.commonClick(videoCoursesPageObj.getVideoDownloadIconList().get(0));
			}
			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getPermissionAllowBtn(), 30);
			if (!result) {
				videoSectionMsgList.add("PermissionAllowBtn is not visible.");
				return result;
			}
			cfObj.commonClick(videoCoursesPageObj.getPermissionAllowBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, videoCoursesPageObj.getSelectDownloadQualityTitle(),
					10);
			if (!result) {
				videoSectionMsgList.add("SelectDownloadQualityTitle is not visible.");
				return result;
			}

			cfObj.commonClick(videoCoursesPageObj.getDownloadQualityList().get(0));
			result = videoCoursesPageObj.getDownloadQualityList().get(0).getAttribute("checked").equals("true");
			if (!result) {
				videoSectionMsgList.add("Download Quality RadioButton is not selected.");
				return result;
			}
			cfObj.commonClick(videoCoursesPageObj.getDownloadBtn());
			Thread.sleep(2000);
			result = cfObj.waitTillElementIsVisible(driver, 10, 4000, videoCoursesPageObj.getDownloadDeterminateBtn());
			if (!result) {
				videoSectionMsgList.add("Not able to download video completely.");
				return result;
			}
			Thread.sleep(2000);
			result = clickOnGotItBtn(driver);
			if (!result) {
				videoSectionMsgList.add("Not able to click GotIt button.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("validateVideoDownloadBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateDownloadedVideo(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			myContentUtilObj = new MyContentUtil(driver);

			result = myContentUtilObj.clickOnDownloadsBtn(driver);
			if (!result) {
				videoSectionMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}
			result = myContentUtilObj.clickOnVideosDownloadSection(driver);
			if (!result) {
				videoSectionMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}
			result = myContentUtilObj.validateDownloadedVideo(driver);
			if (!result) {
				videoSectionMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}
		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("validateDownloadedVideo_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyLCSFeatureOnPurchasedVideoCourse(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String packageTitle;
		try {
			packageTitle = "DNC New Mahapack 1";

			loginUtilObj = new LoginUtil(driver);

			result = loginUtilObj.verifyLoginGuestUser(driver);
			if (!result) {
				videoSectionMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			homePageUtilObj = new HomePageUtil(driver);

			result = homePageUtilObj.clickStoreBtn(driver);
			if (!result) {
				videoSectionMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			result = homePageUtilObj.clickMyContentButton(driver);
			if (!result) {
				videoSectionMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			myContentUtilObj = new MyContentUtil(driver);

			result = myContentUtilObj.clickOnPurchasedBtn(driver);
			if (!result) {
				videoSectionMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}

			// later will remove below line
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.ImageView[contains(@content-desc,'" + packageTitle + "')]", "xpath", 10);
			if (!result) {
				videoSectionMsgList.add(packageTitle + " Package is not available.");
				return result;
			}
			cfObj.commonClick(cfObj.commonGetElement(driver,
					"//android.widget.ImageView[contains(@content-desc,'" + packageTitle + "')]", "xpath"));

//			result = myContentUtilObj.clickOnPackageInPurchasedSection(driver, packageTitle);
//			if (!result) {
//				videoSectionMsgList.addAll(myContentUtilObj.myContentMsgList);
//				return result;
//			}

			Thread.sleep(2000);
			batchUtilObj = new BatchUtil(driver);

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.Button[@content-desc='Apply']", "xpath", 10)) {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.widget.Button[@content-desc='Apply']", "xpath", 10);
				if (!result) {
					videoSectionMsgList.add("Apply button is not visible.");
					return result;
				}

				result = cfObj.commonGetElement(driver, "//android.widget.Button[@content-desc='Apply']", "xpath")
						.getAttribute("enabled").equalsIgnoreCase("false");
				if (!result) {
					videoSectionMsgList.add("Apply button should be on disable state.");
					return result;
				}

				cfObj.commonClick(cfObj.commonGetElement(driver, "android.widget.CheckBox", "class"));

				result = cfObj.commonGetElement(driver, "android.widget.CheckBox", "class").getAttribute("checked")
						.equalsIgnoreCase("true");
				if (!result) {
					videoSectionMsgList.add("Failed to select CheckBox.");
					return result;
				}

				result = cfObj.commonGetElement(driver, "//android.widget.Button[@content-desc='Apply']", "xpath")
						.getAttribute("enabled").equalsIgnoreCase("true");
				if (!result) {
					videoSectionMsgList.add("Apply button should be on enable state.");
					return result;
				}

				cfObj.commonClick(
						cfObj.commonGetElement(driver, "//android.widget.Button[@content-desc='Apply']", "xpath"));
			}
			Thread.sleep(2000);

			result = batchUtilObj.validateMahaPackScreen(driver, packageTitle);
			if (!result) {
				videoSectionMsgList.add("Failed to validate MahaPackScreen.");
				return result;
			}

			String examName = cfObj.commonGetElements(driver,
					"//android.view.View[@content-desc='Your Exams']/following-sibling::android.view.View/descendant::android.widget.ImageView",
					"xpath").get(0).getAttribute("content-desc");

			cfObj.commonClick(cfObj.commonGetElement(driver,
					"//android.view.View[@content-desc='Your Exams']/following-sibling::android.view.View/descendant::android.widget.ImageView",
					"xpath"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.ImageView[@content-desc='" + examName + "']", "xpath", 10);
			if (!result) {
				videoSectionMsgList.add("Exam Screen title is not visible.");
				return result;
			}

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.ImageView[@content-desc='DNC New Parent 2']", "xpath", 10)) {
				cfObj.commonClick(cfObj.commonGetElement(driver,
						"//android.widget.ImageView[@content-desc='DNC New Parent 2']", "xpath"));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.view.View[contains(@content-desc,'Video Course')]", "xpath", 10);
				if (!result) {
					videoSectionMsgList.add("Video Course section is not visible.");
					return result;
				}

				cfObj.commonClick(cfObj.commonGetElement(driver,
						"//android.view.View[contains(@content-desc,'Video Course')]", "xpath"));

				result = cfObj.commonGetElement(driver, "//android.view.View[contains(@content-desc,'Video Course')]",
						"xpath").getAttribute("selected").equalsIgnoreCase("true");
				if (!result) {
					videoSectionMsgList.add("Failed to click Video Course section.");
					return result;
				}

				if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.view.View[@content-desc='Subjects']/preceding-sibling::android.view.View/descendant::android.widget.ImageView",
						"xpath", 10)) {

					cfObj.commonClick(cfObj.commonGetElements(driver,
							"//android.view.View[@content-desc='Subjects']/preceding-sibling::android.view.View/descendant::android.widget.ImageView",
							"xpath").get(0));

					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
							"//android.view.View[@content-desc='btn_appbar_back']/parent::android.view.View/following-sibling::android.view.View/descendant::android.widget.ImageView[@content-desc]",
							"xpath", 10);
					if (!result) {
						videoSectionMsgList.add("Failed to open Subject_Content list screen.");
						return result;
					}

					cfObj.commonClick(cfObj.commonGetElements(driver,
							"//android.view.View[@content-desc='btn_appbar_back']/parent::android.view.View/following-sibling::android.view.View/descendant::android.widget.ImageView[@content-desc]",
							"xpath").get(0));

//					result = validateLCSSection(driver);
//					if (!result) {
//						videoSectionMsgList.add("Not able to validate LCSSection.");
//						return result;
//					}
//
//					result = validateVideoLikeBtn(driver);
//					if (!result) {
//						videoSectionMsgList.add("Not able to validate VideoLikeBtn.");
//						return result;
//					}
//
//					result = validateVideoDisLikeBtn(driver);
//					if (!result) {
//						videoSectionMsgList.add("Not able to validate VideoDisLikeBtn.");
//						return result;
//					}
//
//					result=validateVideoCommentTextBox(driver);
//					if (!result) {
//						videoSectionMsgList.add("Not able to validate VideoCommentTextBox.");
//						return result;
//					}
//
//					result=validateVideoPdfButton(driver);
//					if (!result) {
//						videoSectionMsgList.add("Not able to validate VideoPdfButton.");
//						return result;
//					}
//
//					result=validateVideoDownloadBtn(driver);
//					if (!result) {
//						videoSectionMsgList.add("Not able to validate VideoDownloadBtn.");
//						return result;
//					}
//
//					result = cfObj.pressAndroidKey(driver, key.BACK, 2);
//					if (!result) {
//						videoSectionMsgList.add("Unable to press android back key 2 times");
//						return result;
//					}
//
//					result=validateDownloadedVideo(driver);
//					if(!result) {
//						videoSectionMsgList.add("Not able to validate DownloadVideo.");
//						return result;
//					}
				}
			}

		} catch (Exception e) {
			result = false;
			videoSectionMsgList.add("verifyLCSFeatureInVideoCourse_Exception" + e.getMessage());

		}
		return result;
	}
}
