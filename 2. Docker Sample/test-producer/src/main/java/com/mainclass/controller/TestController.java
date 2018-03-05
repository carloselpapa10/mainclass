package com.mainclass.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Value("${spring.boot.admin.url}")
	private String admin_url;
	
	@Value("${eureka.client.serviceUrl.defaultZone}")
	private String eureka_client_serviceUrl;
	
	@RequestMapping(value="/sayHello", method = RequestMethod.GET)
	public String sayHello() {
		return "Hello World from Carlos Avendano =)";
	}
	
	@RequestMapping(value="/seeSpringBootAdminUrl", method = RequestMethod.GET)
	public String seeSpringBootAdminUrl() {
		return "spring boot admin url => "+admin_url;
	}
	
	@RequestMapping(value="/seeEurekaClientServiceUrl", method = RequestMethod.GET)
	public String seeEurekaClientServiceUrl() {
		return "eureka cliente service url => "+eureka_client_serviceUrl;
	}
}
