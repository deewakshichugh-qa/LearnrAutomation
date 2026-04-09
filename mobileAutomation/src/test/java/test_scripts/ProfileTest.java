package test_scripts;

import applicationUtil.LoginUtil;
import applicationUtil.ProfileUtil;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import util.Common_Function;
import util.ConfigFileReader;

public class ProfileTest extends BaseTest {

    ProfileUtil profileObj;

    @Test
    public void validateProfile() {
        boolean result = true;
        if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
                ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
            throw new SkipException("Skipping test because this feature can not run on ios or production.");
        }
        profileObj = new ProfileUtil(getDriver());
        result = profileObj.verifyProfilePageElements(getDriver());
        Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, profileObj.profileMsgList.toString());
    }
}
