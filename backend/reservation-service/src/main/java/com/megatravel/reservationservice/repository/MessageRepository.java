package com.megatravel.reservationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.reservationservice.model.Message;

import java.util.Collection;
import java.util.List;


public interface MessageRepository extends JpaRepository<Message, Long> 
{
    List<Message> findAllByReservationId(Long id);


}