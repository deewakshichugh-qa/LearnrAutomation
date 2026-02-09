package applicationUtil;

import java.util.ArrayList;

import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.StudyMaterialTab_OR;
import util.Common_Function;
import util.Common_Function.direction;
import util.Common_Function.key;
import util.ConfigFileReader;

public class StudyMaterialTabUtil {

	ChildPackageUtil packageUtilObj;
	HomePageUtil homeUtilObj;
	FixedMockTestUtil fixedMockTestUtilObj;
	StorePageUtil storePageUtilObj;
	StudyMaterialTab_OR studyMaterialPageObj;
	public ArrayList<String> studyMaterialMsgList = new ArrayList<>();
	public Common_Function cfObj = new Common_Function();

	public StudyMaterialTabUtil(AppiumDriver<MobileElement> driver) {
		studyMaterialPageObj = new StudyMaterialTab_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), studyMaterialPageObj);
	}

	public boolean verifyStudyMaterialTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, studyMaterialPageObj.getTitleCurrentAffairs(), 10);
			if (!result) {
				studyMaterialMsgList.add("Unable to verify Current Affairs New section");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, studyMaterialPageObj.getTitleJobAlerts(), 10);
			if (!result) {
				studyMaterialMsgList.add("Unable to verify Job Alert section");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, studyMaterialPageObj.getTitleAllIndiaMock(), 10);
			if (!result) {
				studyMaterialMsgList.add("Unable to verify All India Mock.. May section");
				// return result; //new build doesn't have this option
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, studyMaterialPageObj.getTitleNotesAndArticles(), 10);
			if (!result) {
				studyMaterialMsgList.add("Unable to verify Notes and Articles section");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, studyMaterialPageObj.getTitlePowerCapsules(), 10);
			if (!result) {
				studyMaterialMsgList.add("Unable to verify Power Capsules seciton");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, studyMaterialPageObj.getTitlePremiumStudyMaterial(),
					10);
			if (!result) {
				studyMaterialMsgList.add("Unable to verify Premium Study Material section");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, studyMaterialPageObj.getTitleSubjectWiseQuizzes(),
					10);
			if (!result) {
				studyMaterialMsgList.add("Unable to verify Subject-wise Quizzes section");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, studyMaterialPageObj.getTitleDailyQuizzes(), 10);
			if (!result) {
				studyMaterialMsgList.add("Unable to verify Daily Quizzes section");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, studyMaterialPageObj.getTitleFreePDF(), 10);
			if (!result) {
				studyMaterialMsgList.add("Unable to verify Free PDF section");
				return result;
			}
		} catch (Exception e) {
			result = false;
			studyMaterialMsgList.add("verifyStudyMaterialTab_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickAllIndiaMock(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, studyMaterialPageObj.getTitleAllIndiaMock(), 10);
			if (!result) {
				studyMaterialMsgList.add("All india Mock tab is not visible.");
				return result;
			}
			cfObj.commonClick(studyMaterialPageObj.getTitleAllIndiaMock());

		} catch (Exception e) {
			result = false;
			studyMaterialMsgList.add("clickNewMaterial25May_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickDailyQuizzes(AppiumDriver<MobileElement> driver, boolean isNewUser) {

		boolean result = true;
		try {
			if (ConfigFileReader.strApplication.equalsIgnoreCase("Sankalp")
					&& ConfigFileReader.strEnv.equalsIgnoreCase("PRODUCTION")) {
				cfObj.commonClick(driver, "//*[contains(@text,'Chapter-Wise Quizzes')]", "xpath");
			} else {
				cfObj.commonClick(studyMaterialPageObj.getTitleDailyQuizzes());
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/test_pass_chip", "id",
					5);
			if (result) {

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/close_dialog",
						"id", 5);
				if (!result) {
					System.out.println("Close icon of testpass bottomsheet is not visible.");
					return true;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/all_exams_text",
						"id", 5);
				if (!result) {
					studyMaterialMsgList.add("All exams text of testpass bottomsheet is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"com.adda247.app:id/test_pass_exam_icons", "id", 5);
				if (!result) {
					studyMaterialMsgList.add("All exams icons text of testpass bottomsheet is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"com.adda247.app:id/access_exams_textview", "id", 5);
				if (!result) {
					studyMaterialMsgList.add("access_exams_textview of testpass bottomsheet is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/des_tv1", "id", 5);
				if (!result) {
					studyMaterialMsgList.add("Descriptions of testpass bottomsheet is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"com.adda247.app:id/already_purchased_tv", "id", 5);
				if (!result) {
					studyMaterialMsgList.add("already_purchased_tv of testpass bottomsheet is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"com.adda247.app:id/attempt_free_test_btn", "id", 5);
				if (!result) {
					studyMaterialMsgList.add("attempt_free_test_btn btn of testpass bottomsheet is not visible.");
					return result;
				}

				cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/close_dialog", "id"));

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/test_pass_chip",
						"id", 5);
				if (result) {
					studyMaterialMsgList.add("After closing testpass bottomsheet, still it is visible");
					return false;
				}
			}

			homeUtilObj = new HomePageUtil(driver);
			if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"), 5)) {
				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"));
			}

			result = homeUtilObj.verifyDailyQuizFeedTabPage(driver);
			if (!result) {
				studyMaterialMsgList.add("Failed to verify DailyQuiz FeedTab page.");
			}

		} catch (Exception e) {
			result = false;
			studyMaterialMsgList.add("clickDailyQuizzes Exceptions:\n" + e.getMessage());
		}
		return result;
	}

	public int downloadDailyQuizzes(AppiumDriver<MobileElement> driver, int questionIndex) {
		boolean result = true;
		try {

			result = clickDailyQuizzes(driver, true);
			if (!result) {
				studyMaterialMsgList.add("Unable to click daily Quizzes");
			}

			packageUtilObj = new ChildPackageUtil(driver);

			questionIndex = packageUtilObj.downloadQuiz(driver, result);
			if (!result) {
				studyMaterialMsgList.add("Unable to download Quiz");
			}

		} catch (Exception e) {
			studyMaterialMsgList.add("downloadDailyQuizzes Exception: " + e.getMessage());
			result = false;
		}
		return questionIndex;
	}

	public String downloadDailyQuizzes(AppiumDriver<MobileElement> driver, boolean result, boolean isNewUser) {
		String titleQuiz = null;
		try {

			result = clickDailyQuizzes(driver, isNewUser);
			if (!result) {
				studyMaterialMsgList.add("Failed to click Daily Quizzes.");
				return null;
			}

			packageUtilObj = new ChildPackageUtil(driver);
			titleQuiz = packageUtilObj.downloadQuiz(driver, titleQuiz, result);
			if (titleQuiz == null) {
				studyMaterialMsgList.add("Unable to download Quiz");
			}

		} catch (Exception e) {
			studyMaterialMsgList.add("downloadDailyQuizzes Exception: " + e.getMessage());
			titleQuiz = null;
		}
		return titleQuiz;
	}

	public boolean clickFreePdf(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			cfObj.commonClick(studyMaterialPageObj.getTitleFreePDF());

			if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"), 5)) {
				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"));
			}

			packageUtilObj = new ChildPackageUtil(driver);
			result = packageUtilObj.verifyFreePdfPage(driver);
			if (!result) {
				studyMaterialMsgList.add("Failed to verify Free Pdf page.");
			}

		} catch (Exception e) {
			studyMaterialMsgList.add("clickFreePdf_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean downloadFreePdf(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = clickFreePdf(driver);
			if (!result) {
				studyMaterialMsgList.add("Unable to click free pdf");
				return result;
			}

			packageUtilObj = new ChildPackageUtil(driver);

			packageUtilObj.downloadFreePdf(driver, null);
			if (!result) {
				studyMaterialMsgList.add("Unable to download Free PDF");
			}

		} catch (Exception e) {
			studyMaterialMsgList.add("downloadFreePdf_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public String downloadFreePdf(AppiumDriver<MobileElement> driver, String strTitleMagazines) {
		boolean result = false;
		try {
			result = clickFreePdf(driver);
			if (!result) {
				studyMaterialMsgList.add("Unable to open Free PDF");
				return null;
			}

			strTitleMagazines = packageUtilObj.packagePageObj.getListPackageTitle().get(3).getText();
			if(strTitleMagazines==null) {
				studyMaterialMsgList.add("Magazines title is null");
				return null;
			}

			packageUtilObj = new ChildPackageUtil(driver);
			cfObj.commonClick(packageUtilObj.packagePageObj.getListPackageTitle().get(3));

			result = cfObj.commonWaitForElementToBeVisible(driver, packageUtilObj.packagePageObj.getIconDownload(), 5);
			if (!result) {
				System.out.println("The free pdf download btn is not visible, might be already downloaded in device");

			} else {
				cfObj.commonClick(packageUtilObj.packagePageObj.getIconDownload());

				System.out.println("Waiting for pdf to download for 30 seconds.");
				Thread.sleep(30000);
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/shareBtn", "id", 10);
			if (!result) {
				studyMaterialMsgList.add("The free pdf share btn is not visible");
				return null;
			}

			// Not working as pdf not opening
//			packageUtilObj = new ChildPackageUtil(driver);
//			strTitleMagazines = packageUtilObj.downloadFreePdf(driver, strTitleMagazines);
//			if (strTitleMagazines == null) {
//				studyMaterialMsgList.add("Unable to download Free PDF");
//				return null;
//			}

		} catch (Exception e) {
			studyMaterialMsgList.add("downloadFreePdf_Exception: " + e.getMessage());
			strTitleMagazines = null;
		}
		return strTitleMagazines;
	}

	public boolean clickPowerCapsules(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.commonClick(studyMaterialPageObj.getTitlePowerCapsules());

			if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"), 5)) {
				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"));
			}

			packageUtilObj = new ChildPackageUtil(driver);

			result = packageUtilObj.verifyCapsulesPage(driver);
			if (!result) {
				studyMaterialMsgList.add("Failed to verify Capsules Page.");
			}

		} catch (Exception e) {
			studyMaterialMsgList.add("ClickPowerCapsules_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean downloadPowerCapsules(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = clickPowerCapsules(driver);

			packageUtilObj = new ChildPackageUtil(driver);

			result = packageUtilObj.downloadCapsules(driver);
			if (!result) {
				studyMaterialMsgList.add("Unable to download Power Capsules");
			}

		} catch (Exception e) {
			studyMaterialMsgList.add("downloadPowerCapsules_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public String downloadPowerCapsules(AppiumDriver<MobileElement> driver, String strTitlePowerCapsules) {
		boolean result = true;
		try {

			result = clickPowerCapsules(driver);
			if (!result) {
				studyMaterialMsgList.add("Unable to open Power Capsules");
				return null;
			}

			packageUtilObj = new ChildPackageUtil(driver);

			strTitlePowerCapsules = packageUtilObj.downloadCapsules(driver, strTitlePowerCapsules);
			if (strTitlePowerCapsules == null) {
				studyMaterialMsgList.add("Unable to download Power Capsules");
				return null;
			}

		} catch (Exception e) {
			studyMaterialMsgList.add("downloadPowerCapsules_Exception: " + e.getMessage());
			strTitlePowerCapsules = null;
		}
		return strTitlePowerCapsules;
	}

	public String bookmarkCurrentAffairs(AppiumDriver<MobileElement> driver, String strTitleCurrentAffairs,
			boolean result) {
		try {

			cfObj.commonClick(studyMaterialPageObj.getTitleCurrentAffairs());

			if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"), 5)) {
				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"));
			}

			packageUtilObj = new ChildPackageUtil(driver);

			strTitleCurrentAffairs = packageUtilObj.bookmarkCurrentAffaris(driver, strTitleCurrentAffairs, result);
			if (strTitleCurrentAffairs == null) {
				studyMaterialMsgList.add("Unable to bookmark current Affairs");
				return null;
			}

			System.out.println("CurrentAffair:--> " + strTitleCurrentAffairs);

			result = cfObj.pressAndroidKey(driver, key.BACK, 1);

		} catch (Exception e) {
			studyMaterialMsgList.add("bookmarkCurrentAffairs_Exception: " + e.getMessage());
			strTitleCurrentAffairs = null;
		}
		return strTitleCurrentAffairs;
	}

	public String bookmarkJobAlerts(AppiumDriver<MobileElement> driver, String strTitleJobAlerts, boolean result) {
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='Job Alerts']", "xpath", 30);
			if (!result) {
				studyMaterialMsgList.add("JobAlert title is not visible.");
				return null;

			}
			cfObj.commonClick(studyMaterialPageObj.getTitleJobAlerts());

			Thread.sleep(1000);

			packageUtilObj = new ChildPackageUtil(driver);

			strTitleJobAlerts = packageUtilObj.bookmarkJobAlerts(driver, strTitleJobAlerts, result);
			if (strTitleJobAlerts == null) {
				studyMaterialMsgList.add("Unable to bookmark Job Alert.");
			}
			System.out.println("JobAlert:--> " + strTitleJobAlerts);

			driver.navigate().back();

		} catch (Exception e) {
			studyMaterialMsgList.add("bookmarkJobAlerts_Exception: " + e.getMessage());
			strTitleJobAlerts = null;
		}
		return strTitleJobAlerts;
	}

	public String bookmarkArticles(AppiumDriver<MobileElement> driver, String strTitleArticles, boolean result) {
		try {
			// Handling hidden option due to small screen size in some devices
			cfObj.scrollUtill(driver, 1, direction.DOWN);

			cfObj.commonClick(studyMaterialPageObj.getTitleNotesAndArticles());

			Thread.sleep(1000);

			packageUtilObj = new ChildPackageUtil(driver);

			strTitleArticles = packageUtilObj.bookmarkArticles(driver, strTitleArticles, result);
			if (strTitleArticles == null) {
				studyMaterialMsgList.add("Unable to bookmark Articles");
			}

			System.out.println("Articles:--> " + strTitleArticles);

			driver.navigate().back();

		} catch (Exception e) {
			studyMaterialMsgList.add("bookmarkArticles_Exception: " + e.getMessage());
			strTitleArticles = null;
		}
		return strTitleArticles;
	}

	public String bookmarkVideos(AppiumDriver<MobileElement> driver, String strTitleVideos, boolean result) {
		try {
			// Handling hidden option
			cfObj.scrollUtill(driver, 1, direction.DOWN);

			cfObj.commonClick(studyMaterialPageObj.getTitleVideos());

			Thread.sleep(1000);

			packageUtilObj = new ChildPackageUtil(driver);

			strTitleVideos = packageUtilObj.bookmarkVideos(driver, strTitleVideos, result);
			if (strTitleVideos == null) {
				studyMaterialMsgList.add("Unable to bookmark current Affairs");
			} else {
				System.out.println("Videos:--> " + strTitleVideos);

				driver.navigate().back();
			}
		} catch (Exception e) {
			studyMaterialMsgList.add("bookmarkVideos_Exception: " + e.getMessage());
			strTitleVideos = null;
		}
		return strTitleVideos;
	}

	public boolean validateDailyQuizzesPageOpenFromSideDrawer(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, studyMaterialPageObj.getTitleDailyQuizzes(), 30);
			if (!result) {
				studyMaterialMsgList.add("Daily quiz title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, studyMaterialPageObj.getDailyQuizeTopicList().get(0),
					30);
			if (!result) {
				studyMaterialMsgList.add("Daily quiz Topic is not visible.");
				return result;
			}
			int size = studyMaterialPageObj.getDailyQuizeTopicList().size();

			for (int i = 0; i < size; i++) {

				result = cfObj.commonWaitForElementToBeVisible(driver,
						studyMaterialPageObj.getDailyQuizeTopicList().get(i), 30);
				if (!result) {
					studyMaterialMsgList.add("Daily quiz topic is not visible.");
					return result;
				}

				String topicName = studyMaterialPageObj.getDailyQuizeTopicList().get(i).getText();
				if (topicName == null) {
					studyMaterialMsgList.add("topic name is null.");
					return false;
				}

				cfObj.commonClick(studyMaterialPageObj.getDailyQuizeTopicList().get(i));

//				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/test_pass_chip",
//						"id", 5);
//				if (!result) {
//					result = true;
//				} else {
//
//					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/close_dialog",
//							"id", 5);
//					if (!result) {
//						studyMaterialMsgList.add("Close icon of testpass bottomsheet is not visible.");
//						return result;
//					}
//
//					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
//							"com.adda247.app:id/all_exams_text", "id", 5);
//					if (!result) {
//						studyMaterialMsgList.add("All exams text of testpass bottomsheet is not visible.");
//						return result;
//					}
//
//					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
//							"com.adda247.app:id/test_pass_exam_icons", "id", 5);
//					if (!result) {
//						studyMaterialMsgList.add("All exams icons text of testpass bottomsheet is not visible.");
//						return result;
//					}
//
//					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
//							"com.adda247.app:id/access_exams_textview", "id", 5);
//					if (!result) {
//						studyMaterialMsgList.add("access_exams_textview of testpass bottomsheet is not visible.");
//						return result;
//					}
//
//					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "com.adda247.app:id/des_tv1", "id",
//							5);
//					if (!result) {
//						studyMaterialMsgList.add("Descriptions of testpass bottomsheet is not visible.");
//						return result;
//					}
//
//					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
//							"com.adda247.app:id/already_purchased_tv", "id", 5);
//					if (!result) {
//						studyMaterialMsgList.add("already_purchased_tv of testpass bottomsheet is not visible.");
//						return result;
//					}
//
//					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
//							"com.adda247.app:id/attempt_free_test_btn", "id", 5);
//					if (!result) {
//						studyMaterialMsgList.add("attempt_free_test_btn btn of testpass bottomsheet is not visible.");
//						return result;
//					}
//
//					cfObj.commonClick(cfObj.commonGetElement(driver, "com.adda247.app:id/close_dialog", "id"));
//
//					result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
//							"com.adda247.app:id/test_pass_chip", "id", 5);
//					if (result) {
//						studyMaterialMsgList.add("After closing testpass bottomsheet, still it is visible");
//						return false;
//					} else {
//						result = true;
//					}
//				}

				if (i == 0) {
					if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"), 5)) {
						cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"));
					}
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, studyMaterialPageObj.getSelectedQuizeTitle(),
						30);
				if (!result) {
					studyMaterialMsgList.add("Selected quiz title is not visible.");
					return result;
				}

				cfObj.commonClick(studyMaterialPageObj.getBackBtn());
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, studyMaterialPageObj.getSelectedQuizeTitle(),
					5);
			if (result) {
				driver.navigate().back();
			} else {
				result = true;
			}

		} catch (Exception e) {
			result = false;
			studyMaterialMsgList.add("DailyQuizzesPageOpenFromSideDrawer_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean clickOnDailyQuizeTopic(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, studyMaterialPageObj.getDailyQuizeTopicList().get(0),
					30);
			if (!result) {
				studyMaterialMsgList.add("Daily quiz topic is not visible.");
				return result;
			}
			cfObj.commonClick(studyMaterialPageObj.getDailyQuizeTopicList().get(0));

		} catch (Exception e) {
			result = false;
			studyMaterialMsgList.add("DailyQuizTopic_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyDailyQuizPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, studyMaterialPageObj.getTitleDailyQuizzes(), 30);
			if (!result) {
				studyMaterialMsgList.add("Daily quize Title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, studyMaterialPageObj.getFilterIcon(), 30);
			if (!result) {
				studyMaterialMsgList.add("Filter Icon is not visible.");
				return result;
			}
			result = cfObj.commonWaitForElementToBeVisible(driver, studyMaterialPageObj.getQuizCardList().get(0), 30);
			if (!result) {
				studyMaterialMsgList.add("QuizCardList is not visible.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			studyMaterialMsgList.add("DailyQuizPage_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyStudyMaterialTabFunctionality(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver,
					studyMaterialPageObj.getStudyMaterialTabList().get(0), 10);
			if (!result) {
				System.out.println("StudyMaterialTabList is not visible.");
				return result;
			}

			homeUtilObj = new HomePageUtil(driver);
			String studyMaterialName = studyMaterialPageObj.getStudyMaterialTabList().get(0).getText();
			if (studyMaterialName == null) {
				studyMaterialMsgList.add("StudyMaterial name is null.");
				return false;
			}

			cfObj.commonClick(studyMaterialPageObj.getStudyMaterialTabList().get(0));

			result = cfObj.commonWaitForElementToBeVisible(driver, studyMaterialPageObj.getBtnHome(), 5);
			if (!result) {
				studyMaterialMsgList.add("getBtnHome not visible");
				return result;
			}
			cfObj.commonClick(studyMaterialPageObj.getBtnHome());
			cfObj.commonClick(studyMaterialPageObj.getBtnHome());

			result = homeUtilObj.clickStudyMaterialTab(driver);
			if (!result) {
				studyMaterialMsgList.addAll(homeUtilObj.homeMsgList);
				return result;
			}
		} catch (Exception e) {
			result = false;
			studyMaterialMsgList.add("verifyStudyMaterialTabFunctionality_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnScholarshipTestTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, studyMaterialPageObj.getScholarshipTestTab(), 10);
			if (!result) {
				studyMaterialMsgList.add("ScholarshipTest tab is not visible.");
				return result;
			}

			String tabText = studyMaterialPageObj.getScholarshipTestTab().getText();
			if (tabText == null) {
				studyMaterialMsgList.add("tab text is null.");
				return false;
			}

			cfObj.commonClick(studyMaterialPageObj.getScholarshipTestTab());

			HomePageUtil homePageUtilObj = new HomePageUtil(driver);
			result = homePageUtilObj.testPrimePop(driver);
			if (!result) {
				studyMaterialMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, studyMaterialPageObj.getPageTitle(), 30);
			if (!result) {
				studyMaterialMsgList.add("ScholarshipTest page title is not visible.");
				return result;
			}

			result = cfObj.commonVerifyValueTextBox(studyMaterialPageObj.getPageTitle(), tabText);
			if (!result) {
				studyMaterialMsgList.add("ScholarshipTest page title text is not matching.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			studyMaterialMsgList.add("clickOnScholarshipTestTab_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean clickOnDailyTestTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, studyMaterialPageObj.getDailyTestPageTitle(), 10);
			if (!result) {
				studyMaterialMsgList.add("DailyTest tab is not visible.");
				return result;
			}

			String tabText = studyMaterialPageObj.getDailyTestPageTitle().getText();
			if (tabText == null) {
				studyMaterialMsgList.add("tab text is null.");
				return false;
			}

			cfObj.commonClick(studyMaterialPageObj.getDailyTestPageTitle());

			if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"), 5)) {
				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"));
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, studyMaterialPageObj.getPageTitle(), 30);
			if (!result) {
				studyMaterialMsgList.add("DailyTest page title is not visible.");
				return result;
			}

			result = cfObj.commonVerifyValueTextBox(studyMaterialPageObj.getPageTitle(), tabText);
			if (!result) {
				studyMaterialMsgList.add("DailyTest page title text is not matching.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			studyMaterialMsgList.add("clickOnDailyTestTab_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnSpecificMockCardTimer(AppiumDriver<MobileElement> driver, String mockName) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, studyMaterialPageObj.getPageTitle(), 30);
			if (!result) {
				studyMaterialMsgList.add("ScholarshipTest page title is not visible.");
				return result;
			}

			result = cfObj.commonVerifyValueTextBox(studyMaterialPageObj.getPageTitle(), "Scholarship Test");
			if (!result) {
				studyMaterialMsgList.add("ScholarshipTest page title text is not matching.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='" + mockName
					+ "']/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[contains(@resource-id,'tv_test_timer')]",
					"xpath", 30);
			if (result) {
				cfObj.commonClick(driver, "//*[@text='" + mockName
						+ "']/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[contains(@resource-id,'tv_test_timer')]",
						"xpath");
			} else {
				studyMaterialMsgList.add("MockCardTimer is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			studyMaterialMsgList.add("clickOnMockCardTimer_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean downloadSpecificQuiz(AppiumDriver<MobileElement> driver, String mockName) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'" + mockName
					+ "')]/following-sibling::*[contains(@resource-id,'cutOffLayout')]/child::android.widget.TextView[@text='GET QUIZ']",
					"xpath", 30);
			if (result) {
				cfObj.commonClick(driver, "//*[contains(@text,'" + mockName
						+ "')]/following-sibling::*[contains(@resource-id,'cutOffLayout')]/child::android.widget.TextView[@text='GET QUIZ']",
						"xpath");
			} else {
				studyMaterialMsgList.add("Get_Quiz is not visible.");
				return result;
			}
			Thread.sleep(2000);

		} catch (Exception e) {
			result = false;
			studyMaterialMsgList.add("downloadSpecificQuiz_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnSpecificAttemptLink(AppiumDriver<MobileElement> driver, String mockName) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'" + mockName
					+ "')]/following-sibling::*[contains(@resource-id,'cutOffLayout')]/child::android.widget.TextView[@text='ATTEMPT']",
					"xpath", 30);
			if (result) {
				cfObj.commonClick(driver, "//*[contains(@text,'" + mockName
						+ "')]/following-sibling::*[contains(@resource-id,'cutOffLayout')]/child::android.widget.TextView[@text='ATTEMPT']",
						"xpath");
			} else {
				studyMaterialMsgList.add("ATTEMPT is not visible.");
				return result;
			}
			Thread.sleep(2000);

			cfObj.handleHints(driver);

		} catch (Exception e) {
			result = false;
			studyMaterialMsgList.add("clickOnSpecificAttemptLink_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnSpecificResultStatus(AppiumDriver<MobileElement> driver, String mockName) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'" + mockName
					+ "')]/following-sibling::*[contains(@resource-id,'cutOffLayout')]/child::android.widget.TextView[@text='RESULT']",
					"xpath", 30);
			if (result) {
				cfObj.commonClick(driver, "//*[contains(@text,'" + mockName
						+ "')]/following-sibling::*[contains(@resource-id,'cutOffLayout')]/child::android.widget.TextView[@text='RESULT']",
						"xpath");
			} else {
				studyMaterialMsgList.add("RESULT is not visible.");
				return result;
			}

		} catch (Exception e) {
			studyMaterialMsgList.add("clickOnSpecificResultStatus_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}
}
