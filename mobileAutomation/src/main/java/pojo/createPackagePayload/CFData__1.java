
package pojo.createPackagePayload;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class CFData__1 {

    @SerializedName("mrp")
    @Expose
    private String mrp;
    @SerializedName("sp")
    @Expose
    private String sp;

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getSp() {
        return sp;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }

}
