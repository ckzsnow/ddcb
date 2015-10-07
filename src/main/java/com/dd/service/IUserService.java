package com.dd.service;

import com.dd.models.ResultModel;

public interface IUserService {

	public ResultModel userRegister(String userId, String pwd);
	
	public ResultModel userLogin(String userId, String pwd);
	
	public ResultModel deleteUser(String userId);
	
	public ResultModel updateUserPwd(String userId, String pwd);
	
	public ResultModel getAllRegisterUser(int page, int amountPerPage);
	
}
