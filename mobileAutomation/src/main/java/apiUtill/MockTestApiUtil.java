package apiUtill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import pojo.GetGroupDataResponse.GetGroupData;
import pojo.updateGroupDataPayload.GroupDataRequest;
import pojo.updateGroupDataPayload.UpdateGroupData;
import util.APIResponse;
import util.APIUtils;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import pojo.fixedMockPayLoad.ExamNameBean;
import pojo.fixedMockPayLoad.FixedMockPayload;
import pojo.fixedMockPayLoad.MockExamTypeBean;
import pojo.fixedMockPayLoad.Section;
import util.Common_Function;
import util.ConfigFileReader;

public class MockTestApiUtil {
	static int languageId, subjectId, secSubjectId, examCategoryId, examId, testTypetagId, staticSecSubjectTagId1,
			staticSecSubjectTagId2, staticSecSubjectTagId3;
	static String strExamCategory, strExam;

	static {
			languageId = 561;
			subjectId = 467;
			examCategoryId = 1929;
			examId = 1707;
			testTypetagId = 458;
			strExamCategory = "BBB";
			strExam = "Insurance Exams 2018";
			secSubjectId = 467;
			staticSecSubjectTagId1 = 467;
			staticSecSubjectTagId2 = 1236;
			staticSecSubjectTagId3 = 469;
	}

	APIUtils apiUtilObj;
	APIResponse ap;
	JSONObject requestParams;
	Common_Function cfObj = new Common_Function();
	public static String strFixedMockTestId;
	public static String strFixedMockName;
	public ConfigFileReader cfRederObj = new ConfigFileReader();
	public List<String> mockTestApiMsgList = new ArrayList<String>();

	public boolean createMockTest() {
		boolean result = true;
		FixedMockPayload fixMockTestObj;
		ObjectMapper obj;
		List<Header> listHeader = new ArrayList<Header>();
		List<Header> listHeadertwo = new ArrayList<Header>();
		String strTestId;
		try {
			strFixedMockName = "Automation Mock " + Common_Function.getCurrentDateTime();
			listHeader.add(new Header("X-JWT-Token", cfRederObj.getAdminMiniUIToken()));

			listHeader.add(new Header("content-type", "application/json"));
			listHeader.add(new Header("X-Auth-Token", "fpoa43edty5"));
			Headers headers = new Headers(listHeader);
			fixMockTestObj = new FixedMockPayload();
			fixMockTestObj.setTitle(strFixedMockName);
			fixMockTestObj.setLanguageTagId(languageId);
			fixMockTestObj.setTimeLimit(1800);
			fixMockTestObj.setMarks(100);
			fixMockTestObj.setPositive(20);
			fixMockTestObj.setNegative(0);
			fixMockTestObj.setDifficultyLevel(1);
			fixMockTestObj.setTestTypeTagId(testTypetagId);
			fixMockTestObj.setDescription("Automation Mock Description");

			// fixMockTestObj.setStartTime(0);
			// fixMockTestObj.setEndTime(0);
			fixMockTestObj.setIsCalculator(false);
			fixMockTestObj.setComingSoon(false);
			// fixMockTestObj.setCommingSoonDate("");
			fixMockTestObj.setPaidStatus(0);
			fixMockTestObj.setId(0);
			fixMockTestObj.setMappingId(0);
			fixMockTestObj.setSubjectTagId(subjectId);
			fixMockTestObj.setCreateAt(null);
			fixMockTestObj.setPublished(false);
			fixMockTestObj.setPoints(0);

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

			Section sectionObj = new Section();
			sectionObj.setSecDN("Test");
			sectionObj.setSecSubjectTagId(secSubjectId);
			sectionObj.setSecTotalq(5);
			sectionObj.setSecMarks(100);
			sectionObj.setSecPenalty(0);
			sectionObj.setSecCorrect(20);
			sectionObj.setSecTime(0);
			// sectionObj.setVariableMarks(true);
			List<Section> lst = new ArrayList<Section>();
			lst.add(sectionObj);
			fixMockTestObj.setSections(lst);
			apiUtilObj = new APIUtils();
			obj = new ObjectMapper();
			String jsonmStr = obj.writeValueAsString(fixMockTestObj);
			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/update", jsonmStr, headers);
			if (ap.code != 200) {
				mockTestApiMsgList.add("Issue in update");
				return false;
			}
			strTestId = ap.getJsonKeyValue("data.id");
			listHeadertwo.add(new Header("X-JWT-Token", cfRederObj.getAdminMiniUIToken()));
			listHeadertwo.add(new Header("X-Auth-Token", "fpoa43edty5"));
			headers = new Headers(listHeadertwo);

			Map<String, String> formData = null;
			formData = new HashMap<String, String>();
			formData.put("file", "src/main/resources/Test2.docx");
			ap = apiUtilObj.postCallMultipleHeaderFormData(cfRederObj.getAdminMiniUIUrl(),
					"upload/questions/add/" + strTestId, formData, headers, "Test2.docx");
			if (ap.code != 200) {
				mockTestApiMsgList.add("Issue in ques add");
				return false;
			}

			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/generate/zip?testId=" + strTestId, jsonmStr, headers);
			if (ap.code != 200) {
				mockTestApiMsgList.add("Issue in generate zip");
				return false;
			}

			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/togglePublish?id=" + strTestId, jsonmStr, headers);
			if (ap.code != 200) {
				mockTestApiMsgList.add("Issue in publish");
				return false;
			}

			strFixedMockTestId = strTestId;

		} catch (Exception e) {
			result = false;
			mockTestApiMsgList.add("createMockTest_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean createOptionalMockTestStoreFront(String strFileName) {
		boolean result = true;
		FixedMockPayload fixMockTestObj;
		ObjectMapper obj;
		List<Header> listHeader = new ArrayList<Header>();
		List<Header> listHeaderTwo = new ArrayList<Header>();
		String strTestId;
		strFixedMockName = "Automation Mock Optional " + Common_Function.getCurrentDateTime();
		try {
			listHeader.add(new Header("X-JWT-Token", cfRederObj.getAdminMiniUIToken()));

			listHeader.add(new Header("content-type", "application/json"));
			listHeader.add(new Header("X-Auth-Token", "fpoa43edty5"));
			Headers headers = new Headers(listHeader);
			fixMockTestObj = new FixedMockPayload();
			fixMockTestObj.setTitle(strFixedMockName);
			fixMockTestObj.setLanguageTagId(languageId);

			fixMockTestObj.setTimeLimit(1800);
			fixMockTestObj.setMarks(140);
			fixMockTestObj.setPositive(20);
			fixMockTestObj.setNegative(0);
			fixMockTestObj.setDifficultyLevel(1);
			fixMockTestObj.setTestTypeTagId(testTypetagId);
			fixMockTestObj.setDescription(strFixedMockName);
			// fixMockTestObj.setStartTime(0);
			// fixMockTestObj.setEndTime(0);
			fixMockTestObj.setIsCalculator(false);
			fixMockTestObj.setComingSoon(false);
			// fixMockTestObj.setCommingSoonDate("");
			fixMockTestObj.setPaidStatus(1);
			fixMockTestObj.setId(0);
			fixMockTestObj.setMappingId(0);
			fixMockTestObj.setSubjectTagId(subjectId);
			fixMockTestObj.setCreateAt(null);
			fixMockTestObj.setPublished(false);
			fixMockTestObj.setPoints(0);
			fixMockTestObj.setTestType(5);
			MockExamTypeBean mockExamTypeBeanObj = new MockExamTypeBean();
			mockExamTypeBeanObj.setExamtype("CLASS12_JEE");
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
			sectionObj.setSecOpt(true);
			sectionObj.setAttemptAllowed(5);

			Section secTwoObj = new Section();
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
			lst.add(sectionObj);
			fixMockTestObj.setSections(lst);

			apiUtilObj = new APIUtils();
			obj = new ObjectMapper();
			String jsonmStr = obj.writeValueAsString(fixMockTestObj);
			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/update", jsonmStr, headers);
			if (ap.code != 200) {
				mockTestApiMsgList.add("error in create fixed mock test");
				return false;
			}
			strTestId = ap.getJsonKeyValue("data.id");
			listHeaderTwo.add(new Header("X-JWT-Token", cfRederObj.getAdminMiniUIToken()));

			listHeaderTwo.add(new Header("X-Auth-Token", "fpoa43edty5"));
			headers = new Headers(listHeaderTwo);

			Map<String, String> formData = null;
			formData = new HashMap<String, String>();
			formData.put("file", "src/main/resources/" + strFileName);
			ap = apiUtilObj.postCallMultipleHeaderFormData(cfRederObj.getAdminMiniUIUrl(),
					"upload/questions/add/" + strTestId, formData, headers, strFileName);
			if (ap.code != 200) {
				mockTestApiMsgList.add("error in upload file for fixedMockTest: " + strTestId);
				return false;
			}

			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/generate/zip?testId=" + strTestId, jsonmStr, headers);
			if (ap.code != 200) {
				mockTestApiMsgList.add("error in generate for fixedMockTest: " + strTestId);
				return false;
			}

			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/togglePublish?id=" + strTestId, jsonmStr, headers);
			if (ap.code != 200) {
				mockTestApiMsgList.add("error in publish fixedMockTest: " + strTestId);
				return false;
			}

			strFixedMockTestId = strTestId;

		} catch (Exception e) {
			result = false;
			mockTestApiMsgList.add("createOptionalMockTestStoreFront_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean createMockTestStoreFront(boolean isCalculator) {
		boolean result = true;
		FixedMockPayload fixMockTestObj;
		ObjectMapper obj;
		List<Header> listHeader = new ArrayList<Header>();
		List<Header> listHeaderTwo = new ArrayList<Header>();
		String strTestId;
		try {
			strFixedMockName = "Automation Mock " + Common_Function.getCurrentDateTime();
			listHeader.add(new Header("X-JWT-Token", cfRederObj.getAdminMiniUIToken()));

			listHeader.add(new Header("content-type", "application/json"));
			listHeader.add(new Header("X-Auth-Token", "fpoa43edty5"));
			Headers headers = new Headers(listHeader);
			fixMockTestObj = new FixedMockPayload();
			fixMockTestObj.setTitle(strFixedMockName);
			fixMockTestObj.setLanguageTagId(languageId);

			fixMockTestObj.setTimeLimit(600);
			fixMockTestObj.setMarks(100);
			fixMockTestObj.setPositive(20);
			fixMockTestObj.setNegative(0);
			fixMockTestObj.setDifficultyLevel(1);
			fixMockTestObj.setTestTypeTagId(testTypetagId);
			fixMockTestObj.setDescription("Automation Mock Description");
			// fixMockTestObj.setStartTime(0);
			// fixMockTestObj.setEndTime(0);
			fixMockTestObj.setIsCalculator(isCalculator);
			fixMockTestObj.setComingSoon(false);
			// fixMockTestObj.setCommingSoonDate("");
			fixMockTestObj.setPaidStatus(1);
			fixMockTestObj.setId(0);
			fixMockTestObj.setMappingId(0);
			fixMockTestObj.setSubjectTagId(subjectId);
			fixMockTestObj.setCreateAt(null);
			fixMockTestObj.setPublished(false);
			fixMockTestObj.setPoints(0);

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

			Section sectionObj = new Section();
			sectionObj.setSecDN("Test");
			sectionObj.setSecSubjectTagId(secSubjectId);
			sectionObj.setSecTotalq(5);
			sectionObj.setSecMarks(100);
			sectionObj.setSecPenalty(0);
			sectionObj.setSecCorrect(20);
			sectionObj.setSecTime(0);
			// sectionObj.setVariableMarks(true);
			List<Section> lst = new ArrayList<Section>();
			lst.add(sectionObj);
			fixMockTestObj.setSections(lst);
			apiUtilObj = new APIUtils();
			obj = new ObjectMapper();
			String jsonStr = obj.writeValueAsString(fixMockTestObj);
			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/update", jsonStr, headers);
			if (ap.code != 200) {
				mockTestApiMsgList.add("Issue in testseries update");
				return false;
			}
			strTestId = ap.getJsonKeyValue("data.id");
			listHeaderTwo.add(new Header("X-JWT-Token", cfRederObj.getAdminMiniUIToken()));
			listHeaderTwo.add(new Header("X-Auth-Token", "fpoa43edty5"));
			headers = new Headers(listHeaderTwo);

			Map<String, String> formData = null;
			formData = new HashMap<String, String>();
			formData.put("file", "src/main/resources/Test2.docx");
			ap = apiUtilObj.postCallMultipleHeaderFormData(cfRederObj.getAdminMiniUIUrl(),
					"upload/questions/add/" + strTestId, formData, headers, "Test2.docx");
			if (ap.code != 200) {
				mockTestApiMsgList.add("Issue in ques add");
				return false;
			}

			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/generate/zip?testId=" + strTestId, jsonStr, headers);
			if (ap.code != 200) {
				mockTestApiMsgList.add("Issue in generate zip");
				return false;
			}

			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/togglePublish?id=" + strTestId, jsonStr, headers);
			if (ap.code != 200) {
				mockTestApiMsgList.add("Issue in publish");
				return false;
			}
			strFixedMockTestId = strTestId;

		} catch (Exception e) {
			result = false;
			mockTestApiMsgList.add("createMockTestStoreFront_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean createCutOffMockTest() {
		boolean result = true;
		FixedMockPayload fixMockTestObj;
		ObjectMapper obj;
		List<Header> listHeader = new ArrayList<Header>();
		List<Header> listHeadertwo = new ArrayList<Header>();
		String strTestId;
		try {
			strFixedMockName = "Automation CutOff Mock Test" + Common_Function.getCurrentDateTime();
			listHeader.add(new Header("X-JWT-Token", cfRederObj.getAdminMiniUIToken()));

			listHeader.add(new Header("content-type", "application/json"));
			listHeader.add(new Header("X-Auth-Token", "fpoa43edty5"));
			Headers headers = new Headers(listHeader);
			fixMockTestObj = new FixedMockPayload();
			fixMockTestObj.setTitle(strFixedMockName);
			fixMockTestObj.setLanguageTagId(languageId);

			fixMockTestObj.setTimeLimit(1800);
			fixMockTestObj.setMarks(100);
			fixMockTestObj.setPositive(20);
			fixMockTestObj.setNegative(0);
			fixMockTestObj.setDescription("Automation Mock Description");
			fixMockTestObj.setDifficultyLevel(1);
			fixMockTestObj.setTestTypeTagId(testTypetagId);
			// fixMockTestObj.setStartTime(0);
			// fixMockTestObj.setEndTime(0);
			fixMockTestObj.setIsCalculator(false);
			fixMockTestObj.setComingSoon(false);
			// fixMockTestObj.setCommingSoonDate("");
			fixMockTestObj.setPaidStatus(1);
			fixMockTestObj.setId(0);
			fixMockTestObj.setMappingId(0);
			fixMockTestObj.setSubjectTagId(subjectId);
			fixMockTestObj.setCreateAt(null);
			fixMockTestObj.setPublished(false);
			fixMockTestObj.setPoints(0);
			fixMockTestObj.setCutOffMarks(60);

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

			Section sectionObj = new Section();
			sectionObj.setSecDN("Test");
			sectionObj.setSecSubjectTagId(secSubjectId);
			sectionObj.setSecTotalq(5);
			sectionObj.setSecMarks(100);
			sectionObj.setSecPenalty(0);
			sectionObj.setSecCorrect(20);
			sectionObj.setSecTime(0);
			// sectionObj.setVariableMarks(true);
			List<Section> lst = new ArrayList<Section>();
			lst.add(sectionObj);
			fixMockTestObj.setSections(lst);
			apiUtilObj = new APIUtils();
			obj = new ObjectMapper();
			String jsonmStr = obj.writeValueAsString(fixMockTestObj);
			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/update", jsonmStr, headers);
			if (ap.code != 200) {
				return false;
			}
			strTestId = ap.getJsonKeyValue("data.id");
			listHeadertwo.add(new Header("X-JWT-Token", cfRederObj.getAdminMiniUIToken()));
			listHeadertwo.add(new Header("X-Auth-Token", "fpoa43edty5"));
			headers = new Headers(listHeadertwo);

			Map<String, String> formData = null;
			formData = new HashMap<String, String>();
			formData.put("file", "src/main/resources/Test2.docx");
			ap = apiUtilObj.postCallMultipleHeaderFormData(cfRederObj.getAdminMiniUIUrl(),
					"upload/questions/add/" + strTestId, formData, headers, "Test2.docx");
			if (ap.code != 200) {
				return false;
			}

			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/generate/zip?testId=" + strTestId, jsonmStr, headers);
			if (ap.code != 200) {
				return false;
			}

			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/togglePublish?id=" + strTestId, jsonmStr, headers);
			if (ap.code != 200) {
				return false;
			}

			strFixedMockTestId = strTestId;

		} catch (Exception e) {
			result = false;
			mockTestApiMsgList.add("createCutOffMockTest_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean createAllTypeofMockTestStoreFront(boolean isCalculator) {
		boolean result = true;
		FixedMockPayload fixMockTestObj;
		ObjectMapper obj;
		List<Header> listHeader = new ArrayList<Header>();
		List<Header> listHeadertwo = new ArrayList<Header>();
		String strTestId;
		try {
			strFixedMockName = "Automation All type question Mock Store" + Common_Function.getCurrentDateTime();
			listHeader.add(new Header("X-JWT-Token", cfRederObj.getAdminMiniUIToken()));

			listHeader.add(new Header("content-type", "application/json"));
			listHeader.add(new Header("X-Auth-Token", "fpoa43edty5"));
			Headers headers = new Headers(listHeader);
			fixMockTestObj = new FixedMockPayload();
			fixMockTestObj.setTitle(strFixedMockName);
			fixMockTestObj.setLanguageTagId(languageId);

			fixMockTestObj.setTimeLimit(1800);
			fixMockTestObj.setMarks(150);
			fixMockTestObj.setPositive(5);
			fixMockTestObj.setNegative(0);
			fixMockTestObj.setDifficultyLevel(1);
			fixMockTestObj.setTestTypeTagId(testTypetagId);
			fixMockTestObj.setDescription("Automation Mock Description");
			// fixMockTestObj.setStartTime(0);
			// fixMockTestObj.setEndTime(0);
			fixMockTestObj.setIsCalculator(isCalculator);
			fixMockTestObj.setComingSoon(false);
			// fixMockTestObj.setCommingSoonDate("");
			fixMockTestObj.setPaidStatus(1);
			fixMockTestObj.setId(0);
			fixMockTestObj.setMappingId(0);
			fixMockTestObj.setSubjectTagId(subjectId);
			fixMockTestObj.setCreateAt(null);
			fixMockTestObj.setPublished(false);
			fixMockTestObj.setPoints(0);
			fixMockTestObj.setCutOffMarks(60);

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

			Section sectionObj = new Section();
			sectionObj.setSecDN("Test");
			sectionObj.setSecSubjectTagId(secSubjectId);
			sectionObj.setSecTotalq(30);
			sectionObj.setSecMarks(150);
			sectionObj.setSecPenalty(0);
			sectionObj.setSecCorrect(5);
			sectionObj.setSecTime(0);
			// sectionObj.setVariableMarks(true);
			List<Section> lst = new ArrayList<Section>();
			lst.add(sectionObj);
			fixMockTestObj.setSections(lst);
			apiUtilObj = new APIUtils();
			obj = new ObjectMapper();
			String jsonmStr = obj.writeValueAsString(fixMockTestObj);
			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/update", jsonmStr, headers);
			if (ap.code != 200) {
				mockTestApiMsgList.add("Issue in update");
				return false;
			}
			strTestId = ap.getJsonKeyValue("data.id");
			listHeadertwo.add(new Header("X-JWT-Token", cfRederObj.getAdminMiniUIToken()));
			listHeadertwo.add(new Header("X-Auth-Token", "fpoa43edty5"));
			headers = new Headers(listHeadertwo);

			Map<String, String> formData = null;
			formData = new HashMap<String, String>();
			formData.put("file", "src/main/resources/MIXSankalp.docx");
			ap = apiUtilObj.postCallMultipleHeaderFormData(cfRederObj.getAdminMiniUIUrl(),
					"upload/questions/add/" + strTestId, formData, headers, "MIXSankalp.docx");
			if (ap.code != 200) {
				mockTestApiMsgList.add("error in upload file for MockTest: " + strTestId);
				return false;
			}

			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/generate/zip?testId=" + strTestId, jsonmStr, headers);
			if (ap.code != 200) {
				mockTestApiMsgList.add("error in generate zip for MockTest: " + strTestId);
				return false;
			}

			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/togglePublish?id=" + strTestId, jsonmStr, headers);
			if (ap.code != 200) {
				mockTestApiMsgList.add("error in publish for MockTest: " + strTestId);
				return false;
			}
			strFixedMockTestId = strTestId;

		} catch (Exception e) {
			result = false;
			mockTestApiMsgList.add("createAllTypefMockTestStoreFront_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean createMultiLanguageMockTestStoreFront(boolean isCalculator) {
		boolean result = true;
		FixedMockPayload fixMockTestObj;
		ObjectMapper obj;
		List<Header> listHeader = new ArrayList<Header>();
		List<Header> listHeadertwo = new ArrayList<Header>();
		String jsonmStr;
		String strTestId;
		try {
			strFixedMockName = "Automation Multi Language Mock Store " + Common_Function.getCurrentDateTime();
			listHeader.add(new Header("X-JWT-Token", cfRederObj.getAdminMiniUIToken()));

			listHeader.add(new Header("content-type", "application/json"));
			listHeader.add(new Header("X-Auth-Token", "fpoa43edty5"));
			Headers headers = new Headers(listHeader);
			fixMockTestObj = new FixedMockPayload();
			fixMockTestObj.setTitle(strFixedMockName);
			fixMockTestObj.setLanguageTagId(languageId);

			fixMockTestObj.setTimeLimit(1800);
			fixMockTestObj.setMarks(100);
			fixMockTestObj.setPositive(20);
			fixMockTestObj.setNegative(0);
			fixMockTestObj.setDifficultyLevel(1);
			fixMockTestObj.setTestTypeTagId(testTypetagId);
			fixMockTestObj.setDescription("Automation Mock Description");
			// fixMockTestObj.setStartTime(0);
			// fixMockTestObj.setEndTime(0);
			fixMockTestObj.setIsCalculator(isCalculator);
			fixMockTestObj.setComingSoon(false);
			// fixMockTestObj.setCommingSoonDate("");
			fixMockTestObj.setPaidStatus(1);
			fixMockTestObj.setId(0);
			fixMockTestObj.setMappingId(0);
			fixMockTestObj.setSubjectTagId(subjectId);
			fixMockTestObj.setCreateAt(null);
			fixMockTestObj.setPublished(false);
			fixMockTestObj.setPoints(0);
			fixMockTestObj.setCutOffMarks(60);

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

			Section sectionObj = new Section();
			sectionObj.setSecDN("Test");
			sectionObj.setSecSubjectTagId(secSubjectId);
			sectionObj.setSecTotalq(5);
			sectionObj.setSecMarks(100);
			sectionObj.setSecPenalty(0);
			sectionObj.setSecCorrect(20);
			sectionObj.setSecTime(0);
			// sectionObj.setVariableMarks(true);
			List<Section> lst = new ArrayList<Section>();
			lst.add(sectionObj);
			fixMockTestObj.setSections(lst);
			apiUtilObj = new APIUtils();
			obj = new ObjectMapper();
			jsonmStr = obj.writeValueAsString(fixMockTestObj);
			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/update", jsonmStr, headers);
			if (ap.code != 200) {
				mockTestApiMsgList.add("Issue in update");
				return false;
			}
			strTestId = ap.getJsonKeyValue("data.id");
			listHeadertwo.add(new Header("X-JWT-Token", cfRederObj.getAdminMiniUIToken()));
			listHeadertwo.add(new Header("X-Auth-Token", "fpoa43edty5"));
			headers = new Headers(listHeadertwo);

			Map<String, String> formData = null;
			formData = new HashMap<String, String>();
			formData.put("file", "src/main/resources/Test2.docx");
			ap = apiUtilObj.postCallMultipleHeaderFormData(cfRederObj.getAdminMiniUIUrl(),
					"upload/questions/add/" + strTestId, formData, headers, "Test2.docx");
			if (ap.code != 200) {
				mockTestApiMsgList.add("error in upload file for MockTest: " + strTestId);
				return false;
			}

			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/generate/zip?testId=" + strTestId, jsonmStr, headers);
			if (ap.code != 200) {
				mockTestApiMsgList.add("error in generate zip for MockTest: " + strTestId);
				return false;
			}

			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/togglePublish?id=" + strTestId, jsonmStr, headers);
			if (ap.code != 200) {
				mockTestApiMsgList.add("error in publish for MockTest: " + strTestId);
				return false;
			}

			strFixedMockTestId = strTestId;

			fixMockTestObj.setMappingId(Integer.valueOf(strTestId));
			fixMockTestObj.setLanguageTagId(566);
			// create test in Hindi language
			jsonmStr = obj.writeValueAsString(fixMockTestObj);
			headers = new Headers(listHeader);
			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/update", jsonmStr, headers);
			if (ap.code != 200) {
				mockTestApiMsgList.add("Issue in update 2nd");
				return false;
			}
			strTestId = ap.getJsonKeyValue("data.id");
			headers = new Headers(listHeadertwo);
			Map<String, String> formDataNew = null;
			formDataNew = new HashMap<String, String>();
			formDataNew.put("file", "src/main/resources/Test2.docx");
			ap = apiUtilObj.postCallMultipleHeaderFormData(cfRederObj.getAdminMiniUIUrl(),
					"upload/questions/add/" + strTestId, formDataNew, headers, "Test2.docx");
			if (ap.code != 200) {
				mockTestApiMsgList.add("error in upload file for MockTest: " + strTestId);
				return false;
			}

			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/generate/zip?testId=" + strTestId, jsonmStr, headers);
			if (ap.code != 200) {
				mockTestApiMsgList.add("error in generate zip for MockTest: " + strTestId);
				return false;
			}

		} catch (Exception e) {
			result = false;
			mockTestApiMsgList.add("createMultiLanguageMockTestStoreFront_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean createSectionalTimeLimitMockTestStoreFront(String strFileName) {
		boolean result = true;
		FixedMockPayload fixMockTestObj;
		ObjectMapper obj;
		List<Header> listHeader = new ArrayList<Header>();
		List<Header> listHeadertwo = new ArrayList<Header>();
		String strTestId;
		strFixedMockName = "Automation Sectional time limit Mock " + Common_Function.getCurrentDateTime();
		try {
			listHeader.add(new Header("X-JWT-Token", cfRederObj.getAdminMiniUIToken()));

			listHeader.add(new Header("content-type", "application/json"));
			listHeader.add(new Header("X-Auth-Token", "fpoa43edty5"));
			Headers headers = new Headers(listHeader);
			fixMockTestObj = new FixedMockPayload();
			fixMockTestObj.setTitle(strFixedMockName);
			fixMockTestObj.setLanguageTagId(languageId);

			fixMockTestObj.setTimeLimit(180);
			fixMockTestObj.setMarks(100);
			fixMockTestObj.setPositive(10);
			fixMockTestObj.setNegative(0);
			fixMockTestObj.setDifficultyLevel(1);
			fixMockTestObj.setTestTypeTagId(testTypetagId);
			fixMockTestObj.setDescription(strFixedMockName);
			fixMockTestObj.setIsCalculator(false);
			fixMockTestObj.setComingSoon(false);
			fixMockTestObj.setPaidStatus(1);
			fixMockTestObj.setId(0);
			fixMockTestObj.setMappingId(0);
			fixMockTestObj.setSubjectTagId(subjectId);
			fixMockTestObj.setCreateAt(null);
			fixMockTestObj.setPublished(false);
			fixMockTestObj.setPoints(0);
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
			sectionObj.setSecDN("Section_One");
			sectionObj.setSecSubjectTagId(staticSecSubjectTagId1);
			sectionObj.setSecTotalq(3);
			sectionObj.setSecMarks(30);
			sectionObj.setSecPenalty(0);
			sectionObj.setSecCorrect(10);
			sectionObj.setSecTime(60);
			sectionObj.setSectionId(0);
			sectionObj.setSubSecCompulsory(0);
			lst.add(sectionObj);

			Section sectionObjtwo = new Section();
			sectionObjtwo.setSecDN("Section_Two");
			sectionObjtwo.setSecSubjectTagId(staticSecSubjectTagId2);
			sectionObjtwo.setSecTotalq(3);
			sectionObjtwo.setSecMarks(30);
			sectionObjtwo.setSecPenalty(0);
			sectionObjtwo.setSecCorrect(10);
			sectionObjtwo.setSecTime(60);
			sectionObjtwo.setSectionId(1);
			sectionObjtwo.setSubSecCompulsory(0);
			lst.add(sectionObjtwo);

			Section sectionObjThree = new Section();
			sectionObjThree.setSecDN("Section_Three");
			sectionObjThree.setSecSubjectTagId(staticSecSubjectTagId3);
			sectionObjThree.setSecTotalq(4);
			sectionObjThree.setSecMarks(40);
			sectionObjThree.setSecPenalty(0);
			sectionObjThree.setSecCorrect(10);
			sectionObjThree.setSecTime(60);
			sectionObjThree.setSectionId(2);
			sectionObjThree.setSubSecCompulsory(0);
			lst.add(sectionObjThree);

			fixMockTestObj.setSections(lst);

			apiUtilObj = new APIUtils();
			obj = new ObjectMapper();
			String jsonmStr = obj.writeValueAsString(fixMockTestObj);
			
			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/update", jsonmStr, headers);
			if (ap.code != 200) {
				mockTestApiMsgList.add("Issue in update");
				return false;
			}
			strTestId = ap.getJsonKeyValue("data.id");
			listHeadertwo.add(new Header("X-JWT-Token", cfRederObj.getAdminMiniUIToken()));

			listHeadertwo.add(new Header("X-Auth-Token", "fpoa43edty5"));
			headers = new Headers(listHeadertwo);

			Map<String, String> formData = null;
			formData = new HashMap<String, String>();
			formData.put("file", "src/main/resources/" + strFileName);
			ap = apiUtilObj.postCallMultipleHeaderFormData(cfRederObj.getAdminMiniUIUrl(),
					"upload/questions/add/" + strTestId, formData, headers, strFileName);
			if (ap.code != 200) {
				mockTestApiMsgList.add("error in upload file for fixedMockTest: " + strTestId);
				return false;
			}

			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/generate/zip?testId=" + strTestId, jsonmStr, headers);
			if (ap.code != 200) {
				mockTestApiMsgList.add("error in generate for fixedMockTest: " + strTestId);
				return false;
			}

			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/togglePublish?id=" + strTestId, jsonmStr, headers);
			if (ap.code != 200) {
				mockTestApiMsgList.add("error in publish for fixedMockTest: " + strTestId);
				return false;
			}

			strFixedMockTestId = strTestId;

		} catch (Exception e) {
			result = false;
			mockTestApiMsgList.add("createSectionalTimeLimitMockTestStoreFront_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean createMockTestApp(boolean isCalculator) {
		boolean result = true;
		FixedMockPayload fixMockTestObj;
		ObjectMapper obj;
		List<Header> listHeader = new ArrayList<Header>();
		List<Header> listHeadertwo = new ArrayList<Header>();
		String strTestId;
		try {
			strFixedMockName = "Automation All type question Mock Store " + Common_Function.getCurrentDateTime();
			listHeader.add(new Header("X-JWT-Token", cfRederObj.getAdminMiniUIToken()));

			listHeader.add(new Header("content-type", "application/json"));
			listHeader.add(new Header("X-Auth-Token", "fpoa43edty5"));
			Headers headers = new Headers(listHeader);
			fixMockTestObj = new FixedMockPayload();
			fixMockTestObj.setTitle(strFixedMockName);
			fixMockTestObj.setLanguageTagId(languageId);

			fixMockTestObj.setTimeLimit(1800);
			fixMockTestObj.setMarks(150);
			fixMockTestObj.setPositive(5);
			fixMockTestObj.setNegative(0);
			fixMockTestObj.setDifficultyLevel(1);
			fixMockTestObj.setTestTypeTagId(testTypetagId);
			fixMockTestObj.setDescription("Automation Mock Description");
			// fixMockTestObj.setStartTime(0);
			// fixMockTestObj.setEndTime(0);
			fixMockTestObj.setIsCalculator(isCalculator);
			fixMockTestObj.setComingSoon(false);
			// fixMockTestObj.setCommingSoonDate("");
			fixMockTestObj.setPaidStatus(0);
			fixMockTestObj.setId(0);
			fixMockTestObj.setMappingId(0);
			fixMockTestObj.setSubjectTagId(subjectId);
			fixMockTestObj.setCreateAt(null);
			fixMockTestObj.setPublished(false);
			fixMockTestObj.setPoints(0);
			fixMockTestObj.setCutOffMarks(60);

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

			Section sectionObj = new Section();
			sectionObj.setSecDN("Test");
			sectionObj.setSecSubjectTagId(secSubjectId);
			sectionObj.setSecTotalq(30);
			sectionObj.setSecMarks(150);
			sectionObj.setSecPenalty(0);
			sectionObj.setSecCorrect(5);
			sectionObj.setSecTime(0);
			// sectionObj.setVariableMarks(true);
			List<Section> lst = new ArrayList<Section>();
			lst.add(sectionObj);
			fixMockTestObj.setSections(lst);
			apiUtilObj = new APIUtils();
			obj = new ObjectMapper();
			String jsonmStr = obj.writeValueAsString(fixMockTestObj);
			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/update", jsonmStr, headers);
			if (ap.code != 200) {
				return false;
			}
			strTestId = ap.getJsonKeyValue("data.id");
			listHeadertwo.add(new Header("X-JWT-Token", cfRederObj.getAdminMiniUIToken()));
			listHeadertwo.add(new Header("X-Auth-Token", "fpoa43edty5"));
			headers = new Headers(listHeadertwo);

			Map<String, String> formData = null;
			formData = new HashMap<String, String>();
			formData.put("file", "src/main/resources/MIXSankalp.docx");
			ap = apiUtilObj.postCallMultipleHeaderFormData(cfRederObj.getAdminMiniUIUrl(),
					"upload/questions/add/" + strTestId, formData, headers, "MIXSankalp.docx");
			if (ap.code != 200) {
				mockTestApiMsgList.add("error in upload file for dMockTest: " + strTestId);
				return false;
			}

			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/generate/zip?testId=" + strTestId, jsonmStr, headers);
			if (ap.code != 200) {
				mockTestApiMsgList.add("error in generate zip  MockTest: " + strTestId);
				return false;
			}

			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/togglePublish?id=" + strTestId, jsonmStr, headers);
			if (ap.code != 200) {
				mockTestApiMsgList.add("error in publish  MockTest: " + strTestId);
				return false;
			}

			strFixedMockTestId = strTestId;

		} catch (Exception e) {
			result = false;
			mockTestApiMsgList.add("createMockTestApp_Exception: " + e.getMessage());
		}
		return result;
	}

	public boolean createMockTestStoreFrontLiveTest(boolean isCalculator, boolean isFullLength) {
		boolean result = true;
		FixedMockPayload fixMockTestObj;
		ObjectMapper obj;
		List<Header> listHeader = new ArrayList<Header>();
		List<Header> listHeadertwo = new ArrayList<Header>();
		String strTestId, strCreatedTime, strStartTime, strSubmitTime, strResultTime, strAttempBeforeTime;
		String jsonmStr;
		try {
			strFixedMockName = "Automation Mock Live test Store Front " + Common_Function.getCurrentDateTime();
			listHeader.add(new Header("X-JWT-Token", cfRederObj.getAdminMiniUIToken()));

			strCreatedTime = Common_Function.getFutureDateTime1("yyyy-MM-dd HH:mm:ss.SS", "min", 0);
			strStartTime = Common_Function.getFutureDateTime1("yyyy-MM-dd HH:mm:ss.SS", "min", 5);
			strAttempBeforeTime = Common_Function.getFutureDateTime1("yyyy-MM-dd HH:mm:ss.SS", "min", 7);
			strSubmitTime = Common_Function.getFutureDateTime1("yyyy-MM-dd HH:mm:ss.SS", "min", 17);
			strResultTime = Common_Function.getFutureDateTime1("yyyy-MM-dd HH:mm:ss.SS", "min", 100);

			listHeader.add(new Header("content-type", "application/json"));
			listHeader.add(new Header("X-Auth-Token", "fpoa43edty5"));
			Headers headers = new Headers(listHeader);
			fixMockTestObj = new FixedMockPayload();
			fixMockTestObj.setTitle(strFixedMockName);
			fixMockTestObj.setLanguageTagId(languageId);
			fixMockTestObj.setTimeLimit(600);
			fixMockTestObj.setMarks(100);
			fixMockTestObj.setPositive(20);
			fixMockTestObj.setNegative(0);
			fixMockTestObj.setDifficultyLevel(1);
			fixMockTestObj.setTestTypeTagId(testTypetagId);
			fixMockTestObj.setDescription("Automation Mock Description");
			// fixMockTestObj.setStartTime(0);
			// fixMockTestObj.setEndTime(0);
			fixMockTestObj.setIsCalculator(isCalculator);
			fixMockTestObj.setComingSoon(true);
			// fixMockTestObj.setCommingSoonDate("");
			fixMockTestObj.setPaidStatus(1);
			fixMockTestObj.setId(0);
			fixMockTestObj.setMappingId(0);
			fixMockTestObj.setSubjectTagId(subjectId);
			fixMockTestObj.setCreateAt(strCreatedTime);
			fixMockTestObj.setPublished(false);
			fixMockTestObj.setPoints(0);
			fixMockTestObj.setLiveTest(true);
			if (isFullLength) {
				fixMockTestObj.setTestCategory("2");
			} else {
				fixMockTestObj.setTestCategory("1");
			}
			fixMockTestObj.setIsFixedMock(false);
			fixMockTestObj.setFixedMockTestType("Live Test");
			fixMockTestObj.setFixedMockEventDescription("Event discription");
			fixMockTestObj.setFixedMockPlatform(1);
			fixMockTestObj.setFixedMockPrizeType(1);
			fixMockTestObj.setFixedMockFaqJson("[]");
			fixMockTestObj.setFixedMockPrizeDescription("Automation");
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

			Section sectionObj = new Section();
			sectionObj.setSecDN("Test");
			sectionObj.setSecSubjectTagId(secSubjectId);
			sectionObj.setSecTotalq(5);
			sectionObj.setSecMarks(100);
			sectionObj.setSecPenalty(0);
			sectionObj.setSecCorrect(20);
			sectionObj.setSecTime(0);
			// sectionObj.setVariableMarks(true);
			List<Section> lst = new ArrayList<Section>();
			lst.add(sectionObj);
			fixMockTestObj.setSections(lst);
			apiUtilObj = new APIUtils();
			obj = new ObjectMapper();
			jsonmStr = obj.writeValueAsString(fixMockTestObj);
			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/update", jsonmStr, headers);
			if (ap.code != 200) {
				mockTestApiMsgList.add("Issue in update");
				return false;
			}
			strTestId = ap.getJsonKeyValue("data.id");
			String strExamMappingId = ap.getJsonKeyValue("data.mockExamTypeBeans[0].id");
			String strExamId = ap.getJsonKeyValue("data.examNameBeans[0].id");
			listHeadertwo.add(new Header("X-JWT-Token", cfRederObj.getAdminMiniUIToken()));
			listHeadertwo.add(new Header("X-Auth-Token", "fpoa43edty5"));
			headers = new Headers(listHeadertwo);

			Map<String, String> formData = null;
			formData = new HashMap<String, String>();
			formData.put("file", "src/main/resources/Test2.docx");
			ap = apiUtilObj.postCallMultipleHeaderFormData(cfRederObj.getAdminMiniUIUrl(),
					"upload/questions/add/" + strTestId, formData, headers, "Test2.docx");
			if (ap.code != 200) {
				mockTestApiMsgList.add("error in upload question for LiveTest: " + strTestId);
				return false;
			}

			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/generate/zip?testId=" + strTestId, jsonmStr, headers);
			if (ap.code != 200) {
				mockTestApiMsgList.add("error in generate zip for LiveTest: " + strTestId);
				return false;
			}

			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/togglePublish?id=" + strTestId, jsonmStr, headers);
			if (ap.code != 200) {
				mockTestApiMsgList.add("error in publish for LiveTest: " + strTestId);
				return false;
			}

			fixMockTestObj.setComingSoon(false);
			fixMockTestObj.setId(Integer.parseInt(strTestId));
			fixMockTestObj.setMappingId(Integer.parseInt(strTestId));
			listMockExamTypeBeanObj.get(0).setMockTestId(Integer.parseInt(strTestId));
			listMockExamTypeBeanObj.get(0).setId(Integer.parseInt(strExamMappingId));
			fixMockTestObj.setMockExamTypeBeans(listMockExamTypeBeanObj);
			fixMockTestObj.setFixedMockTestStartTime(strStartTime);
			fixMockTestObj.setFixedMockSubmitTime(strSubmitTime);
			fixMockTestObj.setFixedMockResultTime(strResultTime);
			fixMockTestObj.setAttemptBeforeTime(strAttempBeforeTime);
			listExamNameBeanObj.get(0).setId(Integer.parseInt(strExamId));
			listExamNameBeanObj.get(0).setMockTestId(Integer.parseInt(strTestId));
			fixMockTestObj.setExamNameBeans(listExamNameBeanObj);

			obj = new ObjectMapper();
			jsonmStr = obj.writeValueAsString(fixMockTestObj);
			headers = new Headers(listHeader);
			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/update", jsonmStr, headers);
			if (ap.code != 200) {
				mockTestApiMsgList.add("error in update for LiveTest: " + strTestId);
				return false;
			}

			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/generate/zip?testId=" + strTestId, jsonmStr, headers);
			if (ap.code != 200) {
				mockTestApiMsgList.add("error in generate zip for LiveTest: " + strTestId);
				return false;
			}

			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/togglePublish?id=" + strTestId, jsonmStr, headers);
			if (ap.code != 200) {
				mockTestApiMsgList.add("error in publish for LiveTest: " + strTestId);
				return false;
			}
			strFixedMockTestId = strTestId;

		} catch (Exception e) {
			result = false;
			mockTestApiMsgList.add("createMockTestStoreFrontLiveTest_Exception: " + e.getMessage());
		}
		return result;
	}
	
	public boolean createMockTestStoreFrontUsingQuestionBank(boolean isCalculator) {
		boolean result = true;
		FixedMockPayload fixMockTestObj;
		ObjectMapper obj;
		List<Header> listHeader = new ArrayList<Header>();
		List<Header> listHeadertwo = new ArrayList<Header>();
		String strTestId;
		try {
			strFixedMockName = "Automation QuestionBank Mock " + Common_Function.getCurrentDateTime();
			listHeader.add(new Header("X-JWT-Token", cfRederObj.getAdminMiniUIToken()));

			listHeader.add(new Header("content-type", "application/json"));
			listHeader.add(new Header("X-Auth-Token", "fpoa43edty5"));
			Headers headers = new Headers(listHeader);
			fixMockTestObj = new FixedMockPayload();
			fixMockTestObj.setTitle(strFixedMockName);
			fixMockTestObj.setLanguageTagId(languageId);

			fixMockTestObj.setTimeLimit(600);
			fixMockTestObj.setMarks(100);
			fixMockTestObj.setPositive(20);
			fixMockTestObj.setNegative(0);
			fixMockTestObj.setDifficultyLevel(1);
			fixMockTestObj.setTestTypeTagId(testTypetagId);
			fixMockTestObj.setDescription("Automation Mock Description");
			// fixMockTestObj.setStartTime(0);
			// fixMockTestObj.setEndTime(0);
			fixMockTestObj.setIsCalculator(isCalculator);
			fixMockTestObj.setComingSoon(false);
			// fixMockTestObj.setCommingSoonDate("");
			fixMockTestObj.setPaidStatus(1);
			fixMockTestObj.setId(0);
			fixMockTestObj.setMappingId(0);
			fixMockTestObj.setSubjectTagId(subjectId);
			fixMockTestObj.setCreateAt(null);
			fixMockTestObj.setPublished(false);
			fixMockTestObj.setPoints(0);

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

			Section sectionObj = new Section();
			sectionObj.setSecDN("Test");
			sectionObj.setSectionId(0);
			sectionObj.setSecSubjectTagId(secSubjectId);
			sectionObj.setSecTotalq(5);
			sectionObj.setSecMarks(100);
			sectionObj.setSecPenalty(0);
			sectionObj.setSecCorrect(20);
			sectionObj.setSecTime(0);
			// sectionObj.setVariableMarks(true);
			List<Section> lst = new ArrayList<Section>();
			lst.add(sectionObj);
			fixMockTestObj.setSections(lst);
			apiUtilObj = new APIUtils();
			obj = new ObjectMapper();
			String jsonmStr = obj.writeValueAsString(fixMockTestObj);
			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/update", jsonmStr, headers);
			if (ap.code != 200) {
				mockTestApiMsgList.add("Issue in update");
				return false;
			}
			strTestId = ap.getJsonKeyValue("data.id");
			listHeadertwo.add(new Header("X-JWT-Token", cfRederObj.getAdminMiniUIToken()));
			listHeadertwo.add(new Header("X-Auth-Token", "fpoa43edty5"));
			headers = new Headers(listHeadertwo);

			UserApiUtil userObj = new UserApiUtil();
			String strToken = userObj.adminLogin("abhay.rai@adda247.com", "0002@aada!").getToken();
			QuestionBankUtil qbUtill = new QuestionBankUtil();

			result = qbUtill.addQuestiontoSection("ENGLISH", strToken, strTestId, "CIVIL_0", "MCQ", 5);
			if (!result) {
				mockTestApiMsgList.add("error in adding question in test id: " + strTestId
						+ "with section id: CIVIL_0");
				return false;
			}
			result = qbUtill.saveTest(strToken, strTestId);
			if (!result) {
				mockTestApiMsgList.addAll(qbUtill.questionBankMsgList);
				return result;
			}

			// add question from question bank
			listHeadertwo.add(new Header("X-JWT-Token", cfRederObj.getAdminMiniUIToken()));
			listHeadertwo.add(new Header("X-Auth-Token", "fpoa43edty5"));
			headers = new Headers(listHeadertwo);

			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/generate/zip?testId=" + strTestId, jsonmStr, headers);
			if (ap.code != 200) {
				mockTestApiMsgList.add("Error in generating zip for : " + strTestId);
				return false;
			}

			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/togglePublish?id=" + strTestId, jsonmStr, headers);
			if (ap.code != 200) {
				mockTestApiMsgList.add("Error in publishing Test : " + strTestId);
				return false;
			}
			strFixedMockTestId = strTestId;

		} catch (Exception e) {
			result = false;
			mockTestApiMsgList.add("createMockTestStoreFrontUsingQuestionBank_Exception: " + e.getMessage());
		}
		return result;
	}

	public void edit_deleteTestSeriesForPackageId48219(){
        GetGroupData getGroupData = new GetGroupData();
		List<Header> listHeader = new ArrayList<Header>();
		UpdateGroupData updateGroupData = new UpdateGroupData();;
		try {

			listHeader.add(new Header("x-jwt-token", cfRederObj.getAdminMiniUIToken()));
			listHeader.add(new Header("x-auth-token", "fpoa43edty5"));
			Headers headers = new Headers(listHeader);

			apiUtilObj = new APIUtils();
			ap = apiUtilObj.getCall(cfRederObj.getAdminMiniUIUrlStore(), "api/v1/packages/assign/getGroupData?categoryId=1&packageId=48219", headers);
			if (ap.code != 200) {
				mockTestApiMsgList.add("error in getting the test ids");
				return;
			}
			getGroupData = ap.responseData.as(GetGroupData.class);

			updateGroupData.setPackageId("48219");
			GroupDataRequest groupDataRequest = new GroupDataRequest();

			groupDataRequest.setGroupName("Automation");
			List<Integer> mappingIds = getGroupData.getData().get(0).getMappingIds();

			if (mappingIds.size() > 3) {

				// Skip the first ID (index 0)
				String mappingIdsStr = IntStream.range(1, mappingIds.size()) // start from index 1
						.mapToObj(i -> String.valueOf(mappingIds.get(i)))
						.collect(Collectors.joining(","));

				groupDataRequest.setMappingIds(mappingIdsStr);

				List<GroupDataRequest> groupDataRequestList = new ArrayList<>();
				groupDataRequestList.add(groupDataRequest);
				updateGroupData.setGroupDataRequestList(groupDataRequestList);

				ObjectMapper obj = new ObjectMapper();
				String jsonStr = obj.writeValueAsString(updateGroupData);

				ap = apiUtilObj.putCall(cfRederObj.getAdminMiniUIUrlStore(), "api/v1/packages/assign/updateGroupData?categoryId=1&remove=true", jsonStr, headers);
				if (ap.code != 200) {
					mockTestApiMsgList.add("error in deleting the test ids");
                }
			} else {
				System.out.println("Only 3 mapping ID present.");
			}
		} catch (Exception e) {
            mockTestApiMsgList.add("edit_deleteTestSeries_Exception: " + e.getMessage());
		}
	}
}