package pojo.userLoginResponse;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserLoginResponse {

	@JsonProperty("userInfo")
	private UserInfo userInfo;

	@JsonProperty("jwtToken")
	private String jwtToken;

	@JsonProperty("jwtTokenNew")
	private String jwtTokenNew;

	@JsonProperty("verifierToken")
	private String verifierToken;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public String verifierToken() {
		return verifierToken;
	}

	public String getJwtTokenNew() {
		return jwtTokenNew;
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
