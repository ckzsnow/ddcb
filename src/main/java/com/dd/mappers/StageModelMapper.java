package com.dd.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dd.models.StageModel;

public class StageModelMapper implements RowMapper<StageModel> {
	public StageModel mapRow(ResultSet rs, int index) throws SQLException {
		StageModel u = new StageModel(rs.getInt("id"),
				rs.getString("name"), rs.getTimestamp("create_time"));
		return u;
	}
}