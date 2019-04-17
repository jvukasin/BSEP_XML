package com.xml.MegaTravelMBA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xml.MegaTravelMBA.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	private String getUser() {
		System.out.println("IN Get user");
		return "getUser() metoda odradjena!";
	}
	
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
	//@PreAuthorize("hasAuthority('DELETE_USER')")
	private String deleteUser() {
		return "deleteUser() metoda izvrsena!";
	}
	
}
