package apiUtill;

import java.util.ArrayList;
import java.util.List;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import pojo.myPurchasedPackage.MyPurchasedPackage;
import util.APIResponse;
import util.APIUtils;
import util.ConfigFileReader;

public class PurchaseApiUtil {

	APIUtils apiUtilObj = new APIUtils();
	public List<String> myPurchaseMsgList = new ArrayList<String>();
	ConfigFileReader configObj = new ConfigFileReader();

	public MyPurchasedPackage getMyPurchasedCourse(String strJwtToken) {
		List<Header> headerlist = new ArrayList<Header>();
		APIResponse ap;
		MyPurchasedPackage myPurchasePackageObj = null;
		try {
			headerlist.add(new Header("jwt-token", strJwtToken));
			headerlist.add(new Header("x-auth-token", "fpoa43edty5"));
			headerlist.add(new Header("x-jwt-token", strJwtToken));
			Headers headers = new Headers(headerlist);

			ap = apiUtilObj.getCall(configObj.getStoreUrl(),
					"/api/v1/ppc/package/purchased?pageNumber=0&pageSize=10&src=aweb", headers);

			if (ap.code != 200) {
				myPurchaseMsgList.add("Response code is not 200: " + ap.code + "for " + ap.getFullResponse());
				return null;
			}
			myPurchasePackageObj = ap.responseData.as(MyPurchasedPackage.class);

		} catch (Exception e) {
			// TODO: handle exception
			myPurchaseMsgList.add("getMyPurchasedCourse_Exception: " + e.getMessage());
		}
		return myPurchasePackageObj;
	}

}
