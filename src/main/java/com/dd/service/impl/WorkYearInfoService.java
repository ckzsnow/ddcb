package com.dd.service.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dd.dao.IWorkYearDao;
import com.dd.models.ResultModel;
import com.dd.models.WorkYearModel;
import com.dd.service.IWorkYearInfoService;

@Service("workYearInfoService")
public class WorkYearInfoService implements IWorkYearInfoService {

	@Autowired
	private IWorkYearDao workYearDao;
	
	private static final Logger logger = LoggerFactory.getLogger(WorkYearInfoService.class);

	@Override
	public ResultModel getAllWorkYear() {
		ResultModel ret = new ResultModel();
		List<WorkYearModel> workYearModelList = workYearDao.getAllWorkYear();
		if(workYearModelList == null || workYearModelList.size() == 0) {
			ret.setErrorCode("5001");
			ret.setErrorMsg("未查询到数据");
		} else {
			ret.setErrorCode("5000");
			ret.setErrorMsg("操作成功");
			ret.setResultList(Arrays.asList(workYearModelList.toArray()));
		}
		return ret;
	}

	@Override
	public ResultModel getWorkYearById(String id) {
		ResultModel ret = new ResultModel();
		int workYearId = 0;
		try {
			workYearId = Integer.valueOf(id);
		} catch (NumberFormatException e) {
			logger.error(e.toString());
			ret.setErrorCode("5002");
			ret.setErrorMsg("传入参数格式不正确");
			return ret;
		}
		WorkYearModel workYearModel = workYearDao.getWorkYearById(workYearId);
		if(workYearModel == null) {
			ret.setErrorCode("5001");
			ret.setErrorMsg("未查询到数据");
		} else {
			ret.setErrorCode("5000");
			ret.setErrorMsg("操作成功");
			ret.setResult(workYearModel);
		}
		return ret;
	}
}
