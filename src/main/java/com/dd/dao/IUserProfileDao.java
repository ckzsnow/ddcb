package com.dd.dao;

import com.dd.models.UserProfileModel;

public interface IUserProfileDao {

	public UserProfileModel getUserProfileByUserId(String userId);
	
	public boolean addUserProfile(UserProfileModel userProfileModel);
	
	public boolean deleteUserProfileByUserId(String userId);
	
	public boolean updateUserProfile(UserProfileModel userProfileModel);
	
}
