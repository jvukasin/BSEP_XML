package com.xml.MegaTravelMBA.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.xml.MegaTravelMBA.dto.RatingCommentDTO;

@RestController
@RequestMapping("/ratingAndComments")
public class RatingCommentController 
{
	//ROLE: ulogovanw
	//TODO: user token ili id
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> createReservation(@RequestBody RatingCommentDTO dto)
	{	
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
