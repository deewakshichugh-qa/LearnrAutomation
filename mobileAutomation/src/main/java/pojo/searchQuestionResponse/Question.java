
package pojo.searchQuestionResponse;

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
    "question",
    "subQuestionEntity",
    "questionMetaData",
    "percentageMatch"
})
@Generated("jsonschema2pojo")
public class Question {

    @JsonProperty("question")
    private Question__1 question;
    @JsonProperty("subQuestionEntity")
    private List<SubQuestionEntity> subQuestionEntity;
    @JsonProperty("questionMetaData")
    private QuestionMetaData questionMetaData;
    @JsonProperty("percentageMatch")
    private Double percentageMatch;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("question")
    public Question__1 getQuestion() {
        return question;
    }

    @JsonProperty("question")
    public void setQuestion(Question__1 question) {
        this.question = question;
    }

    @JsonProperty("subQuestionEntity")
    public List<SubQuestionEntity> getSubQuestionEntity() {
        return subQuestionEntity;
    }

    @JsonProperty("subQuestionEntity")
    public void setSubQuestionEntity(List<SubQuestionEntity> subQuestionEntity) {
        this.subQuestionEntity = subQuestionEntity;
    }

    @JsonProperty("questionMetaData")
    public QuestionMetaData getQuestionMetaData() {
        return questionMetaData;
    }

    @JsonProperty("questionMetaData")
    public void setQuestionMetaData(QuestionMetaData questionMetaData) {
        this.questionMetaData = questionMetaData;
    }

    @JsonProperty("percentageMatch")
    public Double getPercentageMatch() {
        return percentageMatch;
    }

    @JsonProperty("percentageMatch")
    public void setPercentageMatch(Double percentageMatch) {
        this.percentageMatch = percentageMatch;
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
