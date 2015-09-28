package com.dd.models;

import java.sql.Timestamp;

public class StageModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer fieldId;
	private String name;
	private Timestamp createTime;

	public StageModel() {
	}

	public StageModel(Integer id, Integer fieldId, String name, Timestamp createTime) {
		this.id = id;
		this.fieldId = fieldId;
		this.name = name;
		this.createTime = createTime;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFieldId() {
		return fieldId;
	}

	public void setFieldId(Integer fieldId) {
		this.fieldId = fieldId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}