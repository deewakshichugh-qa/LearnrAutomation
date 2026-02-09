package pageObject;

import java.util.List;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class StudyMaterialTab_OR {

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Premium Study Material99\")")
	private MobileElement titlePremiumStudyMaterial99;

	public MobileElement getTitlePremiumStudyMaterial99() {
		return titlePremiumStudyMaterial99;
	}

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"New Material 25 May\")")
	private MobileElement titleNewMaterial25May;

	public MobileElement getTitleNewMaterial25May() {
		return titleNewMaterial25May;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Current Affairs New99']")
	private MobileElement titleCurrentAffairsNew99;

	public MobileElement getTitleCurrentAffairsNew99() {
		return titleCurrentAffairsNew99;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Job Alerts99']")
	private MobileElement titleJobAlerts99;

	public MobileElement getTitleJobAlerts99() {
		return titleJobAlerts99;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Test Rikita99']")
	private MobileElement titleTestRikita;

	public MobileElement getTitleTestRikita99() {
		return titleTestRikita;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Subject-wise Quizzes99']")
	private MobileElement titleSubjectWiseQuizzes99;

	public MobileElement getTitleSubjectWiseQuizzes99() {
		return titleSubjectWiseQuizzes99;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Magazines99']")
	private MobileElement titleMagazines99;

	public MobileElement getTitleMagazines99() {
		return titleMagazines99;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Power Capsules99']")
	private MobileElement titlePowerCapsules99;

	public MobileElement getTitlePowerCapsules99() {
		return titlePowerCapsules99;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Notes and Articles']")
	private MobileElement titleNotesAndArticles;

	public MobileElement getTitleNotesAndArticles() {
		return titleNotesAndArticles;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Videos99']")
	private MobileElement titleVideos99;

	public MobileElement getTitleVideos99() {
		return titleVideos99;
	}

	// -------------------------------------------PRODUCTION---------------------------------------------------

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Premium Study Material']")
	private MobileElement titlePremiumStudyMaterial;

	public MobileElement getTitlePremiumStudyMaterial() {
		return titlePremiumStudyMaterial;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Current Affairs']")
	@iOSXCUITFindBy(accessibility = "Current Affairs")
	private MobileElement titleCurrentAffairs;

	public MobileElement getTitleCurrentAffairs() {
		return titleCurrentAffairs;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Job Alerts']")
	@iOSXCUITFindBy(accessibility = "Job Alerts")
	private MobileElement titleJobAlerts;

	public MobileElement getTitleJobAlerts() {
		return titleJobAlerts;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Daily')]")
	@iOSXCUITFindBy(accessibility = "Daily Quizzes")
	private MobileElement titleDailyQuizzes;

	public MobileElement getTitleDailyQuizzes() {
		return titleDailyQuizzes;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Subject-wise Quizzes']")
	@iOSXCUITFindBy(accessibility = "Subject-wise Quizzes")
	private MobileElement titleSubjectWiseQuizzes;

	public MobileElement getTitleSubjectWiseQuizzes() {
		return titleSubjectWiseQuizzes;
	}

	@AndroidFindBy(id = "com.adda247.app:id/title")
	private List<MobileElement> dailyQuizeTopicList;

	public List<MobileElement> getDailyQuizeTopicList() {
		return dailyQuizeTopicList;
	}

	@AndroidFindBy(accessibility = "Navigate up")
	private MobileElement backBtn;

	public MobileElement getBackBtn() {
		return backBtn;
	}

	@AndroidFindBy(id = "text")
	private MobileElement filterInstructionText;

	public MobileElement getFilterInstructionText() {
		return filterInstructionText;
	}

	@AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id='com.adda247.app:id/toolbar']/android.widget.TextView")
	private MobileElement selectedQuizeTitle;

	public MobileElement getSelectedQuizeTitle() {
		return selectedQuizeTitle;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Free PDF']")
	@iOSXCUITFindBy(accessibility = "Free PDF")
	private MobileElement titleFreePDF;

	public MobileElement getTitleFreePDF() {
		return titleFreePDF;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='All India Mock & Scholarship Tests']")
	@iOSXCUITFindBy(accessibility = "All India Mock & Scholarship Tests")
	private MobileElement titleAllIndiaMock;

	public MobileElement getTitleAllIndiaMock() {
		return titleAllIndiaMock;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Power Capsules']")
	@iOSXCUITFindBy(accessibility = "Power Capsules")
	private MobileElement titlePowerCapsules;

	public MobileElement getTitlePowerCapsules() {
		return titlePowerCapsules;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Videos']")
	@iOSXCUITFindBy(accessibility = "Videos")
	private MobileElement titleVideos;

	public MobileElement getTitleVideos() {
		return titleVideos;
	}

	@AndroidFindBy(id = "action_language_change")
	private MobileElement filterIcon;

	public MobileElement getFilterIcon() {
		return filterIcon;
	}

	@AndroidFindBy(id = "card_background")
	private List<MobileElement> quizCardList;

	public List<MobileElement> getQuizCardList() {
		return quizCardList;
	}

	@AndroidFindBy(id = "title")
	private List<MobileElement> studyMaterialTabList;

	public List<MobileElement> getStudyMaterialTabList() {
		return studyMaterialTabList;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Navigate up']/following-sibling::android.widget.TextView")
	private MobileElement pageTitle;

	public MobileElement getPageTitle() {
		return pageTitle;
	}

	/*---------------------------Sankalpa Application------------------------------------*/

	@AndroidFindBy(xpath = "//*[@text='Daily Tests']")
	private MobileElement dailyTestPageTitle;

	public MobileElement getDailyTestPageTitle() {
		return dailyTestPageTitle;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='All India Mock & Scholarship Tests']")
	private MobileElement scholarshipTestTab;

	public MobileElement getScholarshipTestTab() {
		return scholarshipTestTab;
	}

	@AndroidFindBy(id = "tv_timer_title")
	private MobileElement mockCardTimer;

	public MobileElement getMockCardTimer() {
		return mockCardTimer;
	}

	@AndroidFindBy(accessibility = "Home")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'Home')]")
	private MobileElement btnHome;

	public MobileElement getBtnHome() {
		return btnHome;
	}

}