package applicationUtil;

import java.io.File;
import java.util.ArrayList;

import org.openqa.selenium.support.PageFactory;

import apiUtill.CreatePackageUtil;
import apiUtill.DoubtRoomApiUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.DoubtRoomPage_OR;
import util.Common_Function;
import util.ConfigFileReader;
import util.Common_Function.key;

public class DoubtRoomUtil {

	LoginUtil loginUtilObj;
	HomePageUtil homePageUtilObj;
	StorePageUtil storePageUtilObj;
	MyContentUtil myContentUtilObj;
	OrderSuccessUtil orderSuccessUtilObj;
	DoubtRoomApiUtil doubtRoomApiUtilObj;
	DoubtRoomPage_OR doubtRoomPageORObj;
	CreatePackageUtil createPackageUtil;
	PaymentUtil paymentUtilObj;
	PurchasePackageUtil purchasePackageUtilObj;
	UserDetailsLayerUtil userDetailsLayerUtilObj;
	public ArrayList<String> doubtRoomMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();

	String doubtRoomUrl = "https://newadminui-k8s.adda247.com/doubtroom";

	public DoubtRoomUtil(AppiumDriver<MobileElement> driver) {
		doubtRoomPageORObj = new DoubtRoomPage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), doubtRoomPageORObj);
	}

	public boolean verifyDoubtRoomUI(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		String packageTitle = null;
		int packageId;
		try {

			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
				packageTitle = "Batch Mahapack";
				packageId = 4871;
			} else {
				packageTitle = "Sankalp MahaPack with 3D Model order and Category!!!";
				packageId = 31523;
			}
			doubtRoomApiUtilObj = new DoubtRoomApiUtil();
			result = doubtRoomApiUtilObj.createDoubtRoomSlot(packageId);
			if (!result) {
				doubtRoomMsgList.add("Not able to create Doubt room slot.");
				return result;
			}

			loginUtilObj = new LoginUtil(driver);
			result = loginUtilObj.verifyLoginUsingEmailId(driver, "addaAutomation2900081865@gmail.com", "123456", false);
			if (!result) {
				doubtRoomMsgList.addAll(loginUtilObj.loginMsgList);
				return result;
			}

			homePageUtilObj = new HomePageUtil(driver);

			result = homePageUtilObj.clickMyContentButton(driver);
			if (!result) {
				doubtRoomMsgList.addAll(homePageUtilObj.homeMsgList);
				return result;
			}

			myContentUtilObj = new MyContentUtil(driver);

			result = myContentUtilObj.clickOnPurchasedBtn(driver);
			if (!result) {
				doubtRoomMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}

			result = myContentUtilObj.clickOnSpecificPurchasedPackage(driver, packageTitle);
			if (!result) {
				doubtRoomMsgList.addAll(myContentUtilObj.myContentMsgList);
				return result;
			}

			// Dealing with search course hint
			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "btn_done", "id", 5)) {
				cfObj.commonClick(cfObj.commonGetElement(driver, "btn_done", "id"));

			} else {
				System.out.println("Search icon hint in package page didn't appeared");
			}

			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")
					&& cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "applyFilter", "id", 10)) {
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "checkBox", "id", 10);
				if (!result) {
					doubtRoomMsgList.add("ExamName check box is not visible. ");
					return result;
				}

				cfObj.commonClick(cfObj.commonGetElements(driver, "checkBox", "id").get(0));
				Thread.sleep(1000);
				cfObj.commonClick(cfObj.commonGetElements(driver, "applyFilter", "id").get(0));
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "manageExamsTv", "id", 30);
				if (!result) {
					doubtRoomMsgList.add("Failed to click done button. ");
					return result;
				}
			}

			result = clickOnDoubtRoomBtn(driver);
			if (!result) {
				doubtRoomMsgList.add("Not able to click DoubtRoomBtn.");
				return result;
			}

			result = validateDoubtRoomEmptyStateUI(driver);
			if (!result) {
				doubtRoomMsgList.add("Not able to validate DoubtRoomEmptyStateUI.");
				return result;
			}

			// Wait for join button
			int loopCount = 0;
			while (loopCount < 5) {
				Thread.sleep(60000);
				loopCount++;
			}

			result = clickOnDoubtRoomBtn(driver);
			if (!result) {
				doubtRoomMsgList.add("Not able to click DoubtRoomBtn.");
				return result;
			}

			result = validateDoubtRoomUI(driver);
			if (!result) {
				doubtRoomMsgList.add("Not able to validate DoubtRoomUI.");
				return result;
			}

			result = clickOnJoinNowBtn(driver);
			if (!result) {
				doubtRoomMsgList.add("Not able to click JoinNowBtn.");
				return result;
			}

			result = handlePageLoading(driver, doubtRoomPageORObj.getProgressBar());
			if (!result) {
				doubtRoomMsgList.add("Not able to handle page loading.");
				return result;
			}

			result = validateWelComePopUpPage(driver);
			if (!result) {
				doubtRoomMsgList.add("Not able to validate WelComePopUpPage.");
				return result;
			}
			result = clickOnJoinBtn(driver);
			if (!result) {
				doubtRoomMsgList.add("Not able to click JoinBtn.");
				return result;
			}
			result = clickOnOnlyThisTimeBtn(driver);
			if (!result) {
				doubtRoomMsgList.add("Not able to click OnlyThisTimeBtn.");
				return result;
			}

			result = validateLiveDoubtRoomScreenUI(driver);
			if (!result) {
				doubtRoomMsgList.add("Not able to validate LiveDoubtRoomScreenUI.");
				return result;
			}
			result = enterMessageInMessageTextField(driver);
			if (!result) {
				doubtRoomMsgList.add("Not able to enter Message in MessageTextField.");
				return result;
			}

			result = clickOnRaiseHandBtn(driver);
			if (!result) {
				doubtRoomMsgList.add("Not able to click RaiseHandBtn.");
				return result;
			}
			result = handlePageLoading(driver, doubtRoomPageORObj.getExitTab());
			if (!result) {
				doubtRoomMsgList.add("Not able to handle page loading.");
				return result;
			}

			result = clickOnExitBtn(driver);
			if (!result) {
				doubtRoomMsgList.add("Not able to click ExitBtn.");
				return result;
			}

			if (cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='Were you able to ask your doubt?']",
					"xpath", 5)) {
				cfObj.commonClick(driver, "//*[@text='Skip']", "xpath");
			}

			result = handlePageLoading(driver, doubtRoomPageORObj.getJoinNowBtn());
			if (!result) {
				doubtRoomMsgList.add("Not able to handle page loading.");
				return result;
			}

			result = clickOnJoinNowBtn(driver);
			if (!result) {
				doubtRoomMsgList.add("Not able to click JoinNowBtn.");
				return result;
			}

			result = handlePageLoading(driver, doubtRoomPageORObj.getProgressBar());
			if (!result) {
				doubtRoomMsgList.add("Not able to handle page loading.");
				return result;
			}

			result = clickOnJoinBtn(driver);
			if (!result) {
				doubtRoomMsgList.add("Not able to click JoinBtn.");
				return result;
			}

			result = clickOnExitBtn(driver);
			if (!result) {
				doubtRoomMsgList.add("Not able to click ExitBtn.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("VerifyDoubtRoomUI_Exception" + e.getMessage());
		}

		return result;
	}

	public boolean selectAllCourseCheckBox(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getSelectAllCourseCheckBox(), 30);
			if (!result) {
				doubtRoomMsgList.add("AllCourse checkbox is not visible.");
				return result;
			}
			cfObj.commonClick(doubtRoomPageORObj.getSelectAllCourseCheckBox());
			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getApplyFilterBtn(), 30);
			if (!result) {
				doubtRoomMsgList.add("ApplyFilterBtn is not visible.");
				return result;
			}
			cfObj.commonClick(doubtRoomPageORObj.getApplyFilterBtn());
		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("SelectAllCourseCheckBox_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnDoneBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getDoneBtn(), 30);
			if (!result) {
				doubtRoomMsgList.add("DoneBtn is not visible.");
				return result;
			}
			cfObj.commonClick(doubtRoomPageORObj.getDoneBtn());
		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("ClickOnDoneBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validatePurchasePackageUI(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getPurchasedPackageTitle(), 30);
			if (!result) {
				doubtRoomMsgList.add("PurchasedPackageTitle is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getExamModeSwitchBtn(), 30);
			if (!result) {
				doubtRoomMsgList.add("ExamModeSwitchBtn is not visible.");
				return result;
			}
			cfObj.commonClick(doubtRoomPageORObj.getExamModeSwitchBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getDoubtRoomBtn(), 30);
			if (!result) {
				doubtRoomMsgList.add("DoubtRoomBtn is not visible.");
				return result;
			}

			result = validateManageExamBtn(driver);
			if (!result) {
				doubtRoomMsgList.add("Not able to validate ManageExamBtn.");
				return result;
			}

			result = validateDifferentCourseTab(driver);
			if (!result) {
				doubtRoomMsgList.add("Not able to validate DifferentCourseTab.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("ValidatePurchasePackageUI_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean validateManageExamBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getManageExamBtn(), 30);
			if (!result) {
				doubtRoomMsgList.add("ManageExamBtn is not visible.");
				return result;
			}
			cfObj.commonClick(doubtRoomPageORObj.getManageExamBtn());

			result = selectAllCourseCheckBox(driver);
			if (!result) {
				doubtRoomMsgList.add("Not able to select All Course CheckBox.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("ValidateManageExamBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateDifferentCourseTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			Thread.sleep(1000);
			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getCourseTabList().get(0), 30);
			if (!result) {
				doubtRoomMsgList.add("CourseTab is not visible.");
				return result;
			}
			int courseListCount = doubtRoomPageORObj.getCourseTabList().size();
			for (int i = 0; i < courseListCount; i++) {
				result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getCourseTabList().get(i),
						30);
				if (!result) {
					doubtRoomMsgList.add("CourseTab is not visible.");
					return result;
				}
				cfObj.commonClick(doubtRoomPageORObj.getCourseTabList().get(i));
				result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getDoubtRoomBtn(), 30);
				if (!result) {
					doubtRoomMsgList.add("DoubtRoomBtn is not visible.");
					return result;
				}
				cfObj.commonClick(doubtRoomPageORObj.getDoubtRoomBtn());
				result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getDoubtRoomPageTitle(), 30);
				if (!result) {
					doubtRoomMsgList.add("DoubtRoomPageTitle is not visible.");
					return result;
				}
				Thread.sleep(2000);
				cfObj.commonClick(doubtRoomPageORObj.getDoubtRoomBackBtn());
				Thread.sleep(2000);
			}

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("ValidateDifferentCourseTab_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean validateDoubtRoomUI(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getDoubtRoomPageTitle(), 30);
			if (!result) {
				doubtRoomMsgList.add("DoubtRoomPageTitle is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getLiveDoubtRoomTitle(), 30);
			if (!result) {
				doubtRoomMsgList.add("LiveDoubtRoomTitle is not visible.");
				return result;
			}

			if (!(cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getJoinNowBtn(), 10))) {

				result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getFacultyNotAvailableText(),
						30);
				if (!result) {
					doubtRoomMsgList.add("FacultyNotAvailableText is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getUpCommingDoubtRoomTitle(),
						30);
				if (!result) {
					doubtRoomMsgList.add("UpCommingDoubtRoomTitle is not visible.");
					return result;
				}
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getDoubtRoomTopicText().get(0),
					30);
			if (!result) {
				doubtRoomMsgList.add("DoubtRoomTopicText is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getDoubtRoomTimingText().get(0),
					30);
			if (!result) {
				doubtRoomMsgList.add("DoubtRoomTimingText is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getFacultNameText().get(0), 30);
			if (!result) {
				doubtRoomMsgList.add("FacultNameText is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("ValidateDoubtRoomUI_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean validateDoubtRoomEmptyStateUI(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getDoubtRoomPageTitle(), 30);
			if (!result) {
				doubtRoomMsgList.add("DoubtRoomPageTitle is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getFacultyNotAvailableText(), 30);
			if (!result) {
				doubtRoomMsgList.add("FacultyNotAvailableText is not visible.");
				return result;
			}

			cfObj.commonClick(doubtRoomPageORObj.getDoubtRoomBackBtn());
			Thread.sleep(2000);
			result = !cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getDoubtRoomPageTitle(), 30);
			if (!result) {
				doubtRoomMsgList.add("Not able to close DoubtRoom.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("validateDoubtRoomEmptyStateUI_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean clickOnJoinNowBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = (cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getJoinNowBtn(), 10));
			if (!result) {
				doubtRoomMsgList.add("Join now button is not visible.");
				return result;
			}
			cfObj.commonClick(doubtRoomPageORObj.getJoinNowBtn());
			Thread.sleep(1000);
			result = !cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getJoinNowBtn(), 3);
			if (!result) {
				doubtRoomMsgList.add("Failed to click Join now button.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("clickOnJoinNowBtn_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean clickOnDoubtRoomBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = (cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getDoubtRoomBtn(), 10));
			if (!result) {
				doubtRoomMsgList.add("DoubtRoomBtn is not visible.");
				return result;
			}
			cfObj.commonClick(doubtRoomPageORObj.getDoubtRoomBtn());

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("ClickOnDoubtRoomBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean handlePageLoading(AppiumDriver<MobileElement> driver, MobileElement element) {
		boolean result = true;
		try {
			int i = 0;
			while (cfObj.commonWaitForElementToBeVisible(driver, element, 30)) {
				Thread.sleep(3000);
				if (i > 5)
					break;
				i++;
			}

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("HandlePageLoading_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateWelComePopUpPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getWelcomePopUpProfessorName(),
					30);
			if (!result) {
				doubtRoomMsgList.add("WelcomePopUpProfessorName is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getWelcomePopUpTopicName(), 10);
			if (!result) {
				doubtRoomMsgList.add("WelcomePopUpTopicName is not visible");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getStartTimeText(), 10);
			if (!result) {
				doubtRoomMsgList.add("StartTimeText is not visible");
				return result;
			}

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("ValidateWelComePopUpPage_Exception" + e.getMessage());

		}
		return result;
	}

	public boolean clickOnJoinBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			Thread.sleep(1000);
			result = (cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getJoinBtn(), 10));
			if (!result) {
				doubtRoomMsgList.add("JoinBtn is not visible.");
				return result;
			}
			cfObj.commonClick(doubtRoomPageORObj.getJoinBtn());

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("ClickOnJoinBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnOnlyThisTimeBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			if (cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getOnlyThisTimeBtn(), 30)) {

				cfObj.commonClick(doubtRoomPageORObj.getOnlyThisTimeBtn());
			}
			if (cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getOkBtn(), 30)) {

				cfObj.commonClick(doubtRoomPageORObj.getOkBtn());
			}

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("ClickOnOnlyThisTimeBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateLiveDoubtRoomScreenUI(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = (cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getGroupChatStatusText(), 10));
			if (!result) {
				doubtRoomMsgList.add("GroupChatStatusText is not visible.");
				return result;
			}
			result = cfObj.commonVerifyValueTextBox(doubtRoomPageORObj.getGroupChatStatusText(),
					"Group chat is disabled");
			if (!result) {
				doubtRoomMsgList.add("GroupChatStatusText is not matching.");
				return result;
			}

			result = (cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getSendingToText(), 10));
			if (!result) {
				doubtRoomMsgList.add("SendingToText is not visible.");
				return result;
			}

			result = cfObj.commonVerifyValueTextBox(doubtRoomPageORObj.getSendingToText(),
					"Sending to the professor only");
			if (!result) {
				doubtRoomMsgList.add("SendingToText is not matching.");
				return result;
			}

			result = validateFullScreenBtn(driver);
			if (!result) {
				doubtRoomMsgList.add("Not able to validate FullScreenBtn.");
				return result;
			}

			result = validateDataSaverBtn(driver);
			if (!result) {
				doubtRoomMsgList.add("Not able to validate DataSaverBtn.");
				return result;
			}

			result = (cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getAudioTab(), 10));
			if (!result) {
				doubtRoomMsgList.add("AudioTab is not visible.");
				return result;
			}

			result = (cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getVideoTab(), 10));
			if (!result) {
				doubtRoomMsgList.add("VideoTab is not visible.");
				return result;
			}

			result = (cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getExitTab(), 10));
			if (!result) {
				doubtRoomMsgList.add("ExitTab is not visible.");
				return result;
			}

			result = (cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getRaiseHandTab(), 10));
			if (!result) {
				doubtRoomMsgList.add("RaiseHandTab is not visible.");
				return result;
			}

			result = validateThreeDotTab(driver);
			if (!result) {
				doubtRoomMsgList.add("Not able to validate ThreeDotTab.");
				return result;
			}

			result = (cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getMessageSentTextField(), 10));
			if (!result) {
				doubtRoomMsgList.add("MessageSentTextField is not visible.");
				return result;
			}

			result = (cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getMessageSendBtn(), 10));
			if (!result) {
				doubtRoomMsgList.add("MessageSendBtn is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("ValidateLiveDoubtRoomScreenUI_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateFullScreenBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = (cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getFullScreenBtn(), 10));
			if (!result) {
				doubtRoomMsgList.add("FullScreenBtn is not visible.");
				return result;
			}
			cfObj.commonClick(doubtRoomPageORObj.getFullScreenBtn());
			result = !(cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getGroupChatStatusText(), 10));
			if (!result) {
				doubtRoomMsgList.add("GroupChatStatusText is visible.");
				return result;
			}
			cfObj.commonClick(doubtRoomPageORObj.getFullScreenBtn());

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("ValidateFullScreenBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateDataSaverBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = (cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getDataSaverBtn(), 10));
			if (!result) {
				doubtRoomMsgList.add("DataSaverBtn is not visible.");
				return result;
			}
			cfObj.commonClick(doubtRoomPageORObj.getDataSaverBtn());
			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getDataSaverEnableText(), 10);
			if (!result) {
				doubtRoomMsgList.add("DataSaverEnableText is not visible.");
				return result;
			}
			cfObj.commonClick(doubtRoomPageORObj.getCancelBtn());

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("ValidateDataSaverBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateThreeDotTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = (cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getThreeDotTab(), 10));
			if (!result) {
				doubtRoomMsgList.add("ThreeDotTab is not visible.");
				return result;
			}
			cfObj.commonClick(doubtRoomPageORObj.getThreeDotTab());
			result = (cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getReportIssueTab(), 10));
			if (!result) {
				doubtRoomMsgList.add("ReportIssueTab is not visible.");
				return result;
			}
			cfObj.commonClick(doubtRoomPageORObj.getReportIssueTab());

			result = (cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getIssueDropDown(), 10));
			if (!result) {
				doubtRoomMsgList.add("IssueDropDown is not visible.");
				return result;
			}
			cfObj.commonClick(doubtRoomPageORObj.getIssueDropDown());

			int issueListCount = doubtRoomPageORObj.getIssueList().size();
			for (int i = 0; i < issueListCount; i++) {
				result = (cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getIssueList().get(i), 10));
				if (!result) {
					doubtRoomMsgList.add("IssueList is not visible.");
					return result;
				}
				String issueName = doubtRoomPageORObj.getIssueList().get(i).getText();
				if (issueName == null) {
					doubtRoomMsgList.add("Issue name text is null.");
					return false;
				}
				cfObj.commonClick(doubtRoomPageORObj.getIssueList().get(i));
				Thread.sleep(2000);
				result = cfObj.commonVerifyValueTextBox(doubtRoomPageORObj.getIssueDropDown(), issueName);
				if (!result) {
					doubtRoomMsgList.add("Selected issue text is not matching.");
					return result;
				}
				if (i < issueListCount - 1)
					cfObj.commonClick(doubtRoomPageORObj.getIssueDropDown());
				else
					cfObj.commonClick(doubtRoomPageORObj.getOkBtn());

			}

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("ValidateThreeDotTab_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean enterMessageInMessageTextField(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getMessageSentTextField(), 10);
			if (!result) {
				doubtRoomMsgList.add("Message text field is not visible.");
				return result;
			}
			result = cfObj.commonSetTextTextBox(doubtRoomPageORObj.getMessageSentTextField(),
					"Testing message from student.");
			if (!result) {
				doubtRoomMsgList.add("Not able to enter message in messageTextField.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getMessageSendBtn(), 10);
			if (!result) {
				doubtRoomMsgList.add("MessageSendBtn is not visible.");
				return result;
			}
			cfObj.commonClick(doubtRoomPageORObj.getMessageSendBtn());
			result = cfObj.commonWaitForElementToBeVisible(driver,
					doubtRoomPageORObj.getChatMessageText().get(doubtRoomPageORObj.getChatMessageText().size() - 1),
					10);
			if (!result) {
				doubtRoomMsgList.add("Chat Message text is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("EnterMessageInMessageTextField_Exception" + e.getMessage());

		}
		return result;
	}

	public boolean clickOnExitBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = (cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getExitTab(), 10));
			if (!result) {
				doubtRoomMsgList.add("ExitTab is not visible.");
				return result;
			}
			cfObj.commonClick(doubtRoomPageORObj.getExitTab());
			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getExitSessionText(), 10);
			if (!result) {
				doubtRoomMsgList.add("ExitSessionText is not visible.");
				return result;
			}
			cfObj.commonClick(doubtRoomPageORObj.getOkBtn());

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("ClickOnExitBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyPushAction(AppiumDriver<MobileElement> driver, String sourcePath, String remotePath) {
		boolean result = true;
		try {
			File file = new File(sourcePath);
			String absoluteSourcePath = file.getAbsolutePath();
			if (absoluteSourcePath == null) {
				doubtRoomMsgList.add("Absolute source path is null.");
				return false;
			}
			System.out.println(absoluteSourcePath);
			result = cfObj.pushFileToEmulator(driver, absoluteSourcePath, remotePath);
			if (!result) {
				doubtRoomMsgList.add("Not able to push the file to emulator.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("verifyPushAction_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnLanguageChangeSheetCloseBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			if (cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getLanguageChangeSheetCloseBtn(),
					10)) {
				result = cfObj.commonWaitForElementToBeVisible(driver,
						doubtRoomPageORObj.getLanguageChangeSheetCloseBtn(), 10);
				if (!result) {
					doubtRoomMsgList.add("LanguageChangeSheetClose button is not visible.");
					return result;
				}
				cfObj.commonClick(doubtRoomPageORObj.getLanguageChangeSheetCloseBtn());
			}

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("LanguageChangeSheetCloseBtn_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean enterAdminScreenURL(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getChromeSearchTextField(), 10);
			if (!result) {
				doubtRoomMsgList.add("ChromeSearchTextField is not visible.");
				return result;
			}
			cfObj.commonClick(doubtRoomPageORObj.getChromeSearchTextField());
			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getUrlBarSearchField(), 10);
			if (!result) {
				doubtRoomMsgList.add("UrlBarSearchField is not visible.");
				return result;
			}
			result = cfObj.commonSetTextTextBox(doubtRoomPageORObj.getUrlBarSearchField(), doubtRoomUrl);
			if (!result) {
				doubtRoomMsgList.add("Not able to enter message in messageSendTextField.");
				return result;
			}
			result = cfObj.pressAndroidKey(driver, key.ENTER, 1);
			if (!result) {
				doubtRoomMsgList.add("Not able to press ENTER button.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("EnterAdminScreenURL_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean enterDoubtRoomURL(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getUrlBarSearchField(), 10);
			if (!result) {
				doubtRoomMsgList.add("UrlBarSearchField is not visible.");
				return result;
			}
			result = cfObj.commonSetTextTextBox(doubtRoomPageORObj.getUrlBarSearchField(), doubtRoomUrl);
			if (!result) {
				doubtRoomMsgList.add("Not able to enter message in messageSendTextField.");
				return result;
			}
			result = cfObj.pressAndroidKey(driver, key.ENTER, 1);
			if (!result) {
				doubtRoomMsgList.add("Not able to press ENTER button.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("EnterDoubtRoomURL_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean navigateToChromeSearchBar(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			if (cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getTermAcceptBtn(), 10)) {

				cfObj.commonClick(doubtRoomPageORObj.getTermAcceptBtn());

				result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getNoThanksBtn(), 10);
				if (!result) {
					doubtRoomMsgList.add("NoThanksBtn is not visible.");
					return result;
				}
				cfObj.commonClick(doubtRoomPageORObj.getNoThanksBtn());
			}

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("NavigateToChromeSearchBar_Exception" + e.getMessage());
		}
		return result;

	}

	public boolean loginToAdminAccount(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getEmailTextField(), 10);
			if (!result) {
				doubtRoomMsgList.add("Email text field is not visible.");
				return result;
			}

			doubtRoomPageORObj.getEmailTextField().sendKeys("lohith.rm@adda247.com");
			Thread.sleep(2000);

			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getPasswordTextField(), 10);
			if (!result) {
				doubtRoomMsgList.add("Password text field is not visible.");
				return result;
			}

			doubtRoomPageORObj.getPasswordTextField().sendKeys("123456789");

			Thread.sleep(2000);
			cfObj.commonClick(doubtRoomPageORObj.getLoginBtn());

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("LoginToAdminAccount_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnTeacherJoinBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getTeacherJoinBtn(), 10);
			if (!result) {
				doubtRoomMsgList.add("Teacher join btn is not visible.");
				return result;
			}
			cfObj.commonClick(doubtRoomPageORObj.getTeacherJoinBtn());

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("ClickOnTeacherJoinBtn_Exception" + e.getMessage());

		}
		return result;
	}

	public boolean clickOnStartNowBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getStartNowBtn(), 30);
			if (!result) {
				doubtRoomMsgList.add("StartNow btn is not visible.");
				return result;
			}
			cfObj.commonClick(doubtRoomPageORObj.getStartNowBtn());

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("ClickOnStartNowBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean handleDoubtRoomPopUp(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			if (cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getAudioAllowBtn(), 10)) {
				cfObj.commonClick(doubtRoomPageORObj.getAudioAllowBtn());
			}

			if (cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getOnlyThisTimeBtn(), 10)) {
				cfObj.commonClick(doubtRoomPageORObj.getOnlyThisTimeBtn());
			}

			if (cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getOkBtn(), 10)) {
				cfObj.commonClick(doubtRoomPageORObj.getOkBtn());
			}

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("HandleDoubtRoomPopUp_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnGroupChatEnableCheckBox(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getGroupChatDisableBtn(), 30);
			if (!result) {
				doubtRoomMsgList.add("GroupChatDisable btn is not visible.");
				return result;
			}
			cfObj.commonClick(doubtRoomPageORObj.getGroupChatDisableBtn());

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("ClickOnGroupChatEnableCheckBox_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean enterMessageInTeacherMessageTextField(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getChatMessageText().get(0), 10);
			if (!result) {
				doubtRoomMsgList.add("Chat Message text is not visible.");
				return result;
			}
			int chatMessageCount = doubtRoomPageORObj.getChatMessageText().size();
			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getTeacherSideMessageTextField(),
					10);
			if (!result) {
				doubtRoomMsgList.add("TeacherSideMessageTextField is not visible.");
				return result;
			}
			result = cfObj.commonSetTextTextBox(doubtRoomPageORObj.getTeacherSideMessageTextField(),
					"Testing message from Teacher.");
			if (!result) {
				doubtRoomMsgList.add("Not able to enter message in TeachermessageTextField.");
				return result;
			}
			Thread.sleep(2000);
			cfObj.commonClick(doubtRoomPageORObj.getTeacherSideMessageSendBtn());
			Thread.sleep(2000);
			result = cfObj.commonWaitForElementToBeVisible(driver,
					doubtRoomPageORObj.getChatMessageText().get(doubtRoomPageORObj.getChatMessageText().size() - 1),
					10);
			if (!result) {
				doubtRoomMsgList.add("Chat Message text is not visible.");
				return result;
			}
			result = chatMessageCount + 1 == doubtRoomPageORObj.getChatMessageText().size();
			if (!result) {
				doubtRoomMsgList.add("Teacher is not able to send message.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("EnterMessageInTeacherMessageTextField_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnWhiteBoardTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getWhiteBoardTab(), 30);
			if (!result) {
				doubtRoomMsgList.add("WhiteBoardTab is not visible.");
				return result;
			}
			cfObj.commonClick(doubtRoomPageORObj.getWhiteBoardTab());

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("ClickOnWhiteBoardTab_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnTeacherThreeDotTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getTeacherSideThreeDotTab(), 30);
			if (!result) {
				doubtRoomMsgList.add("TeacherSideThreeDotTab is not visible.");
				return result;
			}
			cfObj.commonClick(doubtRoomPageORObj.getTeacherSideThreeDotTab());

			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getTrueFalsePollTab(), 30);
			if (!result) {
				doubtRoomMsgList.add("TrueFalsePollTab is not visible.");
				return result;
			}
			cfObj.commonClick(doubtRoomPageORObj.getTrueFalsePollTab());

			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getPollCreateBtn(), 30);
			if (!result) {
				doubtRoomMsgList.add("PollCreateBtn is not visible.");
				return result;
			}
			cfObj.commonClick(doubtRoomPageORObj.getPollCreateBtn());

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("ClickOnTeacherThreeDotTab_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnPollOption(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getPollOptionRadioBtn().get(0),
					30);
			if (!result) {
				doubtRoomMsgList.add("PollOptionRadioBtn is not visible.");
				return result;
			}
			cfObj.commonClick(doubtRoomPageORObj.getPollOptionRadioBtn().get(0));

			if (cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getPollSubmitBtn(), 30)) {
				cfObj.commonClick(doubtRoomPageORObj.getPollSubmitBtn());
			}

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("ClickOnWhiteBoardTab_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateTeacherMessage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver,
					doubtRoomPageORObj.getFacultySentMessageText().get(0), 30);
			if (!result) {
				doubtRoomMsgList.add("FacultySentMessageText is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("ClickOnWhiteBoardTab_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean closeChromeTabs(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getTabSwitchBtn(), 30);
			if (!result) {
				doubtRoomMsgList.add("TabSwitchBtn is not visible.");
				return result;
			}
			cfObj.commonClick(doubtRoomPageORObj.getTabSwitchBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getChromeMoreOption(), 30);
			if (!result) {
				doubtRoomMsgList.add("ChromeMoreOption is not visible.");
				return result;
			}
			cfObj.commonClick(doubtRoomPageORObj.getChromeMoreOption());
			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getCloseAllTabs(), 30);
			if (!result) {
				doubtRoomMsgList.add("CloseAllTabs is not visible.");
				return result;
			}
			cfObj.commonClick(doubtRoomPageORObj.getCloseAllTabs());

			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getNewTab(), 30);
			if (!result) {
				doubtRoomMsgList.add("NewTab is not visible.");
				return result;
			}
			cfObj.commonClick(doubtRoomPageORObj.getNewTab());

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("CloseChromeTabs_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnRaiseHandBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, doubtRoomPageORObj.getRaiseHandTab(), 30);
			if (!result) {
				doubtRoomMsgList.add("RaiseHandTab is not visible.");
				return result;
			}
			cfObj.commonClick(doubtRoomPageORObj.getRaiseHandTab());

		} catch (Exception e) {
			result = false;
			doubtRoomMsgList.add("ClickOnRaiseHandBtn_Exception" + e.getMessage());
		}
		return result;
	}

}
