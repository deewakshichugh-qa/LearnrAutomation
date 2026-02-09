package pojo.PackageDetailsNewPurchased;

import java.util.List;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "examList"
})

@Generated("jsonschema2pojo")
public class ExamListNewPurchased {
	
	 @JsonProperty("examList")
     private List<ExamDataNewPurchased> examList;
	 
	 @JsonProperty("examList")
	 public List<ExamDataNewPurchased> getExamList(){
		 return examList;
	 }
	 
}
