package com.megatravel.accommodationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.accommodationservice.model.TPerson;

public interface TPersonRepository extends JpaRepository<TPerson, String> 
{	

}