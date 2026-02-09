
package pojo.createPackagePayload;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class PackageDescription {

    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("examCovered")
    @Expose
    private String examCovered;
    @SerializedName("studyPlan")
    @Expose
    private String studyPlan;
    @SerializedName("subjectsCovered")
    @Expose
    private String subjectsCovered;
    @SerializedName("listOfOtherProductTypes")
    @Expose
    private String listOfOtherProductTypes;

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getExamCovered() {
        return examCovered;
    }

    public void setExamCovered(String examCovered) {
        this.examCovered = examCovered;
    }

    public String getStudyPlan() {
        return studyPlan;
    }

    public void setStudyPlan(String studyPlan) {
        this.studyPlan = studyPlan;
    }

    public String getSubjectsCovered() {
        return subjectsCovered;
    }

    public void setSubjectsCovered(String subjectsCovered) {
        this.subjectsCovered = subjectsCovered;
    }

    public String getListOfOtherProductTypes() {
        return listOfOtherProductTypes;
    }

    public void setListOfOtherProductTypes(String listOfOtherProductTypes) {
        this.listOfOtherProductTypes = listOfOtherProductTypes;
    }

}
