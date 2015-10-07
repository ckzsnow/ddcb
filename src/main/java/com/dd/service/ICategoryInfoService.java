package com.dd.service;

import com.dd.models.ResultModel;

public interface ICategoryInfoService {

	public ResultModel getStageByStageId(int stageId);
	
	public ResultModel getStageByFieldId(int fieldId);
	
	public ResultModel getFieldByFieldId(int fieldId);
	
	public ResultModel getFieldByIndustryId(int industryId);
	
	public ResultModel getIndustryByIndustryId(int industryId);
	
	public ResultModel getAllIndustry();
	
}