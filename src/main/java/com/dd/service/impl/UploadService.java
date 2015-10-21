package com.dd.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dd.models.ResultModel;
import com.dd.service.ICourseService;
import com.dd.service.IUploadService;
import com.dd.service.IUserProfileService;

@Service("uploadService")
public class UploadService implements IUploadService {

	private static final Logger logger = LoggerFactory.getLogger(UploadService.class);
	
	@Autowired
	private IUserProfileService userProfileService;
	
	@Autowired
	private ICourseService courseService;
	
	@Override
	public ResultModel uploadUserPhoto(String userId, MultipartFile file) {
		ResultModel rm = new ResultModel();
		try {
			String fileName = generateFileName(userId, file);
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File("/home/appfile/userphoto", fileName));
			Map<String, String> params = new HashMap<>();
			params.put("user_id", userId);
			params.put("photo", "/appfile/userphoto/" + fileName);
			if(!("1000").equals(userProfileService.updateUserProfile(params).getErrorCode())) {
				rm.setErrorCode("8802");
				rm.setErrorMsg("上传失败！");
			} else {
				rm.setErrorCode("8800");
				rm.setErrorMsg("上传成功！");
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
			rm.setErrorCode("8801");
			rm.setErrorMsg("上传失败！");
		}
		return rm;
	}

	@Override
	public ResultModel uploadUserVisitCard(String userId, MultipartFile file) {
		ResultModel rm = new ResultModel();
		try {
			String fileName = generateFileName(userId, file);
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File("/home/appfile/uservisitcard", fileName));
			Map<String, String> params = new HashMap<>();
			params.put("user_id", userId);
			params.put("visit_card", "/appfile/uservisitcard/" + fileName);
			if(!("1000").equals(userProfileService.updateUserProfile(params).getErrorCode())) {
				rm.setErrorCode("8802");
				rm.setErrorMsg("上传失败！");
			} else {
				rm.setErrorCode("8800");
				rm.setErrorMsg("上传成功！");
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
			rm.setErrorCode("8801");
			rm.setErrorMsg("上传失败！");
		}
		return rm;
	}

	@Override
	public ResultModel uploadCourseDoc(String courseId, MultipartFile file) {
		ResultModel rm = new ResultModel();
		try {
			String fileName = generateFileName(courseId, file);
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File("/home/appfile/usercoursedoc", fileName));
			Map<String, String> params = new HashMap<>();
			params.put("course_id", courseId);
			params.put("doc_attatch", "/appfile/usercoursedoc/" + fileName);
			if(!("2000").equals(courseService.updateCourse(params).getErrorCode())) {
				rm.setErrorCode("8802");
				rm.setErrorMsg("上传失败！");
			} else {
				rm.setErrorCode("8800");
				rm.setErrorMsg("上传成功！");
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
			rm.setErrorCode("8801");
			rm.setErrorMsg("上传失败！");
		}
		return rm;
	}

	@Override
	public ResultModel uploadCourseVoice(String courseId, MultipartFile file) {
		ResultModel rm = new ResultModel();
		try {
			String fileName = generateFileName(courseId, file);
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File("/home/appfile/usercoursevoice", fileName));
			Map<String, String> params = new HashMap<>();
			params.put("course_id", courseId);
			params.put("voice_attatch", "/appfile/usercoursevoice/" + fileName);
			if(!("2000").equals(courseService.updateCourse(params).getErrorCode())) {
				rm.setErrorCode("8802");
				rm.setErrorMsg("上传失败！");
			} else {
				rm.setErrorCode("8800");
				rm.setErrorMsg("上传成功！");
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
			rm.setErrorCode("8801");
			rm.setErrorMsg("上传失败！");
		}
		return rm;
	}
	
	private String generateFileName(String userId, MultipartFile file) {
		String extendName = file.getOriginalFilename().substring(
				file.getOriginalFilename().indexOf(".") + 1);
		return userId + "_" + System.currentTimeMillis() + (extendName == null ? ".unknown" : "." + extendName);
	}
	
}
