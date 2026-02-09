package pojo.PackageDetailsAlreadyPurchased;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "name",
    "thumbnail",
    "childPackageCount"
})

@Generated("jsonschema2pojo")
public class ExamDataAlreadyPurchased {
	
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("thumbnail")
    private String thumbnail;
    @JsonProperty("childPackageCount")
    private int childPackageCount;

    @JsonProperty("id")
    public int getId() {
        return id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("thumbnail")
    public String getThumbnail() {
        return thumbnail;
    }

    @JsonProperty("childPackageCount")
    public int getChildPackageCount() {
        return childPackageCount;
    }
    
}
