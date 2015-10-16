package com.dd.models;

import java.util.List;

public class ResultModel {

	private String errorCode;
	
	private String errorMsg;
	
	private String extraInfo;
	
	private Object result;
	
	private List<Object> resultList;

	public String getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public List<Object> getResultList() {
		return resultList;
	}

	public void setResultList(List<Object> resultList) {
		this.resultList = resultList;
	}

	@Override
	public String toString() {
		return "ResultModel [errorCode=" + errorCode + ", errorMsg=" + errorMsg + ", result=" + result + ", resultList="
				+ resultList + "]";
	}
		
}
