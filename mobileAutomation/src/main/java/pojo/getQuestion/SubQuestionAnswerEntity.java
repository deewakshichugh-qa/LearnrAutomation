
package pojo.getQuestion;

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
    "answer",
    "subAnswerId",
    "subExplanation",
    "explanationText",
    "createdAt",
    "modifiedAt",
    "createdBy",
    "modifiedBy"
})
@Generated("jsonschema2pojo")
public class SubQuestionAnswerEntity {

    @JsonProperty("questionId")
    private String questionId;
    @JsonProperty("subQuestionId")
    private String subQuestionId;
    @JsonProperty("answer")
    private String answer;
    @JsonProperty("subAnswerId")
    private Object subAnswerId;
    @JsonProperty("subExplanation")
    private SubExplanation subExplanation;
    @JsonProperty("explanationText")
    private String explanationText;
    @JsonProperty("createdAt")
    private Integer createdAt;
    @JsonProperty("modifiedAt")
    private Integer modifiedAt;
    @JsonProperty("createdBy")
    private String createdBy;
    @JsonProperty("modifiedBy")
    private Object modifiedBy;
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

    @JsonProperty("answer")
    public String getAnswer() {
        return answer;
    }

    @JsonProperty("answer")
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @JsonProperty("subAnswerId")
    public Object getSubAnswerId() {
        return subAnswerId;
    }

    @JsonProperty("subAnswerId")
    public void setSubAnswerId(Object subAnswerId) {
        this.subAnswerId = subAnswerId;
    }

    @JsonProperty("subExplanation")
    public SubExplanation getSubExplanation() {
        return subExplanation;
    }

    @JsonProperty("subExplanation")
    public void setSubExplanation(SubExplanation subExplanation) {
        this.subExplanation = subExplanation;
    }

    @JsonProperty("explanationText")
    public String getExplanationText() {
        return explanationText;
    }

    @JsonProperty("explanationText")
    public void setExplanationText(String explanationText) {
        this.explanationText = explanationText;
    }

    @JsonProperty("createdAt")
    public Integer getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("createdAt")
    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("modifiedAt")
    public Integer getModifiedAt() {
        return modifiedAt;
    }

    @JsonProperty("modifiedAt")
    public void setModifiedAt(Integer modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @JsonProperty("createdBy")
    public String getCreatedBy() {
        return createdBy;
    }

    @JsonProperty("createdBy")
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @JsonProperty("modifiedBy")
    public Object getModifiedBy() {
        return modifiedBy;
    }

    @JsonProperty("modifiedBy")
    public void setModifiedBy(Object modifiedBy) {
        this.modifiedBy = modifiedBy;
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
