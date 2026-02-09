package apiUtill;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import pojo.adminLogin.AdminLogin;
import pojo.login.Login;
import pojo.userRegistration.UserRegistration;
import util.APIResponse;
import util.APIUtils;
import util.Common_Function;
import util.ConfigFileReader;

public class UserApiUtil {

	APIUtils apiUtilObj = new APIUtils();
	public ConfigFileReader configReaderObject;
	Common_Function cfObj = new Common_Function();

	JSONObject requestParams;

	@SuppressWarnings("unchecked")
	public UserRegistration reigsterNewUser() {

		configReaderObject = new ConfigFileReader();
		UserRegistration userRegistration = null;
		APIResponse ap;

		List<Header> headerlist = new ArrayList<Header>();
		String strNumber;

		String strEmailId;
		try {
			strNumber = Common_Function.randomPhoneNumber(10, "8");
			headerlist.add(new Header("x-auth-token", "fpoa43edty5"));
			Headers headers = new Headers(headerlist);
			strEmailId = "addaAutomation" + Common_Function.randomPhoneNumber(10, "3") + "@gmail.com";
			requestParams = new JSONObject();
			requestParams.put("email", strEmailId);
			requestParams.put("name", "test" + strNumber);
			requestParams.put("providerName", "email");
			requestParams.put("sec", "0002@aaada!");

			ap = apiUtilObj.postCall(configReaderObject.getBaseUrlUserRegistration(), "register?src=aweb",
					requestParams, headers);

			if (ap.code != 200) {
				return null;
			}

			userRegistration = ap.responseData.as(UserRegistration.class);

		} catch (Exception e) {

		}

		return userRegistration;

	}

	@SuppressWarnings("unchecked")
	public UserRegistration userLogin(String strUserEmail, String strPassword) {

		configReaderObject = new ConfigFileReader();
		UserRegistration userRegistration = null;
		APIResponse ap;

		List<Header> headerlist = new ArrayList<Header>();
		try {
			headerlist.add(new Header("x-auth-token", "fpoa43edty5"));
			Headers headers = new Headers(headerlist);
			requestParams = new JSONObject();
			requestParams.put("email", strUserEmail);
			requestParams.put("providerName", "email");
			requestParams.put("sec", strPassword);

			ap = apiUtilObj.postCall(configReaderObject.getBaseUrlUserRegistration(), "login?src=aweb", requestParams,
					headers);

			if (ap.code != 200) {
				return null;
			}

			userRegistration = ap.responseData.as(UserRegistration.class);

		} catch (Exception e) {

		}

		return userRegistration;

	}

	@SuppressWarnings("unchecked")
	public AdminLogin adminLogin(String strUserEmail, String strPassword) {
		Headers headers = null;
		APIResponse ap;
		AdminLogin adminLogin = null;
		configReaderObject = new ConfigFileReader();
		try {
			requestParams = new JSONObject();
			requestParams.put("email", strUserEmail);
			requestParams.put("password", strPassword);
			ap = apiUtilObj.postCall(configReaderObject.getBaseUrlUserLoginAdmin(), "api/users/login", requestParams,
					headers);

			if (ap.code != 200) {
				return null;
			}

			adminLogin = ap.responseData.as(AdminLogin.class);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return adminLogin;
	}

	@SuppressWarnings("unchecked")
	public Login userLoginWithMobileNumber(String strMobileNumber) {

		configReaderObject = new ConfigFileReader();
		Login loginObj = null;
		APIResponse ap;
		OtpUtil op;

		List<Header> headerlist = new ArrayList<Header>();
		try {
			headerlist.add(new Header("x-auth-token", "fpoa43edty5"));
			Headers headers = new Headers(headerlist);
			requestParams = new JSONObject();
			requestParams.put("phone", strMobileNumber);
			ap = apiUtilObj.postCall(configReaderObject.getBaseUrlUserRegistration(), "new-phone-verify", requestParams,
					headers);

			if (ap.code != 200) {
				return null;
			}
			op = new OtpUtil();
			String strOtp = op.getOtpAdminEmailPhone(strMobileNumber, "phone");

			// verify otp
			requestParams = new JSONObject();
			requestParams.put("phone", strMobileNumber);
			requestParams.put("otp", strOtp);
			ap = apiUtilObj.postCall(configReaderObject.getBaseUrlUserRegistration(), "new-otp-verify", requestParams,
					headers);

			if (ap.code != 200) {
				return null;
			}
			loginObj = ap.responseData.as(Login.class);

		} catch (Exception e) {

		}
		return loginObj;
	}

}
