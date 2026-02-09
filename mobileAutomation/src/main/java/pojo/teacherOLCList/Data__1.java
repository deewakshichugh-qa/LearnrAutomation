
package pojo. teacherOLCList;

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
    "olcFacultyId",
    "startDate",
    "endDate",
    "endTime",
    "startTime",
    "classRoom",
    "topic",
    "attachement",
    "isCancelled",
    "cancelledBy",
    "cancelReason",
    "rescheduledBy",
    "rescheduleReason",
    "currentState",
    "description",
    "createdAt",
    "updatedAt",
    "notifyStudents",
    "nParticipants",
    "externalScheduleId",
    "packageId",
    "packgaeTitle",
    "videoUrl",
    "m3u8",
    "isOneTwoOneEnable",
    "olcType",
    "youTubeJoinUrl",
    "youTubeStreamKey",
    "youTubeScheduleType",
    "dpp",
    "firstName",
    "lastName",
    "active",
    "cancelled",
    "rescheduled",
    "handoutsAvailable"
})
@Generated("jsonschema2pojo")
public class Data__1 {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("olcFacultyId")
    private Integer olcFacultyId;
    @JsonProperty("startDate")
    private String startDate;
    @JsonProperty("endDate")
    private String endDate;
    @JsonProperty("endTime")
    private String endTime;
    @JsonProperty("startTime")
    private String startTime;
    @JsonProperty("classRoom")
    private String classRoom;
    @JsonProperty("topic")
    private String topic;
    @JsonProperty("attachement")
    private String attachement;
    @JsonProperty("isCancelled")
    private Boolean isCancelled;
    @JsonProperty("cancelledBy")
    private Object cancelledBy;
    @JsonProperty("cancelReason")
    private Object cancelReason;
    @JsonProperty("rescheduledBy")
    private Object rescheduledBy;
    @JsonProperty("rescheduleReason")
    private Object rescheduleReason;
    @JsonProperty("currentState")
    private Integer currentState;
    @JsonProperty("description")
    private String description;
    @JsonProperty("createdAt")
    private Long createdAt;
    @JsonProperty("updatedAt")
    private Long updatedAt;
    @JsonProperty("notifyStudents")
    private Boolean notifyStudents;
    @JsonProperty("nParticipants")
    private Object nParticipants;
    @JsonProperty("externalScheduleId")
    private String externalScheduleId;
    @JsonProperty("packageId")
    private Integer packageId;
    @JsonProperty("packgaeTitle")
    private String packgaeTitle;
    @JsonProperty("videoUrl")
    private Object videoUrl;
    @JsonProperty("m3u8")
    private Boolean m3u8;
    @JsonProperty("isOneTwoOneEnable")
    private Boolean isOneTwoOneEnable;
    @JsonProperty("olcType")
    private String olcType;
    @JsonProperty("youTubeJoinUrl")
    private Object youTubeJoinUrl;
    @JsonProperty("youTubeStreamKey")
    private Object youTubeStreamKey;
    @JsonProperty("youTubeScheduleType")
    private Object youTubeScheduleType;
    @JsonProperty("dpp")
    private Object dpp;
    @JsonProperty("firstName")
    private Object firstName;
    @JsonProperty("lastName")
    private Object lastName;
    @JsonProperty("active")
    private Boolean active;
    @JsonProperty("cancelled")
    private Boolean cancelled;
    @JsonProperty("rescheduled")
    private Boolean rescheduled;
    @JsonProperty("handoutsAvailable")
    private Object handoutsAvailable;
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

    @JsonProperty("olcFacultyId")
    public Integer getOlcFacultyId() {
        return olcFacultyId;
    }

    @JsonProperty("olcFacultyId")
    public void setOlcFacultyId(Integer olcFacultyId) {
        this.olcFacultyId = olcFacultyId;
    }

    @JsonProperty("startDate")
    public String getStartDate() {
        return startDate;
    }

    @JsonProperty("startDate")
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @JsonProperty("endDate")
    public String getEndDate() {
        return endDate;
    }

    @JsonProperty("endDate")
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @JsonProperty("endTime")
    public String getEndTime() {
        return endTime;
    }

    @JsonProperty("endTime")
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @JsonProperty("startTime")
    public String getStartTime() {
        return startTime;
    }

    @JsonProperty("startTime")
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @JsonProperty("classRoom")
    public String getClassRoom() {
        return classRoom;
    }

    @JsonProperty("classRoom")
    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    @JsonProperty("topic")
    public String getTopic() {
        return topic;
    }

    @JsonProperty("topic")
    public void setTopic(String topic) {
        this.topic = topic;
    }

    @JsonProperty("attachement")
    public String getAttachement() {
        return attachement;
    }

    @JsonProperty("attachement")
    public void setAttachement(String attachement) {
        this.attachement = attachement;
    }

    @JsonProperty("isCancelled")
    public Boolean getIsCancelled() {
        return isCancelled;
    }

    @JsonProperty("isCancelled")
    public void setIsCancelled(Boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    @JsonProperty("cancelledBy")
    public Object getCancelledBy() {
        return cancelledBy;
    }

    @JsonProperty("cancelledBy")
    public void setCancelledBy(Object cancelledBy) {
        this.cancelledBy = cancelledBy;
    }

    @JsonProperty("cancelReason")
    public Object getCancelReason() {
        return cancelReason;
    }

    @JsonProperty("cancelReason")
    public void setCancelReason(Object cancelReason) {
        this.cancelReason = cancelReason;
    }

    @JsonProperty("rescheduledBy")
    public Object getRescheduledBy() {
        return rescheduledBy;
    }

    @JsonProperty("rescheduledBy")
    public void setRescheduledBy(Object rescheduledBy) {
        this.rescheduledBy = rescheduledBy;
    }

    @JsonProperty("rescheduleReason")
    public Object getRescheduleReason() {
        return rescheduleReason;
    }

    @JsonProperty("rescheduleReason")
    public void setRescheduleReason(Object rescheduleReason) {
        this.rescheduleReason = rescheduleReason;
    }

    @JsonProperty("currentState")
    public Integer getCurrentState() {
        return currentState;
    }

    @JsonProperty("currentState")
    public void setCurrentState(Integer currentState) {
        this.currentState = currentState;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("createdAt")
    public Long getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("createdAt")
    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updatedAt")
    public Long getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updatedAt")
    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("notifyStudents")
    public Boolean getNotifyStudents() {
        return notifyStudents;
    }

    @JsonProperty("notifyStudents")
    public void setNotifyStudents(Boolean notifyStudents) {
        this.notifyStudents = notifyStudents;
    }

    @JsonProperty("nParticipants")
    public Object getnParticipants() {
        return nParticipants;
    }

    @JsonProperty("nParticipants")
    public void setnParticipants(Object nParticipants) {
        this.nParticipants = nParticipants;
    }

    @JsonProperty("externalScheduleId")
    public String getExternalScheduleId() {
        return externalScheduleId;
    }

    @JsonProperty("externalScheduleId")
    public void setExternalScheduleId(String externalScheduleId) {
        this.externalScheduleId = externalScheduleId;
    }

    @JsonProperty("packageId")
    public Integer getPackageId() {
        return packageId;
    }

    @JsonProperty("packageId")
    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    @JsonProperty("packgaeTitle")
    public String getPackgaeTitle() {
        return packgaeTitle;
    }

    @JsonProperty("packgaeTitle")
    public void setPackgaeTitle(String packgaeTitle) {
        this.packgaeTitle = packgaeTitle;
    }

    @JsonProperty("videoUrl")
    public Object getVideoUrl() {
        return videoUrl;
    }

    @JsonProperty("videoUrl")
    public void setVideoUrl(Object videoUrl) {
        this.videoUrl = videoUrl;
    }

    @JsonProperty("m3u8")
    public Boolean getM3u8() {
        return m3u8;
    }

    @JsonProperty("m3u8")
    public void setM3u8(Boolean m3u8) {
        this.m3u8 = m3u8;
    }

    @JsonProperty("isOneTwoOneEnable")
    public Boolean getIsOneTwoOneEnable() {
        return isOneTwoOneEnable;
    }

    @JsonProperty("isOneTwoOneEnable")
    public void setIsOneTwoOneEnable(Boolean isOneTwoOneEnable) {
        this.isOneTwoOneEnable = isOneTwoOneEnable;
    }

    @JsonProperty("olcType")
    public String getOlcType() {
        return olcType;
    }

    @JsonProperty("olcType")
    public void setOlcType(String olcType) {
        this.olcType = olcType;
    }

    @JsonProperty("youTubeJoinUrl")
    public Object getYouTubeJoinUrl() {
        return youTubeJoinUrl;
    }

    @JsonProperty("youTubeJoinUrl")
    public void setYouTubeJoinUrl(Object youTubeJoinUrl) {
        this.youTubeJoinUrl = youTubeJoinUrl;
    }

    @JsonProperty("youTubeStreamKey")
    public Object getYouTubeStreamKey() {
        return youTubeStreamKey;
    }

    @JsonProperty("youTubeStreamKey")
    public void setYouTubeStreamKey(Object youTubeStreamKey) {
        this.youTubeStreamKey = youTubeStreamKey;
    }

    @JsonProperty("youTubeScheduleType")
    public Object getYouTubeScheduleType() {
        return youTubeScheduleType;
    }

    @JsonProperty("youTubeScheduleType")
    public void setYouTubeScheduleType(Object youTubeScheduleType) {
        this.youTubeScheduleType = youTubeScheduleType;
    }

    @JsonProperty("dpp")
    public Object getDpp() {
        return dpp;
    }

    @JsonProperty("dpp")
    public void setDpp(Object dpp) {
        this.dpp = dpp;
    }

    @JsonProperty("firstName")
    public Object getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(Object firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("lastName")
    public Object getLastName() {
        return lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(Object lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("active")
    public Boolean getActive() {
        return active;
    }

    @JsonProperty("active")
    public void setActive(Boolean active) {
        this.active = active;
    }

    @JsonProperty("cancelled")
    public Boolean getCancelled() {
        return cancelled;
    }

    @JsonProperty("cancelled")
    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    @JsonProperty("rescheduled")
    public Boolean getRescheduled() {
        return rescheduled;
    }

    @JsonProperty("rescheduled")
    public void setRescheduled(Boolean rescheduled) {
        this.rescheduled = rescheduled;
    }

    @JsonProperty("handoutsAvailable")
    public Object getHandoutsAvailable() {
        return handoutsAvailable;
    }

    @JsonProperty("handoutsAvailable")
    public void setHandoutsAvailable(Object handoutsAvailable) {
        this.handoutsAvailable = handoutsAvailable;
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
