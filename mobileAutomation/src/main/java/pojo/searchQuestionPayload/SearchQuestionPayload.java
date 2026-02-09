
package pojo.searchQuestionPayload;

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
    "keyword",
    "clientRef1",
    "clientRef2",
    "clientRef3",
    "clientRef4",
    "clientRef5",
    "clientRef6",
    "createdBy",
    "languageFilter",
    "lastModifiedAt",
    "size",
    "createdAtDateFilter",
    "questionStatus",
    "questionType",
    "businessName",
    "difficulty",
    "tagIdList",
    "taggedUsers",
    "pyq",
    "isVideoSolution",
    "pyqExamDate",
    "pyqExamShift",
    "mappingIds",
    "mappingIdList",
    "isSimilarity",
    "pageNumber",
    "difficultyLevels",
    "pageSize"
})
@Generated("jsonschema2pojo")
public class SearchQuestionPayload {

    @JsonProperty("keyword")
    private List<Object> keyword;
    @JsonProperty("clientRef1")
    private ClientRef1 clientRef1;
    @JsonProperty("clientRef2")
    private ClientRef2 clientRef2;
    @JsonProperty("clientRef3")
    private List<String> clientRef3;
    @JsonProperty("clientRef4")
    private List<Object> clientRef4;
    @JsonProperty("clientRef5")
    private List<Object> clientRef5;
    @JsonProperty("clientRef6")
    private List<Object> clientRef6;
    @JsonProperty("createdBy")
    private List<Object> createdBy;
    @JsonProperty("languageFilter")
    private LanguageFilter languageFilter;
    @JsonProperty("lastModifiedAt")
    private Integer lastModifiedAt;
    @JsonProperty("size")
    private Integer size;
    @JsonProperty("createdAtDateFilter")
    private CreatedAtDateFilter createdAtDateFilter;
    @JsonProperty("questionStatus")
    private List<String> questionStatus;
    @JsonProperty("questionType")
    private List<String> questionType;
    @JsonProperty("businessName")
    private List<String> businessName;
    @JsonProperty("difficulty")
    private List<Object> difficulty;
    @JsonProperty("tagIdList")
    private TagIdList tagIdList;
    @JsonProperty("taggedUsers")
    private List<Object> taggedUsers;
    @JsonProperty("pyq")
    private Boolean pyq;
    @JsonProperty("isVideoSolution")
    private Object isVideoSolution;
    @JsonProperty("pyqExamDate")
    private PyqExamDate pyqExamDate;
    @JsonProperty("pyqExamShift")
    private Object pyqExamShift;
    @JsonProperty("mappingIds")
    private List<Object> mappingIds;
    @JsonProperty("mappingIdList")
    private MappingIdList mappingIdList;
    @JsonProperty("isSimilarity")
    private Boolean isSimilarity;
    @JsonProperty("pageNumber")
    private Integer pageNumber;
    @JsonProperty("difficultyLevels")
    private List<Object> difficultyLevels;
    @JsonProperty("pageSize")
    private Integer pageSize;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("keyword")
    public List<Object> getKeyword() {
        return keyword;
    }

    @JsonProperty("keyword")
    public void setKeyword(List<Object> keyword) {
        this.keyword = keyword;
    }

    @JsonProperty("clientRef1")
    public ClientRef1 getClientRef1() {
        return clientRef1;
    }

    @JsonProperty("clientRef1")
    public void setClientRef1(ClientRef1 clientRef1) {
        this.clientRef1 = clientRef1;
    }

    @JsonProperty("clientRef2")
    public ClientRef2 getClientRef2() {
        return clientRef2;
    }

    @JsonProperty("clientRef2")
    public void setClientRef2(ClientRef2 clientRef2) {
        this.clientRef2 = clientRef2;
    }

    @JsonProperty("clientRef3")
    public List<String> getClientRef3() {
        return clientRef3;
    }

    @JsonProperty("clientRef3")
    public void setClientRef3(List<String> clientRef3) {
        this.clientRef3 = clientRef3;
    }

    @JsonProperty("clientRef4")
    public List<Object> getClientRef4() {
        return clientRef4;
    }

    @JsonProperty("clientRef4")
    public void setClientRef4(List<Object> clientRef4) {
        this.clientRef4 = clientRef4;
    }

    @JsonProperty("clientRef5")
    public List<Object> getClientRef5() {
        return clientRef5;
    }

    @JsonProperty("clientRef5")
    public void setClientRef5(List<Object> clientRef5) {
        this.clientRef5 = clientRef5;
    }

    @JsonProperty("clientRef6")
    public List<Object> getClientRef6() {
        return clientRef6;
    }

    @JsonProperty("clientRef6")
    public void setClientRef6(List<Object> clientRef6) {
        this.clientRef6 = clientRef6;
    }

    @JsonProperty("createdBy")
    public List<Object> getCreatedBy() {
        return createdBy;
    }

    @JsonProperty("createdBy")
    public void setCreatedBy(List<Object> createdBy) {
        this.createdBy = createdBy;
    }

    @JsonProperty("languageFilter")
    public LanguageFilter getLanguageFilter() {
        return languageFilter;
    }

    @JsonProperty("languageFilter")
    public void setLanguageFilter(LanguageFilter languageFilter) {
        this.languageFilter = languageFilter;
    }

    @JsonProperty("lastModifiedAt")
    public Integer getLastModifiedAt() {
        return lastModifiedAt;
    }

    @JsonProperty("lastModifiedAt")
    public void setLastModifiedAt(Integer lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    @JsonProperty("size")
    public Integer getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(Integer size) {
        this.size = size;
    }

    @JsonProperty("createdAtDateFilter")
    public CreatedAtDateFilter getCreatedAtDateFilter() {
        return createdAtDateFilter;
    }

    @JsonProperty("createdAtDateFilter")
    public void setCreatedAtDateFilter(CreatedAtDateFilter createdAtDateFilter) {
        this.createdAtDateFilter = createdAtDateFilter;
    }

    @JsonProperty("questionStatus")
    public List<String> getQuestionStatus() {
        return questionStatus;
    }

    @JsonProperty("questionStatus")
    public void setQuestionStatus(List<String> questionStatus) {
        this.questionStatus = questionStatus;
    }

    @JsonProperty("questionType")
    public List<String> getQuestionType() {
        return questionType;
    }

    @JsonProperty("questionType")
    public void setQuestionType(List<String> questionType) {
        this.questionType = questionType;
    }

    @JsonProperty("businessName")
    public List<String> getBusinessName() {
        return businessName;
    }

    @JsonProperty("businessName")
    public void setBusinessName(List<String> businessName) {
        this.businessName = businessName;
    }

    @JsonProperty("difficulty")
    public List<Object> getDifficulty() {
        return difficulty;
    }

    @JsonProperty("difficulty")
    public void setDifficulty(List<Object> difficulty) {
        this.difficulty = difficulty;
    }

    @JsonProperty("tagIdList")
    public TagIdList getTagIdList() {
        return tagIdList;
    }

    @JsonProperty("tagIdList")
    public void setTagIdList(TagIdList tagIdList) {
        this.tagIdList = tagIdList;
    }

    @JsonProperty("taggedUsers")
    public List<Object> getTaggedUsers() {
        return taggedUsers;
    }

    @JsonProperty("taggedUsers")
    public void setTaggedUsers(List<Object> taggedUsers) {
        this.taggedUsers = taggedUsers;
    }

    @JsonProperty("pyq")
    public Boolean getPyq() {
        return pyq;
    }

    @JsonProperty("pyq")
    public void setPyq(Boolean pyq) {
        this.pyq = pyq;
    }

    @JsonProperty("isVideoSolution")
    public Object getIsVideoSolution() {
        return isVideoSolution;
    }

    @JsonProperty("isVideoSolution")
    public void setIsVideoSolution(Object isVideoSolution) {
        this.isVideoSolution = isVideoSolution;
    }

    @JsonProperty("pyqExamDate")
    public PyqExamDate getPyqExamDate() {
        return pyqExamDate;
    }

    @JsonProperty("pyqExamDate")
    public void setPyqExamDate(PyqExamDate pyqExamDate) {
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

    @JsonProperty("mappingIds")
    public List<Object> getMappingIds() {
        return mappingIds;
    }

    @JsonProperty("mappingIds")
    public void setMappingIds(List<Object> mappingIds) {
        this.mappingIds = mappingIds;
    }

    @JsonProperty("mappingIdList")
    public MappingIdList getMappingIdList() {
        return mappingIdList;
    }

    @JsonProperty("mappingIdList")
    public void setMappingIdList(MappingIdList mappingIdList) {
        this.mappingIdList = mappingIdList;
    }

    @JsonProperty("isSimilarity")
    public Boolean getIsSimilarity() {
        return isSimilarity;
    }

    @JsonProperty("isSimilarity")
    public void setIsSimilarity(Boolean isSimilarity) {
        this.isSimilarity = isSimilarity;
    }

    @JsonProperty("pageNumber")
    public Integer getPageNumber() {
        return pageNumber;
    }

    @JsonProperty("pageNumber")
    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    @JsonProperty("difficultyLevels")
    public List<Object> getDifficultyLevels() {
        return difficultyLevels;
    }

    @JsonProperty("difficultyLevels")
    public void setDifficultyLevels(List<Object> difficultyLevels) {
        this.difficultyLevels = difficultyLevels;
    }

    @JsonProperty("pageSize")
    public Integer getPageSize() {
        return pageSize;
    }

    @JsonProperty("pageSize")
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
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
