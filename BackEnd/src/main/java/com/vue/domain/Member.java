package com.vue.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Member implements Serializable {
	private static final long serialVersionUID = 4912103157554106762L;
	private int id;
	private String userId;
	private String userPw;
	private String userName;
	private int point;
	private boolean enabled;
	private Date createdAt;
	private Date updatedAt;
	private List<MemberAuth> authList;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public List<MemberAuth> getAuthList() {
		return authList;
	}
	public void setAuthList(List<MemberAuth> authList) {
		this.authList = authList;
	}
	@Override
	public String toString() {
		return String.format(
				"Member [id=%s, userId=%s, userPw=%s, userName=%s, point=%s, enabled=%s, createdAt=%s, updatedAt=%s, authList=%s]",
				id, userId, userPw, userName, point, enabled, createdAt, updatedAt, authList);
	}
}
