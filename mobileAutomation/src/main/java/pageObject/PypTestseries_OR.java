package pageObject;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.List;

public class PypTestseries_OR {

	@AndroidFindBy(id = "title")
	private MobileElement titlePage;

	public MobileElement getTitlePage() {
		return titlePage;
	}

}