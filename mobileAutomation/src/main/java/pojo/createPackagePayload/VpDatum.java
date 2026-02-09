
package pojo.createPackagePayload;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class VpDatum {

	@SerializedName("id")
	@Expose
	private Integer id;
	@SerializedName("packageId")
	@Expose
	private Integer packageId;
	@SerializedName("dI")
	@Expose
	private Object dI;
	@SerializedName("mO")
	@Expose
	private Integer mO;
	@SerializedName("sP")
	@Expose
	private Integer sP;
	@SerializedName("mP")
	@Expose
	private Integer mP;
	@SerializedName("lG")
	@Expose
	private String lG;
	@SerializedName("pB")
	@Expose
	private Boolean pB;
	@SerializedName("pM")
	@Expose
	private Boolean pM;
	@SerializedName("iosAvailable")
	@Expose
	private Boolean iosAvailable;
	@SerializedName("iosPrice")
	@Expose
	private Object iosPrice;
	@SerializedName("onSale")
	@Expose
	private Boolean onSale;
	@SerializedName("fCF")
	@Expose
	private Boolean fCF;
	@SerializedName("cFData")
	@Expose
	private CFData__1 cFData;
	@SerializedName("rc")
	@Expose
	private Boolean rc;
	@SerializedName("dV")
	@Expose
	private Boolean dV;
	@SerializedName("paymentType")
	@Expose
	private String paymentType;
	@SerializedName("validity")
	@Expose
	private Integer validity;

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Integer getValidity() {
		return validity;
	}

	public void setValidity(Integer validity) {
		this.validity = validity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public Object getdI() {
		return dI;
	}

	public void setdI(Object dI) {
		this.dI = dI;
	}

	public Integer getmO() {
		return mO;
	}

	public void setmO(Integer mO) {
		this.mO = mO;
	}

	public Integer getsP() {
		return sP;
	}

	public void setsP(Integer sP) {
		this.sP = sP;
	}

	public Integer getmP() {
		return mP;
	}

	public void setmP(Integer mP) {
		this.mP = mP;
	}

	public String getlG() {
		return lG;
	}

	public void setlG(String lG) {
		this.lG = lG;
	}

	public Boolean getpB() {
		return pB;
	}

	public void setpB(Boolean pB) {
		this.pB = pB;
	}

	public Boolean getpM() {
		return pM;
	}

	public void setpM(Boolean pM) {
		this.pM = pM;
	}

	public Boolean getIosAvailable() {
		return iosAvailable;
	}

	public void setIosAvailable(Boolean iosAvailable) {
		this.iosAvailable = iosAvailable;
	}

	public Object getIosPrice() {
		return iosPrice;
	}

	public void setIosPrice(Object iosPrice) {
		this.iosPrice = iosPrice;
	}

	public Boolean getOnSale() {
		return onSale;
	}

	public void setOnSale(Boolean onSale) {
		this.onSale = onSale;
	}

	public Boolean getfCF() {
		return fCF;
	}

	public void setfCF(Boolean fCF) {
		this.fCF = fCF;
	}

	public CFData__1 getcFData() {
		return cFData;
	}

	public void setcFData(CFData__1 cFData) {
		this.cFData = cFData;
	}

	public Boolean getRc() {
		return rc;
	}

	public void setRc(Boolean rc) {
		this.rc = rc;
	}

	public Boolean getdV() {
		return dV;
	}

	public void setdV(Boolean dV) {
		this.dV = dV;
	}

}
