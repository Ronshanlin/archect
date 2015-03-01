/**
 * copyright@all rights belong to shazl
 *
 * @author shanlin
 * file name: OpenApiServiceDynamicProxy.java
 * create time: 2014年11月23日 下午6:51:55
 * version: v1.0
 */
package com.shanlin.demo.invoker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author shanlin
 *
 */
public class OpenApiServiceDynamicProxy{
	public Object getInstance(Class<?> target){
		return Proxy.newProxyInstance(target.getClassLoader(), 
				new Class[]{target}, new OpenApiHandler());
	}
	
	private class OpenApiHandler implements InvocationHandler{

		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			System.out.println("i'm proxy!");
			return null;
		}
		
	}
}
