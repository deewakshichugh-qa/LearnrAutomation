package apiUtill;

import java.util.ArrayList;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import util.APIResponse;
import util.APIUtils;

public class StreakApiUtill {
	
	APIUtils apiUtilsObj;
	APIResponse ap;
	ArrayList<String> streakMsgList = new ArrayList<>();

	
	public boolean addStreak(String id, String strToken) {
		boolean result = true;
		ArrayList<Header> headerList = new ArrayList<>();
		try {
			
			headerList.add(new Header("x-auth-token", "fpoa43edty5"));
			headerList.add(new Header("x-jwt-token", strToken));
			Headers header = new Headers(headerList);
			
			apiUtilsObj = new APIUtils();
			ap = apiUtilsObj.getCall("https://stagingstore.adda247.com/certification-ws/", "api/v1/streak/delete?id=" + id, header);
			
			if (ap.code != 200) {
				streakMsgList.add("api/v1/app/ivs/channel/create response is not 200");
				return false;
			}
			
		} catch (Exception e) {
			streakMsgList.add("addStreak_Exception: " + e.getMessage());
		}
		return result;
	}
}
