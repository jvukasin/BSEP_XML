package com.xml.MegaTravelMBA.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xml.MegaTravelMBA.model.Message;

@RestController
@RequestMapping("/messages")
public class MessageController 
{
	
	//ROLE:  ulogovan, agnets
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> list()
	{
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> send(@RequestBody Message message)
	{	
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
