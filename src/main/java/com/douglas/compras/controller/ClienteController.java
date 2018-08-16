package com.douglas.compras.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.douglas.compras.domain.Cliente;
import com.douglas.compras.dto.ClienteDTO;
import com.douglas.compras.service.ClienteService;

@Controller
@RequestMapping(value="/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private BCryptPasswordEncoder bEncoder;

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
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public ModelAndView insert(Model model) { 
		model.addAttribute("clienteRegister", new ClienteDTO());
 
	    return new ModelAndView("cliente/register");
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(Model model, @PathVariable Integer id) {		
		Cliente cliente = clienteService.find(id);
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setId(cliente.getId());
		clienteDTO.setNome(cliente.getNome());
		clienteDTO.setEmail(cliente.getEmail());
		
		model.addAttribute("clienteUpdate", clienteDTO);
		
		return new ModelAndView("cliente/update");
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(Model model, 
								@ModelAttribute @Valid ClienteDTO clienteUpdate) {
		
		Cliente cliente = new Cliente(clienteUpdate.getId(), 
										clienteUpdate.getNome(), 
										clienteUpdate.getEmail(),
										bEncoder.encode(clienteUpdate.getSenha()));
		
		cliente = clienteService.update(cliente);
		
		model.addAttribute("clientes", clienteService.findAll());
	
		return new ModelAndView("cliente/showAll");
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(Model model, 
							@ModelAttribute @Valid ClienteDTO clienteRegister) {
		
		Cliente cliente = new Cliente(null, 
									clienteRegister.getNome(), 
									clienteRegister.getEmail(),
									bEncoder.encode(clienteRegister.getSenha()));
		
		cliente = clienteService.insert(cliente);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}").buildAndExpand(cliente.getId()).toUri();		
		ResponseEntity.created(uri).build();
		
		model.addAttribute("clientes", clienteService.findAll());
		
		return new ModelAndView("cliente/showAll");
	}
	
	@RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
	public  ModelAndView listProducts(Model model, @PathVariable Integer id) {		
		model.addAttribute("produtos", clienteService.products(id));
		
		return new ModelAndView("cliente/products");
	}
}