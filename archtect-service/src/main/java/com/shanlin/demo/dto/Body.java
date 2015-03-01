/**
 * copyright@all rights belong to shazl
 *
 * @author shanlin
 * file name: Body.java
 * create time: 2014年11月23日 下午4:54:15
 * version: v1.0
 */
package com.shanlin.demo.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * @author shanlin
 *
 */
public class Body {
	
	@Length(min=30,max=50,message="限制长度30-40")
	private String custno;
	
	@NotNull(message="name不能为空")
	private String name;
	
	@NotNull(message="sunbody null")
	private SunBody sunBody;
	
	public String getCustno() {
		return custno;
	}

	public void setCustno(String custno) {
		this.custno = custno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SunBody getSunBody() {
		return sunBody;
	}

	public void setSunBody(SunBody sunBody) {
		this.sunBody = sunBody;
	}
	
}
