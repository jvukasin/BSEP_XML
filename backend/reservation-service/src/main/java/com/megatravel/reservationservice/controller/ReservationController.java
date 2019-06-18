package com.megatravel.reservationservice.controller;

import java.util.Collection;

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

import exceptions.BusinessException;


@RestController
@RequestMapping("/reservations")
public class ReservationController 
{
	
	@Autowired
	private ReservationService reservationService;
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Collection<ReservationDTO>> getReservations()
	{	
		return new ResponseEntity<Collection<ReservationDTO>>(reservationService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getReservation(@PathVariable Long id)
	{	
		try
		{
			return new ResponseEntity<ReservationDTO>(reservationService.findById(id), HttpStatus.OK);
		}
		catch(BusinessException e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	//ROLE: ulogovan, agnet
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
