package com.vue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vue.domain.Notice;
import com.vue.service.NoticeService;

@RestController
@RequestMapping("/notice")
public class RestNoticeController {
	private static final Logger logger = LoggerFactory.getLogger(RestNoticeController.class);
	@Autowired
	NoticeService noticeService;
	
	
	// 전체 목록 -- 페이징처리의 경우 프런트에서 테이블이나 목록 라이브러리 로 처리한다.(공지사항은 일정시간후 내린다는 가정)
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<List<Notice>> list() throws Exception {
		logger.info("공지사항 전체목록확인");
		List<Notice> list = noticeService.list();
		return new ResponseEntity<>(list , HttpStatus.OK);
	}
	
	// 단건조회 -- 프런트에서 테이블 목록라이브러리 이용하면 필요없을수 있음
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Notice> get(@PathVariable("id") int id) throws Exception {
		logger.info("공지사항 단건 확인 id - " + id);
		Notice notice = noticeService.getId(id);
		return new ResponseEntity<>(notice , HttpStatus.OK);
		
	}
	
	// 입력
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> insertNotice(@RequestBody Notice notice) throws Exception {
		logger.info("insert notice " + notice.toString());
		// 기존사용자 확인
		noticeService.insert(notice);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// 수정
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> updater(@RequestBody Notice notice) throws Exception {
		logger.info("update notice : " +notice.toString() );
		noticeService.update(notice);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// 삭제
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") int id) throws Exception {
		logger.info("delete notice id : " +id);
		noticeService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
