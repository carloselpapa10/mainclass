package com.mainclass.customerservice.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mainclass.customerservice.model.Customer;
import com.mainclass.customerservice.model.CustomerRepository;

@Component
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer createCustomer(String id, String name) {
		return customerRepository.save(new Customer(id, name));
	}
	
	public Customer findCustomer(String customerId) {
		
		try {
			Customer customer = customerRepository.findById(customerId).get();
			return customer;
		}catch (NoSuchElementException e) {
			return null;
		}
		
	}
	
	public List<Customer> findAll(){
		return customerRepository.findAll();
	}
	
	public void deleteAll() {
		customerRepository.deleteAll();
	}
}
