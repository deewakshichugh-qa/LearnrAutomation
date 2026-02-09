package listener;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import test_scripts.BaseTest;

public class GetScreenShot {



    public static String capture(AppiumDriver<MobileElement> driver, String screenShotName) throws IOException {
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Use suite-level folder path
        String dest = BaseTest.screenshotFolderPath + "/" + screenShotName + ".png";
        File destination = new File(dest);
        FileUtils.copyFile(source, destination);

        return dest;
    }
}
