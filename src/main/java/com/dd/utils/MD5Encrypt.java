package com.dd.utils;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MD5Encrypt {

	private static final Logger logger = LoggerFactory.getLogger(MD5Encrypt.class);
	
	public static String EncryptPasswordByMd5(String originPwd) {
		byte[] buf = originPwd.getBytes();
		MessageDigest md5;
		StringBuilder sb = new StringBuilder();
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(buf);
			byte[] tmp = md5.digest();
			for(byte b : tmp) {
				sb.append(Integer.toHexString(b & 0xff));
			}
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return sb.toString();
	}

}