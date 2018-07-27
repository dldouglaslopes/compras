package com.douglas.compras;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.douglas.compras.domain.Categoria;
import com.douglas.compras.domain.Cliente;
import com.douglas.compras.domain.Produto;
import com.douglas.compras.repository.CategoriaRepositorio;
import com.douglas.compras.repository.ClienteRepositorio;
import com.douglas.compras.repository.ProdutoRepositorio;

@SpringBootApplication
public class ComprasApplication implements CommandLineRunner{
	
	@Autowired
	private ClienteRepositorio clienteRepositorio;
	@Autowired
	private CategoriaRepositorio categoriaRepositorio;
	@Autowired
	private ProdutoRepositorio produtoRepositorio;

	public static void main(String[] args) {
		SpringApplication.run(ComprasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {		
		Categoria categoria1 = new Categoria(null, "Frutas");
		Categoria categoria2 = new Categoria(null, "Informática");	
		
		categoriaRepositorio.saveAll(Arrays.asList(categoria1, categoria2));
		
		Produto produto1 = new Produto(null, "Maça", 10.00, categoria1);
		Produto produto2 = new Produto(null, "Computador", 10.00, categoria2);	
		Produto produto3 = new Produto(null, "Teclado", 10.00, categoria2);
		
		produtoRepositorio.saveAll(Arrays.asList(produto1, produto2, produto3));
		
		Cliente cliente1 = new Cliente(null, "Maria", "maria@email.com");
		Cliente cliente2 = new Cliente(null, "João", "joao@email.com");
		
		cliente1.getProdutos().add(produto1);
		cliente2.getProdutos().add(produto2);
		
		clienteRepositorio.saveAll(Arrays.asList(cliente1, cliente2));
	}
}
