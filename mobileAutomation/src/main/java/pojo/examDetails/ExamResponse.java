package pojo.examDetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
    "success",
    "message",
    "response",
    "data"
})

public class ExamResponse {

    @JsonProperty("success")
    private boolean success;
    @JsonProperty("message")
    private String message;
    @JsonProperty("response")
    private String response;
    @JsonProperty("data")
    private ExamData data;

    // Getters for the properties

    @JsonProperty("success")
    public boolean isSuccess() {
        return success;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("response")
    public String getResponse() {
        return response;
    }

    @JsonProperty("data")
    public ExamData getData() {
        return data;
    }

}
