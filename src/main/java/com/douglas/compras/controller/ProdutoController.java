package com.douglas.compras.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.douglas.compras.domain.Categoria;
import com.douglas.compras.domain.Produto;
import com.douglas.compras.dto.ProdutoDTO;
import com.douglas.compras.service.CategoriaService;
import com.douglas.compras.service.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoController {
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private CategoriaService categoriaService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView list(Model model) {		
		model.addAttribute("produtos", produtoService.findAll());
		
		return new ModelAndView("produto/showAll");
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable Integer id, Model model) {		
		produtoService.delete(id);
		
		model.addAttribute("produtos", produtoService.findAll());
		
		return new ModelAndView("produto/showAll");
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public ModelAndView insert(Model model) { 
		model.addAttribute("produtoRegister", new ProdutoDTO());
 
	    return new ModelAndView("produto/register");
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(Model model, @PathVariable Integer id) {		
		Produto produto = produtoService.find(id);
		ProdutoDTO produtoDTO = new ProdutoDTO();
		produtoDTO.setId(produto.getId());
		produtoDTO.setNome(produto.getNome());
		produtoDTO.setPreco(produto.getPreco());
		produtoDTO.setCategoria(produto.getCategoria().getId());
		
		model.addAttribute("produtoUpdate", produtoDTO);
		
		return new ModelAndView("produto/update");
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(Model model, 
								@ModelAttribute @Valid ProdutoDTO produtoUpdate) {
		
		Categoria categoria = categoriaService.find(produtoUpdate.getCategoria());
		
		if (categoria != null) {
			Produto produto = new Produto(produtoUpdate.getId(), 
											produtoUpdate.getNome(), 
											produtoUpdate.getPreco(),
											categoria);
			
			produto = produtoService.update(produto);
		}	
		
		model.addAttribute("produtos", produtoService.findAll());
		
		return new ModelAndView("produto/showAll");
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(Model model, 
							@ModelAttribute @Valid ProdutoDTO produtoRegister) {
		
		Categoria categoria = categoriaService.find(produtoRegister.getCategoria());
		
		if (categoria != null) {
			Produto produto = new Produto(null, 
										produtoRegister.getNome(), 
										produtoRegister.getPreco(),
										categoria);

			produto = produtoService.insert(produto);
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(produto.getId()).toUri();		
			ResponseEntity.created(uri).build();
		}	
		
		model.addAttribute("produtos", produtoService.findAll());
		
		return new ModelAndView("produto/showAll");
	}
	
}
