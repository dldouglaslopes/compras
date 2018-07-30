package com.douglas.compras.config;

public class SecurityConfig {
	
	private static final String[] PUBLIC_MATCHERS = {
			"/h2-console/**"
	};
	
	private static final String[] PUBLIC_MATCHERS_GET = {
			"/products/**",
			"/categorias/**"
	};
	
	private static final String[] PUBLIC_MATCHERS_POST = {
			"/clientes/**"
	};
	
}
