/**
 * copyright@all rights belong to shazl
 *
 * @author shanlin
 * file name: OpenApiServiceProxy.java
 * create time: 2014年11月23日 下午2:43:30
 * version: v1.0
 */
package com.shanlin.demo.invoker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.stereotype.Service;

import com.shanlin.demo.annotation.OpenApiMethod;

/**
 * @author shanlin
 *
 */
@Service
public class OpenApiServiceProxy{
	public static final String DOT = ".";
	
	public String invoke(String name, Object... requestBody) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		int lastDot = name.lastIndexOf(DOT);
		String beanAnotationName = name.substring(0, lastDot);
		String methodName = name.substring(++lastDot);
		
		System.out.println("classname="+beanAnotationName+"; methodName="+methodName);
		
		// 获取实例
		Object serviceBean = OpenApiServiceFactory.getServiceByName(beanAnotationName);
		// 获取待执行方法
		Method[] methods = serviceBean.getClass().getMethods();
		Method method = null;
		for (Method tempMethod : methods) {
			if (!tempMethod.isAnnotationPresent(OpenApiMethod.class)) {
				continue;
			}
			
			if (methodName.equals(tempMethod.getAnnotation(OpenApiMethod.class).value())) {
				method = tempMethod;
				break;
			}
		}
		
		if (method == null) {
			throw new NoSuchMethodException("there is no such a method:" + methodName);
		}
		
		String result = (String)method.invoke(serviceBean, requestBody);
		
		return result;
	}
}
