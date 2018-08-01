package com.douglas.compras.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	//  inject via application.properties
	//	@Value("${welcome.message:test}")
	//	private String message = "Hello World";

	@RequestMapping("/oi")
	public String start(Map<String, Object> model) {
		//model.put("message", this.message);
		return "login";
	}

}

