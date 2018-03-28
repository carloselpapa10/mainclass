package com.mainclass.orderviewservice.messaging;

import org.springframework.stereotype.Component;

import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;

import com.mainclass.servicemodel.order.api.events.OrderCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class OrderHistoryEventHandlers {

	private static final Logger log = LoggerFactory.getLogger(OrderHistoryEventHandlers.class);
			
	 public DomainEventHandlers domainEventHandlers() {
		    return DomainEventHandlersBuilder
		            .forAggregateType("com.mainclass.orderservice.model.Order")
		            .onEvent(OrderCreatedEvent.class, this::handleOrderCreated)
		            //.onEvent(DeliveryPickedUp.class, this::handleDeliveryPickedUp)
		            .build();
	}
	 
	public void handleOrderCreated(DomainEventEnvelope<OrderCreatedEvent> dee) {
		log.info("handleOrderCreated() - OrderHistoryEventHandlers");
		log.info("dee "+dee);
		log.info("aggregateID - orderId => "+dee.getAggregateId());
	}
	 
}
