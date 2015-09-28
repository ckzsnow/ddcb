package com.dd.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dd.models.UserModel;

public class UserModelMapper implements RowMapper<UserModel> {
	public UserModel mapRow(ResultSet rs, int index) throws SQLException {
		UserModel u = new UserModel(rs.getLong("id"),
				rs.getString("user_id"), rs.getString("pwd"),
				rs.getTimestamp("create_time"));
		return u;
	}
}