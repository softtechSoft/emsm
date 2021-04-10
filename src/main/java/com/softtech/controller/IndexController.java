package com.softtech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/emsm")
public class IndexController {

	@RequestMapping("/hello")
	public String toVue(Model model) {

		return "hello";
	}


}
