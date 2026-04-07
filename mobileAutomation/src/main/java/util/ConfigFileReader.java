package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    public static String strEnv;
    public static String strDeviceName, userName, accessKey, strDeviceVersion, strRunMode, strFilePath,
            strUserMobileNumber, strApplication, retryCount, isTablet, strApplicationType, strUserEmailId, strUserPassword,
            prodStrUserEmailId, prodEmailMasterPassword, prodOtpMasterPassword, strApkLink;

    static {
        strEnv = System.getProperty("env");
        userName = System.getProperty("userName");
        accessKey = System.getProperty("accessKey");
        strRunMode = System.getProperty("runMode");
        strDeviceVersion = System.getProperty("version");
        strFilePath = System.getProperty("apkId");
        strUserMobileNumber = System.getProperty("mobileNumber");
        strApplication = System.getProperty("application");
        strApplicationType = System.getProperty("applicationType");
        strDeviceName = System.getProperty("deviceId");
        retryCount = System.getProperty("retryCount");
        isTablet = System.getProperty("tablet");
        strUserEmailId = System.getProperty("userEmailId");
        strUserPassword = System.getProperty("userPassword");
        prodStrUserEmailId = System.getProperty("prodStrUserEmailId");
        prodEmailMasterPassword = System.getProperty("prodEmailMasterPassword");
        prodOtpMasterPassword = System.getProperty("prodOtpMasterPassword");
        strApkLink = System.getProperty("strApkLink");

//        // Cloud Configuration
//        userName = "abhay.rai";
//        accessKey = "f562ULSQYygxPzwtlVtAvfsna7mnAoWTlVlS4BIC3FYUs6Tf1W";
//        strFilePath = "lt://APP1016060381743409083341880"; // Configure Adda/Ios build file path
//
//        retryCount = "0";
//        strApplicationType = "Android";
//        strApplication = "Learnr";
//        isTablet = "false";
//        strEnv = "staging";                                // Configure staging, dev, qa, sigmaqa, production, beta
//        strRunMode = "local";                                // Configure cloud, local, localLab
//
//        strUserEmailId = "hamza.arif@adda247.com";            // Configure as per your emailId on test env
//        strUserPassword = "123456789";                        // Configure as per your password on test env
//
//        strUserMobileNumber = "9878252339";                    // Configure as per your phone number on test & prod env
//        prodStrUserEmailId = "shubham.bansal@adda247.com";    // Configure as per your emailId on prod env
//
//        prodEmailMasterPassword = "cYfmhOmZB5qfIH44R1";        // Don't change master email
//        prodOtpMasterPassword = "719492";                    // Don't change master otp
//
//        // Apk link
//		// strApkLink = "http://teacherapp.adda247.com/temp/Adda247-profile-universal.apk";
//        strApkLink = "";

        // Check if the above configurations are not commented
        validateConfig();

        if (strDeviceName == null || strDeviceName.isEmpty()) {

            if (strApplicationType.equalsIgnoreCase("Android")) {

                if (isTablet.equalsIgnoreCase("true")) {

                    strDeviceName = "Pixel Tablet";
                    strDeviceVersion = "13";
                } else {
                    strDeviceName = "Pixel 6";
                    strDeviceVersion = "12";
                }

            } else {
                strDeviceName = "iPhone 12 Mini";
                strDeviceVersion = "14";
            }
        }
    }

    private Properties properties;

    public ConfigFileReader() {
        BufferedReader reader;
        String strPropertyPath = null;
        try {

            if (strApplication.equalsIgnoreCase("Learnr")) {
                if (strEnv.equalsIgnoreCase("staging")) {

                    strPropertyPath = "src/main/resources/config/staging.properties";

                } else if (strEnv.equalsIgnoreCase("dev")) {

                    strPropertyPath = "src/main/resources/config/dev.properties";

                } else if (strEnv.equalsIgnoreCase("production")) {

                    strPropertyPath = "src/main/resources/config/prod.properties";

                } else if (strEnv.equalsIgnoreCase("beta")) {

                    strPropertyPath = "src/main/resources/config/beta.properties";

                } else if (strEnv.equalsIgnoreCase("qa")) {

                    strPropertyPath = "src/main/resources/config/qa.properties";

                } else if (strEnv.equalsIgnoreCase("sigmaqa")) {

                    strPropertyPath = "src/main/resources/config/sigmaqa.properties";
                }

            }

            assert strPropertyPath != null;
            reader = new BufferedReader(new FileReader(strPropertyPath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Configuration.properties not found at " + strPropertyPath);
        }
    }

    private static void validateConfig() {
        if (strEnv == null || strRunMode == null || strApplicationType == null || strFilePath == null) {
            System.err.println("----------");
            System.err.println("ERROR: Missing critical configuration. Please uncomment in ConfigFileReader.");
            System.err.println("----------");
            System.exit(1); // Terminate script execution
        }
    }

    public String getEnv() {
        String strEnv = properties.getProperty("env");
        if (strEnv != null)
            return strEnv;
        else
            throw new RuntimeException(strEnv + "not specified in the Configuration properties file.");
    }

    public String getBaseUrlUserRegistration() {
        String strBaseUrl = properties.getProperty("BaseUrlUserRegistration");
        if (strBaseUrl != null)
            return strBaseUrl;
        else
            throw new RuntimeException("Base url is not defined.");
    }

    public String getOtpToken() {
        String strOtpToken = properties.getProperty("otpToken");
        if (strOtpToken != null)
            return strOtpToken;
        else
            throw new RuntimeException("Otp token is not defined.");
    }

    public String getSocialBaseUrl() {
        String strSocialBaseUrl = properties.getProperty("SocialBaseUrl");
        if (strSocialBaseUrl != null)
            return strSocialBaseUrl;
        else
            throw new RuntimeException("Social Base url is not defined.");
    }

    public String getCategorySubCategory() {
        String strCategorySubCategory = properties.getProperty("CategorySubCategory");
        if (strCategorySubCategory != null)
            return strCategorySubCategory;
        else
            throw new RuntimeException("strCategorySubCategory is not defined.");
    }

    public String getAdminToken() {
        String strAdmintoken = properties.getProperty("AdmnToken");
        if (strAdmintoken != null)
            return strAdmintoken;
        else
            throw new RuntimeException("admintoken is not defined.");
    }

    public String getAppPackageActivity() {
        String strPackageActivity = null;
        if (strApplication.equalsIgnoreCase("Learnr")) {
            strPackageActivity = "com.adda247.gold/com.adda247.gold.MainActivity";
        }
        return strPackageActivity;
    }

    public String getCategoryExamLang() {
        String strCategoryExamLang = properties.getProperty("CategoryExamLang");
        if (strCategoryExamLang != null)
            return strCategoryExamLang;
        else
            throw new RuntimeException("CategoryExamLang is not defined.");
    }

    public String getUserNamePassword() {
        String strUserNamePassword = properties.getProperty("UserName");
        if (strUserNamePassword != null)
            return strUserNamePassword;
        else
            throw new RuntimeException("UserName is not defined.");
    }

    public String getUserToken() {
        String strUserJwtToken = properties.getProperty("UserToken");
        if (strUserJwtToken != null)
            return strUserJwtToken;
        else
            throw new RuntimeException("UserToken is not defined.");
    }

    public String getAdminMiniUIToken() {
        String strPackageName = properties.getProperty("MiniAdminUIToken");
        if (strPackageName != null)
            return strPackageName;
        else
            throw new RuntimeException("MiniAdminUIToken is not defined.");
    }

    public String getAdminMiniUIUrl() {
        String strPackageName = properties.getProperty("MiniAdminUIEndPoint");
        if (strPackageName != null)
            return strPackageName;
        else
            throw new RuntimeException("MiniAdminUIEndPoint is not defined.");
    }

    public String getAdminMiniUIUrlStore() {
        String strPackageName = properties.getProperty("MiniAdminUIEndPointStoreAdmin");
        if (strPackageName != null)
            return strPackageName;
        else
            throw new RuntimeException("MiniAdminUIEndPointStoreAdmin is not defined.");
    }

    public String getPackageId() {
        String strPackageId = properties.getProperty("packageId");
        if (strPackageId != null)
            return strPackageId;
        else
            throw new RuntimeException("packageId is not defined.");
    }

    public String getStoreBaseUrl() {
        String strStoreBaseUrl = properties.getProperty("storeBaseUrl");
        if (strStoreBaseUrl != null)
            return strStoreBaseUrl;
        else
            throw new RuntimeException("storeBaseUrl is not defined.");
    }

    public String getUserServiceBaseUrl() {
        String strUserServiceBaseUrl = properties.getProperty("userServiceBaseUrl");
        if (strUserServiceBaseUrl != null)
            return strUserServiceBaseUrl;
        else
            throw new RuntimeException("userServiceBaseUrl is not defined.");
    }

    public String getStoreAdminUrl() {
        String strStoreAdminUrl = properties.getProperty("StoreAdminUrl");
        if (strStoreAdminUrl != null)
            return strStoreAdminUrl;
        else
            throw new RuntimeException("store admin url is not defined.");
    }

    public String getBaseUrlUserLoginAdmin() {
        String strBaseUrl = properties.getProperty("BaseUrlUserLoginAdmin");
        if (strBaseUrl != null)
            return strBaseUrl;
        else
            throw new RuntimeException("Base url admin is not defined.");
    }

    public String getCouponAdminUrl() {
        String strBaseUrl = properties.getProperty("CouponAdmin");
        if (strBaseUrl != null)
            return strBaseUrl;
        else
            throw new RuntimeException("Base url coupon admin is not defined.");
    }

    public String getStoreUrl() {
        String strStoreUrl = properties.getProperty("StoreUrl");
        if (strStoreUrl != null)
            return strStoreUrl;
        else
            throw new RuntimeException("Store url is not defined.");
    }

    public String getliveClassUrl() {
        String strLiveClassUrl = properties.getProperty("liveClassUrl");
        if (strLiveClassUrl != null)
            return strLiveClassUrl;
        else
            throw new RuntimeException("strLiveClassUrl is not defined.");
    }

    public String getAdmitCardUrl() {
        String strAdmitCardUrl = properties.getProperty("AdmitCardUrl");
        if (strAdmitCardUrl != null)
            return strAdmitCardUrl;
        else
            throw new RuntimeException("strAdmitCardUrl is not defined.");
    }

    public String getBaseUrl() {
        String strBaseUrl = properties.getProperty("BaseUrl");
        if (strBaseUrl != null)
            return strBaseUrl;
        else
            throw new RuntimeException("Base is not defined.");
    }

}