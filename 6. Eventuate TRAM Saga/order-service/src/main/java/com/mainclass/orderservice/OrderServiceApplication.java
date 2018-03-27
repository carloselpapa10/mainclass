package com.mainclass.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.mainclass.orderservice.model.OrderDomainEventPublisher;
import com.mainclass.orderservice.proxy.OrderServiceProxy;
import com.mainclass.orderservice.saga.createorder.CreateOrderSaga;
import com.mainclass.orderservice.saga.createorder.CreateOrderSagaData;
import com.mainclass.orderservice.service.OrderCommandHandlersConfiguration;
import com.mainclass.orderservice.service.OrderServiceConfiguration;

import io.eventuate.jdbckafka.TramJdbcKafkaConfiguration;
import io.eventuate.tram.commands.common.ChannelMapping;
import io.eventuate.tram.commands.common.DefaultChannelMapping;
import io.eventuate.tram.commands.producer.TramCommandProducerConfiguration;
import io.eventuate.tram.consumer.kafka.TramConsumerKafkaConfiguration;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.eventuate.tram.events.publisher.TramEventsPublisherConfiguration;
import io.eventuate.tram.events.subscriber.DomainEventDispatcher;
import io.eventuate.tram.messaging.consumer.MessageConsumer;
import io.eventuate.tram.messaging.producer.jdbc.TramMessageProducerJdbcConfiguration;
import io.eventuate.tram.sagas.orchestration.SagaManager;
import io.eventuate.tram.sagas.orchestration.SagaManagerImpl;
import io.eventuate.tram.sagas.orchestration.SagaOrchestratorConfiguration;
import io.eventuate.tram.sagas.participant.SagaParticipantConfiguration;


@EnableAutoConfiguration
@Import({OrderCommandHandlersConfiguration.class,
		 OrderServiceConfiguration.class,
		 TramJdbcKafkaConfiguration.class
    	})
@SpringBootApplication
public class OrderServiceApplication {

	public static void main(String[] args) {
	    SpringApplication.run(OrderServiceApplication.class, args);
	}
	
	@Bean
	public ChannelMapping channelMapping() {
		return DefaultChannelMapping.builder().build();
	}
	
	/*
	@Bean
	  public OrderDomainEventPublisher orderAggregateEventPublisher(DomainEventPublisher eventPublisher) {
	    return new OrderDomainEventPublisher(eventPublisher);
	}*/
}
