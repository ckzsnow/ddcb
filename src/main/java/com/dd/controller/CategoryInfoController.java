package com.dd.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dd.models.ResultModel;
import com.dd.service.ICategoryInfoService;

@Controller
@RequestMapping("/courseCategoryInfo")
public class CategoryInfoController {

	private static final Logger logger = LoggerFactory.getLogger(CategoryInfoController.class);
	
	@Autowired
	private ICategoryInfoService categoryInfoService;
	
	@RequestMapping("/getAllIndustry")
	@ResponseBody
	public ResultModel getAllIndustry() {
		logger.debug("getAllIndustry");
		return categoryInfoService.getAllIndustry();
	}
	
	@RequestMapping("/getFieldByIndustryId")
	@ResponseBody
	public ResultModel getFieldByIndustryId(HttpServletRequest request) {
		logger.debug("getFieldByIndustryId");
		String industryId = request.getParameter("industryId");
		return categoryInfoService.getFieldByIndustryId(industryId);
	}
	
	@RequestMapping("/getAllStage")
	@ResponseBody
	public ResultModel getAllStage() {
		logger.debug("getAllStage");
		return categoryInfoService.getAllStage();
	}
}
