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
 * @author �ۿ���
 * @filename : ComUtils.java
 * <pre> ����Ű���� ��ƿ���� </pre>
 */
public class ComUtils {
	private static Logger logger = LoggerFactory.getLogger(ComUtils.class);
	
	/**
	 * ��ū�� ���Ǵ� Key ��ü�� ���Ϸ� ������ִ� �޼���
	 * @param {Key} keyObject Ű ��ü
	 * @param {String} userId Ű�� �����ϰ� �� ����� ID
	 * @param {String} baseSecretPath ���ϻ������
	 */
	public static void makeHS512KeyFile(Key keyObject, String userId , String baseSecretPath) {
		FileChannel fileChannel;
		String separatorStr = FileSystems.getDefault().getSeparator();
		String tokenDir = baseSecretPath+separatorStr;
		String tokenPath = tokenDir+separatorStr+userId;
		String keyObjStr= Base64Utils.encodeToString(keyObject.getEncoded());
		
		//��ū������ �����ϴ� ��� �������ش�.
		if(Files.exists(Paths.get(tokenPath))) {
			try{Files.delete(Paths.get(tokenPath));}
			catch(Exception e){
				logger.error(e.getMessage());
			}
		}
		
		try {
			Files.createDirectories(Paths.get(tokenDir)); //���丮 ����
			Files.createFile(Paths.get(tokenPath)); //��ū ���丮�� ��ū Ű ������ ����
			fileChannel = FileChannel.open(Paths.get(tokenPath), StandardOpenOption.WRITE); //���� ����
			
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
	 * ����� ���Ͽ��� Key ��ü�� �������� �޼���
	 * @param {String} userID ����� Id
	 * @param {String} baseSecretPath Ű������ ��ġ�� ���
	 * @return ���Ͽ��� �޾ƿ� Ű ��ü�� ��ȯ�Ѵ�.
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
