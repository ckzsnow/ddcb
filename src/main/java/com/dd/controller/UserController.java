package com.dd.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dd.models.ResultModel;
import com.dd.service.IUserService;

@Controller
@RequestMapping("/safety")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/userRegister")
	@ResponseBody
	public ResultModel userRegister(HttpServletRequest request) {
		logger.debug("user register");
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String sendedSMSCode = (String) request.getSession().getAttribute("USER_PHONE_VERIFY_CODE");
		logger.debug("userRegister session id : {}", request.getSession().getId());
		logger.debug("userRegister session smscode : {}", sendedSMSCode);
		String userVerifyCode = request.getParameter("userVerifyCode");
		return userService.userRegister(userId, userPwd, sendedSMSCode, userVerifyCode);
	}
	
	@RequestMapping("/userLogin")
	@ResponseBody
	public ResultModel userLogin(HttpServletRequest request) {
		logger.debug("user register");
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		ResultModel rm = userService.userLogin(userId, userPwd);
		if(("0000").equals(rm.getErrorCode())) {
			request.getSession().setAttribute("userId", userId);
		}
		return userService.userLogin(userId, userPwd);
	}
	
	@RequestMapping("/sendVerifyCode")
	@ResponseBody
	public ResultModel sendVerifyCode(HttpServletRequest request) {
		logger.debug("sendVerifyCode");
		String userId = request.getParameter("userId");
		ResultModel rm = userService.sendVerifyCode(userId);
		logger.debug("sendVerifyCode session id : {}", request.getSession().getId());
		if(("0000").equals(rm.getErrorCode())) {
			request.getSession().setAttribute("USER_PHONE_VERIFY_CODE",
					rm.getExtraInfo());
			logger.debug("sendVerifyCode session record : {}", rm.getExtraInfo());
			logger.debug("success sendVerifyCode session id : {}", request.getSession().getId());
		}
		return rm;
	}
	
}
