package com.mainclass.orderviewservice.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mainclass.orderviewservice.dao.CustomerService;
import com.mainclass.orderviewservice.model.Customer;
import com.mainclass.orderviewservice.repository.CustomerRepository;

@Component
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public void createCustomer(String id, String name) {
		Customer customer = new Customer(id,name);
		customerRepository.save(customer);
	}

	@Override
	public Customer findCustomer(String id) {
		
		try {
			Customer customer = customerRepository.findById(id).get();
			return customer;
		}catch (NoSuchElementException e) {
			return null;
		}
		
	}
	
	@Override
	public void updateCustomer(String id, String name) {
		Customer customer = findCustomer(id);
		if(customer!=null) {
			customer.setName(name);
			customerRepository.save(customer);
		}
	}

	@Override
	public void deleteCustomer(String id) {
		Customer customer = findCustomer(id);
		if(customer!=null) {
			customerRepository.delete(customer);
		}
		
	}

	@Override
	public void deleteAllCustomers() {
		// TODO
		
	}

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}
}
