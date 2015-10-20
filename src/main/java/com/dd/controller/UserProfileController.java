package com.dd.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dd.models.ResultModel;
import com.dd.service.IUserProfileService;
import com.dd.utils.ConvertRequestMapToMap;

@Controller
@RequestMapping("/userProfile")
public class UserProfileController {

	private static final Logger logger = LoggerFactory.getLogger(UserProfileController.class);
	
	@Autowired
	private IUserProfileService userProfileService;
	
	@RequestMapping("/getUserProfile")
	@ResponseBody
	public ResultModel getUserProfile(HttpServletRequest request) {
		logger.debug("getUserProfile");
		String userId = (String) request.getSession().getAttribute("userId");
		if(userId == null || userId.isEmpty()) {
			ResultModel rm = new ResultModel();
			rm.setErrorCode("9900");
			rm.setErrorMsg("会话已过期，请重新登录");
			return rm;
		}
		return userProfileService.getUserProfile(userId);
	}
	
	@RequestMapping("/deleteUserProfile")
	@ResponseBody
	public ResultModel deleteUserProfile(HttpServletRequest request) {
		logger.debug("deleteUserProfile");
		String userId = (String) request.getSession().getAttribute("userId");
		if(userId == null || userId.isEmpty()) {
			ResultModel rm = new ResultModel();
			rm.setErrorCode("9900");
			rm.setErrorMsg("会话已过期，请重新登录");
			return rm;
		}
		return userProfileService.deleteUserProfile(userId);
	}
	
	@RequestMapping("/addUserProfile")
	@ResponseBody
	public ResultModel addUserProfile(HttpServletRequest request) {
		logger.debug("addUserProfile");
		String userId = (String) request.getSession().getAttribute("userId");
		if(userId == null || userId.isEmpty()) {
			ResultModel rm = new ResultModel();
			rm.setErrorCode("9900");
			rm.setErrorMsg("会话已过期，请重新登录");
			return rm;
		}
		Map<String, String> paramsMap = ConvertRequestMapToMap.convert(request.getParameterMap());
		paramsMap.put("userId", userId);
		return userProfileService.addUserProfile(paramsMap);
	}
	
	@RequestMapping("/updateUserProfile")
	@ResponseBody
	public ResultModel updateUserProfile(HttpServletRequest request) {
		logger.debug("updateUserProfile");
		String userId = (String) request.getSession().getAttribute("userId");
		if(userId == null || userId.isEmpty()) {
			ResultModel rm = new ResultModel();
			rm.setErrorCode("9900");
			rm.setErrorMsg("会话已过期，请重新登录");
			return rm;
		}
		Map<String, String> paramsMap = ConvertRequestMapToMap.convert(request.getParameterMap());
		paramsMap.put("userId", userId);
		return userProfileService.updateUserProfile(ConvertRequestMapToMap.convert(request.getParameterMap()));
	}
	
}
