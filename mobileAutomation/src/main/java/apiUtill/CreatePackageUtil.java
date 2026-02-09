package apiUtill;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Header;
import io.restassured.http.Headers;
//import pojo.createPackagePayload.CFData;
//import pojo.createPackagePayload.CFData__1;
import pojo.createPackagePayload.CreatePackage;
//import pojo.createPackagePayload.Faculty;
import pojo.createPackagePayload.PackageDescription;
import pojo.createPackagePayload.VpDatum;
import pojo.createPackageResponse.CreatePackageResponse;
import util.APIResponse;
import util.APIUtils;
import util.Common_Function;
import util.ConfigFileReader;

public class CreatePackageUtil {

	APIUtils apiUtilObj = new APIUtils();
	ConfigFileReader configReaderObject = new ConfigFileReader();
	String PID;
	public List<String> createPackageMsgList = new ArrayList<>();

	public CreatePackageResponse createPackage(boolean IsMahapack, boolean IsChild, List<Integer> Category,
			int VariantCount) {

		APIResponse ap;
		CreatePackageResponse createresponseobj = null;
		ObjectMapper Obj;
		CreatePackage packageCreation = new CreatePackage();
		PackageDescription packagedescription = new PackageDescription();
		VpDatum vpdata = new VpDatum();
		List<Header> headerlist = new ArrayList<Header>();
		UserApiUtil us;

		try {
			us = new UserApiUtil();
			String strToken = us.adminLogin("abhay.rai@adda247.com", "0002@aada!").getFacultyToken();
			headerlist.add(new Header("X-Jwt-Token", strToken));
			headerlist.add(new Header("X-Auth-Token", "fpoa43edty5"));
			Headers headers = new Headers(headerlist);

			long epochTime = Common_Function.getCurrentTimePlusTenMinutes();

			List<Integer> courseArray = new ArrayList<Integer>();
			if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
				courseArray.add(2010);
			} else {
				courseArray.add(3);
			}
			packageCreation.setCourseIds(courseArray);

			List<Integer> tabArray = new ArrayList<Integer>();
			tabArray.add(4);
			packageCreation.setTabIds(tabArray);

			packageCreation.setCategoryIds(Category);

			packagedescription.setOverview("<p>Automation Test Package</p>");
			packageCreation.setPackageDescription(packagedescription);

			packageCreation.setImageUrl("https://d8cfumlfy8bcr.cloudfront.net/image201685430710.png");

			// For Live Class Related Fields
			if (Category.contains(6)) {
				packageCreation.setOlcAccountId(10);
				packageCreation.setOlcSource("AddaLive");
				packageCreation.setBatchStartDate(Common_Function.getCurrentate("yyyy-MM-dd"));
				packageCreation.setBatchEndDate(Common_Function.getCurrentate("yyyy-MM-dd"));
			}

			List<VpDatum> vpdataset = new ArrayList<VpDatum>();

			// For Mahapack specific fields
			if (IsMahapack) {
				packageCreation.setTitle("Automation Package Mahapack " + epochTime);
				packageCreation.setSlugTitle("Automation Package Mahapack " + epochTime);
				packageCreation.setMahaPack(true);
				packageCreation.setHlsJson(
						"[{\"hlsJson\":\"MH Highlight1\"},{\"hlsJson\":\"MH Highlight2\"},{\"hlsJson\":\"MH Highlight3\"}]");
				vpdata.setpB(true);
				packageCreation.setPublished(true);
			}
			// For Parent specific fields
			else if (!IsChild) {
				packageCreation.setTitle("Automation Package Parent " + epochTime);
				packageCreation.setSlugTitle("Automation Package Parent " + epochTime);
				packageCreation.setMahaPack(false);
				packageCreation.setHlsJson(
						"[{\"hlsJson\":\"Parent Highlight1\"},{\"hlsJson\":\"Parent Highlight2\"},{\"hlsJson\":\"Parent Highlight3\"}]");
				vpdata.setpB(true);
				packageCreation.setPublished(true);
			}
			// For Child specific fields
			else {
				packageCreation.setTitle("Automation Package Child " + epochTime);
				packageCreation.setSlugTitle("Automation Package Child " + epochTime);
				packageCreation.setMahaPack(false);
				packageCreation.setHlsJson(
						"[{\"hlsJson\":\"Child Highlight1\"},{\"hlsJson\":\"Child Highlight2\"},{\"hlsJson\":\"Child Highlight3\"}]");
				vpdata.setpB(false);
				packageCreation.setPublished(false);
			}

			// For Setting Primary vpData
			vpdata.setmO(12);
			vpdata.setmP(1200);
			vpdata.setsP(900);
			vpdata.setlG("ENGLISH");
			vpdata.setIosAvailable(true);
			vpdata.setIosPrice(900);
			vpdata.setpM(true);
			vpdata.setValidity(1);
			vpdata.setPaymentType("FULL_PAYMENT");

			vpdataset.add(vpdata);

			// For Multi validity packages Setting Multi-variant other than Primary one
			if (VariantCount > 0 && (IsMahapack || !IsChild)) {
				for (int j = 1; j < VariantCount; j++) {
					VpDatum vpdatanew = new VpDatum();
					int randommonth = 6 / j;
					int randomprice = 600 / j;
					int randomiosprice = 400 / j;

					vpdatanew.setmO(randommonth);
					vpdatanew.setmP(900);
					vpdatanew.setsP(randomprice);
					vpdatanew.setlG("ENGLISH");
					vpdatanew.setIosAvailable(true);
					vpdatanew.setIosPrice(randomiosprice);
					vpdatanew.setpM(false);
					vpdatanew.setpB(true);
					vpdatanew.setValidity(2);
					vpdatanew.setPaymentType("FULL_PAYMENT");
					vpdataset.add(vpdatanew);
				}
			}

			packageCreation.setVpData(vpdataset);

			Obj = new ObjectMapper();
			String jsonStr = Obj.writeValueAsString(packageCreation);

			ap = apiUtilObj.postCall(configReaderObject.getStoreAdminUrl(), "api/v1/packages", jsonStr, headers);
			if (ap.code != 201) {
				createPackageMsgList.add("Issue in api/v1/packages");
				return null;
			}

			System.out.println(ap.responseData);
			createresponseobj = ap.responseData.as(CreatePackageResponse.class);

		} catch (Exception e) {
			createPackageMsgList.add("createPackage_Exception: "+ e.getMessage());
		}
		return createresponseobj;

	}

}
