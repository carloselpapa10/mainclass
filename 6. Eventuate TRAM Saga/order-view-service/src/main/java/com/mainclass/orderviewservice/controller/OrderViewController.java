package com.mainclass.orderviewservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mainclass.orderviewservice.model.Customer;
import com.mainclass.orderviewservice.service.CustomerServiceImpl;

@RestController
public class OrderViewController {

	@Autowired
	private CustomerServiceImpl customerServiceImpl; 
	
	@GetMapping("customers")
	public List<Customer> findAll(){
		return customerServiceImpl.findAll();
	}
	
	@GetMapping("customer/{customerId}")
	public Customer findCustomer(@PathVariable String customerId) {
		return customerServiceImpl.findCustomer(customerId);
	}
}
