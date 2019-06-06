package com.xml.MegaTravelMBA.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xml.MegaTravelMBA.dto.ExtendedSearchDTO;
import com.xml.MegaTravelMBA.dto.StandardSearchDTO;
import com.xml.MegaTravelMBA.model.AccommodationUnit;

@RestController
@RequestMapping("/accommodations")
public class AccommodationUnitController
{
	
	//ROLE: neulogovan i ulogovan
	@RequestMapping(value = "/standardSeatch/{searchDTO}", method = RequestMethod.GET)
	public ResponseEntity<?> getViaStandardSearch(@PathVariable("searchDTO") StandardSearchDTO dto)
	{
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	//ROLE: neulogovan i ulogovan
	@RequestMapping(value = "/extendedSearch/{searchDTO}", method = RequestMethod.GET)
	public ResponseEntity<?> getViaExtendedSearch(@PathVariable("searchDTO") ExtendedSearchDTO dto)
	{
		return new ResponseEntity<>(HttpStatus.OK);
	}

	
	//ROLE: agent i admin
	//TODO: dto?
	@RequestMapping(value = "/edit/{accommodation}", method = RequestMethod.PUT)
	public ResponseEntity<?> editAccommodation(@PathVariable("accommodation") AccommodationUnit dto)
	{
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
