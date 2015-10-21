package com.dd.service;

import org.springframework.web.multipart.MultipartFile;

import com.dd.models.ResultModel;

public interface IUploadService {

	public ResultModel uploadUserPhoto(String userId, MultipartFile file);
	
	public ResultModel uploadUserVisitCard(String userId, MultipartFile file);
	
	public ResultModel uploadCourseDoc(String courseId, MultipartFile file);
	
	public ResultModel uploadCourseVoice(String courseId, MultipartFile file);
	
}