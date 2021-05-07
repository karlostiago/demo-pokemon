package com.poc.springboot.poc_springboot.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/poke.io")
public class PokemonController {
	
	private static final String URL_POKEAPI = "http://localhost:8081/pokemon/";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping
	public ResponseEntity<String> findByName(@RequestParam(value = "name", required = false) String name) {
		if(name == null) {
			name = "ditto";
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		try {
			String jsonBody = mapper.writeValueAsString(restTemplate.exchange(URL_POKEAPI, HttpMethod.GET, entity, String.class).getBody());
			return ResponseEntity.ok(jsonBody);
		} catch (RestClientException | JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
