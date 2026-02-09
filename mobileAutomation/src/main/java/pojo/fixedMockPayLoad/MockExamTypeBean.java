
package pojo.fixedMockPayLoad;

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
    "examtype",
    "id",
    "mockTestId",
    "examCategoryId"
})
@Generated("jsonschema2pojo")
public class MockExamTypeBean {

    @JsonProperty("examtype")
    private String examtype;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("mockTestId")
    private Integer mockTestId;
    @JsonProperty("examCategoryId")
    private Integer examCategoryId;
    public Integer getExamCategoryId() {
		return examCategoryId;
	}

	public void setExamCategoryId(Integer examCategoryId) {
		this.examCategoryId = examCategoryId;
	}

	@JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("examtype")
    public String getExamtype() {
        return examtype;
    }

    @JsonProperty("examtype")
    public void setExamtype(String examtype) {
        this.examtype = examtype;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("mockTestId")
    public Integer getMockTestId() {
        return mockTestId;
    }

    @JsonProperty("mockTestId")
    public void setMockTestId(Integer mockTestId) {
        this.mockTestId = mockTestId;
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
