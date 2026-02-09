package applicationUtil;

import java.util.ArrayList;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.CommonStudyMaterialSection_OR;
import util.Common_Function;
import util.Common_Function.direction;

public class CurrentAffairsPageUtil {


	CommonStudyMaterialSection_OR currentAffairPageORObj;
	CommentPageUtil commentPageUtilObj;
	FilterPageUtil filterPageUtilObj;
	HomePageUtil homePageUtilObj;
	public ArrayList<String> currentAffairMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();
	private String [] fontsizeList= {"Small","Medium (Default)","Large","Extra Large"};

	public CurrentAffairsPageUtil(AppiumDriver<MobileElement> driver) {
		currentAffairPageORObj = new CommonStudyMaterialSection_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), currentAffairPageORObj);
	}

	public boolean verifyCurrentAffairPage(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {

			if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"), 5)) {
				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"));
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getCurrentAffairPageTitle(), 30);
			if(!result) {
				currentAffairMsgList.add("Current affair page title is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getFilterBtn(), 30);
			if(!result) {
				currentAffairMsgList.add("Filter button is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getStudyMaterialTitleList().get(0), 30);
			if(!result) {
				currentAffairMsgList.add("Study material list is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getStudyMaterialImgList().get(0), 30);
			if(!result) {
				currentAffairMsgList.add("Study material image is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getStudyMaterialNameList().get(0), 30);
			if(!result) {
				currentAffairMsgList.add("Study material name is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getStudyMaterialDescriptionList().get(0), 30);
			if(!result) {
				currentAffairMsgList.add("Study material description is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getAudioIconList().get(0), 30);
			if(!result) {
				currentAffairMsgList.add("Audio icon is not visible.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			currentAffairMsgList.add("CurrentAffairPage_Exception"+e.getMessage());	
		}
		return result;
	}

	public boolean verifyJobAlertPage(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {

			if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"), 5)) {
				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"));
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getJobAlertPageTitle(), 30);
			if(!result) {
				currentAffairMsgList.add("Job alerts page title is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getFilterBtn(), 30);
			if(!result) {
				currentAffairMsgList.add("Filter button is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getStudyMaterialTitleList().get(0), 30);
			if(!result) {
				currentAffairMsgList.add("Study material list is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getStudyMaterialImgList().get(0), 30);
			if(!result) {
				currentAffairMsgList.add("Study material image is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getStudyMaterialNameList().get(0), 30);
			if(!result) {
				currentAffairMsgList.add("Study material name is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getStudyMaterialDescriptionList().get(0), 30);
			if(!result) {
				currentAffairMsgList.add("Study material description is not visible.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			currentAffairMsgList.add("JobAlertsPage_Exception"+e.getMessage());	
		}
		return result;
	}

	public boolean verifyNotesAndArticlesPage(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {

			if (cfObj.commonWaitForElementToBeVisible(driver, cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"), 5)) {
				cfObj.commonClick(cfObj.commonGetElement(driver, "//*[@text='Use filters to view content of your choice']", "xpath"));
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getNotesAndArticlePageTitle(), 30);
			if(!result) {
				currentAffairMsgList.add("NotesAndArticles page title is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getFilterBtn(), 30);
			if(!result) {
				currentAffairMsgList.add("Filter button is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getStudyMaterialTitleList().get(0), 30);
			if(!result) {
				currentAffairMsgList.add("Study material list is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getStudyMaterialImgList().get(0), 30);
			if(!result) {
				currentAffairMsgList.add("Study material image is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getStudyMaterialNameList().get(0), 30);
			if(!result) {
				currentAffairMsgList.add("Study material name is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getStudyMaterialDescriptionList().get(0), 30);
			if(!result) {
				currentAffairMsgList.add("Study material description is not visible.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			currentAffairMsgList.add("NotesAndArticlesPage_Exception"+e.getMessage());	
		}
		return result;
	}

	public boolean verifyPowerCapsulesPage(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			if(cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getFilterInstructionText(), 30)) {
				cfObj.scrollUtill(driver, 1, direction.UP);
			}
			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getPowerCapsulesPageTitle(), 30);
			if(!result) {
				currentAffairMsgList.add("PowerCapsules page title is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getFilterBtn(), 30);
			if(!result) {
				currentAffairMsgList.add("Filter button is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getStudyMaterialImgList().get(0), 30);
			if(!result) {
				currentAffairMsgList.add("Study material image is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getStudyMaterialNameList().get(0), 30);
			if(!result) {
				currentAffairMsgList.add("Study material name is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getStudyMaterialDescriptionList().get(0), 30);
			if(!result) {
				currentAffairMsgList.add("Study material description is not visible.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			currentAffairMsgList.add("PowerCapsulesPage_Exception"+e.getMessage());	
		}
		return result;
	}


	public boolean validateFilterBtn(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			if(!(cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getIsReadIndicator(),30))){
				cfObj.commonClick(currentAffairPageORObj.getStudyMaterialImgList().get(0));
				result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getCommentTextField(), 20);
				if(!result) {
					currentAffairMsgList.add("Comment text field is not visible.");
					return result;
				}
				cfObj.commonClick(currentAffairPageORObj.getBackBtn());
				result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getIsReadIndicator(), 20);
				if(!result) {
					currentAffairMsgList.add("IsReadIndicator is not visible.");
					return result;
				}
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getFilterBtn(), 30);
			if(!result) {
				currentAffairMsgList.add("Filter button is not visible.");
				return result;
			}
			cfObj.commonClick(currentAffairPageORObj.getFilterBtn());
			filterPageUtilObj=new FilterPageUtil(driver);
			result=filterPageUtilObj.verifyFilterPageUI(driver);
			if(!result) {
				currentAffairMsgList.addAll(filterPageUtilObj.filterPageMsgList);
				return result;
			}
			result=filterPageUtilObj.clickOnStatusTab(driver, filterPageUtilObj.filterPageORObj.getUnReadBtn(), "selected");
			if(!result) {
				currentAffairMsgList.addAll(filterPageUtilObj.filterPageMsgList);
				return result;
			}
			result=filterPageUtilObj.clickOnApplyBtn(driver);
			if(!result) {
				currentAffairMsgList.addAll(filterPageUtilObj.filterPageMsgList);
				return result;
			}
			Thread.sleep(2000);
			result=!(cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getIsReadIndicator(), 30));
			if(!result) {
				currentAffairMsgList.add("IsReadIndicator is visible.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			currentAffairMsgList.add("FilterBtn_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean validatePowerCapsuleFilterBtn(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			if(!(cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getIsReadIndicator(),30))){
				cfObj.commonClick(currentAffairPageORObj.getStudyMaterialImgList().get(0));
				result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getPdfBtn(), 20);
				if(!result) {
					currentAffairMsgList.add("PowerCapsule Pdf button is not visible.");
					return result;
				}
				Thread.sleep(2000);
				cfObj.commonClick(currentAffairPageORObj.getBackBtn());
				result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getIsReadIndicator(), 20);
				if(!result) {
					currentAffairMsgList.add("IsReadIndicator is not visible.");
					return result;
				}
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getFilterBtn(), 30);
			if(!result) {
				currentAffairMsgList.add("Filter button is not visible.");
				return result;
			}
			cfObj.commonClick(currentAffairPageORObj.getFilterBtn());
			filterPageUtilObj=new FilterPageUtil(driver);
			result=filterPageUtilObj.verifyFilterPageUI(driver);
			if(!result) {
				currentAffairMsgList.addAll(filterPageUtilObj.filterPageMsgList);
				return result;
			}
			result=filterPageUtilObj.clickOnStatusTab(driver, filterPageUtilObj.filterPageORObj.getUnReadBtn(), "selected");
			if(!result) {
				currentAffairMsgList.addAll(filterPageUtilObj.filterPageMsgList);
				return result;
			}
			result=filterPageUtilObj.clickOnApplyBtn(driver);
			if(!result) {
				currentAffairMsgList.addAll(filterPageUtilObj.filterPageMsgList);
				return result;
			}
			Thread.sleep(2000);
			result=!(cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getIsReadIndicator(), 30));
			if(!result) {
				currentAffairMsgList.add("IsReadIndicator is visible.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			currentAffairMsgList.add("FilterBtn_Exception"+e.getMessage());

		}
		return result;
	}

	public boolean clickOnCurrentAffairStudyMaterialAndValidate(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getStudyMaterialImgList().get(0), 10);
			if(!result) {
				currentAffairMsgList.add("Study material image is not visible.");
				return result;
			}
			int randomNum=Common_Function.RandomNumber(0, currentAffairPageORObj.getStudyMaterialImgList().size()-1);
			cfObj.commonClick(currentAffairPageORObj.getStudyMaterialImgList().get(randomNum));

			result=validateAudioBtn(driver);
			if(!result) {
				currentAffairMsgList.add("not able to validate Audio button.");
				return result;
			}

			result=validateBookMarkBtn(driver);
			if(!result) {
				currentAffairMsgList.add("not able to validate Book mark icon");
				return result;
			}

			result=validateDotIcon(driver);
			if(!result) {
				currentAffairMsgList.add("not able to validate Dot icon");
				return result;
			}

			result=validateLikeBtn(driver);
			if(!result) {
				currentAffairMsgList.add("not able to validate Like button.");
				return result;
			}

			result=validateCommentTextField(driver);
			if(!result) {
				currentAffairMsgList.add("not able to validate CommentTextField.");
				return result;
			}

			result=validateShareBtn(driver);
			if(!result) {
				currentAffairMsgList.add("not able to validate Share button.");
				return result;
			}
			result=navigateToHomePage(driver);
			if(!result) {
				currentAffairMsgList.add("not able to navigate home page.");
				return result;
			}
		} catch (Exception e) {
			result=false;
			currentAffairMsgList.add("CurrentAffairStudyMaterialAndValidate_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean clickOnStudyMaterialAndValidate(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getStudyMaterialImgList().get(0), 10);
			if(!result) {
				currentAffairMsgList.add("Study material image is not visible.");
				return result;
			}
			int randomNum=Common_Function.RandomNumber(0, currentAffairPageORObj.getStudyMaterialImgList().size()-1);
			cfObj.commonClick(currentAffairPageORObj.getStudyMaterialImgList().get(randomNum));

			result=validateBookMarkBtn(driver);
			if(!result) {
				currentAffairMsgList.add("not able to validate Book mark icon");
				return result;
			}

			result=validateDotIcon(driver);
			if(!result) {
				currentAffairMsgList.add("not able to validate Dot icon");
				return result;
			}

			result=validateLikeBtn(driver);
			if(!result) {
				currentAffairMsgList.add("not able to validate Like button.");
				return result;
			}

			result=validateCommentTextField(driver);
			if(!result) {
				currentAffairMsgList.add("not able to validate CommentTextField.");
				return result;
			}

			result=validateShareBtn(driver);
			if(!result) {
				currentAffairMsgList.add("not able to validate Share button.");
				return result;
			}
			result=navigateToHomePage(driver);
			if(!result) {
				currentAffairMsgList.add("not able to navigate home page.");
				return result;
			}
		} catch (Exception e) {
			result=false;
			currentAffairMsgList.add("JobAlertsStudyMaterialAndValidate_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean validateBookMarkBtn(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			for(int i=0;i<2;i++) {
				Thread.sleep(2000);
				result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getBookMarkIcon(), 30);
				if(!result) {
					currentAffairMsgList.add("Book mark icon is not visible.");
					return result;
				}
				if(currentAffairPageORObj.getBookMarkIcon().getAttribute("selected").equals("true")) {
					Thread.sleep(1000);
					cfObj.commonClick(currentAffairPageORObj.getBookMarkIcon());
					result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getToastMessage(), 30);
					if(!result) {
						currentAffairMsgList.add("toast is not visible.");
						return result;
					}
					String toastMessage=currentAffairPageORObj.getToastMessage().getText();
					if(toastMessage==null) {
						currentAffairMsgList.add("Toast message text is null.");
						return false;
					}
					System.out.println(toastMessage);
					result=toastMessage.equalsIgnoreCase("Removed from Bookmark");
					if(!result) {
						currentAffairMsgList.add("toast text is not matching.");
						return result;
					}
				}
				else {
					Thread.sleep(1000);
					cfObj.commonClick(currentAffairPageORObj.getBookMarkIcon());
					result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getToastMessage(), 30);
					if(!result) {
						currentAffairMsgList.add("toast is not visible.");
						return result;
					}
					String toastMessage=currentAffairPageORObj.getToastMessage().getText();
					if(toastMessage==null) {
						currentAffairMsgList.add("Toast message text is null.");
						return false;
					}
					System.out.println(toastMessage);
					result=toastMessage.equalsIgnoreCase("Added to Bookmark");
					if(!result) {
						currentAffairMsgList.add("toast text is not matching.");
						return result;
					}
				}
			}
		} catch (Exception e) {
			result=false;
			currentAffairMsgList.add("validateBookMarkBtn_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean validateAudioBtn(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			Thread.sleep(3000);
			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getAuidoIcon(), 30);
			if(!result) {
				currentAffairMsgList.add("Audio icon is not visible.");
				return result;
			}
			if(currentAffairPageORObj.getAuidoIcon().getAttribute("selected").equals("true")) {
				cfObj.commonClick(currentAffairPageORObj.getAuidoIcon());
				Thread.sleep(1000);
				result=currentAffairPageORObj.getAuidoIcon().getAttribute("selected").equals("false");
				if(!result) {
					currentAffairMsgList.add("not able to click on audio button.");
					return result;
				}
			}
			else {
				cfObj.commonClick(currentAffairPageORObj.getAuidoIcon());
				Thread.sleep(1000);
				result=currentAffairPageORObj.getAuidoIcon().getAttribute("selected").equals("true");
				if(!result) {
					currentAffairMsgList.add("not able to click on audio button.");
					return result;
				}
			}
		} catch (Exception e) {
			result=false;
			currentAffairMsgList.add("validateAudioBtn_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean validateDotIcon(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			Thread.sleep(2000);
			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getDotIcon(), 30);
			if(!result) {
				currentAffairMsgList.add("Dot icon is not visible.");
				return result;
			}
			cfObj.commonClick(currentAffairPageORObj.getDotIcon());
			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getFontSizeBtn(), 30);
			if(!result) {
				currentAffairMsgList.add("FontSize button is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getReportContentBtn(), 30);
			if(!result) {
				currentAffairMsgList.add("ReportContent button is not visible.");
				return result;
			}

			cfObj.commonClick(currentAffairPageORObj.getFontSizeBtn());

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getFontSizeSaveBtn(), 30);
			if(!result) {
				currentAffairMsgList.add("FontSize save button is not visible.");
				return result;
			}
			for(int i=0;i<currentAffairPageORObj.getFontSizeList().size();i++) {
				if(currentAffairPageORObj.getFontSizeList().get(i).getAttribute("checked").equals("false")){
					result=cfObj.commonVerifyValueTextBox(currentAffairPageORObj.getFontSizeList().get(i), fontsizeList[i]);
					if(!result) {
						currentAffairMsgList.add("FontSize Text is not matching.");
						return result;
					}
					cfObj.commonClick(currentAffairPageORObj.getFontSizeList().get(i));
					result=currentAffairPageORObj.getFontSizeList().get(i).getAttribute("checked").equals("true");
					if(!result) {
						currentAffairMsgList.add("Not able to click Font size radion button.");
						return result;
					}
				}
				else {
					if(currentAffairPageORObj.getFontSizeList().get(i).getAttribute("checked").equals("true")){
						result=cfObj.commonVerifyValueTextBox(currentAffairPageORObj.getFontSizeList().get(i), fontsizeList[i]);
						if(!result) {
							currentAffairMsgList.add("FontSize Text is not matching.");
							return result;
						}
						cfObj.commonClick(currentAffairPageORObj.getFontSizeList().get(i));
						result=currentAffairPageORObj.getFontSizeList().get(i).getAttribute("checked").equals("false");
						if(!result) {
							currentAffairMsgList.add("Not able to click Font size radion button.");
							return result;
						}
					}

				}
			}
			cfObj.commonClick(currentAffairPageORObj.getFontSizeCancelBtn());
			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getDotIcon(), 30);
			if(!result) {
				currentAffairMsgList.add("Dot icon is not visible.");
				return result;
			}

		}catch (Exception e) {
			result=false;
			currentAffairMsgList.add("validateDotIcon_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean validateLikeBtn(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getLikeCommentCountText(), 10);
			if(!result) {
				currentAffairMsgList.add("Like comment count text is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getLikeBtn(), 10);
			if(!result) {
				currentAffairMsgList.add("Like button is not visible.");
				return result;
			}
			int oldLikeCount=0;
			if(!(currentAffairPageORObj.getLikeCommentCountText().getText().equals("Be the first one to comment"))) {
				oldLikeCount=Integer.parseInt(currentAffairPageORObj.getLikeCommentCountText().getText().split(" ")[0]); 
				cfObj.commonClick(currentAffairPageORObj.getLikeBtn());
				Thread.sleep(1000);
				int newLikeCount=Integer.parseInt(currentAffairPageORObj.getLikeCommentCountText().getText().split(" ")[0]); 
				result=(oldLikeCount+1==newLikeCount);
			}
			else {
				cfObj.commonClick(currentAffairPageORObj.getLikeBtn());
				Thread.sleep(1000);
				int newLikeCount=Integer.parseInt(currentAffairPageORObj.getLikeCommentCountText().getText().split(" ")[0]); 
				result=oldLikeCount+1==newLikeCount;
			}
			if(!result) {
				currentAffairMsgList.add("Not able to click Like button.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			currentAffairMsgList.add("validateLikeBtn_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean validateShareBtn(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getShareBtn(), 30);
			if(!result) {
				currentAffairMsgList.add("Share button is not visible.");
				return result;
			}
			cfObj.commonClick(currentAffairPageORObj.getShareBtn());
			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getShareScreen(), 30);
			if(!result) {
				currentAffairMsgList.add("Share screen is not visible.");
				return result;
			}

			cfObj.scrollUtill(driver, 1, direction.UP);

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getShareBtn(), 30);
			if(!result) {
				currentAffairMsgList.add("Share button is not visible.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			currentAffairMsgList.add("validateShareBtn_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean validateCommentTextField(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getCommentTextField(), 30);
			if(!result) {
				currentAffairMsgList.add("Comment text field is not visible.");
				return result;
			}
			cfObj.commonClick(currentAffairPageORObj.getCommentTextField());
			commentPageUtilObj=new CommentPageUtil(driver);
			result=commentPageUtilObj.verifyCommentPageUi(driver);
			if(!result) {
				currentAffairMsgList.addAll(commentPageUtilObj.commentPageMsgList);
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getCommentTextField(), 30);
			if(!result) {
				currentAffairMsgList.add("Comment text field is not visible.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			currentAffairMsgList.add("CommentTextField_Exception"+e.getMessage());
		}
		return result;
	}
	public boolean navigateToHomePage(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			cfObj.commonClick(currentAffairPageORObj.getBackBtn());
			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getFilterBtn(), 30);		
			if(!result) {
				currentAffairMsgList.add("Filter button is not visible.");
				return result;
			}
			Thread.sleep(1000);
			cfObj.commonClick(currentAffairPageORObj.getBackBtn());

		} catch (Exception e) {
			result=false;
			currentAffairMsgList.add("NavigateToHomePage_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean clickOnPowerCapsulesAndValidate(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getStudyMaterialImgList().get(0), 10);
			if(!result) {
				currentAffairMsgList.add("Study material image is not visible.");
				return result;
			}
			int randomNum=Common_Function.RandomNumber(1, currentAffairPageORObj.getStudyMaterialImgList().size()-1);
			cfObj.commonClick(currentAffairPageORObj.getStudyMaterialImgList().get(randomNum));
			int i=0;
			while(cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getProgressBarIcon(), 20)) {
				i++;
				Thread.sleep(500);
				if(i>5) {
					break;
				}
			}

			result=validatePowerCapsuleShareBtn(driver);
			if(!result) {
				currentAffairMsgList.add("not able to validate Power capsule share button.");
				return result;
			}
			result=validatePowerCapsulePdfBtn(driver);
			if(!result) {
				currentAffairMsgList.add("not able to validate Power capsules Pdf button.");
				return result;
			}
			result=validateFontSizeChangeBtn(driver);
			if(!result) {
				currentAffairMsgList.add("not able to validate FontSize change button.");
				return result;
			}

			result=validateChapterBtn(driver);
			if(!result) {
				currentAffairMsgList.add("not able to validate ChapterBtn.");
				return result;
			}
			result=navigateToHomePage(driver);
			if(!result) {
				currentAffairMsgList.add("not able to navigate home page.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			currentAffairMsgList.add("PowerCapsulesAndValidate_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean validatePowerCapsuleShareBtn(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getPowerCapsuleShareBtn(), 30);
			if(!result) {
				currentAffairMsgList.add("PowerCapsules Share button is not visible.");
				return result;
			}
			cfObj.commonClick(currentAffairPageORObj.getPowerCapsuleShareBtn());
			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getShareScreen(), 30);
			if(!result) {
				currentAffairMsgList.add("Share screen is not visible.");
				return result;
			}

			cfObj.scrollUtill(driver, 1, direction.UP);

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getPowerCapsuleShareBtn(), 30);
			if(!result) {
				currentAffairMsgList.add("PowerCapsules Share button is not visible.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			currentAffairMsgList.add("validateShareBtn_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean validatePowerCapsulePdfBtn(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getPdfBtn(), 30);
			if(!result) {
				currentAffairMsgList.add("PowerCapsules Pdf button is not visible.");
				return result;
			}
			cfObj.commonClick(currentAffairPageORObj.getPdfBtn());
			Thread.sleep(8000);

			cfObj.commonClick(currentAffairPageORObj.getPdfBtn());

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getPdfView(), 30);
			if(!result) {
				currentAffairMsgList.add("PowerCapsule Pdf view is not visible.");
				return result;
			}

			cfObj.commonClick(currentAffairPageORObj.getPdfViewCloseBtn());
			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getPdfBtn(), 30);
			if(!result) {
				currentAffairMsgList.add("PowerCapsules Pdf button is not visible.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			currentAffairMsgList.add("PowerCapsulePdfBtn_Exception"+e.getMessage());
		}
		return result;
	}


	public boolean validateFontSizeChangeBtn(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getFontSizeChangeBtn(), 30);
			if(!result) {
				currentAffairMsgList.add("FontSize change button is not visible.");
				return result;
			}

			cfObj.commonClick(currentAffairPageORObj.getFontSizeChangeBtn());

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getFontSizeSaveBtn(), 30);
			if(!result) {
				currentAffairMsgList.add("FontSize save button is not visible.");
				return result;
			}
			for(int i=0;i<currentAffairPageORObj.getFontSizeList().size();i++) {
				if(currentAffairPageORObj.getFontSizeList().get(i).getAttribute("checked").equals("false")){
					result=cfObj.commonVerifyValueTextBox(currentAffairPageORObj.getFontSizeList().get(i), fontsizeList[i]);
					if(!result) {
						currentAffairMsgList.add("FontSize Text is not matching.");
						return result;
					}
					cfObj.commonClick(currentAffairPageORObj.getFontSizeList().get(i));
					result=currentAffairPageORObj.getFontSizeList().get(i).getAttribute("checked").equals("true");
					if(!result) {
						currentAffairMsgList.add("Not able to click Font size radion button.");
						return result;
					}
				}
				else {
					if(currentAffairPageORObj.getFontSizeList().get(i).getAttribute("checked").equals("true")){
						result=cfObj.commonVerifyValueTextBox(currentAffairPageORObj.getFontSizeList().get(i), fontsizeList[i]);
						if(!result) {
							currentAffairMsgList.add("FontSize Text is not matching.");
							return result;
						}
						cfObj.commonClick(currentAffairPageORObj.getFontSizeList().get(i));
						result=currentAffairPageORObj.getFontSizeList().get(i).getAttribute("checked").equals("false");
						if(!result) {
							currentAffairMsgList.add("Not able to click Font size radion button.");
							return result;
						}
					}

				}
			}
			cfObj.commonClick(currentAffairPageORObj.getFontSizeCancelBtn());
			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getFontSizeChangeBtn(), 30);
			if(!result) {
				currentAffairMsgList.add("FontSize change button is not visible.");
				return result;
			}

		}catch (Exception e) {
			result=false;
			currentAffairMsgList.add("FontSizeChangeBtn_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean validateChapterBtn(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getTopicBtn(), 30);
			if(!result) {
				currentAffairMsgList.add("Topic button is not visible.");
				return result;
			}
			cfObj.commonClick(currentAffairPageORObj.getTopicBtn());

			result=cfObj.commonWaitForElementToBeVisible(driver, currentAffairPageORObj.getChapterNameList().get(1), 50);
			if(!result) {
				currentAffairMsgList.add("Chapter list name is not visible.");
				return result;
			}
			String chapterName=currentAffairPageORObj.getChapterNameList().get(1).getText();
			if(chapterName==null) {
				currentAffairMsgList.add("Chapter name text is null.");
				return false;
			}
			cfObj.commonClick(currentAffairPageORObj.getChapterNameList().get(1));
			Thread.sleep(2000);
			result=cfObj.commonVerifyValueTextBox(currentAffairPageORObj.getSelectedChapterName(), chapterName);
			if(!result) {
				currentAffairMsgList.add("Selected chapter name is not matching.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			currentAffairMsgList.add("ChapterBtn_Exception"+e.getMessage());
		}
		return result;
	}

}
