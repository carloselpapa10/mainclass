package com.mainclass.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestClientException;

import com.mainclass.services.RemoteCallService;

@Controller
public class ConsumerControllerClient {

	@Autowired
	private RemoteCallService loadBalancer;
	
	public void getSayHello() throws RestClientException, IOException {
		
		try {
			String algo = loadBalancer.getSayHello();
			System.out.println(algo);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	public void getSeeSpringBootAdminUrl() throws RestClientException, IOException {
		
		try {
			String algo = loadBalancer.getSeeSpringBootAdminUrl();
			System.out.println(algo);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	public void getSeeEurekaClientServiceUrl() throws RestClientException, IOException {
		
		try {
			String algo = loadBalancer.getSeeEurekaClientServiceUrl();
			System.out.println(algo);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	
}
