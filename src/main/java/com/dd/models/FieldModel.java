package com.dd.models;

import java.sql.Timestamp;

public class FieldModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer industryId;
	private String name;
	private String icon;
	private Timestamp createTime;

	public FieldModel() {
	}

	public FieldModel(Integer id, Integer industryId, String name, String icon, Timestamp createTime) {
		this.id = id;
		this.industryId = industryId;
		this.name = name;
		this.icon = icon;
		this.createTime = createTime;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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

	@Override
	public String toString() {
		return "FieldModel [id=" + id + ", industryId=" + industryId + ", name=" + name + ", icon=" + icon
				+ ", createTime=" + createTime + "]";
	}

}