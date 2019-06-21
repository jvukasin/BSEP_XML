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

import com.megatravel.accommodationservice.dto.AccommodationUnitDTO;
import com.megatravel.accommodationservice.dto.AmenityDTO;
import com.megatravel.accommodationservice.dto.ExtendedSearchDTO;
import com.megatravel.accommodationservice.dto.TotalPriceAccommodationDTO;
import com.megatravel.accommodationservice.model.AccommodationType;
import com.megatravel.accommodationservice.service.AccommodationUnitService;

import exceptions.BusinessException;

@RestController
@RequestMapping("/accommodations")
public class AccommodationUnitController
{	
	
	@Autowired
	AccommodationUnitService accommodationService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Collection<AccommodationUnitDTO>> getAccommodationUnits()
	{
		return new ResponseEntity<Collection<AccommodationUnitDTO>>(accommodationService.findAll(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAccommodationUnit(@PathVariable Long id)
	{
		try
		{
			return new ResponseEntity<AccommodationUnitDTO>(accommodationService.findById(id), HttpStatus.OK);

		}
		catch(BusinessException e)
		{
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		catch(Exception e)
		{
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value = "/{id}/amenities", method = RequestMethod.GET)
	public ResponseEntity<?> getAUAmenities(@PathVariable Long id)
	{
		try
		{
			return new ResponseEntity<Collection<AmenityDTO>>(accommodationService.findById(id).getAmenities(), HttpStatus.OK);

		}
		catch(BusinessException e)
		{
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		catch(Exception e)
		{
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/amenities", method = RequestMethod.GET)
	public ResponseEntity<Collection<AmenityDTO>> getAmenities()
	{
		return new ResponseEntity<Collection<AmenityDTO>>(accommodationService.findAllAmenitiesDTO(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<?> search(@RequestBody ExtendedSearchDTO dto)
	{
		try
		{
			return new ResponseEntity<Collection<TotalPriceAccommodationDTO>>(accommodationService.search(dto), HttpStatus.OK);
		}
		catch(BusinessException e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	
	
	// * * * TYPE ENDPOINTS * * *
	
	@RequestMapping(value = "/types", method = RequestMethod.GET)
	public ResponseEntity<Collection<AccommodationType>> getAccommodationTypes()
	{
		return new ResponseEntity<Collection<AccommodationType>>(accommodationService.findAllTypes(),HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/types/{type}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removeType(@PathVariable String type)
	{	
		try
		{				
			accommodationService.deleteType(type);
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
	
	
	
	
	@RequestMapping(value = "/types", method = RequestMethod.POST)
	public ResponseEntity<?> addType(@RequestBody AccommodationType dto)
	{
		try
		{
			accommodationService.addType(dto);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		catch(BusinessException e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
