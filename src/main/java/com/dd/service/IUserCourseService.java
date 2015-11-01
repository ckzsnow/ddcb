package com.dd.service;

import java.util.Map;

import com.dd.models.ResultModel;

public interface IUserCourseService {
	
	public ResultModel getUserCourseByUserIdAndUserType(String userId, String userType, String page, String amountPerPage);
	
	public ResultModel getUserCourseByCourseIdAndUserType(String courseId, String userType, String page, String amountPerPage);
	
	public ResultModel getUserAmountForCourse(String courseId);
		
	public ResultModel addUserCourse(Map<String, String> params);
	
	public ResultModel deleteUserCourseByCourseIdAndUserType(String userId, String courseId, String userType);
	
	public ResultModel getOngoingSubscribeCourse(String userId, String page, String amountPerPage);
	
	public ResultModel getFinishedSubscribeCourse(String userId, String page, String amountPerPage);
	
	public ResultModel getOngoingPublishCourse(String userId, String page, String amountPerPage);
	
	public ResultModel getFinishedPublishCourse(String userId, String page, String amountPerPage);
	
	public ResultModel getPublishCourse(String userId, String page, String amountPerPage);
	
}