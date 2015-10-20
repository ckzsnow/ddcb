package com.dd.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dd.dao.ICareerPlanDao;
import com.dd.mappers.CareerPlanModelMapper;
import com.dd.models.CareerPlanModel;

@Repository("careerPlanDao")
public class CareerPlanDaoImpl implements ICareerPlanDao {

	private static final Logger logger = LoggerFactory.getLogger(CareerPlanDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public CareerPlanModel getCareerPlanById(int id) {
		logger.debug("args id : {}", id);
		String sql = "select * from career_plan where id = ?";
		CareerPlanModel careerPlanModel = null;
		try {
			careerPlanModel = jdbcTemplate.queryForObject(sql, new Object[] { id }, new CareerPlanModelMapper());
		} catch (Exception e) {
			logger.error("getCareerPlanById, exception : {}", e.toString());
		}
		return careerPlanModel;
	}

	@Override
	public List<CareerPlanModel> getAllCareerPlan() {
		List<CareerPlanModel> careerPlanList = null;
		String sql = "select * from career_plan";
		try {
			careerPlanList = jdbcTemplate.query(sql, new CareerPlanModelMapper());
		} catch (Exception e) {
			logger.error("getAllCareerPlan, exception : {}", e.toString());
		}
		return careerPlanList;
	}
}
