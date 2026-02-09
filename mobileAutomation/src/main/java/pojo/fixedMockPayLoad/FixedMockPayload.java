package pojo.fixedMockPayLoad;

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
@JsonPropertyOrder({"sections", "id", "title", "subjectTagId", "languageTagId", "mockExamTypeBeans", "examNameBeans", "difficultyLevel", "testTypeTagId", "paidStatus", "marks", "positive", "negative", "timeLimit", "cutOffMarks", "description", "points", "createAt", "published", "mappingId", "comingSoon", "isCalculator", "liveTest", "testCategory", "fixedMockEventDescription", "fixedMockTestType", "fixedMockPlatform", "fixedMockPrizeType", "fixedMockFaqJson", "fixedMockPrizeDescription", "testType", "isFixedMock", "fixedMockTestStartTime", "fixedMockSubmitTime", "fixedMockResultTime", "attemptBeforeTime"})
@Generated("jsonschema2pojo")
public class FixedMockPayload {

    @JsonProperty("sections")
    private List<Section> sections;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("subjectTagId")
    private Integer subjectTagId;
    @JsonProperty("languageTagId")
    private Integer languageTagId;
    @JsonProperty("mockExamTypeBeans")
    private List<MockExamTypeBean> mockExamTypeBeans;
    @JsonProperty("examNameBeans")
    private List<ExamNameBean> examNameBeans;
    @JsonProperty("difficultyLevel")
    private Integer difficultyLevel;
    @JsonProperty("testTypeTagId")
    private Integer testTypeTagId;
    @JsonProperty("paidStatus")
    private Integer paidStatus;
    @JsonProperty("marks")
    private Integer marks;
    @JsonProperty("positive")
    private Integer positive;
    @JsonProperty("negative")
    private Integer negative;
    @JsonProperty("timeLimit")
    private Integer timeLimit;
    @JsonProperty("cutOffMarks")
    private Integer cutOffMarks;
    @JsonProperty("description")
    private String description;
    @JsonProperty("points")
    private Integer points;
    @JsonProperty("createAt")
    private String createAt;
    @JsonProperty("published")
    private Boolean published;
    @JsonProperty("mappingId")
    private Integer mappingId;
    @JsonProperty("comingSoon")
    private Boolean comingSoon;
    @JsonProperty("isCalculator")
    private Boolean isCalculator;
    @JsonProperty("liveTest")
    private Boolean liveTest;
    @JsonProperty("testCategory")
    private String testCategory;
    @JsonProperty("fixedMockEventDescription")
    private String fixedMockEventDescription;
    @JsonProperty("fixedMockTestType")
    private String fixedMockTestType;
    @JsonProperty("fixedMockPlatform")
    private Integer fixedMockPlatform;
    @JsonProperty("fixedMockPrizeType")
    private Integer fixedMockPrizeType;
    @JsonProperty("fixedMockFaqJson")
    private String fixedMockFaqJson;
    @JsonProperty("fixedMockPrizeDescription")
    private String fixedMockPrizeDescription;
    @JsonProperty("testType")
    private Integer testType;
    @JsonProperty("isFixedMock")
    private Boolean isFixedMock;
    @JsonProperty("fixedMockTestStartTime")
    private String fixedMockTestStartTime;
    @JsonProperty("attemptBeforeTime")
    private String attemptBeforeTime;

    @JsonProperty("forStorefront")
    private Boolean forStorefront;
    @JsonProperty("forApp")
    private Boolean forApp;
    @JsonProperty("subSectionAvailable")
    private Boolean subSectionAvailable;
    @JsonProperty("videoSolutionAvailable")
    private Boolean videoSolutionAvailable;
    @JsonProperty("genInstructionTime")
    private Integer genInstructionTime;
    @JsonProperty("pypTest")
    private Boolean pypTest;
    @JsonProperty("pypPdfAvailable")
    private Boolean pypPdfAvailable;
    @JsonProperty("pypYear")
    private Integer pypYear;
    @JsonProperty("pypMonth")
    private String pypMonth;
    @JsonProperty("shift")
    private String shift;
    @JsonProperty("examDate")
    private String examDate;
    @JsonProperty("eventPublishTime")
    private String eventPublishTime;
    @JsonProperty("scheduledPublishAt")
    private String scheduledPublishAt;
    @JsonProperty("startTime")
    private String startTime;
    @JsonProperty("endTime")
    private String endTime;
    @JsonProperty("testSubmissionTime")
    private String testSubmissionTime;
    @JsonProperty("fixedMockSubmitTime")
    private String fixedMockSubmitTime;
    @JsonProperty("fixedMockResultTime")
    private String fixedMockResultTime;

    public String getTestSubmissionTime() {
        return testSubmissionTime;
    }

    public void setTestSubmissionTime(String testSubmissionTime) {
        this.testSubmissionTime = testSubmissionTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getScheduledPublishAt() {
        return scheduledPublishAt;
    }

    public void setScheduledPublishAt(String scheduledPublishAt) {
        this.scheduledPublishAt = scheduledPublishAt;
    }

    public String getEventPublishTime() {
        return eventPublishTime;
    }

    public void setEventPublishTime(String eventPublishTime) {
        this.eventPublishTime = eventPublishTime;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getPypMonth() {
        return pypMonth;
    }

    public void setPypMonth(String pypMonth) {
        this.pypMonth = pypMonth;
    }

    public Integer getPypYear() {
        return pypYear;
    }

    public void setPypYear(Integer pypYear) {
        this.pypYear = pypYear;
    }

    public Boolean getPypPdfAvailable() {
        return pypPdfAvailable;
    }

    public void setPypPdfAvailable(Boolean pypPdfAvailable) {
        this.pypPdfAvailable = pypPdfAvailable;
    }

    public Boolean getPypTest() {
        return pypTest;
    }

    public void setPypTest(Boolean pypTest) {
        this.pypTest = pypTest;
    }

    public Integer getGenInstructionTime() {
        return genInstructionTime;
    }

    public void setGenInstructionTime(Integer genInstructionTime) {
        this.genInstructionTime = genInstructionTime;
    }

    public Boolean getVideoSolutionAvailable() {
        return videoSolutionAvailable;
    }

    public void setVideoSolutionAvailable(Boolean videoSolutionAvailable) {
        this.videoSolutionAvailable = videoSolutionAvailable;
    }

    public Boolean getSubSectionAvailable() {
        return subSectionAvailable;
    }

    public void setSubSectionAvailable(Boolean subSectionAvailable) {
        this.subSectionAvailable = subSectionAvailable;
    }

    public Boolean getForApp() {
        return forApp;
    }

    public void setForApp(Boolean forApp) {
        this.forApp = forApp;
    }

    public Boolean getForStorefront() {
        return forStorefront;
    }

    public void setForStorefront(Boolean forStorefront) {
        this.forStorefront = forStorefront;
    }

    public String getAttemptBeforeTime() {
        return attemptBeforeTime;
    }

    public void setAttemptBeforeTime(String attemptBeforeTime) {
        this.attemptBeforeTime = attemptBeforeTime;
    }

    public String getFixedMockTestStartTime() {
        return fixedMockTestStartTime;
    }

    public void setFixedMockTestStartTime(String fixedMockTestStartTime) {
        this.fixedMockTestStartTime = fixedMockTestStartTime;
    }

    public String getFixedMockSubmitTime() {
        return fixedMockSubmitTime;
    }

    public void setFixedMockSubmitTime(String fixedMockSubmitTime) {
        this.fixedMockSubmitTime = fixedMockSubmitTime;
    }

    public String getFixedMockResultTime() {
        return fixedMockResultTime;
    }

    public void setFixedMockResultTime(String fixedMockResultTime) {
        this.fixedMockResultTime = fixedMockResultTime;
    }

    public Boolean getIsFixedMock() {
        return isFixedMock;
    }

    public void setIsFixedMock(Boolean isFixedMock) {
        this.isFixedMock = isFixedMock;
    }

    public Integer getTestType() {
        return testType;
    }

    public void setTestType(Integer testType) {
        this.testType = testType;
    }

    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("sections")
    public List<Section> getSections() {
        return sections;
    }

    @JsonProperty("sections")
    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("subjectTagId")
    public Integer getSubjectTagId() {
        return subjectTagId;
    }

    @JsonProperty("subjectTagId")
    public void setSubjectTagId(Integer subjectTagId) {
        this.subjectTagId = subjectTagId;
    }

    @JsonProperty("languageTagId")
    public Integer getLanguageTagId() {
        return languageTagId;
    }

    @JsonProperty("languageTagId")
    public void setLanguageTagId(Integer languageTagId) {
        this.languageTagId = languageTagId;
    }

    @JsonProperty("mockExamTypeBeans")
    public List<MockExamTypeBean> getMockExamTypeBeans() {
        return mockExamTypeBeans;
    }

    @JsonProperty("mockExamTypeBeans")
    public void setMockExamTypeBeans(List<MockExamTypeBean> mockExamTypeBeans) {
        this.mockExamTypeBeans = mockExamTypeBeans;
    }

    @JsonProperty("examNameBeans")
    public List<ExamNameBean> getExamNameBeans() {
        return examNameBeans;
    }

    @JsonProperty("examNameBeans")
    public void setExamNameBeans(List<ExamNameBean> examNameBeans) {
        this.examNameBeans = examNameBeans;
    }

    @JsonProperty("difficultyLevel")
    public Integer getDifficultyLevel() {
        return difficultyLevel;
    }

    @JsonProperty("difficultyLevel")
    public void setDifficultyLevel(Integer difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    @JsonProperty("testTypeTagId")
    public Integer getTestTypeTagId() {
        return testTypeTagId;
    }

    @JsonProperty("testTypeTagId")
    public void setTestTypeTagId(Integer testTypeTagId) {
        this.testTypeTagId = testTypeTagId;
    }

    @JsonProperty("paidStatus")
    public Integer getPaidStatus() {
        return paidStatus;
    }

    @JsonProperty("paidStatus")
    public void setPaidStatus(Integer paidStatus) {
        this.paidStatus = paidStatus;
    }

    @JsonProperty("marks")
    public Integer getMarks() {
        return marks;
    }

    @JsonProperty("marks")
    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    @JsonProperty("positive")
    public Integer getPositive() {
        return positive;
    }

    @JsonProperty("positive")
    public void setPositive(Integer positive) {
        this.positive = positive;
    }

    @JsonProperty("negative")
    public Integer getNegative() {
        return negative;
    }

    @JsonProperty("negative")
    public void setNegative(Integer negative) {
        this.negative = negative;
    }

    @JsonProperty("timeLimit")
    public Integer getTimeLimit() {
        return timeLimit;
    }

    @JsonProperty("timeLimit")
    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    @JsonProperty("cutOffMarks")
    public Integer getCutOffMarks() {
        return cutOffMarks;
    }

    @JsonProperty("cutOffMarks")
    public void setCutOffMarks(Integer cutOffMarks) {
        this.cutOffMarks = cutOffMarks;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("points")
    public Integer getPoints() {
        return points;
    }

    @JsonProperty("points")
    public void setPoints(Integer points) {
        this.points = points;
    }

    @JsonProperty("createAt")
    public String getCreateAt() {
        return createAt;
    }

    @JsonProperty("createAt")
    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    @JsonProperty("published")
    public Boolean getPublished() {
        return published;
    }

    @JsonProperty("published")
    public void setPublished(Boolean published) {
        this.published = published;
    }

    @JsonProperty("mappingId")
    public Integer getMappingId() {
        return mappingId;
    }

    @JsonProperty("mappingId")
    public void setMappingId(Integer mappingId) {
        this.mappingId = mappingId;
    }

    @JsonProperty("comingSoon")
    public Boolean getComingSoon() {
        return comingSoon;
    }

    @JsonProperty("comingSoon")
    public void setComingSoon(Boolean comingSoon) {
        this.comingSoon = comingSoon;
    }

    @JsonProperty("isCalculator")
    public Boolean getIsCalculator() {
        return isCalculator;
    }

    @JsonProperty("isCalculator")
    public void setIsCalculator(Boolean isCalculator) {
        this.isCalculator = isCalculator;
    }

    @JsonProperty("liveTest")
    public Boolean getLiveTest() {
        return liveTest;
    }

    @JsonProperty("liveTest")
    public void setLiveTest(Boolean liveTest) {
        this.liveTest = liveTest;
    }

    @JsonProperty("testCategory")
    public String getTestCategory() {
        return testCategory;
    }

    @JsonProperty("testCategory")
    public void setTestCategory(String testCategory) {
        this.testCategory = testCategory;
    }

    @JsonProperty("fixedMockEventDescription")
    public String getFixedMockEventDescription() {
        return fixedMockEventDescription;
    }

    @JsonProperty("fixedMockEventDescription")
    public void setFixedMockEventDescription(String fixedMockEventDescription) {
        this.fixedMockEventDescription = fixedMockEventDescription;
    }

    @JsonProperty("fixedMockTestType")
    public String getFixedMockTestType() {
        return fixedMockTestType;
    }

    @JsonProperty("fixedMockTestType")
    public void setFixedMockTestType(String fixedMockTestType) {
        this.fixedMockTestType = fixedMockTestType;
    }

    @JsonProperty("fixedMockPlatform")
    public Integer getFixedMockPlatform() {
        return fixedMockPlatform;
    }

    @JsonProperty("fixedMockPlatform")
    public void setFixedMockPlatform(Integer fixedMockPlatform) {
        this.fixedMockPlatform = fixedMockPlatform;
    }

    @JsonProperty("fixedMockPrizeType")
    public Integer getFixedMockPrizeType() {
        return fixedMockPrizeType;
    }

    @JsonProperty("fixedMockPrizeType")
    public void setFixedMockPrizeType(Integer fixedMockPrizeType) {
        this.fixedMockPrizeType = fixedMockPrizeType;
    }

    @JsonProperty("fixedMockFaqJson")
    public String getFixedMockFaqJson() {
        return fixedMockFaqJson;
    }

    @JsonProperty("fixedMockFaqJson")
    public void setFixedMockFaqJson(String fixedMockFaqJson) {
        this.fixedMockFaqJson = fixedMockFaqJson;
    }

    @JsonProperty("fixedMockPrizeDescription")
    public String getFixedMockPrizeDescription() {
        return fixedMockPrizeDescription;
    }

    @JsonProperty("fixedMockPrizeDescription")
    public void setFixedMockPrizeDescription(String fixedMockPrizeDescription) {
        this.fixedMockPrizeDescription = fixedMockPrizeDescription;
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
