package test_scripts;

import applicationUtil.DeepLinkUtil;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pojo.deepLinkData.DeepLinkData;
import util.Common_Function;
import util.ConfigFileReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class DeepLinkWithoutLoginTest extends BaseTestWithoutLoginDeeplink {

    @Test(dataProvider = "getData")
    public void verifyDeepLinkWithoutLogin(DeepLinkData deepLinkData) {
        boolean result = true;
        if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios")) {
            throw new SkipException("Skipping test because this feature can not run on ios.");
        }
        DeepLinkUtil deepLinkObj = new DeepLinkUtil();
        result = deepLinkObj.verifyDeeplink(getDriver(), deepLinkData, false);
        Common_Function.updateStatusOnLambdaTest(getDriver(), result);
        Assert.assertTrue(result, deepLinkObj.deepLinkMsgList.toString());
    }

    @DataProvider
    public Object[][] getData() throws FileNotFoundException {

        String environment = ConfigFileReader.strEnv;
        String filePath = environment.equalsIgnoreCase("production") ?
                "src/main/resources/DeepLinkProd.json" :
                "src/main/resources/DeepLink.json";

        JsonElement jsonData = new JsonParser().parse(new FileReader(filePath));
        JsonElement dataSet = jsonData.getAsJsonObject().get("dataSet");

        List<DeepLinkData> testData = new Gson().fromJson(dataSet, new TypeToken<List<DeepLinkData>>() {
        }.getType());
        Object[][] returnValue = new Object[testData.size()][1];
        int index = 0;
        for (Object[] each : returnValue) {
            each[0] = testData.get(index++);
        }
        return returnValue;
    }
}
