package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class PerformanceDashboard_OR {

	@AndroidFindBy(id = "com.adda247.app:id/test_pass_chip")
	private MobileElement testPassHeadingOnBanner;

	public MobileElement testPassHeadingOnBanner() {
		return testPassHeadingOnBanner;
	}

	@AndroidFindBy(id = "com.adda247.app:id/test_pass_title")
	private MobileElement testPassTitleOnBanner;

	public MobileElement testPassTitleOnBanner() {
		return testPassTitleOnBanner;
	}

	@AndroidFindBy(id = "com.adda247.app:id/icons")
	private MobileElement testPassIconsOnBanner;

	public MobileElement testPassIconsOnBanner() {
		return testPassIconsOnBanner;
	}

	@AndroidFindBy(id = "com.adda247.app:id/examsCoveredTv")
	private MobileElement testPassExamsCoveredOnBanner;

	public MobileElement testPassExamsCoveredOnBanner() {
		return testPassExamsCoveredOnBanner;
	}

	@AndroidFindBy(id = "com.adda247.app:id/explore_cta")
	private MobileElement testPassExploreOnBanner;

	public MobileElement testPassExploreOnBanner() {
		return testPassExploreOnBanner;
	}

	@AndroidFindBy(accessibility = "Test Prime")
	private MobileElement btnTestPassOnBottomNav;

	public MobileElement getBtnTestPassOnBottomNav() {
		return btnTestPassOnBottomNav;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"test_pass_text\"]")
	private MobileElement testPassHeadingOnPDP;

	public MobileElement testPassHeadingOnPDP() {
		return testPassHeadingOnPDP;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"btn_back\"]")
	private MobileElement backBtnOnTopOnPDP;

	public MobileElement backBtnOnTopOnPDP() {
		return backBtnOnTopOnPDP;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"btn_explore\n" + "All Exams, One Subscription\n"
			+ "67+ exams covered\n" + "btn_explore\n" + "Explore\"]")
	private MobileElement allExamsSubscriptionHeadingOnPDP;

	public MobileElement allExamsSubscriptionHeadingOnPDP() {
		return allExamsSubscriptionHeadingOnPDP;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"test_pass_title\")]")
	private MobileElement packageNameOnPDP;

	public MobileElement packageNameOnPDP() {
		return packageNameOnPDP;
	}
//
//	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Explore\"]")
//	private MobileElement exploreBtnOnPDP;
//
//	public MobileElement exploreBtnOnPDP() {
//		return exploreBtnOnPDP;
//	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"btn_view_all\n" + "VIEW ALL\"]")
	private MobileElement viewAllBtnInExamsOnPDP;

	public MobileElement viewAllBtnInExamsOnPDP() {
		return viewAllBtnInExamsOnPDP;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"What will you get\"]")
	private MobileElement whatWillYouGetTextOnPDP;

	public MobileElement whatWillYouGetTextOnPDP() {
		return whatWillYouGetTextOnPDP;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"goal_selection_arrow\n"
			+ "State exams\"]/android.widget.ImageView[2]")
	private MobileElement whatWillYouGetBelowArrowOnPDP;

	public MobileElement whatWillYouGetBelowArrowOnPDP() {
		return whatWillYouGetBelowArrowOnPDP;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"exam_category_select\")]")
	private List<MobileElement> examsListingBelowWhatWillYouGetOnPDP;

	public List<MobileElement> examsListingBelowWhatWillYouGetOnPDP() {
		return examsListingBelowWhatWillYouGetOnPDP;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Exams\"]")
	private MobileElement examsHeadingOnPDP;

	public MobileElement examsHeadingOnPDP() {
		return examsHeadingOnPDP;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,\"exam_row_select\")]")
	private List<MobileElement> examsListingBelowExamsOnPDP;

	public List<MobileElement> examsListingBelowExamsOnPDP() {
		return examsListingBelowExamsOnPDP;
	}

	@AndroidFindBy(xpath = "//android.widget.CheckBox")
	private List<MobileElement> checkboxInexamsListingBelowExamsOnPDP;

	public List<MobileElement> checkboxInexamsListingBelowExamsOnPDP() {
		return checkboxInexamsListingBelowExamsOnPDP;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Select Your Goal\"]")
	private MobileElement selectYourGoalHeadingBottomSheet;

	public MobileElement selectYourGoalHeadingBottomSheet() {
		return selectYourGoalHeadingBottomSheet;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,\"goal_selected\")]")
	private List<MobileElement> goalListingInGoalBottomSheet;

	public List<MobileElement> goalListingInGoalBottomSheet() {
		return goalListingInGoalBottomSheet;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"close_bottom_sheet\"]")
	private MobileElement crossIconInGoalBottomSheet;

	public MobileElement crossIconInGoalBottomSheet() {
		return crossIconInGoalBottomSheet;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Proceed\"]")
	private MobileElement proceedBtnInGoalBottomSheet;

	public MobileElement proceedBtnInGoalBottomSheet() {
		return proceedBtnInGoalBottomSheet;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Cancel\"]")
	private MobileElement cancelBtnInGoalBottomSheet;

	public MobileElement cancelBtnInGoalBottomSheet() {
		return cancelBtnInGoalBottomSheet;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"close_bottom_sheet\"]")
	private MobileElement crossIconInWhatYouWillGetBottomSheet;

	public MobileElement crossIconInWhatYouWillGetBottomSheet() {
		return crossIconInWhatYouWillGetBottomSheet;
	}

	@AndroidFindBy(xpath = "//android.widget.EditText")
	private MobileElement searchBoxInWhatYouWillGetBottomSheet;

	public MobileElement searchBoxInWhatYouWillGetBottomSheet() {
		return searchBoxInWhatYouWillGetBottomSheet;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"subjectFilterBottomSheetSearchClear\"]")
	private MobileElement subjectFilterBottomSheetSearchClear;

	public MobileElement subjectFilterBottomSheetSearchClear() {
		return subjectFilterBottomSheetSearchClear;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"MORE OFFERS\"]")
	private MobileElement moreOffersBtnOnPDP;

	public MobileElement moreOffersBtnOnPDP() {
		return moreOffersBtnOnPDP;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Start Learning\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[contains(@name,'purchaseSuccessStartLearningButton')]")
	private MobileElement btnStartLearning;

	public MobileElement getBtnStartLearning() {
		return btnStartLearning;
	}

	@AndroidFindBy(id = "com.adda247.app:id/purchaseTitle")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Purchased']")
	private MobileElement btnPurchasedSection;

	public MobileElement getBtnPurchasedSection() {
		return btnPurchasedSection;
	}

	@AndroidFindBy(accessibility = "Home")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'Home')]")
	private MobileElement btnHome;

	public MobileElement getBtnHome() {
		return btnHome;
	}

	@AndroidFindBy(accessibility = "Test Prime")
	private MobileElement btnTestPass;

	public MobileElement getBtnTestPass() {
		return btnTestPass;
	}

	@AndroidFindBy(id = "com.adda247.app:id/nav_home_performance_dashboard")
	private MobileElement performanceDashboard;

	public MobileElement performanceDashboard() {
		return performanceDashboard;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Purchase a course to access Performance Dashboard\"]")
	private MobileElement purchaseCourseToAccessText;

	public MobileElement purchaseCourseToAccessText() {
		return purchaseCourseToAccessText;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Take Me To Store\"]")
	private MobileElement takeMeToStore;

	public MobileElement takeMeToStore() {
		return takeMeToStore;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Take Me To Store\"]")
	private MobileElement takeMeToStoreInMyContent;

	public MobileElement takeMeToStoreInMyContent() {
		return takeMeToStoreInMyContent;
	}

}
