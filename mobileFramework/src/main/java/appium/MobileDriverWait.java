package appium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class MobileDriverWait extends MobileDriverAccess {
	protected static int waitTime = 10;

	/***********************
	 * Wait until elements are displayed
	 **********************/
	public static void waitUntilElementIsDisplayed(final By... locator) {
		for (By each : locator)
			waitUntilElementIsDisplayed(each, waitTime);
	}

	public static void waitUntilElementIsDisplayed(final MobileElement... elements) {
		for (MobileElement each : elements)
			waitUntilElementIsDisplayed(each, waitTime);
	}

	public static void waitUntilElementIsDisplayed(final MobileElement element, int time) {
		(new WebDriverWait(driver, time)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return Boolean.valueOf(null != element && element.isDisplayed());
			}
		});

	}
	

	public static void waitUntilElementIsDisplayed(final By locator, int time) {
		(new WebDriverWait(driver, time)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				try {
					MobileElement element = d.findElement(locator);
					return Boolean.valueOf(element != null && element.isDisplayed());
				} catch (Exception e) {
					return Boolean.valueOf(false);
				}
			}
		});
	}

	/***********************
	 * Wait until elements are not displayed
	 **********************/

	public static void waitUntilElementIsNotDisplayed(final By... locator) {
		for (By each : locator)
			waitUntilElementIsNotDisplayed(each, waitTime);
	}

	public static void waitUntilElementIsNotDisplayed(final MobileElement... element) {
		for (MobileElement each : element)
			waitUntilElementIsNotDisplayed(each, waitTime);
	}

	public static void waitUntilElementIsNotDisplayed(final By locator, int time) {
		(new WebDriverWait(driver, time)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				try {
					MobileElement element = d.findElement(locator);
					return Boolean.valueOf(null == element || !element.isDisplayed());
				} catch (Exception e) {
					return Boolean.valueOf(true);
				}
			}
		});
	}

	public static void waitUntilElementIsNotDisplayed(final MobileElement element, int time) {
		(new WebDriverWait(driver, time)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return Boolean.valueOf(null == element || !element.isDisplayed());
			}
		});
	}

	public static void waitUntilText(final String... text) {
		for (String each : text)
			waitUntilText(each, waitTime);
	}

	public static void waitUntilText(final String text, int time) {
		(new WebDriverWait(driver, time)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				try {
					return Boolean.valueOf(d.getPageSource().contains(text));
				} catch (Exception e) {
					return Boolean.valueOf(false);
				}
			}
		});
	}
}
