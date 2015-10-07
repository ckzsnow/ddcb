package com.dd.models;

import java.sql.Timestamp;

public class IndustryModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public IndustryModel() {
	}

	/** minimal constructor */
	public IndustryModel(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	/** full constructor */
	public IndustryModel(Integer id, String name, Timestamp createTime) {
		this.id = id;
		this.name = name;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		return "IndustryModel [id=" + id + ", name=" + name + ", createTime=" + createTime + "]";
	}

}