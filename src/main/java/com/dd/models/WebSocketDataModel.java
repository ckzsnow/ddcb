package com.dd.models;

import java.sql.Timestamp;

public class WebSocketDataModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String userId;
	private String userName;
	private String message;
	private String userPhoto;
	private String messageType;//0-系统消息，1-用户消息
	private String time;

	public WebSocketDataModel() {
		this.time = new Timestamp(System.currentTimeMillis()).toString(); 
	}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}