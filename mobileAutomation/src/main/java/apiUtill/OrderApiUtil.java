package apiUtill;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.junit.Test;
import util.APIResponse;
import util.APIUtils;
import util.ConfigFileReader;

public class OrderApiUtil {

	APIUtils apiUtilObj = new APIUtils();
	JSONObject requestParams;
	CouponUtil couponUtilObj;
	ConfigFileReader configObj = new ConfigFileReader();
	public List<String> orderMsgList = new ArrayList<String>();

	@SuppressWarnings("unchecked")
	public boolean createOrder(String strToken, String strMailId, int strPackageId, String strMobileNumber) {
		boolean result = true;
		List<Header> headerlist = new ArrayList<Header>();
		APIResponse ap;
		String strCartJson;
		try {
			strCartJson = "{\"" + strPackageId + "\":1}";
			couponUtilObj = new CouponUtil();
			headerlist.add(new Header("x-auth-token", "fpoa43edty5"));
			if (strToken != null) {
				headerlist.add(new Header("x-jwt-token", strToken));
			} else {
				headerlist.add(new Header("x-jwt-token", ""));
			}
			Headers headers = new Headers(headerlist);
			requestParams = new JSONObject();
			requestParams.put("cartJson", strCartJson);
			requestParams.put("name", "abhay");
			requestParams.put("phone", strMobileNumber);
			requestParams.put("paymentType", "RAZORPAY");
			requestParams.put("couponCode", configObj.getCoupon());
			requestParams.put("redirecturl", configObj.getBaseUrl() + "/paymentInfo?path=/product/" + strPackageId
					+ "/mahapack-first-one&email=" + strMailId);
			requestParams.put("utm_source", "");
			requestParams.put("utm_medium", "");
			requestParams.put("utm_campaign", "");
			requestParams.put("utm_term", "");
			requestParams.put("utm_content", "");
			requestParams.put("gclid", "");
			if (strToken != null) {
				ap = apiUtilObj.postCall(configObj.getStoreUrl() + "/api/v1",
						"/orders?user-email=" + strMailId + "&src=aweb", requestParams, headers);
			} else {
				ap = apiUtilObj.postCall(configObj.getStoreUrl() + "/api/v1",
						"/guest/orders?user-email=" + strMailId + "&src=aweb", requestParams, headers);
			}
			System.out.println(ap.getFullResponse());
			if (ap.code != 201) {
				return false;
			}

			Double transactionAmount = Double.valueOf(ap.getJsonKeyValue("data.razorPayCreateOrderResponse.amount"));
			String strOrderId = ap.getJsonKeyValue("data.razorPayCreateOrderResponse.orderId");

			if(transactionAmount > 0.00)
			{
				ap = apiUtilObj.getCall(configObj.getStoreUrl(), "/api/v1/razorpay/test?orderId="+strOrderId, headers);
				if(ap.code != 200)
				{
					orderMsgList.add("error in marking order to paid through razor pay");
					return false;
				}
			}
		} catch (Exception e) {
			orderMsgList.add("createOrder_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	@Test
	public void testing(){
		createOrder("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZGRhLnYxLjA2ZTUzZTk3ODYwODNlZjk3NWU5ZTA4YjY1OWFmMjdkIiwiYXVkIjoiNTAwNjMxNiIsImlhdCI6MTc0NTIzMTU0MiwiaXNzIjoiYWRkYTI0Ny5jb20iLCJuYW1lIjoiQXV0b21hdGlvbiBVc2VyIiwiZW1haWwiOiJhZGRhQXV0b21hdGlvbjI3MDE5MTAwNjBAZ21haWwuY29tIiwicGhvbmUiOiI5MzI5ODE1MDk5IiwidXNlcklkIjoiYWRkYS52MS4wNmU1M2U5Nzg2MDgzZWY5NzVlOWUwOGI2NTlhZjI3ZCIsImxvZ2luQXBpVmVyc2lvbiI6MX0.jVGHaH85pHrzNyaMb3_D-SkdWjmSCCSZfGmmkayXqd6PbTmm_ajG8a5H-Q70kAcVTBT-gNbXa3RgxDDZCl8uEA"
		,"shubham.bansal@adda247.com", 40499, "9878252339");
	}

}
