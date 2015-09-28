package com.dd.dao;

import java.util.List;

import com.dd.models.FieldModel;

public interface IFieldDao {

	public FieldModel getFieldByFieldId(int fieldId);
	
	public List<FieldModel> getAllField();
	
	public List<FieldModel> getFieldByIndustryId(int industryId);
	
	public boolean addField(FieldModel fieldModel);
	
	public boolean deleteFieldByFieldId(int fieldId);
	
	public boolean updateFieldNameByFieldId(String fieldName, int fieldId);
	
	public boolean updateIndustryIdByFieldId(int industryId, int fieldId);
	
}
