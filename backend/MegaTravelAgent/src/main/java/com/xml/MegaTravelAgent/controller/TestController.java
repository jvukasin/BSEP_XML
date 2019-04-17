package com.xml.MegaTravelAgent.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xml.MegaTravelAgent.client.TestClient;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@RequestMapping(value = "/testController", method = RequestMethod.GET)
	public String testController() throws IOException {
		
		TestClient testClient = new TestClient();
		
		String response = testClient.testGetMethod("https://localhost:8444/users/getUser");
		
		return response;
	}
	
}
