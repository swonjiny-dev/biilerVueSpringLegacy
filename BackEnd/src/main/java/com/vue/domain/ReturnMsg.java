package com.vue.domain;

public class ReturnMsg {
	private String type; // 유형타입
	private String message; // 메세지
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return String.format("ReturnMsg [type=%s, message=%s]", type, message);
	}
	
}