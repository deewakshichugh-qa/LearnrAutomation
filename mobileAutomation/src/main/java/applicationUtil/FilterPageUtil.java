package applicationUtil;

import java.util.ArrayList;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import pageObject.FilterPage_OR;
import util.Common_Function;

public class FilterPageUtil {

	FilterPage_OR filterPageORObj;
	BatchUtil batchUtilObj;
	public ArrayList<String> filterPageMsgList = new ArrayList<String>();
	public Common_Function cfObj = new Common_Function();

	public FilterPageUtil(AppiumDriver<MobileElement> driver) {
		filterPageORObj = new FilterPage_OR();
		PageFactory.initElements(new AppiumFieldDecorator(driver), filterPageORObj);
	}

	public boolean verifyFilterPageUI(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			result = cfObj.commonWaitForElementToBeVisible(driver, filterPageORObj.getFilterPageTitle(), 10);
			if (!result) {
				filterPageMsgList.add("Filter page title is not visible.");
				return result;
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, filterPageORObj.getClearBtn(), 10);
			if (!result) {
				filterPageMsgList.add("Clear button is not visible.");
				return result;
			}
			Thread.sleep(500);

			result = cfObj.commonWaitForElementToBeVisible(driver, filterPageORObj.getFromDateTextField(), 10);
			if (!result) {
				filterPageMsgList.add("FromDate Text field is not visible.");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, filterPageORObj.getToDateTextField(), 10);
			if (!result) {
				filterPageMsgList.add("ToDate Text field is not visible.");
			}

			result = cfObj.commonWaitForElementToBeVisible(driver, filterPageORObj.getApplyBtn(), 10);
			if (!result) {
				filterPageMsgList.add("Apply button is not visible.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			filterPageMsgList.add("FilterPageUI_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean clickOnContentTab(AppiumDriver<MobileElement> driver,MobileElement element,String attribute) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, element, 10);
			if(!result) {
				filterPageMsgList.add(element.getText()+" Tab is not visible.");
				return result;
			}
			if(element.getAttribute(attribute).equals("true")) {
				cfObj.commonClick(element);
				result=element.getAttribute(attribute).equals("false");
				if(!result) {
					filterPageMsgList.add("Not able to click "+element.getText()+"Tab.");
					return result;
				}
			}
			else {
				cfObj.commonClick(element);
				result=element.getAttribute(attribute).equals("true");
				if(!result) {
					filterPageMsgList.add("Not able to click "+element.getText()+"Tab.");
					return result;
				}
			}

		} catch (Exception e) {
			result=false;
			filterPageMsgList.add("ContentTab_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean clickOnStatusTab(AppiumDriver<MobileElement> driver,MobileElement element,String attribute) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, element, 10);
			if(!result) {
				filterPageMsgList.add(element.getText()+" Tab is not visible.");
				return result;
			}
			if(element.getAttribute(attribute).equals("true")) {
				cfObj.commonClick(element);
				result=element.getAttribute(attribute).equals("false");
				if(!result) {
					filterPageMsgList.add("Not able to click "+element.getText()+"Tab.");
					return result;
				}
			}
			else {
				cfObj.commonClick(element);
				result=element.getAttribute(attribute).equals("true");
				if(!result) {
					filterPageMsgList.add("Not able to click "+element.getText()+"Tab.");
					return result;
				}
			}

		} catch (Exception e) {
			result=false;
			filterPageMsgList.add("StatusTab_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean clickOnClearAllBtn(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, filterPageORObj.getClearBtn(), 10);
			if(!result) {
				filterPageMsgList.add("ClearAll button is not visible.");
				return result;
			}
			cfObj.commonClick(filterPageORObj.getClearBtn());


		} catch (Exception e) {
			result=false;
			filterPageMsgList.add("ClearAllBtn_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean clickOnApplyBtn(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, filterPageORObj.getApplyBtn(), 10);
			if(!result) {
				filterPageMsgList.add("Apply button is not visible.");
				return result;
			}
			cfObj.commonClick(filterPageORObj.getApplyBtn());


		} catch (Exception e) {
			result=false;
			filterPageMsgList.add("ApplyBtn_Exception"+e.getMessage());
		}
		return result;
	}

	public boolean validateMahaPackFilterPageUI(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, filterPageORObj.getMahaPackFilterScreenTitle(), 10);
			if(!result) {
				filterPageMsgList.add("MahaPackFilterScreenTitle is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, filterPageORObj.getMahaPackFilterScreenCloseBtn(), 10);
			if(!result) {
				filterPageMsgList.add("MahaPackFilterScreenCloseBtn is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, filterPageORObj.getMahaPackFilterApplyBtn(), 10);
			if(!result) {
				filterPageMsgList.add("MahaPackFilterApplyBtn is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, filterPageORObj.getMahaPackFilterResetBtn(), 10);
			if(!result) {
				filterPageMsgList.add("MahaPackFilterResetBtn is not visible.");
				return result;
			}

			result=cfObj.commonWaitForElementToBeVisible(driver, filterPageORObj.getSearchTextField(), 10);
			if(!result) {
				filterPageMsgList.add("SearchTextField is not visible.");
				return result;
			}

		} catch (Exception e) {
			result=false;
			filterPageMsgList.add("validateMahaPackFilterPageUI_Exception"+e.getMessage());	
		}
		return result;
	}

	public boolean clickOnMahaPackFilterPageCloseBtn(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, filterPageORObj.getMahaPackFilterScreenCloseBtn(), 10);
			if(!result) {
				filterPageMsgList.add("Filter screen close button is not visible");
				return result;
			}

			cfObj.commonClick(filterPageORObj.getMahaPackFilterScreenCloseBtn());
			batchUtilObj=new BatchUtil(driver);
			result=cfObj.commonWaitForElementToBeVisible(driver, batchUtilObj.batchPageObj.getFilterBtn(), 10);
			if(!result) {
				filterPageMsgList.add("Filter button is not visible");
				return result;
			}

		} catch (Exception e) {
			result=false;
			filterPageMsgList.add("clickOnMahaPackFilterPageCloseBtn_Exception"+e.getMessage());		
		}
		return result;
	}

	public boolean clickOnFilterApplyBtn(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, filterPageORObj.getCheckBox().get(0), 10);
			if(!result) {
				filterPageMsgList.add("CheckBox is not visible");
				return result;
			}

			cfObj.commonClick(filterPageORObj.getCheckBox().get(0));
			result=filterPageORObj.getCheckBox().get(0).getAttribute("checked").equalsIgnoreCase("true");
			if(!result) {
				filterPageMsgList.add("CheckBox is not selected.");
				return result;
			}

			cfObj.commonClick(filterPageORObj.getMahaPackFilterApplyBtn());

			batchUtilObj=new BatchUtil(driver);
			result=cfObj.commonWaitForElementToBeVisible(driver, batchUtilObj.batchPageObj.getFilterBtn(), 10);
			if(!result) {
				filterPageMsgList.add("Filter button is not visible");
				return result;
			}

		} catch (Exception e) {
			result=false;
			filterPageMsgList.add("clickOnFilterApplyBtn_Exception"+e.getMessage());		
		}
		return result;
	}

	public boolean clickOnFilterResetBtn(AppiumDriver<MobileElement> driver) {
		boolean result=true;
		try {
			result=cfObj.commonWaitForElementToBeVisible(driver, filterPageORObj.getMahaPackFilterResetBtn(), 10);
			if(!result) {
				filterPageMsgList.add("MahaPackFilterResetBtn is not visible");
				return result;
			}

			cfObj.commonClick(filterPageORObj.getMahaPackFilterResetBtn());

			batchUtilObj=new BatchUtil(driver);
			result=cfObj.commonWaitForElementToBeVisible(driver, batchUtilObj.batchPageObj.getFilterBtn(), 10);
			if(!result) {
				filterPageMsgList.add("Filter button is not visible");
				return result;
			}

		} catch (Exception e) {
			result=false;
			filterPageMsgList.add("clickOnFilterResetBtn_Exception"+e.getMessage());		
		}
		return result;
	}
}
