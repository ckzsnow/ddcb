package com.dd.service;

import com.dd.models.ResultModel;

public interface IRegionInfoService {
	
	public ResultModel getProvinceByProvinceId(String provinceId);
	
	public ResultModel getAllProvince();

	public ResultModel getCityByCityId(String cityId);
	
	public ResultModel getAllCityByProvinceId(String provinceId);
	
	public ResultModel getAreaByAreaId(String areaId);
	
	public ResultModel getAllAreaByCityId(String cityId);
	
}