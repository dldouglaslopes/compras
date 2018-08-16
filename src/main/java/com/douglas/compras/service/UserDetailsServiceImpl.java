package com.douglas.compras.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.douglas.compras.domain.Cliente;
import com.douglas.compras.repository.ClienteRepositorio;
import com.douglas.compras.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {

	@Autowired
	private ClienteRepositorio clienteRepositorio;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente cliente = clienteRepositorio.findByEmail(email);
		
		if (cliente == null) {
			throw new UsernameNotFoundException(email);
		} 
		
		return new UserSS(cliente.getId(), cliente.getEmail(), cliente.getSenha(), cliente.getPerfis());
	}

}
