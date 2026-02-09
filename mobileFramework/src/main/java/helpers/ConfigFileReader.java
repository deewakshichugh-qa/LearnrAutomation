package helpers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
	public static String strEnv;
	public static String  strDeviceName, strDeviceVersion, strRunMode,
			strDeviceIndex, strFilePath;
	static {
		strEnv = System.getProperty("env");
		strEnv = "PRODUCTION";
		strDeviceName = System.getProperty("deviceName");
		System.out.println("strDeviceName " + strDeviceName);
		strDeviceVersion = System.getProperty("version");
		strRunMode = System.getProperty("runMode");
		strRunMode = "Cloud";
		strDeviceName = "Android Device";
		strDeviceVersion = "11";
		strFilePath = System.getProperty("appID");
		//strFilePath = "bs://af78bcd6b02986208d034c691a4e02f72f686392";
	}

	private Properties properties;

	public ConfigFileReader() {
		BufferedReader reader;
		String strPropertyPath = null;
		try {
			if (strEnv.equalsIgnoreCase("staging")) {
				strPropertyPath = "src/main/resources/config/staging.properties";

			} else if (strEnv.equalsIgnoreCase("dev")) {

				strPropertyPath = "src/main/resources/config/dev.properties";

			} else if (strEnv.equalsIgnoreCase("PRODUCTION")) {

				strPropertyPath = "src/main/resources/config/prod.properties";

			}
			reader = new BufferedReader(new FileReader(strPropertyPath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + strPropertyPath);
		}
	}

	public String getEnv() {
		String strEnv = properties.getProperty("env");
		if (strEnv != null)
			return strEnv;
		else
			throw new RuntimeException(strEnv + "not specified in the Configuration properties file.");
	}
}
