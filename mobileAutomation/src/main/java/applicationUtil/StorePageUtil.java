package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import apiUtill.OtpUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.StorePage_OR;
import util.Common_Function;
import util.Common_Function.direction;
import util.Common_Function.key;
import util.ConfigFileReader;

public class StorePageUtil {
	int index = 0;

	ProductPageUtil productPageUtilObj;

	ConfigFileReader configFileReaderObj = new ConfigFileReader();
	FeedbackFormUtil feedbackFormUtilObj;
	RegisterNewUserUtil registrationUserUtilObj;
	PurchasePackageUtil purchasePackageUtilObj;
	LoginUtil loginUtilObj;
	BooksPageUtil booksUtilObj;
	VideoCoursesPageUtil videoCoursesUtilObj;
	LiveClassesPageUtil liveClassUtilObj;
	TestSeriesPageUtil testSeriesUtilObj;
	UserDetailsLayerUtil userDetailsLayerUtilObj;
	MyOrdersPageUtil myOrderPageUtilObj;
	OrderSuccessUtil orderSuccessUtilObj;
	PriceDetailsPageUtil priceDetailUtilObj;
	MyContentUtil myContentUtilObj;
	HomePageUtil homeUtilObj;
	EBooksPageUtil eBooksUtilObj;
	StorePage_OR storePageObj;
	BatchUtil batchUtilObj;
	PaymentUtil paymentUtilObj;
	OtpUtil otpUtilObj;
	public ArrayList<String> storePageMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();

	public StorePageUtil(AppiumDriver<MobileElement> driver) {
		storePageObj = new StorePage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), storePageObj);

	}

	public boolean verifyPackagePurchase(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String strPackageTitle;

		try {
			if (ConfigFileReader.strEnv.equalsIgnoreCase("PRODUCTION")) {
				strPackageTitle = "E-Study Notes";
			} else {
				if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
					strPackageTitle = "Ebook TEST";
				} else {
					strPackageTitle = "AB Mahapack";
				}
			}

			loginUtilObj = new LoginUtil(driver);

			result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), true);
			if (!result) {
				storePageMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			homeUtilObj = new HomePageUtil(driver);

			result = homeUtilObj.clickStoreBtn(driver);
			if (!result) {
				storePageMsgList.add("Unable to open Store Page");
				return result;
			}

			result = purchasePackage(driver, strPackageTitle, ProductType.EBOOKS);
			if (!result) {
				storePageMsgList.add("purchasePackage failed");
				return result;
			}
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				if (ConfigFileReader.strEnv.equalsIgnoreCase("STAGING")) {
					orderSuccessUtilObj = new OrderSuccessUtil(driver);
					if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
//						String orderId = orderSuccessUtilObj.getOrderId();
//						if (orderId.isEmpty()) {
//							storePageMsgList.add("Unable to store order Id");
//							return false;
//						}

						result = orderSuccessUtilObj.clickViewLink(driver);
						if (!result) {
							storePageMsgList.add("Unable to click view link");
							return result;
						}

						myOrderPageUtilObj = new MyOrdersPageUtil(driver);
//						result = myOrderPageUtilObj.checkOrderIdInMyOrders(driver, orderId);
//						if (!result) {
//							storePageMsgList.add("Unable to find order Id in my orders");
//							return result;
//						} else {
//							result = myOrderPageUtilObj.verifyOrderStatus(driver);
//							if (!result) {
//								storePageMsgList.add("Order status is not success");
//								return result;
//							}
//						}
					}
				}
			}

		} catch (Exception e) {
			result = false;
			storePageMsgList.add("verifyEBooks Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean purchaseEBook(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = clickEBooksIcon(driver);
			if (!result) {
				storePageMsgList.add("Unable to open EBooks page");
				return result;
			}

			eBooksUtilObj = new EBooksPageUtil(driver);
			result = eBooksUtilObj.clickUnpurchasedPackage(driver);
			if (!result) {
				storePageMsgList.add("Unable to find and open unpurchased package");
				return result;
			}

			purchasePackageUtilObj = new PurchasePackageUtil(driver);

			result = purchasePackageUtilObj.applyCouponCode(driver, configFileReaderObj.getCoupon());
			if (!result) {
				storePageMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
				return result;
			}

			result = purchasePackageUtilObj.clickBuyNowBtn(driver);
			if (!result) {
				storePageMsgList.add("Unable to click buy now btn");
				return result;
			}

			userDetailsLayerUtilObj = new UserDetailsLayerUtil(driver);

			result = userDetailsLayerUtilObj.fillUserDetailsForm(driver);
			if (!result) {
				storePageMsgList.addAll(userDetailsLayerUtilObj.userDetailsLayerMsgList);
				return result;
			}

			result = userDetailsLayerUtilObj.clickContinueBtn(driver);
			if (!result) {
				storePageMsgList.addAll(userDetailsLayerUtilObj.userDetailsLayerMsgList);
				return result;
			}

			feedbackFormUtilObj = new FeedbackFormUtil(driver);

			result = feedbackFormUtilObj.skipFeedbackForm(driver);
			if (!result) {
				storePageMsgList.addAll(feedbackFormUtilObj.feedbackFormMsgList);
				storePageMsgList.add("Unable to skip feedback form");
			}

		} catch (Exception e) {
			result = false;
			storePageMsgList.add("downloadEBook_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean purchasePackage(AppiumDriver<MobileElement> driver, String strTitlePackage,
			ProductType productType) {
		boolean result = true;
		try {

			result = clickSearchIcon(driver);
			if (!result) {
				storePageMsgList.add("Unable to click search icon in store page");
				return result;
			}

			result = enterPackageNameInSearchField(driver, strTitlePackage);
			if (!result) {
				storePageMsgList.add("Unable to enter package name is search field");
				return result;
			}

			result = selectProductType(driver, productType);
			if (!result) {
				storePageMsgList.add("Unable to select product type from filter");
				return result;
			}

			result = clickPackageNameInSearchResult(driver, strTitlePackage);
			if (!result) {
				storePageMsgList.add("Unable to find open package form search field");
				return result;
			}

			purchasePackageUtilObj = new PurchasePackageUtil(driver);
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {

				result = purchasePackageUtilObj.clickBuyNowBtn(driver);
				if (!result) {
					storePageMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
					return result;
				}

				if (productType == ProductType.BOOKS || productType == ProductType.ALL) {
					userDetailsLayerUtilObj = new UserDetailsLayerUtil(driver);
					if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"User Details\"]",
							"xpath", 10)) {
						result = userDetailsLayerUtilObj.fillUserDetailsForm(driver);
						if (!result) {
							storePageMsgList.addAll(userDetailsLayerUtilObj.userDetailsLayerMsgList);
							return result;
						}

						result = userDetailsLayerUtilObj.clickContinueBtn(driver);
						if (!result) {
							storePageMsgList.addAll(feedbackFormUtilObj.feedbackFormMsgList);
							return result;
						}
					}
				}

			} else {
				result = purchasePackageUtilObj.clickBuyNowBtn(driver);
				if (!result) {
					storePageMsgList.add("Unable to click buy now btn");
					return result;
				}

				userDetailsLayerUtilObj = new UserDetailsLayerUtil(driver);
				if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc=\"User Details\"]",
						"xpath", 10)) {

					result = userDetailsLayerUtilObj.fillUserDetailsForm(driver);
					if (!result) {
						storePageMsgList.addAll(userDetailsLayerUtilObj.userDetailsLayerMsgList);
						return result;
					}

					result = userDetailsLayerUtilObj.clickContinueBtn(driver);
					if (!result) {
						storePageMsgList.addAll(userDetailsLayerUtilObj.userDetailsLayerMsgList);
						return result;
					}
				}
				paymentUtilObj = new PaymentUtil(driver);
				result = paymentUtilObj.completePaymentUsingUPI(driver);
				if (!result) {
					storePageMsgList.addAll(paymentUtilObj.paymentMsgList);
					return result;
				}
			}

			PaymentUtil paymentUtilObj = new PaymentUtil(driver);
			result = paymentUtilObj.verifyPaymentOptionList(driver);
			if (!result) {
				storePageMsgList.addAll(paymentUtilObj.paymentMsgList);
				return result;
			}

			if (ConfigFileReader.strEnv.equalsIgnoreCase("production")) {

				result = cfObj.commonWaitForElementToBeVisible(driver, paymentUtilObj.paymentPageObj.getOptionUPI(), 30);
				if (!result) {
					storePageMsgList.add("UPI option is not visible.");
					return result;
				}
				cfObj.commonClick(paymentUtilObj.paymentPageObj.getOptionUPI());
				Thread.sleep(2000);

				System.out.println("Verified on production, as cannot do payment.");
				return true;
			}

			result = paymentUtilObj.clickOnUPITypePaymentAndValidate(driver);
			if (!result) {
				storePageMsgList.addAll(paymentUtilObj.paymentMsgList);
				return result;
			}

			result = paymentUtilObj.clickOnPayNowBtn(driver);
			if (!result) {
				storePageMsgList.addAll(paymentUtilObj.paymentMsgList);
				return result;
			}

			Thread.sleep(5000);

			orderSuccessUtilObj = new OrderSuccessUtil(driver);
			result = orderSuccessUtilObj.handleRateUsPopUpWindow(driver);
			if (!result) {
				storePageMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
				return result;
			}

			result = orderSuccessUtilObj.verifyOrderSuccessPage(driver);
			if (!result) {
				storePageMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
				return result;
			}

		} catch (Exception e) {
			result = false;
			storePageMsgList.add("purchasePackage_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyStorePage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, storePageObj.getBtnChangeExamType(), 20);
			if (!result) {
				storePageMsgList.add("Unable to verify Change exam type btn in store page");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, storePageObj.getBtnNavigationDrawer(), 10);
			if (!result) {
				storePageMsgList.add("Unable to verify Navigation Drawer btn in store page");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, storePageObj.getIconSearch(), 20);
			if (!result) {
				storePageMsgList.add("Unable to verify search icon in store page");
			}

			Thread.sleep(2000);
			
			HomePageUtil homePageUtil = new HomePageUtil(driver);
			if (cfObj.commonWaitForElementToBeVisible(driver, homePageUtil.homePageObj.freeLiveClassBottomBannerCloseIcon(), 7)) {
				cfObj.commonClick(homePageUtil.homePageObj.freeLiveClassBottomBannerCloseIcon());
			}

			cfObj.scrollDown(driver);

			result = cfObj.commonWaitForElementToBeVisible(driver, storePageObj.getIconEbooks(), 15);
			if (!result) {

				cfObj.scrollDown(driver,1);
				cfObj.scrollUtill(driver, 1, direction.DOWN);

				result = cfObj.commonWaitForElementToBeVisible(driver, storePageObj.getIconEbooks(), 20);
				if (!result) {
					storePageMsgList.add("Unable to verify EBooks icon in store page, after scroll down.");
					return result;
				}
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, storePageObj.getIconLiveClasses(), 10);
			if (!result) {
				storePageMsgList.add("Unable to verify Live classes icon in store page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, storePageObj.getIconVideoCourse(), 10);
			if (!result) {
				storePageMsgList.add("Unable to verify video courses icon in store page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, storePageObj.getBooks(), 10);
			if (!result) {
				storePageMsgList.add("Unable to verify Books icon in store page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, storePageObj.getIconTestSeries(), 10);
			if (!result) {
				storePageMsgList.add("Unable to verify Test series icon in store page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, storePageObj.getIcon3dLearning(), 10);
			if (!result) {
				storePageMsgList.add("Unable to verify 3d learning icon in store page");
				return result;
			}
		} catch (Exception e) {
			result = false;
			storePageMsgList.add("verifyStorePage Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickEBooksIcon(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, storePageObj.getIconEbooks(), 10);
			if (!result) {
				storePageMsgList.add("Unable to verify Ebooks icon in store page");
				return result;
			}

			cfObj.commonClick(storePageObj.getIconEbooks());

			productPageUtilObj = new ProductPageUtil(driver);
			result = productPageUtilObj.verifyProductPage(driver);
			if (!result) {
				storePageMsgList.add("Failed to verify Product page,");
			}

		} catch (Exception e) {
			result = false;
			storePageMsgList.add("clickEBooksIcon Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickTestSeriesIcon(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.commonClick(storePageObj.getIconTestSeries());

			productPageUtilObj = new ProductPageUtil(driver);
			result = productPageUtilObj.verifyProductPage(driver);
			if (!result) {
				storePageMsgList.add("Failed to verify Product page.");
			}

		} catch (Exception e) {
			result = false;
			storePageMsgList.add("clickTestSeriesIcon Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickPrintedBooksIcon(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, storePageObj.getIconLiveClasses(), 10);
			if(!result){
				storePageMsgList.add("getIconLiveClasses on store is not visible");
				return result;
			}
			cfObj.commonClick(storePageObj.getBooks());

			productPageUtilObj = new ProductPageUtil(driver);
			result = productPageUtilObj.verifyProductPage(driver);
			if (!result) {
				storePageMsgList.add("Failed to verify Product page.");
			}

		} catch (Exception e) {
			result = false;
			storePageMsgList.add("clickPrintedBooksIcon Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickLiveClassIcon(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, storePageObj.getIconLiveClasses(), 10);
			if(!result){
				storePageMsgList.add("getIconLiveClasses on store is not visible");
				return result;
			}
			cfObj.commonClick(storePageObj.getIconLiveClasses());

			productPageUtilObj = new ProductPageUtil(driver);
			result = productPageUtilObj.verifyProductPage(driver);
			if (!result) {
				storePageMsgList.add("Failed to verify Product page.");
			}
		} catch (Exception e) {
			result = false;
			storePageMsgList.add("clickLiveClassIcon Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickVideoCoursesIcon(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, storePageObj.getIconVideoCourse(), 10);
			if(!result){
				storePageMsgList.add("getIconVideoCourse on store is not visible");
				return result;
			}
			cfObj.commonClick(storePageObj.getIconVideoCourse());

			productPageUtilObj = new ProductPageUtil(driver);
			result = productPageUtilObj.verifyProductPage(driver);
			if (!result) {
				storePageMsgList.add("Failed to verify Product page.");
			}
		} catch (Exception e) {
			result = false;
			storePageMsgList.add("clickVideoCoursesIcon Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickSearchIcon(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.scrollUtill(driver, 1, direction.UP);

			result = cfObj.commonWaitForElementToBeVisible(driver, storePageObj.getIconSearch(), 10);
			if (!result) {
				storePageMsgList.add("Search icon not visible in store");
				return result;
			}
			cfObj.commonClick(storePageObj.getIconSearch());

			result = verifySearchPageUnderStore(driver);
			if (!result) {
				storePageMsgList.add("Failed to verify SearchPageUnderStore.");
			}
		} catch (Exception e) {
			result = false;
			storePageMsgList.add("clickSearchIcon Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifySearchPageUnderStore(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, storePageObj.getSearchField(), 10);
			if (!result) {
				storePageMsgList.add("Unable to verify search filed in store page search");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, storePageObj.getTextTrendingSearches(), 10);
			if (!result) {
				storePageMsgList.add("Unable to verify Trending searches text in store page search");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, storePageObj.getTextCoursesBoughtByMost(), 10);
			if (!result) {
				storePageMsgList.add("Unable to verify courses bought by most in store page search");
			}

		} catch (Exception e) {
			result = false;
			storePageMsgList.add("verifySearchPageUnderStore_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean enterPackageNameInSearchField(AppiumDriver<MobileElement> driver, String packageName) {
		boolean result = true;
		try {
			result = cfObj.commonSetTextTextBox(storePageObj.getSearchField(), packageName);
			if (!result) {
				storePageMsgList.add("Failed to enter package name on search field.");
			}
			Thread.sleep(1000);

		} catch (Exception e) {
			storePageMsgList.add("enterPackageNameInSearchField Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean selectProductType(AppiumDriver<MobileElement> driver, ProductType productType) {
		boolean result = true;
		MobileElement element;
		try {

			switch (productType) {

			case ALL:
				break;

			case LIVE_CLASSES:
				if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
					element = cfObj.getElementFromAttribute(driver, "text", "Live Classes");
				} else {
					element = cfObj.getElementFromAttribute(driver, "name", "Live Classes");
				}
				element.click();
				break;

			case TEST_SERIES:
				if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
					element = cfObj.getElementFromAttribute(driver, "text", "Test Series");
				} else {
					element = cfObj.getElementFromAttribute(driver, "name", "Test Series");
				}
				element.click();
				break;

			case BOOKS:
				if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
					if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='Live Classes']", "xpath",
							10))
						cfObj.swipeLeftOnElement(cfObj.getElementFromAttribute(driver, "text", "Live Classes"), driver);
					element = cfObj.getElementFromAttribute(driver, "text", "Books");
				} else {
					cfObj.swipeLeftOnElement(cfObj.getElementFromAttribute(driver, "name", "Live Classes"), driver);
					element = cfObj.getElementFromAttribute(driver, "name", "Books");
				}
				element.click();
				break;

			case EBOOKS:
				if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
					if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='Live Classes']", "xpath",
							10))
						cfObj.swipeLeftOnElement(cfObj.getElementFromAttribute(driver, "text", "Live Classes"), driver);
					element = cfObj.getElementFromAttribute(driver, "text", "Ebooks");
				} else {
					cfObj.swipeLeftOnElement(cfObj.getElementFromAttribute(driver, "name", "Live Classes"), driver);
					element = cfObj.getElementFromAttribute(driver, "name", "Ebooks");
				}
				element.click();
				break;

			case VIDEOS:
				if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
					if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='Live Classes']", "xpath",
							10))
						cfObj.swipeLeftOnElement(cfObj.getElementFromAttribute(driver, "text", "Live Classes"), driver);
					element = cfObj.getElementFromAttribute(driver, "text", "Videos");
				} else {
					cfObj.swipeLeftOnElement(cfObj.getElementFromAttribute(driver, "name", "Live Classes"), driver);
					element = cfObj.getElementFromAttribute(driver, "name", "Videos");
				}
				element.click();
				break;

			default:
				break;
			}

		} catch (Exception e) {
			storePageMsgList.add("selectProductType_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyPackageInSearchResults(AppiumDriver<MobileElement> driver, String packageName) {
		boolean result = false;
		try {

			while (!result) {
				result = storePageObj.getListSearchResutls().get(index).getText().contains(packageName);
				if (!result) {
					index++;
				}
			}

		} catch (Exception e) {
			storePageMsgList.add("verifyPackageInSearchResults Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickPackageNameInSearchResult(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.commonClick(storePageObj.getListSearchResutls().get(index));

			purchasePackageUtilObj = new PurchasePackageUtil(driver);

			result = purchasePackageUtilObj.verifyUnpurchasedPackagePage(driver);
			if (!result) {
				storePageMsgList.add("Failed to verify Unpurchased package page.");
			}

		} catch (Exception e) {
			storePageMsgList.add("clickPackageNameInSearchResult_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickPackageNameInSearchResult(AppiumDriver<MobileElement> driver, String strTitle) {
		boolean result = true;
		boolean found = false;
		int attempt = 0;
		int i = 0;
		try {
			result = cfObj.updateListSize(storePageObj.getListSearchResutls());
			if (!result) {
				storePageMsgList.add("The search result list is empty");
			}

			while (attempt < 5) {
				int size = storePageObj.getListSearchResutls().size();
				for (i = 0; i < size; i++) {
					String contentDesc = storePageObj.getListSearchResutls().get(i).getAttribute("content-desc");

					if (contentDesc != null && contentDesc.contains(strTitle)) {
						cfObj.commonClick(storePageObj.getListSearchResutls().get(i));
						found = true;
						break;
					}
				}

				if (!found) {
					cfObj.scrollUtill(driver, 1, direction.DOWN);
					attempt++;
				} else {
					break;
				}
			}

			if (!found) {
				storePageMsgList.add("Unable to find " + strTitle + " in search results within 5 pages.");
				result = false;
				return result;
			}

			purchasePackageUtilObj = new PurchasePackageUtil(driver);
			result = purchasePackageUtilObj.verifyUnpurchasedPackagePage(driver);
			if (!result) {
				storePageMsgList.add("Failed to verify Purchased package page or not.");
				result = true;
			}

		} catch (Exception e) {
			storePageMsgList.add("clickPackageNameInSearchResult_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickPackageNameInSearchResultWithoutVerify(AppiumDriver<MobileElement> driver, String strTitleEBook) {
		boolean result = true;
		boolean found = false;
		int attempt = 0;
		int i = 0;
		try {
			result = cfObj.updateListSize(storePageObj.getListSearchResutls());
			if (!result) {
				storePageMsgList.add("The search result list is empty");
			}

			while (attempt < 5) {
				int size = storePageObj.getListSearchResutls().size();
				for (i = 0; i < size; i++) {
					String contentDesc = storePageObj.getListSearchResutls().get(i).getAttribute("content-desc");

					if (contentDesc != null && contentDesc.contains(strTitleEBook)) {
						cfObj.commonClick(storePageObj.getListSearchResutls().get(i));
						found = true;
						break;
					}
				}
				if (!found) {
					cfObj.scrollUtill(driver, 1, direction.DOWN);
					attempt++;
				} else {
					break;
				}
			}

			if (!found) {
				storePageMsgList.add("Unable to find " + strTitleEBook + " in search results within 5 pages.");
				result = false;
				return result;
			}
		} catch (Exception e) {
			storePageMsgList.add("clickPackageNameInSearchResultWithoutVerify_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public enum ProductType {
		ALL, LIVE_CLASSES, TEST_SERIES, BOOKS, EBOOKS, VIDEOS;
	}

	public boolean validateCertificateCourseTag(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, storePageObj.getCertificateCourseTag().get(0), 10);
			if (!result) {
				storePageMsgList.add("Certificate Course tag is not visible.");
				return result;
			}

		} catch (Exception e) {
			storePageMsgList.add("validateCertificateCourseTag_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnSpecificPackage(AppiumDriver<MobileElement> driver, String packageTitle) {
		boolean result = true;
		try {

			int loop = 0;
			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
				while (!cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='" + packageTitle + "']",
						"xpath", 5)) {
					cfObj.scrollUtill(driver, 1, direction.DOWN);
					if (loop > 20) {
						storePageMsgList.add("Package is not available.");
						System.out.println("Package is not available.");
						break;
					}
					loop++;
				}
				cfObj.scrollUtill(driver, 1, direction.DOWN);

				cfObj.commonClick(driver, "//*[@text='" + packageTitle + "']", "xpath");
			} else {
				while (!cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@text,'" + packageTitle + "')]", "xpath", 5)) {
					cfObj.scrollUtill(driver, 1, direction.DOWN);
					if (loop > 20) {
						storePageMsgList.add("Package is not available.");
						System.out.println("Package is not available.");
						break;
					}
					loop++;
				}
				cfObj.scrollUtill(driver, 1, direction.DOWN);

				cfObj.commonClick(driver, "//*[contains(@text,'" + packageTitle + "')]", "xpath");
			}

			purchasePackageUtilObj = new PurchasePackageUtil(driver);

			result = purchasePackageUtilObj.verifyUnpurchasedPackagePage(driver);
			if (!result) {
				storePageMsgList.add("Failed to verify Unpurchased package page.");
			}

		} catch (Exception e) {
			result = false;
			storePageMsgList.add("clickOnSpecificPackage_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyAllCoursesTabs(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			loginUtilObj = new LoginUtil(driver);

			result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), true);
			if (!result) {
				storePageMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			homeUtilObj = new HomePageUtil(driver);

			result = homeUtilObj.clickStoreBtn(driver);
			if (!result) {
				storePageMsgList.add("Unable to open Store Page");
				return result;
			}

			List<MobileElement> courseTab;
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				courseTab = cfObj.commonGetElements(driver, "//*[contains(@resource-id,'text')]", "xpath");
			} else {
				courseTab = cfObj.commonGetElements(driver, "//*[contains(@name,'browseByProductItemButton')]",
						"xpath");
			}

			for (int i = 0; i < courseTab.size(); i++) {

				cfObj.commonClick(courseTab.get(i));
				if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
					cfObj.waitForPageLoading(driver, 4, 2000, cfObj.commonGetElement(driver, "product_view", "id"));
				} else {
					cfObj.waitForPageLoading(driver, 4, 2000,
							cfObj.commonGetElement(driver, "//*[contains(@name,'packageItem')]", "xpath"));

				}
				productPageUtilObj = new ProductPageUtil(driver);

				result = productPageUtilObj.clickUnpurchasedPackage(driver);
				if (!result) {
					storePageMsgList.add("Unable to find and open unpurchased package");
					return result;
				}
				if (ConfigFileReader.strEnv.equalsIgnoreCase("PRODUCTION")) {
					if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
						result = cfObj.pressAndroidKey(driver, key.BACK, 2);
						if (!result) {
							storePageMsgList.add("Not able to click Back button.");
							return result;
						}
					} else {
						cfObj.commonClick(driver, "//*[@name='storeProductDetailBackItem']", "xpath");
						Thread.sleep(1000);
						cfObj.commonClick(driver,
								"//*[@name='storeSearcIconButton']/preceding-sibling::XCUIElementTypeButton", "xpath");
					}
				} else {
					purchasePackageUtilObj = new PurchasePackageUtil(driver);
					if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
						result = purchasePackageUtilObj.applyCouponCode(driver, configFileReaderObj.getCoupon());
						if (!result) {
							storePageMsgList.addAll(purchasePackageUtilObj.purchasePackageMsgList);
							return result;
						}
					}

					result = purchasePackageUtilObj.clickBuyNowBtn(driver);
					if (!result) {
						storePageMsgList.add("Unable to click buy now btn");
						return result;
					}

					userDetailsLayerUtilObj = new UserDetailsLayerUtil(driver);
					boolean userDetailsPopUp = false;
					if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
						userDetailsPopUp = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
								"//android.view.View[@content-desc=\"User Details\"]", "xpath", 10);
					} else {
						userDetailsPopUp = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
								"//*[contains(@name,'User Details')]", "xpath", 10);
					}
					if (userDetailsPopUp) {
						result = userDetailsLayerUtilObj.fillUserDetailsForm(driver);
						if (!result) {
							storePageMsgList.addAll(userDetailsLayerUtilObj.userDetailsLayerMsgList);
							return result;
						}

						result = userDetailsLayerUtilObj.clickContinueBtn(driver);
						if (!result) {
							storePageMsgList.addAll(userDetailsLayerUtilObj.userDetailsLayerMsgList);
							return result;
						}
						if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios")) {
							paymentUtilObj = new PaymentUtil(driver);
							result = paymentUtilObj.completePaymentUsingUPI(driver);
							if (!result) {
								storePageMsgList.addAll(paymentUtilObj.paymentMsgList);
								return result;
							}
						}
					}

					feedbackFormUtilObj = new FeedbackFormUtil(driver);

					result = feedbackFormUtilObj.skipFeedbackForm(driver);
					if (!result) {
						storePageMsgList.addAll(feedbackFormUtilObj.feedbackFormMsgList);
						storePageMsgList.add("Unable to skip feedback form");
					}

					orderSuccessUtilObj = new OrderSuccessUtil(driver);
					if (ConfigFileReader.strApplication.equalsIgnoreCase("adda")
							&& ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
//						String orderId = orderSuccessUtilObj.getOrderId();
//						if (orderId.isEmpty()) {
//							storePageMsgList.add("Unable to store order Id");
//							return false;
//						}

						result = orderSuccessUtilObj.clickViewLink(driver);
						if (!result) {
							storePageMsgList.add("Unable to click view link");
							return result;
						}

						myOrderPageUtilObj = new MyOrdersPageUtil(driver);
//						result = myOrderPageUtilObj.checkOrderIdInMyOrders(driver, orderId);
//						if (!result) {
//							storePageMsgList.add("Unable to find order Id in my orders");
//							return result;
//						}
						result = myOrderPageUtilObj.verifyOrderStatus(driver);
						if (!result) {
							storePageMsgList.add("Order status is not success");
							return result;
						}

						result = cfObj.pressAndroidKey(driver, key.BACK, 1);
						if (!result) {
							storePageMsgList.add("Not able to click Back button.");
							return result;
						}
					}

					result = cfObj.pressAndroidKey(driver, key.BACK, 3);
					if (!result) {
						storePageMsgList.add("Not able to click Back button.");
						return result;
					}

					result = homeUtilObj.clickMyContentButton(driver);
					if (!result) {
						storePageMsgList.addAll(homeUtilObj.homeMsgList);
						return result;
					}

					myContentUtilObj = new MyContentUtil(driver);
					result = myContentUtilObj.clickOnPurchasedBtn(driver);
					if (!result) {
						storePageMsgList.addAll(myContentUtilObj.myContentMsgList);
						return result;
					}

				}

				result = homeUtilObj.clickStoreBtn(driver);
				if (!result) {
					storePageMsgList.addAll(homeUtilObj.homeMsgList);
					return result;
				}
			}

		} catch (Exception e) {
			result = false;
			storePageMsgList.add("verifyAllCoursesTabs_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verify3DModelPackage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String strPackageTitle = null;
		try {
			loginUtilObj = new LoginUtil(driver);
			result = loginUtilObj.selectYourCategoryExamLanguage(driver);
			if (!result) {
				storePageMsgList.add("Unable to select your category, exam and language");
				return result;
			}
			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {

				strPackageTitle = "Parent Package Testing 4 | Mock Test Series by Adda 247";

				result = loginUtilObj.verifyLoginUsingEmailId(driver, "addaAutomation2866856900@gmail.com", "123456789", false);
			} else {

				strPackageTitle = "Mahapack for 3D model  with parent and child for all 6 categories.";

				result = loginUtilObj.verifyLoginUsingEmailId(driver, "addaAutomation2768690942@gmail.com", "123456789", false);
			}
			if (!result) {
				storePageMsgList.add("Unable to login");
				return result;
			}

			homeUtilObj = new HomePageUtil(driver);

			result = homeUtilObj.clickMyContentButton(driver);
			if (!result) {
				storePageMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}
			myContentUtilObj = new MyContentUtil(driver);
			result = myContentUtilObj.clickOnPurchasedBtn(driver);
			if (!result) {
				storePageMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}

			if (ConfigFileReader.strApplication.equalsIgnoreCase("adda")) {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@content-desc,'" + strPackageTitle + "')]", "xpath", 30);
				if (!result) {
					storePageMsgList.add("Specific package is not available.");
					return result;
				}
				cfObj.commonClick(cfObj.commonGetElement(driver,
						"//*[contains(@content-desc,'" + strPackageTitle + "')]", "xpath"));
			} else {
				result = myContentUtilObj.clickOnSpecificPurchasedPackage(driver, strPackageTitle);
				if (!result) {
					storePageMsgList.addAll(myContentUtilObj.myContentMsgList);
					return result;
				}
			}

			batchUtilObj = new BatchUtil(driver);

			if (cfObj.commonWaitForElementToBeVisible(driver, batchUtilObj.batchPageObj.getBtnDoneCloseHint(), 5)) {
				cfObj.commonClick(batchUtilObj.batchPageObj.getBtnDoneCloseHint());

			} else {
				System.out.println("Search icon hint in package page didn't appeared");
			}

			result = batchUtilObj.selectSpecificTab(driver,
					cfObj.commonGetElement(driver, "//*[@text='3D Learning']", "xpath"));
			if (!result) {
				storePageMsgList.addAll(batchUtilObj.batchMsgList);
				return result;
			}

			result = batchUtilObj.clickOnSpecificSubjectTitle(driver,
					"Child Package Testing 3 | Mock Test Series by Adda 247");
			if (!result) {
				storePageMsgList.addAll(batchUtilObj.batchMsgList);
				return result;
			}

			if (ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.widget.TextView[contains(@text,'Device not supported')]", "xpath", 30);
				if (!result) {
					storePageMsgList.add("Failed to open 3D model package.");
					return result;
				}

				cfObj.commonClick(cfObj.commonGetElement(driver, "crossIv", "id"));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "tabLabel", "id", 30);
				if (!result) {
					storePageMsgList.add("Failed to close 3D model package.");
					return result;
				}

			} else {
				cfObj.waitTillElementIsVisible(driver, 10, 2000, cfObj.commonGetElement(driver, "progressBar", "id"));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "emptyViewMessageTitle", "id", 30);
				if (result) {
					System.out.println("Content is not available.");
				} else {
					// Later, we will do the Content part once content is available.
				}

				cfObj.commonClick(cfObj.commonGetElement(driver,
						"//android.widget.ImageButton[@content-desc='Navigate up']", "xpath"));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "tabLabel", "id", 30);
				if (!result) {
					storePageMsgList.add("Failed to close 3D model package.");
					return result;
				}

			}

		} catch (Exception e) {
			result = false;
			storePageMsgList.add("verify3DModelPackage_Exception: " + e.getMessage());
		}
		return result;
	}
}
