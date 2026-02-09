package pageObject;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CertificatePreviewPage_OR {
	
	@AndroidFindBy(xpath = "//*[@text='Certificate Preview']")
	private MobileElement certificatePreviewPageTitle;

	public MobileElement getCertificatePreviewPageTitle() {
		return certificatePreviewPageTitle;
	}
	
	@AndroidFindBy(accessibility = "Navigate up")
	private MobileElement backBtn;

	public MobileElement getBackBtn() {
		return backBtn;
	}
	
	@AndroidFindAll({
		@AndroidBy(id = "com.google.android.apps.docs:id/pdf_view"),
		@AndroidBy(id = "android:id/chooser_header")
	})
	private MobileElement certificateScreen;

	public MobileElement getCertificateScreen() {
		return certificateScreen;
	}

	@AndroidFindBy(id = "pdfView")
	private MobileElement certificatePreviewScreen;

	public MobileElement getCertificatePreviewScreen() {
		return certificatePreviewScreen;
	}
	
	@AndroidFindBy(id = "edit_cta")
	private MobileElement editDetailsBtn;

	public MobileElement getEditDetailsBtn() {
		return editDetailsBtn;
	}
	
	@AndroidFindBy(id = "submit_cta")
	private MobileElement submitDetailsBtn;

	public MobileElement getSubmitDetailsBtn() {
		return submitDetailsBtn;
	}
	
	@AndroidFindBy(id = "download_cta")
	private MobileElement certificateDownloadBtn;

	public MobileElement getCertificateDownloadBtn() {
		return certificateDownloadBtn;
	}
	
	@AndroidFindBy(id = "share_cta")
	private MobileElement certificateShareBtn;

	public MobileElement getCertificateShareBtn() {
		return certificateShareBtn;
	}
	
	@AndroidFindBy(id = "android:id/resolver_list")
	private MobileElement shareScreen;

	public MobileElement getShareScreen() {
		return shareScreen;
	}
	
	@AndroidFindBy(id = "com.google.android.apps.docs:id/pdf_view")
	private MobileElement downloadedCertificatePreview;

	public MobileElement getDownloadedCertificatePreview() {
		return downloadedCertificatePreview;
	}
	
	@AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
	private MobileElement permissionAllowBtn;

	public MobileElement getPermissionAllowBtn() {
		return permissionAllowBtn;
	}
	
	
}
