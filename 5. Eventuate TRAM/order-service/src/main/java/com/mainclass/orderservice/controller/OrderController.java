package com.mainclass.orderservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mainclass.orderservice.domain.Order;
import com.mainclass.orderservice.domain.OrderRepository;
import com.mainclass.orderservice.service.OrderService;
import com.mainclass.servicemodel.coreapi.order.ProductInfo;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	@RequestMapping(value = "/orders", method = RequestMethod.POST)
	 public String createOrder(@RequestBody ProductInfo productInfo) {
		Order order = orderService.createOrder(productInfo);	    
	    return "Order is being processed - " + order.getId();
	}
	
	@GetMapping("/orders")
	public List<Order> getAllOrders() {
	        return orderRepository.findAll();
	}
	
	@GetMapping("/order/{orderId}")
    public ResponseEntity<Order> findOne(@PathVariable String orderId) {
        Optional<Order> result = orderRepository.findById(orderId);
        return result != null ? ResponseEntity.ok(result.get()) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
