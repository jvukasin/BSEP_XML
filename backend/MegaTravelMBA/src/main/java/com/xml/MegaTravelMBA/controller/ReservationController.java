package com.xml.MegaTravelMBA.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xml.MegaTravelMBA.model.Reservation;

@RestController
@RequestMapping("/reservations")
public class ReservationController 
{
	//ROLE: ulogovan
	//TODO: user token ili id
	//ako ne bude na ne bude moglo da se zameni u modelu XMLGregorianCalendar sa Date, pravicemo DTO
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> createReservation(@RequestBody Reservation dto)
	{	
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	//ROLE: ulogovan
	@RequestMapping(value = "/cancell", method = RequestMethod.DELETE)
	public ResponseEntity<?> cancellResetvation(@RequestBody Reservation dto)
	{	
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
