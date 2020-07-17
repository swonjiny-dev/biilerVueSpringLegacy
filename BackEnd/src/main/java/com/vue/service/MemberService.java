package com.vue.service;

import java.util.List;
import java.util.Map;

import com.vue.domain.Member;

public interface MemberService {
	// 신규회원가입
	public void insertMember(Member member) throws Exception;
	// 회원정보 아이디로 검색
	public Member readMemberId(String userId) throws Exception;
	// 멤버삭제하기
	public void deleteMember(Member member) throws Exception;
	// 회원목록
	public List<Member> list() throws Exception;
	// 회원전체수
	public int countAll() throws Exception;
	// 회원정보 수정
	public void updateMember(Member member) throws Exception;
	// 인증토큰을 생성한다.
	public String createToken(String userId) throws Exception;
	// 인증키 검증을 한다.
	public String validToken(String token, String userId) throws Exception;
	// 토큰에서 정보 추출
	Map<String, Object> getTokenPayload(String tokenStr);

}
