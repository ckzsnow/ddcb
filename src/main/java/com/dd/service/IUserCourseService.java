package com.dd.service;

import java.util.Map;

import com.dd.models.ResultModel;

public interface IUserCourseService {
	
	public ResultModel getUserCourseByUserIdAndUserType(String userId, String userType, String page, String amountPerPage);
	
	public ResultModel getUserCourseByCourseIdAndUserType(String courseId, String userType, String page, String amountPerPage);
	
	public ResultModel getUserAmountForCourse(String courseId);
		
	public ResultModel addUserCourse(Map<String, String> params);
	
	public ResultModel deleteUserCourseByCourseIdAndUserType(String userId, String courseId, String userType);
	
}