package com.dd.service;

import java.util.Map;

import com.dd.models.ResultModel;

public interface ICourseService {

	public ResultModel getCourseByCourseId(String courseId);
	
	public ResultModel getCourseByCourseNameAndBriefAndDetails(String courseName, String courseBrief,
			String courseDetails, String courseAuditStatus, String courseType, String page, String amountPerPage);
	
	public ResultModel getCourseByIndustryIdAndFieldIdAndStageId(String industryId, String fieldId, String stageId, String courseStatus,
			String courseType, String page, String amountPerPage);

	public ResultModel getCourseBySchoolTime(String startTime, String endTime,
			String courseAuditStatus, String courseType, String page, String amountPerPage);

	public ResultModel getCourseByCourseTypeAndCourseAuditStatus(String courseType,
			String courseAuditStatus, String page, String amountPerPage);

	public ResultModel addCourse(Map<String, String> params);
	
	public ResultModel updateCourse(Map<String, String> params);

	public ResultModel deleteCourseByCourseId(String courseId);
	
}
