package com.dd.models;

public class WebSocketDataModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String userId;
	private String message;
	private String userPhoto;
	private WebSocketDataModel[] historyMessage;

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

	public WebSocketDataModel[] getHistoryMessage() {
		return historyMessage;
	}

	public void setHistoryMessage(WebSocketDataModel[] historyMessage) {
		this.historyMessage = historyMessage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}