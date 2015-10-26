package com.dd.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dd.constant.Constant.CourseAuditStatus;
import com.dd.constant.Constant.CourseType;
import com.dd.constant.Constant.UserType;
import com.dd.dao.ICourseDao;
import com.dd.dao.IUserCourseDao;
import com.dd.models.CourseModel;
import com.dd.models.CourseUserInfoModel;
import com.dd.models.FieldModel;
import com.dd.models.IndustryModel;
import com.dd.models.ResultModel;
import com.dd.models.StageModel;
import com.dd.models.UserCourseModel;
import com.dd.models.UserProfileModel;
import com.dd.redis.service.IRedisService;
import com.dd.service.ICategoryInfoService;
import com.dd.service.IUserCourseService;
import com.dd.service.IUserProfileService;

@Service("userCourseService")
public class UserCourseService implements IUserCourseService {

	@Autowired
	private IUserCourseDao userCourseDao;

	@Autowired
	private ICourseDao courseDao;

	@Autowired
	private IUserProfileService userProfileService;

	@Autowired
	private ICategoryInfoService categoryInfoService;

	@Autowired
	private IRedisService redisService;

	private static final Logger logger = LoggerFactory.getLogger(UserCourseService.class);

	@Override
	public ResultModel getUserCourseByUserIdAndUserType(String userId, String userType, String page,
			String amountPerPage) {
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
		if (page_ <= 0 || amountPerPage_ <= 0) {
			ret.setErrorCode("6001");
			ret.setErrorMsg("页数或每页显示数量必须为正数");
			return ret;
		}
		List<UserCourseModel> retList = userCourseDao.getUserCourseByUserIdAndUserType(userId, userType_, page_,
				amountPerPage_);
		List<CourseModel> courseModelList = new ArrayList<>();
		for (UserCourseModel ucm : retList) {
			Long courseId = ucm.getCourseId();
			CourseModel cm = courseDao.getCourseByCourseId(courseId);
			if (userCourseDao.userIsEnterCourseByUserIdAndCourseIdAndUserType(userId, courseId)) {
				cm.setIsEnter("true");
			} else {
				cm.setIsEnter("false");
			}
			if (cm != null)
				courseModelList.add(cm);
		}
		if (courseModelList != null && courseModelList.size() != 0) {
			ret.setErrorCode("6000");
			ret.setErrorMsg("操作成功");
			ret.setResultList(Arrays.asList(courseModelList.toArray()));
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
		if (page_ <= 0 || amountPerPage_ <= 0) {
			ret.setErrorCode("6001");
			ret.setErrorMsg("页数或每页显示数量必须为正数");
			return ret;
		}
		List<UserCourseModel> retList = userCourseDao.getUserCourseByCourseIdAndUserType(courseId_, userType_, page_,
				amountPerPage_);
		if (retList != null && retList.size() != 0) {
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
		if (!params.containsKey("user_id")) {
			ret.setErrorCode("6003");
			ret.setErrorMsg("user_id未设置");
			return ret;
		}
		userCourseModel.setUserId(params.get("user_id"));
		if (!params.containsKey("course_id")) {
			ret.setErrorCode("6004");
			ret.setErrorMsg("course_id未设置");
			return ret;
		}
		userCourseModel.setCourseId(Long.valueOf(params.get("course_id")));
		if (!params.containsKey("user_type")) {
			ret.setErrorCode("6005");
			ret.setErrorMsg("user_type未设置");
			return ret;
		}
		UserType userType_ = UserType.LISTEN;
		try {
			userType_ = Enum.valueOf(UserType.class, params.get("user_type"));
		} catch (IllegalArgumentException | NullPointerException e) {
			logger.error(e.toString());
			ret.setErrorCode("6011");
			ret.setErrorMsg("传入枚举类型格式不正确");
			return ret;
		}
		if (userType_ == UserType.LISTEN && userCourseDao.getUserCourse(userCourseModel.getUserId(),
				userCourseModel.getCourseId(), UserType.TEACH)) {
			ret.setErrorCode("6060");
			ret.setErrorMsg("当前课程为您自己开设的课程，不需要报名！");
			return ret;
		}
		userCourseModel.setUserType(userType_.value());
		if (userCourseDao.userIsEnterCourseByUserIdAndCourseIdAndUserType(userCourseModel.getUserId(),
				userCourseModel.getCourseId())) {
			ret.setErrorCode("6000");
			ret.setErrorMsg("操作成功");
		}
		if (userCourseDao.addUserCourse(userCourseModel)) {
			if (userType_ == UserType.LISTEN)
				courseDao.incListenerAmountForCourse(userCourseModel.getCourseId());
			ret.setErrorCode("6000");
			ret.setErrorMsg("操作成功");
		} else {
			ret.setErrorCode("6006");
			ret.setErrorMsg("操作数据库失败");
		}
		redisService.addUserCourse(userCourseModel);
		return ret;
	}

	@Override
	public ResultModel deleteUserCourseByCourseIdAndUserType(String userId, String courseId, String userType) {
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
		} catch (IllegalArgumentException | NullPointerException e) {
			logger.error(e.toString());
			ret.setErrorCode("6011");
			ret.setErrorMsg("传入枚举类型格式不正确");
			return ret;
		}
		if (userCourseDao.deleteUserCourseByUserIdAndCourseIdAndUserType(userId, courseId_, userType_)) {
			ret.setErrorCode("6000");
			ret.setErrorMsg("操作成功");
		} else {
			ret.setErrorCode("6006");
			ret.setErrorMsg("操作数据库失败");
		}
		return ret;
	}

	@Override
	public ResultModel getOngoingSubscribeCourse(String userId, String page, String amountPerPage) {
		ResultModel ret = new ResultModel();
		int page_ = 0;
		int amountPerPage_ = 0;
		try {
			page_ = Integer.valueOf(page);
			amountPerPage_ = Integer.valueOf(amountPerPage);
		} catch (Exception e) {
			logger.error(e.toString());
			ret.setErrorCode("6010");
			ret.setErrorMsg("传入参数格式不正确");
			return ret;
		}
		if (page_ <= 0 || amountPerPage_ <= 0) {
			ret.setErrorCode("6001");
			ret.setErrorMsg("页数或每页显示数量必须为正数");
			return ret;
		}
		List<CourseUserInfoModel> cuimList = new ArrayList<>();
		List<CourseModel> cmList = userCourseDao.getOngoingSubscribeCourse(userId, page_, amountPerPage_);
		if (cmList == null || cmList.size() == 0) {
			ret.setErrorCode("6002");
			ret.setErrorMsg("查询结果为空");
			return ret;
		}
		for (CourseModel cm : cmList) {
			this.addExtraInfoForModel(cm);
			UserProfileModel upm = (UserProfileModel) userProfileService.getUserProfile(cm.getUserId()).getResult();
			if (upm == null) {
				logger.error("ERROR, get user profile in getOngoingSubscribeCourse");
			}
			CourseUserInfoModel cuim = new CourseUserInfoModel();
			cuim.setCourseInfo(cm);
			cuim.setUserInfo(upm);
			cuimList.add(cuim);
		}
		if (cuimList.size() == 0) {
			ret.setErrorCode("6002");
			ret.setErrorMsg("查询结果为空");
		} else {
			ret.setErrorCode("6000");
			ret.setErrorMsg("操作成功");
			ret.setResultList(Arrays.asList(cuimList.toArray()));
		}
		return ret;
	}

	@Override
	public ResultModel getFinishedSubscribeCourse(String userId, String page, String amountPerPage) {
		ResultModel ret = new ResultModel();
		int page_ = 0;
		int amountPerPage_ = 0;
		try {
			page_ = Integer.valueOf(page);
			amountPerPage_ = Integer.valueOf(amountPerPage);
		} catch (Exception e) {
			logger.error(e.toString());
			ret.setErrorCode("6010");
			ret.setErrorMsg("传入参数格式不正确");
			return ret;
		}
		if (page_ <= 0 || amountPerPage_ <= 0) {
			ret.setErrorCode("6001");
			ret.setErrorMsg("页数或每页显示数量必须为正数");
			return ret;
		}
		List<CourseUserInfoModel> cuimList = new ArrayList<>();
		List<CourseModel> cmList = userCourseDao.getFinishedSubscribeCourse(userId, page_, amountPerPage_);
		if (cmList == null || cmList.size() == 0) {
			ret.setErrorCode("6002");
			ret.setErrorMsg("查询结果为空");
			return ret;
		}
		for (CourseModel cm : cmList) {
			this.addExtraInfoForModel(cm);
			UserProfileModel upm = (UserProfileModel) userProfileService.getUserProfile(cm.getUserId()).getResult();
			if (upm == null) {
				logger.error("ERROR, get user profile in getOngoingSubscribeCourse");
			}
			CourseUserInfoModel cuim = new CourseUserInfoModel();
			cuim.setCourseInfo(cm);
			cuim.setUserInfo(upm);
			cuimList.add(cuim);
		}
		if (cuimList.size() == 0) {
			ret.setErrorCode("6002");
			ret.setErrorMsg("查询结果为空");
		} else {
			ret.setErrorCode("6000");
			ret.setErrorMsg("操作成功");
			ret.setResultList(Arrays.asList(cuimList.toArray()));
		}
		return ret;
	}

	private void addExtraInfoForModel(CourseModel courseModel) {
		this.addIFSName(courseModel);
	}

	private void addIFSName(CourseModel courseModel) {
		ResultModel rm = categoryInfoService.getIndustryByIndustryId(String.valueOf(courseModel.getIndustryId()));
		if (!("3000").equals(rm.getErrorCode())) {
			courseModel.setIndustryName("");
		} else {
			courseModel.setIndustryName(((IndustryModel) rm.getResult()).getName());
		}
		rm = categoryInfoService.getFieldByFieldId(String.valueOf(courseModel.getFieldId()));
		if (!("3000").equals(rm.getErrorCode())) {
			courseModel.setFieldName("");
		} else {
			courseModel.setFieldName(((FieldModel) rm.getResult()).getName());
		}
		rm = categoryInfoService.getStageByStageId(String.valueOf(courseModel.getStageId()));
		if (!("3000").equals(rm.getErrorCode())) {
			courseModel.setStageName("");
		} else {
			courseModel.setStageName(((StageModel) rm.getResult()).getName());
		}
		courseModel.setCourseAuditStatusName(CourseAuditStatus.values()[courseModel.getAuditStatus()].toString());
		courseModel.setCourseTypeName(CourseType.values()[courseModel.getCourseType()].toString());
		courseModel.setFormatSchoolTime(courseModel.getSchoolTime().toString());
	}

	@Override
	public ResultModel getOngoingPublishCourse(String userId, String page, String amountPerPage) {
		ResultModel ret = new ResultModel();
		int page_ = 0;
		int amountPerPage_ = 0;
		try {
			page_ = Integer.valueOf(page);
			amountPerPage_ = Integer.valueOf(amountPerPage);
		} catch (Exception e) {
			logger.error(e.toString());
			ret.setErrorCode("6010");
			ret.setErrorMsg("传入参数格式不正确");
			return ret;
		}
		if (page_ <= 0 || amountPerPage_ <= 0) {
			ret.setErrorCode("6001");
			ret.setErrorMsg("页数或每页显示数量必须为正数");
			return ret;
		}
		List<CourseUserInfoModel> cuimList = new ArrayList<>();
		List<CourseModel> cmList = userCourseDao.getOngoingPublishCourse(userId, page_, amountPerPage_);
		if (cmList == null || cmList.size() == 0) {
			ret.setErrorCode("6002");
			ret.setErrorMsg("查询结果为空");
			return ret;
		}
		for (CourseModel cm : cmList) {
			this.addExtraInfoForModel(cm);
			UserProfileModel upm = (UserProfileModel) userProfileService.getUserProfile(cm.getUserId()).getResult();
			if (upm == null) {
				logger.error("ERROR, get user profile in getOngoingPublishCourse");
			}
			CourseUserInfoModel cuim = new CourseUserInfoModel();
			cuim.setCourseInfo(cm);
			cuim.setUserInfo(upm);
			cuimList.add(cuim);
		}
		if (cuimList.size() == 0) {
			ret.setErrorCode("6002");
			ret.setErrorMsg("查询结果为空");
		} else {
			ret.setErrorCode("6000");
			ret.setErrorMsg("操作成功");
			ret.setResultList(Arrays.asList(cuimList.toArray()));
		}
		return ret;
	}

	@Override
	public ResultModel getFinishedPublishCourse(String userId, String page, String amountPerPage) {
		ResultModel ret = new ResultModel();
		int page_ = 0;
		int amountPerPage_ = 0;
		try {
			page_ = Integer.valueOf(page);
			amountPerPage_ = Integer.valueOf(amountPerPage);
		} catch (Exception e) {
			logger.error(e.toString());
			ret.setErrorCode("6010");
			ret.setErrorMsg("传入参数格式不正确");
			return ret;
		}
		if (page_ <= 0 || amountPerPage_ <= 0) {
			ret.setErrorCode("6001");
			ret.setErrorMsg("页数或每页显示数量必须为正数");
			return ret;
		}
		List<CourseUserInfoModel> cuimList = new ArrayList<>();
		List<CourseModel> cmList = userCourseDao.getFinishedPublishCourse(userId, page_, amountPerPage_);
		if (cmList == null || cmList.size() == 0) {
			ret.setErrorCode("6002");
			ret.setErrorMsg("查询结果为空");
			return ret;
		}
		for (CourseModel cm : cmList) {
			this.addExtraInfoForModel(cm);
			UserProfileModel upm = (UserProfileModel) userProfileService.getUserProfile(cm.getUserId()).getResult();
			if (upm == null) {
				logger.error("ERROR, get user profile in getFinishedPublishCourse");
			}
			CourseUserInfoModel cuim = new CourseUserInfoModel();
			cuim.setCourseInfo(cm);
			cuim.setUserInfo(upm);
			cuimList.add(cuim);
		}
		if (cuimList.size() == 0) {
			ret.setErrorCode("6002");
			ret.setErrorMsg("查询结果为空");
		} else {
			ret.setErrorCode("6000");
			ret.setErrorMsg("操作成功");
			ret.setResultList(Arrays.asList(cuimList.toArray()));
		}
		return ret;
	}
}