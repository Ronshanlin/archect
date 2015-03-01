/**
 * copyright@all rights belong to shazl
 *
 * @author shanlin
 * file name: ApiDispecherServlet.java
 * create time: 2015年1月25日 下午7:57:33
 * version: v1.0
 */
package com.shanlin.demo.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author shanlin
 */
public class ApiDispatcherServlet extends HttpServlet{
	private static final long serialVersionUID = 5010316546228178912L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
		        throws ServletException, IOException{
		 ServletContext context = request.getServletContext();
		 ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
		 
		 applicationContext.getBean("");
	}
}
