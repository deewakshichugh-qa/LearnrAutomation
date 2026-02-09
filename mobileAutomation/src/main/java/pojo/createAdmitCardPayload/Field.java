
package pojo.createAdmitCardPayload;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "type",
    "length"
})
@Generated("jsonschema2pojo")
public class Field
{

    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("length")
    private Object length;
   
    public Field(String name, String type, Object length) {
        this.name = name;
        this.type = type;
        this.length = length;
	}

	@JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("length")
    public Object getLength() {
        return length;
    }

    @JsonProperty("length")
    public void setLength(Object length) {
        this.length = length;
    }

}
