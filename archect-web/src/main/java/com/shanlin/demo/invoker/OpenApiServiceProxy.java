/**
 * copyright@all rights belong to shazl
 *
 * @author shanlin
 * file name: OpenApiServiceProxy.java
 * create time: 2014年11月23日 下午2:43:30
 * version: v1.0
 */
package com.shanlin.demo.invoker;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Service;

import com.shanlin.demo.dto.CommonResult;
import com.shanlin.demo.utils.Constants;
import com.shanlin.demo.utils.JsonUtil;

/**
 * @author shanlin
 *
 */
@Service
public class OpenApiServiceProxy extends ApplicationObjectSupport{
	private static final Logger LOGGER = LoggerFactory.getLogger(OpenApiServiceProxy.class);
    /**
     * 功能描述: 转发代理<br>
     *
     * @param name 执行方法名
     * @param args 被代理执行方法的入参
     * @return
     * @throws Exception
     */
    public String invoke(String name, Object... args) throws Exception {
        long startTime = System.nanoTime();
        CommonResult commonResult = null;
        OpenApiServiceBean serviceBean = OpenApiServiceFactory.getServiceBean(name);
        if (serviceBean != null) {
            // 获取class
            Object proxy = super.getApplicationContext().getBean(serviceBean.getBeanName());
            
            // 获取方法
            Method method = serviceBean.getMethod();
            String result = null;
            if (serviceBean.getMethodParamterCount() == Constants.ZERO) {
                result = (String) method.invoke(proxy);
            }else {
                result = (String) method.invoke(proxy, args);
            }
            LOGGER.info("invoke time：{}ns",(System.nanoTime()-startTime));
            return result;
        }
        
        commonResult = new CommonResult();
        commonResult.setIsSuccess(false);
        commonResult.setReturnMsg("没有此方法");
        
        return JsonUtil.toJson(commonResult);
    }
}
