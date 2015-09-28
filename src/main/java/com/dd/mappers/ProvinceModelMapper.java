package com.dd.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dd.models.ProvinceModel;

public class ProvinceModelMapper  implements RowMapper<ProvinceModel> {
	public ProvinceModel mapRow(ResultSet rs, int index) throws SQLException {
		ProvinceModel u = new ProvinceModel(rs.getInt("id"), rs.getString("provinceID"),
				rs.getString("province"));
		return u;
	}
}