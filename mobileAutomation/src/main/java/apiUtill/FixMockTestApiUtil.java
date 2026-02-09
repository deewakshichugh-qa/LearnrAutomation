package apiUtill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;
import pojo.fixedMockPayLoad.ExamNameBean;
import pojo.fixedMockPayLoad.FixedMockPayload;
import pojo.fixedMockPayLoad.MockExamTypeBean;
import pojo.fixedMockPayLoad.Section;
import util.APIResponse;
import util.APIUtils;
import util.Common_Function;
import util.ConfigFileReader;

public class FixMockTestApiUtil {
	static int languageId, subjectId, secSubjectId, examCategoryId, examId, testTypetagId;
	static String strExamCategory, strExam;

	static {
		if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
			languageId = 561;
			subjectId = 467;
			examCategoryId = 2010;
			examId = 1707;
			testTypetagId = 458;
			strExamCategory = "BBA";
			strExam = "Insurance Exams 2018";
			secSubjectId = 467;

		} else {
			languageId = 561;
			subjectId = 2176;
			examCategoryId = 2057;
			examId = 379;
			testTypetagId = 458;
			strExamCategory = "CLASS12_NEET";
			strExam = "IITJEE";
			secSubjectId = 2177;

		}
	}

	APIUtils apiUtilObj;
	APIResponse ap;
	JSONObject requestParams;
	Common_Function cfObj = new Common_Function();
	public static String strFixedMockTestId;
	public static String strFixedMockName;
	public ConfigFileReader cfRederObj = new ConfigFileReader();
	public List<String> fixedMockTestApiMsgList = new ArrayList<String>();

	public boolean createFixMockTest(boolean isOptional) {
		boolean result = true;
		FixedMockPayload fixMockTestObj;
		ObjectMapper obj;
		List<Header> listHeader = new ArrayList<Header>();
		List<Header> listHeadertwo = new ArrayList<Header>();
		String strStartTime, strSubmitTime, strResultTime;
		String strTestId;
		String strFileName;
		if (isOptional){
			strFixedMockName = "Automation Fixed Optional Mock " + Common_Function.getCurrentDateTime();
		} else {
			strFixedMockName = "Automation Fixed Mock " + Common_Function.getCurrentDateTime();
		}
		try {
			listHeader.add(new Header("X-JWT-Token", cfRederObj.getAdminMiniUIToken()));

			listHeader.add(new Header("content-type", "application/json"));
			listHeader.add(new Header("X-Auth-Token", "fpoa43edty5"));
			Headers headers = new Headers(listHeader);

			if (ConfigFileReader.strRunMode.equalsIgnoreCase("cloud")) {
				strStartTime = Common_Function.getFutureDateTime("yyyy-MM-dd HH:mm:ss.SS", "min", 4, true);
			} else {
				strStartTime = Common_Function.getFutureDateTime("yyyy-MM-dd HH:mm:ss.SS", "min", 3, true);
			}
			strSubmitTime = Common_Function.getFutureDateTime("yyyy-MM-dd HH:mm:ss.SS", "min", 31, true);
			strResultTime = Common_Function.getFutureDateTime("yyyy-MM-dd HH:mm:ss.SS", "min", 95, true);

			System.out.println("strStartTime: " + strStartTime);
			System.out.println("strSubmitTime: " + strSubmitTime);
			System.out.println("strResultTime: " + strResultTime);

			fixMockTestObj = new FixedMockPayload();
			fixMockTestObj.setTitle(strFixedMockName);
			fixMockTestObj.setLanguageTagId(languageId);
			fixMockTestObj.setTimeLimit(1800);

			if (isOptional) {
				fixMockTestObj.setMarks(140);
			} else {
				fixMockTestObj.setMarks(100);
			}
			fixMockTestObj.setPositive(20);
			fixMockTestObj.setNegative(0);
			fixMockTestObj.setDescription(strFixedMockName);
			fixMockTestObj.setDifficultyLevel(1);
			fixMockTestObj.setTestTypeTagId(testTypetagId);
			fixMockTestObj.setIsCalculator(false);
			fixMockTestObj.setIsFixedMock(true);
			fixMockTestObj.setFixedMockTestType("YES");
			fixMockTestObj.setComingSoon(false);
			fixMockTestObj.setFixedMockTestStartTime(strStartTime);
			fixMockTestObj.setFixedMockSubmitTime(strSubmitTime);
			fixMockTestObj.setFixedMockResultTime(strResultTime);
			fixMockTestObj.setFixedMockEventDescription("Event description");
			fixMockTestObj.setFixedMockPlatform(1);
			fixMockTestObj.setPaidStatus(0);
			fixMockTestObj.setId(0);
			fixMockTestObj.setMappingId(0);
			fixMockTestObj.setSubjectTagId(subjectId);
			fixMockTestObj.setCreateAt(null);
			fixMockTestObj.setFixedMockPrizeType(1);
			fixMockTestObj.setFixedMockFaqJson("[]");
			fixMockTestObj.setFixedMockPrizeDescription("Automation");
			fixMockTestObj.setPublished(false);
			fixMockTestObj.setPoints(0);
			if (isOptional) {
				fixMockTestObj.setTestType(5);
			} else {
				fixMockTestObj.setTestType(1);
			}
			MockExamTypeBean mockExamTypeBeanObj = new MockExamTypeBean();
			mockExamTypeBeanObj.setExamtype(strExamCategory);
			mockExamTypeBeanObj.setId(0);
			mockExamTypeBeanObj.setMockTestId(0);
			mockExamTypeBeanObj.setExamCategoryId(examCategoryId);

			List<MockExamTypeBean> listMockExamTypeBeanObj = new ArrayList<MockExamTypeBean>();
			listMockExamTypeBeanObj.add(mockExamTypeBeanObj);
			fixMockTestObj.setMockExamTypeBeans(listMockExamTypeBeanObj);

			ExamNameBean examNameBeanObj = new ExamNameBean();
			examNameBeanObj.setExamNameTagId(examId);
			examNameBeanObj.setExamNameTagName(strExam);
			examNameBeanObj.setId(0);
			examNameBeanObj.setMockTestId(0);
			List<ExamNameBean> listExamNameBeanObj = new ArrayList<ExamNameBean>();
			listExamNameBeanObj.add(examNameBeanObj);
			fixMockTestObj.setExamNameBeans(listExamNameBeanObj);
			List<Section> lst = new ArrayList<Section>();
			Section sectionObj = new Section();
			sectionObj.setSecDN("Test 1");
			sectionObj.setSectionId(1);
			sectionObj.setSecSubjectTagId(secSubjectId);
			sectionObj.setSecTotalq(5);
			sectionObj.setSecMarks(100);
			sectionObj.setSecPenalty(0);
			sectionObj.setSecCorrect(20);
			sectionObj.setSecTime(0);

			Section secTwoObj;
			if (isOptional)
			{
				sectionObj.setSecOpt(true);
				sectionObj.setAttemptAllowed(5);

				secTwoObj = new Section();
				secTwoObj.setSecDN("Test 2");
				secTwoObj.setSectionId(2);
				secTwoObj.setSecSubjectTagId(secSubjectId);
				secTwoObj.setSecTotalq(5);
				secTwoObj.setSecMarks(40);
				secTwoObj.setSecPenalty(0);
				secTwoObj.setSecCorrect(20);
				secTwoObj.setSecTime(0);
				secTwoObj.setSecOpt(true);
				secTwoObj.setAttemptAllowed(2);
				lst.add(secTwoObj);
			}
			lst.add(sectionObj);

			fixMockTestObj.setSections(lst);
			apiUtilObj = new APIUtils();
			obj = new ObjectMapper();
			String jsonStr = obj.writeValueAsString(fixMockTestObj);
			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/update", jsonStr, headers);
			if (ap.code != 200) {
				fixedMockTestApiMsgList.add("error in create fixed mock test");
				return false;
			}
			strTestId = ap.getJsonKeyValue("data.id");
			listHeadertwo.add(new Header("X-JWT-Token", cfRederObj.getAdminMiniUIToken()));

			listHeadertwo.add(new Header("X-Auth-Token", "fpoa43edty5"));
			headers = new Headers(listHeadertwo);

            if (isOptional)
			{
				strFileName = "MCQ Sankalp.docx";
			} else {
				strFileName = "Test2.docx";
			}
			Map<String, String> formData = null;
			formData = new HashMap<String, String>();
			formData.put("file", "src/main/resources/"+strFileName);
			ap = apiUtilObj.postCallMultipleHeaderFormData(cfRederObj.getAdminMiniUIUrl(),
					"upload/questions/add/" + strTestId, formData, headers, strFileName);
			if (ap.code != 200) {
				fixedMockTestApiMsgList.add("error in upload file for fixedMockTest: " + strTestId);
				return false;
			}

			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/generate/zip?testId=" + strTestId, jsonStr, headers);
			if (ap.code != 200) {
				fixedMockTestApiMsgList.add("error in generate for fixedMockTest: " + strTestId);
				return false;
			}

			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/togglePublish?id=" + strTestId, jsonStr, headers);
			if (ap.code != 200) {
				fixedMockTestApiMsgList.add("error in publish fixedMockTest: " + strTestId);
				return false;
			}

			strFixedMockTestId = strTestId;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			result = false;
		}
		return result;
	}
}
