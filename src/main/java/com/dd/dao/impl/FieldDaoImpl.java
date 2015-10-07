package com.dd.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dd.dao.IFieldDao;
import com.dd.mappers.FieldModelMapper;
import com.dd.models.FieldModel;

@Repository("fieldDao")
public class FieldDaoImpl implements IFieldDao {

	private static final Logger logger = LoggerFactory.getLogger(FieldDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public FieldModel getFieldByFieldId(int fieldId) {
		logger.debug("args fieldId : {}", fieldId);
		String sql = "select * from field where  id= ?";
		FieldModel fieldModel = null;
		try {
			fieldModel = jdbcTemplate.queryForObject(sql, new Object[] { fieldId }, new FieldModelMapper());
		} catch (Exception e) {
			logger.error("getFieldByFieldId, exception : {}", e.toString());
		}
		return fieldModel;
	}

	@Override
	public boolean addField(FieldModel fieldModel) {
		logger.debug("args fieldModel : {}", fieldModel.toString());
		String sql = "insert into field (industry_id, name, create_time)" + " values (?, ?, ?)";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(sql, fieldModel.getIndustryId(),
					fieldModel.getName(), new Timestamp(System.currentTimeMillis()));
		} catch (Exception e) {
			logger.debug("addField, exception : {}", e.toString());
		}
		return affectedRows != 0;
	}

	@Override
	public boolean deleteFieldByFieldId(int fieldId) {
		logger.debug("args fieldId : {}", fieldId);
		String sql = "delete from field where id = ?";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(sql, fieldId);
		} catch (Exception e) {
			logger.debug("deleteFieldByFieldId, exception : {}", e.toString());
		}
		return affectedRows != 0;
	}

	@Override
	public boolean updateFieldNameByFieldId(String fieldName, int fieldId) {
		logger.debug("args fieldName : {}, fieldId : {}", fieldName, fieldId);
		String sql = "update field set name=? where id=?";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(sql, fieldName, fieldId);
		} catch (Exception e) {
			logger.debug("updateUserPwdByUserId, exception : {}", e.toString());
		}
		return affectedRows != 0;
	}

	@Override
	public List<FieldModel> getFieldByIndustryId(int industryId) {
		logger.debug("args industryId : {}", industryId);
		List<FieldModel> fieldList = null;
		String sql = "select * from field where industry_id = ?";
		try {
			fieldList = jdbcTemplate.query(sql, new Object[] { industryId }, new FieldModelMapper());
		} catch (Exception e) {
			logger.error("getFieldByIndustryId, exception : {}", e.toString());
		}
		return fieldList;
	}

}
