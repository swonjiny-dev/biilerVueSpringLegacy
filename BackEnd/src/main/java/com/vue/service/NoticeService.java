package com.vue.service;

import java.util.List;

import com.vue.domain.Notice;

public interface NoticeService {
	// 공지사항 목록
	public List<Notice> list() throws Exception;
	// 공지사항 확인
	public Notice getId(int id) throws Exception;
	// 공지사항 입력
	public void insert(Notice notice) throws Exception;
	//공지사항 수정
	public void update(Notice notice) throws Exception;
	// 공지사항 삭제
	public void delete(int id) throws Exception;
}
