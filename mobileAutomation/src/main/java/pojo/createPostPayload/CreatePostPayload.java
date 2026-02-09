
package pojo.createPostPayload;

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
    "content",
    "topicTag",
    "taggedUsers",
    "postType",
    "attachment"
})
@Generated("jsonschema2pojo")
public class CreatePostPayload {

    @JsonProperty("groupId")
    private String groupId;
    @JsonProperty("content")
    private String content;
    @JsonProperty("topicTag")
    private String topicTag;
    @JsonProperty("taggedUsers")
    private List<String> taggedUsers = null;
    @JsonProperty("postType")
    private String postType;
    @JsonProperty("attachment")
    private List<Attachment> attachment = null;
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

    @JsonProperty("content")
    public String getContent() {
        return content;
    }

    @JsonProperty("content")
    public void setContent(String content) {
        this.content = content;
    }

    @JsonProperty("topicTag")
    public String getTopicTag() {
        return topicTag;
    }

    @JsonProperty("topicTag")
    public void setTopicTag(String topicTag) {
        this.topicTag = topicTag;
    }

    @JsonProperty("taggedUsers")
    public List<String> getTaggedUsers() {
        return taggedUsers;
    }

    @JsonProperty("taggedUsers")
    public void setTaggedUsers(List<String> taggedUsers) {
        this.taggedUsers = taggedUsers;
    }

    @JsonProperty("postType")
    public String getPostType() {
        return postType;
    }

    @JsonProperty("postType")
    public void setPostType(String postType) {
        this.postType = postType;
    }

    @JsonProperty("attachment")
    public List<Attachment> getAttachment() {
        return attachment;
    }

    @JsonProperty("attachment")
    public void setAttachment(List<Attachment> attachment) {
        this.attachment = attachment;
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
