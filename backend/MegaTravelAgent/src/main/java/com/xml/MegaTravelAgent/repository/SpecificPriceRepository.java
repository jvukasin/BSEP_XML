package com.xml.MegaTravelAgent.repository;

import com.xml.MegaTravelAgent.model.SpecificPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface SpecificPriceRepository extends JpaRepository<SpecificPrice, Long> {

    Collection<SpecificPrice> findAllByAccommodationUnitId(Long id);


}
