package com.dd.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dd.models.ResultModel;
import com.dd.service.IUploadService;

@Controller
@RequestMapping("/upload")
public class UploadController {

	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	@Autowired
	private IUploadService uploadService;
	
	@RequestMapping("/uploadUserPhoto")
	@ResponseBody
	public ResultModel uploadUserPhoto(HttpServletRequest request,
			@RequestParam("userPhotoFile") MultipartFile file) {
		logger.debug("uploadUserPhoto");
		String userId = (String) request.getSession().getAttribute("userId");
		if (userId == null || userId.isEmpty()) {
			ResultModel rm = new ResultModel();
			rm.setErrorCode("9900");
			rm.setErrorMsg("会话已过期，请重新登录");
			return rm;
		}		
		return uploadService.uploadUserPhoto(userId, file);
	}

	@RequestMapping("/uploadUserVisitCard")
	@ResponseBody
	public ResultModel uploadUserVisitCard(HttpServletRequest request,
			@RequestParam("userVisitCardFile") MultipartFile file) {
		logger.debug("uploadUserVisitCard");
		String userId = (String) request.getSession().getAttribute("userId");
		if (userId == null || userId.isEmpty()) {
			ResultModel rm = new ResultModel();
			rm.setErrorCode("9900");
			rm.setErrorMsg("会话已过期，请重新登录");
			return rm;
		}		
		return uploadService.uploadUserVisitCard(userId, file);
	}
	
	@RequestMapping("/uploadCourseDoc")
	@ResponseBody
	public ResultModel uploadCourseDoc(HttpServletRequest request,
			@RequestParam("courseDocFile") MultipartFile file) {
		logger.debug("uploadCourseDoc");
		String userId = (String) request.getSession().getAttribute("userId");
		if (userId == null || userId.isEmpty()) {
			ResultModel rm = new ResultModel();
			rm.setErrorCode("9900");
			rm.setErrorMsg("会话已过期，请重新登录");
			return rm;
		}
		String courseId = request.getParameter("courseId");
		return uploadService.uploadCourseDoc(courseId, file);
	}
	
	@RequestMapping("/uploadCourseVoice")
	@ResponseBody
	public ResultModel uploadCourseVoice(HttpServletRequest request,
			@RequestParam("courseVoiceFile") MultipartFile file) {
		logger.debug("uploadCourseVoice");
		String userId = (String) request.getSession().getAttribute("userId");
		if (userId == null || userId.isEmpty()) {
			ResultModel rm = new ResultModel();
			rm.setErrorCode("9900");
			rm.setErrorMsg("会话已过期，请重新登录");
			return rm;
		}
		String courseId = request.getParameter("courseId");
		return uploadService.uploadCourseVoice(courseId, file);
	}

}
