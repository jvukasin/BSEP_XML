package com.megatravel.reservationservice.services;

import java.text.SimpleDateFormat;
import java.util.*;

import com.megatravel.reservationservice.dto.*;
import com.megatravel.reservationservice.model.*;
import com.megatravel.reservationservice.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.megatravel.reservationservice.repository.AccommodationUnitRepository;
import com.megatravel.reservationservice.repository.ReservationRepository;
import com.megatravel.reservationservice.repository.TPersonRepository;

import exceptions.BusinessException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ReservationService 
{
	@Autowired
	private ReservationRepository reservationRepo;
	
	@Autowired
	private TPersonRepository personRepo;
	
	@Autowired
	private AccommodationUnitRepository accommodationRepo;

	@Autowired
	private MessageRepository messageRepository;
	
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

	public ReservationDTO findById(Long id, String username)
	{
		try
		{
			Reservation r = reservationRepo.findById(id).get();
			if (!r.getReservator().getUsername().equals(username)) {
				throw new BusinessException("You can access only your reservations.");
			}
			return new ReservationDTO(r);
		}
		catch(NoSuchElementException e)
		{	
			throw new BusinessException("No reservation with id: " + id + " found.");
		}
	}

	@Transactional(isolation = Isolation.REPEATABLE_READ)
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
			throw new OptimisticLockingFailureException("The accommodation is occupied during the selected dates.");
		}
		
		
		reservation.setStartDate(dto.getStartDate());
		reservation.setEndDate(dto.getEndDate());
		reservation.setIsSuccessful(false);


		double price = findPrice(dto.getStartDate(), dto.getEndDate(), reservation.getAccommodationUnit());
		reservation.setPrice(price);
		

		
		try
		{
			TPerson reservator;
			
			//if the reservation request is coming form an agent who own the accommodation, reservator field is null
			if(dto.getReservator().getUsername().equals(reservation.getAccommodationUnit().getAgent().getUsername()))
			{
				reservator = null;
			}
			else
			{
				reservator = personRepo.findOneByUsername(dto.getReservator().getUsername());
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

	public Reservation createFromSOAP(Reservation r) {

		try
		{
			AccommodationUnit accommodation = accommodationRepo.findById(r.getAccommodationUnit().getId()).get();
			r.setAccommodationUnit(accommodation);
		}
		catch(NoSuchElementException e)
		{
			throw new BusinessException("An Unknow accommodation appeared in request.");
		}


		if(r.getStartDate().getTime() >= r.getEndDate().getTime())
		{
			throw new BusinessException("Invalid start and end date input in request.");
		}

		if(isOccupied(r.getStartDate(), r.getEndDate(), r.getAccommodationUnit()))
		{
			throw new BusinessException("The accommodation is occupied during the selected dates.");
		}

		double price = findPrice(r.getStartDate(), r.getEndDate(), r.getAccommodationUnit());
		r.setPrice(price);



		return reservationRepo.save(r);


	}
	
	
	public void delete(Long id)
	{
		try
		{
			Reservation reservation = reservationRepo.findById(id).get();
			int dayz = calcDayDiff(reservation.getStartDate());
			if(dayz >= reservation.getAccommodationUnit().getCancellationPeriod()) {
				reservationRepo.delete(reservation);
			} else {
				throw new BusinessException("Cannot cancel reservation after cancellation period has passed.");
			}
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
			/*for(Message m : reservation.getMessages()){
				messages.add(new MessageDTO(m));
			}*/

			retVal.add(new UserReservationDTO(reservation.getId(), reservation.getStartDate(), reservation.getEndDate(), reservation.getPrice(),
					new AccommodationInfoDTO(reservation.getAccommodationUnit().getName(), image.getImageUrl(),
							new LocationDTO(reservation.getAccommodationUnit().getLocation()), reservation.getAccommodationUnit().getRatingAvg(),
							reservation.getAccommodationUnit().getCancellationPeriod(), reservation.getAccommodationUnit().getCategory()), messages));
		}
		return retVal;
	}


	public List<Reservation> getAgentReservations(String username){
		List<Reservation> reservations = reservationRepo.getAgentReservations(username);

		// todo messages

		return reservations;
	}


	public ChatDTO getChat(Long id, String username) {

		Reservation r = reservationRepo.findById(id).get();

		if (!r.getReservator().getUsername().equals(username)) {

			throw new BusinessException("You can access only your messages.");
		}

		List<Message> messages = messageRepository.findAllByReservationId(id);

		ChatDTO chatDTO = new ChatDTO();

		chatDTO.setAgentUsername(r.getAccommodationUnit().getAgent().getUsername());
		chatDTO.setUserUsername(username);

		for (Message m: messages) {
			chatDTO.getMessages().add(new MessageDTO(m));
		}

		return chatDTO;

	}

	public MessageDTO postMesage(MessageDTO messageDTO, String username) {

		Message m = new Message();

		if (!messageDTO.getContent().equals("")) {
			m.setContent(messageDTO.getContent());
		} else {
			throw new BusinessException("Message content can not be empty.");
		}

		m.setDate(new Date(System.currentTimeMillis()));

		try {
			Reservation r = reservationRepo.findById(messageDTO.getReservationId()).get();
			m.setReservation(r);
			m.setIdReservation(r.getId());

			Agent a = r.getAccommodationUnit().getAgent();

			m.setReceiver(a);
			m.setUsernameReceiver(a.getUsername());

		} catch (NoSuchElementException e) {
			throw new BusinessException("Reservation with given id does not exist.");
		}


		TPerson u = personRepo.findOneByUsername(username);

		m.setSender(u);
		m.setUsernameSender(u.getUsername());

		m.setIsAgentsMessage(false);
		m.setIsUsersMessage(true);

		m = messageRepository.save(m);

		return new MessageDTO(m);
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

	private int calcDayDiff(Date start) {
		Date today = new Date();
		long dei = Math.abs(start.getTime() - today.getTime());
		int days = (int) (dei / (1000*60*60*24));
		return days;
	}

    public Message postMessageFromSOAP(Message message, String agentUsername) {



		TPerson sender = personRepo.findOneByUsername(agentUsername);
		message.setSender(sender);

		Reservation r = reservationRepo.findById(message.getIdReservation()).get();
		message.setReservation(r);

		TPerson receiver = personRepo.findOneByUsername(r.getReservator().getUsername());
		message.setReceiver(receiver);

		message.setIsAgentsMessage(true);

		return messageRepository.save(message);

	}

	public List<Message> getAllMessagesByReservationId(Long id) {
		List<Message> messages = messageRepository.findAllByReservationId(id);

		for (Message m: messages) {
			m.setUsernameSender(m.getSender().getUsername());
			m.setUsernameReceiver(m.getReceiver().getUsername());
			m.setIdReservation(m.getReservation().getId());
		}

		return messages;
	}
}
