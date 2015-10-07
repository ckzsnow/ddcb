package com.dd.dao;

import java.sql.Timestamp;
import java.util.List;

import com.dd.constant.Constant.CourseAuditStatus;
import com.dd.constant.Constant.CourseType;
import com.dd.models.CourseModel;

public interface ICourseDao {

	public CourseModel getCourseByCourseId(Long courseId);

	public List<CourseModel> getCourseByCourseNameAndBriefAndDetails(String courseName, String courseBrief,
			String courseDetails, CourseAuditStatus courseAuditStatus, CourseType courseType, int page, int amountPerPage);

	public List<CourseModel> getCourseByIndustryIdAndFieldIdAndStageId(int industryId, int fieldId, int stageId, CourseAuditStatus courseStatus,
			CourseType courseType, int page, int amountPerPage);

	public List<CourseModel> getCourseBySchoolTime(Timestamp startTime, Timestamp endTime,
			CourseAuditStatus courseAuditStatus, CourseType courseType, int page, int amountPerPage);

	public List<CourseModel> getCourseByCourseTypeAndCourseAuditStatus(CourseType courseType,
			CourseAuditStatus courseAuditStatus, int page, int amountPerPage);

	public boolean addCourse(CourseModel courseModel);
	
	public boolean updateCourse(CourseModel courseModel);

	public boolean deleteCourseByCourseId(Long courseId);

}