package com.dd.dao;

import java.util.List;

import com.dd.models.AreaModel;

public interface IAreaDao {

	public AreaModel getAreaByAreaId(String areaId);
	
	public List<AreaModel> getAllAreaByCityId(String cityId);
	
}
