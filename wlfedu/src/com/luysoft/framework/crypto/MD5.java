package com.luysoft.framework.crypto;

import java.security.MessageDigest;

/**
 * MD5散列
 */
public class MD5 {

	/**
	 * MD5散列
	 * 
	 * @param target 目标字符串 
	 * @return 散列结果
	 */
	public static String digest(String target) {
		if (null == target || "".equals(target)) return "";
		return bytesToMD5(target.getBytes());
	}

	// 把字节数组转成16进位制数
	private static String bytesToHex(byte[] bytes) {
		StringBuffer md5str = new StringBuffer();
		//把数组每一字节换成16进制连成md5字符串
		int digital;
		for (int i = 0; i < bytes.length; i++) {
			 digital = bytes[i];
			if(digital < 0) {
				digital += 256;
			}
			if(digital < 16){
				md5str.append("0");
			}
			md5str.append(Integer.toHexString(digital));
		}
		return md5str.toString();
	}
	
	// 把字节数组转换成md5
	private static String bytesToMD5(byte[] input) {
		String md5str = null;
		try {
			//创建一个提供信息摘要算法的对象，初始化为md5算法对象
			MessageDigest md = MessageDigest.getInstance("MD5");
			//计算后获得字节数组
			byte[] buff = md.digest(input);
			//把数组每一字节换成16进制连成md5字符串
			md5str = bytesToHex(buff);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5str;
	}
}
