package com.dd.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dd.models.ResultModel;
import com.dd.service.ICareerPlanService;

@Controller
@RequestMapping("/careerPlan")
public class CareerPlanController {

	private static final Logger logger = LoggerFactory.getLogger(CareerPlanController.class);
	
	@Autowired
	private ICareerPlanService careerPlanService;
	
	@RequestMapping("/getAllCareerPlan")
	@ResponseBody
	public ResultModel getAllCareerPlan() {
		logger.debug("getAllCareerPlan");
		return careerPlanService.getAllCareerPlan();
	}
		
	@RequestMapping("/getCareerPlan")
	@ResponseBody
	public ResultModel getCareerPlan(HttpServletRequest request) {
		logger.debug("getCareerPlan");
		String careerPlanId = request.getParameter("careerPlanId");
		return careerPlanService.getCareerPlanById(careerPlanId);
	}	
	
}
