package com.dd.controller;

import java.util.Map;

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
	
	@RequestMapping("/getUserCourseByUserType")
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
		Map<String, String> paramsMap = ConvertRequestMapToMap.convert(request.getParameterMap());
		paramsMap.put("user_id", userId);
		return userCourseService.addUserCourse(paramsMap);
	}	
	
	@RequestMapping("/deleteUserCourseByCourseIdAndUserType")
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
		return userCourseService.deleteUserCourseByCourseIdAndUserType(userId, courseId, userType);
	}	
	
	@RequestMapping("/getOngoingSubscribeCourse")
	@ResponseBody
	public ResultModel getOngoingSubscribeCourse(HttpServletRequest request) {
		logger.debug("getOngoingSubscribeCourse");
		String userId = (String) request.getSession().getAttribute("userId");
		if(userId == null || userId.isEmpty()) {
			ResultModel rm = new ResultModel();
			rm.setErrorCode("9900");
			rm.setErrorMsg("会话已过期，请重新登录");
			return rm;
		}
		String page = request.getParameter("page");
		String amountPerPage = request.getParameter("amountPerPage");
		return userCourseService.getOngoingSubscribeCourse(userId, page, amountPerPage);
	}
	
	@RequestMapping("/getFinishedSubscribeCourse")
	@ResponseBody
	public ResultModel getFinishedSubscribeCourse(HttpServletRequest request) {
		logger.debug("getFinishedSubscribeCourse");
		String userId = (String) request.getSession().getAttribute("userId");
		if(userId == null || userId.isEmpty()) {
			ResultModel rm = new ResultModel();
			rm.setErrorCode("9900");
			rm.setErrorMsg("会话已过期，请重新登录");
			return rm;
		}
		String page = request.getParameter("page");
		String amountPerPage = request.getParameter("amountPerPage");
		return userCourseService.getFinishedSubscribeCourse(userId, page, amountPerPage);
	}
	
	@RequestMapping("/getOngoingPublishCourse")
	@ResponseBody
	public ResultModel getOngoingPublishCourse(HttpServletRequest request) {
		logger.debug("getOngoingPublishCourse");
		String userId = (String) request.getSession().getAttribute("userId");
		if(userId == null || userId.isEmpty()) {
			ResultModel rm = new ResultModel();
			rm.setErrorCode("9900");
			rm.setErrorMsg("会话已过期，请重新登录");
			return rm;
		}
		String page = request.getParameter("page");
		String amountPerPage = request.getParameter("amountPerPage");
		return userCourseService.getOngoingPublishCourse(userId, page, amountPerPage);
	}
	
	@RequestMapping("/getFinishedPublishCourse")
	@ResponseBody
	public ResultModel getFinishedPublishCourse(HttpServletRequest request) {
		logger.debug("getFinishedPublishCourse");
		String userId = (String) request.getSession().getAttribute("userId");
		if(userId == null || userId.isEmpty()) {
			ResultModel rm = new ResultModel();
			rm.setErrorCode("9900");
			rm.setErrorMsg("会话已过期，请重新登录");
			return rm;
		}
		String page = request.getParameter("page");
		String amountPerPage = request.getParameter("amountPerPage");
		return userCourseService.getFinishedPublishCourse(userId, page, amountPerPage);
	}
	
	@RequestMapping("/enterNameForCourse")
	@ResponseBody
	public ResultModel enterNameForCourse(HttpServletRequest request) {
		logger.debug("enterNameForCourse");
		String userId = (String) request.getSession().getAttribute("userId");
		if(userId == null || userId.isEmpty()) {
			ResultModel rm = new ResultModel();
			rm.setErrorCode("9900");
			rm.setErrorMsg("会话已过期，请重新登录");
			return rm;
		}
		Map<String, String> paramsMap = ConvertRequestMapToMap.convert(request.getParameterMap());
		paramsMap.put("user_id", userId);
		paramsMap.put("user_type", "LISTEN");
		return userCourseService.addUserCourse(paramsMap);
	}
	
}
