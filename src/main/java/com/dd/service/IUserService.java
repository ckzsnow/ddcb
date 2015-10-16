package com.dd.service;

import com.dd.models.ResultModel;

public interface IUserService {

	public ResultModel userRegister(String userId, String pwd, String sendedSMSCode, String userVerifyCode);
	
	public ResultModel userLogin(String userId, String pwd);
	
	public ResultModel sendVerifyCode(String userId);
	
	public ResultModel deleteUser(String userId);
	
	public ResultModel updateUserPwd(String userId, String pwd);
	
	public ResultModel getAllRegisterUser(int page, int amountPerPage);
	
}
