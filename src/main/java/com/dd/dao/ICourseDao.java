package com.dd.dao;

import java.sql.Timestamp;
import java.util.List;

import com.dd.dao.DaoConstant.CourseAuditStatus;
import com.dd.dao.DaoConstant.CourseType;
import com.dd.models.CourseModel;

public interface ICourseDao {

	public CourseModel getCourseByCourseId(Long courseId);
	
	public List<CourseModel> getCourseByCourseNameForFuzzySearch(String courseName, CourseAuditStatus courseStatus, CourseType courseType);
	
	public List<CourseModel> getCourseByCourseNameForExactSearch(String courseName, CourseAuditStatus courseStatus, CourseType courseType);
	
	public List<CourseModel> getCourseByCourseBriefForFuzzySearch(String courseBrief, CourseAuditStatus courseStatus, CourseType courseType);
	
	public List<CourseModel> getCourseByCourseDetailsForFuzzySearch(String courseDetails, CourseAuditStatus courseStatus, CourseType courseType);
	
	public List<CourseModel> getCourseByIndustryId(int industryId, CourseAuditStatus courseStatus, CourseType courseType);
	
	public List<CourseModel> getCourseByFieldId(int fieldId, CourseAuditStatus courseStatus, CourseType courseType);
	
	public List<CourseModel> getCourseByStageId(int stageId, CourseAuditStatus courseStatus, CourseType courseType);
	
	public List<CourseModel> getCourseBySchoolTime(Timestamp startTime, Timestamp endTime, CourseAuditStatus courseStatus, CourseType courseType);
	
	public List<CourseModel> getCourseByCourseTypeAndCourseAuditStatus(CourseType courseType, CourseAuditStatus courseStatus);
	
	public boolean addCourse(CourseModel courseModel);
	
	public boolean updateCourseNameByCourseId(String courseName, Long courseId);
	
	public boolean updateCourseBriefByCourseId(String courseBrief, Long courseId);
	
	public boolean updateCourseDetailsByCourseId(String courseDetails, Long courseId);
	
	public boolean updateCourseIndustryByCourseId(String courseBrief, Long courseId);
	
	public boolean updateCourseFieldByCourseId(String courseField, Long courseId);
	
	public boolean updateCourseStageByCourseId(String courseStage, Long courseId);
	
	public boolean updateCourseSchoolTimeByCourseId(Timestamp startTime, Long courseId);
	
	public boolean updateCourseTypeByCourseId(CourseType courseType, Long courseId);
	
	public boolean updateCourseAuditStatusByCourseId(CourseAuditStatus courseStatus, Long courseId);
	
	public boolean deleteCourseByCourseId(Long courseId);
	
}
