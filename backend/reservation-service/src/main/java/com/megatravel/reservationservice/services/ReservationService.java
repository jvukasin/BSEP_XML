package com.megatravel.reservationservice.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megatravel.reservationservice.dto.ReservationDTO;
import com.megatravel.reservationservice.model.Reservation;
import com.megatravel.reservationservice.model.TPerson;
import com.megatravel.reservationservice.repository.ReservationRepository;
import com.megatravel.reservationservice.repository.TPersonRepository;

@Service
public class ReservationService 
{
	@Autowired
	private ReservationRepository reservationRepo;
	
	@Autowired
	private TPersonRepository personRepo;
	
	
	public Collection<Reservation> findAll()
	{
		return reservationRepo.findAll();
	}

	public Optional<Reservation> findById(Long id)
	{
		return reservationRepo.findById(id);
	}
	
	
	public boolean create(ReservationDTO dto)
	{
		Reservation reservation = new Reservation();
	
		if(dto.getStartDate().getTime() >= dto.getEndDate().getTime())
		{
			return false;
		}
		
		reservation.setStartDate(dto.getStartDate());
		reservation.setEndDate(dto.getEndDate());
		reservation.setIsSuccessful(false);
		
		try
		{
			TPerson reservator = personRepo.findById(dto.getReservator().getUsername()).get();
			reservation.setReservator(reservator);
		}
		catch(Exception e)
		{
			return false;
		}
		
		
	}
	
}
