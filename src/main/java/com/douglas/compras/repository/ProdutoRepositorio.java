package com.douglas.compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.douglas.compras.domain.Produto;

@Repository
public interface ProdutoRepositorio extends JpaRepository<Produto, Integer> {

}
