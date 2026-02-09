
package pojo.createCertificatePayload;

import java.util.LinkedHashMap;
import java.util.List;
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
    "packageTitle",
    "packageImgUrl",
    "certificationType",
    "userVariables",
    "templateLink",
    "eventType",
    "contentOrderIds",
    "isCertificateIdIncluded"	
})
@Generated("jsonschema2pojo")
public class CreateCertificatePayload {

    @JsonProperty("packageId")
    private String packageId;
    @JsonProperty("packageTitle")
    private String packageTitle;
    @JsonProperty("packageImgUrl")
    private String packageImgUrl;
    @JsonProperty("certificationType")
    private Integer certificationType;
    @JsonProperty("userVariables")
    private List<Integer> userVariables;
    @JsonProperty("templateLink")
    private String templateLink;
    @JsonProperty("eventType")
    private Integer eventType;
    @JsonProperty("contentOrderIds")
    private Map<String, Integer> contentOrderIds;
    @JsonProperty("isCertificateIdIncluded")
    private boolean isCertificateIdIncluded;
    public boolean isCertificateIdIncluded() {
		return isCertificateIdIncluded;
	}

	public void setCertificateIdIncluded(boolean isCertificateIdIncluded) {
		this.isCertificateIdIncluded = isCertificateIdIncluded;
	}

	public Map<String, Integer> getContentOrderIds() {
		return contentOrderIds;
	}

	public void setContentOrderIds(Map<String, Integer> contentOrderIds) {
		this.contentOrderIds = contentOrderIds;
	}

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

    @JsonProperty("packageTitle")
    public String getPackageTitle() {
        return packageTitle;
    }

    @JsonProperty("packageTitle")
    public void setPackageTitle(String packageTitle) {
        this.packageTitle = packageTitle;
    }

    @JsonProperty("packageImgUrl")
    public String getPackageImgUrl() {
        return packageImgUrl;
    }

    @JsonProperty("packageImgUrl")
    public void setPackageImgUrl(String packageImgUrl) {
        this.packageImgUrl = packageImgUrl;
    }

    @JsonProperty("certificationType")
    public Integer getCertificationType() {
        return certificationType;
    }

    @JsonProperty("certificationType")
    public void setCertificationType(Integer certificationType) {
        this.certificationType = certificationType;
    }

    @JsonProperty("userVariables")
    public List<Integer> getUserVariables() {
        return userVariables;
    }

    @JsonProperty("userVariables")
    public void setUserVariables(List<Integer> userVariables) {
        this.userVariables = userVariables;
    }

    @JsonProperty("templateLink")
    public String getTemplateLink() {
        return templateLink;
    }

    @JsonProperty("templateLink")
    public void setTemplateLink(String templateLink) {
        this.templateLink = templateLink;
    }

    @JsonProperty("eventType")
    public Integer getEventType() {
        return eventType;
    }

    @JsonProperty("eventType")
    public void setEventType(Integer eventType) {
        this.eventType = eventType;
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
