package com.dd.dao;

import java.util.List;

import com.dd.models.ProvinceModel;

public interface IProvinceDao {

	public ProvinceModel getProvinceByProvinceId(String provinceId);
	
	public List<ProvinceModel> getAllProvince();
	
}
