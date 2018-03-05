package com.mainclass.services;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="zuul-gateway-service")
public interface RemoteCallService {

	@RequestMapping(method=RequestMethod.GET, value="/producer/copyfiles")
	public String getData();
}
