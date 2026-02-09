
package pojo.getQuestion;

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
    "questionId",
    "subQuestionId",
    "subQuestionTitle",
    "subQuestionInstructions",
    "subQuestionText",
    "subQuestionLink",
    "subtype",
    "clientRef1",
    "clientRef2",
    "clientRef3",
    "clientRef4",
    "clientRef5",
    "defaultDL",
    "difficultyLevel",
    "subQuestionOptions",
    "createdAt",
    "modifiedAt",
    "createdBy",
    "modifiedBy",
    "rank",
    "allTagsIdValueMapping"
})
@Generated("jsonschema2pojo")
public class SubQuestionEntity {

    @JsonProperty("questionId")
    private String questionId;
    @JsonProperty("subQuestionId")
    private String subQuestionId;
    @JsonProperty("subQuestionTitle")
    private String subQuestionTitle;
    @JsonProperty("subQuestionInstructions")
    private String subQuestionInstructions;
    @JsonProperty("subQuestionText")
    private String subQuestionText;
    @JsonProperty("subQuestionLink")
    private Object subQuestionLink;
    @JsonProperty("subtype")
    private String subtype;
    @JsonProperty("clientRef1")
    private Object clientRef1;
    @JsonProperty("clientRef2")
    private Object clientRef2;
    @JsonProperty("clientRef3")
    private Object clientRef3;
    @JsonProperty("clientRef4")
    private Object clientRef4;
    @JsonProperty("clientRef5")
    private Object clientRef5;
    @JsonProperty("defaultDL")
    private String defaultDL;
    @JsonProperty("difficultyLevel")
    private Integer difficultyLevel;
    @JsonProperty("subQuestionOptions")
    private SubQuestionOptions subQuestionOptions;
    @JsonProperty("createdAt")
    private Integer createdAt;
    @JsonProperty("modifiedAt")
    private Integer modifiedAt;
    @JsonProperty("createdBy")
    private String createdBy;
    @JsonProperty("modifiedBy")
    private Object modifiedBy;
    @JsonProperty("rank")
    private Integer rank;
    @JsonProperty("allTagsIdValueMapping")
    private AllTagsIdValueMapping__1 allTagsIdValueMapping;
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

    @JsonProperty("subQuestionId")
    public String getSubQuestionId() {
        return subQuestionId;
    }

    @JsonProperty("subQuestionId")
    public void setSubQuestionId(String subQuestionId) {
        this.subQuestionId = subQuestionId;
    }

    @JsonProperty("subQuestionTitle")
    public String getSubQuestionTitle() {
        return subQuestionTitle;
    }

    @JsonProperty("subQuestionTitle")
    public void setSubQuestionTitle(String subQuestionTitle) {
        this.subQuestionTitle = subQuestionTitle;
    }

    @JsonProperty("subQuestionInstructions")
    public String getSubQuestionInstructions() {
        return subQuestionInstructions;
    }

    @JsonProperty("subQuestionInstructions")
    public void setSubQuestionInstructions(String subQuestionInstructions) {
        this.subQuestionInstructions = subQuestionInstructions;
    }

    @JsonProperty("subQuestionText")
    public String getSubQuestionText() {
        return subQuestionText;
    }

    @JsonProperty("subQuestionText")
    public void setSubQuestionText(String subQuestionText) {
        this.subQuestionText = subQuestionText;
    }

    @JsonProperty("subQuestionLink")
    public Object getSubQuestionLink() {
        return subQuestionLink;
    }

    @JsonProperty("subQuestionLink")
    public void setSubQuestionLink(Object subQuestionLink) {
        this.subQuestionLink = subQuestionLink;
    }

    @JsonProperty("subtype")
    public String getSubtype() {
        return subtype;
    }

    @JsonProperty("subtype")
    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    @JsonProperty("clientRef1")
    public Object getClientRef1() {
        return clientRef1;
    }

    @JsonProperty("clientRef1")
    public void setClientRef1(Object clientRef1) {
        this.clientRef1 = clientRef1;
    }

    @JsonProperty("clientRef2")
    public Object getClientRef2() {
        return clientRef2;
    }

    @JsonProperty("clientRef2")
    public void setClientRef2(Object clientRef2) {
        this.clientRef2 = clientRef2;
    }

    @JsonProperty("clientRef3")
    public Object getClientRef3() {
        return clientRef3;
    }

    @JsonProperty("clientRef3")
    public void setClientRef3(Object clientRef3) {
        this.clientRef3 = clientRef3;
    }

    @JsonProperty("clientRef4")
    public Object getClientRef4() {
        return clientRef4;
    }

    @JsonProperty("clientRef4")
    public void setClientRef4(Object clientRef4) {
        this.clientRef4 = clientRef4;
    }

    @JsonProperty("clientRef5")
    public Object getClientRef5() {
        return clientRef5;
    }

    @JsonProperty("clientRef5")
    public void setClientRef5(Object clientRef5) {
        this.clientRef5 = clientRef5;
    }

    @JsonProperty("defaultDL")
    public String getDefaultDL() {
        return defaultDL;
    }

    @JsonProperty("defaultDL")
    public void setDefaultDL(String defaultDL) {
        this.defaultDL = defaultDL;
    }

    @JsonProperty("difficultyLevel")
    public Integer getDifficultyLevel() {
        return difficultyLevel;
    }

    @JsonProperty("difficultyLevel")
    public void setDifficultyLevel(Integer difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    @JsonProperty("subQuestionOptions")
    public SubQuestionOptions getSubQuestionOptions() {
        return subQuestionOptions;
    }

    @JsonProperty("subQuestionOptions")
    public void setSubQuestionOptions(SubQuestionOptions subQuestionOptions) {
        this.subQuestionOptions = subQuestionOptions;
    }

    @JsonProperty("createdAt")
    public Integer getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("createdAt")
    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("modifiedAt")
    public Integer getModifiedAt() {
        return modifiedAt;
    }

    @JsonProperty("modifiedAt")
    public void setModifiedAt(Integer modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @JsonProperty("createdBy")
    public String getCreatedBy() {
        return createdBy;
    }

    @JsonProperty("createdBy")
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @JsonProperty("modifiedBy")
    public Object getModifiedBy() {
        return modifiedBy;
    }

    @JsonProperty("modifiedBy")
    public void setModifiedBy(Object modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @JsonProperty("rank")
    public Integer getRank() {
        return rank;
    }

    @JsonProperty("rank")
    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @JsonProperty("allTagsIdValueMapping")
    public AllTagsIdValueMapping__1 getAllTagsIdValueMapping() {
        return allTagsIdValueMapping;
    }

    @JsonProperty("allTagsIdValueMapping")
    public void setAllTagsIdValueMapping(AllTagsIdValueMapping__1 allTagsIdValueMapping) {
        this.allTagsIdValueMapping = allTagsIdValueMapping;
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
