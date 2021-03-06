package com.douglas.compras.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private Environment environment;
//	@Autowired
//	private UserDetailsService userDetailsService;
	
	public static final String[] PUBLIC_MATCHERS = {
			"/h2-console/**",
			"/products/**",
			"/categories/**",
			"/clients/**"
	};
	
	public static final String[] PUBLIC_MATCHERS_GET = {
			"/produtos**/",
			"/categorias**/",
			"/clientes**/"
	};	
	
	public static final String[] PUBLIC_MATCHERS_POST = {
			"/produtos**/",
			"/categorias**/",
			"/clientes**/"
	};	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (Arrays.asList(environment.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		
		//http.cors().and().csrf().disable();
		http.authorizeRequests()
			//.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
			//.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
			.antMatchers(PUBLIC_MATCHERS).permitAll()
			.anyRequest().authenticated();
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
//	@Override
//	public void configure(AuthenticationManagerBu	ilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
//	}
//	
//	@Bean
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
}
