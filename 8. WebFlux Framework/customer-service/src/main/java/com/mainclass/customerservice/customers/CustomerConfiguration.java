package com.mainclass.customerservice.customers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import com.mainclass.customerservice.model.*;
import org.springframework.boot.ApplicationRunner;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class CustomerConfiguration {
	
	@Bean
	public RouterFunction<ServerResponse> customerHandlerRouting(CustomerHandler customerHandler){
		return RouterFunctions
				.route(GET("/customer/{customerId}").and(accept(APPLICATION_JSON)), customerHandler::getCustomer)
				.andRoute(GET("/customers").and(accept(APPLICATION_JSON)), customerHandler::listCustomers);
				//.andRoute(POST("/customer").and(contentType(APPLICATION_JSON)), customerHandler::createCustomer);
	}
	
	@Bean
	public CustomerHandler customerHandler() {
		return new CustomerHandler();
	}
	
	/*
	 return RouterFunctions.route(GET("/sample"), r -> ok().body(Flux.just("Hello World!"), String.class))
				.andRoute(GET("/customers"), r -> ok().body(cr.findAll(), Customer.class)); 
	 * */
	
//	@Bean
//	ApplicationRunner init(CustomerRepository cr) {
//		return args -> 
//				cr.deleteAll()
//					.thenMany(Flux.just("Carlos", "Ulises", "Ivan").map(l -> new Customer(l)).flatMap(cr::save));
//	}
	
}
