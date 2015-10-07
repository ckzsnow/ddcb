package com.dd.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dd.dao.ICityDao;
import com.dd.mappers.CityModelMapper;
import com.dd.models.CityModel;

@Repository("cityDao")
public class CityDaoImpl implements ICityDao {

	private static final Logger logger = LoggerFactory.getLogger(CityDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public CityModel getCityByCityId(String cityId) {
		logger.debug("args cityId : {}", cityId);
		String sql = "select * from city where cityID = ?";
		CityModel cityModel = null;
		try {
			cityModel = jdbcTemplate.queryForObject(sql, new Object[] { cityId }, new CityModelMapper());
		} catch (Exception e) {
			logger.error("getCityByCityId, exception : {}", e.toString());
		}
		return cityModel;
	}

	@Override
	public List<CityModel> getAllCityByProvinceId(String provinceId) {
		logger.debug("args provinceId : {}", provinceId);
		List<CityModel> cityList = null;
		String sql = "select * from city where father = ?";
		try {
			cityList = jdbcTemplate.query(sql, new Object[] { provinceId }, new CityModelMapper());
		} catch (Exception e) {
			logger.error("getAllCityByProvinceId, exception : {}", e.toString());
		}
		return cityList;
	}
}
