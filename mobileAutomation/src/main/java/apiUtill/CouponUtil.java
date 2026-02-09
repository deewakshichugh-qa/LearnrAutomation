package apiUtill;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import pojo.adminLogin.AdminLogin;
import pojo.createCouponPayload.ArtefactRequest;
import pojo.createCouponPayload.CreateCouponPayload;
import pojo.createCouponPayload.UsageValidity;
import pojo.createCouponPayload.Validity;
import pojo.createCouponResponse.CreateCouponResponse;
import util.APIResponse;
import util.APIUtils;
import util.Common_Function;
import util.ConfigFileReader;

public class CouponUtil {
	APIUtils apiUtilObj = new APIUtils();
	public List<String> couponMsgList = new ArrayList<String>();
	JSONObject requestParams;
	ConfigFileReader configObj = new ConfigFileReader();

	

	public CreateCouponResponse createCoupon(String strCouponType, String strDiscountType, String strDiscountMode,
			String strPlatFormList, String strPackageList,String strDiscountValue) {

		CreateCouponResponse createResponseObj = null;
		CreateCouponPayload createCouponPayloadObj;
		List<String> platformList;
		int size;
		ObjectMapper Obj;
		APIResponse ap;
		List<Header> headerlist = new ArrayList<Header>();
		String strToken;
		UserApiUtil userObj = new UserApiUtil();
		try {
			strToken = userObj.adminLogin("abhay.rai@adda247.com", "0002@aada!").getFacultyToken();
			headerlist.add(new Header("x-auth-token", "fpoa43edty5"));
			headerlist.add(new Header("x-jwt-token", strToken));
			Headers headers = new Headers(headerlist);
			createCouponPayloadObj = new CreateCouponPayload();
			createCouponPayloadObj.setCouponType("generic");
			createCouponPayloadObj.setCouponAppliesTo(strCouponType);
			createCouponPayloadObj.setPriceRangeMax(2000);
			createCouponPayloadObj.setPriceRangeMin(0);
			size = strPlatFormList.split(",").length;
			platformList = new ArrayList<String>();
			for (int i = 0; i < size; i++) {
				platformList.add(strPlatFormList.split(",")[i]);
			}
			createCouponPayloadObj.setPlatformList(platformList);
			createCouponPayloadObj.setIsAll(false);
			createCouponPayloadObj.setForCrm(false);
			createCouponPayloadObj.setDescription("Automation Coupon");
			createCouponPayloadObj.setIsExternal(true);
			createCouponPayloadObj.setCouponCode(Common_Function.getAlphaNumericString(15));
			createCouponPayloadObj.setDiscountType(strDiscountType);
			createCouponPayloadObj.setDiscountMode(strDiscountMode);
			createCouponPayloadObj.setDiscountValue(strDiscountValue);
			createCouponPayloadObj.setMaxDiscountValue(5000);
			createCouponPayloadObj.setCreatedBy("abhay.rai@adda247.com");

			UsageValidity usageValidityObj = new UsageValidity();
			usageValidityObj.setTotalUsage(10);
			usageValidityObj.setUsagePerUser(2);
			createCouponPayloadObj.setUsageValidity(usageValidityObj);
			List<Validity> validityList = new ArrayList<Validity>();
			Validity vd = new Validity();
			vd.setValidityStartDate(Common_Function.getCurrentDateTime("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",0));
			vd.setValidityEndDate(Common_Function.getCurrentDateTime("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",1));
			validityList.add(vd);
			createCouponPayloadObj.setValidity(validityList);
			List<ArtefactRequest> lstArtefactRequest = new ArrayList<ArtefactRequest>();

			ArtefactRequest artefactRequestObj = new ArtefactRequest();
			artefactRequestObj.setArtefactTypeExc(strCouponType);
			artefactRequestObj.setArtefactTypeInc(strCouponType);
			List<Object> strartefactIdsExcluded = new ArrayList<Object>();
			artefactRequestObj.setArtefactIdsExcluded(strartefactIdsExcluded);
			List<String> strartefactIdsIncluded = new ArrayList<String>();
			strartefactIdsIncluded.add(strPackageList);
			artefactRequestObj.setArtefactIdsIncluded(strartefactIdsIncluded);
			lstArtefactRequest.add(artefactRequestObj);
			createCouponPayloadObj.setArtefactRequest(lstArtefactRequest);
			Obj = new ObjectMapper();
			String jsonStr = Obj.writeValueAsString(createCouponPayloadObj);

			ap = apiUtilObj.postCall(configObj.getCouponAdminUrl(), "api/v1/coupon", jsonStr, headers);

			if (ap.code != 201) {
				return null;
			}
			createResponseObj = ap.responseData.as(CreateCouponResponse.class);
			
			// invalidate coupon
			
			ap= apiUtilObj.getCall(configObj.getCouponAdminUrl(), "api/v1/coupon/invalidateCache", headers);
			if (ap.code != 200) {
				return null;
			}

		} catch (Exception e) {
			couponMsgList.add("createCoupon_Exception: " + e.getMessage());
			e.printStackTrace();
		}
		return createResponseObj;
	}
	@SuppressWarnings("unchecked")
	public boolean makeCouponDefault(String strId, String strCouponCode) {
		boolean result = true;
		List<Header> headerlist = new ArrayList<Header>();
		UserApiUtil userObj = new UserApiUtil();
		AdminLogin adminLoginObj;
		APIResponse ap;
		try {
			adminLoginObj = userObj.adminLogin("abhay.rai@adda247.com", "0002@aada!");
			headerlist.add(new Header("x-auth-token", "fpoa43edty5"));
			headerlist.add(new Header("x-jwt-token", adminLoginObj.getFacultyToken()));
			Headers headers = new Headers(headerlist);
			requestParams = new JSONObject();
			requestParams.put("id", strId);
			requestParams.put("couponCode", strCouponCode);
			requestParams.put("updatedBy", adminLoginObj.getEmail());

			ap = apiUtilObj.putCall(configObj.getCouponAdminUrl(), "api/v1/markUnmarkDefaultCoupon", requestParams, headers);
			System.out.println(ap.getFullResponse());

			if (ap.code != 200) {
				couponMsgList.add("Response code is not 201: " + ap.code + "for " + ap.getFullResponse());
				return false;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
}
