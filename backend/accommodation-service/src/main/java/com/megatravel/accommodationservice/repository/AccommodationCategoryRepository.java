package com.megatravel.accommodationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.megatravel.accommodationservice.model.AccommodationCategory;

public interface AccommodationCategoryRepository extends JpaRepository<AccommodationCategory, Integer> 
{

}
