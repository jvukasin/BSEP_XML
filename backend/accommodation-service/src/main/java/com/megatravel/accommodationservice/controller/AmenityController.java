package com.megatravel.accommodationservice.controller;

import java.util.Collection;

import com.megatravel.accommodationservice.security.TokenUtils;
import com.megatravel.accommodationservice.service.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.accommodationservice.dto.AmenityDTO;
import com.megatravel.accommodationservice.service.AmenityService;

import exceptions.BusinessException;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/amenities")
public class AmenityController 
{
	@Autowired
	AmenityService amenityService;

	@Autowired
	public TokenUtils tokenUtils;

	private Logging logger = new Logging(this);
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Collection<AmenityDTO>> getAmenities()
	{
		return new ResponseEntity<Collection<AmenityDTO>>(amenityService.findAll(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('ADD_AMENITY')")
	public ResponseEntity<?> addAmenity(@RequestBody AmenityDTO dto, HttpServletRequest request)
	{
		String username = getUsernameFromRequest(request);
		logger.logInfo("AU_CREATE - Username: " + username + "; IP: " + request.getRemoteAddr());
		try
		{
			Long id = amenityService.add(dto);
			logger.logInfo("AU_CREATE_SUCCESS - Username: " + username + "; IP: "+ request.getRemoteAddr());
			return new ResponseEntity<Long>(id,HttpStatus.CREATED);
		}
		catch(BusinessException e)
		{
			logger.logError("AU_CREATE_ERR - Username: " + username + "; IP: " + request.getRemoteAddr() + "; Message: " + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		catch(Exception e)
		{
			logger.logError("AU_CREATE_ERR - Username: " + username + "; IP: " + request.getRemoteAddr() + "; Message: " + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasAuthority('REMOVE_AMENITY')")
	public ResponseEntity<?> removeAmenity(@PathVariable Long id, HttpServletRequest request)
	{
		String username = getUsernameFromRequest(request);
		logger.logInfo("AU_DELETE - Username: " + username + "; IP: " + request.getRemoteAddr());
		try
		{				
			amenityService.delete(id);
			logger.logInfo("AU_DELETE_SUCCESS - Username: " + username + "; IP: " + request.getRemoteAddr());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(BusinessException e)
		{
			logger.logError("AU_DELETE_ERR -  Username: " + username + "; IP: " + request.getRemoteAddr() + "; Message: " + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_MODIFIED);
		}
		catch(Exception e)
		{
			logger.logError("AU_DELETE_ERR -  Username: " + username + "; IP: " + request.getRemoteAddr() + "; Message: " + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private String getUsernameFromRequest(HttpServletRequest request) {

		String authToken = tokenUtils.getToken(request);
		if (authToken == null) {
			return null;
		}

		String username = tokenUtils.getUsernameFromToken(authToken);

		return username;
	}

}
