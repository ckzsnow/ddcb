package com.dd.service;

import com.dd.models.ResultModel;

public interface IUserService {

	public ResultModel userRegister(String userId, String pwd, String sendedSMSCode, String userVerifyCode);
	
	public ResultModel userLogin(String userId, String pwd);
	
	public ResultModel userChangePwd(String userId, String oldPwd, String newPwd);
	
	public ResultModel userChangeUserId(String userId, String newUserId, String sendedSMSCode, String userVerifyCode);
	
	public ResultModel sendVerifyCode(String userId);
	
	public ResultModel deleteUser(String userId);
	
	public ResultModel updateUserPwd(String userId, String pwd);
	
	public ResultModel getAllRegisterUser(int page, int amountPerPage);
	
	public ResultModel resetPwd(String userId, String sendedSMSCode, String userVerifyCode, String userPwd);
	
}
