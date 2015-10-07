package com.dd.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dd.constant.Constant.UserType;
import com.dd.dao.IUserCourseDao;
import com.dd.mappers.UserCourseModelMapper;
import com.dd.models.UserCourseModel;

@Repository("userCourseDao")
public class UserCourseDaoImpl implements IUserCourseDao {

	private static final Logger logger = LoggerFactory.getLogger(UserCourseDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<UserCourseModel> getUserCourseByUserIdAndUserType(String userId, UserType userType, int page,
			int amountPerPage) {
		logger.debug("args userId : {}, userType : {}, page : {}, amountPerPage : {}", userId, userType, page, amountPerPage);
		int limitBegin = page == 1 ? 0 : (page-1)*amountPerPage - 1;
		int limitEnd = limitBegin + amountPerPage;
		String sql = "select * from user_course where user_id=? and user_type=? limit ? , ?";
		List<UserCourseModel> userCourseList = null;
		try {
			userCourseList = jdbcTemplate.query(sql, new Object[] { userId, userType, limitBegin, limitEnd }, new UserCourseModelMapper());
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
			affectedRows = jdbcTemplate.queryForObject(sql, new Object[] { courseId, UserType.LISTEN }, Integer.class);
		} catch (Exception e) {
			logger.debug("getUserAmountForCourse, exception : {}", e.toString());
		}
		return affectedRows;
	}

	@Override
	public boolean addUserCourse(UserCourseModel userCourseModel) {
		logger.debug("args userCourseModel : {}", userCourseModel.toString());
		String sql = "insert into user_course (user_id, course_id, user_type, create_time)" + " values (?, ?, ?, ?)";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(sql, userCourseModel.getUserId(),
					userCourseModel.getCourseId(), userCourseModel.getUserType(),
					new Timestamp(System.currentTimeMillis()));
		} catch (Exception e) {
			logger.debug("addUserCourse, exception : {}", e.toString());
		}
		return affectedRows != 0;
	}

	@Override
	public boolean deleteUserCourseByUserIdAndCourseId(String userId, Long courseId) {
		logger.debug("args userId : {}, courseId : {}", userId, courseId);
		String sql = "delete from user_course where user_id = ? and course_id = ?";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(sql, userId, courseId);
		} catch (Exception e) {
			logger.debug("deleteUserCourseByUserIdAndCourseId, exception : {}", e.toString());
		}
		return affectedRows != 0;
	}

	@Override
	public List<UserCourseModel> getUserCourseByCourseIdAndUserType(Long courseId, UserType userType, int page,
			int amountPerPage) {
		logger.debug("args courseId : {}, userType : {}, page : {}, amountPerPage : {}", courseId, userType, page, amountPerPage);
		int limitBegin = page == 1 ? 0 : (page-1)*amountPerPage - 1;
		int limitEnd = limitBegin + amountPerPage;
		String sql = "select * from user_course where course_id=? and user_type=? limit ? , ?";
		List<UserCourseModel> userCourseList = null;
		try {
			userCourseList = jdbcTemplate.query(sql, new Object[] { courseId, userType, limitBegin, limitEnd }, new UserCourseModelMapper());
		} catch (Exception e) {
			logger.debug("getUserCourseByUserIdAndUserType, exception : {}", e.toString());
		}
		return userCourseList;
	}

}
