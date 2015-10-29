package com.dd.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dd.constant.Constant;
import com.dd.constant.Constant.CourseAuditStatus;
import com.dd.constant.Constant.CourseType;
import com.dd.dao.ICourseDao;
import com.dd.dao.IUserCourseDao;
import com.dd.dao.IUserProfileDao;
import com.dd.models.CourseModel;
import com.dd.models.CourseUserInfoModel;
import com.dd.models.FieldModel;
import com.dd.models.IndustryModel;
import com.dd.models.ResultModel;
import com.dd.models.StageModel;
import com.dd.models.UserCourseModel;
import com.dd.models.UserProfileModel;
import com.dd.service.ICategoryInfoService;
import com.dd.service.ICourseService;
import com.dd.service.IUserCourseService;
import com.dd.service.IUserProfileService;

@Service("courseService")
public class CourseService implements ICourseService {

	@Autowired
	private ICourseDao courseDao;

	@Autowired
	private IUserCourseDao userCourseDao;

	@Autowired
	private IUserProfileDao userProfileDao;

	@Autowired
	private IUserProfileService userProfileService;

	@Autowired
	private IUserCourseService userCourseService;

	@Autowired
	private ICategoryInfoService categoryInfoService;

	private static final Logger logger = LoggerFactory.getLogger(CourseService.class);

	@Override
	public ResultModel getCourseByCourseId(String userId, String courseId) {
		ResultModel ret = new ResultModel();
		long courseId_ = 0;
		try {
			courseId_ = Long.valueOf(courseId);
		} catch (NumberFormatException e) {
			logger.error(e.toString());
			ret.setErrorCode("2020");
			ret.setErrorMsg("传入参数格式不正确");
			return ret;
		}
		CourseUserInfoModel cuim = new CourseUserInfoModel();
		CourseModel courseModel = courseDao.getCourseByCourseId(courseId_);
		if (courseModel == null) {
			ret.setErrorCode("2001");
			ret.setErrorMsg("未查询到数据");
			return ret;
		}

		if (userCourseDao.userIsEnterCourseByUserIdAndCourseIdAndUserType(userId, courseId_)) {
			courseModel.setIsEnter("true");
		} else {
			courseModel.setIsEnter("false");
		}

		UserProfileModel upm = getUserProfileInfoForCourse(courseModel.getId());
		if (upm == null) {
			ret.setErrorCode("2030");
			ret.setErrorMsg("获取课程讲师信息失败！");
		} else {
			ret.setErrorCode("2000");
			ret.setErrorMsg("操作成功");
			this.addExtraInfoForModel(courseModel);
			cuim.setCourseInfo(courseModel);
			cuim.setUserInfo(upm);
			ret.setResult(cuim);
		}
		return ret;
	}

	@Override
	public ResultModel getCourseByCourseNameAndBriefAndDetails(String userId, String courseName, String courseBrief,
			String courseDetails, String courseAuditStatus, String courseType, String page, String amountPerPage) {
		ResultModel ret = new ResultModel();
		int page_ = 0;
		int amountPerPage_ = 0;
		CourseAuditStatus courseAuditStatus_ = CourseAuditStatus.ALL;
		CourseType courseType_ = CourseType.ALL;
		try {
			page_ = Integer.valueOf(page);
			amountPerPage_ = Integer.valueOf(amountPerPage);
			courseAuditStatus_ = Enum.valueOf(CourseAuditStatus.class, courseAuditStatus);
			courseType_ = Enum.valueOf(CourseType.class, courseType);
		} catch (Exception e) {
			logger.error(e.toString());
			ret.setErrorCode("2020");
			ret.setErrorMsg("传入参数格式不正确");
			return ret;
		}
		if (page_ <= 0 || amountPerPage_ <= 0) {
			ret.setErrorCode("2002");
			ret.setErrorMsg("页数或每页显示数量必须为正数");
			return ret;
		}
		List<CourseModel> retList = courseDao.getCourseByCourseNameAndBriefAndDetails(courseName, courseBrief,
				courseDetails, courseAuditStatus_, courseType_, page_, amountPerPage_);
		if (retList != null && retList.size() != 0) {
			for (CourseModel cm : retList) {
				if (userCourseDao.userIsEnterCourseByUserIdAndCourseIdAndUserType(userId, cm.getId())) {
					cm.setIsEnter("true");
				} else {
					cm.setIsEnter("false");
				}
			}
			ret.setErrorCode("2000");
			ret.setErrorMsg("操作成功");
			this.addExtraInfoForModelList(retList);
			ret.setResultList(Arrays.asList(retList.toArray()));
		} else {
			ret.setErrorCode("2001");
			ret.setErrorMsg("未查询到数据");
		}
		return ret;
	}

	@Override
	public ResultModel getCourseByIndustryIdAndFieldIdAndStageId(String userId, String industryId, String fieldId,
			String stageId, String courseStatus, String courseType, String page, String amountPerPage) {
		ResultModel ret = new ResultModel();
		int page_ = 0;
		int amountPerPage_ = 0;
		int industryId_ = 0;
		int fieldId_ = 0;
		int stageId_ = 0;
		CourseAuditStatus courseAuditStatus_ = CourseAuditStatus.ALL;
		CourseType courseType_ = CourseType.ALL;
		try {
			page_ = Integer.valueOf(page);
			amountPerPage_ = Integer.valueOf(amountPerPage);
			industryId_ = Integer.valueOf(industryId);
			fieldId_ = Integer.valueOf(fieldId);
			stageId_ = Integer.valueOf(stageId);
			courseAuditStatus_ = Enum.valueOf(CourseAuditStatus.class, courseStatus);
			courseType_ = Enum.valueOf(CourseType.class, courseType);
		} catch (Exception e) {
			logger.error(e.toString());
			ret.setErrorCode("2020");
			ret.setErrorMsg("传入参数格式不正确");
			return ret;
		}
		if (page_ <= 0 || amountPerPage_ <= 0) {
			ret.setErrorCode("2002");
			ret.setErrorMsg("页数或每页显示数量必须为正数");
			return ret;
		}
		List<CourseModel> retList = courseDao.getCourseByIndustryIdAndFieldIdAndStageId(industryId_, fieldId_, stageId_,
				courseAuditStatus_, courseType_, page_, amountPerPage_);
		if (retList != null && retList.size() != 0) {
			for (CourseModel cm : retList) {
				if (userCourseDao.userIsEnterCourseByUserIdAndCourseIdAndUserType(userId, cm.getId())) {
					cm.setIsEnter("true");
				} else {
					cm.setIsEnter("false");
				}
			}
			ret.setErrorCode("2000");
			ret.setErrorMsg("操作成功");
			this.addExtraInfoForModelList(retList);
			ret.setResultList(Arrays.asList(retList.toArray()));
		} else {
			ret.setErrorCode("2001");
			ret.setErrorMsg("未查询到数据");
		}
		return ret;
	}

	@Override
	public ResultModel getCourseBySchoolTime(String userId, String startTime, String endTime, String courseAuditStatus,
			String courseType, String page, String amountPerPage) {
		ResultModel ret = new ResultModel();
		int page_ = 0;
		int amountPerPage_ = 0;
		CourseAuditStatus courseAuditStatus_ = CourseAuditStatus.ALL;
		CourseType courseType_ = CourseType.ALL;
		Timestamp startTime_ = null;
		Timestamp endTime_ = null;
		try {
			page_ = Integer.valueOf(page);
			amountPerPage_ = Integer.valueOf(amountPerPage);
			courseAuditStatus_ = Enum.valueOf(CourseAuditStatus.class, courseAuditStatus);
			courseType_ = Enum.valueOf(CourseType.class, courseType);
			startTime_ = Timestamp.valueOf(startTime);// String的类型必须形如：
														// yyyy-mm-dd
														// hh:mm:ss[.f...]
														// 这样的格式，中括号表示可选，否则报错
			endTime_ = Timestamp.valueOf(endTime);
		} catch (Exception e) {
			logger.error(e.toString());
			ret.setErrorCode("2020");
			ret.setErrorMsg("传入参数格式不正确");
			return ret;
		}
		if (page_ <= 0 || amountPerPage_ <= 0) {
			ret.setErrorCode("2002");
			ret.setErrorMsg("页数或每页显示数量必须为正数");
			return ret;
		}
		List<CourseModel> retList = courseDao.getCourseBySchoolTime(startTime_, endTime_, courseAuditStatus_,
				courseType_, page_, amountPerPage_);
		if (retList != null && retList.size() != 0) {
			for (CourseModel cm : retList) {
				if (userCourseDao.userIsEnterCourseByUserIdAndCourseIdAndUserType(userId, cm.getId())) {
					cm.setIsEnter("true");
				} else {
					cm.setIsEnter("false");
				}
			}
			ret.setErrorCode("2000");
			ret.setErrorMsg("操作成功");
			this.addExtraInfoForModelList(retList);
			ret.setResultList(Arrays.asList(retList.toArray()));
		} else {
			ret.setErrorCode("2001");
			ret.setErrorMsg("未查询到数据");
		}
		return ret;
	}

	@Override
	public ResultModel getCourseByCourseTypeAndCourseAuditStatus(String userId, String courseType,
			String courseAuditStatus, String page, String amountPerPage) {
		ResultModel ret = new ResultModel();
		int page_ = 0;
		int amountPerPage_ = 0;
		CourseAuditStatus courseAuditStatus_ = CourseAuditStatus.ALL;
		CourseType courseType_ = CourseType.ALL;
		try {
			page_ = Integer.valueOf(page);
			amountPerPage_ = Integer.valueOf(amountPerPage);
			courseAuditStatus_ = Enum.valueOf(CourseAuditStatus.class, courseAuditStatus);
			courseType_ = Enum.valueOf(CourseType.class, courseType);
		} catch (Exception e) {
			logger.error(e.toString());
			ret.setErrorCode("2020");
			ret.setErrorMsg("传入参数格式不正确");
			return ret;
		}
		if (page_ <= 0 || amountPerPage_ <= 0) {
			ret.setErrorCode("2002");
			ret.setErrorMsg("页数或每页显示数量必须为正数");
			return ret;
		}

		List<CourseModel> retList = courseDao.getCourseByCourseTypeAndCourseAuditStatus(courseType_, courseAuditStatus_,
				page_, amountPerPage_);
		if (retList != null && retList.size() != 0) {
			for (CourseModel cm : retList) {
				if (userCourseDao.userIsEnterCourseByUserIdAndCourseIdAndUserType(userId, cm.getId())) {
					cm.setIsEnter("true");
				} else {
					cm.setIsEnter("false");
				}
			}
			ret.setErrorCode("2000");
			ret.setErrorMsg("操作成功");
			this.addExtraInfoForModelList(retList);
			ret.setResultList(Arrays.asList(retList.toArray()));
		} else {
			ret.setErrorCode("2001");
			ret.setErrorMsg("未查询到数据");
		}
		return ret;
	}

	@Override
	public ResultModel addCourse(Map<String, String> params) {
		ResultModel ret = new ResultModel();
		CourseModel courseModel = new CourseModel();
		if (!params.containsKey("name")) {
			ret.setErrorCode("2004");
			ret.setErrorMsg("name未设置");
			return ret;
		}
		courseModel.setName(params.get("name"));
		if (!params.containsKey("brief")) {
			ret.setErrorCode("2005");
			ret.setErrorMsg("brief未设置");
			return ret;
		}
		courseModel.setBrief(params.get("brief"));
		if (!params.containsKey("details")) {
			ret.setErrorCode("2006");
			ret.setErrorMsg("details未设置");
			return ret;
		}
		courseModel.setDetails(params.get("details"));
		if (!params.containsKey("industry_id")) {
			ret.setErrorCode("2007");
			ret.setErrorMsg("industry_id未设置");
			return ret;
		}
		courseModel.setIndustryId(Integer.valueOf(params.get("industry_id")));
		if (!params.containsKey("field_id")) {
			ret.setErrorCode("2008");
			ret.setErrorMsg("field_id未设置");
			return ret;
		}
		courseModel.setFieldId(Integer.valueOf(params.get("field_id")));
		if (!params.containsKey("stage_id")) {
			ret.setErrorCode("2009");
			ret.setErrorMsg("stage_id未设置");
			return ret;
		}
		courseModel.setStageId(Integer.valueOf(params.get("stage_id")));
		if (!params.containsKey("school_time")) {
			ret.setErrorCode("2010");
			ret.setErrorMsg("school_time未设置");
			return ret;
		}
		try {
			Timestamp schoolTime_ = Timestamp.valueOf(params.get("school_time"));
			courseModel.setSchoolTime(schoolTime_);
		} catch (Exception e) {
			logger.error(e.toString());
			ret.setErrorCode("2013");
			ret.setErrorMsg("时间格式不正确，格式为： yyyy-mm-dd hh:mm:ss");
			return ret;
		}
		courseModel.setDocAttatch(params.containsKey("doc_attatch") ? params.get("doc_attatch") : "");
		courseModel.setVoiceAttatch(params.containsKey("voice_attatch") ? params.get("voice_attatch") : "");
		courseModel.setCourseType(0);
		courseModel.setAuditStatus(0);
		Long courseId = courseDao.addCourse(courseModel);
		boolean opStatus = false;
		if (courseId != null) {
			UserCourseModel ucm = new UserCourseModel();
			ucm.setUserId(params.get("user_id"));
			ucm.setCourseId(courseId);
			ucm.setUserType(Constant.UserType.TEACH.value());
			Map<String, String> map = new HashMap<>();
			map.put("user_id", params.get("user_id"));
			map.put("course_id", String.valueOf(courseId));
			map.put("user_type", Constant.UserType.TEACH.name());
			opStatus = ("6000").equals(userCourseService.addUserCourse(map).getErrorCode());
		}
		if (opStatus) {
			ret.setErrorCode("2000");
			ret.setErrorMsg("操作成功");
		} else {
			ret.setErrorCode("2011");
			ret.setErrorMsg("操作数据库失败");
		}
		return ret;
	}

	@Override
	public ResultModel updateCourse(Map<String, String> params) {
		ResultModel ret = new ResultModel();
		CourseModel courseModel = new CourseModel();
		if (!params.containsKey("course_id")) {
			ret.setErrorCode("2014");
			ret.setErrorMsg("course_id未设置");
			return ret;
		}
		if (params.containsKey("name")) {
			courseModel.setName(params.get("name"));
		}
		if (params.containsKey("brief")) {
			courseModel.setBrief(params.get("brief"));
		}
		if (params.containsKey("details")) {
			courseModel.setDetails(params.get("details"));
		}
		if (params.containsKey("doc_attatch")) {
			courseModel.setDocAttatch(params.get("doc_attatch"));
		}
		if (params.containsKey("voice_attatch")) {
			courseModel.setVoiceAttatch(params.get("voice_attatch"));
		}

		try {
			if (params.containsKey("industry_id")) {
				courseModel.setIndustryId(Integer.valueOf(params.get("industry_id")));
			}
			if (params.containsKey("field_id")) {
				courseModel.setFieldId(Integer.valueOf(params.get("field_id")));
			}
			if (params.containsKey("stage_id")) {
				courseModel.setStageId(Integer.valueOf(params.get("stage_id")));
			}
			if (params.containsKey("school_time")) {
				courseModel.setSchoolTime(Timestamp.valueOf(params.get("school_time")));
			}
			courseModel.setId(Long.valueOf(params.get("course_id")));
		} catch (Exception e) {
			logger.error(e.toString());
			ret.setErrorCode("2020");
			ret.setErrorMsg("传入参数格式不正确");
			return ret;
		}

		if (params.containsKey("course_type")) {
			CourseType courseType_ = CourseType.ALL;
			try {
				courseType_ = Enum.valueOf(CourseType.class, params.get("course_type"));
			} catch (IllegalArgumentException | NullPointerException e) {
				logger.error(e.toString());
				ret.setErrorCode("2021");
				ret.setErrorMsg("传入枚举类型格式不正确");
				return ret;
			}
			courseModel.setCourseType(courseType_.value());
		}
		if (params.containsKey("audit_status")) {
			CourseAuditStatus courseAuditStatus = CourseAuditStatus.ALL;
			try {
				courseAuditStatus = Enum.valueOf(CourseAuditStatus.class, params.get("audit_status"));
			} catch (IllegalArgumentException | NullPointerException e) {
				logger.error(e.toString());
				ret.setErrorCode("2021");
				ret.setErrorMsg("传入枚举类型格式不正确");
				return ret;
			}
			courseModel.setAuditStatus(courseAuditStatus.value());
		}
		if (courseDao.updateCourse(courseModel)) {
			ret.setErrorCode("2000");
			ret.setErrorMsg("操作成功");
		} else {
			ret.setErrorCode("2011");
			ret.setErrorMsg("操作数据库失败");
		}
		return ret;
	}

	@Override
	public ResultModel deleteCourseByCourseId(String courseId) {
		ResultModel ret = new ResultModel();
		if (courseId == null) {
			ret.setErrorCode("2014");
			ret.setErrorMsg("course_id未设置");
			return ret;
		}
		long courseId_ = 0;
		try {
			courseId_ = Long.valueOf(courseId);
		} catch (NumberFormatException e) {
			logger.error(e.toString());
			ret.setErrorCode("2020");
			ret.setErrorMsg("传入参数格式不正确");
			return ret;
		}
		if (courseDao.deleteCourseByCourseId(courseId_) && userCourseDao.deleteUserCourseByCourseId(courseId_)) {
			ret.setErrorCode("2000");
			ret.setErrorMsg("操作成功");
		} else {
			ret.setErrorCode("2011");
			ret.setErrorMsg("操作数据库失败");
		}
		return ret;
	}

	@Override
	public ResultModel praiseByCourseId(String courseId) {
		ResultModel ret = new ResultModel();
		long courseId_ = 0;
		try {
			courseId_ = Long.valueOf(courseId);
		} catch (NumberFormatException e) {
			logger.error(e.toString());
			ret.setErrorCode("2020");
			ret.setErrorMsg("传入参数格式不正确");
			return ret;
		}
		if (!courseDao.praiseByCourseId(courseId_)) {
			ret.setErrorCode("2001");
			ret.setErrorMsg("未查询到数据");
		} else {
			ret.setErrorCode("2000");
			ret.setErrorMsg("操作成功");
		}
		return ret;
	}

	private UserProfileModel getUserProfileInfoForCourse(Long courseId) {
		List<UserCourseModel> ucmList = userCourseDao.getUserCourseByCourseIdAndUserType(courseId,
				Constant.UserType.TEACH, 1, 10);
		if (ucmList == null || ucmList.size() != 1)
			return null;
		UserProfileModel upm = (UserProfileModel) userProfileService.getUserProfile(ucmList.get(0).getUserId())
				.getResult();
		if (upm == null)
			return null;
		return upm;
	}

	private void addExtraInfoForModel(CourseModel courseModel) {
		this.addIFSName(courseModel);
	}

	private void addExtraInfoForModelList(List<CourseModel> courseModelList) {
		for (CourseModel model : courseModelList) {
			this.addIFSName(model);
		}
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
	public ResultModel getCarefullyChosenCourse(String userId, String industryId, String fieldId, String stageId,
			String page, String amountPerPage) {
		ResultModel ret = new ResultModel();
		int page_ = 0;
		int amountPerPage_ = 0;
		try {
			page_ = Integer.valueOf(page);
			amountPerPage_ = Integer.valueOf(amountPerPage);
		} catch (Exception e) {
			logger.error(e.toString());
			ret.setErrorCode("2020");
			ret.setErrorMsg("传入参数格式不正确");
			return ret;
		}
		if (page_ <= 0 || amountPerPage_ <= 0) {
			ret.setErrorCode("2002");
			ret.setErrorMsg("页数或每页显示数量必须为正数");
			return ret;
		}
		List<CourseUserInfoModel> cuimList = new ArrayList<>();
		List<CourseModel> courseModelList = courseDao.getCarefullyChosenCourse(industryId, fieldId, stageId, page_,
				amountPerPage_);
		if (courseModelList == null || courseModelList.size() == 0) {
			ret.setErrorCode("2001");
			ret.setErrorMsg("未查询到数据");
			return ret;
		}
		for (CourseModel courseModel : courseModelList) {
			if (userId != null && !userId.isEmpty()) {
				if (userCourseDao.userIsEnterCourseByUserIdAndCourseIdAndUserType(userId, courseModel.getId())) {
					courseModel.setIsEnter("true");
				} else {
					courseModel.setIsEnter("false");
				}
			}
			UserProfileModel upm = getUserProfileInfoForCourse(courseModel.getId());
			if (upm == null) {
				logger.error("ERROR, get user profile in getCarefullyChosenCourse");
			} else {
				this.addExtraInfoForModel(courseModel);
			}
			CourseUserInfoModel cuim = new CourseUserInfoModel();
			cuim.setCourseInfo(courseModel);
			cuim.setUserInfo(upm);
			cuimList.add(cuim);
		}
		if (cuimList.size() == 0) {
			ret.setErrorCode("2001");
			ret.setErrorMsg("未查询到数据");
		} else {
			ret.setErrorCode("2000");
			ret.setErrorMsg("操作成功");
			ret.setResultList(Arrays.asList(cuimList.toArray()));
		}
		return ret;
	}

	@Override
	public ResultModel getLatestCourse(String userId, String industryId, String fieldId, String stageId, String page,
			String amountPerPage) {
		ResultModel ret = new ResultModel();
		int page_ = 0;
		int amountPerPage_ = 0;
		try {
			page_ = Integer.valueOf(page);
			amountPerPage_ = Integer.valueOf(amountPerPage);
		} catch (Exception e) {
			logger.error(e.toString());
			ret.setErrorCode("2020");
			ret.setErrorMsg("传入参数格式不正确");
			return ret;
		}
		if (page_ <= 0 || amountPerPage_ <= 0) {
			ret.setErrorCode("2002");
			ret.setErrorMsg("页数或每页显示数量必须为正数");
			return ret;
		}
		List<CourseUserInfoModel> cuimList = new ArrayList<>();
		List<CourseModel> courseModelList = courseDao.getLatestCourse(industryId, fieldId, stageId, page_,
				amountPerPage_);
		if (courseModelList == null || courseModelList.size() == 0) {
			ret.setErrorCode("2001");
			ret.setErrorMsg("未查询到数据");
			return ret;
		}
		for (CourseModel courseModel : courseModelList) {
			if (userId != null && !userId.isEmpty()) {
				if (userCourseDao.userIsEnterCourseByUserIdAndCourseIdAndUserType(userId, courseModel.getId())) {
					courseModel.setIsEnter("true");
				} else {
					courseModel.setIsEnter("false");
				}
			}
			UserProfileModel upm = getUserProfileInfoForCourse(courseModel.getId());
			if (upm == null) {
				logger.error("ERROR, get user profile in getLatestCourse");
			} else {
				this.addExtraInfoForModel(courseModel);
			}
			CourseUserInfoModel cuim = new CourseUserInfoModel();
			cuim.setCourseInfo(courseModel);
			cuim.setUserInfo(upm);
			cuimList.add(cuim);
		}
		if (cuimList.size() == 0) {
			ret.setErrorCode("2001");
			ret.setErrorMsg("未查询到数据");
		} else {
			ret.setErrorCode("2000");
			ret.setErrorMsg("操作成功");
			ret.setResultList(Arrays.asList(cuimList.toArray()));
		}
		return ret;
	}

	@Override
	public ResultModel getRecommendCourse(String userId, String page, String amountPerPage) {
		ResultModel ret = new ResultModel();
		int page_ = 0;
		int amountPerPage_ = 0;
		try {
			page_ = Integer.valueOf(page);
			amountPerPage_ = Integer.valueOf(amountPerPage);
		} catch (Exception e) {
			logger.error(e.toString());
			ret.setErrorCode("2020");
			ret.setErrorMsg("传入参数格式不正确");
			return ret;
		}
		List<CourseUserInfoModel> cuimList = new ArrayList<>();
		String industryId = String.valueOf(userProfileDao.getUserProfileByUserId(userId).getIndustryId());
		List<CourseModel> courseModelList = courseDao.getRecommendCourse(industryId, null, null, page_, amountPerPage_);
		if (courseModelList == null || courseModelList.size() == 0) {
			ret.setErrorCode("2001");
			ret.setErrorMsg("未查询到数据");
			return ret;
		}
		for (CourseModel courseModel : courseModelList) {
			if (userCourseDao.userIsEnterCourseByUserIdAndCourseIdAndUserType(userId, courseModel.getId())) {
				courseModel.setIsEnter("true");
			} else {
				courseModel.setIsEnter("false");
			}
			UserProfileModel upm = getUserProfileInfoForCourse(courseModel.getId());
			if (upm == null) {
				logger.error("ERROR, get user profile in getLatestCourse");
			} else {
				this.addExtraInfoForModel(courseModel);
			}
			CourseUserInfoModel cuim = new CourseUserInfoModel();
			cuim.setCourseInfo(courseModel);
			cuim.setUserInfo(upm);
			cuimList.add(cuim);
		}
		if (cuimList.size() == 0) {
			ret.setErrorCode("2001");
			ret.setErrorMsg("未查询到数据");
		} else {
			ret.setErrorCode("2000");
			ret.setErrorMsg("操作成功");
			ret.setResultList(Arrays.asList(cuimList.toArray()));
		}
		return ret;
	}
}
