/**
 * copyright@all rights belong to shazl
 *
 * @author shanlin
 * file name: OpenApiMethod.java
 * create time: 2014年11月29日 下午6:44:10
 * version: v1.0
 */
package com.shanlin.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author shanlin
 *
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OpenApiMethod {
	/**
	 * 昵称
	 */
	String value() default "";
}
