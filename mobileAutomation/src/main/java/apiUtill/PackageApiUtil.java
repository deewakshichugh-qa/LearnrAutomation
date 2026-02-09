package apiUtill;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import pojo.adminLogin.AdminLogin;
import pojo.assignProduct.AssignProduct;
import pojo.assignProduct.PackageIdsWithGroupName;
import pojo.getVideoList.GetVideoList;
import util.APIResponse;
import util.APIUtils;
import util.Common_Function;
import util.ConfigFileReader;

public class PackageApiUtil {
    APIUtils apiUtilObj;
    APIResponse ap;
    JSONObject requestParams;
    Common_Function cfObj = new Common_Function();
    public ConfigFileReader cfRederObj = new ConfigFileReader();
    public List<String> packagetApiMsgList = new ArrayList<String>();


    public boolean assignTestSeriesToPackage(int typeId, int testSeriesId, int pacakgeId) {
        boolean result = true;
        ObjectMapper obj;
        List<Header> listHeader = new ArrayList<Header>();
        try {
            listHeader.add(new Header("X-JWT-Token", cfRederObj.getAdminMiniUIToken()));

            listHeader.add(new Header("content-type", "application/json"));
            listHeader.add(new Header("X-Auth-Token", "fpoa43edty5"));
            Headers headers = new Headers(listHeader);
            AssignProduct apobj = new AssignProduct();
            apobj.setCategoryId(typeId);
            apobj.setChildId(testSeriesId);
            List<PackageIdsWithGroupName> list = new ArrayList<PackageIdsWithGroupName>();
            PackageIdsWithGroupName pg = new PackageIdsWithGroupName();
            pg.setGroupName("Automation");
            pg.setId(pacakgeId);
            list.add(pg);
            apobj.setPackageIdsWithGroupName(list);
            apiUtilObj = new APIUtils();
            obj = new ObjectMapper();
            String jsonmStr = obj.writeValueAsString(apobj);
            ap = apiUtilObj.postCallMultipleHeader(cfRederObj.getAdminMiniUIUrlStore(), "/api/v1/packages/assign", jsonmStr, headers);
            if (ap.code != 201) {
                packagetApiMsgList.add("Issue in assigning");
                return false;
            }

        } catch (Exception e) {
            result = false;
            packagetApiMsgList.add("assignTestSeriesToPackage_Exception: " + e.getMessage());
        }
        return result;
    }

    public GetVideoList videoLinking(String childId) {

        List<Header> headerlist = new ArrayList<Header>();
        UserApiUtil useApiObj;
        GetVideoList getVideoList = null;
        String FilePath;

        try {
            useApiObj = new UserApiUtil();
            String strToken = useApiObj.adminLogin("abhay.rai@adda247.com", "0002@aada!").getFacultyToken().toString();
            APIResponse ap;
            headerlist.add(new Header("X-Jwt-Token", strToken));
            headerlist.add(new Header("X-Auth-Token", "fpoa43edty5"));
            Headers headers = new Headers(headerlist);
            if (ConfigFileReader.strApplication.equalsIgnoreCase("Adda")) {
                FilePath = "src/main/resources/video_course_package1.docx";
            } else {
                FilePath = "src/main/resources/STAGING_SankalpVideo1.docx";
            }

            apiUtilObj = new APIUtils();
            ap = apiUtilObj.getCallwithfile(cfRederObj.getStoreAdminUrl(), "api/v1/excel/upload/normal/video/pkg/" + childId, FilePath, headers);
            if (ap.code != 200) {
                return null;
            }
            getVideoList = ap.responseData.as(GetVideoList.class);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return getVideoList;
    }

}


