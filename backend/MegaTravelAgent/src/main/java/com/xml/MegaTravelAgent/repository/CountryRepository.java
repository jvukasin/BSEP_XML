package com.xml.MegaTravelAgent.repository;

import com.xml.MegaTravelAgent.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {

    Country findOneById(Long id);

}
