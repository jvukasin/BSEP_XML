package com.megatravel.reservationservice.controller;

import com.megatravel.reservationservice.security.auth.JwtAuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

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
	public String authTest() {return "USPEO SI DA PRISTUPIS!"; }

	@RequestMapping(value = "/setAuth", method = RequestMethod.POST)
	public ResponseEntity<?> setAuth(@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response, Device device, HttpServletRequest hr){

		final Authentication authentication = manager
				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
