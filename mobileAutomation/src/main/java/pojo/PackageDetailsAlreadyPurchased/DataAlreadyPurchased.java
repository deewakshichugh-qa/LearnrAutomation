package pojo.PackageDetailsAlreadyPurchased;

import java.util.List;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "childPackageCount",
    "selectedExamList"
})

@Generated("jsonschema2pojo")
public class DataAlreadyPurchased {

	@JsonProperty("childPackageCount")
	private int childPackageCount;
	@JsonProperty("selectedExamList")
	private List<ExamDataAlreadyPurchased> selectedExamList;
	
	@JsonProperty("childPackageCount")
	public int getChildPackageCount() {
	    return childPackageCount;
	}
	
	@JsonProperty("selectedExamList")
	public List<ExamDataAlreadyPurchased> getSelectedExamList() {
	    return selectedExamList;
	}
}
