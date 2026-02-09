package applicationUtil;

import java.util.ArrayList;

import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.CommentPage_OR;
import util.Common_Function;
import util.Common_Function.direction;
import util.ConfigFileReader;

public class CommentPageUtil {

	CommentPage_OR commentPageOrObj;
	public ArrayList<String> commentPageMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();
	private String commentText="Hiiii";
	private String replyCommentText="I have one doubt.";

	public CommentPageUtil(AppiumDriver<MobileElement> driver) {
		commentPageOrObj = new CommentPage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), commentPageOrObj);
	}

	public boolean verifyCommentPageUi(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getCommentPageHeader(), 30);
			if (!result) {
				commentPageMsgList.add("Comment page header is not visible.");
				return result;
			}

			if (cfObj.commonVerifyValueTextBox(commentPageOrObj.getCommentPageHeader(), "Be the first one to comment")) {

				result = cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getEmptyStateImage(), 30);
				if (!result) {
					commentPageMsgList.add("Empty state image is not visible.");
					return result;
				}

				result = cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getEmptyStateText(), 30);
				if (!result) {
					commentPageMsgList.add("Empty state text is not visible.");
					return result;
				}
			}

			result = validateCommentComposeTextField(driver);
			if (!result) {
				commentPageMsgList.add("Not able to validate comment compose text field.");
				return result;
			}

			result = validateCommentedPostComponent(driver);
			if (!result) {
				commentPageMsgList.add("Not able to validate CommentedPostComponent.");
				return result;
			}

			result = validateCommentDeleteAction(driver);
			if (!result) {
				commentPageMsgList.add("Not able to validate CommentDeleteAction.");
				return result;
			}

			Thread.sleep(2000);

			result = closeCommentPage(driver);
			if (!result) {
				commentPageMsgList.add("Not able to close comment page.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			commentPageMsgList.add("CommentPageUi_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean validateCommentComposeTextField(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForListOfElementsToBeVisible(driver, commentPageOrObj.getCommentComposeTextField(), 30);
			if (!result) {
				commentPageMsgList.add("Comment compose text field is not visible.");
				return result;
			}

			result = cfObj.commonSetTextTextBox(commentPageOrObj.getCommentComposeTextField().get(0), commentText);
			if (!result) {
				commentPageMsgList.add("Not able to enter comment in CommentCompose TextField");
				return result;
			}
			Thread.sleep(2000);

			result = cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getCommentSendBtn(), 20);
			if (!result) {
				commentPageMsgList.add("getCommentSendBtn is not visible.");
				return result;
			}
			cfObj.commonClick(commentPageOrObj.getCommentSendBtn());

			Thread.sleep(2000);

			result = cfObj.commonWaitForListOfElementsToBeVisible(driver, commentPageOrObj.getCommentText(), 20);
			if (!result) {
				commentPageMsgList.add("Commented text is not visible.");
				result = true;
			}
		} catch (Exception e) {
			result = false;
			commentPageMsgList.add("CommentComposeTextField_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean enterAndClickDeepLinkInComposeTextField(AppiumDriver<MobileElement> driver,String deepLink) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getCommentComposeTextField().get(0), 30);
			if(!result) {
				commentPageMsgList.add("Comment compose text field is not visible.");
				return result;
			}
			result=cfObj.commonSetTextTextBox(commentPageOrObj.getCommentComposeTextField().get(0), deepLink);
			if(!result) {
				commentPageMsgList.add("Not able to enter deepLink in CommentCompose TextField");
				return result;
			}
			Thread.sleep(2000);
			cfObj.commonClick(commentPageOrObj.getCommentSendBtn());
			Thread.sleep(2000);
			result=cfObj.commonWaitForElementToBeVisible(driver,commentPageOrObj.getCommentText().get(0), 20);
			if(!result) {
				commentPageMsgList.add("Commented text is not visible.");
				return result;
			}
			cfObj.scrollUtill(driver, 1, direction.DOWN);
			Thread.sleep(1000);
			result=cfObj.commonVerifyValueTextBox(commentPageOrObj.getCommentText().get(commentPageOrObj.getCommentText().size()-1), deepLink);
			if(!result) {
				commentPageMsgList.add("Commented text is not matching.");
				return result;
			}

			cfObj.commonClick(commentPageOrObj.getCommentText().get(commentPageOrObj.getCommentText().size()-1));

		} catch (Exception e) {
			result=false;
			commentPageMsgList.add("enterAndClickDeepLinkInComposeTextField_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean enterOtherDeepLinkAndValidateErrorToast(AppiumDriver<MobileElement> driver,String deepLink) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getCommentComposeTextField().get(0), 30);
			if(!result) {
				commentPageMsgList.add("Comment compose text field is not visible.");
				return result;
			}

			result=cfObj.commonSetTextTextBox(commentPageOrObj.getCommentComposeTextField().get(0), deepLink);
			if(!result) {
				commentPageMsgList.add("Not able to enter deepLink in CommentCompose TextField");
				return result;
			}

			Thread.sleep(2000);

			cfObj.commonClick(commentPageOrObj.getCommentSendBtn());
			
			cfObj.waitTillElementIsVisible(driver, 4, 2000, cfObj.commonGetElement(driver, "fbMessage", "id"));

		} catch (Exception e) {
			result=false;
			commentPageMsgList.add("enterOtherDeepLinkAndValidateErrorToast_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean enterOtherDeepLinkAndValidateCanotShareToast(AppiumDriver<MobileElement> driver,String deepLink) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getCommentComposeTextField().get(0), 30);
			if(!result) {
				commentPageMsgList.add("Comment compose text field is not visible.");
				return result;
			}
			result=cfObj.commonSetTextTextBox(commentPageOrObj.getCommentComposeTextField().get(0), deepLink);
			if(!result) {
				commentPageMsgList.add("Not able to enter deepLink in CommentCompose TextField");
				return result;
			}
			Thread.sleep(2000);
			cfObj.commonClick(commentPageOrObj.getCommentSendBtn());
			if(ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {

				result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@content-desc='This link cannot be shared']", "xpath", 30);
				if(!result) {
					commentPageMsgList.add("Error toast is not visible.");
					return result;
				}
			}

			Thread.sleep(2000);
		} catch (Exception e) {
			result=false;
			commentPageMsgList.add("enterOtherDeepLinkAndValidateCanotShareToast_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean validateCommentComposeTextFieldWithImage(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=upLoadImageCommentThroughGalleryTab(driver);
			if(!result) {
				commentPageMsgList.add("Not able to Upload image comment.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getCommentComposeTextField().get(0), 30);
			if(!result) {
				commentPageMsgList.add("Comment compose text field is not visible.");
				return result;
			}
			result=cfObj.commonSetTextTextBox(commentPageOrObj.getCommentComposeTextField().get(0), commentText);
			if(!result) {
				commentPageMsgList.add("Not able to enter comment in CommentCompose TextField");
				return result;
			}
			Thread.sleep(2000);
			cfObj.commonClick(commentPageOrObj.getCommentSendBtn());
			result=cfObj.commonWaitForElementToBeVisible(driver,commentPageOrObj.getSentComment(), 20);
			if(!result) {
				commentPageMsgList.add("Commented text is not visible.");
				return result;
			}
			Thread.sleep(2000);
			result=cfObj.commonVerifyValueTextBox(commentPageOrObj.getSentComment(), commentText);
			if(!result) {
				commentPageMsgList.add("Commented text is not matching.");
				return result;
			}
		} catch (Exception e) {
			result=false;
			commentPageMsgList.add("CommentComposeTextFieldWithImage_Exception: "+e.getMessage());
		}
		return result;
	}

	public boolean upLoadImageCommentThroughGalleryTab(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getImageUploadBtn(), 30);
			if(!result) {
				commentPageMsgList.add("Image Upload button is not visible.");
				return result;
			}
			cfObj.commonClick(commentPageOrObj.getImageUploadBtn());
			if(cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getOnlyThisTimeBtn(), 10)) {
				cfObj.commonClick(commentPageOrObj.getOnlyThisTimeBtn());
			}

			if(cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getPermissionAllowBtn(), 10)) {
				cfObj.commonClick(commentPageOrObj.getPermissionAllowBtn());
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getTakePhotoBtn(), 10);
			if(!result) {
				commentPageMsgList.add("TakeAPhotoTab is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getSelectGalaryBtn(), 10);
			if(!result) {
				commentPageMsgList.add("SelectFromGalleryTab is not visible.");
				return result;
			}
			cfObj.commonClick(commentPageOrObj.getSelectGalaryBtn());

			result = cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getImageFile(), 30);
			if(!result) {
				commentPageMsgList.add("ImageFile is not visible.");
				return result;
			}

			cfObj.commonClick(commentPageOrObj.getImageFile());
			Thread.sleep(3000);//For handling pageLoading
			result = cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getImageCropBtn(), 10);
			if(!result) {
				commentPageMsgList.add("Crop button is not visible.");
				return result;
			}

			cfObj.commonClick(commentPageOrObj.getImageCropBtn());
			Thread.sleep(4000);
			result = !cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getImageCropBtn(), 10);
			if(!result) {
				commentPageMsgList.add("Crop button is not Clickable.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getUploadingImageView(), 10);
			if(!result) {
				commentPageMsgList.add("UploadingImageView is not visible.");
				return result;
			}

		}catch(Exception e) {
			result=false;
			commentPageMsgList.add("upLoadImageCommentThroughGalleryTab_Exception: "+e.getMessage());
		}return result;
	}

	public boolean validateCommentedPostComponent(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getUserPic().get(commentPageOrObj.getUserPic().size()-1), 30);
			if(!result) {
				commentPageMsgList.add("user picture is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getUserName().get(commentPageOrObj.getUserName().size()-1), 30);
			if(!result) {
				commentPageMsgList.add("user name is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getCommentSentTime().get(commentPageOrObj.getCommentSentTime().size()-1), 30);
			if(!result) {
				commentPageMsgList.add("Comment sent time is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getCommentText().get(commentPageOrObj.getCommentText().size()-1), 30);
			if(!result) {
				commentPageMsgList.add("Comment text is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getLikeIcon().get(commentPageOrObj.getLikeIcon().size()-1), 30);
			if(!result) {
				commentPageMsgList.add("Like icon is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getLikeCountText().get(commentPageOrObj.getLikeCountText().size()-1), 30);
			if(!result) {
				commentPageMsgList.add("LikeCount text is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getCommentReplyBtn().get(commentPageOrObj.getCommentReplyBtn().size()-1), 30);
			if(!result) {
				commentPageMsgList.add("Comment reply button is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getDotIcon().get(commentPageOrObj.getDotIcon().size()-1), 30);
			if(!result) {
				commentPageMsgList.add("Dot icon is not visible.");
				return result;
			}
		} catch (Exception e) {
			result=false;
			commentPageMsgList.add("CommentedPostComponent_Exception: "+e.getMessage());
		}
		return result;
	}

	public boolean validateImageCommentedPostComponent(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getUserPic().get(commentPageOrObj.getUserPic().size()-1), 30);
			if(!result) {
				commentPageMsgList.add("user picture is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getUserName().get(commentPageOrObj.getUserName().size()-1), 30);
			if(!result) {
				commentPageMsgList.add("user name is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getCommentSentTime().get(commentPageOrObj.getCommentSentTime().size()-1), 30);
			if(!result) {
				commentPageMsgList.add("Comment sent time is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getCommentText().get(commentPageOrObj.getCommentText().size()-1), 30);
			if(!result) {
				commentPageMsgList.add("Comment text is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getLikeIcon().get(commentPageOrObj.getLikeIcon().size()-1), 30);
			if(!result) {
				commentPageMsgList.add("Like icon is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getLikeCountText().get(commentPageOrObj.getLikeCountText().size()-1), 30);
			if(!result) {
				commentPageMsgList.add("LikeCount text is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getCommentReplyBtn().get(commentPageOrObj.getCommentReplyBtn().size()-1), 30);
			if(!result) {
				commentPageMsgList.add("Comment reply button is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getDotIcon().get(commentPageOrObj.getDotIcon().size()-1), 30);
			if(!result) {
				commentPageMsgList.add("Dot icon is not visible.");
				return result;
			}
			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getUploadedImageView().get(commentPageOrObj.getUploadedImageView().size()-1), 30);
			if(!result) {
				commentPageMsgList.add("Uploaded image is not visible.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			commentPageMsgList.add("ImageCommentedPostComponent_Exception: "+e.getMessage());
		}
		return result;
	}

	public boolean validateCommentReplyAction(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getCommentReplyBtn().get(commentPageOrObj.getCommentReplyBtn().size()-1), 30);
			if(!result) {
				commentPageMsgList.add("CommentReply button is not visible.");
				return result;
			}
			cfObj.commonClick(commentPageOrObj.getCommentReplyBtn().get(commentPageOrObj.getCommentReplyBtn().size()-1));
			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getCommentReplyBtn().get(commentPageOrObj.getCommentReplyBtn().size()-1), 30);
			if(!result) {
				commentPageMsgList.add("CommentReply button is not visible.");
				return result;
			}
			result=cfObj.commonSetTextTextBox(commentPageOrObj.getCommentComposeTextField().get(0), replyCommentText);
			if(!result) {
				commentPageMsgList.add("Not able to enter comment in CommentCompose TextField");
				return result;
			}

			cfObj.commonClick(commentPageOrObj.getCommentSendBtn());
			Thread.sleep(2000);

			cfObj.commonClick(commentPageOrObj.getCloseBtn());
			cfObj.scrollUtill(driver, 1, direction.DOWN);

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getReplyCommentText().get(commentPageOrObj.getReplyCommentText().size()-1), 10);
			if(!result) {
				commentPageMsgList.add("ReplyComment text is not visible.");
				return result;
			}

			result=cfObj.commonVerifyValueTextBox(commentPageOrObj.getReplyCommentText().get(commentPageOrObj.getReplyCommentText().size()-1), replyCommentText);
			if(!result) {
				commentPageMsgList.add("ReplyComment text is not matching.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			commentPageMsgList.add("validateCommentReplyAction_Exception: "+e.getMessage());
		}
		return result;
	}

	public boolean validateCommentDeleteAction(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getCommentText().get(commentPageOrObj.getCommentText().size() - 1), 30);
			if (!result) {
				commentPageMsgList.add("CommentText is not visible.");
				return result;
			}
			cfObj.commonClick(commentPageOrObj.getDotIcon().get(commentPageOrObj.getDotIcon().size() - 1));

			result = cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getCommentDeleteBtn(), 30);
			if (!result) {
				commentPageMsgList.add("Comment delete button is not visible.");
				return result;
			}
			cfObj.commonClick(commentPageOrObj.getCommentDeleteBtn());

			cfObj.waitTillElementIsVisible(driver, 4, 1000, commentPageOrObj.getDeleteToast());
		} catch (Exception e) {
			result = false;
			commentPageMsgList.add("CommentDeleteAction_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean closeCommentPage(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {

			cfObj.commonClick(commentPageOrObj.getCloseBtn());

			Thread.sleep(4000);

			result=!cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getImageUploadBtn(), 5);
			if(!result) {
				commentPageMsgList.add("Not able to close Comment page.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			commentPageMsgList.add("closeCommentPage_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean clickOnUserName(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			Thread.sleep(3000);
			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getUserName().get(0), 30);
			if(!result) {
				commentPageMsgList.add("User name is not visible in Comment page.");
				return result;
			}
			cfObj.commonClick(commentPageOrObj.getUserName().get(commentPageOrObj.getUserName().size()-1));

		} catch (Exception e) {
			result=false;
			commentPageMsgList.add("clickOnUserName_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean enterRelpyComment(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getCommentReplyBtn().get(commentPageOrObj.getCommentReplyBtn().size()-1), 30);
			if(!result) {
				commentPageMsgList.add("CommentReply button is not visible.");
				return result;
			}
			cfObj.commonClick(commentPageOrObj.getCommentReplyBtn().get(commentPageOrObj.getCommentReplyBtn().size()-1));
			Thread.sleep(2000);
			result=cfObj.commonSetTextTextBox(commentPageOrObj.getCommentComposeTextField().get(0), replyCommentText);
			if(!result) {
				commentPageMsgList.add("Not able to enter comment in CommentCompose TextField");
				return result;
			}

			cfObj.commonClick(commentPageOrObj.getCommentSendBtn());
			Thread.sleep(2000);
			cfObj.scrollUtill(driver, 1, direction.DOWN);

		} catch (Exception e) {
			result=false;
			commentPageMsgList.add("enterRelpyComment_Exception"+e.getMessage());
		}
		return result;
	}


	public boolean validateVideoCommentPage(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getCommentPageHeader(), 30);
			if(!result) {
				commentPageMsgList.add("Comment page header is not visible.");
				return result;
			}
			if(cfObj.commonVerifyValueTextBox(commentPageOrObj.getCommentPageHeader(), "Comments 0 of 0")) {
				result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getEmptyStateImage(), 30);
				if(!result) {
					commentPageMsgList.add("Empty state image is not visible.");
					return result;
				}
				result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getEmptyStateText(), 30);
				if(!result) {
					commentPageMsgList.add("Empty state text is not visible.");
					return result;
				}
			}
			result=validateVideoCommentComposeTextField(driver);
			if(!result) {
				System.out.println("Not able to validate VideoCommentComposeTextField.");
				return result;
			}

			result=validateVideoCommentedPostComponent(driver);
			if(!result) {
				System.out.println("Not able to validate VideoCommentedPostComponent.");
				return result;
			}

			result=validateVideoCommentLikeBtn(driver);
			if(!result) {
				System.out.println("Not able to validate VideoCommentLikeBtn.");
				return result;
			}

			result=validateVideoCommentReplyAction(driver);
			if(!result) {
				System.out.println("Not able to validate VideoCommentReplyAction.");
				return result;
			}

			result=validateVideoCommentReportBtn(driver);
			if(!result) {
				System.out.println("Not able to validate VideoCommentReportBtn.");
				return result;
			}

			cfObj.commonClick(commentPageOrObj.getCloseBtn());

			result=!cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getCommentPageHeader(), 30);
			if(!result) {
				commentPageMsgList.add("Not able to close comment page.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			commentPageMsgList.add("validateVideoCommentPage_Exception"+e.getMessage());
		}
		return result;
	}


	public boolean validateVideoCommentComposeTextField(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getCommentComposeTextField().get(0), 30);
			if(!result) {
				commentPageMsgList.add("Comment compose text field is not visible.");
				return result;
			}
			result=cfObj.commonSetTextTextBox(commentPageOrObj.getCommentComposeTextField().get(0), commentText);
			if(!result) {
				commentPageMsgList.add("Not able to enter comment in CommentCompose TextField");
				return result;
			}
			Thread.sleep(2000);
			cfObj.commonClick(commentPageOrObj.getCommentPostBtn());
			Thread.sleep(2000);
			result=cfObj.commonWaitForElementToBeVisible(driver,commentPageOrObj.getCommentText().get(0), 20);
			if(!result) {
				commentPageMsgList.add("Commented text is not visible.");
				return result;
			}

			result=cfObj.commonVerifyValueTextBox(commentPageOrObj.getCommentText().get(0), commentText);
			if(!result) {
				commentPageMsgList.add("Commented text is not matching.");
				return result;
			}
		} catch (Exception e) {
			result=false;
			commentPageMsgList.add("validateVideoCommentComposeTextField_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean validateVideoCommentedPostComponent(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getProfileIcon().get(0), 30);
			if(!result) {
				commentPageMsgList.add("user picture is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getUserName().get(0), 30);
			if(!result) {
				commentPageMsgList.add("user name is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getCommentSentTime().get(0), 30);
			if(!result) {
				commentPageMsgList.add("Comment sent time is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getCommentText().get(0), 30);
			if(!result) {
				commentPageMsgList.add("Comment text is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getLikeIcon().get(0), 30);
			if(!result) {
				commentPageMsgList.add("Like icon is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getLikeCountText().get(0), 30);
			if(!result) {
				commentPageMsgList.add("LikeCount text is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getCommentReplyBtn().get(0), 30);
			if(!result) {
				commentPageMsgList.add("Comment reply button is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getDotIcon().get(0), 30);
			if(!result) {
				commentPageMsgList.add("Dot icon is not visible.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			commentPageMsgList.add("validateVideoCommentedPostComponent_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean validateVideoCommentLikeBtn(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getLikeCountText().get(0), 30);
			if(!result) {
				commentPageMsgList.add("LikeCount text is not visible.");
				return result;
			}
			int likeCount=(int)(commentPageOrObj.getLikeCountText().get(0).getText().charAt(0)-48);
			cfObj.commonClick(commentPageOrObj.getLikeCountText().get(0));
			Thread.sleep(1000);
			result=validateCommentLikeEmojes(driver);
			if(!result) {
				commentPageMsgList.add("Not able to validate CommentLikeEmojes.");
				return result;
			}

			cfObj.commonClick(commentPageOrObj.getHeartEmoji());

			result=(int)(commentPageOrObj.getLikeCountText().get(0).getText().charAt(0)-48)==likeCount+1;
			if(!result) {
				commentPageMsgList.add("Not able to add like.");
				return result;
			}

			cfObj.commonClick(commentPageOrObj.getLikeCountText().get(0));

			result=validateCommentLikeEmojes(driver);
			if(!result) {
				commentPageMsgList.add("Not able to validate CommentLikeEmojes.");
				return result;
			}

			cfObj.commonClick(commentPageOrObj.getHeartEmoji());

			result=(int)(commentPageOrObj.getLikeCountText().get(0).getText().charAt(0)-48)==likeCount;
			if(!result) {
				commentPageMsgList.add("Not able to remove like.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			commentPageMsgList.add("validateVideoCommentLikeBtn_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean validateCommentLikeEmojes(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getHeartEmoji(), 30);
			if(!result) {
				commentPageMsgList.add("HeartEmoji is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getLikeEmoji(), 30);
			if(!result) {
				commentPageMsgList.add("LikeEmoji is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getLaughEmoji(), 30);
			if(!result) {
				commentPageMsgList.add("LaughEmoji is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getOpenMouthEmoji(), 30);
			if(!result) {
				commentPageMsgList.add("OpenMouthEmoji is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getAngryEmoji(), 30);
			if(!result) {
				commentPageMsgList.add("AngryEmoji is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getSadEmoji(), 30);
			if(!result) {
				commentPageMsgList.add("SadEmojit is not visible.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			commentPageMsgList.add("validateCommentLikeEmojes_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean validateVideoCommentReplyAction(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getCommentReplyBtn().get(0), 30);
			if(!result) {
				commentPageMsgList.add("CommentReply button is not visible.");
				return result;
			}
			cfObj.commonClick(commentPageOrObj.getCommentReplyBtn().get(0));
			result=cfObj.commonSetTextTextBox(commentPageOrObj.getCommentComposeTextField().get(0), replyCommentText);
			if(!result) {
				commentPageMsgList.add("Not able to enter comment in CommentCompose TextField");
				return result;
			}

			cfObj.commonClick(commentPageOrObj.getCommentPostBtn());
			Thread.sleep(2000);

			result=validateVideoCommentLikeBtn(driver);
			if(!result) {
				commentPageMsgList.add("Not able to validate VideoCommentLikeBtn.");
				return result;
			}

			cfObj.commonClick(commentPageOrObj.getCloseBtn());

			result=cfObj.commonWaitForElementToBeLocatedAndVisible(driver, "//*[@text='"+replyCommentText+"']", "xpath", 30);
			if(!result) {
				commentPageMsgList.add("ReplyComment text is not visible.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			commentPageMsgList.add("validateVideoCommentReplyAction_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean validateVideoCommentReportBtn(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getDotIcon().get(0), 30);
			if(!result) {
				commentPageMsgList.add("Report icon is not visible.");
				return result;
			}
			cfObj.commonClick(commentPageOrObj.getDotIcon().get(0));

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getCommentDeleteBtn(), 30);
			if(!result) {
				commentPageMsgList.add("ReportComment button is not visible.");
				return result;
			}
			cfObj.commonClick(commentPageOrObj.getCommentDeleteBtn());

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getReportPopUpScreenTitle(), 30);
			if(!result) {
				commentPageMsgList.add("ReportPopUpScreenTitle is not visible.");
				return result;
			}

			for(int i=0;i<commentPageOrObj.getReportOptionList().size();i++) {
				result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getReportOptionList().get(i), 30);
				if(!result) {
					commentPageMsgList.add("ReportOptionList is not visible.");
					return result;
				}
				result=commentPageOrObj.getReportOptionList().get(i).getAttribute("checked").equalsIgnoreCase("false");
				if(!result) {
					commentPageMsgList.add("ReportOptionList is already checked.");
					return result;
				}

				cfObj.commonClick(commentPageOrObj.getReportOptionList().get(i));

				result=commentPageOrObj.getReportOptionList().get(i).getAttribute("checked").equalsIgnoreCase("true");
				if(!result) {
					commentPageMsgList.add("ReportOptionList is not selected.");
					return result;
				}
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getReportBtn(), 20);
			if (!result) {
				commentPageMsgList.add("Report button is not visible");
				return result;
			}

			cfObj.commonClick(commentPageOrObj.getReportBtn());

			result=cfObj.commonWaitForElementToBeVisible(driver, commentPageOrObj.getCommentReportText().get(0), 30);
			if(!result) {
				commentPageMsgList.add("CommentReportText is not visible.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			commentPageMsgList.add("validateVideoCommentReportBtn_Exception"+e.getMessage());
		}
		return result;
	}
}
