package test_scripts;

import applicationUtil.EventsUtil;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import util.Common_Function;
import util.ConfigFileReader;

public class EventTest extends BaseTest {

    EventsUtil eventsObj;

    @Test
    public void verifyAppEvents() {
        boolean result = true;
        if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios") ||
                ConfigFileReader.strEnv.equalsIgnoreCase("production")) {
            throw new SkipException("Skipping test because this feature can not run on ios or production.");
        }
        eventsObj = new EventsUtil(getDriver(), getLogUtil());
        result = eventsObj.verifyAppEvents(getDriver());
        Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, eventsObj.eventMsgList.toString());
    }
}
