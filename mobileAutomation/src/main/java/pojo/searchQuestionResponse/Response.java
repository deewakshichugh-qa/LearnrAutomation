
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
    "Message",
    "Questions",
    "showSimilarityPopUp"
})
@Generated("jsonschema2pojo")
public class Response {

    @JsonProperty("Message")
    private String message;
    @JsonProperty("Questions")
    private List<Question> questions;
    @JsonProperty("showSimilarityPopUp")
    private Boolean showSimilarityPopUp;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("Message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("Message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("Questions")
    public List<Question> getQuestions() {
        return questions;
    }

    @JsonProperty("Questions")
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @JsonProperty("showSimilarityPopUp")
    public Boolean getShowSimilarityPopUp() {
        return showSimilarityPopUp;
    }

    @JsonProperty("showSimilarityPopUp")
    public void setShowSimilarityPopUp(Boolean showSimilarityPopUp) {
        this.showSimilarityPopUp = showSimilarityPopUp;
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
