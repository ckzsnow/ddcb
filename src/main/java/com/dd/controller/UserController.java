package com.dd.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
		String userVerifyCode = request.getParameter("userVerifyCode");
		return userService.userRegister(userId, userPwd, sendedSMSCode, userVerifyCode);
	}
		
	
	@RequestMapping("/userLogin")
	@ResponseBody
	public ResultModel userLogin(HttpServletRequest request) {
		logger.debug("user register");
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		return userService.userLogin(userId, userPwd);
	}
	
	@RequestMapping("/sendVerifyCode")
	@ResponseBody
	public ResultModel sendVerifyCode(HttpSession httpSession, HttpServletRequest request) {
		logger.debug("sendVerifyCode");
		String userId = request.getParameter("userId");
		ResultModel rm = userService.sendVerifyCode(userId);
		if(("0000").equals(rm.getErrorCode())) {
			httpSession.setAttribute("USER_PHONE_VERIFY_CODE",
					rm.getExtraInfo());
		}
		return rm;
	}	
	
}
