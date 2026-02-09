
package pojo.deepLinkData;

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
    "deeplink",
    "deeplinkType",
    "redirection"
})
@Generated("jsonschema2pojo")
public class DeepLinkData {

    @JsonProperty("deeplink")
    private String deeplink;
    @JsonProperty("deeplinkType")
    private String deeplinkType;
    @JsonProperty("redirection")
    private String redirection;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("deeplink")
    public String getDeeplink() {
        return deeplink;
    }

    @JsonProperty("deeplink")
    public void setDeeplink(String deeplink) {
        this.deeplink = deeplink;
    }

    @JsonProperty("deeplinkType")
    public String getDeeplinkType() {
        return deeplinkType;
    }

    @JsonProperty("deeplinkType")
    public void setDeeplinkType(String deeplinkType) {
        this.deeplinkType = deeplinkType;
    }

    @JsonProperty("redirection")
    public String getRedirection() {
        return redirection;
    }

    @JsonProperty("redirection")
    public void setRedirection(String redirection) {
        this.redirection = redirection;
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
