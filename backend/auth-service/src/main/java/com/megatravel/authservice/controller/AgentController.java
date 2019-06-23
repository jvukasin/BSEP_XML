package com.megatravel.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.authservice.dto.AgentDTO;
import com.megatravel.authservice.service.TPersonService;

import exceptions.BusinessException;

@RestController
@RequestMapping(value = "/agents")
public class AgentController 
{
    @Autowired
    private TPersonService tPersonService;
    
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> saveAgent(@RequestBody AgentDTO agentDTO) 
    {
		try
		{
			return new ResponseEntity<String>(tPersonService.addAgent(agentDTO), HttpStatus.CREATED);
		}
		catch(BusinessException e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> checkIsOccupied(@PathVariable String username) 
    {
    	if(!tPersonService.isUsernameTaken(username))
    	{
			return new ResponseEntity<>(HttpStatus.OK);
    	}
    	else
    	{
    		System.out.println("ma jok");
			return new ResponseEntity<>(HttpStatus.IM_USED);
    	}
 
    }

}
