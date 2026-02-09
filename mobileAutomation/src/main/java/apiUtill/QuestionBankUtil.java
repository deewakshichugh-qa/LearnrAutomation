package apiUtill;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import pojo.addQuestionPaylod.AddQuestionPaylod;
import pojo.addQuestionPaylod.Question;
import pojo.getQuestion.GetQuestion;
import pojo.searchQuestionPayload.LanguageFilter;
import pojo.searchQuestionPayload.SearchQuestionPayload;
import pojo.searchQuestionResponse.SearchQuestionResponse;
import util.APIResponse;
import util.APIUtils;
import util.ConfigFileReader;

public class QuestionBankUtil {

	APIUtils apiUtilObj;
	APIResponse ap;
	JSONObject requestParams;
	List<String> questionBankMsgList = new ArrayList<String>();
	public static ConfigFileReader cfRederObj = new ConfigFileReader();

	public GetQuestion findQuestion(String strquestionId, String strToken) {
		List<Header> listHeader = new ArrayList<Header>();
		GetQuestion getQuestion = null;
		;
		try {
			listHeader.add(new Header("business-name", "Adda247"));
			listHeader.add(new Header("content-type", "application/json"));
			listHeader.add(new Header("jwt-token", strToken));
			Headers headers = new Headers(listHeader);
			apiUtilObj = new APIUtils();
			ap = apiUtilObj.getCall("https://question-service-stag.adda247.com/",
					"question/getQuestion?languageId=en&questionId=" + strquestionId, headers);
			if (ap.code != 200) {
				questionBankMsgList.add("error in get question for question id: " + strquestionId);
				return null;
			}

			getQuestion = ap.responseData.as(GetQuestion.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return getQuestion;
	}

	@SuppressWarnings("unlikely-arg-type")
	public boolean addQuestiontoSection(String strQuestionId, String strToken, String strMapppingId,
			String strSectionId, String questionType, int noOfQuestion) {
		boolean result = true;
		List<Header> listHeader = new ArrayList<Header>();
		AddQuestionPaylod addQuestion;
		ObjectMapper obj;
		int count = 0;
		try {
			listHeader.add(new Header("business-name", "Adda247"));
			listHeader.add(new Header("content-type", "application/json"));
			listHeader.add(new Header("user-token", strToken));
			Headers headers = new Headers(listHeader);
			// GetQuestion getQuestion = findQuestion(strQuestionId, strToken);
			SearchQuestionResponse searchQuestion = searchQuestionBasedonSubjectandType(strToken, questionType,
					strQuestionId);

			if (searchQuestion == null) {
				return false;
			}
			addQuestion = new AddQuestionPaylod();
			addQuestion.setMappingId(strMapppingId);
			addQuestion.setSection(strSectionId);
			List<Question> lstQuestion = new ArrayList<Question>();

			if (questionType.equalsIgnoreCase("COMPREHENSIVE")) {
				for (int i = 0; i < searchQuestion.getResponse().getQuestions().size(); i++) {
					String questionId = searchQuestion.getResponse().getQuestions().get(i).getQuestion()
							.getQuestionId();
					GetQuestion getQuestion = findQuestion(questionId, strToken);
					for (int j = 0; j < getQuestion.getData().getSubQuestionEntity().size(); j++) {
						Question question = new Question();
						question.setQuestionId(getQuestion.getData().getSubQuestionEntity().get(j).getQuestionId());
						question.setSubQuestionId(
								getQuestion.getData().getSubQuestionEntity().get(j).getSubQuestionId());
						question.setPositive(2);
						question.setNegative(0);
						lstQuestion.add(question);
						count = count + 1;
						if (count == noOfQuestion) {
							break;
						}
					}

					if (count == noOfQuestion) {
						break;
					}

				}
			} else {
				List<String> questionList = new ArrayList<>();
				for (int i = 0; i < searchQuestion.getResponse().getQuestions().size(); i++) {
					if (searchQuestion.getResponse().getQuestions().get(i).getQuestion().getLanguageId()
							.equalsIgnoreCase("en")) {
						String strQuestionQbMappingId = searchQuestion.getResponse().getQuestions().get(i).getQuestion()
								.getQuestionId();
						if (!questionList.contains(strQuestionQbMappingId)) {
							questionList.add(strQuestionQbMappingId);
							Question question = new Question();
							question.setQuestionId(strQuestionQbMappingId);
							question.setPositive(2);
							question.setNegative(0);
							lstQuestion.add(question);
							count = count + 1;
							if (count == noOfQuestion) {
								break;
							}
						}

					}
				}
			}
			addQuestion.setQuestions(lstQuestion);
			addQuestion.setLanguage("ENGLISH");
			apiUtilObj = new APIUtils();
			obj = new ObjectMapper();
			String jsonmStr = obj.writeValueAsString(addQuestion);
			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/questions/select/add", jsonmStr, headers);
			if (ap.code != 200) {
				questionBankMsgList.add("Error in while adding question in test: " + strMapppingId);
				return false;
			}

		} catch (Exception e) {
			questionBankMsgList.add("addQuestiontoSection_Exception: " + e.getMessage());
			System.out.println(e.getLocalizedMessage());
		}
		return result;
	}

	public SearchQuestionResponse searchQuestionBasedonSubjectandType(String strToken, String strType,
			String strSubject) {
		SearchQuestionPayload searchQuestion;
		SearchQuestionResponse searchQuestionResponse = null;
		List<String> clientRef3, questionStatus, questionType, businessName;
		ObjectMapper obj;
		List<Header> listHeader = new ArrayList<Header>();
		try {

			listHeader.add(new Header("business-name", "Adda247"));
			listHeader.add(new Header("content-type", "application/json"));
			listHeader.add(new Header("jwt-token", strToken));
			Headers headers = new Headers(listHeader);
			searchQuestion = new SearchQuestionPayload();
			clientRef3 = new ArrayList<String>();
			questionStatus = new ArrayList<String>();
			questionType = new ArrayList<String>();
			businessName = new ArrayList<String>();
			clientRef3.add(strSubject);
			searchQuestion.setClientRef3(clientRef3);
			questionStatus.add("PUBLISHED");
			questionType.add(strType);
			if (strType.equalsIgnoreCase("MCQ")) {
				questionType.add("SCQ");
			}
			businessName.add("Adda247");
			searchQuestion.setBusinessName(businessName);
			searchQuestion.setQuestionType(questionType);
			searchQuestion.setQuestionStatus(questionStatus);
			searchQuestion.setPyq(false);
			searchQuestion.setPageNumber(1);
			LanguageFilter lf = new LanguageFilter();
			List<String> langString = new ArrayList<String>();
			langString.add("en");
			lf.setAnd(langString);
			searchQuestion.setLanguageFilter(lf);
			apiUtilObj = new APIUtils();
			obj = new ObjectMapper();
			String jsonmStr = obj.writeValueAsString(searchQuestion);
			ap = apiUtilObj.postCallMultipleHeader("https://question-service-stag.adda247.com",
					"v2/question/searchQuestions", jsonmStr, headers);
			if (ap.code != 200) {
				questionBankMsgList.add("Error in while adding question in test: ");
				return null;
			}

			searchQuestionResponse = ap.responseData.as(SearchQuestionResponse.class);

		} catch (Exception e) {
			questionBankMsgList.add("searchQuestionBasedonSubjectandType_Exception: " + e.getMessage());
			System.out.println(e.getLocalizedMessage());
		}
		return searchQuestionResponse;
	}

	public boolean saveTest(String strToken, String strTestId) {
		boolean result = true;
		List<Header> listHeader = new ArrayList<Header>();
		try {
			// listHeader.add(new Header("business-name", "Adda247"));
			listHeader.add(new Header("content-type", "application/json"));
			listHeader.add(new Header("user-token", strToken));
			Headers headers = new Headers(listHeader);
			apiUtilObj = new APIUtils();
			ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrl(),
					"admin/studymaterial/test-series/questions/select?mappingId=" + strTestId + "&remove=false", "",
					headers);
			if (ap.code != 200) {
				questionBankMsgList.add("Error in while saving test: " + strTestId);
				return false;
			}

		} catch (Exception e) {
			result = false;
		}
		return result;
	}

}
