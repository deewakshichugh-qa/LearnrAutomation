
package pojo.getVideoList;

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
    "name",
    "sub",
    "imgUrl"
})
@Generated("jsonschema2pojo")
public class Faculty {

    @JsonProperty("name")
    private String name;
    @JsonProperty("sub")
    private Object sub;
    @JsonProperty("imgUrl")
    private String imgUrl;
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

    @JsonProperty("sub")
    public Object getSub() {
        return sub;
    }

    @JsonProperty("sub")
    public void setSub(Object sub) {
        this.sub = sub;
    }

    @JsonProperty("imgUrl")
    public String getImgUrl() {
        return imgUrl;
    }

    @JsonProperty("imgUrl")
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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
