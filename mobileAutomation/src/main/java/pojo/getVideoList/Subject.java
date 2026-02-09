
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
    "cCount",
    "vCount",
    "ch"
})
@Generated("jsonschema2pojo")
public class Subject {

    @JsonProperty("name")
    private String name;
    @JsonProperty("cCount")
    private Integer cCount;
    @JsonProperty("vCount")
    private Integer vCount;
    @JsonProperty("ch")
    private List<Ch> ch;
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

    @JsonProperty("cCount")
    public Integer getcCount() {
        return cCount;
    }

    @JsonProperty("cCount")
    public void setcCount(Integer cCount) {
        this.cCount = cCount;
    }

    @JsonProperty("vCount")
    public Integer getvCount() {
        return vCount;
    }

    @JsonProperty("vCount")
    public void setvCount(Integer vCount) {
        this.vCount = vCount;
    }

    @JsonProperty("ch")
    public List<Ch> getCh() {
        return ch;
    }

    @JsonProperty("ch")
    public void setCh(List<Ch> ch) {
        this.ch = ch;
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
