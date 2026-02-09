
package pojo.assignProduct;

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
    "childId",
    "categoryId",
    "packageIdsWithGroupName"
})
@Generated("jsonschema2pojo")
public class AssignProduct {

    @JsonProperty("childId")
    private Integer childId;
    @JsonProperty("categoryId")
    private Integer categoryId;
    @JsonProperty("packageIdsWithGroupName")
    private List<PackageIdsWithGroupName> packageIdsWithGroupName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("childId")
    public Integer getChildId() {
        return childId;
    }

    @JsonProperty("childId")
    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    @JsonProperty("categoryId")
    public Integer getCategoryId() {
        return categoryId;
    }

    @JsonProperty("categoryId")
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @JsonProperty("packageIdsWithGroupName")
    public List<PackageIdsWithGroupName> getPackageIdsWithGroupName() {
        return packageIdsWithGroupName;
    }

    @JsonProperty("packageIdsWithGroupName")
    public void setPackageIdsWithGroupName(List<PackageIdsWithGroupName> packageIdsWithGroupName) {
        this.packageIdsWithGroupName = packageIdsWithGroupName;
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
