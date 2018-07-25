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

import com.douglas.compras.domain.Cliente;
import com.douglas.compras.service.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Cliente> list() {		
		return clienteService.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		Cliente cliente = clienteService.find(id);
		
		return ResponseEntity.ok().body(cliente);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Cliente> delete(@PathVariable Integer id) {
		clienteService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Cliente> update(@Valid @RequestBody Cliente cliente) {
		Cliente novoCliente = new Cliente();
		novoCliente.setId(cliente.getId());
		novoCliente = clienteService.update(cliente);
		
		return ResponseEntity.noContent().build();
	}
}
