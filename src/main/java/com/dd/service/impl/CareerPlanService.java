package com.dd.service.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dd.dao.ICareerPlanDao;
import com.dd.dao.IWorkYearDao;
import com.dd.models.CareerPlanModel;
import com.dd.models.ResultModel;
import com.dd.models.WorkYearModel;
import com.dd.service.ICareerPlanService;
import com.dd.service.IWorkYearInfoService;

@Service("careerPlanService")
public class CareerPlanService implements ICareerPlanService {

	@Autowired
	private ICareerPlanDao careerPlanDao;
	
	private static final Logger logger = LoggerFactory.getLogger(CareerPlanService.class);

	@Override
	public ResultModel getAllCareerPlan() {
		ResultModel ret = new ResultModel();
		List<CareerPlanModel> careerPlanModelList = careerPlanDao.getAllCareerPlan();
		if(careerPlanModelList == null || careerPlanModelList.size() == 0) {
			ret.setErrorCode("1201");
			ret.setErrorMsg("未查询到数据");
		} else {
			ret.setErrorCode("1200");
			ret.setErrorMsg("操作成功");
			ret.setResultList(Arrays.asList(careerPlanModelList.toArray()));
		}
		return ret;
	}

	@Override
	public ResultModel getCareerPlanById(String id) {
		ResultModel ret = new ResultModel();
		int workYearId = 0;
		try {
			workYearId = Integer.valueOf(id);
		} catch (NumberFormatException e) {
			logger.error(e.toString());
			ret.setErrorCode("1202");
			ret.setErrorMsg("传入参数格式不正确");
			return ret;
		}
		WorkYearModel workYearModel = careerPlanDao.getCareerPlanById(id);
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
