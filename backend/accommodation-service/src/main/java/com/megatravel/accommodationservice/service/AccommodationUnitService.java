package com.megatravel.accommodationservice.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megatravel.accommodationservice.dto.ExtendedSearchDTO;
import com.megatravel.accommodationservice.model.AccommodationUnit;
import com.megatravel.accommodationservice.model.Amenity;
import com.megatravel.accommodationservice.repository.AccommodationUnitRepository;
import com.megatravel.accommodationservice.repository.ReservationRepository;

@Service
public class AccommodationUnitService 
{
	@Autowired
	private AccommodationUnitRepository accommodationRepo;
	
	
	@Autowired
	private ReservationRepository reservationRepo;
	
	
	public Optional<AccommodationUnit> findById(Long id)
	{
		return accommodationRepo.findById(id);
	}
	
	public Collection<AccommodationUnit> findAll()
	{
		return accommodationRepo.findAll();
	}
	
	public Collection<AccommodationUnit> search(ExtendedSearchDTO dto)
	{
		return null;
	}
	
	

	

	
	
	
	private Collection<AccommodationUnit> selectType(Collection<AccommodationUnit> input, String type)
	{
		
		ArrayList<AccommodationUnit> retVal = new ArrayList<AccommodationUnit>();
		
		for(AccommodationUnit au : input)
		{
			if(au.getType().equals(type))
			{
				retVal.add(au);
			}
		}
		
		return retVal;
	}

	//search util method
	private Collection<AccommodationUnit> doesContainAmenities(Collection<AccommodationUnit> input, List<Amenity> amenities)
	{
		ArrayList<AccommodationUnit> retVal = new ArrayList<AccommodationUnit>();
		
		for(AccommodationUnit au : input)
		{
			if(au.getAmenity().containsAll(amenities))
			{
				retVal.add(au);
			}
		}
		
		return retVal;
	}
	private Collection<AccommodationUnit> aboveRating(Collection<AccommodationUnit> input, double rating)
	{
		ArrayList<AccommodationUnit> retVal = new ArrayList<AccommodationUnit>();
		for(AccommodationUnit au : input)
		{
			if(au.getRatingAvg() >= rating)
			{
				retVal.add(au);
			}
		}
		
		return retVal;
	}
	
}
