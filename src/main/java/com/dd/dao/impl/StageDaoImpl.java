package com.dd.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dd.dao.IStageDao;
import com.dd.mappers.StageModelMapper;
import com.dd.models.StageModel;

@Repository("stageDao")
public class StageDaoImpl implements IStageDao {

	private static final Logger logger = LoggerFactory.getLogger(StageDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public StageModel getStageByStageId(int stageId) {
		logger.debug("args stageId : {}", stageId);
		String sql = "select * from stage where  id= ?";
		StageModel stageModel = null;
		try {
			stageModel = jdbcTemplate.queryForObject(sql, new Object[] { stageId }, new StageModelMapper());
		} catch (Exception e) {
			logger.error("getStageByStageId, exception : {}", e.toString());
		}
		return stageModel;
	}

	@Override
	public boolean addStage(StageModel stageModel) {
		logger.debug("args stageModel : {}", stageModel.toString());
		String sql = "insert into stage (name, create_time)" + " values (?, ?)";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(sql,
					stageModel.getName(), new Timestamp(System.currentTimeMillis()));
		} catch (Exception e) {
			logger.debug("addStage, exception : {}", e.toString());
		}
		return affectedRows != 0;
	}

	@Override
	public boolean deleteStageByStageId(int stageId) {
		logger.debug("args stageId : {}", stageId);
		String sql = "delete from stage where id = ?";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(sql, stageId);
		} catch (Exception e) {
			logger.debug("deleteStageByStageId, exception : {}", e.toString());
		}
		return affectedRows != 0;
	}

	@Override
	public boolean updateStageNameByStageId(String stageName, int stageId) {
		logger.debug("args stageName : {}, stageId : {}", stageName, stageId);
		String sql = "update stage set name=? where id=?";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(sql, stageName, stageId);
		} catch (Exception e) {
			logger.debug("updateStageNameByStageId, exception : {}", e.toString());
		}
		return affectedRows != 0;
	}

	@Override
	public List<StageModel> getAllStage() {
		List<StageModel> stageList = null;
		String sql = "select * from stage";
		try {
			stageList = jdbcTemplate.query(sql, new StageModelMapper());
		} catch (Exception e) {
			logger.error("getAllStage, exception : {}", e.toString());
		}
		return stageList;
	}

}
