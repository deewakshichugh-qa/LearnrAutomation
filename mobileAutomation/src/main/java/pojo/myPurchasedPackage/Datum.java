
package pojo.myPurchasedPackage;

import java.util.HashMap;
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
    "title",
    "thumbnail",
    "childPackages",
    "expired",
    "expiry",
    "parent",
    "categories",
    "createdAt",
    "published",
    "packageCategories"
})
@Generated("jsonschema2pojo")
public class Datum {

    @JsonProperty("packageId")
    private Integer packageId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("thumbnail")
    private String thumbnail;
    @JsonProperty("childPackages")
    private List<Integer> childPackages = null;
    @JsonProperty("expired")
    private Boolean expired;
    @JsonProperty("expiry")
    private Long expiry;
    @JsonProperty("parent")
    private Boolean parent;
    @JsonProperty("categories")
    private Object categories;
    @JsonProperty("createdAt")
    private Long createdAt;
    @JsonProperty("published")
    private Boolean published;
    @JsonProperty("packageCategories")
    private List<String> packageCategories = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("packageId")
    public Integer getPackageId() {
        return packageId;
    }

    @JsonProperty("packageId")
    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("thumbnail")
    public String getThumbnail() {
        return thumbnail;
    }

    @JsonProperty("thumbnail")
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @JsonProperty("childPackages")
    public List<Integer> getChildPackages() {
        return childPackages;
    }

    @JsonProperty("childPackages")
    public void setChildPackages(List<Integer> childPackages) {
        this.childPackages = childPackages;
    }

    @JsonProperty("expired")
    public Boolean getExpired() {
        return expired;
    }

    @JsonProperty("expired")
    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    @JsonProperty("expiry")
    public Long getExpiry() {
        return expiry;
    }

    @JsonProperty("expiry")
    public void setExpiry(Long expiry) {
        this.expiry = expiry;
    }

    @JsonProperty("parent")
    public Boolean getParent() {
        return parent;
    }

    @JsonProperty("parent")
    public void setParent(Boolean parent) {
        this.parent = parent;
    }

    @JsonProperty("categories")
    public Object getCategories() {
        return categories;
    }

    @JsonProperty("categories")
    public void setCategories(Object categories) {
        this.categories = categories;
    }

    @JsonProperty("createdAt")
    public Long getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("createdAt")
    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("published")
    public Boolean getPublished() {
        return published;
    }

    @JsonProperty("published")
    public void setPublished(Boolean published) {
        this.published = published;
    }

    @JsonProperty("packageCategories")
    public List<String> getPackageCategories() {
        return packageCategories;
    }

    @JsonProperty("packageCategories")
    public void setPackageCategories(List<String> packageCategories) {
        this.packageCategories = packageCategories;
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
