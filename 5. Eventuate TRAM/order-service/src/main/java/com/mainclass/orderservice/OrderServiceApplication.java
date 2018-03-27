package com.mainclass.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.mainclass.orderservice.service.InvoiceEventConsumer;

import io.eventuate.tram.consumer.kafka.TramConsumerKafkaConfiguration;
import io.eventuate.tram.events.publisher.TramEventsPublisherConfiguration;
import io.eventuate.tram.events.subscriber.DomainEventDispatcher;
import io.eventuate.tram.messaging.consumer.MessageConsumer;
import io.eventuate.tram.messaging.producer.jdbc.TramMessageProducerJdbcConfiguration;


@EnableAutoConfiguration
@Import({TramConsumerKafkaConfiguration.class,
    	TramEventsPublisherConfiguration.class,
    	TramMessageProducerJdbcConfiguration.class})
@SpringBootApplication
public class OrderServiceApplication {

	public static void main(String[] args) {
	    SpringApplication.run(OrderServiceApplication.class, args);
	}
	
	@Bean
	  public DomainEventDispatcher domainEventDispatcher(InvoiceEventConsumer invoiceEventConsumer, MessageConsumer messageConsumer) {
	    return new DomainEventDispatcher("consumerServiceEvents", invoiceEventConsumer.domainEventHandlers(), messageConsumer);
	}
}
