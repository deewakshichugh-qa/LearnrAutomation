package pageObject;

import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class FilterPage_OR {
	
	@AndroidFindBy(id = "tv_filters")
	private MobileElement filterPageTitle;
	
	@AndroidFindBy(id = "tv_clear_all")
	private MobileElement clearBtn;
	
	@AndroidFindBy(id = "from_textview")
	private MobileElement fromDateTextField;
	
	@AndroidFindBy(id = "to_textview")
	private MobileElement toDateTextField;
	
	@AndroidFindBy(id = "btn_apply")
	private MobileElement applyBtn;

	public MobileElement getFilterPageTitle() {
		return filterPageTitle;
	}

	public MobileElement getClearBtn() {
		return clearBtn;
	}

	public MobileElement getFromDateTextField() {
		return fromDateTextField;
	}

	public MobileElement getToDateTextField() {
		return toDateTextField;
	}

	public MobileElement getApplyBtn() {
		return applyBtn;
	}
	
	/*---------------------------------------CurrentAffairs/JobAlert--------------------------------*/
	
	@AndroidFindBy(id = "btn_ca")
	private MobileElement currentAffairContentTab;
	
	@AndroidFindBy(id = "btn_gk")
	private MobileElement dailyGKUpdateTab;
	
	@AndroidFindBy(id = "btn_all")
	private MobileElement allBtn;
	
	@AndroidFindBy(id = "btn_read")
	private MobileElement readBtn;
	
	@AndroidFindBy(id = "btn_unread")
	private MobileElement unReadBtn;

	public MobileElement getCurrentAffairContentTab() {
		return currentAffairContentTab;
	}

	public MobileElement getDailyGKUpdateTab() {
		return dailyGKUpdateTab;
	}

	public MobileElement getAllBtn() {
		return allBtn;
	}

	public MobileElement getReadBtn() {
		return readBtn;
	}

	public MobileElement getUnReadBtn() {
		return unReadBtn;
	}
	
	/*---------------------------------------Video Page-----------------------------------*/
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@resource-id,'subject_flow_layout')]/android.widget.TextView")
	private List<MobileElement> subjectList;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@resource-id,'other_filters_flow_layout')]/android.widget.TextView")
	private List<MobileElement> statusList;

	public List<MobileElement> getSubjectList() {
		return subjectList;
	}

	public List<MobileElement> getStatusList() {
		return statusList;
	}
	
	/*--------------------MahaPack Filter Screen-----------------------------------*/
	
	@AndroidFindBy(id = "header_text")
	private MobileElement mahaPackFilterScreenTitle;

	public MobileElement getMahaPackFilterScreenTitle() {
		return mahaPackFilterScreenTitle;
	}
	
	@AndroidFindBy(id = "close")
	private MobileElement mahaPackFilterScreenCloseBtn;

	public MobileElement getMahaPackFilterScreenCloseBtn() {
		return mahaPackFilterScreenCloseBtn;
	}
	
	@AndroidFindBy(id = "constraint_layout")
	private MobileElement topicTab;

	public MobileElement getTopicTab() {
		return topicTab;
	}
	
	@AndroidFindBy(id = "search_filter")
	private MobileElement SearchTextField;

	public MobileElement getSearchTextField() {
		return SearchTextField;
	}
	
	@AndroidFindBy(id = "checkBox")
	private List<MobileElement> checkBox;

	public List<MobileElement> getCheckBox() {
		return checkBox;
	}
	
	@AndroidFindBy(id = "apply_filter")
	private MobileElement mahaPackFilterApplyBtn;

	public MobileElement getMahaPackFilterApplyBtn() {
		return mahaPackFilterApplyBtn;
	}
	
	@AndroidFindBy(id = "reset_filter")
	private MobileElement mahaPackFilterResetBtn;

	public MobileElement getMahaPackFilterResetBtn() {
		return mahaPackFilterResetBtn;
	}
	
	
	
	
	

}
