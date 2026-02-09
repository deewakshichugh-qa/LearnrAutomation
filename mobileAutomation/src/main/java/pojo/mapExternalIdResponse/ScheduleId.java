
package pojo.mapExternalIdResponse;

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
    "newExternalScheduleId",
    "oldExternalScheduleId"
})
@Generated("jsonschema2pojo")
public class ScheduleId {

    @JsonProperty("newExternalScheduleId")
    private String newExternalScheduleId;
    @JsonProperty("oldExternalScheduleId")
    private Object oldExternalScheduleId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("newExternalScheduleId")
    public String getNewExternalScheduleId() {
        return newExternalScheduleId;
    }

    @JsonProperty("newExternalScheduleId")
    public void setNewExternalScheduleId(String newExternalScheduleId) {
        this.newExternalScheduleId = newExternalScheduleId;
    }

    @JsonProperty("oldExternalScheduleId")
    public Object getOldExternalScheduleId() {
        return oldExternalScheduleId;
    }

    @JsonProperty("oldExternalScheduleId")
    public void setOldExternalScheduleId(Object oldExternalScheduleId) {
        this.oldExternalScheduleId = oldExternalScheduleId;
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
