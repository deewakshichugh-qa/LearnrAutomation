package appium;

import java.time.Duration;

import org.openqa.selenium.By;

import io.appium.java_client.MobileElement;
import io.appium.java_client.touch.offset.PointOption;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;

public class MobileDriverAction extends MobileDriverAccess {
	public static String ACTION_STYLE_MOBILEDRIVER = "MobileDriver";
	public static String ACTION_STYLE_KEYS = "SendKeys";

	/*********** click on element ****************/
	public static void click(final By locator, String... style) throws Exception {
		click(MobileDriverAccess.getElement(locator), style);
	}

	public static void click(final MobileElement el, String... style) throws Exception {
		String click = style.length > 0 ? style[0] : ACTION_STYLE_MOBILEDRIVER; // default is standard click
		if (click.equals(ACTION_STYLE_MOBILEDRIVER)) {
			el.click();
		} else {
			throw new Exception("Unknown click action style: " + style[0]);
		}
	}

	/*********** enter a value into a field ****************/
	public static void enterFieldValue(final By locator, String value, String... styles) throws Exception {
		enterFieldValue(MobileDriverAccess.getElement(locator), value, styles);
	}

	public static void enterFieldValue(final MobileElement el, String value, String... styles) throws Exception {
		String click = styles.length > 0 ? styles[0] : ACTION_STYLE_MOBILEDRIVER; // default is standard click
		String send = styles.length > 1 ? styles[1] : ACTION_STYLE_KEYS; // default is standard send
		click(el, click);
		if (send.equals(ACTION_STYLE_KEYS)) {
			el.sendKeys(value);
		}
	}
	
	public static void runAppInBackground(int time) throws Exception {
		driver.runAppInBackground(Duration.ofSeconds(time));
	}
	
	public static void tap(final By locator) throws Exception {
		touch.tap(tapOptions().withElement(element(MobileDriverAccess.getElement(locator)))).perform();
	}
	
	public static void longPress(final By locator, int time) throws Exception {
		touch.longPress(longPressOptions().withElement(element
				(MobileDriverAccess.getElement(locator))).withDuration(Duration.ofSeconds(time))).
																						release().perform();
	}
	
	public static void tapByCoordinates(int x, int y) throws Exception {
		touch.tap(PointOption.point(x, y)).perform();
	}
}
