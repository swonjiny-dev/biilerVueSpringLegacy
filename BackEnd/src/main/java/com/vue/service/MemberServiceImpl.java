package com.vue.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vue.domain.Member;
import com.vue.domain.MemberAuth;
import com.vue.mapper.MemberMapper;
import com.vue.utils.ComUtils;

import io.jsonwebtoken.ClaimJwtException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.apache.commons.codec.binary.Base64;


@Service
public class MemberServiceImpl implements MemberService {
	private static Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	@Autowired
	private MemberMapper mMapper;
	
	@Transactional
	@Override
	public void insertMember(Member member) throws Exception {
		mMapper.insertMember(member);
		List<MemberAuth> authList = member.getAuthList();
		
		for (MemberAuth memberAuth : authList) {
			if(memberAuth.getAuth().trim().length() ==0 || (memberAuth.getAuth() == null)) {
				continue;
			}
			memberAuth.setId(member.getId());
			mMapper.insertAuth(memberAuth);
		}
	}

	@Override
	public Member readMemberId(String userId) throws Exception {
		return mMapper.readMemberId(userId);
	}
	@Transactional
	@Override
	public void deleteMember(Member member) throws Exception {
		mMapper.deleteMember(member.getUserId());
		mMapper.deleteAuth(member.getId());
	}

	@Override
	public List<Member> list() throws Exception {
		return mMapper.list();
	}

	@Override
	public int countAll() throws Exception {
		return mMapper.countAll();
	}
	
	@Transactional
	@Override
	public void updateMember(Member member) throws Exception {
		mMapper.updateMember(member);
	}

	@Override
	public String createToken(String email) {
		String tokenStr = ""; //토큰 값이 저장될 변수
		String issure = "swonjinyAuth"; //토큰 발급자
		String subject = "tokenData"; //토큰의 주제 (즉 토큰에 담길 내용)
		Date exDate = new Date(System.currentTimeMillis() + 1000*60*10); //토큰 만료 시간 (10분)
		Key tokenKey = MacProvider.generateKey(SignatureAlgorithm.HS256); //토큰의 서명 알고리즘
		String path = "/service/key";
		tokenStr = Jwts.builder()
				.setIssuer(issure)
				.setSubject(subject)
				.setAudience(email)
				.setId(UUID.randomUUID().toString())
				.setExpiration(exDate)
				.setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, tokenKey)
				.compact(); //토큰 생성 
		ComUtils.makeHS512KeyFile(tokenKey, email,path); //토큰 검증을 위해 키를 파일로 생성하고 저장한다.
		return tokenStr;
	}

	@Override
	public String validToken(String tokenStr, String email) {
		String resultMsg = "";
		String path = "/service/key";
		
		try {
			Key key = ComUtils.getKeyObject4File(email , path );
			Jwts.parser().setSigningKey(key).parseClaimsJws(tokenStr).getBody();
			resultMsg="pass";
		}
		catch(ExpiredJwtException e) { //토큰의 만료시간이 지난 경우
			logger.error(e.getMessage());
			resultMsg = "expired"; //
		}
		catch(SignatureException e) { //토큰의 서명 검증이 위조되거나 문제가 생긴 경우
			logger.error(e.getMessage());
			resultMsg = "forgery"; // 위조
		}
		catch(MalformedJwtException e) { //토큰이 구조적으로 문제가 있는경우
			logger.error(e.getMessage());
			resultMsg = "structural"; // 구조적문제
		}catch(IllegalArgumentException e) { //토큰이 구조적으로 문제가 있는경우
			logger.error(e.getMessage());
			resultMsg = "empty key"; // 필요정보 누락
		}catch(ClaimJwtException e) { // 권한 claim 검사가 실패한경우
			logger.error(e.getMessage());
			resultMsg = "claim"; // 필요정보 누락
		}catch(UnsupportedJwtException e) { // 형식이 맞지않음
		logger.error(e.getMessage());
		resultMsg = "unsupport"; 
	}
		return resultMsg;
	}
	
	@Override
	public Map<String, Object> getTokenPayload(String tokenStr) {
		Map<String, Object> payloadMap = new HashMap<String, Object>();
		ObjectMapper om = new ObjectMapper();
		String encodedTokenPayload = tokenStr.split("\\.")[1]; //토큰의 바디 부분을 추린다. 
		String tokenPayload = new String(new Base64(true).decode(encodedTokenPayload)); //토큰의 바디를 디코딩한다.
		try{payloadMap=om.readValue(tokenPayload, new TypeReference<Map<String, Object>>(){});} //토큰의 값을 Map으로 객체화 시킨다.
		catch(Exception e){System.out.println("[getTokenPayload] + " + e.getMessage());}
		return payloadMap;
	}

}
