package com.ctsousa.poke.api.router;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ctsousa.poke.api.service.PokemonService;

@Component
public class PokemonRouter extends RouteBuilder {
	
	@Autowired
	private PokemonService pokemonService;
	
	@Override
	public void configure() throws Exception {
		from("rest:get:pokemon")
		.setHeader("content-Type", constant("application/json"))
		.transform()
		.constant(pokemonService.findByName(null));
		
//		restConfiguration()
//			.host("localhost")
//			.port(8081)
//			.bindingMode(RestBindingMode.auto);
//		
//		rest("/integration")
//			.get("/pokemon")
//			;
//				.route().routeId("pokemon-for-name")
//				.to("direct:call-rest-all")
//			.endRest();
	}
}
