package mobile;

import appium.MobileDriverWait;

import io.appium.java_client.MobileElement;

public class MobileWait extends MobileAccess {
	/**
	 * Wait until elements are displayed
	 * 
	 * @param Object
	 * @throws Exception
	 */
	public static void waitUntilElementIsDisplayed(final Object... locator) throws Exception {
		for (Object each : locator) {
			MobileDriverWait.waitUntilElementIsDisplayed(getLocator(each));
		}
	}

	/**
	 * Wait until elements are displayed
	 * 
	 * @param Mobile Element
	 * @throws Exception
	 */
	public static void waitUntilElementIsDisplayed(final MobileElement e1) throws Exception {
		MobileDriverWait.waitUntilElementIsDisplayed(e1);
	}

	/**
	 * Wait until elements are displayed
	 * 
	 * @param locator
	 * @param waitTime
	 * @throws Exception
	 */
	public static void waitUntilElementIsDisplayed(final Object locator, final int waitTime) throws Exception {
		MobileDriverWait.waitUntilElementIsDisplayed(getLocator(locator), waitTime);
	}
	
	public static void waitUntilText(final String... text) throws Exception {
		for (String each : text) {			
			MobileDriverWait.waitUntilText(each);
		}
	}
}
