package com.vue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vue.domain.Member;
import com.vue.exception.AuthException;
import com.vue.exception.ParamNotFoundException;
import com.vue.service.MemberService;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/member")
public class RestMemberController {
	private static final Logger logger = LoggerFactory.getLogger(RestMemberController.class);
	
	@Autowired
	MemberService mService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Bean
	PasswordEncoder passwordEncoder() 
	{
	    return new BCryptPasswordEncoder();
	}
	
	// 신규사용자등록
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> insert(@RequestBody Member member) throws Exception {
		logger.info("insert member " + member.toString());
		// 사용자 확인
		Member mem = mService.readMemberId(member.getUserId());
		if(mem!=null) throw new AuthException("이용가능 제한  사용자");
		
		String inputPassword = member.getUserPw();
		member.setUserPw(passwordEncoder.encode(inputPassword));
		mService.insertMember(member);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// 사용자 전체
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> list() throws Exception {
		logger.info("all  member list");
		HashMap<String, Object> map = new HashMap<>();
		List<Member> list = mService.list();
		int count = mService.countAll();
		map.put("list", list);
		map.put("count", count);
		return new ResponseEntity<>(map , HttpStatus.OK);
	}
	
	// 특정사용자 확인
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public ResponseEntity<Member> readMemberId(@PathVariable("userId") String userId) throws Exception {
		logger.info("member userID : " +userId );
		return new ResponseEntity<>(mService.readMemberId(userId) , HttpStatus.OK);
	}
	
	// 사용자정보 수정
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateMember(@RequestBody Member member) throws Exception {
		logger.info("updateMember : " +member.toString() );
		mService.updateMember(member);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	// 사용자 인증키발생
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public ResponseEntity<String> getAuthKey( 
			@RequestParam(required = true, value = "userId") String userId 
			, @RequestParam(required = true, value = "userPw")  String userPw) throws Exception {
		Member member = mService.readMemberId(userId);
		if(member.equals(null) ) throw new AuthException("이용가능한 사용자정보누락");
		if(passwordEncoder.matches(member.getUserPw(), userPw)) {
			String authKey = mService.createToken(userId);
			
			return new ResponseEntity<>(authKey , HttpStatus.OK);
		}else {
			 throw new AuthException("이용가능 제한  사용자");
		}
	}
}
