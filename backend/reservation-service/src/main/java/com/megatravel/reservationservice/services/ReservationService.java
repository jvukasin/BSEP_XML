package com.megatravel.reservationservice.services;

import java.text.SimpleDateFormat;
import java.util.*;

import com.megatravel.reservationservice.dto.*;
import com.megatravel.reservationservice.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megatravel.reservationservice.repository.AccommodationUnitRepository;
import com.megatravel.reservationservice.repository.ReservationRepository;
import com.megatravel.reservationservice.repository.TPersonRepository;

import exceptions.BusinessException;

@Service
public class ReservationService 
{
	@Autowired
	private ReservationRepository reservationRepo;
	
	@Autowired
	private TPersonRepository personRepo;
	
	@Autowired
	private AccommodationUnitRepository accommodationRepo;
	
	public Collection<ReservationDTO> findAll()
	{
		Collection<Reservation> list = reservationRepo.findAll();
		Collection<ReservationDTO> retVal = new ArrayList<ReservationDTO>();
		
		for(Reservation reservation : list)
		{
			retVal.add(new ReservationDTO(reservation));
		}
		
		return retVal;
	}

	public ReservationDTO findById(Long id)
	{
		try
		{
			return new ReservationDTO(reservationRepo.findById(id).get());
		}
		catch(NoSuchElementException e)
		{	
			throw new BusinessException("No reservation with id: " + id + " found.");
		}
	}
	
	
	public Long create(ReservationDTO dto)
	{
		Reservation reservation = new Reservation();
	
		try
		{
			AccommodationUnit accommodation = accommodationRepo.findById(dto.getAccommodationUnitId()).get();
			reservation.setAccommodationUnit(accommodation);
		}
		catch(NoSuchElementException e)
		{
			throw new BusinessException("An Unknow accommodation appeared in request.");
		}
		
		
		
		
		if(dto.getStartDate().getTime() >= dto.getEndDate().getTime())
		{
			throw new BusinessException("Invalid start and end date input in request.");
		}
		
		if(isOccupied(dto.getStartDate(), dto.getEndDate(), reservation.getAccommodationUnit()))
		{
			throw new BusinessException("The accommodation is occupied during the selected dates.");
		}
		
		
		reservation.setStartDate(dto.getStartDate());
		reservation.setEndDate(dto.getEndDate());
		reservation.setIsSuccessful(false);


		double price = findPrice(dto.getStartDate(), dto.getEndDate(), reservation.getAccommodationUnit());
		reservation.setPrice(price);
		

		
		try
		{
			User reservator;
			
			//if the reservation request is coming form an agent who own the accommodation, reservator field is null
			if(dto.getReservator().getUsername().equals(reservation.getAccommodationUnit().getAgent().getUsername()))
			{
				reservator = null;
			}
			else
			{
				reservator = (User) personRepo.findOneByUsername(dto.getReservator().getUsername());
			}
			
			reservation.setReservator(reservator);
		}
		catch(Exception e)
		{
			throw new BusinessException("An unauthorized person is trying to create a reservation.");
		}
		
		
		reservationRepo.save(reservation);
		return reservation.getId();
	}
	
	
	public void delete(Long id)
	{
		try
		{
			Reservation reservation = reservationRepo.findById(id).get();
			
			reservationRepo.delete(reservation);
		}
		catch(NoSuchElementException e)
		{	
			throw new BusinessException("No reservation with id: " + id + " found.");
		}
	}
	
	public Long setSuccessful(Long id)
	{
		try
		{
			Reservation reservation = reservationRepo.findById(id).get();
			
			reservation.setIsSuccessful(true);
			
			reservationRepo.setSuccessful(id);
			
			return id;
		}
		catch(NoSuchElementException e)
		{	
			throw new BusinessException("No reservation with id: " + id + " found.");
		}
	}

	public List<UserReservationDTO> getUserReservations(String username){
		List<Reservation> reservations = reservationRepo.getUserReservations(username);
		List<UserReservationDTO> retVal = new ArrayList<UserReservationDTO>();

		for(Reservation reservation: reservations){
			//uzmi prvu sliku za prikaz u listi
			Image image = reservation.getAccommodationUnit().getImage().iterator().next();
			//konvertuj poruke u DTO objetke
			List<MessageDTO> messages = new ArrayList<MessageDTO>();
			for(Message m : reservation.getMessages()){
				messages.add(new MessageDTO(m));
			}

			retVal.add(new UserReservationDTO(reservation.getStartDate(), reservation.getEndDate(), reservation.getPrice(),
						new AccommodationInfoDTO(reservation.getAccommodationUnit().getName(), image.getImageUrl(),
								new LocationDTO(reservation.getAccommodationUnit().getLocation()), reservation.getAccommodationUnit().getRatingAvg()), messages));
		}
		return retVal;
	}
	
	
	// * * * PRICE UNITILITES * * *
	@SuppressWarnings("deprecation")
	public double findPrice(Date start, Date end, AccommodationUnit accommodation)
	{
		

		
		//incrementing for one day in milliseconds from start date, until end date
		double retVal = 0;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		for( long i = start.getTime() ; i <= end.getTime() ; i = i + 86400000L )
		{
			Date currentDay = new Date(i);

			System.out.println(" * RAZMATRA: " + format.format(currentDay));
			boolean found = false;
			for(SpecificPrice specificPrice : accommodation.getSpecificPrice())
			{
				System.out.println("gleda u special price: " + format.format(specificPrice.getStartDate()) + " - " + format.format(specificPrice.getEndDate()));

				if(isInSpecificPrice(currentDay,specificPrice))
				{
					retVal = retVal + specificPrice.getPrice();
					found = true;
					break;
				}
			}
			
			if(!found)
			{
				retVal = retVal + accommodation.getDefaultPrice();
			}
		}
		return retVal;
	}
	
	private boolean isInSpecificPrice(Date currentDay, SpecificPrice specificPrice) 
	{
		if(currentDay.getTime() >= specificPrice.getStartDate().getTime()
		   && 
		   currentDay.getTime() <= (specificPrice.getEndDate().getTime()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
    
	
	
	
	// * * * RESERVATION DATE UTILS * * *
	
	private boolean isOccupied(Date startDate, Date endDate, AccommodationUnit accommodationUnit)
	{
		startDate.setHours(0);
		startDate.setMinutes(0);
		startDate.setSeconds(0);
		endDate.setHours(0);
		endDate.setMinutes(0);
		endDate.setSeconds(0);
		
		boolean retVal = false;
		Collection<Reservation> list = reservationRepo.findOneByAccommodationUnit(accommodationUnit);
		
		for(Reservation reservation : list)
		{
			if(isOverlapping(startDate, endDate, reservation))
			{
				retVal = true;
			}
		}
		
		return retVal;
	}

	private boolean isOverlapping(Date start, Date end, Reservation reservation) 
	{
		if(start.getTime() <= reservation.getStartDate().getTime() && end.getTime() > reservation.getStartDate().getTime())
		{
			return true;
		}
		if(start.getTime() >= reservation.getStartDate().getTime() && start.getTime() <= reservation.getEndDate().getTime())
		{
			return true;
		}
		
		return false;
	}
	
}
