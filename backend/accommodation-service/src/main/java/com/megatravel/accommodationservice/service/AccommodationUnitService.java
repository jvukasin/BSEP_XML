package com.megatravel.accommodationservice.service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;

import com.megatravel.accommodationservice.dto.*;
import com.megatravel.accommodationservice.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megatravel.accommodationservice.repository.AccommodationTypeRepository;
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
	private TPersonRepository tPerrsonRepo;
	
	@Autowired
	private AccommodationTypeRepository typeRepo;

	@Autowired
	EntityManager entityManager;

	
	public AccommodationUnitDTO findById(Long id)
	{
		try
		{
			return new AccommodationUnitDTO(accommodationRepo.findOneById(id));
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


	public AccommodationUnit saveFromSoap(AccommodationUnit au, String agentUsername) {

		Agent agent = (Agent) tPerrsonRepo.findOneByUsername(agentUsername);
		au.setAgent(agent);

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


	
	public Collection<TotalPriceAccommodationDTO> search(ExtendedSearchDTO dto)
	{
		
		if(dto.getCity() == null || dto.getCity() == "" || dto.getEndDate() == null || dto.getFromDate() == null || dto.getPersonCount() < 1)
		{
			throw new BusinessException("Invalid search query.");
		}
		
		if(dto.getFromDate().getTime() >= dto.getEndDate().getTime())
		{
			throw new BusinessException("Invalid search dates.");
		}
		
		List<City> cities = cityRepo.findByNameContainingIgnoreCase(dto.getCity());

		List<AccommodationUnit> list = new ArrayList<>();
		List<TotalPriceAccommodationDTO> ret = new ArrayList<TotalPriceAccommodationDTO>();

		for(City city : cities) 
		{
			if (city != null) 
			{
				list = accommodationRepo.search(city.getId(), dto.getPersonCount(), dto.getFromDate(), dto.getEndDate());

				if(dto.getRatingAvg() >= 0)
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

				if(dto.getDistanceFromCity() >=0)
				{
					list = underDistance(list,dto.getDistanceFromCity());
				}
			}

			for(AccommodationUnit a : list) 
			{	
				TotalPriceAccommodationDTO adto = new TotalPriceAccommodationDTO(a,dto.getFromDate(), dto.getEndDate());
				ret.add(adto);
			}
		}

		return ret;
	}
	

	public List<Amenity> findAllAmenities() {
		return amenityRepo.findAll();
	}
	
	public List<AmenityDTO> findAllAmenitiesDTO() {

		List<AmenityDTO> retVal = new ArrayList<>();

		for (Amenity a: amenityRepo.findAll()) {
			retVal.add(new AmenityDTO(a));
		}

		return retVal;
	}
	
	
	
	
	//* * * TYPES * * *
	
	public Collection<AccTypeDTO> findAllTypes()
	{
		List<AccTypeDTO> retVal = new ArrayList<>();

		for (AccommodationType a: typeRepo.findAll()) {
			retVal.add(new AccTypeDTO(a));
		}

		return retVal;
	}
	
	public void deleteType(String type)
	{
		try
		{			
			typeRepo.deleteById(type);
		}
		catch(NoSuchElementException e)
		{
			throw new BusinessException("No type: " + type + " found.");
		}
	}
	
	
	public void addType(AccommodationType dto) 
	{
		try
		{
			typeRepo.save(dto);
		}
		catch(ConstraintViolationException e)
		{
			throw new BusinessException("A new type must be unique.");
		}
	}
	
	
	
	
	
	
	
	// * * * SEARCH UTILITIES * * *
	
	private List<AccommodationUnit> underDistance(Collection<AccommodationUnit> input, double distanceFromCity)
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

	private List<AccommodationUnit> selectType(Collection<AccommodationUnit> input, String type)
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

	
	private List<AccommodationUnit> doesContainAmenities(Collection<AccommodationUnit> input, List<Amenity> amenities)
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
	private List<AccommodationUnit> aboveRating(Collection<AccommodationUnit> input, double rating)
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
