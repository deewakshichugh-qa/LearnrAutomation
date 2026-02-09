package pojo.testdata;

import java.util.List;

public class BatchTestData {

	private String titleMahapack;
	private int totalChildPackage;
	private int totalParentPackage;
	private int totalExams;
	private String titleExam;
	private String titleParentPackage;
	private List<String> parentContent;
	private String titleChildPackage;
	private String childContent;

	public String getTitleMahapack() {
		return titleMahapack;
	}

	public void setTitleMahapack(String titleMahapack) {
		this.titleMahapack = titleMahapack;
	}

	public int getTotalChildPackage() {
		return totalChildPackage;
	}

	public List<String> getParentContent() {
		return parentContent;
	}

	public void setParentContent(List<String> parentContent) {
		this.parentContent = parentContent;
	}
	
	public String getTitleExam() {
		return titleExam;
	}

	public void setTitleExam(String titleExam) {
		this.titleExam = titleExam;
	}

	public String getChildContent() {
		return childContent;
	}

	public void setChildContent(String childContent) {
		this.childContent = childContent;
	}

	public void setTotalChildPackage(int totalChildPackage) {
		this.totalChildPackage = totalChildPackage;
	}

	public int getTotalParentPackage() {
		return totalParentPackage;
	}

	public void setTotalParentPackage(int totalParentPackage) {
		this.totalParentPackage = totalParentPackage;
	}

	public int getTotalExams() {
		return totalExams;
	}

	public void setTotalExams(int totalExams) {
		this.totalExams = totalExams;
	}

	public String getTitleParentPackage() {
		return titleParentPackage;
	}

	public void setTitleParentPackage(String titleParentPackage) {
		this.titleParentPackage = titleParentPackage;
	}

	public String getTitleChildPackage() {
		return titleChildPackage;
	}

	public void setTitleChildPackage(String titleChildPackage) {
		this.titleChildPackage = titleChildPackage;
	}

}
