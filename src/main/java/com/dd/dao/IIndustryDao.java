package com.dd.dao;

import java.util.List;

import com.dd.models.IndustryModel;

public interface IIndustryDao {

	public IndustryModel getIndustryByIndustryId(int industryId);
	
	public List<IndustryModel> getAllIndustry();
	
	public boolean addIndustry(IndustryModel industryModel);
	
	public boolean deleteIndustryByIndustryId(int industryId);
	
	public boolean updateIndustryNameByIndustryId(String industryName, int industryId);
	
}
