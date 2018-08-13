package com.douglas.compras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.douglas.compras.service.CategoriaService;
import com.douglas.compras.service.ProdutoService;

@Controller
public class LoginController {
	
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private CategoriaService categoriaService; 
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(Model model) {
		return new ModelAndView("login");
	}

//	@RequestMapping(value = "/produtos", method = RequestMethod.GET)
//	public ModelAndView listProdutos(Model model) {
//		return new ModelAndView("init/categorias");
//	}
//	
//	@RequestMapping(value = "/categorias", method = RequestMethod.GET)
//	public ModelAndView listCategorias(Model model) {
//		return new ModelAndView("init/produtos");
//	}
}

//@RequestMapping(value = "/", method = RequestMethod.GET)
//public ModelAndView index(Model model) {
//	return new ModelAndView("index");
//}