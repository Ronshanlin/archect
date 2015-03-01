/**
 * copyright@all rights belong to shazl
 *
 * creater: shanlin
 * file name: UserInfoService.java
 * create time: 2014-11-2 下午1:28:39
 * version: v1.0
 */
package com.shanlin.demo.inf;

/**
 * 
 * @author shanlin
 */
public interface UserInfoService {
	/**
	 * 根据会员编码获取会员名称
	 * 
	 * @param uno
	 * @return
	 */
	public String queryUname(String uno);
}
