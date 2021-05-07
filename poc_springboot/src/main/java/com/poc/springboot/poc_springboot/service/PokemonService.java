package com.poc.springboot.poc_springboot.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PokemonService {
	
	private static final String URL_POKEAPI = "http://localhost:8081/pokemon";
	
	private RestTemplate client = new RestTemplate();
	
	public String findByName(String name) {
		if(name == null) {
			name = "ditto";
		}
		return client.getForEntity(URL_POKEAPI.concat(name), String.class).getBody();
	}
}
