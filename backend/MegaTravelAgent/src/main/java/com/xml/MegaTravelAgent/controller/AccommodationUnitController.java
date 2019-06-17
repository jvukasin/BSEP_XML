package com.xml.MegaTravelAgent.controller;

import com.xml.MegaTravelAgent.dto.AccommodationSettingsDTO;
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
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAccommodationUnit(@PathVariable Long id)
	{
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody AccommodationUnitDTO accommodationUnit)
	{	
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}/amenities", method = RequestMethod.GET)
	public ResponseEntity<?> getAUAmenities(@PathVariable Long id)
	{

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	public ResponseEntity<AccommodationSettingsDTO> getAUSettings()
	{
		GetAccommodationSettingsResponse soapResponse = auClient.getAccommodationSettings();

		AccommodationSettingsDTO settingsDTO = new AccommodationSettingsDTO();

		settingsDTO.setAmenities(soapResponse.getAmenity());

		for (AccommodationType t: soapResponse.getAccommodationType()) {
			settingsDTO.accommodationTypes.add(t.getType());
		}



		return new ResponseEntity<AccommodationSettingsDTO>(settingsDTO, HttpStatus.OK);
	}
}
