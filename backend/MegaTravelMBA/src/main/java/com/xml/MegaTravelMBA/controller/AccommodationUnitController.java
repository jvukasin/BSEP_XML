package com.xml.MegaTravelMBA.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xml.MegaTravelMBA.dto.ExtendedSearchDTO;
import com.xml.MegaTravelMBA.dto.StandardSearchDTO;
import com.xml.MegaTravelMBA.model.AccommodationUnit;
import com.xml.MegaTravelMBA.model.User;

@RestController
@RequestMapping("/accommodations")
public class AccommodationUnitController
{
	
	//ROLE: neulogovan i ulogovan
	@RequestMapping(value = "/query/{searchDTO}", method = RequestMethod.GET)
	public ResponseEntity<?> search(@PathVariable("searchDTO") ExtendedSearchDTO dto)
	{
		return new ResponseEntity<>(HttpStatus.OK);
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
	public ResponseEntity<?> create(@RequestBody AccommodationUnit accommodationUnit)
	{	
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}