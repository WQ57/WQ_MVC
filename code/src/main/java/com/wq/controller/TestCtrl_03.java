package com.wq.controller;

import com.wq.web.servlet.annotation.AjaxResponse;
import com.wq.web.servlet.annotation.Controller;
import com.wq.web.servlet.annotation.RequestMapping;

@Controller
@RequestMapping("/test03")
public class TestCtrl_03 {

	@RequestMapping("/method01")
	public String method031(String s, Integer i, Long l) {	
		System.out.println("this is method031");
		return "test03Method01";
	}

	@RequestMapping("/method02")
	@AjaxResponse
	public String method032(String s, Integer i, Long l) {	
		System.out.println("this is method032");
		return "test03Method02";
	}
	
}
