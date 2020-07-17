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
	
	
	// ��ü ��� -- ����¡ó���� ��� ����Ʈ���� ���̺��̳� ��� ���̺귯�� �� ó���Ѵ�.(���������� �����ð��� �����ٴ� ����)
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<List<Notice>> list() throws Exception {
		logger.info("�������� ��ü���Ȯ��");
		List<Notice> list = noticeService.list();
		return new ResponseEntity<>(list , HttpStatus.OK);
	}
	
	// �ܰ���ȸ -- ����Ʈ���� ���̺� ��϶��̺귯�� �̿��ϸ� �ʿ������ ����
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Notice> get(@PathVariable("id") int id) throws Exception {
		logger.info("�������� �ܰ� Ȯ�� id - " + id);
		Notice notice = noticeService.getId(id);
		return new ResponseEntity<>(notice , HttpStatus.OK);
		
	}
	
	// �Է�
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> insertNotice(@RequestBody Notice notice) throws Exception {
		logger.info("insert notice " + notice.toString());
		// ��������� Ȯ��
		noticeService.insert(notice);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// ����
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> updater(@RequestBody Notice notice) throws Exception {
		logger.info("update notice : " +notice.toString() );
		noticeService.update(notice);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// ����
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable("id") int id) throws Exception {
		logger.info("delete notice id : " +id);
		noticeService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
