package apiUtill;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import pojo.doubtRoomPayload.DoubtRoomPayload;
import pojo.doubtRoomPayload.SlotTiming;
import pojo.doubtRoomPayload.Subject;
import util.APIResponse;
import util.APIUtils;
import util.Common_Function;
import util.ConfigFileReader;

public class DoubtRoomApiUtil {

	APIUtils apiUtilObj;
	APIResponse ap;
	JSONObject requestParams;
	Common_Function cfObj = new Common_Function();

	public boolean createDoubtRoomSlot(int PackageId) {
		boolean result = true;
		List<Header> listHeader = new ArrayList<Header>();
		try {
			listHeader.add(new Header("x-jwt-token",
					"eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpZCI6NzA3LCJlbWFpbCI6ImFiaGF5LnJhaUBhZGRhMjQ3LmNvbSIsIm5hbWUiOiJhYmhheS5yYWlAYWRkYTI0Ny5jb20iLCJhbGxvd2VkU2VjdGlvbnMiOiJBY3Rpb25zLEFkZCBuZXcgdXJsLEFkZFBhY2thZ2UsQ29udGVudEFkbWluVmlldyxDb3Vwb25BY3Rpb25zLENvdXBvbkRlYWN0aXZhdGUsQ291cG9uRGVsZXRlLENvdXBvbkRvd25sb2FkLENvdXBvbkVkaXQsQ291cG9uVmlldyxDcmVhdGUgSW5zdGl0dXRlICxDcmVhdGUgbmV3IGNvdXBvbixDcmVhdGUgbmV3IGdyb3VwLENyZWF0ZSBuZXcgcGVybWlzc2lvbnMsQ3JlYXRlIG5ldyByb2xlLENyZWF0ZSBOZXcgVXNlcixDcmVhdGVTb2NpYWxHcm91cCxEYXNoYm9hcmRWaWV3LERlbGV0ZSByb2xlLERlbGV0ZSB1c2VycyxEZWxldGVQYWNrYWdlLERlbGV0ZVNvY2lhbEdyb3VwLERvdWJ0IFJvb20sRHVwbGljYXRlUGFja2FnZSxFZGl0IHVzZXJzLEVkaXRQYWNrYWdlLEVkaXRTb2NpYWxHcm91cCxGYWN1bHR5LGZhY3VsdHlhZG1pbixGZWVkQ3VzdG9taXNhdGlvbixGaXhlZFBlcmlvZE1vY2ssR2xvYmFsQ29uZmlnLEdyb3VwRGVsZXRlLEdyb3VwRWRpdCxHcm91cHMsSW5zdGl0dXRlcyxKb2luX0FzX1RlYWNoZXIsTGNzVmlldyxMaWdodGhvdXNlQWN0aW9ucyxMaWdodGhvdXNlVmlldyxMaW5rQ2hpbGRQYWNrYWdlLExpbmtQYXJlbnRQYWNrYWdlLExpdmVDbGFzcyxQYWNrYWdlQWN0aW9uLFBhY2thZ2VJT1NQcmljZVVwbG9hZCxQYWNrYWdlVmlkZW9VcGxvYWQsUGFja2FnZVZpZXcsUENSIGFkbWluLFBEUEZyZWVDb250ZW50LFBlcm1pc3Npb25BY3Rpb25zLFBlcm1pc3Npb25EZWxldGUsUGVybWlzc2lvbkVkaXQsUGVybWlzc2lvbnNWaWV3LFByb21vQ29kZXMsUm9sZUFjdGlvbnMsUm9sZURlbGV0ZSxSb2xlRWRpdCxSb2xlc1ZpZXcsU0VPLFNlbyBFZGl0b3IsU2V0T3JDaGFuZ2VQYXNzd29yZCxTb2NpYWxHcm91cEFjdGlvbnMsU29jaWFsVXNlcnNWaWV3LFNvY2lhbF9Hcm91cHMsU3BvbnNvcmVkTGlzdCxTVE9QV09SRFMsU3RvcmVmcm9udEFkbWluLFN1cGVyQWRtaW5fVGVzdCx0ZXN0aW5nYWRtaW4sdGVzdGluZ2FkbWluMixVc2VyQWN0aW9ucyxVc2VyRGVsZXRlLFVzZXJFZGl0LFVzZXJzVmlldyxZIiwicm9sZSI6IlN1cGVyQWRtaW4iLCJpYXQiOjE2ODMyNzM5OTF9.BaMCxaDyY6gGQudDWThKV8gyz7mbhOIjsEDUnexTAHa87OiDpFjlCnZnyivFVw4B5PbIc8exUaEbIisRKcCpWA"));

			listHeader.add(new Header("content-type", "application/json"));
			listHeader.add(new Header("x-auth-token", "fpoa43edty5"));
			Headers headers = new Headers(listHeader);
			DoubtRoomPayload drPlObj = new DoubtRoomPayload();
			drPlObj.setId(0);
			drPlObj.setFacultyId(null);
			drPlObj.setStartDate(Common_Function.getCurrentDate("dd-MM-yyyy"));
			drPlObj.setEndDate(Common_Function.getCurrentDate("dd-MM-yyyy"));
			drPlObj.setTopic(null);
			drPlObj.setCancelled(false);
			drPlObj.setCancelReason("");
			drPlObj.setRescheduled(false);
			drPlObj.setRescheduleReason("");
			drPlObj.setExternalScheduleId("");
			drPlObj.setActive(true);
			drPlObj.setCurrentState("0");
			drPlObj.setChatGroupName(null);
			List<Subject> lsiSubject = new ArrayList<Subject>();
			Subject subject = new Subject();
			subject.setSubjectId("Amit Test");
			subject.setDisplayName("");
			lsiSubject.add(subject);
			drPlObj.setSubjectList(lsiSubject);
			List<Integer> packageId = new ArrayList<Integer>();
			packageId.add(PackageId);
			drPlObj.setPackageIds(packageId);

			List<SlotTiming> lstSlottiming = new ArrayList<SlotTiming>();
			SlotTiming st = new SlotTiming();
			st.setStartTime(Common_Function.getFutureDateTime("HH:mm", "min", 7, false));
			st.setEndTime(Common_Function.getFutureDateTime("HH:mm", "min", 20, false));
			lstSlottiming.add(st);
			drPlObj.setSlotTimings(lstSlottiming);
			ObjectMapper obj = new ObjectMapper();
			String jsonmStr = obj.writeValueAsString(drPlObj);
			apiUtilObj = new APIUtils();
			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
				ap = apiUtilObj.postCallMultipleHeader("https://doubtroom-staging.adda247.com", "/admin/v1/schedule",
						jsonmStr, headers);
			} else {
				ap = apiUtilObj.postCallMultipleHeader("https://doubtroom-staging.sankalpbharat.com",
						"/admin/v1/schedule", jsonmStr, headers);
			}
			if (ap.code != 200) {
				result = false;
			}
		} catch (Exception e) {
			result = false;
		}

		return result;
	}

}
