package com.dd.service;

import com.dd.models.ResultModel;

public interface ICategoryInfoService {

	public ResultModel getStageByStageId(String stageId);
	
	public ResultModel getAllStage();
	
	public ResultModel getFieldByFieldId(String fieldId);
	
	public ResultModel getFieldByIndustryId(String industryId);
	
	public ResultModel getIndustryByIndustryId(String industryId);
	
	public ResultModel getAllIndustry();
	
}