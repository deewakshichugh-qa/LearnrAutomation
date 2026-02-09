
package pojo.createCertificateResponse;

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
    "packageId",
    "certificateId",
    "campaignId"
})
@Generated("jsonschema2pojo")
public class Data {

    @JsonProperty("packageId")
    private String packageId;
    @JsonProperty("certificateId")
    private String certificateId;
    @JsonProperty("campaignId")
    private String campaignId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("packageId")
    public String getPackageId() {
        return packageId;
    }

    @JsonProperty("packageId")
    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    @JsonProperty("certificateId")
    public String getCertificateId() {
        return certificateId;
    }

    @JsonProperty("certificateId")
    public void setCertificateId(String certificateId) {
        this.certificateId = certificateId;
    }

    @JsonProperty("campaignId")
    public String getCampaignId() {
        return campaignId;
    }

    @JsonProperty("campaignId")
    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
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
