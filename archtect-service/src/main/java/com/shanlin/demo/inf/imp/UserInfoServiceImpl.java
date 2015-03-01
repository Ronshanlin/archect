/**
 * copyright@all rights belong to shazl
 *
 * @author shanlin
 * file name: UserInfoServiceImpl.java
 * create time: 2014-11-2 下午7:43:29
 * version: v1.0
 */
package com.shanlin.demo.inf.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shanlin.demo.annotation.OpenApiMethod;
import com.shanlin.demo.annotation.OpenApiService;
import com.shanlin.demo.inf.UserInfoService;

/**
 * @author shanlin
 */
@Service
@OpenApiService(value="open.userInfo", clazz=UserInfoServiceImpl.class)
public class UserInfoServiceImpl implements UserInfoService{
	Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * @param uno
	 */
	@OpenApiMethod()
	public String queryUname(String uno) {
		logger.info("open.userInfo.queryUname->body:"+uno);
		return null;
	}
	
	/**
	 * @param uno
	 */
	@OpenApiMethod("queryRichInfo")
	public String queryRichInfo(String uno) {
		System.out.println("open.userInfo.queryUname->queryVipInfo:"+uno);
		return null;
	}
}
