package com.qinfei.common;

import org.apache.commons.codec.digest.DigestUtils;

public class CmsUtils {

	/**
	 * 加盐加密
	 * @param src 明文
	 * @param salt 盐 
	 * @return
	 */
	public static String encry(String src,String salt){
		
		
		
		return  DigestUtils.md5Hex(salt+src+salt);
	}
}
