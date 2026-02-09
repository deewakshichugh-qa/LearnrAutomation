package apiUtill;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import pojo.createAdmitCardPayload.CreateCampaignAdmitCardPayload;
import pojo.createAdmitCardPayload.Field;
import pojo.createAdmitCardResponse.CreateCampaignAdmitCardResponse;
import util.APIResponse;
import util.APIUtils;
import util.Common_Function;
import util.ConfigFileReader;

public class CreateCampaignAdmitCardApiUtil {

	APIUtils apiUtilObj = new APIUtils();
	public List<String> admitCardMsgList = new ArrayList<String>();
	JSONObject requestParams;
	ConfigFileReader configObj = new ConfigFileReader();
	APIResponse ap;
	ObjectMapper Obj;
	UserApiUtil us;
    public static String campaignName;
    public static String examName;


	public CreateCampaignAdmitCardResponse createCampaign(String strPackageId,int strRewordProductId,boolean isAdmitCardUpload) {

		CreateCampaignAdmitCardPayload createCampaignAdmitCardPayloadObj = null;
		CreateCampaignAdmitCardResponse createCampaignAdmitCardResponseObj = null;
		List<Header> headerlist = new ArrayList<Header>();
		try {
			int randomNum = Common_Function.RandomNumber(1, 100);
			campaignName="AutomationCampaignName_"+randomNum;
			examName="AutomationExamName_"+randomNum;
			us = new UserApiUtil();
			//			String strToken = us.adminLogin("abhay.rai@adda247.com","0002@aada!").getFacultyToken();
			//			if(strToken==null) {
			//				System.out.println("Failed to create Token.");
			//				return null;
			//			}
			String strToken="eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpZCI6OCwiZW1haWwiOiJhYmhheS5yYWlAYWRkYTI0Ny5jb20iLCJuYW1lIjoiQWJoYXkgUmFpIiwiYWxsb3dlZFNlY3Rpb25zIjoiM0RNb2RlbEFkZEVkaXQsQWN0aW9ucyxBZGQgbmV3IHVybCxBZGRFZGl0QnVuZGxlZFByb2R1Y3QsQWRkUGFja2FnZSxBZG1pc3Npb25FbnF1aXJ5LEFkbWl0IENhcmQgQ29sbGVjdGlvbixCb29rRGVsaXZlcnksQm9va3NBZGRFZGl0LENvbnRlbnRBZG1pblZpZXcsQ291cG9uQWN0aW9ucyxDb3Vwb25EZWFjdGl2YXRlLENvdXBvbkRlbGV0ZSxDb3Vwb25Eb3dubG9hZCxDb3Vwb25FZGl0LENvdXBvblZpZXcsQ3JlYXRlIG5ldyBjb3Vwb24sQ3JlYXRlIG5ldyBwZXJtaXNzaW9ucyxDcmVhdGUgbmV3IHJvbGUsQ3JlYXRlIG5ldyBVc2VycyxEYXNoYm9hcmRWaWV3LERlbGV0ZSByb2xlLERlbGV0ZSB1c2VycyxEZWxldGVQYWNrYWdlLERvdWJ0IFJvb20sRHVwbGljYXRlUGFja2FnZSxFZGl0IHJvbGUsRWRpdCB1c2VycyxFZGl0UGFja2FnZSxFeGFtU3lsbGFidXNWaWV3LEZhY3VsdHksR2xvYmFsQ29uZmlnLEpvaW5fQXNfVGVhY2hlcixMY3NWaWV3LExpZ2h0aG91c2VBY3Rpb25zLExpZ2h0aG91c2VWaWV3LExpbmtDaGlsZFBhY2thZ2UsTGlua1BhcmVudFBhY2thZ2UsTGl2ZUNsYXNzLE5ld1JvbGUsT25ib2FyZGluZyxQYWNrYWdlQWN0aW9uLFBhY2thZ2VJT1NQcmljZVVwbG9hZCxwYWNrYWdlVXBncmFkZSxQYWNrYWdlVmlkZW9VcGxvYWQsUGFja2FnZVZpZXcsUENSIGFkbWluLFBEUEZyZWVDb250ZW50LFBlcm1pc3Npb25BY3Rpb25zLFBlcm1pc3Npb25EZWxldGUsUGVybWlzc2lvbkVkaXQsUGVybWlzc2lvbnNWaWV3LHF1ZXN0aW9uYmFuayxSb2xlQWN0aW9ucyxSb2xlRGVsZXRlLFJvbGVFZGl0LFJvbGVzVmlldyxTRU8sU29jaWFsVXNlcnNWaWV3LFNwb25zb3JlZExpc3QsU1RPUFdPUkRTLFN0b3JlZnJvbnRBZG1pbixTdXBlckFkbWluX1Rlc3QsdGFnbWFzdGVyLFRhZ01DcmVhdGVFZGl0LHRhZ21FZGl0Q3JlYXRlLHRhZ21SZWFkLFVzZXJBY3Rpb25zLFVzZXJEZWxldGUsVXNlckVkaXQsVXNlcnNWaWV3LFVzZXJWaWV3LFlvdXR1YmVhY3Rpb25zLFlvdXR1YmV2aWRlb2RlbGV0ZSxZb3V0dWJldmlkZW9FZGl0LFlvdXR1YmV2aWRlb1B1Ymxpc2hlZCxZb3V0dWJldmlkZW9zVmlldyIsInJvbGUiOiJTdXBlciBBZG1pbiIsImlhdCI6MTcwNzkxMTgzM30.B-lJT7TYtsbB-fC57A6OddrhpcUtfWZPsAEirQnc9re4mO23cHky7iSYye9ePHJF4J-aXRMQ9Ogep8rgyCL-qw";
			headerlist.add(new Header("X-Jwt-Token", strToken));
			headerlist.add(new Header("X-Auth-Token", "fpoa43edty5"));
			Headers headers = new Headers(headerlist);
			createCampaignAdmitCardPayloadObj=new CreateCampaignAdmitCardPayload();
			createCampaignAdmitCardPayloadObj.setCampaignName(campaignName);
			createCampaignAdmitCardPayloadObj.setExamName(examName);
			createCampaignAdmitCardPayloadObj.setPackageIds(strPackageId);
			createCampaignAdmitCardPayloadObj.setExamId(4);
			createCampaignAdmitCardPayloadObj.setRewardProductId(strRewordProductId);
			createCampaignAdmitCardPayloadObj.setStartDate(String.valueOf(Common_Function.getEPochTimeInMilliSeconds(0,TimeUnit.MINUTES)));
			createCampaignAdmitCardPayloadObj.setEndDate(String.valueOf(Common_Function.getEPochTimeInMilliSeconds(10,TimeUnit.MINUTES)));
			List<Field> fieldArray = new ArrayList<Field>();


			fieldArray.add(new Field("NAME", "text", 20));
			fieldArray.add(new Field("ROLL NUMBER", "number", 15));
			fieldArray.add(new Field("ADDRESS", "text", 50));
			fieldArray.add(new Field("EXAM NAME", "text", 20));
			fieldArray.add(new Field("DOB", "dob", 10));
			fieldArray.add(new Field("GENDER", "alphabets", 10));


			if(isAdmitCardUpload) {
				fieldArray.add(new Field("admit-card", "file", null));
			}

			createCampaignAdmitCardPayloadObj.setFields(fieldArray);

			System.out.println("Campaign_Name:- "+createCampaignAdmitCardPayloadObj.getCampaignName());
			System.out.println("Exam_Name:- "+createCampaignAdmitCardPayloadObj.getExamName());
			Obj = new ObjectMapper();
			String jsonStr = Obj.writeValueAsString(createCampaignAdmitCardPayloadObj);

			ap = apiUtilObj.postCall(configObj.getAdmitCardUrl(), "/admin/v1/admitCardCampaign", jsonStr, headers);

			if (ap.code != 200) {
				admitCardMsgList.add("Create AdmitCard api got failed.");
				return null;
			}

			System.out.println(ap.responseData);

			createCampaignAdmitCardResponseObj = ap.responseData.as(CreateCampaignAdmitCardResponse.class);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return createCampaignAdmitCardResponseObj;	
	}



}
