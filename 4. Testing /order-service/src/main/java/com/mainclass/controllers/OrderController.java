package com.mainclass.controllers;

import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mainclass.entities.Order;
import com.mainclass.repositories.OrderRepository;

@RestController
public class OrderController{

	@Value("${spring.datasource.url}")
	private String datasource_url;
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	@RequestMapping(value = "/addOrder", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE, consumes= MediaType.APPLICATION_JSON_VALUE)
	public Order addOrder(@RequestBody Order order) {		
		 
		try {
			orderRepository.save(order);
		}catch (Exception e) {
			// TODO: handle exception
			//return e.getCause().toString();
		}
		return order;
	}
	
	@RequestMapping(value="/orders/{orderId}", method= RequestMethod.GET)
	public ResponseEntity<Order> getOrder(@PathVariable long orderId) {
		
		Optional<Order> order = orderRepository.findById(orderId);		
		
		if (order == null) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    } else {
	        return new ResponseEntity<>(new Order(order.get().getId(), order.get().getName(), order.get().getPrice(), order.get().isActive()), HttpStatus.OK);
	    }
		
	}
	
	@RequestMapping(value="/seeSpringDatasourceUrl", method = RequestMethod.GET)
	public String seeSpringBootAdminUrl() {
		return "spring datasource url => "+datasource_url;
	}
	
	
}
