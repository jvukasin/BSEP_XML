package com.megatravel.authservice.controller;

import com.megatravel.authservice.model.Agent;
import com.megatravel.authservice.model.User;
import com.megatravel.authservice.security.TokenUtils;
import com.megatravel.authservice.service.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.megatravel.authservice.dto.AgentDTO;
import com.megatravel.authservice.service.TPersonService;

import exceptions.BusinessException;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/agents")
public class AgentController 
{
    @Autowired
    private TPersonService tPersonService;

    @Autowired
	TokenUtils tokenUtils;

	private Logging logger = new Logging(this);

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/text")
	@PreAuthorize("hasAuthority('ADD_AGENT')")
    public ResponseEntity<?> saveAgent(@RequestBody AgentDTO agentDTO) 
    {
		logger.logInfo("AG_REG");
		try
		{
			logger.logInfo("AG_REG_SUCCESS");
			return new ResponseEntity<String>(tPersonService.addAgent(agentDTO), HttpStatus.CREATED);
		}
		catch(BusinessException e)
		{
			logger.logError("AG_REG_ERR: " + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.logError("AG_REG_ERR: " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @RequestMapping(value = "/approve",method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('APPROVE_AGENT')")
	public ResponseEntity<String> approveAgent(@RequestBody String username){
		logger.logInfo("AGENT_APPROVE");
    	try{
			Agent agent = tPersonService.approveAgent(username);
		}catch(Exception e){
			logger.logWarning("AGENT_APPROVE_ERR");
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.logInfo("AGENT_APPROVE_SUCCESS");
    	return new ResponseEntity(username,HttpStatus.CREATED);
	}

	@RequestMapping(value = "/upgrade", method = RequestMethod.PUT)
	public ResponseEntity<?> upgrade(HttpServletRequest request){
		String authToken = tokenUtils.getToken(request);
		String username = tokenUtils.getUsernameFromToken(authToken);
    	User user = (User) tPersonService.findOneByUsername(username);

		logger.logInfo("AGENT_UPGRADE - Username: " + user.getUsername() + "; IP: " + request.getRemoteAddr());

		user.setStatus("blocked");
		try{
			user = tPersonService.save(user);
			logger.logInfo("AGENT_UPGRADE_SUCCESS - Username: " + user.getUsername() + "; IP: " + request.getRemoteAddr());
		}catch(Exception e){
			logger.logError("AGENT_UPGRADE_ERR - Username: "+ user.getUsername() + "; IP: " + request.getRemoteAddr() + "; Message: " + e.getMessage());
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(HttpStatus.OK);
	}

    
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> checkIsOccupied(@PathVariable String username) 
    {
    	if(!tPersonService.isUsernameTaken(username))
    	{
    		logger.logInfo("AG_REG_USR");
			return new ResponseEntity<>(HttpStatus.OK);
    	}
    	else
    	{
			logger.logWarning("AG_REG_USR_TAKEN");
			return new ResponseEntity<>(HttpStatus.IM_USED);
    	}
    }
}
