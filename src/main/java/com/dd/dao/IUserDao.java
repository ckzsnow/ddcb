package com.dd.dao;

import com.dd.models.UserModel;

public interface IUserDao {

	public UserModel getUserByUserId(String userId);
	
	public boolean addUser(UserModel userModel);
	
	public boolean deleteUserByUserId(String userId);
	
	public boolean updateUserPwdByUserId(String pwd, String userId);
	
}
