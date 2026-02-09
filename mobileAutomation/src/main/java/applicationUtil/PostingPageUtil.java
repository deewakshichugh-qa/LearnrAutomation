package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import applicationUtil.CommunityPageUtil.PostType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.PostingPage_OR;
import util.Common_Function;
import util.Common_Function.direction;
import util.ConfigFileReader;

public class PostingPageUtil {

	PostingPage_OR postingPageObj;
	public List<String> postingMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();
	CommunityPageUtil communityPageUtilObj;

	public PostingPageUtil(AppiumDriver<MobileElement> driver) {
		postingPageObj = new PostingPage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), postingPageObj);
	}
	
	public boolean verifyPostSharingPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			
			result = cfObj.commonWaitForElementToBeVisible(driver, postingPageObj.getSubmitBtn(), 10); 
			if(!result) {
				postingMsgList.add("Post Submit button is not visible.");
				return result;
			}
			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//android.view.View[@content-desc='Group Link']", "xpath", 10);
			if(!result) {
				postingMsgList.add("GroupLink is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'Automation')]", "xpath", 10);
			if(!result) {
				postingMsgList.add("Posting Group is not visible.");
				return result;
			}
			
			result = cfObj.commonWaitForElementToBeVisible(driver, postingPageObj.getAddPhotoTitle(), 10);
			if(!result) {
				postingMsgList.add("AddPhoto title is not visible.");
				return result;
			}
			
			
		} catch (Exception e) {
			postingMsgList.add("verifyPostSharingPage_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean verifyPostingPage(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, postingPageObj.getDropMenuTopic(), 10); 
			if(!result) {
				postingMsgList.add("Unable to verify drop down menu to select topic in posting page");
				return result;
			}

			//			result = cfObj.commonWaitForElementToBeVisible(driver, postingPageObj.getFieldAskQuery(), 10);
			//			if(!result) {
			//				postingMsgList.add("Unable to verify Ask Query/Doubts or share someting field in posting page");
			//				return result;
			//			}

			result = cfObj.commonWaitForElementToBeVisible(driver, postingPageObj.getBtnPost(), 10);
			if(!result) {
				postingMsgList.add("Unable to verify post btn in posting page");
				return result;
			}


		} catch (Exception e) {
			result=false;
			postingMsgList.add("verifyPostingPage_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean selectTopicForSankalp(AppiumDriver<MobileElement> driver, String strPostTopic) {
		boolean result = true;
		try {
			cfObj.commonClick(postingPageObj.getDropMenuTopic());

			result = cfObj.commonWaitForElementToBeVisible(driver, postingPageObj.getTitleSelectTopic(), 10);
			if(result) {
				List<MobileElement> el = postingPageObj.getOptionTopics();

				cfObj.commonClick(el.get(0));


				//				while(!found) {
				//					System.out.println(el.get(index).getText());
				//					if(el.get(index).getAttribute("content-desc").equalsIgnoreCase(strPostTopic)) {
				//						cfObj.commonClick(el.get(index));
				//						found = true;
				//						break;
				//					}else {
				//						if(index < el.size()-1) {
				//							index++;
				//						}else {
				//							postingMsgList.add("Given posting topic: " + strPostTopic + " not found");
				//							result = false;
				//							break;
				//						}
				//					}
				//				}
			}

		} catch (Exception e) {
			postingMsgList.add("selectTopic_Excepiton: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean selectTopic(AppiumDriver<MobileElement> driver, String strPostTopic) {
		boolean result = true;
		try {

			result = cfObj.commonWaitForElementToBeVisible(driver, postingPageObj.getTitleSelectTopic(), 10);
			if (result) {
				List<MobileElement> el = postingPageObj.getOptionTopics();

				cfObj.commonClick(el.get(0));
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, postingPageObj.getPostBtn(), 10);
			if (!result) {
				postingMsgList.add("Post button is not visible.");
				return result;
			}

			result = clickPostBtn(driver, strPostTopic);
			if (!result) {
				postingMsgList.add("Post button is not clickable.");
				return result;
			}
		} catch (Exception e) {
			postingMsgList.add("selectTopic_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean enterQueryOrDoubt(AppiumDriver<MobileElement> driver, String strDoubt) {
		boolean result = true;
		try {
			result = cfObj.commonSetTextTextBox(postingPageObj.getPostQueryTextField(), strDoubt);
			if(!result) {
				postingMsgList.add("Failed to enter query on text field.");
			}
			
		} catch (Exception e) {
			postingMsgList.add("enterQueryOrDoubt_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}


	public boolean tagFriendEmailToTag(AppiumDriver<MobileElement> driver, String strFriendEmail) {
		boolean result = true;
		try {

			result = cfObj.commonSetTextTextBox(postingPageObj.getFieldText().get(1), strFriendEmail);
			if(!result) {
				postingMsgList.add("Failed to enter Friend email id.");
			}
		} catch (Exception e) {
			postingMsgList.add("tagFriendEmailToTag_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}
	
	public boolean clickPostSubmitBtn(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			Thread.sleep(500);

			cfObj.commonClick(postingPageObj.getSubmitBtn());

			cfObj.waitForPageLoading(driver, 10, 2000, postingPageObj.getTitleSelectTopic());

			result = cfObj.commonWaitForElementToBeVisible(driver, postingPageObj.getTitleSelectTopic(), 5);
			if(!result) {
				postingMsgList.add("Unable to verify title in select topic page.");
				return result;
			}

		} catch (Exception e) {
			System.out.println("clickPostSubmitBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean clickPostBtn(AppiumDriver<MobileElement> driver, String strPostContent) {
		boolean result;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, postingPageObj.getPostBtn(), 10);
			if(!result){
				postingMsgList.add("Post button is not visible.");
				return result;
			}

			cfObj.commonClick(postingPageObj.getPostBtn());

			cfObj.waitForPageLoading(driver, 10, 2000, cfObj.commonGetElement(driver, "//*[contains(@text,'" + strPostContent + "')]", "xpath"));

			result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'" + strPostContent + "')]", "xpath", 10);
			if (!result) {

				cfObj.scrollUp(driver, 1);

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@text,'" + strPostContent + "')]", "xpath", 10);
				if (!result) {
					postingMsgList.add("Post content text is not visible.");
					return result;
				}
			}
		} catch (Exception e) {
			System.out.println("clickPostBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}
	
	public boolean addOptionsInMcq(AppiumDriver<MobileElement> driver, int noOfOptions, int correctOptions) {
		boolean result = true;
		try {

			if(ConfigFileReader.strApplicationType.equalsIgnoreCase("ios")) {
				cfObj.commonClick(postingPageObj.getMcqOptionList().get(0));
			}
			result=cfObj.commonSetTextTextBox(postingPageObj.getMcqOptionList().get(0), "Option 1");
			if(!result) {
				postingMsgList.add("Failed to enter Option1.");
				return result;
			}

			Thread.sleep(1000);
			if(ConfigFileReader.strApplicationType.equalsIgnoreCase("ios")) {
				cfObj.commonClick(postingPageObj.getMcqOptionList().get(1));
			}

			result=cfObj.commonSetTextTextBox(postingPageObj.getMcqOptionList().get(1), "Option 2");
			if(!result) {
				postingMsgList.add("Failed to enter Option2.");
				return result;
			}
			if(!ConfigFileReader.strRunMode.equalsIgnoreCase("localLab")){
				driver.hideKeyboard();
			}

			for(int i=2; i<noOfOptions; i++) {
				cfObj.commonClick(postingPageObj.getLinkAddMcq());
				Thread.sleep(1000);
				if(ConfigFileReader.strApplicationType.equalsIgnoreCase("ios")) {
					cfObj.commonClick(postingPageObj.getMcqOptionList().get(i));
				}
				result=cfObj.commonSetTextTextBox(postingPageObj.getMcqOptionList().get(i), "Option " + (i+1));
				if(!result) {
					postingMsgList.add("Failed to enter Option"+(i+1));
					return result;
				}
				if(!ConfigFileReader.strRunMode.equalsIgnoreCase("localLab")){
					driver.hideKeyboard();
				}
			}

			for(int j =0; j<correctOptions; j++) {
				cfObj.commonClick(postingPageObj.getListCheckBox().get(j));
				Thread.sleep(500);
				if(!ConfigFileReader.strRunMode.equalsIgnoreCase("localLab")){
					driver.hideKeyboard();
				}
			}

		} catch (Exception e) {
			postingMsgList.add("clickPostBtn_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	public boolean verifyUserCannotPostWithoutAtleastOneOption(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			result = addOptionsInMcq(driver, 4, 0);
			if(!result) {
				postingMsgList.add("Failed to add MCQ option.");
				return result;
			}

			MobileElement postBtn = cfObj.getElementFromAttribute(driver, "content-desc", "post");

			cfObj.commonClick(postBtn);

			if(!(cfObj.commonWaitForElementToBeVisible(driver, postBtn, 10))) {
				postingMsgList.add("Posted without checking any option true or I don't know the answer checkbox");
				result = false;
			}

		} catch (Exception e) {
			postingMsgList.add("verifyUserCannotPostWithoutAtleastOneOption_Exception"+e.getMessage());
			result=false;
		}
		return result;
	}

	public boolean uploadImagePdfPpt(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			if(ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'Add Photos/PPT/PDF')]/following-sibling::android.widget.ImageView[1]", "xpath", 10);
				if(!result) {
					postingMsgList.add("ImageUpload icon is not visible.");
					return result;
				}

				cfObj.commonClick(driver, "//*[contains(@content-desc,'Add Photos/PPT/PDF')]/following-sibling::android.widget.ImageView[1]", "xpath");

				result=cfObj.commonWaitForElementToBeVisible(driver, postingPageObj.getSelectFromGalleryTab(), 10);
				if(!result) {
					postingMsgList.add("SelectFromGalleryTab is not visible.");
					return result;
				}
				cfObj.commonClick(postingPageObj.getSelectFromGalleryTab());
				Thread.sleep(2000);
				result = cfObj.commonWaitForElementToBeVisible(driver, postingPageObj.getImageFile(), 30);
				if(!result) {
					postingMsgList.add("ImageFile is not visible.");
					return result;
				}

				cfObj.commonClick(postingPageObj.getImageFile());
				Thread.sleep(3000);//For handling pageLoading
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'Add Photos/PPT/PDF')]/preceding-sibling::android.view.View/descendant::*[contains(@content-desc,'MB')]", "xpath", 10);
				if(!result) {
					postingMsgList.add("Not able to upload image.");
					return result;
				}

				//PPT

				result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'Add Photos/PPT/PDF')]/following-sibling::android.widget.ImageView[2]", "xpath", 10);
				if(!result) {
					postingMsgList.add("PPTUpload icon is not visible.");
					return result;
				}

				cfObj.commonClick(driver, "//*[contains(@content-desc,'Add Photos/PPT/PDF')]/following-sibling::android.widget.ImageView[2]", "xpath");

				if(cfObj.commonWaitForElementToBeVisible(driver, postingPageObj.getPermissionAllowBtn(), 4)) {
					cfObj.commonClick(postingPageObj.getPermissionAllowBtn());
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='SocialDemo.pptx']", "xpath", 10);
				if(!result) {
					postingMsgList.add("PPTFile is not visible.");
					return result;
				}

				cfObj.commonClick(driver, "//*[@text='SocialDemo.pptx']", "xpath");
				Thread.sleep(3000);//For handling pageLoading
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'Add Photos/PPT/PDF')]/preceding-sibling::android.view.View/descendant::*[contains(@content-desc,'MB')][2]", "xpath", 10);
				if(!result) {
					postingMsgList.add("Not able to upload PPT file.");
					return result;
				}

				//PDF

				result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'Add Photos/PPT/PDF')]/following-sibling::android.widget.ImageView[3]", "xpath", 10);
				if(!result) {
					postingMsgList.add("PDFUpload icon is not visible.");
					return result;
				}

				cfObj.commonClick(driver, "//*[contains(@content-desc,'Add Photos/PPT/PDF')]/following-sibling::android.widget.ImageView[3]", "xpath");

				if(cfObj.commonWaitForElementToBeVisible(driver, postingPageObj.getPermissionAllowBtn(), 4)) {
					cfObj.commonClick(postingPageObj.getPermissionAllowBtn());
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='SocialDemo.pdf']", "xpath", 10);
				if(!result) {
					postingMsgList.add("PDFFile is not visible.");
					return result;
				}

				cfObj.commonClick(driver, "//*[@text='SocialDemo.pdf']", "xpath");
				Thread.sleep(3000);//For handling pageLoading
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@content-desc,'Add Photos/PPT/PDF')]/preceding-sibling::android.view.View/descendant::*[contains(@content-desc,'MB')][3]", "xpath", 10);
				if(!result) {
					postingMsgList.add("Not able to upload PPT file.");
					return result;
				}
			}else {
				result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@name,'Add Photos/PPT/PDF')]/following-sibling::XCUIElementTypeImage[1]", "xpath", 10);
				if(!result) {
					postingMsgList.add("ImageUpload icon is not visible.");
					return result;
				}

				cfObj.commonClick(driver, "//*[contains(@name,'Add Photos/PPT/PDF')]/following-sibling::XCUIElementTypeImage[1]", "xpath");

				result=cfObj.commonWaitForElementToBeVisible(driver, postingPageObj.getSelectFromGalleryTab(), 10);
				if(!result) {
					postingMsgList.add("SelectFromGalleryTab is not visible.");
					return result;
				}
				cfObj.commonClick(postingPageObj.getSelectFromGalleryTab());

				result = cfObj.commonWaitForElementToBeVisible(driver, postingPageObj.getImageFile(), 30);
				if(!result) {
					postingMsgList.add("ImageFile is not visible.");
					return result;
				}

				cfObj.commonClick(postingPageObj.getImageFile());
				Thread.sleep(3000);//For handling pageLoading
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@name,'Add Photos/PPT/PDF')]/preceding-sibling::android.view.View/descendant::*[contains(@content-desc,'MB')]", "xpath", 10);
				if(!result) {
					postingMsgList.add("Not able to upload image.");
					return result;
				}

				//PPT

				result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@name,'Add Photos/PPT/PDF')]/following-sibling::XCUIElementTypeImage[2]", "xpath", 10);
				if(!result) {
					postingMsgList.add("PPTUpload icon is not visible.");
					return result;
				}

				cfObj.commonClick(driver, "//*[contains(@name,'Add Photos/PPT/PDF')]/following-sibling::XCUIElementTypeImage[2]", "xpath");

				if(cfObj.commonWaitForElementToBeVisible(driver, postingPageObj.getPermissionAllowBtn(), 4)) {
					cfObj.commonClick(postingPageObj.getPermissionAllowBtn());
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@name='SocialDemo.pptx']", "xpath", 10);
				if(!result) {
					postingMsgList.add("PPTFile is not visible.");
					return result;
				}

				cfObj.commonClick(driver, "//*[@name='SocialDemo.pptx']", "xpath");
				Thread.sleep(3000);//For handling pageLoading
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@name,'Add Photos/PPT/PDF')]/preceding-sibling::android.view.View/descendant::*[contains(@content-desc,'MB')][2]", "xpath", 10);
				if(!result) {
					postingMsgList.add("Not able to upload PPT file.");
					return result;
				}

				//PDF

				result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@name,'Add Photos/PPT/PDF')]/following-sibling::XCUIElementTypeImage[3]", "xpath", 10);
				if(!result) {
					postingMsgList.add("PDFUpload icon is not visible.");
					return result;
				}

				cfObj.commonClick(driver, "//*[contains(@name,'Add Photos/PPT/PDF')]/following-sibling::XCUIElementTypeImage[3]", "xpath");

				if(cfObj.commonWaitForElementToBeVisible(driver, postingPageObj.getPermissionAllowBtn(), 4)) {
					cfObj.commonClick(postingPageObj.getPermissionAllowBtn());
				}

				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@name='SocialDemo.pdf']", "xpath", 10);
				if(!result) {
					postingMsgList.add("PDFFile is not visible.");
					return result;
				}

				cfObj.commonClick(driver, "//*[@name='SocialDemo.pdf']", "xpath");
				Thread.sleep(3000);//For handling pageLoading
				result = cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[contains(@name,'Add Photos/PPT/PDF')]/preceding-sibling::android.view.View/descendant::*[contains(@content-desc,'MB')][3]", "xpath", 10);
				if(!result) {
					postingMsgList.add("Not able to upload PPT file.");
					return result;
				}
			}

		} catch (Exception e) {
			postingMsgList.add("uploadImagePdfPpt_Exception"+e.getMessage());
			result=false;
		}
		return result;	
	}
	
	public boolean verifyAttachmentMCQPostTabPage(AppiumDriver<MobileElement> driver, PostType postType) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, postingPageObj.getSelectGroupDropDown(), 10);
			if(!result) {
				postingMsgList.add("Select Group dropdown is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, postingPageObj.getPostQueryTextField(), 10);
			if(!result) {
				postingMsgList.add("Post Query text field is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, postingPageObj.getAddPhotoTitle(), 10);
			if(!result) {
				postingMsgList.add("Add photo title is not visible.");
				return result;
			}

			if(postType==PostType.MCQ) {
				result = cfObj.commonWaitForElementToBeVisible(driver, postingPageObj.getMcqOptionCheckBoxList().get(0), 10);
				if(!result) {
					postingMsgList.add("McqOptionCheckBoxList is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, postingPageObj.getLinkAddMcq(), 10);
				if(!result) {
					postingMsgList.add("LinkAddMcq is not visible.");
					return result;
				}
				result = cfObj.commonWaitForElementToBeVisible(driver, postingPageObj.getDontKnowAnswerCheckBox(), 10);
				if(!result) {
					postingMsgList.add("DontKnowAnswerCheckBox is not visible.");
					return result;
				}
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, postingPageObj.getSubmitBtn(), 10);
			if(!result) {
				cfObj.scrollUtill(driver, 1, direction.DOWN);
				result = cfObj.commonWaitForElementToBeVisible(driver, postingPageObj.getSubmitBtn(), 10);
				if(!result) {
					postingMsgList.add("Post Submit button is not visible.");
					return result;
				}
				cfObj.scrollUtill(driver, 1, direction.UP);

			}

		} catch (Exception e) {
			postingMsgList.add("verifyAttachmentMCQPostTabPage_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}
	
	public boolean clickOnAttachmentMCQPostTab(AppiumDriver<MobileElement> driver, PostType postType) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, postingPageObj.getAttachmentPostTab(), 10);
			if(!result) {
				postingMsgList.add("AttachmentPostTab is not visible.");
				return result;
			}
			
			result = cfObj.commonWaitForElementToBeVisible(driver, postingPageObj.getMcqPostTab(), 10);
			if(!result) {
				postingMsgList.add("MCQPostTab is not visible.");
				return result;
			}
			if(postType==PostType.PICTURE_ATTACHMENT_POST) {
				cfObj.commonClick(postingPageObj.getAttachmentPostTab());

				result = postingPageObj.getAttachmentPostTab().getAttribute("selected").equalsIgnoreCase("true");
				if(!result) {
					postingMsgList.add("Failed to select AttachmentPostTab.");
					return result;
				}
			}
			else {
				cfObj.commonClick(postingPageObj.getMcqPostTab());

				result = postingPageObj.getMcqPostTab().getAttribute("selected").equalsIgnoreCase("true");
				if(!result) {
					postingMsgList.add("Failed to select McqPostTab.");
					return result;
				}
			}

			result = verifyAttachmentMCQPostTabPage(driver, postType);
			if(!result) {
				postingMsgList.add("Failed to verify "+postType+" Tab Page.");
				return result;
			}

		} catch (Exception e) {
			postingMsgList.add("clickOnAttachmentMCQPostTab_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}
	
	public boolean clickOnSelectGroupDropDown(AppiumDriver<MobileElement> driver) {
		boolean result;
		try {
			cfObj.scrollUntilElementFound(driver, postingPageObj.getSelectGroupDropDown(), "*Please select a group to post your doubt.", 2,"description",direction.UP);
			
			result = cfObj.commonWaitForElementToBeVisible(driver, postingPageObj.getSelectGroupDropDown(), 10);
			if(!result) {
				postingMsgList.add("SelectGroupDropDown is not visible.");
				return result;
			}
			cfObj.commonClick(postingPageObj.getSelectGroupDropDown());

			communityPageUtilObj=new CommunityPageUtil(driver);
			result = communityPageUtilObj.verifySelectGroupSlider(driver);
			if(!result) {
				postingMsgList.addAll(communityPageUtilObj.communityPageMsgList);
				return result;
			}
		} catch (Exception e) {
			postingMsgList.add("clickOnSelectGroupDropDown_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}
}
