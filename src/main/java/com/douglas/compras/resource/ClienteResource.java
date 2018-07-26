package com.douglas.compras.resource;

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
import com.douglas.compras.dto.ClienteDTO;
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
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDTO clienteDTO) {		
		Cliente cliente = new Cliente(null, clienteDTO.getNome(), clienteDTO.getEmail());
		cliente = clienteService.insert(cliente);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
		.path("/{id}").buildAndExpand(cliente.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
//	public ResponseEntity<Void> insert(@Valid @RequestBody ClientNewDTO clientNewDTO) {
//		Client client = service.fromDTO(clientNewDTO);
//		client = service.insert(client);
//		
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//					.path("/{id}").buildAndExpand(client.getId()).toUri();
//		
//		return ResponseEntity.created(uri).build();
//}
	
//	public Client fromDTO(ClientDTO clientDTO) {
//		return new Client(clientDTO.getId(), clientDTO.getName(), clientDTO.getEmail(), null, null, null);
//	}
//	
//	public Client fromDTO(ClientNewDTO clientNewDTO) {
//		Client client = new Client(null, clientNewDTO.getName(), clientNewDTO.getEmail(), clientNewDTO.getCpfOrCnpj(), TypeClient.toEnum(clientNewDTO.getType()), encoder.encode(clientNewDTO.getPassword()));
//		City city = new City(clientNewDTO.getCityId(), null, null);
//		Address address = new Address(null, clientNewDTO.getPatio(), clientNewDTO.getNumber(), clientNewDTO.getAdditional(), clientNewDTO.getDistrict(), clientNewDTO.getZipCode(), client, city);
//		client.getAddresses().add(address);
//		client.getPhones().add(clientNewDTO.getPhone1());
//		
//		if (clientNewDTO.getPhone2() != null) {
//			client.getPhones().add(clientNewDTO.getPhone2());
//		}
//		
//		if (clientNewDTO.getPhone3() != null) {
//			client.getPhones().add(clientNewDTO.getPhone3());
//		}
//		
//		return client;
//}
}
