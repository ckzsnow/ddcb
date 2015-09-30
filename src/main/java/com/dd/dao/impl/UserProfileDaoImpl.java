package com.dd.dao.impl;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dd.dao.DaoConstant.UserSex;
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
		String sql = "insert into user_profile (user_id, name, photo, sex, industry, company, position, work_year_id, province_id, city_id, area_id, resume, create_time)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(sql, userProfileModel.getUserId(), userProfileModel.getUserName(),
					userProfileModel.getUserPhoto(), userProfileModel.getUserSex(), userProfileModel.getIndustryId(),
					userProfileModel.getCompanyName(), userProfileModel.getCompanyPosition(),
					userProfileModel.getWorkYearId(), userProfileModel.getProvinceId(), userProfileModel.getCityId(),
					userProfileModel.getAreaId(), userProfileModel.getResume(),
					new Timestamp(System.currentTimeMillis()));
		} catch (Exception e) {
			logger.debug("addUserProfile, exception : {}", e.toString());
		}
		return affectedRows != 0;
	}

	@Override
	public boolean deleteUserProfileByUserId(String userId) {
		String sql = "delete from user_profile where user_id = ?";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(sql, userId);
		} catch (Exception e) {
			logger.debug("deleteUserProfileByUserId, exception : {}", e.toString());
		}
		return affectedRows != 0;
	}

	@Override
	public boolean updateUserNameByUserId(String userName, String userId) {
		String sql = "update user_profile set name=? where user_id=?";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(sql, userName, userId);
		} catch (Exception e) {
			logger.debug("updateUserNameByUserId, exception : {}", e.toString());
		}
		return affectedRows != 0;
	}

	@Override
	public boolean updateUserPhotoByUserId(String userPhoto, String userId) {
		String sql = "update user_profile set photo=? where user_id=?";
		int affectedRows = 0;
		try {
			affectedRows = jdbcTemplate.update(sql, userPhoto, userId);
		} catch (Exception e) {
			logger.debug("updateUserPhotoByUserId, exception : {}", e.toString());
		}
		return affectedRows != 0;
	}

	@Override
	public boolean updateUserSexByUserId(UserSex userSex, String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateIndustryIdByUserId(int industryId, String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCompanyByUserId(String companyName, String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatePositionByUserId(String position, String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateWorkYearIdByUserId(int workYearId, String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateProvinceIdByUserId(String provinceId, String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCityIdByUserId(String cityId, String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateAreaIdByUserId(String areaId, String userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateResumeByUserId(String resume, String userId) {
		// TODO Auto-generated method stub
		return false;
	}

}
