/**
 * copyright@all rights belong to shazl
 *
 * @author shanlin
 * file name: ParamsValidation.java
 * create time: 2014年11月30日 下午8:35:43
 * version: v1.0
 */
package com.shanlin.demo.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author shanlin
 *
 */
@Component
public class ParamsValidation<T> {
	private static final Gson gson = new GsonBuilder().disableHtmlEscaping().create();
	
	@Autowired
	private LocalValidatorFactoryBean validatorFactoryBean;
	
	public Errors validate(String json, Class<T> targetClass){
		Assert.notNull(json,"json数据不能为空");
		
		T target = gson.fromJson(json, targetClass);
		
		// 目前还不确定Errors的几个实现有哪些区别
		Errors errors = /*new DirectFieldBindingResult(target, targetClass.getName())*/
				new BeanPropertyBindingResult(target, targetClass.getName());
		// 执行校验
		ValidationUtils.invokeValidator(validatorFactoryBean, target, errors);
		
		for(FieldError fieldError: errors.getFieldErrors()){
			System.out.println(fieldError.getField()+":"+fieldError.getDefaultMessage());
		}
		
		return errors;
	}
	
}
