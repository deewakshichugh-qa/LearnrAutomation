package mobile;

import appium.MobileDriverAction;

public class MobileAction extends MobileAccess {
	public static void click(final Object locator, final String... style) throws Exception {
		MobileDriverAction.click(getLocator(locator), style);
	}

	public static void enterFieldValue(final Object locator, final String value, final String... styles)
			throws Exception {
		MobileDriverAction.enterFieldValue(getLocator(locator), value, styles);
	}
	
	public static void runAppInBackground(final int time) throws Exception {
		MobileDriverAction.runAppInBackground(time);
	}
	
	public static void tap(final Object locator) throws Exception {
		MobileDriverAction.tap(getLocator(locator));
	}
	
	public static void longPress(final Object locator, int time) throws Exception {
		MobileDriverAction.longPress(getLocator(locator), time);
	}
	
	public static void tapByCoordinates(int x, int y) throws Exception {
		MobileDriverAction.tapByCoordinates(x, y);
	}

}
