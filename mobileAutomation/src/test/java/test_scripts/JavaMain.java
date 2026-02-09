package test_scripts;

import apiUtill.CertificateUtil;
import apiUtill.CreatePackageUtil;
import apiUtill.PackageApiUtil;
import pojo.createCertificateResponse.CreateCertificateResponse;
import pojo.createPackageResponse.CreatePackageResponse;
import pojo.getSignedUrl.GetSignedUrl;
import pojo.getVideoList.GetVideoList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JavaMain {

	public static void main(String[] args) {
		
		
		//CreatePackageUtil ps = new CreatePackageUtil();
		//List<Integer> categorylist = new ArrayList<Integer>();
		//categorylist.add(6);									// For Live Class category
//		categorylist.add(5);									// For Video category
//		categorylist.add(4);									// For Ebooks category
//		categorylist.add(2);									// For Books category
//		categorylist.add(1);									// For Test Series category
		// ps.createPackage(true,false,categorylist,0);	
		
		
		// TODO Auto-generated method stub


		
				CreatePackageUtil cr = new CreatePackageUtil();
				List<Integer> categorylist = new ArrayList<Integer>();
				Map<String, Integer> conetntList = new HashMap<>();
				PackageApiUtil pu = new PackageApiUtil();
				GetSignedUrl getSignedUrlObj;
				categorylist.add(5);
				String strToken = "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTMxLCJlbWFpbCI6ImFrYXNoLmthbm9qaWFAYWRkYTI0Ny5jb20iLCJuYW1lIjoiQWthc2giLCJhbGxvd2VkU2VjdGlvbnMiOiJBY3Rpb25zLEFkZCBuZXcgdXJsLEFkZFBhY2thZ2UsYnVsa1B1Ymxpc2hVbnB1Ymxpc2gsQ29udGVudEFkbWluVmlldyxDb3Vwb25BY3Rpb25zLENvdXBvbkRlYWN0aXZhdGUsQ291cG9uRGVsZXRlLENvdXBvbkVkaXQsQ291cG9uVmlldyxDcmVhdGUgSW5zdGl0dXRlICxDcmVhdGUgbmV3IGNvdXBvbixDcmVhdGUgbmV3IGdyb3VwLENyZWF0ZSBuZXcgcGVybWlzc2lvbnMsQ3JlYXRlIE5ldyBQb3N0LENyZWF0ZSBuZXcgcm9sZSxDcmVhdGUgTmV3IFVzZXIsQ3JlYXRlU29jaWFsR3JvdXAsRGFzaGJvYXJkVmlldyxEZWxldGUgcm9sZSxEZWxldGUgdXNlcnMsRGVsZXRlU29jaWFsR3JvdXAsRG91YnQgUm9vbSxEdXBsaWNhdGVQYWNrYWdlLEVkaXQgdXNlcnMsRWRpdFBhY2thZ2UsRWRpdFBvc3QsRWRpdFNvY2lhbEdyb3VwLEZhY3VsdHksZmFjdWx0eWFkbWluLEZlZWRDdXN0b21pc2F0aW9uLEZpeGVkUGVyaW9kTW9jayxHbG9iYWxDb25maWcsR3JvdXBEZWxldGUsR3JvdXBFZGl0LEdyb3VwcyxJbnN0aXR1dGVzLEpvaW5fQXNfVGVhY2hlcixMY3NWaWV3LExpZ2h0aG91c2VBY3Rpb25zLExpZ2h0aG91c2VWaWV3LExpbmtDaGlsZFBhY2thZ2UsTGlua1BhcmVudFBhY2thZ2UsTGl2ZUNsYXNzLFBhY2thZ2VBY3Rpb24sUGFja2FnZUlPU1ByaWNlVXBsb2FkLHBhY2thZ2VVcGdyYWRlLFBhY2thZ2VWaWRlb1VwbG9hZCxQYWNrYWdlVmlldyxQQ1IgYWRtaW4sUERQRnJlZUNvbnRlbnQsUGVybWlzc2lvbkFjdGlvbnMsUGVybWlzc2lvbkRlbGV0ZSxQZXJtaXNzaW9uRWRpdCxQZXJtaXNzaW9uc1ZpZXcsUHJvbW9Db2RlcyxSb2xlQWN0aW9ucyxSb2xlRGVsZXRlLFJvbGVFZGl0LFJvbGVzVmlldyxTRU8sU2VvIEVkaXRvcixTZXRPckNoYW5nZVBhc3N3b3JkLFNraWxsQ2VydGlmaWNhdGlvbixTb2NpYWxHcm91cEFjdGlvbnMsU29jaWFsVXNlcnNWaWV3LFNvY2lhbF9Hcm91cHMsU3BvbnNvcmVkTGlzdCxTVE9QV09SRFMsU3RvcmVmcm9udEFkbWluLFN1cGVyQWRtaW5fVGVzdCx0ZXN0aW5nYWRtaW4sVXNlcnNWIiwicm9sZSI6IlN1cGVyQWRtaW4iLCJpYXQiOjE2ODg0NTc2NzZ9.oFwXxDcVpILFdF4Y-TZPnmCOqdCRsztXwFiQ6StLIXBTgsCQmIdWudNLCXlfUvATQS-RZcViH52HqziKOYJ5Yw";
				
				CreatePackageResponse cpr= cr.createPackage(false, false, categorylist, 2);
				System.out.println("strPackageId: "+cpr.getData().getId());
			GetVideoList getVideoList =	pu.videoLinking(String.valueOf(cpr.getData().getId()));
				
				
			
			for(int i=0;i<getVideoList.getData().getInputVideoStreamingBean().getVideoStreamingBean().getBilingual().getCc().getSubject().get(0).getCh().get(0).getVideo().size();i++)
			{
				conetntList.put(getVideoList.getData().getInputVideoStreamingBean().getVideoStreamingBean().getBilingual().getCc().getSubject().get(0).getCh().get(0).getVideo().get(i).getUrl(),(i+1));
				
			}
			/*	for(int i=0;i<1;i++)
				{
					mcq.createCutOffMockTest();
					String strId = MockTestApiUtil.strFixedMockTestId;
					conetntList.add(Integer.parseInt(strId));
					pu.assignTestSeriesToPackage(1, Integer.parseInt(strId), cpr.getData().getId());
					
				}*/
				
				CertificateUtil cfUtilObj = new CertificateUtil();
				
				getSignedUrlObj = cfUtilObj.getSignedUrl(strToken, "certificate1.html", cpr.getData().getId());
				if(getSignedUrlObj == null)
				{
					System.out.println("error in get signed url");
				}
				
				cfUtilObj.uploadfile(getSignedUrlObj.getData().getSignedUrl(), "certificate1.html",String.valueOf(cpr.getData().getId()));
				
				CreateCertificateResponse createCertificateResponseObj =	cfUtilObj.createCertificate(strToken, String.valueOf(cpr.getData().getId()), 2, 2,conetntList,getSignedUrlObj.getData().getTempS3Url());
				cfUtilObj.activeCertificate(strToken, String.valueOf(cpr.getData().getId()));
			cfUtilObj.aprroveCertificate(strToken, String.valueOf(cpr.getData().getId()), createCertificateResponseObj.getData().getCertificateId());
		
	}

}
