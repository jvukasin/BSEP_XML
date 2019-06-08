package com.xml.MegaTravelMBA.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.xml.MegaTravelMBA.dto.RatingCommentDTO;

@RestController
@RequestMapping("/ratings")
public class RatingCommentController 
{
	//ROLE: ulogovan
	//TODO: user token ili id
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody RatingCommentDTO dto)
	{	
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/visibility", method = RequestMethod.POST)
	public ResponseEntity<?> commentVisibility(@RequestBody RatingCommentDTO dto)
	{	
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> getAllRatings()
	{	
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getSpecificRating(@PathVariable Long id)
	{	
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/accommodation/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getRatingForAU(@PathVariable Long id)
	{	
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
