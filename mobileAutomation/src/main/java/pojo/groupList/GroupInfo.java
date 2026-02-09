
package pojo.groupList;

import java.util.HashMap;
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
    "groupId",
    "categoryIdentifier",
    "categoryL1Id",
    "categoryL2Id",
    "topicTags",
    "description",
    "groupName",
    "groupIcon",
    "groupType",
    "memberPostAllowed",
    "createdAt",
    "updatedAt",
    "createdBy",
    "isActive",
    "lastModifiedBy"
})
@Generated("jsonschema2pojo")
public class GroupInfo {

    @JsonProperty("groupId")
    private String groupId;
    @JsonProperty("categoryIdentifier")
    private String categoryIdentifier;
    @JsonProperty("categoryL1Id")
    private String categoryL1Id;
    @JsonProperty("categoryL2Id")
    private String categoryL2Id;
    @JsonProperty("topicTags")
    private List<TopicTag> topicTags = null;
    @JsonProperty("description")
    private String description;
    @JsonProperty("groupName")
    private String groupName;
    @JsonProperty("groupIcon")
    private String groupIcon;
    @JsonProperty("groupType")
    private String groupType;
    @JsonProperty("memberPostAllowed")
    private Boolean memberPostAllowed;
    @JsonProperty("createdAt")
    private Long createdAt;
    @JsonProperty("updatedAt")
    private Object updatedAt;
    @JsonProperty("createdBy")
    private String createdBy;
    @JsonProperty("isActive")
    private Boolean isActive;
    @JsonProperty("lastModifiedBy")
    private Object lastModifiedBy;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("groupId")
    public String getGroupId() {
        return groupId;
    }

    @JsonProperty("groupId")
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @JsonProperty("categoryIdentifier")
    public String getCategoryIdentifier() {
        return categoryIdentifier;
    }

    @JsonProperty("categoryIdentifier")
    public void setCategoryIdentifier(String categoryIdentifier) {
        this.categoryIdentifier = categoryIdentifier;
    }

    @JsonProperty("categoryL1Id")
    public String getCategoryL1Id() {
        return categoryL1Id;
    }

    @JsonProperty("categoryL1Id")
    public void setCategoryL1Id(String categoryL1Id) {
        this.categoryL1Id = categoryL1Id;
    }

    @JsonProperty("categoryL2Id")
    public String getCategoryL2Id() {
        return categoryL2Id;
    }

    @JsonProperty("categoryL2Id")
    public void setCategoryL2Id(String categoryL2Id) {
        this.categoryL2Id = categoryL2Id;
    }

    @JsonProperty("topicTags")
    public List<TopicTag> getTopicTags() {
        return topicTags;
    }

    @JsonProperty("topicTags")
    public void setTopicTags(List<TopicTag> topicTags) {
        this.topicTags = topicTags;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("groupName")
    public String getGroupName() {
        return groupName;
    }

    @JsonProperty("groupName")
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @JsonProperty("groupIcon")
    public String getGroupIcon() {
        return groupIcon;
    }

    @JsonProperty("groupIcon")
    public void setGroupIcon(String groupIcon) {
        this.groupIcon = groupIcon;
    }

    @JsonProperty("groupType")
    public String getGroupType() {
        return groupType;
    }

    @JsonProperty("groupType")
    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    @JsonProperty("memberPostAllowed")
    public Boolean getMemberPostAllowed() {
        return memberPostAllowed;
    }

    @JsonProperty("memberPostAllowed")
    public void setMemberPostAllowed(Boolean memberPostAllowed) {
        this.memberPostAllowed = memberPostAllowed;
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
    public Object getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updatedAt")
    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("createdBy")
    public String getCreatedBy() {
        return createdBy;
    }

    @JsonProperty("createdBy")
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @JsonProperty("isActive")
    public Boolean getIsActive() {
        return isActive;
    }

    @JsonProperty("isActive")
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @JsonProperty("lastModifiedBy")
    public Object getLastModifiedBy() {
        return lastModifiedBy;
    }

    @JsonProperty("lastModifiedBy")
    public void setLastModifiedBy(Object lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
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
