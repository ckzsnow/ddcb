package com.dd.dao;

import java.util.List;

import com.dd.dao.DaoConstant.UserType;
import com.dd.models.UserCourseModel;

public interface IUserCourseDao {

	public List<UserCourseModel> getUserCourseByUserIdAndUserType(String userId, UserType userType);
	
	public Long getUserAmountForCourse(Long courseId);
	
	public List<UserCourseModel> getUserCourseForCourse(Long courseId);
		
	public boolean addUserCourse(UserCourseModel userCourseModel);
	
	public boolean deleteUserCourseByUserIdAndCourseId(String userId, Long courseId);
	
}