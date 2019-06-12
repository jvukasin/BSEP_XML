package com.megatravel.reservationservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.reservationservice.dto.ReservationDTO;


@RestController
@RequestMapping("/reservations")
public class ReservationController 
{
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> getReservations()
	{	
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getReservation(@PathVariable Long id)
	{	
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//ROLE: ulogovan, agnet
	//TODO: user token ili id
	//ako ne bude na ne bude moglo da se zameni u modelu XMLGregorianCalendar sa Date, pravicemo DTO
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> createReservation(@RequestBody ReservationDTO dto)
	{	
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	//ROLE: ulogovan
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> cancellReservation(@PathVariable Long reservationId)
	{	
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//ROLE: agent
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> setActive(@PathVariable Long reservationId)
	{	
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
