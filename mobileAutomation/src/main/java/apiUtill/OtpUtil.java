package apiUtill;

import util.APIResponse;
import util.APIUtils;
import util.ConfigFileReader;
import util.Constant;

import static util.ConfigFileReader.strUserPassword;

public class OtpUtil {

    public ConfigFileReader cfReaderObject = new ConfigFileReader();
    public APIUtils apiUtilsObj = new APIUtils();


    public String getOtp(String strMobileNumberUserId) {
        String strOtp = null;
        APIResponse ap = null;
        String strBasicAuth;
        String jwtToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZWNoX3RlYW0ifQ.10FLppEgttwjiUKWrynQG5lLsh6e1vUJYau9-G7NuHxCWno3D3X0k2l8jQXyb8M_x_dtyp5MxIT4d5cqdLuq_w";
        try {

            if (ConfigFileReader.strApplication.equalsIgnoreCase("Learnr")){
                strBasicAuth = "Basic YWRtaW46YWRkYWFkbWluQDEyMzQ1";
            } else {
                strBasicAuth = "Basic YWRtaW46c2Fua2FscEAxMjM0NQ==";
            }
            ap = apiUtilsObj.getCallwithBasicAuth("https://staginguserapi.adda247.com",
                    Constant.USER_OTP_EMAIL_PHONE + strMobileNumberUserId+ "&type=Phone", "admin", "addaadmin@12345",strBasicAuth,jwtToken);

            System.out.println(ap.getmessageCode());

            strOtp = ap.getmessageCode();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return strOtp;

    }

//    public String getOtpAdminEmailPhone(String strMobileNumberUserId, String strType) {
//            String strOtp = null, strBasicAuth;
//            APIResponse ap = null;
//            String strUserName, strUserPassword;
//            try {
//                Thread.sleep(1000);
//                if (ConfigFileReader.strApplication.equalsIgnoreCase("Learnr")) {
//                    strUserName = "admin";
//                    strUserPassword = "addaadmin@12345";
//                    strBasicAuth = "Basic YWRtaW46YWRkYWFkbWluQDEyMzQ1";
//                } else {
//                    strUserName = "admin";
//                    strUserPassword = "sankalp@12345";
//                    strBasicAuth = "Basic YWRtaW46c2Fua2FscEAxMjM0NQ==";
//                }
//
//                ap = apiUtilsObj.getCallwithBasicAuth("http://staginguserapi.adda247.com",
//                        Constant.USER_OTP_EMAIL_PHONE + strMobileNumberUserId + "&type=" + strType, strUserName,
//                        strUserPassword, strBasicAuth);
//
//                System.out.println(ap.getmessageCode());
//
//                strOtp = ap.getmessageCode();
//
//                if (strOtp.contains("No")) {
//                    if (ConfigFileReader.strApplication.equalsIgnoreCase("Learnr")) {
//                        strUserName = "admin";
//                        strUserPassword = "addaadmin@12345";
//                        strBasicAuth = "Basic YWRtaW46YWRkYWFkbWluQDEyMzQ1";
//                    } else {
//                        strUserName = "admin";
//                        strUserPassword = "sankalp@12345";
//                        strBasicAuth = "Basic YWRtaW46c2Fua2FscEAxMjM0NQ==";
//                    }
//
//                    ap = apiUtilsObj.getCallwithBasicAuth("http://staginguserapi.adda247.com",
//                            Constant.USER_OTP_EMAIL_PHONE + strMobileNumberUserId + "&type=" + strType, strUserName,
//                            strUserPassword, strBasicAuth);
//
//                    System.out.println(ap.getmessageCode());
//
//                    strOtp = ap.getmessageCode();
//                }
//
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//            }
//            return strOtp;
//
//        }
    }

