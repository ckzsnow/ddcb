package com.dd.dao;

import java.util.List;

import com.dd.models.WorkYearModel;

public interface IWorkYearDao {

	public List<WorkYearModel> getAllWorkYear();
	
	public WorkYearModel getWorkYearById(int id);
	
}
