
package pojo.createCouponResponse;

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
    "artefactTypeInc",
    "artefactIdsIncluded",
    "artefactIdsExcluded",
    "artefactTypeExc"
})
@Generated("jsonschema2pojo")
public class ArtefactRequest {

    @JsonProperty("artefactTypeInc")
    private String artefactTypeInc;
    @JsonProperty("artefactIdsIncluded")
    private List<String> artefactIdsIncluded;
    @JsonProperty("artefactIdsExcluded")
    private List<Object> artefactIdsExcluded;
    @JsonProperty("artefactTypeExc")
    private String artefactTypeExc;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("artefactTypeInc")
    public String getArtefactTypeInc() {
        return artefactTypeInc;
    }

    @JsonProperty("artefactTypeInc")
    public void setArtefactTypeInc(String artefactTypeInc) {
        this.artefactTypeInc = artefactTypeInc;
    }

    @JsonProperty("artefactIdsIncluded")
    public List<String> getArtefactIdsIncluded() {
        return artefactIdsIncluded;
    }

    @JsonProperty("artefactIdsIncluded")
    public void setArtefactIdsIncluded(List<String> artefactIdsIncluded) {
        this.artefactIdsIncluded = artefactIdsIncluded;
    }

    @JsonProperty("artefactIdsExcluded")
    public List<Object> getArtefactIdsExcluded() {
        return artefactIdsExcluded;
    }

    @JsonProperty("artefactIdsExcluded")
    public void setArtefactIdsExcluded(List<Object> artefactIdsExcluded) {
        this.artefactIdsExcluded = artefactIdsExcluded;
    }

    @JsonProperty("artefactTypeExc")
    public String getArtefactTypeExc() {
        return artefactTypeExc;
    }

    @JsonProperty("artefactTypeExc")
    public void setArtefactTypeExc(String artefactTypeExc) {
        this.artefactTypeExc = artefactTypeExc;
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
