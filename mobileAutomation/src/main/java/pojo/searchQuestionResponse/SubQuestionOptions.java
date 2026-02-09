
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
    "A",
    "B",
    "C",
    "D",
    "E"
})
@Generated("jsonschema2pojo")
public class SubQuestionOptions {

    @JsonProperty("A")
    private String a;
    @JsonProperty("B")
    private String b;
    @JsonProperty("C")
    private String c;
    @JsonProperty("D")
    private String d;
    @JsonProperty("E")
    private String e;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("A")
    public String getA() {
        return a;
    }

    @JsonProperty("A")
    public void setA(String a) {
        this.a = a;
    }

    @JsonProperty("B")
    public String getB() {
        return b;
    }

    @JsonProperty("B")
    public void setB(String b) {
        this.b = b;
    }

    @JsonProperty("C")
    public String getC() {
        return c;
    }

    @JsonProperty("C")
    public void setC(String c) {
        this.c = c;
    }

    @JsonProperty("D")
    public String getD() {
        return d;
    }

    @JsonProperty("D")
    public void setD(String d) {
        this.d = d;
    }

    @JsonProperty("E")
    public String getE() {
        return e;
    }

    @JsonProperty("E")
    public void setE(String e) {
        this.e = e;
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
