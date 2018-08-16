package com.mainclass.customerservice.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.mainclass.customerservice.model.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

public class CustomerHandler {

	@Autowired
	private CustomerRepository customerRepository;
	
	public Mono<ServerResponse> listCustomers(ServerRequest request){
		Flux<Customer> customers = customerRepository.findAll();
		return ok().contentType(APPLICATION_JSON).body(customers, Customer.class);
	}
	
	public Mono<ServerResponse> createCustomer(ServerRequest request){
		Mono<Customer> customer = request.bodyToMono(Customer.class);
		return ok().contentType(APPLICATION_JSON).body(customer, Customer.class);
	}
	
	public Mono<ServerResponse> getCustomer(ServerRequest request){
		String customerId = request.pathVariable("customerId");
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		Mono<Customer> customerMono = customerRepository.findById(customerId);
		
		return customerMono.flatMap(customer -> ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(fromObject(customer)))
				.onErrorResume(CustomerNotFoundException.class, e -> notFound);				
	}
}
