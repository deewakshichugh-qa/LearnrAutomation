package apiUtill;

import java.util.ArrayList;
import java.util.List;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import pojo.PackageDetailsAlreadyPurchased.DataAlreadyPurchased;
import pojo.PackageDetailsAlreadyPurchased.ExamDataAlreadyPurchased;
import pojo.PackageDetailsAlreadyPurchased.PpcResponseAlreadyPurchased;
import pojo.PackageDetailsNewPurchased.ExamDataNewPurchased;
import pojo.PackageDetailsNewPurchased.ExamListNewPurchased;
import pojo.PackageDetailsNewPurchased.PpcResponseNewPurchased;
import pojo.examDetails.ExamResponse;
import util.APIResponse;
import util.APIUtils;
import util.ConfigFileReader;

public class PpcUtil {
	APIUtils apiUtilObj = new APIUtils();
	ConfigFileReader configReaderObject = new ConfigFileReader();
	
	/*Returns the response of https://store.adda247.com/api/v1/ppc/package/exams?packageId=strPackageId
	 * Take User jwt-token and the Mahpack Id as parameter
	 * Package Id should be of package user has purchased and opening for the first time
	 * Used within the Utill
	 */
	public PpcResponseNewPurchased getPackageDetailNewPurchased(String strUserToken, String strPackageId) {
		PpcResponseNewPurchased ppcResponseObj = new PpcResponseNewPurchased();
		APIResponse ap;
		
		List<Header> headerlist = new ArrayList<Header>();
		try {
			
			headerlist.add(new Header("X-Auth-Token", "fpoa43edty5"));
			headerlist.add(new Header("X-JWT-Token", strUserToken));
			Headers headers = new Headers(headerlist);
			
			ap = apiUtilObj.getCall("https://stagingstore.adda247.com", "/api/v1/ppc/package/exams?packageId=" + strPackageId, headers);
			
			if (ap.code != 200) {
				return null;
			}
			
			ppcResponseObj = ap.responseData.as(PpcResponseNewPurchased.class);
			
		} catch (Exception e) {
			System.out.println("getPackageDetail_exception: " + e.getMessage());
			return null;
		} 
		return ppcResponseObj;
	}
	
	/*Returns the response of https://store.adda247.com/api/v1/ppc/package/exams?packageId=strPackageId
	 * Take User jwt-token and the Mahpack Id as parameter
	 * Package Id should be of package user has purchased and already opened
	 * Used within the Utill
	 */
	public PpcResponseAlreadyPurchased getPackageDetailAlreadyPurchased(String strUserToken, String strPackageId) {
		PpcResponseAlreadyPurchased ppcResponseObj = new PpcResponseAlreadyPurchased();
		APIResponse ap;
		List<Header> headerlist = new ArrayList<Header>();
		try {
			
			headerlist.add(new Header("X-Auth-Token", "fpoa43edty5"));
			headerlist.add(new Header("X-JWT-Token", strUserToken));
			Headers headers = new Headers(headerlist);
			
			ap = apiUtilObj.getCall("https://stagingstore.adda247.com", "/api/v1/ppc/package/exams?packageId=" + strPackageId, headers);
			
			ppcResponseObj = ap.responseData.as(PpcResponseAlreadyPurchased.class);
			
		} catch (Exception e) {
			System.out.println("getPackageDetailAlreadyPurchased_exception: " + e.getMessage());
			return null;
		}
		return ppcResponseObj;
	}
	
	public ExamResponse getExamDetailResponse(String strUserToken, String strPackageId, String strExamId){
		ExamResponse examResponseObj = new ExamResponse();
		APIResponse ap;
		List<Header> headerlist = new ArrayList<Header>();
		try {
			
			headerlist.add(new Header("X-Auth-Token", "fpoa43edty5"));
			headerlist.add(new Header("X-JWT-Token", strUserToken));
			Headers headers = new Headers(headerlist);
			
			ap = apiUtilObj.getCall("https://stagingstore.adda247.com", "/api/v1/ppc/package/getPackageFacetsCount?packageId= " + strPackageId +"&exams=" + strExamId, headers);
			
			examResponseObj = ap.getResponse().as(ExamResponse.class);
			
		} catch (Exception e) {
			System.out.println("getExamDetailResponse_exception: " + e.getMessage());
			return null;
		}
		return examResponseObj;
	}
	
	//Returns the list of name of selected Exams for a particular users in a Mahapack
	public List<String> getListOfSelectedExamsAlreadyPurchased(String strUserToken, String strPackgeId){
		List<String> listSelectedExams = new ArrayList<>();
		PpcResponseAlreadyPurchased ppcResponseObj = new PpcResponseAlreadyPurchased();
		DataAlreadyPurchased dataObj = new DataAlreadyPurchased();
		try {
			//user token changed
			ppcResponseObj = getPackageDetailAlreadyPurchased(strUserToken, strPackgeId);
			dataObj = ppcResponseObj.getData();

			for(int i=0; i< dataObj.getSelectedExamList().size(); i++) {
				listSelectedExams.add(dataObj.getSelectedExamList().get(i).getName());
			}
			
		} catch (Exception e) {
			System.out.println("getListOfSelectedExamsAlreadyPurchased_exception: " + e.getMessage());
			return null;
		}
		return listSelectedExams;
	}
	
	//Returns the total child package count of the mahapack already purchased by the user
	public int getChildPackageCountAlreadyPurchased(String strUserToken, String strPackgeId) {
		PpcResponseAlreadyPurchased ppcResponseObj = new PpcResponseAlreadyPurchased();
		DataAlreadyPurchased dataObj = new DataAlreadyPurchased();
		int childPackageCount=0;
		try {
			
			ppcResponseObj = getPackageDetailAlreadyPurchased(strUserToken, strPackgeId);
			dataObj = ppcResponseObj.getData();
			
			childPackageCount = dataObj.getChildPackageCount();
			
		} catch (Exception e) {
			System.out.println("getChildPackageCountAlreadyPurchased_exception: " + e.getMessage());
			return 0;
		}
		return childPackageCount;
	}
	
	
	
	public int getTotalExamCountNewPurchased(String strUserToken, String strPackgeId) {
		PpcResponseNewPurchased ppcResponseObj = new PpcResponseNewPurchased();
		ExamListNewPurchased dataObj = new ExamListNewPurchased();
		int examCount=0;
		try {
			
			ppcResponseObj = getPackageDetailNewPurchased(strUserToken, strPackgeId);
			dataObj = ppcResponseObj.getData();
			
			examCount = dataObj.getExamList().size();
			
		} catch (Exception e) {
			System.out.println("getTotalExamCountNewPurchased_exception: " + e.getMessage());
			return 0;
		}
		return examCount;
	}
	
	public List<ExamDataNewPurchased> getListOfExamDataNewPurchased(String strUserToken, String strPackgeId){
		List<ExamDataNewPurchased> listExamData = new ArrayList<>();
		PpcResponseNewPurchased ppcResponseObj = new PpcResponseNewPurchased();
		ExamListNewPurchased examListObj = new ExamListNewPurchased();
		try {
			
			ppcResponseObj = getPackageDetailNewPurchased(strUserToken, strPackgeId);
			examListObj = ppcResponseObj.getData();
			listExamData = examListObj.getExamList();
			
		} catch (Exception e) {
			System.out.println("getListOfExamDataNewPurchased_exception: " + e.getMessage());
			return null;
		}
		return listExamData;
	}
	
	public List<ExamDataAlreadyPurchased> getListOfSelectedExamDataAlreadyPurchased(String strUserToken, String strPackgeId){
		List<ExamDataAlreadyPurchased> listExamData = new ArrayList<>();
		PpcResponseAlreadyPurchased ppcResponseObj = new PpcResponseAlreadyPurchased();
		DataAlreadyPurchased dataObj = new DataAlreadyPurchased();
		try {
			
			ppcResponseObj = getPackageDetailAlreadyPurchased(strUserToken, strPackgeId);
			dataObj = ppcResponseObj.getData();
			listExamData = dataObj.getSelectedExamList();
			
		} catch (Exception e) {
			System.out.println("getListOfSelectedExamDataAlreadyPurchased_exception: " + e.getMessage());
			return null;
		}
		return listExamData;
	}
	
	public ExamDataAlreadyPurchased getExamWithBatchFlow(String strUserToken, String strPackgeId) {
		ExamDataAlreadyPurchased examDataObj = new ExamDataAlreadyPurchased();
		List<ExamDataAlreadyPurchased> listExamData = new ArrayList<>();
		ExamResponse examResponseObj = new ExamResponse();
		int examId;
		try {
			System.out.println("Hello");
			listExamData = getListOfSelectedExamDataAlreadyPurchased(strUserToken, strPackgeId);
			
			for(int i=0; i<listExamData.size(); i++) {
				examId = listExamData.get(i).getId();
				String strExamId = String.valueOf(examId);
				examResponseObj = getExamDetailResponse(strUserToken, strPackgeId, strExamId);
				System.out.println(examResponseObj.getData().getSubParentCount());
				if(examResponseObj.getData().getSubParentCount()!=0) {
					return listExamData.get(i);
				}
			}
			
		} catch (Exception e) {
			System.out.println("getExamWithBatchFlow_exception: " + e.getMessage());
			return null;
		}
		return examDataObj;
	}
	
	public int getExamSubParentCount(String strUserToken, String strPackgeId, int examId) {
		ExamResponse examResponseObj = new ExamResponse();
		int subParentCount=0;
		try {
			String strExamId = String.valueOf(examId);
			examResponseObj = getExamDetailResponse(strUserToken, strPackgeId, strExamId);
			subParentCount = examResponseObj.getData().getSubParentCount();
			
		} catch (Exception e) {
			System.out.println("getListOfExamDataNewPurchased_exception: " + e.getMessage());
			return 0;
		}
		return subParentCount;
	}
	
	
}
