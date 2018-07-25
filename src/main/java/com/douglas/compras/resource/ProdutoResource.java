package com.douglas.compras.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.douglas.compras.domain.Produto;
import com.douglas.compras.service.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Produto> list() {		
		return produtoService.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		Produto produto = produtoService.find(id);
		
		return ResponseEntity.ok().body(produto);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Produto> delete(@PathVariable Integer id) {
		produtoService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Produto> update(@Valid @RequestBody Produto produto) {
		Produto novoProduto = new Produto();
		novoProduto.setId(produto.getId());
		novoProduto = produtoService.update(produto);
		
		return ResponseEntity.noContent().build();
	}
}
