package com.megatravel.accommodationservice.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megatravel.accommodationservice.dto.AccommodationUnitDTO;
import com.megatravel.accommodationservice.dto.AmenityDTO;
import com.megatravel.accommodationservice.dto.ExtendedSearchDTO;
import com.megatravel.accommodationservice.dto.ImageDTO;
import com.megatravel.accommodationservice.model.AccommodationUnit;
import com.megatravel.accommodationservice.model.Agent;
import com.megatravel.accommodationservice.model.Amenity;
import com.megatravel.accommodationservice.model.City;
import com.megatravel.accommodationservice.model.Image;
import com.megatravel.accommodationservice.model.Location;
import com.megatravel.accommodationservice.repository.AccommodationUnitRepository;
import com.megatravel.accommodationservice.repository.AmenityRepository;
import com.megatravel.accommodationservice.repository.CityRepository;
import com.megatravel.accommodationservice.repository.ImageRepository;
import com.megatravel.accommodationservice.repository.LocationRepository;
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
	private TPersonRepository personRepo;

	
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
			retVal.add(new AccommodationUnitDTO(a));
		}
		
		return retVal;
	}
	
	public Collection<AccommodationUnit> search(ExtendedSearchDTO dto)
	{
		Collection<AccommodationUnit> list = accommodationRepo.search(dto.getCityID(), dto.getPersonCount(), dto.getFromDate(), dto.getEndDate());
		
		if(dto.getRatingAvg() != -1)
		{
			list = aboveRating(list,dto.getRatingAvg());
		}
		
		if(dto.getType() != null)
		{
			list = selectType(list,dto.getType());
		}
		
		if(dto.getAmenities().size() != 0 && dto.getAmenities() != null)
		{
			list = doesContainAmenities(list,dto.getAmenities());
		}
		
		if(dto.getDistanceFromCity() != -1)
		{
			list = underDistance(list,dto.getDistanceFromCity());
		}
		
		return list;
	}
	
	public boolean save(AccommodationUnitDTO dto)
	{			
		AccommodationUnit accommodation = new AccommodationUnit();

		try
		{
			Agent agent = (Agent) personRepo.findById(dto.getAgent().getUsername()).get();
			accommodation.setAgent(agent);

		}
		catch(Exception e)
		{
			return false;
		}
		
		accommodation.setName(dto.getName());
		accommodation.setDescription(dto.getDescription());
		accommodation.setType(dto.getType());
		
		if(dto.getCapacity() < 1)
		{
			System.out.println("Izasao zbog: capacity");
			return false;
		}
			
		
		accommodation.setCapacity(dto.getCapacity());
		accommodation.setCancellationPeriod(dto.getCancellationPeriod());
		accommodation.setDefaultPrice(dto.getPrice());
		accommodation.setRatingAvg(-1);
		accommodation.setCategory(-1);
		
		accommodation.setAmenity(new HashSet<Amenity>());
		if(dto.getAmenities() != null)
		{
			for(AmenityDTO amenityDTO : dto.getAmenities())
			{
				try
				{
					Amenity amenity = amenityRepo.findById(amenityDTO.getId()).get();
					accommodation.getAmenity().add(amenity);
				}
				catch(NoSuchElementException e)
				{
					System.out.println("Izasao zbog: amenity" + amenityDTO.getId());
					return false;
				}
			}
		}

		
		accommodation.setImage(new HashSet<Image>());
		if(dto.getImages() != null)
		{
			for(ImageDTO imageDTO : dto.getImages())
			{
					Image image = new Image();
					image.setImageUrl(imageDTO.getImageUrl());
					
					accommodation.getImage().add(image);
					imageRepo.save(image);	
			}
		}

		
		Location location = new Location();
		try
		{
			location.setCoordinates(dto.getLocation().getCoordinates());
			location.setDistanceFromCity(dto.getLocation().getDistanceFromCity());
			
			City city = cityRepo.findById(dto.getLocation().getCity().getId()).get();
			location.setCity(city);
			
			accommodation.setLocation(location);
			locationRepo.save(location);
		}
		catch(NoSuchElementException e)
		{
			System.out.println("Izasao zbog: city" + dto.getLocation().getCity().getId());
			return false;
		}

		
		
		accommodationRepo.save(accommodation);
		return true;
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
