package com.douglas.compras.controller;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/categorias")
public class CategoriaController {
	
	@RequestMapping("/oi")
	public String start(Map<String, Object> model) {
		return "categorias";
	}
}
