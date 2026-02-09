package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class DoubtsPage_OR {

	@AndroidFindBy(id = "com.adda247.app:id/compose_box")
	private MobileElement textFieldAskAQuestion;
	
	public MobileElement getTextFieldAskAQuestion() {
		return textFieldAskAQuestion;
	}
	
	@AndroidFindBy(id = "//android.widget.TextView[@text='ALL DOUBTS']")
	private MobileElement tabAllDoubts;
	
	public MobileElement getTabAllDoubts() {
		return tabAllDoubts;
	}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='MY DOUBTS']")
	private MobileElement tabMyDoubts;
	
	public MobileElement getTabMyDoubts() {
		return tabMyDoubts;
	}
	
	@AndroidFindBy(id = "com.adda247.app:id/filter_topic")
	private MobileElement textFilterByExamOrTopic;
	
	public MobileElement getTextFilterByExamOrTopic() {
		return textFilterByExamOrTopic;
	}
	
	@AndroidFindBy(id = "com.adda247.app:id/filter")
	private MobileElement btnFilters;
	
	public MobileElement getBtnFilters() {
		return btnFilters;
	}
	
	@AndroidFindBy(id = "com.adda247.app:id/image_camera")
	private MobileElement btnCamera;
	
	public MobileElement getBtnCamera() {
		return btnCamera;
	}
	
//-----------------------------QuestionSection-------------------------------
	
	@AndroidFindBy(id = "com.adda247.app:id/compose_view")
	private MobileElement textFieldTypeYourQuestion;
	
	public MobileElement getTextFieldTypeYourQuestion() {
		return textFieldTypeYourQuestion;
	}
	
	@AndroidFindBy(id = "com.adda247.app:id/image_txt")
	private MobileElement linkAddAPhoto;
	
	public MobileElement getLinkAddAPhoto() {
		return linkAddAPhoto;
	}
	
	@AndroidFindBy(id = "com.adda247.app:id/image_icon")
	private MobileElement iconCamera;
	
	public MobileElement getIconCamera() {
		return iconCamera;
	}
	
	@AndroidFindBy(id = "com.adda247.app:id/next")
	private MobileElement btnNext;
	
	public MobileElement getBtnNext() {
		return btnNext;
	}
	
	@AndroidFindBy(id = "com.adda247.app:id/back")
	private MobileElement btnBack;
	
	public MobileElement getBtnBack() {
		return btnBack;
	}
	
	@AndroidFindBy(id = "com.adda247.app:id/bottom_view")
	private MobileElement scrollMenuSelectExam;
	
	public MobileElement getScrollMenuSelectExam() {
		return scrollMenuSelectExam;
	}
	
	@AndroidFindBy(id = "com.adda247.app:id/tv_exam_title")
	private List<MobileElement> optionsExamType;
	
	public List<MobileElement> getOptionsExamType() {
		return optionsExamType;
	}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='POST YOUR QUESTION']")
	private MobileElement btnPostYourQuestion;
	
	public MobileElement getBtnPostYourQuestion() {
		return btnPostYourQuestion;
	}
//----------------------------------------------------------------------------	
	@AndroidFindBy(id = "com.adda247.app:id/description")
	private List<MobileElement> descAllDoubts;
	
	public List<MobileElement> getDescAllDoubts() {
		return descAllDoubts;
	}
	
	@AndroidFindBy(id = "com.adda247.app:id/share_link")
	private MobileElement iconWhatsapp;
	
	public MobileElement getIconWhatsapp() {
		return iconWhatsapp;
	}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='You can not like your own doubt.']")
	private MobileElement textYouCantLikeYourOwnDoubt;
	
	public MobileElement getTextYouCantLikeYourOwnDoubt() {
		return textYouCantLikeYourOwnDoubt;
	}
	
	@AndroidFindBy(id = "com.adda247.app:id/like")
	private List<MobileElement> btnLikeDoubt;
	
	public List<MobileElement> getBtnLikeDoubt() {
		return btnLikeDoubt;
	}
	
	@AndroidFindBy(id = "com.adda247.app:id/over_flow")
	private List<MobileElement> dotsMenuDoubt;
	
	public List<MobileElement> getMenuDoubt() {
		return dotsMenuDoubt;
	}
	
	//-----------------------Dots slide menu--------------------------------------
	
	@AndroidFindBy(id = "com.adda247.app:id/book_mark")
	private MobileElement dotsMenuBookmark;	

	public MobileElement getDotsMenuBookmark() {
		return dotsMenuBookmark;
	}
	
	@AndroidFindBy(id = "com.adda247.app:id/notification")
	private MobileElement dotMenuTurnOffPostNotification;
	
	public MobileElement getDotMenuTurnOffPostNotification() {
		return dotMenuTurnOffPostNotification;
	}
	
	@AndroidFindBy(id = "com.adda247.app:id/share")
	private MobileElement dotMenuSharePost;
	
	public MobileElement getDotMenuSharePost() {
		return dotMenuSharePost;
	}
	
	@AndroidFindBy(id = "com.adda247.app:id/delete")
	private MobileElement dotMenuDelete;
	
	public MobileElement getDotMenuDelete() {
		return dotMenuDelete;
	}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Added to Bookmark']")
	private MobileElement textAddedToBookmark;
	
	public MobileElement getTextAddedToBookmark() {
		return textAddedToBookmark;
	}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Are you sure you want to delete this post?']")
	private MobileElement textDeleteConfirmation;
	
	public MobileElement getTextDeleteConfirmation() {
		return textDeleteConfirmation;
	}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='No Doubts found!']")
	private MobileElement textNoDoubtsFound;
	
	public MobileElement getTextNoDoubtsFound() {
		return textNoDoubtsFound;
	}
	

//----------------------------------------------------------------------------------
	
	@AndroidFindBy(id = "android:id/button2")
	private MobileElement btnDeletePopup;
	
	public MobileElement getBtnDeletePopup() {
		return btnDeletePopup;
	}
	
	@AndroidFindBy(id = "android:id/button1")
	private MobileElement btnCancelPopup;
	
	public MobileElement getBtnCancelPopup() {
		return btnCancelPopup;
	
	}
	
	
}
