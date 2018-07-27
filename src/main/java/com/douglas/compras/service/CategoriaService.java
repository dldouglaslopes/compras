package com.douglas.compras.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.compras.domain.Categoria;
import com.douglas.compras.repository.CategoriaRepositorio;

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
		Categoria novaCategoria = find(categoria.getId());
		
		novaCategoria.setNome(categoria.getNome());
		
		return categoriaRepositorio.save(novaCategoria);
	}

	@Transactional
	public Categoria insert(@Valid Categoria categoria) {
		categoria.setId(null);
		categoria = categoriaRepositorio.save(categoria);		
		
		return categoria;
	} 
}
