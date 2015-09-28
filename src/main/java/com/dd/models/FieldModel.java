package com.dd.models;

import java.sql.Timestamp;

public class FieldModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer industryId;
	private String name;
	private Timestamp createTime;

	public FieldModel() {
	}

	public FieldModel(Integer id, Integer industryId, String name, Timestamp createTime) {
		this.id = id;
		this.industryId = industryId;
		this.name = name;
		this.createTime = createTime;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIndustryId() {
		return industryId;
	}

	public void setIndustryId(Integer industryId) {
		this.industryId = industryId;
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