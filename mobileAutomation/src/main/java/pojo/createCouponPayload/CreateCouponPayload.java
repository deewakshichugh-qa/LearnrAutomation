
package pojo.createCouponPayload;

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
    "couponType",
    "couponAppliesTo",
    "priceRangeMax",
    "priceRangeMin",
    "platformList",
    "isAll",
    "forCrm",
    "artefactRequest",
    "usageValidity",
    "validity",
    "description",
    "isExternal",
    "couponCode",
    "discountType",
    "discountMode",
    "discountValue",
    "maxDiscountValue",
    "createdBy"
})
@Generated("jsonschema2pojo")
public class CreateCouponPayload {

    @JsonProperty("couponType")
    private String couponType;
    @JsonProperty("couponAppliesTo")
    private String couponAppliesTo;
    @JsonProperty("priceRangeMax")
    private Integer priceRangeMax;
    @JsonProperty("priceRangeMin")
    private Integer priceRangeMin;
    @JsonProperty("platformList")
    private List<String> platformList;
    @JsonProperty("isAll")
    private Boolean isAll;
    @JsonProperty("forCrm")
    private Boolean forCrm;
    @JsonProperty("artefactRequest")
    private List<ArtefactRequest> artefactRequest;
    @JsonProperty("usageValidity")
    private UsageValidity usageValidity;
    @JsonProperty("validity")
    private List<Validity> validity;
    @JsonProperty("description")
    private String description;
    @JsonProperty("isExternal")
    private Boolean isExternal;
    @JsonProperty("couponCode")
    private String couponCode;
    @JsonProperty("discountType")
    private String discountType;
    @JsonProperty("discountMode")
    private String discountMode;
    @JsonProperty("discountValue")
    private String discountValue;
    @JsonProperty("maxDiscountValue")
    private Integer maxDiscountValue;
    @JsonProperty("createdBy")
    private String createdBy;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("couponType")
    public String getCouponType() {
        return couponType;
    }

    @JsonProperty("couponType")
    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    @JsonProperty("couponAppliesTo")
    public String getCouponAppliesTo() {
        return couponAppliesTo;
    }

    @JsonProperty("couponAppliesTo")
    public void setCouponAppliesTo(String couponAppliesTo) {
        this.couponAppliesTo = couponAppliesTo;
    }

    @JsonProperty("priceRangeMax")
    public Integer getPriceRangeMax() {
        return priceRangeMax;
    }

    @JsonProperty("priceRangeMax")
    public void setPriceRangeMax(Integer priceRangeMax) {
        this.priceRangeMax = priceRangeMax;
    }

    @JsonProperty("priceRangeMin")
    public Integer getPriceRangeMin() {
        return priceRangeMin;
    }

    @JsonProperty("priceRangeMin")
    public void setPriceRangeMin(Integer priceRangeMin) {
        this.priceRangeMin = priceRangeMin;
    }

    @JsonProperty("platformList")
    public List<String> getPlatformList() {
        return platformList;
    }

    @JsonProperty("platformList")
    public void setPlatformList(List<String> platformList) {
        this.platformList = platformList;
    }

    @JsonProperty("isAll")
    public Boolean getIsAll() {
        return isAll;
    }

    @JsonProperty("isAll")
    public void setIsAll(Boolean isAll) {
        this.isAll = isAll;
    }

    @JsonProperty("forCrm")
    public Boolean getForCrm() {
        return forCrm;
    }

    @JsonProperty("forCrm")
    public void setForCrm(Boolean forCrm) {
        this.forCrm = forCrm;
    }

    @JsonProperty("artefactRequest")
    public List<ArtefactRequest> getArtefactRequest() {
        return artefactRequest;
    }

    @JsonProperty("artefactRequest")
    public void setArtefactRequest(List<ArtefactRequest> artefactRequest) {
        this.artefactRequest = artefactRequest;
    }

    @JsonProperty("usageValidity")
    public UsageValidity getUsageValidity() {
        return usageValidity;
    }

    @JsonProperty("usageValidity")
    public void setUsageValidity(UsageValidity usageValidity) {
        this.usageValidity = usageValidity;
    }

    @JsonProperty("validity")
    public List<Validity> getValidity() {
        return validity;
    }

    @JsonProperty("validity")
    public void setValidity(List<Validity> validity) {
        this.validity = validity;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("isExternal")
    public Boolean getIsExternal() {
        return isExternal;
    }

    @JsonProperty("isExternal")
    public void setIsExternal(Boolean isExternal) {
        this.isExternal = isExternal;
    }

    @JsonProperty("couponCode")
    public String getCouponCode() {
        return couponCode;
    }

    @JsonProperty("couponCode")
    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    @JsonProperty("discountType")
    public String getDiscountType() {
        return discountType;
    }

    @JsonProperty("discountType")
    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    @JsonProperty("discountMode")
    public String getDiscountMode() {
        return discountMode;
    }

    @JsonProperty("discountMode")
    public void setDiscountMode(String discountMode) {
        this.discountMode = discountMode;
    }

    @JsonProperty("discountValue")
    public String getDiscountValue() {
        return discountValue;
    }

    @JsonProperty("discountValue")
    public void setDiscountValue(String discountValue) {
        this.discountValue = discountValue;
    }

    @JsonProperty("maxDiscountValue")
    public Integer getMaxDiscountValue() {
        return maxDiscountValue;
    }

    @JsonProperty("maxDiscountValue")
    public void setMaxDiscountValue(Integer maxDiscountValue) {
        this.maxDiscountValue = maxDiscountValue;
    }

    @JsonProperty("createdBy")
    public String getCreatedBy() {
        return createdBy;
    }

    @JsonProperty("createdBy")
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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
