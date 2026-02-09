
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
    "sideData",
    "bankUrls",
    "cD",
    "languages"
})
@Generated("jsonschema2pojo")
public class ChildJson {

    @JsonProperty("sideData")
    private Object sideData;
    @JsonProperty("bankUrls")
    private List<String> bankUrls;
    @JsonProperty("cD")
    private List<Cd> cD;
    @JsonProperty("languages")
    private List<String> languages;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("sideData")
    public Object getSideData() {
        return sideData;
    }

    @JsonProperty("sideData")
    public void setSideData(Object sideData) {
        this.sideData = sideData;
    }

    @JsonProperty("bankUrls")
    public List<String> getBankUrls() {
        return bankUrls;
    }

    @JsonProperty("bankUrls")
    public void setBankUrls(List<String> bankUrls) {
        this.bankUrls = bankUrls;
    }

    @JsonProperty("cD")
    public List<Cd> getcD() {
        return cD;
    }

    @JsonProperty("cD")
    public void setcD(List<Cd> cD) {
        this.cD = cD;
    }

    @JsonProperty("languages")
    public List<String> getLanguages() {
        return languages;
    }

    @JsonProperty("languages")
    public void setLanguages(List<String> languages) {
        this.languages = languages;
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
