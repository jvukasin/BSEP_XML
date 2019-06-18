package com.megatravel.accommodationservice.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megatravel.accommodationservice.dto.AccommodationUnitDTO;
import com.megatravel.accommodationservice.dto.ExtendedSearchDTO;
import com.megatravel.accommodationservice.model.AccommodationUnit;
import com.megatravel.accommodationservice.model.Amenity;
import com.megatravel.accommodationservice.model.City;
import com.megatravel.accommodationservice.model.Image;
import com.megatravel.accommodationservice.model.Location;
import com.megatravel.accommodationservice.model.SpecificPrice;
import com.megatravel.accommodationservice.repository.AccommodationUnitRepository;
import com.megatravel.accommodationservice.repository.AmenityRepository;
import com.megatravel.accommodationservice.repository.CityRepository;
import com.megatravel.accommodationservice.repository.ImageRepository;
import com.megatravel.accommodationservice.repository.LocationRepository;
import com.megatravel.accommodationservice.repository.SpecificPriceRepository;
import com.megatravel.accommodationservice.repository.TPersonRepository;

import exceptions.BusinessException;

@Service
public class AccommodationUnitService 
{
	@Autowired
	private AccommodationUnitRepository accommodationRepo;
	
	@Autowired 
	private AmenityRepository amenityRepo;
	
	@Autowired
	private ImageRepository imageRepo;

	@Autowired
	private CityRepository cityRepo;

	@Autowired
	private LocationRepository locationRepo;

	@Autowired
	private SpecificPriceRepository specificPriceRepo;
	
	@Autowired
	private TPersonRepository personRepo;

	@Autowired
	EntityManager entityManager;

	
	public AccommodationUnitDTO findById(Long id)
	{
		try
		{
			return new AccommodationUnitDTO(accommodationRepo.findById(id).get());
		}
		catch(NoSuchElementException e)
		{
			throw new BusinessException("No accommodation unit with id: " + id + " found.");
		}
	}
	
	public Collection<AccommodationUnitDTO> findAll()
	{
		Collection<AccommodationUnit> list = accommodationRepo.findAll();
		Collection<AccommodationUnitDTO> retVal = new ArrayList<AccommodationUnitDTO>();
		for(AccommodationUnit a : list)
		{
			AccommodationUnitDTO dto = new AccommodationUnitDTO(a);
			retVal.add(dto);
		}

		return retVal;
	}

	public AccommodationUnit saveFromSoap(AccommodationUnit au) {

		Location loc = locationRepo.save(au.getLocation());
		au.setLocation(loc);
		accommodationRepo.save(au);

		for (SpecificPrice sp: au.getSpecificPrice()) {
			sp.setAccommodationUnit(au);
		}

		for (Image i: au.getImage()) {
			i.setAccommodationUnit(au);
		}

		specificPriceRepo.saveAll(au.getSpecificPrice());
		imageRepo.saveAll(au.getImage());

		return au;

	}


	
	public Collection<AccommodationUnitDTO> search(ExtendedSearchDTO dto)
	{
		City city = cityRepo.findOneByName(dto.getCity());

		Collection<AccommodationUnit> list = accommodationRepo.search(city.getId(), dto.getPersonCount(), dto.getFromDate(), dto.getEndDate());
		
		if(dto.getRatingAvg() > 0)
		{
			list = aboveRating(list,dto.getRatingAvg());
		}
		
		if(dto.getType() != null && dto.getType() != "")
		{
			list = selectType(list,dto.getType());
		}
		
		if(dto.getAmenities().size() != 0 && dto.getAmenities() != null)
		{
			list = doesContainAmenities(list,dto.getAmenities());
		}

		if(dto.getDistanceFromCity() >=0 )
		{
			list = underDistance(list,dto.getDistanceFromCity());
		}

		List<AccommodationUnitDTO> ret = new ArrayList<AccommodationUnitDTO>();

		for(AccommodationUnit a : list) {
			AccommodationUnitDTO adto = new AccommodationUnitDTO(a);
			ret.add(adto);
		}
		
		return ret;
	}
	
	
	
	
	
	
	// * * * SEARCH UTILITIES * * *
	
	private Collection<AccommodationUnit> underDistance(Collection<AccommodationUnit> input, double distanceFromCity)
	{
		ArrayList<AccommodationUnit> retVal = new ArrayList<AccommodationUnit>();
		
		for(AccommodationUnit au : input)
		{
			if(au.getLocation().getDistanceFromCity() <= distanceFromCity)
			{
				retVal.add(au);
			}
		}
		
		return retVal;
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
