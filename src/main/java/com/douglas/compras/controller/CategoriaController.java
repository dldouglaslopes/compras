package com.douglas.compras.controller;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.douglas.compras.domain.Categoria;
import com.douglas.compras.domain.Produto;
import com.douglas.compras.service.CategoriaService;

@RestController
@RequestMapping(value="/categories")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoryService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Categoria>> findAll() {
		List<Categoria> categories  = categoryService.findAll();
		
		return ResponseEntity.ok().body(categories);
	}	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> findOne(@PathVariable Integer id) {
		Categoria category  = categoryService.find(id);
		
		return ResponseEntity.ok().body(category);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody @Valid Categoria categoryRegister) {
		
		Categoria category = new Categoria(null, 
									categoryRegister.getNome());
		
		category = categoryService.insert(category);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}").buildAndExpand(category.getId()).toUri();		
		ResponseEntity.created(uri).build();
				
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@ModelAttribute @Valid Categoria categoryUpdate) {
		
		Categoria categoria = new Categoria(categoryUpdate.getId(), 
										categoryUpdate.getNome());
	
		categoria = categoryService.update(categoria);
			
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {	
		Categoria category = categoryService.find(id);
		
		if (category.getProdutos().isEmpty()) {
			categoryService.delete(id);
		}		
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public  ResponseEntity<List<Produto>> findProducts(@PathVariable Integer id) {		
		List<Produto> products = categoryService.products(id);
		
		return ResponseEntity.ok().body(products);
	}
}
