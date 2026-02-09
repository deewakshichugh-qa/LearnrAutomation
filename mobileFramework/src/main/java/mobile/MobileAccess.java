package mobile;

import java.lang.reflect.Field;
import java.util.List;

import org.openqa.selenium.By;

import appium.MobileDriverAccess;
import appium.MobileDriverAction;
import objects.exceptions.ResolveLocatorException;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class MobileAccess extends MobileDriverAccess {

	public static MobileElement getElement(final Object locator) throws Exception {
		return MobileDriverAccess.getElement(getLocator(locator));
	}

	public static List<MobileElement> getElements(final Object locator) throws Exception {
		return MobileDriverAccess.getElements(getLocator(locator));
	}

	public static By getLocator(final Object elementName) throws Exception {
		if (elementName instanceof Enum) {
			return getLocator(((Enum<?>) elementName).name(), elementName.toString());
		} else {
			final Field field = MobileDriverAccess.getObjectMapClass().getField(elementName.toString());
			final Class<?> clazz = field.getClass();
			final String str = field.get(clazz).toString();

			return getLocator(elementName.toString(), str);
		}
	}

	public static By getLocator(final String elementName, String elementValue) throws Exception {
		if (elementName.endsWith("_ID")) {
			return MobileBy.id(elementValue);
		} else if (elementName.endsWith("_NAME")) {
			return MobileBy.name(elementValue);
		} else if (elementName.endsWith("_CLASS")) {
			return MobileBy.className(elementValue);
		} else if (elementName.endsWith("_CSS")) {
			return MobileBy.cssSelector(elementValue);
		} else if (elementName.endsWith("_LINK")) {
			return MobileBy.linkText(elementValue);
		} else if (elementName.endsWith("_PLINK")) {
			return MobileBy.partialLinkText(elementValue);
		} else if (elementName.endsWith("_TAG")) {
			return MobileBy.tagName(elementValue);
		} else if (elementName.endsWith("_XPATH")) {
			return MobileBy.xpath(elementValue);
		} else if (elementName.endsWith("_ACCESSID")) {
			return MobileBy.AccessibilityId(elementValue);
		} else if (elementName.endsWith("_TEXT")) {
			elementValue = "new UiSelector().text(\""+elementValue+"\")";
			return MobileBy.AndroidUIAutomator(elementValue);
		}else if (elementName.endsWith("_DESC")) {
			elementValue = "new UiSelector().description(\""+elementValue+"\")";
			return MobileBy.AndroidUIAutomator(elementValue);
		}
		throw new ResolveLocatorException("Unable to handle the locator type: " + elementName
				+ ". Locator name should end with _ID/_NAME/" + "_CLASS/_CSS/_LINK/_PLINK/_TAG/_XPATH");
	}
	
	public static Boolean isMobileElementClickable(By locator) throws Exception {
		return MobileDriverAccess.isMobileElementClickable(locator);
	}
	
	public static Boolean isMobileElementEnabled(By locator) throws Exception {
		return MobileDriverAccess.isMobileElementEnabled(locator);
	}

}
