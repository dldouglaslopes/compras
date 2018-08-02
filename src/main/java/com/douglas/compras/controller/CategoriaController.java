package com.douglas.compras.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douglas.compras.domain.Categoria;
import com.douglas.compras.service.CategoriaService;

@Controller
@RequestMapping(value="/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {		
		model.addAttribute("categorias", categoriaService.findAll());
		
		return "categoria/categorias";
	}
	
	@RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
	public String find(@PathVariable Integer id, Model model) {
		Categoria categoria = categoriaService.find(id);
		
		if (categoria == null) {
			model.addAttribute("categoria", new Categoria());
			
			return "categoria/findCategoria";
		}
		
		model.addAttribute("categoria", categoria);
		
		return "categoria/findCategoria";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable Integer id, Model model) {
		Categoria categoria = categoriaService.find(id);
		
		if (categoria == null) {
			model.addAttribute("deleteCategoria", "Não existe");	
			
			return "categoria/deleteCategoria";
		}
		
		else {
			if (categoria.getProdutos().size() < 1) {
				model.addAttribute("deleteCategoria", "Essa categoria possue produtos");	
				
				return "categoria/deleteCategoria";
			}
			
			categoriaService.delete(id);
			
			model.addAttribute("deleteCategoria", "Deletado");
			
			return "categoria/deleteCategoria";
		}	
	}
}
