package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class PostingPage_OR {
	
	@AndroidFindBy(xpath = "//*[contains(@content-desc,'openBottomSheet\nSelect Topic')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='openBottomSheet\nSelect Topic']")
	private MobileElement dropMenuTopic;
	
	public MobileElement getDropMenuTopic() {
		return dropMenuTopic;
	}
	
	@AndroidFindBy(xpath = "(//android.widget.EditText)")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[contains(@name,'openTextFormField')]")
	private MobileElement fieldAskQuery;
	
	public MobileElement getFieldAskQuery() {
		return fieldAskQuery;
	}
	
	@AndroidFindBy(xpath = "//android.widget.EditText")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeTextField[contains(@name,'openTextFormField')]")
	private List<MobileElement> fieldText;
	
	public List<MobileElement> getFieldText() {
		return fieldText;
	}
	
//	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'addAPhoto\nAdd Photos/Videos']")
//	private MobileElement linkAddPhotosVideos;
//	
//	public MobileElement getLinkAddPhotosVideos() {
//		return linkAddPhotosVideos;
//	}
	
	@AndroidFindBy(xpath = "(//android.widget.EditText)")
	private MobileElement fieldTagFriends;
	
	public MobileElement getFieldTagFriends() {
		return fieldTagFriends;
	}
	
	@AndroidFindBy(accessibility = "Post")
	private MobileElement postBtn;
	
	public MobileElement getPostBtn() {
		return postBtn;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@content-desc,'post\nPost')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='post\nPost']")
	private MobileElement btnPost;
	
	public MobileElement getBtnPost() {
		return btnPost;
	}
	
//------------------------------SELECT TOPIC------------------------------------------
	
	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Select Topic\"]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name='Select Topic']")
	private MobileElement titleSelectTopic;
	
	public MobileElement getTitleSelectTopic() {
		return titleSelectTopic;
	}
	
	@AndroidFindBy(xpath = "//android.widget.RadioButton")
	@iOSXCUITFindBy(className = "XCUIElementTypeButton")
	private List<MobileElement> optionTopics;
	
	public List<MobileElement> getOptionTopics() {
		return optionTopics;
	}

//-------------------------------MCQs------------------------------------------------
	
	@AndroidFindBy(xpath = "//android.widget.CheckBox")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name='addMcq\nAdd Options']/preceding-sibling::XCUIElementTypeOther/descendant::XCUIElementTypeSwitch")
	private List<MobileElement> listCheckBox;
	
	public List<MobileElement> getListCheckBox() {
		return listCheckBox;
	}
	
	@AndroidFindBy(xpath = "//*[contains(@content-desc,'addMcq')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeImage[@name='addMcq\nAdd Options']")
	private MobileElement linkAddMcq;
	
	public MobileElement getLinkAddMcq() {
		return linkAddMcq;
	}
	
	@AndroidFindBy(accessibility = "Select from gallery")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Select from gallery']")
	private MobileElement selectFromGalleryTab;
	
	@AndroidFindBy(accessibility = "openCamera")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Open Camera']")
	private MobileElement openCameraTab;
	
	@AndroidFindBy(id = "com.google.android.documentsui:id/icon_thumb")
	private MobileElement imageFile;
	
	@AndroidFindBy(accessibility = "Crop")
	private MobileElement cropBtn;

	public MobileElement getSelectFromGalleryTab() {
		return selectFromGalleryTab;
	}

	public MobileElement getOpenCameraTab() {
		return openCameraTab;
	}

	public MobileElement getImageFile() {
		return imageFile;
	}

	public MobileElement getCropBtn() {
		return cropBtn;
	}
	
	@AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
	private MobileElement permissionAllowBtn;

	public MobileElement getPermissionAllowBtn() {
		return permissionAllowBtn;
	}
	
	@AndroidFindBy(xpath = "//*[contains(@content-desc,'openBottomSheet')]")
	private MobileElement selectGroupDropDown;

	public MobileElement getSelectGroupDropDown() {
		return selectGroupDropDown;
	}
	
	@AndroidFindBy(xpath = "//*[contains(@content-desc,'Submit')]")
	private MobileElement submitBtn;

	public MobileElement getSubmitBtn() {
		return submitBtn;
	}
	
	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'addAPhoto')]")
	private MobileElement addPhotoTitle;

	public MobileElement getAddPhotoTitle() {
		return addPhotoTitle;
	}
	
	@AndroidFindBy(xpath = "//android.widget.CheckBox[not(@content-desc)]/preceding-sibling::android.widget.EditText")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Options']/following-sibling::XCUIElementTypeOther/descendant::XCUIElementTypeTextField[contains(@name,'openTextFormField')]")
	private List<MobileElement> mcqOptionList;

	public List<MobileElement> getMcqOptionList() {
		return mcqOptionList;
	}
	
	@AndroidFindBy(xpath = "//android.widget.CheckBox[not(@content-desc)]")
	private List<MobileElement> mcqOptionCheckBoxList;

	public List<MobileElement> getMcqOptionCheckBoxList() {
		return mcqOptionCheckBoxList;
	}
	
	@AndroidFindBy(accessibility = "don'tKnowTheAnswer")
	private MobileElement dontKnowAnswerCheckBox;

	public MobileElement getDontKnowAnswerCheckBox() {
		return dontKnowAnswerCheckBox;
	}
	
	@AndroidFindBy(xpath = "//android.widget.ImageView[contains(@content-desc,'addAPhoto')]/preceding-sibling::android.widget.EditText")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'addAPhoto')]/preceding-sibling::XCUIElementTypeTextField")
	private MobileElement postQueryTextField;

	public MobileElement getPostQueryTextField() {
		return postQueryTextField;
	}
	
	@AndroidFindBy(xpath = "//*[contains(@content-desc,'MCQ/Poll')]")
	private MobileElement mcqPostTab;
	
	public MobileElement getMcqPostTab() {
		return mcqPostTab;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'Attachment Post')]")
	private MobileElement attachmentPostTab;
	
	public MobileElement getAttachmentPostTab() {
		return attachmentPostTab;
	}
}

