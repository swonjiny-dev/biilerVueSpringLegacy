package com.vue.mapper;

import java.util.List;

import com.vue.domain.Notice;

public interface NoticeMapper {
	// �������׸�� Ȯ��
	public List<Notice> list() throws Exception;
	
	// �������� �ܰ� Ȯ��
	public Notice getId(int id) throws Exception;
	
	// �������� �Է�
	public void insert(Notice notice) throws Exception;
	
	// �������� ����
	public void update(Notice notice) throws Exception;
	
	// �������� ����
	public void delete(int id) throws Exception;

}
