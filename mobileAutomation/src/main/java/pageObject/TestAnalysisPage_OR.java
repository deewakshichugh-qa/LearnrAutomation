package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class TestAnalysisPage_OR {
	
	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Navigate up\"]")
	private MobileElement btnNavigateUp;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Test Analysis']")
	private MobileElement titlePage;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='29July Fixedmock ALPHA]")
	private MobileElement titleTest;
	
	@AndroidFindBy(id = "com.adda247.app:id/reattempt")
	private MobileElement btnReattempt;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='SHARE SCORECARD']")
	private MobileElement btnShareScorecard;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='POST NOW']")
	private MobileElement linkPostNow;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Top Rankers']")
	private MobileElement titleTopRankers;
	
//------------------------------------------------------------------------------------------------------
	
	public MobileElement getBtnNavigateUp() {
		return btnNavigateUp;
	}
	
	public MobileElement getTitlePage() {
		return titlePage;
	}
	
	public MobileElement getTitleTest() {
		return titleTest;
	}

	public MobileElement getBtnReattempt() {
		return btnReattempt;
	}

	public MobileElement getBtnShareScorecard() {
		return btnShareScorecard;
	}

	public MobileElement getLinkPostNow() {
		return linkPostNow;
	}

	public MobileElement getTitleTopRankers() {
		return titleTopRankers;
	}



}
