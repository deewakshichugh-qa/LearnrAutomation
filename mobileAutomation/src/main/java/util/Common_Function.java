package util;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionState;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.*;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.HasSupportedPerformanceDataType;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.xml.sax.SAXException;
import pojo.device.Environment;

import javax.xml.parsers.ParserConfigurationException;
import java.io.InputStreamReader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class Common_Function {
	public APIUtils apiUtilObj;
	public APIResponse apiResponseObj;
	ConfigFileReader configReaderObj = new ConfigFileReader();
	private AppiumDriver<MobileElement> driver;
	private Process process;
	private BufferedReader reader;
	private final List<String> apiResponses = new ArrayList<>();
	private final boolean capturing = false;
    private StringBuilder logBuilder; // StringBuilder to store captured logs
	private static final int LOG_ROTATION_THRESHOLD = 1000; // Save logs after every 1000 entries
	private final int logEntryCount = 0;
	private String methodName;

    public AppiumDriver<MobileElement> commonStartAndOpenURLBrowser(String port, String udid) throws Exception {
		AppiumDriver<MobileElement> driver = null;

		try {
			final String applicationType = ConfigFileReader.strApplicationType;
			final String runMode = ConfigFileReader.strRunMode;
			final String apkLink = ConfigFileReader.strApkLink;
			final String iosDeviceName = ConfigFileReader.strDeviceName;
			final String iosVersion = ConfigFileReader.strDeviceVersion;
			final String iosAppPath = ConfigFileReader.strFilePath;
			final String appName = ConfigFileReader.strApplication;
			final Map<String, String> deviceCaps = getCapability(port, udid);
			final String remoteAddress = deviceCaps.get("remoteAddress");

			DesiredCapabilities capabilities = new DesiredCapabilities();

			if (applicationType.equalsIgnoreCase("Android")) {
				capabilities.setCapability("platformName", "Android");
				capabilities.setCapability("appium:deviceName", "");
				capabilities.setCapability("appium:newCommandTimeout", 300);
				capabilities.setCapability("appium:appPackage", deviceCaps.get("appPackage"));
				capabilities.setCapability("appium:appActivity", deviceCaps.get("appActivity"));
//				capabilities.setCapability("noReset", false);
				capabilities.setCapability("appium:resetKeyboard", true);
				capabilities.setCapability("appium:logLevel", "warn");
	//			capabilities.setCapability(CapabilityType.VERSION, ConfigFileReader.strDeviceVersion);
				if (runMode.equalsIgnoreCase("localLab")) {
					capabilities.setCapability("appium:unicodeKeyboard", true);
				}
				capabilities.setCapability("appium:uiautomator2ServerLaunchTimeout", 120000);
				capabilities.setCapability("appium:adbExecTimeout", 120000);

				capabilities.setCapability("appium:appWaitForLaunch", false);
	//			capabilities.setCapability("clearDeviceLogsOnStart", true);
	//			capabilities.setCapability("ignoreHiddenApiPolicyError", true);
	//			capabilities.setCapability("build", appName + "_Android Test");
	//			capabilities.setCapability("network", true);

				// Local run mode - Set automation engine
				if (runMode.equalsIgnoreCase("local") || runMode.equalsIgnoreCase("localLab")) {
					capabilities.setCapability("appium:automationName", deviceCaps.get("automationName"));
				}
				if (udid != null && !udid.isEmpty()) {
					capabilities.setCapability("appium:udid", udid);
				}
				// APK install logic
				if (apkLink != null && !apkLink.isEmpty()) {
					capabilities.setCapability("appium:fullReset", true);
					capabilities.setCapability("appium:noReset", false);
					capabilities.setCapability("appium:app", apkLink);       // Set the app (APK) path here
				} else {
					capabilities.setCapability("appium:noReset", false);
					capabilities.setCapability("appium:fullReset", false);
				}

				// Cloud configuration
				if (runMode.equalsIgnoreCase("cloud")) {
					capabilities.setCapability("app", deviceCaps.get("strFilePath"));
					capabilities.setCapability("isRealMobile", true);
					capabilities.setCapability("visual", true);
					capabilities.setCapability("devicelog", true);
				}
				System.out.println("HUB URL: " + remoteAddress);

				driver = new AndroidDriver<>(new URL(remoteAddress), capabilities);

			} else if (applicationType.equalsIgnoreCase("iOS")) {
				capabilities.setCapability("platformName", "iOS");
				capabilities.setCapability("appium:deviceName", iosDeviceName);
				capabilities.setCapability("appium:platformVersion", iosVersion);
				capabilities.setCapability("autoAcceptAlerts", true);
				capabilities.setCapability("appium:isRealMobile", true);
				capabilities.setCapability("appium:devicelog", true);
				capabilities.setCapability("appium:video", true);
				capabilities.setCapability("build", appName + "_iOS Test");
				capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 30000);

				if (runMode.equalsIgnoreCase("cloud")) {
					capabilities.setCapability("app", iosAppPath);
				}
				System.out.println("HUB URL: " + remoteAddress);

				driver = new IOSDriver<>(new URL(remoteAddress), capabilities);
			}
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		} catch (Exception e) {
			System.out.println(" - - - - - - - - - - - - ");
			System.out.println("Exception in commonStartAndOpenURLBrowser(): " + e.getMessage());
			System.out.println(" - - - - - - - - - - - - ");
		}
		return driver;
	}

	public void captureNetworkLogs(String methodName, ExtentTest test) {
		try {
			String os = System.getProperty("os.name").toLowerCase();

            String adbPath;
            if (os.contains("mac")) {
				adbPath = "/Users/adda247/Library/Android/sdk/platform-tools/adb logcat"; // macOS
			} else if (os.contains("win")) {
				adbPath = ""; // Windows
			} else if (os.contains("nux") || os.contains("nix")) {
				adbPath = "/usr/bin/adb logcat"; // Linux
			} else {
				throw new RuntimeException("Unsupported OS: " + os);
			}

			Process process = Runtime.getRuntime().exec(adbPath);
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			logBuilder = new StringBuilder();

            // Thread for capturing logs
            Thread logCaptureThread = new Thread(() -> {
                String line;
                try {
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("network request trace") &&
                                line.contains("adda247.com") &&
                                !line.contains("json") &&
                                !line.contains("jpeg") &&
                                !line.contains("png") &&
                                !line.contains("jpg")) {

                            logBuilder.append(line).append("\n");

                            String statusCode = extractStatusCode(line);
                            if (statusCode != null && !statusCode.startsWith("2")) {
                                test.log(Status.INFO, "Non-2xx API Response: " + line);
                            }
                        }
                    }
                } catch (IOException e) {
                    test.log(Status.FAIL, "Error reading logcat output: " + e.getMessage());
                } finally {
                    try {
                        reader.close();
                        process.destroy();
                    } catch (IOException e) {
                        test.log(Status.FAIL, "Error closing logcat process: " + e.getMessage());
                    }
                }
            });

			logCaptureThread.start();

		} catch (IOException e) {
			test.log(Status.FAIL, "Not able to start logcat process: " + e.getMessage());
		}
	}

	public void analyzeApiResponses(ExtentTest test) {
		if (logBuilder == null) {
			test.log(Status.WARNING, "No logs captured to analyze.");
			return;
		}

		List<String> failedApis = new ArrayList<>();
		String[] logs = logBuilder.toString().split("\n");

		for (String response : logs) {
			String statusCode = extractStatusCode(response);
			if (statusCode != null && !statusCode.startsWith("2")) {
				failedApis.add(response);
			}
		}

		if (!failedApis.isEmpty()) {
			String failedLogs = String.join("\n\n", failedApis);
			test.log(Status.INFO, "Non-2xx API Responses: \n" + failedLogs);
		}
	}

	private String extractStatusCode(String response) {
		Pattern pattern = Pattern.compile("\\(responseCode:\\s*(\\d{3})");
		Matcher matcher = pattern.matcher(response);
		return matcher.find() ? matcher.group(1) : "";
	}

	public boolean commonWaitForElementToBeVisible(AppiumDriver<MobileElement> driver, MobileElement elementForWait,
												   int timeOutInSeconds) {
		boolean result = true;
		try {
			// Element is Clickable - it is Displayed and Enabled.
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			WebElement element = wait.until(ExpectedConditions.visibilityOf(elementForWait));
			result = element.isDisplayed();
		} catch (Exception e) {
			result = false;
			System.out.println("Element is not visible: " + elementForWait);
		}
		return result;
	}

	public boolean commonWaitForListOfElementsToBeVisible(AppiumDriver<MobileElement> driver,
														  List<MobileElement> elementsForWait,
														  int timeOutInSeconds) {
		boolean result = false;
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		try {
			result = wait.until((ExpectedCondition<Boolean>) driver1 -> {
				for (MobileElement element : elementsForWait) {
					if (element.isDisplayed()) {
						return true;
					}
				}
				return false;
			});
		} catch (TimeoutException e) {
			System.out.println("Elements are not visible: " + elementsForWait);
		} catch (Exception e) {
			System.out.println("Error while waiting for elements to be visible: " + e.getMessage());
		}
		return result;
	}

	public boolean commonWaitForElementToBeLocatedAndVisible(AppiumDriver<MobileElement> driver, String elementforWait,
															 String strfindType, int timeOutInSeconds) {
		boolean result = true;
		WebElement element = null;
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			// Element is Clickable - it is Displayed and Enabled.
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			if (strfindType.equalsIgnoreCase("xpath")) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementforWait)));
			} else if (strfindType.equalsIgnoreCase("id")) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementforWait)));
			} else if (strfindType.equalsIgnoreCase("class")) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(elementforWait)));
			} else if (strfindType.equalsIgnoreCase("name")) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(elementforWait)));
			}
			result = element.isDisplayed();
		} catch (Exception e) {
			result = false;
			System.out.println("Element is not visible: " + elementforWait);
		} finally {
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS); // Always restore implicit wait
		}
		return result;
	}

	public boolean commonWaitForElementToBeLocatedAndVisible(WebDriver webDriver, String elementforWait,
			String strfindType, int timeOutInSeconds) {
		boolean result = true;
		WebElement element = null;
		try {
			webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			// Element is Clickable - it is Displayed and Enabled.
			WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSeconds);
			if (strfindType.equalsIgnoreCase("xpath")) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementforWait)));
			} else if (strfindType.equalsIgnoreCase("id")) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementforWait)));
			} else if (strfindType.equalsIgnoreCase("class")) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(elementforWait)));
			} else if (strfindType.equalsIgnoreCase("css")) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(elementforWait)));
			}
			result = element.isDisplayed();
			webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		} catch (Exception e) {
			result = false;
			System.out.println("Element is not visible: " + elementforWait);
		}
		return result;
	}

	public boolean commonClick(AppiumDriver<MobileElement> driver, String elementforWait, String strfindType) {
		boolean result = true;
		WebElement element = null;
		try {

			if (strfindType.equalsIgnoreCase("xpath")) {
				driver.findElement(By.xpath(elementforWait)).click();
			} else if (strfindType.equalsIgnoreCase("id")) {
				driver.findElement(By.xpath(elementforWait)).click();
			} else if (strfindType.equalsIgnoreCase("class")) {
				driver.findElement(By.xpath(elementforWait)).click();
			} else if (strfindType.equalsIgnoreCase("class")) {
				driver.findElement(By.name(elementforWait)).click();
			}
			Thread.sleep(500);
		} catch (Exception e) {
			result = false;
			System.out.println("Element is not clickable: " + elementforWait);
		}
		return result;
	}

	public void commonClick(MobileElement iclickInfo) throws Exception {

		try {
			iclickInfo.click();
			Thread.sleep(500);

		} catch (Exception e) {

		}
	}

	public void commonClick(WebElement iclickInfo) throws Exception {

		try {
			iclickInfo.click();
			Thread.sleep(500);

		} catch (Exception e) {

		}
	}

	/**
	 * @param iTextBoxInfo WebElement reference
	 * @param sText        String type text which will be set in text box
	 * @return boolean True/False as a result on the basis of findings
	 * @throws IOException                  if IO exception occurred
	 * @throws ParserConfigurationException if parse configuration exception
	 *                                      occurred
	 * @throws SAXException                 if SAX exception occurred
	 */
	public boolean commonSetTextTextBox(WebElement iTextBoxInfo, String sText) throws Exception {

		boolean Result = true;
		try {
			iTextBoxInfo.click();
			iTextBoxInfo.clear();
			Thread.sleep(1000);
			iTextBoxInfo.sendKeys(sText);
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				Result = commonVerifyValueTextBox(iTextBoxInfo, sText);
			}

		} catch (Exception e) {
			Result = false;
		}
		return Result;
	}

	/**
	 * @param iTextBoxInfo   WebElement reference
	 * @param sExpectedValue String type expected value
	 * @return boolean True/False as a result on the basis of verification pass or
	 *         fail
	 * @throws IOException                  if IO exception occurred
	 * @throws SAXException                 if SAX exception occurred
	 * @throws ParserConfigurationException if parser configuration exception
	 *                                      occurred
	 */
	public boolean commonVerifyValueTextBox(WebElement iTextBoxInfo, String sExpectedValue) throws Exception {

		String sTempStr = null;
		boolean Result = false;

		try {
			sExpectedValue = sExpectedValue.trim().toLowerCase();
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				sTempStr = iTextBoxInfo.getText().trim().toLowerCase();

			} else {
				sTempStr = iTextBoxInfo.getAttribute("name").trim().toLowerCase();

			}

			if ((sTempStr.contains(sExpectedValue))) {
				Result = true;
			} else {
				Result = false;
			}
		} catch (Exception e) {
			Result = false;
		}
		return Result;
	}

	public boolean commonSetTextTextBox(WebElement iTextBoxInfo, String sText, String attribute) throws Exception {

		boolean Result = true;
		try {
			iTextBoxInfo.clear();
			iTextBoxInfo.click();
			Thread.sleep(1000);
			iTextBoxInfo.sendKeys(sText);
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				Result = commonVerifyValueTextBox(iTextBoxInfo, sText, attribute);
			}

		} catch (Exception ex) {
			Result = false;
		}
		return Result;
	}

	public boolean commonVerifyValueTextBox(WebElement iTextBoxInfo, String sExpectedValue, String attribute)
			throws Exception {

		String sTempStr = null;
		boolean Result = false;

		try {
			sExpectedValue = sExpectedValue.trim().toLowerCase();
			sTempStr = iTextBoxInfo.getAttribute(attribute).trim().toLowerCase();

			if ((sTempStr.contains(sExpectedValue))) {
				Result = true;
			} else {
				Result = false;
			}
		} catch (Exception e) {
			Result = false;
		}
		return Result;
	}

	public JsonObject getJsonData(String strfileLocation) {
		JsonElement jsonData = null;
		try {
			jsonData = new JsonParser().parse(new FileReader(strfileLocation));
			// System.out.println(jsonData.getAsJsonObject());
			// System.out.println(jsonData.getAsJsonObject().get("मराठी").getAsJsonObject().get("title"));
		} catch (Exception e) {

		}
		return jsonData.getAsJsonObject();
	}

	public boolean commonWaitForListSizeTobeGreaterThanZero(AppiumDriver<MobileElement> driver, By locator,
			int timeOutInSeconds) {
		boolean result = true;
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			List<WebElement> elementLst = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
			if (elementLst.size() == 0) {
				return result;
			}
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	public boolean commonWaitpresenceOfElementLocated(WebDriver driver, int timeOutInSeconds, By locator)
			throws TimeoutException {

		boolean result = true;
		WebElement element = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			if (element == null) {
				result = false;
			}
		} catch (NoSuchElementException e) {
		} catch (Exception e) {
		}
		return result;
	}

	public Boolean scrollByID(String xpath, AppiumDriver<MobileElement> driver) {
		Boolean result = false;
		try {
			driver.findElement(MobileBy.AndroidUIAutomator(
							"new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
									".scrollIntoView(new UiSelector().resourceId(\"" + xpath + "\").instance(0));"))
					.click();

			result = true;
		} catch (Exception e) {
			System.out.println("Error: Element not found or scroll not working.");
		}
		return result;
	}

	public static String randomPhoneNumber(long len, String start) {
		String num = null;
		try {
			if (len > 10)
				throw new IllegalStateException("To many digits");
			long tLen = (long) Math.pow(10, len - 1) * 9;

			long number = (long) (Math.random() * tLen) + (long) Math.pow(10, len - 1) * 1;

			String tVal = start + number + "";

			num = tVal.substring(0, 10);

			if (num.length() != len) {

				throw new IllegalStateException("The random number '" + num + "' is not '" + len + "' digits");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to generate Random Mobile number");

		}
		return num;
	}

	public static String getCurrentDateTime() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		return currentDateTime.toString();
	}

	public int getRandomNumber(int start, int end) {
		Random rand = new Random();
		int randomNum = rand.nextInt((end - start) + 1) + start;
		return randomNum;
	}

	/**
	 * @param //iTextBoxInfo WebElement reference
	 * @param sText        String type text which will be set in text box
	 * @return boolean True/False as a result on the basis of findings
	 * @throws IOException                  if IO exception occurred
	 * @throws ParserConfigurationException if parse configuration exception
	 *                                      occurred
	 * @throws SAXException                 if SAX exception occurred
	 */
	public boolean commonSetTextTextBox_Action(AppiumDriver<MobileElement> driver, String sText) throws Exception {

		boolean Result = false;
		try {
			if (!ConfigFileReader.strRunMode.equalsIgnoreCase("Cloud")) {
				@SuppressWarnings("rawtypes")
				List enterText = Arrays.asList("text", sText);

				Map<String, Object> cmd = ImmutableMap.of("command", "input", "args", enterText);

				driver.executeScript("mobile: shell", cmd);
			} else {
				Actions action = new Actions(driver);
				action.sendKeys(sText).perform();
			}
		} catch (Exception e) {
			Result = false;
		}
		return Result;
	}

	@SuppressWarnings("rawtypes")
	public void sendKeys(AppiumDriver<MobileElement> driver, String sText) {
		try {
			if (!ConfigFileReader.strRunMode.equalsIgnoreCase("Cloud")) {
				List enterText = Arrays.asList("text", sText);

				Map<String, Object> cmd = ImmutableMap.of("command", "input", "args", enterText);

				driver.executeScript("mobile: shell", cmd);
			} else {
				Actions action = new Actions(driver);
				action.sendKeys(sText).perform();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public boolean commonWaitForElementToPresent(AppiumDriver<MobileElement> driver, String elementforWait,
			int timeOutInSeconds) {
		boolean result = true;
		try {
			// Element to be present
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementforWait)));
			result = element.isDisplayed();
		} catch (Exception e) {
			result = false;
			System.out.println("Element is not visible: "+ elementforWait);
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes" })
	public boolean scrollUtillTheElementFound(AppiumDriver<MobileElement> driver, String elementToFind,
			String strFindBy) {
		boolean result = true, isFound = false;
		int count = 0;
		List<MobileElement> elements = null;
		try {

			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			if (strFindBy.equalsIgnoreCase("xpath")) {
				elements = driver.findElements(By.xpath(elementToFind));
			} else if (strFindBy.equalsIgnoreCase("id")) {
				elements = driver.findElements(By.id(elementToFind));
			}
			isFound = elements.size() > 0;
			TouchAction action = new TouchAction(driver);
			Dimension size = driver.manage().window().getSize();
			int width = size.width;
			int height = size.height;
			int middleOfX = width / 2;
			int startYCoordinate = (int) (height * .7);
			int endYCoordinate = (int) (height * .2);
			while (!isFound && count < 5) {
				count = count + 1;
				action.press(PointOption.point(middleOfX, startYCoordinate))
						.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
						.moveTo(PointOption.point(middleOfX, endYCoordinate)).release().perform();
				if (strFindBy.equalsIgnoreCase("xpath")) {
					isFound = driver.findElements(By.xpath(elementToFind)).size() > 0;
				} else if (strFindBy.equalsIgnoreCase("id")) {
					isFound = driver.findElements(By.id(elementToFind)).size() > 0;
				}
			}

			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		} catch (Exception e) {
			System.out.println("Error: Element not found or scroll not working.");
		}
		return isFound;
	}

	public static String convert(String str) {

		// Create a char array of given String
		char ch[] = str.toCharArray();
		for (int i = 0; i < str.length(); i++) {

			// If first character of a word is found
			if (i == 0 && ch[i] != ' ' || ch[i] != ' ' && ch[i - 1] == ' ') {

				// If it is in lower-case
				if (ch[i] >= 'a' && ch[i] <= 'z') {

					// Convert into Upper-case
					ch[i] = (char) (ch[i] - 'a' + 'A');
				}
			}

			// If apart from first character
			// Any one is in Upper-case
			else if (ch[i] >= 'A' && ch[i] <= 'Z')

				// Convert into Lower-Case
				ch[i] = (char) (ch[i] + 'a' - 'A');
		}

		// Convert the char array to equivalent String
		String st = new String(ch);
		System.out.println("new string: " + st);
		return st;
	}

	/**
	 * @param iTextBoxInfo WebElement reference
	 * @param sText        String type text which will be set in text box
	 * @return boolean True/False as a result on the basis of findings
	 * @throws IOException                  if IO exception occurred
	 * @throws ParserConfigurationException if parse configuration exception
	 *                                      occurred
	 * @throws SAXException                 if SAX exception occurred
	 */
	public boolean commonClearAndSendKeys(AppiumDriver<MobileElement> driver, MobileElement iTextBoxInfo, String sText)
			throws Exception {

		boolean Result = true;
		String prefieldText;
		try {
			iTextBoxInfo.click();
			prefieldText = iTextBoxInfo.getText().toString();
			prefieldText = prefieldText.split(",")[0];
			for (int i = 0; i < prefieldText.length(); i++) {
				// ((PressesKey) driver).pressKeyCode(67);
				Thread.sleep(500);
			}
			if (ConfigFileReader.strRunMode.equalsIgnoreCase("cloud")) {
				commonSetTextTextBox_Action(driver, sText);
			} else {
				commonSetTextTextBox_Action(driver, sText);
			}
			driver.hideKeyboard();
		} catch (Exception e) {
			Result = false;
		}
		return Result;
	}

	public MobileElement commonGetElement(AppiumDriver<MobileElement> driver, String elementforWait,
			String strfindType) {
		MobileElement element = null;
		try {
			if (strfindType.equalsIgnoreCase("xpath")) {
				element = driver.findElement(By.xpath(elementforWait));
			} else if (strfindType.equalsIgnoreCase("id")) {
				element = driver.findElement(By.id(elementforWait));
			} else if (strfindType.equalsIgnoreCase("class")) {
				element = driver.findElement(By.className(elementforWait));
			} else if (strfindType.equalsIgnoreCase("name")) {
				element = driver.findElement(By.name(elementforWait));
			}
		} catch (Exception e) {
			System.out.println("commonGetElement is not visible: " + elementforWait);
		}
		return element;
	}

	public List<MobileElement> commonGetElements(AppiumDriver<MobileElement> driver, String elementforWait,
			String strfindType) {
		List<MobileElement> element = null;
		try {
			if (strfindType.equalsIgnoreCase("xpath")) {
				element = driver.findElements(By.xpath(elementforWait));
			} else if (strfindType.equalsIgnoreCase("id")) {
				element = driver.findElements(By.id(elementforWait));
			} else if (strfindType.equalsIgnoreCase("class")) {
				element = driver.findElements(By.className(elementforWait));
			} else if (strfindType.equalsIgnoreCase("name")) {
				element = driver.findElements(By.name(elementforWait));
			}
		} catch (Exception e) {
			System.out.println("commonGetElements are not visible: " + elementforWait);
		}

		return element;
	}

	public WebElement commonGetElement(WebDriver driver, String elementforWait, String strfindType) {
		WebElement element = null;
		try {
			if (strfindType.equalsIgnoreCase("xpath")) {
				element = driver.findElement(By.xpath(elementforWait));
			} else if (strfindType.equalsIgnoreCase("id")) {
				element = driver.findElement(By.id(elementforWait));
			} else if (strfindType.equalsIgnoreCase("css")) {
				element = driver.findElement(By.cssSelector(elementforWait));
			}
		} catch (Exception e) {
			System.out.println("commonGetElement is not visible: " + elementforWait);
		}
		return element;
	}

	public List<WebElement> commonGetElements(WebDriver driver, String elementforWait, String strfindType) {
		List<WebElement> element = null;
		try {
			if (strfindType.equalsIgnoreCase("xpath")) {
				element = driver.findElements(By.xpath(elementforWait));
			} else if (strfindType.equalsIgnoreCase("id")) {
				element = driver.findElements(By.id(elementforWait));
			} else if (strfindType.equalsIgnoreCase("class")) {
				element = driver.findElements(By.className(elementforWait));
			} else if (strfindType.equalsIgnoreCase("css")) {
				element = driver.findElement(By.cssSelector(elementforWait));
			}
		} catch (Exception e) {
			System.out.println("commonGetElements are not visible: " + elementforWait);
		}
		return element;
	}

	public List<WebElement> commonWaitForElementToBeGreaterThan(AppiumDriver<MobileElement> driver,
			String elementforWait, String strfindType, int timeOutInSeconds) {
		List<WebElement> elements = null;
		try {
			// Element is Clickable - it is Displayed and Enabled.
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			if (strfindType.equalsIgnoreCase("xpath")) {
				elements = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(strfindType), 1));
			} else if (strfindType.equalsIgnoreCase("id")) {
				elements = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.id(strfindType), 1));
			}
		} catch (Exception e) {
			System.out.println("commonWaitForElementToBeGreaterThan is not visible: " + elementforWait);
		}
		return elements;
	}

	public HashMap<String, Integer> getMemoryInfo(AppiumDriver<MobileElement> driver, String strPackage,
			String strPerfType, int strMemoryCaptureWait) throws Exception {
		List<List<Object>> data = ((HasSupportedPerformanceDataType) driver).getPerformanceData(strPackage, strPerfType,
				strMemoryCaptureWait);
		HashMap<String, Integer> readableData = new HashMap<>();
		for (int i = 0; i < data.get(0).size(); i++) {
			int val;
			if (data.get(1).get(i) == null) {
				val = 0;
			} else {
				val = Integer.parseInt((String) data.get(1).get(i));
			}
			readableData.put((String) data.get(0).get(i), val);
		}
		return readableData;
	}

	public static String getCurrentDateInGivenFormat(String dateFormat) {
		SimpleDateFormat DateFor;
		String stringDate = null;
		try {
			Date date = new Date();
			DateFor = new SimpleDateFormat(dateFormat);
			stringDate = DateFor.format(date);
			System.out.println("Date Format with E, dd MMM yyyy:" + stringDate);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return stringDate;
	}

	public void closePopUpIfExist(AppiumDriver<MobileElement> driver) {
		List<MobileElement> popups;
		try {
			Thread.sleep(10000);
			popups = commonGetElements(driver, "android.widget.ImageView", "class");
			if (popups.size() == 2) {
				commonClick(popups.get(1));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String getTimedifference(long start, long end) {
		String strTime = null;
		try {
			NumberFormat formatter = new DecimalFormat("#0.00000");
			strTime = formatter.format((end - start) / 1000d) + " seconds";
			System.out.println(strTime);

		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
		return strTime;
	}

	public static String getFutureDateTime(String currentDate, int numberOfDays) {
		String newDate = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();

			c.setTime(sdf.parse(currentDate));

			// Incrementing the date by n day
			c.add(Calendar.DAY_OF_MONTH, numberOfDays);
			newDate = sdf.format(c.getTime());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return newDate;
	}

	public static String getFutureDateTime(String currentDate, int numberOfDays, String strPattern) {
		String newDate = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(strPattern);
			Calendar c = Calendar.getInstance();

			c.setTime(sdf.parse(currentDate));

			// Incrementing the date by n day
			c.add(Calendar.DAY_OF_MONTH, numberOfDays);
			newDate = sdf.format(c.getTime());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return newDate;
	}

	public static String getCurrentate(String strPattern) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strPattern);

		String date = simpleDateFormat.format(new Date());
		System.out.println(date);
		return date;
	}

	public static Integer RandomNumber(int start, int end) {
		Random rand = new Random();
		int randomNum = rand.nextInt((end - start) + 1) + start;
		return randomNum;

	}

	public static Date convertStringToDate(String strDate) {
		Date date = null;
		try {
			DateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
			date = formatter.parse(strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Long getEPochTimeInMilliSeconds(int extratTime, TimeUnit timeUnit) {
		Date date = null;
		long epocTime = 0;
		Instant epocTimeInst = null;
		try {
			if (timeUnit.equals(TimeUnit.SECONDS)) {
				epocTimeInst = Instant.now().plus(extratTime, ChronoUnit.SECONDS);
			} else if (timeUnit.equals(TimeUnit.MINUTES)) {
				epocTimeInst = Instant.now().plus(extratTime, ChronoUnit.MINUTES);
			} else if (timeUnit.equals(TimeUnit.HOURS)) {
				epocTimeInst = Instant.now().plus(extratTime, ChronoUnit.HOURS);
			} else if (timeUnit.equals(TimeUnit.MILLISECONDS)) {
				epocTimeInst = Instant.now().plus(extratTime, ChronoUnit.MILLIS);
			}
			epocTime = epocTimeInst.toEpochMilli();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return epocTime;
	}

	public static boolean isSorted(List<Date> dateString) {
		for (int i = 0; i < dateString.size() - 1; ++i) {
			System.out.println(dateString.get(i));
			if (dateString.get(i).compareTo(dateString.get(i + 1)) > 0)
				return false;
		}
		return true;
	}

	@SuppressWarnings("rawtypes")
	public boolean scrollUpUtillTheElementFound(AppiumDriver<MobileElement> driver, String elementToFind) {
		boolean result = true, isFound = false;
		int count = 0;
		try {

			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			List<MobileElement> elements = driver.findElements(By.xpath(elementToFind));
			isFound = elements.size() > 0;
			TouchAction action = new TouchAction(driver);
			Dimension size = driver.manage().window().getSize();
			int width = size.width;
			int height = size.height;
			int middleOfX = width / 2;
			int endYCoordinate = (int) (height * .7);
			int startYCoordinate = (int) (height * .2);
			while (!isFound && count < 5) {
				count = count + 1;
				action.press(PointOption.point(middleOfX, startYCoordinate))
						.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
						.moveTo(PointOption.point(middleOfX, endYCoordinate)).release().perform();
				isFound = driver.findElements(By.xpath(elementToFind)).size() > 0;
			}

			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		} catch (Exception e) {
			System.out.println("Error: Element not found or scroll not working.");
		}
		return isFound;
	}

	/**
	 * @param iTextBoxInfo WebElement reference
	 * @param sText        String type text which will be set in text box
	 * @return boolean True/False as a result on the basis of findings
	 * @throws IOException                  if IO exception occurred
	 * @throws ParserConfigurationException if parse configuration exception
	 *                                      occurred
	 * @throws SAXException                 if SAX exception occurred
	 */

	public boolean commonClearTextAndSendKeys(AppiumDriver<MobileElement> driver, MobileElement iTextBoxInfo,
			String sText) throws Exception {

		boolean Result = true;
		String prefieldText;
		try {
			iTextBoxInfo.click();
			prefieldText = iTextBoxInfo.getText().toString();
			prefieldText = prefieldText.split(",")[0];
			for (int i = 0; i < prefieldText.length(); i++) {
				// ((PressesKey) driver).pressKeyCode(67);
			}
			if (ConfigFileReader.strRunMode.equalsIgnoreCase("cloud")) {
				commonSetTextTextBox_Action(driver, sText);
			} else {
				commonSetTextTextBox_Action(driver, sText);
			}
		} catch (Exception e) {
			Result = false;
		}
		return Result;
	}

	public String getLangaugeEnum(String strLangauge) {
		String strLanguagekey = null;
		try {
			if (strLangauge.equalsIgnoreCase("English")) {
				strLanguagekey = "en";
			} else if (strLangauge.equalsIgnoreCase("Marathi")) {
				strLanguagekey = "mr";
			} else if (strLangauge.equalsIgnoreCase("Hinglish")) {
				strLanguagekey = "hr";
			} else if (strLangauge.equalsIgnoreCase("Hindi")) {
				strLanguagekey = "hi";
			} else if (strLangauge.equalsIgnoreCase("Panjabi")) {
				strLanguagekey = "pa";
			} else if (strLangauge.equalsIgnoreCase("Tamil")) {
				strLanguagekey = "ta";
			} else if (strLangauge.equalsIgnoreCase("Telugu")) {
				strLanguagekey = "te";
			} else if (strLangauge.equalsIgnoreCase("Gujarati")) {

				strLanguagekey = "gu";
			} else if (strLangauge.equalsIgnoreCase("Assamese")) {

				strLanguagekey = "as";
			} else if (strLangauge.equalsIgnoreCase("Bengali")) {

				strLanguagekey = "bn";
			} else if (strLangauge.equalsIgnoreCase("Kannada")) {

				strLanguagekey = "kn";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return strLanguagekey;

	}

	@SuppressWarnings("rawtypes")
	public void scrollUtill(AppiumDriver<MobileElement> driver, int noOfTime, direction DIRECTION) {
		int count = 0;
		try {
			if (DIRECTION == direction.DOWN) {
				if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios")) {
					TouchAction action = new TouchAction(driver);
					Dimension size = driver.manage().window().getSize();
					int width = size.width;
					int height = size.height;
					int middleOfX = width / 2;
					int startYCoordinate = (int) (height * .7);
					int endYCoordinate = (int) (height * .2);
					while (count < noOfTime) {
						count = count + 1;
						action.press(PointOption.point(middleOfX, startYCoordinate))
								.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
								.moveTo(PointOption.point(middleOfX, endYCoordinate)).release().perform();
					}
				} else {
					while (count < noOfTime) {
						count = count + 1;
						scrollDown(driver);
					}
				}

			} else if (DIRECTION == direction.UP) {
				if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios")) {

					TouchAction action = new TouchAction(driver);
					Dimension size = driver.manage().window().getSize();
					int width = size.width;
					int height = size.height;
					int middleOfX = width / 2;
					int startYCoordinate = (int) (height * .2);
					int endYCoordinate = (int) (height * .7);
					while (count < noOfTime) {
						count = count + 1;
						action.press(PointOption.point(middleOfX, startYCoordinate))
								.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
								.moveTo(PointOption.point(middleOfX, endYCoordinate)).release().perform();
					}
				} else {
					while (count < noOfTime) {
						count = count + 1;
						scrollUp(driver);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Error: Element not found or scroll not working.");
		}
	}

	public void hideKeyBoard(AppiumDriver<MobileElement> driver) {
		try {
			driver.hideKeyboard();
		} catch (Exception e) {
			System.out.println(Arrays.toString(e.getStackTrace()));
		}
	}

	public Map<String, String> getCapability(String port, String udid) {
		Map<String, String> capebilityMap = new HashMap<>();
		String strDeviceIndex;
		try {
			if (ConfigFileReader.strEnv.contains("PRODUCTION")) {
				capebilityMap.put("appPackage", configReaderObj.getAppPackageActivity().split("/")[0]);
				capebilityMap.put("appActivity", configReaderObj.getAppPackageActivity().split("/")[1]);

			} else {
				capebilityMap.put("appActivity", configReaderObj.getAppPackageActivity().split("/")[1]);
				capebilityMap.put("appPackage", configReaderObj.getAppPackageActivity().split("/")[0]);
			}

			if (ConfigFileReader.strRunMode.equalsIgnoreCase("local")
					|| ConfigFileReader.strRunMode.equalsIgnoreCase("localLab")) {
				capebilityMap.put("udid", udid);
//				capebilityMap.put("remoteAddress", "https://617f-14-195-208-46.ngrok-free.app/wd/hub");
				capebilityMap.put("remoteAddress", "http://127.0.0.1:" + port + "/wd/hub");
//				capebilityMap.put("remoteAddress", "http://localhost:" + port + "/wd/hub");
				capebilityMap.put("automationName", "UiAutomator2");

			} else if (ConfigFileReader.strRunMode.equalsIgnoreCase("cloud")) {
				capebilityMap.put("deviceName", ConfigFileReader.strDeviceName);
				capebilityMap.put("deviceVersion", ConfigFileReader.strDeviceVersion);
				capebilityMap.put("remoteAddress", "https://" + ConfigFileReader.userName + ":" + ConfigFileReader.accessKey + "@mobile-hub.lambdatest.com/wd/hub");
				capebilityMap.put("strFilePath", ConfigFileReader.strFilePath);
			}
		} catch (Exception ignored) {
		}
		return capebilityMap;
	}

	public List<Environment> getDeviceData() {
		JsonElement jsonData = null;
		List<Environment> testData = null;
		try {
			jsonData = new JsonParser()
					.parse(new FileReader(System.getProperty("user.dir") + "/src/main/resources/device.json"));
			JsonElement dataSet = jsonData.getAsJsonObject().get("environments").getAsJsonArray();
			testData = new Gson().fromJson(dataSet, new TypeToken<List<Environment>>() {
			}.getType());

		} catch (JsonIOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonSyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return testData;
	}

	public void openDeepLink(AppiumDriver<MobileElement> driver, String strDeepLinkUrl) {
		System.out.println(driver.getPlatformName());
		try {
			if (driver.getPlatformName().equalsIgnoreCase("ios")) {
				Map<String, String> mobileCommand = new HashMap<String, String>();
				mobileCommand.put("bundleId", "com.apple.mobilesafari");
				driver.executeScript("mobile:launchApp", (Map<String, ?>) mobileCommand);
			} else {
				driver.closeApp();
				driver.get(strDeepLinkUrl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void commonClick_Action(AppiumDriver<MobileElement> driver, MobileElement iclickInfo) throws Exception {

		try {
			Actions action = new Actions(driver);
			action.moveToElement(iclickInfo);
			action.click();
			action.perform();
			Thread.sleep(500);

		} catch (Exception e) {

		}
	}

	public void commonClick_Action(WebDriver webDriver, WebElement iclickInfo) throws Exception {

		try {

			Actions action = new Actions(webDriver);
			action.moveToElement(iclickInfo);
			action.click();
			action.perform();
			Thread.sleep(500);

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}

	@SuppressWarnings("rawtypes")
	public boolean scrollUtillTheElementFound(AppiumDriver<MobileElement> driver, MobileElement element,
			String elementToFind) {
		boolean result = true, isFound = false;
		int count = 0;
		try {

			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			List<MobileElement> elements = element.findElements(By.xpath(elementToFind));
			isFound = elements.size() > 0;
			TouchAction action = new TouchAction(driver);
			Dimension size = driver.manage().window().getSize();
			int width = size.width;
			int height = size.height;
			int middleOfX = width / 2;
			int startYCoordinate = (int) (height * .7);
			int endYCoordinate = (int) (height * .2);
			while (!isFound && count < 5) {
				count = count + 1;
				action.press(PointOption.point(middleOfX, startYCoordinate))
						.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
						.moveTo(PointOption.point(middleOfX, endYCoordinate)).release().perform();
				isFound = element.findElements(By.xpath(elementToFind)).size() > 0;
			}

			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		} catch (Exception e) {
			System.out.println("Error: Element not found or scroll not working.");
		}
		return isFound;
	}

	public MobileElement scrollIntoText(AppiumDriver<MobileElement> driver, String visibleText) {
		return driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
						+ visibleText + "\").instance(0))"));
	}

	public void printBanner(String text) {
		try {
			for (int i = 0; i < text.length() + 10; i++) {
				System.out.print("*");
			}
			System.out.print("\n");
			for (int i = 0; i < 5; i++) {
				System.out.print(" ");
			}
			System.out.println(text);
			for (int i = 0; i < 5; i++) {
				System.out.print(" ");
			}
			System.out.print("\n");
			for (int i = 0; i < text.length() + 10; i++) {
				System.out.print("*");
			}
			System.out.print("\n");

		} catch (Exception e) {
			System.out.println("Function not working");
		}
	}

	public void swipeLeftWithNtimes(int numberOfSwipes, AppiumDriver<MobileElement> driver) {
		try {

			// Get screen size
			Dimension screenSize = driver.manage().window().getSize();
			int startX = (int) (screenSize.getWidth() * 0.8);
			int endX = (int) (screenSize.getWidth() * 0.2);
			int centerY = screenSize.getHeight() / 2;

			for (int i = 0; i < numberOfSwipes; i++) {
				PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
				Sequence swipe = new Sequence(finger, 1);

				swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, centerY));
				swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
				swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, centerY));
				swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

				driver.perform(Collections.singletonList(swipe));
			}

		} catch (Exception e) {
			System.out.println("swipeLeftWithNtimes: " + e.getMessage());
		}
	}

	public void swipeRightWithNtimes(int numberOfSwipes, AppiumDriver<MobileElement> driver) {
		try {
			// Get screen size
			Dimension screenSize = driver.manage().window().getSize();
			int startX = (int) (screenSize.getWidth() * 0.2); // Start from left
			int endX = (int) (screenSize.getWidth() * 0.8);   // Move to right
			int centerY = screenSize.getHeight() / 2;        // Center Y coordinate

			for (int i = 0; i < numberOfSwipes; i++) {
				PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
				Sequence swipe = new Sequence(finger, 1);

				swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, centerY));
				swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
				swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, centerY));
				swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

				driver.perform(Collections.singletonList(swipe));
			}

		} catch (Exception e) {
			System.out.println("swipeRightWithNtimes: " + e.getMessage());
		}
	}

	public void swipeLeftOnElement(WebElement element, AppiumDriver<MobileElement> driver) {
		try {
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("ios")) {
				Point point = element.getLocation();
				Dimension size = element.getSize();
				int startX = point.getX() + (size.getWidth() * 8 / 10); // 80% right
				int startY = point.getY() + (size.getHeight() / 2);
				int endX = point.getX() + (size.getWidth() * 2 / 10);   // 20% left
				int endY = startY;
				new TouchAction<>(driver)
						.press(PointOption.point(startX, startY))
						.waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
						.moveTo(PointOption.point(endX - startX, endY - startY)) // moveTo uses relative coordinates!
						.release()
						.perform();
			} else {
				Point point = element.getLocation();
				Dimension size = element.getSize();
				int startX = point.getX() + (size.getWidth() * 8 / 10); // 80% right
				int startY = point.getY() + (size.getHeight() / 2);
				int endX = point.getX() + (size.getWidth() * 2 / 10);   // 20% left
				int endY = startY;
				PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
				Sequence swipe = new Sequence(finger, 1);
				swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
				swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
				swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, endY));
				swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
				driver.perform(Collections.singletonList(swipe));
			}
		} catch (Exception e) {
			System.out.println("swipeRightWithNtimes: " + e.getMessage());
		}
	}

	public void swipeUpOnElement(WebElement element, AppiumDriver<MobileElement> driver) {

		try {
			Point point = element.getLocation();
			Dimension eleSize = element.getSize();
			int centerX = point.getX() + (eleSize.getWidth() / 2);
			int centerY = point.getY() + (eleSize.getHeight() / 2);
			int moveToX = point.getX() + (eleSize.getWidth() / 2);
			int moveToY = point.getY() - 190;
			System.out.println(centerX + " and " + centerY);
			System.out.println(moveToX + " and " + moveToY);
			new TouchAction(driver).press(PointOption.point(centerX, centerY))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					.moveTo(PointOption.point(moveToX, moveToY)).release().perform();
		} catch (Exception e) {
			System.out.println("swipeUpOnElement Function not working");

		}
	}

	public void swipeRightOnElement(WebElement element, AppiumDriver<MobileElement> driver) {
		try {
			Point point = element.getLocation();
			Dimension eleSize = element.getSize();
			int centerX = point.getX() + (eleSize.getWidth() / 2);
			int centerY = point.getY() + (eleSize.getHeight() / 2);
			int moveToX = point.getX() + (eleSize.getWidth()) + 190;
			int moveToY = point.getY() + (eleSize.getHeight() / 2);
			new TouchAction(driver).press(PointOption.point(centerX, centerY))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
					.moveTo(PointOption.point(moveToX, moveToY)).release().perform();
		} catch (Exception e) {
			System.out.println("swipeRightOnElement Function not working");

		}
	}

	/**
	 * This method is developed to perform swipeUp action.
	 *
	 * @param driver
	 * @param xPerc      This variable contains x-axis percentage(it will same for
	 *                   both start and end point).
	 * @param startYPerc This variable contains start y-axis percentage.
	 * @param endYPerc   This variable contains end y-axis percentage.
	 */

	public void swipeUp(AppiumDriver<MobileElement> driver, double xPerc, double startYPerc, double endYPerc) {
		try {
			Dimension size = driver.manage().window().getSize();

			// Define starting and ending points for the swipe
			int startX = (int) (size.getWidth() * xPerc);
			int startY = (int) (size.getHeight() * startYPerc);
			int endY = (int) (size.getHeight() * endYPerc);

			// Create a TouchAction instance
			TouchAction touchAction = new TouchAction(driver);

			// Perform the swipe
			touchAction.press(PointOption.point(startX, startY)).moveTo(PointOption.point(startX, endY)).release()
					.perform();

		} catch (Exception e) {
			System.out.println("swipeUp Function not working");

		}
	}

	public boolean pressAndroidKey(AppiumDriver<MobileElement> driver, key KEY, int noOfTimes) {
		boolean result = true;
		try {
			for (int i = 0; i < noOfTimes; i++) {

				if (KEY == key.POWER) {
					// ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
					((AndroidDriver<MobileElement>) driver)
							.pressKey(new KeyEvent(AndroidKey.POWER));
				} else if (KEY == key.ENTER) {
					// ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
					((AndroidDriver<MobileElement>) driver)
							.pressKey(new KeyEvent(AndroidKey.ENTER));
				}

				else if (KEY == key.BACK) {
					// ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
					((AndroidDriver<MobileElement>) driver)
							.pressKey(new KeyEvent(AndroidKey.BACK));
				}
				Thread.sleep(500);
			}

		} catch (Exception e) {
			result = false;
			System.out.println(e.getMessage());
		}
		return result;
	}

	public enum key {
		BACK, ENTER, POWER
	}

	public void tapOnCenter(AppiumDriver<MobileElement> driver) {
		try {
			Dimension size = driver.manage().window().getSize();
			int startX = (int) (size.getWidth() / 2);
			int startY = (int) (size.getHeight() / 2);

			new TouchAction(driver).tap(PointOption.point(startX, startY)).perform();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void tapOnTopPointerClass(AppiumDriver<MobileElement> driver) {
		try {
			Dimension size = driver.manage().window().getSize();
			int startX = size.getWidth() / 2;
			int startY = (int) (size.getHeight() * 0.1);

			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
			Sequence tap = new Sequence(finger, 1);
			tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
			tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			tap.addAction(new Pause(finger, Duration.ofMillis(100)));
			tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

			driver.perform(List.of(tap));
		} catch (Exception e) {
			System.out.println("Error during tap: " + e.getMessage());
		}
	}


	public void tapOnTop(AppiumDriver<MobileElement> driver) {
		try {
			Dimension size = driver.manage().window().getSize();
			int startX = size.getWidth() / 2;  // Keep X at the center
			int startY = (int) (size.getHeight() * 0.1); // 10% from the top

			new TouchAction<>(driver)
					.tap(PointOption.point(startX, startY))
					.perform();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	public enum direction {
		UP, DOWN
	}

	public enum screenView {
		LANDSCAPE, PORTRAIT
	}

	public int findElementInList(AppiumDriver<MobileElement> driver, List<MobileElement> listPackages,
			String elementToFound) {
		boolean found = false;
		int i = 0;
		try {
			if (listPackages.isEmpty()) {
				throw new Exception("The list is empty");
			}

			while (!found) {
				for (i = 0; i < listPackages.size(); i++) {
					if (listPackages.get(i).getText().equalsIgnoreCase(elementToFound)) {
						found = true;
						break;
					}
				}
				if (!found)
					scrollUtill(driver, 1, direction.DOWN);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return i;
	}

	public boolean updateListSize(List<MobileElement> list) {
		boolean result = true;
		try {
			if (list.size() == 0) {
				Thread.sleep(5000);
			}
			result = list.size() != 0;
		} catch (Exception e) {
			System.out.println("updateListSize_Exception: " + e.getMessage());
			return result;
		}
		return result;
	}

	public boolean handleHints(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			Thread.sleep(1000);
			tapOnCenter(driver);
			Thread.sleep(1000);

		} catch (Exception e) {
			System.out.println("handleHints_Exception: " + e.getMessage());
			return result;
		}
		return result;
	}

	public boolean handleHints(AppiumDriver<MobileElement> driver, int noOfTimes) {
		boolean result = true;
		try {

			for (int i = 0; i < noOfTimes; i++) {
				tapOnCenter(driver);
				Thread.sleep(2000);
			}

		} catch (Exception e) {
			System.out.println("handleHints_Exception: " + e.getMessage());
			return result;
		}
		return result;
	}

	/**
	 * This method is developed to Make the device Internet ON/OFF.
	 * 
	 * @param driver
	 * @param state
	 * @return
	 */
	public boolean changeNetworkState(AppiumDriver<MobileElement> driver, NetworkState state) {
		boolean result = true;
		try {

			if (ConfigFileReader.strRunMode.equalsIgnoreCase("local")) {
				if (state.equals(NetworkState.ON)) {
					((AndroidDriver<MobileElement>) driver).setConnection(new ConnectionState(6));
				} else {
					((AndroidDriver<MobileElement>) driver).setConnection(new ConnectionState(0));
				}
			} else {
				if (state.equals(NetworkState.ON)) {
					// Enable both Wi-Fi and Data
					((AndroidDriver<MobileElement>) driver)
							.setConnection(new ConnectionState(ConnectionState.WIFI_MASK | ConnectionState.DATA_MASK));
				} else {
					// Disable all network connections
					((AndroidDriver<MobileElement>) driver)
							.setConnection(new ConnectionState(ConnectionState.AIRPLANE_MODE_MASK));
				}
			}

		} catch (Exception e) {
			System.out.println("changeNetworkState_Exception: " + e.getMessage());
			return result;
		}
		return result;
	}

	public enum NetworkState {
		ON, OFF
	}

	// TODO
	public boolean countElementsInList(AppiumDriver<MobileElement> driver, List<MobileElement> listElements,
			int ExpectedCount) {
		boolean result = true;
		int ActualCount = 0;
		boolean loop = true;
		List<String> titleElements = new ArrayList<String>();
		try {

			if (listElements.isEmpty())
				throw new Exception("The list is empty");

			while (loop) {
				for (int i = 1; i < listElements.size(); i++) {
					if (!titleElements.contains(listElements.get(i).getText())) {
						ActualCount++;
						titleElements.add(listElements.get(i).getText());
					}
					System.out.println(titleElements.toString() + " <-------- " + listElements.get(i).getText());
				}
				scrollUtill(driver, 1, direction.DOWN);
				System.out.println(listElements.get(listElements.size() - 1).getText() + "----"
						+ titleElements.get(titleElements.size() - 1));
				if (listElements.get(listElements.size() - 1).getText()
						.equalsIgnoreCase(titleElements.get(titleElements.size() - 1).toString())) { // not workingg
					loop = false;
				}
			}
			result = ActualCount == ExpectedCount;

		} catch (Exception e) {
			System.out.println("countElementsInList_Exception: " + e.getMessage());
		}
		return result;
	}

	public MobileElement getElementFromAttribute(AppiumDriver<MobileElement> driver, String attributeName,
			String attributeValue) {
		MobileElement element = null;
		try {

			Thread.sleep(2000);

			element = commonGetElement(driver, "//*[contains(@" + attributeName + ",'" + attributeValue + "')]",
					"xpath");

		} catch (Exception e) {
			System.out.println("getElementFromText_Exception: " + e.getMessage());
		}
		return element;
	}

	public void skipCoachMarkSocial(AppiumDriver<MobileElement> driver) {
		try {
			String xpath = "//*[contains(@content-desc,'coachMarkLocator')]";
			if (!commonWaitForElementToBeLocatedAndVisible(driver, xpath, "xpath", 10)) return;

			MobileElement coachMark = commonGetElement(driver, xpath, "xpath");
			for (int i = 0; i < 15 && commonWaitForElementToBeVisible(driver, coachMark, 5); i++) {
				tapOnCenter(driver);
				Thread.sleep(200);
			}
		} catch (Exception e) {
		}
	}

	public static String getFutureDateTime(String strPattern, String strAddType, int noOf, boolean isUTC) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strPattern);
		if (isUTC) {
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		}
		String newDate;
		String date = simpleDateFormat.format(new Date());
		System.out.println(date);
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(simpleDateFormat.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (strAddType.equalsIgnoreCase("min")) {
			c.add(Calendar.MINUTE, noOf);
		} else if (strAddType.equalsIgnoreCase("hr")) {
			c.add(Calendar.HOUR, noOf);
		}
		newDate = simpleDateFormat.format(c.getTime());
		System.out.println(newDate);
		return newDate;
	}

	public enum TimeType {
		hr, min;
	}

	public static String getFutureTimeInISTFormat(String strPattern, TimeType timeType, int noOf) {
		String futureISTFormatted = null;
		Duration duration = null;
		try {
			Instant utcTime = Instant.now();

			// Convert UTC to IST
			ZoneId istZone = ZoneId.of("Asia/Kolkata");
			ZonedDateTime istDateTime = ZonedDateTime.ofInstant(utcTime, istZone);

			if (timeType.equals(TimeType.hr)) {
				// Define the duration to add to the current time (e.g., 2 hours)
				duration = Duration.ofHours(noOf); // You can change this as needed
			} else if (timeType.equals(TimeType.min)) {
				duration = Duration.ofMinutes(noOf); // You can change this as needed
			}

			// Calculate the future IST time
			ZonedDateTime futureIST = istDateTime.plus(duration);

			// Format the future IST time
			// String strPattern = "yyyy-MM-dd HH:mm:ss"; // Define your desired pattern
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(strPattern);
			futureISTFormatted = futureIST.format(formatter);

			System.out.println("Current IST time: " + istDateTime.format(formatter));
			System.out.println("Future IST time: " + futureISTFormatted);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return futureISTFormatted;
	}

	public static String getCurrentDate(String strFormat) {
		Format formatter = new SimpleDateFormat(strFormat);
		String s = formatter.format(new Date());
		System.out.println(s);
		return s;
	}

	/**
	 * This method is developed to perform switching action between two application.
	 * This method is applicable only for android.
	 * 
	 * @param driver
	 * @param appPackage  is a String variable which store application's package
	 *                    name.
	 * @param appActivity is a String variable which store application's
	 *                    appActivity.
	 * @return boolean
	 */
	public boolean switchOneApplicationToAnother(AppiumDriver<MobileElement> driver, String appPackage,
			String appActivity) {
		boolean result = true;
		try {
			AndroidDriver<MobileElement> androidDriver = (AndroidDriver<MobileElement>) driver;
			androidDriver.startActivity(new Activity(appPackage, appActivity));
		} catch (Exception e) {
			result = false;
			System.out.println("switchOneApplicationToAnother_Exception" + e.getMessage());
		}
		return result;
	}

	/**
	 * This method is developed to perform switching operation between NATIVEVIEW
	 * and WEBVIEW.
	 * 
	 * @param driver
	 * @param switchView is a String variable which store switching
	 *                   Mode(NATIVE/WEB).
	 * @return
	 */

	public boolean switchBetweenWebViewAndNativeView(AppiumDriver<MobileElement> driver, String switchView) {
		boolean result = true;
		try {
			Set<String> contextNames = driver.getContextHandles();
			for (String contextName : contextNames) {
				System.out.println(contextName);
				if (contextName.contains("WEB") && switchView.contains("WEBVIEW")) {
					System.out.println("Switching to :" + contextName);
					driver.context(contextName);
					break;
				} else if (contextName.contains("NATIVE") && switchView.contains("NATIVE")) {
					System.out.println("Switching to :" + contextName);
					driver.context(contextName);
					break;
				}
			}
		} catch (Exception e) {
			result = false;
			System.out.println("switchBetweenWebViewAndNativeView_Exception" + e.getMessage());
		}
		return result;
	}

	public boolean commonWaitForElementToBeVisible(WebDriver driver, WebElement elementforWait, int timeOutInSeconds) {
		boolean result = true;
		try {
			// Element is Clickable - it is Displayed and Enabled.
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			WebElement element = wait.until(ExpectedConditions.visibilityOf(elementforWait));
			result = element.isDisplayed();
			if (!result) {
				System.out.println("Element not visible");
			}

		} catch (Exception e) {
			result = false;
			System.out.println("Element is not visible: " + elementforWait);
		}
		return result;
	}

	/**
	 * This method is developed to push the file to Emulator.
	 * 
	 * @param driver
	 * @param sourcePath
	 * @param remotePath
	 * @return
	 */
	public boolean pushFileToEmulator(AppiumDriver<MobileElement> driver, String sourcePath, String remotePath) {
		boolean result = true;
		try {
			if (ConfigFileReader.strApplicationType.equalsIgnoreCase("Android")) {
				AndroidDriver<MobileElement> androidDriver = (AndroidDriver<MobileElement>) driver;
				androidDriver.pushFile(remotePath, new File(sourcePath));
			} else {
				IOSDriver<MobileElement> iosDriver = (IOSDriver<MobileElement>) driver;
				iosDriver.pushFile(remotePath, new File(sourcePath));
			}
		} catch (Exception e) {
			result = false;
			System.out.println(e.getMessage());
		}
		return result;
	}

	public boolean changeScreenOrientation(AppiumDriver<MobileElement> driver, screenView orientation) {
		boolean result = true;
		try {
			if (orientation.equals(screenView.LANDSCAPE)) {
				driver.rotate(ScreenOrientation.LANDSCAPE);
			} else {
				driver.rotate(ScreenOrientation.PORTRAIT);
			}
		} catch (Exception e) {
			result = false;
			System.out.println(e.getMessage());
		}
		return result;
	}

	public boolean waitWhileLoading(AppiumDriver<MobileElement> driver, String elementLocator, String strfindType) {
		boolean result = true;
		boolean progressing = true;
		int count = 0;
		try {

			while (progressing) {
				if (count > 5)
					return false;
				progressing = commonWaitForElementToBeVisible(driver,
						commonGetElement(driver, elementLocator, strfindType), 5);
				Thread.sleep(5000);
				count++;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	public boolean allowPermission(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {
			MobileElement btnAllow = commonGetElement(driver,
					"com.android.permissioncontroller:id/permission_allow_button", "id");
			if (commonWaitForElementToBeVisible(driver, btnAllow, 5)) {
				commonClick(btnAllow);
			} else {
				System.out.println("---> Permission pop-up did not appeared");
				return false;
			}

		} catch (Exception e) {
			System.out.println("allowPermission: " + e.getMessage());
		}
		return result;
	}

	public static long getCurrentTimePlusTenMinutes() {
		long currentTime = Instant.now().getEpochSecond();
		long tenMinutesLater = (currentTime + (10 * 60)) * 1000;
		return tenMinutesLater;
	}

	public static String getCurrentDateTime(String format, int addDays) {
		LocalDateTime currentDateTime = LocalDateTime.now(ZoneOffset.UTC);
		LocalDateTime modifiedDateTime = currentDateTime.plusDays(addDays);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		String formattedDateTime = modifiedDateTime.format(formatter);
		System.out.println(formattedDateTime);
		return formattedDateTime;
	}

	public static String getAlphaNumericString(int n) {

		// choose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	/**
	 * This method is used to take the Screenshot of a particular page.
	 * 
	 * @param driver
	 * @param screenshotName
	 * @return
	 * @throws IOException
	 */

	public File takeScreenshot(AppiumDriver<MobileElement> driver, String screenshotName) throws IOException {
		System.out.println("Taking a screenshot: " + screenshotName);
		File dest = null;
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
			dest = new File("./ExpectedScreenshots/AddaScreenShot/" + screenshotName + ".PNG");
		} else {
			dest = new File("./ExpectedScreenshots/SankalpScreenShot/" + screenshotName + ".PNG");
		}
		Files.copy(src, dest);
		return dest;
	}

	/**
	 * This method is developed to Compare two images.
	 * 
	 * @param image1
	 * @param image2
	 * @return
	 * @throws IOException
	 */
	public double compareImage(File image1, File image2) throws IOException {
//		System.out.println("comparing images :" + image1.getAbsolutePath() + " with image: " + image2);
//		Pattern pattern1 = new Pattern(image1.getAbsolutePath());
//		Pattern pattern2 = new Pattern(image2.getAbsolutePath());
//		Finder f = new Finder(pattern1.getImage());
//		f.find(pattern2);
//		if (f.hasNext()) {
//			Match m = f.next();
//			System.out.println("Match found with: " + (m.getScore()) * 100 + "%");
//			return m.getScore() * 100;
//		} else {
//			System.out.println("Image not found similar");
//		}
		return 0;
	}

	public boolean performPastAction(AppiumDriver<MobileElement> driver, MobileElement element) {
		boolean result = true;
		try {
			element.click();
			Thread.sleep(1000);
			TouchAction touchAction = new TouchAction(driver);

			// Perform long press action on the element
			touchAction.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(element)))
					.release().perform();

		} catch (Exception e) {
			result = false;
			System.out.println(e.getMessage());
		}
		return result;
	}

	public boolean compareTwoImages(AppiumDriver<MobileElement> driver, String expectedImage, String actualImage,
			double comparePercentage) {
		boolean result = true;
		double compImagePercentage = 0;
		try {
			File expectedScreenshot = takeScreenshot(driver, expectedImage);
			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
				compImagePercentage = compareImage(expectedScreenshot,
						new File("./ActualScreenshots/AddaScreenShot/" + actualImage + ".png"));
			} else {
				compImagePercentage = compareImage(expectedScreenshot,
						new File("./ActualScreenshots/SankalpScreenShot/" + actualImage + ".png"));
			}
			result = compImagePercentage > comparePercentage;
			if (!result) {
				System.out.println("Actual Image and Expected image both are different.");
				return result;
			}
		} catch (Exception e) {
			result = false;
			System.out.println(e.getMessage());
		}
		return result;
	}

	/**
	 * This method is developed to tab elements end point.
	 * 
	 * @param driver
	 * @param element
	 */
	public void tapOnElementEndpoint(AppiumDriver<MobileElement> driver, MobileElement element) {
		try {
			Point elementLocation = element.getLocation();

			// Get the size of the element (width and height)
			Dimension elementSize = element.getSize();

			// Calculate the end point (bottom-right corner) of the element
			int endX = elementLocation.getX() + elementSize.getWidth() - 5;
			int endY = elementLocation.getY() + elementSize.getHeight() - 2;
			System.out.println("x---->" + endX + "<------y----->" + endY);
			new TouchAction(driver).tap(PointOption.point(endX, endY)).perform();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void tapOnElement(AppiumDriver<MobileElement> driver, MobileElement element) {
		try {
			Point elementLocation = element.getLocation();

			// Get the size of the element (width and height)
			Dimension elementSize = element.getSize();

			// Calculate the end point (bottom-right corner) of the element
			int endX = elementLocation.getX() + elementSize.getWidth();
			int endY = elementLocation.getY() + elementSize.getHeight();
			System.out.println("x---->" + endX + "<------y----->" + endY);
			new TouchAction(driver).tap(PointOption.point(endX, endY)).perform();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * This method is developed to handle Page loading.
	 * 
	 * @param driver
	 * @param loopCount
	 * @param delayTime
	 * @param element
	 * @return
	 */
	public boolean waitForPageLoading(AppiumDriver<MobileElement> driver, int loopCount, long delayTime,
									  MobileElement element) {
		try {
			for (int loop = 0; loop < loopCount; loop++) {
				if (commonWaitForElementToBeVisible(driver, element, 5)) {
					return true; // Element found
				}
				Thread.sleep(delayTime);
			}
			return false; // Element not found even after retries
		} catch (Exception e) {
			System.out.println("Exception in waitForPageLoading: " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is developed to handle Page loading.
	 * 
	 * @param driver
	 * @param loopcount
	 * @param delayTime
	 * @param element
	 * @return
	 */
	public boolean waitTillElementIsVisible(AppiumDriver<MobileElement> driver, int loopcount, long delayTime,
			MobileElement element) {
		boolean result = true;

		try {
			int loop = 0;
			while (commonWaitForElementToBeVisible(driver, element, 10)) {
				Thread.sleep(delayTime);
				if (loop > loopcount) {
					System.out.println("Not able to load page.");
					break;
				}
				loop++;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		return result;
	}

	public static String getCuurentTimeAddMin(int min) {

		LocalTime currentTime = LocalTime.now();

		// Add 10 minutes
		LocalTime newTime = currentTime.plusMinutes(min);

		// Format the time to "hh:mm"
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		String currentTimeFormatted = currentTime.format(formatter);
		String newTimeFormatted = newTime.format(formatter);

		System.out.println("currentTimeFormatted: " + currentTimeFormatted);
		System.out.println("newTimeFormatted: " + newTimeFormatted);
		return newTimeFormatted;
	}

	public static String getCurrentate(String strPattern, int noOfDays) {

		// Get the current date
		LocalDate currentDate = LocalDate.now();

		// Add two days
		LocalDate newDate = currentDate.plusDays(noOfDays);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(strPattern);
		String currentDateFormatted = currentDate.format(formatter);
		String newDateFormatted = newDate.format(formatter);
		System.out.println("currentDateFormatted: " + currentDateFormatted);
		System.out.println("newDateFormatted: " + newDateFormatted);
		return newDateFormatted;
	}

	public static long getFutureTime(int min) {
		long currentTime = Instant.now().getEpochSecond();
		long tenMinutesLater = (currentTime + (min * 60)) * 1000;
		return tenMinutesLater;
	}

	public static void updateStatusOnLambdaTest(AppiumDriver<MobileElement> driver, boolean result) {
		String strResult;
		if (ConfigFileReader.strRunMode.equalsIgnoreCase("cloud")) {
			if (result) {
				strResult = "passed";
			} else {
				strResult = "failed";
			}
			driver.executeScript("lambda-status=" + strResult);
			((JavascriptExecutor) driver).executeScript("lambda-status=" + strResult);
		}
	}

	public boolean enterText(AppiumDriver<MobileElement> driver, String text) {
		boolean result = true;
		try {
			AndroidDriver<MobileElement> androidDriver = (AndroidDriver<MobileElement>) driver;
			for (char c : text.toCharArray()) {
				androidDriver.pressKey(new KeyEvent(AndroidKey.valueOf(String.valueOf(c).toUpperCase())));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			result = false;
		}
		return result;
	}

	public WebDriver commonStartAndOpenURLBrowserOnWeb(String teacherUrl) {
		WebDriver driver = null;
		String strBrowser = "Chrome";
		try {
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();

			chromeOptions.addArguments("--remote-allow-origins=*");
			chromeOptions.addArguments("--start-maximized");
			chromeOptions.addArguments("--use-fake-ui-for-media-stream");
			chromeOptions.addArguments("--use-fake-device-for-media-stream");
			chromeOptions.addArguments("--no-sandbox");
			chromeOptions.addArguments("--disable-dev-shm-usage");
			chromeOptions.addArguments("disable-infobars");
			// chromeOptions.addArguments("--headless=new");
			chromeOptions.addArguments("--disable-notifications");

			chromeOptions.setExperimentalOption("prefs", new HashMap<String, Object>() {{
				put("profile.default_content_setting_values.notifications", 2); // 2 to block
			}});

			driver = new ChromeDriver(chromeOptions);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

			driver.get(teacherUrl);

			// Add cookies
			addCookie(driver, "lc_userJwtToken", "your_jwt_token_value");
			addCookie(driver, "lc_id", "144");
			addCookie(driver, "lc_user_email", "test@adda247.com");

			// Refresh to apply cookies
			driver.navigate().refresh();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return driver;
	}

	// Reusable method to add cookies
	private void addCookie(WebDriver driver, String name, String value) {
		Cookie cookie = new Cookie.Builder(name, value).build();
		driver.manage().addCookie(cookie);
	}

	/**
	 * This method is developed to upload the file;
	 * 
	 * @param filePath String reference to contain file's path
	 * @param element  WebElement reference contains locator(where you want to
	 *                 upload the file)
	 * @return
	 */

	public boolean commonFileUpload(String filePath, WebElement element) {
		boolean result = true;
		try {
			File file = new File(filePath);
			String absolutePath = file.getAbsolutePath();
			System.out.println("Absolute path:--->" + absolutePath);
			element.sendKeys(absolutePath);
		} catch (Exception e) {
			result = false;
			System.out.println("--> commonFileUpload: " + e.getMessage());
		}
		return result;
	}

	public static String getFutureDateTime(String strPattern, String strAddType, int noOf) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strPattern);
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		String newDate;
		String date = simpleDateFormat.format(new Date());
		System.out.println(date);
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(simpleDateFormat.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (strAddType.equalsIgnoreCase("min")) {
			c.add(Calendar.MINUTE, noOf);
		} else if (strAddType.equalsIgnoreCase("hr")) {
			c.add(Calendar.HOUR, noOf);
		}
		newDate = simpleDateFormat.format(c.getTime());
		System.out.println(newDate);
		return newDate;
	}

	public static String getFutureDateTime1(String strPattern, String strAddType, int noOf) {
		// Create a SimpleDateFormat for the desired pattern
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strPattern);
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

		// Create a Calendar instance for the current date and time
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

		// Add specified time based on strAddType
		if (strAddType.equalsIgnoreCase("min")) {
			c.add(Calendar.MINUTE, noOf);
		} else if (strAddType.equalsIgnoreCase("hr")) {
			c.add(Calendar.HOUR, noOf);
		}

		// Format the new date
		String newDate = simpleDateFormat.format(c.getTime());
		System.out.println("Future Date: " + newDate);

		return newDate;
	}

	public boolean scrollDown(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			MobileElement element = driver.findElement(
					MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"));
			if (element == null) {
				result = false;
			}

		} catch (Exception e) {
			System.out.println("Error: Element not found or scroll not working.");
		}
		return result;
	}

	public boolean scrollUp(AppiumDriver<MobileElement> driver) {
		boolean result = true;
		try {

			MobileElement element = driver.findElement(MobileBy
					.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollBackward()"));
			if (element == null) {
				result = false;
			}

		} catch (Exception e) {
			System.out.println("Error: Element not found or scroll not working.");
		}
		return result;

	}

	public boolean scrollDown(AppiumDriver<MobileElement> driver, int noOfScroll) {
		boolean result = true;
		try {
			driver.findElement(MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(" + noOfScroll + ")"));
		} catch (Exception e) {
			System.out.println("Error: Scroll down not working.");
			result = false;
		}
		return result;
	}

	public boolean scrollUp(AppiumDriver<MobileElement> driver, int noOfScroll) {
		boolean result = true;
		try {
			driver.findElement(MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true)).scrollToBeginning(" + noOfScroll + ")"));
		} catch (Exception e) {
			System.out.println("Error: Scroll up not working.");
			result = false;
		}
		return result;
	}

	public boolean scrollDown(AppiumDriver<MobileElement> driver, String parentScrollViewId, int noOfScroll) {
		boolean result = true;
		try {

			MobileElement element = driver
					.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().text(\""
							+ parentScrollViewId + "\")).scrollToEnd(" + noOfScroll + ")"));
			if (element == null) {
				result = false;
			}

		} catch (Exception e) {
			System.out.println("Error: Element not found or scroll not working.");
		}
		return result;
	}

	public String commonGetTextFromClipBoard(AppiumDriver<MobileElement> driver) {
		String clipBoardText = null;
		try {

			clipBoardText = ((AndroidDriver<MobileElement>) driver).getClipboardText();

			System.out.println("ClipBoard Text: " + clipBoardText);
		} catch (Exception e) {
			clipBoardText = null;
			e.printStackTrace();
		}
		return clipBoardText;
	}

	public boolean scrollUp(AppiumDriver<MobileElement> driver, String parentScrollViewId, int noOfScroll) {
		boolean result = true;
		try {

			MobileElement element = driver
					.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().text(\""
							+ parentScrollViewId + "\")).scrollToBeginning(" + noOfScroll + ")"));
			if (element == null) {
				result = false;
			}

		} catch (Exception e) {
			System.out.println("Error: Element not found or scroll not working.");
		}
		return result;
	}

	public boolean scrollUpUsingTouchAction(AppiumDriver<MobileElement> driver) {
		try {
			Dimension size = driver.manage().window().getSize();
			int startX = size.width / 2;
			int startY = (int) (size.height * 0.3);
			int endY = (int) (size.height * 0.7);

			new TouchAction<>(driver)
					.press(PointOption.point(startX, startY))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
					.moveTo(PointOption.point(startX, endY))
					.release()
					.perform();

		} catch (Exception e) {
			System.out.println("Error during scroll up");
		}
        return true;
    }

	public void scrollUntilElementFound(AppiumDriver<MobileElement> driver, MobileElement elementScrollTo,
			String parentScrollViewId, int scrollCount, String searchType, direction name) {
		boolean elementFound = false;
		int scroll = 0;
		while (elementFound == false) {
			try {
				if (elementScrollTo.isDisplayed() == true || scroll > scrollCount) {
					break;
				}
			} catch (Exception e) {
				scroll++;
				elementFound = false;
			}
			try {
				if (direction.DOWN.equals(name)) {
					if (searchType.equalsIgnoreCase("text")) {
						driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().text(\""
								+ parentScrollViewId + "\")).scrollForward()"));
					} else if (searchType.equalsIgnoreCase("description")) {
						driver.findElement(
								MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().description(\""
										+ parentScrollViewId + "\")).scrollForward()"));

					}
				} else {
					if (searchType.equalsIgnoreCase("text")) {
						driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().text(\""
								+ parentScrollViewId + "\")).scrollBackward()"));
					} else if (searchType.equalsIgnoreCase("description")) {
						driver.findElement(
								MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().description(\""
										+ parentScrollViewId + "\")).scrollBackward()"));

					}
				}
			} catch (Exception e) {
				System.out.println("Error: Element not found or scroll not working.");
			}
		}
	}

	public MobileElement scrollIntoDesc(AppiumDriver<MobileElement> driver, String visibleDesc) {
		try {
			return driver.findElement(MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
							".scrollIntoView(new UiSelector().description(\"" + visibleDesc + "\").instance(0))"));
		} catch (Exception e) {
			System.out.println("Error: Element not found or scroll not working.");
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public void smallScrollUtillNtimes(AppiumDriver<MobileElement> driver, int noOfTime, direction DIRECTION) {
		int count = 0;
		try {
			if (DIRECTION == direction.DOWN) {
				TouchAction action = new TouchAction(driver);
				Dimension size = driver.manage().window().getSize();
				int width = size.width;
				int height = size.height;
				int middleOfX = width / 2;
				int startYCoordinate = (int) (height * .2);
				int endYCoordinate = (int) (height * .1);
				while (count < noOfTime) {
					count = count + 1;
					action.press(PointOption.point(middleOfX, startYCoordinate))
							.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
							.moveTo(PointOption.point(middleOfX, endYCoordinate)).release().perform();
				}
			} else if (DIRECTION == direction.UP) {
				TouchAction action = new TouchAction(driver);
				Dimension size = driver.manage().window().getSize();
				int width = size.width;
				int height = size.height;
				int middleOfX = width / 2;
				int startYCoordinate = (int) (height * .2);
				int endYCoordinate = (int) (height * .3);
				while (count < noOfTime) {
					count = count + 1;
					action.press(PointOption.point(middleOfX, startYCoordinate))
							.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
							.moveTo(PointOption.point(middleOfX, endYCoordinate)).release().perform();
				}
			}
		} catch (Exception e) {
			System.out.println("Error: Element not found or scroll not working.");
		}
	}

	public static long addMonthToCurrentDate(int noOfMonth) {
		LocalDate currentDate = LocalDate.now();
		LocalDate futureDate = currentDate.plusMonths(noOfMonth);

		return futureDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
	}

	public static String getTimestamp() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

	public boolean commonFileUploadWithEdgeCases(String filePath, WebElement element) {
		boolean result = true;
		try {
			// Check if the filePath or element is null
			if (filePath == null || element == null) {
				throw new IllegalArgumentException("File path or WebElement is null");
			}
			// Create a File object and get the absolute path
			File file = new File(filePath);
			if (!file.exists()) {
				throw new IllegalArgumentException("File does not exist: " + filePath);
			}
			String absolutePath = file.getAbsolutePath();
			System.out.println("Absolute path: " + absolutePath);

			// Upload the file using sendKeys
			element.sendKeys(absolutePath);
		} catch (Exception e) {
			result = false;
			// Use proper logging framework instead of System.out.println
			System.err.println("commonFileUpload: " + e.getMessage());
		}
		return result;
	}

	public String randomString() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();
		return generatedString;
	}

	public void swipe(AppiumDriver<MobileElement> driver, MobileElement element, String direction,
			int durationInMillis) {
		// Get element's location and size
		int elementWidth = element.getSize().width;
		int elementHeight = element.getSize().height;
		int startX, endX, y;

		// Calculate start and end points based on direction
		if (direction.equalsIgnoreCase("Left")) {
			startX = element.getLocation().getX(); // Start from the left of the element
			endX = startX + elementWidth; // Swipe to the right end of the element
		} else { // RIGHT_TO_LEFT
			startX = element.getLocation().getX() + elementWidth; // Start from the right of the element
			endX = element.getLocation().getX(); // Swipe to the left end of the element
		}

		// Vertical position for swipe, centered within the element's height
		y = element.getLocation().getY() + (elementHeight / 2);

		// Perform the swipe within the element
		new TouchAction<>(driver).press(PointOption.point(startX, y))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(durationInMillis)))
				.moveTo(PointOption.point(endX, y)).release().perform();
	}

	public void commonClick_JS(WebDriver driver, WebElement ele) {
		boolean result = true;
		try {
			WebDriverWait wait = (new WebDriverWait(driver, 20));

			wait.until(ExpectedConditions.elementToBeClickable(ele));
			// alternate solution
			JavascriptExecutor j = (JavascriptExecutor) driver;
			j.executeScript("arguments[0].click();", ele);
		} catch (Exception e) {
			result = false;
		}
	}

	public void pullToRefresh(AppiumDriver<MobileElement> driver) {
		try {
			Dimension size = driver.manage().window().getSize();

			int startX = size.width / 2; // middle of screen width
			int startY = (int) (size.height * 0.25); // 25% from top
			int endY = (int) (size.height * 0.85);   // 85% from top

			new TouchAction<>(driver)
					.press(PointOption.point(startX, startY))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(800)))
					.moveTo(PointOption.point(startX, endY))
					.release()
					.perform();

			System.out.println("Pull-to-refresh action performed.");
		} catch (Exception e) {
			System.out.println("Error while performing pull-to-refresh: " + e.getMessage());
		}
	}

	public void pullToRefreshFromTop(AppiumDriver<MobileElement> driver) {
		try {
			Dimension size = driver.manage().window().getSize();

			int startX = size.width / 2;
			int startY = (int) (size.height * 0.05); // very top
			int endY = (int) (size.height * 0.60);   // further down

			new TouchAction<>(driver)
					.press(PointOption.point(startX, startY))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1200)))
					.moveTo(PointOption.point(startX, endY))
					.release()
					.perform();

			System.out.println("Pull-to-refresh from top performed.");
		} catch (Exception e) {
			System.out.println("Error in pullToRefreshFromTop: " + e.getMessage());
		}
	}

	public void pullToRefreshW3C(AppiumDriver<MobileElement> driver) {
		try {
			Dimension size = driver.manage().window().getSize();

			int startX = size.width / 2;
			int startY = (int) (size.height * 0.15);
			int endY = (int) (size.height * 0.65);

			PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
			Sequence swipe = new Sequence(finger, 1);

			swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
			swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
			swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), startX, endY));
			swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

			driver.perform(Collections.singletonList(swipe));

			System.out.println("Pull-to-refresh W3C performed.");
		} catch (Exception e) {
			System.out.println("Error in pullToRefreshW3C: " + e.getMessage());
		}
	}
}