/**
 * 
 */
package com.shanlin.demo.invoker;

import java.lang.reflect.Method;

/**
 * @author shanlin
 *
 */
public class OpenApiServiceBean {
    /**
     * bean名
     */
    private String beanName;
    /**
     * 方法
     */
    private Method method;
    /**
     * 方法参数个数
     */
    private int methodParamterCount;
    
    public String getBeanName() {
        return beanName;
    }
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
    public Method getMethod() {
        return method;
    }
    public void setMethod(Method method) {
        this.method = method;
    }
    public int getMethodParamterCount() {
        return methodParamterCount;
    }
    public void setMethodParamterCount(int methodParamterCount) {
        this.methodParamterCount = methodParamterCount;
    }
}
