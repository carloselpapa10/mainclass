package com.mainclass.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@RequestMapping(value="/copyfiles", method = RequestMethod.GET)
	public String doSomething() {
		
		return "Carlos Avendano Arango";
	}
}
