package pojo.mcSearchResultsResponse;


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
    "id",
    "olcCount",
    "videoCount",
    "categories",
    "subCategories",
    "olchoursVideos",
    "title",
    "iosAvailable",
    "createdAt",
    "updatedAt",
    "imgUrl",
    "tax",
    "maxPrice",
    "price",
    "isPrimary",
    "courses",
    "languages",
    "subjects",
    "suggestionCat",
    "tags",
    "freeContent",
    "onSale",
    "recommended",
    "freeContentAvailable",
    "dualValidity",
    "categoryList",
    "subParent",
    "webinarJsonUrl",
    "hlsJson",
    "slugTitle",
    "faculties"
})
@Generated("jsonschema2pojo")
public class Package {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("olcCount")
    private Integer olcCount;
    @JsonProperty("videoCount")
    private Integer videoCount;
    @JsonProperty("categories")
    private String categories;
    @JsonProperty("subCategories")
    private List<SubCategory> subCategories;
    @JsonProperty("olchoursVideos")
    private Double olchoursVideos;
    @JsonProperty("title")
    private String title;
    @JsonProperty("iosAvailable")
    private Boolean iosAvailable;
    @JsonProperty("createdAt")
    private String createdAt;
    @JsonProperty("updatedAt")
    private String updatedAt;
    @JsonProperty("imgUrl")
    private String imgUrl;
    @JsonProperty("tax")
    private Double tax;
    @JsonProperty("maxPrice")
    private Integer maxPrice;
    @JsonProperty("price")
    private Integer price;
    @JsonProperty("isPrimary")
    private Integer isPrimary;
    @JsonProperty("courses")
    private String courses;
    @JsonProperty("languages")
    private List<Language> languages;
    @JsonProperty("subjects")
    private List<Object> subjects;
    @JsonProperty("suggestionCat")
    private String suggestionCat;
    @JsonProperty("tags")
    private List<Object> tags;
    @JsonProperty("freeContent")
    private Boolean freeContent;
    @JsonProperty("onSale")
    private Boolean onSale;
    @JsonProperty("recommended")
    private Boolean recommended;
    @JsonProperty("freeContentAvailable")
    private Boolean freeContentAvailable;
    @JsonProperty("dualValidity")
    private Boolean dualValidity;
    @JsonProperty("categoryList")
    private List<String> categoryList;
    @JsonProperty("subParent")
    private Boolean subParent;
    @JsonProperty("webinarJsonUrl")
    private String webinarJsonUrl;
    @JsonProperty("hlsJson")
    private List<HlsJson> hlsJson;
    @JsonProperty("slugTitle")
    private String slugTitle;
    @JsonProperty("faculties")
    private List<Faculty> faculties;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("olcCount")
    public Integer getOlcCount() {
        return olcCount;
    }

    @JsonProperty("olcCount")
    public void setOlcCount(Integer olcCount) {
        this.olcCount = olcCount;
    }

    @JsonProperty("videoCount")
    public Integer getVideoCount() {
        return videoCount;
    }

    @JsonProperty("videoCount")
    public void setVideoCount(Integer videoCount) {
        this.videoCount = videoCount;
    }

    @JsonProperty("categories")
    public String getCategories() {
        return categories;
    }

    @JsonProperty("categories")
    public void setCategories(String categories) {
        this.categories = categories;
    }

    @JsonProperty("subCategories")
    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    @JsonProperty("subCategories")
    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    @JsonProperty("olchoursVideos")
    public Double getOlchoursVideos() {
        return olchoursVideos;
    }

    @JsonProperty("olchoursVideos")
    public void setOlchoursVideos(Double olchoursVideos) {
        this.olchoursVideos = olchoursVideos;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("iosAvailable")
    public Boolean getIosAvailable() {
        return iosAvailable;
    }

    @JsonProperty("iosAvailable")
    public void setIosAvailable(Boolean iosAvailable) {
        this.iosAvailable = iosAvailable;
    }

    @JsonProperty("createdAt")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("createdAt")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updatedAt")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updatedAt")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("imgUrl")
    public String getImgUrl() {
        return imgUrl;
    }

    @JsonProperty("imgUrl")
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @JsonProperty("tax")
    public Double getTax() {
        return tax;
    }

    @JsonProperty("tax")
    public void setTax(Double tax) {
        this.tax = tax;
    }

    @JsonProperty("maxPrice")
    public Integer getMaxPrice() {
        return maxPrice;
    }

    @JsonProperty("maxPrice")
    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    @JsonProperty("price")
    public Integer getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(Integer price) {
        this.price = price;
    }

    @JsonProperty("isPrimary")
    public Integer getIsPrimary() {
        return isPrimary;
    }

    @JsonProperty("isPrimary")
    public void setIsPrimary(Integer isPrimary) {
        this.isPrimary = isPrimary;
    }

    @JsonProperty("courses")
    public String getCourses() {
        return courses;
    }

    @JsonProperty("courses")
    public void setCourses(String courses) {
        this.courses = courses;
    }

    @JsonProperty("languages")
    public List<Language> getLanguages() {
        return languages;
    }

    @JsonProperty("languages")
    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    @JsonProperty("subjects")
    public List<Object> getSubjects() {
        return subjects;
    }

    @JsonProperty("subjects")
    public void setSubjects(List<Object> subjects) {
        this.subjects = subjects;
    }

    @JsonProperty("suggestionCat")
    public String getSuggestionCat() {
        return suggestionCat;
    }

    @JsonProperty("suggestionCat")
    public void setSuggestionCat(String suggestionCat) {
        this.suggestionCat = suggestionCat;
    }

    @JsonProperty("tags")
    public List<Object> getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    @JsonProperty("freeContent")
    public Boolean getFreeContent() {
        return freeContent;
    }

    @JsonProperty("freeContent")
    public void setFreeContent(Boolean freeContent) {
        this.freeContent = freeContent;
    }

    @JsonProperty("onSale")
    public Boolean getOnSale() {
        return onSale;
    }

    @JsonProperty("onSale")
    public void setOnSale(Boolean onSale) {
        this.onSale = onSale;
    }

    @JsonProperty("recommended")
    public Boolean getRecommended() {
        return recommended;
    }

    @JsonProperty("recommended")
    public void setRecommended(Boolean recommended) {
        this.recommended = recommended;
    }

    @JsonProperty("freeContentAvailable")
    public Boolean getFreeContentAvailable() {
        return freeContentAvailable;
    }

    @JsonProperty("freeContentAvailable")
    public void setFreeContentAvailable(Boolean freeContentAvailable) {
        this.freeContentAvailable = freeContentAvailable;
    }

    @JsonProperty("dualValidity")
    public Boolean getDualValidity() {
        return dualValidity;
    }

    @JsonProperty("dualValidity")
    public void setDualValidity(Boolean dualValidity) {
        this.dualValidity = dualValidity;
    }

    @JsonProperty("categoryList")
    public List<String> getCategoryList() {
        return categoryList;
    }

    @JsonProperty("categoryList")
    public void setCategoryList(List<String> categoryList) {
        this.categoryList = categoryList;
    }

    @JsonProperty("subParent")
    public Boolean getSubParent() {
        return subParent;
    }

    @JsonProperty("subParent")
    public void setSubParent(Boolean subParent) {
        this.subParent = subParent;
    }

    @JsonProperty("webinarJsonUrl")
    public String getWebinarJsonUrl() {
        return webinarJsonUrl;
    }

    @JsonProperty("webinarJsonUrl")
    public void setWebinarJsonUrl(String webinarJsonUrl) {
        this.webinarJsonUrl = webinarJsonUrl;
    }

    @JsonProperty("hlsJson")
    public List<HlsJson> getHlsJson() {
        return hlsJson;
    }

    @JsonProperty("hlsJson")
    public void setHlsJson(List<HlsJson> hlsJson) {
        this.hlsJson = hlsJson;
    }

    @JsonProperty("slugTitle")
    public String getSlugTitle() {
        return slugTitle;
    }

    @JsonProperty("slugTitle")
    public void setSlugTitle(String slugTitle) {
        this.slugTitle = slugTitle;
    }

    @JsonProperty("faculties")
    public List<Faculty> getFaculties() {
        return faculties;
    }

    @JsonProperty("faculties")
    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
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

