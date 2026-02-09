
package pojo.createPackagePayload;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class CreatePackage {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("secondTitle")
    @Expose
    private String secondTitle;
    @SerializedName("slugTitle")
    @Expose
    private String slugTitle;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("maximumRetailPrice")
    @Expose
    private Integer maximumRetailPrice;
    @SerializedName("sellingPrice")
    @Expose
    private Integer sellingPrice;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("published")
    @Expose
    private Boolean published;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("examDateTime")
    @Expose
    private String examDateTime;
    @SerializedName("tabIds")
    @Expose
    private List<Integer> tabIds;
    @SerializedName("tabs")
    @Expose
    private List<Object> tabs;
    @SerializedName("selectedTabs")
    @Expose
    private List<Object> selectedTabs;
    @SerializedName("fCF")
    @Expose
    private Boolean fCF;
    @SerializedName("rc")
    @Expose
    private Boolean rc;
    @SerializedName("cFData")
    @Expose
    private CFData cFData;
    @SerializedName("vpData")
    @Expose
    private List<VpDatum> vpData;
    @SerializedName("olcHours")
    @Expose
    private String olcHours;
    @SerializedName("faqJson")
    @Expose
    private String faqJson;
    @SerializedName("hlsJson")
    @Expose
    private Object hlsJson;
    @SerializedName("faculties")
    @Expose
    private List<Faculty> faculties;
    @SerializedName("mahaPack")
    @Expose
    private Boolean mahaPack;
    @SerializedName("certification")
    @Expose
    private Boolean certification;
    @SerializedName("iosAvailable")
    @Expose
    private Boolean iosAvailable;
    @SerializedName("isMultiValidity")
    @Expose
    private Boolean isMultiValidity;
    @SerializedName("iosPrice")
    @Expose
    private String iosPrice;
    @SerializedName("webinarId")
    @Expose
    private Object webinarId;
    @SerializedName("olcAccountId")
    @Expose
    private Integer olcAccountId;
    @SerializedName("olcSource")
    @Expose
    private String olcSource;
    @SerializedName("olcMetaJson")
    @Expose
    private String olcMetaJson;
    @SerializedName("introVideoUrl")
    @Expose
    private String introVideoUrl;
    @SerializedName("salientFeatureIds")
    @Expose
    private List<Object> salientFeatureIds;
    @SerializedName("packageDescription")
    @Expose
    private PackageDescription packageDescription;
    @SerializedName("testimonialBean")
    @Expose
    private List<Object> testimonialBean;
    @SerializedName("mahapackComparison")
    @Expose
    private List<Object> mahapackComparison;
    @SerializedName("batchStartDate")
    @Expose
    private String batchStartDate;
    @SerializedName("categoryIds")
    @Expose
    private List<Integer> categoryIds;
    @SerializedName("courseIds")
    @Expose
    private List<Integer> courseIds;
    @SerializedName("batchEndDate")
    @Expose
    private String batchEndDate;

    public void setBatchEndDate(String batchEndDate) {
		this.batchEndDate = batchEndDate;
	}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSecondTitle() {
        return secondTitle;
    }

    public void setSecondTitle(String secondTitle) {
        this.secondTitle = secondTitle;
    }

    public String getSlugTitle() {
        return slugTitle;
    }

    public void setSlugTitle(String slugTitle) {
        this.slugTitle = slugTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaximumRetailPrice() {
        return maximumRetailPrice;
    }

    public void setMaximumRetailPrice(Integer maximumRetailPrice) {
        this.maximumRetailPrice = maximumRetailPrice;
    }

    public Integer getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Integer sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getExamDateTime() {
        return examDateTime;
    }

    public void setExamDateTime(String examDateTime) {
        this.examDateTime = examDateTime;
    }

    public List<Integer> getTabIds() {
        return tabIds;
    }

    public void setTabIds(List<Integer> tabIds) {
        this.tabIds = tabIds;
    }

    public List<Object> getTabs() {
        return tabs;
    }

    public void setTabs(List<Object> tabs) {
        this.tabs = tabs;
    }

    public List<Object> getSelectedTabs() {
        return selectedTabs;
    }

    public void setSelectedTabs(List<Object> selectedTabs) {
        this.selectedTabs = selectedTabs;
    }

    public Boolean getfCF() {
        return fCF;
    }

    public void setfCF(Boolean fCF) {
        this.fCF = fCF;
    }

    public Boolean getRc() {
        return rc;
    }

    public void setRc(Boolean rc) {
        this.rc = rc;
    }

    public CFData getcFData() {
        return cFData;
    }

    public void setcFData(CFData cFData) {
        this.cFData = cFData;
    }

    public List<VpDatum> getVpData() {
        return vpData;
    }

    public void setVpData(List<VpDatum> vpData) {
        this.vpData = vpData;
    }

    public String getOlcHours() {
        return olcHours;
    }

    public void setOlcHours(String olcHours) {
        this.olcHours = olcHours;
    }

    public String getFaqJson() {
        return faqJson;
    }

    public void setFaqJson(String faqJson) {
        this.faqJson = faqJson;
    }

    public Object getHlsJson() {
        return hlsJson;
    }

    public void setHlsJson(Object hlsJson) {
        this.hlsJson = hlsJson;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    public Boolean getMahaPack() {
        return mahaPack;
    }

    public void setMahaPack(Boolean mahaPack) {
        this.mahaPack = mahaPack;
    }

    public Boolean getCertification() {
        return certification;
    }

    public void setCertification(Boolean certification) {
        this.certification = certification;
    }

    public Boolean getIosAvailable() {
        return iosAvailable;
    }

    public void setIosAvailable(Boolean iosAvailable) {
        this.iosAvailable = iosAvailable;
    }

    public Boolean getIsMultiValidity() {
        return isMultiValidity;
    }

    public void setIsMultiValidity(Boolean isMultiValidity) {
        this.isMultiValidity = isMultiValidity;
    }

    public String getIosPrice() {
        return iosPrice;
    }

    public void setIosPrice(String iosPrice) {
        this.iosPrice = iosPrice;
    }

    public Object getWebinarId() {
        return webinarId;
    }

    public void setWebinarId(Object webinarId) {
        this.webinarId = webinarId;
    }

    public Integer getOlcAccountId() {
        return olcAccountId;
    }

    public void setOlcAccountId(Integer olcAccountId) {
        this.olcAccountId = olcAccountId;
    }

    public String getOlcSource() {
        return olcSource;
    }

    public void setOlcSource(String olcSource) {
        this.olcSource = olcSource;
    }

    public String getOlcMetaJson() {
        return olcMetaJson;
    }

    public void setOlcMetaJson(String olcMetaJson) {
        this.olcMetaJson = olcMetaJson;
    }

    public String getIntroVideoUrl() {
        return introVideoUrl;
    }

    public void setIntroVideoUrl(String introVideoUrl) {
        this.introVideoUrl = introVideoUrl;
    }

    public List<Object> getSalientFeatureIds() {
        return salientFeatureIds;
    }

    public void setSalientFeatureIds(List<Object> salientFeatureIds) {
        this.salientFeatureIds = salientFeatureIds;
    }

    public PackageDescription getPackageDescription() {
        return packageDescription;
    }

    public void setPackageDescription(PackageDescription packageDescription) {
        this.packageDescription = packageDescription;
    }

    public List<Object> getTestimonialBean() {
        return testimonialBean;
    }

    public void setTestimonialBean(List<Object> testimonialBean) {
        this.testimonialBean = testimonialBean;
    }

    public List<Object> getMahapackComparison() {
        return mahapackComparison;
    }

    public void setMahapackComparison(List<Object> mahapackComparison) {
        this.mahapackComparison = mahapackComparison;
    }

    public String getBatchStartDate() {
        return batchStartDate;
    }

    public void setBatchStartDate(String batchStartDate) {
        this.batchStartDate = batchStartDate;
    }

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public List<Integer> getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(List<Integer> courseIds) {
        this.courseIds = courseIds;
    }

    public String getBatchEndDate() {
	return batchEndDate;
	}

}
