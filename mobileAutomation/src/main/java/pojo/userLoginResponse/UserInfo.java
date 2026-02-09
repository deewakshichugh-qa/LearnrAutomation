package pojo.userLoginResponse;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfo {
	
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("hasPasswaord")
    private boolean hasPasswaord;
	@JsonProperty("status")
    private int status;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("phoneVerified")
    private boolean phoneVerified;
    @JsonProperty("discourseUserId")
    private String discourseUserId;
    @JsonProperty("socialUserId")
    private String socialUserId;
    @JsonProperty("profilePicUrl")
    private String profilePicUrl;
	@JsonProperty("referralId")
    private String referralId;
    @JsonProperty("coins")
    private double coins;
    @JsonProperty("category")
    private String category;  
    @JsonProperty("dateOfBirth")
    private String dateOfBirth;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isHasPasswaord() {
        return hasPasswaord;
    }

    public int getStatus() {
        return status;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isPhoneVerified() {
        return phoneVerified;
    }

    public String getDiscourseUserId() {
        return discourseUserId;
    }

    public String getSocialUserId() {
        return socialUserId;
    }
    
    public String getProfilePicUrl() {
  		return profilePicUrl;
  	}

    public String getReferralId() {
        return referralId;
    }

    public double getCoins() {
        return coins;
    }
    
    public String getCategory() {
		return category;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
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
