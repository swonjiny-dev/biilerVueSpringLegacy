package com.vue.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Notice implements Serializable {
	private static final long serialVersionUID = -7264126432096469933L;
	private int id;
	private String title;
	private String content;
	private String userId;
	private String fileName;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date expireDate;
	private boolean enabled;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
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
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	@Override
	public String toString() {
		return String.format(
				"Notice [id=%s, title=%s, content=%s, userId=%s, fileName=%s, createdAt=%s, updatedAt=%s, expireDate=%s, enabled=%s]",
				id, title, content, userId, fileName, createdAt, updatedAt, expireDate, enabled);
	}
	
}
