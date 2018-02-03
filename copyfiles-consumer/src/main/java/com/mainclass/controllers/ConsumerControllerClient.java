package com.mainclass.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.mainclass.services.RemoteCallService;

import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

@Controller
public class ConsumerControllerClient {

	//@Autowired
	//private DiscoveryClient discoveryClient;
	
	@Autowired
	private RemoteCallService loadBalancer;
	
	public void getCopyFiles() throws RestClientException, IOException {
		
		try {
			String algo = loadBalancer.getData();
			System.out.println(algo);
		} catch (Exception ex) {
			System.out.println(ex);
		}		
		
		//String baseUrl = "http://localhost:8080/employee";
		
		/*List<ServiceInstance> instances=discoveryClient.getInstances("zuul-gateway-service");
		ServiceInstance serviceInstance=instances.get(0);
		
		String baseUrl=serviceInstance.getUri().toString();
		
		baseUrl=baseUrl+"/producer/copyfiles";
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response=null;
		try{
			response=restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(),String.class);
		}catch (Exception ex)
		{
			System.out.println(ex);
		}
		System.out.println(response.getBody());*/
	}
	
	/*
	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}*/
}
