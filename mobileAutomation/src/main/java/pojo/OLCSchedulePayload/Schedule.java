
package pojo.OLCSchedulePayload;

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
    "olcFacultyId",
    "startDate",
    "startTime",
    "endTime",
    "topic",
    "classRoom",
    "attachement",
    "cancelled",
    "cancelReason",
    "rescheduled",
    "rescheduleReason",
    "externalScheduleId",
    "active",
    "currentState",
    "description",
    "isOneTwoOneEnable"
})
@Generated("jsonschema2pojo")
public class Schedule {

    @JsonProperty("olcFacultyId")
    private Integer olcFacultyId;
    @JsonProperty("startDate")
    private String startDate;
    @JsonProperty("startTime")
    private String startTime;
    @JsonProperty("endTime")
    private String endTime;
    @JsonProperty("topic")
    private String topic;
    @JsonProperty("classRoom")
    private String classRoom;
    @JsonProperty("attachement")
    private String attachement;
    @JsonProperty("cancelled")
    private Boolean cancelled;
    @JsonProperty("cancelReason")
    private String cancelReason;
    @JsonProperty("rescheduled")
    private Boolean rescheduled;
    @JsonProperty("rescheduleReason")
    private String rescheduleReason;
    @JsonProperty("externalScheduleId")
    private Integer externalScheduleId;
    @JsonProperty("active")
    private Boolean active;
    @JsonProperty("currentState")
    private String currentState;
    @JsonProperty("description")
    private String description;
    @JsonProperty("isOneTwoOneEnable")
    private Boolean isOneTwoOneEnable;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

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

    @JsonProperty("startTime")
    public String getStartTime() {
        return startTime;
    }

    @JsonProperty("startTime")
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @JsonProperty("endTime")
    public String getEndTime() {
        return endTime;
    }

    @JsonProperty("endTime")
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @JsonProperty("topic")
    public String getTopic() {
        return topic;
    }

    @JsonProperty("topic")
    public void setTopic(String topic) {
        this.topic = topic;
    }

    @JsonProperty("classRoom")
    public String getClassRoom() {
        return classRoom;
    }

    @JsonProperty("classRoom")
    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    @JsonProperty("attachement")
    public String getAttachement() {
        return attachement;
    }

    @JsonProperty("attachement")
    public void setAttachement(String attachement) {
        this.attachement = attachement;
    }

    @JsonProperty("cancelled")
    public Boolean getCancelled() {
        return cancelled;
    }

    @JsonProperty("cancelled")
    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    @JsonProperty("cancelReason")
    public String getCancelReason() {
        return cancelReason;
    }

    @JsonProperty("cancelReason")
    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    @JsonProperty("rescheduled")
    public Boolean getRescheduled() {
        return rescheduled;
    }

    @JsonProperty("rescheduled")
    public void setRescheduled(Boolean rescheduled) {
        this.rescheduled = rescheduled;
    }

    @JsonProperty("rescheduleReason")
    public String getRescheduleReason() {
        return rescheduleReason;
    }

    @JsonProperty("rescheduleReason")
    public void setRescheduleReason(String rescheduleReason) {
        this.rescheduleReason = rescheduleReason;
    }

    @JsonProperty("externalScheduleId")
    public Integer getExternalScheduleId() {
        return externalScheduleId;
    }

    @JsonProperty("externalScheduleId")
    public void setExternalScheduleId(Integer externalScheduleId) {
        this.externalScheduleId = externalScheduleId;
    }

    @JsonProperty("active")
    public Boolean getActive() {
        return active;
    }

    @JsonProperty("active")
    public void setActive(Boolean active) {
        this.active = active;
    }

    @JsonProperty("currentState")
    public String getCurrentState() {
        return currentState;
    }

    @JsonProperty("currentState")
    public void setCurrentState(String currentState) {
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

    @JsonProperty("isOneTwoOneEnable")
    public Boolean getIsOneTwoOneEnable() {
        return isOneTwoOneEnable;
    }

    @JsonProperty("isOneTwoOneEnable")
    public void setIsOneTwoOneEnable(Boolean isOneTwoOneEnable) {
        this.isOneTwoOneEnable = isOneTwoOneEnable;
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
