
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
    "questionId",
    "languageVersion",
    "numberOfAssessmentBindIn",
    "numberOfTimesAttempted",
    "numberOfTimesCorrect",
    "numberOfTimesIncorrect",
    "numberOfTimesSkipped",
    "averageTimeSpentOnQuestion",
    "addedTests",
    "removedTests"
})
@Generated("jsonschema2pojo")
public class QuestionMetaData {

    @JsonProperty("questionId")
    private String questionId;
    @JsonProperty("languageVersion")
    private List<String> languageVersion;
    @JsonProperty("numberOfAssessmentBindIn")
    private Integer numberOfAssessmentBindIn;
    @JsonProperty("numberOfTimesAttempted")
    private Integer numberOfTimesAttempted;
    @JsonProperty("numberOfTimesCorrect")
    private Integer numberOfTimesCorrect;
    @JsonProperty("numberOfTimesIncorrect")
    private Integer numberOfTimesIncorrect;
    @JsonProperty("numberOfTimesSkipped")
    private Integer numberOfTimesSkipped;
    @JsonProperty("averageTimeSpentOnQuestion")
    private Double averageTimeSpentOnQuestion;
    @JsonProperty("addedTests")
    private Object addedTests;
    @JsonProperty("removedTests")
    private Object removedTests;
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

    @JsonProperty("languageVersion")
    public List<String> getLanguageVersion() {
        return languageVersion;
    }

    @JsonProperty("languageVersion")
    public void setLanguageVersion(List<String> languageVersion) {
        this.languageVersion = languageVersion;
    }

    @JsonProperty("numberOfAssessmentBindIn")
    public Integer getNumberOfAssessmentBindIn() {
        return numberOfAssessmentBindIn;
    }

    @JsonProperty("numberOfAssessmentBindIn")
    public void setNumberOfAssessmentBindIn(Integer numberOfAssessmentBindIn) {
        this.numberOfAssessmentBindIn = numberOfAssessmentBindIn;
    }

    @JsonProperty("numberOfTimesAttempted")
    public Integer getNumberOfTimesAttempted() {
        return numberOfTimesAttempted;
    }

    @JsonProperty("numberOfTimesAttempted")
    public void setNumberOfTimesAttempted(Integer numberOfTimesAttempted) {
        this.numberOfTimesAttempted = numberOfTimesAttempted;
    }

    @JsonProperty("numberOfTimesCorrect")
    public Integer getNumberOfTimesCorrect() {
        return numberOfTimesCorrect;
    }

    @JsonProperty("numberOfTimesCorrect")
    public void setNumberOfTimesCorrect(Integer numberOfTimesCorrect) {
        this.numberOfTimesCorrect = numberOfTimesCorrect;
    }

    @JsonProperty("numberOfTimesIncorrect")
    public Integer getNumberOfTimesIncorrect() {
        return numberOfTimesIncorrect;
    }

    @JsonProperty("numberOfTimesIncorrect")
    public void setNumberOfTimesIncorrect(Integer numberOfTimesIncorrect) {
        this.numberOfTimesIncorrect = numberOfTimesIncorrect;
    }

    @JsonProperty("numberOfTimesSkipped")
    public Integer getNumberOfTimesSkipped() {
        return numberOfTimesSkipped;
    }

    @JsonProperty("numberOfTimesSkipped")
    public void setNumberOfTimesSkipped(Integer numberOfTimesSkipped) {
        this.numberOfTimesSkipped = numberOfTimesSkipped;
    }

    @JsonProperty("averageTimeSpentOnQuestion")
    public Double getAverageTimeSpentOnQuestion() {
        return averageTimeSpentOnQuestion;
    }

    @JsonProperty("averageTimeSpentOnQuestion")
    public void setAverageTimeSpentOnQuestion(Double averageTimeSpentOnQuestion) {
        this.averageTimeSpentOnQuestion = averageTimeSpentOnQuestion;
    }

    @JsonProperty("addedTests")
    public Object getAddedTests() {
        return addedTests;
    }

    @JsonProperty("addedTests")
    public void setAddedTests(Object addedTests) {
        this.addedTests = addedTests;
    }

    @JsonProperty("removedTests")
    public Object getRemovedTests() {
        return removedTests;
    }

    @JsonProperty("removedTests")
    public void setRemovedTests(Object removedTests) {
        this.removedTests = removedTests;
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
