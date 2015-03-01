/**
 * 
 */
package com.shanlin.demo.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * json工具类
 * 
 * @author shanlin
 */
public class JsonUtil {
	private static final Gson GSON = new GsonBuilder()
			.enableComplexMapKeySerialization().serializeNulls()
			.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	
	public static String toJson(Object obj){
		return GSON.toJson(obj);
	}
	
	public static <T> T fromJson(String json, Class<T> clazz){
		return GSON.fromJson(json, clazz);
	}
}
