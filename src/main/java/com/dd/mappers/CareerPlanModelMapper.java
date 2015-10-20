package com.dd.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dd.models.CareerPlanModel;

public class CareerPlanModelMapper implements RowMapper<CareerPlanModel> {
	public CareerPlanModel mapRow(ResultSet rs, int index) throws SQLException {
		CareerPlanModel u = new CareerPlanModel(rs.getInt("id"),
				rs.getString("year"), rs.getTimestamp("create_time"));
		return u;
	}
}