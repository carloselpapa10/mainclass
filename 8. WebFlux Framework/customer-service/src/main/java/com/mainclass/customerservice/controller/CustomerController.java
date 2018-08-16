package com.mainclass.customerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.mainclass.customerservice.model.*;
import reactor.core.publisher.Mono;

@RestController
public class CustomerController {

	@Autowired
	private CustomerRepository cr;
	
	@PostMapping("/customer")
	public Mono<Customer> createCustomer(@RequestBody Customer customer){
		return cr.save(customer);
	}
	
	/*@GetMapping("/customer")
	public Flux<Customer> getAllCustomers(){
		return cr.findAll();
	}*/
}
