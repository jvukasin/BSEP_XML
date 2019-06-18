package com.megatravel.reservationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.reservationservice.model.TPerson;


public interface TPersonRepository extends JpaRepository<TPerson, String> 
{	

}