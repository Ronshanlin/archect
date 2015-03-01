/**
 * copyright@all rights belong to shazl
 *
 * @author shanlin
 * file name: LogContextListener.java
 * create time: 2015年1月25日 下午8:12:05
 * version: v1.0
 */
package com.shanlin.demo.listener;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.LoggerFactory;
import com.shanlin.demo.utils.StringUtil;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;

/**
 * @author shanlin
 *
 */
public class LogContextListener implements ServletContextListener{
	private static final Logger LOGGER = Logger.getLogger("LogContextListener");
	private static final String LOGXMLLOCATION="logbackConfigLocation";
	private static final String DEFAULT_LOCATION="logbackConfig.xml";
	private static final String CLASSPATH="WEB-INF/classes/";
	
	
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		try {
			String location = sce.getServletContext().getInitParameter(LOGXMLLOCATION);
			if (StringUtil.isBlank(location)) {
				location = DEFAULT_LOCATION;
			}
			LOGGER.info("the log configration location is:" + location);
			String path = sce.getServletContext().getRealPath(CLASSPATH+location);
			
			LoggerContext logContext = (LoggerContext) LoggerFactory.getILoggerFactory();
			logContext.reset();

			JoranConfigurator joranConfigurator = new JoranConfigurator();
			joranConfigurator.setContext(logContext);
			joranConfigurator.doConfigure(path);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "can not load loggback configration!", e);
		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
