
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
    "authorized",
    "ingestEndpoint",
    "insecureIngest",
    "latencyMode",
    "name",
    "playbackUrl",
    "preset",
    "recordingConfigurationArn",
    "tags",
    "type"
})
@Generated("jsonschema2pojo")
public class Channel {

    @JsonProperty("arn")
    private String arn;
    @JsonProperty("authorized")
    private Boolean authorized;
    @JsonProperty("ingestEndpoint")
    private String ingestEndpoint;
    @JsonProperty("insecureIngest")
    private Boolean insecureIngest;
    @JsonProperty("latencyMode")
    private String latencyMode;
    @JsonProperty("name")
    private String name;
    @JsonProperty("playbackUrl")
    private String playbackUrl;
    @JsonProperty("preset")
    private String preset;
    @JsonProperty("recordingConfigurationArn")
    private String recordingConfigurationArn;
    @JsonProperty("tags")
    private Tags__1 tags;
    @JsonProperty("type")
    private String type;
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

    @JsonProperty("authorized")
    public Boolean getAuthorized() {
        return authorized;
    }

    @JsonProperty("authorized")
    public void setAuthorized(Boolean authorized) {
        this.authorized = authorized;
    }

    @JsonProperty("ingestEndpoint")
    public String getIngestEndpoint() {
        return ingestEndpoint;
    }

    @JsonProperty("ingestEndpoint")
    public void setIngestEndpoint(String ingestEndpoint) {
        this.ingestEndpoint = ingestEndpoint;
    }

    @JsonProperty("insecureIngest")
    public Boolean getInsecureIngest() {
        return insecureIngest;
    }

    @JsonProperty("insecureIngest")
    public void setInsecureIngest(Boolean insecureIngest) {
        this.insecureIngest = insecureIngest;
    }

    @JsonProperty("latencyMode")
    public String getLatencyMode() {
        return latencyMode;
    }

    @JsonProperty("latencyMode")
    public void setLatencyMode(String latencyMode) {
        this.latencyMode = latencyMode;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("playbackUrl")
    public String getPlaybackUrl() {
        return playbackUrl;
    }

    @JsonProperty("playbackUrl")
    public void setPlaybackUrl(String playbackUrl) {
        this.playbackUrl = playbackUrl;
    }

    @JsonProperty("preset")
    public String getPreset() {
        return preset;
    }

    @JsonProperty("preset")
    public void setPreset(String preset) {
        this.preset = preset;
    }

    @JsonProperty("recordingConfigurationArn")
    public String getRecordingConfigurationArn() {
        return recordingConfigurationArn;
    }

    @JsonProperty("recordingConfigurationArn")
    public void setRecordingConfigurationArn(String recordingConfigurationArn) {
        this.recordingConfigurationArn = recordingConfigurationArn;
    }

    @JsonProperty("tags")
    public Tags__1 getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(Tags__1 tags) {
        this.tags = tags;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
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
