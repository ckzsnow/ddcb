package com.dd.dao.impl;

import java.sql.Timestamp;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dd.constant.Constant;
import com.dd.constant.Constant.UserType;
import com.dd.dao.IUserCourseDao;
import com.dd.mappers.CourseModelMapper;
import com.dd.mappers.UserCourseModelMapper;
import com.dd.models.CourseModel;
import com.dd.models.UserCourseModel;

@Repository("userCourseDao")
public class UserCourseDaoImpl implements IUserCourseDao {

	private static final Logger logger = LoggerFactory.getLogger(UserCourseDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<UserCourseModel> getUserCourseByUserIdAndUserType(String userId, UserType userType, int page,
			int amountPerPage) {
		logger.debug("args userId : {}, userType : {}, page : {}, amountPerPage : {}", userId, userType, page,
				amountPerPage);
		int limitBegin = page == 1 ? 0 : (page - 1) * amountPerPage;
		int limitEnd = limitBegin + amountPerPage;
		String sql = "select * from user_course where user_id=? and user_type=? limit ? , ?";
		List<UserCourseModel> userCourseList = null;
		try {
			userCourseList = jdbcTemplate.query(sql, new Object[] { userId, userType.value(), limitBegin, limitEnd },
					new UserCourseModelMapper());
		} catch (Exception e) {
			logger.debug("getUserCourseByUserIdAndUserType, exception : {}", e.toString());
		}
		return userCourseList;
	}

	@Override
	public Integer getUserAmountForCourse(Long courseId) {
		logger.debug("args courseId : {}", courseId);
		String sql = "select count(*) from user_course where course_id=? and user_type=?";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.queryForObject(sql, new Object[] { courseId, UserType.LISTEN.value() },
					Integer.class);
		} catch (Exception e) {
			logger.debug("getUserAmountForCourse, exception : {}", e.toString());
		}
		return affectedRows;
	}

	@Override
	public boolean addUserCourse(UserCourseModel userCourseModel) {
		logger.debug("args userCourseModel : {}", userCourseModel.toString());
		String checkSql = "select * from user_course where user_id=? and course_id = ? and user_type=?";
		String sql = "insert into user_course (user_id, course_id, user_type, create_time)" + " values (?, ?, ?, ?)";
		List<UserCourseModel> userCourseList = null;
		int affectedRows = 0;
		try {
			userCourseList = jdbcTemplate.query(checkSql, new Object[] { userCourseModel.getUserId(),
					userCourseModel.getCourseId(), userCourseModel.getUserType() }, new UserCourseModelMapper());
			if (userCourseList == null || userCourseList.isEmpty()) {
				affectedRows = jdbcTemplate.update(sql, userCourseModel.getUserId(), userCourseModel.getCourseId(),
						userCourseModel.getUserType(), new Timestamp(System.currentTimeMillis()));
			} else {
				affectedRows = 1;
			}
		} catch (Exception e) {
			logger.debug("addUserCourse, exception : {}", e.toString());
		}
		return affectedRows != 0;
	}

	@Override
	public boolean deleteUserCourseByUserIdAndCourseIdAndUserType(String userId, Long courseId, UserType userType) {
		logger.debug("args userId : {}, courseId : {}", userId, courseId);
		String sql = "delete from user_course where user_id = ? and course_id = ? and user_type=?";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(sql, userId, courseId, userType.value());
			affectedRows = affectedRows == 0 ? 1 : affectedRows;
		} catch (Exception e) {
			logger.debug("deleteUserCourseByUserIdAndCourseIdAndUserType, exception : {}", e.toString());
		}
		return affectedRows != 0;
	}

	@Override
	public List<UserCourseModel> getUserCourseByCourseIdAndUserType(Long courseId, UserType userType, int page,
			int amountPerPage) {
		logger.debug("args courseId : {}, userType : {}, page : {}, amountPerPage : {}", courseId, userType, page,
				amountPerPage);
		int limitBegin = page == 1 ? 0 : (page - 1) * amountPerPage;
		int limitEnd = limitBegin + amountPerPage;
		String sql = "select * from user_course where course_id=? and user_type=? limit ? , ?";
		List<UserCourseModel> userCourseList = null;
		try {
			userCourseList = jdbcTemplate.query(sql, new Object[] { courseId, userType.value(), limitBegin, limitEnd },
					new UserCourseModelMapper());
		} catch (Exception e) {
			logger.debug("getUserCourseByUserIdAndUserType, exception : {}", e.toString());
		}
		return userCourseList;
	}

	@Override
	public boolean userIsEnterCourseByUserIdAndCourseIdAndUserType(String userId, Long courseId) {
		logger.debug("args courseId : {}", courseId);
		String sql = "select count(*) from user_course where user_id=? and course_id=? and user_type=?";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.queryForObject(sql, new Object[] { userId, courseId, UserType.LISTEN.value() },
					Integer.class);
		} catch (Exception e) {
			logger.debug("userIsEnterCourseByUserIdAndCourseIdAndUserType, exception : {}", e.toString());
		}
		return affectedRows != 0;
	}
	
	@Override
	public boolean getUserCourse(String userId, Long courseId, UserType userType) {
		logger.debug("args courseId : {}", courseId);
		String sql = "select count(*) from user_course where user_id=? and course_id=? and user_type=?";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.queryForObject(sql, new Object[] { userId, courseId, userType.value() },
					Integer.class);
		} catch (Exception e) {
			logger.debug("getUserCourse, exception : {}", e.toString());
		}
		return affectedRows != 0;
	}

	@Override
	public boolean deleteUserCourseByCourseId(Long courseId) {
		logger.debug("args courseId : {}", courseId);
		String sql = "delete from user_course where course_id = ?";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(sql, courseId);
			affectedRows = affectedRows == 0 ? 1 : affectedRows;
		} catch (Exception e) {
			logger.debug("deleteUserCourseByCourseId, exception : {}", e.toString());
		}
		return affectedRows != 0;
	}

	@Override
	public boolean updateUserId(String userId, String newUserId) {
		logger.debug("args userId : {}, newUserId : {}", userId, newUserId);
		String sql = "update user_course set user_id=? where user_id=?";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(sql, newUserId, userId);
		} catch (Exception e) {
			logger.debug("updateUserId, exception : {}", e.toString());
		}
		return affectedRows != 0;
	}

	@Override
	public List<CourseModel> getOngoingSubscribeCourse(String userId, int page, int amountPerPage) {
		logger.debug("args userId : {}, page : {}, amountPerPage : {}", userId, page, amountPerPage);
		int limitBegin = page == 1 ? 0 : (page - 1) * amountPerPage;
		int limitEnd = limitBegin + amountPerPage;
		String sql = "select * from course a inner join user_course b on a.id=b.course_id and b.user_id=? and b.user_type=? and a.school_time>=? ORDER BY school_time DESC limit ? , ?";
		List<CourseModel> courseModelList = null;
		try {
			courseModelList = jdbcTemplate.query(sql, new Object[] { userId, Constant.UserType.LISTEN.value(), new Timestamp(System.currentTimeMillis()).toString(), limitBegin, limitEnd },
					new CourseModelMapper());
		} catch (Exception e) {
			logger.debug("getOngoingSubscribeCourse, exception : {}", e.toString());
		}
		return courseModelList;
	}

	@Override
	public List<CourseModel> getFinishedSubscribeCourse(String userId, int page, int amountPerPage) {
		logger.debug("args userId : {}, page : {}, amountPerPage : {}", userId, page, amountPerPage);
		int limitBegin = page == 1 ? 0 : (page - 1) * amountPerPage;
		int limitEnd = limitBegin + amountPerPage;
		String sql = "select * from course a inner join user_course b on a.id=b.course_id and b.user_id=? and b.user_type=? and a.school_time<? ORDER BY school_time DESC limit ? , ?";
		List<CourseModel> courseModelList = null;
		try {
			courseModelList = jdbcTemplate.query(sql, new Object[] { userId, Constant.UserType.LISTEN.value(), new Timestamp(System.currentTimeMillis()).toString(), limitBegin, limitEnd },
					new CourseModelMapper());
		} catch (Exception e) {
			logger.debug("getFinishedSubscribeCourse, exception : {}", e.toString());
		}
		return courseModelList;
	}

	@Override
	public List<CourseModel> getOngoingPublishCourse(String userId, int page, int amountPerPage) {
		logger.debug("args userId : {}, page : {}, amountPerPage : {}", userId, page, amountPerPage);
		int limitBegin = page == 1 ? 0 : (page - 1) * amountPerPage;
		int limitEnd = limitBegin + amountPerPage;
		String sql = "select * from course a inner join user_course b on a.id=b.course_id and b.user_id=? and b.user_type=? and a.school_time>=? ORDER BY school_time DESC limit ? , ?";
		List<CourseModel> courseModelList = null;
		try {
			courseModelList = jdbcTemplate.query(sql, new Object[] { userId, Constant.UserType.TEACH.value(), new Timestamp(System.currentTimeMillis()).toString(), limitBegin, limitEnd },
					new CourseModelMapper());
		} catch (Exception e) {
			logger.debug("getOngoingPublishCourse, exception : {}", e.toString());
		}
		return courseModelList;
	}

	@Override
	public List<CourseModel> getFinishedPublishCourse(String userId, int page, int amountPerPage) {
		logger.debug("args userId : {}, page : {}, amountPerPage : {}", userId, page, amountPerPage);
		int limitBegin = page == 1 ? 0 : (page - 1) * amountPerPage;
		int limitEnd = limitBegin + amountPerPage;
		String sql = "select * from course a inner join user_course b on a.id=b.course_id and b.user_id=? and b.user_type=? and a.school_time<? ORDER BY school_time DESC limit ? , ?";
		List<CourseModel> courseModelList = null;
		try {
			courseModelList = jdbcTemplate.query(sql, new Object[] { userId, Constant.UserType.TEACH.value(), new Timestamp(System.currentTimeMillis()).toString(), limitBegin, limitEnd },
					new CourseModelMapper());
		} catch (Exception e) {
			logger.debug("getFinishedPublishCourse, exception : {}", e.toString());
		}
		return courseModelList;
	}

	@Override
	public List<CourseModel> getPublishCourse(String userId, int page, int amountPerPage) {
		logger.debug("args userId : {}, page : {}, amountPerPage : {}", userId, page, amountPerPage);
		int limitBegin = page == 1 ? 0 : (page - 1) * amountPerPage;
		int limitEnd = limitBegin + amountPerPage;
		String sql = "select * from course a inner join user_course b on a.id=b.course_id and b.user_id=? and b.user_type=? ORDER BY school_time DESC limit ? , ?";
		List<CourseModel> courseModelList = null;
		try {
			courseModelList = jdbcTemplate.query(sql, new Object[] { userId, Constant.UserType.TEACH.value(), limitBegin, limitEnd },
					new CourseModelMapper());
		} catch (Exception e) {
			logger.debug("getPublishCourse, exception : {}", e.toString());
		}
		return courseModelList;
	}

}
