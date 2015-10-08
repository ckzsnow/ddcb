package com.dd.service.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger logger = LoggerFactory.getLogger(CategoryInfoService.class);

	@Override
	public ResultModel getStageByStageId(String stageId) {
		ResultModel ret = new ResultModel();
		try {
			int stageId_ = Integer.valueOf(stageId);
			StageModel stageModel = stageDao.getStageByStageId(stageId_);
			if (stageModel == null) {
				ret.setErrorCode("3001");
				ret.setErrorMsg("未查询到数据");
			} else {
				ret.setErrorCode("3000");
				ret.setErrorMsg("操作成功");
				ret.setResult(stageModel);
			}
		} catch (NumberFormatException e) {
			logger.error(e.toString());
			ret.setErrorCode("3002");
			ret.setErrorMsg("传入参数应为数字，数字格式不正确");
		}
		return ret;
	}

	@Override
	public ResultModel getAllStage() {
		ResultModel ret = new ResultModel();
		List<StageModel> stageModelList = stageDao.getAllStage();
		if (stageModelList == null || stageModelList.size() == 0) {
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
	public ResultModel getFieldByFieldId(String fieldId) {
		ResultModel ret = new ResultModel();
		try {
			int fieldId_ = Integer.valueOf(fieldId);
			FieldModel fieldModel = fieldDao.getFieldByFieldId(fieldId_);
			if (fieldModel == null) {
				ret.setErrorCode("3001");
				ret.setErrorMsg("未查询到数据");
			} else {
				ret.setErrorCode("3000");
				ret.setErrorMsg("操作成功");
				ret.setResult(fieldModel);
			}
		} catch (NumberFormatException e) {
			logger.error(e.toString());
			ret.setErrorCode("3002");
			ret.setErrorMsg("传入参数应为数字，数字格式不正确");
		}
		return ret;
	}

	@Override
	public ResultModel getFieldByIndustryId(String industryId) {
		ResultModel ret = new ResultModel();
		try {
			int industryId_ = Integer.valueOf(industryId);
			List<FieldModel> fieldModelList = fieldDao.getFieldByIndustryId(industryId_);
			if (fieldModelList == null || fieldModelList.size() == 0) {
				ret.setErrorCode("3001");
				ret.setErrorMsg("未查询到数据");
			} else {
				ret.setErrorCode("3000");
				ret.setErrorMsg("操作成功");
				ret.setResultList(Arrays.asList(fieldModelList.toArray()));
			}
		} catch (NumberFormatException e) {
			logger.error(e.toString());
			ret.setErrorCode("3002");
			ret.setErrorMsg("传入参数应为数字，数字格式不正确");
		}
		return ret;
	}

	@Override
	public ResultModel getIndustryByIndustryId(String industryId) {
		ResultModel ret = new ResultModel();
		try {
			int industryId_ = Integer.valueOf(industryId);
			IndustryModel industryModel = industryDao.getIndustryByIndustryId(industryId_);
			if (industryModel == null) {
				ret.setErrorCode("3001");
				ret.setErrorMsg("未查询到数据");
			} else {
				ret.setErrorCode("3000");
				ret.setErrorMsg("操作成功");
				ret.setResult(industryModel);
			}
		} catch (NumberFormatException e) {
			logger.error(e.toString());
			ret.setErrorCode("3002");
			ret.setErrorMsg("传入参数应为数字，数字格式不正确");
		}
		return ret;
	}

	@Override
	public ResultModel getAllIndustry() {
		ResultModel ret = new ResultModel();
		List<IndustryModel> industryModelList = industryDao.getAllIndustry();
		if (industryModelList == null || industryModelList.size() == 0) {
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
