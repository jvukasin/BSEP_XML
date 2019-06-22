package com.megatravel.accommodationservice.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.accommodationservice.dto.AmenityDTO;
import com.megatravel.accommodationservice.service.AmenityService;

import exceptions.BusinessException;

@RestController
@RequestMapping("/amenities")
public class AmenityController 
{
	@Autowired
	AmenityService amenityService;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<Collection<AmenityDTO>> getAmenities()
	{
		System.out.println("Upaoooouu");
		return new ResponseEntity<Collection<AmenityDTO>>(amenityService.findAll(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> addAmenity(@RequestBody AmenityDTO dto)
	{
		try
		{
			return new ResponseEntity<Long>(amenityService.add(dto),HttpStatus.CREATED);
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
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removeAmenity(@PathVariable Long id)
	{	
		try
		{				
			amenityService.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(BusinessException e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_MODIFIED);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
}
