package applicationUtil;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import pojo.userRegistration.UserRegistration;
import util.APIResponse;
import util.APIUtils;
import util.Common_Function;
import util.ConfigFileReader;

public class RegisterNewUserUtil {

	APIUtils apiUtilObj = new APIUtils();

	JSONObject requestParams;

	@SuppressWarnings("unchecked")
	public UserRegistration reigsterNewUser() {

		ConfigFileReader configReaderObject = new ConfigFileReader();
		UserRegistration userRegistration = null;
		APIResponse ap;

		List<Header> headerlist = new ArrayList<Header>();

		String strEmailId;
		try {
			headerlist.add(new Header("x-auth-token", "fpoa43edty5"));
			Headers headers = new Headers(headerlist);
			strEmailId = "addaAutomation" + Common_Function.randomPhoneNumber(10, "2") + "@gmail.com";
			requestParams = new JSONObject();
			requestParams.put("email", strEmailId);
			requestParams.put("name", "test");
			requestParams.put("providerName", "email");
			requestParams.put("sec", "002@aaada!");
//			if(ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
				ap = apiUtilObj.postCall(configReaderObject.getBaseUrlUserRegistration(), "register?src=aweb",requestParams, headers);
//			}else {
//				ap = apiUtilObj.postCall(configReaderObject.getBaseUrlUserRegistration(), "register",requestParams, headers);
//			}

			if (ap.code != 200) {
				return null;
			}

			userRegistration = ap.responseData.as(UserRegistration.class);

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

		return userRegistration;

	}

}
