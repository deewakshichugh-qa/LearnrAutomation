
package pojo.getVideoList;

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
    "name",
    "duration",
    "demoUrl",
    "desc",
    "url",
    "isFree",
    "pImg",
    "tImg",
    "faculty",
    "drmEnabled",
    "hlsUrl",
    "dashUrl",
    "drmProviderId"
})
@Generated("jsonschema2pojo")
public class Video {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("duration")
    private Integer duration;
    @JsonProperty("demoUrl")
    private Object demoUrl;
    @JsonProperty("desc")
    private String desc;
    @JsonProperty("url")
    private String url;
    @JsonProperty("isFree")
    private Boolean isFree;
    @JsonProperty("pImg")
    private Object pImg;
    @JsonProperty("tImg")
    private String tImg;
    @JsonProperty("faculty")
    private List<Faculty> faculty;
    @JsonProperty("drmEnabled")
    private Boolean drmEnabled;
    @JsonProperty("hlsUrl")
    private String hlsUrl;
    @JsonProperty("dashUrl")
    private String dashUrl;
    @JsonProperty("drmProviderId")
    private Integer drmProviderId;
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

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("duration")
    public Integer getDuration() {
        return duration;
    }

    @JsonProperty("duration")
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @JsonProperty("demoUrl")
    public Object getDemoUrl() {
        return demoUrl;
    }

    @JsonProperty("demoUrl")
    public void setDemoUrl(Object demoUrl) {
        this.demoUrl = demoUrl;
    }

    @JsonProperty("desc")
    public String getDesc() {
        return desc;
    }

    @JsonProperty("desc")
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("isFree")
    public Boolean getIsFree() {
        return isFree;
    }

    @JsonProperty("isFree")
    public void setIsFree(Boolean isFree) {
        this.isFree = isFree;
    }

    @JsonProperty("pImg")
    public Object getpImg() {
        return pImg;
    }

    @JsonProperty("pImg")
    public void setpImg(Object pImg) {
        this.pImg = pImg;
    }

    @JsonProperty("tImg")
    public String gettImg() {
        return tImg;
    }

    @JsonProperty("tImg")
    public void settImg(String tImg) {
        this.tImg = tImg;
    }

    @JsonProperty("faculty")
    public List<Faculty> getFaculty() {
        return faculty;
    }

    @JsonProperty("faculty")
    public void setFaculty(List<Faculty> faculty) {
        this.faculty = faculty;
    }

    @JsonProperty("drmEnabled")
    public Boolean getDrmEnabled() {
        return drmEnabled;
    }

    @JsonProperty("drmEnabled")
    public void setDrmEnabled(Boolean drmEnabled) {
        this.drmEnabled = drmEnabled;
    }

    @JsonProperty("hlsUrl")
    public String getHlsUrl() {
        return hlsUrl;
    }

    @JsonProperty("hlsUrl")
    public void setHlsUrl(String hlsUrl) {
        this.hlsUrl = hlsUrl;
    }

    @JsonProperty("dashUrl")
    public String getDashUrl() {
        return dashUrl;
    }

    @JsonProperty("dashUrl")
    public void setDashUrl(String dashUrl) {
        this.dashUrl = dashUrl;
    }

    @JsonProperty("drmProviderId")
    public Integer getDrmProviderId() {
        return drmProviderId;
    }

    @JsonProperty("drmProviderId")
    public void setDrmProviderId(Integer drmProviderId) {
        this.drmProviderId = drmProviderId;
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
