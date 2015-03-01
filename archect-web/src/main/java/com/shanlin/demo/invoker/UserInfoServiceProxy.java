/**
 * copyright@all rights belong to shazl
 *
 * @author shanlin
 * file name: UserInfoServiceProxy.java
 * create time: 2014年11月30日 下午9:04:30
 * version: v1.0
 */
package com.shanlin.demo.invoker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shanlin.demo.dto.Body;
import com.shanlin.demo.validation.ParamsValidation;

/**
 * @author shanlin 
 *
 */
@Service
public class UserInfoServiceProxy {
	@Autowired
	ParamsValidation paramsValidation;
	
	public void validate(String json){
		paramsValidation.validate(json, Body.class);
	}
}
