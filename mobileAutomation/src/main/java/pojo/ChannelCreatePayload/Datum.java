
package pojo.ChannelCreatePayload;

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
    "name",
    "stageName",
    "latencyMode",
    "tags",
    "stageTags",
    "type",
    "maximumMessageLength",
    "maximumMessageRatePerSecond",
    "messageReviewHandler",
    "createRoomName",
    "createRoomTags",
    "domain",
    "class_startTime",
    "class_endTime",
    "vendor",
    "topic"
})
@Generated("jsonschema2pojo")
public class Datum {

    @JsonProperty("name")
    private String name;
    @JsonProperty("stageName")
    private String stageName;
    @JsonProperty("latencyMode")
    private String latencyMode;
    @JsonProperty("tags")
    private Tags tags;
    @JsonProperty("stageTags")
    private StageTags stageTags;
    @JsonProperty("type")
    private String type;
    @JsonProperty("maximumMessageLength")
    private Integer maximumMessageLength;
    @JsonProperty("maximumMessageRatePerSecond")
    private Integer maximumMessageRatePerSecond;
    @JsonProperty("messageReviewHandler")
    private MessageReviewHandler messageReviewHandler;
    @JsonProperty("createRoomName")
    private String createRoomName;
    @JsonProperty("createRoomTags")
    private CreateRoomTags createRoomTags;
    @JsonProperty("domain")
    private String domain;
    @JsonProperty("class_startTime")
    private Long classStartTime;
    @JsonProperty("class_endTime")
    private Long classEndTime;
    @JsonProperty("vendor")
    private Integer vendor;
    @JsonProperty("topic")
    private String topic;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("stageName")
    public String getStageName() {
        return stageName;
    }

    @JsonProperty("stageName")
    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    @JsonProperty("latencyMode")
    public String getLatencyMode() {
        return latencyMode;
    }

    @JsonProperty("latencyMode")
    public void setLatencyMode(String latencyMode) {
        this.latencyMode = latencyMode;
    }

    @JsonProperty("tags")
    public Tags getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(Tags tags) {
        this.tags = tags;
    }

    @JsonProperty("stageTags")
    public StageTags getStageTags() {
        return stageTags;
    }

    @JsonProperty("stageTags")
    public void setStageTags(StageTags stageTags) {
        this.stageTags = stageTags;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
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
    public MessageReviewHandler getMessageReviewHandler() {
        return messageReviewHandler;
    }

    @JsonProperty("messageReviewHandler")
    public void setMessageReviewHandler(MessageReviewHandler messageReviewHandler) {
        this.messageReviewHandler = messageReviewHandler;
    }

    @JsonProperty("createRoomName")
    public String getCreateRoomName() {
        return createRoomName;
    }

    @JsonProperty("createRoomName")
    public void setCreateRoomName(String createRoomName) {
        this.createRoomName = createRoomName;
    }

    @JsonProperty("createRoomTags")
    public CreateRoomTags getCreateRoomTags() {
        return createRoomTags;
    }

    @JsonProperty("createRoomTags")
    public void setCreateRoomTags(CreateRoomTags createRoomTags) {
        this.createRoomTags = createRoomTags;
    }

    @JsonProperty("domain")
    public String getDomain() {
        return domain;
    }

    @JsonProperty("domain")
    public void setDomain(String domain) {
        this.domain = domain;
    }

    @JsonProperty("class_startTime")
    public Long getClassStartTime() {
        return classStartTime;
    }

    @JsonProperty("class_startTime")
    public void setClassStartTime(Long classStartTime) {
        this.classStartTime = classStartTime;
    }

    @JsonProperty("class_endTime")
    public Long getClassEndTime() {
        return classEndTime;
    }

    @JsonProperty("class_endTime")
    public void setClassEndTime(Long classEndTime) {
        this.classEndTime = classEndTime;
    }

    @JsonProperty("vendor")
    public Integer getVendor() {
        return vendor;
    }

    @JsonProperty("vendor")
    public void setVendor(Integer vendor) {
        this.vendor = vendor;
    }

    @JsonProperty("topic")
    public String getTopic() {
        return topic;
    }

    @JsonProperty("topic")
    public void setTopic(String topic) {
        this.topic = topic;
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
