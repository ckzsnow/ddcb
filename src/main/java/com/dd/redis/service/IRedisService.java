package com.dd.redis.service;

import com.dd.models.UserCourseModel;
import com.dd.models.UserProfileModel;

public interface IRedisService {

	public boolean addUserCourse(UserCourseModel userCourseModel);
	
	public boolean addUserInfo(UserProfileModel userProfileModel);
	
}
