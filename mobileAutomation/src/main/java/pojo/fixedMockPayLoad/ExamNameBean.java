
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
    "examNameTagName",
    "id",
    "mockTestId",
    "examNameTagId"
})
@Generated("jsonschema2pojo")
public class ExamNameBean {

    @JsonProperty("examNameTagName")
    private String examNameTagName;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("mockTestId")
    private Integer mockTestId;
    @JsonProperty("examNameTagId")
    private Integer examNameTagId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("examNameTagName")
    public String getExamNameTagName() {
        return examNameTagName;
    }

    @JsonProperty("examNameTagName")
    public void setExamNameTagName(String examNameTagName) {
        this.examNameTagName = examNameTagName;
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

    @JsonProperty("examNameTagId")
    public Integer getExamNameTagId() {
        return examNameTagId;
    }

    @JsonProperty("examNameTagId")
    public void setExamNameTagId(Integer examNameTagId) {
        this.examNameTagId = examNameTagId;
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
