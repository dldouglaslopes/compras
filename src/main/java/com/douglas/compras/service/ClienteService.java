package com.douglas.compras.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.compras.domain.Cliente;
import com.douglas.compras.domain.Produto;
import com.douglas.compras.repository.ClienteRepositorio;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepositorio clienteRepositorio;
	
	public List<Cliente> findAll() {
		List<Cliente> clientes = clienteRepositorio.findAll();
		
		return clientes;
	}
	
	public Cliente find(Integer id) {		
		Optional<Cliente> cliente = clienteRepositorio.findById(id);
		
		return cliente.orElse(null);
	}

	public void delete(Integer id) {
		if (find(id) != null) {
			clienteRepositorio.deleteById(id);
		}		
	}

	public Cliente update(Cliente cliente) {		
		Cliente novoCliente = find(cliente.getId());
		
		novoCliente.setNome(cliente.getNome());
		novoCliente.setEmail(cliente.getEmail());
		
		return clienteRepositorio.save(novoCliente);
	}	
	
	@Transactional
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		cliente = clienteRepositorio.save(cliente);		
		
		return cliente;
	}
	
	public List<Produto> products(Integer id) {
		Cliente cliente = find(id);
		List<Produto> produtos = cliente.getProdutos();	
		
		return produtos;
	}
}
