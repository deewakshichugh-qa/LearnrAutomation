
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
    "purchased",
    "title",
    "updAt",
    "type",
    "cc"
})
@Generated("jsonschema2pojo")
public class Bilingual {

    @JsonProperty("purchased")
    private Boolean purchased;
    @JsonProperty("title")
    private String title;
    @JsonProperty("updAt")
    private Integer updAt;
    @JsonProperty("type")
    private Integer type;
    @JsonProperty("cc")
    private Cc cc;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("purchased")
    public Boolean getPurchased() {
        return purchased;
    }

    @JsonProperty("purchased")
    public void setPurchased(Boolean purchased) {
        this.purchased = purchased;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("updAt")
    public Integer getUpdAt() {
        return updAt;
    }

    @JsonProperty("updAt")
    public void setUpdAt(Integer updAt) {
        this.updAt = updAt;
    }

    @JsonProperty("type")
    public Integer getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(Integer type) {
        this.type = type;
    }

    @JsonProperty("cc")
    public Cc getCc() {
        return cc;
    }

    @JsonProperty("cc")
    public void setCc(Cc cc) {
        this.cc = cc;
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
