package com.hanains.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/")
	public String index(){
		System.out.println("들어왓음");
		return "/main/index";
	}
}
