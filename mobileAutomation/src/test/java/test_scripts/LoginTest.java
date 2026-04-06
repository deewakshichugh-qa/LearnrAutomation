//package test_scripts;
//
//import applicationUtil.LoginUtil;
//import org.testng.Assert;
//import org.testng.SkipException;
//import org.testng.annotations.Test;
//import util.Common_Function;
//import util.ConfigFileReader;
//import util.TestDescriptionConstant;
//
//public class LoginTest extends BaseTest {
//
//    LoginUtil loginObj;
//
//    @Test(description = TestDescriptionConstant.verfiyLoginGuestUser)
//    public void verifyLoginUsingEmailId() {
//        boolean result = true;
//        if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios")) {
//            throw new SkipException("Skipping test because this feature can not run on ios");
//        }
//        loginObj = new LoginUtil(getDriver());
//        result = loginObj.verifyLoginUsingEmailIdFromConfig(getDriver());
//        Common_Function.updateStatusOnLambdaTest(getDriver(), result);
//    }
//
//    @Test
//    public void verifyLoginUsingMobileNumber() {
//        boolean result = true;
//        if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios")) {
//            throw new SkipException("Skipping test because this feature can not run on ios");
//        }
//        loginObj = new LoginUtil(getDriver());
//        result = loginObj.verifyLoginUsingMobileNumber(getDriver(), ConfigFileReader.strUserMobileNumber);
//        Common_Function.updateStatusOnLambdaTest(getDriver(), result);
//        Assert.assertTrue(result, loginObj.loginMsgList.toString());
//    }
//
//    @Test
//    public void verifyNegativeLogin() {
//        boolean result = true;
//        Common_Function.updateStatusOnLambdaTest(getDriver(), result);
//        if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios")) {
//            throw new SkipException("Skipping test because this feature can not run on ios");
//        }
//        loginObj = new LoginUtil(getDriver());
//        result = loginObj.verifyNegativeLogin(getDriver());
//        Common_Function.updateStatusOnLambdaTest(getDriver(), result);
//        Assert.assertTrue(result, loginObj.loginMsgList.toString());
//    }
//
//    // Forgot password api not working
//    @Test(enabled = false)
//    public void verifyForgetPasswordLink() {
//        boolean result = true;
//        if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
//                ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
//            throw new SkipException("Skipping test because this feature can not run on ios or production.");
//        }
//        loginObj = new LoginUtil(getDriver());
//        result = loginObj.verifyForgetPasswordLink(getDriver());
//        Common_Function.updateStatusOnLambdaTest(getDriver(), result);
//        Assert.assertTrue(result, loginObj.loginMsgList.toString());
//    }
//}