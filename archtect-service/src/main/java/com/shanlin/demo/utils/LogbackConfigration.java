/**
 * copyright@all rights belong to shazl
 *
 * creater: shanlin
 * file name: LogbackConfigration.java
 * create time: 2014-11-2 下午6:32:40
 * version: v1.0
 */
package com.shanlin.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;

/**
 * logback 配置
 * 
 * @author shanlin
 */
public class LogbackConfigration {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private String location;

	public void init() {
		try {
			logger.info("the log configration location is:" + this.location);
			Resource resource = new ClassPathResource(location);
			
			LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
			context.reset();

			JoranConfigurator joranConfigurator = new JoranConfigurator();
			joranConfigurator.setContext(context);
			joranConfigurator.doConfigure(resource.getURL());
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error("can not load loggback configration!");
		}
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
}
