package apiUtill;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import pojo.LiveClassPackageResponse.liveClassPackageResponse;
import pojo.createSchedulePayLoad.CreateSchedulePayLoad;
import pojo.createSchedulePayLoad.Schedule;
import pojo.createScheduleResponse.CreateScheduleResponse;
import pojo.teacherOLCList.TeacherOLCListResponse;
import pojo.teacherScheduleList.TeacherScheduleList;
import util.APIResponse;
import util.APIUtils;
import util.Common_Function;
import util.ConfigFileReader;

public class LiveClassApiUtil {

	ObjectMapper Obj;
	ConfigFileReader configReaderObject = new ConfigFileReader();
	public List<String> liveClassMsgList = new ArrayList<String>();
	APIResponse ap;
	JSONObject requestParams;
	Common_Function cfobj = new Common_Function();

	public CreateScheduleResponse createSchedule(String strAdminToken, String strPackageID, boolean isMultipleSchedule,
			int intFacultyId) {

		CreateSchedulePayLoad cSPObj;
		List<Header> headerlist = new ArrayList<Header>();
		String strStartDate, strStartTime, strEndTime;
		CreateScheduleResponse createScheduleResponse = null;
		int noOfSchedule;
		try {

			strStartTime = Common_Function.getCuurentTimeAddMin(15);
			strEndTime = Common_Function.getCuurentTimeAddMin(40);
			headerlist.add(new Header("X-Jwt-Token", strAdminToken));
			headerlist.add(new Header("X-Auth-Token", "fpoa43edty5"));
			Headers headers = new Headers(headerlist);
			cSPObj = new CreateSchedulePayLoad();
			cSPObj.setPackageId(strPackageID);
			List<Schedule> scheduleList = new ArrayList<Schedule>();
			if (isMultipleSchedule) {
				noOfSchedule = 7;
			} else {
				noOfSchedule = 1;
			}
			Schedule sch;
			for (int i = 0; i < noOfSchedule; i++) {
				strStartDate = Common_Function.getCurrentate("dd-MM-YYYY", i);
				sch = new Schedule();
				sch.setActive(true);
				sch.setAttachement("");
				sch.setCancelled(false);
				sch.setCancelReason("");
				sch.setClassRoom("");
				sch.setCurrentState("");
				sch.setDescription("");
				sch.setStartDate(strStartDate);
				sch.setStartTime(strStartTime);
				sch.setEndTime(strEndTime);
				sch.setCurrentState("0");
				sch.setRescheduled(false);
				sch.setRescheduleReason("");
				sch.setIsOneTwoOneEnable(true);
				sch.setOlcFacultyId(intFacultyId);
				String topicName = "Automation Class: " + cfobj.randomString();
				sch.setTopic(topicName);

				scheduleList.add(sch);
			}

			cSPObj.setScheduleList(scheduleList);
			Obj = new ObjectMapper();
			String jsonStr = Obj.writeValueAsString(cSPObj);
			APIUtils apiUtilObj = new APIUtils();

			ap = apiUtilObj.postCall(configReaderObject.getliveClassUrl(),
					"api/v1/olc-schedule?userEmail=shubham.bansal@adda247.com", jsonStr, headers);

			if (ap.code != 200) {
				liveClassMsgList.add("Response code is not 200: " + ap.code + "for " + ap.getFullResponse());
				return null;
			}

			createScheduleResponse = ap.responseData.as(CreateScheduleResponse.class);

		} catch (Exception e) {
			liveClassMsgList.add("createSchedule_Exception: " + e.getMessage());
		}
		return createScheduleResponse;
	}

	@SuppressWarnings("unchecked")
	public void cancelSchedule(String strAdminToken, String strPackageId, String liveClassId) {

		List<Header> headerlist = new ArrayList<Header>();
		try {
			headerlist.add(new Header("X-Jwt-Token", strAdminToken));
			headerlist.add(new Header("X-Auth-Token", "fpoa43edty5"));
			Headers headers = new Headers(headerlist);
			requestParams = new JSONObject();
			requestParams.put("reason", "teacher not available");
			APIUtils apiUtilObj = new APIUtils();
			ap = apiUtilObj.putCall(configReaderObject.getliveClassUrl(),
					"api/v1/olc-schedule/cancel/" + strPackageId + "?scheduleId=" + liveClassId
							+ "&cancelledBy=abhay.rai@adda247.com&userEmail=abhay.rai@adda247.com",
					requestParams, headers);

			if (ap.code != 200) {
				liveClassMsgList.add("Response code is not 200: " + ap.code + "for " + ap.getFullResponse());
			}

		} catch (Exception e) {
			liveClassMsgList.add("Exception in cancelSchedule api - " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public boolean updateSchedule(String strAdminToken, String strPackageID, int channelId, int intFacultyId,
			String scheduleIdMain) {
		List<Header> headerlist = new ArrayList<Header>();
		String strStartDate, strStartTime, strEndTime;
		boolean result = true;
		try {

			strStartDate = Common_Function.getCurrentate("dd-MM-YYYY", 0);
			strStartTime = Common_Function.getCuurentTimeAddMin(0);
			strEndTime = Common_Function.getCuurentTimeAddMin(25);
			headerlist.add(new Header("X-Jwt-Token", strAdminToken));
			headerlist.add(new Header("X-Auth-Token", "fpoa43edty5"));
			Headers headers = new Headers(headerlist);
			requestParams = new JSONObject();
			requestParams.put("notifyStudents", true);
			requestParams.put("reason", "data");
			requestParams.put("rescheduleReason", "teacher available early");
			requestParams.put("rescheduled", true);
			requestParams.put("rescheduledBy", "abhay.rai@adda247.com");
			requestParams.put("startDate", strStartDate);
			requestParams.put("startTime", strStartTime);
			requestParams.put("endTime", strEndTime);
			requestParams.put("olcFacultyId", intFacultyId);
			String topicName = "Automation Class: " + cfobj.randomString();
			requestParams.put("topic", topicName);
			requestParams.put("attachement", "");

			APIUtils apiUtilObj = new APIUtils();
			ap = apiUtilObj.putCall(configReaderObject.getliveClassUrl(), "api/v1/olc-schedule/edit/" + strPackageID
					+ "?scheduleId=" + channelId + "&userEmail=shubham.bansal@adda247.com", requestParams, headers);

			if (ap.code != 200) {
				liveClassMsgList.add("Response code is not 200: " + ap.code + "for " + ap.getFullResponse());
				result = false;
			}
		} catch (Exception e) {
			liveClassMsgList.add("updateSchedule_Exception: " + e.getMessage());
			result = false;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public void addHandOut(String strAdminToken, String externalScheduleId, int intFacultyId) {
		List<Header> headerlist = new ArrayList<Header>();

		try {

			headerlist.add(new Header("X-Jwt-Token", strAdminToken));
			headerlist.add(new Header("X-Auth-Token", "fpoa43edty5"));
			Headers headers = new Headers(headerlist);
			requestParams = new JSONObject();
			requestParams.put("scheduled_id", externalScheduleId);
			requestParams.put("teacher_id", intFacultyId);
			requestParams.put("comment", "Automation comment");
			requestParams.put("handout_link",
					"https://live-class-chat-upload.s3.amazonaws.com/liveclasspdf/13132-1690975633628-Screenshot%20%28297%29.png");
			requestParams.put("message_id", "qP4pduUoakdg");
			requestParams.put("sendtime", "teacher not available");
			requestParams.put("username", "abhay Rai");
			requestParams.put("filename", "Screenshot (297)-13132-1690975635108.png");
			APIUtils apiUtilObj = new APIUtils();
			ap = apiUtilObj.postCall(configReaderObject.getBaseUrlUserLoginAdmin(), "api/v1/app/handout/add",
					requestParams, headers);

			if (ap.code != 200) {
				liveClassMsgList.add("Response code is not 200: " + ap.code + "for " + ap.getFullResponse());
			}
		} catch (Exception e) {
			liveClassMsgList.add("updateSchedule_Exception: " + e.getMessage());
		}
	}

	public boolean deleteAllTeacherSchedule(String strToken, String strFacultyId) {
		boolean result = true;
		List<Header> headerlist = new ArrayList<Header>();
		TeacherScheduleList teacherScheduleList;

		try {

			headerlist.add(new Header("X-Jwt-Token", strToken));
			headerlist.add(new Header("X-Auth-Token", "fpoa43edty5"));
			Headers headers = new Headers(headerlist);
			APIUtils apiUtilObj = new APIUtils();
			ap = apiUtilObj.getCall(configReaderObject.getliveClassUrl(),
					"api/v1/olc-schedule/teacherOLCList?facultyId=" + strFacultyId
							+ "&type=today&pageSize=10&pageNumber=1",
					headers);

			// get all schedule
			if (ap.code != 200) {
				liveClassMsgList.add("error in fetch teacher schedule list");
				return false;
			}

			teacherScheduleList = ap.responseData.as(TeacherScheduleList.class);

			if (!teacherScheduleList.getSuccess()) {
				liveClassMsgList.add("error in fetch teacher schedule list");
				return false;
			}

			if (!teacherScheduleList.getData().getDataList().isEmpty()) {
				for (int i = 0; i < teacherScheduleList.getData().getDataList().size(); i++) {
					int strPackageId = teacherScheduleList.getData().getDataList().get(i).getPackageId();
					int scheduleId = teacherScheduleList.getData().getDataList().get(i).getId();
					ap = apiUtilObj.putCall(configReaderObject.getliveClassUrl(),
							"api/v1/olc-schedule/delete/" + strPackageId + "?scheduleId=" + scheduleId, requestParams,
							headers);

					if (ap.code != 200) {
						liveClassMsgList.add("Response code is not 200: " + ap.code + "for " + ap.getFullResponse());
						result = false;
					}
				}
			}

		} catch (Exception e) {
			result = false;
			liveClassMsgList.add("deleteAllTeacherSchedule_Exception: " + e.getMessage());
		}
		return result;
	}

	public liveClassPackageResponse getListOfLiveClassPackage(String packageId) {
		liveClassPackageResponse livePackageResponse = null;
		List<Header> listHeader = new ArrayList<Header>();
		try {
			listHeader.add(new Header("X-Auth-Token", "fpoa43edty5"));
			Headers headers = new Headers(listHeader);
			APIUtils apiUtilObj = new APIUtils();
			ap = apiUtilObj.getCall(configReaderObject.getliveClassUrl(),
					"api/v1/olc-schedule/list?packageId=" + packageId + "&orderBy=DESC", headers);

			if (ap.code != 200) {
				liveClassMsgList.add("Response code is not 200: " + ap.code + "for " + ap.getFullResponse());
				return null;
			}
			livePackageResponse = ap.responseData.as(liveClassPackageResponse.class);

		} catch (Exception e) {
			liveClassMsgList.add("getListOfLivePackage_Exception: " + e.getMessage());
		}
		return livePackageResponse;
	}

	public TeacherOLCListResponse getListOfTeacherOlcList(int facultyId) {
		TeacherOLCListResponse teacherOLCListResponseObj = null;
		List<Header> listHeader = new ArrayList<Header>();
		try {
			listHeader.add(new Header("X-Auth-Token", "fpoa43edty5"));
			Headers headers = new Headers(listHeader);
			APIUtils apiUtilObj = new APIUtils();
			ap = apiUtilObj.getCall(configReaderObject.getliveClassUrl(),
					"api/v1/olc-schedule/teacherOLCList?facultyId=" + facultyId
							+ "&type=today&pageSize=10&pageNumber=1",
					headers);

			if (ap.code != 200) {
				liveClassMsgList.add("Response code is not 200: " + ap.code + "for " + ap.getFullResponse());
				return null;
			}
			teacherOLCListResponseObj = ap.responseData.as(TeacherOLCListResponse.class);

		} catch (Exception e) {
			liveClassMsgList.add("getListOfTeacherOlcList_Exception: " + e.getMessage());
		}
		return teacherOLCListResponseObj;
	}

	public void endAddaLiveClass(String externalScheduleId) {
		List<Header> headerList = new ArrayList<>();
		try {
			headerList.add(new Header("x-auth-token", "fpoa43edty5"));
			Headers headers = new Headers(headerList);

			requestParams = new JSONObject();
            requestParams.put("externalScheduleId", externalScheduleId);
            requestParams.put("timestamp", null);
			APIUtils apiUtilObj = new APIUtils();
			ap = apiUtilObj.postCall(
					"https://stagingliveclasses.adda247.com",
					"api/v1/olc-schedule/EndAddaLiveClass?userEmail=null",
					requestParams,
					headers
			);

			if (ap.code != 200) {
				System.out.println("Response code is not 200: " + ap.code + " for " + ap.getFullResponse());
			}
		} catch (Exception e) {
			System.out.println("endAddaLiveClass_Exception: " + e.getMessage());
		}
	}
}