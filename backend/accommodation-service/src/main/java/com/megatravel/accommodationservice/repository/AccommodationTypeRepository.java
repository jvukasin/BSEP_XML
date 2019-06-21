package com.megatravel.accommodationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.accommodationservice.model.AccommodationType;

import java.util.List;

public interface AccommodationTypeRepository extends JpaRepository<AccommodationType, String> 
{
    List<AccommodationType> findAll();
}
