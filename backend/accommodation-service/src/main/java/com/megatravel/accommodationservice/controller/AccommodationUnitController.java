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

import com.megatravel.accommodationservice.dto.AccTypeDTO;
import com.megatravel.accommodationservice.dto.AccommodationCategoryDTO;
import com.megatravel.accommodationservice.dto.AccommodationUnitDTO;
import com.megatravel.accommodationservice.dto.AmenityDTO;
import com.megatravel.accommodationservice.dto.ExtendedSearchDTO;
import com.megatravel.accommodationservice.dto.TotalPriceAccommodationDTO;
import com.megatravel.accommodationservice.model.AccommodationCategory;
import com.megatravel.accommodationservice.model.AccommodationType;
import com.megatravel.accommodationservice.service.AccommodationUnitService;

import exceptions.BusinessException;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/accommodations")
public class AccommodationUnitController
{

	private Logging logger = new Logging(this);

	@Autowired
	AccommodationUnitService accommodationService;

	@Autowired
	public TokenUtils tokenUtils;
	
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
		logger.logInfo("AU_SEARCH");
		try
		{
			Collection<TotalPriceAccommodationDTO> result = accommodationService.search(dto);
			logger.logInfo("AU_SEARCH_SUCCESS");
			return new ResponseEntity<Collection<TotalPriceAccommodationDTO>>(result, HttpStatus.OK);
		}
		catch(BusinessException e)
		{
			logger.logError("AU_SEARCH_ERR: " + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		catch(Exception e)
		{
			logger.logError("AU_SEARCH_ERR: " + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// * * * TYPE ENDPOINTS * * *
	
	@RequestMapping(value = "/types", method = RequestMethod.GET)
	public ResponseEntity<Collection<AccTypeDTO>> getAccommodationTypes()
	{
		return new ResponseEntity<Collection<AccTypeDTO>>(accommodationService.findAllTypesDTO(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/types/{type}", method = RequestMethod.DELETE)
	@PreAuthorize("hasAuthority('REMOVE_AU_TYPE')")
	public ResponseEntity<?> removeType(@PathVariable String type, HttpServletRequest request)
	{
		String username = getUsernameFromRequest(request);
		logger.logInfo("DELETE_AU_TYPE - Username: " + username + "; IP: " + request.getRemoteAddr());
		try
		{				
			accommodationService.deleteType(type);
			logger.logInfo("DELETE_AU_TYPE_SUCCESS - Username: " + username + "; IP: " + request.getRemoteAddr());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(BusinessException e)
		{
			logger.logError("DELETE_AU_TYPE_ERR - Username: " + username + "; IP: " + request.getRemoteAddr() + "Message: " + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_MODIFIED);
		}
		catch(Exception e)
		{
			logger.logError("DELETE_AU_TYPE_ERR - Username: " + username + "; IP: " + request.getRemoteAddr() + "Message: " + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@RequestMapping(value = "/types", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('ADD_AU_TYPE')")
	public ResponseEntity<?> addType(@RequestBody AccommodationType dto, HttpServletRequest request)
	{
		String username = getUsernameFromRequest(request);
		logger.logError("AU_TYPE - Username: " + username + "; IP: " + request.getRemoteAddr());
		try
		{
			accommodationService.addType(dto);
			logger.logInfo("AU_TYPE_SUCCESS - Username: " + username + "; IP: " + request.getRemoteAddr());
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		catch(BusinessException e)
		{
			logger.logError("AU_TYPE_ERR - Username:" + username + "; IP: " + request.getRemoteAddr() + "; Message:" + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		catch(Exception e)
		{
			logger.logError("AU_TYPE_ERR - Username:" + username + "; IP: " + request.getRemoteAddr() + "; Message:" + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	// * * * CATEGORY ENDPOINTS * * *
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public ResponseEntity<Collection<AccommodationCategoryDTO>> getCategories()
	{
		return new ResponseEntity<Collection<AccommodationCategoryDTO>>(accommodationService.findAllCategories(),HttpStatus.OK);
	}
	

	@RequestMapping(value = "/categories/{value}", method = RequestMethod.DELETE)
	@PreAuthorize("hasAuthority('REMOVE_AU_CATEGORY')")
	public ResponseEntity<?> removeCategory(@PathVariable int value, HttpServletRequest request)
	{
		String username = getUsernameFromRequest(request);
		logger.logInfo("DELETE_AU_CATEGORY - Username: " + username + "; IP: " + request.getRemoteAddr());
		try
		{				
			accommodationService.deleteCategory(value);
			logger.logInfo("DELETE_AU_CATEGORY_SUCCESS - Username: " + username + "; IP: " + request.getRemoteAddr());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(BusinessException e)
		{
			logger.logError("DELETE_AU_CATEGORY_ERR - Username: " + username + "; IP: " + request.getRemoteAddr() + "; Message: " + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_MODIFIED);
		}
		catch(Exception e)
		{
			logger.logError("DELETE_AU_CATEGORY_ERR - Username: " + username + "; IP: " + request.getRemoteAddr() + "; Message: " + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@RequestMapping(value = "/categories", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('ADD_AU_CATEGORY')")
	public ResponseEntity<?> addCategory(@RequestBody AccommodationCategory dto, HttpServletRequest request)
	{
		String username = getUsernameFromRequest(request);
		logger.logInfo("AU_CATEGORY");
		try
		{
			accommodationService.addCategory(dto);
			logger.logInfo("AU_CATEGORY_SUCCESS - Username: " + username + "; IP: " + request.getRemoteAddr());
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		catch(BusinessException e)
		{
			logger.logError("AU_CATEGORY_ERR - Username: " + username + "; IP: " + request.getRemoteAddr() + "; Message: " + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.logError("AU_CATEGORY_ERR - Username: " + username + "; IP: " + request.getRemoteAddr() + "; Message: " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
