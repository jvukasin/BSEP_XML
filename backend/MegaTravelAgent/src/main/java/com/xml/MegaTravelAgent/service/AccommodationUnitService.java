package com.xml.MegaTravelAgent.service;

import java.util.*;

import com.xml.MegaTravelAgent.dto.*;
import com.xml.MegaTravelAgent.exceptions.BusinessException;
import com.xml.MegaTravelAgent.model.*;
import com.xml.MegaTravelAgent.repository.*;
import com.xml.MegaTravelAgent.soap.client.AccommodationUnitClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


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

	@Autowired
	private SpecificPriceRepository specificPriceRepo;

	@Autowired
	private AccommodationUnitClient auClient;

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
		return null;
	}


 	@Transactional
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

		if(dto.getDefaultPrice() <= 0)
		{
			throw new BusinessException("Price can not be 0 or negative.");
		}


		if(dto.getCancellationPeriod() < 0)
		{
			throw new BusinessException("Cancellation period can not be negative.");
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
//				imageRepo.save(image);
			}
		} else {
			throw new BusinessException("There must be at least one image.");
		}


		au.setSpecificPrice(new ArrayList<>());

		if (dto.getSpecificPrices() != null) {



			for (SpecificPriceDTO spDTO: dto.getSpecificPrices()) {
				SpecificPrice sp = new SpecificPrice();

				if(spDTO.getPrice() <= 0)
				{
					throw new BusinessException("Price can not be 0 or negative.");
				}

				if (spDTO.getStartDate().after(spDTO.getEndDate())) {
					throw new BusinessException("Specific price plan start date can not be after end date.");
				}

				sp.setPrice(spDTO.getPrice());
				sp.setStartDate(spDTO.getStartDate());
				sp.setEndDate(spDTO.getEndDate());

//				sp = specificPriceRepo.save(sp);
				au.getSpecificPrice().add(sp);

			}
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

		for (SpecificPrice sp: au.getSpecificPrice()) {
			sp.setAccommodationUnit(au);
		}

		for (Image i: au.getImage()) {
			i.setAccommodationUnit(au);
		}

		specificPriceRepo.saveAll(au.getSpecificPrice());
		imageRepo.saveAll(au.getImage());

		// send to soap
		auClient.createAccommodationUnit(au, new Long(-1));


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
