package pojo.createFLCPayload;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateFLCRequest {
	
	@JsonProperty("scheduleId")
	private Integer scheduleId;
	@JsonProperty("categories")
	private List<String> categories;
	@JsonProperty("packages")
	private List<String> packages;

	@JsonProperty("scheduleId")
	public Integer getScheduleId() {
		return scheduleId;
	}

	@JsonProperty("scheduleId")
	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	@JsonProperty("categories")
	public List<String> getCategories() {
		return categories;
	}

	@JsonProperty("categories")
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	@JsonProperty("packages")
	public List<String> getPackages() {
		return packages;
	}

	@JsonProperty("packages")
	public void setPackages(List<String> packages) {
		this.packages = packages;
	}
}
