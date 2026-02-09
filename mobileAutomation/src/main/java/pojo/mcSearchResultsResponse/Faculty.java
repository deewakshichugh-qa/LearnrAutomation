package pojo.mcSearchResultsResponse;

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
    "createdAt",
    "updatedAt",
    "name",
    "image",
    "designation",
    "topic",
    "hoursTaught",
    "experience"
})
@Generated("jsonschema2pojo")
public class Faculty {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("createdAt")
    private Object createdAt;
    @JsonProperty("updatedAt")
    private Object updatedAt;
    @JsonProperty("name")
    private String name;
    @JsonProperty("image")
    private Object image;
    @JsonProperty("designation")
    private Object designation;
    @JsonProperty("topic")
    private Object topic;
    @JsonProperty("hoursTaught")
    private Object hoursTaught;
    @JsonProperty("experience")
    private Object experience;
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

    @JsonProperty("createdAt")
    public Object getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("createdAt")
    public void setCreatedAt(Object createdAt) {
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

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("image")
    public Object getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(Object image) {
        this.image = image;
    }

    @JsonProperty("designation")
    public Object getDesignation() {
        return designation;
    }

    @JsonProperty("designation")
    public void setDesignation(Object designation) {
        this.designation = designation;
    }

    @JsonProperty("topic")
    public Object getTopic() {
        return topic;
    }

    @JsonProperty("topic")
    public void setTopic(Object topic) {
        this.topic = topic;
    }

    @JsonProperty("hoursTaught")
    public Object getHoursTaught() {
        return hoursTaught;
    }

    @JsonProperty("hoursTaught")
    public void setHoursTaught(Object hoursTaught) {
        this.hoursTaught = hoursTaught;
    }

    @JsonProperty("experience")
    public Object getExperience() {
        return experience;
    }

    @JsonProperty("experience")
    public void setExperience(Object experience) {
        this.experience = experience;
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

