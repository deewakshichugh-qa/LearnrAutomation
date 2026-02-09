package appium;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import helpers.ConfigFileReader;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import logger.ScriptLogger;
import mobile.MobileAccess;
import mobile.MobileAction;

public class BaseMobileDriver {
	
	public static AndroidDriver<MobileElement> driver;
	public static TouchAction touch;
	
	@BeforeSuite
	public void startAppBeforeSuite() throws Exception {
		ScriptLogger.info();
		URL url;

		try {
			if (ConfigFileReader.strRunMode.equalsIgnoreCase("Cloud") || ConfigFileReader.strRunMode == null) {
				String username = System.getenv("LT_USERNAME") == null ? "manmay.mohanty"
						: System.getenv("LT_USERNAME");
				String authkey = System.getenv("LT_ACCESS_KEY") == null
						? "WBHpAONqzpjkEQarfmxl3yLbZsdJrLMwUtkgFt6LtcqP3f1uNE"
						: System.getenv("LT_ACCESS_KEY");
				String hub = "@beta-hub.lambdatest.com/wd/hub"; // beta-hub.lambdatest.com/wd/hub
				url = new URL("https://" + username + ":" + authkey + hub);
			} else {
				url = new URL("http://localhost:4723/wd/hub");
			}

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("deviceName", ConfigFileReader.strDeviceName);
			capabilities.setCapability("platformVersion", ConfigFileReader.strDeviceVersion);
			capabilities.setCapability("newCommandTimeout", 600);
			capabilities.setCapability("launchTimeout", 90000);
			capabilities.setCapability("automationName", "uiautomator2");
			capabilities.setCapability("build", "Adda Build");

			if (ConfigFileReader.strRunMode.equalsIgnoreCase("Cloud") || ConfigFileReader.strRunMode == null) {
				capabilities.setCapability("app", "lt://APP100202361655268248723587");
				capabilities.setCapability("isRealMobile", true);
				capabilities.setCapability("visual", true);
				capabilities.setCapability("console", true);
				
			}

			capabilities.setCapability("appPackage", "com.adda247.app");
			capabilities.setCapability("appActivity", "com.adda247.modules.home.HomeActivity");
			// capabilities.setCapability("name", m.getName() + this.getClass().getName());
		
			capabilities.setCapability("plugin", "git-testng");
			
			String[] Tags = new String[] { "Feature", "Magicleap", "Severe" };
			capabilities.setCapability("tags", Tags);

			driver = new AndroidDriver<MobileElement>(url, capabilities);

			driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);

			touch = new TouchAction(driver);
			
			if(ConfigFileReader.strEnv.equalsIgnoreCase("staging")) {
				MobileElement el = driver.findElementById("com.adda247.app:id/description_title");
				touch.longPress(longPressOptions().withElement(element(el)).withDuration(Duration.ofSeconds(3))).release().perform();
				
				List<MobileElement> el1 = driver.findElementsById("android:id/text1");
				
				el1.get(1).click();
				
				String packageName = ((AndroidDriver)driver).getCurrentPackage();
				driver.closeApp();
				driver.terminateApp(packageName);
				driver.activateApp(packageName);
			}
			
		}catch (MalformedURLException e) {
			throw new Exception(e);
		}
	}

	@AfterSuite
	public void closeDriver() throws Exception {
        driver.quit();
	}
}
