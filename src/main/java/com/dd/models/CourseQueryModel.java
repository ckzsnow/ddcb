package com.dd.models;

public class CourseQueryModel {

	private String industryIdList;
	
	private String fieldIdList;
	
	private String stageIdList;
	
	public CourseQueryModel() {}

	public String getIndustryIdList() {
		return industryIdList;
	}

	public void setIndustryIdList(String industryIdList) {
		this.industryIdList = industryIdList;
	}

	public String getFieldIdList() {
		return fieldIdList;
	}

	public void setFieldIdList(String fieldIdList) {
		this.fieldIdList = fieldIdList;
	}

	public String getStageIdList() {
		return stageIdList;
	}

	public void setStageIdList(String stageIdList) {
		this.stageIdList = stageIdList;
	}
	
}
