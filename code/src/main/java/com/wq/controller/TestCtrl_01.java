package com.wq.controller;

import com.wq.dto.TestDTO;
import com.wq.web.servlet.annotation.AjaxResponse;
import com.wq.web.servlet.annotation.Controller;
import com.wq.web.servlet.annotation.RequestMapping;
import com.wq.web.servlet.vo.ModelAndView;

@Controller
@RequestMapping("/test01")
public class TestCtrl_01 {

	@RequestMapping("/method01")
	public ModelAndView method011(TestDTO dto) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/test/page01");
		view.addModle("obj", dto);
		return view;
	}

	@RequestMapping("/method02")
	@AjaxResponse
	public TestDTO method012(TestDTO dto) {
		return dto;
	}
}
