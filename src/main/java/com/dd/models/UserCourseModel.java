package com.dd.models;

import java.sql.Timestamp;

public class UserCourseModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String userId;
	private Long courseId;
	private Integer userType;
	private Timestamp createTime;

	// Constructors

	/** default constructor */
	public UserCourseModel() {
	}

	public UserCourseModel(Long id, String userId, Long courseId, Integer userType, Timestamp createTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.courseId = courseId;
		this.userType = userType;
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

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
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

	@Override
	public String toString() {
		return "UserCourseModel [id=" + id + ", userId=" + userId + ", courseId=" + courseId + ", userType=" + userType
				+ ", createTime=" + createTime + "]";
	}

}