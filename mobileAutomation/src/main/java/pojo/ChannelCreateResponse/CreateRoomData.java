
package pojo.ChannelCreateResponse;

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
    "arn",
    "createTime",
    "id",
    "maximumMessageLength",
    "maximumMessageRatePerSecond",
    "messageReviewHandler",
    "name",
    "tags",
    "updateTime",
    "loggingConfigurationIdentifiers"
})
@Generated("jsonschema2pojo")
public class CreateRoomData {

    @JsonProperty("arn")
    private String arn;
    @JsonProperty("createTime")
    private String createTime;
    @JsonProperty("id")
    private String id;
    @JsonProperty("maximumMessageLength")
    private Integer maximumMessageLength;
    @JsonProperty("maximumMessageRatePerSecond")
    private Integer maximumMessageRatePerSecond;
    @JsonProperty("messageReviewHandler")
    private MessageReviewHandler__1 messageReviewHandler;
    @JsonProperty("name")
    private String name;
    @JsonProperty("tags")
    private Tags__3 tags;
    @JsonProperty("updateTime")
    private Long updateTime;
    @JsonProperty("loggingConfigurationIdentifiers")
    private List<String> loggingConfigurationIdentifiers;
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

    @JsonProperty("createTime")
    public String getCreateTime() {
        return createTime;
    }

    @JsonProperty("createTime")
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("maximumMessageLength")
    public Integer getMaximumMessageLength() {
        return maximumMessageLength;
    }

    @JsonProperty("maximumMessageLength")
    public void setMaximumMessageLength(Integer maximumMessageLength) {
        this.maximumMessageLength = maximumMessageLength;
    }

    @JsonProperty("maximumMessageRatePerSecond")
    public Integer getMaximumMessageRatePerSecond() {
        return maximumMessageRatePerSecond;
    }

    @JsonProperty("maximumMessageRatePerSecond")
    public void setMaximumMessageRatePerSecond(Integer maximumMessageRatePerSecond) {
        this.maximumMessageRatePerSecond = maximumMessageRatePerSecond;
    }

    @JsonProperty("messageReviewHandler")
    public MessageReviewHandler__1 getMessageReviewHandler() {
        return messageReviewHandler;
    }

    @JsonProperty("messageReviewHandler")
    public void setMessageReviewHandler(MessageReviewHandler__1 messageReviewHandler) {
        this.messageReviewHandler = messageReviewHandler;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("tags")
    public Tags__3 getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(Tags__3 tags) {
        this.tags = tags;
    }

    @JsonProperty("updateTime")
    public Long getUpdateTime() {
        return updateTime;
    }

    @JsonProperty("updateTime")
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @JsonProperty("loggingConfigurationIdentifiers")
    public List<String> getLoggingConfigurationIdentifiers() {
        return loggingConfigurationIdentifiers;
    }

    @JsonProperty("loggingConfigurationIdentifiers")
    public void setLoggingConfigurationIdentifiers(List<String> loggingConfigurationIdentifiers) {
        this.loggingConfigurationIdentifiers = loggingConfigurationIdentifiers;
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
