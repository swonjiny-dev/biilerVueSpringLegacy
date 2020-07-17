package com.vue.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vue.domain.Notice;
import com.vue.mapper.NoticeMapper;
@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private NoticeMapper nMapper;
	private static final Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);
	
	
	@Override
	public List<Notice> list() throws Exception {
		logger.info("공지사항 목록확인  " );
		return nMapper.list();
	}

	@Override
	public void insert(Notice notice) throws Exception {
		logger.info("공지사항 입력 - " + notice);
		nMapper.insert(notice);
	}

	@Override
	public void update(Notice notice) throws Exception {
		logger.info("공지사항 수정 " + notice);
		nMapper.update(notice);
	}

	@Override
	public void delete(int id) throws Exception {
		logger.info("공지사항 삭제 - " + id);
		nMapper.delete(id);
	}

	@Override
	public Notice getId(int id) throws Exception {
		logger.info("getId - " + id);
		return nMapper.getId(id);
	}

}
