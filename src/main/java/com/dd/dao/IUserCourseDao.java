package com.dd.dao;

import java.util.List;
import com.dd.constant.Constant.UserType;
import com.dd.models.UserCourseModel;

public interface IUserCourseDao {

	public List<UserCourseModel> getUserCourseByUserIdAndUserType(String userId, UserType userType, int page, int amountPerPage);
	
	public List<UserCourseModel> getUserCourseByCourseIdAndUserType(Long courseId, UserType userType, int page, int amountPerPage);
	
	public Integer getUserAmountForCourse(Long courseId);
		
	public boolean addUserCourse(UserCourseModel userCourseModel);
	
	public boolean deleteUserCourseByUserIdAndCourseIdAndUserType(String userId, Long courseId, UserType userType);
	
}