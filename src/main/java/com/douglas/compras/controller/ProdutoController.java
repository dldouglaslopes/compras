package com.douglas.compras.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.douglas.compras.domain.Categoria;
import com.douglas.compras.domain.Produto;
import com.douglas.compras.dto.ProdutoDTO;
import com.douglas.compras.service.CategoriaService;
import com.douglas.compras.service.ProdutoService;

@RestController
@RequestMapping(value="/products")
public class ProdutoController {
	@Autowired
	private ProdutoService productService;
	@Autowired
	private CategoriaService categoryService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> findAll() {		
		List<Produto> products = productService.findAll();
		
		return ResponseEntity.ok().body(products);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> findOne(@PathVariable Integer id) {		
		Produto product = productService.find(id);
		
		return ResponseEntity.ok().body(product);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody @Valid ProdutoDTO productRegister) {
		
		Categoria category = categoryService.find(productRegister.getCategoria());
		
		if (category != null) {
			Produto product = new Produto(null, 
										productRegister.getNome(), 
										productRegister.getPreco(),
										category);

			product = productService.insert(product);
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}").buildAndExpand(product.getId()).toUri();		
			ResponseEntity.created(uri).build();
		
			return ResponseEntity.created(uri).build();
		}	
			
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id,
										@RequestBody @Valid ProdutoDTO productUpdate) {
		
		Categoria category = categoryService.find(productUpdate.getCategoria());
		
		if (category != null) {
			Produto produto = new Produto(productUpdate.getId(), 
											productUpdate.getNome(), 
											productUpdate.getPreco(),
											category);
			
			produto = productService.update(produto);
		}	
		
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Produto> delete(@PathVariable Integer id) {		
		productService.delete(id);
		
		return ResponseEntity.noContent().build();
	}	
}
