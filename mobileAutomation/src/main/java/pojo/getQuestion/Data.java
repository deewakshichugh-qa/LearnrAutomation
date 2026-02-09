
package pojo.getQuestion;

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
    "question",
    "answer",
    "subQuestionEntity",
    "subQuestionAnswerEntity",
    "questionMetaData",
    "videosLink",
    "lastModifiedAt",
    "lastCreatedBy",
    "lastQuestionId",
    "lastClientRef1",
    "lastClientRef2",
    "noIndex",
    "noFollow",
    "canonical",
    "userCount"
})
@Generated("jsonschema2pojo")
public class Data {

    @JsonProperty("question")
    private Question question;
    @JsonProperty("answer")
    private Object answer;
    @JsonProperty("subQuestionEntity")
    private List<SubQuestionEntity> subQuestionEntity;
    @JsonProperty("subQuestionAnswerEntity")
    private List<SubQuestionAnswerEntity> subQuestionAnswerEntity;
    @JsonProperty("questionMetaData")
    private QuestionMetaData questionMetaData;
    @JsonProperty("videosLink")
    private Object videosLink;
    @JsonProperty("lastModifiedAt")
    private Integer lastModifiedAt;
    @JsonProperty("lastCreatedBy")
    private Object lastCreatedBy;
    @JsonProperty("lastQuestionId")
    private Object lastQuestionId;
    @JsonProperty("lastClientRef1")
    private Object lastClientRef1;
    @JsonProperty("lastClientRef2")
    private Object lastClientRef2;
    @JsonProperty("noIndex")
    private Object noIndex;
    @JsonProperty("noFollow")
    private Object noFollow;
    @JsonProperty("canonical")
    private Object canonical;
    @JsonProperty("userCount")
    private Integer userCount;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("question")
    public Question getQuestion() {
        return question;
    }

    @JsonProperty("question")
    public void setQuestion(Question question) {
        this.question = question;
    }

    @JsonProperty("answer")
    public Object getAnswer() {
        return answer;
    }

    @JsonProperty("answer")
    public void setAnswer(Object answer) {
        this.answer = answer;
    }

    @JsonProperty("subQuestionEntity")
    public List<SubQuestionEntity> getSubQuestionEntity() {
        return subQuestionEntity;
    }

    @JsonProperty("subQuestionEntity")
    public void setSubQuestionEntity(List<SubQuestionEntity> subQuestionEntity) {
        this.subQuestionEntity = subQuestionEntity;
    }

    @JsonProperty("subQuestionAnswerEntity")
    public List<SubQuestionAnswerEntity> getSubQuestionAnswerEntity() {
        return subQuestionAnswerEntity;
    }

    @JsonProperty("subQuestionAnswerEntity")
    public void setSubQuestionAnswerEntity(List<SubQuestionAnswerEntity> subQuestionAnswerEntity) {
        this.subQuestionAnswerEntity = subQuestionAnswerEntity;
    }

    @JsonProperty("questionMetaData")
    public QuestionMetaData getQuestionMetaData() {
        return questionMetaData;
    }

    @JsonProperty("questionMetaData")
    public void setQuestionMetaData(QuestionMetaData questionMetaData) {
        this.questionMetaData = questionMetaData;
    }

    @JsonProperty("videosLink")
    public Object getVideosLink() {
        return videosLink;
    }

    @JsonProperty("videosLink")
    public void setVideosLink(Object videosLink) {
        this.videosLink = videosLink;
    }

    @JsonProperty("lastModifiedAt")
    public Integer getLastModifiedAt() {
        return lastModifiedAt;
    }

    @JsonProperty("lastModifiedAt")
    public void setLastModifiedAt(Integer lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    @JsonProperty("lastCreatedBy")
    public Object getLastCreatedBy() {
        return lastCreatedBy;
    }

    @JsonProperty("lastCreatedBy")
    public void setLastCreatedBy(Object lastCreatedBy) {
        this.lastCreatedBy = lastCreatedBy;
    }

    @JsonProperty("lastQuestionId")
    public Object getLastQuestionId() {
        return lastQuestionId;
    }

    @JsonProperty("lastQuestionId")
    public void setLastQuestionId(Object lastQuestionId) {
        this.lastQuestionId = lastQuestionId;
    }

    @JsonProperty("lastClientRef1")
    public Object getLastClientRef1() {
        return lastClientRef1;
    }

    @JsonProperty("lastClientRef1")
    public void setLastClientRef1(Object lastClientRef1) {
        this.lastClientRef1 = lastClientRef1;
    }

    @JsonProperty("lastClientRef2")
    public Object getLastClientRef2() {
        return lastClientRef2;
    }

    @JsonProperty("lastClientRef2")
    public void setLastClientRef2(Object lastClientRef2) {
        this.lastClientRef2 = lastClientRef2;
    }

    @JsonProperty("noIndex")
    public Object getNoIndex() {
        return noIndex;
    }

    @JsonProperty("noIndex")
    public void setNoIndex(Object noIndex) {
        this.noIndex = noIndex;
    }

    @JsonProperty("noFollow")
    public Object getNoFollow() {
        return noFollow;
    }

    @JsonProperty("noFollow")
    public void setNoFollow(Object noFollow) {
        this.noFollow = noFollow;
    }

    @JsonProperty("canonical")
    public Object getCanonical() {
        return canonical;
    }

    @JsonProperty("canonical")
    public void setCanonical(Object canonical) {
        this.canonical = canonical;
    }

    @JsonProperty("userCount")
    public Integer getUserCount() {
        return userCount;
    }

    @JsonProperty("userCount")
    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
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
