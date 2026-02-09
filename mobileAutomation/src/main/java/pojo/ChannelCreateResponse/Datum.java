
package pojo.ChannelCreateResponse;

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
    "db_data",
    "aws_data",
    "create_room_data"
})
@Generated("jsonschema2pojo")
public class Datum {

    @JsonProperty("db_data")
    private DbData dbData;
    @JsonProperty("aws_data")
    private AwsData awsData;
    @JsonProperty("create_room_data")
    private CreateRoomData createRoomData;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("db_data")
    public DbData getDbData() {
        return dbData;
    }

    @JsonProperty("db_data")
    public void setDbData(DbData dbData) {
        this.dbData = dbData;
    }

    @JsonProperty("aws_data")
    public AwsData getAwsData() {
        return awsData;
    }

    @JsonProperty("aws_data")
    public void setAwsData(AwsData awsData) {
        this.awsData = awsData;
    }

    @JsonProperty("create_room_data")
    public CreateRoomData getCreateRoomData() {
        return createRoomData;
    }

    @JsonProperty("create_room_data")
    public void setCreateRoomData(CreateRoomData createRoomData) {
        this.createRoomData = createRoomData;
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
