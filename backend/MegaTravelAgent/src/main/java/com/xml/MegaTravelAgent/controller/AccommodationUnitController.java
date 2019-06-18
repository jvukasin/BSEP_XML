package com.xml.MegaTravelAgent.controller;

import com.xml.MegaTravelAgent.dto.AccommodationSettingsDTO;
import com.xml.MegaTravelAgent.dto.AmenityDTO;
import com.xml.MegaTravelAgent.dto.NewAccommodationUnitDTO;
import com.xml.MegaTravelAgent.exceptions.BusinessException;
import com.xml.MegaTravelAgent.model.Amenity;
import com.xml.MegaTravelAgent.service.AccommodationUnitService;
import com.xml.MegaTravelAgent.soap.client.IAccommodationUnitClient;
import com.xml.MegaTravelAgent.soap.reqres.AccommodationType;
import com.xml.MegaTravelAgent.soap.reqres.GetAccommodationSettingsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xml.MegaTravelAgent.dto.AccommodationUnitDTO;


@RestController
@RequestMapping("/accommodations")
public class AccommodationUnitController {

	@Autowired
	IAccommodationUnitClient auClient;

	@Autowired
	AccommodationUnitService auService;
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAccommodationUnit(@PathVariable Long id)
	{
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody NewAccommodationUnitDTO auDTO)
	{

		try {
			return new ResponseEntity<>(auService.save(auDTO), HttpStatus.CREATED);
		} catch (BusinessException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/{id}/amenities", method = RequestMethod.GET)
	public ResponseEntity<?> getAUAmenities(@PathVariable Long id)
	{

		return new ResponseEntity<>(HttpStatus.OK);
	}

	// just for testing, treba da se prebaci u servis
	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	public ResponseEntity<AccommodationSettingsDTO> getAUSettings()
	{
		GetAccommodationSettingsResponse soapResponse = auClient.getAccommodationSettings();

		AccommodationSettingsDTO settingsDTO = new AccommodationSettingsDTO();

		for (Amenity a: soapResponse.getAmenity()) {
			AmenityDTO aDTO = new AmenityDTO(a.getId(), a.getName(), a.getFaIcon());
			settingsDTO.getAmenities().add(aDTO);
		}

		for (AccommodationType t: soapResponse.getAccommodationType()) {
			settingsDTO.getAccommodationTypes().add(t.getType());
		}



		return new ResponseEntity<AccommodationSettingsDTO>(settingsDTO, HttpStatus.OK);
	}
}
