package com.douglas.compras.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.compras.domain.Cliente;
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
		//novoCliente.setProdutos(cliente.getProdutos());
		
		return clienteRepositorio.save(novoCliente);
	}	
}
