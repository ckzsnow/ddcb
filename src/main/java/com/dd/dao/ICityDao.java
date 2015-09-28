package com.dd.dao;

import java.util.List;

import com.dd.models.CityModel;

public interface ICityDao {

	public CityModel getCityByCityId(String cityId);
	
	public List<CityModel> getAllCityByProvinceId(String provinceId);
	
}
