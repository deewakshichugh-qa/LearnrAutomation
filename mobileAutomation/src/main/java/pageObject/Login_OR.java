package pageObject;

import java.util.List;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.support.PageFactory;

public class Login_OR {

    // Constructor to accept AppiumDriver and initialize elements
    public Login_OR(AppiumDriver<MobileElement> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//*[contains(@text,'SignUp')]")
    @iOSXCUITFindBy(xpath = "//*[contains(@name,'SignUp')]")
    private MobileElement btnSignUp;

    public MobileElement getBtnSignUp() {
        return btnSignUp;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"93K + Government Job Selections in 2024\n" +
            "Log in/Sign up\n" +
            "Mobile Number\n" +
            "Or Log in\"]")
    private MobileElement firstPageOfApp;

    public MobileElement getTeachersLoginFirstPage() {
        return firstPageOfApp;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Log in/Sign up\n" +
            "Mobile Number\"]/android.widget.EditText/android.widget.EditText")
    private MobileElement inputBoxAfterClickInTeachersLogin;

    public MobileElement getInputBoxAfterClickInTeachersLogin() {
        return inputBoxAfterClickInTeachersLogin;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"93K + Government Job Selections in 2024\n" +
            "Log in/Sign up\n" +
            "Mobile Number\n" +
            "Or Log in\"]/android.widget.EditText/android.widget.EditText")
    private MobileElement inputBoxBeforeClickInTeachersLogin;

    public MobileElement getInputBoxBeforeClickInTeachersLogin() {
        return inputBoxBeforeClickInTeachersLogin;
    }

    @AndroidFindBy(xpath = "//android.widget.EditText/android.widget.EditText")
    private MobileElement inputBoxAfterClickInWaterfallLogin;

    public MobileElement getInputBoxAfterClickInWaterfallLogin() {
        return inputBoxAfterClickInWaterfallLogin;
    }

    @AndroidFindBy(xpath = "//android.widget.EditText/android.widget.EditText")
    private MobileElement inputBoxBeforeClickInWaterfallLogin;

    public MobileElement getInputBoxBeforeClickInWaterfallLogin() {
        return inputBoxBeforeClickInWaterfallLogin;
    }

    @AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,\"login_continue_btn\")]")
    private MobileElement continueBtn;

    public MobileElement getContinueBtn() {
        return continueBtn;
    }

    @AndroidFindBy(accessibility = "Home")
    @iOSXCUITFindBy(xpath = "//*[contains(@name,'Home')]")
    private MobileElement btnHome;

    public MobileElement getBtnHome() {
        return btnHome;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Sign in with Google']")
    @iOSXCUITFindBy(accessibility = "Google")
    private MobileElement btnSignInWithGoogle;

    public MobileElement getBtnSignInWithGoogle() {
        return btnSignInWithGoogle;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Email/Phone']")
    @iOSXCUITFindBy(id = "Email / Phone")
    private MobileElement btnEmailPhone;

    public MobileElement getBtnEmailMobile() {
        return btnEmailPhone;
    }

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"E-mail\"]")
    private MobileElement btnEmail;

    public MobileElement getBtnEmail() {
        return btnEmail;
    }

    @iOSXCUITFindBy(accessibility = "Apple")
    private MobileElement btnApple;

    public MobileElement getBtnApple() {
        return btnApple;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Sign Up']")
    @iOSXCUITFindBy(id = "Sign Up")
    private MobileElement titleSignUp;

    public MobileElement getTitleSignUp() {
        return titleSignUp;
    }

    @AndroidFindBy(className = "android.widget.EditText")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Mobile Number']")
    private MobileElement fieldMobileNumber;

    public MobileElement getFieldMobileNumber() {
        return fieldMobileNumber;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Generate OTP']")
    @iOSXCUITFindBy(id = "Generate OTP")
    private MobileElement newBtnGenerateOTP;

    public MobileElement getBtnGenerateOTP() {
        return newBtnGenerateOTP;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Login']")
    @iOSXCUITFindBy(xpath = "//*[contains(@name,'Already have an account')]")
    private MobileElement linkLogin;

    public MobileElement getLinkLogin() {
        return linkLogin;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Login']")
    private MobileElement titleLogin;

    public MobileElement getTitleLogin() {
        return titleLogin;
    }

    @AndroidFindBy(className = "android.widget.EditText")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField")
    private List<MobileElement> fieldText;

    public List<MobileElement> getListFieldText() {
        return fieldText;
    }

    @AndroidFindBy(xpath = "//android.view.View/android.view.View/android.widget.EditText")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[contains(@name,'otpEntered')]")
    private MobileElement fieldOtp;

    public MobileElement getFieldOtp() {
        return fieldOtp;
    }

//	@AndroidFindBy(className = "(android.widget.EditText)[1]")
//	private MobileElement fieldPassword;
//	
//	public MobileElement getFieldPassword() {
//		return fieldPassword;
//	}

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Forgot Password?']")
    private MobileElement newLinkForgotPassword;

    public MobileElement getLinkForgotPassword() {
        return newLinkForgotPassword;
    }

    @AndroidFindBy(xpath = "(//*[contains(@text,'Login')])[2]")
    @iOSXCUITFindBy(id = "Login")
    private MobileElement btnLogin;

    public MobileElement getBtnLogin() {
        return btnLogin;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='SignUp Now']")
    private MobileElement newLinkSignUpNow;

    public MobileElement getLinkSignUpNow() {
        return newLinkSignUpNow;
    }

    @AndroidFindBy(xpath = "//*[contains(@text,'NONE OF THE ABOVE')]")
    private MobileElement btnNOTA;

    public MobileElement getBtnNOTA() {
        return btnNOTA;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Skip']")
    @iOSXCUITFindBy(id = "SKIP")
    private MobileElement newBtnSkip;

    public MobileElement getBtnSkip() {
        return newBtnSkip;
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Continue']")
    private MobileElement newBtnContinue;

    public MobileElement getBtnContinue() {
        return newBtnContinue;
    }

//---------------------------Select Your Category----------------------------------------

    @AndroidFindBy(id = "com.adda247.app:id/title")
    private List<MobileElement> categoryOptions;

    public List<MobileElement> getCategoryOptions() {
        return categoryOptions;
    }

    //@AndroidFindBy(xpath = "//androidx.compose.ui.platform.ComposeView[contains(@resource-id,'compose_view')]/descendant::android.widget.Button")
    @AndroidFindBy(xpath = "//androidx.compose.ui.platform.ComposeView[contains(@resource-id,'compose_view')]/descendant::android.view.View/android.view.View/android.widget.TextView[1]")
    private List<MobileElement> categoryList;

    public List<MobileElement> getCategoryList() {
        return categoryList;
    }


//--------------------------Select Your Exam---------------------------------------------

    @AndroidFindBy(id = "com.adda247.app:id/title")
    private List<MobileElement> examOptions;

    public List<MobileElement> getExamOptions() {
        return examOptions;
    }

    @AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'Select your Exam')]")
    private MobileElement selectExamPageTitle;

    public MobileElement getSelectExamPageTitle() {
        return selectExamPageTitle;
    }

    @AndroidFindBy(xpath = "//*[@text='Go Back to Main List']/preceding-sibling::android.view.View/android.view.View/android.widget.TextView[1]")
    private List<MobileElement> examListText;

    public List<MobileElement> getExamListText() {
        return examListText;
    }

//-------------------------Select Your Language--------------------------------------------

    public MobileElement getBtnEmailPhone() {
        return btnEmailPhone;
    }

    public MobileElement getNewBtnGenerateOTP() {
        return newBtnGenerateOTP;
    }

    public List<MobileElement> getFieldText() {
        return fieldText;
    }

    public MobileElement getNewLinkForgotPassword() {
        return newLinkForgotPassword;
    }

    public MobileElement getNewLinkSignUpNow() {
        return newLinkSignUpNow;
    }

    public MobileElement getNewBtnSkip() {
        return newBtnSkip;
    }

    public MobileElement getNewBtnContinue() {
        return newBtnContinue;
    }

    @AndroidFindBy(id = "com.adda247.app:id/tv_language_name")
    private List<MobileElement> languageOptions;

    public List<MobileElement> getLanguageOptions() {
        return languageOptions;
    }

    @AndroidFindBy(xpath = "//*[@text='Highest Success Numbers']")
    private MobileElement highestSuccessNumbersTitle;

    public MobileElement getHighestSuccessNumbersTitle() {
        return highestSuccessNumbersTitle;
    }

    @AndroidFindBy(xpath = "//*[@text='Largest Learning Destination']")
    private MobileElement largestLearningDestTitle;

    public MobileElement getLargestLearningDestTitle() {
        return largestLearningDestTitle;
    }

    @AndroidFindBy(xpath = "//*[@text='Unmatched Tests & Video Classes']")
    private MobileElement unmatchedTestVideoTitle;

    public MobileElement getUnmatchedTestVideoTitle() {
        return unmatchedTestVideoTitle;
    }

    @AndroidFindBy(xpath = "//*[@text='Stay ahead of the pack']")
    private MobileElement stayAheadPackTitle;

    public MobileElement getStayAheadPackTitle() {
        return stayAheadPackTitle;
    }

    @AndroidFindBy(xpath = "//*[@content-desc='vs']/following-sibling::android.widget.TextView[1]")
    private MobileElement signUpHomePageTitle;

    public MobileElement getSignUpHomePageTitle() {
        return signUpHomePageTitle;
    }

    @AndroidFindBy(xpath = "//*[@text='Best in Class Faculties']")
    private MobileElement classFacultiesTitle;

    public MobileElement getClassFacultiesTitle() {
        return classFacultiesTitle;
    }

    @AndroidFindBy(xpath = "//*[@text='Mock Test']")
    private MobileElement mockTestTitle;

    public MobileElement getMockTestTitle() {
        return mockTestTitle;
    }

    @AndroidFindBy(xpath = "//*[@text='Conceptual Videos']")
    private MobileElement conceptualVideosTitle;

    public MobileElement getConceptualVideosTitle() {
        return conceptualVideosTitle;
    }

    @AndroidFindBy(xpath = "//*[@text='Class Notes, PDFs']")
    private MobileElement classNotesPdfTitle;

    public MobileElement getClassNotesPdfTitle() {
        return classNotesPdfTitle;
    }

    @iOSXCUITFindAll({
            @iOSXCUITBy(id = "Email or Mobile Number"),
            @iOSXCUITBy(xpath = "//XCUIElementTypeOther/XCUIElementTypeTextField")
    })
    private List<MobileElement> EnterMobileNumber;

    public List<MobileElement> getEnterMobileNumber() {
        return EnterMobileNumber;
    }

    @iOSXCUITFindAll({
            @iOSXCUITBy(id = "Password"),
            @iOSXCUITBy(xpath = "//XCUIElementTypeButton/preceding-sibling::XCUIElementTypeTextField")
    })
    private List<MobileElement> EnterPassword;

    public List<MobileElement> getEnterPassword() {
        return EnterPassword;
    }

    @iOSXCUITFindBy(id = "Google")
    @AndroidFindBy(xpath = "//*[@text='Sign in with Google']")
    private List<MobileElement> googleLoginButton;

    public List<MobileElement> getGoogleLoginButton() {
        return googleLoginButton;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"singleSessionLogoutClick\n" +
            "Log out\"]")
    private MobileElement logoutBtn;

    public MobileElement getLogoutBtn() {
        return logoutBtn;
    }

    @AndroidFindBy(xpath = "//*[contains(@content-desc,\"LOGIN\")]")
    private MobileElement logIn_ctn;

    public MobileElement logIn_ctn() {
        return logIn_ctn;
    }

    @AndroidFindBy(xpath = "//*[contains(@content-desc, \"Search Exam or Course\")]")
    private MobileElement examCourseSearchBar;

    public MobileElement examCourseSearchBar() {
        return examCourseSearchBar;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc,\"Type to search Your exam\\\"]/android.widget.ImageView[2]")
    private MobileElement searchBoxOfExamCategory;

    public MobileElement searchBoxOfExamCategory() {
        return searchBoxOfExamCategory;
    }

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"no_results_found_image\"]")
    private MobileElement errorView;

    public MobileElement errorView() {
        return errorView;
    }

    @AndroidFindBy(xpath = "//android.widget.ImageView[@text=\"exam\"]")
    private MobileElement textBoxFilled;

    public MobileElement textBoxFilled() {
        return textBoxFilled;
    }


    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"start_search_image\"]")
    private MobileElement blankViewTextBox;

    public MobileElement blankViewTextBox() {
        return blankViewTextBox;
    }


    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"ExamListSearchWidget\"]")
    private MobileElement bckBtnOnSearchPage;

    public MobileElement bckBtnOnSearchPage() {
        return bckBtnOnSearchPage;
    }


    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"GOVERNMENT JOB EXAMS\n" +
            "exam_list_category_wrapper\"]")
    private MobileElement govtJobExam;

    public MobileElement govtJobExam() {
        return govtJobExam;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"GOVERNMENT JOB EXAMS\n" +
            "exam_list_category_wrapper\"]")
    private MobileElement govtJobExamList;

    public MobileElement govtJobExamList() {
        return govtJobExamList;
    }

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"exam_list_category\n" +
            "Banking\n" +
            "IBPS PO, CLERK, RRB, SBI, RBI, Insurance\"]")
    private MobileElement bankingSelection;

    public MobileElement bankingSelection() {
        return bankingSelection;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Choose your Language\n" +
            "Choose a Language you are comfortable in Studying\"]")
    private MobileElement languageSelectionPage;

    public MobileElement languageSelectionPage() {
        return languageSelectionPage;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"English\n" +
            "English\n" +
            "Hello\"]")
    private MobileElement englishLanguageSelected;

    public MobileElement englishLanguageSelected() {
        return englishLanguageSelected;
    }

    @AndroidFindBy(xpath = "//*[@content-desc='Terms of Use']")
    private MobileElement termsOfUseText;

    public MobileElement termsOfUseText() {
        return termsOfUseText;
    }

    @AndroidFindBy(xpath = "//*[@content-desc='btn_terms_and_condition']")
    private MobileElement privacyPolicyText;

    public MobileElement privacyPolicyText() {
        return privacyPolicyText;
    }

    @AndroidFindBy(xpath = "//*[@content-desc='Store']")
    private MobileElement storeBtn;

    public MobileElement storeBtn() {
        return storeBtn;
    }

    @AndroidFindBy(xpath =
            "//android.view.View[(@content-desc='password_text_field' or @content-desc='Atleast 6 characters')]"
                    + "/android.widget.EditText | " + "//android.view.View[@content-desc='password_text_field']/android.view.View/android.widget.EditText")
    private MobileElement passwordEditBoxBtn;

    public MobileElement passwordEditBoxBtn() {
        return passwordEditBoxBtn;
    }

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='email_text_field']/android.widget.EditText")
    private MobileElement userNameEditBoxBtn;

    public MobileElement userNameEditBoxBtn() {
        return userNameEditBoxBtn;
    }

    @AndroidFindBy(xpath = "//*[contains(@content-desc,'btn_confirm')]")
    private MobileElement confirmOrContinueBtn;

    public MobileElement confirmOrContinueBtn() {
        return confirmOrContinueBtn;
    }

}