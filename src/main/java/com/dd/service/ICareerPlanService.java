package com.dd.service;

import com.dd.models.ResultModel;

public interface ICareerPlanService {

	public ResultModel getAllCareerPlan();
	
	public ResultModel getCareerPlanById(String id);
	
}