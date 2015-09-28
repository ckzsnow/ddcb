package com.dd.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dd.models.FieldModel;

public class FieldModelMapper implements RowMapper<FieldModel> {
	public FieldModel mapRow(ResultSet rs, int index) throws SQLException {
		FieldModel u = new FieldModel(rs.getInt("id"),
				rs.getInt("industry_id"), rs.getString("field_name"), rs.getTimestamp("create_time"));
		return u;
	}
}