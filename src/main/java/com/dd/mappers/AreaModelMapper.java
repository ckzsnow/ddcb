package com.dd.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dd.models.AreaModel;

public class AreaModelMapper implements RowMapper<AreaModel> {
	public AreaModel mapRow(ResultSet rs, int index) throws SQLException {
		AreaModel u = new AreaModel(rs.getInt("id"), rs.getString("areaID"),
				rs.getString("area"), rs.getString("father"));
		return u;
	}
}
