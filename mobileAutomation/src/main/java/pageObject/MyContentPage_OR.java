package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class MyContentPage_OR {

    @AndroidFindBy(xpath = "//*[contains(@content-desc,'GO TO DOUBTS')]")
    private MobileElement btnGoToDoubts;

    public MobileElement getBtnGoToDoubts() {
        return btnGoToDoubts;
    }

    @AndroidFindBy(xpath = "//*[contains(@content-desc,'No Doubts found!')]")
    private MobileElement titleNoDoubtsFound;

    public MobileElement getTitleNoDoubtsFound() {
        return titleNoDoubtsFound;
    }

    @AndroidFindBy(xpath = "//*[contains(@content-desc,'No Doubts are Bookmarked!')]")
    private MobileElement titleNoDoubtsAreBookmarked;

    public MobileElement getTitleNoDoubtsAreBookmarked() {
        return titleNoDoubtsAreBookmarked;
    }

    @AndroidFindBy(id = "emptyViewBuy")
    private MobileElement btnGoToPurchased;

    public MobileElement getBtnGoToPurchased() {
        return btnGoToPurchased;
    }

    @AndroidFindBy(id = "notification_item")
    private MobileElement iconNotification;

    public MobileElement getIconNotification() {
        return iconNotification;
    }

    @AndroidFindBy(id = "purchaseTitle")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Purchased']")
    private MobileElement btnPurchasedSection;

    public MobileElement getBtnPurchasedSection() {
        return btnPurchasedSection;
    }

    @AndroidFindBy(id = "downloads_title")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Downloads']")
    private MobileElement btnDownloadSection;

    public MobileElement getBtnDownloadSection() {
        return btnDownloadSection;
    }

    @AndroidFindBy(id = "doubt_title")
    private MobileElement btnDoubtSection;

    public MobileElement getBtnDoubtSection() {
        return btnDoubtSection;
    }

    @AndroidFindBy(id = "bookmarks_title")
    private MobileElement btnBookmarkSection;

    public MobileElement getBtnBookmarkSection() {
        return btnBookmarkSection;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Live Class']")
    private MobileElement textSectionLiveClass;

    public MobileElement getTextSectionLiveClass() {
        return textSectionLiveClass;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Test Series']")
    private MobileElement textSectionTestSeries;

    public MobileElement getTextSectionTestSeries() {
        return textSectionTestSeries;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Video Course']")
    private MobileElement textSectionVideoCourse;

    public MobileElement getTextSectionVideoCourse() {
        return textSectionVideoCourse;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='EBooks']")
    private MobileElement textSectionEBooks;

    public MobileElement getTextSectionEBooks() {
        return textSectionEBooks;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Doubts']")
    private MobileElement textBookmarksSectionDoubts;

    public MobileElement getTextBookmarksSectionDoubts() {
        return textBookmarksSectionDoubts;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Questions']")
    private MobileElement textSectionQuestions;

    public MobileElement getTextSectionQuestions() {
        return textSectionQuestions;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Current Affairs']")
    private MobileElement textBookmarksSectionCurrentAffairs;

    public MobileElement getTextBookmarksSectionCurrentAffairs() {
        return textBookmarksSectionCurrentAffairs;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Job Alerts']")
    private MobileElement textBookmarksSectionJobAlerts;

    public MobileElement getTextBookmarksSectionJobAlerts() {
        return textBookmarksSectionJobAlerts;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Articles']")
    private MobileElement textBookmarksSectionArticles;

    public MobileElement getTextBookmarksSectionArticles() {
        return textBookmarksSectionArticles;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Videos']")
    private MobileElement textBookmarksSectionVideos;

    public MobileElement getTextBookmarksSectionVideos() {
        return textBookmarksSectionVideos;
    }

    @AndroidFindBy(id = "widget_list_item")
    private MobileElement listPost;

    public MobileElement getBookmarksListPost() {
        return listPost;
    }

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]")
    private MobileElement btnNavigateUp;

    public MobileElement getBtnNavigateUp() {
        return btnNavigateUp;
    }

    @AndroidFindBy(id = "tv_title")
    private MobileElement titleUnderTabs;

    public MobileElement getTitleUnderTabs() {
        return titleUnderTabs;
    }

    @AndroidFindAll({@AndroidBy(id = "searchBtn"), @AndroidBy(id = "search_item")})
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Banking']/following-sibling::XCUIElementTypeOther")
    private MobileElement iconSearch;

    public MobileElement getIconSearch() {
        return iconSearch;
    }

    @AndroidFindBy(id = "voiceButton")
    private MobileElement iconVoice;

    public MobileElement getIconVoice() {
        return iconVoice;
    }

    @AndroidFindBy(id = "searchEditText")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[contains(@name,'storeSearchField')]")
    private MobileElement fieldText;

    public MobileElement getFieldText() {
        return fieldText;
    }

    @AndroidFindBy(id = "recentSearchList")
    private MobileElement sectionSearchAutofill;

    public MobileElement getSectionSearchAutofill() {
        return sectionSearchAutofill;
    }

    @AndroidFindBy(id = "backButton")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[contains(@name,'storeSearchField')]/parent::XCUIElementTypeOther/preceding-sibling::XCUIElementTypeButton")
    private MobileElement optionBack;

    public MobileElement getOptionBack() {
        return optionBack;
    }

    @AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='Videos']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Videos']")
    private MobileElement optionVideos;

    public MobileElement getOptionVideos() {
        return optionVideos;
    }

    @AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='Test series']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Test series']")
    private MobileElement optionTestSeries;

    public MobileElement getOptionTestSeries() {
        return optionTestSeries;
    }

    @AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='Online live classes']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Online live classes']")
    private MobileElement optionLiveClasses;

    public MobileElement getOptionLiveClasses() {
        return optionLiveClasses;
    }

    @AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='Ebooks']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Ebooks']")
    private MobileElement optionEbooks;

    public MobileElement getOptionEbooks() {
        return optionEbooks;
    }

    @AndroidFindBy(id = "filterButton")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name='storeSearchField']/following-sibling::XCUIElementTypeButton[2]")
    private MobileElement btnFilter;

    public MobileElement getBtnFilter() {
        return btnFilter;
    }

    @AndroidFindBy(id = "searchButton")
    private MobileElement btnSearch;

    public MobileElement getBtnSearch() {
        return btnSearch;
    }

    @AndroidFindBy(id = "searchList")
    private MobileElement listSearch;

    public MobileElement getListSearch() {
        return listSearch;
    }

    @AndroidFindBy(id = "tagTextView")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[@name='storeSearchField']/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeOther[2]/descendant::XCUIElementTypeStaticText")
    private List<MobileElement> listSearchResult;

    public List<MobileElement> getListSearchResult() {
        return listSearchResult;
    }

    // ------------------------------------------ FILTER OPTIONS PANEL LOCATORS
    // -----------------------------------------

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Product Category']")
    private MobileElement btnProductCategory;

    public MobileElement getBtnProductCategory() {
        return btnProductCategory;
    }

    @AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='Alphabetical A-Z']")
    private MobileElement btnAlphabeticalAtoZ;

    public MobileElement getBtnAlphabeticalAtoZ() {
        return btnAlphabeticalAtoZ;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Exam Name']")
    @iOSXCUITFindBy(accessibility = "Exam Name")
    private MobileElement tabExamName;

    public MobileElement getTabExamName() {
        return tabExamName;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Packages']")
    private MobileElement tabPackages;

    public MobileElement getTabPackages() {
        return tabPackages;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Faculty')]")
    @iOSXCUITFindBy(accessibility = "Faculty Name")
    private MobileElement tabFaculty;

    public MobileElement getTabFaculty() {
        return tabFaculty;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Languages']")
    @iOSXCUITFindBy(accessibility = "Languages")
    private MobileElement tabLanguage;

    public MobileElement getTabLanguage() {
        return tabLanguage;
    }

    @AndroidFindBy(id = "reset_filter")
    @iOSXCUITFindBy(accessibility = "RESET ALL FILTERS")
    private MobileElement btnReset;

    public MobileElement getBtnReset() {
        return btnReset;
    }

    @AndroidFindBy(id = "apply_filter")
    @iOSXCUITFindBy(accessibility = "APPLY")
    private MobileElement btnApply;

    public MobileElement getBtnApply() {
        return btnApply;
    }

    // --------------------------------------------PURCHASED
    // SECTION---------------------------------------------------------

    @AndroidFindBy(xpath = "//*[contains(@text,'Recently Viewed Courses')]")
    @iOSXCUITFindBy(accessibility = "Recently Viewed Courses")
    private MobileElement titleRecentlyViewedCourses;

    public MobileElement getTitleRecentlyViewedCourses() {
        return titleRecentlyViewedCourses;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Your Purchased Courses\"]")
    @iOSXCUITFindBy(accessibility = "Your Purchased Courses")
    private MobileElement titleYourPurchasedCourses;

    public MobileElement getTitleYourPurchasedCourses() {
        return titleYourPurchasedCourses;
    }

//	@AndroidFindBy(id = "title")
//	private MobileElement titlePackageLiveClass;
//
//	public MobileElement getTitlePackageLiveClass() {
//		return titlePackageLiveClass;
//	}

    @AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'purchase_course_card')]")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Your Purchased Courses']/following-sibling::XCUIElementTypeOther/descendant::XCUIElementTypeStaticText")
    private List<MobileElement> listPurchasedCourses;

    public List<MobileElement> getListPurchasedCourses() {
        return listPurchasedCourses;
    }

    @AndroidFindBy(id = "statusBtn")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'Resume')]")
    private MobileElement btnResumeLiveClass;

    public MobileElement getBtnResumeLiveClass() {
        return btnResumeLiveClass;
    }

    // ----------------------------------------DOUBTS
    // SECTION-----------------------------------------------

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Asked']")
    private MobileElement askedDoubtsSection;

    public MobileElement getAskedDoubtsSection() {
        return askedDoubtsSection;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Answered']")
    private MobileElement answeredDoubtsSection;

    public MobileElement getAnsweredDoubtsSection() {
        return answeredDoubtsSection;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Bookmarked']")
    private MobileElement bookmarksDoubtsSection;

    public MobileElement getBookmarksDoubtsSection() {
        return bookmarksDoubtsSection;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='MCQ']")
    private MobileElement McqDoubtsSection;

    public MobileElement getMcqInDoubtsSection() {
        return McqDoubtsSection;
    }

    // ----------------------------------------DOWNLOADS
    // SECTION-----------------------------------------------

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Quizzes']")
    @iOSXCUITFindBy(accessibility = "Quizzes")
    private MobileElement quizzesDownloadSection;

    public MobileElement getQuizzesDownloadsSection() {
        return quizzesDownloadSection;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Videos']")
    @iOSXCUITFindBy(accessibility = "Videos")
    private MobileElement videosDownloadsSection;

    public MobileElement getVideosDownloadSection() {
        return videosDownloadsSection;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'EBooks')]")
    @iOSXCUITFindBy(accessibility = "Ebooks")
    private MobileElement eBooksDownloadsSection;

    public MobileElement getEBooksDownloadSection() {
        return eBooksDownloadsSection;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Magazines']")
    @iOSXCUITFindBy(accessibility = "Free PDF (Magazines)")
    private MobileElement magazinesDownloadsSection;

    public MobileElement getMagazinesDownloadSection() {
        return magazinesDownloadsSection;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Capsules']")
    @iOSXCUITFindBy(accessibility = "Power Capsule")
    private MobileElement capsulesDownloadsSection;

    public MobileElement getCapsulesDownloadSection() {
        return capsulesDownloadsSection;
    }

    @AndroidFindBy(id = "tv_download")
    private List<MobileElement> getAllDownloadBtn;

    public List<MobileElement> getAllDownloadBtn() {
        return getAllDownloadBtn;
    }

    // ----------------------------------------Bookmarks
    // SECTION-----------------------------------------------

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Questions']")
    private MobileElement questionsBookmarksSection;

    public MobileElement getQuesitonsBookmarksSection() {
        return questionsBookmarksSection;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Current Affairs']")
    private MobileElement currentAffairsBookmarksSection;

    public MobileElement getCurrentAffairsInBookmarksSection() {
        return currentAffairsBookmarksSection;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Job Alerts']")
    private MobileElement jobAlertsBookmarksSection;

    public MobileElement getJobAlertsBookmarksSection() {
        return jobAlertsBookmarksSection;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Articles']")
    private MobileElement articlesBookmarksSection;

    public MobileElement getArticlesBookmarksSection() {
        return articlesBookmarksSection;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Videos']")
    private MobileElement videosBookmarksSection;

    public MobileElement getVideoBookmarkdedSection() {
        return videosBookmarksSection;
    }

    @AndroidFindBy(id = "favourite")
    private List<MobileElement> bookMarkVideoDotIcon;

    public List<MobileElement> getBookMarkVideoDotIcon() {
        return bookMarkVideoDotIcon;
    }

    @AndroidFindBy(id = "lay_bookmark")
    private MobileElement bookMarkTab;

    public MobileElement getBookMarkTab() {
        return bookMarkTab;
    }

    @AndroidFindBy(id = "emptyViewMessageTitle")
    private MobileElement emptyState;

    public MobileElement getEmptyState() {
        return emptyState;
    }

    @AndroidFindBy(id = "thumb")
    private MobileElement bookMarkVideos;

    public MobileElement getBookMarkVideos() {
        return bookMarkVideos;
    }

    // ---------------------Package details----------------------------------------

    @AndroidFindBy(id = "title")
    private List<MobileElement> listPackageTitle;

    public List<MobileElement> getListPackageTitle() {
        return listPackageTitle;
    }

    @AndroidFindAll({@AndroidBy(id = "packageDescription"), @AndroidBy(id = "description")})
    private List<MobileElement> listPackageDescriptions;

    public List<MobileElement> getListPackageDescription() {
        return listPackageDescriptions;
    }

    @AndroidFindBy(id = "video_course_title_head")
    private MobileElement downloadVideoTitle;

    public MobileElement getDownloadVideoTitle() {
        return downloadVideoTitle;
    }

    @AndroidFindBy(id = "emptyViewBuy")
    private MobileElement goToPurchasedBtn;

    public MobileElement getGoToPurchasedBtn() {
        return goToPurchasedBtn;
    }

    @AndroidFindBy(id = "emptyViewMessageTitle")
    private MobileElement downloadedVideoEmptyStateTitle;

    public MobileElement getDownloadedVideoEmptyStateTitle() {
        return downloadedVideoEmptyStateTitle;
    }

    @AndroidFindBy(id = "skip")
    private MobileElement skipBtn;

    public MobileElement getSkipBtn() {
        return skipBtn;
    }

    @AndroidFindBy(id = "live_video_container")
    private MobileElement liveVideoCell;

    public MobileElement getLiveVideoCell() {
        return liveVideoCell;
    }

    @AndroidFindBy(id = "takeMeToStoreBtn")
    private MobileElement takeMeToStoreBtn;

    public MobileElement getTakeMeToStoreBtn() {
        return takeMeToStoreBtn;
    }

    @AndroidFindBy(id = "btn_done")
    private MobileElement doneBtn;

    public MobileElement getDoneBtn() {
        return doneBtn;
    }

    @AndroidFindBy(id = "lay_download")
    private List<MobileElement> eBookDownloadBtn;

    public List<MobileElement> geteBookDownloadBtn() {
        return eBookDownloadBtn;
    }

    @AndroidFindBy(id = "title")
    private List<MobileElement> eBookTitle;

    public List<MobileElement> geteBookTitle() {
        return eBookTitle;
    }

    @AndroidFindBy(className = "android.widget.CheckBox")
    private List<MobileElement> filterContentOption;

    public List<MobileElement> getFilterContentOption() {
        return filterContentOption;
    }

    @AndroidFindBy(id = "reset_filter")
    private MobileElement filterResetBtn;

    public MobileElement getFilterResetBtn() {
        return filterResetBtn;
    }

    @AndroidFindBy(id = "apply_filter")
    private MobileElement filterApplyBtn;

    public MobileElement getFilterApplyBtn() {
        return filterApplyBtn;
    }

    @AndroidFindBy(id = "submit")
    private List<MobileElement> quizContentBtn;

    public List<MobileElement> getQuizContentBtn() {
        return quizContentBtn;
    }

    @AndroidFindBy(id = "cl_ad")
    private List<MobileElement> bookmarkedMaterial;

    public MobileElement getListPost() {
        return listPost;
    }

    public MobileElement getMcqDoubtsSection() {
        return McqDoubtsSection;
    }

    public MobileElement getQuizzesDownloadSection() {
        return quizzesDownloadSection;
    }

    public MobileElement getVideosDownloadsSection() {
        return videosDownloadsSection;
    }

    public MobileElement geteBooksDownloadsSection() {
        return eBooksDownloadsSection;
    }

    public MobileElement getMagazinesDownloadsSection() {
        return magazinesDownloadsSection;
    }

    public MobileElement getCapsulesDownloadsSection() {
        return capsulesDownloadsSection;
    }

    public MobileElement getQuestionsBookmarksSection() {
        return questionsBookmarksSection;
    }

    public MobileElement getCurrentAffairsBookmarksSection() {
        return currentAffairsBookmarksSection;
    }

    public MobileElement getVideosBookmarksSection() {
        return videosBookmarksSection;
    }

    public List<MobileElement> getListPackageDescriptions() {
        return listPackageDescriptions;
    }

    public List<MobileElement> getBookmarkedMaterial() {
        return bookmarkedMaterial;
    }

    @AndroidFindBy(accessibility = "Go to Store")
    private MobileElement goToStoreBtn;

    public MobileElement getGoToStoreBtn() {
        return goToStoreBtn;
    }

    @AndroidFindBy(accessibility = "Retry")
    private MobileElement retryBtn;

    public MobileElement getRetryBtn() {
        return retryBtn;
    }

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Take Me To Store\"]")
    private MobileElement takeMeToStore;

    public MobileElement takeMeToStore() {
        return takeMeToStore;
    }

    @AndroidFindBy(xpath = "//*[@content-desc='btn_appbar_back']")
    private MobileElement backBtnInNewPackage;

    public MobileElement backBtnInNewPackage() {
        return backBtnInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='searchIconClick']")
    private MobileElement searchIconBtnInNewPackage;

    public MobileElement searchIconBtnInNewPackage() {
        return searchIconBtnInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Live Class\n" + "Tab\")]")
    private MobileElement LiveclassBtnInNewPackage;

    public MobileElement LiveclassBtnInNewPackage() {
        return LiveclassBtnInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Test Series\n" + "Tab\")]")
    private MobileElement testseriesBtnInNewPackage;

    public MobileElement testseriesBtnInNewPackage() {
        return testseriesBtnInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Video Course\n" + "Tab\")]")
    private MobileElement videoCourseBtnInNewPackage;

    public MobileElement videoCourseBtnInNewPackage() {
        return videoCourseBtnInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"Ebooks\n" + "Tab\")]")
    private MobileElement ebooksBtnInNewPackage;

    public MobileElement ebooksBtnInNewPackage() {
        return ebooksBtnInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"3D Learning\n" + "Tab\")]")
    private MobileElement threeDlearningBtnInNewPackage;

    public MobileElement threeDlearningBtnInNewPackage() {
        return threeDlearningBtnInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Turn on notification to get all the updates about your live classes\"]")
    private MobileElement turnOnNotificationTextInNewPackage;

    public MobileElement turnOnNotificationTextInNewPackage() {
        return turnOnNotificationTextInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.widget.Switch")
    private MobileElement turnOnNotificationBtnInNewPackage;

    public MobileElement turnOnNotificationBtnInNewPackage() {
        return turnOnNotificationBtnInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"batchScheduleCardClick\n" + "Access all your classes at one place!\n" + "Weekly Schedule\"]")
    private MobileElement weeklyScheduleBtnInNewPackage;

    public MobileElement weeklyScheduleBtnInNewPackage() {
        return weeklyScheduleBtnInNewPackage;
    }

    @AndroidFindBy(xpath = "//*[contains(@content-desc,'Exam Level Live Classes')]")
    private MobileElement examLevelLiveClassesTextInNewPackage;

    public MobileElement examLevelLiveClassesTextInNewPackage() {
        return examLevelLiveClassesTextInNewPackage;
    }

    @AndroidFindBy(xpath = "//*[contains(@content-desc,'Exam Level Live Classes Live Class')]")
    private MobileElement examLevelLiveClassesInsideTextInNewPackage;

    public MobileElement examLevelLiveClassesInsideTextInNewPackage() {
        return examLevelLiveClassesInsideTextInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc='View All']")
    private MobileElement viewAllBtnInNewPackage;

    public MobileElement viewAllBtnInNewPackage() {
        return viewAllBtnInNewPackage;
    }

    @AndroidFindBy(xpath = "//*[contains(@content-desc,'subjectExamViewCardClick')]")
    private List<MobileElement> examLevelCardBtnsInNewPackage;

    public List<MobileElement> examLevelCardBtnsInNewPackage() {
        return examLevelCardBtnsInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Subject-Wise Live Classes']")
    private MobileElement subjectWiseLiveClassesTextInNewPackage;

    public MobileElement subjectWiseLiveClassesTextInNewPackage() {
        return subjectWiseLiveClassesTextInNewPackage;
    }

    @AndroidFindBy(xpath = "//*[contains(@content-desc,'subjectListingViewCardClick')]")
    private List<MobileElement> subjectWiseCardBtnsOutsideInNewPackage;

    public List<MobileElement> subjectWiseCardBtnsOutsideInNewPackage() {
        return subjectWiseCardBtnsOutsideInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'LiveClassItemCardClick')]")
    private List<MobileElement> listingOfLiveVideosInNewPackage;

    public List<MobileElement> listingOfLiveVideosInNewPackage() {
        return listingOfLiveVideosInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'videoCoursesConsumptionCardClick')]")
    private List<MobileElement> listingOfVideosInNewPackage;

    public List<MobileElement> listingOfVideosInNewPackage() {
        return listingOfVideosInNewPackage;
    }

    @AndroidFindBy(xpath = "//*[contains(@content-desc,'ebooksConsumptionCardClick')]")
    private List<MobileElement> listingOfEbooksInNewPackage;

    public List<MobileElement> listingOfEbooksInNewPackage() {
        return listingOfEbooksInNewPackage;
    }

    @AndroidFindBy(xpath = "//*[contains(@content-desc,'ebookDownloadStatusIconClick')]")
    private List<MobileElement> listingOfEbooksDownloadStatusInNewPackage;

    public List<MobileElement> listingOfEbooksDownloadStatusInNewPackage() {
        return listingOfEbooksDownloadStatusInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Subject Level Live Classes\"]")
    private MobileElement subjectLevelLiveClassesTextInNewPackage;

    public MobileElement subjectLevelLiveClassesTextInNewPackage() {
        return subjectLevelLiveClassesTextInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'chapterExamViewCardClick')]")
    private List<MobileElement> subjectLevelCardBtnsInSubInNewPackage;

    public List<MobileElement> subjectLevelCardBtnsInSubInNewPackage() {
        return subjectLevelCardBtnsInSubInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'chapterListingViewCardCLick')]")
    private List<MobileElement> chapterLevelCardBtnsInNewPackage;

    public List<MobileElement> chapterLevelCardBtnsInNewPackage() {
        return chapterLevelCardBtnsInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Chapters')]")
    private MobileElement chapterWiseLiveClassesTextInNewPackage;

    public MobileElement chapterWiseLiveClassesTextInNewPackage() {
        return chapterWiseLiveClassesTextInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Chapter Level Live Classes']")
    private MobileElement chapterLevelLiveClassesTextInNewPackage;

    public MobileElement chapterLevelLiveClassesTextInNewPackage() {
        return chapterLevelLiveClassesTextInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Topics')]")
    private MobileElement topicLevelLiveClassesTextInNewPackage;

    public MobileElement topicLevelLiveClassesTextInNewPackage() {
        return topicLevelLiveClassesTextInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'chapterListingViewCardCLick')]")
    private List<MobileElement> topicLevelCardBtnsInNewPackage;

    public List<MobileElement> topicLevelCardBtnsInNewPackage() {
        return topicLevelCardBtnsInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Live Class Recording\n" +
            "Tab')]")
    private MobileElement liveclassRecordingTextInNewPackage;

    public MobileElement liveclassRecordingTextInNewPackage() {
        return liveclassRecordingTextInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Upcoming\n" +
            "Tab')]")
    private MobileElement upcomingTextInNewPackage;

    public MobileElement upcomingTextInNewPackage() {
        return upcomingTextInNewPackage;
    }

    @AndroidFindBy(xpath = "//*[contains(@content-desc,'Exam Level Mock Tests')]")
    private MobileElement examLevelMockTestsTextInNewPackage;

    public MobileElement examLevelMockTestsTextInNewPackage() {
        return examLevelMockTestsTextInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Subject-Wise Tests\"]")
    private MobileElement subjectWiseMockTestsTextInNewPackage;

    public MobileElement subjectWiseMockTestsTextInNewPackage() {
        return subjectWiseMockTestsTextInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Subject Level Mock Tests\"]")
    private MobileElement subjectLevelMockTestsTextInNewPackage;

    public MobileElement subjectLevelMockTestsTextInNewPackage() {
        return subjectLevelMockTestsTextInNewPackage;
    }

    @AndroidFindBy(xpath = "//*[contains(@content-desc,'Chapters')]")
    private MobileElement chapterHeadingMockTestsTextInNewPackage;

    public MobileElement chapterHeadingMockTestsTextInNewPackage() {
        return chapterHeadingMockTestsTextInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Chapter Level Mock Tests\"]")
    private MobileElement chapterLevelMockTestsTextInNewPackage;

    public MobileElement chapterLevelMockTestsTextInNewPackage() {
        return chapterLevelMockTestsTextInNewPackage;
    }

    @AndroidFindBy(xpath = "//*[contains(@content-desc,'test_series_card')]")
    private List<MobileElement> listingOfTestseries;

    public List<MobileElement> listingOfTestseries() {
        return listingOfTestseries;
    }

    @AndroidFindBy(xpath = "//*[contains(@content-desc,'Topics')]")
    private MobileElement topicLevelMockTestsTextInNewPackage;

    public MobileElement topicLevelMockTestsTextInNewPackage() {
        return topicLevelMockTestsTextInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"All Tests\n" +
            "Tab 1 of 4\"]")
    private MobileElement allTestsTabTextInTopicLevel;

    public MobileElement allTestsTabTextInTopicLevel() {
        return allTestsTabTextInTopicLevel;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Completed\n" +
            "Tab 2 of 4\"]")
    private MobileElement completedTabTextInTopicLevel;

    public MobileElement completedTabTextInTopicLevel() {
        return completedTabTextInTopicLevel;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Incomplete\n" +
            "Tab 3 of 4\"]")
    private MobileElement incompleteTabTextInTopicLevel;

    public MobileElement incompleteTabTextInTopicLevel() {
        return incompleteTabTextInTopicLevel;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Unattempted\n" +
            "Tab 4 of 4\"]")
    private MobileElement unattemptedTabTextInTopicLevel;

    public MobileElement unattemptedTabTextInTopicLevel() {
        return unattemptedTabTextInTopicLevel;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Exam Level Video Courses\"]")
    private MobileElement examLevelVideosTextInNewPackage;

    public MobileElement examLevelVideosTextInNewPackage() {
        return examLevelVideosTextInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Subject-Wise Videos\"]")
    private MobileElement subjectWiseVideosTextInNewPackage;

    public MobileElement subjectWiseVideosTextInNewPackage() {
        return subjectWiseVideosTextInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Subject Level Video Courses\"]")
    private MobileElement subjectLevelVideosTextInNewPackage;

    public MobileElement subjectLevelVideosTextInNewPackage() {
        return subjectLevelVideosTextInNewPackage;
    }

    @AndroidFindBy(xpath = "//*[contains(@content-desc,'Chapters')]")
    private MobileElement chapterHeadingVideosTextInNewPackage;

    public MobileElement chapterHeadingVideosTextInNewPackage() {
        return chapterHeadingVideosTextInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Chapter Level Video Courses\"]")
    private MobileElement chapterLevelVideosTextInNewPackage;

    public MobileElement chapterLevelVideosTextInNewPackage() {
        return chapterLevelVideosTextInNewPackage;
    }

    @AndroidFindBy(xpath = "//*[contains(@content-desc,'Topics')]")
    private MobileElement topicLevelVideosTextInNewPackage;

    public MobileElement topicLevelVideosTextInNewPackage() {
        return topicLevelVideosTextInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Exam Level Ebooks\"]")
    private MobileElement examLevelEbooksTextInNewPackage;

    public MobileElement examLevelEbooksTextInNewPackage() {
        return examLevelEbooksTextInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Subject-Wise Ebooks\"]")
    private MobileElement subjectWiseEbooksTextInNewPackage;

    public MobileElement subjectWiseEbooksTextInNewPackage() {
        return subjectWiseEbooksTextInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Subject Level Ebooks\"]")
    private MobileElement subjectLevelEbooksTextInNewPackage;

    public MobileElement subjectLevelEbooksTextInNewPackage() {
        return subjectLevelEbooksTextInNewPackage;
    }

    @AndroidFindBy(xpath = "//*[contains(@content-desc,'Chapters')]")
    private MobileElement chapterHeadingEbooksTextInNewPackage;

    public MobileElement chapterHeadingEbooksTextInNewPackage() {
        return chapterHeadingEbooksTextInNewPackage;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Chapter Level Ebooks\"]")
    private MobileElement chapterLevelEbooksTextInNewPackage;

    public MobileElement chapterLevelEbooksTextInNewPackage() {
        return chapterLevelEbooksTextInNewPackage;
    }

    @AndroidFindBy(xpath = "//*[contains(@content-desc,'Topics')]")
    private MobileElement topicLevelEbooksTextInNewPackage;

    public MobileElement topicLevelEbooksTextInNewPackage() {
        return topicLevelEbooksTextInNewPackage;
    }

    // ---------------- Mahapack -----------------

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Pick Your Exams\n" +
            "You can modify it later\"]")
    private MobileElement selectPickYourExamsText;

    public MobileElement selectPickYourExamsText() {
        return selectPickYourExamsText;
    }

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Reset\"]")
    private MobileElement resetBtnInPriorityExams;

    public MobileElement resetBtnInPriorityExams() {
        return resetBtnInPriorityExams;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Choose your priority exams\"]")
    private MobileElement choosePriorityExamsText;

    public MobileElement choosePriorityExamsText() {
        return choosePriorityExamsText;
    }

    @AndroidFindBy(xpath = "//android.widget.EditText")
    private MobileElement examsInputBox;

    public MobileElement examsInputBox() {
        return examsInputBox;
    }

    @AndroidFindBy(xpath = "//*[@content-desc=\"Clear All\"]")
    private MobileElement clearAllBtnInPriorityExams;

    public MobileElement clearAllBtnInPriorityExams() {
        return clearAllBtnInPriorityExams;
    }

    @AndroidFindBy(xpath = "//android.widget.CheckBox")
    private List<MobileElement> examsListingInPriorityExams;

    public List<MobileElement> examsListingInPriorityExams() {
        return examsListingInPriorityExams;
    }

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Cancel\"]")
    private MobileElement cancelBtnInPriorityExams;

    public MobileElement cancelBtnInPriorityExams() {
        return cancelBtnInPriorityExams;
    }

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"CONTINUE\"]")
    private MobileElement continueBtnInPriorityExams;

    public MobileElement continueBtnInPriorityExams() {
        return continueBtnInPriorityExams;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Your Exams\"]")
    private MobileElement yourExamsTextInPriorityExams;

    public MobileElement yourExamsTextInPriorityExams() {
        return yourExamsTextInPriorityExams;
    }

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Manage Exams\"]")
    private MobileElement manageExamsBtnInPriorityExams;

    public MobileElement manageExamsBtnInPriorityExams() {
        return manageExamsBtnInPriorityExams;
    }

    @AndroidFindBy(xpath = "//*[contains(@content-desc,\"examSelectClick\")]")
    private List<MobileElement> examsSelectedListing;

    public List<MobileElement> examsSelectedListing() {
        return examsSelectedListing;
    }

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"batchClick\")]//preceding-sibling::android.widget.ImageView[2]")
    private List<MobileElement> listOfLikeButtonInMahapack;

    public List<MobileElement> listOfLikeButtonInMahapack() {
        return listOfLikeButtonInMahapack;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"bookmarkButtonCLick\"]")
    private List<MobileElement> bookmarkBtnListingInPackageExam;

    public List<MobileElement> bookmarkBtnListingInPackageExam() {
        return bookmarkBtnListingInPackageExam;
    }

    @AndroidFindBy(xpath = "//*[contains(@content-desc,'batchListingCardClick')]")
    private List<MobileElement> packagesListingInExam;

    public List<MobileElement> packagesListingInExam() {
        return packagesListingInExam;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Batch updated successfully\"]")
    private MobileElement batchUpdatedSuccessfullyToast;

    public MobileElement batchUpdatedSuccessfullyToast() {
        return batchUpdatedSuccessfullyToast;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Remove Bookmark\"]")
    private MobileElement removeBookmarkTextInPopup;

    public MobileElement removeBookmarkTextInPopup() {
        return removeBookmarkTextInPopup;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Are you sure you want to remove this batch from the Favourite Batches list.\"]")
    private MobileElement areYouSureTextInRemoveBookmarkPopup;

    public MobileElement areYouSureTextInRemoveBookmarkPopup() {
        return areYouSureTextInRemoveBookmarkPopup;
    }

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Cancel\"]")
    private MobileElement cancelBtnInRemoveBookmarkPopup;

    public MobileElement cancelBtnInRemoveBookmarkPopup() {
        return cancelBtnInRemoveBookmarkPopup;
    }

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Remove\"]")
    private MobileElement removeBtnInRemoveBookmarkPopup;

    public MobileElement removeBtnInRemoveBookmarkPopup() {
        return removeBtnInRemoveBookmarkPopup;
    }

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"removeBookmarkDialogClose\"]")
    private MobileElement closeBtnInRemoveBookmarkPopup;

    public MobileElement closeBtnInRemoveBookmarkPopup() {
        return closeBtnInRemoveBookmarkPopup;
    }

    @AndroidFindBy(xpath = "//*[@content-desc=\"searchIconClick\"]")
    private MobileElement searchBtnInMahapack;

    public MobileElement searchBtnInMahapack() {
        return searchBtnInMahapack;
    }
}