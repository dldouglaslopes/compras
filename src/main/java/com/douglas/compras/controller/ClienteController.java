package com.douglas.compras.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douglas.compras.dto.ClienteDTO;
import com.douglas.compras.service.ClienteService;

@Controller
@RequestMapping(value="/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(Model model) {		
		model.addAttribute("clientes", clienteService.findAll());
		
		return "cliente/showAll";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable Integer id, Model model) {		
		clienteService.delete(id);
		
		model.addAttribute("clientes", clienteService.findAll());
		
		return "cliente/showAll";
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable Integer id, 
											@Valid @RequestBody ClienteDTO clienteDTO) {
		
//		Cliente cliente = new Cliente(clienteDTO.getId(), 
//										clienteDTO.getNome(), 
//										clienteDTO.getEmail());
//		cliente.setId(id);
//		cliente = clienteService.update(cliente);
		
		return "cliente/update";
	}
	
	@RequestMapping(value = "/insert/", method = RequestMethod.GET)
	public String insert() {
		return "cliente/register";
	}
	
	@RequestMapping(value = "/insert/save/", method = RequestMethod.GET)
	public String save(Model model) {	//, @RequestParam("clienteRegister") ClienteDTO clienteDTO	
//		Cliente cliente = new Cliente(null, 
//										clienteDTO.getNome(), 
//										clienteDTO.getEmail());
//		
//		cliente = clienteService.insert(cliente);
//		
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//		.path("/{id}").buildAndExpand(cliente.getId()).toUri();
		
		//return ResponseEntity.created(uri).build();
		model.addAttribute("clientes", clienteService.findAll());
		
		return "cliente/showAll";
	}
}
