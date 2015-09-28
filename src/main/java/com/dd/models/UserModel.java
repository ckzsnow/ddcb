package com.dd.models;

import java.sql.Timestamp;

public class UserModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String userId;
	
	private String pwd;
	
	private Timestamp createTime;

	public UserModel() {}
	
	public UserModel(Long id, String userId, String pwd, Timestamp createTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.pwd = pwd;
		this.createTime = createTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
