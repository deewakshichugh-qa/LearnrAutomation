
package pojo.addQuestionPaylod;

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
    "questionId",
    "subQuestionId",
    "positive",
    "negative"
})
@Generated("jsonschema2pojo")
public class Question {

    @JsonProperty("questionId")
    private String questionId;
    @JsonProperty("subQuestionId")
    private String subQuestionId;
    @JsonProperty("positive")
    private Integer positive;
    @JsonProperty("negative")
    private Integer negative;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("questionId")
    public String getQuestionId() {
        return questionId;
    }

    @JsonProperty("questionId")
    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    @JsonProperty("subQuestionId")
    public String getSubQuestionId() {
        return subQuestionId;
    }

    @JsonProperty("subQuestionId")
    public void setSubQuestionId(String subQuestionId) {
        this.subQuestionId = subQuestionId;
    }

    @JsonProperty("positive")
    public Integer getPositive() {
        return positive;
    }

    @JsonProperty("positive")
    public void setPositive(Integer positive) {
        this.positive = positive;
    }

    @JsonProperty("negative")
    public Integer getNegative() {
        return negative;
    }

    @JsonProperty("negative")
    public void setNegative(Integer negative) {
        this.negative = negative;
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
