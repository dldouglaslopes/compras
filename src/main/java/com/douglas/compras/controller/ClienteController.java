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

import com.douglas.compras.domain.Cliente;
import com.douglas.compras.domain.Produto;
import com.douglas.compras.dto.ClienteDTO;
import com.douglas.compras.service.ClienteService;

@RestController
@RequestMapping(value="/clients")
public class ClienteController {
	
	@Autowired
	private ClienteService clientService;
	
//	@Autowired
//	private BCryptPasswordEncoder bEncoder;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> findAll() {		
		List<Cliente> clients = clientService.findAll();
		
		return ResponseEntity.ok().body(clients);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> findOne(@PathVariable Integer id) {		
		Cliente client = clientService.find(id);
		
		return ResponseEntity.ok().body(client);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody @Valid ClienteDTO clientRegister) {
		
		Cliente cliente = new Cliente(null, 
									clientRegister.getNome(), 
									clientRegister.getEmail(),
									//bEncoder.encode(
									clientRegister.getSenha());
		
		cliente = clientService.insert(cliente);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}").buildAndExpand(cliente.getId()).toUri();	
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody @Valid ClienteDTO clientUpdate) {
		
		Cliente cliente = new Cliente(clientUpdate.getId(), 
										clientUpdate.getNome(), 
										clientUpdate.getEmail(),
										//bEncoder.encode(
										clientUpdate.getSenha());
		cliente.setId(id);
		cliente = clientService.update(cliente);
		
		return ResponseEntity.noContent().build();
	}
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Cliente> delete(@PathVariable Integer id) {		
		clientService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> findProducts(@PathVariable Integer id) {		
		List<Produto> products = clientService.products(id);
		
		return ResponseEntity.ok().body(products);
	}
}