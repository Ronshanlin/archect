package com.shanlin.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shanlin.demo.inf.UserInfoService;

@RequestMapping("/")
@Controller
public class HomeController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UserInfoService userInfoService;
	
	@RequestMapping("index")
	public String index(){
		return null;
	}
	
	@RequestMapping("error")
	public String error(Model model){
		logger.info("======================");
		userInfoService.queryUname("a");
		model.addAttribute("errMsg", "wrong!");
		return "error.ftl";
	}
}
