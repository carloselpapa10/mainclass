package com.mainclass.orderservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mainclass.orderservice.domain.Order;
import com.mainclass.orderservice.domain.OrderRepository;
import com.mainclass.servicemodel.coreapi.order.InvoiceCreatedEvent;
import com.mainclass.servicemodel.coreapi.order.InvoiceCreationFailedEvent;

import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class InvoiceEventConsumer {

	private static final Logger log = LoggerFactory.getLogger(InvoiceEventConsumer.class);
	
	@Autowired
	private OrderRepository orderRepository;

	public DomainEventHandlers domainEventHandlers() {
		return DomainEventHandlersBuilder
	            .forAggregateType("com.mainclass.invoiceservice.model.Invoice")
	            .onEvent(InvoiceCreatedEvent.class, this::invoiceCreatedEventHandler)
	            .onEvent(InvoiceCreationFailedEvent.class, this::invoiceCreationFailedEventHandler)
	            .build();
	}
	
	public void invoiceCreatedEventHandler(DomainEventEnvelope<InvoiceCreatedEvent> domainEventEnvelope) {
		log.info("received invoiceCreatedEventHandler");
		
		InvoiceCreatedEvent invoiceCreatedEvent = domainEventEnvelope.getEvent();		
		Optional<Order> order = orderRepository.findById(invoiceCreatedEvent.getOrderId().toString());
		order.get().setInvoiceId(invoiceCreatedEvent.getInvoiceId());
		order.get().setCompleted(true);
		
		orderRepository.save(order.get());
		log.info("order completed");
		
	}
	
	public void invoiceCreationFailedEventHandler(DomainEventEnvelope<InvoiceCreationFailedEvent> domainEventEnvelope) {
		log.info("received invoiceCreationFailedEventHandler");
		
		InvoiceCreationFailedEvent invoiceCreatedEvent = domainEventEnvelope.getEvent();		
		Optional<Order> order = orderRepository.findById(invoiceCreatedEvent.getOrderId().toString());		
		orderRepository.delete(order.get());
	}
	
}
