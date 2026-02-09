package test_scripts;

import applicationUtil.PaymentUtil;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import util.Common_Function;
import util.ConfigFileReader;
import util.TestDescriptionConstant;

public class PaymentTest extends BaseTest {

	PaymentUtil paymentUtilObj;

	//No locators
	@Test(description = TestDescriptionConstant.verifyCompletePaymentByUsingUPI, enabled = false)
	public void verifyCompletePaymentByUsingUPI() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios")) {
			throw new SkipException("Skipping test because this feature can not run on ios");
		}
		paymentUtilObj = new PaymentUtil(getDriver());
		result = paymentUtilObj.verifyPaymentFlowUsingDiffMethods(getDriver(), "upi");
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, paymentUtilObj.paymentMsgList.toString());
	}

	@Test(description = TestDescriptionConstant.verifyCompletePaymentByUsingNetBanking)
	public void verifyCompletePaymentByUsingNetBanking() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios & prod");
		}
		paymentUtilObj = new PaymentUtil(getDriver());
		result = paymentUtilObj.verifyPaymentFlowUsingDiffMethods(getDriver(), "netBanking");
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, paymentUtilObj.paymentMsgList.toString());
	}

	@Test(description = TestDescriptionConstant.verifyCompletePaymentByUsingNetBanking)
	public void verifyCompletePaymentByUsingUpiAutoPay() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios & prod");
		}
		paymentUtilObj = new PaymentUtil(getDriver());
		result = paymentUtilObj.verifyPaymentFlowUsingDiffMethods(getDriver(), "upiAutoPay");
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
		Assert.assertTrue(result, paymentUtilObj.paymentMsgList.toString());
	}

	@Test(description = TestDescriptionConstant.verifyDebitCardTypePayment)
	public void verifyAllOtherTypesPayment() {
		boolean result = true;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
				ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
			throw new SkipException("Skipping test because this feature can not run on ios & prod");
		}
		paymentUtilObj = new PaymentUtil(getDriver());
		result = paymentUtilObj.verifyPaymentFlowUsingDiffMethods(getDriver(), "allOtherTypes");
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, paymentUtilObj.paymentMsgList.toString());
	}
}
