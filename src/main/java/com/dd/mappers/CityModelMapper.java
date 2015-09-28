package com.dd.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dd.models.CityModel;

public class CityModelMapper  implements RowMapper<CityModel> {
	public CityModel mapRow(ResultSet rs, int index) throws SQLException {
		CityModel u = new CityModel(rs.getInt("id"), rs.getString("cityID"),
				rs.getString("city"), rs.getString("father"));
		return u;
	}
}