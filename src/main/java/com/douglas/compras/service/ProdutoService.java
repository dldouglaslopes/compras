package com.douglas.compras.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.compras.domain.Produto;
import com.douglas.compras.repository.ProdutoRepositorio;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepositorio produtoRepositorio;
	
	public List<Produto> findAll() {
		List<Produto> produtos = produtoRepositorio.findAll();
		
		return produtos;
	}
	
	public Produto find(Integer id) {
		Optional<Produto> produto = produtoRepositorio.findById(id);
		
		return produto.orElse(null);
	}

	public void delete(Integer id) {
		if (find(id) != null) {
			produtoRepositorio.deleteById(id);
		}		
	}

	public Produto update(Produto produto) {		
		Produto novoProduto = find(produto.getId());
		
		novoProduto.setNome(produto.getNome());
		novoProduto.setCategoria(produto.getCategoria());
		novoProduto.setPreco(produto.getPreco());
		//novoProduto.setClientes(produto.getClientes());
		
		return produtoRepositorio.save(novoProduto);
	}	
}
