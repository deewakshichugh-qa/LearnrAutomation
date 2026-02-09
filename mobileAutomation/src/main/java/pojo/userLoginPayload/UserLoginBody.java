package pojo.userLoginPayload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserLoginBody {

	@JsonProperty("phone")
	private String phone;
	@JsonProperty("email")
	private String email;
	@JsonProperty("providerName")
	private String providerName;
	@JsonProperty("sec")
	private String sec;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getSec() {
		return sec;
	}

	public void setSec(String sec) {
		this.sec = sec;
	}
	
    // Override toString() method
    @Override
    public String toString() {
        return "UserLoginBody{" +
                "phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", providerName='" + providerName + '\'' +
                ", sec='" + sec + '\'' +
                '}';
    }
}
