
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
    "id",
    "authorized",
    "latencyMode",
    "name",
    "recordingConfigurationArn",
    "tags",
    "type",
    "channel_arn",
    "channel_ingestEndpoint",
    "channel_playbackUrl",
    "streamKey_arn",
    "streamKey_value",
    "chat_arn",
    "chat_createTime",
    "chat_id",
    "chat_updateTime",
    "maximumMessageLength",
    "maximumMessageRatePerSecond",
    "messageReviewHandler",
    "createRoomName",
    "createRoomTags",
    "status",
    "audio_on",
    "video_on",
    "screenshare_on",
    "chat_on",
    "teacher_join",
    "class_startTime",
    "class_endTime",
    "health_monitor",
    "private_mode",
    "isRestarted",
    "webStats",
    "updatedAt",
    "createdAt",
    "vendor",
    "broadcast_type",
    "live_broadcast_type",
    "isBookmark",
    "isStage",
    "issue_by_teacher",
    "student_count",
    "stageName",
    "stageArn",
    "stageTags",
    "handRaiseParticipants"
})
@Generated("jsonschema2pojo")
public class DbData {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("authorized")
    private Boolean authorized;
    @JsonProperty("latencyMode")
    private String latencyMode;
    @JsonProperty("name")
    private String name;
    @JsonProperty("recordingConfigurationArn")
    private String recordingConfigurationArn;
    @JsonProperty("tags")
    private Tags tags;
    @JsonProperty("type")
    private String type;
    @JsonProperty("channel_arn")
    private String channelArn;
    @JsonProperty("channel_ingestEndpoint")
    private String channelIngestEndpoint;
    @JsonProperty("channel_playbackUrl")
    private String channelPlaybackUrl;
    @JsonProperty("streamKey_arn")
    private String streamKeyArn;
    @JsonProperty("streamKey_value")
    private String streamKeyValue;
    @JsonProperty("chat_arn")
    private String chatArn;
    @JsonProperty("chat_createTime")
    private String chatCreateTime;
    @JsonProperty("chat_id")
    private String chatId;
    @JsonProperty("chat_updateTime")
    private String chatUpdateTime;
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
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("audio_on")
    private Boolean audioOn;
    @JsonProperty("video_on")
    private Boolean videoOn;
    @JsonProperty("screenshare_on")
    private Boolean screenshareOn;
    @JsonProperty("chat_on")
    private Boolean chatOn;
    @JsonProperty("teacher_join")
    private Boolean teacherJoin;
    @JsonProperty("class_startTime")
    private Long classStartTime;
    @JsonProperty("class_endTime")
    private Long classEndTime;
    @JsonProperty("health_monitor")
    private Boolean healthMonitor;
    @JsonProperty("private_mode")
    private Boolean privateMode;
    @JsonProperty("isRestarted")
    private Integer isRestarted;
    @JsonProperty("webStats")
    private Object webStats;
    @JsonProperty("updatedAt")
    private String updatedAt;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("vendor")
    private Integer vendor;
    @JsonProperty("broadcast_type")
    private String broadcastType;
    @JsonProperty("live_broadcast_type")
    private String liveBroadcastType;
    @JsonProperty("isBookmark")
    private Boolean isBookmark;
    @JsonProperty("isStage")
    private Boolean isStage;
    @JsonProperty("issue_by_teacher")
    private Object issueByTeacher;
    @JsonProperty("student_count")
    private Integer studentCount;
    @JsonProperty("stageName")
    private String stageName;
    @JsonProperty("stageArn")
    private String stageArn;
    @JsonProperty("stageTags")
    private StageTags stageTags;
    @JsonProperty("handRaiseParticipants")
    private Object handRaiseParticipants;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("authorized")
    public Boolean getAuthorized() {
        return authorized;
    }

    @JsonProperty("authorized")
    public void setAuthorized(Boolean authorized) {
        this.authorized = authorized;
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

    @JsonProperty("recordingConfigurationArn")
    public String getRecordingConfigurationArn() {
        return recordingConfigurationArn;
    }

    @JsonProperty("recordingConfigurationArn")
    public void setRecordingConfigurationArn(String recordingConfigurationArn) {
        this.recordingConfigurationArn = recordingConfigurationArn;
    }

    @JsonProperty("tags")
    public Tags getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(Tags tags) {
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

    @JsonProperty("channel_arn")
    public String getChannelArn() {
        return channelArn;
    }

    @JsonProperty("channel_arn")
    public void setChannelArn(String channelArn) {
        this.channelArn = channelArn;
    }

    @JsonProperty("channel_ingestEndpoint")
    public String getChannelIngestEndpoint() {
        return channelIngestEndpoint;
    }

    @JsonProperty("channel_ingestEndpoint")
    public void setChannelIngestEndpoint(String channelIngestEndpoint) {
        this.channelIngestEndpoint = channelIngestEndpoint;
    }

    @JsonProperty("channel_playbackUrl")
    public String getChannelPlaybackUrl() {
        return channelPlaybackUrl;
    }

    @JsonProperty("channel_playbackUrl")
    public void setChannelPlaybackUrl(String channelPlaybackUrl) {
        this.channelPlaybackUrl = channelPlaybackUrl;
    }

    @JsonProperty("streamKey_arn")
    public String getStreamKeyArn() {
        return streamKeyArn;
    }

    @JsonProperty("streamKey_arn")
    public void setStreamKeyArn(String streamKeyArn) {
        this.streamKeyArn = streamKeyArn;
    }

    @JsonProperty("streamKey_value")
    public String getStreamKeyValue() {
        return streamKeyValue;
    }

    @JsonProperty("streamKey_value")
    public void setStreamKeyValue(String streamKeyValue) {
        this.streamKeyValue = streamKeyValue;
    }

    @JsonProperty("chat_arn")
    public String getChatArn() {
        return chatArn;
    }

    @JsonProperty("chat_arn")
    public void setChatArn(String chatArn) {
        this.chatArn = chatArn;
    }

    @JsonProperty("chat_createTime")
    public String getChatCreateTime() {
        return chatCreateTime;
    }

    @JsonProperty("chat_createTime")
    public void setChatCreateTime(String chatCreateTime) {
        this.chatCreateTime = chatCreateTime;
    }

    @JsonProperty("chat_id")
    public String getChatId() {
        return chatId;
    }

    @JsonProperty("chat_id")
    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    @JsonProperty("chat_updateTime")
    public String getChatUpdateTime() {
        return chatUpdateTime;
    }

    @JsonProperty("chat_updateTime")
    public void setChatUpdateTime(String chatUpdateTime) {
        this.chatUpdateTime = chatUpdateTime;
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

    @JsonProperty("status")
    public Integer getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    @JsonProperty("audio_on")
    public Boolean getAudioOn() {
        return audioOn;
    }

    @JsonProperty("audio_on")
    public void setAudioOn(Boolean audioOn) {
        this.audioOn = audioOn;
    }

    @JsonProperty("video_on")
    public Boolean getVideoOn() {
        return videoOn;
    }

    @JsonProperty("video_on")
    public void setVideoOn(Boolean videoOn) {
        this.videoOn = videoOn;
    }

    @JsonProperty("screenshare_on")
    public Boolean getScreenshareOn() {
        return screenshareOn;
    }

    @JsonProperty("screenshare_on")
    public void setScreenshareOn(Boolean screenshareOn) {
        this.screenshareOn = screenshareOn;
    }

    @JsonProperty("chat_on")
    public Boolean getChatOn() {
        return chatOn;
    }

    @JsonProperty("chat_on")
    public void setChatOn(Boolean chatOn) {
        this.chatOn = chatOn;
    }

    @JsonProperty("teacher_join")
    public Boolean getTeacherJoin() {
        return teacherJoin;
    }

    @JsonProperty("teacher_join")
    public void setTeacherJoin(Boolean teacherJoin) {
        this.teacherJoin = teacherJoin;
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

    @JsonProperty("health_monitor")
    public Boolean getHealthMonitor() {
        return healthMonitor;
    }

    @JsonProperty("health_monitor")
    public void setHealthMonitor(Boolean healthMonitor) {
        this.healthMonitor = healthMonitor;
    }

    @JsonProperty("private_mode")
    public Boolean getPrivateMode() {
        return privateMode;
    }

    @JsonProperty("private_mode")
    public void setPrivateMode(Boolean privateMode) {
        this.privateMode = privateMode;
    }

    @JsonProperty("isRestarted")
    public Integer getIsRestarted() {
        return isRestarted;
    }

    @JsonProperty("isRestarted")
    public void setIsRestarted(Integer isRestarted) {
        this.isRestarted = isRestarted;
    }

    @JsonProperty("webStats")
    public Object getWebStats() {
        return webStats;
    }

    @JsonProperty("webStats")
    public void setWebStats(Object webStats) {
        this.webStats = webStats;
    }

    @JsonProperty("updatedAt")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updatedAt")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("createdAt")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("createdAt")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("vendor")
    public Integer getVendor() {
        return vendor;
    }

    @JsonProperty("vendor")
    public void setVendor(Integer vendor) {
        this.vendor = vendor;
    }

    @JsonProperty("broadcast_type")
    public String getBroadcastType() {
        return broadcastType;
    }

    @JsonProperty("broadcast_type")
    public void setBroadcastType(String broadcastType) {
        this.broadcastType = broadcastType;
    }

    @JsonProperty("live_broadcast_type")
    public String getLiveBroadcastType() {
        return liveBroadcastType;
    }

    @JsonProperty("live_broadcast_type")
    public void setLiveBroadcastType(String liveBroadcastType) {
        this.liveBroadcastType = liveBroadcastType;
    }

    @JsonProperty("isBookmark")
    public Boolean getIsBookmark() {
        return isBookmark;
    }

    @JsonProperty("isBookmark")
    public void setIsBookmark(Boolean isBookmark) {
        this.isBookmark = isBookmark;
    }

    @JsonProperty("isStage")
    public Boolean getIsStage() {
        return isStage;
    }

    @JsonProperty("isStage")
    public void setIsStage(Boolean isStage) {
        this.isStage = isStage;
    }

    @JsonProperty("issue_by_teacher")
    public Object getIssueByTeacher() {
        return issueByTeacher;
    }

    @JsonProperty("issue_by_teacher")
    public void setIssueByTeacher(Object issueByTeacher) {
        this.issueByTeacher = issueByTeacher;
    }

    @JsonProperty("student_count")
    public Integer getStudentCount() {
        return studentCount;
    }

    @JsonProperty("student_count")
    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }

    @JsonProperty("stageName")
    public String getStageName() {
        return stageName;
    }

    @JsonProperty("stageName")
    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    @JsonProperty("stageArn")
    public String getStageArn() {
        return stageArn;
    }

    @JsonProperty("stageArn")
    public void setStageArn(String stageArn) {
        this.stageArn = stageArn;
    }

    @JsonProperty("stageTags")
    public StageTags getStageTags() {
        return stageTags;
    }

    @JsonProperty("stageTags")
    public void setStageTags(StageTags stageTags) {
        this.stageTags = stageTags;
    }

    @JsonProperty("handRaiseParticipants")
    public Object getHandRaiseParticipants() {
        return handRaiseParticipants;
    }

    @JsonProperty("handRaiseParticipants")
    public void setHandRaiseParticipants(Object handRaiseParticipants) {
        this.handRaiseParticipants = handRaiseParticipants;
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
