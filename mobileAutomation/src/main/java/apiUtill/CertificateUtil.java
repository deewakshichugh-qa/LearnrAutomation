package apiUtill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import pojo.createCertificatePayload.CreateCertificatePayload;
import pojo.createCertificateResponse.CreateCertificateResponse;
import pojo.createPackageResponse.CreatePackageResponse;
import pojo.createScheduleResponse.CreateScheduleResponse;
import pojo.getSignedUrl.GetSignedUrl;
import pojo.getVideoList.GetVideoList;
import pojo.userRegistration.UserRegistration;
import util.APIResponse;
import util.APIUtils;
import util.ConfigFileReader;

public class CertificateUtil {

	APIUtils apiUtilObj = new APIUtils();
	public List<String> certificateMsgList = new ArrayList<String>();
	JSONObject requestParams;
	ConfigFileReader configObj = new ConfigFileReader();
	APIResponse ap;
	ConfigFileReader configReaderObject = new ConfigFileReader();
	ObjectMapper Obj;

	public CreateCertificateResponse createCertificate(String strToken, String strPackageId, int eventType,
			int certificateType, Map<String, Integer> contentIds,String strTemplateLink) {
		CreateCertificatePayload certificatePayloadObj;
		List<Header> headerlist = new ArrayList<Header>();
		CreateCertificateResponse createCertificateResponseObj = null;
		try {
			headerlist.add(new Header("X-Jwt-Token", strToken));
			headerlist.add(new Header("X-Auth-Token", "fpoa43edty5"));
			Headers headers = new Headers(headerlist);
			certificatePayloadObj = new CreateCertificatePayload();
			certificatePayloadObj.setPackageId(strPackageId);
			certificatePayloadObj.setPackageTitle("Automation Package");
			certificatePayloadObj.setPackageImgUrl("https://d8cfumlfy8bcr.cloudfront.net/image201685430710.png");
			certificatePayloadObj.setCertificationType(certificateType);
			certificatePayloadObj.setTemplateLink(
					strTemplateLink);
			List<Integer> userVariable = new ArrayList<Integer>();
			userVariable.add(1);
			certificatePayloadObj.setUserVariables(userVariable);
			certificatePayloadObj.setEventType(eventType);
			certificatePayloadObj.setContentOrderIds(contentIds);
			certificatePayloadObj.setCertificateIdIncluded(false);
			Obj = new ObjectMapper();
			String jsonStr = Obj.writeValueAsString(certificatePayloadObj);

			ap = apiUtilObj.postCall(configReaderObject.getStoreUrl(),
					"certification-ws/admin/v1/certification?packageId=" + strPackageId, jsonStr, headers);

			if (ap.code != 200) {
				return null;
			}

			createCertificateResponseObj = ap.responseData.as(CreateCertificateResponse.class);

		} catch (Exception e) {
			certificateMsgList.add("createCertificate_Exception: " + e.getMessage());
			createCertificateResponseObj=null;
		}

		return createCertificateResponseObj;
	}

	public void activeCertificate(String strToken, String strPackageId) {
		List<Header> headerlist = new ArrayList<Header>();
		try {
			headerlist.add(new Header("X-Jwt-Token", strToken));
			headerlist.add(new Header("X-Auth-Token", "fpoa43edty5"));

			Headers headers = new Headers(headerlist);
			ap = apiUtilObj.putCall(configReaderObject.getStoreUrl(),
					"certification-ws/admin/v1/certification/changeActiveStatus?packageId=" + strPackageId
							+ "&active=true",
					"", headers);

			if (ap.code != 201) {
				certificateMsgList.add("Error in Active Certificate.");
				System.out.println("Error in Active Certificate.");

			}
		} catch (Exception e) {
			certificateMsgList.add("createCertificate_Exception: " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public void aprroveCertificate(String strToken, String strPackageId, String strCertificateId) {
		List<Header> headerlist = new ArrayList<Header>();
		try {
			headerlist.add(new Header("X-Jwt-Token", strToken));
			headerlist.add(new Header("X-Auth-Token", "fpoa43edty5"));
			requestParams = new JSONObject();
			requestParams.put("sampleLink",
					"https://stag-certificate-templates-1.s3.ap-south-1.amazonaws.com/1/2440/certificate1.html");
			requestParams.put("packageId", strPackageId);
			requestParams.put("certificateId", strCertificateId);
			Headers headers = new Headers(headerlist);
			ap = apiUtilObj.postCall(configReaderObject.getStoreUrl(),
					"certification-ws/admin/v1/certification/approve", requestParams, headers);

			if (ap.code != 200) {
				certificateMsgList.add("Error in Approve Certificate.");
				System.out.println("Error in Approve Certificate.");
			}
		} catch (Exception e) {
			certificateMsgList.add("createCertificate_Exception: " + e.getMessage());
		}
	}

	public boolean uploadfile(String strUrl, String strFileName, String strPackageId) {
		boolean result = true;

		List<Header> headerlist = new ArrayList<Header>();
		//UserApiUtil useApiObj;
		String strBaseUrl;
		String strPath;
		
		try {
			//strBaseUrl = strUrl.split(strPackageId)[0];
			//strPath = strPackageId+strUrl.split(strPackageId)[1];
			//useApiObj = new UserApiUtil();
			//String strToken = useApiObj.adminLogin("abhay.rai@adda247.com", "0002@aada!").getFacultyToken().toString();
			APIResponse ap;
			//headerlist.add(new Header("X-Jwt-Token", strToken));
			//headerlist.add(new Header("X-Auth-Token", "fpoa43edty5"));
			Headers headers = new Headers(headerlist);
			String FilePath ="src/main/resources/"+strFileName;
			
			apiUtilObj = new APIUtils();
			ap = apiUtilObj.putwithfile(strUrl, null, FilePath, headers);
			
			if(ap.code != 200) {
				certificateMsgList.add("Getting error while uploading file.");
				System.out.println("Getting error while uploading file.");
				return false;
			}

		} catch (Exception e) {
			result = false;
			certificateMsgList.add("uploadfile_Exception: " + e.getMessage());

		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public GetSignedUrl getSignedUrl(String strToken,String strFileName, int strPackageId) {
		List<Header> headerlist = new ArrayList<Header>();
		GetSignedUrl getSignedUrlObj = null;
		try {
			Headers headers = new Headers(headerlist);
			headerlist.add(new Header("X-Jwt-Token", configObj.getAdminMiniUIToken()));
			headerlist.add(new Header("X-Auth-Token", "fpoa43edty5"));
			
			requestParams = new JSONObject();
			requestParams.put("fileName", strFileName);
			requestParams.put("bucket", "CERTIFICATE");
			requestParams.put("fileSize", 6445);
			requestParams.put("packageId", strPackageId);
			ap = apiUtilObj.postCall("https://contentadminapi-qa.adda247.com/",
					"generate/signed/url", requestParams, headers);

			if (ap.code != 202) {
				return null;

			}
			
			getSignedUrlObj = ap.responseData.as(GetSignedUrl.class);
		} catch (Exception e) {
			certificateMsgList.add("getSignedUrl_Exception: " + e.getMessage());
			getSignedUrlObj=null;
			
		}
		return getSignedUrlObj;
	}
	
	public String verifyLiveClassCertification(String strToken) {
		boolean result=true;
		int intPackageId;
		String strTokenNew;
//		CreateChannelResponse createChannelResponse;
		GetSignedUrl getSignedUrlObj;
		String packageTitle;
		try {
			UserApiUtil us = new UserApiUtil();
			String strTokenAdmin = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTMxLCJlbWFpbCI6ImFrYXNoLmthbm9qaWFAYWRkYTI0Ny5jb20iLCJuYW1lIjoiQWthc2giLCJhbGxvd2VkU2VjdGlvbnMiOiJBY3Rpb25zLEFkZCBuZXcgdXJsLEFkZFBhY2thZ2UsYnVsa1B1Ymxpc2hVbnB1Ymxpc2gsQ29udGVudEFkbWluVmlldyxDb3Vwb25BY3Rpb25zLENvdXBvbkRlYWN0aXZhdGUsQ291cG9uRGVsZXRlLENvdXBvbkVkaXQsQ291cG9uVmlldyxDcmVhdGUgSW5zdGl0dXRlICxDcmVhdGUgbmV3IGNvdXBvbixDcmVhdGUgbmV3IGdyb3VwLENyZWF0ZSBuZXcgcGVybWlzc2lvbnMsQ3JlYXRlIE5ldyBQb3N0LENyZWF0ZSBuZXcgcm9sZSxDcmVhdGUgTmV3IFVzZXIsQ3JlYXRlU29jaWFsR3JvdXAsRGFzaGJvYXJkVmlldyxEZWxldGUgcm9sZSxEZWxldGUgdXNlcnMsRGVsZXRlU29jaWFsR3JvdXAsRG91YnQgUm9vbSxEdXBsaWNhdGVQYWNrYWdlLEVkaXQgdXNlcnMsRWRpdFBhY2thZ2UsRWRpdFBvc3QsRWRpdFNvY2lhbEdyb3VwLEZhY3VsdHksZmFjdWx0eWFkbWluLEZlZWRDdXN0b21pc2F0aW9uLEZpeGVkUGVyaW9kTW9jayxHbG9iYWxDb25maWcsR3JvdXBEZWxldGUsR3JvdXBFZGl0LEdyb3VwcyxJbnN0aXR1dGVzLEpvaW5fQXNfVGVhY2hlcixMY3NWaWV3LExpZ2h0aG91c2VBY3Rpb25zLExpZ2h0aG91c2VWaWV3LExpbmtDaGlsZFBhY2thZ2UsTGlua1BhcmVudFBhY2thZ2UsTGl2ZUNsYXNzLFBhY2thZ2VBY3Rpb24sUGFja2FnZUlPU1ByaWNlVXBsb2FkLHBhY2thZ2VVcGdyYWRlLFBhY2thZ2VWaWRlb1VwbG9hZCxQYWNrYWdlVmlldyxQQ1IgYWRtaW4sUERQRnJlZUNvbnRlbnQsUGVybWlzc2lvbkFjdGlvbnMsUGVybWlzc2lvbkRlbGV0ZSxQZXJtaXNzaW9uRWRpdCxQZXJtaXNzaW9uc1ZpZXcsUHJvbW9Db2RlcyxSb2xlQWN0aW9ucyxSb2xlRGVsZXRlLFJvbGVFZGl0LFJvbGVzVmlldyxTRU8sU2VvIEVkaXRvcixTZXRPckNoYW5nZVBhc3N3b3JkLFNraWxsQ2VydGlmaWNhdGlvbixTb2NpYWxHcm91cEFjdGlvbnMsU29jaWFsVXNlcnNWaWV3LFNvY2lhbF9Hcm91cHMsU3BvbnNvcmVkTGlzdCxTVE9QV09SRFMsU3RvcmVmcm9udEFkbWluLFN1cGVyQWRtaW5fVGVzdCx0ZXN0aW5nYWRtaW4sVXNlcnNWIiwicm9sZSI6IlN1cGVyQWRtaW4iLCJpYXQiOjE2ODg0NTc2NzZ9.oFwXxDcVpILFdF4Y-TZPnmCOqdCRsztXwFiQ6StLIXBTgsCQmIdWudNLCXlfUvATQS-RZcViH52HqziKOYJ5Yw";
			strTokenNew = us.adminLogin("abhay.rai@adda247.com", "0002@aada!").getFacultyToken();
			CreatePackageUtil cr = new CreatePackageUtil();
			List<Integer> categorylist = new ArrayList<Integer>();
			Map<String, Integer> conetntList = new HashMap<String, Integer>();
			categorylist.add(6);
			CreatePackageResponse cpr = cr.createPackage(false, false, categorylist, 2);
			if (cpr == null) {
				certificateMsgList.addAll(cr.createPackageMsgList);
				return null;
			}
			intPackageId = cpr.getData().getId();
			System.out.println("intPackageId: " + intPackageId);

			LiveClassApiUtil liveClassObj = new LiveClassApiUtil();

//			createChannelResponse = liveClassObj.createChannel(strTokenNew, String.valueOf(intPackageId), false);
//			if (createChannelResponse == null) {
//				certificateMsgList.add("not able to create channel");
//				return null;
//			}
//			
//			if(!createChannelResponse.getSuccess())
//			{
//				certificateMsgList.add("not able to create channel");
//				return null;
//			}
//			List<Integer> scheduleId = new ArrayList<Integer>();
//
//			for (int i = 0; i < createChannelResponse.getData().size(); i++) {
//				scheduleId.add(createChannelResponse.getData().get(i).getDbData().getId());
//
//			}
//			CreateScheduleResponse createScheduleResponse = liveClassObj.createSchedule(strTokenNew,
//					String.valueOf(intPackageId), "New Class", scheduleId, false);
//			if (createScheduleResponse == null) {
//				certificateMsgList.add("not able to create schedule");
//				return null;
//			}
//			
//			if(!createScheduleResponse.getSuccess())
//			{
//				certificateMsgList.add("not able to create Schedule");
//				return null;
//			}
//
//			liveClassObj.updateSchedule(strTokenNew, String.valueOf(intPackageId), "New",
//					Integer.parseInt(createScheduleResponse.getData().getCreated().get(0).getId()));
//			conetntList.put(createScheduleResponse.getData().getCreated().get(0).getId(),1);

		    getSignedUrlObj = getSignedUrl(strToken, "certificate1.html", cpr.getData().getId());
			if(getSignedUrlObj == null)
			{
				System.out.println("error in get signed url");
			}

			result=uploadfile(getSignedUrlObj.getData().getSignedUrl(), "certificate1.html",String.valueOf(cpr.getData().getId()));
			if (!result) {
				certificateMsgList.add("Error in Uploading file.");
				return null;
			}
			CreateCertificateResponse createCertificateResponseObj = createCertificate(strTokenAdmin,
					String.valueOf(cpr.getData().getId()), 3, 1, conetntList,getSignedUrlObj.getData().getTempS3Url());

			if (createCertificateResponseObj == null) {
				certificateMsgList.add("Error in create mock test series certificate");
				return null;
			}
			activeCertificate(strTokenAdmin, String.valueOf(intPackageId));
			aprroveCertificate(strTokenAdmin, String.valueOf(intPackageId),
					createCertificateResponseObj.getData().getCertificateId());
			packageTitle=cpr.getData().getTitle();
			System.out.println("Package Title:->"+packageTitle);

		} catch (Exception e) {
			certificateMsgList.add("verifyLiveClassCertification_Exception: " + e.getMessage());
			packageTitle = null;
		}

		return packageTitle;
	}
	
	
	public String verifyRecordedLiveClassCertification(String strToken) {
		boolean result=true;
		int intPackageId;
		String strTokenNew;
//		CreateChannelResponse createChannelResponse;
		GetSignedUrl getSignedUrlObj;
		String packageTitle;
		try {
			UserApiUtil us = new UserApiUtil();
			String strTokenAdmin = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTMxLCJlbWFpbCI6ImFrYXNoLmthbm9qaWFAYWRkYTI0Ny5jb20iLCJuYW1lIjoiQWthc2giLCJhbGxvd2VkU2VjdGlvbnMiOiJBY3Rpb25zLEFkZCBuZXcgdXJsLEFkZFBhY2thZ2UsYnVsa1B1Ymxpc2hVbnB1Ymxpc2gsQ29udGVudEFkbWluVmlldyxDb3Vwb25BY3Rpb25zLENvdXBvbkRlYWN0aXZhdGUsQ291cG9uRGVsZXRlLENvdXBvbkVkaXQsQ291cG9uVmlldyxDcmVhdGUgSW5zdGl0dXRlICxDcmVhdGUgbmV3IGNvdXBvbixDcmVhdGUgbmV3IGdyb3VwLENyZWF0ZSBuZXcgcGVybWlzc2lvbnMsQ3JlYXRlIE5ldyBQb3N0LENyZWF0ZSBuZXcgcm9sZSxDcmVhdGUgTmV3IFVzZXIsQ3JlYXRlU29jaWFsR3JvdXAsRGFzaGJvYXJkVmlldyxEZWxldGUgcm9sZSxEZWxldGUgdXNlcnMsRGVsZXRlU29jaWFsR3JvdXAsRG91YnQgUm9vbSxEdXBsaWNhdGVQYWNrYWdlLEVkaXQgdXNlcnMsRWRpdFBhY2thZ2UsRWRpdFBvc3QsRWRpdFNvY2lhbEdyb3VwLEZhY3VsdHksZmFjdWx0eWFkbWluLEZlZWRDdXN0b21pc2F0aW9uLEZpeGVkUGVyaW9kTW9jayxHbG9iYWxDb25maWcsR3JvdXBEZWxldGUsR3JvdXBFZGl0LEdyb3VwcyxJbnN0aXR1dGVzLEpvaW5fQXNfVGVhY2hlcixMY3NWaWV3LExpZ2h0aG91c2VBY3Rpb25zLExpZ2h0aG91c2VWaWV3LExpbmtDaGlsZFBhY2thZ2UsTGlua1BhcmVudFBhY2thZ2UsTGl2ZUNsYXNzLFBhY2thZ2VBY3Rpb24sUGFja2FnZUlPU1ByaWNlVXBsb2FkLHBhY2thZ2VVcGdyYWRlLFBhY2thZ2VWaWRlb1VwbG9hZCxQYWNrYWdlVmlldyxQQ1IgYWRtaW4sUERQRnJlZUNvbnRlbnQsUGVybWlzc2lvbkFjdGlvbnMsUGVybWlzc2lvbkRlbGV0ZSxQZXJtaXNzaW9uRWRpdCxQZXJtaXNzaW9uc1ZpZXcsUHJvbW9Db2RlcyxSb2xlQWN0aW9ucyxSb2xlRGVsZXRlLFJvbGVFZGl0LFJvbGVzVmlldyxTRU8sU2VvIEVkaXRvcixTZXRPckNoYW5nZVBhc3N3b3JkLFNraWxsQ2VydGlmaWNhdGlvbixTb2NpYWxHcm91cEFjdGlvbnMsU29jaWFsVXNlcnNWaWV3LFNvY2lhbF9Hcm91cHMsU3BvbnNvcmVkTGlzdCxTVE9QV09SRFMsU3RvcmVmcm9udEFkbWluLFN1cGVyQWRtaW5fVGVzdCx0ZXN0aW5nYWRtaW4sVXNlcnNWIiwicm9sZSI6IlN1cGVyQWRtaW4iLCJpYXQiOjE2ODg0NTc2NzZ9.oFwXxDcVpILFdF4Y-TZPnmCOqdCRsztXwFiQ6StLIXBTgsCQmIdWudNLCXlfUvATQS-RZcViH52HqziKOYJ5Yw";
			strTokenNew = us.adminLogin("abhay.rai@adda247.com", "0002@aada!").getFacultyToken();
			CreatePackageUtil cr = new CreatePackageUtil();
			PackageApiUtil packageApiUtil = new PackageApiUtil();
			List<Integer> categorylist = new ArrayList<Integer>();
			Map<String, Integer> conetntList = new HashMap<String, Integer>();
			categorylist.add(6);
			CreatePackageResponse cpr = cr.createPackage(false, false, categorylist, 2);
			if (cpr == null) {
				certificateMsgList.add("error in creation of test series package");
				return null;
			}
			intPackageId = cpr.getData().getId();
			System.out.println("intPackageId: " + intPackageId);
			
			GetVideoList getVideoList =	packageApiUtil.videoLinking(String.valueOf(cpr.getData().getId()));
			if(getVideoList == null)
			{
				certificateMsgList.add("Not able to Link video.");
				return null;
			}

			System.out.println("VideoList:--->"+getVideoList.getData().getInputVideoStreamingBean().getVideoStreamingBean().getBilingual().getCc().getSubject().get(0).getCh().get(0).getVideo().size());
			for(int i=0;i<getVideoList.getData().getInputVideoStreamingBean().getVideoStreamingBean().getBilingual().getCc().getSubject().get(0).getCh().get(0).getVideo().size();i++)
			{
				conetntList.put(getVideoList.getData().getInputVideoStreamingBean().getVideoStreamingBean().getBilingual().getCc().getSubject().get(0).getCh().get(0).getVideo().get(i).getUrl(),(i+1));

			}

			LiveClassApiUtil liveClassObj = new LiveClassApiUtil();

//			createChannelResponse = liveClassObj.createChannel(strTokenNew, String.valueOf(intPackageId), false);
//			if (createChannelResponse == null) {
//				certificateMsgList.add("not able to create channel");
//				return null;
//			}
//			
//			if(!createChannelResponse.getSuccess())
//			{
//				certificateMsgList.add("not able to create channel");
//				return null;
//			}
//			List<Integer> scheduleId = new ArrayList<Integer>();
//
//			for (int i = 0; i < createChannelResponse.getData().size(); i++) {
//				scheduleId.add(createChannelResponse.getData().get(i).getDbData().getId());
//
//			}
//			CreateScheduleResponse createScheduleResponse = liveClassObj.createSchedule(strTokenNew,
//					String.valueOf(intPackageId), "New Class", scheduleId, false);
//			if (createScheduleResponse == null) {
//				certificateMsgList.add("not able to create schedule");
//				return null;
//			}
//			
//			if(!createScheduleResponse.getSuccess())
//			{
//				certificateMsgList.add("not able to create Schedule");
//				return null;
//			}
//
//			liveClassObj.updateSchedule(strTokenNew, String.valueOf(intPackageId),"New",
//					Integer.parseInt(createScheduleResponse.getData().getCreated().get(0).getId()));
//			conetntList.put(createScheduleResponse.getData().getCreated().get(0).getId(),1);

		    getSignedUrlObj = getSignedUrl(strToken, "certificate1.html", cpr.getData().getId());
			if(getSignedUrlObj == null)
			{
				System.out.println("error in get signed url");
			}

			result=uploadfile(getSignedUrlObj.getData().getSignedUrl(), "certificate1.html",String.valueOf(cpr.getData().getId()));
			if (!result) {
				certificateMsgList.add("Error in Uploading file.");
				return null;
			}
			CreateCertificateResponse createCertificateResponseObj = createCertificate(strTokenAdmin,
					String.valueOf(cpr.getData().getId()), 3, 1, conetntList,getSignedUrlObj.getData().getTempS3Url());

			if (createCertificateResponseObj == null) {
				certificateMsgList.add("Error in create mock test series certificate");
				return null;
			}
			activeCertificate(strTokenAdmin, String.valueOf(intPackageId));
			aprroveCertificate(strTokenAdmin, String.valueOf(intPackageId),
					createCertificateResponseObj.getData().getCertificateId());
			packageTitle=cpr.getData().getTitle();
			System.out.println("Package Title:->"+packageTitle);

		} catch (Exception e) {
			certificateMsgList.add("verifyRecordedLiveClassCertification_Exception: " + e.getMessage());
			packageTitle = null;
		}

		return packageTitle;
	}
	
	public String createLiveClass() {
		int intPackageId;
		String strTokenNew;
//		CreateChannelResponse createChannelResponse;
		String packageTitle;
		try {
			UserApiUtil us = new UserApiUtil();
			strTokenNew = us.adminLogin("abhay.rai@adda247.com", "0002@aada!").getFacultyToken();
			CreatePackageUtil cr = new CreatePackageUtil();
			List<Integer> categorylist = new ArrayList<Integer>();
			Map<String, Integer> conetntList = new HashMap<String, Integer>();
			categorylist.add(6);
			CreatePackageResponse cpr = cr.createPackage(false, false, categorylist, 2);
			if (cpr == null) {
				certificateMsgList.add("error in creation of test series package");
				return null;
			}
			intPackageId = cpr.getData().getId();
			System.out.println("intPackageId: " + intPackageId);

			LiveClassApiUtil liveClassObj = new LiveClassApiUtil();
//
//			createChannelResponse = liveClassObj.createChannel(strTokenNew, String.valueOf(intPackageId), false);
//			if (createChannelResponse == null) {
//				certificateMsgList.add("not able to create channel");
//				return null;
//			}
//			
//			if(!createChannelResponse.getSuccess())
//			{
//				certificateMsgList.add("not able to create channel");
//				return null;
//			}
//			List<Integer> scheduleId = new ArrayList<Integer>();
//
//			for (int i = 0; i < createChannelResponse.getData().size(); i++) {
//				scheduleId.add(createChannelResponse.getData().get(i).getDbData().getId());
//
//			}
//			CreateScheduleResponse createScheduleResponse = liveClassObj.createSchedule(strTokenNew,
//					String.valueOf(intPackageId), "New Class", scheduleId, false);
//			if (createScheduleResponse == null) {
//				certificateMsgList.add("not able to create schedule");
//				return null;
//			}
//			
//			if(!createScheduleResponse.getSuccess())
//			{
//				certificateMsgList.add("not able to create Schedule");
//				return null;
//			}
//
//			liveClassObj.updateSchedule(strTokenNew, String.valueOf(intPackageId),"New",
//					Integer.parseInt(createScheduleResponse.getData().getCreated().get(0).getId()));
//			conetntList.put(createScheduleResponse.getData().getCreated().get(0).getId(),1);
			packageTitle=cpr.getData().getTitle();
			System.out.println("Package Title:->"+packageTitle);

		} catch (Exception e) {
			certificateMsgList.add("createLiveClass_Exception: " + e.getMessage());
			packageTitle = null;
		}

		return packageTitle;
	}

}
