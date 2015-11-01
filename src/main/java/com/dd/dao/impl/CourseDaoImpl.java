package com.dd.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.dd.constant.Constant;
import com.dd.constant.Constant.CourseAuditStatus;
import com.dd.constant.Constant.CourseType;
import com.dd.dao.ICourseDao;
import com.dd.mappers.CityModelMapper;
import com.dd.mappers.CourseModelMapper;
import com.dd.models.CityModel;
import com.dd.models.CourseModel;

@Repository("courseDao")
public class CourseDaoImpl implements ICourseDao {

	private static final Logger logger = LoggerFactory.getLogger(CourseDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public CourseModel getCourseByCourseId(Long courseId) {
		logger.debug("args courseId : {}", courseId);
		String sql = "select * from course where id = ?";
		CourseModel courseModel = null;
		try {
			courseModel = jdbcTemplate.queryForObject(sql, new Object[] { courseId }, new CourseModelMapper());
		} catch (Exception e) {
			logger.error("getCourseByCourseId, exception : {}", e.toString());
		}
		return courseModel;
	}

	@Override
	public List<CourseModel> getCourseByCourseNameAndBriefAndDetails(String courseName, String courseBrief,
			String courseDetails, CourseAuditStatus courseAuditStatus, CourseType courseType, int page,
			int amountPerPage) {
		logger.debug(
				"args courseName : {}, courseBrief : {}, courseDetails : {}, courseAuditStatus : {}, courseType : {}",
				courseName, courseBrief, courseDetails, courseAuditStatus, courseType);
		List<CourseModel> courseList = null;
		StringBuilder sql = new StringBuilder();
		List<Object> argsList = new ArrayList<>();
		int limitBegin = page == 1 ? 0 : (page - 1) * amountPerPage;
		int limitEnd = limitBegin + amountPerPage;
		courseName = courseName == null || courseName.isEmpty() ? "%%" : "%" + courseName + "%";
		courseBrief = courseBrief == null || courseBrief.isEmpty() ? "%%" : "%" + courseBrief + "%";
		courseDetails = courseDetails == null || courseDetails.isEmpty() ? "%%" : "%" + courseDetails + "%";
		sql.append("select * from course where name like '" + courseName + "' and brief like '" + courseBrief
				+ "' and details like '" + courseDetails + "' ");
		if (courseAuditStatus != CourseAuditStatus.ALL) {
			sql.append("and audit_status =? ");
			argsList.add(courseAuditStatus.value());
		}
		if (courseType != CourseType.ALL) {
			sql.append("and course_type =? ");
			argsList.add(courseType.value());
		}
		sql.append(" limit ?,? ");
		argsList.add(limitBegin);
		argsList.add(limitEnd);
		logger.debug("sql : {}", sql);
		try {
			Object[] args = (Object[]) argsList.toArray(new Object[argsList.size()]);
			courseList = jdbcTemplate.query(sql.toString(), args, new CourseModelMapper());
		} catch (Exception e) {
			logger.error("getCourseByCourseNameAndBriefAndDetails, exception : {}", e.toString());
		}
		return courseList;
	}

	@Override
	public List<CourseModel> getCourseByIndustryIdAndFieldIdAndStageId(int industryId, int fieldId, int stageId,
			CourseAuditStatus courseAuditStatus, CourseType courseType, int page, int amountPerPage) {
		logger.debug("args industryId : {}, fieldId : {}, stageId : {}, courseAuditStatus : {}, courseType : {}",
				industryId, fieldId, stageId, courseAuditStatus, courseType);
		List<CourseModel> courseList = null;
		StringBuilder sql = new StringBuilder();
		List<Object> argsList = new ArrayList<>();
		int limitBegin = page == 1 ? 0 : (page - 1) * amountPerPage;
		int limitEnd = limitBegin + amountPerPage;
		sql.append("select * from course where industry_id=? and field_id=? and stage_id=? ");
		argsList.add(industryId);
		argsList.add(fieldId);
		argsList.add(stageId);
		if (courseAuditStatus != CourseAuditStatus.ALL) {
			sql.append("and audit_status =? ");
			argsList.add(courseAuditStatus.value());
		}
		if (courseType != CourseType.ALL) {
			sql.append("and course_type =? ");
			argsList.add(courseType.value());
		}
		sql.append(" limit ?,? ");
		argsList.add(limitBegin);
		argsList.add(limitEnd);
		logger.debug("sql : {}", sql);
		try {
			Object[] args = (Object[]) argsList.toArray(new Object[argsList.size()]);
			courseList = jdbcTemplate.query(sql.toString(), args, new CourseModelMapper());
		} catch (Exception e) {
			logger.error("getCourseByIndustryIdAndFieldIdAndStageId, exception : {}", e.toString());
		}
		return courseList;
	}

	@Override
	public List<CourseModel> getCourseBySchoolTime(Timestamp startTime, Timestamp endTime,
			CourseAuditStatus courseAuditStatus, CourseType courseType, int page, int amountPerPage) {
		logger.debug("args startTime : {}, endTime : {}, courseAuditStatus : {}, courseType : {}", startTime, endTime,
				courseAuditStatus, courseType);
		List<CourseModel> courseList = null;
		StringBuilder sql = new StringBuilder();
		List<Object> argsList = new ArrayList<>();
		int limitBegin = page == 1 ? 0 : (page - 1) * amountPerPage;
		int limitEnd = limitBegin + amountPerPage;
		sql.append("select * from course where create_time between ? and ? ");
		argsList.add(startTime);
		argsList.add(endTime);
		if (courseAuditStatus != CourseAuditStatus.ALL) {
			sql.append("and audit_status =? ");
			argsList.add(courseAuditStatus.value());
		}
		if (courseType != CourseType.ALL) {
			sql.append("and course_type =? ");
			argsList.add(courseType.value());
		}
		sql.append(" limit ?,? ");
		argsList.add(limitBegin);
		argsList.add(limitEnd);
		logger.debug("sql : {}", sql);
		try {
			Object[] args = (Object[]) argsList.toArray(new Object[argsList.size()]);
			courseList = jdbcTemplate.query(sql.toString(), args, new CourseModelMapper());
		} catch (Exception e) {
			logger.error("getCourseBySchoolTime, exception : {}", e.toString());
		}
		return courseList;
	}

	@Override
	public List<CourseModel> getCourseByCourseTypeAndCourseAuditStatus(CourseType courseType,
			CourseAuditStatus courseAuditStatus, int page, int amountPerPage) {
		logger.debug("args courseAuditStatus : {}, courseType : {}", courseAuditStatus, courseType);
		List<CourseModel> courseList = null;
		StringBuilder sql = new StringBuilder();
		List<Object> argsList = new ArrayList<>();
		int limitBegin = page == 1 ? 0 : (page - 1) * amountPerPage;
		int limitEnd = limitBegin + amountPerPage;
		sql.append("select * from course ");
		boolean where_flag = false;
		if (courseAuditStatus != CourseAuditStatus.ALL) {
			sql.append("where audit_status =? ");
			argsList.add(courseAuditStatus.value());
			where_flag = true;
		}
		if (courseType != CourseType.ALL) {
			if (where_flag) {
				sql.append("and course_type =? ");
			} else {
				sql.append("where course_type =? ");
			}
			argsList.add(courseType.value());
		}
		sql.append(" limit ?,? ");
		argsList.add(limitBegin);
		argsList.add(limitEnd);
		logger.debug("sql : {}", sql);
		try {
			Object[] args = (Object[]) argsList.toArray(new Object[argsList.size()]);
			courseList = jdbcTemplate.query(sql.toString(), args, new CourseModelMapper());
		} catch (Exception e) {
			logger.error("getCourseBySchoolTime, exception : {}", e.toString());
		}
		return courseList;
	}

	@Override
	public long addCourse(CourseModel courseModel) {
		logger.debug("args courseModel : {}", courseModel.toString());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(
						Connection connection) throws SQLException {
					String sql = "insert into course (name, brief, details, industry_id, field_id, stage_id, school_time, doc_attatch, voice_attatch, course_type, audit_status, create_time)"
							+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement ps = connection.prepareStatement(sql,
							Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, courseModel.getName());
					ps.setString(2, courseModel.getBrief());
					ps.setString(3, courseModel.getDetails());
					ps.setInt(4, courseModel.getIndustryId());
					ps.setInt(5, courseModel.getFieldId());
					ps.setInt(6, courseModel.getStageId());
					ps.setTimestamp(7, courseModel.getSchoolTime());
					ps.setString(8, courseModel.getDocAttatch());
					ps.setString(9, courseModel.getVoiceAttatch());
					ps.setInt(10, courseModel.getCourseType());
					ps.setInt(11, courseModel.getAuditStatus());
					ps.setTimestamp(12, new Timestamp(System.currentTimeMillis()));
					return ps;
				}
			}, keyHolder);
		} catch (Exception e) {
			logger.debug("addCourse, exception : {}", e.toString());
		}
		return keyHolder.getKey().longValue();
	}

	@Override
	public boolean deleteCourseByCourseId(Long courseId) {
		logger.debug("args courseId : {}", courseId);
		String sql = "delete from course where id = ?";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(sql, courseId);
			affectedRows = affectedRows == 0 ? 1 : affectedRows;
		} catch (Exception e) {
			logger.debug("deleteCourseByCourseId, exception : {}", e.toString());
		}
		return affectedRows != 0;
	}

	@Override
	public boolean updateCourse(CourseModel courseModel) {
		logger.debug("args courseModel : {}", courseModel.toString());
		int affectedRows = 0;
		StringBuilder sql = new StringBuilder();
		List<Object> argsList = new ArrayList<>();
		sql.append("update course set ");
		if (courseModel.getName() != null && !courseModel.getName().isEmpty()) {
			sql.append("name=? ,");
			argsList.add(courseModel.getName());
		}
		if (courseModel.getBrief() != null && !courseModel.getBrief().isEmpty()) {
			sql.append("brief=? ,");
			argsList.add(courseModel.getBrief());
		}
		if (courseModel.getDetails() != null && !courseModel.getDetails().isEmpty()) {
			sql.append("details=? ,");
			argsList.add(courseModel.getDetails());
		}
		if (courseModel.getIndustryId() != null) {
			sql.append("industry_id=? ,");
			argsList.add(courseModel.getIndustryId());
		}
		if (courseModel.getFieldId() != null) {
			sql.append("field_id=? ,");
			argsList.add(courseModel.getFieldId());
		}
		if (courseModel.getStageId() != null) {
			sql.append("stage_id=? ,");
			argsList.add(courseModel.getStageId());
		}
		if (courseModel.getSchoolTime() != null) {
			sql.append("school_time=? ,");
			argsList.add(courseModel.getSchoolTime());
		}
		if (courseModel.getVoiceAttatch() != null && !courseModel.getVoiceAttatch().isEmpty()) {
			sql.append("voice_attatch=? ,");
			argsList.add(courseModel.getVoiceAttatch());
		}
		if (courseModel.getDocAttatch() != null && !courseModel.getDocAttatch().isEmpty()) {
			sql.append("doc_attatch=? ,");
			argsList.add(courseModel.getDocAttatch());
		}
		if (courseModel.getAuditStatus() != null) {
			sql.append("audit_status=? ,");
			argsList.add(courseModel.getAuditStatus());
		}
		if (courseModel.getCourseType() != null) {
			sql.append("course_type=? ,");
			argsList.add(courseModel.getCourseType());
		}
		if (courseModel.getId() == null) {
			logger.debug("courseId is invalid, failed in updating.");
			return false;
		}
		if(sql.toString().endsWith(",")) sql.deleteCharAt(sql.length() - 1 );
		sql.append("where id=?");
		argsList.add(courseModel.getId());
		if (argsList.size() > 1) {
			Object[] args = (Object[]) argsList.toArray(new Object[argsList.size()]);
			try {
				affectedRows = jdbcTemplate.update(sql.toString(), args);
			} catch (Exception e) {
				logger.debug("updateCourse, exception : {}", e.toString());
			}
		} else {
			affectedRows = -1;
		}
		return affectedRows != 0;
	}

	@Override
	public boolean praiseByCourseId(Long courseId) {
		logger.debug("args courseId : {}", courseId);
		String sql = "update course set praise_amount = praise_amount + 1 where id=?";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(sql, courseId);
		} catch (Exception e) {
			logger.debug("praiseByCourseId, exception : {}", e.toString());
		}
		return affectedRows != 0;
	}

	@Override
	public List<CourseModel> getCarefullyChosenCourse(String industryId, String fieldId, String stageId, int page, int amountPerPage) {
		List<CourseModel> courseList = null;
		StringBuilder sql = new StringBuilder();
		List<Object> argsList = new ArrayList<>();
		sql.append("select * from course where course_type=?");
		argsList.add(Constant.CourseType.CHOICENESS.value());
		processQueryParams(sql, argsList, industryId, fieldId, stageId);
		sql.append(" ORDER BY school_time DESC ");
		int limitBegin = page == 1 ? 0 : (page - 1) * amountPerPage;
		int limitEnd = limitBegin + amountPerPage;
		sql.append(" limit ?,? ");
		argsList.add(limitBegin);
		argsList.add(limitEnd);
		logger.debug("sql : {}", sql);
		try {
			Object[] args = (Object[]) argsList.toArray(new Object[argsList.size()]);
			courseList = jdbcTemplate.query(sql.toString(), args, new CourseModelMapper());
		} catch (Exception e) {
			logger.error("getCarefullyChosenCourse, exception : {}", e.toString());
		}
		return courseList;
	}

	@Override
	public List<CourseModel> getLatestCourse(String industryId, String fieldId, String stageId, int page, int amountPerPage) {
		List<CourseModel> courseList = null;
		StringBuilder sql = new StringBuilder();
		List<Object> argsList = new ArrayList<>();
		sql.append("select * from course where course_type=?");
		argsList.add(Constant.CourseType.COMMON.value());
		processQueryParams(sql, argsList, industryId, fieldId, stageId);
		sql.append(" ORDER BY school_time DESC ");
		int limitBegin = page == 1 ? 0 : (page - 1) * amountPerPage;
		int limitEnd = limitBegin + amountPerPage;
		sql.append(" limit ?,? ");
		argsList.add(limitBegin);
		argsList.add(limitEnd);
		logger.debug("sql : {}", sql);
		try {
			Object[] args = (Object[]) argsList.toArray(new Object[argsList.size()]);
			courseList = jdbcTemplate.query(sql.toString(), args, new CourseModelMapper());
		} catch (Exception e) {
			logger.error("getLatestCourse, exception : {}", e.toString());
		}
		return courseList;
	}

	@Override
	public List<CourseModel> getRecommendCourse(String industryId, String fieldId, String stageId, int page, int amountPerPage) {
		List<CourseModel> courseList = null;
		StringBuilder sql = new StringBuilder();
		List<Object> argsList = new ArrayList<>();
		sql.append("select * from course");
		Integer industryId_ = 0;
		try {
			industryId_ = Integer.valueOf(industryId);
		} catch(Exception ex) {
			logger.error(ex.toString());
		}
		if(industryId_ != 0) {
			sql.append(" where industry_id = ? ");
			argsList.add(industryId_);
		}
		sql.append(" ORDER BY school_time DESC ");
		int limitBegin = page == 1 ? 0 : (page - 1) * amountPerPage;
		int limitEnd = limitBegin + amountPerPage;
		sql.append(" limit ?,? ");
		argsList.add(limitBegin);
		argsList.add(limitEnd);
		logger.debug("sql : {}", sql);
		try {
			Object[] args = (Object[]) argsList.toArray(new Object[argsList.size()]);
			courseList = jdbcTemplate.query(sql.toString(), args, new CourseModelMapper());
		} catch (Exception e) {
			logger.error("getRecommendCourse, exception : {}", e.toString());
		}
		return courseList;
	}
	
	private void processQueryParams(StringBuilder sql, List<Object> argsList, String industryId, String fieldId, String stageId) {
		if (industryId != null && !industryId.isEmpty()) {
			String[] industry = industryId.split(",");
			sql.append(" and (");
			for(String id : industry) {
				Integer id_ = 0;
				try {
					id_ = Integer.valueOf(id);
				} catch (Exception ex) {
					logger.error(ex.toString());
					continue;
				}
				sql.append(" industry_id =? or ");
				argsList.add(id_);
			}
			if(sql.toString().endsWith("or ")) {
				sql.delete(sql.length() - 3, sql.length());
			}
			sql.append(")");
		}
		if (fieldId != null && !fieldId.isEmpty()) {
			String[] field = fieldId.split(",");
			sql.append(" and (");
			for(String id : field) {
				Integer id_ = 0;
				try {
					id_ = Integer.valueOf(id);
				} catch (Exception ex) {
					logger.error(ex.toString());
					continue;
				}
				sql.append(" field_id =? or ");
				argsList.add(id_);
			}
			if(sql.toString().endsWith("or ")) {
				sql.delete(sql.length() - 3, sql.length());
			}
			sql.append(")");
		}
		if (stageId != null && !stageId.isEmpty()) {
			String[] stage = stageId.split(",");
			sql.append(" and (");
			for(String id : stage) {
				Integer id_ = 0;
				try {
					id_ = Integer.valueOf(id);
				} catch (Exception ex) {
					logger.error(ex.toString());
					continue;
				}
				sql.append(" stage_id =? or ");
				argsList.add(id_);
			}
			if(sql.toString().endsWith("or ")) {
				sql.delete(sql.length() - 3, sql.length());
			}
			sql.append(")");
		}
	}

	@Override
	public boolean incListenerAmountForCourse(Long courseId) {
		logger.debug("args courseId : {}", courseId);
		String sql = "update course set enter_amount = enter_amount + 1 where id=?";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(sql, courseId);
		} catch (Exception e) {
			logger.debug("incListenerAmountForCourse, exception : {}", e.toString());
		}
		return affectedRows != 0;
	}
	
	public List<CourseModel> getNotifyCourseList() {
		List<CourseModel> courseList = null;
		String sql = "select * from course where school_time<? and notify_status='0'";
		String sqlUpdate = "update course set notify_status='1' where id=?";
		Timestamp ts1 = new Timestamp(System.currentTimeMillis() + System.currentTimeMillis() + 1000 * 60 * 10);
		try {
			courseList = jdbcTemplate.query(sql.toString(), new Object[] { ts1 }, new CourseModelMapper());
			if(courseList != null && courseList.size() != 0) {
				for(CourseModel cm : courseList) {
					jdbcTemplate.update(sqlUpdate, cm.getId());
				}
			}
		} catch (Exception e) {
			logger.error("getNotifyCourseList, exception : {}", e.toString());
		}
		return courseList;
	}
}
