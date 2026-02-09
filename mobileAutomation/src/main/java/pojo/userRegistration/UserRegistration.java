
package pojo.userRegistration;

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
    "userInfo",
    "jwtToken",
    "jwtTokenNew"
})
@Generated("jsonschema2pojo")
public class UserRegistration {

    @JsonProperty("userInfo")
    private UserInfo userInfo;
    @JsonProperty("jwtToken")
    private String jwtToken;
    @JsonProperty("jwtTokenNew")
    private String jwtTokenNew;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("userInfo")
    public UserInfo getUserInfo() {
        return userInfo;
    }

    @JsonProperty("userInfo")
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @JsonProperty("jwtToken")
    public String getJwtToken() {
        return jwtToken;
    }

    @JsonProperty("jwtToken")
    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    @JsonProperty("jwtTokenNew")
    public String getJwtTokenNew() {
        return jwtTokenNew;
    }

    @JsonProperty("jwtTokenNew")
    public void setJwtTokenNew(String jwtTokenNew) {
        this.jwtTokenNew = jwtTokenNew;
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
