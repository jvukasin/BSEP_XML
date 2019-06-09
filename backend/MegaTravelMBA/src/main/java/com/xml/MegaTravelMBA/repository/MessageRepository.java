package com.xml.MegaTravelMBA.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xml.MegaTravelMBA.model.Message;


public interface MessageRepository extends JpaRepository<Message, Long> 
{

}