package com.vue.mapper;

import java.util.List;

import com.vue.domain.Member;
import com.vue.domain.MemberAuth;

public interface MemberMapper {
	// ȸ������ Ȯ�� -- ���̵�
	public Member readMemberId(String userId) throws Exception;
	// ȸ������
	public void insertMember(Member member) throws Exception;
	// ȸ������ ���� - �̸���
	public void updateMember(Member member) throws Exception;
	// ȸ������ ���� -- ������Ʈ�� ���Ұ� ó����
	public void deleteMember(String useId) throws Exception;
	// ȸ������ �Է�
	public void insertAuth(MemberAuth auth) throws Exception;
	// ȸ������ ����
	public void deleteAuth(int id) throws Exception;
	// ȸ����ü ���� -- ����¡ ó���뵵 �� ���������� findAndCountAll �� �����ϰ� ó���ϱ� ���ؼ����
	public int countAll() throws Exception;
	// ��ü ȸ������
	public List<Member> list() throws Exception;
}
