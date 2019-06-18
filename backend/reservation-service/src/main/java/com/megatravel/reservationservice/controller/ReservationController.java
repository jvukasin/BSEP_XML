package com.megatravel.reservationservice.controller;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.megatravel.reservationservice.dto.ReservationDTO;
import com.megatravel.reservationservice.model.Reservation;
import com.megatravel.reservationservice.services.ReservationService;


@RestController
@RequestMapping("/reservations")
public class ReservationController 
{
	
	@Autowired
	private ReservationService reservationService;
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Collection<Reservation>> getReservations()
	{	
		return new ResponseEntity<Collection<Reservation>>(reservationService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Reservation> getReservation(@PathVariable Long id)
	{	
		try
		{
			return new ResponseEntity<Reservation>(reservationService.findById(id).get(), HttpStatus.OK);
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
			return new ResponseEntity<Reservation>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	//ROLE: ulogovan, agnet
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<> createReservation(@RequestBody ReservationDTO dto)
	{	
		if()
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
