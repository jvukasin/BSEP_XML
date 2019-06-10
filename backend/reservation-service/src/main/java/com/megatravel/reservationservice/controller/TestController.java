package com.megatravel.reservationservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getUser() {
		return "USPEO SI DA DOBIJES TEKST IZ RESERVATION KONTROLERA!!!";
	}
	
}
