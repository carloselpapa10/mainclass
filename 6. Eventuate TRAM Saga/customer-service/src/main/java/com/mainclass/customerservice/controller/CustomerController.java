package com.mainclass.customerservice.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mainclass.customerservice.model.Customer;
import com.mainclass.customerservice.service.CustomerService;
import com.mainclass.customerservice.webapi.CreateCustomerRequest;
import com.mainclass.customerservice.webapi.CreateCustomerResponse;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService; 
	
	
	@RequestMapping(value = "/customer", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public CreateCustomerResponse createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest) {
		
		Customer customer = customerService.findCustomer(createCustomerRequest.getId());
		
		if(customer == null) {
			customer = customerService.createCustomer(createCustomerRequest.getId(), createCustomerRequest.getName());
			return new CreateCustomerResponse(customer.getId());
		}
		
		return new CreateCustomerResponse();		
	}
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomers(){
		return customerService.findAll();
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<Customer> findOne(@PathVariable String customerId){
		
		Customer costumer = customerService.findCustomer(customerId);
		return costumer != null ? ResponseEntity.ok(costumer) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@DeleteMapping("/deleteAllCustomers")
	public String deleteAll() {
		customerService.deleteAll();
		return "Customers deleted";
	}
}
