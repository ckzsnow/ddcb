package com.dd.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

	@Override
	public ResultModel getUserCourseByUserIdAndUserType(String userId, UserType userType, int page, int amountPerPage) {
		ResultModel ret = new ResultModel();
		if(page <= 0 || amountPerPage <= 0) {
			ret.setErrorCode("6001");
			ret.setErrorMsg("页数或每页显示数量为负数");
			return ret;
		}
		List<UserCourseModel> retList = userCourseDao.getUserCourseByUserIdAndUserType(userId, userType, page, amountPerPage);
		if(retList.size() != 0) {
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
	public ResultModel getUserCourseByCourseIdAndUserType(Long courseId, UserType userType, int page,
			int amountPerPage) {
		ResultModel ret = new ResultModel();
		if(page <= 0 || amountPerPage <= 0) {
			ret.setErrorCode("6001");
			ret.setErrorMsg("页数或每页显示数量为负数");
			return ret;
		}
		List<UserCourseModel> retList = userCourseDao.getUserCourseByCourseIdAndUserType(courseId, userType, page, amountPerPage);
		if(retList.size() != 0) {
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
	public ResultModel getUserAmountForCourse(Long courseId) {
		ResultModel ret = new ResultModel();
		Integer amount = userCourseDao.getUserAmountForCourse(courseId);
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
		userCourseModel.setUserType(Integer.valueOf(params.get("user_type")));
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
	public ResultModel deleteUserCourseByUserIdAndCourseId(String userId, Long courseId) {
		ResultModel ret = new ResultModel();
		if(courseId == null) {
			ret.setErrorCode("6004");
			ret.setErrorMsg("course_id为空");
			return ret;
		}
		if(userCourseDao.deleteUserCourseByUserIdAndCourseId(userId, courseId)) {
			ret.setErrorCode("6000");
			ret.setErrorMsg("操作成功");
		} else {
			ret.setErrorCode("6006");
			ret.setErrorMsg("操作数据库失败");
		}
		return ret;
	}	
}