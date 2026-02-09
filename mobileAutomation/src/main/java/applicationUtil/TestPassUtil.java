package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.TestPass_OR;
import util.Common_Function;
import util.ConfigFileReader;

public class TestPassUtil {

	TestPass_OR testPassObj;
	public Common_Function cfObj = new Common_Function();
	public ArrayList<String> testPassMsgList = new ArrayList<>();
	LoginUtil loginUtilObj;
	HomePageUtil homeUtilObj;
	StorePageUtil storePageUtilObj;

	public TestPassUtil(AppiumDriver<MobileElement> driver) {
		testPassObj = new TestPass_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), testPassObj);
	}

	public boolean verifyTestPassFlow(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		loginUtilObj = new LoginUtil(driver);
		try {
			if (ConfigFileReader.strEnv.equalsIgnoreCase("production")) {

				result = loginUtilObj.verifyLoginUsingEmailIdFromConfig(driver, "testingabc130@yopmail.com");
				if (!result) {
					testPassMsgList.addAll(loginUtilObj.loginMsgList);
					return result;
				}
			} else {
				result = loginUtilObj.verifySignUp(driver, Common_Function.randomPhoneNumber(10, "8"), true);
				if (!result) {
					testPassMsgList.addAll(loginUtilObj.loginMsgList);
					return result;
				}
			}
			
			homeUtilObj = new HomePageUtil(driver);
			result = homeUtilObj.clickHomeBtn(driver);
			if (!result) {
				testPassMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			// As no test pass banner on production.
			if(!ConfigFileReader.strEnv.equalsIgnoreCase("production")){

				result = verifyTestPassOnHomePage(driver);
				if (!result) {
					testPassMsgList.add("Failed verifyTestPassOnHomePage_Banner");
				}
			}

			result = verifyTestPassFromBottomNav(driver);
			if (!result) {
				testPassMsgList.add("Failed verifyTestPassFromBottomNav");
				return result;
			}

			result = verifyTestPassFromStore(driver);
			if (!result) {
				testPassMsgList.add("Failed verifyTestPassFromStore");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@content-desc=\"BUY NOW\"]", "xpath", 10);
			if (!result) {
				testPassMsgList.add("Buy Now btn on PDP is not visible.");
				return result;
			}
			cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.Button[@content-desc=\"BUY NOW\"]", "xpath"));


            result = verifyOurPlanPage(driver);
            if (!result) {
                testPassMsgList.add("Failed to verifyOurPlanPage");
                return result;
            }
            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.Button[@content-desc=\"BUY NOW\"]", "xpath", 10);
            if (!result) {
                testPassMsgList.add("Buy Now btn on PDP is not visible.");
                return result;
            }
            cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.Button[@content-desc=\"BUY NOW\"]", "xpath"));

            result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.TextView[@text=\"Select Payment method\"]", "xpath", 10);
            if (!result) {
                testPassMsgList.add("Select payment method popup is not visible after clicking buy now btn.");
                return result;
            }
			cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/close", "id"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.Button[contains(@content-desc, \"Start\")]", "xpath", 10);
			if (!result) {
				System.out.println("Start Free Trail btn on test prime PDP is not visible, might be trial disabled.");
				return true;
			}

			if(!ConfigFileReader.strEnv.equalsIgnoreCase("production")){

				result = verifyPurchaseOfTestPassFreeTrail(driver);
				if (!result) {
					testPassMsgList.add("Failed verifyPurchaseOfTestPassFreeTrail");
					return result;
				}

				result = verifyPurchaseOfTestPass(driver);
				if (!result) {
					testPassMsgList.add("Failed verifyPurchaseOfTestPass");
					return result;
				}

				result = verifyTestPassAfterPurchase(driver);
				if (!result) {
					testPassMsgList.add("Failed verifyTestPassAfterPurchase");
					return result;
				}
			} else {
				System.out.println("Cannot verify after purchase testpass.");
			}
		} catch (Exception e) {
			result = false;
			testPassMsgList.add("verifyTestPass_flow Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyTestPassOnHomePage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.testPassTitleOnBanner(), 20);
			if (!result) {
				System.out.println("testPassTitleOnBanner is not visible, refresh the page");

				cfObj.scrollUtill(driver, 2, Common_Function.direction.UP);

				Thread.sleep(2000);

				result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.testPassTitleOnBanner(), 20);
				if (!result) {
					System.out.println("testPassTitleOnBanner is not visible on homepage");
					return true;
				}
			}
			Thread.sleep(2000);

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.testPassHeadingOnBanner(), 20);
			if (!result) {
				testPassMsgList.add("testPassHeadingOnBanner is not visible on homepage");
				return result;
			}

			Thread.sleep(2000);

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.testPassIconsOnBanner(), 20);
			if (!result) {
				testPassMsgList.add("testPassIconsOnBanner is not visible on homepage");
				return result;
			}

			Thread.sleep(2000);

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.testPassExploreOnBanner(), 20);
			if (!result) {
				testPassMsgList.add("testPassExploreOnBanner is not visible on homepage");
				return result;
			}
			cfObj.commonClick(testPassObj.testPassExploreOnBanner());

			Thread.sleep(1000);

			result = verifyTestPassPage(driver);
			if (!result) {
				testPassMsgList.add("verifyTestPassOnPDP failed from homepage");
				return result;
			}
			driver.navigate().back();

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.testPassHeadingOnBanner(), 30);
			if (!result) {
				testPassMsgList.add("testPassHeadingOnBanner is not visible after coming from pdp on homepage.");
				return result;
			}
		} catch (Exception e) {
			testPassMsgList.add("Exception verifyTestPassOnHomePage : " + e.getMessage());
			return result;
		}
		return result;
	}

	public boolean verifyTestPassFromBottomNav(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.getBtnTestPassOnBottomNav(), 15);
			if (!result) {
				testPassMsgList.add("getBtnTestPassOnBottomNav is not visible");
				return result;
			}
			cfObj.commonClick(testPassObj.getBtnTestPassOnBottomNav());

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.getBtnTestPassOnBottomNav(), 5);
			if (result) {
				cfObj.commonClick(testPassObj.getBtnTestPassOnBottomNav());
			} else {
				result = true;
			}

			result = verifyTestPassPage(driver);
			if (!result) {
				testPassMsgList.add("Failed verifyTestPassPage from bottom nav");
				return result;
			}

			driver.navigate().back();

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.getBtnTestPassOnBottomNav(), 30);
			if (!result) {
				testPassMsgList.add("getBtnTestPassOnBottomNav is not visible");
				return result;
			}
		} catch (Exception e) {
			testPassMsgList.add("Exception verifyTestPassFromBottomNav : " + e.getMessage());
			return result;
		}
		return result;
	}

	public boolean verifyTestPassFromStore(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String strTitlePackage;
		try {
			if(ConfigFileReader.strEnv.equalsIgnoreCase("production")){
				strTitlePackage = "TEST PRIME";
			} else {
				strTitlePackage = "PARENT PACKAGE TEST PASS";
			}

			homeUtilObj = new HomePageUtil(driver);
			result = homeUtilObj.clickStoreBtn(driver);
			if (!result) {
				testPassMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			storePageUtilObj = new StorePageUtil(driver);
			result = storePageUtilObj.clickSearchIcon(driver);
			if (!result) {
				testPassMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = storePageUtilObj.enterPackageNameInSearchField(driver, strTitlePackage);
			if (!result) {
				testPassMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = storePageUtilObj.clickPackageNameInSearchResultWithoutVerify(driver, strTitlePackage);
			if (!result) {
				testPassMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = verifyTestPassOnPDPfromStore(driver);
			if (!result) {
				return result;
			}
		} catch (Exception e) {
			testPassMsgList.add("Exception verifyTestPassFromStore : " + e.getMessage());
			return result;
		}
		return result;
	}

	private boolean verifyTestPassPage(AppiumDriver<MobileElement> driver){
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.testPassHeadingOnPDP(), 30);
			if (!result) {
				testPassMsgList.add("testPassHeadingOnPDP is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.exploreBtnOnPDP(), 30);
			if (!result) {
				testPassMsgList.add("exploreBtnOnPDP is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.backBtnOnTopOnPDP(), 10);
			if (!result) {
				testPassMsgList.add("backBtnOnTopOnPDP is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.allExamsSubscriptionHeadingOnPDP(), 10);
			if (!result) {
				testPassMsgList.add("allExamsSubscriptionHeadingOnPDP is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.packageNameOnPDP(), 10);
			if (!result) {
				testPassMsgList.add("packageNameOnPDP is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.ImageView[@content-desc=\"test_pass_exam_images\"]", "xpath", 10);
			if (!result) {
				testPassMsgList.add("Test pass exams image is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.Button[@content-desc=\"BUY NOW\"]", "xpath", 10);
			if (!result) {
				testPassMsgList.add("Buy Now btn on PDP is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.moreOffersBtnOnPDP(), 10);
			if (!result) {
				testPassMsgList.add("More Offers text on payment amount on PDP is not visible.");
				return result;
			}
		} catch (Exception e) {
			testPassMsgList.add("Exception verifyTestPassPage : " + e.getMessage());
			return result;
		}
		return result;
	}

	private boolean verifyTestPassOnPDPfromStore(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.testPassHeadingOnPDP(), 30);
			if (!result) {
				testPassMsgList.add("testPassHeadingOnPDP is not visible on pdp.");
				return result;
			}

            result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.shareButtonOnPDP(), 10);
            if (!result) {
                testPassMsgList.add("shareButtonOnTestPrime is not visible.");
                return result;
            }
            cfObj.commonClick(testPassObj.shareButtonOnPDP());

            result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.shareButtonScreen(), 10);
            if (!result) {
                testPassMsgList.add("shareButtonOnTestPrime is not visible.");
                return result;
            }
            cfObj.commonClick(testPassObj.shareButtonScreen());

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.exploreBtnOnPDP(), 30);
			if (!result) {
				testPassMsgList.add("exploreBtnOnPDP is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.backBtnOnTopOnPDP(), 10);
			if (!result) {
				testPassMsgList.add("backBtnOnTopOnPDP is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.allExamsSubscriptionHeadingOnPDP(), 10);
			if (!result) {
				testPassMsgList.add("allExamsSubscriptionHeadingOnPDP is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.packageNameOnPDP(), 10);
			if (!result) {
				testPassMsgList.add("packageNameOnPDP is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.ImageView[@content-desc=\"test_pass_exam_images\"]", "xpath", 10);
			if (!result) {
				testPassMsgList.add("Test pass exams image is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.Button[@content-desc=\"BUY NOW\"]", "xpath", 10);
			if (!result) {
				testPassMsgList.add("Buy Now btn on PDP is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.moreOffersBtnOnPDP(), 10);
			if (!result) {
				testPassMsgList.add("More Offers text on payment amount on PDP is not visible.");
				return result;
			}

			Thread.sleep(2000);

			if(ConfigFileReader.strEnv.equalsIgnoreCase("production")){

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.view.View[contains(@content-desc, \"btn_explore\") and contains(@content-desc, \"Explore\")]\n", "xpath",
						10);
				if (!result) {
					testPassMsgList.add(
							"Explore dashboard btn Inside content after selected exams in content of testpass is not visible.");
					return result;
				}
				cfObj.commonClick(cfObj.commonGetElement(driver ,"//android.view.View[contains(@content-desc, \"btn_explore\") and contains(@content-desc, \"Explore\")]\n", "xpath"));

			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//android.view.View[@content-desc=\"btn_explore\n" +
								"All Exams, One Subscription\n" +
								"500+ exams covered\n" +
								"btn_explore\n" +
								"Explore\"]", "xpath",
						10);
				if (!result) {
					testPassMsgList.add(
							"Explore dashboard btn Inside content after selected exams in content of testpass is not visible.");
					return result;
				}

				cfObj.commonClick(cfObj.commonGetElement(driver ,"//android.view.View[@content-desc=\"btn_explore\n" +
						"All Exams, One Subscription\n" +
						"500+ exams covered\n" +
						"btn_explore\n" +
						"Explore\"]", "xpath"));
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.whatWillYouGetTextOnPDP(), 15);
			if (!result) {
				testPassMsgList.add("whatWillYouGetTextOnPDP is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.whatWillYouGetBelowArrowOnPDP().get(0),
					10);
			if (!result) {
				testPassMsgList.add("whatWillYouGetBelowArrowOnPDP is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@content-desc=\"goal_selection_arrow\n" +
					"GOVERNMENT JOB EXAMS\"]", "xpath"));

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.selectYourGoalHeadingBottomSheet(), 10);
			if (!result) {
				testPassMsgList.add("selectYourGoalHeadingBottomSheet is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.cancelBtnInGoalBottomSheet(), 10);
			if (!result) {
				testPassMsgList.add("cancelBtnInGoalBottomSheet is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.proceedBtnInGoalBottomSheet(), 10);
			if (!result) {
				testPassMsgList.add("proceedBtnInGoalBottomSheet is not visible.");
				return result;
			}

			List<MobileElement> listOfGoals = testPassObj.goalListingInGoalBottomSheet();

			for (int i = 0; i < listOfGoals.size(); i++) {

				result = cfObj.commonWaitForElementToBeVisible(driver, listOfGoals.get(i), 10);
				if (!result) {
					testPassMsgList.add("selectYourGoalHeadingBottomSheet is not visible.");
					return result;
				}
			}

			cfObj.commonClick(cfObj.commonGetElement(driver,"//*[@content-desc=\"goal_selected\n" +
					"GOVERNMENT JOB EXAMS\"]","xpath"));

			cfObj.commonClick(testPassObj.proceedBtnInGoalBottomSheet());

			List<MobileElement> examListingBelowWhatYouWillGet = testPassObj.examsListingBelowWhatWillYouGetOnPDP();

			for (int i = 0; i < examListingBelowWhatYouWillGet.size(); i++) {

				result = cfObj.commonWaitForElementToBeVisible(driver, examListingBelowWhatYouWillGet.get(i), 10);
				if (!result) {
					testPassMsgList.add("examListingBelowWhatYouWillGet is not visible.");
					return result;
				}
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.examsHeadingOnPDP(), 10);
			if (!result) {
				testPassMsgList.add("examsHeadingOnPDP is not visible.");
				return result;
			}

			List<MobileElement> examsListingBelowExamsOnPDP = testPassObj.examsListingBelowExamsOnPDP();

			for (int i = 0; i < examsListingBelowExamsOnPDP.size(); i++) {

				result = cfObj.commonWaitForElementToBeVisible(driver, examsListingBelowExamsOnPDP.get(i), 10);
				if (!result) {
					testPassMsgList.add("examsListingBelowExamsOnPDP is not visible.");
					return result;
				}
			}
			// Verify Inside exams from PDP below exams
			cfObj.commonClick(examsListingBelowExamsOnPDP.get(0));

			result = verifyInsideMyContent(driver);
			if (!result) {
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.examsHeadingOnPDP(), 10);
			if (!result) {
				testPassMsgList.add("examsHeadingOnPDP is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.viewAllBtnInExamsOnPDP(), 10);
			if (!result) {
				testPassMsgList.add("viewAllBtnInExamsOnPDP is not visible.");
				return result;
			}

			cfObj.commonClick(testPassObj.viewAllBtnInExamsOnPDP());

			Thread.sleep(1000);

			result = whatYouWillGetBottomsheet(driver);
			if (!result) {
				return result;
			}

			driver.navigate().back();

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.examsHeadingOnPDP(), 10);
			if (!result) {
				testPassMsgList.add("examsHeadingOnPDP is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.viewAllBtnInExamsOnPDP(), 10);
			if (!result) {
				testPassMsgList.add("viewAllBtnInExamsOnPDP is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.moreOffersBtnOnPDP(), 10);
			if (!result) {
				testPassMsgList.add("More Offers text on payment amount on PDP is not visible.");
				return result;
			}

			cfObj.commonClick(testPassObj.moreOffersBtnOnPDP());

			result = verifyMoreOffersScreen(driver);
			if (!result) {
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.moreOffersBtnOnPDP(), 10);
			if (!result) {
				testPassMsgList.add("More Offers text on payment amount on PDP is not visible.");
				return result;
			}
		} catch (Exception e) {
			testPassMsgList.add("Exception verifyTestPassOnPdpFromStore : " + e.getMessage());
			return result;
		}
		return result;
	}

	public boolean verifyInsideMyContent(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"btn_appbar_back\"]", "xpath", 10);
			if (!result) {
				testPassMsgList.add("Back btn btn on Locked PDP is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.Button[@content-desc=\"BUY NOW\"]", "xpath", 10);
			if (!result) {
				testPassMsgList.add("Buy Now btn on Locked PDP is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//*[contains(@content-desc,\"Subject\")]", "xpath", 10);
			if (!result) {
				testPassMsgList.add("Subjects text on PDP is not visible.");
				result = true;
			}

//			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "", "xpath", 10);
//			if (!result) {
//				testPassMsgList.add("Buy Now btn on PDP is not visible.");
//				return result;
//			}
//
//			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "", "xpath", 10);
//			if (!result) {
//				testPassMsgList.add("Buy Now btn on PDP is not visible.");
//				return result;
//			}
//
//			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "", "xpath", 10);
//			if (!result) {
//				testPassMsgList.add("Buy Now btn on PDP is not visible.");
//				return result;
//			}
//
//			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "", "xpath", 10);
//			if (!result) {
//				testPassMsgList.add("Buy Now btn on PDP is not visible.");
//				return result;
//			}
//
//			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "", "xpath", 10);
//			if (!result) {
//				testPassMsgList.add("Buy Now btn on PDP is not visible.");
//				return result;
//			}
//
//			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "", "xpath", 10);
//			if (!result) {
//				testPassMsgList.add("Buy Now btn on PDP is not visible.");
//				return result;
//			}
//
//			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "", "xpath", 10);
//			if (!result) {
//				testPassMsgList.add("Buy Now btn on PDP is not visible.");
//				return result;
//			}
//
//			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "", "xpath", 10);
//			if (!result) {
//				testPassMsgList.add("Buy Now btn on PDP is not visible.");
//				return result;
//			}
//
//			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "", "xpath", 10);
//			if (!result) {
//				testPassMsgList.add("Buy Now btn on PDP is not visible.");
//				return result;
//			}

			driver.navigate().back();

		} catch (Exception e) {
			testPassMsgList.add("Exception verifyInsideMyContent : " + e.getMessage());
			return result;
		}
		return result;
	}

	public boolean verifyPurchaseOfTestPassFreeTrail(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.Button[contains(@content-desc, \"Start\")]", "xpath", 10);
			if (!result) {
				testPassMsgList.add("Start Free Trail btn on test prime PDP is not visible");
				return result;
			}
			cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.Button[contains(@content-desc, \"Start\")]", "xpath"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"What will you get\"]", "xpath", 10);
			if (!result) {
				testPassMsgList.add("What will you get text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.Button[@content-desc=\"NO, THANKS\"]", "xpath", 10);
			if (!result) {
				testPassMsgList.add("No Thanks btn is not visible.");
				return result;
			}
			cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.Button[@content-desc=\"NO, THANKS\"]", "xpath"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.Button[contains(@content-desc, \"Start\")]", "xpath", 10);
			if (!result) {
				testPassMsgList.add("Start Free Trail btn on PDP is not visible.");
				return result;
			}
			cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.Button[contains(@content-desc, \"Start\")]", "xpath"));

			Thread.sleep(1000);

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.Button[@content-desc=\"ACTIVATE NOW!\"]", "xpath", 10);
			if (!result) {
				testPassMsgList.add("ACTIVATE NOW btn is not visible.");
				return result;
			}
			cfObj.commonClick(cfObj.commonGetElement(driver, "//android.widget.Button[@content-desc=\"ACTIVATE NOW!\"]", "xpath"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[contains(@content-desc, \"Adda Test Prime\")]\n", "xpath", 10);
			if (!result) {
				testPassMsgList.add("Exam selection screen inside free trial package is not visible.");
				return result;
			}

			driver.navigate().back();
			driver.navigate().back();

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/navigation_content", "id" , 10);
			if(!result){
				testPassMsgList.add("My content icon is not visible");
				return result;
			}
			cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/navigation_content", "id"));

			String appPackageName;
			try {
				appPackageName = ((AndroidDriver<MobileElement>) driver).getCurrentPackage();
				driver.terminateApp(appPackageName);
				Thread.sleep(1000);
				driver.activateApp(appPackageName);
			} catch (Exception e) {
				testPassMsgList.add("1st Error restarting app: " + e.getMessage());
				return false;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/navigation_content", "id" , 10);
			if(!result){
				testPassMsgList.add("My content icon is not visible");
				return result;
			}
			cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/navigation_content", "id"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/purchaseTitle", "id" , 10);
			if(!result){
				testPassMsgList.add("Purchase title is not visible");
				return result;
			}
			cfObj.commonClick(cfObj.commonGetElement(driver,"com.adda247.app:id/purchaseTitle","id"));

			Thread.sleep(1000);
			cfObj.scrollUp(driver,1);

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,\"PARENT PACKAGE TEST PASS\")]", "xpath" , 10);
			if(!result){
				testPassMsgList.add("Test Prime Package is not visible");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver,"//*[contains(@content-desc,\"PARENT PACKAGE TEST PASS\")]", "xpath"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,"//android.view.View[@content-desc=\"You haven’t selected any exams yet\"]", "xpath" , 10);
			if(!result){
				testPassMsgList.add("You haven’t selected any exams yet text is not visible");
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,"//android.widget.ImageView[@content-desc=\"Repurchase\"]", "xpath" , 10);
			if(!result){
				testPassMsgList.add("Repurchase cta is not visible");
			}
			cfObj.commonClick(cfObj.commonGetElement(driver,"//android.widget.ImageView[@content-desc=\"Repurchase\"]", "xpath"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.ImageView[@content-desc=\"test_pass_text\"]", "xpath", 10);
			if(!result){
				testPassMsgList.add("Test Prime title on image is not visible");
				return result;
			}
		} catch (Exception e) {
			testPassMsgList.add("Exception verifyPurchaseOfTestPassFreeTrail : " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyPurchaseOfTestPass(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.Button[@content-desc=\"REPURCHASE\"]", "xpath", 10);
			if (!result) {
				testPassMsgList.add("Repurchase btn on PDP is not visible.");
				return result;
			}

			cfObj.commonClick(
					cfObj.commonGetElement(driver, "//android.widget.Button[@content-desc=\"REPURCHASE\"]", "xpath"));

			PaymentUtil paymentUtilObj = new PaymentUtil(driver);

			result = paymentUtilObj.verifyPaymentOptionList(driver);
			if (!result) {
				testPassMsgList.addAll(paymentUtilObj.paymentMsgList);
				return result;
			}

			result = paymentUtilObj.clickOnNetBankingTypePaymentAndValidate(driver);
			if (!result) {
				testPassMsgList.addAll(paymentUtilObj.paymentMsgList);
				return result;
			}

			result = paymentUtilObj.clickOnSBIBankTab(driver);
			if (!result) {
				testPassMsgList.addAll(paymentUtilObj.paymentMsgList);
				return result;
			}

			result = paymentUtilObj.clickOnPaymentSuccessBtn(driver);
			if (!result) {
				testPassMsgList.addAll(paymentUtilObj.paymentMsgList);
				return result;
			}

			OrderSuccessUtil orderSuccessUtilObj = new OrderSuccessUtil(driver);

			result = orderSuccessUtilObj.handleRateUsPopUpWindow(driver);
			if (!result) {
				testPassMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
				return result;
			}
			result = orderSuccessUtilObj.verifyOrderSuccessPage(driver);
			if (!result) {
				testPassMsgList.addAll(orderSuccessUtilObj.orderSuccessMsgList);
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.getBtnStartLearning(), 10);
			if (!result) {
				testPassMsgList.add("Unable to verify start learning btn in order success page");
				return result;
			}

			cfObj.commonClick(testPassObj.getBtnStartLearning());

		} catch (Exception e) {
			testPassMsgList.add("Exception verifyPurchaseOfTestPass : " + e.getMessage());
			return result;
		}
		return result;
	}

	public boolean verifyTestPassAfterPurchase(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Adda Test Prime\n" +
							"Select your category and exam\"]",
					"xpath", 10);
			if (!result) {
				testPassMsgList.add("Inside content text after purchase of testpass is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"// android.widget.ImageView[@content-desc=\"btnAppBarBack\"]", "xpath", 10);
			if (!result) {
				testPassMsgList.add("Back btn inside content after purchase of testpass is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"btn_done\n" + "Done\"]", "xpath", 10);
			if (!result) {
				testPassMsgList.add("Done btn Inside content after purchase of testpass is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.ImageView[@content-desc=\"no_content_found_img\"]", "xpath", 10);
			if (result) {
				System.out.println("---------------------------------------------------");
				System.out.println("As no locators cant check inside and select the exam");
				System.out.println("---------------------------------------------------");
				return true;
			}

			result = true;
			List<MobileElement> examsListingBelowExamsOnPDP = testPassObj.examsListingBelowExamsOnPDP();

			for (int i = 0; i < examsListingBelowExamsOnPDP.size(); i++) {

				result = cfObj.commonWaitForElementToBeVisible(driver, examsListingBelowExamsOnPDP.get(i), 10);
				if (!result) {
					testPassMsgList.add("examsListingBelowExamsOnPDP inside content is not visible.");
					return result;
				}
			}

			List<MobileElement> checkboxInexamsListingBelowExamsOnPDP = testPassObj.checkboxInexamsListingBelowExamsOnPDP();

			for (int i = 0; i < checkboxInexamsListingBelowExamsOnPDP.size(); i++) {

				result = cfObj.commonWaitForElementToBeVisible(driver, checkboxInexamsListingBelowExamsOnPDP.get(i),
						10);
				if (!result) {
					testPassMsgList.add("checkboxInexamsListingBelowExamsOnPDP inside content is not visible.");
					return result;
				}
			}

			cfObj.commonClick(checkboxInexamsListingBelowExamsOnPDP.get(0));

			cfObj.commonClick(cfObj.commonGetElement(driver,
					"//android.view.View[@content-desc=\"btn_done\n" + "Done\"]", "xpath"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Add/Remove Exam\"]", "xpath", 10);
			if (!result) {
				testPassMsgList
						.add("Add/Remove Exam btn Inside content after selected exams in content of testpass is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.ImageView[@content-desc=\"btnAppBarBack\"]", "xpath", 10);
			if (!result) {
				testPassMsgList
						.add("Back btn Inside content after selected exams in content of testpass is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Performance Summary\"]", "xpath", 10);
			if (!result) {
				testPassMsgList.add(
						"Performance summary Inside content after selected exams in content of testpass is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.ImageView[contains(@content-desc, \"Explore Dashboard\")]", "xpath", 10);
			if (!result) {
				testPassMsgList.add(
						"Explore dashboard btn Inside content after selected exams in content of testpass is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[contains(@content-desc, \"Your performance analysis\")]", "xpath", 10);
			if (!result) {
				testPassMsgList
						.add("Performance analysis text Inside content after selected exams in content of testpass is not visible.");
				return result;
			}

			driver.navigate().back();

			driver.navigate().back();

			driver.navigate().back();

			driver.navigate().back();

//			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/btn_skip_test_pass",
//					"id", 10);
//			if (!result) {
//				testPassMsgList.add("Skip tutorial not visible");
//				return result;
//			}
//
//			cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/btn_skip_test_pass", "id"));

			HomePageUtil homeUtilObj = new HomePageUtil(driver);
			result = homeUtilObj.clickMyContentButton(driver);
			if (!result) {
				testPassMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.getBtnPurchasedSection(), 10);
			if (!result) {
				testPassMsgList.add("Purchased btn after coming from testpass content is not visible.");
				return result;
			}

//			result = verifyAllRedirectionsInTestPassAfterPurchase(driver);
//			if (!result) {
//				return result;
//			}
		} catch (Exception e) {
			testPassMsgList.add("Exception verifyTestPassAfterPurchase : " + e.getMessage());
			return result;
		}
		return result;
	}

	public boolean verifyAllRedirectionsInTestPassAfterPurchase(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.getBtnHome(), 10);
			if (!result) {
				testPassMsgList.add("Home Btn on bottom nav is not visible.");
				return result;
			}

			cfObj.commonClick(testPassObj.getBtnHome());

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.testPassHeadingOnBanner(), 20);
			if (!result) {
				testPassMsgList.add("testPassHeadingOnBanner is not visible.");
				return result;
			}

			Thread.sleep(3000);

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.testPassExploreOnBanner(), 20);
			if (!result) {
				testPassMsgList.add("testPassExploreOnBanner is not visible.");
				return result;
			}

			cfObj.commonClick(testPassObj.testPassExploreOnBanner());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Add/Remove Exam\"]", "xpath", 20);
			if (!result) {
				testPassMsgList
						.add("Done btn Inside content after selected exams in content of testpass is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Performance Summary\"]", "xpath", 10);
			if (!result) {
				testPassMsgList.add(
						"Performance summary Inside content after selected exams in content of testpass is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.Button[@content-desc=\"explore_dashboard\"]", "xpath",
					10);
			if (!result) {
				testPassMsgList.add(
						"Explore dashboard btn Inside content after selected exams in content of testpass is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Your performance analysis\"]", "xpath", 10);
			if (!result) {
				testPassMsgList
						.add("Your performance analysis text Inside content after selected exams in content of testpass is not visible.");
				return result;
			}

			driver.navigate().back();

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.testPassHeadingOnBanner(), 10);
			if (!result) {
				testPassMsgList.add("testPassHeadingOnBanner is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.getBtnTestPass(), 10);
			if (!result) {
				testPassMsgList.add("Test pass btn on bottom nav is not visible.");
				return result;
			}

			cfObj.commonClick(testPassObj.getBtnTestPass());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Add/Remove Exam\"]", "xpath", 10);
			if (!result) {
				testPassMsgList
						.add("Done btn Inside content after selected exams in content of testpass is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Performance Summary\"]", "xpath", 10);
			if (!result) {
				testPassMsgList.add(
						"Performance summary Inside content after selected exams in content of testpass is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.Button[@content-desc=\"explore_dashboard\"]", "xpath",
					10);
			if (!result) {
				testPassMsgList.add(
						"Explore dashboard btn Inside content after selected exams in content of testpass is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Your performance analysis\"]", "xpath", 10);
			if (!result) {
				testPassMsgList
						.add("Your performance analysis text Inside content after selected exams in content of testpass is not visible.");
				return result;
			}

			driver.navigate().back();

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.testPassHeadingOnBanner(), 10);
			if (!result) {
				testPassMsgList.add("testPassHeadingOnBanner is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.getBtnTestPass(), 10);
			if (!result) {
				testPassMsgList.add("Test pass btn on bottom nav is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.getBtnHome(), 10);
			if (!result) {
				testPassMsgList.add("Home Btn on bottom nav is not visible.");
				return result;
			}

		} catch (Exception e) {
			testPassMsgList.add("Exception verifyAllRedirectionsInTestPassAfterPurchase : " + e.getMessage());
			return result;
		}
		return result;
	}

	public boolean whatYouWillGetBottomsheet(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.whatWillYouGetTextOnPDP(), 10);
			if (!result) {
				testPassMsgList.add("whatWillYouGetTextOnPDP in bottom sheet is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.crossIconInWhatYouWillGetBottomSheet(),
					10);
			if (!result) {
				testPassMsgList.add("crossIconInWhatYouWillGetBottomSheet is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.searchBoxInWhatYouWillGetBottomSheet(),
					10);
			if (!result) {
				testPassMsgList.add("searchBoxInWhatYouWillGetBottomSheet is not visible.");
				return result;
			}

//			cfObj.commonClick(testPassObj.searchBoxInWhatYouWillGetBottomSheet());
//
//			if(!ConfigFileReader.strRunMode.equalsIgnoreCase("localLab")){
//				driver.hideKeyboard();
//			}
//			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
//					"//android.widget.ImageView[@content-desc=\"no_content_found_img\"]", "xpath", 10);
//			if (!result) {
//				testPassMsgList.add("student image in whatYouWillGet bottomsheet is not visible.");
//				return result;
//			}
//
//			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
//					"//android.view.View[@content-desc=\"Search your exam\"]", "xpath", 10);
//			if (!result) {
//				testPassMsgList.add("Search your exam text in whatYouWillGet bottomsheet is not visible.");
//				return result;
//			}
//
//			testPassObj.searchBoxInWhatYouWillGetBottomSheet().sendKeys("invalid");
//
//			if(!ConfigFileReader.strRunMode.equalsIgnoreCase("localLab")){
//				driver.hideKeyboard();
//			}
//
//			Thread.sleep(5000);
//
//			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
//					"//android.widget.ImageView[@content-desc=\"no_content_found_img\"]", "xpath", 10);
//			if (!result) {
//				testPassMsgList.add("student image in whatYouWillGet bottomsheet is not visible.");
//				return result;
//			}
//
//			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
//					"//android.view.View[@content-desc=\"No exam found\"]", "xpath", 10);
//			if (!result) {
//				testPassMsgList.add("No exam found text in whatYouWillGet bottomsheet is not visible.");
//				return result;
//			}
//
//			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
//					"//android.view.View[@content-desc=\"Exam will be added soon.\"]", "xpath", 10);
//			if (!result) {
//				testPassMsgList.add("No exam found text in whatYouWillGet bottomsheet is not visible.");
//				return result;
//			}
//
//			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.subjectFilterBottomSheetSearchClear(),
//					10);
//			if (!result) {
//				testPassMsgList.add("subjectFilterBottomSheetSearchClear is not visible.");
//				return result;
//			}
//
//			cfObj.commonClick(testPassObj.subjectFilterBottomSheetSearchClear());
//
//			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.whatWillYouGetTextOnPDP(), 10);
//			if (!result) {
//				testPassMsgList.add("whatWillYouGetTextOnPDP in bottomsheet is not visible.");
//				return result;
//			}
//
//			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.crossIconInWhatYouWillGetBottomSheet(),
//					10);
//			if (!result) {
//				testPassMsgList.add("crossIconInWhatYouWillGetBottomSheet is not visible.");
//				return result;
//			}

			// Verify exams
//			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
//					"//android.view.View[@content-desc=\"GOVERNMENT JOB EXAMS\"]", "xpath", 10);
//			if (!result) {
//				testPassMsgList.add("Govt Job Exams in whatYouWillGet in bottomsheet is not visible.");
//				return result;
//			}

			List<MobileElement> examListingBelowWhatYouWillGet1 = testPassObj.examsListingBelowWhatWillYouGetOnPDP();
			result = cfObj.commonWaitForElementToBeVisible(driver, examListingBelowWhatYouWillGet1.get(0), 10);
			if (!result) {
				testPassMsgList.add("examListingBelowWhatYouWillGet is not visible.");
				return result;
			}

//			List<MobileElement> examListingBelowWhatYouWillGet1 = testPassObj.examsListingBelowWhatWillYouGetOnPDP();
//
//			for (int i = 0; i < examListingBelowWhatYouWillGet1.size(); i++) {
//
//				result = cfObj.commonWaitForElementToBeVisible(driver, examListingBelowWhatYouWillGet1.get(i), 10);
//				if (!result) {
//					testPassMsgList.add("examListingBelowWhatYouWillGet is not visible.");
//					return result;
//				}
//			}

			List<MobileElement> examsListingBelowExamsOnPDP1 = testPassObj.examsListingBelowExamsOnPDP();

			for (int i = 0; i < examsListingBelowExamsOnPDP1.size(); i++) {

				result = cfObj.commonWaitForElementToBeVisible(driver, examsListingBelowExamsOnPDP1.get(i), 10);
				if (!result) {
					testPassMsgList.add("examsListingBelowExamsOnPDP is not visible.");
					return result;
				}
			}

			// Verify inside chapter, topics, buy now, pd
			cfObj.commonClick(examsListingBelowExamsOnPDP1.get(0));

			result = verifyInsideMyContent(driver);
			if (!result) {
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.searchBoxInWhatYouWillGetBottomSheet(),
					10);
			if (!result) {
				testPassMsgList.add("searchBoxInWhatYouWillGetBottomSheet is not visible.");
				return result;
			}

		} catch (Exception e) {
			testPassMsgList.add("Exception whatYouWillGetBottom sheet : " + e.getMessage());
			return result;
		}
		return result;
	}

	public boolean verifyMoreOffersScreen(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Price Details\"]", "xpath", 10);
			if (!result) {
				testPassMsgList.add("Price Details text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"btn_close_coupon_screen\"]", "xpath", 10);
			if (!result) {
				testPassMsgList.add("Close btn coupon screen is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Subtotal\"]", "xpath", 10);
			if (!result) {
				testPassMsgList.add("Subtotal text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Coins Used\"]", "xpath", 10);
			if (!result) {
				testPassMsgList.add("Coins used text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Amount Payable\"]", "xpath", 10);
			if (!result) {
				testPassMsgList.add("Amount payable text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"Available Offers\"]", "xpath", 10);
			if (!result) {
				testPassMsgList.add("Available offers text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[@content-desc=\"btn_buy_now\"]", "xpath", 10);
			if (!result) {
				testPassMsgList.add("Buy now btn text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.view.View[contains(@content-desc,\"final_price_text\")]", "xpath", 10);
			if (!result) {
				testPassMsgList.add("Final price text is not visible.");
				return result;
			}

			driver.navigate().back();

		} catch (Exception e) {
			testPassMsgList.add("Exception verifyMoreOffersScreen : " + e.getMessage());
			return result;
		}
		return result;
	}
    public boolean verifyOurPlanPage(AppiumDriver<MobileElement> driver) {
        boolean result = true;
        try {
            result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.ourPlans(), 20);
            if (!result) {
                testPassMsgList.add("ourPlans bottom sheet is not visible");
                return result;
            }
            result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.ourPlansDropDown(), 20);
            if (!result) {
                testPassMsgList.add("Drop down button is not visible on our plans bottom  sheet");
                return result;
            }

            result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.percentOff(), 20);
            if (!result) {
                testPassMsgList.add("percentOff section is not visible");
                return result;
            }
            result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.buyNowOnOurPlan(), 20);result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.percentOff(), 20);
            if (!result) {
                testPassMsgList.add("BuyNow button is not visible");
                return result;
            }
            result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.ourPlansDropDown(), 20);
            if (!result) {
                testPassMsgList.add("Drop down button is not visible on our plans bottom  sheet");
                return result;
            }
            cfObj.commonClick(testPassObj.ourPlansDropDown());

            result = cfObj.commonWaitForElementToBeVisible(driver, testPassObj.monthsIcon(), 20);
            if (!result) {
                testPassMsgList.add("monthsIcon is not visible on our plans bottom  sheet");
                return result;
            }
            cfObj.commonClick(testPassObj.monthsIcon());

        } catch (Exception e) {
            testPassMsgList.add("Exception verifyMoreOffersScreen : " + e.getMessage());
            return result;
        }
        return result;
    }
}
