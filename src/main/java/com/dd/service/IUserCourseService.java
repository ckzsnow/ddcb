package com.dd.service;

import java.util.Map;

import com.dd.constant.Constant.UserType;
import com.dd.models.ResultModel;

public interface IUserCourseService {
	
	public ResultModel getUserCourseByUserIdAndUserType(String userId, UserType userType, int page, int amountPerPage);
	
	public ResultModel getUserCourseByCourseIdAndUserType(Long courseId, UserType userType, int page, int amountPerPage);
	
	public ResultModel getUserAmountForCourse(Long courseId);
		
	public ResultModel addUserCourse(Map<String, String> params);
	
	public ResultModel deleteUserCourseByUserIdAndCourseId(String userId, Long courseId);
	
}