package com.dd.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dd.models.UserProfileModel;

public class UserProfileModelMapper implements RowMapper<UserProfileModel> {
	public UserProfileModel mapRow(ResultSet rs, int index) throws SQLException {
		UserProfileModel u = new UserProfileModel(rs.getString("user_id"),
				rs.getString("name"), rs.getString("nick_name"), rs.getString("photo"), rs.getString("visit_card"),
				rs.getInt("sex"),
				rs.getInt("industry_id"), rs.getString("company"),
				rs.getString("position"), rs.getInt("work_year_id"), rs.getInt("career_plan_id"), 
				rs.getString("province_id"), rs.getString("city_id"),
				rs.getString("area_id"), rs.getString("resume"),
				rs.getTimestamp("create_time"));
		return u;
	}
}