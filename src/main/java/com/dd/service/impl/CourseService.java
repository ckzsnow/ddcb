package com.dd.service.impl;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dd.constant.Constant.CourseAuditStatus;
import com.dd.constant.Constant.CourseType;
import com.dd.dao.ICourseDao;
import com.dd.models.CourseModel;
import com.dd.models.FieldModel;
import com.dd.models.IndustryModel;
import com.dd.models.ResultModel;
import com.dd.models.StageModel;
import com.dd.service.ICategoryInfoService;
import com.dd.service.ICourseService;

@Service("courseService")
public class CourseService implements ICourseService {

	@Autowired
	private ICourseDao courseDao;
	
	@Autowired
	private ICategoryInfoService categoryInfoService;

	@Override
	public ResultModel getCourseByCourseId(Long courseId) {
		ResultModel ret = new ResultModel();
		CourseModel courseModel = courseDao.getCourseByCourseId(courseId);
		if(courseModel == null) {
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
			String courseDetails, CourseAuditStatus courseAuditStatus, CourseType courseType, int page,
			int amountPerPage) {
		ResultModel ret = new ResultModel();
		if(page <= 0 || amountPerPage <= 0) {
			ret.setErrorCode("2002");
			ret.setErrorMsg("页数或每页显示数量为负数");
			return ret;
		}
		List<CourseModel> retList = courseDao.getCourseByCourseNameAndBriefAndDetails(courseName, courseBrief, courseDetails, courseAuditStatus, courseType, page, amountPerPage);
		if(retList.size() != 0) {
			ret.setErrorCode("0000");
			ret.setErrorMsg("操作成功");
			this.addExtraInfoForModelList(retList);
			ret.setResultList(Arrays.asList(retList.toArray()));
		} else {
			ret.setErrorCode("2003");
			ret.setErrorMsg("查询结果为空");
		}
		return ret;
	}

	@Override
	public ResultModel getCourseByIndustryIdAndFieldIdAndStageId(int industryId, int fieldId, int stageId,
			CourseAuditStatus courseStatus, CourseType courseType, int page, int amountPerPage) {
		ResultModel ret = new ResultModel();
		if(page <= 0 || amountPerPage <= 0) {
			ret.setErrorCode("2002");
			ret.setErrorMsg("页数或每页显示数量为负数");
			return ret;
		}
		List<CourseModel> retList = courseDao.getCourseByIndustryIdAndFieldIdAndStageId(industryId, fieldId, stageId, courseStatus, courseType, page, amountPerPage);
		if(retList.size() != 0) {
			ret.setErrorCode("0000");
			ret.setErrorMsg("操作成功");
			this.addExtraInfoForModelList(retList);
			ret.setResultList(Arrays.asList(retList.toArray()));
		} else {
			ret.setErrorCode("2003");
			ret.setErrorMsg("查询结果为空");
		}
		return ret;
	}

	@Override
	public ResultModel getCourseBySchoolTime(Timestamp startTime, Timestamp endTime,
			CourseAuditStatus courseAuditStatus, CourseType courseType, int page, int amountPerPage) {
		ResultModel ret = new ResultModel();
		if(page <= 0 || amountPerPage <= 0) {
			ret.setErrorCode("2002");
			ret.setErrorMsg("页数或每页显示数量为负数");
			return ret;
		}
		List<CourseModel> retList = courseDao.getCourseBySchoolTime(startTime, endTime, courseAuditStatus, courseType, page, amountPerPage);
		if(retList.size() != 0) {
			ret.setErrorCode("0000");
			ret.setErrorMsg("操作成功");
			this.addExtraInfoForModelList(retList);
			ret.setResultList(Arrays.asList(retList.toArray()));
		} else {
			ret.setErrorCode("2003");
			ret.setErrorMsg("查询结果为空");
		}
		return ret;
	}

	@Override
	public ResultModel getCourseByCourseTypeAndCourseAuditStatus(CourseType courseType,
			CourseAuditStatus courseAuditStatus, int page, int amountPerPage) {
		ResultModel ret = new ResultModel();
		if(page <= 0 || amountPerPage <= 0) {
			ret.setErrorCode("2002");
			ret.setErrorMsg("页数或每页显示数量为负数");
			return ret;
		}
		List<CourseModel> retList = courseDao.getCourseByCourseTypeAndCourseAuditStatus(courseType, courseAuditStatus, page, amountPerPage);
		if(retList.size() != 0) {
			ret.setErrorCode("0000");
			ret.setErrorMsg("操作成功");
			this.addExtraInfoForModelList(retList);
			ret.setResultList(Arrays.asList(retList.toArray()));
		} else {
			ret.setErrorCode("2003");
			ret.setErrorMsg("查询结果为空");
		}
		return ret;
	}

	@Override
	public ResultModel addCourse(Map<String, String> params) {
		ResultModel ret = new ResultModel();
		CourseModel courseModel = new CourseModel();
		if(!params.containsKey("name")) {
			ret.setErrorCode("2004");
			ret.setErrorMsg("name未设置");
			return ret;
		}
		courseModel.setName(params.get("name"));
		if(!params.containsKey("brief")) {
			ret.setErrorCode("2005");
			ret.setErrorMsg("brief未设置");
			return ret;
		}
		courseModel.setBrief(params.get("brief"));
		if(!params.containsKey("details")) {
			ret.setErrorCode("2006");
			ret.setErrorMsg("details未设置");
			return ret;
		}
		courseModel.setDetails(params.get("details"));
		if(!params.containsKey("industry_id")) {
			ret.setErrorCode("2007");
			ret.setErrorMsg("industry_id未设置");
			return ret;
		}
		courseModel.setIndustryId(Integer.valueOf(params.get("industry_id")));
		if(!params.containsKey("field_id")) {
			ret.setErrorCode("2008");
			ret.setErrorMsg("field_id未设置");
			return ret;
		}
		courseModel.setFieldId(Integer.valueOf(params.get("field_id")));
		if(!params.containsKey("stage_id")) {
			ret.setErrorCode("2009");
			ret.setErrorMsg("stage_id未设置");
			return ret;
		}
		courseModel.setStageId(Integer.valueOf(params.get("stage_id")));
		if(!params.containsKey("school_time")) {
			ret.setErrorCode("2010");
			ret.setErrorMsg("school_time未设置");
			return ret;
		}
		courseModel.setSchoolTime(Timestamp.valueOf(params.get("school_time")));//String的类型必须形如： yyyy-mm-dd hh:mm:ss[.f...] 这样的格式，中括号表示可选
		courseModel.setDocAttatch(params.containsKey("doc_attatch") ? params.get("doc_attatch") : "");
		courseModel.setVoiceAttatch(params.containsKey("voice_attatch") ? params.get("voice_attatch") : "");
		courseModel.setCourseType(params.containsKey("course_type") ? Integer.valueOf(params.get("course_type")) : 0);
		courseModel.setAuditStatus(0);
		if(courseDao.addCourse(courseModel)) {
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
		if(params.containsKey("name")) {
			courseModel.setName(params.get("name"));
		}
		if(params.containsKey("brief")) {
			courseModel.setBrief(params.get("brief"));
		}
		if(params.containsKey("details")) {
			courseModel.setDetails(params.get("details"));
		}
		if(params.containsKey("industry_id")) {
			courseModel.setIndustryId(Integer.valueOf(params.get("industry_id")));
		}
		if(params.containsKey("field_id")) {
			courseModel.setFieldId(Integer.valueOf(params.get("field_id")));
		}
		if(params.containsKey("stage_id")) {
			courseModel.setStageId(Integer.valueOf(params.get("stage_id")));
		}
		if(params.containsKey("school_time")) {
			courseModel.setSchoolTime(Timestamp.valueOf(params.get("school_time")));
		}
		if(params.containsKey("doc_attatch")) {
			courseModel.setDocAttatch(params.get("doc_attatch"));
		}
		if(params.containsKey("voice_attatch")) {
			courseModel.setVoiceAttatch(params.get("voice_attatch"));
		}
		if(params.containsKey("course_type")) {
			courseModel.setCourseType(Integer.valueOf(params.get("course_type")));
		}
		if(params.containsKey("audit_status")) {
			courseModel.setAuditStatus(Integer.valueOf(params.get("audit_status")));
		}
		if(courseDao.updateCourse(courseModel)) {
			ret.setErrorCode("2000");
			ret.setErrorMsg("操作成功");
		} else {
			ret.setErrorCode("2011");
			ret.setErrorMsg("操作数据库失败");
		}
		return ret;
	}

	@Override
	public ResultModel deleteCourseByCourseId(Long courseId) {
		ResultModel ret = new ResultModel();
		if(courseId == null) {
			ret.setErrorCode("2012");
			ret.setErrorMsg("course_id为空");
			return ret;
		}
		if(courseDao.deleteCourseByCourseId(courseId)) {
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
		for(CourseModel model : courseModelList) {
			this.addIFSName(model);
		}
	}
	
	private void addIFSName(CourseModel courseModel) {
		ResultModel rm = categoryInfoService.getIndustryByIndustryId(courseModel.getIndustryId());
		if(!("3000").equals(rm.getErrorCode())) {
			courseModel.setIndustryName("未知");
		} else {
			courseModel.setIndustryName(((IndustryModel)rm.getResult()).getName());
		}
		rm = categoryInfoService.getFieldByFieldId(courseModel.getFieldId());
		if(!("3000").equals(rm.getErrorCode())) {
			courseModel.setFieldName("未知");
		} else {
			courseModel.setFieldName(((FieldModel)rm.getResult()).getName());
		}
		rm = categoryInfoService.getStageByStageId(courseModel.getStageId());
		if(!("3000").equals(rm.getErrorCode())) {
			courseModel.setStageName("未知");
		} else {
			courseModel.setStageName(((StageModel)rm.getResult()).getName());
		}
	}
}
