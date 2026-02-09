
package pojo.createCouponPayload;

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
    "validityStartDate",
    "validityEndDate"
})
@Generated("jsonschema2pojo")
public class Validity {

    @JsonProperty("validityStartDate")
    private String validityStartDate;
    @JsonProperty("validityEndDate")
    private String validityEndDate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("validityStartDate")
    public String getValidityStartDate() {
        return validityStartDate;
    }

    @JsonProperty("validityStartDate")
    public void setValidityStartDate(String validityStartDate) {
        this.validityStartDate = validityStartDate;
    }

    @JsonProperty("validityEndDate")
    public String getValidityEndDate() {
        return validityEndDate;
    }

    @JsonProperty("validityEndDate")
    public void setValidityEndDate(String validityEndDate) {
        this.validityEndDate = validityEndDate;
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
