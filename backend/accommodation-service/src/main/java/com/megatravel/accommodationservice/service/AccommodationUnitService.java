package com.megatravel.accommodationservice.service;


import com.megatravel.accommodationservice.dto.*;
import com.megatravel.accommodationservice.model.*;
import com.megatravel.accommodationservice.repository.*;
import exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;
import java.util.*;

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
	private ReservationRepository reservationRepo;

	@Autowired
	private SpecificPriceRepository specificPriceRepo;
	
	@Autowired
	private TPersonRepository tPerrsonRepo;
	
	@Autowired
	private AccommodationTypeRepository typeRepo;
	
	@Autowired
	private AccommodationCategoryRepository categoryRepo;

	@Autowired
	EntityManager entityManager;

	private Logging logger = new Logging(this);

	
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
		String cty = dto.getCity().replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("\'", "&#x27;");
		List<City> cities = cityRepo.findByNameContainingIgnoreCase(cty);

		List<AccommodationUnit> list = new ArrayList<>();
		List<TotalPriceAccommodationDTO> ret = new ArrayList<TotalPriceAccommodationDTO>();

		for(City city : cities) 
		{
			if (city != null) 
			{
				list = accommodationRepo.search(city.getId(), dto.getPersonCount());
				list = excludeReserved(list, dto.getFromDate(), dto.getEndDate());
				
				
					
				if(dto.getCancellationPeriod() >= 0)
				{
					list = cancellationPer(list,dto.getCancellationPeriod());
				}

				if(dto.getType() != null && dto.getType() != "")
				{
					list = selectType(list,dto.getType());
				}

				if(dto.getAmenities().size() != 0 && dto.getAmenities() != null)
				{
					list = doesContainAmenities(list,dto.getAmenities());
				}

				if(dto.getCategory() != null && dto.getCategory() != "") 
				{
					list = isCategory(list,dto.getCategory());
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

	public AccommodationInfDTO setAUAvg(Long id, double rating){
		AccommodationUnit au = accommodationRepo.findOneById(id);

		au.setRatingAvg(rating);
		au = accommodationRepo.save(au);

		AccommodationUnitDTO dto = new AccommodationUnitDTO(au);
		AccommodationInfDTO dd = new AccommodationInfDTO(dto);

		return dd;
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

	public Collection<AccommodationType> findAllTypes() {
		return typeRepo.findAll();
	}

	
	public Collection<AccTypeDTO> findAllTypesDTO()
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
			dto.setType(dto.getType().replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("\'", "&#x27;"));
			typeRepo.save(dto);
		}
		catch(ConstraintViolationException e)
		{
			throw new BusinessException("A new type must be unique.");
		}
	}
	
	
	
	
	
	
	
	// * * * CATEGORIES * * *
	
	public Collection<AccommodationCategoryDTO> findAllCategoriesDTO()
	{
		ArrayList<AccommodationCategoryDTO> retVal = new ArrayList<AccommodationCategoryDTO>();

		for (AccommodationCategory a: categoryRepo.findAll()) 
		{
			retVal.add(new AccommodationCategoryDTO(a));
		}

		return retVal;
	}


	public Collection<AccommodationCategory> findAllCategories()
	{

		return categoryRepo.findAll();
	}

	public void deleteCategory(int value)
	{
		try
		{			
			categoryRepo.deleteById(value);
		}
		catch(NoSuchElementException e)
		{
			throw new BusinessException("No category: " + value + " found.");
		}
	}
	
	public void addCategory(AccommodationCategory dto) 
	{
		try
		{
			categoryRepo.save(dto);
		}
		catch(ConstraintViolationException e)
		{
			throw new BusinessException("A new type must be unique.");
		}
	}
	
	
	public Rating formRatingFromDTO (RatingDTO rDTO) {

		Rating r = new Rating();
		r.setId(rDTO.getId());
		r.setAccommodationId(rDTO.getAccommodation_id());
		r.setPostingDate(rDTO.getPosting_date());
		r.setReservator(rDTO.getReservator());
		r.setValue(rDTO.getValue());
		r.setComment(rDTO.getComment());

		if (rDTO.getApproved() == 1) {
			r.setApproved(true);
		} else {
			r.setApproved(false);
		}

		return r;

	}
	
	
	
	// * * * SEARCH UTILITIES * * *
	
	private List<AccommodationUnit> excludeReserved(List<AccommodationUnit> list, Date fromDate, Date endDate)
	{
		ArrayList<AccommodationUnit> retVal = new ArrayList<AccommodationUnit>();
		boolean found;

		for(AccommodationUnit accommodation : list)
		{
			
			found = false;
			for(Reservation r : reservationRepo.findAll())
			{
				if(r.getAccommodationUnit().getId() == accommodation.getId())
				{
					if(
						(r.getStartDate().getTime() <= fromDate.getTime() && r.getEndDate().getTime() >= fromDate.getTime())
						||
						(r.getStartDate().getTime() >= fromDate.getTime() && r.getStartDate().getTime() <= endDate.getTime())
					   )
						
					{
						found = true;
					}
				}
			}
			
			if(!found)
			{
				retVal.add(accommodation);
			}
			
		}
		
		return retVal;
	}
	
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
	private List<AccommodationUnit> cancellationPer(Collection<AccommodationUnit> input, int cancel)
	{
		ArrayList<AccommodationUnit> retVal = new ArrayList<AccommodationUnit>();
		for(AccommodationUnit au : input)
		{
			if(au.getCancellationPeriod() <= cancel)
			{
				retVal.add(au);
			}
		}
		
		return retVal;
	}

	private List<AccommodationUnit> isCategory(Collection<AccommodationUnit> input, String cat) {

		ArrayList<AccommodationUnit> retVal = new ArrayList<AccommodationUnit>();
		int ctg = Integer.parseInt(cat);
		for(AccommodationUnit au : input)
		{
			if(au.getCategory() == ctg)
			{
				retVal.add(au);
			}
		}

		return retVal;
	}


}
