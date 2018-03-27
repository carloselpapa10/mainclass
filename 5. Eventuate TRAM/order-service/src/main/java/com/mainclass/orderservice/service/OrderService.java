package com.mainclass.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mainclass.orderservice.domain.Order;
import com.mainclass.orderservice.domain.OrderRepository;
import com.mainclass.servicemodel.coreapi.order.OrderCreatedEvent;
import com.mainclass.servicemodel.coreapi.order.ProductInfo;

import io.eventuate.tram.events.common.DomainEvent;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static java.util.Collections.singletonList;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderService {

	private static final Logger log = LoggerFactory.getLogger(OrderService.class);
	
	@Autowired
	private DomainEventPublisher domainEventPublisher;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Transactional
	public Order createOrder(ProductInfo productInfo) {
		log.info("processing order for " + productInfo);		
		Order order = orderRepository.save(new Order(productInfo));
				
		domainEventPublisher.publish(Order.class, 
				order.getId(), 
				singletonList(new OrderCreatedEvent(order.getId(), productInfo)));
		
		return order;
	}
}
