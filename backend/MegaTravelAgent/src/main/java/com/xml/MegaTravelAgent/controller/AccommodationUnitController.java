package com.xml.MegaTravelAgent.controller;

import com.xml.MegaTravelAgent.dto.*;
import com.xml.MegaTravelAgent.exceptions.BusinessException;
import com.xml.MegaTravelAgent.model.AccommodationCategory;
import com.xml.MegaTravelAgent.model.AccommodationUnit;
import com.xml.MegaTravelAgent.model.Amenity;
import com.xml.MegaTravelAgent.security.TokenUtils;
import com.xml.MegaTravelAgent.service.AccommodationUnitService;
import com.xml.MegaTravelAgent.service.AmenityService;
import com.xml.MegaTravelAgent.service.Logging;
import com.xml.MegaTravelAgent.soap.client.IAccommodationUnitClient;
import com.xml.MegaTravelAgent.model.AccommodationType;
import com.xml.MegaTravelAgent.soap.reqres.GetAccommodationSettingsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/accommodations")
public class AccommodationUnitController {

	@Autowired
	IAccommodationUnitClient auClient;

	@Autowired
	AccommodationUnitService accommodationService;

	@Autowired
	AmenityService amenityService;

	@Autowired
	TokenUtils tokenUtils;

	private Logging logger = new Logging(this);

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Collection<AccommodationUnitDTO>> getAccommodationUnits(HttpServletRequest request)
	{

		String username = getUsernameFromRequest(request);

		if (username == null) {
			logger.logError("AU_GET_USRERR; IP: " + request.getRemoteAddr());
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<Collection<AccommodationUnitDTO>>(accommodationService.findAllByAgentUsername(username),
				HttpStatus.OK);
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
			logger.logError("AU_GET_ERR: " + e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		catch(Exception e)
		{
			logger.logError("AU_GET_ERR: " + e.getMessage());
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	@PreAuthorize("hasAuthority('CREATE_AU_UNIT')")
	public ResponseEntity<?> create(@RequestBody NewAccommodationUnitDTO auDTO, HttpServletRequest request)
	{
		logger.logInfo("AU_CREATE");

		String username = getUsernameFromRequest(request);

		if (username == null) {
			logger.logError("AU_CREATE_USRERR; IP: " + request.getRemoteAddr());
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		try {
			AccommodationUnit au = accommodationService.save(auDTO, username);
			// send to soap
			auClient.createAccommodationUnit(au, username);
			logger.logInfo("AU_CREATE_SUCCESS");
			return new ResponseEntity<>(au.getId(), HttpStatus.CREATED);
		} catch (BusinessException e) {
			logger.logError("AU_CREATE_ERR: " + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			logger.logError("AU_CREATE_ERR: " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
			logger.logError("AU_AM_GET_ERR: " + e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		catch(Exception e)
		{
			logger.logError("AU_AM_GET_ERR: " + e.getMessage());
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/{id}/priceplan", method = RequestMethod.GET)
	public ResponseEntity<?> getAUPricePlan(@PathVariable Long id)
	{
		try
		{
			return new ResponseEntity<PricePlanDTO>(accommodationService.getPricePlan(id),
					HttpStatus.OK);

		}
		catch(BusinessException e)
		{
			logger.logError("AU_PP_GET_ERR: " + e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		catch(Exception e)
		{
			logger.logError("AU_PP_GET_ERR: " + e.getMessage());
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	public ResponseEntity<AccommodationSettingsDTO> getAUSettings()
	{

		GetAccommodationSettingsResponse soapResponse = auClient.getAccommodationSettings();

		// update amenities
		List<Amenity> updatedAmenities = amenityService.updateAmenities(soapResponse.getAmenity());


		AccommodationSettingsDTO settingsDTO = new AccommodationSettingsDTO();

		for (Amenity a: updatedAmenities) {
			AmenityDTO aDTO = new AmenityDTO(a.getId(), a.getName(), a.getFaIcon());
			settingsDTO.getAmenities().add(aDTO);
		}

		for (AccommodationType t: soapResponse.getAccommodationType()) {
			settingsDTO.getAccommodationTypes().add(t.getType());
		}

		for (AccommodationCategory c: soapResponse.getAccommodationCategory()) {
			settingsDTO.getAccommodationCategories().add(c);
		}


		return new ResponseEntity<AccommodationSettingsDTO>(settingsDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/ratings", method = RequestMethod.GET)
	public ResponseEntity<?> fetchRatings(HttpServletRequest request) {

		String username = getUsernameFromRequest(request);

		if (username == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		return  new ResponseEntity<>(HttpStatus.OK);

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
