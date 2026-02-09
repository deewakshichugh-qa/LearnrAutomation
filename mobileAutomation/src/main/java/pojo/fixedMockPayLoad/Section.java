package pojo.fixedMockPayLoad;

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
    "sectionId",
    "secParentMarks",
    "secParentTime",
    "secParentTotalq",
    "subSecCompulsory",
    "subSecNumber",
    "secDN",
    "secSubject",
    "secTime",
    "secMarks",
    "secPenalty",
    "secCorrect",
    "secTotalq",
    "subject",
    "time",
    "marks",
    "variableMarks",
    "secSubjectTagId",
    "language",
    "attemptAllowed",
    "secOpt"
})
@Generated("jsonschema2pojo")
public class Section {

    @JsonProperty("sectionId")
    private Integer sectionId;
    @JsonProperty("secParentMarks")
    private Integer secParentMarks;
    @JsonProperty("secParentTime")
    private Integer secParentTime;
    @JsonProperty("secParentTotalq")
    private Integer secParentTotalq;
    @JsonProperty("subSecCompulsory")
    private Integer subSecCompulsory;
    @JsonProperty("subSecNumber")
    private Integer subSecNumber;
    @JsonProperty("secDN")
    private String secDN;
    @JsonProperty("secSubject")
    private String secSubject;
    @JsonProperty("secTime")
    private Integer secTime;
    @JsonProperty("secMarks")
    private Integer secMarks;
    @JsonProperty("secPenalty")
    private Integer secPenalty;
    @JsonProperty("secCorrect")
    private Integer secCorrect;
    @JsonProperty("secTotalq")
    private Integer secTotalq;
    @JsonProperty("subject")
    private String subject;
    @JsonProperty("time")
    private Integer time;
    @JsonProperty("marks")
    private Integer marks;
    @JsonProperty("variableMarks")
    private Boolean variableMarks;
    @JsonProperty("secSubjectTagId")
    private Integer secSubjectTagId;
    @JsonProperty("language")
    private String language;
    @JsonProperty("attemptAllowed")
    private Object attemptAllowed;
    @JsonProperty("secOpt")
    private Object secOpt;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("sectionId")
    public Integer getSectionId() {
        return sectionId;
    }

    @JsonProperty("sectionId")
    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    @JsonProperty("secParentMarks")
    public Integer getSecParentMarks() {
        return secParentMarks;
    }

    @JsonProperty("secParentMarks")
    public void setSecParentMarks(Integer secParentMarks) {
        this.secParentMarks = secParentMarks;
    }

    @JsonProperty("secParentTime")
    public Integer getSecParentTime() {
        return secParentTime;
    }

    @JsonProperty("secParentTime")
    public void setSecParentTime(Integer secParentTime) {
        this.secParentTime = secParentTime;
    }

    @JsonProperty("secParentTotalq")
    public Integer getSecParentTotalq() {
        return secParentTotalq;
    }

    @JsonProperty("secParentTotalq")
    public void setSecParentTotalq(Integer secParentTotalq) {
        this.secParentTotalq = secParentTotalq;
    }

    @JsonProperty("subSecCompulsory")
    public Integer getSubSecCompulsory() {
        return subSecCompulsory;
    }

    @JsonProperty("subSecCompulsory")
    public void setSubSecCompulsory(Integer subSecCompulsory) {
        this.subSecCompulsory = subSecCompulsory;
    }

    @JsonProperty("subSecNumber")
    public Integer getSubSecNumber() {
        return subSecNumber;
    }

    @JsonProperty("subSecNumber")
    public void setSubSecNumber(Integer subSecNumber) {
        this.subSecNumber = subSecNumber;
    }

    @JsonProperty("secDN")
    public String getSecDN() {
        return secDN;
    }

    @JsonProperty("secDN")
    public void setSecDN(String secDN) {
        this.secDN = secDN;
    }

    @JsonProperty("secSubject")
    public String getSecSubject() {
        return secSubject;
    }

    @JsonProperty("secSubject")
    public void setSecSubject(String secSubject) {
        this.secSubject = secSubject;
    }

    @JsonProperty("secTime")
    public Integer getSecTime() {
        return secTime;
    }

    @JsonProperty("secTime")
    public void setSecTime(Integer secTime) {
        this.secTime = secTime;
    }

    @JsonProperty("secMarks")
    public Integer getSecMarks() {
        return secMarks;
    }

    @JsonProperty("secMarks")
    public void setSecMarks(Integer secMarks) {
        this.secMarks = secMarks;
    }

    @JsonProperty("secPenalty")
    public Integer getSecPenalty() {
        return secPenalty;
    }

    @JsonProperty("secPenalty")
    public void setSecPenalty(Integer secPenalty) {
        this.secPenalty = secPenalty;
    }

    @JsonProperty("secCorrect")
    public Integer getSecCorrect() {
        return secCorrect;
    }

    @JsonProperty("secCorrect")
    public void setSecCorrect(Integer secCorrect) {
        this.secCorrect = secCorrect;
    }

    @JsonProperty("secTotalq")
    public Integer getSecTotalq() {
        return secTotalq;
    }

    @JsonProperty("secTotalq")
    public void setSecTotalq(Integer secTotalq) {
        this.secTotalq = secTotalq;
    }

    @JsonProperty("subject")
    public String getSubject() {
        return subject;
    }

    @JsonProperty("subject")
    public void setSubject(String subject) {
        this.subject = subject;
    }

    @JsonProperty("time")
    public Integer getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(Integer time) {
        this.time = time;
    }

    @JsonProperty("marks")
    public Integer getMarks() {
        return marks;
    }

    @JsonProperty("marks")
    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    @JsonProperty("variableMarks")
    public Boolean getVariableMarks() {
        return variableMarks;
    }

    @JsonProperty("variableMarks")
    public void setVariableMarks(Boolean variableMarks) {
        this.variableMarks = variableMarks;
    }

    @JsonProperty("secSubjectTagId")
    public Integer getSecSubjectTagId() {
        return secSubjectTagId;
    }

    @JsonProperty("secSubjectTagId")
    public void setSecSubjectTagId(Integer secSubjectTagId) {
        this.secSubjectTagId = secSubjectTagId;
    }

    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonProperty("attemptAllowed")
    public Object getAttemptAllowed() {
        return attemptAllowed;
    }

    @JsonProperty("attemptAllowed")
    public void setAttemptAllowed(Object attemptAllowed) {
        this.attemptAllowed = attemptAllowed;
    }

    @JsonProperty("secOpt")
    public Object getSecOpt() {
        return secOpt;
    }

    @JsonProperty("secOpt")
    public void setSecOpt(Object secOpt) {
        this.secOpt = secOpt;
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