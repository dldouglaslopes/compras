package com.douglas.compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.douglas.compras.domain.Cliente;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Integer>{

}
