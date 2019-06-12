package com.megatravel.reservationservice.controller;

import com.megatravel.reservationservice.security.auth.JwtAuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private AuthenticationManager manager;



	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getUser() {
		return "USPEO SI DA DOBIJES TEKST IZ RESERVATION KONTROLERA!!!";
	}

	@RequestMapping(value = "/authTest", method = RequestMethod.GET)
	//hasAuthority
	public String authTest() {return "USPEO SI DA PRISTUPIS!"; }

	@RequestMapping(value = "/setAuth", method = RequestMethod.POST)
	//hasAuthority
	public String setAuth(@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response, Device device, HttpServletRequest hr) {

		final Authentication authentication = manager
				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);



		return "Postavljeno u kontekst!";
	}


}
