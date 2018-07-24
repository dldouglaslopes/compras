package com.douglas.compras.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.compras.domain.Cliente;
import com.douglas.compras.repository.ClienteRepositorio;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepositorio clienteRepositorio;
	
	public Cliente find(Integer id) {
		Optional<Cliente> cliente = clienteRepositorio.findById(id);
		
		return cliente.orElse(null);
	}

}
