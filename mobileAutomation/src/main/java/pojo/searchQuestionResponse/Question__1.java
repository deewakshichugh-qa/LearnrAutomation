
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
    "modifiedAt",
    "clientRef1",
    "clientRef2",
    "clientRef3",
    "clientRef4",
    "clientRef5",
    "clientRef6",
    "businessCreatedFrom",
    "questionType",
    "questionTitle",
    "titleHtml",
    "questionStatus",
    "maxLength",
    "questionOptions",
    "questionRightOptions",
    "questionLeftOptions",
    "source",
    "defaultDL",
    "difficultyLevel",
    "clientSource",
    "maxMarks",
    "ttl",
    "isAttachment",
    "createdAt",
    "createdBy",
    "modifiedBy",
    "reviewBy",
    "reviewAt",
    "languageId",
    "url",
    "questionInstruction",
    "isPYQ",
    "isVideoSolution",
    "pyqExamTagId",
    "pyqExamDate",
    "pyqExamShift",
    "allTagsIdValueMapping",
    "comprehensiveQuestionList",
    "occurrenceCount",
    "addedTests",
    "byPassedBy",
    "totalOptions",
    "bypassed",
    "autoVersionEnabled"
})
@Generated("jsonschema2pojo")
public class Question__1 {

    @JsonProperty("questionId")
    private String questionId;
    @JsonProperty("modifiedAt")
    private Integer modifiedAt;
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
    @JsonProperty("clientRef6")
    private Object clientRef6;
    @JsonProperty("businessCreatedFrom")
    private String businessCreatedFrom;
    @JsonProperty("questionType")
    private String questionType;
    @JsonProperty("questionTitle")
    private String questionTitle;
    @JsonProperty("titleHtml")
    private String titleHtml;
    @JsonProperty("questionStatus")
    private String questionStatus;
    @JsonProperty("maxLength")
    private Integer maxLength;
    @JsonProperty("questionOptions")
    private Object questionOptions;
    @JsonProperty("questionRightOptions")
    private Object questionRightOptions;
    @JsonProperty("questionLeftOptions")
    private Object questionLeftOptions;
    @JsonProperty("source")
    private String source;
    @JsonProperty("defaultDL")
    private String defaultDL;
    @JsonProperty("difficultyLevel")
    private Integer difficultyLevel;
    @JsonProperty("clientSource")
    private Object clientSource;
    @JsonProperty("maxMarks")
    private Double maxMarks;
    @JsonProperty("ttl")
    private Integer ttl;
    @JsonProperty("isAttachment")
    private Object isAttachment;
    @JsonProperty("createdAt")
    private Integer createdAt;
    @JsonProperty("createdBy")
    private String createdBy;
    @JsonProperty("modifiedBy")
    private Object modifiedBy;
    @JsonProperty("reviewBy")
    private Object reviewBy;
    @JsonProperty("reviewAt")
    private Integer reviewAt;
    @JsonProperty("languageId")
    private String languageId;
    @JsonProperty("url")
    private Object url;
    @JsonProperty("questionInstruction")
    private Object questionInstruction;
    @JsonProperty("isPYQ")
    private Boolean isPYQ;
    @JsonProperty("isVideoSolution")
    private Object isVideoSolution;
    @JsonProperty("pyqExamTagId")
    private Object pyqExamTagId;
    @JsonProperty("pyqExamDate")
    private Long pyqExamDate;
    @JsonProperty("pyqExamShift")
    private Object pyqExamShift;
    @JsonProperty("allTagsIdValueMapping")
    private AllTagsIdValueMapping allTagsIdValueMapping;
    @JsonProperty("comprehensiveQuestionList")
    private Object comprehensiveQuestionList;
    @JsonProperty("occurrenceCount")
    private Integer occurrenceCount;
    @JsonProperty("addedTests")
    private List<Integer> addedTests;
    @JsonProperty("byPassedBy")
    private Object byPassedBy;
    @JsonProperty("totalOptions")
    private Integer totalOptions;
    @JsonProperty("bypassed")
    private Boolean bypassed;
    @JsonProperty("autoVersionEnabled")
    private Boolean autoVersionEnabled;
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

    @JsonProperty("modifiedAt")
    public Integer getModifiedAt() {
        return modifiedAt;
    }

    @JsonProperty("modifiedAt")
    public void setModifiedAt(Integer modifiedAt) {
        this.modifiedAt = modifiedAt;
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

    @JsonProperty("clientRef6")
    public Object getClientRef6() {
        return clientRef6;
    }

    @JsonProperty("clientRef6")
    public void setClientRef6(Object clientRef6) {
        this.clientRef6 = clientRef6;
    }

    @JsonProperty("businessCreatedFrom")
    public String getBusinessCreatedFrom() {
        return businessCreatedFrom;
    }

    @JsonProperty("businessCreatedFrom")
    public void setBusinessCreatedFrom(String businessCreatedFrom) {
        this.businessCreatedFrom = businessCreatedFrom;
    }

    @JsonProperty("questionType")
    public String getQuestionType() {
        return questionType;
    }

    @JsonProperty("questionType")
    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    @JsonProperty("questionTitle")
    public String getQuestionTitle() {
        return questionTitle;
    }

    @JsonProperty("questionTitle")
    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    @JsonProperty("titleHtml")
    public String getTitleHtml() {
        return titleHtml;
    }

    @JsonProperty("titleHtml")
    public void setTitleHtml(String titleHtml) {
        this.titleHtml = titleHtml;
    }

    @JsonProperty("questionStatus")
    public String getQuestionStatus() {
        return questionStatus;
    }

    @JsonProperty("questionStatus")
    public void setQuestionStatus(String questionStatus) {
        this.questionStatus = questionStatus;
    }

    @JsonProperty("maxLength")
    public Integer getMaxLength() {
        return maxLength;
    }

    @JsonProperty("maxLength")
    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    @JsonProperty("questionOptions")
    public Object getQuestionOptions() {
        return questionOptions;
    }

    @JsonProperty("questionOptions")
    public void setQuestionOptions(Object questionOptions) {
        this.questionOptions = questionOptions;
    }

    @JsonProperty("questionRightOptions")
    public Object getQuestionRightOptions() {
        return questionRightOptions;
    }

    @JsonProperty("questionRightOptions")
    public void setQuestionRightOptions(Object questionRightOptions) {
        this.questionRightOptions = questionRightOptions;
    }

    @JsonProperty("questionLeftOptions")
    public Object getQuestionLeftOptions() {
        return questionLeftOptions;
    }

    @JsonProperty("questionLeftOptions")
    public void setQuestionLeftOptions(Object questionLeftOptions) {
        this.questionLeftOptions = questionLeftOptions;
    }

    @JsonProperty("source")
    public String getSource() {
        return source;
    }

    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
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

    @JsonProperty("clientSource")
    public Object getClientSource() {
        return clientSource;
    }

    @JsonProperty("clientSource")
    public void setClientSource(Object clientSource) {
        this.clientSource = clientSource;
    }

    @JsonProperty("maxMarks")
    public Double getMaxMarks() {
        return maxMarks;
    }

    @JsonProperty("maxMarks")
    public void setMaxMarks(Double maxMarks) {
        this.maxMarks = maxMarks;
    }

    @JsonProperty("ttl")
    public Integer getTtl() {
        return ttl;
    }

    @JsonProperty("ttl")
    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    @JsonProperty("isAttachment")
    public Object getIsAttachment() {
        return isAttachment;
    }

    @JsonProperty("isAttachment")
    public void setIsAttachment(Object isAttachment) {
        this.isAttachment = isAttachment;
    }

    @JsonProperty("createdAt")
    public Integer getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("createdAt")
    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
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

    @JsonProperty("reviewBy")
    public Object getReviewBy() {
        return reviewBy;
    }

    @JsonProperty("reviewBy")
    public void setReviewBy(Object reviewBy) {
        this.reviewBy = reviewBy;
    }

    @JsonProperty("reviewAt")
    public Integer getReviewAt() {
        return reviewAt;
    }

    @JsonProperty("reviewAt")
    public void setReviewAt(Integer reviewAt) {
        this.reviewAt = reviewAt;
    }

    @JsonProperty("languageId")
    public String getLanguageId() {
        return languageId;
    }

    @JsonProperty("languageId")
    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    @JsonProperty("url")
    public Object getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(Object url) {
        this.url = url;
    }

    @JsonProperty("questionInstruction")
    public Object getQuestionInstruction() {
        return questionInstruction;
    }

    @JsonProperty("questionInstruction")
    public void setQuestionInstruction(Object questionInstruction) {
        this.questionInstruction = questionInstruction;
    }

    @JsonProperty("isPYQ")
    public Boolean getIsPYQ() {
        return isPYQ;
    }

    @JsonProperty("isPYQ")
    public void setIsPYQ(Boolean isPYQ) {
        this.isPYQ = isPYQ;
    }

    @JsonProperty("isVideoSolution")
    public Object getIsVideoSolution() {
        return isVideoSolution;
    }

    @JsonProperty("isVideoSolution")
    public void setIsVideoSolution(Object isVideoSolution) {
        this.isVideoSolution = isVideoSolution;
    }

    @JsonProperty("pyqExamTagId")
    public Object getPyqExamTagId() {
        return pyqExamTagId;
    }

    @JsonProperty("pyqExamTagId")
    public void setPyqExamTagId(Object pyqExamTagId) {
        this.pyqExamTagId = pyqExamTagId;
    }

    @JsonProperty("pyqExamDate")
    public Long getPyqExamDate() {
        return pyqExamDate;
    }

    @JsonProperty("pyqExamDate")
    public void setPyqExamDate(Long pyqExamDate) {
        this.pyqExamDate = pyqExamDate;
    }

    @JsonProperty("pyqExamShift")
    public Object getPyqExamShift() {
        return pyqExamShift;
    }

    @JsonProperty("pyqExamShift")
    public void setPyqExamShift(Object pyqExamShift) {
        this.pyqExamShift = pyqExamShift;
    }

    @JsonProperty("allTagsIdValueMapping")
    public AllTagsIdValueMapping getAllTagsIdValueMapping() {
        return allTagsIdValueMapping;
    }

    @JsonProperty("allTagsIdValueMapping")
    public void setAllTagsIdValueMapping(AllTagsIdValueMapping allTagsIdValueMapping) {
        this.allTagsIdValueMapping = allTagsIdValueMapping;
    }

    @JsonProperty("comprehensiveQuestionList")
    public Object getComprehensiveQuestionList() {
        return comprehensiveQuestionList;
    }

    @JsonProperty("comprehensiveQuestionList")
    public void setComprehensiveQuestionList(Object comprehensiveQuestionList) {
        this.comprehensiveQuestionList = comprehensiveQuestionList;
    }

    @JsonProperty("occurrenceCount")
    public Integer getOccurrenceCount() {
        return occurrenceCount;
    }

    @JsonProperty("occurrenceCount")
    public void setOccurrenceCount(Integer occurrenceCount) {
        this.occurrenceCount = occurrenceCount;
    }

    @JsonProperty("addedTests")
    public List<Integer> getAddedTests() {
        return addedTests;
    }

    @JsonProperty("addedTests")
    public void setAddedTests(List<Integer> addedTests) {
        this.addedTests = addedTests;
    }

    @JsonProperty("byPassedBy")
    public Object getByPassedBy() {
        return byPassedBy;
    }

    @JsonProperty("byPassedBy")
    public void setByPassedBy(Object byPassedBy) {
        this.byPassedBy = byPassedBy;
    }

    @JsonProperty("totalOptions")
    public Integer getTotalOptions() {
        return totalOptions;
    }

    @JsonProperty("totalOptions")
    public void setTotalOptions(Integer totalOptions) {
        this.totalOptions = totalOptions;
    }

    @JsonProperty("bypassed")
    public Boolean getBypassed() {
        return bypassed;
    }

    @JsonProperty("bypassed")
    public void setBypassed(Boolean bypassed) {
        this.bypassed = bypassed;
    }

    @JsonProperty("autoVersionEnabled")
    public Boolean getAutoVersionEnabled() {
        return autoVersionEnabled;
    }

    @JsonProperty("autoVersionEnabled")
    public void setAutoVersionEnabled(Boolean autoVersionEnabled) {
        this.autoVersionEnabled = autoVersionEnabled;
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
