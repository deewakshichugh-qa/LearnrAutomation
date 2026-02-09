package apiUtill;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import pojo.fixedMockPayLoad.ExamNameBean;
import pojo.fixedMockPayLoad.FixedMockPayload;
import pojo.fixedMockPayLoad.MockExamTypeBean;
import pojo.fixedMockPayLoad.Section;
import util.APIResponse;
import util.APIUtils;
import util.Common_Function;
import util.ConfigFileReader;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import java.util.*;

public class PypApiUtil {
	static int languageId, subjectTagId, secSubjectTagId, examCategoryId, examNameTagId, testTypetagId;
	static String strExamType, strExamNameTagName;

	static {
		languageId = 561;
		subjectTagId = 34146;
		examCategoryId = 2010;
		strExamType = "BANKING";
		testTypetagId = 464;
		strExamNameTagName = "Insurance Exams 2019 Ali 4a";
		examNameTagId = 1705;
		secSubjectTagId = 34146;
	}

	APIUtils apiUtilObj;
	APIResponse ap;
	JSONObject requestParams;
	Common_Function cfObj = new Common_Function();
	public static String strFixedMockTestId;
	public static String strFixedMockName;
	public ConfigFileReader cfRederObj = new ConfigFileReader();
	public List<String> mockTestApiMsgList = new ArrayList<String>();

	public boolean createFreePYPMockTest() {
		boolean result = true;
		FixedMockPayload fixMockTestObj;
		ObjectMapper obj;
		List<Header> listHeader = new ArrayList<Header>();
		List<Header> listHeadertwo = new ArrayList<Header>();
		String strTestId;
		try {
			LocalDate today = LocalDate.now();
			String monthName = today.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
			int year = today.getYear();
			int dayOfMonth = today.getDayOfMonth();

			strFixedMockName = "Automation PYP Free Test : " + cfObj.getRandomNumber(1, 500);
			listHeader.add(new Header("X-JWT-Token", cfRederObj.getAdminMiniUIToken()));

			listHeader.add(new Header("content-type", "application/json"));
			listHeader.add(new Header("X-Auth-Token", "fpoa43edty5"));
			Headers headers = new Headers(listHeader);
			fixMockTestObj = new FixedMockPayload();
			fixMockTestObj.setTitle(strFixedMockName);
			fixMockTestObj.setLanguageTagId(languageId);
			fixMockTestObj.setTimeLimit(600);
			fixMockTestObj.setMarks(5);
			fixMockTestObj.setPositive(1);
			fixMockTestObj.setNegative(0);
			fixMockTestObj.setDifficultyLevel(1);
			fixMockTestObj.setTestTypeTagId(testTypetagId);
			fixMockTestObj.setDescription("Automation Mock Description by automation");
			fixMockTestObj.setIsCalculator(false);
			fixMockTestObj.setComingSoon(false);
			fixMockTestObj.setPaidStatus(0);
			fixMockTestObj.setId(0);
			fixMockTestObj.setMappingId(0);
			fixMockTestObj.setSubjectTagId(subjectTagId);
			fixMockTestObj.setCreateAt(null);
			fixMockTestObj.setPublished(false);
			fixMockTestObj.setPoints(0);
			fixMockTestObj.setCutOffMarks(0);
			fixMockTestObj.setForStorefront(false);
			fixMockTestObj.setLiveTest(false);
			fixMockTestObj.setIsFixedMock(false);
			fixMockTestObj.setForApp(false);
			fixMockTestObj.setIsCalculator(false);
			fixMockTestObj.setVideoSolutionAvailable(false);
			fixMockTestObj.setGenInstructionTime(0);
			fixMockTestObj.setPypTest(false);
			fixMockTestObj.setPypPdfAvailable(false);
			fixMockTestObj.setPypMonth(monthName);
			fixMockTestObj.setPypYear(year);
			fixMockTestObj.setExamDate(String.valueOf(dayOfMonth));
			fixMockTestObj.setShift("1");
			fixMockTestObj.setIsCalculator(false);

			MockExamTypeBean mockExamTypeBeanObj = new MockExamTypeBean();
			mockExamTypeBeanObj.setExamtype(strExamType);
			mockExamTypeBeanObj.setId(0);
			mockExamTypeBeanObj.setMockTestId(0);
			mockExamTypeBeanObj.setExamCategoryId(examCategoryId);
			List<MockExamTypeBean> listMockExamTypeBeanObj = new ArrayList<MockExamTypeBean>();
			listMockExamTypeBeanObj.add(mockExamTypeBeanObj);
			fixMockTestObj.setMockExamTypeBeans(listMockExamTypeBeanObj);

			ExamNameBean examNameBeanObj = new ExamNameBean();
			examNameBeanObj.setExamNameTagId(examNameTagId);
			examNameBeanObj.setExamNameTagName(strExamNameTagName);
			examNameBeanObj.setId(0);
			examNameBeanObj.setMockTestId(0);
			List<ExamNameBean> listExamNameBeanObj = new ArrayList<ExamNameBean>();
			listExamNameBeanObj.add(examNameBeanObj);
			fixMockTestObj.setExamNameBeans(listExamNameBeanObj);

			Section sectionObj = new Section();
			sectionObj.setSecDN("PYP TEST 005");
			sectionObj.setSecSubjectTagId(secSubjectTagId);
			sectionObj.setSecTotalq(5);
			sectionObj.setSecMarks(5);
			sectionObj.setSecPenalty(0);
			sectionObj.setSecCorrect(1);
			sectionObj.setSecTime(600);
			sectionObj.setSectionId(0);
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
			String strExamMappingId = ap.getJsonKeyValue("data.mappingId");
			listHeadertwo.add(new Header("X-JWT-Token", cfRederObj.getAdminMiniUIToken()));
			listHeadertwo.add(new Header("X-Auth-Token", "fpoa43edty5"));
			headers = new Headers(listHeadertwo);

			Map<String, String> formData = null;
			formData = new HashMap<String, String>();
			formData.put("file", "src/main/resources/Test2.docx");
			ap = apiUtilObj.postCallMultipleHeaderFormData(cfRederObj.getAdminMiniUIUrl(),
					"upload/questions/add/" + strTestId, formData, headers, "Mcq_hindi.docx");
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

			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/add-edit/pdfUpload?creatorId=shubham.bansal@adda247.com&mappingId=" + strExamMappingId + "&pdfUrl=" + getRandomValue(), jsonmStr, headers);
			if (ap.code != 200) {
				return false;
			}

			strFixedMockTestId = strTestId;

		} catch (Exception e) {
			result = false;
			mockTestApiMsgList.add("createFreePYPMockTest_Exception: " + e.getMessage());
		}
		return result;
	}

	public String getRandomValue() {

		List<String> values1 = List.of("https://cdn033.adda247.com/411706ca-c513-4bd4-ba9e-cab5b287432d.pdf","https://cdn033.adda247.com/98cb0f41-600d-4e05-a833-aae3b8c1bdf2.pdf", "https://cdn033.adda247.com/e8e6651b-2042-489d-94c2-c30df53ec837.pdf", "https://cdn033.adda247.com/7e0ac766-744d-41f5-b932-fcfb1aaec289.pdf", "https://cdn033.adda247.com/616b6a27-e3b0-4350-a26c-cb2a7f688d92.pdf");
		Random random = new Random();
		int randomIndex = random.nextInt(values1.size());
		return values1.get(randomIndex);
	}

	public boolean createPaidPYPMockTest() {
		boolean result = true;
		FixedMockPayload fixMockTestObj;
		ObjectMapper obj;
		List<Header> listHeader = new ArrayList<Header>();
		List<Header> listHeadertwo = new ArrayList<Header>();
		String strTestId;
		try {
			strFixedMockName = "Automation Paid PYP Test : " + cfObj.getRandomNumber(1, 500);
			listHeader.add(new Header("X-JWT-Token", cfRederObj.getAdminMiniUIToken()));

			listHeader.add(new Header("content-type", "application/json"));
			listHeader.add(new Header("X-Auth-Token", "fpoa43edty5"));
			Headers headers = new Headers(listHeader);
			fixMockTestObj = new FixedMockPayload();
			fixMockTestObj.setTitle(strFixedMockName);
			fixMockTestObj.setLanguageTagId(languageId);
			fixMockTestObj.setTimeLimit(600);
			fixMockTestObj.setMarks(5);
			fixMockTestObj.setPositive(1);
			fixMockTestObj.setNegative(0);
			fixMockTestObj.setDifficultyLevel(1);
			fixMockTestObj.setTestTypeTagId(testTypetagId);
			fixMockTestObj.setDescription("Automation Mock Description by automation");
			fixMockTestObj.setIsCalculator(false);
			fixMockTestObj.setComingSoon(false);
			fixMockTestObj.setPaidStatus(1);
			fixMockTestObj.setId(0);
			fixMockTestObj.setMappingId(0);
			fixMockTestObj.setSubjectTagId(subjectTagId);
			fixMockTestObj.setCreateAt(null);
			fixMockTestObj.setPublished(false);
			fixMockTestObj.setPoints(0);
			MockExamTypeBean mockExamTypeBeanObj = new MockExamTypeBean();
			mockExamTypeBeanObj.setExamtype(strExamType);
			mockExamTypeBeanObj.setId(0);
			mockExamTypeBeanObj.setMockTestId(0);
			mockExamTypeBeanObj.setExamCategoryId(examCategoryId);
			List<MockExamTypeBean> listMockExamTypeBeanObj = new ArrayList<MockExamTypeBean>();
			listMockExamTypeBeanObj.add(mockExamTypeBeanObj);
			fixMockTestObj.setMockExamTypeBeans(listMockExamTypeBeanObj);
			ExamNameBean examNameBeanObj = new ExamNameBean();
			examNameBeanObj.setExamNameTagId(examNameTagId);
			examNameBeanObj.setExamNameTagName(strExamNameTagName);
			examNameBeanObj.setId(0);
			examNameBeanObj.setMockTestId(0);
			List<ExamNameBean> listExamNameBeanObj = new ArrayList<ExamNameBean>();
			listExamNameBeanObj.add(examNameBeanObj);
			fixMockTestObj.setExamNameBeans(listExamNameBeanObj);

			Section sectionObj = new Section();
			sectionObj.setSecDN("PYP TEST 005");
			sectionObj.setSecSubjectTagId(secSubjectTagId);
			sectionObj.setSecTotalq(5);
			sectionObj.setSecMarks(5);
			sectionObj.setSecPenalty(0);
			sectionObj.setSecCorrect(1);
			sectionObj.setSecTime(600);
			sectionObj.setSectionId(0);
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
			String strExamMappingId = ap.getJsonKeyValue("data.mappingId");

			strTestId = ap.getJsonKeyValue("data.id");
			listHeadertwo.add(new Header("X-JWT-Token", cfRederObj.getAdminMiniUIToken()));
			listHeadertwo.add(new Header("X-Auth-Token", "fpoa43edty5"));
			headers = new Headers(listHeadertwo);

			Map<String, String> formData = null;
			formData = new HashMap<String, String>();
			formData.put("file", "src/main/resources/Test2.docx");
			ap = apiUtilObj.postCallMultipleHeaderFormData(cfRederObj.getAdminMiniUIUrl(),
					"upload/questions/add/" + strTestId, formData, headers, "Mcq_hindi.docx");
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

			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/add-edit/pdfUpload?creatorId=tech_team&mappingId=" + strExamMappingId + "&pdfUrl=" + getRandomValue(), jsonmStr, headers);
			if (ap.code != 200) {
				return false;
			}

			strFixedMockTestId = strTestId;

		} catch (Exception e) {
			result = false;
			mockTestApiMsgList.add("createPaidPYPMockTest_Exception: " + e.getMessage());
		}
		return result;
	}
}