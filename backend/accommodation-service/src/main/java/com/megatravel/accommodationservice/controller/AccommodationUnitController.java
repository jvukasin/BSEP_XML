package com.megatravel.accommodationservice.controller;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.accommodationservice.dto.AccommodationDTO;
import com.megatravel.accommodationservice.dto.ExtendedSearchDTO;
import com.megatravel.accommodationservice.model.AccommodationUnit;
import com.megatravel.accommodationservice.service.AccommodationUnitService;

@RestController
@RequestMapping("/accommodations")
public class AccommodationUnitController
{	
	
	@Autowired
	AccommodationUnitService accommodationService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Collection<AccommodationUnit>> getAccommodationUnits()
	{
		return new ResponseEntity<Collection<AccommodationUnit>>(accommodationService.findAll(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<AccommodationUnit> getAccommodationUnit(@PathVariable Long id)
	{
		try
		{
			return new ResponseEntity<AccommodationUnit>(accommodationService.findById(id).get(), HttpStatus.OK);

		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
	
	@RequestMapping(value = "/{id}/amenities", method = RequestMethod.GET)
	public ResponseEntity<?> getAUAmenities(@PathVariable Long id)
	{
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//ROLE: neulogovan i ulogovan
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<List<AccommodationUnit>> search(@RequestBody ExtendedSearchDTO dto)
	{
		if(dto.getCityID() == null || dto.getEndDate() == null || dto.getFromDate() == null || dto.getPersonCount() == null)
		{
			return new ResponseEntity<List<AccommodationUnit>>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<List<AccommodationUnit>>(accommodationService.search(dto), HttpStatus.OK);
	}
	
	
	//ROLE: agent i admin
	//TODO: dto?
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> edit(@PathVariable("id") Long accommodationId)
	{
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//ROLE: agent, admin
	//TODO: user token ili id
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody AccommodationDTO accommodationUnit)
	{	
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
