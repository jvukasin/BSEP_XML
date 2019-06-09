package com.xml.MegaTravelMBA.service;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml.MegaTravelMBA.dto.ExtendedSearchDTO;
import com.xml.MegaTravelMBA.model.AccommodationUnit;
import com.xml.MegaTravelMBA.repository.AccommodationUnitRepository;
import com.xml.MegaTravelMBA.repository.ReservationRepository;

@Service
public class AccommodationUnitService 
{
	@Autowired
	private AccommodationUnitRepository accommodationRepo;
	
	/*
	@Autowired
	private ReservationRepository reservationRepo;
	*/
	
	public Optional<AccommodationUnit> findById(Long id)
	{
		return accommodationRepo.findById(id);
	}
	
	public Collection<AccommodationUnit> findAll()
	{
		return accommodationRepo.findAll();
	}
	
	/*
	public Collection<AccommodationUnit> search(ExtendedSearchDTO dto)
	{
		Collection<AccommodationUnit> list = accommodationRepo.findAll();
		
		
	}
	*/
	

	
	
	
	//search util methods
	/*
	private boolean isAccommodationAvailable(AccommodationUnit accommodation, Date from, Date until)
	{
		Collection<Reservation> list = reservationRepo.findOneByAccommodationUnit(accommodation);
	}
	*/
	
}
