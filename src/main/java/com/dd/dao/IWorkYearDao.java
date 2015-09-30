package com.dd.dao;

import java.util.List;

import com.dd.models.IndustryModel;
import com.dd.models.WorkYearModel;

public interface IWorkYearDao {

	public List<WorkYearModel> getAllWorkYear();
	
	public IndustryModel getWorkYearById(int id);
	
	public boolean addWorkYear(WorkYearModel workYearModel);
	
	public boolean deleteWorkYearById(int id);
	
	public boolean updateWorkYearNameById(String workYearName, int id);
	
}
