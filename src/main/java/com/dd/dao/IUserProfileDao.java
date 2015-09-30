package com.dd.dao;

import com.dd.dao.DaoConstant.UserSex;
import com.dd.models.UserProfileModel;

public interface IUserProfileDao {

	public UserProfileModel getUserProfileByUserId(String userId);
	
	public boolean addUserProfile(UserProfileModel userProfileModel);
	
	public boolean deleteUserProfileByUserId(String userId);
	
	public boolean updateUserNameByUserId(String userName, String userId);
	
	public boolean updateUserPhotoByUserId(String userPhoto, String userId);
	
	public boolean updateUserSexByUserId(UserSex userSex, String userId);
	
	public boolean updateIndustryIdByUserId(int industryId, String userId);
	
	public boolean updateCompanyByUserId(String companyName, String userId);
	
	public boolean updatePositionByUserId(String position, String userId);
	
	public boolean updateWorkYearIdByUserId(int workYearId, String userId);
	
	public boolean updateProvinceIdByUserId(String provinceId, String userId);
	
	public boolean updateCityIdByUserId(String cityId, String userId);
	
	public boolean updateAreaIdByUserId(String areaId, String userId);
	
	public boolean updateResumeByUserId(String resume, String userId);
	
}
