/**
 * copyright@all rights belong to shazl
 *
 * @author shanlin
 * file name: StringUtil.java
 * create time: 2014年11月30日 下午7:48:36
 * version: v1.0
 */
package com.shanlin.demo.utils;

/**
 * @author shanlin
 */
public class StringUtil {
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str){
		if (str == null || str == "") {
			return true;
		}
		
		int strLen = str.length();
		int j=0;
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(str.charAt(i))) {
				j++;
			}
		}
		
		if (j < strLen) {
			return false;
		}
		
		return true;
	}
	
	public static boolean isNotBlank(String str){
		return !isBlank(str);
	}
}
