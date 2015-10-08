package com.dd.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dd.models.ResultModel;
import com.dd.service.IRegionInfoService;

@Controller
@RequestMapping("/regionInfo")
public class RegionInfoController {

	private static final Logger logger = LoggerFactory.getLogger(RegionInfoController.class);
	
	@Autowired
	private IRegionInfoService regionInfoService;
	
	@RequestMapping("/getAllProvince")
	@ResponseBody
	public ResultModel getAllProvince() {
		logger.debug("getAllProvince");
		return regionInfoService.getAllProvince();
	}
	
	@RequestMapping("/getAllCityByProvinceId")
	@ResponseBody
	public ResultModel getAllCityByProvinceId(HttpServletRequest request) {
		logger.debug("getAllCityByProvinceId");
		String provinceId = request.getParameter("provinceId");
		return regionInfoService.getAllCityByProvinceId(provinceId);
	}
	
	@RequestMapping("/getAllAreaByCityId")
	@ResponseBody
	public ResultModel getAllAreaByCityId(HttpServletRequest request) {
		logger.debug("getAllAreaByCityId");
		String cityId = request.getParameter("cityId");
		return regionInfoService.getAllAreaByCityId(cityId);
	}
}
