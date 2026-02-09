package pageObject;

import io.appium.java_client.MobileElement;
import java.util.List;

import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class PaymentPage_OR {

	// ------------------SELECT PAYMENT METHOD-----------------------------

	@AndroidFindBy(id = "tv_header")
	private List<MobileElement> optionPayment;

	public List<MobileElement> getListOptionPayment() {
		return optionPayment;
	}

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id=\"com.adda247.app:id/close\"]/android.widget.ImageView")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Preferred Payment Methods']/preceding-sibling::XCUIElementTypeButton")
	private MobileElement btnClose;

	public MobileElement getBtnClose() {
		return btnClose;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"UPI\"]")
	@iOSXCUITFindBy(xpath = "//*[@name='UPI']")
	private MobileElement optionUPI;

	public MobileElement getOptionUPI() {
		return optionUPI;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Debit Card\"]")
	@iOSXCUITFindBy(xpath = "//*[@name='Card']")
	private MobileElement optionDebitCard;

	public MobileElement getOpitonDebitCard() {
		return optionDebitCard;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Credit Card\"]")
	@iOSXCUITFindBy(xpath = "//*[@name='Card']")
	private MobileElement optionCreditCard;

	public MobileElement getOpitonCreditCard() {
		return optionCreditCard;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"EMI\"]")
	@iOSXCUITFindBy(xpath = "//*[@name='EMI']")
	private MobileElement optionEMI;

	public MobileElement getOptionEMI() {
		return optionEMI;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Net Banking\"]")
	private MobileElement optionNetbanking;

	public MobileElement getOptionNetbanking() {
		return optionNetbanking;
	}

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Other Wallets\"]")
	@iOSXCUITFindBy(xpath = "//*[@name='Wallet']")
	private MobileElement optionOtherWallets;

	public MobileElement getOptionOtherWallets() {
		return optionOtherWallets;
	}

	@AndroidFindBy(xpath = "//android.view.View[@content-desc=\"Confirm Your Plan\"]")
	private MobileElement selectPaymentPageTitle;

	public MobileElement getSelectPaymentPageTitle() {
		return selectPaymentPageTitle;
	}

	@AndroidFindBy(id = "ll_loader")
	private MobileElement paymentLoaderIcon;

	public MobileElement getPaymentLoaderIcon() {
		return paymentLoaderIcon;
	}

	@AndroidFindBy(id = "progressBar")
	private MobileElement pageLoaderIcon;

	public MobileElement getPageLoaderIcon() {
		return pageLoaderIcon;
	}

	@AndroidFindBy(id = "check_list")
	private MobileElement checkListPopUp;

	public MobileElement getCheckListPopUp() {
		return checkListPopUp;
	}

	/*---------------------------------UPI TYPE PAYMENT PAGE--------------------- --------*/

	@AndroidFindBy(xpath = "//*[@resource-id='logo']")
	@iOSXCUITFindBy(xpath = "//*[@name='Adda247_App Icon']")
	private MobileElement upiPaymentPageLogo;

	public MobileElement getUpiPaymentPageLogo() {
		return upiPaymentPageLogo;
	}

	@AndroidFindAll({ @AndroidBy(xpath = "//*[@text='Adda247']"), @AndroidBy(xpath = "//*[@text='SankalpBharat']") })
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Adda247']")
	private MobileElement upiPaymentPageHeader;

	public MobileElement getUpiPaymentPageHeader() {
		return upiPaymentPageHeader;
	}

	@AndroidFindBy(xpath = "//android.view.View[@resource-id='header-wrapper']/descendant::android.widget.Button")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Adda247']/following-sibling::XCUIElementTypeButton[1]")
	private MobileElement upiPaymentPageCloseBtn;

	public MobileElement getUpiPaymentPageCloseBtn() {
		return upiPaymentPageCloseBtn;
	}

	@AndroidFindBy(xpath = "//android.view.View[@resource-id='header-wrapper']/descendant::android.widget.Button/following-sibling::android.view.View")
	@iOSXCUITFindBy(xpath = "//*[@name='This page will timeout in']/parent::XCUIElementTypeOther/preceding-sibling::XCUIElementTypeOther[1] ")
	private MobileElement upiPaymentLanguageChangeBtn;

	public MobileElement getUpiPaymentLanguageChangeBtn() {
		return upiPaymentLanguageChangeBtn;
	}

	@AndroidFindBy(xpath = "//*[@text='Change language']")
	@iOSXCUITFindBy(xpath = "//*[@name='Change language']")
	private MobileElement changeLanguagePageTitle;

	public MobileElement getChangeLanguagePageTitle() {
		return changeLanguagePageTitle;
	}

	@AndroidFindBy(xpath = "//*[@text='Change language']/following-sibling::android.widget.Button")
	@iOSXCUITFindBy(xpath = "//*[@name='Change language']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeButton")
	private MobileElement changeLanguagePageCloseBtn;

	public MobileElement getChangeLanguagePageCloseBtn() {
		return changeLanguagePageCloseBtn;
	}

	@AndroidFindBy(xpath = "//*[@text='Change language']/following-sibling::android.widget.ListView/android.view.View")
	@iOSXCUITFindBy(xpath = "//*[@name='Change language']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeOther/descendant::XCUIElementTypeButton")
	private List<MobileElement> languageTypeList;

	public List<MobileElement> getLanguageTypeList() {
		return languageTypeList;
	}

	@AndroidFindAll({ @AndroidBy(xpath = "//*[contains(@text,'Pay With UPI ID')]"),
			@AndroidBy(xpath = "//*[contains(@text,'Pay Using UPI ID')]") })
	@iOSXCUITFindBy(xpath = "//*[@name='Pay Using UPI ID']")
	private MobileElement upiFormTitle;

	public MobileElement getUpiFormTitle() {
		return upiFormTitle;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@text='View Details']/preceding-sibling::android.widget.TextView")
	@iOSXCUITFindBy(xpath = "//*[@name='Secured by']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeOther/XCUIElementTypeStaticText")
	private MobileElement packagePrice;

	public MobileElement getPackagePrice() {
		return packagePrice;
	}

	@AndroidFindBy(xpath = "//android.view.View[@resource-id='new-vpa-field-upi']")
	@iOSXCUITFindBy(xpath = "//*[@name='UPI ID']")
	private MobileElement upiIdTextFieldHeader;

	public MobileElement getUpiIdTextFieldHeader() {
		return upiIdTextFieldHeader;
	}

	@AndroidFindBy(xpath = "//android.widget.EditText")
	@iOSXCUITFindBy(className = "XCUIElementTypeTextField")
	private MobileElement upiIdTextField;

	public MobileElement getUpiIdTextField() {
		return upiIdTextField;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Account']")
	private MobileElement accountTab;

	public MobileElement getAccountTab() {
		return accountTab;
	}

	@AndroidFindBy(xpath = "//*[@text='Edit contact details']")
	private MobileElement editContactDetailsTab;

	public MobileElement getEditContactDetailsTab() {
		return editContactDetailsTab;
	}

	@AndroidFindBy(xpath = "//*[@text='Edit Contact Details']")
	private MobileElement editContactdetailsPageTitle;

	public MobileElement getEditContactdetailsPageTitle() {
		return editContactdetailsPageTitle;
	}

	@AndroidFindBy(xpath = "//android.view.View[@resource-id='payment-details-block']/preceding-sibling::android.widget.Button")
	private MobileElement editContactdetailsPageCloseBtn;

	public MobileElement getEditContactdetailsPageCloseBtn() {
		return editContactdetailsPageCloseBtn;
	}

	@AndroidFindBy(xpath = "//*[@text='Phone Number']/preceding-sibling::android.widget.EditText")
	private MobileElement phoneNumberTextField;

	public MobileElement getPhoneNumberTextField() {
		return phoneNumberTextField;
	}

	@AndroidFindBy(xpath = "//*[@text='Email']/preceding-sibling::android.widget.EditText")
	private MobileElement emailTextField;

	public MobileElement getEmailTextField() {
		return emailTextField;
	}

	@AndroidFindBy(xpath = "//*[@text='Continue']")
	private MobileElement continueBtn;

	public MobileElement getContinueBtn() {
		return continueBtn;
	}

	@AndroidFindBy(xpath = "//*[@text='Change language']/parent::android.view.View")
	private MobileElement changeLangugeTab;

	public MobileElement getChangeLangugeTab() {
		return changeLangugeTab;
	}

	@AndroidFindBy(xpath = "//*[@text='Account Change language']")
	private MobileElement accountChangeLanguageTitle;

	public MobileElement getAccountChangeLanguageTitle() {
		return accountChangeLanguageTitle;
	}

	@AndroidFindBy(xpath = "//*[@text='Account Change language']/following-sibling::android.widget.Button[not(@text='Back')]")
	private MobileElement accountChangeLanguageCloseBtn;

	public MobileElement getAccountChangeLanguageCloseBtn() {
		return accountChangeLanguageCloseBtn;
	}

	@AndroidFindBy(xpath = "//*[@text='Edit contact details']/preceding-sibling::android.widget.Button")
	private MobileElement accountPopUpWindowCloseBtn;

	public MobileElement getAccountPopUpWindowCloseBtn() {
		return accountPopUpWindowCloseBtn;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@text='View Details']")
	@iOSXCUITFindBy(xpath = "//*[@name='View Details']")
	private MobileElement viewDetailsBtn;

	public MobileElement getViewDetailsBtn() {
		return viewDetailsBtn;
	}

	@AndroidFindBy(xpath = "//*[contains(@text,'summary')]")
	@iOSXCUITFindBy(xpath = "//*[contains(@text,'summary')]")
	private MobileElement getPriceSummaryTitle;

	public MobileElement getPriceSummaryTitle() {
		return getPriceSummaryTitle;
	}

	@AndroidFindBy(xpath = "//*[@text='Order Summary']/following-sibling::android.view.View/android.widget.Image")
	@iOSXCUITFindBy(xpath = "//*[@name='Adda IOS Defence']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeOther[1]")
	private MobileElement orderSummaryCloseBtn;

	public MobileElement getOrderSummaryCloseBtn() {
		return orderSummaryCloseBtn;
	}

	@AndroidFindBy(xpath = "//*[@text='Grand Total']")
	@iOSXCUITFindBy(xpath = "//*[@name='Grand Total']")
	private MobileElement grandTotalText;

	public MobileElement grandTotalText() {
		return grandTotalText;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@text='Pay Now']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Pay Now']")
	private MobileElement payNowBtn;

	public MobileElement getPayNowBtn() {
		return payNowBtn;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@text='Continue']")
	private MobileElement continueBtnForPayment;

	public MobileElement continueBtnForPayment() {
		return continueBtnForPayment;
	}

	/*-------------------------------------------PAYTM TYPE PAYMENT PAGE------------------------------*/

	@AndroidFindBy(xpath = "//*[@text='ADDA247']")
	private MobileElement paytmFormTitle;

	public MobileElement getPaytmFormTitle() {
		return paytmFormTitle;
	}

	@AndroidFindBy(xpath = "//android.view.View[@resource-id='ptm-checkout-header']/descendant::android.widget.Button")
	private MobileElement paytmFormBackBtn;

	public MobileElement getPaytmFormBackBtn() {
		return paytmFormBackBtn;
	}

	@AndroidFindBy(xpath = "//*[@text='Select an option to pay']")
	private MobileElement selectAnOptionToPayHeader;

	public MobileElement getSelectAnOptionToPayHeader() {
		return selectAnOptionToPayHeader;
	}

	@AndroidFindBy(xpath = "//*[@text='Login to Pay with Paytm']")
	private MobileElement logInToPayWithPaytmHeader;

	public MobileElement getLogInToPayWithPaytmHeader() {
		return logInToPayWithPaytmHeader;
	}

	@AndroidFindAll({ @AndroidBy(xpath = "//android.widget.EditText[@resource-id='mobile_input']"),
			@AndroidBy(xpath = "//*[@text='+91']/following-sibling::android.widget.EditText") })
	private MobileElement paytmMobileNumberTextField;

	public MobileElement getPaytmMobileNumberTextField() {
		return paytmMobileNumberTextField;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@text='Proceed Securely']")
	private MobileElement proceedSecurelyBtn;

	public MobileElement getProceedSecurelyBtn() {
		return proceedSecurelyBtn;
	}

	@AndroidFindBy(xpath = "//*[@text='More Payment Options']")
	private MobileElement otherPayMentOptionHeader;

	public MobileElement getOtherPayMentOptionHeader() {
		return otherPayMentOptionHeader;
	}

	@AndroidFindAll({ @AndroidBy(xpath = "//android.view.View[@resource-id='checkout-card']"),
			@AndroidBy(xpath = "//android.widget.TextView[contains(@text,'Debit and Credit Cards')]") })
	private MobileElement checkOutCardTab;

	public MobileElement getCheckOutCardTab() {
		return checkOutCardTab;
	}

	@AndroidFindBy(xpath = "//*[@text='Enter Debit or Credit Card details']")
	private MobileElement checkOutCardPopUpTitle;

	public MobileElement getCheckOutCardPopUpTitle() {
		return checkOutCardPopUpTitle;
	}

	@AndroidFindBy(xpath = "//*[@text='Enter Debit or Credit Card details']/following-sibling::android.view.View[not(@resource-id)]")
	private MobileElement checkOutCardPopUpCloseBtn;

	public MobileElement getCheckOutCardPopUpCloseBtn() {
		return checkOutCardPopUpCloseBtn;
	}

	@AndroidFindAll({ @AndroidBy(xpath = "//android.widget.EditText[@resource-id='cardNumber']"),
			@AndroidBy(xpath = "//android.widget.TextView[@text='Card Number']/preceding-sibling::android.widget.EditText") })
	private MobileElement cardNumberTextField;

	public MobileElement getCardNumberTextField() {
		return cardNumberTextField;
	}

	@AndroidFindAll({ @AndroidBy(xpath = "//android.widget.EditText[@resource-id='cardExpirationMonth']"),
			@AndroidBy(xpath = "//android.widget.TextView[@text='Expiry / Validity']/preceding-sibling::android.widget.EditText[1]") })
	private MobileElement cardExpirationMonthTextField;

	public MobileElement getCardExpirationMonthTextField() {
		return cardExpirationMonthTextField;
	}

	@AndroidFindAll({ @AndroidBy(xpath = "//android.widget.EditText[@resource-id='cardExpirationYear']"),
			@AndroidBy(xpath = "//android.widget.TextView[@text='Expiry / Validity']/preceding-sibling::android.widget.EditText[2]") })
	private MobileElement cardExpirationYearTextField;

	public MobileElement getCardExpirationYearTextField() {
		return cardExpirationYearTextField;
	}

	@AndroidFindAll({ @AndroidBy(xpath = "//android.widget.EditText[@resource-id='cvv']"),
			@AndroidBy(xpath = "//android.widget.TextView[@text='CVV']/preceding-sibling::android.widget.EditText") })
	private MobileElement cvvTextField;

	public MobileElement getCvvTextField() {
		return cvvTextField;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[contains(@text,'Pay')]")
	private MobileElement payButton;

	public MobileElement getPayButton() {
		return payButton;
	}

	@AndroidFindAll({ @AndroidBy(xpath = "//android.view.View[@resource-id='checkout-upi']"),
			@AndroidBy(xpath = "//android.widget.TextView[@text='UPI']") })
	private MobileElement checkOutUpiTab;

	public MobileElement getCheckOutUpiTab() {
		return checkOutUpiTab;
	}

	@AndroidFindBy(xpath = "//*[@text='Enter UPI ID']")
	private MobileElement checkOutUpiPopUpTitle;

	public MobileElement getCheckOutUpiPopUpTitle() {
		return checkOutUpiPopUpTitle;
	}

	@AndroidFindAll({
			@AndroidBy(xpath = "//android.view.View[@text='Note: UPI Credit Card Transactions are not allowed on this merchant']/preceding-sibling::android.view.View[not(@text='Enter UPI ID')]"),
			@AndroidBy(xpath = "//*[@text='Enter UPI ID']/following-sibling::android.widget.TextView[1]") })
	private MobileElement checkOutUpiPopUpCloseBtn;

	public MobileElement getCheckOutUpiPopUpCloseBtn() {
		return checkOutUpiPopUpCloseBtn;
	}

	@AndroidFindAll({ @AndroidBy(xpath = "//android.widget.EditText[@resource-id='ptm-upi-input']"),
			@AndroidBy(xpath = "//*[@content-desc='Know more']/following-sibling::android.view.View/android.widget.EditText") })
	private MobileElement enterVpaTextField;

	public MobileElement getEnterVpaTextField() {
		return enterVpaTextField;
	}

	@AndroidFindBy(accessibility = "Verify")
	private MobileElement mobileNumberVerifyBtn;

	public MobileElement getMobileNumberVerifyBtn() {
		return mobileNumberVerifyBtn;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@text='Proceed']")
	private MobileElement proceedBtn;

	public MobileElement getProceedBtn() {
		return proceedBtn;
	}

	@AndroidFindAll({ @AndroidBy(xpath = "//android.view.View[@resource-id='checkout-nb']"),
			@AndroidBy(xpath = "//android.widget.TextView[@text='Net Banking']") })
	private MobileElement checkOutNetBanking;

	public MobileElement getCheckOutNetBanking() {
		return checkOutNetBanking;
	}

	@AndroidFindBy(xpath = "//*[@text='Select your Bank']")
	private MobileElement checkOutNetBankingPopUpTitle;

	public MobileElement getCheckOutNetBankingPopUpTitle() {
		return checkOutNetBankingPopUpTitle;
	}

	@AndroidFindBy(xpath = "//*[@text='Select your Bank']/following-sibling::android.view.View[not(@resource-id)]")
	private MobileElement checkOutNetBankingPopUpCloseBtn;

	public MobileElement getCheckOutNetBankingPopUpCloseBtn() {
		return checkOutNetBankingPopUpCloseBtn;
	}

	@AndroidFindAll({ @AndroidBy(xpath = "//android.view.View[@resource-id='ptm-nb-inner']"),
			@AndroidBy(xpath = "//android.widget.TextView[@text='Select your Bank']/following-sibling::android.view.View/android.widget.TextView") })
	private MobileElement bankListIcon;

	public MobileElement getBankListIcon() {
		return bankListIcon;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@text='Skip Feedback']")
	private MobileElement skipFeedBackBtn;

	public MobileElement getSkipFeedBackBtn() {
		return skipFeedBackBtn;
	}

	/*------------------------------------DebitCard/CreaditCard Payment type page--------------*/

	@AndroidFindBy(xpath = "//*[@text='Add a new card']")
	@iOSXCUITFindBy(xpath = "//*[@name='Add a new card']")
	private MobileElement addNewCardTitle;

	public MobileElement getAddNewCardTitle() {
		return addNewCardTitle;
	}

	@AndroidFindBy(xpath = "//android.widget.EditText")
	private List<MobileElement> cardDetailsEnterTextField;

	public List<MobileElement> getCardDetailsEnterTextField() {
		return cardDetailsEnterTextField;
	}

	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='card_number']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Card Number']/preceding-sibling::XCUIElementTypeTextField")
	private MobileElement cardNumberEnterTextField;

	public MobileElement getCardNumberEnterTextField() {
		return cardNumberEnterTextField;
	}

	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='card_expiry']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Expiry']/preceding-sibling::XCUIElementTypeTextField")
	private MobileElement cardExpiryTextField;

	public MobileElement getCardExpiryTextField() {
		return cardExpiryTextField;
	}

	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='card_name']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[contains(@name,'Card Holder')]/preceding-sibling::XCUIElementTypeTextField")
	private MobileElement cardHolderTextField;

	public MobileElement getCardHolderTextField() {
		return cardHolderTextField;
	}

	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='card_cvv']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='CVV']/preceding-sibling::XCUIElementTypeTextField")
	private MobileElement cardCvvTextField;

	public MobileElement getCardCvvTextField() {
		return cardCvvTextField;
	}

	/*-------------------EMI PAYMENT FORM-------------------------*/

	@AndroidFindBy(xpath = "//*[@text='Bank EMI']")
	@iOSXCUITFindBy(xpath = "//*[@name='All EMI Options']")
	private MobileElement bankEmiTitle;

	public MobileElement getBankEmiTitle() {
		return bankEmiTitle;
	}

	@AndroidFindBy(xpath = "//android.view.View[@resource-id='bank-item-HDFC']")
	private MobileElement hdfcBankIcon;

	public MobileElement getHdfcBankIcon() {
		return hdfcBankIcon;
	}

	@AndroidFindBy(xpath = "//android.view.View[@resource-id='bank-item-ICIC']")
	private MobileElement iciciBankIcon;

	public MobileElement getIciciBankIcon() {
		return iciciBankIcon;
	}

	@AndroidFindBy(xpath = "//android.view.View[@resource-id='bank-item-CITI']")
	private MobileElement citiBankIcon;

	public MobileElement getCitiBankIcon() {
		return citiBankIcon;
	}

	@AndroidFindBy(xpath = "//android.view.View[@resource-id='bank-item-KKBK']")
	private MobileElement kotakBankIcon;

	public MobileElement getKotakBankIcon() {
		return kotakBankIcon;
	}

	@AndroidFindBy(xpath = "//android.view.View[@resource-id='bank-item-INDB']")
	private MobileElement induslndBankIcon;

	public MobileElement getInduslndBankIcon() {
		return induslndBankIcon;
	}

	@AndroidFindBy(xpath = "//*[@text='More Banks']")
	private MobileElement moreBankIcon;

	public MobileElement getMoreBankIcon() {
		return moreBankIcon;
	}

	@AndroidFindBy(xpath = "//android.view.View[@resource-id='bank-item-walnut369']")
	private MobileElement axioIcon;

	public MobileElement getAxioIcon() {
		return axioIcon;
	}

	@AndroidFindBy(xpath = "//android.view.View[@resource-id='bank-item-zestmoney']")
	private MobileElement zestmoneyIcon;

	public MobileElement getZestmoneyIcon() {
		return zestmoneyIcon;
	}

	@AndroidFindBy(xpath = "//android.view.View[@resource-id='bank-item-hcin']")
	private MobileElement homeCreditUjjwalCardIcon;

	public MobileElement getHomeCreditUjjwalCardIcon() {
		return homeCreditUjjwalCardIcon;
	}

	@AndroidFindBy(xpath = "//android.view.View[@resource-id='bank-item-sezzle']")
	private MobileElement sezzleIcon;

	public MobileElement getSezzleIcon() {
		return sezzleIcon;
	}

	/*----------------------------------NetBanking payment Form-------------------------*/

	@AndroidFindBy(xpath = "//*[@text='Suggested Banks']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Select Bank']")
	private MobileElement selectBankTitle;

	public MobileElement getSelectBankTitle() {
		return selectBankTitle;
	}

	@AndroidFindBy(xpath = "//android.widget.RadioButton[@text='State Bank of India State Bank of India']")
	@iOSXCUITFindBy(xpath = "//*[@name='SBI']")
	private MobileElement sbiBankTab;

	public MobileElement getSbiBankTab() {
		return sbiBankTab;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@text=\"HDFC Bank HDFC Bank\"]")
	private MobileElement hdfcBankTab;

	public MobileElement getHdfcBankTab() {
		return hdfcBankTab;
	}

	@AndroidFindBy(xpath = "//android.view.View[@resource-id='netb-banks']/android.view.View")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Select Bank']/following-sibling::XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText")
	private List<MobileElement> bankTypeList;

	public List<MobileElement> getBankTypeList() {
		return bankTypeList;
	}

	@AndroidFindBy(xpath = "//android.widget.EditText")
	@iOSXCUITFindBy(xpath = "//*[@name='Select a different bank']")
	private MobileElement suggestedBanksText;

	public MobileElement suggestedBanksText() {
		return suggestedBanksText;
	}

	@AndroidFindBy(xpath = "//*[@text='All banks']")
	@iOSXCUITFindBy(xpath = "//*[@name='All banks']")
	private MobileElement allBankTitle;

	public MobileElement getAllBankTitle() {
		return allBankTitle;
	}

	@AndroidFindBy(xpath = "//*[@text='All banks']/following-sibling::android.view.View[1]")
	@iOSXCUITFindBy(xpath = "//*[@name='Adda247']/following-sibling::XCUIElementTypeButton")
	private MobileElement allBankPageCloseBtn;

	public MobileElement getAllBankPageCloseBtn() {
		return allBankPageCloseBtn;
	}

	@AndroidFindBy(xpath = "//*[@text='All banks']/following-sibling::android.view.View/android.widget.EditText")
	@iOSXCUITFindBy(xpath = "//*[@name='All banks']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeOther[1]")
	private MobileElement bankSearchTextField;

	public MobileElement getBankSearchTextField() {
		return bankSearchTextField;
	}

	@AndroidFindBy(xpath = "//android.widget.EditText")
	@iOSXCUITFindBy(xpath = "//*[@name='All banks']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeOther[2]")
	private MobileElement bankSearchResult;

	public MobileElement getBankSearchResult() {
		return bankSearchResult;
	}

	@AndroidFindBy(xpath = "//android.view.View[@resource-id='error-modal']")
	private MobileElement paymentProcessedLoader;

	public MobileElement getPaymentProcessedLoader() {
		return paymentProcessedLoader;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@text='Success']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Success']")
	private MobileElement paymentSuccessBtn;

	public MobileElement getPaymentSuccessBtn() {
		return paymentSuccessBtn;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@text='Failure']")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Failure']")
	private MobileElement paymentFailureBtn;

	public MobileElement getPaymentFailureBtn() {
		return paymentFailureBtn;
	}

	@AndroidFindBy(xpath = "//*[contains(@text,'Payment could not be completed')]")
	private MobileElement paymentFailedTitle;

	public MobileElement getPaymentFailedTitle() {
		return paymentFailedTitle;
	}

	@AndroidFindBy(xpath = "//android.widget.Button[@text='Try Again']")
	private MobileElement tryAgainBtn;

	public MobileElement getTryAgainBtn() {
		return tryAgainBtn;
	}

	/*------------------------------------OTHERWALLET PAYMENT FORM--------------------------*/

	@AndroidFindBy(xpath = "//*[contains(@text,'Wallet')]")
	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Select Wallet']")
	private MobileElement selectWalletTitle;

	public MobileElement getSelectWalletTitle() {
		return selectWalletTitle;
	}

//	@AndroidFindAll({
//			@AndroidBy(xpath = "//android.view.View[@resource-id='form-wallet']/child::android.view.View[not(@text='Select Wallet')]"),
//			@AndroidBy(xpath = "//android.widget.TextView[@text='Select Wallet']/following-sibling::android.view.View") })
//	@iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name='Select Wallet']/following-sibling::XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeStaticText")
//	private List<MobileElement> walletTypeList;
//
//	public List<MobileElement> getWalletTypeList() {
//		return walletTypeList;
//	}

	/*------------------------------ALTERNATIVE PAYMENT SCREEN---------------------*/

//	@AndroidFindBy(xpath = "//*[@text='Contact Details']")
//	private MobileElement contactDetailsTitle;
//
//	public MobileElement getContactDetailsTitle() {
//		return contactDetailsTitle;
//	}

	@AndroidFindBy(xpath = "//*[@text='Cards, UPI & More']")
	private MobileElement cardsUpiMoreTitle;

	public MobileElement getCardsUpiMoreTitle() {
		return cardsUpiMoreTitle;
	}

	@AndroidFindBy(xpath = "//android.widget.ListView/*[contains(@text,'Card')]")
	private List<MobileElement> cardTab;

	public List<MobileElement> getCardTab() {
		return cardTab;
	}

	@AndroidFindBy(xpath = "//android.widget.ListView/*[contains(@text,'UPI')]")
	private MobileElement upiTab;

	public MobileElement getUpiTab() {
		return upiTab;
	}

	@AndroidFindBy(xpath = "//*[contains(@text,'Netbanking')]")
	private List<MobileElement> netBankingTab;

	public List<MobileElement> getNetBankingTab() {
		return netBankingTab;
	}

	@AndroidFindBy(xpath = "//*[contains(@text,'Wallet')]")
	private MobileElement walletTab;

	public MobileElement getWalletTab() {
		return walletTab;
	}

	@AndroidFindBy(xpath = "//android.view.View[contains(@text,'EMI')]")
	private MobileElement emiTab;

	public MobileElement getEmiTab() {
		return emiTab;
	}

	@AndroidFindBy(xpath = "//*[contains(@text,'Pay Later')]")
	private MobileElement payLaterTab;

	public MobileElement getPayLaterTab() {
		return payLaterTab;
	}

	@AndroidFindBy(xpath = "//*[@text='Select An Option']")
	private MobileElement selectAnOptionTitle;

	public MobileElement getSelectAnOptionTitle() {
		return selectAnOptionTitle;
	}

	@AndroidFindBy(xpath = "//*[@text='ePayLater']")
	private MobileElement ePayLaterTab;

	public MobileElement getePayLaterTab() {
		return ePayLaterTab;
	}

	@AndroidFindBy(xpath = "//*[@text='Simpl']")
	private MobileElement simplTab;

	public MobileElement getSimplTab() {
		return simplTab;
	}

	@AndroidFindBy(xpath = "//*[@text='ICICI Bank PayLater']")
	private MobileElement iciciBankPayLaterTab;

	public MobileElement getIciciBankPayLaterTab() {
		return iciciBankPayLaterTab;
	}

	@AndroidFindBy(xpath = "//*[@text='Kotak Mahindra Bank']")
	private MobileElement kotakMahindraBankTab;

	public MobileElement getKotakMahindraBankTab() {
		return kotakMahindraBankTab;
	}

	@AndroidFindBy(xpath = "//*[@text='LazyPay']")
	private MobileElement lazyPayTab;

	public MobileElement getLazyPayTab() {
		return lazyPayTab;
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='APPLY']")
	public List<MobileElement> getAllApplyBtns;

	public List<MobileElement> getAllApplyBtns() {
		return getAllApplyBtns;
	}

	@AndroidFindBy(id = "com.adda247.app:id/edittext_coupon")
	private MobileElement editBoxCoupon;

	public MobileElement CouponEditBox() {
		return editBoxCoupon;
	}

	@AndroidFindBy(accessibility = "My Content")
	@iOSXCUITFindBy(xpath = "//*[contains(@name,'My Content')]")
	private MobileElement btnMyContent;

	public MobileElement getBtnMyContent() {
		return btnMyContent;
	}

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'packageCardClick')]")
	public List<MobileElement> listOfPurchasedCourses;

	public List<MobileElement> listOfPurchasedCourses() {
		return listOfPurchasedCourses;
	}
}
