package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.CommonStudyMaterialSection_OR;
import util.Common_Function;
import util.Common_Function.key;
import util.ConfigFileReader;

public class CommonStudyMaterialSectionUtil {

	CommonStudyMaterialSection_OR commonStudyMaterialSectionORObj;
	CommentPageUtil commentPageUtilObj;
	FilterPageUtil filterPageUtilObj;
	HomePageUtil homePageUtilObj;
	public ArrayList<String> commonStudyMaterialMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();
	private String[] fontsizeList = { "Small", "Medium (Default)", "Large", "Extra Large" };

	public CommonStudyMaterialSectionUtil(AppiumDriver<MobileElement> driver) {
		commonStudyMaterialSectionORObj = new CommonStudyMaterialSection_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), commonStudyMaterialSectionORObj);
	}

	public boolean verifyCurrentAffairPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver,
					commonStudyMaterialSectionORObj.getCurrentAffairPageTitle(), 30);
			if (!result) {
				commonStudyMaterialMsgList.add("Current affair page title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonStudyMaterialSectionORObj.getFilterBtn(), 30);
			if (!result) {
				commonStudyMaterialMsgList.add("Filter button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.TextView[contains(@text,'No result found')]", "xpath", 5);
			if (result) {
				System.out.println("No Content in Notes & Articles");
				return true;
			} else {
				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonStudyMaterialSectionORObj.getStudyMaterialTitleList().get(0), 30);
				if (!result) {
					commonStudyMaterialMsgList.add("Study material list is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonStudyMaterialSectionORObj.getStudyMaterialImgList().get(0), 30);
				if (!result) {
					commonStudyMaterialMsgList.add("Study material image is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonStudyMaterialSectionORObj.getStudyMaterialNameList().get(0), 30);
				if (!result) {
					commonStudyMaterialMsgList.add("Study material name is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonStudyMaterialSectionORObj.getStudyMaterialDescriptionList().get(0), 30);
				if (!result) {
					commonStudyMaterialMsgList.add("Study material description is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonStudyMaterialSectionORObj.getAudioIconList().get(0), 30);
				if (!result) {
					commonStudyMaterialMsgList.add("Audio icon is not visible.");
					return result;
				}
			}

		} catch (Exception e) {
			result = false;
			commonStudyMaterialMsgList.add("CurrentAffairPage_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyJobAlertPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			Thread.sleep(2000);

			if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"), 5)) {
				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"));
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,
					commonStudyMaterialSectionORObj.getJobAlertPageTitle(), 15);
			if (!result) {
				driver.navigate().back();

				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonStudyMaterialSectionORObj.getJobAlertPageTitle(), 30);
				if (!result) {
					commonStudyMaterialMsgList.add("Job alerts page title is not visible.");
					return result;
				}
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonStudyMaterialSectionORObj.getFilterBtn(), 30);
			if (!result) {
				commonStudyMaterialMsgList.add("Filter button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.TextView[contains(@text,'No result found')]", "xpath", 5);
			if (result) {
				System.out.println("No Content in Job Alerts");
				return true;
			} else {

				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonStudyMaterialSectionORObj.getStudyMaterialTitleList().get(0), 30);
				if (!result) {
					commonStudyMaterialMsgList.add("Study material list is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonStudyMaterialSectionORObj.getStudyMaterialImgList().get(0), 30);
				if (!result) {
					commonStudyMaterialMsgList.add("Study material image is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonStudyMaterialSectionORObj.getStudyMaterialNameList().get(0), 30);
				if (!result) {
					commonStudyMaterialMsgList.add("Study material name is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonStudyMaterialSectionORObj.getStudyMaterialDescriptionList().get(0), 30);
				if (!result) {
					commonStudyMaterialMsgList.add("Study material description is not visible.");
					return result;
				}
			}

		} catch (Exception e) {
			result = false;
			commonStudyMaterialMsgList.add("JobAlertsPage_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyNotesAndArticlesPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			if (ConfigFileReader.strEnv.equalsIgnoreCase("PRODUCTION")
					&& ConfigFileReader.strApplication.equalsIgnoreCase("Sankalp")) {

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
						"//*[contains(@text,'Notifications & Updates')]", "xpath", 10);
			} else {
				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonStudyMaterialSectionORObj.getNotesAndArticlePageTitle(), 30);
			}
			if (!result) {
				commonStudyMaterialMsgList.add("NotesAndArticles page title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonStudyMaterialSectionORObj.getFilterBtn(), 30);
			if (!result) {
				commonStudyMaterialMsgList.add("Filter button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.TextView[contains(@text,'No result found')]", "xpath", 5);
			if (result) {
				System.out.println("No Content in Notes & Articles");
				return true;
			} else {

				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonStudyMaterialSectionORObj.getStudyMaterialTitleList().get(0), 30);
				if (!result) {
					commonStudyMaterialMsgList.add("Study material list is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonStudyMaterialSectionORObj.getStudyMaterialImgList().get(0), 30);
				if (!result) {
					commonStudyMaterialMsgList.add("Study material image is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonStudyMaterialSectionORObj.getStudyMaterialNameList().get(0), 30);
				if (!result) {
					commonStudyMaterialMsgList.add("Study material name is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonStudyMaterialSectionORObj.getStudyMaterialDescriptionList().get(0), 30);
				if (!result) {
					commonStudyMaterialMsgList.add("Study material description is not visible.");
					return result;
				}
			}

		} catch (Exception e) {
			result = false;
			commonStudyMaterialMsgList.add("NotesAndArticlesPage_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean verifyPowerCapsulesPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			if (cfObj.commonWaitForElementToBeVisible(driver,
					commonStudyMaterialSectionORObj.getFilterInstructionText(), 10)) {

				if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"), 5)) {
					cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"));
				}
			}
			result = cfObj.commonWaitForElementToBeVisible(driver,
					commonStudyMaterialSectionORObj.getPowerCapsulesPageTitle(), 30);
			if (!result) {
				commonStudyMaterialMsgList.add("PowerCapsules page title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonStudyMaterialSectionORObj.getFilterBtn(), 30);
			if (!result) {
				commonStudyMaterialMsgList.add("Filter button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver,
					"//android.widget.TextView[contains(@text,'No result found')]", "xpath", 5);
			if (result) {
				System.out.println("No Content in Power Capsules");
				return true;
			} else {
				if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
					result = cfObj.commonWaitForElementToBeVisible(driver,
							commonStudyMaterialSectionORObj.getStudyMaterialImgList().get(0), 30);
					if (!result) {
						commonStudyMaterialMsgList.add("Study material image is not visible.");
						return result;
					}

					result = cfObj.commonWaitForElementToBeVisible(driver,
							commonStudyMaterialSectionORObj.getStudyMaterialNameList().get(0), 30);
					if (!result) {
						commonStudyMaterialMsgList.add("Study material name is not visible.");
						return result;
					}

					result = cfObj.commonWaitForElementToBeVisible(driver,
							commonStudyMaterialSectionORObj.getStudyMaterialDescriptionList().get(0), 30);
					if (!result) {
						commonStudyMaterialMsgList.add("Study material description is not visible.");
						return result;
					}
				}
			}

		} catch (Exception e) {
			result = false;
			commonStudyMaterialMsgList.add("PowerCapsulesPage_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateFilterBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			if (!(cfObj.commonWaitForElementToBeVisible(driver, commonStudyMaterialSectionORObj.getIsReadIndicator(),
					10))) {
				cfObj.commonClick(commonStudyMaterialSectionORObj.getStudyMaterialImgList().get(0));
				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonStudyMaterialSectionORObj.getCommentTextField(), 20);
				if (!result) {
					commonStudyMaterialMsgList.add("Comment text field is not visible.");
					return result;
				}
				cfObj.commonClick(commonStudyMaterialSectionORObj.getBackBtn());
				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonStudyMaterialSectionORObj.getIsReadIndicator(), 20);
				if (!result) {
					commonStudyMaterialMsgList.add("IsReadIndicator is not visible.");
					return result;
				}
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonStudyMaterialSectionORObj.getFilterBtn(), 30);
			if (!result) {
				commonStudyMaterialMsgList.add("Filter button is not visible.");
				return result;
			}
			cfObj.commonClick(commonStudyMaterialSectionORObj.getFilterBtn());

			filterPageUtilObj = new FilterPageUtil(driver);
			result = filterPageUtilObj.verifyFilterPageUI(driver);
			if (!result) {
				commonStudyMaterialMsgList.addAll(filterPageUtilObj.filterPageMsgList);
				return result;
			}
			result = filterPageUtilObj.clickOnStatusTab(driver, filterPageUtilObj.filterPageORObj.getUnReadBtn(),
					"selected");
			if (!result) {
				commonStudyMaterialMsgList.addAll(filterPageUtilObj.filterPageMsgList);
				return result;
			}
			result = filterPageUtilObj.clickOnApplyBtn(driver);
			if (!result) {
				commonStudyMaterialMsgList.addAll(filterPageUtilObj.filterPageMsgList);
				return result;
			}
			Thread.sleep(2000);
			result = !(cfObj.commonWaitForElementToBeVisible(driver,
					commonStudyMaterialSectionORObj.getIsReadIndicator(), 30));
			if (!result) {
				commonStudyMaterialMsgList.add("IsReadIndicator is visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			commonStudyMaterialMsgList.add("FilterBtn_Exception" + e.getMessage());

		}
		return result;

	}

	public boolean validatePowerCapsuleFilterBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			if (!(cfObj.commonWaitForElementToBeVisible(driver, commonStudyMaterialSectionORObj.getIsReadIndicator(),
					30))) {
				// cfObj.commonClick(commonStudyMaterialSectionORObj.getStudyMaterialImgList().get(1));
				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='testing capsules october']", "xpath"));
				int i = 0;
				while (cfObj.commonWaitForElementToBeVisible(driver,
						commonStudyMaterialSectionORObj.getProgressBarIcon(), 20)) {
					Thread.sleep(2000);
					if (i > 5) {
						commonStudyMaterialMsgList.add("Not able to load PowerCapsule page.");
						System.out.println("Not able to load PowerCapsule page.");
						break;
					}
				}
				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonStudyMaterialSectionORObj.getPowerCapsuleShareBtn(), 20);
				if (!result) {
					commonStudyMaterialMsgList.add("PowerCapsuleShare button is not visible.");
					return result;
				}
				Thread.sleep(2000);
				cfObj.commonClick(commonStudyMaterialSectionORObj.getBackBtn());
				result = cfObj.commonWaitForElementToBeVisible(driver,
						commonStudyMaterialSectionORObj.getIsReadIndicator(), 20);
				if (!result) {
					commonStudyMaterialMsgList.add("IsReadIndicator is not visible.");
					return result;
				}
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonStudyMaterialSectionORObj.getFilterBtn(), 30);
			if (!result) {
				commonStudyMaterialMsgList.add("Filter button is not visible.");
				return result;
			}
			cfObj.commonClick(commonStudyMaterialSectionORObj.getFilterBtn());
			filterPageUtilObj = new FilterPageUtil(driver);
			result = filterPageUtilObj.verifyFilterPageUI(driver);
			if (!result) {
				commonStudyMaterialMsgList.addAll(filterPageUtilObj.filterPageMsgList);
				return result;
			}
			result = filterPageUtilObj.clickOnStatusTab(driver, filterPageUtilObj.filterPageORObj.getUnReadBtn(),
					"selected");
			if (!result) {
				commonStudyMaterialMsgList.addAll(filterPageUtilObj.filterPageMsgList);
				return result;
			}
			result = filterPageUtilObj.clickOnApplyBtn(driver);
			if (!result) {
				commonStudyMaterialMsgList.addAll(filterPageUtilObj.filterPageMsgList);
				return result;
			}
			Thread.sleep(2000);
			result = !(cfObj.commonWaitForElementToBeVisible(driver,
					commonStudyMaterialSectionORObj.getIsReadIndicator(), 30));
			if (!result) {
				commonStudyMaterialMsgList.add("IsReadIndicator is visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			commonStudyMaterialMsgList.add("FilterBtn_Exception" + e.getMessage());

		}
		return result;

	}

	public boolean clickOnCurrentAffairStudyMaterialAndValidate(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver,
					commonStudyMaterialSectionORObj.getStudyMaterialImgList().get(0), 10);
			if (!result) {
				commonStudyMaterialMsgList.add("Study material image is not visible.");
				return result;
			}
			int randomNum = Common_Function.RandomNumber(0,
					commonStudyMaterialSectionORObj.getStudyMaterialImgList().size() - 1);
			cfObj.commonClick(commonStudyMaterialSectionORObj.getStudyMaterialImgList().get(randomNum));

			result = validateAudioBtn(driver);
			if (!result) {
				commonStudyMaterialMsgList.add("not able to validate Audio button.");
				return result;
			}

			result = validateBookMarkBtn(driver);
			if (!result) {
				commonStudyMaterialMsgList.add("not able to validate Book mark icon");
				return result;
			}

			result = validateDotIcon(driver);
			if (!result) {
				commonStudyMaterialMsgList.add("not able to validate Dot icon");
				return result;
			}

			result = validateLikeBtn(driver);
			if (!result) {
				commonStudyMaterialMsgList.add("not able to validate Like button.");
				return result;
			}
			if (ConfigFileReader.strEnv.equalsIgnoreCase("STAGING")) {
				result = validateCommentTextField(driver);
				if (!result) {
					commonStudyMaterialMsgList.add("not able to validate CommentTextField.");
					return result;
				}
			}

			result = validateShareBtn(driver);
			if (!result) {
				commonStudyMaterialMsgList.add("not able to validate Share button.");
				return result;
			}
			result = navigateToHomePage(driver);
			if (!result) {
				commonStudyMaterialMsgList.add("not able to navigate home page.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			commonStudyMaterialMsgList.add("CurrentAffairStudyMaterialAndValidate_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnStudyMaterialAndValidate(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver,
					commonStudyMaterialSectionORObj.getStudyMaterialImgList().get(0), 10);
			if (!result) {
				commonStudyMaterialMsgList.add("Study material image is not visible.");
				return result;
			}

			int randomNum = Common_Function.RandomNumber(0,
					commonStudyMaterialSectionORObj.getStudyMaterialImgList().size() - 1);
			cfObj.commonClick(commonStudyMaterialSectionORObj.getStudyMaterialImgList().get(randomNum));

			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
				result = validateBookMarkBtn(driver);
				if (!result) {
					commonStudyMaterialMsgList.add("not able to validate Book mark icon");
					return result;
				}
			}

			result = validateDotIcon(driver);
			if (!result) {
				commonStudyMaterialMsgList.add("not able to validate Dot icon");
				return result;
			}

			result = validateLikeBtn(driver);
			if (!result) {
				commonStudyMaterialMsgList.add("not able to validate Like button.");
				return result;
			}
			if (ConfigFileReader.strEnv.equalsIgnoreCase("STAGING")) {
				result = validateCommentTextField(driver);
				if (!result) {
					commonStudyMaterialMsgList.add("not able to validate CommentTextField.");
					return result;
				}
			}
			result = validateShareBtn(driver);
			if (!result) {
				commonStudyMaterialMsgList.add("not able to validate Share button.");
				return result;
			}
			result = navigateToHomePage(driver);
			if (!result) {
				commonStudyMaterialMsgList.add("not able to navigate home page.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			commonStudyMaterialMsgList.add("JobAlertsStudyMaterialAndValidate_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateBookMarkBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, commonStudyMaterialSectionORObj.getBookMarkIcon(),
					30);
			if (!result) {
				commonStudyMaterialMsgList.add("Book mark icon is not visible.");
				return result;
			}
			cfObj.commonClick(commonStudyMaterialSectionORObj.getBookMarkIcon());

		} catch (Exception e) {
			result = false;
			commonStudyMaterialMsgList.add("validateBookMarkBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateAudioBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			Thread.sleep(3000);
			result = cfObj.commonWaitForElementToBeVisible(driver, commonStudyMaterialSectionORObj.getAuidoIcon(), 30);
			if (!result) {
				commonStudyMaterialMsgList.add("Audio icon is not visible.");
				return result;
			}
			if (commonStudyMaterialSectionORObj.getAuidoIcon().getAttribute("selected").equals("true")) {
				cfObj.commonClick(commonStudyMaterialSectionORObj.getAuidoIcon());
				Thread.sleep(1000);
				result = commonStudyMaterialSectionORObj.getAuidoIcon().getAttribute("selected").equals("false");
				if (!result) {
					commonStudyMaterialMsgList.add("not able to click on audio button.");
					return result;
				}
			} else {
				cfObj.commonClick(commonStudyMaterialSectionORObj.getAuidoIcon());
				Thread.sleep(1000);
				result = commonStudyMaterialSectionORObj.getAuidoIcon().getAttribute("selected").equals("true");
				if (!result) {
					commonStudyMaterialMsgList.add("not able to click on audio button.");
					return result;
				}
			}
		} catch (Exception e) {
			result = false;
			commonStudyMaterialMsgList.add("validateAudioBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateDotIcon(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			Thread.sleep(2000);
			result = cfObj.commonWaitForElementToBeVisible(driver, commonStudyMaterialSectionORObj.getDotIcon(), 30);
			if (!result) {
				commonStudyMaterialMsgList.add("Dot icon is not visible.");
				return result;
			}
			cfObj.commonClick(commonStudyMaterialSectionORObj.getDotIcon());
			result = cfObj.commonWaitForElementToBeVisible(driver, commonStudyMaterialSectionORObj.getFontSizeBtn(),
					30);
			if (!result) {
				commonStudyMaterialMsgList.add("FontSize button is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,
					commonStudyMaterialSectionORObj.getReportContentBtn(), 30);
			if (!result) {
				commonStudyMaterialMsgList.add("ReportContent button is not visible.");
				return result;
			}

			cfObj.commonClick(commonStudyMaterialSectionORObj.getFontSizeBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, commonStudyMaterialSectionORObj.getFontSizeSaveBtn(),
					30);
			if (!result) {
				commonStudyMaterialMsgList.add("FontSize save button is not visible.");
				return result;
			}
			for (int i = 0; i < commonStudyMaterialSectionORObj.getFontSizeList().size(); i++) {
				if (commonStudyMaterialSectionORObj.getFontSizeList().get(i).getAttribute("checked").equals("false")) {
					result = cfObj.commonVerifyValueTextBox(commonStudyMaterialSectionORObj.getFontSizeList().get(i),
							fontsizeList[i]);
					if (!result) {
						commonStudyMaterialMsgList.add("FontSize Text is not matching.");
						return result;
					}
					cfObj.commonClick(commonStudyMaterialSectionORObj.getFontSizeList().get(i));
					result = commonStudyMaterialSectionORObj.getFontSizeList().get(i).getAttribute("checked")
							.equals("true");
					if (!result) {
						commonStudyMaterialMsgList.add("Not able to click Font size radion button.");
						return result;
					}
				} else {
					if (commonStudyMaterialSectionORObj.getFontSizeList().get(i).getAttribute("checked")
							.equals("true")) {
						result = cfObj.commonVerifyValueTextBox(
								commonStudyMaterialSectionORObj.getFontSizeList().get(i), fontsizeList[i]);
						if (!result) {
							commonStudyMaterialMsgList.add("FontSize Text is not matching.");
							return result;
						}
						cfObj.commonClick(commonStudyMaterialSectionORObj.getFontSizeList().get(i));
						result = commonStudyMaterialSectionORObj.getFontSizeList().get(i).getAttribute("checked")
								.equals("false");
						if (!result) {
							commonStudyMaterialMsgList.add("Not able to click Font size radion button.");
							return result;
						}
					}

				}
			}
			cfObj.commonClick(commonStudyMaterialSectionORObj.getFontSizeCancelBtn());
			result = cfObj.commonWaitForElementToBeVisible(driver, commonStudyMaterialSectionORObj.getDotIcon(), 30);
			if (!result) {
				commonStudyMaterialMsgList.add("Dot icon is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			commonStudyMaterialMsgList.add("validateDotIcon_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateLikeBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver,
					commonStudyMaterialSectionORObj.getLikeCommentCountText(), 10);
			if (!result) {
				commonStudyMaterialMsgList.add("Like comment count text is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commonStudyMaterialSectionORObj.getLikeBtn(), 10);
			if (!result) {
				commonStudyMaterialMsgList.add("Like button is not visible.");
				return result;
			}

			cfObj.commonClick(commonStudyMaterialSectionORObj.getLikeBtn());
//			int oldLikeCount = 0;
//			if (!(commonStudyMaterialSectionORObj.getLikeCommentCountText().getText()
//					.equals("Be the first one to comment"))) {
//				if (ConfigFileReader.isTablet.equalsIgnoreCase("False")) {
//					oldLikeCount = Integer.parseInt(
//							commonStudyMaterialSectionORObj.getLikeCommentCountText().getText().split(" ")[0]);
//					cfObj.commonClick(commonStudyMaterialSectionORObj.getLikeBtn());
//					Thread.sleep(1000);
//					int newLikeCount = Integer.parseInt(
//							commonStudyMaterialSectionORObj.getLikeCommentCountText().getText().split(" ")[0]);
//					result = (oldLikeCount + 1 == newLikeCount);
//				} else {
//					oldLikeCount = Integer.parseInt(cfObj.commonGetElement(driver, "like_count", "id").getText());
//					cfObj.commonClick(commonStudyMaterialSectionORObj.getLikeBtn());
//					Thread.sleep(1000);
//					int newLikeCount = Integer.parseInt(cfObj.commonGetElement(driver, "like_count", "id").getText());
//					result = (oldLikeCount + 1 == newLikeCount);
//				}
//			} else {
//				cfObj.commonClick(commonStudyMaterialSectionORObj.getLikeBtn());
//				Thread.sleep(1000);
//				int newLikeCount = 0;
//				if (ConfigFileReader.isTablet.equalsIgnoreCase("False")) {
//					newLikeCount = Integer.parseInt(
//							commonStudyMaterialSectionORObj.getLikeCommentCountText().getText().split(" ")[0]);
//				} else {
//					newLikeCount = Integer.parseInt(
//							commonStudyMaterialSectionORObj.getLikeCommentCountText().getText().split(" ")[0]);
//				}
//				result = oldLikeCount + 1 == newLikeCount;
//			}
//			if (!result) {
//				commonStudyMaterialMsgList.add("Not able to click Like button.");
//				return result;
//			}

		} catch (Exception e) {
			result = false;
			commonStudyMaterialMsgList.add("validateLikeBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateShareBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, commonStudyMaterialSectionORObj.getShareBtn(), 30);
			if (!result) {
				commonStudyMaterialMsgList.add("Share button is not visible.");
				return result;
			}
			cfObj.commonClick(commonStudyMaterialSectionORObj.getShareBtn());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'Share via')]",
					"xpath", 30);
			if (!result) {
				commonStudyMaterialMsgList.add("Share via bottom sheet is not visible.");
				return result;
			}

			driver.navigate().back();

			result = cfObj.commonWaitForElementToBeVisible(driver, commonStudyMaterialSectionORObj.getShareBtn(), 30);
			if (!result) {
				commonStudyMaterialMsgList.add("Share button is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			commonStudyMaterialMsgList.add("validateShareBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateCommentTextField(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver,
					commonStudyMaterialSectionORObj.getCommentTextField(), 30);
			if (!result) {
				commonStudyMaterialMsgList.add("Comment text field is not visible.");
				return result;
			}
			cfObj.commonClick(commonStudyMaterialSectionORObj.getCommentTextField());
			commentPageUtilObj = new CommentPageUtil(driver);
			result = commentPageUtilObj.verifyCommentPageUi(driver);
			if (!result) {
				commonStudyMaterialMsgList.addAll(commentPageUtilObj.commentPageMsgList);
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver,
					commonStudyMaterialSectionORObj.getCommentTextField(), 30);
			if (!result) {
				commonStudyMaterialMsgList.add("Comment text field is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			commonStudyMaterialMsgList.add("CommentTextField_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean navigateToHomePage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			cfObj.commonClick(commonStudyMaterialSectionORObj.getBackBtn());
			result = cfObj.commonWaitForElementToBeVisible(driver, commonStudyMaterialSectionORObj.getFilterBtn(), 30);
			if (!result) {
				commonStudyMaterialMsgList.add("Filter button is not visible.");
				return result;
			}
			Thread.sleep(1000);
			cfObj.commonClick(commonStudyMaterialSectionORObj.getBackBtn());

		} catch (Exception e) {
			result = false;
			commonStudyMaterialMsgList.add("NavigateToHomePage_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnPowerCapsulesAndValidate(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			List<MobileElement> materialList = cfObj.commonGetElements(driver,
					"//android.view.ViewGroup/*[contains(@resource-id,'title')]", "xpath");
			result = cfObj.commonWaitForElementToBeVisible(driver, materialList.get(1), 10);
			if (!result) {
				commonStudyMaterialMsgList.add("Study material image is not visible.");
				return result;
			}
			// cfObj.commonClick(materialList.get(1));
			cfObj.commonClick(cfObj.commonGetElements(driver, "//*[@text='test amit']", "xpath").get(0));
			int i = 0;
			while (cfObj.commonWaitForElementToBeVisible(driver, commonStudyMaterialSectionORObj.getProgressBarIcon(),
					20)) {
				i++;
				Thread.sleep(2000);
				if (i > 5) {
					break;
				}
			}

			result = validatePowerCapsuleShareBtn(driver);
			if (!result) {
				commonStudyMaterialMsgList.add("not able to validate Power capsule share button.");
				return result;
			}

			result = validatePowerCapsulePdfBtn(driver);
			if (!result) {
				commonStudyMaterialMsgList.add("not able to validate Power capsules Pdf button.");
				// return result;
			}
			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
				result = validateFontSizeChangeBtn(driver);
				if (!result) {
					commonStudyMaterialMsgList.add("not able to validate FontSize change button.");
					return result;
				}

				result = validateChapterBtn(driver);
				if (!result) {
					commonStudyMaterialMsgList.add("not able to validate ChapterBtn.");
					return result;
				}
			}
			Thread.sleep(2000);
			result = navigateToHomePage(driver);
			if (!result) {
				commonStudyMaterialMsgList.add("not able to navigate home page.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			commonStudyMaterialMsgList.add("PowerCapsulesAndValidate_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validatePowerCapsuleShareBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver,
					commonStudyMaterialSectionORObj.getPowerCapsuleShareBtn(), 30);
			if (!result) {
				commonStudyMaterialMsgList.add("PowerCapsules Share button is not visible.");
				return result;
			}
			cfObj.commonClick(commonStudyMaterialSectionORObj.getPowerCapsuleShareBtn());

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'Share via')]",
					"xpath", 30);
			if (!result) {
				commonStudyMaterialMsgList.add("Share via bottom sheet is not visible.");
				return result;
			}

			driver.navigate().back();

			result = cfObj.commonWaitForElementToBeVisible(driver,
					commonStudyMaterialSectionORObj.getPowerCapsuleShareBtn(), 30);
			if (!result) {
				commonStudyMaterialMsgList.add("PostShareIcon is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			commonStudyMaterialMsgList.add("validateShareBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validatePowerCapsulePdfBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, commonStudyMaterialSectionORObj.getPdfBtn(), 30);
			if (!result) {
				commonStudyMaterialMsgList.add("PowerCapsules Pdf button is not visible.");
				return result;
			}
			// cfObj.commonClick(commonStudyMaterialSectionORObj.getPdfBtn());
			// Thread.sleep(8000);
			//
			// cfObj.commonClick(commonStudyMaterialSectionORObj.getPdfBtn());
			//
			// result=cfObj.commonWaitForElementToBeVisible(driver,
			// commonStudyMaterialSectionORObj.getPdfView(), 30);
			// if(!result) {
			// commonStudyMaterialMsgList.add("Pdf view is not visible.");
			// return result;
			// }
			//
			// cfObj.commonClick(commonStudyMaterialSectionORObj.getPdfViewCloseBtn());
			// result=cfObj.commonWaitForElementToBeVisible(driver,
			// commonStudyMaterialSectionORObj.getPdfBtn(), 30);
			// if(!result) {
			// commonStudyMaterialMsgList.add("PowerCapsules Pdf button is not visible.");
			// return result;
			// }

		} catch (Exception e) {
			result = false;
			commonStudyMaterialMsgList.add("PowerCapsulePdfBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateFontSizeChangeBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver,
					commonStudyMaterialSectionORObj.getFontSizeChangeBtn(), 30);
			if (!result) {
				commonStudyMaterialMsgList.add("FontSize change button is not visible.");
				return result;
			}

			cfObj.commonClick(commonStudyMaterialSectionORObj.getFontSizeChangeBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, commonStudyMaterialSectionORObj.getFontSizeSaveBtn(),
					30);
			if (!result) {
				commonStudyMaterialMsgList.add("FontSize save button is not visible.");
				return result;
			}
			for (int i = 0; i < commonStudyMaterialSectionORObj.getFontSizeList().size(); i++) {
				if (commonStudyMaterialSectionORObj.getFontSizeList().get(i).getAttribute("checked").equals("false")) {
					result = cfObj.commonVerifyValueTextBox(commonStudyMaterialSectionORObj.getFontSizeList().get(i),
							fontsizeList[i]);
					if (!result) {
						commonStudyMaterialMsgList.add("FontSize Text is not matching.");
						return result;
					}
					cfObj.commonClick(commonStudyMaterialSectionORObj.getFontSizeList().get(i));
					result = commonStudyMaterialSectionORObj.getFontSizeList().get(i).getAttribute("checked")
							.equals("true");
					if (!result) {
						commonStudyMaterialMsgList.add("Not able to click Font size radion button.");
						return result;
					}
				} else {
					if (commonStudyMaterialSectionORObj.getFontSizeList().get(i).getAttribute("checked")
							.equals("true")) {
						result = cfObj.commonVerifyValueTextBox(
								commonStudyMaterialSectionORObj.getFontSizeList().get(i), fontsizeList[i]);
						if (!result) {
							commonStudyMaterialMsgList.add("FontSize Text is not matching.");
							return result;
						}
						cfObj.commonClick(commonStudyMaterialSectionORObj.getFontSizeList().get(i));
						result = commonStudyMaterialSectionORObj.getFontSizeList().get(i).getAttribute("checked")
								.equals("false");
						if (!result) {
							commonStudyMaterialMsgList.add("Not able to click Font size radion button.");
							return result;
						}
					}

				}
			}
			cfObj.commonClick(commonStudyMaterialSectionORObj.getFontSizeCancelBtn());
			result = cfObj.commonWaitForElementToBeVisible(driver,
					commonStudyMaterialSectionORObj.getFontSizeChangeBtn(), 30);
			if (!result) {
				commonStudyMaterialMsgList.add("FontSize change button is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			commonStudyMaterialMsgList.add("FontSizeChangeBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateChapterBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, commonStudyMaterialSectionORObj.getTopicBtn(), 30);
			if (!result) {
				commonStudyMaterialMsgList.add("Topic button is not visible.");
				return result;
			}
			cfObj.commonClick(commonStudyMaterialSectionORObj.getTopicBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver,
					commonStudyMaterialSectionORObj.getChapterNameList().get(1), 50);
			if (!result) {
				commonStudyMaterialMsgList.add("Chapter list name is not visible.");
				return result;
			}
			cfObj.commonClick(commonStudyMaterialSectionORObj.getChapterNameList().get(1));
			Thread.sleep(2000);
			result = !cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "name", "id", 4);
			if (!result) {
				commonStudyMaterialMsgList.add("Selected chapter name is not visible.");
				return result;
			}

		} catch (Exception e) {
			result = false;
			commonStudyMaterialMsgList.add("ChapterBtn_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clicOnMaterialAndAddInBookMark(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver,
					commonStudyMaterialSectionORObj.getStudyMaterialImgList().get(0), 10);
			if (!result) {
				commonStudyMaterialMsgList.add("Study material image is not visible.");
				return result;
			}
			cfObj.commonClick(commonStudyMaterialSectionORObj.getStudyMaterialImgList().get(0));
			Thread.sleep(3000);
			cfObj.commonClick(commonStudyMaterialSectionORObj.getBookMarkIcon());

			cfObj.waitTillElementIsVisible(driver, 10, 1000, commonStudyMaterialSectionORObj.getToastMessage());
			cfObj.pressAndroidKey(driver, key.BACK, 1);
//			cfObj.commonClick(commonStudyMaterialSectionORObj.getBackBtn());
			result = cfObj.commonWaitForElementToBeVisible(driver, commonStudyMaterialSectionORObj.getFilterBtn(), 30);
			if (!result) {
				commonStudyMaterialMsgList.add("FilterBtn is not visible.");
				return result;
			}
			cfObj.pressAndroidKey(driver, key.BACK, 1);

//			cfObj.commonClick(commonStudyMaterialSectionORObj.getBackBtn());
		} catch (Exception e) {
			result = false;
			commonStudyMaterialMsgList.add("clicOnMaterialAndAddInBookMark_Exception" + e.getMessage());
		}
		return result;
	}

}
