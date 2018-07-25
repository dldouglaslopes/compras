package com.douglas.compras.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.compras.domain.Categoria;
import com.douglas.compras.repository.CategoriaRepositorio;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepositorio categoriaRepositorio;
	
	public List<Categoria> findAll() {
		List<Categoria> categorias = categoriaRepositorio.findAll();
		
		return categorias;
	}
	
	public Categoria find(Integer id) {
		Optional<Categoria> categoria = categoriaRepositorio.findById(id);
		
		return categoria.orElse(null);
	}

	public void delete(Integer id) {
		if (find(id) != null) {
			categoriaRepositorio.deleteById(id);
		}		
	}

	public Categoria update(Categoria categoria) {		
		Categoria novoCategoria = find(categoria.getId());
		
		novoCategoria.setNome(categoria.getNome());
		novoCategoria.setProdutos(categoria.getProdutos());
		
		return categoriaRepositorio.save(novoCategoria);
	} 
}
