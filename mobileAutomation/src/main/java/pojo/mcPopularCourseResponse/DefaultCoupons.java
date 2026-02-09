package pojo.mcPopularCourseResponse;

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
    "code",
    "discountType",
    "discountValue",
    "excludedPackages",
    "validityStart",
    "validityExpire",
    "includedPackages",
    "artefactTypeIncluded",
    "artefactTypeExcluded"
})
@Generated("jsonschema2pojo")
public class DefaultCoupons {

    @JsonProperty("code")
    private String code;
    @JsonProperty("discountType")
    private String discountType;
    @JsonProperty("discountValue")
    private Double discountValue;
    @JsonProperty("excludedPackages")
    private List<String> excludedPackages;
    @JsonProperty("validityStart")
    private Long validityStart;
    @JsonProperty("validityExpire")
    private Long validityExpire;
    @JsonProperty("includedPackages")
    private List<String> includedPackages;
    @JsonProperty("artefactTypeIncluded")
    private String artefactTypeIncluded;
    @JsonProperty("artefactTypeExcluded")
    private String artefactTypeExcluded;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("discountType")
    public String getDiscountType() {
        return discountType;
    }

    @JsonProperty("discountType")
    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    @JsonProperty("discountValue")
    public Double getDiscountValue() {
        return discountValue;
    }

    @JsonProperty("discountValue")
    public void setDiscountValue(Double discountValue) {
        this.discountValue = discountValue;
    }

    @JsonProperty("excludedPackages")
    public List<String> getExcludedPackages() {
        return excludedPackages;
    }

    @JsonProperty("excludedPackages")
    public void setExcludedPackages(List<String> excludedPackages) {
        this.excludedPackages = excludedPackages;
    }

    @JsonProperty("validityStart")
    public Long getValidityStart() {
        return validityStart;
    }

    @JsonProperty("validityStart")
    public void setValidityStart(Long validityStart) {
        this.validityStart = validityStart;
    }

    @JsonProperty("validityExpire")
    public Long getValidityExpire() {
        return validityExpire;
    }

    @JsonProperty("validityExpire")
    public void setValidityExpire(Long validityExpire) {
        this.validityExpire = validityExpire;
    }

    @JsonProperty("includedPackages")
    public List<String> getIncludedPackages() {
        return includedPackages;
    }

    @JsonProperty("includedPackages")
    public void setIncludedPackages(List<String> includedPackages) {
        this.includedPackages = includedPackages;
    }

    @JsonProperty("artefactTypeIncluded")
    public String getArtefactTypeIncluded() {
        return artefactTypeIncluded;
    }

    @JsonProperty("artefactTypeIncluded")
    public void setArtefactTypeIncluded(String artefactTypeIncluded) {
        this.artefactTypeIncluded = artefactTypeIncluded;
    }

    @JsonProperty("artefactTypeExcluded")
    public String getArtefactTypeExcluded() {
        return artefactTypeExcluded;
    }

    @JsonProperty("artefactTypeExcluded")
    public void setArtefactTypeExcluded(String artefactTypeExcluded) {
        this.artefactTypeExcluded = artefactTypeExcluded;
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
