package com.mainclass.invoiceservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mainclass.invoiceservice.model.Invoice;
import com.mainclass.invoiceservice.model.InvoiceRepository;
import com.mainclass.servicemodel.coreapi.order.InvoiceCreatedEvent;
import com.mainclass.servicemodel.coreapi.order.InvoiceCreationFailedEvent;
import com.mainclass.servicemodel.coreapi.order.OrderCreatedEvent;

import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collections;

@Component
public class OrderEventConsumer {

	private static final Logger log = LoggerFactory.getLogger(OrderEventConsumer.class);
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private DomainEventPublisher domainEventPublisher;
	
	
	public DomainEventHandlers domainEventHandlers() {
		return DomainEventHandlersBuilder
	            .forAggregateType("com.mainclass.orderservice.domain.Order")
	            .onEvent(OrderCreatedEvent.class, this::orderCreatedEventHandler)
	            .build();
	}
	
	public void orderCreatedEventHandler(DomainEventEnvelope<OrderCreatedEvent> domainEventEnvelope) {
		log.info("received orderCreatedEventHandler");
		
		OrderCreatedEvent orderCreatedEvent = domainEventEnvelope.getEvent();
		
		Invoice invoice = invoiceService.createInvoice(orderCreatedEvent.getOrderId(), orderCreatedEvent.getProductInfo());
		
		try {
			domainEventPublisher.publish(Invoice.class, 
					invoice.getId(), 
					Collections.singletonList(new InvoiceCreatedEvent(invoice.getOrderId(),invoice.getId())));
			
		}catch (Exception e) {
			// TODO: handle exception
			
			domainEventPublisher.publish(Invoice.class, 
					invoice.getId(),
					Collections.singletonList(new InvoiceCreationFailedEvent(orderCreatedEvent.getOrderId())));
		}
		
		
	}
	
}
