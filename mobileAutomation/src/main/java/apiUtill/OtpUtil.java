package apiUtill;

import util.APIResponse;
import util.APIUtils;
import util.ConfigFileReader;
import util.Constant;

public class OtpUtil {

	public ConfigFileReader cfReaderObject = new ConfigFileReader();
	public APIUtils apiUtilsObj = new APIUtils();

	public String getOtp(String strMobileNumberUserId) {
		String strOtp = null;
		APIResponse ap = null;
		String strBasicAuth;
		try {

			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
				strBasicAuth = "Basic YWRtaW46YWRkYWFkbWluQDEyMzQ1";
			} else {
				strBasicAuth = "Basic YWRtaW46c2Fua2FscEAxMjM0NQ==";
			}
			ap = apiUtilsObj.getCallwithBasicAuth(cfReaderObject.getBaseUrlUserRegistration(),
					Constant.USER_OTP + strMobileNumberUserId, "admin", "addaadmin@12345", strBasicAuth);
			System.out.println(ap.getmessageCode());

			strOtp = ap.getmessageCode();

			if (strOtp.equalsIgnoreCase("No OTP found")) {

				if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
					strBasicAuth = "Basic YWRtaW46YWRkYWFkbWluQDEyMzQ1";
				} else {
					strBasicAuth = "Basic YWRtaW46c2Fua2FscEAxMjM0NQ==";
				}
				ap = apiUtilsObj.getCallwithBasicAuth(cfReaderObject.getBaseUrlUserRegistration(),
						Constant.USER_OTP + strMobileNumberUserId, "admin", "addaadmin@12345", strBasicAuth);
				System.out.println(ap.getmessageCode());

				strOtp = ap.getmessageCode();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return strOtp;

	}

	public String getOtpAdminEmailPhone(String strMobileNumberUserId, String strType) {
		String strOtp = null, strBasicAuth;
		APIResponse ap = null;
		String strUserName, strUserPassword;
		try {
			Thread.sleep(1000);
			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
				strUserName = "admin";
				strUserPassword = "addaadmin@12345";
				strBasicAuth = "Basic YWRtaW46YWRkYWFkbWluQDEyMzQ1";
			} else {
				strUserName = "admin";
				strUserPassword = "sankalp@12345";
				strBasicAuth = "Basic YWRtaW46c2Fua2FscEAxMjM0NQ==";
			}

			ap = apiUtilsObj.getCallwithBasicAuth(cfReaderObject.getBaseUrlUserRegistration(),
					Constant.USER_OTP_EMAIL_PHONE + strMobileNumberUserId + "&type=" + strType, strUserName,
					strUserPassword, strBasicAuth);

			System.out.println(ap.getmessageCode());

			strOtp = ap.getmessageCode();

			if (strOtp.contains("No")) {
				if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
					strUserName = "admin";
					strUserPassword = "addaadmin@12345";
					strBasicAuth = "Basic YWRtaW46YWRkYWFkbWluQDEyMzQ1";
				} else {
					strUserName = "admin";
					strUserPassword = "sankalp@12345";
					strBasicAuth = "Basic YWRtaW46c2Fua2FscEAxMjM0NQ==";
				}

				ap = apiUtilsObj.getCallwithBasicAuth(cfReaderObject.getBaseUrlUserRegistration(),
						Constant.USER_OTP_EMAIL_PHONE + strMobileNumberUserId + "&type=" + strType, strUserName,
						strUserPassword, strBasicAuth);

				System.out.println(ap.getmessageCode());

				strOtp = ap.getmessageCode();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return strOtp;

	}

	public String getOtpAdminForgetPwd(String strEmailId, String strType) {
		String strOtp = null, strBasicAuth;
		APIResponse ap = null;
		String strUserName, strUserPassword;
		try {
			Thread.sleep(4000);
			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
				strUserName = "admin";
				strUserPassword = "addaadmin@12345";
				strBasicAuth = "Basic YWRtaW46YWRkYWFkbWluQDEyMzQ1";
			} else {
				strUserName = "admin";
				strUserPassword = "sankalp@12345";
				strBasicAuth = "Basic YWRtaW46c2Fua2FscEAxMjM0NQ==";
			}
			ap = apiUtilsObj.getCallwithBasicAuth(cfReaderObject.getBaseUrlUserRegistration(),
					Constant.USER_OTP_FORGOT_PWD + strEmailId + "&type=" + strType, strUserName, strUserPassword,
					strBasicAuth);

			System.out.println(ap.getmessageCode());

			strOtp = ap.getmessageCode();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return strOtp;

	}

}
