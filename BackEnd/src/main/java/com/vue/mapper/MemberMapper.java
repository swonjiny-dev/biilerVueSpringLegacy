package com.vue.mapper;

import java.util.List;

import com.vue.domain.Member;
import com.vue.domain.MemberAuth;

public interface MemberMapper {
	// 회원정보 확인 -- 아이디만
	public Member readMemberId(String userId) throws Exception;
	// 회원가입
	public void insertMember(Member member) throws Exception;
	// 회원정보 수정 - 이름만
	public void updateMember(Member member) throws Exception;
	// 회원정보 삭제 -- 업데이트로 사용불가 처리함
	public void deleteMember(String useId) throws Exception;
	// 회원권한 입력
	public void insertAuth(MemberAuth auth) throws Exception;
	// 회원권한 삭제
	public void deleteAuth(int id) throws Exception;
	// 회원전체 숫자 -- 페이징 처리용도 및 시퀄라이저 findAndCountAll 와 동일하게 처리하기 위해서사용
	public int countAll() throws Exception;
	// 전체 회원정보
	public List<Member> list() throws Exception;
}
