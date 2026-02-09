package pojo.examDetails;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
    "childPackageMap",
    "packages",
    "subParentCount"
})


public class ExamData {

	  @JsonProperty("childPackageMap")
      private Map<String, Integer> childPackageMap;
      @JsonProperty("packages")
      private List<ExamPackages> packages;
      @JsonProperty("subParentCount")
      private int subParentCount;

      // Getters for the properties

      @JsonProperty("childPackageMap")
      public Map<String, Integer> getChildPackageMap() {
          return childPackageMap;
      }

      @JsonProperty("packages")
      public List<ExamPackages> getPackages() {
          return packages;
      }

      @JsonProperty("subParentCount")
      public int getSubParentCount() {
          return subParentCount;
      }
  
}
