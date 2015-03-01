/**
 * copyright@all rights belong to shazl
 *
 * @author shanlin
 * file name: OpenApiServiceRouter.java
 * create time: 2014年11月23日 下午2:40:01
 * version: v1.0
 */
package com.shanlin.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.shanlin.demo.dto.Body;
import com.shanlin.demo.dto.CommonResult;
import com.shanlin.demo.dto.Input;
import com.shanlin.demo.dto.SysInfo;
import com.shanlin.demo.invoker.OpenApiServiceProxy;
import com.shanlin.demo.utils.JsonUtil;

/**
 * @author shanlin
 *
 */
@Controller
@RequestMapping("/open")
public class OpenApiServiceRouter extends BaseController{
	private static final Logger LOGGER = LoggerFactory.getLogger(OpenApiServiceRouter.class);
	
	@Autowired
	private OpenApiServiceProxy proxy;
	
	@RequestMapping(value="/router", method=RequestMethod.POST)
	public String route(HttpServletRequest request, HttpServletResponse response){
		String result = null;
		try {
//			InputStream stream = request.getInputStream();
//			StringBuffer sb = new StringBuffer();
//			byte[] buf = new byte[1024];
//			while (stream.read(buf) != -1) {
//				sb.append(buf.toString());
//			}
//			
//			System.out.println("request:"+sb);
			
			String params = request.getParameter("params");
			LOGGER.info("request datas:{}", params);
			
			SysInfo sysInfo = JsonUtil.fromJson(params, SysInfo.class);
			
			result = proxy.invoke(sysInfo.getName(), params);
		} catch (Exception e) {
			LOGGER.error("发生异常", e);
			CommonResult commonResult = new CommonResult();
			commonResult.setIsSuccess(false);
			commonResult.setReturnMsg("系统异常");
			
			result = JsonUtil.toJson(commonResult);
		} 
		
		return super.ajax(response, result);
	}
	
	
	
	
	public static void main(String[] args) {
		Gson gson = new GsonBuilder().create();
		SysInfo sysInfo = new SysInfo();
		sysInfo.setAppKey("apppppp");
		sysInfo.setSign("e1t2v2er6f1");
		sysInfo.setTimestamp(new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date()));
		sysInfo.setName("open.userInfo.queryUname");
		
		Body body = new Body();
		body.setCustno("123123");
		
		Input input = new Input();
		input.setSysInfo(sysInfo);
		input.setBody(body);
		
		String json = gson.toJson(input);
		System.out.println(json);
		
		JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
		JsonElement sysJsonElement = jsonObject.get("sysInfo");
		SysInfo sysInfo2 = gson.fromJson(sysJsonElement, SysInfo.class);
		
		System.out.println(sysInfo2.getName());
	}
}

