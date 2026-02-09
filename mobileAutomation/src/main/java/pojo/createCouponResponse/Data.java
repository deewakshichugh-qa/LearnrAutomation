
package pojo.createCouponResponse;

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
    "id",
    "couponCode",
    "couponType",
    "couponAppliesTo",
    "isAll",
    "artefactRequest",
    "priceRangeMin",
    "priceRangeMax",
    "platformList",
    "discountType",
    "discountMode",
    "discountValue",
    "maxDiscountValue",
    "isExternal",
    "additionalDiscountValue",
    "usageValidity",
    "description",
    "updatedBy",
    "createdBy",
    "errorMessage",
    "successMessage",
    "status",
    "users",
    "dynamicDiscountCategory",
    "dynamicDiscount",
    "examCategoryIdList",
    "isPromocode",
    "createdAt",
    "updatedAt",
    "genMode",
    "genModeBatchPrefix",
    "validityResponse",
    "isDefaultCoupon",
    "maxValidityEnd"
})
@Generated("jsonschema2pojo")
public class Data {

    @JsonProperty("id")
    private String id;
    @JsonProperty("couponCode")
    private String couponCode;
    @JsonProperty("couponType")
    private String couponType;
    @JsonProperty("couponAppliesTo")
    private String couponAppliesTo;
    @JsonProperty("isAll")
    private Boolean isAll;
    @JsonProperty("artefactRequest")
    private List<ArtefactRequest> artefactRequest;
    @JsonProperty("priceRangeMin")
    private Integer priceRangeMin;
    @JsonProperty("priceRangeMax")
    private Integer priceRangeMax;
    @JsonProperty("platformList")
    private List<String> platformList;
    @JsonProperty("discountType")
    private String discountType;
    @JsonProperty("discountMode")
    private String discountMode;
    @JsonProperty("discountValue")
    private Integer discountValue;
    @JsonProperty("maxDiscountValue")
    private Integer maxDiscountValue;
    @JsonProperty("isExternal")
    private Boolean isExternal;
    @JsonProperty("additionalDiscountValue")
    private Integer additionalDiscountValue;
    @JsonProperty("usageValidity")
    private UsageValidity usageValidity;
    @JsonProperty("description")
    private String description;
    @JsonProperty("updatedBy")
    private String updatedBy;
    @JsonProperty("createdBy")
    private String createdBy;
    @JsonProperty("errorMessage")
    private String errorMessage;
    @JsonProperty("successMessage")
    private String successMessage;
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("users")
    private Object users;
    @JsonProperty("dynamicDiscountCategory")
    private Object dynamicDiscountCategory;
    @JsonProperty("dynamicDiscount")
    private Object dynamicDiscount;
    @JsonProperty("examCategoryIdList")
    private Object examCategoryIdList;
    @JsonProperty("isPromocode")
    private Boolean isPromocode;
    @JsonProperty("createdAt")
    private Long createdAt;
    @JsonProperty("updatedAt")
    private Long updatedAt;
    @JsonProperty("genMode")
    private String genMode;
    @JsonProperty("genModeBatchPrefix")
    private Object genModeBatchPrefix;
    @JsonProperty("validityResponse")
    private List<ValidityResponse> validityResponse;
    @JsonProperty("isDefaultCoupon")
    private Integer isDefaultCoupon;
    @JsonProperty("maxValidityEnd")
    private Object maxValidityEnd;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("couponCode")
    public String getCouponCode() {
        return couponCode;
    }

    @JsonProperty("couponCode")
    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

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

    @JsonProperty("isAll")
    public Boolean getIsAll() {
        return isAll;
    }

    @JsonProperty("isAll")
    public void setIsAll(Boolean isAll) {
        this.isAll = isAll;
    }

    @JsonProperty("artefactRequest")
    public List<ArtefactRequest> getArtefactRequest() {
        return artefactRequest;
    }

    @JsonProperty("artefactRequest")
    public void setArtefactRequest(List<ArtefactRequest> artefactRequest) {
        this.artefactRequest = artefactRequest;
    }

    @JsonProperty("priceRangeMin")
    public Integer getPriceRangeMin() {
        return priceRangeMin;
    }

    @JsonProperty("priceRangeMin")
    public void setPriceRangeMin(Integer priceRangeMin) {
        this.priceRangeMin = priceRangeMin;
    }

    @JsonProperty("priceRangeMax")
    public Integer getPriceRangeMax() {
        return priceRangeMax;
    }

    @JsonProperty("priceRangeMax")
    public void setPriceRangeMax(Integer priceRangeMax) {
        this.priceRangeMax = priceRangeMax;
    }

    @JsonProperty("platformList")
    public List<String> getPlatformList() {
        return platformList;
    }

    @JsonProperty("platformList")
    public void setPlatformList(List<String> platformList) {
        this.platformList = platformList;
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
    public Integer getDiscountValue() {
        return discountValue;
    }

    @JsonProperty("discountValue")
    public void setDiscountValue(Integer discountValue) {
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

    @JsonProperty("isExternal")
    public Boolean getIsExternal() {
        return isExternal;
    }

    @JsonProperty("isExternal")
    public void setIsExternal(Boolean isExternal) {
        this.isExternal = isExternal;
    }

    @JsonProperty("additionalDiscountValue")
    public Integer getAdditionalDiscountValue() {
        return additionalDiscountValue;
    }

    @JsonProperty("additionalDiscountValue")
    public void setAdditionalDiscountValue(Integer additionalDiscountValue) {
        this.additionalDiscountValue = additionalDiscountValue;
    }

    @JsonProperty("usageValidity")
    public UsageValidity getUsageValidity() {
        return usageValidity;
    }

    @JsonProperty("usageValidity")
    public void setUsageValidity(UsageValidity usageValidity) {
        this.usageValidity = usageValidity;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("updatedBy")
    public String getUpdatedBy() {
        return updatedBy;
    }

    @JsonProperty("updatedBy")
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @JsonProperty("createdBy")
    public String getCreatedBy() {
        return createdBy;
    }

    @JsonProperty("createdBy")
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @JsonProperty("errorMessage")
    public String getErrorMessage() {
        return errorMessage;
    }

    @JsonProperty("errorMessage")
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @JsonProperty("successMessage")
    public String getSuccessMessage() {
        return successMessage;
    }

    @JsonProperty("successMessage")
    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    @JsonProperty("status")
    public Integer getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    @JsonProperty("users")
    public Object getUsers() {
        return users;
    }

    @JsonProperty("users")
    public void setUsers(Object users) {
        this.users = users;
    }

    @JsonProperty("dynamicDiscountCategory")
    public Object getDynamicDiscountCategory() {
        return dynamicDiscountCategory;
    }

    @JsonProperty("dynamicDiscountCategory")
    public void setDynamicDiscountCategory(Object dynamicDiscountCategory) {
        this.dynamicDiscountCategory = dynamicDiscountCategory;
    }

    @JsonProperty("dynamicDiscount")
    public Object getDynamicDiscount() {
        return dynamicDiscount;
    }

    @JsonProperty("dynamicDiscount")
    public void setDynamicDiscount(Object dynamicDiscount) {
        this.dynamicDiscount = dynamicDiscount;
    }

    @JsonProperty("examCategoryIdList")
    public Object getExamCategoryIdList() {
        return examCategoryIdList;
    }

    @JsonProperty("examCategoryIdList")
    public void setExamCategoryIdList(Object examCategoryIdList) {
        this.examCategoryIdList = examCategoryIdList;
    }

    @JsonProperty("isPromocode")
    public Boolean getIsPromocode() {
        return isPromocode;
    }

    @JsonProperty("isPromocode")
    public void setIsPromocode(Boolean isPromocode) {
        this.isPromocode = isPromocode;
    }

    @JsonProperty("createdAt")
    public Long getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("createdAt")
    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updatedAt")
    public Long getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updatedAt")
    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("genMode")
    public String getGenMode() {
        return genMode;
    }

    @JsonProperty("genMode")
    public void setGenMode(String genMode) {
        this.genMode = genMode;
    }

    @JsonProperty("genModeBatchPrefix")
    public Object getGenModeBatchPrefix() {
        return genModeBatchPrefix;
    }

    @JsonProperty("genModeBatchPrefix")
    public void setGenModeBatchPrefix(Object genModeBatchPrefix) {
        this.genModeBatchPrefix = genModeBatchPrefix;
    }

    @JsonProperty("validityResponse")
    public List<ValidityResponse> getValidityResponse() {
        return validityResponse;
    }

    @JsonProperty("validityResponse")
    public void setValidityResponse(List<ValidityResponse> validityResponse) {
        this.validityResponse = validityResponse;
    }

    @JsonProperty("isDefaultCoupon")
    public Integer getIsDefaultCoupon() {
        return isDefaultCoupon;
    }

    @JsonProperty("isDefaultCoupon")
    public void setIsDefaultCoupon(Integer isDefaultCoupon) {
        this.isDefaultCoupon = isDefaultCoupon;
    }

    @JsonProperty("maxValidityEnd")
    public Object getMaxValidityEnd() {
        return maxValidityEnd;
    }

    @JsonProperty("maxValidityEnd")
    public void setMaxValidityEnd(Object maxValidityEnd) {
        this.maxValidityEnd = maxValidityEnd;
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
