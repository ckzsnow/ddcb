package com.dd.dao;

import java.util.List;
import com.dd.constant.Constant.UserType;
import com.dd.models.CourseModel;
import com.dd.models.UserCourseModel;

public interface IUserCourseDao {

	public List<UserCourseModel> getUserCourseByUserIdAndUserType(String userId, UserType userType, int page, int amountPerPage);
	
	public List<UserCourseModel> getUserCourseByCourseIdAndUserType(Long courseId, UserType userType, int page, int amountPerPage);
	
	public Integer getUserAmountForCourse(Long courseId);
		
	public boolean addUserCourse(UserCourseModel userCourseModel);
	
	public boolean deleteUserCourseByUserIdAndCourseIdAndUserType(String userId, Long courseId, UserType userType);
	
	public boolean deleteUserCourseByCourseId(Long courseId);
	
	public boolean userIsEnterCourseByUserIdAndCourseIdAndUserType(String userId, Long courseId);
	
	public boolean updateUserId(String userId, String newUserId);
	
	public boolean getUserCourse(String userId, Long courseId, UserType userType);
	
	public List<CourseModel> getOngoingSubscribeCourse(String userId, int page, int amountPerPage);
	
	public List<CourseModel> getFinishedSubscribeCourse(String userId, int page, int amountPerPage);
	
	public List<CourseModel> getOngoingPublishCourse(String userId, int page, int amountPerPage);
	
	public List<CourseModel> getFinishedPublishCourse(String userId, int page, int amountPerPage);
	
	public List<CourseModel> getPublishCourse(String userId, int page, int amountPerPage);
	
}