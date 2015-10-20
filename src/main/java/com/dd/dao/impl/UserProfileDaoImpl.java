package com.dd.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dd.dao.IUserProfileDao;
import com.dd.mappers.UserProfileModelMapper;
import com.dd.models.UserProfileModel;

@Repository("userProfileDao")
public class UserProfileDaoImpl implements IUserProfileDao {

	private static final Logger logger = LoggerFactory.getLogger(UserProfileDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public UserProfileModel getUserProfileByUserId(String userId) {
		logger.debug("args userId : {}", userId);
		String sql = "select * from user_profile where user_id = ?";
		UserProfileModel userProfileModel = null;
		try {
			userProfileModel = jdbcTemplate.queryForObject(sql, new Object[] { userId }, new UserProfileModelMapper());
		} catch (Exception e) {
			logger.error("getUserProfileByUserId, exception : {}", e.toString());
		}
		return userProfileModel;
	}

	@Override
	public boolean addUserProfile(UserProfileModel userProfileModel) {
		logger.debug("args userProfileModel : {}", userProfileModel.toString());
		String sql = "insert into user_profile (user_id, name, photo, visit_card, sex, industry_id, company, position, work_year_id, career_plan_id, province_id, city_id, area_id, resume, create_time)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(sql, userProfileModel.getUserId(), userProfileModel.getUserName(),
					userProfileModel.getUserPhoto(), userProfileModel.getUserVisitCard(), userProfileModel.getUserSex(), userProfileModel.getIndustryId(),
					userProfileModel.getCompanyName(), userProfileModel.getCompanyPosition(),
					userProfileModel.getWorkYearId(), userProfileModel.getCareerPlanId(), userProfileModel.getProvinceId(), userProfileModel.getCityId(),
					userProfileModel.getAreaId(), userProfileModel.getResume(),
					new Timestamp(System.currentTimeMillis()));
		} catch (Exception e) {
			logger.debug("addUserProfile, exception : {}", e.toString());
		}
		return affectedRows != 0;
	}

	@Override
	public boolean deleteUserProfileByUserId(String userId) {
		logger.debug("args userId : {}", userId);
		String sql = "delete from user_profile where user_id = ?";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(sql, userId);
			affectedRows = affectedRows == 0 ? 1 : affectedRows;
		} catch (Exception e) {
			logger.debug("deleteUserProfileByUserId, exception : {}", e.toString());
		}
		return affectedRows != 0;
	}

	@Override
	public boolean updateUserProfile(UserProfileModel userProfileModel) {
		logger.debug("args userProfileModel : {}", userProfileModel.toString());
		int affectedRows = 0;
		StringBuilder sql = new StringBuilder();
		List<Object> argsList = new ArrayList<>();
		sql.append("update user_profile set ");
		if (userProfileModel.getUserName() != null && !userProfileModel.getUserName().isEmpty()) {
			sql.append("name=? ,");
			argsList.add(userProfileModel.getUserName());
		}
		if (userProfileModel.getUserPhoto() != null && !userProfileModel.getUserPhoto().isEmpty()) {
			sql.append("photo=? ,");
			argsList.add(userProfileModel.getUserPhoto());
		}
		if (userProfileModel.getUserVisitCard() != null && !userProfileModel.getUserVisitCard().isEmpty()) {
			sql.append("visit_card=? ,");
			argsList.add(userProfileModel.getUserVisitCard());
		}
		if (userProfileModel.getUserSex() != null) {
			sql.append("sex=? ,");
			argsList.add(userProfileModel.getUserSex());
		}
		if (userProfileModel.getIndustryId() != null) {
			sql.append("industry_id=? ,");
			argsList.add(userProfileModel.getIndustryId());
		}
		if (userProfileModel.getCompanyName() != null && !userProfileModel.getCompanyName().isEmpty()) {
			sql.append("company=? ,");
			argsList.add(userProfileModel.getCompanyName());
		}
		if (userProfileModel.getCompanyPosition() != null && !userProfileModel.getCompanyPosition().isEmpty()) {
			sql.append("position=? ,");
			argsList.add(userProfileModel.getCompanyPosition());
		}
		if (userProfileModel.getWorkYearId() != null) {
			sql.append("work_year_id=? ,");
			argsList.add(userProfileModel.getWorkYearId());
		}
		if (userProfileModel.getCareerPlanId() != null) {
			sql.append("career_plan_id=? ,");
			argsList.add(userProfileModel.getCareerPlanId());
		}
		if (userProfileModel.getProvinceId() != null && !userProfileModel.getProvinceId().isEmpty()) {
			sql.append("province_id=? ,");
			argsList.add(userProfileModel.getProvinceId());
		}
		if (userProfileModel.getCityId() != null && !userProfileModel.getCityId().isEmpty()) {
			sql.append("city_id=? ,");
			argsList.add(userProfileModel.getCityId());
		}
		if (userProfileModel.getAreaId() != null && !userProfileModel.getAreaId().isEmpty()) {
			sql.append("area_id=? ,");
			argsList.add(userProfileModel.getAreaId());
		}
		if (userProfileModel.getResume() != null && !userProfileModel.getResume().isEmpty()) {
			sql.append("resume=? ,");
			argsList.add(userProfileModel.getResume());
		}
		if (argsList.size() == 0) {
			logger.debug("valid args amount is 0, failed in updating.");
			return false;
		}
		if (userProfileModel.getUserId() == null || userProfileModel.getUserId().isEmpty()) {
			logger.debug("userId is invalid, failed in updating.");
			return false;
		}
		if (sql.toString().endsWith(","))
			sql.deleteCharAt(sql.length() - 1);
		sql.append("where user_id=?");
		argsList.add(userProfileModel.getUserId());
		if (argsList.size() > 1) {
			Object[] args = (Object[]) argsList.toArray(new Object[argsList.size()]);
			try {
				affectedRows = jdbcTemplate.update(sql.toString(), args);
			} catch (Exception e) {
				logger.debug("updateUserProfile, exception : {}", e.toString());
			}
		} else {
			affectedRows = -1;
		}
		return affectedRows != 0;
	}

}
