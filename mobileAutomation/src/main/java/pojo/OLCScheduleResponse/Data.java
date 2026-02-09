
package pojo.OLCScheduleResponse;

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
    "created",
    "invalid",
    "overlapped",
    "participants"
})
@Generated("jsonschema2pojo")
public class Data {

    @JsonProperty("created")
    private List<Object> created;
    @JsonProperty("invalid")
    private List<Invalid> invalid;
    @JsonProperty("overlapped")
    private List<Object> overlapped;
    @JsonProperty("participants")
    private List<Object> participants;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("created")
    public List<Object> getCreated() {
        return created;
    }

    @JsonProperty("created")
    public void setCreated(List<Object> created) {
        this.created = created;
    }

    @JsonProperty("invalid")
    public List<Invalid> getInvalid() {
        return invalid;
    }

    @JsonProperty("invalid")
    public void setInvalid(List<Invalid> invalid) {
        this.invalid = invalid;
    }

    @JsonProperty("overlapped")
    public List<Object> getOverlapped() {
        return overlapped;
    }

    @JsonProperty("overlapped")
    public void setOverlapped(List<Object> overlapped) {
        this.overlapped = overlapped;
    }

    @JsonProperty("participants")
    public List<Object> getParticipants() {
        return participants;
    }

    @JsonProperty("participants")
    public void setParticipants(List<Object> participants) {
        this.participants = participants;
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
