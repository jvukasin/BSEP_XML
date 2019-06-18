package com.xml.MegaTravelAgent.service;

import java.util.*;

import com.xml.MegaTravelAgent.dto.AmenityDTO;
import com.xml.MegaTravelAgent.dto.ImageDTO;
import com.xml.MegaTravelAgent.dto.NewAccommodationUnitDTO;
import com.xml.MegaTravelAgent.exceptions.BusinessException;
import com.xml.MegaTravelAgent.model.*;
import com.xml.MegaTravelAgent.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xml.MegaTravelAgent.dto.ExtendedSearchDTO;


@Service
public class AccommodationUnitService 
{
	@Autowired
	private AccommodationUnitRepository accommodationRepo;

	@Autowired
	private ReservationRepository reservationRepo;

	@Autowired
	private AmenityRepository amenityRepo;

	@Autowired
	private ImageRepository imageRepo;

	@Autowired
	private CityRepository cityRepo;

	@Autowired
	private LocationRepository locationRepo;


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



	public Long save(NewAccommodationUnitDTO dto) throws Exception {

		AccommodationUnit au = new AccommodationUnit();

		/*try
		{
			Agent agent = (Agent) personRepo.findById(dto.getAgent().getUsername()).get();
			accommodation.setAgent(agent);

		}
		catch(Exception e)
		{
			return false;
		}*/

		au.setName(dto.getName());
		au.setDescription(dto.getDescription());
		au.setType(dto.getType());

		if(dto.getCapacity() < 1)
		{
			throw new BusinessException("Capacity can not be lower than 1.");
		}


		au.setCapacity(dto.getCapacity());
		au.setCancellationPeriod(dto.getCancellationPeriod());
		au.setDefaultPrice(dto.getDefaultPrice());
		au.setRatingAvg(-1);
		au.setCategory(-1);

		au.setAmenity(new HashSet<Amenity>());

		if(dto.getAmenities() != null)
		{
			for(AmenityDTO amenityDTO : dto.getAmenities())
			{
				try
				{
					Amenity amenity = amenityRepo.findById(amenityDTO.getId()).get();
					au.getAmenity().add(amenity);
				}
				catch(NoSuchElementException e)
				{
					throw new BusinessException("Given amenity does not exist.", e);
				}
			}
		}


		au.setImage(new HashSet<Image>());
		if(dto.getImages() != null && dto.getImages().size() > 0)
		{
			for(ImageDTO imageDTO : dto.getImages())
			{
				Image image = new Image();
				image.setImageUrl(imageDTO.getImageUrl());

				au.getImage().add(image);
				imageRepo.save(image);
			}
		} else {
			throw new BusinessException("There must be at least one image.");
		}


		Location location = new Location();
		try
		{
			location.setCoordinates(dto.getLocation().getCoordinates());
			location.setDistanceFromCity(dto.getLocation().getDistanceFromCity());

			City city = cityRepo.findById(dto.getLocation().getCity().getId()).get();
			location.setCity(city);

			au.setLocation(location);
			locationRepo.save(location);
		}
		catch(NoSuchElementException e)
		{
			throw new BusinessException("Given city does not exists.", e);
		}


		au = accommodationRepo.save(au);

		return au.getId();
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
