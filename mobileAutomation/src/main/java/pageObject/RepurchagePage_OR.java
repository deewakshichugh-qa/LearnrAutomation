package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class RepurchagePage_OR {

	@AndroidFindBy(id = "com.adda247.app:id/expiryDate")
	private List<MobileElement> CTA;

	public List<MobileElement> getCTA() {
		return CTA;
	}

	@AndroidFindBy(xpath = "//*[@content-desc=\"Repurchase\"]")
	private MobileElement linkRepurchase;

	public MobileElement getLinkRepurchase() {
		return linkRepurchase;
	}

	@AndroidFindBy(xpath = "//*[@content-desc=\"Repurchase\"]")
	private List<MobileElement> linkRepurchaseMany;

	public List<MobileElement> getManyLinkRepurchase() {
		return linkRepurchaseMany;
	}

//-------------------Comparision Slider-------------------------
	@AndroidFindBy(xpath = "//android.widget.Button[contains(@content-desc,\"REPURCHASE\n" +
			"For\")]")
	private MobileElement btnRepurchase;

	public MobileElement getBtnRepurchase() {
		return btnRepurchase;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[contains(@content-desc,\"BUY A MAHAPACK\n" +
			"Starting from\")]")
	private MobileElement btnBuyAMahapack;

	public MobileElement getBtnBuyAMahapack() {
		return btnBuyAMahapack;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"backClick\"]")
	private MobileElement btnCloseSlider;

	public MobileElement btnCloseSlider() {
		return btnCloseSlider;
	}

//------------------Mahapack Recommendation page--------------------

	@AndroidFindBy(xpath = "//*[contains(@content-desc,\"RecommendationSingleItem\")]")
	private List<MobileElement> listSingleMahapack;

	public List<MobileElement> getListSingleMahapack() {
		return listSingleMahapack;
	}
}
