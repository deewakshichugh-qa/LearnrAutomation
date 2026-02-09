package applicationUtil;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.CertificatePreviewPage_OR;
import util.Common_Function;
import util.Common_Function.key;

public class CertificatePreviewUtil {

	CertificatePreviewPage_OR certificatePreviewUtilORObj;
	ChildPackageUtil childPackageUtilObj;
	List<String> certificatePreviewMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();

	public CertificatePreviewUtil(AppiumDriver<MobileElement> driver) {
		certificatePreviewUtilORObj = new CertificatePreviewPage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), certificatePreviewUtilORObj);
	}

	public boolean validateCertificatePreviewBeforePurchase(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, certificatePreviewUtilORObj.getCertificatePreviewPageTitle(), 10);
			if(!result) {
				certificatePreviewMsgList.add("Certificate Preview Page title is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, certificatePreviewUtilORObj.getCertificatePreviewScreen(), 30);
			if(!result) {
				certificatePreviewMsgList.add("CertificatePreviewScreen is not visible.");
				return result;
			}

			cfObj.commonClick(certificatePreviewUtilORObj.getBackBtn());

			result=!cfObj.commonWaitForElementToBeVisible(driver, certificatePreviewUtilORObj.getCertificatePreviewPageTitle(), 10);
			if(!result) {
				certificatePreviewMsgList.add("Not able to close Certificate Preview Page.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			certificatePreviewMsgList.add("validateCertificatePreviewBeforePurchase_Exception"+e.getMessage());
		}
		return result;

	}

	public boolean validateCertificatePreview(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, certificatePreviewUtilORObj.getCertificatePreviewPageTitle(), 10);
			if(!result) {
				certificatePreviewMsgList.add("Certificate Preview Page title is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, certificatePreviewUtilORObj.getCertificatePreviewScreen(), 30);
			if(!result) {
				certificatePreviewMsgList.add("CertificatePreviewScreen is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, certificatePreviewUtilORObj.getEditDetailsBtn(), 10);
			if(!result) {
				certificatePreviewMsgList.add("EditDetailsBtn is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, certificatePreviewUtilORObj.getSubmitDetailsBtn(), 10);
			if(!result) {
				certificatePreviewMsgList.add("SubmitDetailsBtn is not visible.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			certificatePreviewMsgList.add("validateCertificatePreview_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean validateEditDetailsBtn(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {

			result=cfObj.commonWaitForElementToBeVisible(driver, certificatePreviewUtilORObj.getEditDetailsBtn(), 10);
			if(!result) {
				certificatePreviewMsgList.add("EditDetailsBtn is not visible.");
				return result;
			}

			cfObj.commonClick(certificatePreviewUtilORObj.getEditDetailsBtn());
			Thread.sleep(2000);

			childPackageUtilObj=new ChildPackageUtil(driver);

			result=cfObj.commonWaitForElementToBeVisible(driver, childPackageUtilObj.packagePageObj.getCandidateDetailsPopUpTitle(), 10);
			if(!result) {
				certificatePreviewMsgList.add("CandidateDetailsPopUpTitle is not visible.");
				return result;
			}

			cfObj.commonClick(childPackageUtilObj.packagePageObj.getSubmitBtn());

			result=cfObj.commonWaitForElementToBeVisible(driver, certificatePreviewUtilORObj.getEditDetailsBtn(), 10);
			if(!result) {
				certificatePreviewMsgList.add("EditDetailsBtn is not visible.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			certificatePreviewMsgList.add("validateEditDetailsBtn_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean validateSubmitDetailsBtn(AppiumDriver<MobileElement> driver,String imagePath) {
		boolean result=true;
		try {

			result=cfObj.commonWaitForElementToBeVisible(driver, certificatePreviewUtilORObj.getSubmitDetailsBtn(), 10);
			if(!result) {
				certificatePreviewMsgList.add("SubmitDetailsBtn is not visible.");
				return result;
			}

			cfObj.commonClick(certificatePreviewUtilORObj.getSubmitDetailsBtn());

			
//			Thread.sleep(2000);
//			result=cfObj.compareTwoImages(driver, imagePath, imagePath, 95);
//			if (!result) {
//				certificatePreviewMsgList.add("Unable to compare "+ imagePath +".");
//				return result;
//			}

			result=validateCertificateShareAndDownloadBtn(driver);
			if(!result) {
				certificatePreviewMsgList.add("Not able to validate CertificateShareAndDownloadBtn.");
				return result;
			}
			

		} catch (Exception e) {
			result=false;
			certificatePreviewMsgList.add("validateSubmitDetailsBtn_Exception"+e.getMessage());
		}
		return result;
	}
	
	public boolean validateCertificateShareAndDownloadBtn(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {

			result=cfObj.commonWaitForElementToBeVisible(driver, certificatePreviewUtilORObj.getCertificateShareBtn(), 10);
			if(!result) {
				certificatePreviewMsgList.add("CertificateShareBtn is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, certificatePreviewUtilORObj.getCertificateDownloadBtn(), 30);
			if(!result) {
				certificatePreviewMsgList.add("CertificateDownloadBtn is not visible.");
				return result;
			}

			cfObj.commonClick(certificatePreviewUtilORObj.getCertificateDownloadBtn());
			if(cfObj.commonWaitForElementToBeVisible(driver, certificatePreviewUtilORObj.getPermissionAllowBtn(), 10)) {
				cfObj.commonClick(certificatePreviewUtilORObj.getPermissionAllowBtn());
			}
			
			if(cfObj.commonWaitForElementToBeVisible(driver,certificatePreviewUtilORObj.getCertificateScreen(), 10)) {
				cfObj.pressAndroidKey(driver, key.BACK, 1);
			}
			Thread.sleep(3000);
			
			cfObj.commonClick(certificatePreviewUtilORObj.getCertificateShareBtn());
			if(cfObj.commonWaitForElementToBeVisible(driver, certificatePreviewUtilORObj.getPermissionAllowBtn(), 10)) {
				cfObj.commonClick(certificatePreviewUtilORObj.getPermissionAllowBtn());
			}
			
			result=cfObj.commonWaitForElementToBeVisible(driver, certificatePreviewUtilORObj.getShareScreen(), 10);
			if(!result) {
				certificatePreviewMsgList.add("Share screen is not visible.");
				return result;
			}

			result=cfObj.pressAndroidKey(driver, key.BACK, 1);
			if(!result) {
				certificatePreviewMsgList.add("Not able to click Back button.");
				return result;
			}
			
			result=cfObj.commonWaitForElementToBeVisible(driver, certificatePreviewUtilORObj.getCertificateShareBtn(), 10);
			if(!result) {
				certificatePreviewMsgList.add("CertificateShareBtn is not visible.");
				return result;
			}
			
			cfObj.commonClick(certificatePreviewUtilORObj.getBackBtn());

		} catch (Exception e) {
			result=false;
			certificatePreviewMsgList.add("validateCertificateShareAndDownloadBtn_Exception"+e.getMessage());
		}
		return result;
	}
}
