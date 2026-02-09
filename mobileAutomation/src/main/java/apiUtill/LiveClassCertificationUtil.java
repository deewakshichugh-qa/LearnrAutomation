package apiUtill;


public class LiveClassCertificationUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		CertificateUtil cr = new CertificateUtil();
		UserApiUtil us = new UserApiUtil();
		cr.verifyLiveClassCertification(us.adminLogin("abhay.rai@adda247.com", "0002@aada!").getFacultyToken());
		
		
	}
}
