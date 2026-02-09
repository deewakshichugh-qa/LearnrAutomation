
package pojo.createAdmitCardPayload;
import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "campaignName",
    "examId",
    "packageIds",
    "rewardProductId",
    "startDate",
    "endDate",
    "examName",
    "fields"
})
@Generated("jsonschema2pojo")
public class CreateCampaignAdmitCardPayload{

    @JsonProperty("campaignName")
    private String campaignName;
    @JsonProperty("examId")
    private Integer examId;
    @JsonProperty("packageIds")
    private String packageIds;
    @JsonProperty("rewardProductId")
    private Integer rewardProductId;
    @JsonProperty("startDate")
    private String startDate;
    @JsonProperty("endDate")
    private String endDate;
    @JsonProperty("examName")
    private String examName;
    @JsonProperty("fields")
    private List<Field> fields;
   

    @JsonProperty("campaignName")
    public String getCampaignName() {
        return campaignName;
    }

    @JsonProperty("campaignName")
    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    @JsonProperty("examId")
    public Integer getExamId() {
        return examId;
    }

    @JsonProperty("examId")
    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    @JsonProperty("packageIds")
    public String getPackageIds() {
        return packageIds;
    }

    @JsonProperty("packageIds")
    public void setPackageIds(String packageIds) {
        this.packageIds = packageIds;
    }

    @JsonProperty("rewardProductId")
    public Integer getRewardProductId() {
        return rewardProductId;
    }

    @JsonProperty("rewardProductId")
    public void setRewardProductId(Integer rewardProductId) {
        this.rewardProductId = rewardProductId;
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

    @JsonProperty("examName")
    public String getExamName() {
        return examName;
    }

    @JsonProperty("examName")
    public void setExamName(String examName) {
        this.examName = examName;
    }

    @JsonProperty("fields")
    public List<Field> getFields() {
        return fields;
    }

    @JsonProperty("fields")
    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

}
