package pojo.PackageDetailsNewPurchased;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "success",
    "message",
    "response",
    "data"
})

@Generated("jsonschema2pojo")
public class PpcResponseNewPurchased {

	@JsonProperty("success")
	private boolean success;
	@JsonProperty("message")
	private String message;
	@JsonProperty("response")
	private Object response;
	@JsonProperty("data")
	private ExamListNewPurchased data;

	@JsonProperty("success")
	public boolean isSuccess() {
		return success;
	}

	@JsonProperty("message")
	public String getMessage() {
		return message;
	}

	@JsonProperty("response")
	public Object getResponse() {
		return response;
	}

	@JsonProperty("data")
	public ExamListNewPurchased getData() {
		return data;
	}
}
