
package pojo.ChannelCreateResponse;

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
    "arn",
    "channelArn",
    "tags",
    "value"
})
@Generated("jsonschema2pojo")
public class StreamKey {

    @JsonProperty("arn")
    private String arn;
    @JsonProperty("channelArn")
    private String channelArn;
    @JsonProperty("tags")
    private Tags__2 tags;
    @JsonProperty("value")
    private String value;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("arn")
    public String getArn() {
        return arn;
    }

    @JsonProperty("arn")
    public void setArn(String arn) {
        this.arn = arn;
    }

    @JsonProperty("channelArn")
    public String getChannelArn() {
        return channelArn;
    }

    @JsonProperty("channelArn")
    public void setChannelArn(String channelArn) {
        this.channelArn = channelArn;
    }

    @JsonProperty("tags")
    public Tags__2 getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(Tags__2 tags) {
        this.tags = tags;
    }

    @JsonProperty("value")
    public String getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
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
