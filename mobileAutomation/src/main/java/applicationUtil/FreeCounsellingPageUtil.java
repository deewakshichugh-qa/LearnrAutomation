package applicationUtil;

import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.FreeCousellingPage_OR;
import util.Common_Function;

public class FreeCounsellingPageUtil {
	
	FreeCousellingPage_OR freeCousellingPageObj;
	public Common_Function cfObj = new Common_Function();

	public FreeCounsellingPageUtil(AppiumDriver<MobileElement> driver) {
		freeCousellingPageObj = new FreeCousellingPage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), freeCousellingPageObj);
	}
	
	public void closeCousellingPage(AppiumDriver<MobileElement> driver) throws Exception {
		try {
			//if couselling page appears
			if(cfObj.commonWaitForElementToBeVisible(driver, freeCousellingPageObj.getTitlePage(), 5)) {
				cfObj.commonClick(freeCousellingPageObj.getBtnCloseCousellingPage());
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
