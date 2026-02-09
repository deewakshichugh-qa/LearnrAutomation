package test_scripts;

import applicationUtil.LoginUtil;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.Common_Function;

import java.io.*;

public class MonkeyStressTest extends BaseTest {

    @Test
    public void verifyMonkeyTest() {

        LoginUtil loginObj = new LoginUtil(getDriver());
        boolean result = loginObj.verifyLoginUsingEmailIdFromConfig(getDriver());
        if (!result){
            Assert.assertTrue(result, loginObj.loginMsgList.toString());
        }
        System.out.println("Login performed.");

        String packageName = "com.adda247.app";
        int monkeyEventCount = 20000;
        int throttle = 150;

        // Create logs directory
        String logsDir = System.getProperty("user.dir") + "/monkey_logs";
        File dir = new File(logsDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String logFilePath = logsDir + "/monkey_logs_" + System.currentTimeMillis() + ".txt";

        try {
//            String adbPath = "/opt/homebrew/bin/adb";                         //Shubham Laptop
            String[] command = getStrings(packageName, throttle, monkeyEventCount);

            System.out.println("Running Monkey Test...");
            ProcessBuilder builder = new ProcessBuilder(command);
            builder.redirectErrorStream(true);
            Process process = builder.start();

            // Capture output and save to log file
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                writer.write(line);
                writer.newLine();
            }
            writer.close();

            int exitCode = process.waitFor();
            System.out.println("Monkey process finished with exit code: " + exitCode);

            // Step 2: Parse the Monkey output for errors
            parseMonkeyLog(logFilePath, packageName);

            result = cfObj.changeNetworkState(getDriver(), Common_Function.NetworkState.ON);
            if (!result) {
                System.out.println("Not able to turn ON the internet");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String[] getStrings(String packageName, int throttle, int monkeyEventCount) {
        String adbPath = "/home/adda247/Android/Sdk/platform-tools/adb";    //Parallel Desktop

        String[] command = {
                adbPath,
                "monkey",
                "-p", packageName,
                "--throttle", String.valueOf(throttle),
                "-v", "-v", "-v",
                "--pct-touch", "60",            // Emphasize touches
                "--pct-motion", "20",           // Emphasize motions (scrolls/swipes)
                "--pct-nav", "10",              // Optional: for nav within app
                "--pct-majornav", "10",         // Optional: back/home/menu (set to 0 if not desired)
                "--pct-appswitch", "0",         // Prevent app switching
                "--pct-anyevent", "0",          // No random events
                "--ignore-crashes",
                "--monitor-native-crashes",
                "--ignore-security-exceptions",
                "--ignore-timeouts",
                String.valueOf(monkeyEventCount)
        };
        return command;
    }

    private void parseMonkeyLog(String filePath, String packageName) {
        boolean crashDetected = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("CRASH") || line.contains("ANR") || line.contains("Exception")) {
                    System.err.println("[MONKEY ERROR]: " + line);
                    crashDetected = true;
                }
            }

            if (crashDetected) {
                System.out.println("Crash or ANR detected. Restarting app...");
                AndroidDriver<?> androidDriver = (AndroidDriver<?>) getDriver();
                androidDriver.terminateApp(packageName);
                Thread.sleep(2000);
                androidDriver.activateApp(packageName);
                System.out.println("App restarted.");
            } else {
                System.out.println("Monkey test completed with no crash or ANR.");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
