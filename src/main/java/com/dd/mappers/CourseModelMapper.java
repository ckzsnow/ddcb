package com.dd.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.dd.models.CourseModel;

public class CourseModelMapper implements RowMapper<CourseModel> {
	public CourseModel mapRow(ResultSet rs, int index) throws SQLException {
		CourseModel u = new CourseModel(rs.getLong("id"), rs.getString("name"),
				rs.getString("brief"), rs.getString("details"), rs.getInt("industry_id"),
				rs.getInt("field_id"), rs.getInt("stage_id"), rs.getTimestamp("school_time"),
				rs.getString("doc_attatch"), rs.getString("voice_attatch"),
				rs.getInt("course_type"), rs.getInt("audit_status"),
				rs.getTimestamp("create_time"));
		return u;
	}
}