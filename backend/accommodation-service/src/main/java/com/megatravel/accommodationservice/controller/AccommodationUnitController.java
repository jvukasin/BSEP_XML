package com.megatravel.accommodationservice.controller;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import com.megatravel.accommodationservice.dto.AccommodationUnitDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<List<AccommodationUnitDTO>> search(@RequestBody ExtendedSearchDTO dto)
	{
		if(dto.getCity() == null || dto.getEndDate() == null || dto.getStartDate() == null || dto.getPersonCount() == null || dto.getCity() == "" || dto.getEndDate().toString() == "" || dto.getStartDate().toString() == "" || dto.getPersonCount().toString() == "")
		{
			return new ResponseEntity<List<AccommodationUnitDTO>>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<List<AccommodationUnitDTO>>(accommodationService.search(dto), HttpStatus.OK);
	}
}
