
package pojo.adminLogin;

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
    "lcsdoubtToken",
    "facultyToken",
    "success",
    "name",
    "email",
    "allowedSections",
    "role",
    "id",
    "token",
    "faculty_id"
})
@Generated("jsonschema2pojo")
public class AdminLogin {

    @JsonProperty("lcsdoubtToken")
    private String lcsdoubtToken;
    @JsonProperty("facultyToken")
    private String facultyToken;
    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("allowedSections")
    private String allowedSections;
    @JsonProperty("role")
    private List<Role> role;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("token")
    private String token;
    @JsonProperty("faculty_id")
    private Integer facultyId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("lcsdoubtToken")
    public String getLcsdoubtToken() {
        return lcsdoubtToken;
    }

    @JsonProperty("lcsdoubtToken")
    public void setLcsdoubtToken(String lcsdoubtToken) {
        this.lcsdoubtToken = lcsdoubtToken;
    }

    @JsonProperty("facultyToken")
    public String getFacultyToken() {
        return facultyToken;
    }

    @JsonProperty("facultyToken")
    public void setFacultyToken(String facultyToken) {
        this.facultyToken = facultyToken;
    }

    @JsonProperty("success")
    public Boolean getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(Boolean success) {
        this.success = success;
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

    @JsonProperty("allowedSections")
    public String getAllowedSections() {
        return allowedSections;
    }

    @JsonProperty("allowedSections")
    public void setAllowedSections(String allowedSections) {
        this.allowedSections = allowedSections;
    }

    @JsonProperty("role")
    public List<Role> getRole() {
        return role;
    }

    @JsonProperty("role")
    public void setRole(List<Role> role) {
        this.role = role;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("token")
    public String getToken() {
        return token;
    }

    @JsonProperty("token")
    public void setToken(String token) {
        this.token = token;
    }

    @JsonProperty("faculty_id")
    public Integer getFacultyId() {
        return facultyId;
    }

    @JsonProperty("faculty_id")
    public void setFacultyId(Integer facultyId) {
        this.facultyId = facultyId;
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
