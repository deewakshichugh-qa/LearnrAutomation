
package pojo.login;

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
    "name",
    "email",
    "refId",
    "socialRefId",
    "phoneNumber",
    "existingUser",
    "jwtToken",
    "jwt256Token"
})
@Generated("jsonschema2pojo")
public class Data {

    @JsonProperty("id")
    private Integer userId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("refId")
    private String refId;
    @JsonProperty("socialRefId")
    private String socialRefId;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("existingUser")
    private Boolean existingUser;
    @JsonProperty("jwtToken")
    private String jwtToken;
    @JsonProperty("jwt256Token")
    private String jwt256Token;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("userId")
    public Integer getUserId() {
        return userId;
    }

    @JsonProperty("userId")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("refId")
    public String getRefId() {
        return refId;
    }

    @JsonProperty("refId")
    public void setRefId(String refId) {
        this.refId = refId;
    }

    @JsonProperty("socialRefId")
    public String getSocialRefId() {
        return socialRefId;
    }

    @JsonProperty("socialRefId")
    public void setSocialRefId(String socialRefId) {
        this.socialRefId = socialRefId;
    }

    @JsonProperty("phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @JsonProperty("phoneNumber")
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @JsonProperty("existingUser")
    public Boolean getExistingUser() {
        return existingUser;
    }

    @JsonProperty("existingUser")
    public void setExistingUser(Boolean existingUser) {
        this.existingUser = existingUser;
    }

    @JsonProperty("jwtToken")
    public String getJwtToken() {
        return jwtToken;
    }

    @JsonProperty("jwtToken")
    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    @JsonProperty("jwt256Token")
    public String getJwt256Token() {
        return jwt256Token;
    }

    @JsonProperty("jwt256Token")
    public void setJwt256Token(String jwt256Token) {
        this.jwt256Token = jwt256Token;
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
