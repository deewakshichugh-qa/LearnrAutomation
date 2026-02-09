
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
    "id",
    "name",
    "email",
    "status",
    "phoneVerified",
    "socialUserId",
    "coins"
})
@Generated("jsonschema2pojo")
public class UserInfo {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("phoneVerified")
    private Boolean phoneVerified;
    @JsonProperty("socialUserId")
    private String socialUserId;
    @JsonProperty("coins")
    private Double coins;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
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

    @JsonProperty("status")
    public Integer getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    @JsonProperty("phoneVerified")
    public Boolean getPhoneVerified() {
        return phoneVerified;
    }

    @JsonProperty("phoneVerified")
    public void setPhoneVerified(Boolean phoneVerified) {
        this.phoneVerified = phoneVerified;
    }

    @JsonProperty("socialUserId")
    public String getSocialUserId() {
        return socialUserId;
    }

    @JsonProperty("socialUserId")
    public void setSocialUserId(String socialUserId) {
        this.socialUserId = socialUserId;
    }

    @JsonProperty("coins")
    public Double getCoins() {
        return coins;
    }

    @JsonProperty("coins")
    public void setCoins(Double coins) {
        this.coins = coins;
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
