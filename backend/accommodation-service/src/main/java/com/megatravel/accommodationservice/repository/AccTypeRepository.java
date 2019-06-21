package com.megatravel.accommodationservice.repository;

import com.megatravel.accommodationservice.model.AccommodationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccTypeRepository extends JpaRepository<AccommodationType, Long> {

    List<AccommodationType> findAll();
}
