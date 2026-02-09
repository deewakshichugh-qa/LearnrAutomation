package pojo.examDetails;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({
    "packageId",
    "title",
    "thumbnail",
    "categories",
    "communityLink",
    "liveTest",
    "liveTestCount",
    "liveTestExists"
})

public class ExamPackages {

	   @JsonProperty("packageId")
       private int packageId;
       @JsonProperty("title")
       private String title;
       @JsonProperty("thumbnail")
       private String thumbnail;
       @JsonProperty("categories")
       private List<String> categories;
       @JsonProperty("communityLink")
       private String communityLink;
       @JsonProperty("liveTest")
       private boolean liveTest;
       public boolean isLiveTest() {
		return liveTest;
	}

	public void setLiveTest(boolean liveTest) {
		this.liveTest = liveTest;
	}

	public boolean isLiveTestExists() {
		return liveTestExists;
	}

	public void setLiveTestExists(boolean liveTestExists) {
		this.liveTestExists = liveTestExists;
	}

	public int getLiveTestCount() {
		return liveTestCount;
	}

	public void setLiveTestCount(int liveTestCount) {
		this.liveTestCount = liveTestCount;
	}

	@JsonProperty("liveTestExists")
       private boolean liveTestExists;
       @JsonProperty("liveTestCount")
       private int liveTestCount;

       // Getters for the properties

       @JsonProperty("subParentCount")
       public int getPackageId() {
           return packageId;
       }

       @JsonProperty("title")
       public String getTitle() {
           return title;
       }

       @JsonProperty("thumbnail")
       public String getThumbnail() {
           return thumbnail;
       }

       @JsonProperty("categories")
       public List<String> getCategories() {
           return categories;
       }
       
       @JsonProperty("communityLink")
       public String getCommunityLink() {
           return communityLink;
       }
}
