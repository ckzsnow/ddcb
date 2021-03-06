package com.dd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dd.dao.IUserCourseDao;
import com.dd.dao.IUserDao;
import com.dd.dao.IUserProfileDao;
import com.dd.models.ResultModel;
import com.dd.models.UserModel;
import com.dd.service.IUserProfileService;
import com.dd.service.IUserService;
import com.dd.sms.SendTemplateSMS;
import com.dd.utils.MD5Encrypt;

@Service("userService")
public class UserService implements IUserService {

	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IUserProfileDao userProfileDao;
	
	@Autowired
	private IUserCourseDao userCourseDao;
	
	@Autowired
	private IUserProfileService userProfileService;
	
	@Override
	public ResultModel userRegister(String userId, String pwd, String sendedSMSCode, String userVerifyCode) {
		ResultModel ret = new ResultModel();
		if(userId == null || userId.isEmpty()) {
			ret.setErrorCode("0001");
			ret.setErrorMsg("用户id为空");
			return ret;
		}
		if(userVerifyCode == null || userVerifyCode.isEmpty()) {
			ret.setErrorCode("0007");
			ret.setErrorMsg("验证码为空");
			return ret;
		}
		if(!userVerifyCode.equals(sendedSMSCode)) {
			ret.setErrorCode("0009");
			ret.setErrorMsg("验证码不正确或已经过期");
			return ret;
		}
		if(pwd == null || pwd.isEmpty()) {
			ret.setErrorCode("0002");
			ret.setErrorMsg("用户密码为空");
			return ret;
		}
		UserModel user = userDao.getUserByUserId(userId);
		if(user != null) {
			ret.setErrorCode("0003");
			ret.setErrorMsg("用户Id已经存在");
			return ret;
		}
		user = new UserModel();
		user.setUserId(userId);
		user.setPwd(MD5Encrypt.EncryptPasswordByMd5(pwd));
		if(userDao.addUser(user)) {
			ret.setErrorCode("0000");
			ret.setErrorMsg("操作成功");
		} else {
			ret.setErrorCode("0004");
			ret.setErrorMsg("数据库操作失败");
		}
		return ret;
	}

	@Override
	public ResultModel userLogin(String userId, String pwd) {
		ResultModel ret = new ResultModel();
		if(userId == null || userId.isEmpty()) {
			ret.setErrorCode("0001");
			ret.setErrorMsg("用户id为空");
			return ret;
		}
		if(pwd == null || pwd.isEmpty()) {
			ret.setErrorCode("0002");
			ret.setErrorMsg("用户密码为空");
			return ret;
		}
		UserModel user = userDao.getUserByUserId(userId);
		if(user == null) {
			ret.setErrorCode("0005");
			ret.setErrorMsg("用户id不存在");
			return ret;
		}
		String encryptPwd = MD5Encrypt.EncryptPasswordByMd5(pwd);
		if(encryptPwd == null || encryptPwd.isEmpty() ||
				!encryptPwd.equals(user.getPwd())) {
			ret.setErrorCode("0006");
			ret.setErrorMsg("密码不正确");
			return ret;
		}
		ret.setErrorCode("0000");
		ret.setErrorMsg("操作成功");
		return ret;
	}

	@Override
	public ResultModel deleteUser(String userId) {
		ResultModel ret = new ResultModel();
		if(userId == null || userId.isEmpty()) {
			ret.setErrorCode("0001");
			ret.setErrorMsg("用户id为空");
			return ret;
		}
		UserModel user = userDao.getUserByUserId(userId);
		if(user == null) {
			ret.setErrorCode("0005");
			ret.setErrorMsg("用户id不存在");
			return ret;
		}
		if(userDao.deleteUserByUserId(userId)) {
			ret.setErrorCode("0000");
			ret.setErrorMsg("操作成功");
		} else {
			ret.setErrorCode("0004");
			ret.setErrorMsg("数据库操作失败");
		}
		return ret;
	}

	@Override
	public ResultModel updateUserPwd(String userId, String pwd) {
		ResultModel ret = new ResultModel();
		if(userId == null || userId.isEmpty()) {
			ret.setErrorCode("0001");
			ret.setErrorMsg("用户id为空");
			return ret;
		}
		if(pwd == null || pwd.isEmpty()) {
			ret.setErrorCode("0002");
			ret.setErrorMsg("用户密码为空");
			return ret;
		}
		UserModel user = userDao.getUserByUserId(userId);
		if(user == null) {
			ret.setErrorCode("0005");
			ret.setErrorMsg("用户id不存在");
			return ret;
		}
		String encryptPwd = MD5Encrypt.EncryptPasswordByMd5(pwd);
		if(userDao.updateUserPwdByUserId(encryptPwd, userId)) {
			ret.setErrorCode("0000");
			ret.setErrorMsg("操作成功");
		} else {
			ret.setErrorCode("0004");
			ret.setErrorMsg("数据库操作失败");
		}
		return ret;
	}

	@Override
	public ResultModel getAllRegisterUser(int page, int amountPerPage) {
		ResultModel ret = new ResultModel();
		if(page <= 0 || amountPerPage <= 0) {
			ret.setErrorCode("0007");
			ret.setErrorMsg("页数或每页显示数量为负数");
			return ret;
		}
		List<UserModel> userList = userDao.getAllUser(page, amountPerPage);
		List<Object> retList = new ArrayList<>();
		if(userList != null && userList.size() != 0) {
			for(UserModel userModel : userList) {
				ret = userProfileService.getUserProfile(userModel.getUserId());
				if(("1000").equals(ret.getErrorCode())) retList.add(ret.getResult());
			}
		}
		if(retList.size() != 0) {
			ret.setErrorCode("0000");
			ret.setErrorMsg("操作成功");
			ret.setResultList(retList);
		} else {
			ret.setErrorCode("0008");
			ret.setErrorMsg("查询结果为空");
		}
		return ret;
	}

	@Override
	public ResultModel sendVerifyCode(String userId) {
		ResultModel ret = new ResultModel();
		if(userId == null || userId.isEmpty()) {
			ret.setErrorCode("0001");
			ret.setErrorMsg("用户id为空");
			return ret;
		}
		String verifyCode = SendTemplateSMS.sendSMS(userId);
		if(verifyCode == null || verifyCode.isEmpty()) {
			ret.setErrorCode("0008");
			ret.setErrorMsg("发送短信验证码失败");
		} else {
			ret.setExtraInfo(verifyCode);
			ret.setErrorCode("0000");
			ret.setErrorMsg("操作成功");
		}
		return ret;
	}

	@Override
	public ResultModel userChangePwd(String userId, String oldPwd, String newPwd) {
		ResultModel ret = new ResultModel();
		if(userId == null || userId.isEmpty()) {
			ret.setErrorCode("0001");
			ret.setErrorMsg("用户id为空");
			return ret;
		}
		if(oldPwd == null || oldPwd.isEmpty() || newPwd == null || newPwd.isEmpty()) {
			ret.setErrorCode("0002");
			ret.setErrorMsg("用户密码为空");
			return ret;
		}
		
		UserModel user = userDao.getUserByUserId(userId);
		if(!MD5Encrypt.EncryptPasswordByMd5(oldPwd).equals(user.getPwd())) {
			ret.setErrorCode("0006");
			ret.setErrorMsg("密码不正确");
			return ret;
		}
		if(userDao.updateUserPwdByUserId(MD5Encrypt.EncryptPasswordByMd5(newPwd), userId)) {
			ret.setErrorCode("0000");
			ret.setErrorMsg("操作成功");
		} else {
			ret.setErrorCode("0004");
			ret.setErrorMsg("数据库操作失败");
		}
		return ret;
	}

	@Override
	public ResultModel userChangeUserId(String userId, String newUserId, String sendedSMSCode, String userVerifyCode) {
		ResultModel ret = new ResultModel();
		if(userId == null || userId.isEmpty() || newUserId == null || newUserId.isEmpty()) {
			ret.setErrorCode("0001");
			ret.setErrorMsg("用户id为空");
			return ret;
		}
		if(userVerifyCode == null || userVerifyCode.isEmpty()) {
			ret.setErrorCode("0007");
			ret.setErrorMsg("验证码为空");
			return ret;
		}
		if(!userVerifyCode.equals(sendedSMSCode)) {
			ret.setErrorCode("0009");
			ret.setErrorMsg("验证码不正确或已经过期");
			return ret;
		}
		if(!userDao.updateUserId(userId, newUserId)) {
			ret.setErrorCode("0004");
			ret.setErrorMsg("数据库操作失败");
		}
		userProfileDao.updateUserId(userId, newUserId);
		userCourseDao.updateUserId(userId, newUserId);
		ret.setErrorCode("0000");
		ret.setErrorMsg("操作成功");
		return ret;
	}

	@Override
	public ResultModel resetPwd(String userId, String sendedSMSCode, String userVerifyCode, String userPwd) {
		ResultModel ret = new ResultModel();
		if(userId == null || userId.isEmpty()) {
			ret.setErrorCode("0001");
			ret.setErrorMsg("用户id为空");
			return ret;
		}
		if(userVerifyCode == null || userVerifyCode.isEmpty()) {
			ret.setErrorCode("0007");
			ret.setErrorMsg("验证码为空");
			return ret;
		}
		if(userPwd == null || userPwd.isEmpty()) {
			ret.setErrorCode("0002");
			ret.setErrorMsg("密码为空");
			return ret;
		}
		if(!userVerifyCode.equals(sendedSMSCode)) {
			ret.setErrorCode("0009");
			ret.setErrorMsg("验证码不正确或已经过期");
			return ret;
		}
		if(userDao.updateUserPwdByUserId(MD5Encrypt.EncryptPasswordByMd5(userPwd), userId)) {
			ret.setErrorCode("0000");
			ret.setErrorMsg("操作成功");
		} else {
			ret.setErrorCode("0004");
			ret.setErrorMsg("数据库操作失败");
		}
		return ret;
	}

}
