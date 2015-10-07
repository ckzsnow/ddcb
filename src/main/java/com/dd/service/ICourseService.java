package com.dd.service;

import java.sql.Timestamp;
import java.util.Map;

import com.dd.constant.Constant.CourseAuditStatus;
import com.dd.constant.Constant.CourseType;
import com.dd.models.ResultModel;

public interface ICourseService {

	public ResultModel getCourseByCourseId(Long courseId);
	
	public ResultModel getCourseByCourseNameAndBriefAndDetails(String courseName, String courseBrief,
			String courseDetails, CourseAuditStatus courseAuditStatus, CourseType courseType, int page, int amountPerPage);
	
	public ResultModel getCourseByIndustryIdAndFieldIdAndStageId(int industryId, int fieldId, int stageId, CourseAuditStatus courseStatus,
			CourseType courseType, int page, int amountPerPage);

	public ResultModel getCourseBySchoolTime(Timestamp startTime, Timestamp endTime,
			CourseAuditStatus courseAuditStatus, CourseType courseType, int page, int amountPerPage);

	public ResultModel getCourseByCourseTypeAndCourseAuditStatus(CourseType courseType,
			CourseAuditStatus courseAuditStatus, int page, int amountPerPage);

	public ResultModel addCourse(Map<String, String> params);
	
	public ResultModel updateCourse(Map<String, String> params);

	public ResultModel deleteCourseByCourseId(Long courseId);
	
}
