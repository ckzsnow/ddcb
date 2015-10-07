package com.dd.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dd.dao.IWorkYearDao;
import com.dd.mappers.WorkYearModelMapper;
import com.dd.models.WorkYearModel;

@Repository("workYearDao")
public class WorkYearDaoImpl implements IWorkYearDao {

	private static final Logger logger = LoggerFactory.getLogger(WorkYearDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public WorkYearModel getWorkYearById(int id) {
		logger.debug("args id : {}", id);
		String sql = "select * from work_year where id = ?";
		WorkYearModel workYearModel = null;
		try {
			workYearModel = jdbcTemplate.queryForObject(sql, new Object[] { id }, new WorkYearModelMapper());
		} catch (Exception e) {
			logger.error("getWorkYearById, exception : {}", e.toString());
		}
		return workYearModel;
	}

	@Override
	public List<WorkYearModel> getAllWorkYear() {
		List<WorkYearModel> areaList = null;
		String sql = "select * from work_year";
		try {
			areaList = jdbcTemplate.query(sql, new WorkYearModelMapper());
		} catch (Exception e) {
			logger.error("getAllWorkYear, exception : {}", e.toString());
		}
		return areaList;
	}
}
