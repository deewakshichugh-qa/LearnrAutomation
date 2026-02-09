package pojo.updateGroupDataPayload;

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
    "groupDataRequestList"
})
@Generated("jsonschema2pojo")
public class UpdateGroupData {

    @JsonProperty("packageId")
    private String packageId;
    @JsonProperty("groupDataRequestList")
    private List<GroupDataRequest> groupDataRequestList;
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

    @JsonProperty("groupDataRequestList")
    public List<GroupDataRequest> getGroupDataRequestList() {
        return groupDataRequestList;
    }

    @JsonProperty("groupDataRequestList")
    public void setGroupDataRequestList(List<GroupDataRequest> groupDataRequestList) {
        this.groupDataRequestList = groupDataRequestList;
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