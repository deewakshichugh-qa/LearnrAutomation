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
    "categoryId",
    "count",
    "course",
    "thumbnail",
    "category",
    "vduration"
})
@Generated("jsonschema2pojo")
public class SubCategory {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("categoryId")
    private Integer categoryId;
    @JsonProperty("count")
    private Integer count;
    @JsonProperty("course")
    private Object course;
    @JsonProperty("thumbnail")
    private Object thumbnail;
    @JsonProperty("category")
    private String category;
    @JsonProperty("vduration")
    private Object vduration;
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

    @JsonProperty("categoryId")
    public Integer getCategoryId() {
        return categoryId;
    }

    @JsonProperty("categoryId")
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @JsonProperty("count")
    public Integer getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(Integer count) {
        this.count = count;
    }

    @JsonProperty("course")
    public Object getCourse() {
        return course;
    }

    @JsonProperty("course")
    public void setCourse(Object course) {
        this.course = course;
    }

    @JsonProperty("thumbnail")
    public Object getThumbnail() {
        return thumbnail;
    }

    @JsonProperty("thumbnail")
    public void setThumbnail(Object thumbnail) {
        this.thumbnail = thumbnail;
    }

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty("vduration")
    public Object getVduration() {
        return vduration;
    }

    @JsonProperty("vduration")
    public void setVduration(Object vduration) {
        this.vduration = vduration;
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
