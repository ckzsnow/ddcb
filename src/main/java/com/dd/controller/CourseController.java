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
import com.dd.service.ICourseService;
import com.dd.utils.ConvertRequestMapToMap;

@Controller
@RequestMapping("/course")
public class CourseController {

	private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

	@Autowired
	private ICourseService courseService;

	@RequestMapping("/getCourseByCourseId")
	@ResponseBody
	public ResultModel getCourseByCourseId(HttpServletRequest request) {
		logger.debug("getCourseByCourseId");
		String userId = (String) request.getSession().getAttribute("userId");
		if(userId == null || userId.isEmpty()) {
			ResultModel rm = new ResultModel();
			rm.setErrorCode("9900");
			rm.setErrorMsg("会话已过期，请重新登录");
			return rm;
		}
		String courseId = request.getParameter("courseId");
		return courseService.getCourseByCourseId(courseId);
	}

	@RequestMapping("/getCourseByCourseNameAndBriefAndDetails")
	@ResponseBody
	public ResultModel getCourseByCourseNameAndBriefAndDetails(HttpServletRequest request) {
		logger.debug("getCourseByCourseNameAndBriefAndDetails");
		String userId = (String) request.getSession().getAttribute("userId");
		if(userId == null || userId.isEmpty()) {
			ResultModel rm = new ResultModel();
			rm.setErrorCode("9900");
			rm.setErrorMsg("会话已过期，请重新登录");
			return rm;
		}
		String courseName = request.getParameter("courseName");
		String courseBrief = request.getParameter("courseBrief");
		String courseDetails = request.getParameter("courseDetails");
		String courseAuditStatus = request.getParameter("courseAuditStatus");
		String courseType = request.getParameter("courseType");
		String page = request.getParameter("page");
		String amountPerPage = request.getParameter("amountPerPage");
		return courseService.getCourseByCourseNameAndBriefAndDetails(courseName, courseBrief, courseDetails,
				courseAuditStatus, courseType, page, amountPerPage);
	}

	@RequestMapping("/getCourseByIndustryIdAndFieldIdAndStageId")
	@ResponseBody
	public ResultModel getCourseByIndustryIdAndFieldIdAndStageId(HttpServletRequest request) {
		logger.debug("getCourseByIndustryIdAndFieldIdAndStageId");
		String userId = (String) request.getSession().getAttribute("userId");
		if(userId == null || userId.isEmpty()) {
			ResultModel rm = new ResultModel();
			rm.setErrorCode("9900");
			rm.setErrorMsg("会话已过期，请重新登录");
			return rm;
		}
		String industryId = request.getParameter("industryId");
		String fieldId = request.getParameter("fieldId");
		String stageId = request.getParameter("stageId");
		String courseAuditStatus = request.getParameter("courseAuditStatus");
		String courseType = request.getParameter("courseType");
		String page = request.getParameter("page");
		String amountPerPage = request.getParameter("amountPerPage");
		return courseService.getCourseByIndustryIdAndFieldIdAndStageId(industryId, fieldId, stageId, courseAuditStatus,
				courseType, page, amountPerPage);
	}

	@RequestMapping("/getCourseBySchoolTime")
	@ResponseBody
	public ResultModel getCourseBySchoolTime(HttpServletRequest request) {
		logger.debug("getCourseBySchoolTime");
		String userId = (String) request.getSession().getAttribute("userId");
		if(userId == null || userId.isEmpty()) {
			ResultModel rm = new ResultModel();
			rm.setErrorCode("9900");
			rm.setErrorMsg("会话已过期，请重新登录");
			return rm;
		}
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String courseAuditStatus = request.getParameter("courseAuditStatus");
		String courseType = request.getParameter("courseType");
		String page = request.getParameter("page");
		String amountPerPage = request.getParameter("amountPerPage");
		return courseService.getCourseBySchoolTime(startTime, endTime, courseAuditStatus, courseType, page,
				amountPerPage);
	}

	@RequestMapping("/getCourseByCourseTypeAndCourseAuditStatus")
	@ResponseBody
	public ResultModel getCourseByCourseTypeAndCourseAuditStatus(HttpServletRequest request) {
		logger.debug("getCourseByCourseTypeAndCourseAuditStatus");
		String userId = (String) request.getSession().getAttribute("userId");
		if(userId == null || userId.isEmpty()) {
			ResultModel rm = new ResultModel();
			rm.setErrorCode("9900");
			rm.setErrorMsg("会话已过期，请重新登录");
			return rm;
		}
		String courseAuditStatus = request.getParameter("courseAuditStatus");
		String courseType = request.getParameter("courseType");
		String page = request.getParameter("page");
		String amountPerPage = request.getParameter("amountPerPage");
		return courseService.getCourseByCourseTypeAndCourseAuditStatus(courseType, courseAuditStatus, page,
				amountPerPage);
	}

	@RequestMapping("/addCourse")
	@ResponseBody
	public ResultModel addCourse(HttpServletRequest request) {
		logger.debug("addCourse");
		String userId = (String) request.getSession().getAttribute("userId");
		if(userId == null || userId.isEmpty()) {
			ResultModel rm = new ResultModel();
			rm.setErrorCode("9900");
			rm.setErrorMsg("会话已过期，请重新登录");
			return rm;
		}
		Map<String, String> paramsMap = ConvertRequestMapToMap.convert(request.getParameterMap());
		paramsMap.put("user_id", userId);
		return courseService.addCourse(paramsMap);
	}

	@RequestMapping("/updateCourse")
	@ResponseBody
	public ResultModel updateCourse(HttpServletRequest request) {
		logger.debug("updateCourse");
		String userId = (String) request.getSession().getAttribute("userId");
		if(userId == null || userId.isEmpty()) {
			ResultModel rm = new ResultModel();
			rm.setErrorCode("9900");
			rm.setErrorMsg("会话已过期，请重新登录");
			return rm;
		}
		return courseService.updateCourse(ConvertRequestMapToMap.convert(request.getParameterMap()));
	}

	@RequestMapping("/deleteCourseByCourseId")
	@ResponseBody
	public ResultModel deleteCourseByCourseId(HttpServletRequest request) {
		logger.debug("deleteCourseByCourseId");
		String userId = (String) request.getSession().getAttribute("userId");
		if(userId == null || userId.isEmpty()) {
			ResultModel rm = new ResultModel();
			rm.setErrorCode("9900");
			rm.setErrorMsg("会话已过期，请重新登录");
			return rm;
		}
		String courseId = request.getParameter("courseId");
		return courseService.deleteCourseByCourseId(courseId);
	}

}