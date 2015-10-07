package com.dd.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dd.dao.IFieldDao;
import com.dd.dao.IIndustryDao;
import com.dd.dao.IStageDao;
import com.dd.models.FieldModel;
import com.dd.models.IndustryModel;
import com.dd.models.ResultModel;
import com.dd.models.StageModel;
import com.dd.service.ICategoryInfoService;

@Service("categoryInfoService")
public class CategoryInfoService implements ICategoryInfoService {

	@Autowired
	private IIndustryDao industryDao;
	
	@Autowired
	private IFieldDao fieldDao;
	
	@Autowired
	private IStageDao stageDao;

	@Override
	public ResultModel getStageByStageId(int stageId) {
		ResultModel ret = new ResultModel();
		StageModel stageModel = stageDao.getStageByStageId(stageId);
		if(stageModel == null) {
			ret.setErrorCode("3001");
			ret.setErrorMsg("未查询到数据");
		} else {
			ret.setErrorCode("3000");
			ret.setErrorMsg("操作成功");
			ret.setResult(stageModel);
		}
		return ret;
	}

	@Override
	public ResultModel getStageByFieldId(int fieldId) {
		ResultModel ret = new ResultModel();
		List<StageModel> stageModelList = stageDao.getStageByFieldId(fieldId);
		if(stageModelList == null || stageModelList.size() == 0) {
			ret.setErrorCode("3001");
			ret.setErrorMsg("未查询到数据");
		} else {
			ret.setErrorCode("3000");
			ret.setErrorMsg("操作成功");
			ret.setResultList(Arrays.asList(stageModelList.toArray()));
		}
		return ret;
	}

	@Override
	public ResultModel getFieldByFieldId(int fieldId) {
		ResultModel ret = new ResultModel();
		FieldModel fieldModel = fieldDao.getFieldByFieldId(fieldId);
		if(fieldModel == null) {
			ret.setErrorCode("3001");
			ret.setErrorMsg("未查询到数据");
		} else {
			ret.setErrorCode("3000");
			ret.setErrorMsg("操作成功");
			ret.setResult(fieldModel);
		}
		return ret;
	}

	@Override
	public ResultModel getFieldByIndustryId(int industryId) {
		ResultModel ret = new ResultModel();
		List<FieldModel> fieldModelList = fieldDao.getFieldByIndustryId(industryId);
		if(fieldModelList == null || fieldModelList.size() == 0) {
			ret.setErrorCode("3001");
			ret.setErrorMsg("未查询到数据");
		} else {
			ret.setErrorCode("3000");
			ret.setErrorMsg("操作成功");
			ret.setResultList(Arrays.asList(fieldModelList.toArray()));
		}
		return ret;
	}

	@Override
	public ResultModel getIndustryByIndustryId(int industryId) {
		ResultModel ret = new ResultModel();
		IndustryModel industryModel = industryDao.getIndustryByIndustryId(industryId);
		if(industryModel == null) {
			ret.setErrorCode("3001");
			ret.setErrorMsg("未查询到数据");
		} else {
			ret.setErrorCode("3000");
			ret.setErrorMsg("操作成功");
			ret.setResult(industryModel);
		}
		return ret;
	}

	@Override
	public ResultModel getAllIndustry() {
		ResultModel ret = new ResultModel();
		List<IndustryModel> industryModelList = industryDao.getAllIndustry();
		if(industryModelList == null || industryModelList.size() == 0) {
			ret.setErrorCode("3001");
			ret.setErrorMsg("未查询到数据");
		} else {
			ret.setErrorCode("3000");
			ret.setErrorMsg("操作成功");
			ret.setResultList(Arrays.asList(industryModelList.toArray()));
		}
		return ret;
	}
}
