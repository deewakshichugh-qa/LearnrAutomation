
package pojo.createReferAndEarnCampaignPayLoad;

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
	"order",
	"threshold",
	"refererRewardType",
	"refererRewardVal",
	"refereeRewardType",
	"refereeRewardVal"
})
@Generated("jsonschema2pojo")
public class CampaignMilestone {

	@JsonProperty("order")
	private Integer order;
	@JsonProperty("threshold")
	private Integer threshold;
	@JsonProperty("refererRewardType")
	private Integer refererRewardType;
	@JsonProperty("refererRewardVal")
	private Integer refererRewardVal;
	@JsonProperty("refereeRewardType")
	private Integer refereeRewardType;
	@JsonProperty("refereeRewardVal")
	private Integer refereeRewardVal;

	@JsonProperty("order")
	public Integer getOrder() {
		return order;
	}

	@JsonProperty("order")
	public void setOrder(Integer order) {
		this.order = order;
	}

	@JsonProperty("threshold")
	public Integer getThreshold() {
		return threshold;
	}

	@JsonProperty("threshold")
	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}

	@JsonProperty("refererRewardType")
	public Integer getRefererRewardType() {
		return refererRewardType;
	}

	@JsonProperty("refererRewardType")
	public void setRefererRewardType(Integer refererRewardType) {
		this.refererRewardType = refererRewardType;
	}

	@JsonProperty("refererRewardVal")
	public Integer getRefererRewardVal() {
		return refererRewardVal;
	}

	@JsonProperty("refererRewardVal")
	public void setRefererRewardVal(Integer refererRewardVal) {
		this.refererRewardVal = refererRewardVal;
	}

	@JsonProperty("refereeRewardType")
	public Integer getRefereeRewardType() {
		return refereeRewardType;
	}

	@JsonProperty("refereeRewardType")
	public void setRefereeRewardType(Integer refereeRewardType) {
		this.refereeRewardType = refereeRewardType;
	}

	@JsonProperty("refereeRewardVal")
	public Integer getRefereeRewardVal() {
		return refereeRewardVal;
	}

	@JsonProperty("refereeRewardVal")
	public void setRefereeRewardVal(Integer refereeRewardVal) {
		this.refereeRewardVal = refereeRewardVal;
	}

}
