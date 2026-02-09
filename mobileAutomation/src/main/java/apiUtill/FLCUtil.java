package apiUtill;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import pojo.createFLCPayload.CreateFLCRequest;
import util.APIResponse;
import util.APIUtils;
import util.ConfigFileReader;

public class FLCUtil {
	ObjectMapper Obj;
	
	APIUtils apiUtilObj = new APIUtils();
	ConfigFileReader configReaderObject = new ConfigFileReader();
	
	public boolean createFLC(String userToken, int scheduleId, List<String> listCategory, List<String> listPackages) {
		boolean result = true;
		CreateFLCRequest createFLCRequestObj = new CreateFLCRequest();
		APIResponse ap;
		List<Header> headerList = new ArrayList<Header>();
		try {
			headerList.add(new Header("x-auth-token", "fpoa43edty5"));
			headerList.add(new Header("x-jwt-token", userToken));
			Headers header = new Headers(headerList);
			
			createFLCRequestObj.setScheduleId(scheduleId);
			createFLCRequestObj.setCategories(listCategory);
			createFLCRequestObj.setPackages(listPackages);
			Obj = new ObjectMapper();
			String jsonStr = Obj.writeValueAsString(createFLCRequestObj);
			
			ap = apiUtilObj.postCall(configReaderObject.getliveClassUrl(), "/api/v1/olc-schedule/freeLiveClass", jsonStr, header);
			if (ap.code != 200) {
				return false;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

}
