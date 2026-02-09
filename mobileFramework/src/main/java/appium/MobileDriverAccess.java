package appium;

import java.util.List;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class MobileDriverAccess extends BaseMobileDriver {
	protected static Class<?> objectMapClass;

	public static Class<?> getObjectMapClass() {
		return objectMapClass;
	}

	public static MobileElement getElement(final By locator) throws Exception {
		return driver.findElement(locator);
	}

	public static List<MobileElement> getElements(final By locator) throws Exception {
		return driver.findElements(locator);
	}

	public static String getElementAttributeValue(By locator, String attribute) throws Exception {
		return driver.findElement(locator).getAttribute(attribute);
	}
	
	public static Boolean isMobileElementClickable(By locator) throws Exception {
		List<MobileElement> el = driver.findElements(MobileBy.AndroidUIAutomator("new UiSelector().clickable(true)"));
		if(el.size()>0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static Boolean isMobileElementEnabled(By locator) throws Exception {
		List<MobileElement> el = driver.findElements(MobileBy.AndroidUIAutomator("new UiSelector().enabled(true)"));
		if(el.size()>0) {
			return true;
		} else {
			return false;
		}
	}

}
