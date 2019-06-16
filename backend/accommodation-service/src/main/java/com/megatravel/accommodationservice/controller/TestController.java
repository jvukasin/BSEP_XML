package com.megatravel.accommodationservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
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

	@RequestMapping(value = "/authTest", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('TEST')")
	public String authTest() {return "USPEO SI DA PRISTUPIS!"; }

}
