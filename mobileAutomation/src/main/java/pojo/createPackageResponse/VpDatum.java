
package pojo.createPackageResponse;


import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "packageId",
    "dI",
    "mO",
    "sP",
    "mP",
    "lG",
    "pB",
    "pM",
    "dV",
    "iosPrice",
    "iosAvailable",
    "rc",
    "onSale",
    "fCF",
    "cFData"
})
@Generated("jsonschema2pojo")
public class VpDatum {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("packageId")
    private Integer packageId;
    @JsonProperty("dI")
    private Object dI;
    @JsonProperty("mO")
    private Integer mO;
    @JsonProperty("sP")
    private Integer sP;
    @JsonProperty("mP")
    private Integer mP;
    @JsonProperty("lG")
    private String lG;
    @JsonProperty("pB")
    private Boolean pB;
    @JsonProperty("pM")
    private Boolean pM;
    @JsonProperty("dV")
    private Boolean dV;
    @JsonProperty("iosPrice")
    private Integer iosPrice;
    @JsonProperty("iosAvailable")
    private Boolean iosAvailable;
    @JsonProperty("rc")
    private Boolean rc;
    @JsonProperty("onSale")
    private Boolean onSale;
    @JsonProperty("fCF")
    private Boolean fCF;
    @JsonProperty("cFData")
    private Object cFData;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("packageId")
    public Integer getPackageId() {
        return packageId;
    }

    @JsonProperty("packageId")
    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    @JsonProperty("dI")
    public Object getdI() {
        return dI;
    }

    @JsonProperty("dI")
    public void setdI(Object dI) {
        this.dI = dI;
    }

    @JsonProperty("mO")
    public Integer getmO() {
        return mO;
    }

    @JsonProperty("mO")
    public void setmO(Integer mO) {
        this.mO = mO;
    }

    @JsonProperty("sP")
    public Integer getsP() {
        return sP;
    }

    @JsonProperty("sP")
    public void setsP(Integer sP) {
        this.sP = sP;
    }

    @JsonProperty("mP")
    public Integer getmP() {
        return mP;
    }

    @JsonProperty("mP")
    public void setmP(Integer mP) {
        this.mP = mP;
    }

    @JsonProperty("lG")
    public String getlG() {
        return lG;
    }

    @JsonProperty("lG")
    public void setlG(String lG) {
        this.lG = lG;
    }

    @JsonProperty("pB")
    public Boolean getpB() {
        return pB;
    }

    @JsonProperty("pB")
    public void setpB(Boolean pB) {
        this.pB = pB;
    }

    @JsonProperty("pM")
    public Boolean getpM() {
        return pM;
    }

    @JsonProperty("pM")
    public void setpM(Boolean pM) {
        this.pM = pM;
    }

    @JsonProperty("dV")
    public Boolean getdV() {
        return dV;
    }

    @JsonProperty("dV")
    public void setdV(Boolean dV) {
        this.dV = dV;
    }

    @JsonProperty("iosPrice")
    public Integer getIosPrice() {
        return iosPrice;
    }

    @JsonProperty("iosPrice")
    public void setIosPrice(Integer iosPrice) {
        this.iosPrice = iosPrice;
    }

    @JsonProperty("iosAvailable")
    public Boolean getIosAvailable() {
        return iosAvailable;
    }

    @JsonProperty("iosAvailable")
    public void setIosAvailable(Boolean iosAvailable) {
        this.iosAvailable = iosAvailable;
    }

    @JsonProperty("rc")
    public Boolean getRc() {
        return rc;
    }

    @JsonProperty("rc")
    public void setRc(Boolean rc) {
        this.rc = rc;
    }

    @JsonProperty("onSale")
    public Boolean getOnSale() {
        return onSale;
    }

    @JsonProperty("onSale")
    public void setOnSale(Boolean onSale) {
        this.onSale = onSale;
    }

    @JsonProperty("fCF")
    public Boolean getfCF() {
        return fCF;
    }

    @JsonProperty("fCF")
    public void setfCF(Boolean fCF) {
        this.fCF = fCF;
    }

    @JsonProperty("cFData")
    public Object getcFData() {
        return cFData;
    }

    @JsonProperty("cFData")
    public void setcFData(Object cFData) {
        this.cFData = cFData;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
