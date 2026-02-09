package applicationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.PackagePage_OR;
import util.Common_Function;
import util.Common_Function.direction;
import util.Common_Function.key;
import util.ConfigFileReader;

public class ChildPackageUtil {

	public PackagePage_OR packagePageObj;
	CommonTestUtil commonTestUtilObj;
	BatchUtil batchUtilObj;
	CertificatePreviewUtil certificatePreviewUtilObj;
	public List<String> packagePageMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();

	public ChildPackageUtil(AppiumDriver<MobileElement> driver) {
		packagePageObj = new PackagePage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), packagePageObj);
	}

	//This method download quiz and return the title of the dowloaded quiz 
	public String downloadQuiz(AppiumDriver<MobileElement> driver, String strTitleQuiz, boolean result) {
		int count=0;
		int i=0;
		try {
			//Finding quiz with Get Link
			while(count<5) {
				for(i=0; i<packagePageObj.getLinkGetQuiz().size(); i++) {
					if(packagePageObj.getLinkGetQuiz().get(i).getText().contains("GET")) {
						cfObj.commonClick(packagePageObj.getLinkGetQuiz().get(i));
						result = true;
						break;
					}
				}
				if(!result) {
					cfObj.scrollUtill(driver, 1, direction.DOWN);
					count++;
				}else {break;}
			}
			if (count == 5) {
				packagePageMsgList.add(
						"Did not find any unattempted quiz in the first 5 pages. Run the test with different user");
				result =false;
				return null;
			}

			//Waiting for quiz to download
			count=0;
			while(count<5) {
				if(!packagePageObj.getLinkGetQuiz().get(i).getText().contains("ATTEMPT")) {
					Thread.sleep(5000);
					count++;
				}else break;
			}
			if(count==5) {
				packagePageMsgList.add("The quiz did not dowloaded in after 30 sec");
				result = false;
				return null;
			}

			strTitleQuiz = packagePageObj.getTitleQuiz().get(i).getText();
			if(strTitleQuiz==null) {
				packagePageMsgList.add("Quiz title is null.");
			}

		} catch (Exception e) {
			strTitleQuiz=null;
			packagePageMsgList.add("downloadQuiz_Exception: " + e.getMessage());
		}
		return strTitleQuiz;
	}

	public int downloadQuiz(AppiumDriver<MobileElement> driver, boolean result) {
		int count=0;
		int i=0;
		try {
			//Finding quiz with Get Link
			while(count<5) {
				for(i=0; i<packagePageObj.getLinkGetQuiz().size(); i++) {
					if(packagePageObj.getLinkGetQuiz().get(i).getText().contains("GET")) {
						cfObj.commonClick(packagePageObj.getLinkGetQuiz().get(i));
						result = true;
						break;
					}
				}
				if(!result) {
					cfObj.scrollUtill(driver, 1, direction.DOWN);
					count++;
				}else {break;}
			}
			if (count == 5) {
				packagePageMsgList.add(
						"Did not find any unattempted quiz in the first 5 pages. Run the test with different user");
                throw new Exception("Download quiz failed");
			}

			//Waiting for quiz to download
			count=0;
			while(count<5) {
				if(!packagePageObj.getLinkGetQuiz().get(i).getText().contains("ATTEMPT")) {
					Thread.sleep(5000);
					count++;
				}else break;
			}
			if(count==5) {
				packagePageMsgList.add("The quiz did not downloaded in after 30 sec");
                throw new Exception("Download quiz failed");
			}
		} catch (Exception e) {
			packagePageMsgList.add("downloadQuiz_Exception: " + e.getMessage());
		}
		return i;
	}

	//This method download quiz and return the result in boolean
	public boolean downloadQuiz(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			cfObj.commonClick(packagePageObj.getLinkGetQuiz().get(0));

			while(result) {
				if(packagePageObj.getLinkGetQuiz().get(0).getText().contains("GET")) {
					Thread.sleep(5000);
				}else {
					result = false;
				}
			}

			result = packagePageObj.getLinkGetQuiz().get(0).getText().contains("ATTEMPT");
			if (!result) {
				packagePageMsgList.add("Get Quiz is not contains Attempt text.");
			}

		} catch (Exception e) {
			result=false;
			packagePageMsgList.add("downloadQuiz_Exception: " + e.getMessage());
		}
		return result;
	}

	//This method download FreePdf and return the title of downloaded pdf
	public String downloadFreePdf(AppiumDriver<MobileElement> driver, String strTitleMagazines) {
		int i = 1;
		int count=0;
		boolean result = true;
		try {
			result = cfObj.updateListSize(packagePageObj.getListPackageTitle());
			if (!result) {
				packagePageMsgList.add("The free pdf page is empty");
				return null;
			}

			cfObj.commonClick(packagePageObj.getListPackageTitle().get(i));
			//Waiting for pdf to open
			result = cfObj.waitWhileLoading(driver, "com.adda247.app:id/progressBar", "id");
			if(!result) {
				throw new Exception("30 second timeout while waiting for free pdf to open");
			}

			if(cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getIconDownload(), 5)) {

				cfObj.commonClick(packagePageObj.getIconDownload());

				result = cfObj.allowPermission(driver);
				if(!result) {
					packagePageMsgList.add("The permission pop-up didn't appeared");

					return null;
				}
				//Waiting if download is not complete in 30 sec
				while(cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getIconDownload(), 10)) {
					if(count==5) {
						throw new Exception("Download failed after trying 30 sec");
					}
					Thread.sleep(5000);
					count++;
				}
			}

			cfObj.pressAndroidKey(driver, key.BACK, 1);

			if (packagePageObj.getListIconIsRead().size() != 1) {
				packagePageMsgList.add("After reading one package the is read icon is either less than or greater than 1 ");
			}

			strTitleMagazines = packagePageObj.getListPackageTitle().get(i).getText();
			if(strTitleMagazines==null) {
				packagePageMsgList.add("Magazines title is null");
			}

		} catch (Exception e) {
			packagePageMsgList.add("downloadFreePdf_Exception: " + e.getMessage());
			strTitleMagazines=null;
		}
		return strTitleMagazines;
	}

	//This method download Free pdf and return the result in boolean
	public boolean downloadFreePdf(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		int i=1;
		try {

			result = cfObj.updateListSize(packagePageObj.getListPackageTitle());
			if (!result) {
				packagePageMsgList.add("The free pdf page is empty");
				return result;
			}

			cfObj.commonClick(packagePageObj.getListPackageTitle().get(i));

			Thread.sleep(5000);

			cfObj.pressAndroidKey(driver, key.BACK, i);

			if (packagePageObj.getListIconIsRead().size() != 1) {
				packagePageMsgList.add("After reading one package the is read icon is either less than or greater than 1 ");
				result = false;
			}


		} catch (Exception e) {
			result=false;
			packagePageMsgList.add("downloadQuiz_Exception: " + e.getMessage());
		}
		return result;
	}

	//This method download capusles and return the downloaded capsule title
	public String downloadCapsules(AppiumDriver<MobileElement> driver, String strTitleCapsule) {
		int i=1;
		int count=0;
		boolean result = true;
		try {

			result = cfObj.updateListSize(cfObj.commonGetElements(driver, "//android.view.ViewGroup/*[contains(@resource-id,'title')]", "xpath"));
			if (!result) {
				packagePageMsgList.add("The capsule page is empty");
                return null;
			}
			
			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='test amit' and contains(@resource-id,'title')]", "xpath", 30);
			if(result) {
				strTitleCapsule ="test amit";

				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='test amit' and contains(@resource-id,'title')]", "xpath"));
			}else {
				strTitleCapsule =cfObj.commonGetElements(driver, "//android.view.ViewGroup/*[contains(@resource-id,'title')]", "xpath").get(i).getText();
				if(strTitleCapsule==null) {
					packagePageMsgList.add("Capsule title is null.");
				}

				cfObj.commonClick(cfObj.commonGetElements(driver, "//android.view.ViewGroup/*[contains(@resource-id,'title')]", "xpath").get(i));
			}

			//Waiting for capsule to open
			result = cfObj.waitTillElementIsVisible(driver,4,2000,cfObj.commonGetElement(driver, "com.adda247.app:id/progressBar", "id") );
			if(!result) {
				packagePageMsgList.add("Waited 30 second to open the capsule");
				return null;
			}

			if(cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getIconDownload(), 5)) {
				cfObj.commonClick(packagePageObj.getIconDownload());

				result = cfObj.allowPermission(driver);
				if(!result) {
					packagePageMsgList.add("The permission pop-up didn't appeared");

					return null;
				}

				//Waiting if download is not complete in 30 sec
				while(cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getIconDownload(), 10)) {
					if(count==5) {
						packagePageMsgList.add("Waited for 30 sec to downlaod capsule");
						return null;
					}
					Thread.sleep(5000);
					count++;
				}
			}

			cfObj.pressAndroidKey(driver, key.BACK, 1);

			if (packagePageObj.getListIconIsRead().size() != 1) {
				packagePageMsgList.add("After reading one package the is read icon is either less than or greater than 1 ");
			}

		} catch (Exception e) {
			packagePageMsgList.add("downloadCapsules_Exception: " + e.getMessage());
			strTitleCapsule = null;
		}
		return strTitleCapsule;
	}

	//This method download capsule and return the result
	public boolean downloadCapsules(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		int i =1;
		try {

			result = cfObj.updateListSize(packagePageObj.getListPackageTitle());
			if (!result) {
				packagePageMsgList.add("The capusle page is empty");
				return result;
			}

			cfObj.commonClick(packagePageObj.getListPackageTitle().get(i));

			Thread.sleep(5000);

			cfObj.pressAndroidKey(driver, key.BACK, i);

			if (packagePageObj.getListIconIsRead().size() != 1) {
				packagePageMsgList.add("After reading one package the is read icon is either less than or greater than 1 ");
				result = false;
			}
		} catch (Exception e) {
			packagePageMsgList.add("downloadCapsules_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	//This method download Ebook and return the downloaded ebook title
	public String dowloadEbook(AppiumDriver<MobileElement> driver, String strTitleEbook) {
		boolean result=true;
		int i = 0;
		try {

			result = cfObj.updateListSize(packagePageObj.getLinkDownloadEBook());
			if(!result) {
				packagePageMsgList.add("The EBook page is empty");
			}

			//			for(i=0; i<packagePageObj.getListPackageTitle().size(); i++) {
			//				if(packagePageObj.getListPackageTitle().get(i).getText().contains(strTitleEbook)) {
			//					cfObj.commonClick(packagePageObj.getLinkDownloadEBook().get(i));
			//					break;
			//				}
			//			}
			cfObj.commonClick(packagePageObj.getLinkDownloadEBook().get(i));

			Thread.sleep(2000);

			if(cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'DOWNLOAD')]", "xpath", 10)) {
				packagePageMsgList.add("Unable to download the Ebook");
			}

			strTitleEbook = packagePageObj.getListPackageTitle().get(i).getText();
			if(strTitleEbook==null) {
				packagePageMsgList.add("EBook title is null.");
			}

			cfObj.pressAndroidKey(driver, key.BACK, 1);

		} catch (Exception e) {
			packagePageMsgList.add("downloadEbook_Exception: " + e.getMessage());
			strTitleEbook=null;
		}
		return strTitleEbook;
	}

	public boolean dowloadEbook(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		int i = 0;
		try {

			result = cfObj.updateListSize(packagePageObj.getLinkDownloadEBook());
			if(!result) {
				packagePageMsgList.add("The EBook page is empty");
				return result;
			}

			//			for(i=0; i<packagePageObj.getListPackageTitle().size(); i++) {
			//				if(packagePageObj.getListPackageTitle().get(i).getText().contains(strTitleEbook)) {
			//					cfObj.commonClick(packagePageObj.getLinkDownloadEBook().get(i));
			//					break;
			//				}
			//			}
			cfObj.commonClick(packagePageObj.getLinkDownloadEBook().get(i));

			Thread.sleep(2000);

			if(cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'DOWNLOAD')]", "xpath", 5)) {
				packagePageMsgList.add("Unable to download the Ebook");
				return result;
			}

			cfObj.pressAndroidKey(driver, key.BACK, 1);

		} catch (Exception e) {
			result=false;
			packagePageMsgList.add("downloadEbook_Exception: " + e.getMessage());
		}
		return result;
	}

	public String bookmarkCurrentAffaris(AppiumDriver<MobileElement> driver, String strTitleCurrentAffaris, boolean result) {
		int i = 1;
		try {

			result = cfObj.updateListSize(packagePageObj.getListPackageTitle());
			if(!result) {
				packagePageMsgList.add("The list of package is not updating");
				return null;
			}
			
			strTitleCurrentAffaris = packagePageObj.getListPackageTitle().get(i).getText();
			if(strTitleCurrentAffaris==null) {
				packagePageMsgList.add("CurrentAffair title is null.");
				return null;
			}

			cfObj.commonClick(packagePageObj.getListPackageTitle().get(i));
			Thread.sleep(2000);

			if(cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getIconBookmark(), 10)) {
				cfObj.commonClick(packagePageObj.getIconBookmark());
				Thread.sleep(2000);
			}else {
				packagePageMsgList.add("The bookmark icon is not visible in current affair page");
				return null;
			}

			cfObj.pressAndroidKey(driver, key.BACK, 1);
			cfObj.waitForPageLoading(driver, 2000, 3, cfObj.commonGetElement(driver, "title", "id"));

		} catch (Exception e) {
			packagePageMsgList.add("bookmarkCurrentAffairs_Exception: " + e.getMessage());
			strTitleCurrentAffaris=null;
		}
		return strTitleCurrentAffaris;
	}

	public String bookmarkJobAlerts(AppiumDriver<MobileElement> driver, String strTitleJobAlerts, boolean result) {
		int i = 1;
		try {

			result = cfObj.updateListSize(packagePageObj.getListPackageTitle());
			if(!result) {
				packagePageMsgList.add("The list of package is not updating");
				return null;
			}
			strTitleJobAlerts = packagePageObj.getListPackageTitle().get(i).getText();
			if(strTitleJobAlerts==null) {
				packagePageMsgList.add("JobAlert title is null.");
				return null;
			}
			cfObj.commonClick(packagePageObj.getListPackageTitle().get(i));
			Thread.sleep(2000);

			if(cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getIconBookmark(), 10)) {
				cfObj.commonClick(packagePageObj.getIconBookmark());
				Thread.sleep(2000);
			}else {
				packagePageMsgList.add("The bookmark icon is not visible in current affair page");
				return null;
			}

			cfObj.pressAndroidKey(driver, key.BACK, 1);
			System.out.println(strTitleJobAlerts);

		} catch (Exception e) {
			packagePageMsgList.add("bookmarkJobAlerts_Exception: " + e.getMessage());
			strTitleJobAlerts = null;
		}
		return strTitleJobAlerts;
	}

	public String bookmarkArticles(AppiumDriver<MobileElement> driver, String strTitleArticles, boolean result) {
		int i = 1;
		try {

			result = cfObj.updateListSize(packagePageObj.getListPackageTitle());
			if(!result) {
				packagePageMsgList.add("The list of package is not updating");
				return null;
			}
			strTitleArticles = packagePageObj.getListPackageTitle().get(i).getText();
			if(strTitleArticles==null) {
				packagePageMsgList.add("Articles title is null.");
				return null;
			}
			cfObj.commonClick(packagePageObj.getListPackageTitle().get(i));
			Thread.sleep(2000);

			if(cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getIconBookmark(), 10)) {
				cfObj.commonClick(packagePageObj.getIconBookmark());
				Thread.sleep(2000);
			}else {
				packagePageMsgList.add("The bookmark icon is not visible in current affair page");
				return null;
			}

			cfObj.pressAndroidKey(driver, key.BACK, 1);
			System.out.println(strTitleArticles);

		} catch (Exception e) {
			packagePageMsgList.add("bookmarkArticles_Exception: " + e.getMessage());
			strTitleArticles = null;
		}
		return strTitleArticles;
	}

	public String bookmarkVideos(AppiumDriver<MobileElement> driver, String strTitleVideos, boolean result) {
		int i = 1;
		try {

			result = cfObj.updateListSize(packagePageObj.getListPackageTitle());
			if(!result) {
				packagePageMsgList.add("The list of package is not updating");
				return null;
			}
			strTitleVideos = packagePageObj.getListPackageTitle().get(i).getText();
			if(strTitleVideos==null) {
				packagePageMsgList.add("Video title is null.");
				return null;
			}
			cfObj.commonClick(packagePageObj.getListPackageTitle().get(i));
			Thread.sleep(2000);

			if(cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getIconBookmarkVideos(), 10)) {
				cfObj.commonClick(packagePageObj.getIconBookmarkVideos());
				Thread.sleep(2000);
			}else {
				packagePageMsgList.add("The bookmark icon is not visible in video page");
				return null;
			}

			cfObj.pressAndroidKey(driver, key.BACK, 1);
			System.out.println(strTitleVideos);
		} catch (Exception e) {
			packagePageMsgList.add("bookmarkVideos_Exception: " + e.getMessage());
			strTitleVideos = null;
		}
		return strTitleVideos;
	}

	public boolean verifyTestSeriesPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
				result = cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getIconShare(), 10);
			} else {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "tv_share", "id", 10);
			}
			if (!result) {
				System.out.println("Unable to verify share icon");
			}

			try {
				List<MobileElement> listOfQuiz = packagePageObj.getLinkGetQuiz();
				if (listOfQuiz == null || listOfQuiz.isEmpty()) {
					packagePageMsgList.add("Quiz icon is not present on the page.");
					return result;
				}
			} catch (NoSuchElementException e) {
				packagePageMsgList.add("Quiz element not found");
				return result;
			} catch (Exception e) {
				packagePageMsgList.add("Unexpected error while accessing quiz element: " + e.getMessage());
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getLinkGetQuiz().get(0), 10);
			if (!result) {
				packagePageMsgList.add("Unable to verify quiz icon");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getIconDownloadQuiz().get(0), 10);
			if (!result) {
				packagePageMsgList.add("Unable to verify download icon");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getTitleQuiz().get(0), 10);
			if (!result) {
				packagePageMsgList.add("Unable to verify title of quiz");
				return result;
			}
		} catch (Exception e) {
			packagePageMsgList.add("verifyTestSeriesPage_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyVideoCoursesPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getIconShare(), 10);
			if(!result) {
				packagePageMsgList.add("Unable to verify share icon");
				return result;
			}


		} catch (Exception e) {
			packagePageMsgList.add("verifyVideoCoursesPage_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyEBooksPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getIconShare(), 10);
			if(!result) {
				packagePageMsgList.add("Unable to verify share icon");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getLinkDownloadEBook().get(0), 10);
			if(!result) {
				packagePageMsgList.add("Unable to verify share icon");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getTitleEBooks().get(0), 10);
			if(!result) {
				packagePageMsgList.add("Unable to verify share icon");
				return result;
			}

		} catch (Exception e) {
			packagePageMsgList.add("verifyEBookPage_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyLiveClassPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getIconShare(), 10);
			if(!result) {
				packagePageMsgList.add("Unable to verify share icon");
				return result;
			}


		} catch (Exception e) {
			packagePageMsgList.add("veriyLiveClassPage_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyMagazinesPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getIconDownload(), 10);
			if(!result) {
				packagePageMsgList.add("Unable to verify download icon in Magazines page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getIconFilterMagazines().get(0), 10);
			if(!result) {
				packagePageMsgList.add("Unable to verify filter icon in Magazines page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getLinkDownloadMagazines().get(0), 10);
			if(!result) {
				packagePageMsgList.add("Unable to verify download link in Magazines page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getTitleMagazines().get(0), 10);
			if(!result) {
				packagePageMsgList.add("Unable to verify title in magazines page");
				return result;
			}


		} catch (Exception e) {
			packagePageMsgList.add("verifyMagazinesPage_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}
	
	public boolean verifyFreePdfPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "action_language_change", "id", 10);
			if(!result) {
				packagePageMsgList.add("Unable to verify filter icon in FreePDF page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getTitleMagazines().get(0), 10);
			if(!result) {
				packagePageMsgList.add("Unable to verify title in FreePDF page");
				return result;
			}
			
			result = cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getListPackageDescription().get(0), 10);
			if(!result) {
				packagePageMsgList.add("Unable to verify Package Description in FreePDF page");
				return result;
			}

		} catch (Exception e) {
			packagePageMsgList.add("verifyFreePdfPage_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyCapsulesPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "action_language_change", "id", 10);
			if(!result) {
				packagePageMsgList.add("Unable to verify filter icon in Capsules page");
				return result;
			}

			//			result = cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getLinkDownloadCapsules().get(0), 10);
			//			if(!result) {
			//				packagePageMsgList.add("Unable to verify download link in Capsules page");
			//				//return result;
			//			}

			result = cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getTitileCapsules().get(0), 10);
			if(!result) {
				packagePageMsgList.add("Unable to verify title in Capsules page");
				return result;
			}


		} catch (Exception e) {
			packagePageMsgList.add("veriyLiveClassPage_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyMahpackPage(AppiumDriver<MobileElement> driver, boolean liveClass, boolean testSeries, boolean videoCourses, boolean EBooks) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getBtnEBooks(), 10);
			if(result!=EBooks) {
				packagePageMsgList.add("Unable to verify EBooks btn in mahapack page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getBtnLiveClass(), 10);
			if(result!=liveClass) {
				packagePageMsgList.add("Unable to verify Live Class btn in mahapack page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getBtnTestSeries(), 10);
			if(result!=testSeries) {
				packagePageMsgList.add("Unable to verify Test series btn in mahapack page");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getBtnVideoCourses(), 10);
			if(result!=videoCourses) {
				packagePageMsgList.add("Unable to verify Video Courses btn in mahapack page");
				return result;
			}

			result = cfObj.updateListSize(packagePageObj.getListChildPackage());
			if(!result) {
				packagePageMsgList.add("The list of child package is 0");
				return result;
			}

			result = cfObj.updateListSize(packagePageObj.getListIconNotification());
			if(!result) {
				packagePageMsgList.add("The list of notificaiton icon is 0");
				return result;
			}

		} catch (Exception e) {
			packagePageMsgList.add("veriyLiveClassPage_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnPurchasedCourse(AppiumDriver<MobileElement> driver,String packageName) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getListChildPackage().get(0), 10);
			if(!result) {
				packagePageMsgList.add("Package description is not visible.");
				return result;
			}

			result=cfObj.commonVerifyValueTextBox(packagePageObj.getListChildPackage().get(0), packageName);
			if(result) {
				cfObj.commonClick(packagePageObj.getListChildPackage().get(0));
			}
			else {
				packagePageMsgList.add("Purchased Course name is not matching.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			packagePageMsgList.add("clickOnPurchasedCourse"+e.getMessage());
		}
		return result;	
	}

	public boolean validateSpecificMockTitle(AppiumDriver<MobileElement> driver, String mockName) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getTitleQuiz().get(0), 10);
			if (!result) {
				packagePageMsgList.add("Quiz title is not visible.");
				return result;
			}

			result = cfObj.commonVerifyValueTextBox(packagePageObj.getTitleQuiz().get(0), mockName);
			if (!result) {
				packagePageMsgList.add("Quiz title is not Matching.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			packagePageMsgList.add("validateSpecificMockTitle_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean downloadSpecificQuiz(AppiumDriver<MobileElement> driver, String mockName) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'" + mockName + "')]/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='GET QUIZ']", "xpath", 30);
			if (result) {
				cfObj.commonClick(driver, "//*[contains(@text,'" + mockName + "')]/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='GET QUIZ']", "xpath");
			} else {
				packagePageMsgList.add("Get_Quiz is not visible.");
				return result;
			}
			Thread.sleep(500);

		} catch (Exception e) {
			result = false;
			packagePageMsgList.add("downloadSpecificQuiz_Exception" + e.getMessage());
		}
		return result;
	}
	
	public boolean clickOnSpecificAttemptLink(AppiumDriver<MobileElement> driver,String mockName) {
		boolean result=true;
		try {

			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'"+mockName+"')]/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='ATTEMPT']", "xpath", 30);
			if(result) {
				cfObj.commonClick(driver, "//*[contains(@text,'"+mockName+"')]/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='ATTEMPT']", "xpath");
			} else {
				packagePageMsgList.add("ATTEMPT is not visible.");
				return result;
			}
			Thread.sleep(3000);

			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'"+mockName+"')]/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='ATTEMPT']", "xpath", 10);
			if(result) {
				cfObj.commonClick(driver, "//*[contains(@text,'"+mockName+"')]/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='ATTEMPT']", "xpath");
			} else {
				result = true;
			}

		} catch (Exception e) {
			result=false;
			packagePageMsgList.add("clickOnSpecificAttemptLink_Exception"+e.getMessage());
		}
		return result;
	}
	
	public boolean clickOnAttemptLink(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {

			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.TextView[@text='ATTEMPT']", "xpath", 30);
			if(result) {
				cfObj.commonClick(driver, "//android.widget.TextView[@text='ATTEMPT']", "xpath");
			}
			else {
				packagePageMsgList.add("ATTEMPT is not visible.");
				return result;
			}
			Thread.sleep(2000);

			cfObj.handleHints(driver);

		} catch (Exception e) {
			result=false;
			packagePageMsgList.add("clickOnAttemptLink_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean clickOnSpecificResultStatus(AppiumDriver<MobileElement> driver,String mockName) {
		boolean result = true;
		try {

			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'"+mockName+"')]/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='RESULT']", "xpath", 30);
			if(result) {
				cfObj.commonClick(driver, "//*[contains(@text,'"+mockName+"')]/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='RESULT']", "xpath");
			}
			else {
				packagePageMsgList.add("RESULT is not visible.");
				return result;
			}

		} catch (Exception e) {
			packagePageMsgList.add("clickOnSpecificResultStatus_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean handleSearchInstructionPopUp(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			if(cfObj.commonWaitForElementToBeVisible(driver,packagePageObj.getDoneBtn(),20)){
				cfObj.commonClick(packagePageObj.getDoneBtn());
			}
			else {
				System.out.println("Done button is not visible.");
			}

		} catch (Exception e) {
			result=false;
			packagePageMsgList.add("handleSearchInstructionPopUp_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean clickOnSpecificResumeStatus(AppiumDriver<MobileElement> driver,String mockName) {
		boolean result = true;
		try {

			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'"+mockName+"')]/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='RESUME']", "xpath", 30);
			if(result) {
				cfObj.commonClick(driver, "//*[contains(@text,'"+mockName+"')]/following-sibling::android.widget.LinearLayout/child::android.widget.TextView[@text='RESUME']", "xpath");
			}
			else {
				packagePageMsgList.add("RESUME is not visible.");
				return result;
			}

		} catch (Exception e) {
			packagePageMsgList.add("clickOnSpecificResumeStatus_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifySpecificCutOffTag(AppiumDriver<MobileElement> driver,String mockName) {
		boolean result=true;
		try {

			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'"+mockName+"')]/following-sibling::*[contains(@resource-id,'cutOffLayout')]", "xpath", 30);
			if(result) {
				packagePageMsgList.add("Cut-Off tag is not visible.");
				return result;
			}

			result=cfObj.commonVerifyValueTextBox(packagePageObj.getCutOffMarksTag().get(0), "60");
			if(result) {
				packagePageMsgList.add("Cut-Off mark-60 is not present.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			packagePageMsgList.add("verifySpecificCutOffTag_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean validateTestTagCount(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		TreeSet<String> testTag=new TreeSet<>();
		try {
			Thread.sleep(3000);
			if(cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getCertificateLabelTestTag(), 10)) {
				result=cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getProgressBarTextValue(), 10);
				if(!result) {
					packagePageMsgList.add("ProgressBar Text value is not visible.");
					return result;
				}
				String testTagCount=packagePageObj.getProgressBarTextValue().getText().split(" ")[0];
				if(testTagCount==null) {
					packagePageMsgList.add("TestTag count is null.");
					return false;
				}
				int index=testTagCount.indexOf("/");
				System.out.println(index);
				System.out.println(testTagCount.substring(index).replace("/", ""));
				int totalTestTagCount=Integer.parseInt(testTagCount.substring(index).replace("/", ""));
				System.out.println("Total TestTag:-->"+totalTestTagCount);
				System.out.println(testTagCount.substring(0,testTagCount.indexOf('/')));
				int attemptedTestTagCount=Integer.parseInt(testTagCount.substring(0,testTagCount.indexOf('/')));
				System.out.println("Attempted TestTag:-->"+attemptedTestTagCount);
				for(int i=0;i<totalTestTagCount;i++) {
					for(int j=0;j<packagePageObj.getQuizTestTag().size();j++) {
						testTag.add(packagePageObj.getQuizTestTag().get(j).getText());
					}
					cfObj.scrollUtill(driver, 1, direction.DOWN);
				}

				result=totalTestTagCount==testTag.size();
				if(!result) {
					packagePageMsgList.add("TestTag count is not matching.");
					return result;
				}
			}

		} catch (Exception e) {
			result=false;
			packagePageMsgList.add("validateTestTagCount_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean validateUpdatedCertificatePercentage(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {

			if(cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getCertificateLabelTestTag(), 10)) {
				result=cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getProgressBarTextValue(), 10);
				if(!result) {
					packagePageMsgList.add("ProgressBar Text value is not visible.");
					return result;
				}
				String testTagCount=packagePageObj.getProgressBarTextValue().getText().split(" ")[0];
				if(testTagCount==null) {
					packagePageMsgList.add("TestTag count is null.");
					return false;
				}
				int index=testTagCount.indexOf("/");
				System.out.println(index);
				double totalTestTagCount=Double.parseDouble(testTagCount.substring(index).replace("/", ""));
				System.out.println("Total TestTag:-->"+totalTestTagCount);
				double attemptedTestTagCount=Double.parseDouble(testTagCount.substring(0,index));
				System.out.println("Attempted TestTag:-->"+attemptedTestTagCount);

				result=cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getProgressBarPercentage(), 10);
				if(!result) {
					packagePageMsgList.add("ProgressBarPercentage is not visible.");
					return result;
				}
				int certificatePercentage=Integer.parseInt(packagePageObj.getProgressBarPercentage().getText().replace("%", "").trim());
				System.out.println("CertificatePercentage:-->"+certificatePercentage);
				if(attemptedTestTagCount>0) {
					double updatedPercentage=(attemptedTestTagCount/totalTestTagCount)*100;
					System.out.println("Updated Percentage:--"+updatedPercentage);
					result=certificatePercentage==(int)updatedPercentage;
					if(!result) {
						packagePageMsgList.add("Updated Certificate Percentage is not matching.");
						return result;
					}
					result=validateCertificatePercentage(driver);
					if(!result) {
						packagePageMsgList.add("Not able to validate CertificatePercentage.");
						return result;
					}
				}
			}

		} catch (Exception e) {
			result=false;
			packagePageMsgList.add("validateUpdatedCertificatePercentage_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean validateQuizCertificateCell(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {

			result=cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getCertificateQuizTitle(), 10);
			if(!result) {
				packagePageMsgList.add("CertificateQuizTitle is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getProgressBarTextValue(), 10);
			if(!result) {
				packagePageMsgList.add("ProgressBar Text value is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getCertificateAttemptBtn(), 10);
			if(!result) {
				packagePageMsgList.add("ProgressBar Text value is not visible.");
				return result;
			}

			result=cfObj.commonVerifyValueTextBox(packagePageObj.getCertificateAttemptBtn(), "Attempt");
			if(!result) {
				packagePageMsgList.add("CertificateAttemptBtn text is not matching.");
				return result;
			}

			result=cfObj.commonVerifyValueTextBox(packagePageObj.getCertificateAttemptBtn(), "Attempt");
			if(!result) {
				packagePageMsgList.add("CertificateAttemptBtn text is not matching.");
				return result;
			}

			result=validateCertificatePercentage(driver);
			if(!result) {
				packagePageMsgList.add("Not able to validate CertificatePercentage.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			packagePageMsgList.add("validateQuizCertificateCell_Exception"+e.getMessage());
		}
		return result;		
	}

	public boolean validateCertificatePercentage(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getProgressBarPercentage(), 10);
			if(!result) {
				packagePageMsgList.add("ProgressBarPercentage is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getCertificateProgressBar(), 10);
			if(!result) {
				packagePageMsgList.add("CertificateProgressBar is not visible.");
				return result;
			}

			double progressBarValue=Double.parseDouble(packagePageObj.getCertificateProgressBar().getText());
			System.out.println("ProgressBarValue:-->"+progressBarValue);
			double certificatePercentage=Double.parseDouble(packagePageObj.getProgressBarPercentage().getText().replace("%", "").trim());
			System.out.println("CertificatePercentage:-->"+certificatePercentage);

			result=progressBarValue==certificatePercentage;
			if(!result) {
				packagePageMsgList.add("Certificate Percentage is not matching.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			packagePageMsgList.add("validateCertificatePercentage_Exception"+e.getMessage());
		}
		return result;		
	}


	public boolean attemptSpecificTestTagQuizInCutOffCase(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getQuizTestTag().get(0), 10);
			if(!result) {
				packagePageMsgList.add("Quiz test tag is not visible.");
				return result;
			}

			String testTag=packagePageObj.getQuizTestTag().get(0).getText();
			if(testTag==null) {
				packagePageMsgList.add("TestTag text is null.");
				return false;
			}
            System.out.println(testTag);
			result=validateReAttemptBtnInSpecificQuiz(driver, testTag,true);
			if(!result) {
				packagePageMsgList.add("Not able to validate ReAttemptBtnInSpecificQuiz.");
				return result;
			}

			cfObj.commonClick(driver, "//*[@text='"+testTag+"']/parent::*[contains(@resource-id,'test_level')]/following-sibling::*[contains(@resource-id,'btm_strip')]/child::*[@text='REATTEMPT']", "xpath");

			commonTestUtilObj=new CommonTestUtil(driver);
			result = commonTestUtilObj.verifyInstructionPage(driver);
			if (!result) {
				packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
				return result;
			}
			result = commonTestUtilObj.validateCutOffMarksInstruction(driver);
			if (!result) {
				packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
				return result;
			}

			result = commonTestUtilObj.completeAndSubmitTestWithCorrectAnswer(driver,false,false);
			if (!result) {
				packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
				return result;
			}
			Thread.sleep(2000);
//			result=commonTestUtilObj.handleTestRatingPopUp(driver);
//			if (!result) {
//				packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
//				return result;
//			}
//
//			result=commonTestUtilObj.verifyAndCloseCoinPopup(driver);
//			if (!result) {
//				packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
//				return result;
//			}

			result=commonTestUtilObj.validateSolutionBtnStatus(driver);
			if (!result) {
				packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
				return result;
			}

			cfObj.commonClick(commonTestUtilObj.commonTestPageObj.getBtnNavigateUp());
			//Later will remove this statement
//			result=commonTestUtilObj.handleTestRatingPopUp(driver);
//			if (!result) {
//				packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
//				return result;
//			}
//			result=cfObj.pressAndroidKey(driver, key.BACK, 1);
//			if (!result) {
//				packagePageMsgList.add("Not able to click back button.");
//				return result;
//			}

			Thread.sleep(2000);
			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='"+testTag+"']/parent::*[contains(@resource-id,'test_level')]/following-sibling::*[contains(@resource-id,'btm_strip')]/child::*[@text='RESULT']", "xpath", 30);
			if(!result) {
				packagePageMsgList.add("Result button is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='"+testTag+"']/preceding-sibling::*[@text='Completed']", "xpath", 30);
			if(!result) {
				packagePageMsgList.add("Completed Tag is not visible.");
				return result;
			}

			result=validateUpdatedCertificatePercentage(driver);
			if(!result) {
				packagePageMsgList.add("Not able to validate UpdatedCertificatePercentage.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			packagePageMsgList.add("attemptSpecificTestTagQuizInCutOffCase_Exception"+e.getMessage());
		}
		return result;	
	}

	public boolean attemptSpecificTestTagQuiz(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getQuizTestTag().get(0), 10);
			if(!result) {
				packagePageMsgList.add("Quiz test tag is not visible.");
				return result;
			}

			String testTag=packagePageObj.getQuizTestTag().get(0).getText();
            if(testTag==null) {
            	packagePageMsgList.add("Test Tag text is null.");
            	return false;
            }
			
			result=validateReAttemptBtnInSpecificQuiz(driver, testTag,false);
			if(!result) {
				packagePageMsgList.add("Not able to validate ReAttemptBtnInSpecificQuiz.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='"+testTag+"']/preceding-sibling::*[@text='Completed']", "xpath", 30);
			if(!result) {
				packagePageMsgList.add("Completed Tag is not visible.");
				return result;
			}

			result=validateUpdatedCertificatePercentage(driver);
			if(!result) {
				packagePageMsgList.add("Not able to validate UpdatedCertificatePercentage.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			packagePageMsgList.add("attemptSpecificTestTagQuiz_Exception"+e.getMessage());
		}
		return result;	
	}

	public boolean validateReAttemptBtnInSpecificQuiz(AppiumDriver<MobileElement> driver,String testTag,boolean isCutoff) {
		boolean result=true;
		try {

			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='"+testTag+"']/parent::*[contains(@resource-id,'test_level')]/following-sibling::*[contains(@resource-id,'btm_strip')]/child::*[@text='GET QUIZ']", "xpath", 30);
			if(!result) {
				packagePageMsgList.add("GetQuiz button is not visible.");
				return result;
			}
			cfObj.commonClick(driver, "//*[@text='"+testTag+"']/parent::*[contains(@resource-id,'test_level')]/following-sibling::*[contains(@resource-id,'btm_strip')]/child::*[@text='GET QUIZ']", "xpath");
			Thread.sleep(4000);
			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='"+testTag+"']/parent::*[contains(@resource-id,'test_level')]/following-sibling::*[contains(@resource-id,'btm_strip')]/child::*[@text='ATTEMPT']", "xpath", 30);
			if(!result) {
				packagePageMsgList.add("Attempt button is not visible.");
				return result;
			}

			cfObj.commonClick(driver, "//*[@text='"+testTag+"']/parent::*[contains(@resource-id,'test_level')]/following-sibling::*[contains(@resource-id,'btm_strip')]/child::*[@text='ATTEMPT']", "xpath");
            Thread.sleep(3000);
			commonTestUtilObj = new CommonTestUtil(driver);
			//cfObj.handleHints(driver);

			result = commonTestUtilObj.verifyInstructionPage(driver);
			if (!result) {
				packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
				return result;
			}

			result=commonTestUtilObj.clickStartTestBtn(driver);
			if (!result) {
				packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
				return result;
			}
			
			if(ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
				result=commonTestUtilObj.clickOnFilterBtn(driver);
				if(!result) {
					packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
					return result;
				}
			}


			result=commonTestUtilObj.clickOnSubmitTestBtn(driver);
			if (!result) {
				packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
				return result;
			}

//			result=commonTestUtilObj.handleTestRatingPopUp(driver);
//			if (!result) {
//				packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
//				return result;
//			}
			if(isCutoff) {
				result=commonTestUtilObj.validateSolutionBtnStatusInCutOffMockCase(driver);
				if (!result) {
					packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
					return result;
				}
				cfObj.commonClick(commonTestUtilObj.commonTestPageObj.getBtnNavigateUp());

//				//Later will remove this statement
//				result=commonTestUtilObj.handleTestRatingPopUp(driver);
//				if (!result) {
//					packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
//					return result;
//				}

				Thread.sleep(3000);

				result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='"+testTag+"']/parent::*[contains(@resource-id,'test_level')]/following-sibling::*[contains(@resource-id,'btm_strip')]/child::*[@text='REATTEMPT']", "xpath", 30);
				if(!result) {
					packagePageMsgList.add("ReAttempt button is not visible.");
					return result;
				}
			}else {
				result=commonTestUtilObj.validateSolutionBtnStatus(driver);
				if (!result) {
					packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
					return result;
				}
				cfObj.commonClick(commonTestUtilObj.commonTestPageObj.getBtnNavigateUp());

//				//Later will remove this statement
//				result=commonTestUtilObj.handleTestRatingPopUp(driver);
//				if (!result) {
//					packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
//					return result;
//				}

				Thread.sleep(3000);
				result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='"+testTag+"']/parent::*[contains(@resource-id,'test_level')]/following-sibling::*[contains(@resource-id,'btm_strip')]/child::*[@text='RESULT']", "xpath", 30);
				if(!result) {
					packagePageMsgList.add("Result button is not visible.");
					return result;
				}
			}

		} catch (Exception e) {
			result=false;
			packagePageMsgList.add("validateReAttemptBtnInSpecificQuiz_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean validateCertificateReAttemptAndResumeBtn(AppiumDriver<MobileElement> driver,boolean isCutOff) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getCertificateAttemptBtn(), 10);
			if(!result) {
				packagePageMsgList.add("Quiz test tag is not visible.");
				return result;
			}

			result=cfObj.commonVerifyValueTextBox(packagePageObj.getCertificateAttemptBtn(), "Attempt");
			if(!result) {
				packagePageMsgList.add("Certificate Attempt button is not visible.");
				return result;
			}

			cfObj.commonClick(packagePageObj.getCertificateAttemptBtn());
            Thread.sleep(1000);
			//Later will Remove it

			cfObj.commonClick(packagePageObj.getCertificateAttemptBtn());

			Thread.sleep(2000);
			commonTestUtilObj=new CommonTestUtil(driver);
			result = commonTestUtilObj.verifyInstructionPage(driver);
			if (!result) {
				packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
				return result;
			}
			if(isCutOff) {

				result = commonTestUtilObj.validateCutOffMarksInstruction(driver);
				if (!result) {
					packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
					return result;
				}
			}

			result =commonTestUtilObj. clickStartTestBtn(driver);
			if (!result) {
				packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
				return result;
			}

			Thread.sleep(2000); //page loading takes time

			result =commonTestUtilObj.clickOnTestPauseBtn(driver);
			if (!result) {
				packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
				return result;
			}

			result=cfObj.commonVerifyValueTextBox(packagePageObj.getCertificateAttemptBtn(), "Resume");
			if(!result) {
				packagePageMsgList.add("Certificate Resume button is not visible.");
				return result;
			}

			cfObj.commonClick(packagePageObj.getCertificateAttemptBtn());

			result =commonTestUtilObj.clickOnResumeTestBtn(driver);
			if (!result) {
				packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
				return result;
			}
			
			if(ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
				result=commonTestUtilObj.clickOnFilterBtn(driver);
				if(!result) {
					packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
					return result;
				}
			}

			result =commonTestUtilObj.clickOnSubmitTestBtn(driver);
			if (!result) {
				packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
				return result;
			}

//			result=commonTestUtilObj.handleTestRatingPopUp(driver);
//			if (!result) {
//				packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
//				return result;
//			}
			if(isCutOff) {
				result=commonTestUtilObj.validateSolutionBtnStatusInCutOffMockCase(driver);
				if (!result) {
					packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
					return result;
				}

			}else {
				result=commonTestUtilObj.validateSolutionBtnStatus(driver);
				if (!result) {
					packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
					return result;
				}
			}
			Thread.sleep(1000);
			cfObj.commonClick(commonTestUtilObj.commonTestPageObj.getBtnNavigateUp());

			Thread.sleep(3000);
			if(isCutOff) {
				result=cfObj.commonVerifyValueTextBox(packagePageObj.getCertificateAttemptBtn(), "Reattempt");
				if(!result) {
					packagePageMsgList.add("Certificate Reattempt button is not visible.");
					return result;
				}

				cfObj.commonClick(packagePageObj.getCertificateAttemptBtn());

				result = commonTestUtilObj.verifyInstructionPage(driver);
				if (!result) {
					packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
					return result;
				}

				result = commonTestUtilObj.completeAndSubmitTestWithCorrectAnswer(driver,false,false);
				if (!result) {
					packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
					return result;
				}
//				result=commonTestUtilObj.handleTestRatingPopUp(driver);
//				if (!result) {
//					packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
//					return result;
//				}
//
//				result=commonTestUtilObj.verifyAndCloseCoinPopup(driver);
//				if (!result) {
//					packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
//					return result;
//				}

				result=commonTestUtilObj.validateSolutionBtnStatus(driver);
				if (!result) {
					packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
					return result;
				}

				cfObj.commonClick(commonTestUtilObj.commonTestPageObj.getBtnNavigateUp());
				//Later will remove this statement
//				result=commonTestUtilObj.handleTestRatingPopUp(driver);
//				if (!result) {
//					packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
//					return result;
//				}
//				result=cfObj.pressAndroidKey(driver, key.BACK, 1);
//				if (!result) {
//					packagePageMsgList.add("Not able to click back button.");
//					return result;
//				}
			}
			result=cfObj.commonVerifyValueTextBox(packagePageObj.getCertificateAttemptBtn(), "Attempt");
			if(!result) {
				packagePageMsgList.add("Certificate Attempt button is not visible.");
				return result;
			}

			result=validateUpdatedCertificatePercentage(driver);
			if(!result) {
				packagePageMsgList.add("Not able to validate UpdatedCertificatePercentage.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			packagePageMsgList.add("validateCertificateReAttemptAndResumeBtn_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean validateCertificateAttemptBtnInCutOffMock(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			String testTagCount=packagePageObj.getProgressBarTextValue().getText().split(" ")[0];
			if(testTagCount==null) {
				packagePageMsgList.add("TestTag count is null.");
				return false;
			}
			int index=testTagCount.indexOf("/");
			System.out.println(index);
			int totalTestTagCount=Integer.parseInt(testTagCount.substring(index).replace("/", ""));
			System.out.println("Total TestTag:-->"+totalTestTagCount);
			for(int i=2;i<totalTestTagCount;i++) {
				result=cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getCertificateAttemptBtn(), 10);
				if(!result) {
					packagePageMsgList.add("Quiz test tag is not visible.");
					return result;
				}

				result=cfObj.commonVerifyValueTextBox(packagePageObj.getCertificateAttemptBtn(), "Attempt");
				if(!result) {
					packagePageMsgList.add("Certificate Attempt button is not visible.");
					return result;
				}

				cfObj.commonClick(packagePageObj.getCertificateAttemptBtn());
				if(i==2) {
					cfObj.commonClick(packagePageObj.getCertificateAttemptBtn());
				}

				Thread.sleep(2000);
				commonTestUtilObj=new CommonTestUtil(driver);
				result = commonTestUtilObj.verifyInstructionPage(driver);
				if (!result) {
					packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
					return result;
				}

				result = commonTestUtilObj.validateCutOffMarksInstruction(driver);
				if (!result) {
					packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
					return result;
				}

				result = commonTestUtilObj.completeAndSubmitTestWithCorrectAnswer(driver,false,false);
				if (!result) {
					packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
					return result;
				}
//				result=commonTestUtilObj.handleTestRatingPopUp(driver);
//				if (!result) {
//					packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
//					return result;
//				}
//
//				result=commonTestUtilObj.verifyAndCloseCoinPopup(driver);
//				if (!result) {
//					packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
//					return result;
//				}

				result=commonTestUtilObj.validateSolutionBtnStatusInCutOffMockCase(driver);
				if (!result) {
					packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
					return result;
				}

				cfObj.commonClick(commonTestUtilObj.commonTestPageObj.getBtnNavigateUp());
				//Later will remove this statement
//				result=commonTestUtilObj.handleTestRatingPopUp(driver);
//				if (!result) {
//					packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
//					return result;
//				}
//				result=cfObj.pressAndroidKey(driver, key.BACK, 1);
//				if (!result) {
//					packagePageMsgList.add("Not able to click back button.");
//					return result;
//				}
				result=validateUpdatedCertificatePercentage(driver);
				if(!result) {
					packagePageMsgList.add("Not able to validate UpdatedCertificatePercentage.");
					return result;
				}
			}

		} catch (Exception e) {
			result=false;
			packagePageMsgList.add("validateCertificateAttemptBtnInCutOffMock_Exception"+e.getMessage());
		}
		return result;	
	}

	public boolean validateCertificateAttemptBtn(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			String testTagCount=packagePageObj.getProgressBarTextValue().getText().split(" ")[0];
			if(testTagCount==null) {
				packagePageMsgList.add("TestTag count is null.");
				return false;
			}
			int index=testTagCount.indexOf("/");
			System.out.println(index);
			int totalTestTagCount=Integer.parseInt(testTagCount.substring(index).replace("/", ""));
			System.out.println("Total TestTag:-->"+totalTestTagCount);
			for(int i=2;i<totalTestTagCount;i++) {
				result=cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getCertificateAttemptBtn(), 10);
				if(!result) {
					packagePageMsgList.add("Quiz test tag is not visible.");
					return result;
				}

				result=cfObj.commonVerifyValueTextBox(packagePageObj.getCertificateAttemptBtn(), "Attempt");
				if(!result) {
					packagePageMsgList.add("Certificate Attempt button is not visible.");
					return result;
				}

				cfObj.commonClick(packagePageObj.getCertificateAttemptBtn());

				//Later will Remove it
                Thread.sleep(1000);
				cfObj.commonClick(packagePageObj.getCertificateAttemptBtn());

				Thread.sleep(2000);
				commonTestUtilObj=new CommonTestUtil(driver);
				result = commonTestUtilObj.verifyInstructionPage(driver);
				if (!result) {
					packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
					return result;
				}

				result = commonTestUtilObj.completeAndSubmitTest(driver, false, false, false);
				if (!result) {
					packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
					return result;
				}
//				result=commonTestUtilObj.handleTestRatingPopUp(driver);
//				if (!result) {
//					packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
//					return result;
//				}
//
//				result=commonTestUtilObj.verifyAndCloseCoinPopup(driver);
//				if (!result) {
//					packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
//					return result;
//				}

				result=commonTestUtilObj.validateSolutionBtnStatus(driver);
				if (!result) {
					packagePageMsgList.addAll(commonTestUtilObj.commonTestMsgList);
					return result;
				}

				cfObj.commonClick(commonTestUtilObj.commonTestPageObj.getBtnNavigateUp());

				result=validateUpdatedCertificatePercentage(driver);
				if(!result) {
					packagePageMsgList.add("Not able to validate UpdatedCertificatePercentage.");
					return result;
				}
			}

		} catch (Exception e) {
			result=false;
			packagePageMsgList.add("validateCertificateAttemptBtn_Exception"+e.getMessage());
		}
		return result;	
	}


	public boolean clickOnGenerateCertificateBtn(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {

			result=cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getCertificateAttemptBtn(), 10);
			if(!result) {
				packagePageMsgList.add("CertificateAttemptBtn is not visible.");
				return result;
			}

			result=cfObj.commonVerifyValueTextBox(packagePageObj.getCertificateAttemptBtn(), "Generate Certificate");
			if(!result) {
				packagePageMsgList.add("Generate Certificate text is not matching.");
				return result;
			}

			cfObj.commonClick(packagePageObj.getCertificateAttemptBtn());

			result=cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getCandidateDetailsPopUpTitle(), 10);
			if(!result) {
				packagePageMsgList.add("CandidateDetailsPopUpTitle is not visible.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			packagePageMsgList.add("clickOnGenerateCertificateBtn_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean fillCandidateDetailsForm(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getCandidateDetailsPopUpTitle(), 10);
			if(!result) {
				packagePageMsgList.add("CandidateDetailsPopUpTitle is not visible.");
				return result;
			}
			if(cfObj.commonWaitForElementToBeVisible(driver,packagePageObj.getCandidateNameTextField(),5)) {
				result=cfObj.commonSetTextTextBox(packagePageObj.getCandidateNameTextField(), "ADDA247");
				if(!result) {
					packagePageMsgList.add("Not able to enter name in NameTextField.");
					return result;
				}
			}
			else {
				result=scrollTillElementAndFillTextField(driver, packagePageObj.getFathersNameTextField(), "Sankalp");
				if(!result) {
					packagePageMsgList.add("Not able to enter Father's name.");
					return result;
				}

				result=scrollTillElementAndFillTextField(driver, packagePageObj.getCityTextField(), "Delhi");
				if(!result) {
					packagePageMsgList.add("Not able to enter City name.");
					return result;
				}

				result=scrollTillElementAndFillTextField(driver, packagePageObj.getMothersNameTextField(), "Adda");
				if(!result) {
					packagePageMsgList.add("Not able to enter Mother's name.");
					return result;
				}

				result=scrollTillElementAndFillTextField(driver, packagePageObj.getStateTextField(), "Haryana");
				if(!result) {
					packagePageMsgList.add("Not able to enter State name.");
					return result;
				}

				result=scrollTillElementAndFillTextField(driver, packagePageObj.getPlaceTextField(), "Noida");
				if(!result) {
					packagePageMsgList.add("Not able to enter Place name.");
					return result;
				}

				result=scrollTillElementAndFillTextField(driver, packagePageObj.getCandidateNameTextField(), "ADDA247");
				if(!result) {
					packagePageMsgList.add("Not able to enter Candidate name.");
					return result;
				}

				result=scrollTillElementAndFillTextField(driver, packagePageObj.getCategoryNameTextField(), "GEN");
				if(!result) {
					packagePageMsgList.add("Not able to enter Category name.");
					return result;
				}

				result=scrollTillElementAndFillTextField(driver, packagePageObj.getCollegeTextField(), "IIT");
				if(!result) {
					packagePageMsgList.add("Not able to enter College name.");
					return result;
				}

				result=scrollTillElementAndFillTextField(driver, packagePageObj.getAddressTextField(), "Gurgaon, Haryana, India");
				if(!result) {
					packagePageMsgList.add("Not able to enter Address.");
					return result;
				}
			}
			Thread.sleep(2000);
			cfObj.commonClick(packagePageObj.getSubmitBtn());

		} catch (Exception e) {
			result=false;
			packagePageMsgList.add("fillCandidateDetailsForm_Exception"+e.getMessage());
		}
		return result;	
	}

	public boolean scrollTillElementAndFillTextField(AppiumDriver<MobileElement> driver,MobileElement element,String fieldText) {
		boolean result=true;
		try {
			int i=0;
			while(!cfObj.commonWaitForElementToBeVisible(driver, element, 5)) {
				cfObj.swipeUp(driver,0.03,0.5, 0.3);
				if(i>2) {
					System.out.println("Element is not visible: "+ element);
					break;
				}
				i++;
			}

			result=cfObj.commonSetTextTextBox(element, fieldText);
			if(!result) {
				packagePageMsgList.add("Not able to enter text in TextField.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			packagePageMsgList.add("scrollTillElementAndFillTextField_Exception"+e.getMessage());		}
		return result;	
	}

	public boolean clickOnViewCertificateBtn(AppiumDriver<MobileElement> driver,String imagePath) {
		boolean result=true;
		try {

			result=cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getCertificateAttemptBtn(), 10);
			if(!result) {
				packagePageMsgList.add("CertificateAttemptBtn is not visible.");
				return result;
			}

			result=cfObj.commonVerifyValueTextBox(packagePageObj.getCertificateAttemptBtn(), "View Certificate");
			if(!result) {
				packagePageMsgList.add("View Certificate text is not matching.");
				return result;
			}

			cfObj.commonClick(packagePageObj.getCertificateAttemptBtn());

			certificatePreviewUtilObj=new CertificatePreviewUtil(driver);
			result=cfObj.commonWaitForElementToBeVisible(driver, certificatePreviewUtilObj.certificatePreviewUtilORObj.getCertificateScreen(), 10);
			if(!result) {
				packagePageMsgList.add("CertificateScreen is not visible.");
				return result;
			}

			//			Thread.sleep(2000);
			//			result=cfObj.compareTwoImages(driver, imagePath, imagePath, 95);
			//			if (!result) {
			//				packagePageMsgList.add("Unable to compare "+ imagePath +".");
			//				return result;
			//			}

			result=cfObj.pressAndroidKey(driver, key.BACK, 1);
			if(!result) {
				packagePageMsgList.add("Not able to press back button.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, packagePageObj.getCertificateAttemptBtn(), 10);
			if(!result) {
				packagePageMsgList.add("CertificateAttemptBtn is not visible.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			packagePageMsgList.add("clickOnViewCertificateBtn_Exception"+e.getMessage());
		}
		return result;
	}
	
	public boolean selectSpecificTab(AppiumDriver<MobileElement> driver,MobileElement element) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, element, 10);
			if (!result) {
				packagePageMsgList.add("Specific Live Test tab is not visible.");
				return result;
			}

			cfObj.commonClick(element);
			result=element.getAttribute("checked").equalsIgnoreCase("true");
			if (!result) {
				packagePageMsgList.add("Failed to select Specific Live Test tab.");
				return result;
			}
			
		} catch (Exception e) {
			result=false;
			packagePageMsgList.add("selectSpecificTab_Exception"+e.getMessage());
		}
		return result;
	}
	
	public boolean clickOnResultStatus(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			int i=0;
			while (!cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.widget.TextView[@text='RESULT']", "xpath", 5)) {
				cfObj.scrollUtill(driver, 1, direction.DOWN);
				if(i>5) {
					System.out.println("Result status is not available.");
					result=false;
					break;
				}
				i++;

			}
			if(!result) {
				packagePageMsgList.add("RESULT is not visible.");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElements(driver, "//android.widget.TextView[@text='RESULT']", "xpath").get(0));

		} catch (Exception e) {
			packagePageMsgList.add("clickOnResultStatus_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}
}
