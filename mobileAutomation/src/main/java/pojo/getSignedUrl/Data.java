
package pojo.getSignedUrl;

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
    "signedUrl",
    "tempS3Url"
})
@Generated("jsonschema2pojo")
public class Data {

    @JsonProperty("signedUrl")
    private String signedUrl;
    @JsonProperty("tempS3Url")
    private String tempS3Url;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("signedUrl")
    public String getSignedUrl() {
        return signedUrl;
    }

    @JsonProperty("signedUrl")
    public void setSignedUrl(String signedUrl) {
        this.signedUrl = signedUrl;
    }

    @JsonProperty("tempS3Url")
    public String getTempS3Url() {
        return tempS3Url;
    }

    @JsonProperty("tempS3Url")
    public void setTempS3Url(String tempS3Url) {
        this.tempS3Url = tempS3Url;
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
