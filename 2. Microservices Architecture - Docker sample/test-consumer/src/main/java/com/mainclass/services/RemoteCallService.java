package com.mainclass.services;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="zuul-gateway-service")
public interface RemoteCallService {

	@RequestMapping(method=RequestMethod.GET, value="/producer/sayHello")
	public String getSayHello();
	
	@RequestMapping(method=RequestMethod.GET, value="/producer/seeSpringBootAdminUrl")
	public String getSeeSpringBootAdminUrl();
	
	@RequestMapping(method=RequestMethod.GET, value="/producer/seeEurekaClientServiceUrl")
	public String getSeeEurekaClientServiceUrl();
}
