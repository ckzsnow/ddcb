package com.dd.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dd.dao.IUserProfileDao;
import com.dd.models.AreaModel;
import com.dd.models.CareerPlanModel;
import com.dd.models.CityModel;
import com.dd.models.IndustryModel;
import com.dd.models.ProvinceModel;
import com.dd.models.ResultModel;
import com.dd.models.UserProfileModel;
import com.dd.models.WorkYearModel;
import com.dd.redis.service.IRedisService;
import com.dd.service.ICareerPlanService;
import com.dd.service.ICategoryInfoService;
import com.dd.service.IRegionInfoService;
import com.dd.service.IUserProfileService;
import com.dd.service.IWorkYearInfoService;

@Service("userProfileService")
public class UserProfileService implements IUserProfileService {

	@Autowired
	private IUserProfileDao userProfileDao;

	@Autowired
	private ICategoryInfoService categoryInfoService;

	@Autowired
	private IWorkYearInfoService workYearInfoService;
	
	@Autowired
	private ICareerPlanService careerPlanService;

	@Autowired
	private IRegionInfoService regionInfoService;
	
	@Autowired
	private IRedisService redisService;

	@Override
	public ResultModel getUserProfile(String userId) {
		ResultModel ret = new ResultModel();
		if (userId == null || userId.isEmpty()) {
			ret.setErrorCode("1001");
			ret.setErrorMsg("userid为空");
			return ret;
		}
		UserProfileModel userProgile = userProfileDao.getUserProfileByUserId(userId);
		if (userProgile == null) {
			ret.setErrorCode("1014");
			ret.setErrorMsg("userid不存在");
			return ret;
		}
		ResultModel rm = categoryInfoService.getIndustryByIndustryId(String.valueOf(userProgile.getIndustryId()));
		if (!("3000").equals(rm.getErrorCode())) {
			userProgile.setIndustryName("");
		} else {
			userProgile.setIndustryName(((IndustryModel) rm.getResult()).getName());
		}
		rm = regionInfoService.getProvinceByProvinceId(userProgile.getProvinceId());
		if (!("4000").equals(rm.getErrorCode())) {
			userProgile.setProvinceName("");
		} else {
			userProgile.setProvinceName(((ProvinceModel) rm.getResult()).getProvince());
		}
		rm = regionInfoService.getCityByCityId(userProgile.getCityId());
		if (!("4000").equals(rm.getErrorCode())) {
			userProgile.setCityName("");
		} else {
			userProgile.setCityName(((CityModel) rm.getResult()).getCity());
		}
		rm = regionInfoService.getAreaByAreaId(userProgile.getAreaId());
		if (!("4000").equals(rm.getErrorCode())) {
			userProgile.setAreaName("");
		} else {
			userProgile.setAreaName(((AreaModel) rm.getResult()).getArea());
		}
		rm = workYearInfoService.getWorkYearById(String.valueOf(userProgile.getWorkYearId()));
		if (!("5000").equals(rm.getErrorCode())) {
			userProgile.setWorkYear("");
		} else {
			userProgile.setWorkYear(((WorkYearModel) rm.getResult()).getName());
		}
		rm = careerPlanService.getCareerPlanById(String.valueOf(userProgile.getWorkYearId()));
		if (!("1200").equals(rm.getErrorCode())) {
			userProgile.setCareerPlan("");
		} else {
			userProgile.setCareerPlan(((CareerPlanModel)rm.getResult()).getName());
		}
		if(userProgile.getResume() == null) userProgile.setResume("");
		ret.setErrorCode("1000");
		ret.setErrorMsg("操作成功");
		ret.setResult(userProgile);
		return ret;
	}

	@Override
	public ResultModel addUserProfile(Map<String, String> profileParams) {
		ResultModel ret = new ResultModel();
		UserProfileModel profileModel = new UserProfileModel();
		if (!profileParams.containsKey("user_id")) {
			ret.setErrorCode("1002");
			ret.setErrorMsg("user_id未设置");
			return ret;
		}
		profileModel.setUserId(profileParams.get("user_id"));
		if (!profileParams.containsKey("name")) {
			ret.setErrorCode("1003");
			ret.setErrorMsg("name未设置");
			return ret;
		}
		profileModel.setUserName(profileParams.get("name"));
		if (!profileParams.containsKey("nick_name")) {
			ret.setErrorCode("1020");
			ret.setErrorMsg("nick name未设置");
			return ret;
		}
		profileModel.setNickName(profileParams.get("nick_name"));
		if (!profileParams.containsKey("photo")) {
			ret.setErrorCode("1004");
			ret.setErrorMsg("photo未设置");
			return ret;
		}
		profileModel.setUserPhoto(profileParams.get("photo"));
		try {
			if (!profileParams.containsKey("sex")) {
				ret.setErrorCode("1005");
				ret.setErrorMsg("sex未设置");
				return ret;
			}
			profileModel.setUserSex(Integer.valueOf(profileParams.get("sex")));
			if (!profileParams.containsKey("industry_id")) {
				ret.setErrorCode("1006");
				ret.setErrorMsg("industry_id未设置");
				return ret;
			}
			profileModel.setIndustryId(Integer.valueOf(profileParams.get("industry_id")));
			if (!profileParams.containsKey("work_year_id")) {
				ret.setErrorCode("1009");
				ret.setErrorMsg("work_year_id未设置");
				return ret;
			}
			profileModel.setWorkYearId(Integer.valueOf(profileParams.get("work_year_id")));
		} catch (Exception e) {
			ret.setErrorCode("1020");
			ret.setErrorMsg("参数格式不正确");
			return ret;
		}
		if (!profileParams.containsKey("company")) {
			ret.setErrorCode("1007");
			ret.setErrorMsg("company未设置");
			return ret;
		}
		profileModel.setCompanyName(profileParams.get("company"));
		if (!profileParams.containsKey("position")) {
			ret.setErrorCode("1008");
			ret.setErrorMsg("position未设置");
			return ret;
		}
		profileModel.setCompanyPosition(profileParams.get("position"));
		if (!profileParams.containsKey("province_id")) {
			ret.setErrorCode("1010");
			ret.setErrorMsg("province_id未设置");
			return ret;
		}
		profileModel.setProvinceId(profileParams.get("province_id"));
		if (!profileParams.containsKey("city_id")) {
			ret.setErrorCode("1011");
			ret.setErrorMsg("city_id未设置");
			return ret;
		}
		profileModel.setCityId(profileParams.get("city_id"));
		if (!profileParams.containsKey("area_id")) {
			ret.setErrorCode("1012");
			ret.setErrorMsg("area_id未设置");
			return ret;
		}
		profileModel.setAreaId(profileParams.get("area_id"));
		profileModel.setResume(profileParams.containsKey("resume") ? profileParams.get("resume") : "");
		if (userProfileDao.addUserProfile(profileModel)) {
			ret.setErrorCode("1000");
			ret.setErrorMsg("操作成功");
		} else {
			ret.setErrorCode("1013");
			ret.setErrorMsg("操作数据库失败");
		}
		redisService.addUserInfo(profileModel);
		return ret;
	}

	@Override
	public ResultModel updateUserProfile(Map<String, String> profileParams) {
		ResultModel ret = new ResultModel();
		UserProfileModel profileModel = new UserProfileModel();
		if (profileParams.containsKey("user_id")) {
			profileModel.setUserId(profileParams.get("user_id"));
		} else {
			ret.setErrorCode("1002");
			ret.setErrorMsg("user_id未设置");
			return ret;
		}
		if (profileParams.containsKey("name")) {
			profileModel.setUserName(profileParams.get("name"));
		} else {
			profileModel.setUserName("");
		}
		if (profileParams.containsKey("nick_name")) {
			profileModel.setNickName(profileParams.get("nick_name"));
		} else {
			profileModel.setNickName("");
		}
		if (profileParams.containsKey("photo")) {
			profileModel.setUserPhoto(profileParams.get("photo"));
		} else {
			profileModel.setUserPhoto("");
		}
		if (profileParams.containsKey("visit_card")) {
			profileModel.setUserVisitCard(profileParams.get("visit_card"));
		} else {
			profileModel.setUserVisitCard("");
		}
		try {
			if (profileParams.containsKey("sex")) {
				profileModel.setUserSex(Integer.valueOf(profileParams.get("sex")));
			} else {
				profileModel.setUserSex(0);
			}
			if (profileParams.containsKey("industry_id")) {
				profileModel.setIndustryId(Integer.valueOf(profileParams.get("industry_id")));
			} else {
				profileModel.setIndustryId(0);
			}
			if (profileParams.containsKey("work_year_id")) {
				profileModel.setWorkYearId(Integer.valueOf(profileParams.get("work_year_id")));
			} else {
				profileModel.setWorkYearId(0);
			}
			if (profileParams.containsKey("career_plan_id")) {
				profileModel.setCareerPlanId(Integer.valueOf(profileParams.get("career_plan_id")));
			} else {
				profileModel.setCareerPlanId(0);
			}
		} catch (Exception e) {
			ret.setErrorCode("1020");
			ret.setErrorMsg("参数格式不正确");
			return ret;
		}
		if (profileParams.containsKey("company")) {
			profileModel.setCompanyName(profileParams.get("company"));
		} else {
			profileModel.setCompanyName("");
		}
		if (profileParams.containsKey("position")) {
			profileModel.setCompanyPosition(profileParams.get("position"));
		} else {
			profileModel.setCompanyPosition("");
		}
		if (profileParams.containsKey("province_id")) {
			profileModel.setProvinceId(profileParams.get("province_id"));
		} else {
			profileModel.setProvinceId("0");
		}
		if (profileParams.containsKey("city_id")) {
			profileModel.setCityId(profileParams.get("city_id"));
		} else {
			profileModel.setCityId("0");
		}
		if (profileParams.containsKey("area_id")) {
			profileModel.setAreaId(profileParams.get("area_id"));
		} else {
			profileModel.setAreaId("0");
		}
		if (profileParams.containsKey("resume")) {
			profileModel.setResume(profileParams.get("resume"));
		} else {
			profileModel.setResume("");
		}
		if(userProfileDao.getUserProfileByUserId(profileParams.get("user_id")) != null) {
			if (userProfileDao.updateUserProfile(profileModel)) {
				ret.setErrorCode("1000");
				ret.setErrorMsg("操作成功");
			} else {
				ret.setErrorCode("1013");
				ret.setErrorMsg("操作数据库失败");
			}
		} else {
			userProfileDao.addUserProfile(profileModel);
		}
		return ret;
	}

	@Override
	public ResultModel deleteUserProfile(String userId) {
		ResultModel ret = new ResultModel();
		if (userId == null || userId.isEmpty()) {
			ret.setErrorCode("1002");
			ret.setErrorMsg("user_id未设置");
			return ret;
		}
		if (userProfileDao.deleteUserProfileByUserId(userId)) {
			ret.setErrorCode("1000");
			ret.setErrorMsg("操作成功");
		} else {
			ret.setErrorCode("1013");
			ret.setErrorMsg("操作数据库失败");
		}
		return ret;
	}

}
