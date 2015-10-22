package com.dd.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dd.models.IndustryModel;

public class IndustryModelMapper implements RowMapper<IndustryModel> {
	public IndustryModel mapRow(ResultSet rs, int index) throws SQLException {
		IndustryModel u = new IndustryModel(rs.getInt("id"),
				rs.getString("name"), rs.getString("icon"), rs.getTimestamp("create_time"));
		return u;
	}
}