package test_scripts;

import applicationUtil.PurchasePackageUtil;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import util.Common_Function;
import util.ConfigFileReader;

public class PDPTest extends BaseTest {
	PurchasePackageUtil purchasePackageUtilObj;
	
	@Test
	public void verifyPDPPage() {
		boolean result;
		if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios")) {
			throw new SkipException("Skipping test because this feature can not run on ios");
		}
		purchasePackageUtilObj = new PurchasePackageUtil(getDriver());
		result = purchasePackageUtilObj.verifyPDPPage(getDriver());
		Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, purchasePackageUtilObj.purchasePackageMsgList.toString());
	}
}