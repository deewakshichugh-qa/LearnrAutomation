
package pojo.getVideoList;

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
    "inputVideoStreamingBean",
    "childJson"
})
@Generated("jsonschema2pojo")
public class Data {

    @JsonProperty("inputVideoStreamingBean")
    private InputVideoStreamingBean inputVideoStreamingBean;
    @JsonProperty("childJson")
    private ChildJson childJson;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("inputVideoStreamingBean")
    public InputVideoStreamingBean getInputVideoStreamingBean() {
        return inputVideoStreamingBean;
    }

    @JsonProperty("inputVideoStreamingBean")
    public void setInputVideoStreamingBean(InputVideoStreamingBean inputVideoStreamingBean) {
        this.inputVideoStreamingBean = inputVideoStreamingBean;
    }

    @JsonProperty("childJson")
    public ChildJson getChildJson() {
        return childJson;
    }

    @JsonProperty("childJson")
    public void setChildJson(ChildJson childJson) {
        this.childJson = childJson;
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
