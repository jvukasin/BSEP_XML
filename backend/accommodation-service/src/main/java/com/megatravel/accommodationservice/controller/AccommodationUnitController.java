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

import com.megatravel.accommodationservice.dto.AccommodationUnitDTO;
import com.megatravel.accommodationservice.dto.ExtendedSearchDTO;
import com.megatravel.accommodationservice.model.AccommodationUnit;
import com.megatravel.accommodationservice.model.Amenity;
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
			return new ResponseEntity<AccommodationUnit>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value = "/{id}/amenities", method = RequestMethod.GET)
	public ResponseEntity<Collection<Amenity>> getAUAmenities(@PathVariable Long id)
	{
		try
		{
			AccommodationUnit ac = accommodationService.findById(id).get();
			
			return new ResponseEntity<Collection<Amenity>>(ac.getAmenity(), HttpStatus.OK);
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
			return new ResponseEntity<Collection<Amenity>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<Collection<AccommodationUnitDTO>> search(@RequestBody ExtendedSearchDTO dto)
	{
		if(dto.getCity() == null || dto.getEndDate() == null || dto.getFromDate() == null || dto.getPersonCount() < 1)
		{
			return new ResponseEntity<Collection<AccommodationUnitDTO>>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Collection<AccommodationUnitDTO>>(accommodationService.search(dto), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody AccommodationUnitDTO accommodationUnit)
	{	
		
		if(accommodationService.save(accommodationUnit) == true)
		{
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}	
	}
	
	
	
	//TODO: dale treba???? 
	@RequestMapping(value = "/{dto}", method = RequestMethod.PUT)
	public ResponseEntity<?> edit(@PathVariable("dto") AccommodationUnitDTO dto)
	{
		return null;
	}
}
