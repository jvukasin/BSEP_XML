package com.xml.MegaTravelMBA.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml.MegaTravelMBA.dto.ExtendedSearchDTO;
import com.xml.MegaTravelMBA.model.AccommodationUnit;
import com.xml.MegaTravelMBA.model.Amenity;
import com.xml.MegaTravelMBA.repository.AccommodationUnitRepository;
import com.xml.MegaTravelMBA.repository.ReservationRepository;

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
		try
		{
			Collection<AccommodationUnit> retVal =  accommodationRepo.search( dto.getCityID(), dto.getPersonCount(),dto.getFromDate(),dto.getEndDate());
			
			if(dto.getAmenities().size() != 0)
			{
				retVal = doesContainAmenities(retVal,dto.getAmenities());
			}
			if(dto.getRatingAvg() != null)
			{
				retVal = aboveRating(retVal,dto.getRatingAvg());
			}
			//TODO: distance from city
			
			if(dto.getType() != null)
			{
				retVal = selectType(retVal, dto.getType());
			}
			
			return retVal;
		}
		catch(NoSuchElementException e)
		{
			return new ArrayList<AccommodationUnit>();
		}
		

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
