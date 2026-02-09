package apiUtill;

import java.util.ArrayList;

import io.restassured.http.Header;
import io.restassured.http.Headers;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.ui.ModelMap;

import pojo.userLoginPayload.UserLoginBody;
import pojo.userLoginResponse.UserLoginResponse;
import util.APIResponse;
import util.APIUtils;
import util.ConfigFileReader;

public class UserLoginUtil {
	APIUtils apiUtilObj = new APIUtils();
	ConfigFileReader configReaderObject = new ConfigFileReader();
	JSONObject requestParams;

	@SuppressWarnings("unchecked")
	public UserLoginResponse userLogin(String usrEmail, String usrPassword) {
		UserLoginResponse loginResponseObj = new UserLoginResponse();
//		UserLoginBody loginBody = new UserLoginBody();
		APIResponse ap;
		List<Header> headerList = new ArrayList<Header>();
		try {
			headerList.add(new Header("X-Auth-Token", "fpoa43edty5"));
			Headers header = new Headers(headerList);
			
			requestParams = new JSONObject();
			requestParams.put("phone", "");
			requestParams.put("email", usrEmail);
			requestParams.put("providerName", "email");
			requestParams.put("sec", usrPassword);
			
//			loginBody.setPhone("");
//			loginBody.setEmail(usrEmail);
//			loginBody.setProviderName("email");
//			loginBody.setSec("0002@aada!");
			
//			System.out.println(loginBody.toString());
			
			ap = apiUtilObj.postCall(configReaderObject.getUserServiceBaseUrl(), "/login?src=and", requestParams, header);
			if (ap.code != 200) {
				return null;
			}
			
			loginResponseObj = ap.responseData.as(UserLoginResponse.class);
			
		} catch (Exception e) {
			System.out.println("userLogin_Exception: " + e.getMessage());
		}
		return loginResponseObj;
	}
	
	public String getUserToken(String usrEmail, String usrpassword) {
		UserLoginResponse loginResponseObj = new UserLoginResponse();
		String userToken=null;
		try {
			
			loginResponseObj = userLogin(usrEmail, usrpassword);
			if(loginResponseObj==null) {
				System.out.println("The loginResponseObj is null");
				return null;
			}
			
			userToken = loginResponseObj.getJwtToken();
			System.out.println("--->" + userToken);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return userToken;
	}
}
