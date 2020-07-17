package com.vue.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.vue.domain.ReturnMsg;
import com.vue.exception.AuthException;
import com.vue.exception.NormalErrorException;
import com.vue.exception.ParamNotFoundException;



/**
 * @author 송원진
 * @filename : APIExceptionHandler.java
 * 
 * <pre> api 호출 에러처리 핸들러 </pre>
 */
@ControllerAdvice
public class APIExceptionHandler {
	
	// 파라미터중 입력안된 부분이 존재할때
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = ParamNotFoundException.class)
	 public ResponseEntity<ReturnMsg> handleParamNotFoundException(ParamNotFoundException e){
		ReturnMsg dto = new ReturnMsg();
		String temp = e.getMessage();
		dto.setType("param");
		if("id".equals(temp)){
			dto.setMessage("id empty");
		}else if("password".equals(temp)){
			dto.setMessage("password empty");			
		}else if("serviceType".equals(temp)){
			dto.setMessage("serviceType empty");						
		}else {
			dto.setMessage(temp);
		}
        return new ResponseEntity<ReturnMsg> (dto,HttpStatus.BAD_REQUEST);  
    } 
	// 인증 실패시
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(value = AuthException.class)
	 public ResponseEntity<ReturnMsg> handleAuthException(AuthException e){
		ReturnMsg dto = new ReturnMsg();
		String temp = e.getMessage();
		dto.setType("auth");
		dto.setMessage(temp);
        return new ResponseEntity<ReturnMsg> (dto,HttpStatus.UNAUTHORIZED);  
    } 
	// 로그인 실패한경우
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MissingServletRequestParameterException.class)
	 public ResponseEntity<ReturnMsg> handleParamNotFoundException2(MissingServletRequestParameterException e){
		ReturnMsg dto = new ReturnMsg();
		String temp = e.getMessage();
		dto.setType("param");
		if( temp.contains("email") ){ 
			dto.setMessage("email empty");
		}else if(temp.contains("password")){
			dto.setMessage("passWord empty");
		}else if(temp.contains("serviceType")){
			dto.setMessage("serviceType empty");						
		}else {
			dto.setMessage(temp);
		} 
		return new ResponseEntity<ReturnMsg> (dto,HttpStatus.BAD_REQUEST);  
	}
	
	// 일반적인 오류발생시
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = NormalErrorException.class)
	 public ResponseEntity<ReturnMsg> handleNormalErrorException(NormalErrorException e){
		ReturnMsg dto = new ReturnMsg();
		String temp = e.getMessage();
		dto.setType("error");
		dto.setMessage(temp);
        return new ResponseEntity<ReturnMsg> (dto,HttpStatus.BAD_REQUEST);  
    } 
}