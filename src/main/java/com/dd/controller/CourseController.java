package com.dd.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dd.models.ResultModel;
import com.dd.service.ICourseService;

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
		String courseId = request.getParameter("courseId");
		return courseService.getCourseByCourseId(courseId);
	}
	
	@RequestMapping("/getCourseByCourseNameAndBriefAndDetails")
	@ResponseBody
	public ResultModel getCourseByCourseNameAndBriefAndDetails(HttpServletRequest request) {
		logger.debug("getCourseByCourseNameAndBriefAndDetails");
		String courseName = request.getParameter("courseName");
		String courseBrief = request.getParameter("courseBrief");
		String courseDetails = request.getParameter("courseDetails");
		String courseAuditStatus = request.getParameter("courseAuditStatus");
		String courseType = request.getParameter("courseType");
		String page = request.getParameter("page");
		String amountPerPage = request.getParameter("amountPerPage");
		return courseService.getCourseByCourseNameAndBriefAndDetails(courseName, courseBrief, courseDetails, courseAuditStatus, courseType, page, amountPerPage);
	}
	
}