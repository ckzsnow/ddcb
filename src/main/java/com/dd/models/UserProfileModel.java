package com.dd.models;

import java.sql.Timestamp;

public class UserProfileModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String userId;
	
	private String userName;
	
	private String userPhoto;
	
	private String userVisitCard;
	
	private Integer userSex;
	
	private Integer industryId;
	
	private String industryName;
	
	private String companyName;
	
	private String companyPosition;
	
	private Integer workYearId;
	
	private Integer careerPlanId;
	
	private String workYear;
	
	private String careerPlan;
	
	private String provinceId;
	
	private String provinceName;
	
	private String cityId;
	
	private String cityName;
	
	private String areaId;
	
	private String areaName;
	
	private String resume;
	
	private Timestamp createTime;

	public UserProfileModel() {}

	public UserProfileModel(String userId, String userName, String userPhoto, String userVisitCard, Integer userSex,
			Integer industryId, String companyName, String companyPosition, Integer workYearId,
			Integer careerPlanId, String provinceId, 
			String cityId, String areaId, String resume, Timestamp createTime) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPhoto = userPhoto;
		this.userVisitCard = userVisitCard;
		this.userSex = userSex;
		this.industryId = industryId;
		this.companyName = companyName;
		this.companyPosition = companyPosition;
		this.workYearId = workYearId;
		this.careerPlanId = careerPlanId;
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.areaId = areaId;
		this.resume = resume;
		this.createTime = createTime;
	}

	public Integer getCareerPlanId() {
		return careerPlanId;
	}

	public void setCareerPlanId(Integer careerPlanId) {
		this.careerPlanId = careerPlanId;
	}

	public String getCareerPlan() {
		return careerPlan;
	}

	public void setCareerPlan(String careerPlan) {
		this.careerPlan = careerPlan;
	}

	public String getUserVisitCard() {
		return userVisitCard;
	}

	public void setUserVisitCard(String userVisitCard) {
		this.userVisitCard = userVisitCard;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getWorkYear() {
		return workYear;
	}

	public void setWorkYear(String workYear) {
		this.workYear = workYear;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
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

	@Override
	public String toString() {
		return "UserProfileModel [userId=" + userId + ", userName=" + userName + ", userPhoto=" + userPhoto
				+ ", userVisitCard=" + userVisitCard + ", userSex=" + userSex + ", industryId=" + industryId
				+ ", industryName=" + industryName + ", companyName=" + companyName + ", companyPosition="
				+ companyPosition + ", workYearId=" + workYearId + ", careerPlanId=" + careerPlanId + ", workYear="
				+ workYear + ", careerPlan=" + careerPlan + ", provinceId=" + provinceId + ", provinceName="
				+ provinceName + ", cityId=" + cityId + ", cityName=" + cityName + ", areaId=" + areaId + ", areaName="
				+ areaName + ", resume=" + resume + ", createTime=" + createTime + "]";
	}
		
}
