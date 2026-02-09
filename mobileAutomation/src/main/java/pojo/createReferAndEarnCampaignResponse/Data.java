
package pojo.createReferAndEarnCampaignResponse;

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
    "campaignName",
    "campaignId",
    "examCategory",
    "campaignDescription",
    "campaignStatus",
    "milestoneIds"
})
@Generated("jsonschema2pojo")
public class Data {

    @JsonProperty("campaignName")
    private String campaignName;
    @JsonProperty("campaignId")
    private String campaignId;
    @JsonProperty("examCategory")
    private String examCategory;
    @JsonProperty("campaignDescription")
    private String campaignDescription;
    @JsonProperty("campaignStatus")
    private String campaignStatus;
    @JsonProperty("milestoneIds")
    private List<String> milestoneIds;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("campaignName")
    public String getCampaignName() {
        return campaignName;
    }

    @JsonProperty("campaignName")
    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    @JsonProperty("campaignId")
    public String getCampaignId() {
        return campaignId;
    }

    @JsonProperty("campaignId")
    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    @JsonProperty("examCategory")
    public String getExamCategory() {
        return examCategory;
    }

    @JsonProperty("examCategory")
    public void setExamCategory(String examCategory) {
        this.examCategory = examCategory;
    }

    @JsonProperty("campaignDescription")
    public String getCampaignDescription() {
        return campaignDescription;
    }

    @JsonProperty("campaignDescription")
    public void setCampaignDescription(String campaignDescription) {
        this.campaignDescription = campaignDescription;
    }

    @JsonProperty("campaignStatus")
    public String getCampaignStatus() {
        return campaignStatus;
    }

    @JsonProperty("campaignStatus")
    public void setCampaignStatus(String campaignStatus) {
        this.campaignStatus = campaignStatus;
    }

    @JsonProperty("milestoneIds")
    public List<String> getMilestoneIds() {
        return milestoneIds;
    }

    @JsonProperty("milestoneIds")
    public void setMilestoneIds(List<String> milestoneIds) {
        this.milestoneIds = milestoneIds;
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
