package com.dd.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dd.dao.IAreaDao;
import com.dd.dao.ICityDao;
import com.dd.dao.IProvinceDao;
import com.dd.models.AreaModel;
import com.dd.models.CityModel;
import com.dd.models.ProvinceModel;
import com.dd.models.ResultModel;
import com.dd.service.IRegionInfoService;

@Service("regionInfoService")
public class RegionInfoService implements IRegionInfoService {

	@Autowired
	private IProvinceDao provinceDao;
	
	@Autowired
	private ICityDao cityDao;
	
	@Autowired
	private IAreaDao areaDao;

	@Override
	public ResultModel getProvinceByProvinceId(String provinceId) {
		ResultModel ret = new ResultModel();
		ProvinceModel provinceModel = provinceDao.getProvinceByProvinceId(provinceId);
		if(provinceModel == null) {
			ret.setErrorCode("4001");
			ret.setErrorMsg("未查询到数据");
		} else {
			ret.setErrorCode("4000");
			ret.setErrorMsg("操作成功");
			ret.setResult(provinceModel);
		}
		return ret;
	}

	@Override
	public ResultModel getAllProvince() {
		ResultModel ret = new ResultModel();
		List<ProvinceModel> provinceModelList = provinceDao.getAllProvince();
		if(provinceModelList == null || provinceModelList.size() == 0) {
			ret.setErrorCode("4001");
			ret.setErrorMsg("未查询到数据");
		} else {
			ret.setErrorCode("4000");
			ret.setErrorMsg("操作成功");
			ret.setResultList(Arrays.asList(provinceModelList.toArray()));
		}
		return ret;
	}

	@Override
	public ResultModel getCityByCityId(String cityId) {
		ResultModel ret = new ResultModel();
		CityModel cityModel = cityDao.getCityByCityId(cityId);
		if(cityModel == null) {
			ret.setErrorCode("4001");
			ret.setErrorMsg("未查询到数据");
		} else {
			ret.setErrorCode("4000");
			ret.setErrorMsg("操作成功");
			ret.setResult(cityModel);
		}
		return ret;
	}

	@Override
	public ResultModel getAllCityByProvinceId(String provinceId) {
		ResultModel ret = new ResultModel();
		List<CityModel> cityModelList = cityDao.getAllCityByProvinceId(provinceId);
		if(cityModelList == null || cityModelList.size() == 0) {
			ret.setErrorCode("4001");
			ret.setErrorMsg("未查询到数据");
		} else {
			ret.setErrorCode("4000");
			ret.setErrorMsg("操作成功");
			ret.setResultList(Arrays.asList(cityModelList.toArray()));
		}
		return ret;
	}

	@Override
	public ResultModel getAreaByAreaId(String areaId) {
		ResultModel ret = new ResultModel();
		AreaModel areaModel = areaDao.getAreaByAreaId(areaId);
		if(areaModel == null) {
			ret.setErrorCode("4001");
			ret.setErrorMsg("未查询到数据");
		} else {
			ret.setErrorCode("4000");
			ret.setErrorMsg("操作成功");
			ret.setResult(areaModel);
		}
		return ret;
	}

	@Override
	public ResultModel getAllAreaByCityId(String cityId) {
		ResultModel ret = new ResultModel();
		List<AreaModel> areaModelList = areaDao.getAllAreaByCityId(cityId);
		if(areaModelList == null || areaModelList.size() == 0) {
			ret.setErrorCode("4001");
			ret.setErrorMsg("未查询到数据");
		} else {
			ret.setErrorCode("4000");
			ret.setErrorMsg("操作成功");
			ret.setResultList(Arrays.asList(areaModelList.toArray()));
		}
		return ret;
	}	
}
