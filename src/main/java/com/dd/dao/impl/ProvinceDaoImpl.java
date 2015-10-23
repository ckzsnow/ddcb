package com.dd.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dd.dao.IProvinceDao;
import com.dd.mappers.ProvinceModelMapper;
import com.dd.models.ProvinceModel;

@Repository("provinceDao")
public class ProvinceDaoImpl implements IProvinceDao {

	private static final Logger logger = LoggerFactory.getLogger(ProvinceDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public ProvinceModel getProvinceByProvinceId(String provinceId) {
		logger.debug("args provinceId : {}", provinceId);
		String sql = "select * from province where provinceID = ?";
		ProvinceModel cityModel = null;
		try {
			cityModel = jdbcTemplate.queryForObject(sql, new Object[] { provinceId }, new ProvinceModelMapper());
		} catch (Exception e) {
			logger.error("getProvinceByProvinceId, exception : {}", e.toString());
		}
		return cityModel;
	}

	@Override
	public List<ProvinceModel> getAllProvince() {
		List<ProvinceModel> provinceList = null;
		String sql = "select * from province";
		try {
			provinceList = jdbcTemplate.query(sql, new ProvinceModelMapper());
		} catch (Exception e) {
			logger.error("getAllProvince, exception : {}", e.toString());
		}
		return provinceList;
	}
}
