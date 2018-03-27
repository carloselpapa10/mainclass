package com.mainclass.customerservice.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.eventuate.tram.commands.common.ChannelMapping;
import io.eventuate.tram.commands.common.DefaultChannelMapping;
import io.eventuate.tram.commands.consumer.CommandDispatcher;
import io.eventuate.tram.events.publisher.TramEventsPublisherConfiguration;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaParticipantConfiguration;

@Configuration
@Import({ SagaParticipantConfiguration.class, TramEventsPublisherConfiguration.class })
public class CustomerServiceConfiguration {

	@Bean
	public CustomerServiceCommandHandlers consumerServiceCommandHandlers() {
	    return new CustomerServiceCommandHandlers();
	}
	
	@Bean
	public CommandDispatcher commandDispatcher(CustomerServiceCommandHandlers customerServiceCommandHandlers) {
	    return new SagaCommandDispatcher("customerServiceDispatcher", customerServiceCommandHandlers.commandHandlers());
	}
	
	@Bean
	public ChannelMapping channelMapping() {
	    return new DefaultChannelMapping.DefaultChannelMappingBuilder().build();
	}
	
}
