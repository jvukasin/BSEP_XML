package com.xml.MegaTravelAgent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xml.MegaTravelAgent.model.Message;

import java.util.Collection;
import java.util.List;


public interface MessageRepository extends JpaRepository<Message, Long>
{

    List<Message> findAllByReservationId(Long id);

}