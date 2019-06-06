package com.xml.MegaTravelMBA.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xml.MegaTravelMBA.model.User;

@RestController
@RequestMapping("/agent")
public class AgentController 
{
	//ROLE: admin
	//TODO: user token ili id
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> upgradeUserToAgent(@RequestBody User user)
	{	
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
