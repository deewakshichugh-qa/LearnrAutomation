
package pojo.searchQuestionResponse;

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
    "clientRef4",
    "clientRef5",
    "clientRef6",
    "clientRef3",
    "clientRef1",
    "clientRef2",
    "additionalTags"
})
@Generated("jsonschema2pojo")
public class AllTagsIdValueMapping__1 {

    @JsonProperty("clientRef4")
    private ClientRef4__1 clientRef4;
    @JsonProperty("clientRef5")
    private ClientRef5__1 clientRef5;
    @JsonProperty("clientRef6")
    private ClientRef6__1 clientRef6;
    @JsonProperty("clientRef3")
    private ClientRef3__1 clientRef3;
    @JsonProperty("clientRef1")
    private ClientRef1__1 clientRef1;
    @JsonProperty("clientRef2")
    private ClientRef2__1 clientRef2;
    @JsonProperty("additionalTags")
    private AdditionalTags additionalTags;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("clientRef4")
    public ClientRef4__1 getClientRef4() {
        return clientRef4;
    }

    @JsonProperty("clientRef4")
    public void setClientRef4(ClientRef4__1 clientRef4) {
        this.clientRef4 = clientRef4;
    }

    @JsonProperty("clientRef5")
    public ClientRef5__1 getClientRef5() {
        return clientRef5;
    }

    @JsonProperty("clientRef5")
    public void setClientRef5(ClientRef5__1 clientRef5) {
        this.clientRef5 = clientRef5;
    }

    @JsonProperty("clientRef6")
    public ClientRef6__1 getClientRef6() {
        return clientRef6;
    }

    @JsonProperty("clientRef6")
    public void setClientRef6(ClientRef6__1 clientRef6) {
        this.clientRef6 = clientRef6;
    }

    @JsonProperty("clientRef3")
    public ClientRef3__1 getClientRef3() {
        return clientRef3;
    }

    @JsonProperty("clientRef3")
    public void setClientRef3(ClientRef3__1 clientRef3) {
        this.clientRef3 = clientRef3;
    }

    @JsonProperty("clientRef1")
    public ClientRef1__1 getClientRef1() {
        return clientRef1;
    }

    @JsonProperty("clientRef1")
    public void setClientRef1(ClientRef1__1 clientRef1) {
        this.clientRef1 = clientRef1;
    }

    @JsonProperty("clientRef2")
    public ClientRef2__1 getClientRef2() {
        return clientRef2;
    }

    @JsonProperty("clientRef2")
    public void setClientRef2(ClientRef2__1 clientRef2) {
        this.clientRef2 = clientRef2;
    }

    @JsonProperty("additionalTags")
    public AdditionalTags getAdditionalTags() {
        return additionalTags;
    }

    @JsonProperty("additionalTags")
    public void setAdditionalTags(AdditionalTags additionalTags) {
        this.additionalTags = additionalTags;
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
