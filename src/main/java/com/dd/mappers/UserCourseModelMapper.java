package com.dd.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dd.models.UserCourseModel;

public class UserCourseModelMapper implements RowMapper<UserCourseModel> {
	public UserCourseModel mapRow(ResultSet rs, int index) throws SQLException {
		UserCourseModel u = new UserCourseModel(rs.getLong("id"),
				rs.getString("user_id"), rs.getLong("course_id"),
				rs.getInt("user_type"), rs.getTimestamp("create_time"));
		return u;
	}
}