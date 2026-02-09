
package pojo.createReferAndEarnCampaignPayLoad;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"campaignTypeEnum",
	"landingPageUrl",
	"startDate",
	"endDate",
	"title",
	"description",
	"packageId",
	"campaignMilestones"
})
@Generated("jsonschema2pojo")
public class CreateReferAndEarnCampaignPayLoad {

	@JsonProperty("campaignTypeEnum")
	private Integer campaignTypeEnum;
	@JsonProperty("landingPageUrl")
	private String landingPageUrl;
	@JsonProperty("startDate")
	private String startDate;
	@JsonProperty("endDate")
	private String endDate;
	@JsonProperty("title")
	private String title;
	@JsonProperty("description")
	private String description;
	@JsonProperty("packageId")
	private List<Integer> packageId;
	@JsonProperty("campaignMilestones")
	private List<CampaignMilestone> campaignMilestones;

	@JsonProperty("campaignTypeEnum")
	public Integer getCampaignTypeEnum() {
		return campaignTypeEnum;
	}

	@JsonProperty("campaignTypeEnum")
	public void setCampaignTypeEnum(Integer campaignTypeEnum) {
		this.campaignTypeEnum = campaignTypeEnum;
	}

	@JsonProperty("landingPageUrl")
	public String getLandingPageUrl() {
		return landingPageUrl;
	}

	@JsonProperty("landingPageUrl")
	public void setLandingPageUrl(String landingPageUrl) {
		this.landingPageUrl = landingPageUrl;
	}

	@JsonProperty("startDate")
	public String getStartDate() {
		return startDate;
	}

	@JsonProperty("startDate")
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@JsonProperty("endDate")
	public String getEndDate() {
		return endDate;
	}

	@JsonProperty("endDate")
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@JsonProperty("title")
	public String getTitle() {
		return title;
	}

	@JsonProperty("title")
	public void setTitle(String title) {
		this.title = title;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("packageId")
	public List<Integer> getPackageId() {
		return packageId;
	}

	@JsonProperty("packageId")
	public void setPackageId(List<Integer> packageId) {
		this.packageId = packageId;
	}

	@JsonProperty("campaignMilestones")
	public List<CampaignMilestone> getCampaignMilestones() {
		return campaignMilestones;
	}

	@JsonProperty("campaignMilestones")
	public void setCampaignMilestones(List<CampaignMilestone> campaignMilestones) {
		this.campaignMilestones = campaignMilestones;
	}

}
