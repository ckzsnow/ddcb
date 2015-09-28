package com.dd.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dd.models.WorkYearModel;

public class WorkYearModelMapper implements RowMapper<WorkYearModel> {
	public WorkYearModel mapRow(ResultSet rs, int index) throws SQLException {
		WorkYearModel u = new WorkYearModel(rs.getInt("id"),
				rs.getString("year"), rs.getTimestamp("create_time"));
		return u;
	}
}