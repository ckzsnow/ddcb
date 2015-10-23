package com.dd.dao;

import java.util.List;

import com.dd.models.UserModel;

public interface IUserDao {

	public UserModel getUserByUserId(String userId);
	
	public List<UserModel> getAllUser(int page, int amountPerPage);
	
	public boolean addUser(UserModel userModel);
	
	public boolean deleteUserByUserId(String userId);
	
	public boolean updateUserPwdByUserId(String pwd, String userId);
	
	public boolean updateUserId(String userId, String newUserId);
	
}
