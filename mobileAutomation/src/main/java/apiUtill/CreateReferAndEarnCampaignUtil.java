package apiUtill;

import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import pojo.createReferAndEarnCampaignPayLoad.CampaignMilestone;
import pojo.createReferAndEarnCampaignPayLoad.CreateReferAndEarnCampaignPayLoad;
import pojo.createReferAndEarnCampaignResponse.CreateReferAndEarnCampaignResponse;
import util.APIResponse;
import util.APIUtils;
import util.Common_Function;
import util.ConfigFileReader;

public class CreateReferAndEarnCampaignUtil {
	
	APIUtils apiUtilObj = new APIUtils();
	public List<String> referAndEarnCampaignMsgList = new ArrayList<String>();
	JSONObject requestParams;
	ConfigFileReader configObj = new ConfigFileReader();
	APIResponse ap;
	ObjectMapper Obj;
	UserApiUtil us;
    public static String referAndEarnCampaignTitle;
    public static String referAndEarnCampaignDesc;


	public CreateReferAndEarnCampaignResponse createReferAndEarnCampaign(List<Integer> strPackageId) {

		CreateReferAndEarnCampaignPayLoad createReferAndEarnCampaignPayLoadObj=null;
		CreateReferAndEarnCampaignResponse createReferAndEarnCampaignResponseObj=null;
		CampaignMilestone campaignMilestoneObj=null;
		List<Header> headerlist = new ArrayList<Header>();
		try {
			int randomNum = Common_Function.RandomNumber(1, 100);
			referAndEarnCampaignTitle="AutomationReferTitle_"+randomNum;
			referAndEarnCampaignDesc="AutomationReferalDesc_"+randomNum;
			us = new UserApiUtil();
			//			String strToken = us.adminLogin("abhay.rai@adda247.com","0002@aada!").getFacultyToken();
			//			if(strToken==null) {
			//				System.out.println("Failed to create Token.");
			//				return null;
			//			}
			String strToken="eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpZCI6NzA3LCJlbWFpbCI6ImFiaGF5LnJhaUBhZGRhMjQ3LmNvbSIsIm5hbWUiOiJhYmhheS5yYWlAYWRkYTI0Ny5jb20iLCJhbGxvd2VkU2VjdGlvbnMiOiIzRE1vZGVsQWRkRWRpdCxBY3Rpb25zLEFkZCBuZXcgdXJsLEFkZEVkaXRCdW5kbGVkUHJvZHVjdCxBZGRQYWNrYWdlLEFkbWlzc2lvbkVucXVpcnksQm9va0RlbGl2ZXJ5LEJvb2tzQWRkRWRpdCxidWxrUHVibGlzaFVucHVibGlzaFBhY2thZ2VzLENvbnRlbnRBZG1pblZpZXcsQ291cG9uQWN0aW9ucyxDb3Vwb25EZWFjdGl2YXRlLENvdXBvbkRlbGV0ZSxDb3Vwb25Eb3dubG9hZCxDb3Vwb25FZGl0LENvdXBvblZpZXcsQ3JlYXRlIEluc3RpdHV0ZSAsQ3JlYXRlIG5ldyBjb3Vwb24sQ3JlYXRlIG5ldyBncm91cCxDcmVhdGUgbmV3IHBlcm1pc3Npb25zLENyZWF0ZSBOZXcgUG9zdCxDcmVhdGUgbmV3IHJvbGUsQ3JlYXRlIG5ldyBVc2VycyxDcmVhdGVTb2NpYWxHcm91cCxEYXNoYm9hcmRWaWV3LERlbGV0ZSByb2xlLERlbGV0ZSB1c2VycyxEZWxldGVTb2NpYWxHcm91cCxEb3VidCBSb29tLER1cGxpY2F0ZVBhY2thZ2UsRWRpdCB1c2VycyxFZGl0UGFja2FnZSxFZGl0UG9zdCxFZGl0U29jaWFsR3JvdXAsRXhhbVN5bGxhYnVzQWN0aW9ucyxFeGFtU3lsbGFidXNDb3B5LEV4YW1TeWxsYWJ1c0NyZWF0ZSxFeGFtU3lsbGFidXNFZGl0LEV4YW1TeWxsYWJ1c01hcHBlcixFeGFtU3lsbGFidXNWaWV3LEZhY3VsdHksZmFjdWx0eWFkbWluLEZlZWRDdXN0b21pc2F0aW9uLEZpeGVkUGVyaW9kTW9jayxHbG9iYWxDb25maWcsR3JvdXBEZWxldGUsR3JvdXBFZGl0LEdyb3VwcyxJbnN0aXR1dGVzLEpvaW5fQXNfVGVhY2hlcixMY3NWaWV3LExpZ2h0aG91c2VBY3Rpb25zLExpZ2h0aG91c2VWaWV3LExpbmtDaGlsZFBhY2thZ2UsTGlua1BhcmVudFBhY2thZ2UsTGl2ZUNsYXNzLE1RT3BlcmF0aW9ucyxPbmJvYXJkaW5nLE9yZGVyIEFkbWluLFBhY2thZ2VBY3Rpb24sUGFja2FnZUlPU1ByaWNlVXBsb2FkLFBhY2thZ2VTeWxsYWJ1c0NvcHkscGFja2FnZVVwZ3JhZGUsUGFja2FnZVZpZGVvVXBsb2FkLFBhY2thZ2VWaWV3LFBDUiBhZG1pbixQRFBGcmVlQ29udGVudCxQZXJtaXNzaW9uQWN0aW9ucyxQZXJtaXNzaW9uRGVsZXRlLFBlcm1pc3Npb25FZGl0LFBlcm1pc3Npb25zVmlldyxQcm9tb0NvZGVzLFFCQXNzaWduLFFCQ3JlYXRlRWRpdCxRQkRlbGV0ZSxRQlB1Ymxpc2hVbnB1Ymxpc2gsUUJSZWFkLHF1ZXN0aW9uYmFuayxSZWZlcl9hbmRfZWFybixSb2xlQWN0aW9ucyxSb2xlRGVsZXRlLFJvbGVFZGl0LFJvbGVzVmlldyxTRU8sU2VvIEVkaXRvcixTZXRPckNoYW5nZVBhc3N3b3JkLFNraWxsQ2VydGlmaWNhdGlvbixTb2NpYWxHcm91cEFjdGlvbnMsU29jaWFsVXNlcnNWaWV3LFNvY2lhbF9Hcm91cHMsU3BvbnNvcmVkTGlzdCxTVE9QV09SRFMsU3RvcmVmcm9udEFkbWluLFN0cmVha3MsU3VwZXJBZG1pbl9UZXN0LHRhZ21hc3RlcixUYWdNQ3JlYXRlRWRpdCxUYWdNRGVsZXRlLHRlc3RpbmdhZG1pbix0ZXN0aW5nYWRtaW4yLFVzZXJBY3Rpb25zLFVzZXJEZWxldGUsVXNlckVkaXQsVXNlcnNWaWV3LFVzZXJUcmFja2VyVmlldyx1c2VyX3ZpZXcsVmlkZW9Tb2x1dGlvbnMsVmlld1VzZXJzLFlvdXR1YmVhY3Rpb25zLFlvdVR1YmVDbGFzcyxZb3V0dWJldmlkZW9kZWxldGUsWW91dHViZXZpZGVvRWRpdCxZb3V0dWJldmlkZW9QdWJsaXNoZWQsWW91dHViZXZpZGVvc1ZpZXciLCJyb2xlIjoiU3VwZXJBZG1pbiIsImlhdCI6MTcyMTgxMzQxNn0.6Kt4uz1YyshZG5_8j9jZF3AqmfcebxOL_1DzdOqrRDBfMiI4N9m5crLaZ9CAkgqd973kvTbCIohIOP8yRh24Vg";
			headerlist.add(new Header("X-Jwt-Token", strToken));
			headerlist.add(new Header("X-Auth-Token", "fpoa43edty5"));
			Headers headers = new Headers(headerlist);
			createReferAndEarnCampaignPayLoadObj= new CreateReferAndEarnCampaignPayLoad();
			createReferAndEarnCampaignPayLoadObj.setStartDate(Common_Function.getCurrentDateTime("dd-MM-yyyy HH:mm:ss", 0));
			createReferAndEarnCampaignPayLoadObj.setEndDate(Common_Function.getCurrentDateTime("dd-MM-yyyy HH:mm:ss", 1));
			createReferAndEarnCampaignPayLoadObj.setTitle(referAndEarnCampaignTitle);
			createReferAndEarnCampaignPayLoadObj.setDescription(referAndEarnCampaignDesc);
			createReferAndEarnCampaignPayLoadObj.setPackageId(strPackageId);
			createReferAndEarnCampaignPayLoadObj.setLandingPageUrl("https://www-qa.adda247.com");//later will change the URL
			createReferAndEarnCampaignPayLoadObj.setCampaignTypeEnum(6);
			campaignMilestoneObj=new CampaignMilestone();
			campaignMilestoneObj.setOrder(1);
			campaignMilestoneObj.setThreshold(2);
			campaignMilestoneObj.setRefereeRewardType(6);
			campaignMilestoneObj.setRefereeRewardVal(2000);
			campaignMilestoneObj.setRefererRewardType(6);//Cash-->6 and coin--->5
			campaignMilestoneObj.setRefererRewardVal(2000);
			List<CampaignMilestone> campaignMilestones = new ArrayList<CampaignMilestone>();
			campaignMilestones.add(campaignMilestoneObj);
			createReferAndEarnCampaignPayLoadObj.setCampaignMilestones(campaignMilestones);
			
			Obj = new ObjectMapper();
			String jsonStr = Obj.writeValueAsString(createReferAndEarnCampaignPayLoadObj);

			ap = apiUtilObj.postCall(configObj.getStoreUrl(), "/certification-ws/admin/v1/refer/create", jsonStr, headers);
			if (ap.code != 200) {
				referAndEarnCampaignMsgList.add("Create ReferAndEarn Campaign api got failed.");
				return null;
			}

			System.out.println(ap.responseData);

			createReferAndEarnCampaignResponseObj = ap.responseData.as(CreateReferAndEarnCampaignResponse.class);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}

		return createReferAndEarnCampaignResponseObj;	
	}

}
