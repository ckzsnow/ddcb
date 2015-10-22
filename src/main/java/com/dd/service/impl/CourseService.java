package com.dd.service.impl;

import java.sql.Timestamp;
import java.util.Arrays;
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
import com.dd.models.CourseModel;
import com.dd.models.FieldModel;
import com.dd.models.IndustryModel;
import com.dd.models.ResultModel;
import com.dd.models.StageModel;
import com.dd.models.UserCourseModel;
import com.dd.service.ICategoryInfoService;
import com.dd.service.ICourseService;

@Service("courseService")
public class CourseService implements ICourseService {

	@Autowired
	private ICourseDao courseDao;
	
	@Autowired
	private IUserCourseDao userCourseDao;

	@Autowired
	private ICategoryInfoService categoryInfoService;

	private static final Logger logger = LoggerFactory.getLogger(CourseService.class);

	@Override
	public ResultModel getCourseByCourseId(String courseId) {
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
		CourseModel courseModel = courseDao.getCourseByCourseId(courseId_);
		if (courseModel == null) {
			ret.setErrorCode("2001");
			ret.setErrorMsg("未查询到数据");
		} else {
			ret.setErrorCode("2000");
			ret.setErrorMsg("操作成功");
			this.addExtraInfoForModel(courseModel);
			ret.setResult(courseModel);
		}
		return ret;
	}

	@Override
	public ResultModel getCourseByCourseNameAndBriefAndDetails(String courseName, String courseBrief,
			String courseDetails, String courseAuditStatus, String courseType, String page,
			String amountPerPage) {
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
	public ResultModel getCourseByIndustryIdAndFieldIdAndStageId(String industryId, String fieldId, String stageId,
			String courseStatus, String courseType, String page, String amountPerPage) {
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
	public ResultModel getCourseBySchoolTime(String startTime, String endTime,
			String courseAuditStatus, String courseType, String page, String amountPerPage) {
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
			startTime_ = Timestamp.valueOf(startTime);//String的类型必须形如： yyyy-mm-dd hh:mm:ss[.f...] 这样的格式，中括号表示可选，否则报错
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
		List<CourseModel> retList = courseDao.getCourseBySchoolTime(startTime_, endTime_, courseAuditStatus_, courseType_,
				page_, amountPerPage_);
		if (retList != null && retList.size() != 0) {
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
	public ResultModel getCourseByCourseTypeAndCourseAuditStatus(String courseType,
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
		if(courseId != null) {
			UserCourseModel ucm = new UserCourseModel();
			ucm.setUserId(params.get("user_id"));
			ucm.setCourseId(courseId);
			ucm.setUserType(Constant.UserType.TEACH.value());
			opStatus = userCourseDao.addUserCourse(ucm);
		}
		if(opStatus) {
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
			} catch (IllegalArgumentException | NullPointerException  e) {
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
			} catch (IllegalArgumentException | NullPointerException  e) {
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
		if (courseDao.deleteCourseByCourseId(courseId_)) {
			ret.setErrorCode("2000");
			ret.setErrorMsg("操作成功");
		} else {
			ret.setErrorCode("2011");
			ret.setErrorMsg("操作数据库失败");
		}
		return ret;
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
			courseModel.setIndustryName("未知");
		} else {
			courseModel.setIndustryName(((IndustryModel) rm.getResult()).getName());
		}
		rm = categoryInfoService.getFieldByFieldId(String.valueOf(courseModel.getFieldId()));
		if (!("3000").equals(rm.getErrorCode())) {
			courseModel.setFieldName("未知");
		} else {
			courseModel.setFieldName(((FieldModel) rm.getResult()).getName());
		}
		rm = categoryInfoService.getStageByStageId(String.valueOf(courseModel.getStageId()));
		if (!("3000").equals(rm.getErrorCode())) {
			courseModel.setStageName("未知");
		} else {
			courseModel.setStageName(((StageModel) rm.getResult()).getName());
		}
		courseModel.setCourseAuditStatusName(CourseAuditStatus.values()[courseModel.getAuditStatus()].toString());
		courseModel.setCourseTypeName(CourseType.values()[courseModel.getCourseType()].toString());
		courseModel.setFormatSchoolTime(courseModel.getSchoolTime().toString());
	}
}
