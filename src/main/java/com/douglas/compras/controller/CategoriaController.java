package com.douglas.compras.controller;
import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.douglas.compras.domain.Categoria;
import com.douglas.compras.service.CategoriaService;

@Controller
@RequestMapping(value="/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(Model model) {		
		model.addAttribute("categorias", categoriaService.findAll());
		
		return "categoria/showAll";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable Integer id, Model model) {	
		Categoria categoria = categoriaService.find(id);
		
		if (categoria.getProdutos().isEmpty()) {
			categoriaService.delete(id);
		}		
		
		model.addAttribute("categorias", categoriaService.findAll());
		
		return "categoria/showAll";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public ModelAndView insert(Model model) { 
		model.addAttribute("categoriaRegister", new Categoria());
 
	    return new ModelAndView("categoria/register");
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(Model model, @PathVariable Integer id) {		
		Categoria categoria = categoriaService.find(id);
		categoria.setId(categoria.getId());
		categoria.setNome(categoria.getNome());
		
		model.addAttribute("categoriaUpdate", categoria);
		
		return new ModelAndView("categoria/update");
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(Model model, 
								@ModelAttribute @Valid Categoria categoriaUpdate) {
		
		Categoria categoria = new Categoria(categoriaUpdate.getId(), 
										categoriaUpdate.getNome());
	
		categoria = categoriaService.update(categoria);
		
		model.addAttribute("categorias", categoriaService.findAll());
	
		return new ModelAndView("categoria/showAll");
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(Model model, 
							@ModelAttribute @Valid Categoria categoriaRegister) {
		
		Categoria categoria = new Categoria(null, 
									categoriaRegister.getNome());
		
		categoria = categoriaService.insert(categoria);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}").buildAndExpand(categoria.getId()).toUri();		
		ResponseEntity.created(uri).build();
		
		model.addAttribute("categorias", categoriaService.findAll());
		
		return new ModelAndView("categoria/showAll");
	}
	
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public  ModelAndView listProducts(Model model, @PathVariable Integer id) {		
		model.addAttribute("produtos", categoriaService.products(id));
		
		return new ModelAndView("categoria/products");
	}
}
