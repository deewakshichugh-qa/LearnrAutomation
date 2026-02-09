
package pojo.getVideoList;

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
    "name",
    "video",
    "demo"
})
@Generated("jsonschema2pojo")
public class Ch {

    @JsonProperty("name")
    private String name;
    @JsonProperty("video")
    private List<Video> video;
    @JsonProperty("demo")
    private Boolean demo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("video")
    public List<Video> getVideo() {
        return video;
    }

    @JsonProperty("video")
    public void setVideo(List<Video> video) {
        this.video = video;
    }

    @JsonProperty("demo")
    public Boolean getDemo() {
        return demo;
    }

    @JsonProperty("demo")
    public void setDemo(Boolean demo) {
        this.demo = demo;
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
