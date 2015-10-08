package com.dd.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dd.models.ResultModel;
import com.dd.service.IWorkYearInfoService;

@Controller
@RequestMapping("/workYearInfo")
public class WorkYearInfoController {

	private static final Logger logger = LoggerFactory.getLogger(WorkYearInfoController.class);
	
	@Autowired
	private IWorkYearInfoService workYearInfoService;
	
	@RequestMapping("/getAllWorkYear")
	@ResponseBody
	public ResultModel getAllWorkYear() {
		logger.debug("getAllWorkYear");
		return workYearInfoService.getAllWorkYear();
	}
	
	
	
	@RequestMapping("/getWorkYearById")
	@ResponseBody
	public ResultModel getWorkYearById(HttpServletRequest request) {
		logger.debug("getWorkYearById");
		String workYearId = request.getParameter("workYearId");
		return workYearInfoService.getWorkYearById(workYearId);
	}	
	
}
