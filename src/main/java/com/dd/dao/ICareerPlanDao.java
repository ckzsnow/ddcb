package com.dd.dao;

import java.util.List;

import com.dd.models.CareerPlanModel;

public interface ICareerPlanDao {

	public List<CareerPlanModel> getAllCareerPlan();
	
	public CareerPlanModel getCareerPlanById(int id);
	
}
