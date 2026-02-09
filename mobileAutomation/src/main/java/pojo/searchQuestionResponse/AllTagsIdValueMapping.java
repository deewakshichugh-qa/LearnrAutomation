
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
    "clientRef8",
    "clientRef4",
    "clientRef5",
    "clientRef6",
    "clientRef7",
    "clientRef1",
    "clientRef2",
    "clientRef3"
})
@Generated("jsonschema2pojo")
public class AllTagsIdValueMapping {

    @JsonProperty("clientRef8")
    private ClientRef8 clientRef8;
    @JsonProperty("clientRef4")
    private ClientRef4 clientRef4;
    @JsonProperty("clientRef5")
    private ClientRef5 clientRef5;
    @JsonProperty("clientRef6")
    private ClientRef6 clientRef6;
    @JsonProperty("clientRef7")
    private ClientRef7 clientRef7;
    @JsonProperty("clientRef1")
    private ClientRef1 clientRef1;
    @JsonProperty("clientRef2")
    private ClientRef2 clientRef2;
    @JsonProperty("clientRef3")
    private ClientRef3 clientRef3;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("clientRef8")
    public ClientRef8 getClientRef8() {
        return clientRef8;
    }

    @JsonProperty("clientRef8")
    public void setClientRef8(ClientRef8 clientRef8) {
        this.clientRef8 = clientRef8;
    }

    @JsonProperty("clientRef4")
    public ClientRef4 getClientRef4() {
        return clientRef4;
    }

    @JsonProperty("clientRef4")
    public void setClientRef4(ClientRef4 clientRef4) {
        this.clientRef4 = clientRef4;
    }

    @JsonProperty("clientRef5")
    public ClientRef5 getClientRef5() {
        return clientRef5;
    }

    @JsonProperty("clientRef5")
    public void setClientRef5(ClientRef5 clientRef5) {
        this.clientRef5 = clientRef5;
    }

    @JsonProperty("clientRef6")
    public ClientRef6 getClientRef6() {
        return clientRef6;
    }

    @JsonProperty("clientRef6")
    public void setClientRef6(ClientRef6 clientRef6) {
        this.clientRef6 = clientRef6;
    }

    @JsonProperty("clientRef7")
    public ClientRef7 getClientRef7() {
        return clientRef7;
    }

    @JsonProperty("clientRef7")
    public void setClientRef7(ClientRef7 clientRef7) {
        this.clientRef7 = clientRef7;
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
    public ClientRef3 getClientRef3() {
        return clientRef3;
    }

    @JsonProperty("clientRef3")
    public void setClientRef3(ClientRef3 clientRef3) {
        this.clientRef3 = clientRef3;
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
