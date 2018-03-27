package com.mainclass.orderviewservice.service;

import org.springframework.stereotype.Component;

import com.mainclass.servicemodel.order.api.events.OrderCreatedEvent;

import io.eventuate.EventHandlerMethod;
import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component
public class OrderViewWorkflow {

	private static final Logger log = LoggerFactory.getLogger(OrderViewWorkflow.class);
	
	@EventHandlerMethod
	public void createOrder(DomainEventEnvelope<OrderCreatedEvent> domainEventEnvelope) {
		log.info("received createOrder - OrderViewWorkflow");
		log.info("EventId", domainEventEnvelope.getEventId());
		log.info("Event Object", domainEventEnvelope.getEvent());
	}
	
}
