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
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/safety/userRegister")
	@ResponseBody
	public ResultModel userRegister(HttpServletRequest request) {
		logger.debug("user register");
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		return userService.userRegister(userId, userPwd);
	}
	
	
	
	@RequestMapping("/safety/userLogin")
	@ResponseBody
	public ResultModel userLogin(HttpServletRequest request) {
		logger.debug("user register");
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		return userService.userLogin(userId, userPwd);
	}	
	
}
