package com.megatravel.reservationservice.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megatravel.reservationservice.dto.ReservationDTO;
import com.megatravel.reservationservice.model.Reservation;
import com.megatravel.reservationservice.model.TPerson;
import com.megatravel.reservationservice.model.User;
import com.megatravel.reservationservice.repository.ReservationRepository;
import com.megatravel.reservationservice.repository.TPersonRepository;

import exceptions.BusinessException;

@Service
public class ReservationService 
{
	@Autowired
	private ReservationRepository reservationRepo;
	
	@Autowired
	private TPersonRepository personRepo;
	
	
	public Collection<ReservationDTO> findAll()
	{
		Collection<Reservation> list = reservationRepo.findAll();
		Collection<ReservationDTO> retVal = new ArrayList<ReservationDTO>();
		
		for(Reservation reservation : list)
		{
			retVal.add(new ReservationDTO(reservation));
		}
		
		return retVal;
	}

	public ReservationDTO findById(Long id)
	{
		try
		{
			return new ReservationDTO(reservationRepo.findById(id).get());
		}
		catch(NoSuchElementException e)
		{	
			throw new BusinessException("No reservation with id: " + id + " found.");
		}
	}
	
	
	public boolean create(ReservationDTO dto)
	{
		Reservation reservation = new Reservation();
	
		if(dto.getStartDate().getTime() >= dto.getEndDate().getTime())
		{
			throw new BusinessException("Invalid start and end date input in request.");
		}
		
		reservation.setStartDate(dto.getStartDate());
		reservation.setEndDate(dto.getEndDate());
		reservation.setIsSuccessful(false);
		
		try
		{
			User reservator = (User) personRepo.findById(dto.getReservator().getUsername()).get();
			
		}
		catch(Exception e)
		{
			throw new BusinessException("Invalid start and end date input in request.");
		}
		
		
	}
	
}
