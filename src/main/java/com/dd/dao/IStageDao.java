package com.dd.dao;

import java.util.List;

import com.dd.models.StageModel;

public interface IStageDao {

	public StageModel getStageByStageId(int stageId);
	
	public List<StageModel> getAllField();
	
	public List<StageModel> getFieldByFieldId(int fieldId);
	
	public boolean addStage(StageModel stageModel);
	
	public boolean deleteStageByStageId(int stageId);
	
	public boolean updateStageNameByStageId(String stageName, int stageId);
	
	public boolean updateFieldIdByStageId(int fieldId, int stageId);
	
}
