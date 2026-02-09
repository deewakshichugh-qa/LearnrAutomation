package apiUtill;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import pojo.mapExternalIdResponse.MapExternalIdResponse;
import util.APIResponse;
import util.APIUtils;
import util.ConfigFileReader;

public class MapExternalScheduleIdUtil {

	APIUtils apiUtils = new APIUtils();
	ConfigFileReader configReaderObject = new ConfigFileReader();
	JSONObject requestParams;
	APIResponse ap;
	List<String> mapExternalIdMsgList = new ArrayList<String>();

	public MapExternalIdResponse mapExternalScheduleId(String scheduleId) {
		List<Header> headerlist = new ArrayList<Header>();
		MapExternalIdResponse externalIdResponse = null;

		try {
			headerlist.add(new Header("X-Auth-Token", "fpoa43edty5"));

			Headers headers = new Headers(headerlist);
			requestParams = new JSONObject();

			APIUtils apiUtilObj = new APIUtils();
			ap = apiUtilObj.postCall(configReaderObject.getliveClassUrl(),
					"api/v1/olc-schedule/map/externalScheduleId?scheduleIdList=" + Integer.parseInt(scheduleId),
					requestParams, headers);
			if (ap.code != 200) {
				mapExternalIdMsgList.add("Response code is not 200: " + ap.code + "for " + ap.getFullResponse());
				return null;
			}

			externalIdResponse = ap.responseData.as(MapExternalIdResponse.class);

		} catch (Exception e) {
			mapExternalIdMsgList.add("mapExternalScheduleId_Exception: " + e.getMessage());
		}
		return externalIdResponse;
	}

}
