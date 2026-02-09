package test_scripts;

import apiUtill.MockTestApiUtil;
import apiUtill.PpcUtil;
import apiUtill.UserLoginUtil;
import applicationUtil.LoginUtil;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.PackageDetailsAlreadyPurchased.DataAlreadyPurchased;
import pojo.PackageDetailsAlreadyPurchased.ExamDataAlreadyPurchased;
import pojo.PackageDetailsAlreadyPurchased.PpcResponseAlreadyPurchased;
import pojo.PackageDetailsNewPurchased.ExamDataNewPurchased;
import pojo.PackageDetailsNewPurchased.ExamListNewPurchased;
import pojo.PackageDetailsNewPurchased.PpcResponseNewPurchased;
import util.Common_Function;

public class test extends BaseTest {
	
	MockTestApiUtil mockTestObj;
	
	@Test
	public void testing() throws InterruptedException {
		
		Thread.sleep(2000);
		
		((AndroidDriver<MobileElement>) getDriver()).toggleAirplaneMode();
		
		Thread.sleep(5000);
	}
	
	@Test
	public void ppcTestNewUser() {
		boolean result = true;

		PpcUtil ppcPackageUtilObjDetailsUtil = new PpcUtil();
		PpcResponseNewPurchased ppcResponseObj = new PpcResponseNewPurchased();
		ExamListNewPurchased examListObj = new ExamListNewPurchased();
		ExamDataNewPurchased examDataObj = new ExamDataNewPurchased();
		
		ppcResponseObj = ppcPackageUtilObjDetailsUtil.getPackageDetailNewPurchased("","4498");
		if(ppcResponseObj == null) {
			result = false;
		}
		
		examListObj = ppcResponseObj.getData();
		
		for(int i=0; i<examListObj.getExamList().size(); i++) {
			
			examDataObj = examListObj.getExamList().get(i);
			System.out.println(examDataObj.getName() + ", ");
		}
		
		
		Assert.assertEquals(result, true);
	}
	
	@Test
	public void ppcTestGuestUser() {
		boolean result = true;

		PpcUtil ppcPackageUtilObjDetailsUtil = new PpcUtil();
		PpcResponseAlreadyPurchased ppcResponseObj = new PpcResponseAlreadyPurchased();
		DataAlreadyPurchased dataObj = new DataAlreadyPurchased();
		ExamDataAlreadyPurchased examDataObj = new ExamDataAlreadyPurchased();
		
		ppcResponseObj = ppcPackageUtilObjDetailsUtil.getPackageDetailAlreadyPurchased("", "4658");
		if(ppcResponseObj == null) {
			result = false;
		}
		
		dataObj = ppcResponseObj.getData();
		
		System.out.println(dataObj.getChildPackageCount() + "<--------------");
		
		for(int i=0; i<dataObj.getSelectedExamList().size(); i++) {
			
			examDataObj = dataObj.getSelectedExamList().get(i);
			System.out.println(examDataObj.getName() + ", ");
		}
		
		Assert.assertEquals(result, true);
	}
	
	@Test
	public void loginTest() {
		String randomNubmer = Common_Function.randomPhoneNumber(10, "8");
		
		LoginUtil loginObj = new LoginUtil(getDriver());
		loginObj.verifySignUp(getDriver(), randomNubmer, true);

		UserLoginUtil userLoginObj = new UserLoginUtil();
		String usrEmail = "addaAutomation" + randomNubmer + "@gmail.com";
		String userToken = null;
		
		userToken = userLoginObj.getUserToken(usrEmail, "");
		if(userToken==null) {
			System.out.println("User Token is null");
		}
		
	}
	
//	@Test
//	public void myContentTest() {
//		Response responseObj = new Response();
////		String randomNubmer = Common_Function.randomPhoneNumber(10, "8");
////		
////		NewLoginUtil loginObj = new NewLoginUtil(getDriver());
////		loginObj.verifySignUp(getDriver(), randomNubmer, true);
//
//		MyContentSearchApiUtil myContentSearchApiUtillObj = new MyContentSearchApiUtil();
//		
//		String userToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aWJob3JhZGRhdGVzdEBnbWFpbC5jb20iLCJhdWQiOiIxMTYxNjI2OSIsImlhdCI6MTY4NjY2MTkyMywiaXNzIjoiYWRkYTI0Ny5jb20iLCJuYW1lIjoiVmliaG9yIFRlc3QifQ.qo_ivHzoykgu9MPivRiuk1ZXDjja1YyapRwf01Yz0PB_Oqe1QeHMZisn-GQb6wCeYj5wNw-coaNyaCWJPbIF1g";
//		
//		responseObj = myContentSearchApiUtillObj.getPopuparCourseResponse(userToken);
//		if(responseObj==null) {
//			System.out.println(">> responseDataObj is null");
//		}
//	}
//		
//		@Test
//		public void myContentSearchTest() {
//			SearchResponse responseObj = new SearchResponse();
////			String randomNubmer = Common_Function.randomPhoneNumber(10, "8");
////			
////			NewLoginUtil loginObj = new NewLoginUtil(getDriver());
////			loginObj.verifySignUp(getDriver(), randomNubmer, true);
//
//			MyContentSearchApiUtil myContentSearchApiUtillObj = new MyContentSearchApiUtil();
//			
//			String userToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2aWJob3JhZGRhdGVzdEBnbWFpbC5jb20iLCJhdWQiOiIxMTYxNjI2OSIsImlhdCI6MTY4NjY2MTkyMywiaXNzIjoiYWRkYTI0Ny5jb20iLCJuYW1lIjoiVmliaG9yIFRlc3QifQ.qo_ivHzoykgu9MPivRiuk1ZXDjja1YyapRwf01Yz0PB_Oqe1QeHMZisn-GQb6wCeYj5wNw-coaNyaCWJPbIF1g";
//			
//			responseObj = myContentSearchApiUtillObj.getMyContentSearchResult(userToken, "bank");
//			if(responseObj==null) {
//				System.out.println(">> responseDataObj is null");
//			}
//		
//	}
	
}
