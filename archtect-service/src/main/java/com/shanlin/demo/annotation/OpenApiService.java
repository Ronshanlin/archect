/**
 * copyright@all rights belong to shazl
 *
 * @author shanlin
 * file name: OpenApiService.java
 * create time: 2014年11月23日 下午12:51:25
 * version: v1.0
 */
package com.shanlin.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <b>注：</b>此注解用于获取class，获取实例后将可能执行其中<br>
 * 的一些方法，但请注意：被执行的方法的返回值会被强制转为{@link String}
 * 
 * @author shanlin
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OpenApiService {
	/**
	 * 名字
	 */
	String value();
	/**
	 * 接口
	 */
	Class<?> clazz();
}
