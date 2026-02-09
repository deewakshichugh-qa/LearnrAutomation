
package pojo.createCouponResponse;

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
    "totalUsage",
    "usagePerUser"
})
@Generated("jsonschema2pojo")
public class UsageValidity {

    @JsonProperty("totalUsage")
    private Integer totalUsage;
    @JsonProperty("usagePerUser")
    private Integer usagePerUser;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("totalUsage")
    public Integer getTotalUsage() {
        return totalUsage;
    }

    @JsonProperty("totalUsage")
    public void setTotalUsage(Integer totalUsage) {
        this.totalUsage = totalUsage;
    }

    @JsonProperty("usagePerUser")
    public Integer getUsagePerUser() {
        return usagePerUser;
    }

    @JsonProperty("usagePerUser")
    public void setUsagePerUser(Integer usagePerUser) {
        this.usagePerUser = usagePerUser;
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
