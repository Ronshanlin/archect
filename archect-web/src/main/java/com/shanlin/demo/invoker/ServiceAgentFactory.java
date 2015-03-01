/**
 * copyright@all rights belong to shazl
 *
 * @author shanlin
 * file name: ServiceAgentImpl.java
 * create time: 2014年11月29日 下午6:24:33
 * version: v1.0
 */
package com.shanlin.demo.invoker;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shanlin.demo.annotation.OpenApiMethod;
import com.shanlin.demo.utils.StringUtil;

/**
 * @author shanlin
 *
 */
@Service
public class ServiceAgentFactory implements ServiceAgent {
	@Autowired
	UserInfoServiceProxy userInfoServiceProxy;
	
	public static final String DOT = ".";
	/* (non-Javadoc)
	 * @see com.shanlin.demo.Hanlder.ServiceAgent#invoke(java.lang.String, java.lang.String[])
	 */
	public String invoke(String reqMethod, Object... args) throws Exception {
		int lastDot = reqMethod.lastIndexOf(DOT);
		String beanAnotationName = reqMethod.substring(0, lastDot);
		String methodName = reqMethod.substring(++lastDot);
		
		System.out.println("annotation="+beanAnotationName+"; methodName="+methodName);
		
		userInfoServiceProxy.validate((String)args[0]);
		
		
		Class<?> clazz = OpenApiServiceDyFactory.getServiceByName(beanAnotationName);
		
		if (clazz == null) {
			return null;
		}
		System.out.println("bean name:"+clazz.getName());
		
		Method method = this.getMethod(methodName, clazz);
		
		if (method == null) {
			return null;
		}
		
		return (String) method.invoke(clazz.newInstance(), args);
	}
	
	/**
	 * 不支持重载方法
	 * 
	 * @param methodName
	 * @param clazz
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	private Method getMethod(String methodName, Class<?> clazz) throws 
					NoSuchMethodException, SecurityException{
		if (methodName == null || methodName.equals("toString") ||
				methodName.equals("hashCode") || methodName.equals("equals")) {
			return null;
		}
		
		for (Method tempMethod:clazz.getDeclaredMethods()) {
			if (!tempMethod.isAnnotationPresent(OpenApiMethod.class)) {
				continue;
			}
			
			String alian = tempMethod.getAnnotation(OpenApiMethod.class).value();
			if ((StringUtil.isNotBlank(alian) && methodName.equals(alian)) ||
					methodName.equals(tempMethod.getName())) {
				return tempMethod;
			}
		}
		
		return null;
	}
}
