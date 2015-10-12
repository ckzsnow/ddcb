package com.dd.models;

public class WebSocketDataModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String userId;
	private String userName;
	private String message;
	private String userPhoto;
	private int messageType;//0-系统消息，1-用户消息

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public WebSocketDataModel() {
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