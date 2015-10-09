package com.dd.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dd.constant.Constant.UserType;
import com.dd.dao.IUserCourseDao;
import com.dd.models.ResultModel;
import com.dd.models.UserCourseModel;
import com.dd.service.IUserCourseService;

@Service("userCourseService")
public class UserCourseService implements IUserCourseService {

	@Autowired
	private IUserCourseDao userCourseDao;
	
	private static final Logger logger = LoggerFactory.getLogger(UserCourseService.class);

	@Override
	public ResultModel getUserCourseByUserIdAndUserType(String userId, String userType, String page, String amountPerPage) {
		ResultModel ret = new ResultModel();
		int page_ = 0;
		int amountPerPage_ = 0;
		UserType userType_ = UserType.LISTEN;
		try {
			page_ = Integer.valueOf(page);
			amountPerPage_ = Integer.valueOf(amountPerPage);
			userType_ = Enum.valueOf(UserType.class, userType);
		} catch (Exception e) {
			logger.error(e.toString());
			ret.setErrorCode("6010");
			ret.setErrorMsg("传入参数格式不正确");
			return ret;
		}
		if(page_ <= 0 || amountPerPage_ <= 0) {
			ret.setErrorCode("6001");
			ret.setErrorMsg("页数或每页显示数量必须为正数");
			return ret;
		}
		List<UserCourseModel> retList = userCourseDao.getUserCourseByUserIdAndUserType(userId, userType_, page_, amountPerPage_);
		if(retList != null && retList.size() != 0) {
			ret.setErrorCode("6000");
			ret.setErrorMsg("操作成功");
			ret.setResultList(Arrays.asList(retList.toArray()));
		} else {
			ret.setErrorCode("6002");
			ret.setErrorMsg("查询结果为空");
		}
		return ret;
	}

	@Override
	public ResultModel getUserCourseByCourseIdAndUserType(String courseId, String userType, String page,
			String amountPerPage) {
		ResultModel ret = new ResultModel();
		int page_ = 0;
		int amountPerPage_ = 0;
		long courseId_ = 0;
		UserType userType_ = UserType.LISTEN;
		try {
			page_ = Integer.valueOf(page);
			amountPerPage_ = Integer.valueOf(amountPerPage);
			userType_ = Enum.valueOf(UserType.class, userType);
			courseId_ = Long.valueOf(courseId);
		} catch (Exception e) {
			logger.error(e.toString());
			ret.setErrorCode("6010");
			ret.setErrorMsg("传入参数格式不正确");
			return ret;
		}
		if(page_ <= 0 || amountPerPage_ <= 0) {
			ret.setErrorCode("6001");
			ret.setErrorMsg("页数或每页显示数量必须为正数");
			return ret;
		}
		List<UserCourseModel> retList = userCourseDao.getUserCourseByCourseIdAndUserType(courseId_, userType_, page_, amountPerPage_);
		if(retList != null && retList.size() != 0) {
			ret.setErrorCode("6000");
			ret.setErrorMsg("操作成功");
			ret.setResultList(Arrays.asList(retList.toArray()));
		} else {
			ret.setErrorCode("6002");
			ret.setErrorMsg("查询结果为空");
		}
		return ret;
	}

	@Override
	public ResultModel getUserAmountForCourse(String courseId) {
		ResultModel ret = new ResultModel();
		long courseId_ = 0;
		try {
			courseId_ = Long.valueOf(courseId);
		} catch (Exception e) {
			logger.error(e.toString());
			ret.setErrorCode("6010");
			ret.setErrorMsg("传入参数格式不正确");
			return ret;
		}
		Integer amount = userCourseDao.getUserAmountForCourse(courseId_);
		ret.setErrorCode("6000");
		ret.setErrorMsg("操作成功");
		ret.setResult(amount);
		return ret;
	}

	@Override
	public ResultModel addUserCourse(Map<String, String> params) {
		ResultModel ret = new ResultModel();
		UserCourseModel userCourseModel = new UserCourseModel();
		if(!params.containsKey("user_id")) {
			ret.setErrorCode("6003");
			ret.setErrorMsg("user_id未设置");
			return ret;
		}
		userCourseModel.setUserId(params.get("user_id"));
		if(!params.containsKey("course_id")) {
			ret.setErrorCode("6004");
			ret.setErrorMsg("course_id未设置");
			return ret;
		}
		userCourseModel.setCourseId(Long.valueOf(params.get("course_id")));
		if(!params.containsKey("user_type")) {
			ret.setErrorCode("6005");
			ret.setErrorMsg("user_type未设置");
			return ret;
		}
		UserType userType_ = UserType.LISTEN;
		try {
			userType_ = Enum.valueOf(UserType.class, params.get("user_type"));
		} catch (IllegalArgumentException | NullPointerException  e) {
			logger.error(e.toString());
			ret.setErrorCode("6011");
			ret.setErrorMsg("传入枚举类型格式不正确");
			return ret;
		}
		userCourseModel.setUserType(userType_.value());
		if(userCourseDao.addUserCourse(userCourseModel)) {
			ret.setErrorCode("6000");
			ret.setErrorMsg("操作成功");
		} else {
			ret.setErrorCode("6006");
			ret.setErrorMsg("操作数据库失败");
		}
		return ret;
	}

	@Override
	public ResultModel deleteUserCourseByUserIdAndCourseIdAndUserType(String userId, String courseId, String userType) {
		ResultModel ret = new ResultModel();
		long courseId_ = 0;
		try {
			courseId_ = Long.valueOf(courseId);
		} catch (Exception e) {
			logger.error(e.toString());
			ret.setErrorCode("6010");
			ret.setErrorMsg("传入参数格式不正确");
			return ret;
		}
		UserType userType_ = UserType.LISTEN;
		try {
			userType_ = Enum.valueOf(UserType.class, userType);
		} catch (IllegalArgumentException | NullPointerException  e) {
			logger.error(e.toString());
			ret.setErrorCode("6011");
			ret.setErrorMsg("传入枚举类型格式不正确");
			return ret;
		}
		if(userCourseDao.deleteUserCourseByUserIdAndCourseIdAndUserType(userId, courseId_, userType_)) {
			ret.setErrorCode("6000");
			ret.setErrorMsg("操作成功");
		} else {
			ret.setErrorCode("6006");
			ret.setErrorMsg("操作数据库失败");
		}
		return ret;
	}	
}