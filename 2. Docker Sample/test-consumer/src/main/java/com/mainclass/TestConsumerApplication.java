package com.mainclass;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.web.client.RestClientException;

import com.mainclass.controllers.ConsumerControllerClient;

@SpringBootApplication
@EnableFeignClients
public class TestConsumerApplication {

	public static void main(String[] args) throws RestClientException, IOException {
		ApplicationContext ctx = SpringApplication.run(
				TestConsumerApplication.class, args);
		
		ConsumerControllerClient consumerControllerClient=ctx.getBean(ConsumerControllerClient.class);
		
		System.out.println("::::::::::: TEST CONSUMER APPLICATION :::::::::::");
		System.out.println(consumerControllerClient);
		consumerControllerClient.getSayHello();
		consumerControllerClient.getSeeEurekaClientServiceUrl();
		consumerControllerClient.getSeeSpringBootAdminUrl();
	}
}
