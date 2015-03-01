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
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

import com.shanlin.demo.annotation.OpenApiService;

/**
 * @author shanlin
 */
@Component
public class OpenApiServiceDyFactory extends ApplicationObjectSupport implements InitializingBean{
	Logger logger = LoggerFactory.getLogger(OpenApiServiceDyFactory.class);
	
	private static final Map<String, Class<?>> openApiServicenCache = 
			Collections.synchronizedMap(new HashMap<String, Class<?>>()); 
	
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
		
		for (String beanName : beanNames) {
			if (!context.getBean(beanName).getClass().isAnnotationPresent(OpenApiService.class)) {
				continue;
			}
			OpenApiService openApiService = context.getBean(beanName).getClass().getAnnotation(OpenApiService.class);
			String annotationValue = openApiService.value();
			
			if (!openApiServicenCache.containsKey(annotationValue)) {
				openApiServicenCache.put(annotationValue, openApiService.clazz());
			}else {
				throw new KeyAlreadyExistsException("it is alreay exist:"+annotationValue);
			}
		}
	}
	
	public static Class<?> getServiceByName(String beanAnnotationName){
		return OpenApiServiceDyFactory.openApiServicenCache.get(beanAnnotationName);
	}
}
