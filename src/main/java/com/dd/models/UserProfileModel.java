package com.dd.models;

import java.sql.Timestamp;

public class UserProfileModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String userId;
	
	private String userName;
	
	private String userPhoto;
	
	private Integer userSex;
	
	private Integer industryId;
	
	private String companyName;
	
	private String companyPosition;
	
	private Integer workYearId;
	
	private String provinceId;
	
	private String cityId;
	
	private String areaId;
	
	private String resume;
	
	private Timestamp createTime;

	public UserProfileModel() {}

	public UserProfileModel(String userId, String userName, String userPhoto, Integer userSex, Integer industryId,
			String companyName, String companyPosition, Integer workYearId, String provinceId, String cityId,
			String areaId, String resume, Timestamp createTime) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPhoto = userPhoto;
		this.userSex = userSex;
		this.industryId = industryId;
		this.companyName = companyName;
		this.companyPosition = companyPosition;
		this.workYearId = workYearId;
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.areaId = areaId;
		this.resume = resume;
		this.createTime = createTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}

	public Integer getUserSex() {
		return userSex;
	}

	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}

	public Integer getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Integer industryId) {
		this.industryId = industryId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyPosition() {
		return companyPosition;
	}

	public void setCompanyPosition(String companyPosition) {
		this.companyPosition = companyPosition;
	}

	public Integer getWorkYearId() {
		return workYearId;
	}

	public void setWorkYearId(Integer workYearId) {
		this.workYearId = workYearId;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		
}
