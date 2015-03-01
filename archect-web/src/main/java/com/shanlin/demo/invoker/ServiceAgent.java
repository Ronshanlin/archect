/**
 * copyright@all rights belong to shazl
 *
 * @author shanlin
 * file name: ServiceAgent.java
 * create time: 2014年11月29日 下午6:22:43
 * version: v1.0
 */
package com.shanlin.demo.invoker;

/**
 * @author shanlin
 *
 */
public interface ServiceAgent {
	String invoke(String method, Object... args) throws Exception;
}
