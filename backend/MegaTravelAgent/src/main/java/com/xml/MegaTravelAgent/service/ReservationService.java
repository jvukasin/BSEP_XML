package com.xml.MegaTravelAgent.service;

import com.xml.MegaTravelAgent.dto.ChatDTO;
import com.xml.MegaTravelAgent.dto.MessageDTO;
import com.xml.MegaTravelAgent.dto.ReservationDTO;
import com.xml.MegaTravelAgent.exceptions.BusinessException;
import com.xml.MegaTravelAgent.model.*;
import com.xml.MegaTravelAgent.repository.MessageRepository;
import com.xml.MegaTravelAgent.repository.ReservationRepository;
import com.xml.MegaTravelAgent.repository.TPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepo;

    @Autowired
    TPersonRepository tPersonRepository;

    @Autowired
    MessageRepository messageRepository;

    public ReservationDTO findById(Long id, String username)
    {
        try
        {
            Reservation r = reservationRepo.findById(id).get();
            if (!r.getAccommodationUnit().getAgent().getUsername().equals(username)) {
                throw new BusinessException("You can access only your reservations.");
            }
            return new ReservationDTO(reservationRepo.findById(id).get());
        }
        catch(NoSuchElementException e)
        {
            throw new BusinessException("No reservation with id: " + id + " found.");
        }
    }

    public List<ReservationDTO> updateReservations(Collection<Reservation> reservations, String agentUsername) {

        Collection<Reservation> oldReservations = reservationRepo.findAllByAgentUsername(agentUsername);

        for (Reservation oldReservation: oldReservations) {
            boolean exists = false;

            for (Reservation newReservation: reservations) {
                if (newReservation.getId().equals(oldReservation.getId())) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                reservationRepo.deleteById(oldReservation.getId());
            }
        }

        List<ReservationDTO> reservationDTOs = new ArrayList<>();

        for (Reservation newReservation: reservations) {
            Reservation oldReservation;

            try {
                oldReservation = reservationRepo.findById(newReservation.getId()).get();
            } catch (NoSuchElementException e) {

                if (newReservation.getUsernameReservator().equals(agentUsername)) {
                    newReservation.setSelfReserved(true);
                } else {
                    newReservation.setSelfReserved(false);
                }

                oldReservation = reservationRepo.save(newReservation);
            }

            reservationDTOs.add(new ReservationDTO(oldReservation));
        }

        return reservationDTOs;

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

    public Reservation formReservationForSOAP(ReservationDTO reservationDTO, String usernameAgent) {

        AccommodationUnit au = new AccommodationUnit();
        au.setId(reservationDTO.getAccommodationUnitId());

        Reservation r = new Reservation();

        r.setAccommodationUnit(au);
        r.setStartDate(reservationDTO.getStartDate());
        r.setEndDate(reservationDTO.getEndDate());
        r.setUsernameReservator(usernameAgent);

        return r;

    }

    public Message postMesage(MessageDTO messageDTO, String username) {

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
        } catch (NoSuchElementException e) {
            throw new BusinessException("Reservation with given id does not exist.");
        }

        Agent a = (Agent) tPersonRepository.findOneByUsername(username);

        m.setSender(a);
        m.setUsernameSender(a.getUsername());

        m.setUsernameReceiver(messageDTO.getReceiverUsername());

        m = messageRepository.save(m);

        return m;
    }

    public Message formMessageForSOAP(MessageDTO messageDTO, String username) {

        Message m = new Message();

        if (!messageDTO.getContent().equals("")) {
            m.setContent(messageDTO.getContent());
        } else {
            throw new BusinessException("Message content can not be empty.");
        }

        m.setDate(new Date(System.currentTimeMillis()));

        try {
            Reservation r = reservationRepo.findById(messageDTO.getReservationId()).get();
            m.setIdReservation(r.getId());
        } catch (NoSuchElementException e) {
            throw new BusinessException("Reservation with given id does not exist.");
        }
        m.setUsernameSender(username);

        return m;
    }

    public void updateMessages(List<Message> message) {

        for (Message m: message) {

            try {
                Message oldMessage = messageRepository.findById(m.getId()).get();
            } catch (NoSuchElementException e) {

                if (m.isIsAgentsMessage()) {
                    TPerson person = tPersonRepository.findOneByUsername(m.getUsernameSender());
                    m.setSender(person);
                } else {
                    TPerson person = tPersonRepository.findOneByUsername(m.getUsernameReceiver());
                    m.setReceiver(person);
                }

                Reservation r = reservationRepo.findById(m.getIdReservation()).get();
                m.setReservation(r);

                messageRepository.save(m);
            }


        }

    }

    public ChatDTO getChat(Long id, String username) {


        Reservation r = reservationRepo.findById(id).get();

        if (!r.getAccommodationUnit().getAgent().getUsername().equals(username)) {

            throw new BusinessException("You can access only your messages.");
        }

        List<Message> messages = messageRepository.findAllByReservationId(id);


        ChatDTO chatDTO = new ChatDTO();

        for (Message m: messages) {
            chatDTO.getMessages().add(new MessageDTO(m, r.getUsernameReservator()));
        }

        chatDTO.setUserUsername(r.getUsernameReservator());
        chatDTO.setAgentUsername(username);

        return chatDTO;

    }
}
