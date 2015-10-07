package com.dd.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dd.dao.IAreaDao;
import com.dd.mappers.AreaModelMapper;
import com.dd.models.AreaModel;

@Repository("areaDao")
public class AreaDaoImpl implements IAreaDao {

	private static final Logger logger = LoggerFactory.getLogger(AreaDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public AreaModel getAreaByAreaId(String areaId) {
		logger.debug("args areaId : {}", areaId);
		String sql = "select * from area where areaID = ?";
		AreaModel areaModel = null;
		try {
			areaModel = jdbcTemplate.queryForObject(sql, new Object[] { areaId }, new AreaModelMapper());
		} catch (Exception e) {
			logger.error("getAreaByAreaId, exception : {}", e.toString());
		}
		return areaModel;
	}

	@Override
	public List<AreaModel> getAllAreaByCityId(String cityId) {
		logger.debug("args cityId : {}", cityId);
		List<AreaModel> areaList = null;
		String sql = "select * from area where father = ?";
		try {
			areaList = jdbcTemplate.query(sql, new Object[] { cityId }, new AreaModelMapper());
		} catch (Exception e) {
			logger.error("getAllAreaByCityId, exception : {}", e.toString());
		}
		return areaList;
	}
}
