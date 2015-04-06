/**
 * 
 */
package com.shanlin.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import weibo4j.model.WeiboException;

import com.shanlin.demo.weibo.AuthorizeCallback;

/**
 * @author shanlin
 *
 */
@Controller
@RequestMapping("/auth")
public class AuthorizeCallbackController extends BaseController{
	
	@Autowired
	AuthorizeCallback callback;
	
	@RequestMapping("/cb")
	public String get(HttpServletRequest request){
		try {
			callback.exec(request.getParameter("code"), request.getParameter("code"));
		} catch (WeiboException e) {
			logger.error("执行回调失败", e);
		}
		return null;
	}
}
