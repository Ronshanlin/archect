/**
 * copyright@all rights belong to shazl
 *
 * @author shanlin
 * file name: DetectOpenApiServiceHanlder.java
 * create time: 2014年11月23日 下午1:17:20
 * version: v1.0
 */
package com.shanlin.demo.invoker;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.management.openmbean.KeyAlreadyExistsException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import com.shanlin.demo.annotation.OpenApiService;

/**
 * @author shanlin
 */
public class OpenApiServiceFactory extends WebApplicationObjectSupport implements InitializingBean{
	Logger logger = LoggerFactory.getLogger(OpenApiServiceFactory.class);
	
	private static final Map<String, Object> openApiServiceCache = 
			Collections.synchronizedMap(new HashMap<String, Object>()); 
	
	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
		this.detectHandlers();
	}
	
	public void detectHandlers() throws Exception{
		ApplicationContext context = super.getApplicationContext();
		String[] beanNames = context.getBeanNamesForAnnotation(OpenApiService.class);
		
		logger.info("beanNames:"+beanNames);
		
		for (String name : beanNames) {
			String annotationValue = context.getBean(name).getClass().getAnnotation(OpenApiService.class).value();
			Object obj = context.getBean(name);
			
			if (obj.getClass().isInterface()) {
				throw new Exception("the annotation \"OpenApiService\" does not spuport interface!");
			}
			
			if (!openApiServiceCache.containsKey(annotationValue)) {
				openApiServiceCache.put(annotationValue, obj);
			}else {
				throw new KeyAlreadyExistsException("it is alreay exist:"+annotationValue);
			}
		}
	}
	
	public static Object getServiceByName(String beanName){
		return OpenApiServiceFactory.openApiServiceCache.get(beanName);
	}
}
