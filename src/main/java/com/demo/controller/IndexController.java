package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	
	/**
	 * 首页
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(){
		return "index.html";
	}

}
