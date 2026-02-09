package apiUtill;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import util.APIResponse;
import util.APIUtils;
import util.ConfigFileReader;

public class UpdateValidityApiUtil {

	APIUtils apiUtilObj = new APIUtils();
	JSONObject requestParams;
	ConfigFileReader configObj = new ConfigFileReader();
	public List<String> updateValidiMsgList = new ArrayList<String>();
	APIResponse ap;

	@SuppressWarnings("unchecked")
	public boolean updateValidity(String strToken, int validityType, int productId, int extension,
			String extensionUnit) {
		JSONObject requestParams;
		List<Header> headerlist = new ArrayList<Header>();
		boolean result = true;
		try {
			Headers headers = new Headers(headerlist);
			headerlist.add(new Header("x-jwt-token", strToken));
			headerlist.add(new Header("x-auth-token", "fpoa43edty5"));

			requestParams = new JSONObject();
			requestParams.put("productId", productId);
			requestParams.put("validity", validityType);
			requestParams.put("extension", extension);
			requestParams.put("extensionUnit", extensionUnit);
			ap = apiUtilObj.postCall(configObj.getStoreAdminUrl(), "api/v1/packages/updateValidity", requestParams,
					headers);
			if (ap.code != 200) {

			}

		} catch (Exception e) {
			updateValidiMsgList.add("updateValidity_Exception: " + e.getMessage());
			result = false;

		}
		return result;
	}
}
