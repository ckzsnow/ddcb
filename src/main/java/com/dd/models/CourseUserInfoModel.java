package com.dd.models;

public class CourseUserInfoModel {

	private CourseModel courseInfo;
	
	private UserProfileModel userInfo;

	public CourseUserInfoModel() {}
	
	public CourseUserInfoModel(CourseModel courseInfo, UserProfileModel userInfo) {
		super();
		this.courseInfo = courseInfo;
		this.userInfo = userInfo;
	}

	public CourseModel getCourseInfo() {
		return courseInfo;
	}

	public void setCourseInfo(CourseModel courseInfo) {
		this.courseInfo = courseInfo;
	}

	public UserProfileModel getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserProfileModel userInfo) {
		this.userInfo = userInfo;
	}	
	
}
