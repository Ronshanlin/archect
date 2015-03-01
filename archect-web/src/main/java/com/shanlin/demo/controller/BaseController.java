/**
 * 
 */
package com.shanlin.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author shanlin
 *
 */
public class BaseController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@ExceptionHandler
	protected ModelAndView exceptionHandle(Exception exception){
		ModelAndView mav = new ModelAndView("");
		
		logger.error("contoller exception", exception);
		
		return mav;
	}
	
	protected String ajax(HttpServletResponse response, String json){
		PrintWriter writer = null;
		try {
			response.setContentType("text/html");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			
			writer = response.getWriter();
			writer.write(json);
		} catch (IOException e) {
			logger.error("ajax writer error", e);
		} finally{
			if (writer != null) {
				writer.close();
			}
		}
		
		return null;
	}
}
