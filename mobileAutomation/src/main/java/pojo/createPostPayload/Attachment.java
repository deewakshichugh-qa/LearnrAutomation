
package pojo.createPostPayload;

import java.util.HashMap;
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
    "attachmentType",
    "attachmentLink"
})
@Generated("jsonschema2pojo")
public class Attachment {

    @JsonProperty("attachmentType")
    private String attachmentType;
    @JsonProperty("attachmentLink")
    private String attachmentLink;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("attachmentType")
    public String getAttachmentType() {
        return attachmentType;
    }

    @JsonProperty("attachmentType")
    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType;
    }

    @JsonProperty("attachmentLink")
    public String getAttachmentLink() {
        return attachmentLink;
    }

    @JsonProperty("attachmentLink")
    public void setAttachmentLink(String attachmentLink) {
        this.attachmentLink = attachmentLink;
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
