package com.dd.service;

import java.util.Map;

import com.dd.models.ResultModel;

public interface IUserProfileService {

	public ResultModel getUserProfile(String userId);
	
	public ResultModel addUserProfile(Map<String, String> profileParams);
	
	public ResultModel updateUserProfile(Map<String, String> profileParams);
	
	public ResultModel deleteUserProfile(String userId);
}