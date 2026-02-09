
package pojo.LiveClassPackageResponse;

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
    "olcFacultyName",
    "primaryPackageId",
    "isPrimary",
    "startDate",
    "endTime",
    "startTime",
    "classRoom",
    "topic",
    "attachement",
    "isCancelled",
    "cancelReason",
    "isRescheduled",
    "rescheduleReason",
    "currentState",
    "description",
    "isActive",
    "createdAt",
    "updatedAt",
    "externalScheduleId",
    "secondaryPackageIds",
    "copiedFromPackageId",
    "videoUrl",
    "isM3u8",
    "thumbnail",
    "freeLiveClassPackages",
    "freeLiveClassCategories",
    "dpp",
    "isClassStarted",
    "videoUploadedByAdmin",
    "freeLiveClass",
    "handoutsAvailable",
    "videoVerified",
    "badStream"
})
@Generated("jsonschema2pojo")
public class Datum {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("olcFacultyId")
    private Integer olcFacultyId;
    @JsonProperty("olcFacultyName")
    private String olcFacultyName;
    @JsonProperty("primaryPackageId")
    private Object primaryPackageId;
    @JsonProperty("isPrimary")
    private Boolean isPrimary;
    @JsonProperty("startDate")
    private String startDate;
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
    @JsonProperty("cancelReason")
    private String cancelReason;
    @JsonProperty("isRescheduled")
    private Boolean isRescheduled;
    @JsonProperty("rescheduleReason")
    private String rescheduleReason;
    @JsonProperty("currentState")
    private Integer currentState;
    @JsonProperty("description")
    private String description;
    @JsonProperty("isActive")
    private Boolean isActive;
    @JsonProperty("createdAt")
    private Long createdAt;
    @JsonProperty("updatedAt")
    private Long updatedAt;
    @JsonProperty("externalScheduleId")
    private String externalScheduleId;
    @JsonProperty("secondaryPackageIds")
    private Object secondaryPackageIds;
    @JsonProperty("copiedFromPackageId")
    private Object copiedFromPackageId;
    @JsonProperty("videoUrl")
    private Object videoUrl;
    @JsonProperty("isM3u8")
    private Boolean isM3u8;
    @JsonProperty("thumbnail")
    private String thumbnail;
    @JsonProperty("freeLiveClassPackages")
    private Object freeLiveClassPackages;
    @JsonProperty("freeLiveClassCategories")
    private Object freeLiveClassCategories;
    @JsonProperty("dpp")
    private Object dpp;
    @JsonProperty("isClassStarted")
    private Boolean isClassStarted;
    @JsonProperty("videoUploadedByAdmin")
    private Object videoUploadedByAdmin;
    @JsonProperty("freeLiveClass")
    private Boolean freeLiveClass;
    @JsonProperty("handoutsAvailable")
    private Object handoutsAvailable;
    @JsonProperty("videoVerified")
    private Boolean videoVerified;
    @JsonProperty("badStream")
    private Object badStream;
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

    @JsonProperty("olcFacultyName")
    public String getOlcFacultyName() {
        return olcFacultyName;
    }

    @JsonProperty("olcFacultyName")
    public void setOlcFacultyName(String olcFacultyName) {
        this.olcFacultyName = olcFacultyName;
    }

    @JsonProperty("primaryPackageId")
    public Object getPrimaryPackageId() {
        return primaryPackageId;
    }

    @JsonProperty("primaryPackageId")
    public void setPrimaryPackageId(Object primaryPackageId) {
        this.primaryPackageId = primaryPackageId;
    }

    @JsonProperty("isPrimary")
    public Boolean getIsPrimary() {
        return isPrimary;
    }

    @JsonProperty("isPrimary")
    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    @JsonProperty("startDate")
    public String getStartDate() {
        return startDate;
    }

    @JsonProperty("startDate")
    public void setStartDate(String startDate) {
        this.startDate = startDate;
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

    @JsonProperty("cancelReason")
    public String getCancelReason() {
        return cancelReason;
    }

    @JsonProperty("cancelReason")
    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    @JsonProperty("isRescheduled")
    public Boolean getIsRescheduled() {
        return isRescheduled;
    }

    @JsonProperty("isRescheduled")
    public void setIsRescheduled(Boolean isRescheduled) {
        this.isRescheduled = isRescheduled;
    }

    @JsonProperty("rescheduleReason")
    public String getRescheduleReason() {
        return rescheduleReason;
    }

    @JsonProperty("rescheduleReason")
    public void setRescheduleReason(String rescheduleReason) {
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

    @JsonProperty("isActive")
    public Boolean getIsActive() {
        return isActive;
    }

    @JsonProperty("isActive")
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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

    @JsonProperty("externalScheduleId")
    public String getExternalScheduleId() {
        return externalScheduleId;
    }

    @JsonProperty("externalScheduleId")
    public void setExternalScheduleId(String externalScheduleId) {
        this.externalScheduleId = externalScheduleId;
    }

    @JsonProperty("secondaryPackageIds")
    public Object getSecondaryPackageIds() {
        return secondaryPackageIds;
    }

    @JsonProperty("secondaryPackageIds")
    public void setSecondaryPackageIds(Object secondaryPackageIds) {
        this.secondaryPackageIds = secondaryPackageIds;
    }

    @JsonProperty("copiedFromPackageId")
    public Object getCopiedFromPackageId() {
        return copiedFromPackageId;
    }

    @JsonProperty("copiedFromPackageId")
    public void setCopiedFromPackageId(Object copiedFromPackageId) {
        this.copiedFromPackageId = copiedFromPackageId;
    }

    @JsonProperty("videoUrl")
    public Object getVideoUrl() {
        return videoUrl;
    }

    @JsonProperty("videoUrl")
    public void setVideoUrl(Object videoUrl) {
        this.videoUrl = videoUrl;
    }

    @JsonProperty("isM3u8")
    public Boolean getIsM3u8() {
        return isM3u8;
    }

    @JsonProperty("isM3u8")
    public void setIsM3u8(Boolean isM3u8) {
        this.isM3u8 = isM3u8;
    }

    @JsonProperty("thumbnail")
    public String getThumbnail() {
        return thumbnail;
    }

    @JsonProperty("thumbnail")
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @JsonProperty("freeLiveClassPackages")
    public Object getFreeLiveClassPackages() {
        return freeLiveClassPackages;
    }

    @JsonProperty("freeLiveClassPackages")
    public void setFreeLiveClassPackages(Object freeLiveClassPackages) {
        this.freeLiveClassPackages = freeLiveClassPackages;
    }

    @JsonProperty("freeLiveClassCategories")
    public Object getFreeLiveClassCategories() {
        return freeLiveClassCategories;
    }

    @JsonProperty("freeLiveClassCategories")
    public void setFreeLiveClassCategories(Object freeLiveClassCategories) {
        this.freeLiveClassCategories = freeLiveClassCategories;
    }

    @JsonProperty("dpp")
    public Object getDpp() {
        return dpp;
    }

    @JsonProperty("dpp")
    public void setDpp(Object dpp) {
        this.dpp = dpp;
    }

    @JsonProperty("isClassStarted")
    public Boolean getIsClassStarted() {
        return isClassStarted;
    }

    @JsonProperty("isClassStarted")
    public void setIsClassStarted(Boolean isClassStarted) {
        this.isClassStarted = isClassStarted;
    }

    @JsonProperty("videoUploadedByAdmin")
    public Object getVideoUploadedByAdmin() {
        return videoUploadedByAdmin;
    }

    @JsonProperty("videoUploadedByAdmin")
    public void setVideoUploadedByAdmin(Object videoUploadedByAdmin) {
        this.videoUploadedByAdmin = videoUploadedByAdmin;
    }

    @JsonProperty("freeLiveClass")
    public Boolean getFreeLiveClass() {
        return freeLiveClass;
    }

    @JsonProperty("freeLiveClass")
    public void setFreeLiveClass(Boolean freeLiveClass) {
        this.freeLiveClass = freeLiveClass;
    }

    @JsonProperty("handoutsAvailable")
    public Object getHandoutsAvailable() {
        return handoutsAvailable;
    }

    @JsonProperty("handoutsAvailable")
    public void setHandoutsAvailable(Object handoutsAvailable) {
        this.handoutsAvailable = handoutsAvailable;
    }

    @JsonProperty("videoVerified")
    public Boolean getVideoVerified() {
        return videoVerified;
    }

    @JsonProperty("videoVerified")
    public void setVideoVerified(Boolean videoVerified) {
        this.videoVerified = videoVerified;
    }

    @JsonProperty("badStream")
    public Object getBadStream() {
        return badStream;
    }

    @JsonProperty("badStream")
    public void setBadStream(Object badStream) {
        this.badStream = badStream;
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
