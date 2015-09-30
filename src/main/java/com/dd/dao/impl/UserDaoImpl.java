package com.dd.dao.impl;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dd.dao.IUserDao;
import com.dd.mappers.UserModelMapper;
import com.dd.models.UserModel;

@Repository("userDao")
public class UserDaoImpl implements IUserDao {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public UserModel getUserByUserId(String userId) {
		String sql = "select * from user where user_id = ?";
		UserModel userModel = null;
		try {
			userModel = jdbcTemplate.queryForObject(sql, new Object[] { userId }, new UserModelMapper());
		} catch (Exception e) {
			logger.error("getUserByUserId, exception : {}", e.toString());
		}
		return userModel;
	}

	@Override
	public boolean addUser(UserModel userModel) {
		String sql = "insert into user (user_id, pwd, create_time)" + " values (?, ?, ?)";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(sql, userModel.getUserId(), userModel.getPwd(),
					userModel.getCreateTime() == null ? new Timestamp(System.currentTimeMillis())
							: userModel.getCreateTime());
		} catch (Exception e) {
			logger.debug("addUser, exception : {}", e.toString());
		}
		return affectedRows != 0;
	}

	@Override
	public boolean deleteUserByUserId(String userId) {
		String sql = "delete from user where user_id = ?";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(sql, userId);
		} catch (Exception e) {
			logger.debug("deleteUserByUserId, exception : {}", e.toString());
		}
		return affectedRows != 0;
	}

	@Override
	public boolean updateUserPwdByUserId(String pwd, String userId) {
		String sql = "update user set pwd=? where user_id=?";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(sql, pwd, userId);
		} catch (Exception e) {
			logger.debug("updateUserPwdByUserId, exception : {}", e.toString());
		}
		return affectedRows != 0;
	}

}
