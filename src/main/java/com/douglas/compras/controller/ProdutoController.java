package com.douglas.compras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douglas.compras.domain.Produto;
import com.douglas.compras.service.ProdutoService;

@Controller
@RequestMapping(value="/produtos")
public class ProdutoController {
	@Autowired
	private ProdutoService produtoService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {		
		model.addAttribute("produtos", produtoService.findAll());
		
		return "produto/produtos";
	}
	
	@RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
	public String find(@PathVariable Integer id, Model model) {
		Produto produto = produtoService.find(id);
		
		if (produto == null) {
			model.addAttribute("produto", new Produto());
			
			return "produto/findProduto";
		}
		
		model.addAttribute("produto", produto);
		
		return "produto/findProduto";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable Integer id, Model model) {
		Produto produto = produtoService.find(id);
		
		if (produto == null) {
			model.addAttribute("deleteProduto", "Não existe");	
			
			return "produto/deleteProduto";
		}
		
		produtoService.delete(id);
		
		model.addAttribute("deleteProduto", "Deletado");
		
		return "produto/deleteProduto";
	}
	
}
