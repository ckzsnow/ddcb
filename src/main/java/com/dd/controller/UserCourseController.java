package com.dd.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dd.models.ResultModel;
import com.dd.service.IUserCourseService;
import com.dd.utils.ConvertRequestMapToMap;

@Controller
@RequestMapping("/userCourse")
public class UserCourseController {

	private static final Logger logger = LoggerFactory.getLogger(UserCourseController.class);
	
	@Autowired
	private IUserCourseService userCourseService;
	
	@RequestMapping("/getUserCourseByUserIdAndUserType")
	@ResponseBody
	public ResultModel getUserCourseByUserIdAndUserType(HttpServletRequest request) {
		logger.debug("getUserCourseByUserIdAndUserType");
		String userId = (String) request.getSession().getAttribute("userId");
		if(userId == null || userId.isEmpty()) {
			ResultModel rm = new ResultModel();
			rm.setErrorCode("9900");
			rm.setErrorMsg("会话已过期，请重新登录");
			return rm;
		}
		String userType = request.getParameter("userType");
		String page = request.getParameter("page");
		String amountPerPage = request.getParameter("amountPerPage");
		return userCourseService.getUserCourseByUserIdAndUserType(userId, userType, page, amountPerPage);
	}
	
	
	
	@RequestMapping("/getUserCourseByCourseIdAndUserType")
	@ResponseBody
	public ResultModel getUserCourseByCourseIdAndUserType(HttpServletRequest request) {
		logger.debug("getUserCourseByCourseIdAndUserType");
		String userId = (String) request.getSession().getAttribute("userId");
		if(userId == null || userId.isEmpty()) {
			ResultModel rm = new ResultModel();
			rm.setErrorCode("9900");
			rm.setErrorMsg("会话已过期，请重新登录");
			return rm;
		}
		String courseId = request.getParameter("courseId");
		String userType = request.getParameter("userType");
		String page = request.getParameter("page");
		String amountPerPage = request.getParameter("amountPerPage");
		return userCourseService.getUserCourseByCourseIdAndUserType(courseId, userType, page, amountPerPage);
	}	
	
	@RequestMapping("/getUserAmountForCourse")
	@ResponseBody
	public ResultModel getUserAmountForCourse(HttpServletRequest request) {
		logger.debug("getUserAmountForCourse");
		String userId = (String) request.getSession().getAttribute("userId");
		if(userId == null || userId.isEmpty()) {
			ResultModel rm = new ResultModel();
			rm.setErrorCode("9900");
			rm.setErrorMsg("会话已过期，请重新登录");
			return rm;
		}
		String courseId = request.getParameter("courseId");
		return userCourseService.getUserAmountForCourse(courseId);
	}	
	
	@RequestMapping("/addUserCourse")
	@ResponseBody
	public ResultModel addUserCourse(HttpServletRequest request) {
		logger.debug("addUserCourse");
		String userId = (String) request.getSession().getAttribute("userId");
		if(userId == null || userId.isEmpty()) {
			ResultModel rm = new ResultModel();
			rm.setErrorCode("9900");
			rm.setErrorMsg("会话已过期，请重新登录");
			return rm;
		}
		return userCourseService.addUserCourse(ConvertRequestMapToMap.convert(request.getParameterMap()));
	}	
	
	@RequestMapping("/deleteUserCourseByUserIdAndCourseIdAndUserType")
	@ResponseBody
	public ResultModel deleteUserCourseByUserIdAndCourseIdAndUserType(HttpServletRequest request) {
		logger.debug("deleteUserCourseByUserIdAndCourseIdAndUserType");
		String userId = (String) request.getSession().getAttribute("userId");
		if(userId == null || userId.isEmpty()) {
			ResultModel rm = new ResultModel();
			rm.setErrorCode("9900");
			rm.setErrorMsg("会话已过期，请重新登录");
			return rm;
		}
		String courseId = request.getParameter("courseId");
		String userType = request.getParameter("userType");
		return userCourseService.deleteUserCourseByUserIdAndCourseIdAndUserType(userId, courseId, userType);
	}	
	
}
