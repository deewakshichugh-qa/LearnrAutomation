package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class PackagePage_OR {

	@AndroidFindBy(accessibility = "Navigate up")
	private MobileElement btnNaviagateUp;

	public MobileElement getBtnNavigateUp() {
		return btnNaviagateUp;
	}

	@AndroidFindBy(id = "com.adda247.app:id/title")
	private List<MobileElement> listPackageTitle;

	public List<MobileElement> getListPackageTitle() {
		return listPackageTitle;
	}

	@AndroidFindBy(id = "description")
	private List<MobileElement> listPackageDescriptions;

	public List<MobileElement> getListPackageDescription() {
		return listPackageDescriptions;
	}

	@AndroidFindBy(id = "is_read")
	private List<MobileElement> listIconIsRead;

	public List<MobileElement> getListIconIsRead() {
		return listIconIsRead;
	}

	@AndroidFindBy(id = "action_bookmark")
	private MobileElement iconBookmark;

	public MobileElement getIconBookmark() {
		return iconBookmark;
	}

	@AndroidFindBy(id = "favourite")
	private MobileElement iconBookmarkVideos;

	public MobileElement getIconBookmarkVideos() {
		return iconBookmarkVideos;
	}

	@AndroidFindAll({ @AndroidBy(accessibility = "Share"), @AndroidBy(id = "action_share") })
	private MobileElement iconShare;

	public MobileElement getIconShare() {
		return iconShare;
	}

	@AndroidFindBy(id = "emptyViewBuy")
	private MobileElement btnGoToMagazineOrCapsule;

	public MobileElement getBtnGoToMagazinesOrCapsules() {
		return btnGoToMagazineOrCapsule;
	}
	// ---------------------------VIDEO
	// COURSE----------------------------------------------

	@AndroidFindBy(id = "video_course_title_head")
	private MobileElement titleVideoCourse;

	public MobileElement getTitleVideoCourse() {
		return titleVideoCourse;
	}

	// ------------------------LIVE
	// CLASS-----------------------------------------------------

	// ------------------------EBOOKS---------------------------------------------------------

	@AndroidFindBy(id = "title")
	private List<MobileElement> titleEBooks;

	public List<MobileElement> getTitleEBooks() {
		return titleEBooks;
	}

	@AndroidFindBy(id = "tv_download")
	private List<MobileElement> linkDownloadEBook;

	public List<MobileElement> getLinkDownloadEBook() {
		return linkDownloadEBook;
	}

	// ------------------------TEST
	// SERIES---------------------------------------------------
	@AndroidFindAll({ @AndroidBy(id = "downloaded_status"), @AndroidBy(id = "downloaded_status_line1") })
	private List<MobileElement> linkGetQuiz;

	public List<MobileElement> getLinkGetQuiz() {
		return linkGetQuiz;
	}

	@AndroidFindAll({ @AndroidBy(id = "downloaded_status_icon"), @AndroidBy(id = "tv_download") })
	private List<MobileElement> iconDownloadQuiz;

	public List<MobileElement> getIconDownloadQuiz() {
		return iconDownloadQuiz;
	}

	@AndroidFindBy(id = "bl_title")
	private List<MobileElement> titleQuiz;

	public List<MobileElement> getTitleQuiz() {
		return titleQuiz;
	}

	@AndroidFindAll({ @AndroidBy(xpath = "//android.widget.Button[@text='Start Test']"),
			@AndroidBy(id = "start_test") })
	private MobileElement btnStartTest;

	public MobileElement getBtnStartTest() {
		return btnStartTest;
	}

	// ------------------------MAGAZINES---------------------------------------------------
	@AndroidFindBy(id = "downloaded_status")
	private List<MobileElement> linkDownloadMagazines;

	public List<MobileElement> getLinkDownloadMagazines() {
		return linkDownloadMagazines;
	}

	@AndroidFindBy(id = "downloaded_status_icon")
	private List<MobileElement> iconDownloadMagazines;

	public List<MobileElement> getIconDownloadMagazines() {
		return iconDownloadMagazines;
	}

	@AndroidFindBy(id = "title")
	private List<MobileElement> titleMagazines;

	public List<MobileElement> getTitleMagazines() {
		return titleMagazines;
	}

	@AndroidFindBy(id = "frontView")
	private List<MobileElement> iconFilter;

	public List<MobileElement> getIconFilterMagazines() {
		return iconFilter;
	}
	// -----------------------CAPSULES-----------------------------------------------------

	@AndroidFindBy(id = "downloaded_status")
	private List<MobileElement> linkDownloadCapsules;

	public List<MobileElement> getLinkDownloadCapsules() {
		return linkDownloadCapsules;
	}

	@AndroidFindBy(id = "downloadBtn")
	private MobileElement iconDownload;

	public MobileElement getIconDownload() {
		return iconDownload;
	}

	@AndroidFindBy(id = "title")
	private List<MobileElement> titleCapsules;

	public List<MobileElement> getTitileCapsules() {
		return titleCapsules;
	}

	@AndroidFindBy(id = "frontView")
	private List<MobileElement> iconFilterCapsule;

	public List<MobileElement> getIconFilterCapsules() {
		return iconFilter;
	}

	// ---------------------MAHAPACK-----------------------------------------------

	@AndroidFindBy(accessibility = "Live Classes")
	private MobileElement btnLiveClass;

	public MobileElement getBtnLiveClass() {
		return btnLiveClass;
	}

	@AndroidFindBy(accessibility = "Test Series")
	private MobileElement btnTestSeries;

	public MobileElement getBtnTestSeries() {
		return btnTestSeries;
	}

	@AndroidFindBy(accessibility = "Video Courses")
	private MobileElement btnVideoCourses;

	public MobileElement getBtnVideoCourses() {
		return btnVideoCourses;
	}

	@AndroidFindBy(accessibility = "EBooks")
	private MobileElement btnEBooks;

	public MobileElement getBtnEBooks() {
		return btnEBooks;
	}

	@AndroidFindBy(id = "packageDescription")
	private List<MobileElement> listChildPackage;

	public List<MobileElement> getListChildPackage() {
		return listChildPackage;
	}

	@AndroidFindBy(id = "imgNotification")
	private List<MobileElement> iconNotification;

	public List<MobileElement> getListIconNotification() {
		return iconNotification;
	}

	@AndroidFindBy(id = "btn_done")
	private MobileElement doneBtn;

	public MobileElement getDoneBtn() {
		return doneBtn;
	}

	@AndroidFindBy(id = "cutoff_marks")
	private List<MobileElement> cutOffMarksTag;

	public List<MobileElement> getCutOffMarksTag() {
		return cutOffMarksTag;
	}

	@AndroidFindBy(xpath = "//*[contains(@resource-id,'level')]/child::*[contains(@resource-id,'test_number')]")
	private List<MobileElement> quizTestTag;

	public List<MobileElement> getQuizTestTag() {
		return quizTestTag;
	}

	@AndroidFindBy(xpath = "//*[contains(@resource-id,'test_number')]/ancestor::*[contains(@resource-id,'card_background')]/descendant::*[contains(@text,'GET QUIZ')]")
	private List<MobileElement> testTagGetQuizBtn;

	public List<MobileElement> getTestTagGetQuizBtn() {
		return testTagGetQuizBtn;
	}

	@AndroidFindBy(xpath = "//*[@text='GET QUIZ']/ancestor::*[contains(@resource-id,'card_background')]/descendant::*[contains(@resource-id,'test_number')]")
	private List<MobileElement> testTagOnUnAttemptedQuiz;

	public List<MobileElement> getTestTagOnUnAttemptedQuiz() {
		return testTagOnUnAttemptedQuiz;
	}

	@AndroidFindBy(xpath = "//*[contains(@resource-id,'test_number')]/ancestor::*[contains(@resource-id,'card_background')]/descendant::*[contains(@text,'ATTEMPT')]")
	private MobileElement testTagAttemptBtn;

	public MobileElement getTestTagAttemptBtn() {
		return testTagAttemptBtn;
	}

	@AndroidFindBy(xpath = "//*[contains(@resource-id,'test_number')]/ancestor::*[contains(@resource-id,'card_background')]/descendant::*[contains(@text,'REATTEMPT')]")
	private MobileElement testTagReAttemptBtn;

	public MobileElement getTestTagReAttemptBtn() {
		return testTagReAttemptBtn;
	}

	@AndroidFindBy(xpath = "//*[contains(@resource-id,'certificate_card')]/following-sibling::*[contains(@resource-id,'test_number')]")
	private MobileElement certificateLabelTestTag;

	public MobileElement getCertificateLabelTestTag() {
		return certificateLabelTestTag;
	}

	@AndroidFindBy(id = "certificate_cta")
	private MobileElement certificateAttemptBtn;

	public MobileElement getCertificateAttemptBtn() {
		return certificateAttemptBtn;
	}

	@AndroidFindBy(id = "progress_tv")
	private MobileElement progressBarTextValue;

	public MobileElement getProgressBarTextValue() {
		return progressBarTextValue;
	}

	@AndroidFindBy(id = "progress_percentage_tv")
	private MobileElement progressBarPercentage;

	public MobileElement getProgressBarPercentage() {
		return progressBarPercentage;
	}

	@AndroidFindBy(id = "test_status")
	private List<MobileElement> testStatusTag;

	public List<MobileElement> getTestStatusTag() {
		return testStatusTag;
	}

	@AndroidFindBy(id = "certificateProgressBar")
	private MobileElement certificateProgressBar;

	public MobileElement getCertificateProgressBar() {
		return certificateProgressBar;
	}

	@AndroidFindBy(xpath = "//*[@text='Candidate details for Certificate']")
	private MobileElement candidateDetailsPopUpTitle;

	public MobileElement getCandidateDetailsPopUpTitle() {
		return candidateDetailsPopUpTitle;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc='submitBtn']/following-sibling::android.widget.Button")
	private MobileElement submitBtn;

	public MobileElement getSubmitBtn() {
		return submitBtn;
	}

	@AndroidFindBy(id = "certificate_type")
	private MobileElement certificateQuizTitle;

	public MobileElement getCertificateQuizTitle() {
		return certificateQuizTitle;
	}

	@AndroidFindBy(xpath = "//*[@content-desc='father_name']/parent::android.widget.EditText")
	private MobileElement fathersNameTextField;

	public MobileElement getFathersNameTextField() {
		return fathersNameTextField;
	}

	@AndroidFindBy(xpath = "//*[@content-desc='city']/parent::android.widget.EditText")
	private MobileElement cityTextField;

	public MobileElement getCityTextField() {
		return cityTextField;
	}

	@AndroidFindBy(xpath = "//*[@content-desc='mother_name']/parent::android.widget.EditText")
	private MobileElement mothersNameTextField;

	public MobileElement getMothersNameTextField() {
		return mothersNameTextField;
	}

	@AndroidFindBy(xpath = "//*[@content-desc='state']/parent::android.widget.EditText")
	private MobileElement stateTextField;

	public MobileElement getStateTextField() {
		return stateTextField;
	}

	@AndroidFindBy(xpath = "//*[@content-desc='place']/parent::android.widget.EditText")
	private MobileElement placeTextField;

	public MobileElement getPlaceTextField() {
		return placeTextField;
	}

	@AndroidFindBy(xpath = "//*[@content-desc='name']/parent::android.widget.EditText")
	private MobileElement candidateNameTextField;

	public MobileElement getCandidateNameTextField() {
		return candidateNameTextField;
	}

	@AndroidFindBy(xpath = "//*[@content-desc='category']/parent::android.widget.EditText")
	private MobileElement categoryNameTextField;

	public MobileElement getCategoryNameTextField() {
		return categoryNameTextField;
	}

	@AndroidFindBy(xpath = "//*[@content-desc='college']/parent::android.widget.EditText")
	private MobileElement collegeTextField;

	public MobileElement getCollegeTextField() {
		return collegeTextField;
	}

	@AndroidFindBy(xpath = "//*[@content-desc='address']/parent::android.widget.EditText")
	private MobileElement addressTextField;

	public MobileElement getAddressTextField() {
		return addressTextField;
	}

	/*------------------------------Live Test ------------------------------------*/

	@AndroidFindBy(xpath = "//*[@text='SCHEDULED ON']")
	private List<MobileElement> scheduledOnBtn;

	public List<MobileElement> getScheduledOnBtn() {
		return scheduledOnBtn;
	}

	@AndroidFindBy(xpath = "tv_time_detail")
	private List<MobileElement> mockTimeDateText;

	public List<MobileElement> getMockTimeDateText() {
		return mockTimeDateText;
	}

	@AndroidFindBy(id = "com.adda247.app:id/live_test")
	private List<MobileElement> allLiveTestTab;

	public List<MobileElement> allLiveTestTab() {
		return allLiveTestTab;
	}

	@AndroidFindBy(id = "com.adda247.app:id/full_length_tag")
	private List<MobileElement> fullLengthLiveTestTab;

	public List<MobileElement> fullLengthLiveTestTab() {
		return fullLengthLiveTestTab;
	}

	@AndroidFindBy(id = "sectional_test_chip")
	private MobileElement sectionalLiveTestTab;

	public MobileElement getSectionalLiveTestTab() {
		return sectionalLiveTestTab;
	}

	@AndroidFindBy(id = "com.adda247.app:id/downloaded_status")
	private List<MobileElement> statusOfTest;

	public List<MobileElement> getStatusOfTest() {
		return statusOfTest;
	}

	@AndroidFindBy(id = "com.adda247.app:id/tv_time_detail")
	private List<MobileElement> timeDetailOfTest;

	public List<MobileElement> getTimeDetailOfTest() {
		return timeDetailOfTest;
	}

}
