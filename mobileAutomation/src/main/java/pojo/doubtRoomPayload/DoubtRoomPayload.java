
package pojo.doubtRoomPayload;

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
    "id",
    "facultyId",
    "startDate",
    "endDate",
    "topic",
    "cancelled",
    "cancelReason",
    "rescheduled",
    "rescheduleReason",
    "externalScheduleId",
    "active",
    "currentState",
    "chatGroupName",
    "subjectList",
    "packageIds",
    "slotTimings"
})
@Generated("jsonschema2pojo")
public class DoubtRoomPayload {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("facultyId")
    private Object facultyId;
    @JsonProperty("startDate")
    private String startDate;
    @JsonProperty("endDate")
    private String endDate;
    @JsonProperty("topic")
    private Object topic;
    @JsonProperty("cancelled")
    private Boolean cancelled;
    @JsonProperty("cancelReason")
    private String cancelReason;
    @JsonProperty("rescheduled")
    private Boolean rescheduled;
    @JsonProperty("rescheduleReason")
    private String rescheduleReason;
    @JsonProperty("externalScheduleId")
    private String externalScheduleId;
    @JsonProperty("active")
    private Boolean active;
    @JsonProperty("currentState")
    private String currentState;
    @JsonProperty("chatGroupName")
    private Object chatGroupName;
    @JsonProperty("subjectList")
    private List<Subject> subjectList;
    @JsonProperty("packageIds")
    private List<Integer> packageIds;
    @JsonProperty("slotTimings")
    private List<SlotTiming> slotTimings;
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

    @JsonProperty("facultyId")
    public Object getFacultyId() {
        return facultyId;
    }

    @JsonProperty("facultyId")
    public void setFacultyId(Object facultyId) {
        this.facultyId = facultyId;
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

    @JsonProperty("topic")
    public Object getTopic() {
        return topic;
    }

    @JsonProperty("topic")
    public void setTopic(Object topic) {
        this.topic = topic;
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
    public String getExternalScheduleId() {
        return externalScheduleId;
    }

    @JsonProperty("externalScheduleId")
    public void setExternalScheduleId(String externalScheduleId) {
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

    @JsonProperty("chatGroupName")
    public Object getChatGroupName() {
        return chatGroupName;
    }

    @JsonProperty("chatGroupName")
    public void setChatGroupName(Object chatGroupName) {
        this.chatGroupName = chatGroupName;
    }

    @JsonProperty("subjectList")
    public List<Subject> getSubjectList() {
        return subjectList;
    }

    @JsonProperty("subjectList")
    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    @JsonProperty("packageIds")
    public List<Integer> getPackageIds() {
        return packageIds;
    }

    @JsonProperty("packageIds")
    public void setPackageIds(List<Integer> packageIds) {
        this.packageIds = packageIds;
    }

    @JsonProperty("slotTimings")
    public List<SlotTiming> getSlotTimings() {
        return slotTimings;
    }

    @JsonProperty("slotTimings")
    public void setSlotTimings(List<SlotTiming> slotTimings) {
        this.slotTimings = slotTimings;
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
