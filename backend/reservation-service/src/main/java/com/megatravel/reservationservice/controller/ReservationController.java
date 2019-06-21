package com.megatravel.reservationservice.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.reservationservice.dto.ReservationDTO;
import com.megatravel.reservationservice.services.ReservationService;

import exceptions.BusinessException;


@RestController
@RequestMapping("/reservations")
public class ReservationController 
{
	
	@Autowired
	private ReservationService reservationService;
	
//
//	@RequestMapping(value = "/all", method = RequestMethod.GET)
//	public ResponseEntity<List<?>> getAllReservations() {
//
//	}

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
		catch(Exception e)
		{
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	
	//ROLE: ulogovan, agnet
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> createReservation(@RequestBody ReservationDTO dto)
	{	
		try
		{
			return new ResponseEntity<Long>(reservationService.create(dto), HttpStatus.OK);
		}
		catch(BusinessException e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	//ROLE: ulogovan
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> cancellReservation(@PathVariable Long reservationId)
	{	
		try
		{				
			reservationService.delete(reservationId);
			
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(BusinessException e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_MODIFIED);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	//ROLE: agent
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> setSuccessful(@PathVariable Long reservationId)
	{	
		try
		{				
			return new ResponseEntity<Long>(reservationService.setSuccessful(reservationId), HttpStatus.OK);
		}
		catch(BusinessException e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_MODIFIED);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
