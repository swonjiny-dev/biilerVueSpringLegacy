package com.vue.domain;

import java.io.Serializable;

public class MemberAuth implements Serializable {
	private static final long serialVersionUID = 804619833105543175L;
	private int id;
	private String auth;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	@Override
	public String toString() {
		return String.format("MemberAuth [id=%s, auth=%s]", id, auth);
	}
}
