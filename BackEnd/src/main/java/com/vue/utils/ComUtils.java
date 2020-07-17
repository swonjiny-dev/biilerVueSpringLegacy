package com.vue.utils;

import java.security.Key;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import org.slf4j.Logger;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.ByteBuffer;
import java.io.IOException;

/**
 * @author 송원진
 * @filename : ComUtils.java
 * <pre> 인증키관련 유틸모음 </pre>
 */
public class ComUtils {
	private static Logger logger = LoggerFactory.getLogger(ComUtils.class);
	
	/**
	 * 토큰에 사용되는 Key 객체를 파일로 만들어주는 메서드
	 * @param {Key} keyObject 키 객체
	 * @param {String} userId 키를 생성하게 된 사용자 ID
	 * @param {String} baseSecretPath 파일생성경로
	 */
	public static void makeHS512KeyFile(Key keyObject, String userId , String baseSecretPath) {
		FileChannel fileChannel;
		String separatorStr = FileSystems.getDefault().getSeparator();
		String tokenDir = baseSecretPath+separatorStr;
		String tokenPath = tokenDir+separatorStr+userId;
		String keyObjStr= Base64Utils.encodeToString(keyObject.getEncoded());
		
		//토큰파일이 존재하는 경우 삭제해준다.
		if(Files.exists(Paths.get(tokenPath))) {
			try{Files.delete(Paths.get(tokenPath));}
			catch(Exception e){
				logger.error(e.getMessage());
			}
		}
		
		try {
			Files.createDirectories(Paths.get(tokenDir)); //디렉토리 생성
			Files.createFile(Paths.get(tokenPath)); //토큰 디렉토리에 토큰 키 파일을 생성
			fileChannel = FileChannel.open(Paths.get(tokenPath), StandardOpenOption.WRITE); //파일 쓰기
			
			ByteBuffer buffer = ByteBuffer.allocateDirect(keyObjStr.length());
			Charset charset = Charset.forName("UTF-8");
			buffer = charset.encode(keyObjStr);
			
			fileChannel.write(buffer);
			fileChannel.close();
		}
		catch(IOException e) {
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * 저장된 파일에서 Key 객체를 가져오는 메서드
	 * @param {String} userID 사용자 Id
	 * @param {String} baseSecretPath 키파일이 위치한 경로
	 * @return 파일에서 받아온 키 객체를 반환한다.
	 */
	public static Key getKeyObject4File(String userID , String baseSecretPath) {
		FileChannel fileChannel;
		String keyStr = "";
		String separatorStr = FileSystems.getDefault().getSeparator();
		String tokenDir = baseSecretPath+separatorStr;
		String tokenPath = tokenDir+separatorStr+userID;
		try {
			fileChannel = FileChannel.open(Paths.get(tokenPath));
			ByteBuffer buffers = ByteBuffer.allocateDirect(100);
			Charset charset = Charset.forName("UTF-8");
			
			int byteCnt = 0;
			while(true){
				byteCnt=fileChannel.read(buffers);
				if(byteCnt==-1)break;
				buffers.flip();
				keyStr += charset.decode(buffers).toString();
				buffers.clear();
			}
	
			fileChannel.close();

		}
		catch(IOException e) {
			logger.error(  e.getMessage()  );
		}
		return new SecretKeySpec(Base64Utils.decodeFromString(keyStr), "HmacSHA256");
	}
}
