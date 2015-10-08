package com.dd.service;

import com.dd.models.ResultModel;

public interface IWorkYearInfoService {

	public ResultModel getAllWorkYear();
	
	public ResultModel getWorkYearById(String id);
	
}