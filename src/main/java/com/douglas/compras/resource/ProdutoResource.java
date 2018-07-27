package com.douglas.compras.resource;

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
	public ResponseEntity<Produto> update(@PathVariable Integer id, 
										@Valid @RequestBody Produto novoProduto) {
		
		Produto produto = new Produto(novoProduto.getId(), 
									novoProduto.getNome(), 
									novoProduto.getPreco(), 
									novoProduto.getCategoria());
		
		produto.setId(id);
		produto = produtoService.update(produto);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Produto novoProduto) {
		Produto produto = new Produto(null, 
										novoProduto.getNome(), 
										novoProduto.getPreco(), 
										novoProduto.getCategoria());
		
		produto = produtoService.insert(produto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}").buildAndExpand(produto.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
