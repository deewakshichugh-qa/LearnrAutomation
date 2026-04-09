package apiUtill;

import util.APIResponse;
import util.APIUtils;
import util.ConfigFileReader;
import util.Constant;

public class OtpUtil {

    public ConfigFileReader cfReaderObject = new ConfigFileReader();
    public APIUtils apiUtilsObj = new APIUtils();

    public String getOtp(String strMobileNumberUserId) {
        String strOtp = null;
        APIResponse ap = null;
        String strBasicAuth = "Basic YWRtaW46YWRkYWFkbWluQDEyMzQ1";
        String jwtToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZWNoX3RlYW0ifQ.10FLppEgttwjiUKWrynQG5lLsh6e1vUJYau9-G7NuHxCWno3D3X0k2l8jQXyb8M_x_dtyp5MxIT4d5cqdLuq_w";
        try {
            ap = apiUtilsObj.getCallwithBasicAuth("https://staginguserapi.adda247.com",
                    Constant.USER_OTP_EMAIL_PHONE + strMobileNumberUserId + "&type=Phone", "admin", "addaadmin@12345", strBasicAuth, jwtToken);

            System.out.println(ap.getmessageCode());
            strOtp = ap.getmessageCode();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return strOtp;
    }
}

