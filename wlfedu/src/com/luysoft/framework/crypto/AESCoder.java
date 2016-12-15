package com.luysoft.framework.crypto;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * AES加密、解密
 * 
 * @author lifz
 */
public class AESCoder {
	
	private static final String TRANSFORM = "AES/ECB/PKCS5Padding";
	private static final String ALGORITHM = "AES";
	
	/**
	 * 加密处理
	 * 
	 * @param key 密钥
	 * @param charsetName 字符编码
	 * @param value 加密内容
	 * 
	 * @return 加密结果
	 * @throws Exception
	 */
	public static String encode(String key, String charsetName, String value) throws Exception {
		
		byte[] bVal = value.getBytes(charsetName);
		byte[] bKey = Base64.decodeBase64(key);

		SecretKey secretKey = new SecretKeySpec(bKey, ALGORITHM);
		Cipher cipher = Cipher.getInstance(TRANSFORM);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		
		byte[] outBytes = cipher.doFinal(bVal);
		
		return Base64.encodeBase64String(outBytes).replaceAll("\r|\n", "");
	}

	/**
	 * 解密处理
	 * 
	 * @param key 密钥
	 * @param charsetName 字符编码
	 * @param value 解密内容
	 * 
	 * @return 解密结果
	 * @throws Exception
	 */
	public static String decode(String key, String charsetName, String value) throws Exception {
		
		byte[] bVal = Base64.decodeBase64(value);
		byte[] bKey = Base64.decodeBase64(key);

		SecretKey secretKey = new SecretKeySpec(bKey, ALGORITHM);
		Cipher cipher = Cipher.getInstance(TRANSFORM);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);

		byte[] outBytes = cipher.doFinal(bVal);
		return new String(outBytes, charsetName);
	}
	
	/**
	 * 生成加密KEY
	 * 
	 * @return 加密Key
	 * @throws Exception
	 */
	public static String generateKey() throws Exception {

		KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);
		kg.init(new SecureRandom());
		SecretKey secretKey = kg.generateKey();

		return Base64.encodeBase64String(secretKey.getEncoded()).replaceAll("\r|\n", "");
	}
}
