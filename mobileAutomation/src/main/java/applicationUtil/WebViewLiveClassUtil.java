package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import applicationUtil.LiveClassesPageUtil.ConfigurationMode;
import util.Common_Function;

public class WebViewLiveClassUtil {

	@FindBy(css = ".chatmsg")
	private List<WebElement> chatMsgList;

	public List<WebElement> getChatMsgList() {
		return chatMsgList;
	}

	@FindBy(css = ".chatmsg")
	private List<WebElement> listChat;

	public List<WebElement> getListChat() {
		return listChat;
	}

	@FindBy(css = ".sendbg")
	private WebElement btnSendMessage;

	public WebElement getBtnSendMessage() {
		return btnSendMessage;
	}

	@FindBy(css = ".textinput")
	private WebElement textField;

	public WebElement getTextField() {
		return textField;
	}

	@FindBy(xpath = "//span[contains(text(),'Start Class')]")
	private WebElement btnStartClass;

	public WebElement getBtnStartClass() {
		return btnStartClass;
	}

	@FindBy(xpath = "//button[@ant-click-animating-without-extra-node='false']")
	private WebElement btnExitClass;

	public WebElement getBtnExitClass() {
		return btnExitClass;
	}

	@FindBy(css = ".ant-select-selection-item")
	private List<WebElement> listSelection;

	public List<WebElement> getListSelection() {
		return listSelection;
	}

	@FindBy(xpath = "//div[position()=8]")
	private WebElement btnHandout;

	public WebElement getBtnHandout() {
		return btnHandout;
	}

	@FindBy(xpath = "//div[position()=7]")
	private WebElement btnPoll;

	public WebElement getBtnPoll() {
		return btnPoll;
	}

	@FindBy(xpath = "//input[@type='file']")
	private List<WebElement> uploadIcon;

	public List<WebElement> getUploadIcon() {
		return uploadIcon;
	}

	@FindBy(xpath = "//input[@placeholder='Add Comment']")
	private WebElement handOutCommentField;

	public WebElement getHandOutCommentField() {
		return handOutCommentField;
	}

	@FindBy(xpath = "//button[contains(@class,'ant-btn ant-btn-primary')]")
	private WebElement handOutSendBtn;

	public WebElement getHandOutSendBtn() {
		return handOutSendBtn;
	}

	@FindBy(css = ".lc-button-download")
	private WebElement btnDownloadHandout;

	public WebElement getBtnDownlaodHandout() {
		return btnDownloadHandout;
	}

	@FindBy(css = ".lc-button-exit")
	private WebElement btnExitHandout;

	public WebElement getBtnExitHandout() {
		return btnExitHandout;
	}

	@FindBy(xpath = "//div[@class='ant-modal-root']//button[2]")
	private WebElement btnSubmitPoll;

	public WebElement getBtnSubmitPoll() {
		return btnSubmitPoll;
	}

	@FindBy(xpath = "//div[@class='ant-modal-root']//button[1]")
	private WebElement btnCancelPoll;

	public WebElement getBtnCancelPoll() {
		return btnCancelPoll;
	}

	@FindBy(css = ".ant-input")
	private List<WebElement> textFieldPoll;

	public List<WebElement> getTextFieldPoll() {
		return textFieldPoll;
	}

	@FindBy(css = ".handoutbtn")
	private WebElement btnHandoutSection;

	public WebElement getBtnHandoutSection() {
		return btnHandoutSection;
	}

	@FindBy(css = ".adda-ll-handout-message")
	private List<WebElement> listHandoutTitle;

	public List<WebElement> getListHandoutTitle() {
		return listHandoutTitle;
	}

	@FindBy(css = ".adda-ll-handout-message")
	private List<WebElement> listBtnHandoutOpen;

	public List<WebElement> getListBtnHandoutOpen() {
		return listBtnHandoutOpen;
	}

	@FindBy(css = ".poll-radio-title")
	private WebElement titlePoll;

	public WebElement getTitlePoll() {
		return titlePoll;
	}

	@FindBy(css = ".poll-radio-list")
	private List<WebElement> listPollOptions;

	public List<WebElement> getListPollOptions() {
		return listPollOptions;
	}

	@FindBy(css = ".doubt-count")
	private List<WebElement> doubtCount;

	public List<WebElement> getDoubtCount() {
		return doubtCount;
	}

	@FindBy(xpath = "//div[@id='ele_stream_doubts']")
	private WebElement teacherdoubtIcon;

	public WebElement getTeacherdoubtIcon() {
		return teacherdoubtIcon;
	}

	@FindBy(css = ".doubt-msg>input")
	private List<WebElement> doubtEditBox;

	public List<WebElement> getDoubtEditBox() {
		return doubtEditBox;
	}

	@FindBy(css = ".sendbg")
	private List<WebElement> sendButon;

	public List<WebElement> getSendButon() {
		return sendButon;
	}

	@FindBy(css = ".chatmsg")
	private List<WebElement> chatMsg;

	public List<WebElement> getChatMsg() {
		return chatMsg;
	}

	@FindBy(css = ".askdoubt")
	private List<WebElement> askDoubt;

	public List<WebElement> getAskDoubt() {
		return askDoubt;
	}

	// Vertical dots menu
	@FindBy(css = ".vertical-dots")
	private List<WebElement> verticalDots;

	public List<WebElement> getVerticalDots() {
		return verticalDots;
	}

	@FindBy(xpath = "(//*[name()='svg'][@class='vertical-dots'])")
	private List<WebElement> verticalDots1;

	public List<WebElement> getVerticalDots1() {
		return verticalDots1;
	}

	@FindBy(xpath = "//*[contains(text(),'Pin Message')]")
	private WebElement optionPinMessage;

	public WebElement getOptionPinMessage() {
		return optionPinMessage;
	}

	@FindBy(xpath = "//*[contains(text(),'Reply Privately')]")
	private WebElement optionReplyPrivately;

	public WebElement getOptionReplyPrivately() {
		return optionReplyPrivately;
	}

	@FindBy(xpath = "//*[contains(text(),'Remove Participant')]")
	private WebElement optionRemoveParticipant;

	public WebElement getOptionRemoveParticipant() {
		return optionRemoveParticipant;
	}

	@FindBy(xpath = "//*[contains(text(),'Unpin Message')]")
	private WebElement optionUnpinMessage;

	public WebElement getOptionUnpinMessage() {
		return optionUnpinMessage;
	}

	// Feedback form
	@FindBy(xpath = "//li[@class='ant-rate-star ant-rate-star-zero']")
	private List<WebElement> rateStar;

	public List<WebElement> getRateStar() {
		return rateStar;
	}

	@FindBy(xpath = "//span[text()='View Poll Responses']")
	private WebElement viewPollResponsesBtn;

	public WebElement getViewPollResponsesBtn() {
		return viewPollResponsesBtn;
	}

	@FindBy(xpath = "//span[text()='View Cumulative Leaderboard']")
	private WebElement viewCumulativeLeaderboardBtn;

	public WebElement getViewCumulativeLeaderboardBtn() {
		return viewCumulativeLeaderboardBtn;
	}

	@FindBy(xpath = "//span[text()='Quick Poll']")
	private WebElement viewQuickPollBtn;

	public WebElement getViewQuickPollBtn() {
		return viewQuickPollBtn;
	}

	@FindBy(xpath = "//input[contains(@name,'checkbox')]")
	private List<WebElement> pollInputCheckbox;

	public List<WebElement> getPollInputCheckbox() {
		return pollInputCheckbox;
	}

	@FindBy(xpath = "//*[@id=\"SvgjsTspan1878\"]")
	private WebElement firstOptionInPollBar;

	public WebElement firstOptionInPollBar() {
		return firstOptionInPollBar;
	}

	@FindBy(xpath = "//button[@class='ant-btn ant-btn-default wide-modal-btn']")
	private WebElement generateLeaderboardBtn;

	public WebElement generateLeaderboardBtn() {
		return generateLeaderboardBtn;
	}

	@FindBy(xpath = "//div[@id='ele_stream_screenshare']")
	private WebElement getBtnScreenshare;

	public WebElement getBtnScreenshare() {
		return getBtnScreenshare;
	}

	@FindBy(xpath = "//span[normalize-space()='Extend Class']")
	private WebElement getBtnExtendClass;

	public WebElement getBtnExtendClass() {
		return getBtnExtendClass;
	}

	@FindBy(xpath = "//div[@id='ele_stream_bookmark']")
	private WebElement getBtnBookmark;

	public WebElement getBtnBookmark() {
		return getBtnBookmark;
	}

	@FindBy(xpath = "//div[@id='ele_stream_sos']")
	private WebElement getBtnSos;

	public WebElement getBtnSos() {
		return getBtnSos;
	}

	@FindBy(xpath = "//div[@id='ele_stream_whiteboard']")
	private WebElement getBtnWhiteboard;

	public WebElement getBtnWhiteboard() {
		return getBtnWhiteboard;
	}

	@FindBy(xpath = "//div[contains(@class,'camera-feed')]")
	private WebElement getBtnCameraSwap;

	public WebElement getBtnCameraSwap() {
		return getBtnCameraSwap;
	}

	@FindBy(xpath = "//*[contains(text(),'Start')]")
	private WebElement startBtnInBookmark;

	public WebElement startBtnInBookmark() {
		return startBtnInBookmark;
	}

	@FindBy(xpath = "//*[contains(text(),'Stop')]")
	private WebElement stopBtnInBookmark;

	public WebElement stopBtnInBookmark() {
		return stopBtnInBookmark;
	}

	@FindBy(xpath = "//*[contains(text(),'Bookmarks')]")
	private WebElement bookmarksListBtn;

	public WebElement bookmarksListBtn() {
		return bookmarksListBtn;
	}

	@FindBy(css = "button[id='hamburgericon']")
	private WebElement HamburgerIcon;

	public WebElement getHamburgerIcon() {
		return HamburgerIcon;
	}

	@FindBy(xpath = "//input[@id='pdfFileUpload']")
	private WebElement PDFFileUploaderWhiteboard;

	public WebElement getPDFFileUploaderWhiteboard() {
		return PDFFileUploaderWhiteboard;
	}

	@FindBy(xpath = "//div[@class='row d-flex align-items-center flex-row px-md-5 col-sm-6 justify-content-around teacher-header-wrapper']/div[7]")
	private WebElement WhiteBoardIcon;

	public WebElement getWhiteBoardIcon() {
		return WhiteBoardIcon;
	}

	@FindBy(xpath = "//canvas[@id='wb-preview']")
	private WebElement Whiteboard;

	public WebElement getWhiteboard() {
		return Whiteboard;
	}

	@FindBy(xpath = "//div[contains(text(),'Download File')]")
	private WebElement DownloadPDFIconInHamburger;

	public WebElement getDownloadPDFIconInHamburger() {
		return DownloadPDFIconInHamburger;
	}

	@FindBy(css = "button[id='pencil']")
	private WebElement PencilIconWhiteboard;

	public WebElement getPencilIconWhiteboard() {
		return PencilIconWhiteboard;
	}

	@FindBy(css = "button[id='brush']")
	private WebElement MarkerIconWhiteboard;

	public WebElement getMarkerIconWhiteboard() {
		return MarkerIconWhiteboard;
	}

	@FindBy(css = "button[id='eraser']")
	private WebElement EraserIconWhiteboard;

	public WebElement getEraserIconWhiteboard() {
		return EraserIconWhiteboard;
	}

	@FindBy(css = "button[id='text']")
	private WebElement TextIconWhiteboard;

	public WebElement getTextIconWhiteboard() {
		return TextIconWhiteboard;
	}

	@FindBy(xpath = "//input[@class='ant-input']")
	private WebElement InputBoxofTextInWhiteBoard;

	public WebElement getInputBoxofTextInWhiteBoard() {
		return InputBoxofTextInWhiteBoard;
	}

	@FindBy(css = "button[id='color-picker']")
	private WebElement ColorPickerIconWhiteboard;

	public WebElement getColorPickerIconWhiteboard() {
		return ColorPickerIconWhiteboard;
	}

	@FindBy(xpath = "//div[@class='flexbox-fix'][3]/div")
	List<WebElement> ColorPickerTemplateIconWhiteboard;

	public List<WebElement> getColorPickerTemplateIconWhiteboard() {
		return ColorPickerTemplateIconWhiteboard;
	}

	@FindBy(css = "button[id='reset-wb']")
	private WebElement resetIconWhiteboard;

	public WebElement getResetIconWhiteboard() {
		return resetIconWhiteboard;
	}

	@FindBy(xpath = "(//div[contains(@class,'icon_bg teacher_icon mx-2')])[2]")
	WebElement VideoIconatTecherEnd;

	public WebElement getVideoIconatTecherEnd() {
		return VideoIconatTecherEnd;
	}

	@FindBy(xpath = "//li[@class='fullscreen']")
	WebElement windowResizeBtn;

	public WebElement getWindowResizeBtn() {
		return windowResizeBtn;
	}


	@FindBy(xpath = "//div[@class='fullscrn noselect']")
	WebElement fullScreenWindowResizeBtn;

	public WebElement getFullScreenWindowResizeBtn() {
		return fullScreenWindowResizeBtn;
	}

	WebViewLiveClassUtil webViewLiveClassUtilObj;
	List<String> webViewLiveClassUtilMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();
	WebDriver webDriver;

	public WebViewLiveClassUtil(WebDriver webDriver) {
		this.webDriver = webDriver;
		PageFactory.initElements(webDriver, this);
	}

	public boolean startClassFromTeacherSide(WebDriver webDriver, ConfigurationMode mode, String teacherScreenUrl) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(webDriver, getBtnStartClass(), 10);
			if (!result) {
				webViewLiveClassUtilMsgList.add("Start class btn is not visible");
				return result;
			}

			// click on start class button
			cfObj.commonClick(getBtnStartClass());

			// camera and mic selection window doesn't open
			if (cfObj.commonWaitForElementToBeVisible(webDriver, getListSelection().get(0), 30)) {

				Thread.sleep(2000);

				// Selecting Configuration mode
				if (mode.equals(ConfigurationMode.BASIC)) {

					cfObj.commonClick(getListSelection().get(0));
					Thread.sleep(1000);
					cfObj.commonClick(cfObj.commonGetElement(webDriver, "//div[text()='BASIC']", "xpath"));

				} else {
					cfObj.commonClick(getListSelection().get(0));
					Thread.sleep(1000);
					cfObj.commonClick(cfObj.commonGetElement(webDriver, "//div[text()='STANDARD']", "xpath"));
				}

				Thread.sleep(1000);

				String cameraSelection = getListSelection().get(1).getText();
				System.out.println("The default camera selection is: " + cameraSelection);

				Thread.sleep(1000);

				String micSelection = getListSelection().get(2).getText();
				System.out.println("The default mic selection is: " + micSelection);

				Thread.sleep(1000);

				// Click on start button in pop-up
				cfObj.commonClick(cfObj.commonGetElement(webDriver, ".ant-btn-primary", "css"));
				Thread.sleep(12000);
			} else {
				webViewLiveClassUtilMsgList.add("The camera and mic selection window doesn't open");
				return result;
			}

			// wait for class to be start at teacher end
			result = cfObj.commonWaitForElementToBeVisible(webDriver, getBtnExitClass(), 10);
			if (!result) {
				webViewLiveClassUtilMsgList.add("Live Class not started, as end class btn not visible");
				return result;
			}

			// Off the audio of class
			cfObj.commonClick_JS(webDriver, cfObj.commonGetElement(webDriver, "//div[@id='ele_stream_audio']", "xpath"));

			// Off the video of class
			cfObj.commonClick_JS(webDriver, cfObj.commonGetElement(webDriver, "//div[@id='ele_stream_video']", "xpath"));

		} catch (Exception e) {
			result = false;
			webViewLiveClassUtilMsgList.add("Exception in startClassFromTeacherSide_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean endLiveClass(WebDriver webDriver) {
		boolean result = true;
		try {

			cfObj.commonClick(getBtnExitClass());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, ".ant-btn-primary", "css", 10);
			if (result) {
				cfObj.commonClick(cfObj.commonGetElement(webDriver, ".ant-btn-primary", "css"));
			} else {

                cfObj.commonClick(getBtnExitClass());

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, ".ant-btn-primary", "css", 10);
				if (result) {
					cfObj.commonClick(cfObj.commonGetElement(webDriver, ".ant-btn-primary", "css"));
				} else {
					webViewLiveClassUtilMsgList.add("The class end confirmation pop-up doesn't appeared");
					return result;
				}
			}

			Thread.sleep(500);

//			result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//*[text()='Cumulative Leaderboard']", "xpath", 5);
//			if (!result) {
//				webViewLiveClassUtilMsgList.add("The Cumulative leaderboard heading in popup is not visible after end class");
//				result = true;
//			} else {
//
//				// close leaderboard
//				result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//*[@id=\"icondiv\"]/span", "xpath", 20);
//				if (!result) {
//					webViewLiveClassUtilMsgList.add("In Cumulative leaderboard close icon in popup is not visible after end class");
//					return result;
//				} else {
//					cfObj.commonGetElement(webDriver, "//*[@id=\"icondiv\"]/span", "xpath").click();
//				}
//			}

		} catch (Exception e) {
			result = false;
			webViewLiveClassUtilMsgList.add("Exception in endLiveClass: " + e.getMessage());
		}
		return result;
	}

	public boolean validateStudentMessage(WebDriver webDriver, String studentMsg, String teacherMsg) {
		boolean result = true;
		try {
			result = cfObj.commonVerifyValueTextBox(getChatMsgList().get(getChatMsgList().size() - 1), studentMsg);
			if (!result) {
				webViewLiveClassUtilMsgList.add("Student message is not visible on teacher screen.");
				return result;
			}

			result = cfObj.commonSetTextTextBox(getTextField(), teacherMsg, "value");
			if (!result) {
				webViewLiveClassUtilMsgList.add("Failed to enter message on teacher screen text field.");
				return result;
			}
			cfObj.commonClick(getBtnSendMessage());
		} catch (Exception e) {
			result = false;
			webViewLiveClassUtilMsgList.add("validateStudentMessage_Exception: " + e.getMessage());
		}
		return result;

	}

	public boolean validateStudentDoubtMessage(WebDriver webDriver, String studentMsg, String teacherMsg) {
		boolean result = true;
		try {

			cfObj.commonClick(getTeacherdoubtIcon());
			Thread.sleep(1000);
			result = cfObj.commonVerifyValueTextBox(getChatMsgList().get(getChatMsgList().size() - 1), studentMsg);
			if (!result) {
				webViewLiveClassUtilMsgList.add("Student message is not visible on teacher screen.");
				return result;
			}
			result = cfObj.commonSetTextTextBox(getTextField(), teacherMsg, "value");
			if (!result) {
				webViewLiveClassUtilMsgList.add("Failed to enter message on teacher screen text field.");
				return result;
			}
			cfObj.commonClick(getBtnSendMessage());
		} catch (Exception e) {
			result = false;
			webViewLiveClassUtilMsgList.add("validateStudentDoubtMessage_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean createPoll(WebDriver webDriver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver,
					"//div[@class='icon_bg teacher_icon mx-2 poll  ']", "xpath", 10);
			if (!result) {
				webViewLiveClassUtilMsgList.add("Poll btn is not visible");
				return result;
			}

			// Click on poll btn
			cfObj.commonClick(
					cfObj.commonGetElement(webDriver, "//div[@class='icon_bg teacher_icon mx-2 poll  ']", "xpath"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//span[contains(text(),'Poll')]", "xpath",
					10);
			if (result) {
				cfObj.commonClick(cfObj.commonGetElement(webDriver, "//span[contains(text(),'Poll')]", "xpath"));

				System.out.println("1ST POLL STARTED");

				result = verifyAddPollWindow(webDriver);
				if (!result) {
					webViewLiveClassUtilMsgList.add("Add poll window verification failed");
					result = true;
				}

				// Type Poll question
				cfObj.commonSetTextTextBox(getTextFieldPoll().get(0), "This is a automated poll question");
				// Tyep poll first option and marking correct
				cfObj.commonSetTextTextBox(getTextFieldPoll().get(1), "Correct");
				Thread.sleep(1000);
				// Selecting first option as correct
				WebElement selection = cfObj.commonGetElement(webDriver, "//Select", "xpath");
				Select dropdown = new Select(selection);
				dropdown.selectByValue("true");

				// Add another option
				cfObj.commonClick(cfObj.commonGetElement(webDriver, ".addmoreoption", "css"));
				Thread.sleep(1000);
				cfObj.commonSetTextTextBox(getTextFieldPoll().get(2), "Wrong");

				Thread.sleep(1000);

				// Sending poll
				cfObj.commonClick(getBtnSubmitPoll());
			}
		} catch (Exception e) {
			webViewLiveClassUtilMsgList.add("Exception in createPoll: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyAddPollWindow(WebDriver webDriver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, ".ant-modal-header", "css", 10);
			if (!result) {
				webViewLiveClassUtilMsgList.add("Poll window is not present");
			}

			result = cfObj.commonWaitForElementToBeVisible(webDriver, getBtnSubmitPoll(), 10);
			if (!result) {
				webViewLiveClassUtilMsgList.add("Submit poll button is not present");
			}

			result = cfObj.commonWaitForElementToBeVisible(webDriver, getBtnCancelPoll(), 10);
			if (!result) {
				webViewLiveClassUtilMsgList.add("Cancel poll button is not present");
			}

		} catch (Exception e) {
			webViewLiveClassUtilMsgList.add("Exception in verifyAddPollWindow: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean closePollResponseScreenOnTeacherEnd(WebDriver webDriver) {
		boolean result = true;
		try {
			if (cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//div[@class='ant-modal-content']", "xpath",
					30)) {
				cfObj.commonClick(cfObj.commonGetElement(webDriver, ".ant-modal-close-x", "css"));
			} else {
				webViewLiveClassUtilMsgList.add("Poll Response screen is not visible.");
				System.out.println("Poll Response screen is not visible.");
			}

			result = cfObj.commonWaitForElementToBeVisible(webDriver, getBtnExitClass(), 10);
			if (!result) {
				webViewLiveClassUtilMsgList.add("End Class button is not visible.");
				return result;
			}
		} catch (Exception e) {
			webViewLiveClassUtilMsgList.add("Exception in closePollResponseScreen_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean uploadHandOut(WebDriver webDriver) {
		boolean result = true;
		try {

			// Click on handout button
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//div[@id='ele_stream_handout']", "xpath",
					10);
			if (result) {
				cfObj.commonClick(cfObj.commonGetElement(webDriver, "//div[@id='ele_stream_handout']", "xpath"));
			} else {
				webViewLiveClassUtilMsgList.add("Handout button is not visible on teacher");
				return result;
			}

			Thread.sleep(500);

			// Validate Handout window open
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver,
					"//div[@class='ant-upload ant-upload-drag']", "xpath", 10);
			if (!result) {
				webViewLiveClassUtilMsgList.add("The upload handout pop is not visible on teacher");
				return result;
			}

			result = cfObj.commonFileUploadWithEdgeCases("src/main/resources/small.pdf",
					cfObj.commonGetElement(webDriver, "//input[@accept='.pdf,.jpg, .jpeg,.png,.docx,.doc']", "xpath"));
			if (!result) {
				webViewLiveClassUtilMsgList.add("Not able to upLoad handout on teacher");
				return result;
			}

			cfObj.commonClick(webViewLiveClassUtilObj.getHandOutCommentField());

			// Write Handout title in Comment
			result = cfObj.commonSetTextTextBox(webViewLiveClassUtilObj.getHandOutCommentField(), "Handout Sample");
			if (!result) {
				webViewLiveClassUtilMsgList.add("Not able to enter HandOut Comment on teacher");
				return result;
			}

			// Click on Send button
			cfObj.commonClick(webViewLiveClassUtilObj.getHandOutSendBtn());

			Thread.sleep(5000);

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver,
					"//div[@class='ant-upload ant-upload-drag']", "xpath", 5);
			if (result) {
				webViewLiveClassUtilMsgList.add("Not able to Send handout on teacher, as popup still open.");
				return false;
			}

			// Validate handout title in chat
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, "//span[text()='Handout Sample']", "xpath",
					10);
			if (!result) {
				webViewLiveClassUtilMsgList.add("The handout title is not visible at teacher chat");
				return false;
			}

			// Validate Handout pdf
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, ".chat-upload-widget", "css", 10);
			if (!result) {
				webViewLiveClassUtilMsgList.add("Handout pdf is not visible at teacher");
				return false;
			}

		} catch (Exception e) {
			webViewLiveClassUtilMsgList.add("Exception in uploadHandOut_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnEnableChatButton(WebDriver webDriver) {
		boolean result = true;
		try {
			cfObj.commonClick(cfObj.commonGetElements(webDriver, "//button[@role='switch']", "xpath").get(0));

		} catch (Exception e) {
			webViewLiveClassUtilMsgList.add("Exception in clickOnEnableChatButton_Exception" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickOnPinMessage(WebDriver webDriver) {
		boolean result = true;
		try {

			cfObj.commonClick(getVerticalDots().get(0));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(webDriver, ".ant-dropdown-menu-title-content", "css",
					10);
			if (result) {
				cfObj.commonClick(getOptionPinMessage());
			}

			Thread.sleep(500);

		} catch (Exception e) {
			webViewLiveClassUtilMsgList.add("Exception in clickOnPinMessage" + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyStartBookmark(WebDriver driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, getBtnBookmark(), 10);
			if (!result) {
				webViewLiveClassUtilMsgList.add("Bookmark btn is not visible at teacher side");
				return result;
			}

			cfObj.commonClick(getBtnBookmark());

			result = cfObj.commonWaitForElementToBeVisible(driver, startBtnInBookmark(), 10);
			if (!result) {
				webViewLiveClassUtilMsgList.add("startBtnInBookmark is not visible at teacher side");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, bookmarksListBtn(), 10);
			if (!result) {
				webViewLiveClassUtilMsgList.add("bookmarksListBtn is not visible at teacher side");
				return result;
			}

			cfObj.commonClick(startBtnInBookmark());

			result = cfObj.commonWaitForElementToBeVisible(driver, stopBtnInBookmark(), 10);
			if (!result) {
				webViewLiveClassUtilMsgList.add("startBtnInBookmark is not visible at teacher side");
				return result;
			}
		} catch (Exception e) {
			result = false;
			webViewLiveClassUtilMsgList.add("verifyStartBookmark_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean verifyStopBookmark(WebDriver driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, stopBtnInBookmark(), 10);
			if (!result) {
				webViewLiveClassUtilMsgList.add("startBtnInBookmark is not visible at teacher side");
				return result;
			}

			cfObj.commonClick(stopBtnInBookmark());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//div[contains(text(),'Name this concept')]", "xpath", 10);
			if (!result) {
				webViewLiveClassUtilMsgList.add("Name this concept text is not visible after stopping bookmark");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//div[@class='ant-col ant-col-8']/span",
					"xpath", 10);
			if (!result) {
				webViewLiveClassUtilMsgList.add("Duration text is not visible after stopping bookmark");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//button[contains(.,'CANCEL')]", "xpath",
					10);
			if (!result) {
				webViewLiveClassUtilMsgList.add("Cancel btn is not visible after stopping bookmark");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//button[contains(.,'SAVE')]", "xpath",
					10);
			if (!result) {
				webViewLiveClassUtilMsgList.add("Save btn is not visible after stopping bookmark");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//input[contains(@class,'ant-input ant-input-lg')]", "xpath", 10);
			if (!result) {
				webViewLiveClassUtilMsgList.add("Edit box for naming bookmark is not visible after stopping bookmark");
				return result;
			}

			cfObj.commonClick(cfObj.commonGetElement(driver, "//button[contains(.,'SAVE')]", "xpath"));

			Thread.sleep(2000);

			result = cfObj.commonWaitForElementToBeVisible(driver, getBtnBookmark(), 10);
			if (!result) {
				webViewLiveClassUtilMsgList.add("Bookmark btn is not visible at teacher side");
				return result;
			}

			// Not verify list of bookmarks, as it admin link

		} catch (Exception e) {
			result = false;
			webViewLiveClassUtilMsgList.add("verifyStopBookmark_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean clickOnPrivateMode(WebDriver driver) {
		boolean result = true;
		try {
			cfObj.commonClick(cfObj.commonGetElements(webDriver, "//button[@role='switch']", "xpath").get(1));

		} catch (Exception e) {
			result = false;
			webViewLiveClassUtilMsgList.add("clickOnPrivateMode_Exception: " + e.getMessage());
		}
		return result;
	}
}