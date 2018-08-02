package com.douglas.compras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douglas.compras.domain.Cliente;
import com.douglas.compras.service.ClienteService;

@Controller
@RequestMapping(value="/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {		
		model.addAttribute("clientes", clienteService.findAll());
		
		return "cliente/clientes";
	}
	
	@RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
	public String find(@PathVariable Integer id, Model model) {
		Cliente cliente = clienteService.find(id);
		
		if (cliente == null) {
			model.addAttribute("cliente", new Cliente());
			
			return "findCliente";
		}
		
		model.addAttribute("cliente", cliente);
		
		return "cliente/findCliente";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable Integer id, Model model) {
		Cliente cliente = clienteService.find(id);
		
		if (cliente == null) {
			model.addAttribute("deleteCliente", "Não existe");	
			
			return "cliente/deleteCliente";
		}
		
		clienteService.delete(id);
		
		model.addAttribute("deleteCliente", "Deletado");
		
		return "cliente/deleteCliente";
	}
	
//	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
//	public ResponseEntity<Cliente> update(@PathVariable Integer id, 
//											@Valid @RequestBody ClienteDTO clienteDTO) {
//		
//		Cliente cliente = new Cliente(clienteDTO.getId(), 
//										clienteDTO.getNome(), 
//										clienteDTO.getEmail());
//		cliente.setId(id);
//		cliente = clienteService.update(cliente);
//		
//		return ResponseEntity.noContent().build();
//	}
	
//	@RequestMapping(method = RequestMethod.POST)
//	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDTO clienteDTO) {		
//		Cliente cliente = new Cliente(null, 
//										clienteDTO.getNome(), 
//										clienteDTO.getEmail());
//		
//		cliente = clienteService.insert(cliente);
//		
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//		.path("/{id}").buildAndExpand(cliente.getId()).toUri();
//		
//		return ResponseEntity.created(uri).build();
//	}
}
