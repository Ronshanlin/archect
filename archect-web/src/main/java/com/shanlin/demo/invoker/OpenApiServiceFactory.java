/**
 * copyright@all rights belong to shazl
 *
 * @author shanlin
 * file name: DetectOpenApiServiceHanlder.java
 * create time: 2014年11月23日 下午1:17:20
 * version: v1.0
 */
package com.shanlin.demo.invoker;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.management.openmbean.KeyAlreadyExistsException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import com.shanlin.demo.annotation.OpenApiMethod;
import com.shanlin.demo.annotation.OpenApiService;

/**
 * @author shanlin
 */
public class OpenApiServiceFactory extends WebApplicationObjectSupport implements InitializingBean{
	Logger logger = LoggerFactory.getLogger(OpenApiServiceFactory.class);
    
    private static final Map<String, OpenApiServiceBean> serviceCache = new HashMap<String, OpenApiServiceBean>();
    public static final String DOT = ".";
    
    private OpenApiServiceFactory(){}
    
    /*
     * (non-Javadoc)
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    public void afterPropertiesSet() throws Exception {
        this.detectOpenApiService();
    }
    
    /**
     * 检测代理类
     *
     * @throws Exception
     */
    public void detectOpenApiService() throws Exception {
        // 取得上下文
        ApplicationContext context = super.getApplicationContext();
        String[] beanNames = context.getBeanNamesForType(Object.class);
        
        if (logger.isDebugEnabled()) {
            logger.debug("beanNames:" + beanNames);
        }
        
        if (beanNames == null || beanNames.length==0) {
            return;
        }
        
        for (String name : beanNames) {
            Class<?> clazz = context.getBean(name).getClass();
            
            if (!clazz.isAnnotationPresent(OpenApiService.class)) {
                continue;
            }
            
            if (clazz.isInterface()) {
                throw new Exception("the annotation \"OpenApiService\" does not spuport interface!");
            }
            
            this.getMethod(clazz, name);
        }
    }
    
    /**
     * 功能描述: 获取可执行方法<br>
     * alias+method作为key,可避免不同类拥有相同的alien
     *
     * @param serviceBeanClass
     */
    private void getMethod(Class<?> serviceBeanClass, String beanName){
        OpenApiService annotation = serviceBeanClass.getAnnotation(OpenApiService.class);
        if (StringUtils.isBlank(annotation.alias())) {
            throw new NullPointerException("the annotation.alias() can't be null on the class:"+serviceBeanClass);
        }
        
        Map<String, Object> tempMethodMap = new HashMap<String, Object>();
        
        // 获取方法
        for (Method tempMethod : serviceBeanClass.getDeclaredMethods()) {
            if (!tempMethod.isAnnotationPresent(OpenApiMethod.class)) {
                continue;
            }
            
            // 首先取注解value,如果空，则取method的名字
            OpenApiMethod openApiMethod = tempMethod.getAnnotation(OpenApiMethod.class);
            String methodName = openApiMethod.value();
            if (StringUtils.isBlank(openApiMethod.value())) {
                methodName = tempMethod.getName();
            }
            
            // 是否存在相同方法（重载方法可加注解，来区分为不同方法）
            if (tempMethodMap.containsKey(methodName)) {
                throw new KeyAlreadyExistsException("the method is alreay exist:" + methodName);
            }else {
                tempMethodMap.put(methodName, tempMethod);
            }
            
            // 存入缓存
            String key = annotation.alias().concat(".").concat(methodName);
            if (serviceCache.containsKey(key)) {
                throw new KeyAlreadyExistsException("it is alreay exist:" + key);
            } else {
                OpenApiServiceBean serviceBean = new OpenApiServiceBean();
                serviceBean.setBeanName(beanName);
                serviceBean.setMethod(tempMethod);
                serviceBean.setMethodParamterCount(tempMethod.getGenericParameterTypes().length);
                serviceCache.put(key, serviceBean);
            }
        }
    }
    
    /**
     * 根据注解取得beanName和方法
     * 
     * @param key
     * @return
     */
    public static OpenApiServiceBean getServiceBean(String key) {
        return serviceCache.get(key);
    }
}
