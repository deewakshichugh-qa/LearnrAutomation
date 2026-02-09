
package pojo.createPackageResponse;


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
    "title",
    "slugTitle",
    "slugURL",
    "imageUrl",
    "shippable",
    "published",
    "createdAt",
    "updatedAt",
    "videoPackage",
    "batchDataList",
    "vpData",
    "olcAccountId",
    "hlsJson",
    "olcHours",
    "mahaPack",
    "packageDescription",
    "certificationIncluded"
})
@Generated("jsonschema2pojo")
public class Data {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("slugTitle")
    private String slugTitle;
    @JsonProperty("slugURL")
    private String slugURL;
    @JsonProperty("imageUrl")
    private String imageUrl;
    @JsonProperty("shippable")
    private Boolean shippable;
    @JsonProperty("published")
    private Boolean published;
    @JsonProperty("createdAt")
    private Long createdAt;
    @JsonProperty("updatedAt")
    private Long updatedAt;
    @JsonProperty("videoPackage")
    private Boolean videoPackage;
    @JsonProperty("batchDataList")
    private List<Object> batchDataList;
    @JsonProperty("vpData")
    private List<VpDatum> vpData;
    @JsonProperty("olcAccountId")
    private Integer olcAccountId;
    @JsonProperty("hlsJson")
    private String hlsJson;
    @JsonProperty("olcHours")
    private Double olcHours;
    @JsonProperty("mahaPack")
    private Boolean mahaPack;
    @JsonProperty("packageDescription")
    private PackageDescription packageDescription;
    @JsonProperty("certificationIncluded")
    private Boolean certificationIncluded;
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

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("slugTitle")
    public String getSlugTitle() {
        return slugTitle;
    }

    @JsonProperty("slugTitle")
    public void setSlugTitle(String slugTitle) {
        this.slugTitle = slugTitle;
    }

    @JsonProperty("slugURL")
    public String getSlugURL() {
        return slugURL;
    }

    @JsonProperty("slugURL")
    public void setSlugURL(String slugURL) {
        this.slugURL = slugURL;
    }

    @JsonProperty("imageUrl")
    public String getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("imageUrl")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonProperty("shippable")
    public Boolean getShippable() {
        return shippable;
    }

    @JsonProperty("shippable")
    public void setShippable(Boolean shippable) {
        this.shippable = shippable;
    }

    @JsonProperty("published")
    public Boolean getPublished() {
        return published;
    }

    @JsonProperty("published")
    public void setPublished(Boolean published) {
        this.published = published;
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

    @JsonProperty("videoPackage")
    public Boolean getVideoPackage() {
        return videoPackage;
    }

    @JsonProperty("videoPackage")
    public void setVideoPackage(Boolean videoPackage) {
        this.videoPackage = videoPackage;
    }

    @JsonProperty("batchDataList")
    public List<Object> getBatchDataList() {
        return batchDataList;
    }

    @JsonProperty("batchDataList")
    public void setBatchDataList(List<Object> batchDataList) {
        this.batchDataList = batchDataList;
    }

    @JsonProperty("vpData")
    public List<VpDatum> getVpData() {
        return vpData;
    }

    @JsonProperty("vpData")
    public void setVpData(List<VpDatum> vpData) {
        this.vpData = vpData;
    }

    @JsonProperty("olcAccountId")
    public Integer getOlcAccountId() {
        return olcAccountId;
    }

    @JsonProperty("olcAccountId")
    public void setOlcAccountId(Integer olcAccountId) {
        this.olcAccountId = olcAccountId;
    }

    @JsonProperty("hlsJson")
    public String getHlsJson() {
        return hlsJson;
    }

    @JsonProperty("hlsJson")
    public void setHlsJson(String hlsJson) {
        this.hlsJson = hlsJson;
    }

    @JsonProperty("olcHours")
    public Double getOlcHours() {
        return olcHours;
    }

    @JsonProperty("olcHours")
    public void setOlcHours(Double olcHours) {
        this.olcHours = olcHours;
    }

    @JsonProperty("mahaPack")
    public Boolean getMahaPack() {
        return mahaPack;
    }

    @JsonProperty("mahaPack")
    public void setMahaPack(Boolean mahaPack) {
        this.mahaPack = mahaPack;
    }

    @JsonProperty("packageDescription")
    public PackageDescription getPackageDescription() {
        return packageDescription;
    }

    @JsonProperty("packageDescription")
    public void setPackageDescription(PackageDescription packageDescription) {
        this.packageDescription = packageDescription;
    }

    @JsonProperty("certificationIncluded")
    public Boolean getCertificationIncluded() {
        return certificationIncluded;
    }

    @JsonProperty("certificationIncluded")
    public void setCertificationIncluded(Boolean certificationIncluded) {
        this.certificationIncluded = certificationIncluded;
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
