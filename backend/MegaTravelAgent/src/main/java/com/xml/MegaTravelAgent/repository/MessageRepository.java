package com.xml.MegaTravelAgent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xml.MegaTravelAgent.model.Message;



public interface MessageRepository extends JpaRepository<Message, Long> 
{

}