package com.dd.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dd.dao.IIndustryDao;
import com.dd.mappers.IndustryModelMapper;
import com.dd.models.IndustryModel;

@Repository("industryDao")
public class IndustryDaoImpl implements IIndustryDao {

	private static final Logger logger = LoggerFactory.getLogger(IndustryDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public IndustryModel getIndustryByIndustryId(int industryId) {
		logger.debug("args industryId : {}", industryId);
		String sql = "select * from industry where  id= ?";
		IndustryModel industryModel = null;
		try {
			industryModel = jdbcTemplate.queryForObject(sql, new Object[] { industryId }, new IndustryModelMapper());
		} catch (Exception e) {
			logger.error("getIndustryByIndustryId, exception : {}", e.toString());
		}
		return industryModel;
	}

	@Override
	public boolean addIndustry(IndustryModel industryModel) {
		logger.debug("args industryModel : {}", industryModel.toString());
		String sql = "insert into industry (name, create_time)" + " values (?, ?)";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(sql, industryModel.getName(),
					new Timestamp(System.currentTimeMillis()));
		} catch (Exception e) {
			logger.debug("addIndustry, exception : {}", e.toString());
		}
		return affectedRows != 0;
	}

	@Override
	public boolean deleteIndustryByIndustryId(int industryId) {
		logger.debug("args industryId : {}", industryId);
		String sql = "delete from industry where id = ?";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(sql, industryId);
		} catch (Exception e) {
			logger.debug("deleteIndustryByIndustryId, exception : {}", e.toString());
		}
		return affectedRows != 0;
	}

	@Override
	public boolean updateIndustryNameByIndustryId(String industryName, int industryId) {
		logger.debug("args industryName : {}, industryId : {}", industryName, industryId);
		String sql = "update industry set name=? where id=?";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(sql, industryName, industryId);
		} catch (Exception e) {
			logger.debug("updateIndustryNameByIndustryId, exception : {}", e.toString());
		}
		return affectedRows != 0;
	}

	@Override
	public List<IndustryModel> getAllIndustry() {
		List<IndustryModel> industryList = null;
		String sql = "select * from industry";
		try {
			industryList = jdbcTemplate.query(sql, new IndustryModelMapper());
		} catch (Exception e) {
			logger.error("getAllIndustry, exception : {}", e.toString());
		}
		return industryList;
	}
}
