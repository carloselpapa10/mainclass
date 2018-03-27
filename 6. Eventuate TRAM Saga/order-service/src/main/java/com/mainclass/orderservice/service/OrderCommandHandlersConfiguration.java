package com.mainclass.orderservice.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.eventuate.tram.events.publisher.TramEventsPublisherConfiguration;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaParticipantConfiguration;

@Configuration
@Import({ SagaParticipantConfiguration.class, TramEventsPublisherConfiguration.class })
public class OrderCommandHandlersConfiguration {

	@Bean
	public OrderCommandHandlers orderCommandHandlers() {
		return new OrderCommandHandlers();
	}

	@Bean
	public SagaCommandDispatcher orderCommandHandlersDispatcher(OrderCommandHandlers orderCommandHandlers) {
		return new SagaCommandDispatcher("orderService", orderCommandHandlers.commandHandlers());
	}

}
