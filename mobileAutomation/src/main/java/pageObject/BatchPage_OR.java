package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class BatchPage_OR {
	
	@AndroidFindBy(id = "title")
	private MobileElement titlePage;
	
	public MobileElement getTitlePage() {
		return titlePage;
	}
	
	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]")
	private MobileElement btnNavigateUp;
	
	public MobileElement getBtnNavigateUp() {
		return btnNavigateUp;
	}

//------------------------------CHOOSE YOUR PRIORITY EXAM----------------------------------
	
	@AndroidFindBy(id = "btn_done")
	private MobileElement btnDoneCloseHint;
	
	public MobileElement getBtnDoneCloseHint() {
		return btnDoneCloseHint;
	}
	
	@AndroidFindBy(id = "examName")
	private List<MobileElement> checkBoxExams;
	
	public List<MobileElement> getListCheckBoxExams() {
		return checkBoxExams;
	}
	
	@AndroidFindBy(id = "searchFilter")
	private MobileElement searchField;
	
	public MobileElement getSearchField(){
		return searchField;
	}
	
	@AndroidFindBy(id = "applyFilter")
	private MobileElement btnDone;
	
	public MobileElement getBtnDone(){
		return btnDone;
	}
	
//-----------------------------------------------------------------------------------------
	
	@AndroidFindBy(id = "manageExamsTv")
	private MobileElement manageExamBtn;
	
	public MobileElement getManageExamBtn() {
		return manageExamBtn;
	}
	
	@AndroidFindBy(id = "searchBtn")
	private MobileElement iconSearch;
	
	public MobileElement getIconSearch() {
		return iconSearch;
	}
	
	@AndroidFindBy(id = "examName")
	private List<MobileElement> textExamName;
	
	public List<MobileElement> getTextExamName() {
		return textExamName;
	}
	
	@AndroidFindBy(id = "topView")
	private List<MobileElement> iconExam;

	public List<MobileElement> getIconExams() {
		return iconExam;
	}

	@AndroidFindBy(id = "selectAllCourse")
	private MobileElement checkBoxViewAllCourses;
	
	public MobileElement getCheckBoxViewAllCourses() {
		return checkBoxViewAllCourses;
	}
//--------------------------------------PARENT AND CHILD PACKAGE DISPLAY---------------------------------------------------
	
	@AndroidFindBy(id = "btn_allBatches")
	private MobileElement btnViewAllBatches;

	public MobileElement getBtnViewAllBatches() {
		return btnViewAllBatches;
	}
	
	@AndroidFindBy(id = "btn_allCourse")
	private MobileElement btnViewAllCourses;

	public MobileElement getBtnViewAllCourses() {
		return btnViewAllCourses;
	}
	
	@AndroidFindBy(id = "lv")
	private MobileElement countLiveClasses;

	public MobileElement getCountLiveClasses() {
		return countLiveClasses;
	}
	
	@AndroidFindBy(id = "ts")
	private MobileElement countTestSeries;

	public MobileElement getCountTestSeries() {
		return countTestSeries;
	}
	
	
	@AndroidFindBy(id = "vc")
	private MobileElement countVideoCourses;

	public MobileElement getCountVIdeoCourses() {
		return countVideoCourses;
	}
	
	
	@AndroidFindBy(id = "eb")
	private MobileElement countEBooks;

	public MobileElement getCountEBooks() {
		return countEBooks;
	}
	
	

//--------------------------------------BATCH DISPLAY------------------------------------------------------
	
	@AndroidFindBy(id = "title")
	private List<MobileElement> listTitlePackage;

	public List<MobileElement> getListTitlePackage() {
		return listTitlePackage;
	}
	
	@AndroidFindBy(id = "batch_card")
	private List<MobileElement> listBatchCard;

	public List<MobileElement> getListBatchCard() {
		return listBatchCard;
	}

	
//======================================PACKAGE WITH MULTIPLE CHILD SINGLE EXAM(Normal Flow)=======================================
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"Live Classes\"]")
	private MobileElement sectionLiveClasses;

	public MobileElement getSectionLiveClasses() {
		return sectionLiveClasses;
	}
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"Test Series\"]")
	private MobileElement sectionTestSeries;

	public MobileElement getSectionTestSeries() {
		return sectionTestSeries;
	}
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc=\"Video Courses\"]")
	private MobileElement sectionVideoCourses;

	public MobileElement getSectionVideoCourses() {
		return sectionVideoCourses;
	}
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[contains(@content-desc,'EBooks')]")
	private MobileElement sectionEBooks;

	public MobileElement getSectionEBooks() {
		return sectionEBooks;
	}
	
	@AndroidFindBy(id = "imgNotification")
	private List<MobileElement> iconNotification;

	public List<MobileElement> getIconNotificaion() {
		return iconNotification;
	}
	
	
//=========================PACKAGE WITH SINGLE CHILD MULTIPLE EXAMS==============================================
	
	@AndroidFindBy(id = "action_share")
	private MobileElement iconShare;

	public MobileElement getIconShare() {
		return iconShare;
	}
	
	@AndroidFindBy(id = "packageDescription")
	private List<MobileElement> packageDescription;

	public List<MobileElement> getPackageDescription() {
		return packageDescription;
	}
	
	@AndroidFindBy(id = "btn_done")
	private MobileElement doneBtn;

	public MobileElement getDoneBtn() {
		return doneBtn;
	}
	
	
	/*------------------------------------------------Sankalp BatchView Section------------------------*/
	
	@AndroidFindBy(xpath = "//*[@text='Resume']/preceding-sibling::*[contains(@resource-id,'title')]")
	private List<MobileElement> resumeSubjectTitle;

	public List<MobileElement> getResumeSubjectTitle() {
		return resumeSubjectTitle;
	}
	
	@AndroidFindBy(xpath = "//*[contains(@resource-id,'searchBtn')]/preceding-sibling::*[contains(@resource-id,'title')]")
	private MobileElement batchMahaPackPageTitle;

	public MobileElement getBatchMahaPackPageTitle() {
		return batchMahaPackPageTitle;
	}
	
	@AndroidFindBy(xpath = "//*[@text='Your Exams']")
	private MobileElement yoursExamTitle;

	public MobileElement getYoursExamTitle() {
		return yoursExamTitle;
	}
	
	@AndroidFindBy(xpath = "//*[contains(@resource-id,'examName')]")
	private List<MobileElement> examNameList;

	public List<MobileElement> getExamNameList() {
		return examNameList;
	}
	
	@AndroidFindBy(id = "ivBack")
	private MobileElement batchMahaPackPageBackBtn;

	public MobileElement getBatchMahaPackPageBackBtn() {
		return batchMahaPackPageBackBtn;
	}
	
	@AndroidFindBy(xpath = "//*[contains(@resource-id,'logo')]/following-sibling::*[contains(@resource-id,'title')]")
	private MobileElement ExamPageTitle;

	public MobileElement getExamPageTitle() {
		return ExamPageTitle;
	}
	
	@AndroidFindBy(id = "course_title")
	private MobileElement courseTitle;

	public MobileElement getCourseTitle() {
		return courseTitle;
	}
	
	@AndroidFindBy(id = "batch_title")
	private MobileElement batchTitle;

	public MobileElement getBatchTitle() {
		return batchTitle;
	}
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout/*[contains(@resource-id,'cv_iv')]")
	private List<MobileElement> subjectCardList;

	public List<MobileElement> getSubjectCardList() {
		return subjectCardList;
	}
	
	@AndroidFindBy(xpath = "//*[contains(@text,'more courses')]")
	private MobileElement moreCourseTitle;

	public MobileElement getMoreCourseTitle() {
		return moreCourseTitle;
	}
	
	@AndroidFindBy(xpath = "//*[contains(@resource-id,'tabs')]/descendant::android.widget.TextView")
	private List<MobileElement> batchTabList;

	public List<MobileElement> getBatchTabList() {
		return batchTabList;
	}
	
	@AndroidFindBy(id = "searchEditText")
	private MobileElement searchTextField;

	public MobileElement getSearchTextField() {
		return searchTextField;
	}
	
	@AndroidFindBy(id = "filterButtonClk")
	private MobileElement filterBtn;

	public MobileElement getFilterBtn() {
		return filterBtn;
	}
	
	@AndroidFindBy(id = "clearSearchButton")
	private MobileElement searchFieldClearBtn;

	public MobileElement getSearchFieldClearBtn() {
		return searchFieldClearBtn;
	}
	
	@AndroidFindBy(id = "backButton")
	private MobileElement searchScreenBackBtn;

	public MobileElement getSearchScreenBackBtn() {
		return searchScreenBackBtn;
	}
	
	@AndroidFindBy(xpath = "//*[contains(@resource-id,'topView')]/following-sibling::*[contains(@resource-id,'title')]")
	private List<MobileElement> commingSoonSubTitle;

	public List<MobileElement> getCommingSoonSubTitle() {
		return commingSoonSubTitle;
	}
	
	@AndroidFindBy(id = "filter_txt1")
	private MobileElement filterCountText;

	public MobileElement getFilterCountText() {
		return filterCountText;
	}
	
		
}