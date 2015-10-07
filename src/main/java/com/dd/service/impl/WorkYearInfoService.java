package com.dd.service.impl;

import java.util.Arrays;
import java.util.List;

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
	public ResultModel getWorkYearById(int id) {
		ResultModel ret = new ResultModel();
		WorkYearModel workYearModel = workYearDao.getWorkYearById(id);
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
