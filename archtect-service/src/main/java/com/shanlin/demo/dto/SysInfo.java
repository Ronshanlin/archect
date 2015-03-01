/**
 * copyright@all rights belong to shazl
 *
 * @author shanlin
 * file name: SysInfo.java
 * create time: 2014年11月23日 下午4:49:38
 * version: v1.0
 */
package com.shanlin.demo.dto;

import java.sql.Timestamp;

/**
 * @author shanlin
 *
 */
public class SysInfo {
	/**
	 * 版本号
	 */
	private String v;
	private String name;
	private String sign;
	private String appKey;
	private String timestamp;
	
	public String getV() {
		return v;
	}
	public void setV(String v) {
		this.v = v;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
