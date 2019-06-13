package com.megatravel.accommodationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.accommodationservice.model.Message;



public interface MessageRepository extends JpaRepository<Message, Long> 
{

}